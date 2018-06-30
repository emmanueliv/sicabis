/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.SolicitudesService;
import com.issste.sicabis.ejb.modelo.Solicitudes;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.PlaneacionEstatusEnum;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Toshiba Manolo
 */
@ManagedBean(name = "consultaSolicitudesBean")
@ViewScoped
public class PlaneacionConsultaSolicitudesBean implements Serializable {
 
    private List<Solicitudes> listSolicitudesMensuales;
    private List<Solicitudes> listSolicitudesMensualesFilter;
    private List<Solicitudes> listSolicitudesExtraordinarias;
    
    private List<Solicitudes> listSolicitudesSoporte;
    private List<Solicitudes> listSolicitudesSoporteFilter;
    
    private Utilidades util = new Utilidades();
    private Usuarios usuarios;
    
    @EJB
    private SolicitudesService solicitudesServices;
    
    @PostConstruct
    public void init() {
       listSolicitudesMensuales= solicitudesServices.buscaSolicitudesMensualesExtraordinarias();
       listSolicitudesSoporte= solicitudesServices.buscaSolicitudesPorTipoSolicitud(PlaneacionEstatusEnum.IDSOLICITUD_SOPORTE.getValue());
    }
    
    public String irSolicitudSeleccionadaDetalle(Solicitudes solicitudSeleccionada) {
        util.setContextAtributte("idSolicitud", solicitudSeleccionada.getIdSolicitud());
        util.setContextAtributte("ingresoPlaneacion", true);
        return "solicitudUnidadesDetalle.xhtml?faces-redirect=true";

    }

    public List<Solicitudes> getListSolicitudesMensuales() {
        return listSolicitudesMensuales;
    }

    public void setListSolicitudesMensuales(List<Solicitudes> listSolicitudesMensuales) {
        this.listSolicitudesMensuales = listSolicitudesMensuales;
    }

    public List<Solicitudes> getListSolicitudesMensualesFilter() {
        return listSolicitudesMensualesFilter;
    }

    public void setListSolicitudesMensualesFilter(List<Solicitudes> listSolicitudesMensualesFilter) {
        this.listSolicitudesMensualesFilter = listSolicitudesMensualesFilter;
    }

    public List<Solicitudes> getListSolicitudesSoporte() {
        return listSolicitudesSoporte;
    }

    public void setListSolicitudesSoporte(List<Solicitudes> listSolicitudesSoporte) {
        this.listSolicitudesSoporte = listSolicitudesSoporte;
    }

    public List<Solicitudes> getListSolicitudesSoporteFilter() {
        return listSolicitudesSoporteFilter;
    }

    public void setListSolicitudesSoporteFilter(List<Solicitudes> listSolicitudesSoporteFilter) {
        this.listSolicitudesSoporteFilter = listSolicitudesSoporteFilter;
    }

    public List<Solicitudes> getListSolicitudesExtraordinarias() {
        return listSolicitudesExtraordinarias;
    }

    public void setListSolicitudesExtraordinarias(List<Solicitudes> listSolicitudesExtraordinarias) {
        this.listSolicitudesExtraordinarias = listSolicitudesExtraordinarias;
    }
    
        
     
    
}
