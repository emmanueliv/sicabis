/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.GrupoTerapeuticoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.GrupoTerapeutico;
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
public class DetalleGrupoTerapeuticoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private GrupoTerapeuticoService grupoTerapeuticoService;

    private String nombreGT;

    private Usuarios usuarioLogin;
    private GrupoTerapeutico grupoT;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleGrupoTerapeuticoBean() {
        grupoT = new GrupoTerapeutico();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        grupoT = (GrupoTerapeutico) util.getSessionAtributte("grupoT");
    }

    public void guardarGrupoTerapeutico() {
        nombreGT = grupoT.getDescripcion();
        if (validar()) {
            if (validarAgregar()) {
                grupoT.setActivo(1);
                grupoT.setFechaModificacion(new Date());
                grupoT.setUsuarioModificacion(usuarioLogin.getUsuario());
                grupoTerapeuticoService.guardarGruposTerapeuticos(grupoT);
                bitacoraTareaEstatus.setDescripcion("Actualizar grupo terapeutico:" + nombreGT + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                grupoT = new GrupoTerapeutico();
                mensaje.mensaje("El grupo terapéutico " + nombreGT + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El grupo terapéutico " + nombreGT + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (grupoT.getDescripcion() == null || grupoT.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del grupo terapeútico", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        GrupoTerapeutico g = grupoTerapeuticoService.obtenerGPByNombre(nombreGT);
        if (g != null) {
            if (Objects.equals(g.getIdGrupoTerapeutico(), grupoT.getIdGrupoTerapeutico())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombreGT() {
        return nombreGT;
    }

    public void setNombreGT(String nombreGT) {
        this.nombreGT = nombreGT;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public GrupoTerapeutico getGrupoT() {
        return grupoT;
    }

    public void setGrupoT(GrupoTerapeutico grupoT) {
        this.grupoT = grupoT;
    }
}
