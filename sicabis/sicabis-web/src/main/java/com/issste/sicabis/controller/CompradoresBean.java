/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.CompradorService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Compradores;
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
public class CompradoresBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private CompradorService compradorService;

    private String nombreComprador;

    private Usuarios usuarioLogin;
    private Compradores comprador;
    private List<Compradores> compradoresList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    public CompradoresBean() {
        comprador = new Compradores();
        compradoresList = new ArrayList<>();
        usuarioLogin = new Usuarios();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
    }

    public void guardarComprador() {
        nombreComprador = comprador.getNombre();
        if (valida()) {
            if (!existeComprador()) {
                comprador.setActivo(1);
                comprador.setFechaAlta(new Date());
                comprador.setUsuarioAlta(usuarioLogin.getUsuario());
                compradorService.guardarComprador(comprador);
                comprador = new Compradores();
                bitacoraTareaEstatus.setDescripcion("Guardar nuevo comprador:" + nombreComprador + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                mensaje.mensaje("El comprador " + nombreComprador + " se ha guardado correctamente.", "verde");

            } else {
                mensaje.mensaje("El comprador " + nombreComprador + " ya existe.", "rojo");
            }
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (comprador.getNombre() == null || comprador.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del comprador", "amarillo");
            bandera = false;
        }
        if (comprador.getApaterno() == null || comprador.getApaterno().equals("")) {
            mensaje.mensaje("Debes capturar el apellido paterno del comprador", "amarillo");
            bandera = false;
        }
        if (comprador.getAmaterno() == null || comprador.getAmaterno().equals("")) {
            mensaje.mensaje("Debes capturar el apellido materno del comprador", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeComprador() {
        Compradores c = compradorService.obtenerCompradorByNombre(nombreComprador);
        return c != null;
    }

    public void modificarRedirect(Compradores comp) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("comprador", comp);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleComprador.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catCompradores.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarDialogo(Compradores comp) {
        nombreComprador = comp.getNombre();
        comprador = comp;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaComprador').show();");
    }

    public void eliminarComprador() {
        nombreComprador = comprador.getNombre();
        comprador.setActivo(0);
        comprador.setFechaBaja(new Date());
        comprador.setUsuarioBaja(usuarioLogin.getUsuario());
        compradorService.guardarComprador(comprador);
        compradoresList = new ArrayList<>();
        compradoresList = compradorService.obtenerCompradores();
        bitacoraTareaEstatus.setDescripcion("Borrado comprador:" + nombreComprador + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        mensaje.mensaje("El comprador " + nombreComprador + " ha sido dado de baja.", "verde");
    }

    public void consultarCompradores() {
        compradoresList = new ArrayList<>();
        if (nombreComprador == null || nombreComprador.equals("")) {
            compradoresList = compradorService.obtenerCompradoresByActivo();
        } else {
            Compradores c = compradorService.obtenerCompradorByNombre(nombreComprador);
            if (c != null) {
                compradoresList.add(c);
            }
        }
        if (compradoresList == null || compradoresList.isEmpty()) {
            mensaje.mensaje("No se encontraron compradores.", "amarillo");
        }
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Compradores getComprador() {
        return comprador;
    }

    public void setComprador(Compradores comprador) {
        this.comprador = comprador;
    }

    public List<Compradores> getCompradoresList() {
        return compradoresList;
    }

    public void setCompradoresList(List<Compradores> compradoresList) {
        this.compradoresList = compradoresList;
    }

}
