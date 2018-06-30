/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.LoteService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Lote;
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
public class LoteBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private LoteService loteService;

    private String nombreLote;
    private Usuarios usuarioLogin;
    private Lote lote;
    private List<Lote> loteList;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public LoteBean() {
        usuarioLogin = new Usuarios();
        lote = new Lote();
        loteList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catLotes.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarLotes() {
        loteList = new ArrayList<>();
        loteList = loteService.traeListaLotes();
        if (loteList == null || loteList.isEmpty()) {
            mensaje.mensaje("No se encontraron lotes.", "amarillo");
        }
    }

    public void mostrarDialogo(Lote l) {
        nombreLote = l.getLote();
        lote = l;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaLote').show();");
    }

    public void eliminarLote() {
        nombreLote = lote.getLote();
        lote.setActivo(0);
        lote.setFechaBaja(new Date());
        lote.setUsuarioBaja(usuarioLogin.getUsuario());
        loteService.guardarLote(lote);
        bitacoraTareaEstatus.setDescripcion("Eliminar lote:" + nombreLote + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        loteList = new ArrayList<>();
        loteList = loteService.traeListaLotes();
        mensaje.mensaje("El lote " + nombreLote + " ha sido dado de baja.", "amarillo");
    }

    public void modificarRedirect(Lote lt) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("lote", lt);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleLote.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarLote() {
        nombreLote = lote.getLote();
        if (!existeLote()) {
            lote.setActivo(1);
            lote.setFechaAlta(new Date());
            lote.setUsuarioAlta(usuarioLogin.getUsuario());
            loteService.guardarLote(lote);
            bitacoraTareaEstatus.setDescripcion("Guardar lote:" + nombreLote + "");
            bitacoraTareaEstatus.setFecha(new Date());
            bitacoraTareaEstatus.setIdModulos(4);
            bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
            bitacoraTareaEstatus.setIdTareaId(16);
            bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
            lote = new Lote();
            mensaje.mensaje("El lote " + nombreLote + " se ha guardado correctamente.", "amarillo");
        } else {
            mensaje.mensaje("El lote " + nombreLote + " ya existe.", "rojo");
        }
    }

    public boolean existeLote() {
        Lote g = loteService.obtenerLoteByNombre(nombreLote);
        return g != null;
    }

    public String getNombreLote() {
        return nombreLote;
    }

    public void setNombreLote(String nombreLote) {
        this.nombreLote = nombreLote;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public List<Lote> getLoteList() {
        return loteList;
    }

    public void setLoteList(List<Lote> loteList) {
        this.loteList = loteList;
    }

}
