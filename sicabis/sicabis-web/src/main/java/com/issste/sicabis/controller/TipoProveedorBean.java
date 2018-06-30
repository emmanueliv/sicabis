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
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author kriosoft
 */
public class TipoProveedorBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoProveedorService tipoProveedorService;

    private String nombreTipoProveedor;
    private Usuarios usuarioLogin;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private TipoProveedor tipoProveedor;
    private List<TipoProveedor> tipoProveedorList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public TipoProveedorBean() {
        tipoProveedor = new TipoProveedor();
        tipoProveedorList = new ArrayList<>();
        usuarioLogin = new Usuarios();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
    }

    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catTipoProveedor.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TipoProveedorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarTiposProv() {
        tipoProveedorList = new ArrayList<>();
        if (nombreTipoProveedor == null || nombreTipoProveedor.equals("")) {
            tipoProveedorList = tipoProveedorService.obtenerTipoProveedores();
        } else {
            TipoProveedor tp = tipoProveedorService.obtenerTipoDocumentosByNombre(nombreTipoProveedor);
            if (tp != null) {
                tipoProveedorList.add(tp);
            }
        }
        if (tipoProveedorList == null || tipoProveedorList.isEmpty()) {
            mensaje.mensaje("No se encontraron tipos de proveedores.", "amarillo");
        }
    }

    public void modificarRedirect(TipoProveedor tipoProv) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("tipoProveedor", tipoProv);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleTipoProveedor.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TipoProveedorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarTipoProveedor() {
        nombreTipoProveedor = tipoProveedor.getDescripcion();
        if (validar()) {
            if (validarAgregar()) {
                tipoProveedor.setActivo(1);
                tipoProveedor.setFechaAlta(new Date());
                tipoProveedor.setUsuarioAlta(usuarioLogin.getUsuario());
                tipoProveedorService.guardarTipoDocumento(tipoProveedor);
                bitacoraTareaEstatus.setDescripcion("Guardar tipo proveedor:" + nombreTipoProveedor + "");
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
        return tp == null;
    }

    public void mostrarDialogo(TipoProveedor tipoProv) {
        nombreTipoProveedor = tipoProv.getDescripcion();
        tipoProveedor = tipoProv;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaTProveedor').show();");
    }

    public void eliminarTipoProveedor() {
        nombreTipoProveedor = tipoProveedor.getDescripcion();
        tipoProveedor.setActivo(0);
        tipoProveedor.setFechaBaja(new Date());
        tipoProveedor.setUsuarioBaja(usuarioLogin.getUsuario());
        tipoProveedorService.guardarTipoDocumento(tipoProveedor);
        bitacoraTareaEstatus.setDescripcion("Eliminar tipo proveedor:" + nombreTipoProveedor + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        tipoProveedorList = new ArrayList<>();
        tipoProveedorList = tipoProveedorService.obtenerTipoProveedores();
        mensaje.mensaje("El tipo de proveedor " + nombreTipoProveedor + " ha sido dado de baja.", "verde");
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

    public List<TipoProveedor> getTipoProveedorList() {
        return tipoProveedorList;
    }

    public void setTipoProveedorList(List<TipoProveedor> tipoProveedorList) {
        this.tipoProveedorList = tipoProveedorList;
    }

}
