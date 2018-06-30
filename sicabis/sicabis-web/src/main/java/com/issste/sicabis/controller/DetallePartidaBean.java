/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.PartidaService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.PartidaPresupuestal;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author kriosoft
 */
public class DetallePartidaBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private PartidaService partidaService;

    private String nombrePartida;
    private String nombrePartidaAnt;
    private String partidaAnt;
    private String descripcionAnt;

    private Usuarios usuarioLogin;
    private PartidaPresupuestal partida;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetallePartidaBean() {
        partida = new PartidaPresupuestal();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        partida = (PartidaPresupuestal) util.getSessionAtributte("partida");
        nombrePartidaAnt = partida.getPartidaPresupuestal();
        partidaAnt = partida.getPartida();
        descripcionAnt = partida.getDescripcion();
    }

    public void guardarPartidaPresupuestal() {
        nombrePartida = partida.getDescripcion();
        if (validar()) {
            if (validarAgregar()) {
                int id = partida.getIdPartidaPresupuestal();
                partida.setIdPartidaPresupuestal(null);
                partida.setIdPadre(id);
                partida.setActivo(1);
                partida.setUsuarioModificacion(usuarioLogin.getUsuario());
                partida.setFechaModificacion(new Date());
                partidaService.guardarPartidaP(partida);

                partida = new PartidaPresupuestal();
                partida.setDescripcion(descripcionAnt);
                partida.setIdPartidaPresupuestal(id);
                partida.setActivo(0);
                partida.setPartidaPresupuestal(nombrePartidaAnt);
                partida.setPartida(partidaAnt);
                partida.setUsuarioModificacion(usuarioLogin.getUsuario());
                partida.setFechaModificacion(new Date());
                partidaService.guardarPartidaP(partida);
                bitacoraTareaEstatus.setDescripcion("Actualiza partida ");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                partida = new PartidaPresupuestal();
                mensaje.mensaje("La partida " + nombrePartida + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("La partida " + nombrePartida + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (partida.getPartida() == null || partida.getPartida().equals("")) {
            mensaje.mensaje("Debes capturar la partida", "amarillo");
            bandera = false;
        } else {
            try {
                int partInt = Integer.valueOf(partida.getPartida());
            } catch (Exception ex) {
                mensaje.mensaje("El formato del campo partida debe ser numérico", "amarillo");
                Logger.getLogger(ArticulosBean.class.getName()).log(Level.SEVERE, null, ex);
                bandera = false;
            }
        }
        if (partida.getDescripcion() == null || partida.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar la descripción de la partida presupuestal", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        PartidaPresupuestal p = partidaService.obtenerAreaByNombre(nombrePartida);
        if (p != null) {
            if (Objects.equals(p.getIdPartidaPresupuestal(), partida.getIdPartidaPresupuestal())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombrePartida() {
        return nombrePartida;
    }

    public void setNombrePartida(String nombrePartida) {
        this.nombrePartida = nombrePartida;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public PartidaPresupuestal getPartida() {
        return partida;
    }

    public void setPartida(PartidaPresupuestal partida) {
        this.partida = partida;
    }

}
