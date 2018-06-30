/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.TareasService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Tareas;
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
public class DetalleTareasBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TareasService tareasService;

    private String nombreTarea;
    private Usuarios usuarioLogin;
    private Tareas tarea;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleTareasBean() {
        usuarioLogin = new Usuarios();
        tarea = new Tareas();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        tarea = (Tareas) util.getSessionAtributte("tarea");
    }

    public void guardarTarea() {
        nombreTarea = tarea.getDescripcion();
        if (validar()) {
            if (existeTarea()) {
                tarea.setFechaAlta(new Date());
                tarea.setUsuarioAlta(usuarioLogin.getUsuario());
                tareasService.guardarTarea(tarea);
                bitacoraTareaEstatus.setDescripcion("Actualizar tarea:" + nombreTarea + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                tarea = new Tareas();
                mensaje.mensaje("La tarea " + nombreTarea + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("La tarea " + nombreTarea + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (tarea.getDescripcion() == null || tarea.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar la descripción de la tarea", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeTarea() {
        Tareas t = tareasService.obtenerTareaByNombre(nombreTarea);
        if (t != null) {
            if (Objects.equals(t.getIdTarea(), tarea.getIdTarea())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Tareas getTarea() {
        return tarea;
    }

    public void setTarea(Tareas tarea) {
        this.tarea = tarea;
    }

}
