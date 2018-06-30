/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.FabricanteService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Fabricante;
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
public class FabricantesBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private FabricanteService fabricanteService;

    private String nombreFabricante;

    private Usuarios usuarioLogin = new Usuarios();
    private Fabricante fabricante;
    private List<Fabricante> fabricanteList;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public FabricantesBean() {
        fabricante = new Fabricante();
        fabricanteList = new ArrayList<>();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
    }

    public void guardarFabricante() {
        nombreFabricante = fabricante.getNombre();
        if (validarAgregar()) {
            if (!existeFabricante()) {
                fabricante.setActivo(1);
                fabricante.setFechaAlta(new Date());
                fabricante.setUsuarioAlta(usuarioLogin.getUsuario());
                fabricanteService.guardarFabricante(fabricante);
                bitacoraTareaEstatus.setDescripcion("Guardar fabricante:" + nombreFabricante + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                fabricante = new Fabricante();
                mensaje.mensaje("El fabricante " + nombreFabricante + " se ha guardado correctamente.", "verde");

            } else {
                mensaje.mensaje("El fabricante " + nombreFabricante + " ya existe.", "rojo");
            }
        }
    }

    public boolean validarAgregar() {
        boolean bandera = true;
        if (fabricante.getNombre() == null || fabricante.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del faricante", "amarillo");
            bandera = false;
        }
        if (fabricante.getRegistroSanitario() == null || fabricante.getRegistroSanitario().equals("")) {
            mensaje.mensaje("Debes capturar el registro sanitario del fabricante", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeFabricante() {
        Fabricante fab = fabricanteService.fabricanteByNombre(nombreFabricante);
        return fab != null;
    }

    public void modificarRedirect(Fabricante fab) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("fabricante", fab);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleFabricantes.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/proveedores.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarFabricantes() {
        fabricanteList = new ArrayList<>();
        if (nombreFabricante == null || nombreFabricante.equals("")) {
            fabricanteList = fabricanteService.fabricanteList();
        } else {
            Fabricante fab = fabricanteService.fabricanteByNombre(nombreFabricante);
            if (fab != null) {
                fabricanteList.add(fab);
            }
        }

        if (fabricanteList == null || fabricanteList.isEmpty()) {
            mensaje.mensaje("No se encontraron fabricantes.", "amarillo");
        }
    }

    public void mostrarDialogo(Fabricante fab) {
        nombreFabricante = fab.getNombre();
        fabricante = fab;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaFabricante').show();");
    }

    public void eliminarFabricante() {
        nombreFabricante = fabricante.getNombre();
        fabricante.setActivo(0);
        fabricante.setFechaBaja(new Date());
        fabricante.setUsuarioBaja(usuarioLogin.getUsuario());
        fabricanteService.guardarFabricante(fabricante);
        fabricanteList = new ArrayList<>();
        fabricanteList = fabricanteService.fabricanteList();
        mensaje.mensaje("El tipo de documento " + nombreFabricante + " ha sido dado de baja.", "verde");
    }

    public String getNombreFabricante() {
        return nombreFabricante;
    }

    public void setNombreFabricante(String nombreFabricante) {
        this.nombreFabricante = nombreFabricante;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public List<Fabricante> getFabricanteList() {
        return fabricanteList;
    }

    public void setFabricanteList(List<Fabricante> fabricanteList) {
        this.fabricanteList = fabricanteList;
    }

}
