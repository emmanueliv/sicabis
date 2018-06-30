/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.AreasService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.modelo.Area;
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
public class DetalleAreaBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private AreasService areasService;

    private String nombreArea;
    private Usuarios usuarioLogin;
    private Area area;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleAreaBean() {
        usuarioLogin = new Usuarios();
        area = new Area();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        area = (Area) util.getSessionAtributte("area");
    }

    public void guardarArea() {
        nombreArea = area.getNombreArea();
        if (valida()) {
            if (existeArea()) {
                area.setActivo(1);
                area.setFechaModificacion(new Date());
                area.setUsuarioModificacion(usuarioLogin.getUsuario());
                areasService.guardarArea(area);
                bitacoraTareaEstatus.setDescripcion("Actualizar área:" + nombreArea + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                area = new Area();
                mensaje.mensaje("El área " + nombreArea + " se ha modificado correctamente.", "verde");
            } else {
                mensaje.mensaje("El área " + nombreArea + " ya existe.", "verde");
            }
        }

    }

    public boolean valida() {
        boolean bandera = true;
        if (area.getNombreArea() == null || area.getNombreArea().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del área", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeArea() {
        Area a = areasService.obtenerAreaByNombre(nombreArea);
        if (a != null) {
            if (Objects.equals(a.getIdArea(), area.getIdArea())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

}
