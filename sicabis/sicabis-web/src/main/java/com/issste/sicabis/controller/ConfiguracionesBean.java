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
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author kriosoft
 */
public class ConfiguracionesBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private ConfiguracionesService configuracionesService;

    private String descConfig;

    private Usuarios usuarioLogin;
    private Configuraciones configuraciones;
    private List<Configuraciones> configuracionesList;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public ConfiguracionesBean() {
        usuarioLogin = new Usuarios();
        configuraciones = new Configuraciones();
        configuracionesList = new ArrayList<>();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
    }

    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catConfiguraciones.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarConfiguraciones() {
        configuracionesList = new ArrayList<>();
        if (descConfig == null || descConfig.equals("")) {
            configuracionesList = configuracionesService.obtenerConfiguraciones();
        } else {
            Configuraciones conf = configuracionesService.obtenerConfigByNombre(descConfig);
            if (conf != null) {
                configuracionesList.add(conf);
            }
        }
        if (configuracionesList == null || configuracionesList.isEmpty()) {
            mensaje.mensaje("No se encontraron configuraciones.", "amarillo");
        }
    }

    public void mostrarDialogo(Configuraciones conf) {
        descConfig = conf.getParametro();
        configuraciones = conf;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaCnf').show();");
    }

    public void eliminarConfiguracion() {
        descConfig = configuraciones.getParametro();
        configuraciones.setActivo(0);
        configuraciones.setFechaBaja(new Date());
        configuraciones.setUsuarioBaja(usuarioLogin.getUsuario());
        configuracionesService.guardarConfiguracion(configuraciones);
        bitacoraTareaEstatus.setDescripcion("Eliminar configuración:" + descConfig + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(1);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        configuracionesList = new ArrayList<>();
        configuracionesList = configuracionesService.obtenerConfiguraciones();
        mensaje.mensaje("La configuración " + descConfig + " ha sido dado de baja.", "verde");
    }

    public void guardarConfiguracion() {
        descConfig = configuraciones.getParametro();
        if (valida()) {
            if (!existeConfig()) {
                configuraciones.setActivo(1);
                configuraciones.setFechaAlta(new Date());
                configuraciones.setUsuarioAlta(usuarioLogin.getUsuario());
                configuracionesService.guardarConfiguracion(configuraciones);
                configuraciones = new Configuraciones();
                bitacoraTareaEstatus.setDescripcion("Guardar configuracion:" + descConfig + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(1);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                mensaje.mensaje("La configuracion " + descConfig + " se ha guardado correctamente.", "verde");
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
        return conf != null;
    }

    public void modificarRedirect(Configuraciones cnf) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("configuracion", cnf);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleConfiguracion.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public List<Configuraciones> getConfiguracionesList() {
        return configuracionesList;
    }

    public void setConfiguracionesList(List<Configuraciones> configuracionesList) {
        this.configuracionesList = configuracionesList;
    }

}
