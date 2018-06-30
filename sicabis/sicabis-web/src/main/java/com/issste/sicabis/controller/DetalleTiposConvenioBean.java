/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.TiposConvenioService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.TipoConvenio;
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
public class DetalleTiposConvenioBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TiposConvenioService tiposConvenioService;

    private String nombreConvenio;
    private Usuarios usuarioLogin;
    private TipoConvenio convenio;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleTiposConvenioBean() {
        usuarioLogin = new Usuarios();
        convenio = new TipoConvenio();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        convenio = (TipoConvenio) util.getSessionAtributte("convenio");
    }

    public void guardarTipoConvenio() {
        nombreConvenio = convenio.getNombre();
        if (validar()) {
            if (existeTipoConvenio()) {
                convenio.setActivo(1);
                convenio.setFechaModificacion(new Date());
                convenio.setUsuarioModificacion(usuarioLogin.getUsuario());
                tiposConvenioService.guardarTipoConvenio(convenio);
                bitacoraTareaEstatus.setDescripcion("Actualizar tipo convenio:" + nombreConvenio + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                convenio = new TipoConvenio();
                mensaje.mensaje("El tipo de convenio " + nombreConvenio + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El tipo de convenio " + nombreConvenio + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (convenio.getNombre() == null || convenio.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del tipo de convenio", "amarillo");
            bandera = false;
        }
        if (convenio.getDescripcion() == null || convenio.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar la descripción del tipo de convenio", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeTipoConvenio() {
        TipoConvenio tc = tiposConvenioService.obtenerTipoConvenioByNombre(nombreConvenio);
        if (tc != null) {
            if (Objects.equals(tc.getIdTipoConvenio(), convenio.getIdTipoConvenio())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombreConvenio() {
        return nombreConvenio;
    }

    public void setNombreConvenio(String nombreConvenio) {
        this.nombreConvenio = nombreConvenio;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public TipoConvenio getConvenio() {
        return convenio;
    }

    public void setConvenio(TipoConvenio convenio) {
        this.convenio = convenio;
    }

}
