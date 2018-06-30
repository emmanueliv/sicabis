package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.CatDetalleImService;
import com.issste.sicabis.ejb.modelo.CatDetalleIm;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class DetalleCatDetalleIMBean {

    @EJB
    private CatDetalleImService catDetalleImService;

    private Usuarios usuarios;
    private CatDetalleIm catDetalleIm;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleCatDetalleIMBean() {
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        catDetalleIm = (CatDetalleIm) util.getSessionAtributte("cdim");
    }
    
    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catDetalleIM.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DetalleCatDetalleIMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizar() {
        System.out.println("g---->" + catDetalleIm);
        catDetalleIm.setUsuarioModificacion(usuarios.getUsuario());
        catDetalleIm.setFechaModificacion(new Date());
        catDetalleIm.setIdUsuario(usuarios);
        if (catDetalleImService.actualizar(catDetalleIm)) {
            mensaje.mensaje(mensaje.datos_actualizados, "verde");
        } else {
            mensaje.mensaje(mensaje.error_modificar, "rojo");
        }
    }

    public CatDetalleIm getCatDetalleIm() {
        return catDetalleIm;
    }

    public void setCatDetalleIm(CatDetalleIm catDetalleIm) {
        this.catDetalleIm = catDetalleIm;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

}
