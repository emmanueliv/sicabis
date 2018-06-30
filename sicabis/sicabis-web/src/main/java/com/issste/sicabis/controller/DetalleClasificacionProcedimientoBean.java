/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ClasificacionProcedimientoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.ClasificacionProcedimiento;
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
public class DetalleClasificacionProcedimientoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private ClasificacionProcedimientoService clasifProcedService;

    private String nombreCP;

    private Usuarios usuarioLogin;
    private ClasificacionProcedimiento clasificacionProcedimiento;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleClasificacionProcedimientoBean() {
        clasificacionProcedimiento = new ClasificacionProcedimiento();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        clasificacionProcedimiento = (ClasificacionProcedimiento) util.getSessionAtributte("clasifProd");
    }

    public void guardarClasificacionProcedimiento() {
        nombreCP = clasificacionProcedimiento.getDescripcion();
        if (valida()) {
            if (validarAgregar()) {
                clasificacionProcedimiento.setActivo(1);
                clasificacionProcedimiento.setFechaModificacion(new Date());
                clasificacionProcedimiento.setUsuarioModificacion(usuarioLogin.getUsuario());
                clasifProcedService.guardarClasificacionProcedimiento(clasificacionProcedimiento);
                bitacoraTareaEstatus.setDescripcion("Actualizar clasificación procedimiento:" + nombreCP + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                clasificacionProcedimiento = new ClasificacionProcedimiento();
                mensaje.mensaje("La clasificación " + nombreCP + " se ha modificado correctamente.", "verde");
            } else {
                mensaje.mensaje("La clasificación " + nombreCP + " ya existe.", "rojo");
            }
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (clasificacionProcedimiento.getDescripcion() == null || clasificacionProcedimiento.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar la clasificación del procedimiento", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        ClasificacionProcedimiento c = clasifProcedService.obtenerClasificacionProcedimientoByNombre(nombreCP);
        if (c != null) {
            if (Objects.equals(c.getIdClasificacionProcedimiento(), clasificacionProcedimiento.getIdClasificacionProcedimiento())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
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

    public ClasificacionProcedimiento getClasificacionProcedimiento() {
        return clasificacionProcedimiento;
    }

    public void setClasificacionProcedimiento(ClasificacionProcedimiento clasificacionProcedimiento) {
        this.clasificacionProcedimiento = clasificacionProcedimiento;
    }

}
