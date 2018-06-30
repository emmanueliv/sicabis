package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ClasificacionImportanciaService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.ClasificacionImportancia;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
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

public class ClasificacionImportanciaBean {
    
    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;
    
    @EJB
    private ClasificacionImportanciaService clasificacionImportanciaService;
    
    private Usuarios usuarioLogin;
    private ClasificacionImportancia clasificacionImportancia;
    private ClasificacionImportancia clasificacionImportanciaBuscar;
    
    private List<ClasificacionImportancia> listaClasificacion;
    private String sigla;
    
    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    
    @PostConstruct
    public void init() {
        clasificacionImportancia = new ClasificacionImportancia();
        clasificacionImportanciaBuscar = new ClasificacionImportancia();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        listaClasificacion = new ArrayList();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        usuarioLogin = new Usuarios();
        util = new Utilidades();
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        
    }
    
    public void cancel() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catClasificacionImportancia.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ClasificacionImportanciaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void consulta() {
        listaClasificacion = new ArrayList<>();
        listaClasificacion = clasificacionImportanciaService.obtenerByClasificacion(clasificacionImportanciaBuscar);
        if (listaClasificacion == null || listaClasificacion.isEmpty()) {
            mensaje.mensaje("No se encontraron clasificaciones.", "amarillo");
        }
    }
    
    public boolean valida() {
        boolean bandera = true;
        if (clasificacionImportancia.getSigla() == null || clasificacionImportancia.getSigla().equals("")) {
            mensaje.mensaje("Debes ingresar la sigla de la clasificación", "amarillo");
            bandera = false;
        } else {
            ClasificacionImportancia ci = new ClasificacionImportancia();
            ci.setSigla(clasificacionImportancia.getSigla());
            List<ClasificacionImportancia> listaCI = clasificacionImportanciaService.obtenerByClasificacion(ci);
            if (listaCI != null) {
                mensaje.mensaje("La sigla de la clasificación ya se encuentra previamente almacenada", "amarillo");
                bandera = false;
            }
        }
        if (clasificacionImportancia.getDescripcion() == null || clasificacionImportancia.getDescripcion().equals("")) {
            mensaje.mensaje("Debes ingresar la descripción de la clasificación", "amarillo");
            bandera = false;
        }
        return bandera;
    }
    
    public void guardar() {
        if (valida()) {
            clasificacionImportancia.setActivo(1);
            clasificacionImportancia.setUsuarioAlta(usuarioLogin.getUsuario());
            clasificacionImportancia.setIdClasificacionImportancia(clasificacionImportanciaService.obtenerUltimoRegistro() + 1);
            clasificacionImportancia.setFechaAlta(new Date());
            clasificacionImportanciaService.guardarClasificacionImportancia(clasificacionImportancia);
            bitacoraTareaEstatus.setDescripcion("Guardar nueva clasificacion importancia:" + clasificacionImportancia.getSigla() + "");
            bitacoraTareaEstatus.setFecha(new Date());
            bitacoraTareaEstatus.setIdModulos(4);
            bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
            bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
            mensaje.mensaje("La clasificación " + clasificacionImportancia.getSigla() + " se ha guardado correctamente.", "verde");
        }
    }
    
    public void modificar(ClasificacionImportancia ci) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("clasificacionImportancia", ci);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleClasificacionImportancia.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ClasificacionImportanciaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarDialogo(ClasificacionImportancia ci) {
        sigla = ci.getSigla();
        clasificacionImportancia = ci;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarClasificacion').show();");
    }
    
    public void eliminar() {
        sigla = clasificacionImportancia.getSigla();
        clasificacionImportancia.setActivo(0);
        clasificacionImportancia.setFechaBaja(new Date());
        clasificacionImportancia.setUsuarioBaja(usuarioLogin.getUsuario());
        clasificacionImportanciaService.guardarClasificacionImportancia(clasificacionImportancia);
        listaClasificacion = new ArrayList<>();
        listaClasificacion = clasificacionImportanciaService.obtenerByClasificacion(clasificacionImportancia);
        mensaje.mensaje("La clasificación " + sigla + " ha sido dado de baja.", "verde");
    }
    
    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }
    
    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }
    
    public ClasificacionImportancia getClasificacionImportancia() {
        return clasificacionImportancia;
    }
    
    public void setClasificacionImportancia(ClasificacionImportancia clasificacionImportancia) {
        this.clasificacionImportancia = clasificacionImportancia;
    }
    
    public List<ClasificacionImportancia> getListaClasificacion() {
        return listaClasificacion;
    }
    
    public void setListaClasificacion(List<ClasificacionImportancia> listaClasificacion) {
        this.listaClasificacion = listaClasificacion;
    }
    
    public Mensajes getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }
    
    public ClasificacionImportancia getClasificacionImportanciaBuscar() {
        return clasificacionImportanciaBuscar;
    }
    
    public void setClasificacionImportanciaBuscar(ClasificacionImportancia clasificacionImportanciaBuscar) {
        this.clasificacionImportanciaBuscar = clasificacionImportanciaBuscar;
    }
    
    public String getSigla() {
        return sigla;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
}
