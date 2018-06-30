package com.issste.sicabis.controller;

import com.google.gson.Gson;
import com.issste.sicabis.DTO.MapaDTO;
import com.issste.sicabis.ejb.ln.ColorPorcentajeService;
import com.issste.sicabis.ejb.ln.DelegacionesService;
import com.issste.sicabis.ejb.ln.PorcentajeDelegacionHistoricoService;
import com.issste.sicabis.ejb.modelo.PorcentajeDelegacion;
import com.issste.sicabis.ejb.ln.PorcentajeDelegacionService;
import com.issste.sicabis.ejb.modelo.ColorPorcentaje;
import com.issste.sicabis.ejb.modelo.Indicador;
import com.issste.sicabis.ejb.modelo.PorcentajeDelegacionHistorico;
import com.issste.sicabis.ejb.service.silodisa.controller.MapaEjecutivoDisponibilidadEstadosController;
import com.issste.sicabis.utils.Mensajes;
import java.io.IOException;
import java.io.Serializable;
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

/**
 *
 * @author 6JWBBG2
 */
public class MapaBean implements Serializable {

    @EJB
    private DelegacionesService delegacionesService;

    @EJB
    private PorcentajeDelegacionHistoricoService porcentajeDelegacionHistoricoService;

    @EJB
    private MapaEjecutivoDisponibilidadEstadosController mapaEjecutivoDisponibilidadEstadosController;

    @EJB
    private ColorPorcentajeService colorPorcentajeService;

    @EJB
    private PorcentajeDelegacionService porcentajeDelegacionService;

    private List<MapaDTO> mapaPorcentajes;
    private List<ColorPorcentaje> listacolores;
    private String datosEstadosFormatoJSON;
    private String fecha;
    private List<PorcentajeDelegacion> listaPorcentajeDel;
    private Mensajes mensaje;

    private Integer clavesAut;
    private Integer clavesDisp;
    private BigDecimal disponibilidad;
    private Date fechaHistorico;

    public MapaBean() {
        mapaPorcentajes = new ArrayList();
        mensaje = new Mensajes();
        fechaHistorico = new Date();
    }

    @PostConstruct
    public void init() {
        listacolores = colorPorcentajeService.colorPorcentajeAllActivos();
        mapaPorcentajes = listaPorcentajeDelegacion();
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
        mapaPorcentajes = listaPorcentajeDelegacion();
        if (mapaPorcentajes != null && mapaPorcentajes.size() > 0) {
            fecha = formateaFecha(mapaPorcentajes.get(0).getFechaActualizacion());
        }
        if (!formateaFecha(fechaHistorico).equals(formateaFecha(new Date())) && porcentajeDelegacionHistoricoService.getByFechaIngreso(fechaHistorico) != null) {
            fecha = formateaFecha(fechaHistorico);
        }
        datosEstadosFormatoJSON = listaAJson(mapaPorcentajes);
    }

    public void actualizaServicio() {
        try {
            mapaEjecutivoDisponibilidadEstadosController.obtenerPorcentajePorEstado(1);
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

    //Devuelve una lista de todos los estados de la republica y el porcentaje que le corresponde al estado
    private List<MapaDTO> listaPorcentajeDelegacion() {
        List<PorcentajeDelegacion> listaDelegaciones = new ArrayList<>();
        List<PorcentajeDelegacionHistorico> listaDelegacionesHistorico = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        listaDelegacionesHistorico = porcentajeDelegacionHistoricoService.getByFechaIngreso(fechaHistorico);
        if (!format.format(fechaHistorico).equals(format.format(new Date()))) {
            PorcentajeDelegacion pd;
            if (listaDelegacionesHistorico == null) {
                mensaje.mensaje("No existen historico con la fecha digitada.", "amarillo");
                listaDelegaciones = porcentajeDelegacionService.getListaPorcentajeDelegacion();
            } else {
                for (PorcentajeDelegacionHistorico iterator : listaDelegacionesHistorico) {
                    pd = new PorcentajeDelegacion();
                    pd.setClavesDPN(iterator.getClavesDpn());
                    pd.setClavesEnUMU(iterator.getClavesUmu());
                    pd.setFechaActualizacion(iterator.getFechaActualizacion());
                    pd.setIdDelegacion(iterator.getIdDelegacion());
                    pd.setIdIndicador(iterator.getIdIndicador());
                    pd.setPorcentaje(iterator.getPorcentaje());
                    listaDelegaciones.add(pd);
                }
                mensaje.mensaje("Se cargo el historico del dia " + formateaFecha(fechaHistorico) + ".", "verde");
            }
        } else {
            listaDelegaciones = porcentajeDelegacionService.getListaPorcentajeDelegacion();
        }
        listaPorcentajeDel = listaDelegaciones;
        List<MapaDTO> listaEstados = new ArrayList();
        clavesAut = 0;
        clavesDisp = 0;
        disponibilidad = BigDecimal.ZERO;
        if (listaDelegaciones != null) {
            for (PorcentajeDelegacion item : listaDelegaciones) {
                MapaDTO dto = new MapaDTO();
                if (item.getIdDelegacion().getNombreDelegacion().equals("CIUDAD DE MEXICO")) {
                    dto.setNombreE("DISTRITO FEDERAL");
                } else {
                    dto.setNombreE(item.getIdDelegacion().getNombreDelegacion());
                }
                dto.setFechaActualizacion(item.getFechaActualizacion());
                dto.setPorcentaje(item.getPorcentaje());
                dto.setColorE(colorByRango(item.getPorcentaje()));
                listaEstados.add(dto);
                clavesAut = clavesAut + Integer.parseInt(item.getClavesDPN());
                clavesDisp = clavesDisp + Integer.parseInt(item.getClavesEnUMU());
                disponibilidad = disponibilidad.add(item.getPorcentaje());
            }
            if (!listaDelegaciones.isEmpty()) {
                disponibilidad = disponibilidad.divide(new BigDecimal(listaDelegaciones.size()), 2, RoundingMode.HALF_UP);
            }
        }
        return listaEstados;
    }

    public BigDecimal truncar(BigDecimal numero) {
        return numero.setScale(2, RoundingMode.FLOOR);
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

    public String getDatosEstadosFormatoJSON() {
        return datosEstadosFormatoJSON;
    }

    public void setDatosEstadosFormatoJSON(String datosEstadosFormatoJSON) {
        this.datosEstadosFormatoJSON = datosEstadosFormatoJSON;
    }

    public List<ColorPorcentaje> getListacolores() {
        return listacolores;
    }

    public void setListacolores(List<ColorPorcentaje> listacolores) {
        this.listacolores = listacolores;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<PorcentajeDelegacion> getListaPorcentajeDel() {
        return listaPorcentajeDel;
    }

    public void setListaPorcentajeDel(List<PorcentajeDelegacion> listaPorcentajeDelegacion) {
        this.listaPorcentajeDel = listaPorcentajeDelegacion;
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
