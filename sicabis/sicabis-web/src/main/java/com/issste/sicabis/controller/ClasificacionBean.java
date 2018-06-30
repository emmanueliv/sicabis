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
public class ClasificacionBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private ClasificacionService clasificacionService;

    private String nombreClasificacion;
    private Usuarios usuarioLogin;
    private Clasificacion clasificacion;
    private List<Clasificacion> clasificacionList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    public ClasificacionBean() {
        usuarioLogin = new Usuarios();
        clasificacion = new Clasificacion();
        clasificacionList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catClasificacions.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarClasificacions() {
        clasificacionList = new ArrayList<>();
        if (nombreClasificacion == null || nombreClasificacion.equals("")) {
            clasificacionList = clasificacionService.obtenerClasificaciones();
        } else {
            Clasificacion c = clasificacionService.obtenerClasificacionByNombre(nombreClasificacion);
            if (c != null) {
                clasificacionList.add(c);
            }
        }

        if (clasificacionList == null || clasificacionList.isEmpty()) {
            mensaje.mensaje("No se encontraron clasificaciones.", "amarillo");
        }
    }

    public void mostrarDialogo(Clasificacion c) {
        nombreClasificacion = c.getDescripcion();
        clasificacion = c;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaClasificacion').show();");
    }

    public void eliminarClasificacion() {
        nombreClasificacion = clasificacion.getDescripcion();
        clasificacion.setActivo(0);
        clasificacion.setFechaBaja(new Date());
        clasificacion.setUsuarioBaja(usuarioLogin.getUsuario());
        clasificacionService.guardarClasificacion(clasificacion);
        clasificacionList = new ArrayList<>();
        clasificacionList = clasificacionService.obtenerClasificaciones();
        bitacoraTareaEstatus.setDescripcion("Borrado clasificación:" + nombreClasificacion + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        mensaje.mensaje("La clasificación " + nombreClasificacion + " ha sido dado de baja.", "verde");
    }

    public void modificarRedirect(Clasificacion cl) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("clasificacion", cl);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleClasificacion.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarClasificacion() {
        nombreClasificacion = clasificacion.getDescripcion();
        if (valida()) {
            if (!existeClasificacion()) {
                clasificacion.setActivo(1);
                clasificacion.setFechaAlta(new Date());
                clasificacion.setUsuarioAlta(usuarioLogin.getUsuario());
                clasificacionService.guardarClasificacion(clasificacion);
                clasificacion = new Clasificacion();
                bitacoraTareaEstatus.setDescripcion("Guadar nueva clasificación:" + nombreClasificacion + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                mensaje.mensaje("La clasificacion " + nombreClasificacion + " se ha guardado correctamente.", "verde");
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
        return c != null;
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

    public List<Clasificacion> getClasificacionList() {
        return clasificacionList;
    }

    public void setClasificacionList(List<Clasificacion> clasificacionList) {
        this.clasificacionList = clasificacionList;
    }

}
