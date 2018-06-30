/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ContactoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Contactos;
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
public class ContactosBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private ContactoService contactoService;

    private String nombreContacto;

    private Usuarios usuarioLogin;
    private Contactos contacto;
    private List<Contactos> contactosList;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public ContactosBean() {
        usuarioLogin = new Usuarios();
        contacto = new Contactos();
        contactosList = new ArrayList<>();
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

    public void consultarcontactos() {
        contactosList = new ArrayList<>();
        if (nombreContacto == null || nombreContacto.equals("")) {
            contactosList = contactoService.obtenerContactos();
        } else {
            Contactos con = contactoService.obtenerContactoByNombre(nombreContacto);
            if (con != null) {
                contactosList.add(con);
            }
        }
        if (contactosList == null || contactosList.isEmpty()) {
            mensaje.mensaje("No se encontraron contactos.", "amarillo");
        }
    }

    public void mostrarDialogo(Contactos con) {
        nombreContacto = con.getNombre();
        contacto = con;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaContacto').show();");
    }

    public void eliminarContacto() {
        nombreContacto = contacto.getNombre();
        contacto.setActivo(0);
        contacto.setFechaBaja(new Date());
        contacto.setUsuarioBaja(usuarioLogin.getUsuario());
        contactoService.guardarContacto(contacto);
        bitacoraTareaEstatus.setDescripcion("Borrado contacto:" + nombreContacto + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(1);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        contactosList = new ArrayList<>();
        contactosList = contactoService.obtenerContactos();
        mensaje.mensaje("El contacto " + nombreContacto + " ha sido dado de baja.", "verde");
    }

    public void modificarRedirect(Contactos cont) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("contacto", cont);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleContacto.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarContacto() {
        nombreContacto = contacto.getNombre();
        if (valida()) {
            if (!existeContacto()) {
                contacto.setActivo(1);
                contacto.setFechaAlta(new Date());
                contacto.setUsuarioAlta(usuarioLogin.getUsuario());
                contactoService.guardarContacto(contacto);
                bitacoraTareaEstatus.setDescripcion("Guardar contacto:" + nombreContacto + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(1);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                contacto = new Contactos();
                mensaje.mensaje("El contacto " + nombreContacto + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El contacto " + nombreContacto + " ya existe.", "rojo");
            }
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (contacto.getNombre() == null || contacto.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del contacto", "amarillo");
            bandera = false;
        }
        if (contacto.getApellidoPaterno() == null || contacto.getApellidoPaterno().equals("")) {
            mensaje.mensaje("Debes capturar el apellido paterno del contacto", "amarillo");
            bandera = false;
        }
        if (contacto.getApellidoMaterno() == null || contacto.getApellidoMaterno().equals("")) {
            mensaje.mensaje("Debes capturar el apellido materno del contacto", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeContacto() {
        Contactos c = contactoService.obtenerContactoByNombre(nombreContacto);
        return c != null;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Contactos getContacto() {
        return contacto;
    }

    public void setContacto(Contactos contacto) {
        this.contacto = contacto;
    }

    public List<Contactos> getContactosList() {
        return contactosList;
    }

    public void setContactosList(List<Contactos> contactosList) {
        this.contactosList = contactosList;
    }

}
