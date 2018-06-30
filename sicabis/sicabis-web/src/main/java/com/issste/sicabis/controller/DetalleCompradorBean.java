/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.CompradorService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.CompradorContrato;
import com.issste.sicabis.ejb.modelo.Compradores;
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
public class DetalleCompradorBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private CompradorService compradorService;

    private String nombreComprador;
    private String nombreCompradorAnt;
    private String paternoCompradorAnt;
    private String maternoCompradorAnt;

    private Usuarios usuarioLogin;
    private Compradores comprador;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleCompradorBean() {
        comprador = new Compradores();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        usuarioLogin = new Usuarios();
    }

    @PostConstruct
    public void init() {
        comprador = (Compradores) util.getSessionAtributte("comprador");
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        nombreCompradorAnt = comprador.getNombre();
        paternoCompradorAnt = comprador.getApaterno();
        maternoCompradorAnt = comprador.getAmaterno();
    }

    public void guardarComprador() {
        nombreComprador = comprador.getNombre();
        if (valida()) {
            if (existeComprador()) {
                int id = comprador.getIdComprador();
                String aPaterno = comprador.getApaterno();
                String aMaterno = comprador.getAmaterno();
                comprador.setActivo(0);
                comprador.setAmaterno(maternoCompradorAnt);
                comprador.setApaterno(paternoCompradorAnt);
                comprador.setNombre(nombreCompradorAnt);
                comprador.setFechaAlta(new Date());
                comprador.setUsuarioAlta(usuarioLogin.getUsuario());
                compradorService.guardarComprador(comprador);

                comprador = new Compradores();
                comprador.setActivo(1);
                comprador.setIdPadre(id);
                comprador.setAmaterno(aMaterno);
                comprador.setApaterno(aPaterno);
                comprador.setNombre(nombreComprador);
                comprador.setFechaAlta(new Date());
                comprador.setUsuarioAlta(usuarioLogin.getUsuario());
                compradorService.guardarComprador(comprador);
                comprador = new Compradores();

                bitacoraTareaEstatus.setDescripcion("Actualiza comprador:" + nombreComprador + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                comprador = new Compradores();
                mensaje.mensaje("El comprador " + nombreComprador + " se ha modificado correctamente.", "verde");
            } else {
                mensaje.mensaje("El comprador " + nombreComprador + " ya existe.", "verde");
            }
        }

    }

    public boolean valida() {
        boolean bandera = true;
        if (comprador.getNombre() == null || comprador.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del comprador", "amarillo");
            bandera = false;
        }
        if (comprador.getApaterno() == null || comprador.getApaterno().equals("")) {
            mensaje.mensaje("Debes capturar el apellido paterno del comprador", "amarillo");
            bandera = false;
        }
        if (comprador.getAmaterno() == null || comprador.getAmaterno().equals("")) {
            mensaje.mensaje("Debes capturar el apellido materno del comprador", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeComprador() {
        Compradores c = compradorService.obtenerCompradorByNombre(nombreComprador);
        if (c != null) {
            if (Objects.equals(c.getIdComprador(), comprador.getIdComprador())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Compradores getComprador() {
        return comprador;
    }

    public void setComprador(Compradores comprador) {
        this.comprador = comprador;
    }

}
