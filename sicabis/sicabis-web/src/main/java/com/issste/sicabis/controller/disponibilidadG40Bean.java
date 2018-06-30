package com.issste.sicabis.controller;

import com.google.gson.Gson;
import com.issste.sicabis.DTO.MapaDTO;
import com.issste.sicabis.ejb.ln.ColorPorcentajeService;
import com.issste.sicabis.ejb.ln.DelegacionesService;
import com.issste.sicabis.ejb.ln.MapaEjecutivoDispG40HistoricoService;
import com.issste.sicabis.ejb.ln.PorcentajeDelegacionService;
import com.issste.sicabis.ejb.modelo.ColorPorcentaje;
import com.issste.sicabis.ejb.modelo.Indicador;
import com.issste.sicabis.ejb.modelo.MapaEjecutivoDispG40Historico;
import com.issste.sicabis.ejb.modelo.PorcentajeDelegacion;
import com.issste.sicabis.ejb.service.silodisa.controller.MapaEjecutivoDispDelegacionesController;
import com.issste.sicabis.ejb.service.silodisa.controller.MapaEjecutivoDispG40Controller;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispG40;
import com.issste.sicabis.ejb.service.silodisa.service.MapaEjecutivoDispG40Service;
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

public class disponibilidadG40Bean {

    @EJB
    private DelegacionesService delegacionesService;

    @EJB
    private MapaEjecutivoDispG40HistoricoService mapaEjecutivoDispG40HistoricoService;

    @EJB
    private MapaEjecutivoDispG40Controller mapaEjecutivoDispG40Controller;

    @EJB
    private MapaEjecutivoDispG40Service mapaEjecutivoDispG40Service;

    @EJB
    private ColorPorcentajeService colorPorcentajeService;

    private List<MapaDTO> mapaPorcentajes;
    private List<ColorPorcentaje> listacolores;
    private String datosEstadosFormatoJSON;
    private String fecha;
    private List<MapaEjecutivoDispG40> dispG40List;
    private Mensajes mensaje;

    private Integer clavesAut;
    private Integer clavesDisp;
    private BigDecimal disponibilidad;
    private Date fechaHistorico;

    public disponibilidadG40Bean() {
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
        if (!formateaFecha(fechaHistorico).equals(formateaFecha(new Date())) && mapaEjecutivoDispG40HistoricoService.getByFechaIngreso(fechaHistorico) != null) {
            fecha = formateaFecha(fechaHistorico);
        } else {
            fecha = formateaFecha(mapaPorcentajes.get(0).getFechaActualizacion());
        }
        datosEstadosFormatoJSON = listaAJson(mapaPorcentajes);
    }

    public void actualizaServicio() {
        try {
            mapaEjecutivoDispG40Controller.obtenerMapaEjecutivoDispG40(1);
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
        List<MapaEjecutivoDispG40Historico> disDelegacionHistoricoList = new ArrayList<>();
        List<MapaEjecutivoDispG40> dispG40List = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        disDelegacionHistoricoList = mapaEjecutivoDispG40HistoricoService.getByFechaIngreso(fechaHistorico);
        if (!format.format(fechaHistorico).equals(format.format(new Date()))) {
            MapaEjecutivoDispG40 med;
            if (disDelegacionHistoricoList == null) {
                mensaje.mensaje("No existen historico con la fecha digitada.", "amarillo");
                dispG40List = mapaEjecutivoDispG40Service.getAll();
            } else {
                for (MapaEjecutivoDispG40Historico iterador : disDelegacionHistoricoList) {
                    med = new MapaEjecutivoDispG40();
                    med.setActivo(1);
                    med.setClavesAutorizadas(iterador.getClavesAutorizadas());
                    med.setClavesDisponibles(iterador.getClavesDisponibles());
                    med.setDisponibilidad(iterador.getDisponibilidad());
                    med.setEstado(iterador.getEstado());
                    med.setFecha(iterador.getFecha());
                    med.setFechaIngreso(iterador.getFechaIngreso());
                    med.setIdDelegacion(delegacionesService.obtenerDelegacionporId(iterador.getIdDelegacion()));
                    med.setIdIndicador(new Indicador(3));
                    med.setNombre(iterador.getNombre());
                    med.setUmu(iterador.getUmu());
                    dispG40List.add(med);
                }
                mensaje.mensaje("Se cargo el historico del dia " + formateaFecha(fechaHistorico) + ".", "verde");
            }
        } else {
            dispG40List = mapaEjecutivoDispG40Service.getAll();
        }
        this.dispG40List = dispG40List;
        List<MapaDTO> listaG40 = new ArrayList();
        clavesAut = 0;
        clavesDisp = 0;
        disponibilidad = BigDecimal.ZERO;
        if (dispG40List != null) {
            for (MapaEjecutivoDispG40 medg : dispG40List) {
                MapaDTO dto = new MapaDTO();
                if (medg.getIdDelegacion().getNombreDelegacion().equals("CIUDAD DE MEXICO")) {
                    dto.setNombreE("DISTRITO FEDERAL");
                } else {
                    dto.setNombreE(medg.getIdDelegacion().getNombreDelegacion());
                }
                dto.setFechaActualizacion(medg.getFecha());
                dto.setPorcentaje(medg.getDisponibilidad());
                dto.setColorE(colorByRango(medg.getDisponibilidad()));
                listaG40.add(dto);
                clavesAut = clavesAut + medg.getClavesAutorizadas();
                clavesDisp = clavesDisp + medg.getClavesDisponibles();
                disponibilidad = disponibilidad.add(medg.getDisponibilidad());
            }
            disponibilidad = disponibilidad.divide(new BigDecimal(dispG40List.size()), 2, RoundingMode.HALF_UP);
        }
        return listaG40;
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

    public List<MapaEjecutivoDispG40> getDispG40List() {
        return dispG40List;
    }

    public void setDispG40List(List<MapaEjecutivoDispG40> dispG40List) {
        this.dispG40List = dispG40List;
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
