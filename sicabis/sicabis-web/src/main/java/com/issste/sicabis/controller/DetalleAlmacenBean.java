/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.AlmacenService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.modelo.Almacen;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
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
public class DetalleAlmacenBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private AlmacenService almacenService;

    private String nombreAlmacen;
    private String nombreAntAlmacen;

    private Usuarios usuarioLogin;
    private Almacen almacen;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    public DetalleAlmacenBean() {
        almacen = new Almacen();
        usuarioLogin = new Usuarios();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        almacen = (Almacen) util.getSessionAtributte("almacen");
        nombreAntAlmacen = almacen.getNombreAlmacen();
    }

    public void guardarAlmacen() {
        nombreAlmacen = almacen.getNombreAlmacen();
        if (valida()) {
            if (validarAgregar()) {
                almacen.setFechaModificacion(new Date());
                almacen.setUsuarioModificacion(usuarioLogin.getUsuario());
                almacen.setNombreAlmacen(nombreAntAlmacen);
                almacen.setActivo(0);
                almacenService.guardarAlmacen(almacen);
                
                int id = almacen.getIdAlmacen();
                almacen = new Almacen();
                almacen.setFechaModificacion(new Date());
                almacen.setUsuarioModificacion(usuarioLogin.getUsuario());
                almacen.setNombreAlmacen(nombreAlmacen);
                almacen.setIdPadre(id);
                almacen.setActivo(1);
                almacenService.guardarAlmacen(almacen);
                
                bitacoraTareaEstatus.setDescripcion("Actualizar almacen:" + nombreAlmacen + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                almacen = new Almacen();
                mensaje.mensaje("El almacen " + nombreAlmacen + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El almacen " + nombreAlmacen + " ya existe.", "rojo");
            }
        }

    }

    public boolean valida() {
        boolean bandera = true;
        if (almacen.getNombreAlmacen() == null || almacen.getNombreAlmacen().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del almacén", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        Almacen a = almacenService.obtenerAlmacenByNombre(nombreAlmacen);
        if (a != null) {
            if (Objects.equals(a.getIdAlmacen(), almacen.getIdAlmacen())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public String getNombreAntAlmacen() {
        return nombreAntAlmacen;
    }

    public void setNombreAntAlmacen(String nombreAntAlmacen) {
        this.nombreAntAlmacen = nombreAntAlmacen;
    }
}
