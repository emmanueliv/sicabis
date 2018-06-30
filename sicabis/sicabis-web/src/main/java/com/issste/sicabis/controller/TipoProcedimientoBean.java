/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.TipoProcedimientoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.TipoProcedimiento;
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
public class TipoProcedimientoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoProcedimientoService tipoProcedimientoService;

    private String nombreTP;

    private Usuarios usuarioLogin;
    private TipoProcedimiento tipoProcedimiento;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private List<TipoProcedimiento> tipoProcedimientoList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public TipoProcedimientoBean() {
        tipoProcedimiento = new TipoProcedimiento();
        tipoProcedimientoList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catTipoProcedimiento.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TipoProcedimientoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarTipoProcedimientos() {
        tipoProcedimientoList = new ArrayList<>();
        if (nombreTP == null || nombreTP.equals("")) {
            tipoProcedimientoList = tipoProcedimientoService.obtenerTiposProcedimientos();
        } else {
            TipoProcedimiento t = tipoProcedimientoService.obtenerTipoProcedimientoByNombre(nombreTP);
            if (t != null) {
                tipoProcedimientoList.add(t);
            }
        }
        if (tipoProcedimientoList == null || tipoProcedimientoList.isEmpty()) {
            mensaje.mensaje("No se encontraron tipos de procedimientos.", "amarillo");
        }
    }

    public void modificarRedirect(TipoProcedimiento tipoProc) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("tipoprocedimiento", tipoProc);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleTipoProcedimiento.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TipoProcedimientoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarTipoProcedimiento() {
        nombreTP = tipoProcedimiento.getDescripcion();
        if (validar()) {
            if (validarAgregar()) {
                tipoProcedimiento.setActivo(1);
                tipoProcedimiento.setFechaAlta(new Date());
                tipoProcedimiento.setUsuarioAlta(usuarioLogin.getUsuario());
                tipoProcedimientoService.guardarTipoProcedimiento(tipoProcedimiento);
                bitacoraTareaEstatus.setDescripcion("Eliminar tipo procedimiento:" + nombreTP + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                tipoProcedimiento = new TipoProcedimiento();
                mensaje.mensaje("El tipo de procedimiento " + nombreTP + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El tipo de procedimiento " + nombreTP + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (tipoProcedimiento.getDescripcion() == null || tipoProcedimiento.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del tipo de procedimiento", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        TipoProcedimiento t = tipoProcedimientoService.obtenerTipoProcedimientoByNombre(nombreTP);
        return t == null;
    }

    public void mostrarDialogo(TipoProcedimiento p) {
        nombreTP = p.getDescripcion();
        tipoProcedimiento = p;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarTipoProcedimiento').show();");
    }

    public void eliminarTipoProcedimiento() {
        nombreTP = tipoProcedimiento.getDescripcion();
        tipoProcedimiento.setActivo(0);
        tipoProcedimiento.setFechaBaja(new Date());
        tipoProcedimiento.setUsuarioBaja(usuarioLogin.getUsuario());
        tipoProcedimientoService.guardarTipoProcedimiento(tipoProcedimiento);
        bitacoraTareaEstatus.setDescripcion("Eliminar tipo procedimiento:" + nombreTP + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        tipoProcedimientoList = new ArrayList<>();
        tipoProcedimientoList = tipoProcedimientoService.obtenerTiposProcedimientos();
        mensaje.mensaje("El tipo de procedimiento " + nombreTP + " ha sido dado de baja.", "verde");
    }

    public String getNombreTP() {
        return nombreTP;
    }

    public void setNombreTP(String nombreTP) {
        this.nombreTP = nombreTP;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public TipoProcedimiento getTipoProcedimiento() {
        return tipoProcedimiento;
    }

    public void setTipoProcedimiento(TipoProcedimiento tipoProcedimiento) {
        this.tipoProcedimiento = tipoProcedimiento;
    }

    public List<TipoProcedimiento> getTipoProcedimientoList() {
        return tipoProcedimientoList;
    }

    public void setTipoProcedimientoList(List<TipoProcedimiento> tipoProcedimientoList) {
        this.tipoProcedimientoList = tipoProcedimientoList;
    }

}
