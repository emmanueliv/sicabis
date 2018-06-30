package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
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
@ManagedBean(name = "cancelacionRescisionBean")
@ViewScoped
public class CancelacionRescisionBean1 {

    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;
    private DetalleOrdenSuministro detalle;
    private Mensajes mensaje = new Mensajes();
    private Integer seleccionaConsulta;
    private String criterioBusqueda;
    private List<DetalleOrdenSuministro> cancelacionRescisionList;
    private boolean verMensaje;

    public CancelacionRescisionBean1() {

    }

    public void selecciona() {
        if (criterioBusqueda != null) {
            cancelacionRescisionList = detalleOrdenSuministroService.cancelacionesRescisiones(criterioBusqueda);
            if (cancelacionRescisionList == null) {
                verMensaje = true;
                mensaje.mensaje("No Existen Claves con Porcentaje de Incumplimineto", "amarillo");
            }
        } else {
            verMensaje = true;
            mensaje.mensaje("Favor de Ingresar Número de Orden", "amarillo");
        }
    }
    
    

    public String verDettaleCncelacionRescision() throws IOException {
        return "penas.xhtml?faces-redirect=true&idDetalle=" + this.detalle.getIdDetalleOrdenSuministro();
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

    public List<DetalleOrdenSuministro> getCancelacionRescisionList() {
        return cancelacionRescisionList;
    }

    public void setCancelacionRescisionList(List<DetalleOrdenSuministro> cancelacionRescisionList) {
        this.cancelacionRescisionList = cancelacionRescisionList;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
    }

    public DetalleOrdenSuministro getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleOrdenSuministro detalle) {
        this.detalle = detalle;
    }

}
