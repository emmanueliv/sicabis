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
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author kriosoft
 */
public class DetalleCondicionesPagoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private CondicionesPagoService condicionesService;

    private String descCondicion;

    private Usuarios usuarioLogin;
    private CondicionesPago condicionPago;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleCondicionesPagoBean() {
        condicionPago = new CondicionesPago();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        condicionPago = (CondicionesPago) util.getSessionAtributte("condicionesPago");
    }

    public void guardarCondicionP() {
        descCondicion = condicionPago.getDescripcion();
        if (valida()) {
            if (validarAgregar()) {
                condicionPago.setActivo(1);
                condicionPago.setFechaAlta(new Date());
                condicionPago.setUsuarioAlta(usuarioLogin.getUsuario());
                condicionesService.guardarCondicionPago(condicionPago);
                bitacoraTareaEstatus.setDescripcion("Actualizar condicion de pago:" + descCondicion + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                condicionPago = new CondicionesPago();
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
        if (c != null) {
            if (Objects.equals(c.getIdCondicion(), condicionPago.getIdCondicion())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
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

}
