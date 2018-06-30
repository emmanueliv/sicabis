/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.RemisionesService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Remisiones;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author kriosoft
 */
public class CierreDevolucionesBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;
    @EJB
    private RemisionesService remisionesService;

    private boolean disabled;

    private List<Remisiones> remisionesList;
    private Mensajes mensaje = new Mensajes();
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private Utilidades util;
    private Usuarios usuarios;

    public CierreDevolucionesBean() {
        remisionesList = new ArrayList<>();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        usuarios = new Usuarios();
    }

    @PostConstruct
    public void init() {
        remisionesList = remisionesService.obtenerRemisionesDevolucionAll();
        if (remisionesList.isEmpty()) {
            disabled = true;
            mensaje.mensaje("Por el momento no se han registrado devoluciones.", "amarillo");
        }
    }

    public void generarCierre() {
        for (Remisiones remision : remisionesList) {
            remision.setActivo(0);
            remisionesService.actualizarRemision(remision);
            bitacoraTareaEstatus.setDescripcion("Modifico remision: RC=" + remision.getRegistroControl() + "");
            bitacoraTareaEstatus.setFecha(new Date());
            bitacoraTareaEstatus.setIdModulos(3);
            bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        }
        mensaje.mensaje("Se han cancelado las remisiones.", "verde");
    }

    public List<Remisiones> getRemisionesList() {
        return remisionesList;
    }

    public void setRemisionesList(List<Remisiones> remisionesList) {
        this.remisionesList = remisionesList;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

}
