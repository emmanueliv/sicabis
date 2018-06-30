package com.issste.sicabis.controller;

import com.google.gson.Gson;
import com.issste.sicabis.DTO.MapaDTO;
import com.issste.sicabis.ejb.ln.ColorPorcentajeService;
import com.issste.sicabis.ejb.ln.DelegacionesService;
import com.issste.sicabis.ejb.ln.MapaEjecutivoDispDelegacionesHistoricoService;
import com.issste.sicabis.ejb.modelo.ColorPorcentaje;
import com.issste.sicabis.ejb.modelo.Indicador;
import com.issste.sicabis.ejb.modelo.MapaEjecutivoDispDelegacionesHistorico;
import com.issste.sicabis.ejb.service.silodisa.controller.MapaEjecutivoDispDelegacionesController;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispDelegaciones;
import com.issste.sicabis.ejb.service.silodisa.service.MapaEjecutivoDispDelegacionesService;
import com.issste.sicabis.utils.Mensajes;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

public class DisponibilidadDelegacionesBean {

    @EJB
    private DelegacionesService delegacionesService;

    @EJB
    private MapaEjecutivoDispDelegacionesHistoricoService mapaEjecutivoDispDelegacionesHistoricoService;

    @EJB
    private MapaEjecutivoDispDelegacionesController mapaEjecutivoDispDelegacionesController;

    @EJB
    private MapaEjecutivoDispDelegacionesService mapaEjecutivoDispDelegacionesService;

    @EJB
    private ColorPorcentajeService colorPorcentajeService;

    private List<MapaDTO> mapaPorcentajes;
    private List<ColorPorcentaje> listacolores;
    private String datosEstadosFormatoJSON;
    private String fecha;
    private List<MapaEjecutivoDispDelegaciones> dispDelegacionesList;
    private Mensajes mensaje;

    private Integer clavesAut;
    private Integer clavesDisp;
    private BigDecimal disponibilidad;
    private Date fechaHistorico;

    public DisponibilidadDelegacionesBean() {
        mapaPorcentajes = new ArrayList();
        fechaHistorico = new Date();
        mensaje = new Mensajes();

    }

    @PostConstruct
    public void init() {
        listacolores = colorPorcentajeService.colorPorcentajeAllActivos();
        mapaPorcentajes = listaMapa();
        Date fechaActual = new Date();
//        if (mapaPorcentajes.isEmpty()) {
//            this.actualizaServicio();
//        } else {
//            if (fechaActual.compareTo(mapaPorcentajes.get(0).getFechaActualizacion()) < 0) {
//                this.actualizaServicio();
//            }
//            if (mapaPorcentajes.size() < 32) {
//                this.actualizaServicio();
//            }
//        }
        mapaPorcentajes = listaMapa();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        if (!format.format(fechaHistorico).equals(format.format(new Date())) && mapaEjecutivoDispDelegacionesHistoricoService.getByFechaIngreso(fechaHistorico) != null) {
            fecha = formateaFecha(fechaHistorico);
        } else {
            fecha = formateaFecha(mapaPorcentajes.get(0).getFechaActualizacion());
        }
        datosEstadosFormatoJSON = listaAJson(mapaPorcentajes);
    }

