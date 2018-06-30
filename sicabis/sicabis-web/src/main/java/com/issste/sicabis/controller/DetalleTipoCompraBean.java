/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.TipoCompraService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.TipoCompra;
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
public class DetalleTipoCompraBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoCompraService tipoCompraService;

    private String nombreCompra;
    private Usuarios usuarioLogin;
    private TipoCompra tipoCompra;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleTipoCompraBean() {
        usuarioLogin = new Usuarios();
        tipoCompra = new TipoCompra();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        tipoCompra = (TipoCompra) util.getSessionAtributte("tipoCompra");
    }

    public void guardarTipoCompra() {
        nombreCompra = tipoCompra.getNombre();
        if (validar()) {
            if (existeTipoCompra()) {
                tipoCompra.setActivo(1);
                tipoCompra.setFechaModificacion(new Date());
                tipoCompra.setUsuarioModificacion(usuarioLogin.getUsuario());
                tipoCompraService.guardar(tipoCompra);
                bitacoraTareaEstatus.setDescripcion("Actualizar tipo compra:" + nombreCompra + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                tipoCompra = new TipoCompra();
                mensaje.mensaje("El tipo de compra " + nombreCompra + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El tipoCompra " + nombreCompra + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (tipoCompra.getNombre() == null || tipoCompra.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del tipo de compra", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeTipoCompra() {
        TipoCompra tc = tipoCompraService.obtenerTipoCompraByNombre(nombreCompra);
        if (tc != null) {
            if (Objects.equals(tc.getIdTipoCompra(), tipoCompra.getIdTipoCompra())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombreCompra() {
        return nombreCompra;
    }

    public void setNombreCompra(String nombreCompra) {
        this.nombreCompra = nombreCompra;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public TipoCompra getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(TipoCompra tipoCompra) {
        this.tipoCompra = tipoCompra;
    }

}
