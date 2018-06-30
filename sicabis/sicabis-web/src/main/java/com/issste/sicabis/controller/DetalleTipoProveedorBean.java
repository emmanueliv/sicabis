/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.TipoProveedorService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.TipoProveedor;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.util.Date;
import java.util.Objects;
import javax.ejb.EJB;

/**
 *
 * @author kriosoft
 */
public class DetalleTipoProveedorBean {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoProveedorService tipoProveedorService;

    private String nombreTipoProveedor;
    private String nombreTipoProveedorAnt;
    private Usuarios usuarioLogin;

    private TipoProveedor tipoProveedor;
    private TipoProveedor tipoProveedorAnt;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleTipoProveedorBean() {
        tipoProveedor = new TipoProveedor();
        tipoProveedorAnt = new TipoProveedor();
        usuarioLogin = new Usuarios();
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        tipoProveedor = (TipoProveedor) util.getSessionAtributte("tipoProveedor");
        nombreTipoProveedorAnt = tipoProveedor.getDescripcion();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    public void guardarTipoProveedor() {
        nombreTipoProveedor = tipoProveedor.getDescripcion();
        if (validar()) {
            if (validarAgregar()) {
                tipoProveedorAnt.setActivo(0);
                tipoProveedorAnt.setUsuarioModificacion(usuarioLogin.getUsuario());
                tipoProveedorAnt.setTipo(tipoProveedor.getTipo());
                tipoProveedorAnt.setIdTipoProveedor(tipoProveedor.getIdTipoProveedor());
                tipoProveedorAnt.setDescripcion(nombreTipoProveedorAnt);
                tipoProveedorService.guardarTipoDocumento(tipoProveedorAnt);
                
                tipoProveedorAnt = new TipoProveedor();
                tipoProveedorAnt.setActivo(1);
                tipoProveedorAnt.setUsuarioModificacion(usuarioLogin.getUsuario());
                tipoProveedorAnt.setTipo(tipoProveedor.getTipo());
                tipoProveedorAnt.setIdPadre(tipoProveedor.getIdTipoProveedor());
                tipoProveedorAnt.setDescripcion(nombreTipoProveedor);
                tipoProveedorService.guardarTipoDocumento(tipoProveedorAnt);
                bitacoraTareaEstatus.setDescripcion("Actualizar tipo proveedor:" + nombreTipoProveedor + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                tipoProveedor = new TipoProveedor();
                mensaje.mensaje("El tipo de proveedor " + nombreTipoProveedor + " se ha guardado correctamente.", "verde");

            } else {
                mensaje.mensaje("El tipo de proveedor " + nombreTipoProveedor + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (tipoProveedor.getDescripcion() == null || tipoProveedor.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del tipo de proveedor", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        TipoProveedor tp = tipoProveedorService.obtenerTipoDocumentosByNombre(nombreTipoProveedor);
        if (tp != null) {
            if (Objects.equals(tp.getIdTipoProveedor(), tipoProveedor.getIdTipoProveedor())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombreTipoProveedor() {
        return nombreTipoProveedor;
    }

    public void setNombreTipoProveedor(String nombreTipoProveedor) {
        this.nombreTipoProveedor = nombreTipoProveedor;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public TipoProveedor getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(TipoProveedor tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public TipoProveedor getTipoProveedorAnt() {
        return tipoProveedorAnt;
    }

    public void setTipoProveedorAnt(TipoProveedor tipoProveedorAnt) {
        this.tipoProveedorAnt = tipoProveedorAnt;
    }
}
