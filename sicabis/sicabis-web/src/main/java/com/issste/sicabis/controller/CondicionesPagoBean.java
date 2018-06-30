/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.CondicionesPagoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.CondicionesPago;
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
public class CondicionesPagoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private CondicionesPagoService condicionesService;

    private String descCondicion;

    private Usuarios usuarioLogin;
    private CondicionesPago condicionPago;
    private List<CondicionesPago> condicionPagoList;
    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    public CondicionesPagoBean() {
        condicionPago = new CondicionesPago();
        condicionPagoList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catCondicionesPago.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CondicionesPagoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarCondicionesP() {
        System.out.println(descCondicion);
        condicionPagoList = new ArrayList<>();
        if (descCondicion == null || descCondicion.equals("")) {
            condicionPagoList = condicionesService.obtenerCondicionesPago();
        } else {
            CondicionesPago conPago = condicionesService.obtenerCondicionesByDesc(descCondicion);
            if (conPago != null) {
                condicionPagoList.add(conPago);
            }
        }
        if (condicionPagoList == null || condicionPagoList.isEmpty()) {
            mensaje.mensaje("No se encontraron condiciones de pago.", "amarillo");
        }
    }

    public void modificarRedirect(CondicionesPago condicionesPago) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("condicionesPago", condicionesPago);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleCondicionPago.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CondicionesPagoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarCondicionP() {
        descCondicion = condicionPago.getDescripcion();
        if (valida()) {
            if (validarAgregar()) {
                condicionPago.setActivo(1);
                condicionPago.setFechaAlta(new Date());
                condicionPago.setUsuarioAlta(usuarioLogin.getUsuario());
                condicionesService.guardarCondicionPago(condicionPago);
                condicionPago = new CondicionesPago();
                bitacoraTareaEstatus.setDescripcion("Guardar condición de pago:" + descCondicion + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                mensaje.mensaje("La condición de pago " + descCondicion + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("La condición de pago " + descCondicion + " ya existe.", "rojo");
            }
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (condicionPago.getDescripcion() == null || condicionPago.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar la desripción de la condición de pago.", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        CondicionesPago c = condicionesService.obtenerCondicionesByDesc(descCondicion);
        return c == null;
    }

    public void mostrarDialogo(CondicionesPago cp) {
        descCondicion = cp.getDescripcion();
        condicionPago = cp;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarCondicion').show();");
    }

    public void eliminarCondicionP() {
        descCondicion = condicionPago.getDescripcion();
        condicionPago.setActivo(0);
        condicionPago.setFechaBaja(new Date());
        condicionPago.setUsuarioBaja(usuarioLogin.getUsuario());
        condicionesService.guardarCondicionPago(condicionPago);
        condicionPagoList = new ArrayList<>();
        condicionPagoList = condicionesService.obtenerCondicionesPago();
        bitacoraTareaEstatus.setDescripcion("Borrar condición de pago:" + descCondicion + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        mensaje.mensaje("La condición de pago " + descCondicion + " ha sido dada de baja.", "verde");
    }

    public String getDescCondicion() {
        return descCondicion;
    }

    public void setDescCondicion(String descCondicion) {
        this.descCondicion = descCondicion;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public CondicionesPago getCondicionPago() {
        return condicionPago;
    }

    public void setCondicionPago(CondicionesPago condicionPago) {
        this.condicionPago = condicionPago;
    }

    public List<CondicionesPago> getCondicionPagoList() {
        return condicionPagoList;
    }

    public void setCondicionPagoList(List<CondicionesPago> condicionPagoList) {
        this.condicionPagoList = condicionPagoList;
    }

}
