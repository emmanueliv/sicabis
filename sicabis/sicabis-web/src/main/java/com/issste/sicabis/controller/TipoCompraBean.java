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
public class TipoCompraBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoCompraService tipoCompraService;

    private String nombreCompra;
    private Usuarios usuarioLogin;
    private TipoCompra tipoCompra;
    private List<TipoCompra> tipoCompraList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    public TipoCompraBean() {
        usuarioLogin = new Usuarios();
        tipoCompra = new TipoCompra();
        tipoCompraList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catTipoCompras.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarTipoCompras() {
        tipoCompraList = new ArrayList<>();
        if (nombreCompra == null || nombreCompra.equals("")) {
            tipoCompraList = tipoCompraService.traeListaTipoCompra();
        } else {
            TipoCompra tc = tipoCompraService.obtenerTipoCompraByNombre(nombreCompra);
            if (tc != null) {
                tipoCompraList.add(tc);
            }
        }
        if (tipoCompraList == null || tipoCompraList.isEmpty()) {
            mensaje.mensaje("No se encontraron tipos de tipoCompra.", "amarillo");
        }
    }

    public void mostrarDialogo(TipoCompra tc) {
        nombreCompra = tc.getNombre();
        tipoCompra = tc;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaTipoCompra').show();");
    }

    public void eliminarTipoCompra() {
        nombreCompra = tipoCompra.getNombre();
        tipoCompra.setActivo(0);
        tipoCompra.setFechaBaja(new Date());
        tipoCompra.setUsuarioBaja(usuarioLogin.getUsuario());
        tipoCompraService.guardar(tipoCompra);
        bitacoraTareaEstatus.setDescripcion("Eliminar tipo compra:" + nombreCompra + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        tipoCompraList = new ArrayList<>();
        tipoCompraList = tipoCompraService.traeListaTipoCompra();
        mensaje.mensaje("El tipoCompra " + nombreCompra + " ha sido dado de baja.", "verde");
    }

    public void modificarRedirect(TipoCompra tc) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("tipoCompra", tc);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleTipoCompra.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarTipoCompra() {
        nombreCompra = tipoCompra.getNombre();
        if (validar()) {
            if (!existeTipoCompra()) {
                tipoCompra.setActivo(1);
                tipoCompra.setFechaAlta(new Date());
                tipoCompra.setUsuarioAlta(usuarioLogin.getUsuario());
                tipoCompraService.guardar(tipoCompra);
                bitacoraTareaEstatus.setDescripcion("Guardar tipo compra:" + nombreCompra + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                tipoCompra = new TipoCompra();
                mensaje.mensaje("El tipoCompra " + nombreCompra + " se ha guardado correctamente.", "verde");
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
        return tc != null;
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

    public List<TipoCompra> getTipoCompraList() {
        return tipoCompraList;
    }

    public void setTipoCompraList(List<TipoCompra> tipoCompraList) {
        this.tipoCompraList = tipoCompraList;
    }

}
