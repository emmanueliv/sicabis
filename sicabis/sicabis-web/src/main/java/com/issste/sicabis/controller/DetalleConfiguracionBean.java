/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ConfiguracionesService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Configuraciones;
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
public class DetalleConfiguracionBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private ConfiguracionesService configuracionesService;

    private String descConfig;

    private Usuarios usuarioLogin;
    private Configuraciones configuraciones;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleConfiguracionBean() {
        usuarioLogin = new Usuarios();
        configuraciones = new Configuraciones();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        configuraciones = (Configuraciones) util.getSessionAtributte("configuracion");
    }

    public void guardarConfiguracion() {
        descConfig = configuraciones.getParametro();
        if (valida()) {
            if (existeConfig()) {
                configuraciones.setFechaModificacion(new Date());
                configuraciones.setUsuarioModificacion(usuarioLogin.getUsuario());
                configuracionesService.guardarConfiguracion(configuraciones);
                bitacoraTareaEstatus.setDescripcion("Actualiza configuracion:" + descConfig + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                configuraciones = new Configuraciones();
                mensaje.mensaje("La configuracion " + descConfig + " se ha modificado correctamente.", "verde");
            } else {
                mensaje.mensaje("La configuracion " + descConfig + " ya existe.", "rojo");
            }
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (configuraciones.getParametro() == null || configuraciones.getParametro().equals("")) {
            mensaje.mensaje("Debes capturar el parámetro de la configuración", "amarillo");
            bandera = false;
        }
        if (configuraciones.getValor() == null || configuraciones.getValor().equals("")) {
            mensaje.mensaje("Debes capturar el valor de la configuración", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeConfig() {
        Configuraciones conf = configuracionesService.obtenerConfigByNombre(descConfig);
        if (conf != null) {
            if (Objects.equals(conf.getIdConfiguraciones(), configuraciones.getIdConfiguraciones())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getDescConfig() {
        return descConfig;
    }

    public void setDescConfig(String descConfig) {
        this.descConfig = descConfig;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Configuraciones getConfiguraciones() {
        return configuraciones;
    }

    public void setConfiguraciones(Configuraciones configuraciones) {
        this.configuraciones = configuraciones;
    }

}
