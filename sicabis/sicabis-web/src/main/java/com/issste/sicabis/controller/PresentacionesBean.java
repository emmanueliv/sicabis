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
public class PresentacionesBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private PresentacionService presentacionesService;

    private String descPresentacion;

    private Usuarios usuarioLogin;
    private Presentacion presentacion;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private List<Presentacion> presentacionList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public PresentacionesBean() {
        presentacion = new Presentacion();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        presentacionList = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
    }

    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catPresentaciones.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PresentacionesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarPresentaciones() {
        presentacionList = new ArrayList<>();
        if (descPresentacion == null || descPresentacion.equals("")) {
            presentacionList = presentacionesService.presentacionList();
        } else {
            Presentacion p = presentacionesService.presentacionByDesc(descPresentacion);
            if (p != null) {
                presentacionList.add(p);
            }
        }
        if (presentacionList == null || presentacionList.isEmpty()) {
            mensaje.mensaje("No se encontraron presentaciones.", "amarillo");
        }
    }

    public void modificarRedirect(Presentacion presentacion) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("presentacion", presentacion);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detallePresentacion.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PresentacionesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarPresentacion() {
        descPresentacion = presentacion.getPresentacion();
        if (valida()) {
            if (validarAgregar()) {
                presentacion.setActivo(1);
                presentacion.setFechaAlta(new Date());
                presentacion.setUsuarioAlta(usuarioLogin.getUsuario());
                presentacionesService.guardarPresentacion(presentacion);
                bitacoraTareaEstatus.setDescripcion("Guardar presentacion:" + descPresentacion + "");
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
        return p == null;
    }

    public void mostrarDialogo(Presentacion pr) {
        descPresentacion = pr.getPresentacion();
        presentacion = pr;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarPresentacion').show();");
    }

    public void eliminarPresentacion() {
        descPresentacion = presentacion.getPresentacion();
        presentacion.setActivo(0);
        presentacion.setFechaBaja(new Date());
        presentacion.setUsuarioBaja(usuarioLogin.getUsuario());
        presentacionesService.guardarPresentacion(presentacion);
        bitacoraTareaEstatus.setDescripcion("Eliminar presentacion:" + descPresentacion + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        presentacionList = new ArrayList<>();
        presentacionList = presentacionesService.presentacionList();
        mensaje.mensaje("La presentación " + descPresentacion + " ha sido dada de baja.", "verde");
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

    public List<Presentacion> getPresentacionList() {
        return presentacionList;
    }

    public void setPresentacionList(List<Presentacion> presentacionList) {
        this.presentacionList = presentacionList;
    }

}
