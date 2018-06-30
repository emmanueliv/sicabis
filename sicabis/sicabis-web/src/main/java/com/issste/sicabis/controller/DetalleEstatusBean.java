/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.TareasService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Tareas;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author kriosoft
 */
public class DetalleEstatusBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private EstatusService estatusService;

    @EJB
    private TareasService tareasService;

    private String nombreStatus;
    private Usuarios usuarioLogin;
    private Estatus estatus;
    private List<Tareas> tareasList;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleEstatusBean() {
        usuarioLogin = new Usuarios();
        estatus = new Estatus();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        estatus = (Estatus) util.getSessionAtributte("estatus");
        tareasList = tareasService.obtenerTareas();
    }

    public void guardarEstatus() {
        nombreStatus = estatus.getNombre();
        if (valida()) {
            if (existeEstatus()) {
                estatus.setActivo(1);
                estatus.setFechaAlta(new Date());
                estatus.setUsuarioAlta(usuarioLogin.getUsuario());
                estatusService.guardarEstatus(estatus);
                bitacoraTareaEstatus.setDescripcion("Actualiza estatus:" + nombreStatus + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                estatus = new Estatus();
                mensaje.mensaje("El estatus " + nombreStatus + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El estatus " + nombreStatus + " ya existe.", "rojo");
            }
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (estatus.getNombre() == null || estatus.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del estatus", "amarillo");
            bandera = false;
        }
        if (estatus.getIdTarea() == null || estatus.getIdTarea() == null) {
            mensaje.mensaje("Debes capturar la tarea del estatus", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeEstatus() {
        Estatus e = estatusService.getEstatusByNombre(nombreStatus);
        if (e != null) {
            if (Objects.equals(e.getIdEstatus(), estatus.getIdEstatus())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombreStatus() {
        return nombreStatus;
    }

    public void setNombreStatus(String nombreStatus) {
        this.nombreStatus = nombreStatus;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public List<Tareas> getTareasList() {
        return tareasList;
    }

    public void setTareasList(List<Tareas> tareasList) {
        this.tareasList = tareasList;
    }

}
