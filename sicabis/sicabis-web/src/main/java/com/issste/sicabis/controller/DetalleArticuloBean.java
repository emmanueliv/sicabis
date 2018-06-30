/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.ArticulosService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.FundamentoLegal;
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
public class DetalleArticuloBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private ArticulosService articulosService;

    private String nombreFundamentoLegal;
    private String nombreFundamentoLegalAnt;
    private String descripcionAnt;

    private Usuarios usuarioLogin;
    private FundamentoLegal fundamento;
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleArticuloBean() {
        fundamento = new FundamentoLegal();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        fundamento = (FundamentoLegal) util.getSessionAtributte("fundamento");
        nombreFundamentoLegalAnt = fundamento.getNombre();
        descripcionAnt = fundamento.getDescripcion();
    }

    public void guardarFundamentoLegal() {
        nombreFundamentoLegal = fundamento.getDescripcion();
        if (valida()) {
            if (validarAgregar()) {
                int id = fundamento.getIdFundamentoLegal();
                fundamento.setIdFundamentoLegal(null);
                fundamento.setIdPadre(id);
                fundamento.setActivo(0);
                fundamento.setFechaAlta(new Date());
                fundamento.setUsuarioAlta(usuarioLogin.getUsuario());
                articulosService.guardarFundamentoLegal(fundamento);

                fundamento = new FundamentoLegal();
                fundamento.setFechaAlta(new Date());
                fundamento.setNombre(nombreFundamentoLegalAnt);
                fundamento.setDescripcion(descripcionAnt);
                fundamento.setActivo(1);
                fundamento.setIdFundamentoLegal(id);
                fundamento.setUsuarioAlta(usuarioLogin.getUsuario());
                articulosService.guardarFundamentoLegal(fundamento);

                bitacoraTareaEstatus.setDescripcion("Actualizar fundamento legal:" + nombreFundamentoLegal + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(1);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                fundamento = new FundamentoLegal();
                mensaje.mensaje("El fundamento " + nombreFundamentoLegal + " se ha modificado correctamente.", "verde");
            } else {
                mensaje.mensaje("El fundamento " + nombreFundamentoLegal + " ya existe.", "rojo");
            }
        }
    }

    public boolean validarAgregar() {
        FundamentoLegal f = articulosService.obtenerFundamentoLegalByNombre(nombreFundamentoLegal);
        if (f != null) {
            if (Objects.equals(f.getIdFundamentoLegal(), fundamento.getIdFundamentoLegal())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean valida() {
        boolean bandera = true;
        if (fundamento.getNombre() == null || fundamento.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del fundamento legal", "amarillo");
            bandera = false;
        }
        if (fundamento.getDescripcion() == null || fundamento.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar la descrpción del fundamento legal", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public String getNombreFundamentoLegal() {
        return nombreFundamentoLegal;
    }

    public void setNombreFundamentoLegal(String nombreFundamentoLegal) {
        this.nombreFundamentoLegal = nombreFundamentoLegal;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public FundamentoLegal getFundamento() {
        return fundamento;
    }

    public void setFundamento(FundamentoLegal fundamento) {
        this.fundamento = fundamento;
    }

}
