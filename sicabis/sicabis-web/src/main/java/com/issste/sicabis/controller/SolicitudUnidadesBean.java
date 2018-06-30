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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Toshiba Manolo
 */
@ManagedBean(name = "solicitudUnidadesBean")
@ViewScoped
public class SolicitudUnidadesBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Solicitudes solicitudConsulta=new Solicitudes();
    private List<Solicitudes> listaSolicitudes;
    private Utilidades util = new Utilidades();
    private Usuarios usuarios;
    
    private Integer idTipoSolicitud;
    
    @EJB
    private SolicitudesService solicitudesServices;
    
    @PostConstruct
    public void init() {
        System.out.println("Entro a SolicitudUnidadesBean");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
        .getRequest();
        idTipoSolicitud = Integer.parseInt(request.getParameter("idTipoSolicitud"));   
        solicitudConsulta.setNumeroSolicitud("");
        listaSolicitudes = new ArrayList();
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
    }
    
     public void buscarSolicitud() {
        if(idTipoSolicitud==PlaneacionEstatusEnum.IDSOLICITUD_MENSUAL.getValue()){
           listaSolicitudes = solicitudesServices.buscaSolicitudesMensualesExtraordinarias();
        }else{
            listaSolicitudes = solicitudesServices.buscaSolicitudesPorTipoSolicitud(idTipoSolicitud);
        }        
        //listaSolicitudes = solicitudesServices.buscaSolicitudesPorTipoSolicitudUnidadMedica(idTipoSolicitud,usuarios.getIdUsuarioSuperior());
     }
     
    public String irSolicitudSeleccionadaDetalle(Solicitudes solicitudSeleccionada) {
        util.setContextAtributte("idSolicitud", solicitudSeleccionada.getIdSolicitud());
        util.setContextAtributte("ingresoPlaneacion", false);
        return "solicitudUnidadesDetalle.xhtml?faces-redirect=true";

    }

    public Solicitudes getSolicitudConsulta() {
        return solicitudConsulta;
    }

    public void setSolicitudConsulta(Solicitudes solicitudConsulta) {
        this.solicitudConsulta = solicitudConsulta;
    }

    public List<Solicitudes> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(List<Solicitudes> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public Integer getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    public void setIdTipoSolicitud(Integer idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
    }
    
    
    
}
