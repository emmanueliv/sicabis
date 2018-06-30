/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.AreasService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.modelo.Area;
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
public class AreasBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    AreasService areasService;

    private String nombreArea;
    private Usuarios usuarioLogin;
    private Area area;
    private List<Area> areaList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    public AreasBean() {
        usuarioLogin = new Usuarios();
        area = new Area();
        areaList = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
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

    public void consultarAreas() {
        areaList = new ArrayList<>();
        if (nombreArea == null || nombreArea.equals("")) {
            areaList = areasService.obtenerAreas();
        } else {
            Area ar = areasService.obtenerAreaByNombre(nombreArea);
            if (ar != null) {
                areaList.add(ar);
            }
        }
        if (areaList == null || areaList.isEmpty()) {
            mensaje.mensaje("No se encontraron áreas.", "amarillo");
        }
    }

    public void mostrarDialogo(Area a) {
        nombreArea = a.getNombreArea();
        area = a;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaArea').show();");
    }

    public void eliminarArea() {
        nombreArea = area.getNombreArea();
        area.setActivo(0);
        area.setFechaBaja(new Date());
        area.setUsuarioBaja(usuarioLogin.getUsuario());
        areasService.guardarArea(area);
        areaList = new ArrayList<>();
        areaList = areasService.obtenerAreas();

        bitacoraTareaEstatus.setDescripcion("Borrado area:" + nombreArea + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(1);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        mensaje.mensaje("El área " + nombreArea + " ha sido dado de baja.", "verde");
    }

    public void modificarRedirect(Area ar) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("area", ar);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleAreas.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarArea() {
        nombreArea = area.getNombreArea();
        if (valida()) {
            if (!existeArea()) {
                area.setActivo(1);
                area.setFechaAlta(new Date());
                area.setUsuarioAlta(usuarioLogin.getUsuario());
                areasService.guardarArea(area);
                area = new Area();

                bitacoraTareaEstatus.setDescripcion("Guardado area:" + nombreArea + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(1);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                mensaje.mensaje("El área " + nombreArea + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El área " + nombreArea + " ya existe.", "rojo");
            }
        }
    }

    public boolean existeArea() {
        Area c = areasService.obtenerAreaByNombre(nombreArea);
        return c != null;
    }

    public boolean valida() {
        boolean bandera = true;
        if (area.getNombreArea() == null || area.getNombreArea().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del área", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

}
