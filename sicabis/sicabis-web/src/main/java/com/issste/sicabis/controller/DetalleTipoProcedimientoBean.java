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
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author kriosoft
 */
public class DetalleTipoProcedimientoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoProcedimientoService tipoProcedimientoService;

    private String nombreTP;

    private Usuarios usuarioLogin;
    private TipoProcedimiento tipoProcedimiento;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleTipoProcedimientoBean() {
        tipoProcedimiento = new TipoProcedimiento();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        tipoProcedimiento = (TipoProcedimiento) util.getSessionAtributte("tipoprocedimiento");
    }

    public void guardarTipoProcedimiento() {
        nombreTP = tipoProcedimiento.getDescripcion();
        if (validar()) {
            if (validarAgregar()) {
                tipoProcedimiento.setActivo(1);
                tipoProcedimiento.setFechaModificiacion(new Date());
                tipoProcedimiento.setUsuarioModificacion(usuarioLogin.getUsuario());
                tipoProcedimientoService.guardarTipoProcedimiento(tipoProcedimiento);
                bitacoraTareaEstatus.setDescripcion("Actualizar tipo procedimiento:" + nombreTP + "");
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
        if (t != null) {
            if (Objects.equals(t.getIdTipoProcedimiento(), tipoProcedimiento.getIdTipoProcedimiento())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
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

}
