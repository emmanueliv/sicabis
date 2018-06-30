/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.TipoSolicitudService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.TipoSolicitud;
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
public class DetalleTipoSolicitudBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoSolicitudService tipoSolicitudService;

    private String nombreTS;

    private Usuarios usuarioLogin;
    private TipoSolicitud tipoSolicitud;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleTipoSolicitudBean() {
        tipoSolicitud = new TipoSolicitud();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        tipoSolicitud = (TipoSolicitud) util.getSessionAtributte("tipoSol");
    }

    public void guardarTipoSolicitud() {
        nombreTS = tipoSolicitud.getDescripcion();
        if (validar()) {
            if (validarAgregar()) {
                tipoSolicitud.setActivo(1);
                tipoSolicitud.setFechaModificacion(new Date());
                tipoSolicitud.setUsuarioModificacion(usuarioLogin.getUsuario());
                tipoSolicitudService.guardarTS(tipoSolicitud);
                bitacoraTareaEstatus.setDescripcion("Actualizar tipo solicitud:" + nombreTS + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                tipoSolicitud = new TipoSolicitud();
                mensaje.mensaje("El tipo de solicitud " + nombreTS + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El tipo de solicitud " + nombreTS + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (tipoSolicitud.getDescripcion() == null || tipoSolicitud.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del tipo de solicitud", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        TipoSolicitud t = tipoSolicitudService.obtenerTSByNombre(nombreTS);
        if (t != null) {
            if (Objects.equals(t.getIdTipoSolicitud(), tipoSolicitud.getIdTipoSolicitud())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombreTS() {
        return nombreTS;
    }

    public void setNombreTS(String nombreTS) {
        this.nombreTS = nombreTS;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public TipoSolicitud getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

}
