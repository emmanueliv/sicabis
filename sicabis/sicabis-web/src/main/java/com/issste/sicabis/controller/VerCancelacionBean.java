/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.CancelacionesService;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.modelo.Cancelaciones;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.utils.Mensajes;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author fabianvr
 */
@ManagedBean(name = "verCancelacionBean")
@ViewScoped
public class VerCancelacionBean {

    @EJB
    private CancelacionesService cancelacionesService;
    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;
    private Cancelaciones cancelaciones;
    private DetalleOrdenSuministro detalle;
    private Mensajes mensaje = new Mensajes();
    private Integer seleccionaConsulta;
    private String criterioBusqueda;
    private List<Cancelaciones> cancelacionRescisionList;
    private boolean verMensaje;

    public VerCancelacionBean() {

    }

    public void selecciona() {
        if (criterioBusqueda != null) {
            cancelacionRescisionList = cancelacionesService.cancelacionesByOrden(criterioBusqueda);
            if (cancelacionRescisionList == null) {
                verMensaje = true;
                mensaje.mensaje("No Existen Claves con Porcentaje de Incumplimineto", "amarillo");
            }
        } else {
            verMensaje = true;
            mensaje.mensaje("Favor de Ingresar Numero de Orden", "amarillo");
        }
    }

    public String verDettaleCncelacionRescision() throws IOException {
        return "cancelacionResicion.xhtml?faces-redirect=true&idCancelacion=" + this.cancelaciones.getIdCancelacion();
    }

    public Integer getSeleccionaConsulta() {
        return seleccionaConsulta;
    }

    public void setSeleccionaConsulta(Integer seleccionaConsulta) {
        this.seleccionaConsulta = seleccionaConsulta;
    }

    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    public List<Cancelaciones> getCancelacionRescisionList() {
        return cancelacionRescisionList;
    }

    public void setCancelacionRescisionList(List<Cancelaciones> cancelacionRescisionList) {
        this.cancelacionRescisionList = cancelacionRescisionList;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
    }

    public Cancelaciones getCancelaciones() {
        return cancelaciones;
    }

    public void setCancelaciones(Cancelaciones cancelaciones) {
        this.cancelaciones = cancelaciones;
    }

        

}
