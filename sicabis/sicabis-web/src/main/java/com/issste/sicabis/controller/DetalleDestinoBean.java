/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.DestinoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Destinos;
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
public class DetalleDestinoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private DestinoService destinoService;

    private String claveDestino;
    private String claveDestinoAnt;
    private String nombreAnt;
    private String domicilioAnt;

    private Usuarios usuarioLogin;
    private Destinos destino;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleDestinoBean() {
        usuarioLogin = new Usuarios();
        destino = new Destinos();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        destino = (Destinos) util.getSessionAtributte("destino");
        claveDestinoAnt = destino.getClave();
        nombreAnt = destino.getNombre();
        domicilioAnt = destino.getDomicilio();
    }

    public void guardarDestino() {
        claveDestino = destino.getClave();
        if (valida()) {
            if (existeDestino()) {
                int id = destino.getIdDestino();
                String dom = destino.getDomicilio();
                String nom = destino.getNombre();
                destino.setActivo(0);
                destino.setFechaModificacion(new Date());
                destino.setUsuarioModifcacion(usuarioLogin.getUsuario());
                destino.setClave(claveDestinoAnt);
                destino.setDomicilio(domicilioAnt);
                destino.setNombre(nombreAnt);
                destinoService.guardarDestino(destino);

                destino = new Destinos();
                destino.setIdPadre(id);
                destino.setActivo(1);
                destino.setFechaModificacion(new Date());
                destino.setUsuarioModifcacion(usuarioLogin.getUsuario());
                destino.setClave(claveDestino);
                destino.setDomicilio(dom);
                destino.setNombre(nom);
                destinoService.guardarDestino(destino);
                destino = new Destinos();
                bitacoraTareaEstatus.setDescripcion("Actualiza destino:" + claveDestino + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                destino = new Destinos();
                mensaje.mensaje("El destino " + claveDestino + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El destino " + claveDestino + " ya existe.", "rojo");
            }
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (destino.getClave() == null || destino.getClave().equals("")) {
            mensaje.mensaje("Debes capturar la clave del destino", "amarillo");
            bandera = false;
        }
        if (destino.getNombre() == null || destino.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del destino", "amarillo");
            bandera = false;
        }
        if (destino.getDomicilio() == null || destino.getDomicilio().equals("")) {
            mensaje.mensaje("Debes capturar el domicilio del destino", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeDestino() {
        Destinos d = destinoService.obtenerDestinoByCve(destino.getClave());
        if (d != null) {
            if (Objects.equals(d.getIdDestino(), destino.getIdDestino())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getClaveDestino() {
        return claveDestino;
    }

    public void setClaveDestino(String claveDestino) {
        this.claveDestino = claveDestino;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Destinos getDestino() {
        return destino;
    }

    public void setDestino(Destinos destino) {
        this.destino = destino;
    }

}
