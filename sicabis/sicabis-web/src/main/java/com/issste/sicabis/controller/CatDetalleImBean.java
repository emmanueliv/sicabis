package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.CatDetalleImService;
import com.issste.sicabis.ejb.ln.JefaturaService;
import com.issste.sicabis.ejb.modelo.CatDetalleIm;
import com.issste.sicabis.ejb.modelo.Jefatura;
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

public class CatDetalleImBean implements Serializable {

    @EJB
    private CatDetalleImService catDetalleImService;

    @EJB
    private JefaturaService jefaturaService;

    private Usuarios usuarios;
    private CatDetalleIm catDetalleIm;
    private CatDetalleIm catDetalleImAux;

    private Integer idJefatura;

    private List<CatDetalleIm> catDetalleImList;
    private List<Jefatura> jefaturaList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public CatDetalleImBean() {
        catDetalleIm = new CatDetalleIm();
        catDetalleImList = new ArrayList();
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        jefaturaList = jefaturaService.getAll();
    }

    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catDetalleIM.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CatDetalleImBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validaExiste(Integer idJefatura) {
        boolean band = true;
        catDetalleImAux = catDetalleImService.obtenerByIdJefatura(idJefatura);
        if (catDetalleImAux != null) {
            band = false;
        }
        return band;
    }

    public boolean valida() {
        if (catDetalleIm.getIdJefatura() == null) {
            mensaje.mensaje("Debes seleccionar la jefatura.", "amarillo");
            return false;
        }
        return true;
    }

    public void redireccionaExiste() {
        System.out.println("g---->" + catDetalleIm);
        if (catDetalleIm.getIdJefatura() != null) {
            if (!validaExiste(catDetalleIm.getIdJefatura().getIdJefatura())) {
                this.modificarRedirect(catDetalleImAux);
            }
        }
    }

    public void guardar() {
        System.out.println("g---->" + catDetalleIm);
        if (valida()) {
            if (validaExiste(catDetalleIm.getIdJefatura().getIdJefatura())) {
                catDetalleIm.setActivo(1);
                catDetalleIm.setUsuarioAlta(usuarios.getUsuario());
                catDetalleIm.setIdUsuario(usuarios);
                if (catDetalleImService.guardar(catDetalleIm)) {
                    mensaje.mensaje(mensaje.datos_guardados, "verde");
                    catDetalleIm = new CatDetalleIm();
                } else {
                    mensaje.mensaje(mensaje.error_guardar, "rojo");
                }
            } else {
                mensaje.mensaje("Los datos de Investigación de mercado para esta jefatura ya existen.", "amarillo");
            }
        }
    }

    public void consultar() {
        System.out.println("c---->" + idJefatura);
        if (idJefatura == -1) {
            catDetalleImList = catDetalleImService.obtenerTodos();
        } else {
            catDetalleImAux = catDetalleImService.obtenerByIdJefatura(idJefatura);
            if (catDetalleImAux != null) {
                catDetalleImList = new ArrayList();
                catDetalleImList.add(catDetalleImAux);
            }
        }
        if (catDetalleImList == null || catDetalleImList.isEmpty()) {
            mensaje.mensaje("No existen registros almacenados.", "amarillo");
        }
    }

    public void modificarRedirect(CatDetalleIm cdim) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("cdim", cdim);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleCatDetalleIM.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarDialogo(CatDetalleIm cdim) {
        catDetalleImAux = cdim;
        RequestContext.getCurrentInstance().execute("PF('dialogElimina').show();");
    }

    public void elimina() {
        catDetalleImAux.setActivo(0);
        catDetalleImAux.setFechaBaja(new Date());
        catDetalleImAux.setUsuarioBaja(usuarios.getUsuario());
        if (catDetalleImService.actualizar(catDetalleImAux)) {
            mensaje.mensaje(mensaje.datos_eliminados, "verde");
            this.consultar();
        } else {
            mensaje.mensaje(mensaje.error_borrar, "rojo");
        }
    }

    public CatDetalleIm getCatDetalleIm() {
        return catDetalleIm;
    }

    public void setCatDetalleIm(CatDetalleIm catDetalleIm) {
        this.catDetalleIm = catDetalleIm;
    }

    public List<CatDetalleIm> getCatDetalleImList() {
        return catDetalleImList;
    }

    public void setCatDetalleImList(List<CatDetalleIm> catDetalleImList) {
        this.catDetalleImList = catDetalleImList;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public List<Jefatura> getJefaturaList() {
        return jefaturaList;
    }

    public void setJefaturaList(List<Jefatura> jefaturaList) {
        this.jefaturaList = jefaturaList;
    }

    public Integer getIdJefatura() {
        return idJefatura;
    }

    public void setIdJefatura(Integer idJefatura) {
        this.idJefatura = idJefatura;
    }

}
