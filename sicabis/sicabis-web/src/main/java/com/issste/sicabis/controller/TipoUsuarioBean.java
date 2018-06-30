/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.TipoUsuariosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.TipoUsuarios;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
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
 * @author 9RZCBG2
 */
public class TipoUsuarioBean {

    //EJB´s
    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoUsuariosService tipoUsuariosService;

    //Objetos
    private TipoUsuarios tipoUsuario;
    private Mensajes mensajes;
    private Utilidades util;
    private Usuarios usuarioLogin;
    private List<TipoUsuarios> listaTipoUsuarios;
    private BitacoraTareaEstatus bitacora;

    //Variables
    public TipoUsuarioBean() {
        tipoUsuario = new TipoUsuarios();
        mensajes = new Mensajes();
        util = new Utilidades();
        usuarioLogin = new Usuarios();
        listaTipoUsuarios = new ArrayList<>();
        bitacora = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
    }

    public void guadarTipoUsuario() {
        if (valida()) {
            if (validarAgregar()) {
                tipoUsuario.setFechaAlta(new Date());
                tipoUsuario.setUsuarioAlta(usuarioLogin.getUsuario());
                tipoUsuario.setActivo(1);
                tipoUsuariosService.guadarActualizar(tipoUsuario);
                bitacora.setDescripcion("Guardado tipo de usuarios");
                bitacora.setFecha(new Date());
                bitacora.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacora.setIdModulos(5);
                bitacoraTareaSerice.guardarEnBitacora(bitacora);
                mensajes.mensaje("El tipo de usuario " + tipoUsuario.getNombre() + " se ha guardado correctamente.", "verde");
            } else {
                mensajes.mensaje("El tipo de usuario " + tipoUsuario.getNombre() + " ya existe.", "rojo");
            }
            tipoUsuario = new TipoUsuarios();
        }
    }

    public boolean validarAgregar() {
        TipoUsuarios tu = tipoUsuariosService.obtenerTipoUsuarioByNombre(tipoUsuario.getNombre());
        return tu.getIdTipoUsuario() == null;
    }

    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catTipoUsuarios.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AlmacenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (tipoUsuario.getNombre() == null || tipoUsuario.getNombre().equals("")) {
            mensajes.mensaje("Debes capturar el nombre asignado al tipo de usuario", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public void modificarRedirect(TipoUsuarios tu) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setContextAtributte("tipoUsuario", tu);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleTipoUsuarios.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TipoUsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarDialogo(TipoUsuarios tu) {
        tipoUsuario = tu;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminar').show();");
    }

    public void eliminarTipoUsuario() {
        tipoUsuario.setFechaBaja(new Date());
        tipoUsuario.setUsuarioBaja(usuarioLogin.getUsuario());
        tipoUsuario.setActivo(0);
        tipoUsuariosService.guadarActualizar(tipoUsuario);
        bitacora.setDescripcion("Eliminar o inactivo tipo de usuarios");
        bitacora.setFecha(new Date());
        bitacora.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacora.setIdModulos(5);
        bitacoraTareaSerice.guardarEnBitacora(bitacora);
        listaTipoUsuarios = tipoUsuariosService.obtenerListaTiposUsuarios("");
        mensajes.mensaje("El tipo de usuario " + tipoUsuario.getNombre() + " ha sido dado de baja.", "verde");
    }
    
    //Getters and Setters 
    public void consultarTiposUsuario() {
        listaTipoUsuarios = tipoUsuariosService.obtenerListaTiposUsuarios(tipoUsuario.getNombre());
    }

    public TipoUsuarios getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarios tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<TipoUsuarios> getListaTipoUsuarios() {
        return listaTipoUsuarios;
    }

    public void setListaTipoUsuarios(List<TipoUsuarios> listaTipoUsuarios) {
        this.listaTipoUsuarios = listaTipoUsuarios;
    }

}
