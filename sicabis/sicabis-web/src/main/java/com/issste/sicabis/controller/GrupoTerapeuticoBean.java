/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.GrupoTerapeuticoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.GrupoTerapeutico;
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
public class GrupoTerapeuticoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private GrupoTerapeuticoService grupoTerapeuticoService;

    private String nombreGT;

    private Usuarios usuarioLogin;
    private GrupoTerapeutico grupoT;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private List<GrupoTerapeutico> grupoTList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public GrupoTerapeuticoBean() {
        grupoT = new GrupoTerapeutico();
        grupoTList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catGruposTerapeuticos.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(GrupoTerapeuticoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarGrupoTerapeuticos() {
        grupoTList = new ArrayList<>();
        if (nombreGT == null || nombreGT.equals("")) {
            grupoTList = grupoTerapeuticoService.obtenerGruposTerapeuticos();
        } else {
            GrupoTerapeutico c = grupoTerapeuticoService.obtenerGPByNombre(nombreGT);
            if (c != null) {
                grupoTList.add(c);
            }
        }
        if (grupoTList == null || grupoTList.isEmpty()) {
            mensaje.mensaje("No se encontraron resultados.", "amarillo");
        }
    }

    public void modificarRedirect(GrupoTerapeutico gp) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("grupoT", gp);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleGrupoTerapeutico.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(GrupoTerapeuticoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarGrupoTerapeutico() {
        nombreGT = grupoT.getDescripcion();
        if (validar()) {
            if (validarAgregar()) {
                grupoT.setActivo(1);
                grupoT.setFechaAlta(new Date());
                grupoT.setUsuarioAlta(usuarioLogin.getUsuario());
                grupoTerapeuticoService.guardarGruposTerapeuticos(grupoT);
                bitacoraTareaEstatus.setDescripcion("Guardar grupo terapeutico:" + nombreGT + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                grupoT = new GrupoTerapeutico();
                mensaje.mensaje("El grupo terapéutico " + nombreGT + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El grupo terapéutico " + nombreGT + " ya existe.", "rojo");
            }
            nombreGT = "";
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (grupoT.getDescripcion() == null || grupoT.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del grupo terapeútico", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        GrupoTerapeutico c = grupoTerapeuticoService.obtenerGPByNombre(nombreGT);
        return c == null;
    }

    public void mostrarDialogo(GrupoTerapeutico g) {
        nombreGT = g.getDescripcion();
        grupoT = g;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarGrupoTerapeutico').show();");
    }

    public void eliminarGrupoTerapeutico() {
        nombreGT = grupoT.getDescripcion();
        grupoT.setActivo(0);
        grupoT.setFechaBaja(new Date());
        grupoT.setUsuarioBaja(usuarioLogin.getUsuario());
        grupoTerapeuticoService.guardarGruposTerapeuticos(grupoT);
        bitacoraTareaEstatus.setDescripcion("Eliminar grupo terapeutico:" + nombreGT + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        grupoTList = new ArrayList<>();
        grupoTList = grupoTerapeuticoService.obtenerGruposTerapeuticos();
        mensaje.mensaje("El grupo terapéutico " + nombreGT + " ha sido dado de baja.", "verde");
    }

    public String getNombreGT() {
        return nombreGT;
    }

    public void setNombreGT(String nombreGT) {
        this.nombreGT = nombreGT;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public GrupoTerapeutico getGrupoT() {
        return grupoT;
    }

    public void setGrupoT(GrupoTerapeutico grupoT) {
        this.grupoT = grupoT;
    }

    public List<GrupoTerapeutico> getGrupoTList() {
        return grupoTList;
    }

    public void setGrupoTList(List<GrupoTerapeutico> grupoTList) {
        this.grupoTList = grupoTList;
    }

}
