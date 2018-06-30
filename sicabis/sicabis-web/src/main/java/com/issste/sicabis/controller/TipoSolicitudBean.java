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
public class TipoSolicitudBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoSolicitudService tipoSolicitudService;

    private String nombreTS;

    private Usuarios usuarioLogin;
    private TipoSolicitud tipoSolicitud;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private List<TipoSolicitud> tipoSolicitudList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public TipoSolicitudBean() {
        tipoSolicitud = new TipoSolicitud();
        tipoSolicitudList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catTipoSolicitudes.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TipoSolicitudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarTipoSolicituds() {
        tipoSolicitudList = new ArrayList<>();
        if (nombreTS == null || nombreTS.equals("")) {
            tipoSolicitudList = tipoSolicitudService.obtenerTiposSolicitud();
        } else {
            TipoSolicitud t = tipoSolicitudService.obtenerTSByNombre(nombreTS);
            if (t != null) {
                tipoSolicitudList.add(t);
            }
        }
        if (tipoSolicitudList == null || tipoSolicitudList.isEmpty()) {
            mensaje.mensaje("No se encontraron tipos de solicitud.", "amarillo");
        }
    }

    public void modificarRedirect(TipoSolicitud tipoSol) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("tipoSol", tipoSol);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleTipoSolicitud.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TipoSolicitudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarTipoSolicitud() {
        nombreTS = tipoSolicitud.getDescripcion();
        if (validar()) {
            if (validarAgregar()) {
                tipoSolicitud.setActivo(1);
                tipoSolicitud.setFechaAlta(new Date());
                tipoSolicitud.setUsuarioAlta(usuarioLogin.getUsuario());
                tipoSolicitudService.guardarTS(tipoSolicitud);
                bitacoraTareaEstatus.setDescripcion("Guardar tipo solicitud:" + nombreTS + "");
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
        return t == null;
    }

    public void mostrarDialogo(TipoSolicitud t) {
        nombreTS = t.getDescripcion();
        tipoSolicitud = t;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarTipoSolicitud').show();");
    }

    public void eliminarTipoSolicitud() {
        nombreTS = tipoSolicitud.getDescripcion();
        tipoSolicitud.setActivo(0);
        tipoSolicitud.setFechaBaja(new Date());
        tipoSolicitud.setUsuarioBaja(usuarioLogin.getUsuario());
        tipoSolicitudService.guardarTS(tipoSolicitud);
        tipoSolicitudList = new ArrayList<>();
        tipoSolicitudList = tipoSolicitudService.obtenerTiposSolicitud();
        mensaje.mensaje("El tipo de solicitud " + nombreTS + " ha sido dado de baja.", "verde");
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

    public List<TipoSolicitud> getTipoSolicitudList() {
        return tipoSolicitudList;
    }

    public void setTipoSolicitudList(List<TipoSolicitud> tipoSolicitudList) {
        this.tipoSolicitudList = tipoSolicitudList;
    }

}
