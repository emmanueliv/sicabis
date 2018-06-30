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
public class CaracterProcedimientoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    CaracterProcedimientoService caracterProcedimientoService;

    private String nombreCP;

    private Usuarios usuarioLogin;
    private CaracterProcedimiento caracterProcedimiento;

    private List<CaracterProcedimiento> caracterProcedimientoList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    public CaracterProcedimientoBean() {
        caracterProcedimiento = new CaracterProcedimiento();
        caracterProcedimientoList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catCaracterProcedimientos.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CaracterProcedimientoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarCaracterProcedimientos() {
        caracterProcedimientoList = new ArrayList<>();
        if (nombreCP == null || !nombreCP.equals("")) {
            caracterProcedimientoList = caracterProcedimientoService.obtenerCaracterProcedimiento();
        } else {
            CaracterProcedimiento c = caracterProcedimientoService.obtenerCPByNombre(nombreCP);
            if (c != null) {
                caracterProcedimientoList.add(c);
            }
        }
        if (caracterProcedimientoList == null || caracterProcedimientoList.isEmpty()) {
            mensaje.mensaje("No se encontraron resultados.", "amarillo");
        }
    }

    public void modificarRedirect(CaracterProcedimiento cp) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("caracterProc", cp);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleCaracterProcedimiento.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CaracterProcedimientoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarCaracterProcedimiento() {
        nombreCP = caracterProcedimiento.getDescripcion();
        if (valida()) {
            if (validarAgregar()) {
                caracterProcedimiento.setActivo(1);
                caracterProcedimiento.setFechaAlta(new Date());
                caracterProcedimiento.setUsuarioAlta(usuarioLogin.getUsuario());
                caracterProcedimientoService.guardarCP(caracterProcedimiento);
                caracterProcedimiento = new CaracterProcedimiento();
                bitacoraTareaEstatus.setDescripcion("Guardar caracter procedimiento:" + nombreCP + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                mensaje.mensaje("El carácter " + nombreCP + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El carácter " + nombreCP + " ya existe.", "rojo");
            }
        }
    }

    public boolean validarAgregar() {
        CaracterProcedimiento t = caracterProcedimientoService.obtenerCPByNombre(nombreCP);
        return t == null;
    }

    public boolean valida() {
        boolean bandera = true;
        if (caracterProcedimiento.getDescripcion() == null || caracterProcedimiento.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar el caracter de procedimiento", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public void mostrarDialogo(CaracterProcedimiento t) {
        nombreCP = t.getDescripcion();
        caracterProcedimiento = t;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarCaracterProcedimiento').show();");
    }

    public void eliminarCaracterProcedimiento() {
        nombreCP = caracterProcedimiento.getDescripcion();
        caracterProcedimiento.setActivo(0);
        caracterProcedimiento.setFechaBaja(new Date());
        caracterProcedimiento.setUsuarioBaja(usuarioLogin.getUsuario());
        caracterProcedimientoService.guardarCP(caracterProcedimiento);
        caracterProcedimientoList = new ArrayList<>();
        caracterProcedimientoList = caracterProcedimientoService.obtenerCaracterProcedimiento();
        bitacoraTareaEstatus.setDescripcion("Borrado caracter procedimiento:" + nombreCP + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        mensaje.mensaje("El carácter " + nombreCP + " ha sido dado de baja.", "verde");
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

    public List<CaracterProcedimiento> getCaracterProcedimientoList() {
        return caracterProcedimientoList;
    }

    public void setCaracterProcedimientoList(List<CaracterProcedimiento> caracterProcedimientoList) {
        this.caracterProcedimientoList = caracterProcedimientoList;
    }

}
