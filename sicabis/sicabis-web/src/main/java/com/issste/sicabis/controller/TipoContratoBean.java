/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.TipoContratoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.TipoContrato;
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
public class TipoContratoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoContratoService tipoContratoService;

    private String nombreTC;

    private Usuarios usuarioLogin;
    private TipoContrato tipoContrato;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private List<TipoContrato> tipoContratosList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public TipoContratoBean() {
        tipoContrato = new TipoContrato();
        tipoContratosList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catTipoContratos.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TipoContratoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarTipoContratoes() {
        tipoContratosList = new ArrayList<>();
        if (nombreTC == null || nombreTC.equals("")) {
            tipoContratosList = tipoContratoService.obtenerTiposContrato();
        } else {
            TipoContrato t = tipoContratoService.obtenerTipoContratoByNombre(nombreTC);
            if (t != null) {
                tipoContratosList.add(t);
            }
        }
        if (tipoContratosList == null || tipoContratosList.isEmpty()) {
            mensaje.mensaje("No se encontraron tipos de contratos.", "amarillo");
        }
    }

    public void modificarRedirect(TipoContrato tipoContrato) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("tipoContrato", tipoContrato);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleTipoContrato.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TipoCompraBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarTipoContrato() {
        nombreTC = tipoContrato.getDescripcion();
        if (validar()) {
            if (validarAgregar()) {
                tipoContrato.setFechaAlta(new Date());
                tipoContrato.setUsuarioAlta(usuarioLogin.getUsuario());
                tipoContratoService.guardarTipoContrato(tipoContrato);
                bitacoraTareaEstatus.setDescripcion("Guardar tipo contrato:" + nombreTC + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                tipoContrato = new TipoContrato();
                mensaje.mensaje("El tipo de contrato " + nombreTC + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El tipo de contrato " + nombreTC + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (tipoContrato.getDescripcion() == null || tipoContrato.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar el tipo de contrato", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        TipoContrato t = tipoContratoService.obtenerTipoContratoByNombre(nombreTC);
        return t == null;
    }

    public void mostrarDialogo(TipoContrato p) {
        nombreTC = p.getDescripcion();
        tipoContrato = p;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarTipoContrato').show();");
    }

    public void eliminarTipoContrato() {
        nombreTC = tipoContrato.getDescripcion();
        tipoContrato.setFechaBaja(new Date());
        tipoContrato.setUsuarioBaja(usuarioLogin.getUsuario());
        tipoContratoService.guardarTipoContrato(tipoContrato);
        bitacoraTareaEstatus.setDescripcion("Eliminar tipo contrato:" + nombreTC + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        tipoContratosList = new ArrayList<>();
        tipoContratosList = tipoContratoService.obtenerTiposContrato();
        mensaje.mensaje("El tipo de contrato " + nombreTC + " ha sido dado de baja.", "verde");
    }

    public String getNombreTC() {
        return nombreTC;
    }

    public void setNombreTC(String nombreTC) {
        this.nombreTC = nombreTC;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public List<TipoContrato> getTipoContratosList() {
        return tipoContratosList;
    }

    public void setTipoContratosList(List<TipoContrato> tipoContratosList) {
        this.tipoContratosList = tipoContratosList;
    }

}
