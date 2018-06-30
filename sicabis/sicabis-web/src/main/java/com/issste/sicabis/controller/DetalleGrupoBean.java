/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.GrupoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Grupo;
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
public class DetalleGrupoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private GrupoService grupoService;

    private String nombreGrupo;
    private Usuarios usuarioLogin;
    private Grupo grupo;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleGrupoBean() {
        usuarioLogin = new Usuarios();
        grupo = new Grupo();
    }

    @PostConstruct
    public void init() {
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        grupo = (Grupo) util.getSessionAtributte("grupo");
    }

    public void guardarGrupo() {
        nombreGrupo = grupo.getGrupo();
        if (validarAgregar()) {
            if (existeGrupo()) {
                grupo.setActivo(1);
                grupo.setFechaModificacion(new Date());
                grupo.setUsuarioModificacion(usuarioLogin.getUsuario());
                grupoService.guardarGrupo(grupo);
                bitacoraTareaEstatus.setDescripcion("Actualizar grupo:" + nombreGrupo + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                grupo = new Grupo();
                mensaje.mensaje("El grupo " + nombreGrupo + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El grupo " + nombreGrupo + " ya existe.", "rojo");
            }
        }
    }

    public boolean validarAgregar() {
        boolean bandera = true;
        if (grupo.getGrupo() == null || grupo.getGrupo().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del grupo", "amarillo");
            bandera = false;
        }
        if (grupo.getDescripcion() == null || grupo.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar la descripción del grupo", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeGrupo() {
        Grupo g = grupoService.obtenerGrupoByNombre(nombreGrupo);
        if (g != null) {
            if (Objects.equals(g.getIdGrupo(), grupo.getIdGrupo())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

}
