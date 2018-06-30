/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.FabricanteService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Fabricante;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.ejb.EJB;

/**
 *
 * @author kriosoft
 */
public class DetalleFabricantesBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private FabricanteService fabricanteService;

    private String nombreFabricante;

    private Usuarios usuarioLogin = new Usuarios();
    private Fabricante fabricante;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleFabricantesBean() {
        fabricante = new Fabricante();
        usuarioLogin = new Usuarios();
        fabricante = (Fabricante) util.getSessionAtributte("fabricante");
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
    }

    public void guardarFabricante() {
        nombreFabricante = fabricante.getNombre();
        if (validarAgregar()) {
            if (existeFabricante()) {
                fabricante.setActivo(1);
                fabricante.setFechaModificacion(new Date());
                fabricante.setUsuarioModificacion(usuarioLogin.getUsuario());
                fabricanteService.guardarFabricante(fabricante);
                bitacoraTareaEstatus.setDescripcion("Actualizar fabricante:" + nombreFabricante + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                mensaje.mensaje("El fabricante " + nombreFabricante + " se ha modificado correctamente.", "verde");
            } else {
                mensaje.mensaje("El fabricante " + nombreFabricante + " ya existe.", "rojo");
            }
        }
    }

    public boolean validarAgregar() {
        boolean bandera = true;
        if (fabricante.getNombre() == null || fabricante.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del faricante", "amarillo");
            bandera = false;
        }
        if (fabricante.getRegistroSanitario() == null || fabricante.getRegistroSanitario().equals("")) {
            mensaje.mensaje("Debes capturar el registro sanitario del fabricante", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeFabricante() {
        Fabricante fab = fabricanteService.fabricanteByNombre(nombreFabricante);
        if (fab != null) {
            if (Objects.equals(fab.getIdFabricante(), fabricante.getIdFabricante())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombreFabricante() {
        return nombreFabricante;
    }

    public void setNombreFabricante(String nombreFabricante) {
        this.nombreFabricante = nombreFabricante;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

}
