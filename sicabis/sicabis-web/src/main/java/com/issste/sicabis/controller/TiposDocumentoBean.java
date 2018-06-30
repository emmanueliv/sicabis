/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.TareasService;
import com.issste.sicabis.ejb.ln.TipoDocumentosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Tareas;
import com.issste.sicabis.ejb.modelo.TipoDocumentos;
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
public class TiposDocumentoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoDocumentosService tipoDocumentosService;

    @EJB
    private TareasService tareasService;

    private String nombreDocumento;

    private Usuarios usuarioLogin;
    private TipoDocumentos tipoDocumento;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private List<Tareas> tareaList;
    private List<TipoDocumentos> tipoDocumentosList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public TiposDocumentoBean() {
        tipoDocumento = new TipoDocumentos();
        tipoDocumentosList = new ArrayList<>();
        tareaList = new ArrayList<>();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        tareaList = tareasService.obtenerTareas();
    }

    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catTipoDocumento.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarTiposDoc() {
        tipoDocumentosList = new ArrayList<>();
        if (nombreDocumento == null || nombreDocumento.equals("")) {
            tipoDocumentosList = tipoDocumentosService.obtenerTipoDocumentos();
        } else {
            TipoDocumentos td = tipoDocumentosService.obtenerTipoDocumentosByNombre(nombreDocumento);
            if (td != null) {
                tipoDocumentosList.add(td);
            }
        }
        if (tipoDocumentosList == null || tipoDocumentosList.isEmpty()) {
            mensaje.mensaje("No se encontraron tipos de documento.", "amarillo");
        }
    }

    public void modificarRedirect(TipoDocumentos tipoDoc) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("tipoDocumento", tipoDoc);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleTipoDocumento.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarTipoDocumento() {
        nombreDocumento = tipoDocumento.getNombre();
        if (validar()) {
            if (validarAgregar()) {
                tipoDocumento.setActivo(1);
                tipoDocumento.setFechaAlta(new Date());
                tipoDocumento.setUsuarioAlta(usuarioLogin.getUsuario());
                tipoDocumentosService.guardarTipoDocumento(tipoDocumento);
                tipoDocumento = new TipoDocumentos();
                mensaje.mensaje("El tipo de documento " + nombreDocumento + " se ha guardado correctamente.", "verde");

            } else {
                mensaje.mensaje("El tipo de documento " + nombreDocumento + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (tipoDocumento.getNombre() == null || tipoDocumento.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del tipo de documento", "amarillo");
            bandera = false;
        }
        if (tipoDocumento.getIdTarea() == null || tipoDocumento.getIdTarea().equals("")) {
            mensaje.mensaje("Debes seleccionar la tarea", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        TipoDocumentos td = tipoDocumentosService.obtenerTipoDocumentosByNombre(nombreDocumento);
        return td == null;
    }

    public void mostrarDialogo(TipoDocumentos tipoDoc) {
        nombreDocumento = tipoDoc.getNombre();
        tipoDocumento = tipoDoc;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaDocumento').show();");
    }

    public void eliminarDocumento() {
        nombreDocumento = tipoDocumento.getNombre();
        tipoDocumento.setActivo(0);
        tipoDocumento.setFechaBaja(new Date());
        tipoDocumento.setUsuarioBaja(usuarioLogin.getUsuario());
        tipoDocumentosService.guardarTipoDocumento(tipoDocumento);
        tipoDocumentosList = new ArrayList<>();
        tipoDocumentosList = tipoDocumentosService.obtenerTipoDocumentos();
        mensaje.mensaje("El tipo de documento " + nombreDocumento + " ha sido dado de baja.", "verde");
    }

    public TipoDocumentos getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentos tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<TipoDocumentos> getTipoDocumentosList() {
        return tipoDocumentosList;
    }

    public void setTipoDocumentosList(List<TipoDocumentos> tipoDocumentosList) {
        this.tipoDocumentosList = tipoDocumentosList;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public List<Tareas> getTareaList() {
        return tareaList;
    }

    public void setTareaList(List<Tareas> tareaList) {
        this.tareaList = tareaList;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

}
