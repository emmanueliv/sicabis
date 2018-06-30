/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ContactoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Contactos;
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
public class DetalleContactoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private ContactoService contactoService;

    private String nombreContacto;

    private Usuarios usuarioLogin;
    private Contactos contacto;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleContactoBean() {
        usuarioLogin = new Usuarios();
        contacto = new Contactos();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        contacto = (Contactos) util.getSessionAtributte("contacto");
    }

    public void guardarContacto() {
        nombreContacto = contacto.getNombre();
        if (valida()) {
            if (existeContacto()) {
                contacto.setActivo(1);
                contacto.setFechaAlta(new Date());
                contacto.setUsuarioAlta(usuarioLogin.getUsuario());
                contactoService.guardarContacto(contacto);
                bitacoraTareaEstatus.setDescripcion("Actualiza contacto:" + nombreContacto + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                contacto = new Contactos();
                mensaje.mensaje("El contacto " + nombreContacto + " se ha modificado correctamente.", "verde");
            } else {
                mensaje.mensaje("El contacto " + nombreContacto + " ya existe.", "verde");
            }
        }
    }

    public boolean existeContacto() {
        Contactos c = contactoService.obtenerContactoByNombre(nombreContacto);
        if (c != null) {
            if (Objects.equals(c.getIdContacto(), contacto.getIdContacto())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean valida() {
        boolean bandera = true;
        if (contacto.getNombre() == null || contacto.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del contacto", "amarillo");
            bandera = false;
        }
        if (contacto.getApellidoPaterno() == null || contacto.getApellidoPaterno().equals("")) {
            mensaje.mensaje("Debes capturar el apellido paterno del contacto", "amarillo");
            bandera = false;
        }
        if (contacto.getApellidoMaterno() == null || contacto.getApellidoMaterno().equals("")) {
            mensaje.mensaje("Debes capturar el apellido materno del contacto", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Contactos getContacto() {
        return contacto;
    }

    public void setContacto(Contactos contacto) {
        this.contacto = contacto;
    }

}
