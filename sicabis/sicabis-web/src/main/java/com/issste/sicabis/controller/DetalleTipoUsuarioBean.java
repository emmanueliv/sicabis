/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.TipoUsuariosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.TipoUsuarios;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author 9RZCBG2
 */
public class DetalleTipoUsuarioBean {

    //EJB's
    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;
    @EJB
    private TipoUsuariosService tipoUsuariosService;

    //Objetos
    TipoUsuarios tipoUsuario;
    Mensajes mensajes;
    Utilidades util;
    Usuarios usuarioLogin;
    BitacoraTareaEstatus bitacora;

    //Variables
    public DetalleTipoUsuarioBean() {
        tipoUsuario = new TipoUsuarios();
        mensajes = new Mensajes();
        util = new Utilidades();
        usuarioLogin = new Usuarios();
        bitacora = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        tipoUsuario = (TipoUsuarios) util.getContextAtributte("tipoUsuario");
    }

    public void actualizarTipoUsuario() {
        tipoUsuario.setFechaAlta(new Date());
        tipoUsuario.setUsuarioAlta(usuarioLogin.getUsuario());
        tipoUsuario.setActivo(1);
        tipoUsuariosService.guadarActualizar(tipoUsuario);
        bitacora.setDescripcion("Actualiza tipo de usuarios");
        bitacora.setFecha(new Date());
        bitacora.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacora.setIdModulos(5);
        bitacoraTareaSerice.guardarEnBitacora(bitacora);
        mensajes.mensaje("El almacen " + tipoUsuario.getNombre() + " se ha guardado correctamente.", "verde");

    }

    //Getters and Setters 
    public TipoUsuarios getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarios tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
