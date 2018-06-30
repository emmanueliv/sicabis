/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.AlmacenService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.modelo.Almacen;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
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
public class AlmacenBean implements Serializable {

    //EJB's
    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;
    @EJB
    private AlmacenService almacenService;
    //Variables
    private String nombreAlmacen;

    //Objetos
    private Usuarios usuarioLogin;
    private Almacen almacen;
    private List<Almacen> almacensList;
    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    public AlmacenBean() {
        almacen = new Almacen();
        almacensList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catAlmacenes.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AlmacenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarAlmacenes() {
        System.out.println(nombreAlmacen);
        almacensList = new ArrayList<>();
        if (nombreAlmacen == null || nombreAlmacen.equals("")) {
            almacensList = almacenService.getAlmacenesByActivo();
        } else {
            Almacen al = almacenService.obtenerAlmacenByNombre(nombreAlmacen);
            if (al != null) {
                almacensList.add(al);
            }
        }
        if (almacensList == null || almacensList.isEmpty()) {
            mensaje.mensaje("No se encontraron almacenes.", "amarillo");
        }
    }

    public void modificarRedirect(Almacen almacen) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("almacen", almacen);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleAlmacen.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AlmacenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarAlmacen() {
        nombreAlmacen = almacen.getNombreAlmacen();
        if (valida()) {
            if (validarAgregar()) {
                almacen.setFechaAlta(new Date());
                almacen.setUsuarioAlta(usuarioLogin.getUsuario());
                almacen.setActivo(1);
                almacenService.guardarAlmacen(almacen);
                almacen = new Almacen();
                bitacoraTareaEstatus.setDescripcion("Guardado nuevo almacen:" + nombreAlmacen + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(1);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                mensaje.mensaje("El almacen " + nombreAlmacen + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El almacen " + nombreAlmacen + " ya existe.", "rojo");
            }
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (almacen.getNombreAlmacen() == null || almacen.getNombreAlmacen().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del almacén", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        Almacen a = almacenService.obtenerAlmacenByNombre(nombreAlmacen);
        return a == null;
    }

    public void mostrarDialogo(Almacen al) {
        nombreAlmacen = al.getNombreAlmacen();
        almacen = al;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarAlmacen').show();");
    }

    public void eliminarAlmacen() {
        nombreAlmacen = almacen.getNombreAlmacen();
        almacen.setFechaBaja(new Date());
        almacen.setUsuarioBaja(usuarioLogin.getUsuario());
        almacen.setActivo(0);
        almacenService.guardarAlmacen(almacen);
        almacensList = new ArrayList<>();
        almacensList = almacenService.obtenerAlmacenes();
        bitacoraTareaEstatus.setDescripcion("Borrado almacen:" + nombreAlmacen + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(1);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        mensaje.mensaje("El almacen " + nombreAlmacen + " ha sido dado de baja.", "verde");
    }

    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public List<Almacen> getAlmacensList() {
        return almacensList;
    }

    public void setAlmacensList(List<Almacen> almacensList) {
        this.almacensList = almacensList;
    }

}
