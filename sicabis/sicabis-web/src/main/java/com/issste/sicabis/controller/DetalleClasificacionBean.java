/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ClasificacionService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Clasificacion;
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
public class DetalleClasificacionBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private ClasificacionService clasificacionService;

    private String nombreClasificacion;
    private Usuarios usuarioLogin;
    private Clasificacion clasificacion;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleClasificacionBean() {
        usuarioLogin = new Usuarios();
        clasificacion = new Clasificacion();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        clasificacion = (Clasificacion) util.getSessionAtributte("clasificacion");
    }

    public void guardarClasificacion() {
        nombreClasificacion = clasificacion.getDescripcion();
        if (valida()) {
            if (existeClasificacion()) {
                clasificacion.setActivo(1);
                clasificacion.setFechaAlta(new Date());
                clasificacion.setUsuarioAlta(usuarioLogin.getUsuario());
                clasificacionService.guardarClasificacion(clasificacion);
                bitacoraTareaEstatus.setDescripcion("Actualiza clasifiación:" + nombreClasificacion + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                clasificacion = new Clasificacion();
                mensaje.mensaje("La clasificacion " + nombreClasificacion + " se ha modificado correctamente.", "verde");
            } else {
                mensaje.mensaje("La clasificacion " + nombreClasificacion + " ya existe.", "rojo");
            }
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (clasificacion.getDescripcion() == null || clasificacion.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar la descripción de la clasificación", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeClasificacion() {
        Clasificacion c = clasificacionService.obtenerClasificacionByNombre(nombreClasificacion);
        if (c != null) {
            if (Objects.equals(c.getIdClasificacion(), clasificacion.getIdClasificacion())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombreClasificacion() {
        return nombreClasificacion;
    }

    public void setNombreClasificacion(String nombreClasificacion) {
        this.nombreClasificacion = nombreClasificacion;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

}
