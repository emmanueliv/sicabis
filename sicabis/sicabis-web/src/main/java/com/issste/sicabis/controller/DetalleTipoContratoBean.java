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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author kriosoft
 */
public class DetalleTipoContratoBean implements Serializable {

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

    public DetalleTipoContratoBean() {
        tipoContrato = new TipoContrato();
        tipoContratosList = new ArrayList<>();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        tipoContrato = (TipoContrato) util.getSessionAtributte("tipoContrato");
    }

    public void guardarTipoContrato() {
        nombreTC = tipoContrato.getDescripcion();
        if (validar()) {
            if (validarAgregar()) {
                tipoContrato.setFechaModificacion(new Date());
                tipoContrato.setUsuarioModificacion(usuarioLogin.getUsuario());
                tipoContratoService.guardarTipoContrato(tipoContrato);
                bitacoraTareaEstatus.setDescripcion("Actualizar tipo contrato:" + nombreTC + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                tipoContrato = new TipoContrato();
                mensaje.mensaje("El tipo de contrato " + nombreTC + " se ha modificado correctamente.", "verde");

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
        if (t != null) {
            if (Objects.equals(t.getIdTipoContrato(), tipoContrato.getIdTipoContrato())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
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
