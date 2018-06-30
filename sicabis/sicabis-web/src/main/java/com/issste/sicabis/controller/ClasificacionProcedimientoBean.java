/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ClasificacionProcedimientoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.ClasificacionProcedimiento;
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
public class ClasificacionProcedimientoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    ClasificacionProcedimientoService clasifProcedService;

    private String nombreCP;

    private Usuarios usuarioLogin;
    private ClasificacionProcedimiento clasificacionProcedimiento;
    private List<ClasificacionProcedimiento> clasificacionProcedimientoList;
    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    public ClasificacionProcedimientoBean() {
        clasificacionProcedimiento = new ClasificacionProcedimiento();
        clasificacionProcedimientoList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catClasificacionProcedimientos.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ClasificacionProcedimientoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarClasificacionProcedimientos() {
        clasificacionProcedimientoList = new ArrayList<>();
        if (nombreCP == null || nombreCP.equals("")) {
            clasificacionProcedimientoList = clasifProcedService.obtenerClasificacionProcedimiento();
        } else {
            ClasificacionProcedimiento c = clasifProcedService.obtenerClasificacionProcedimientoByNombre(nombreCP);
            if (c != null) {
                clasificacionProcedimientoList.add(c);
            }
        }
        if (clasificacionProcedimientoList == null || clasificacionProcedimientoList.isEmpty()) {
            mensaje.mensaje("No se encontraron resultados.", "amarillo");
        }
    }

    public void modificarRedirect(ClasificacionProcedimiento cp) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("clasifProd", cp);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleClasificacionProcedimiento.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ClasificacionProcedimientoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarClasificacionProcedimiento() {
        nombreCP = clasificacionProcedimiento.getDescripcion();
        if (valida()) {
            if (validarAgregar()) {
                clasificacionProcedimiento.setActivo(1);
                clasificacionProcedimiento.setFechaAlta(new Date());
                clasificacionProcedimiento.setUsuarioAlta(usuarioLogin.getUsuario());
                clasifProcedService.guardarClasificacionProcedimiento(clasificacionProcedimiento);
                clasificacionProcedimiento = new ClasificacionProcedimiento();
                bitacoraTareaEstatus.setDescripcion("Guardar nueva clasificación procedimiento:" + nombreCP + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                mensaje.mensaje("La clasificación " + nombreCP + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("La clasificación " + nombreCP + " ya existe.", "rojo");
            }
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (clasificacionProcedimiento.getDescripcion() == null || clasificacionProcedimiento.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar la clasificación del procedimiento", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        ClasificacionProcedimiento c = clasifProcedService.obtenerClasificacionProcedimientoByNombre(nombreCP);
        return c == null;
    }

    public void mostrarDialogo(ClasificacionProcedimiento t) {
        nombreCP = t.getDescripcion();
        clasificacionProcedimiento = t;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarClasificacionProcedimiento').show();");
    }

    public void eliminarClasificacionProcedimiento() {
        nombreCP = clasificacionProcedimiento.getDescripcion();
        clasificacionProcedimiento.setActivo(0);
        clasificacionProcedimiento.setFechaBaja(new Date());
        clasificacionProcedimiento.setUsuarioBaja(usuarioLogin.getUsuario());
        clasifProcedService.guardarClasificacionProcedimiento(clasificacionProcedimiento);
        clasificacionProcedimientoList = new ArrayList<>();
        clasificacionProcedimientoList = clasifProcedService.obtenerClasificacionProcedimiento();
        bitacoraTareaEstatus.setDescripcion("Eliminar clasificación procedimiento:" + nombreCP + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        mensaje.mensaje("La clasificación " + nombreCP + " ha sido dado de baja.", "verde");
    }

    public String getNombreCP() {
        return nombreCP;
    }

    public void setNombreCP(String nombreCP) {
        this.nombreCP = nombreCP;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public ClasificacionProcedimiento getClasificacionProcedimiento() {
        return clasificacionProcedimiento;
    }

    public void setClasificacionProcedimiento(ClasificacionProcedimiento clasificacionProcedimiento) {
        this.clasificacionProcedimiento = clasificacionProcedimiento;
    }

    public List<ClasificacionProcedimiento> getClasificacionProcedimientoList() {
        return clasificacionProcedimientoList;
    }

    public void setClasificacionProcedimientoList(List<ClasificacionProcedimiento> clasificacionProcedimientoList) {
        this.clasificacionProcedimientoList = clasificacionProcedimientoList;
    }

}
