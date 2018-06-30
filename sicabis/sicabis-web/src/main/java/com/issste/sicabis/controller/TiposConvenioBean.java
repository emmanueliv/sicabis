/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.TiposConvenioService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.TipoConvenio;
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
public class TiposConvenioBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TiposConvenioService tiposConvenioService;

    private String nombreConvenio;
    private Usuarios usuarioLogin;
    private TipoConvenio convenio;
    private List<TipoConvenio> conveniosList;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public TiposConvenioBean() {
        usuarioLogin = new Usuarios();
        convenio = new TipoConvenio();
        conveniosList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catTipoConvenios.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarTipoConvenios() {
        conveniosList = new ArrayList<>();
        if (nombreConvenio == null || nombreConvenio.equals("")) {
            conveniosList = tiposConvenioService.obtenerTipoConvenios();
        } else {
            TipoConvenio tc = tiposConvenioService.obtenerTipoConvenioByNombre(nombreConvenio);
            if (tc != null) {
                conveniosList.add(tc);
            }
        }
        if (conveniosList == null || conveniosList.isEmpty()) {
            mensaje.mensaje("No se encontraron tipos de convenio.", "amarillo");
        }
    }

    public void mostrarDialogo(TipoConvenio tc) {
        nombreConvenio = tc.getDescripcion();
        convenio = tc;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminaTipoConvenio').show();");
    }

    public void eliminarTipoConvenio() {
        nombreConvenio = convenio.getDescripcion();
        convenio.setActivo(0);
        convenio.setFechaBaja(new Date());
        convenio.setUsuarioBaja(usuarioLogin.getUsuario());
        tiposConvenioService.guardarTipoConvenio(convenio);
        bitacoraTareaEstatus.setDescripcion("Elimina convenio:" + nombreConvenio + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(16);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        conveniosList = new ArrayList<>();
        conveniosList = tiposConvenioService.obtenerTipoConvenios();
        mensaje.mensaje("El tipo de convenio " + nombreConvenio + " ha sido dado de baja.", "verde");
    }

    public void modificarRedirect(TipoConvenio tc) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("convenio", tc);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleTiposConvenio.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(TiposDocumentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarTipoConvenio() {
        nombreConvenio = convenio.getNombre();
        if (validar()) {
            if (!existeTipoConvenio()) {
                convenio.setActivo(1);
                convenio.setFechaAlta(new Date());
                convenio.setUsuarioAlta(usuarioLogin.getUsuario());
                tiposConvenioService.guardarTipoConvenio(convenio);
                bitacoraTareaEstatus.setDescripcion("Guardar convenio:" + nombreConvenio + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                convenio = new TipoConvenio();
                mensaje.mensaje("El tipo de convenio " + nombreConvenio + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El tipo de convenio " + nombreConvenio + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (convenio.getNombre() == null || convenio.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del tipo de convenio", "amarillo");
            bandera = false;
        }
        if (convenio.getDescripcion() == null || convenio.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar la descripción del tipo de convenio", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean existeTipoConvenio() {
        TipoConvenio tc = tiposConvenioService.obtenerTipoConvenioByNombre(nombreConvenio);
        return tc != null;
    }

    public String getNombreConvenio() {
        return nombreConvenio;
    }

    public void setNombreConvenio(String nombreConvenio) {
        this.nombreConvenio = nombreConvenio;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public TipoConvenio getConvenio() {
        return convenio;
    }

    public void setConvenio(TipoConvenio convenio) {
        this.convenio = convenio;
    }

    public List<TipoConvenio> getConveniosList() {
        return conveniosList;
    }

    public void setConveniosList(List<TipoConvenio> conveniosList) {
        this.conveniosList = conveniosList;
    }
}