    public void actualizaServicio() {
        try {
            mapaEjecutivoDispDelegacionesController.obtenerMapaEjecutivoDispDelegacion(1);
        } catch (IOException ex) {
            Logger.getLogger(MapaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Se evalua que el rango en el cual se encuentra el porcentaje seteado a los estados
    // y en base a ello se setea un color con el cual se pintara el estado
    // el resultado se devuelve en formato JSON y es posteriormente parseado en javascript 
    // para su facil manipulacion 
    public String listaAJson(List<MapaDTO> listaDtos) {
        Gson gson = new Gson();
        String strJSON = gson.toJson(listaDtos);
        return strJSON;
    }

    // Devuelve una lista de porcentajes a los cuales va asignado un color
    public String colorByRango(BigDecimal porcentaje) {
        String color = "";
        listacolores = colorPorcentajeService.colorPorcentajeAllActivos();
        for (ColorPorcentaje cp : listacolores) {
            BigDecimal porcIniTrun = truncar(cp.getPorcentajeInicial());
            BigDecimal porcFinTrun = truncar(cp.getPorcentajeFinal());
            BigDecimal porcentajeTrun = truncar(porcentaje);
            if (porcentajeTrun.compareTo(porcIniTrun) >= 0 && porcentajeTrun.compareTo(porcFinTrun) <= 0) {
                color = cp.getHexColor();
                break;
            }
        }
        return color;
    }

    public String formateaFecha(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
        return formateador.format(fecha);
    }

    public BigDecimal truncar(BigDecimal numero) {
        return numero.setScale(2, RoundingMode.FLOOR);
    }

    //Devuelve una lista de todos los estados de la republica y el porcentaje que le corresponde al estado
    private List<MapaDTO> listaMapa() {
        List<MapaEjecutivoDispDelegacionesHistorico> disDelegacionHistoricoList = new ArrayList<>();
        List<MapaEjecutivoDispDelegaciones> dispDelegacionesList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        disDelegacionHistoricoList = mapaEjecutivoDispDelegacionesHistoricoService.getByFechaIngreso(fechaHistorico);
        if (!format.format(fechaHistorico).equals(format.format(new Date()))) {
            MapaEjecutivoDispDelegaciones medh;
            if (disDelegacionHistoricoList == null) {
                mensaje.mensaje("No existen historico con la fecha digitada.", "amarillo");
                dispDelegacionesList = mapaEjecutivoDispDelegacionesService.getAll();
            } else {
                for (MapaEjecutivoDispDelegacionesHistorico iterador : disDelegacionHistoricoList) {
                    medh = new MapaEjecutivoDispDelegaciones();
                    medh.setActivo(1);
                    medh.setClavesAutorizadas(iterador.getClavesAutorizadas());
                    medh.setClavesDisponibles(iterador.getClavesDisponibles());
                    medh.setDelegacion(iterador.getDelegacion());
                    medh.setDisponibilidad(iterador.getDisponibilidad());
                    medh.setEstado(iterador.getEstado());
                    medh.setFecha(iterador.getFecha());
                    medh.setFechaIngreso(iterador.getFechaIngreso());
                    medh.setIdDelegacion(delegacionesService.obtenerDelegacionporId(iterador.getIdDelegacion()));
                    medh.setIdIndicador(new Indicador(2));
                    dispDelegacionesList.add(medh);
                }
                mensaje.mensaje("Se cargo el historico del dia "+formateaFecha(fechaHistorico)+".", "verde");
            }
        } else {
            dispDelegacionesList = mapaEjecutivoDispDelegacionesService.getAll();
        }
        this.dispDelegacionesList = dispDelegacionesList;
        List<MapaDTO> listaDelegaciones = new ArrayList();
        clavesAut = 0;
        clavesDisp = 0;
        disponibilidad = BigDecimal.ZERO;
        if (dispDelegacionesList != null) {
            for (MapaEjecutivoDispDelegaciones medd : dispDelegacionesList) {
                MapaDTO dto = new MapaDTO();
                if (medd.getIdDelegacion().getNombreDelegacion().equals("CIUDAD DE MEXICO")) {
                    dto.setNombreE("DISTRITO FEDERAL");
                } else {
                    dto.setNombreE(medd.getIdDelegacion().getNombreDelegacion());
                }
                dto.setFechaActualizacion(medd.getFecha());
                dto.setPorcentaje(medd.getDisponibilidad());
                dto.setColorE(colorByRango(medd.getDisponibilidad()));
                listaDelegaciones.add(dto);
                clavesAut = clavesAut + medd.getClavesAutorizadas();
                clavesDisp = clavesDisp + medd.getClavesDisponibles();
                disponibilidad = disponibilidad.add(medd.getDisponibilidad());
            }
            disponibilidad = disponibilidad.divide(new BigDecimal(dispDelegacionesList.size()), 2, RoundingMode.HALF_UP);
        }
        return listaDelegaciones;
    }

    public void buscarPorFecha() {
        init();
    }

    public List<MapaDTO> getMapaPorcentajes() {
        return mapaPorcentajes;
    }

    public void setMapaPorcentajes(List<MapaDTO> mapaPorcentajes) {
        this.mapaPorcentajes = mapaPorcentajes;
    }

    public List<ColorPorcentaje> getListacolores() {
        return listacolores;
    }

    public void setListacolores(List<ColorPorcentaje> listacolores) {
        this.listacolores = listacolores;
    }

    public String getDatosEstadosFormatoJSON() {
        return datosEstadosFormatoJSON;
    }

    public void setDatosEstadosFormatoJSON(String datosEstadosFormatoJSON) {
        this.datosEstadosFormatoJSON = datosEstadosFormatoJSON;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<MapaEjecutivoDispDelegaciones> getDispDelegacionesList() {
        return dispDelegacionesList;
    }

    public void setDispDelegacionesList(List<MapaEjecutivoDispDelegaciones> dispDelegacionesList) {
        this.dispDelegacionesList = dispDelegacionesList;
    }

    public Integer getClavesAut() {
        return clavesAut;
    }

    public void setClavesAut(Integer clavesAut) {
        this.clavesAut = clavesAut;
    }

    public Integer getClavesDisp() {
        return clavesDisp;
    }

    public void setClavesDisp(Integer clavesDisp) {
        this.clavesDisp = clavesDisp;
    }

    public BigDecimal getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(BigDecimal disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Date getFechaHistorico() {
        return fechaHistorico;
    }

    public void setFechaHistorico(Date fechaHistorico) {
        this.fechaHistorico = fechaHistorico;
    }

}
