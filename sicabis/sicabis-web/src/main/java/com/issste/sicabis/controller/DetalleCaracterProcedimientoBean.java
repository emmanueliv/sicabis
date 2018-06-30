/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.CaracterProcedimientoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.CaracterProcedimiento;
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
public class DetalleCaracterProcedimientoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private CaracterProcedimientoService caracterProcedimientoService;

    private String nombreCP;
    private Usuarios usuarioLogin;
    private CaracterProcedimiento caracterProcedimiento;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleCaracterProcedimientoBean() {
        caracterProcedimiento = new CaracterProcedimiento();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        caracterProcedimiento = (CaracterProcedimiento) util.getSessionAtributte("caracterProc");
    }

    public void guardarCaracterProcedimiento() {
        nombreCP = caracterProcedimiento.getDescripcion();
        if (valida()) {
            if (validarAgregar()) {
                caracterProcedimiento.setActivo(1);
                caracterProcedimiento.setFechaModificacion(new Date());
                caracterProcedimiento.setUsuarioModificacion(usuarioLogin.getUsuario());
                caracterProcedimientoService.guardarCP(caracterProcedimiento);
                bitacoraTareaEstatus.setDescripcion("Guardar caracter procedimiento:" + nombreCP + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                caracterProcedimiento = new CaracterProcedimiento();
                mensaje.mensaje("El carácter " + nombreCP + " se ha modificado correctamente.", "verde");

            } else {
                mensaje.mensaje("El carácter " + nombreCP + " ya existe.", "rojo");
            }
        }
    }

    public boolean validarAgregar() {
        CaracterProcedimiento proc = caracterProcedimientoService.obtenerCPByNombre(nombreCP);
        if (proc != null) {
            if (Objects.equals(proc.getIdCaracterProcedimiento(), caracterProcedimiento.getIdCaracterProcedimiento())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean valida() {
        boolean bandera = true;
        if (caracterProcedimiento.getDescripcion() == null || caracterProcedimiento.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar el caracter de procedimiento", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public String getNombreCP() {
        return nombreCP;
    }

    public void setNombreCP(String nombreCP) {
        this.nombreCP = nombreCP;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public CaracterProcedimiento getCaracterProcedimiento() {
        return caracterProcedimiento;
    }

    public void setCaracterProcedimiento(CaracterProcedimiento caracterProcedimiento) {
        this.caracterProcedimiento = caracterProcedimiento;
    }

}
