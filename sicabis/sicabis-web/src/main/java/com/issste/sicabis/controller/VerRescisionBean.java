package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.CancelacionesService;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.ln.RescisionesService;
import com.issste.sicabis.ejb.modelo.Cancelaciones;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Rescisiones;
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
@ManagedBean(name = "verRescisionBean")
@ViewScoped
public class VerRescisionBean {
    @EJB
    private RescisionesService rescisionesService;

    
    private Rescisiones rescisiones;
    private Mensajes mensaje = new Mensajes();
    private Integer seleccionaConsulta;
    private String criterioBusqueda;
    private List<Rescisiones> cancelacionRescisionList;
    private boolean verMensaje;

    public VerRescisionBean() {
    }

    public void selecciona() {
        if (criterioBusqueda != null) {
            cancelacionRescisionList = rescisionesService.recisionesByOrden(criterioBusqueda);
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
        return "rescisiones.xhtml?faces-redirect=true&idRescision=" + this.rescisiones.getIdRescision();
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

    public List<Rescisiones> getCancelacionRescisionList() {
        return cancelacionRescisionList;
    }

    public void setCancelacionRescisionList(List<Rescisiones> cancelacionRescisionList) {
        this.cancelacionRescisionList = cancelacionRescisionList;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
    }

    public Rescisiones getRescisiones() {
        return rescisiones;
    }

    public void setRescisiones(Rescisiones rescisiones) {
        this.rescisiones = rescisiones;
    }

    

}
