/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.TareasService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Tareas;
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
public class TareasBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TareasService tareasService;

    private String nombreTarea;
    private Usuarios usuarioLogin;
    private Tareas tarea;
    private List<Tareas> tareaList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    public TareasBean() {
        usuarioLogin = new Usuarios();
        tarea = new Tareas();
        tareaList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catTareas.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarTareas() {
        tareaList = new ArrayList<>();
        if (nombreTarea == null || nombreTarea.equals("")) {
            tareaList = tareasService.obtenerTareas();
        } else {
            Tareas t = tareasService.obtenerTareaByNombre(nombreTarea);
            if (t != null) {
                tareaList.add(t);
            }
        }
        if (tareaList == null || tareaList.isEmpty()) {
            mensaje.mensaje("No se encontraron tareas.", "amarillo");
        }
    }

    public void mostrarDialogo(Tareas t) {
        nombreTarea = t.getDescripcion();
        tarea = t;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaTarea').show();");
    }

    public void eliminarTarea() {
        nombreTarea = tarea.getDescripcion();
        tarea.setFechaBaja(new Date());
        tarea.setUsuarioBaja(usuarioLogin.getUsuario());
        tareasService.guardarTarea(tarea);
        bitacoraTareaEstatus.setDescripcion("Eliminar tarea:" + nombreTarea + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        tareaList = new ArrayList<>();
        tareaList = tareasService.obtenerTareas();
        mensaje.mensaje("La tarea " + nombreTarea + " ha sido dado de baja.", "verde");
    }

    public void modificarRedirect(Tareas tar) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("tarea", tar);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleTarea.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarTarea() {
        nombreTarea = tarea.getDescripcion();
        if (validar()) {
            if (!existeTarea()) {
                tarea.setFechaAlta(new Date());
                tarea.setUsuarioAlta(usuarioLogin.getUsuario());
                tareasService.guardarTarea(tarea);
                bitacoraTareaEstatus.setDescripcion("Guardar tarea:" + nombreTarea + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                tarea = new Tareas();
                mensaje.mensaje("La tarea " + nombreTarea + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("La tarea " + nombreTarea + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (tarea.getDescripcion() == null || tarea.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar la descripción de la tarea", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeTarea() {
        Tareas t = tareasService.obtenerTareaByNombre(nombreTarea);
        return t != null;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Tareas getTarea() {
        return tarea;
    }

    public void setTarea(Tareas tarea) {
        this.tarea = tarea;
    }

    public List<Tareas> getTareaList() {
        return tareaList;
    }

    public void setTareaList(List<Tareas> tareaList) {
        this.tareaList = tareaList;
    }

}
