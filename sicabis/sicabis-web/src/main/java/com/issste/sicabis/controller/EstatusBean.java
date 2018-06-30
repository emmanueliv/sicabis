/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.TareasService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Estatus;
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
public class EstatusBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TareasService tareasService;

    @EJB
    private EstatusService estatusService;

    private String nombreStatus;
    private Usuarios usuarioLogin;
    private Estatus estatus;
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private List<Estatus> estatusList;
    private List<Tareas> tareasList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public EstatusBean() {
        usuarioLogin = new Usuarios();
        estatus = new Estatus();
        estatusList = new ArrayList<>();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        tareasList = tareasService.obtenerTareas();
    }

    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catAreas.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarEstatus() {
        estatusList = new ArrayList<>();
        if (nombreStatus == null || nombreStatus.equals("")) {
            estatusList = estatusService.traeEstatusList();
        } else {
            Estatus e = estatusService.getEstatusByNombre(nombreStatus);
            if (e != null) {
                estatusList.add(e);
            }
        }

        if (estatusList == null || estatusList.isEmpty()) {
            mensaje.mensaje("No se encontraron proveedores.", "amarillo");
        }
    }

    public void mostrarDialogo(Estatus e) {
        nombreStatus = e.getNombre();
        estatus = e;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaEstatus').show();");
    }

    public void eliminarEstatus() {
        nombreStatus = estatus.getNombre();
        estatus.setActivo(0);
        estatus.setFechaBaja(new Date());
        estatus.setUsuarioBaja(usuarioLogin.getUsuario());
        estatusService.guardarEstatus(estatus);
        bitacoraTareaEstatus.setDescripcion("Eliminar estatus:" + nombreStatus + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        estatusList = new ArrayList<>();
        estatusList = estatusService.traeEstatusList();
        mensaje.mensaje("El estatus " + nombreStatus + " ha sido dado de baja.", "verde");
    }

    public void modificarRedirect(Estatus es) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("estatus", es);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleEstatus.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarEstatus() {
        nombreStatus = estatus.getNombre();
        if (valida()) {
            if (existeEstatus()) {
                estatus.setActivo(1);
                estatus.setFechaAlta(new Date());
                estatus.setUsuarioAlta(usuarioLogin.getUsuario());
                estatusService.guardarEstatus(estatus);
                bitacoraTareaEstatus.setDescripcion("Guardar estatus:" + nombreStatus + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                estatus = new Estatus();
                mensaje.mensaje("El estatus " + nombreStatus + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El estatus " + nombreStatus + " ya existe.", "rojo");
            }
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (estatus.getNombre() == null || estatus.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del estatus", "amarillo");
            bandera = false;
        }
        if (estatus.getIdTarea() == null || estatus.getIdTarea() == null) {
            mensaje.mensaje("Debes capturar la tarea del estatus", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeEstatus() {
        Estatus e = estatusService.getEstatusByNombre(nombreStatus);
        return e == null;
    }

    public String getNombreStatus() {
        return nombreStatus;
    }

    public void setNombreStatus(String nombreStatus) {
        this.nombreStatus = nombreStatus;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public List<Estatus> getEstatusList() {
        return estatusList;
    }

    public void setEstatusList(List<Estatus> estatusList) {
        this.estatusList = estatusList;
    }

    public List<Tareas> getTareasList() {
        return tareasList;
    }

    public void setTareasList(List<Tareas> tareasList) {
        this.tareasList = tareasList;
    }

}
