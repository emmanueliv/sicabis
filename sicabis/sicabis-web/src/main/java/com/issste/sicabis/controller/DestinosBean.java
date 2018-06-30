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
public class DestinosBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private DestinoService destinoService;

    private String claveDestino;
    private Usuarios usuarioLogin;
    private Destinos destino;
    private List<Destinos> destinosList;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DestinosBean() {
        usuarioLogin = new Usuarios();
        destino = new Destinos();
        destinosList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catDestinos.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarDestinos() {
        destinosList = new ArrayList<>();
        if (claveDestino == null || claveDestino.equals("")) {
            destinosList = destinoService.obtenerDestinos();
        } else {
            Destinos d = destinoService.obtenerDestinoByCve(claveDestino);
            if (d != null) {
                destinosList.add(d);
            }
        }
        if (destinosList == null || destinosList.isEmpty()) {
            mensaje.mensaje("No se encontraron destinos.", "amarillo");
        }
    }

    public void mostrarDialogo(Destinos dest) {
        claveDestino = dest.getClave();
        destino = dest;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaDestino').show();");
    }

    public void eliminarDestino() {
        claveDestino = destino.getClave();
        destino.setActivo(0);
        destino.setFechaBaja(new Date());
        destino.setUsuarioBaja(usuarioLogin.getUsuario());
        destinoService.guardarDestino(destino);
        bitacoraTareaEstatus.setDescripcion("Borrado destino:" + destino + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        destinosList = new ArrayList<>();
        destinosList = destinoService.obtenerDestinos();
        mensaje.mensaje("El destino " + claveDestino + " ha sido dado de baja.", "verde");
    }

    public void modificarRedirect(Destinos dest) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("destino", dest);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleDestino.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarDestino() {
        claveDestino = destino.getClave();
        if (valida()) {
            if (existeDestino()) {
                destino.setActivo(1);
                destino.setFechaAlta(new Date());
                destino.setUsuarioAlta(usuarioLogin.getUsuario());
                destinoService.guardarDestino(destino);
                bitacoraTareaEstatus.setDescripcion("Guardar destino:" + destino + "");
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
        return d == null;
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

    public List<Destinos> getDestinosList() {
        return destinosList;
    }

    public void setDestinosList(List<Destinos> destinosList) {
        this.destinosList = destinosList;
    }

}
