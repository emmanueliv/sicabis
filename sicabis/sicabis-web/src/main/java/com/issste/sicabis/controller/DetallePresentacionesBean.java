/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.PresentacionService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Presentacion;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author kriosoft
 */
public class DetallePresentacionesBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private PresentacionService presentacionesService;

    private String descPresentacion;

    private Usuarios usuarioLogin;
    private Presentacion presentacion;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetallePresentacionesBean() {
        presentacion = new Presentacion();
        usuarioLogin = new Usuarios();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();

    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        presentacion = (Presentacion) util.getSessionAtributte("presentacion");
    }

    public void guardarPresentacion() {
        descPresentacion = presentacion.getPresentacion();
        if (valida()) {
            if (validarAgregar()) {
                presentacion.setActivo(1);
                presentacion.setFechaAlta(new Date());
                presentacion.setUsuarioAlta(usuarioLogin.getUsuario());
                presentacionesService.guardarPresentacion(presentacion);
                bitacoraTareaEstatus.setDescripcion("Actualizar presentacion:" + descPresentacion + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                presentacion = new Presentacion();
                mensaje.mensaje("La presentacion " + descPresentacion + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("La presentacion " + descPresentacion + " ya existe.", "rojo");
            }
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (presentacion.getPresentacion() == null || presentacion.getPresentacion().equals("")) {
            mensaje.mensaje("Debes capturar la descripción de la presentación", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        Presentacion p = presentacionesService.presentacionByDesc(descPresentacion);
        if (p != null) {
            if (Objects.equals(p.getIdPresentacion(), presentacion.getIdPresentacion())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getDescPresentacion() {
        return descPresentacion;
    }

    public void setDescPresentacion(String descPresentacion) {
        this.descPresentacion = descPresentacion;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Presentacion getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(Presentacion presentacion) {
        this.presentacion = presentacion;
    }

}
