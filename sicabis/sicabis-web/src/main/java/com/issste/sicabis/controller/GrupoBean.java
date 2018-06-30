/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.GrupoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Grupo;
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
public class GrupoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private GrupoService grupoService;

    private String nombreGrupo;
    private Usuarios usuarioLogin;
    private Grupo grupo;
    private List<Grupo> grupoList;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public GrupoBean() {
        usuarioLogin = new Usuarios();
        grupo = new Grupo();
        grupoList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catGrupos.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarGrupos() {
        grupoList = new ArrayList<>();
        if (nombreGrupo == null || nombreGrupo.equals("")) {
            grupoList = grupoService.traeListaGrupos();
        } else {
            Grupo g = grupoService.obtenerGrupoByNombre(nombreGrupo);
            if (g != null) {
                grupoList.add(g);
            }
        }
        if (grupoList == null || grupoList.isEmpty()) {
            mensaje.mensaje("No se encontraron grupos.", "amarillo");
        }
    }

    public void mostrarDialogo(Grupo g) {
        nombreGrupo = g.getGrupo();
        grupo = g;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaGrupo').show();");
    }

    public void eliminarGrupo() {
        nombreGrupo = grupo.getGrupo();
        grupo.setActivo(0);
        grupo.setFechaBaja(new Date());
        grupo.setUsuarioBaja(usuarioLogin.getUsuario());
        grupoService.guardarGrupo(grupo);
        bitacoraTareaEstatus.setDescripcion("Guardar grupo:" + nombreGrupo + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        grupoList = new ArrayList<>();
        grupoList = grupoService.traeListaGrupos();
        mensaje.mensaje("El grupo " + nombreGrupo + " ha sido dado de baja.", "verde");
    }

    public void modificarRedirect(Grupo gr) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("grupo", gr);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleGrupo.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarGrupo() {
        nombreGrupo = grupo.getGrupo();
        if (validarAgregar()) {
            if (!existeGrupo()) {
                grupo.setActivo(1);
                grupo.setFechaAlta(new Date());
                grupo.setUsuarioAlta(usuarioLogin.getUsuario());
                grupoService.guardarGrupo(grupo);
                bitacoraTareaEstatus.setDescripcion("Eliminar grupo:" + nombreGrupo + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                grupo = new Grupo();
                mensaje.mensaje("El grupo " + nombreGrupo + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El grupo " + nombreGrupo + " ya existe.", "rojo");
            }
        }
    }

    public boolean validarAgregar() {
        boolean bandera = true;
        if (grupo.getGrupo() == null || grupo.getGrupo().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del grupo", "amarillo");
            bandera = false;
        }
        if (grupo.getDescripcion() == null || grupo.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar la descripción del grupo", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeGrupo() {
        Grupo g = grupoService.obtenerGrupoByNombre(nombreGrupo);
        return g != null;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

}
