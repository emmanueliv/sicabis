/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.PartidaService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.PartidaPresupuestal;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class PartidaBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private PartidaService partidaService;

    private String nombrePartida;

    private Usuarios usuarioLogin;
    private PartidaPresupuestal partida;
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private List<PartidaPresupuestal> partidasList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public PartidaBean() {
        partida = new PartidaPresupuestal();
        partidasList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catPartidas.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PartidaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarPartidaPresupuestales() {
        partidasList = new ArrayList<>();
        if (nombrePartida == null || nombrePartida.equals("")) {
            partidasList = partidaService.getPartidaPresupuestalesByActivo();
        } else {
            PartidaPresupuestal pp = partidaService.obtenerAreaByNombre(nombrePartida);
            if (pp != null) {
                partidasList.add(pp);
            }
        }
        if (partidasList == null || partidasList.isEmpty()) {
            mensaje.mensaje("No se encontraron partidas.", "amarillo");
        }
    }

    public void modificarRedirect(PartidaPresupuestal partida) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("partida", partida);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detallePartida.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ArticulosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarPartidaPresupuestal() {
        nombrePartida = partida.getDescripcion();
        if (validar()) {
            if (validarAgregar()) {
                partida.setActivo(1);
                partida.setUsuarioAlta(usuarioLogin.getUsuario());
                partida.setFechaAlta(new Date());
                partidaService.guardarPartidaP(partida);
                bitacoraTareaEstatus.setDescripcion("Guardar partida:" + nombrePartida + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                partida = new PartidaPresupuestal();
                mensaje.mensaje("La partida " + nombrePartida + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("La partida " + nombrePartida + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (partida.getPartida() == null || partida.getPartida().equals("")) {
            mensaje.mensaje("Debes capturar la partida", "amarillo");
            bandera = false;
        } else {
            try {
                String texto = partida.getPartida();
                Long.parseLong(texto);
            } catch (Exception ex) {
                mensaje.mensaje("El formato del campo partida debe ser numérico", "amarillo");
                Logger.getLogger(ArticulosBean.class.getName()).log(Level.SEVERE, null, ex);
                bandera = false;
            }
        }
        if (partida.getDescripcion() == null || partida.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar la descripción de la partida presupuestal", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        PartidaPresupuestal f = partidaService.obtenerAreaByNombre(nombrePartida);
        return f == null;
    }

    public void mostrarDialogo(PartidaPresupuestal p) {
        nombrePartida = p.getDescripcion();
        partida = p;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarPartidaPresupuestal').show();");
    }

    public void eliminarPartidaPresupuestal() {
        nombrePartida = partida.getDescripcion();
        partida.setActivo(0);
        partida.setUsuarioBaja(usuarioLogin.getUsuario());
        partida.setFechaBaja(new Date());
        partidaService.guardarPartidaP(partida);
        partidasList = new ArrayList<>();
        partidasList = partidaService.obtenerPartidaPresupuestales();
        mensaje.mensaje("La partida " + nombrePartida + " ha sido dado de baja.", "verde");
    }

    public String getNombrePartida() {
        return nombrePartida;
    }

    public void setNombrePartida(String nombrePartida) {
        this.nombrePartida = nombrePartida;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public PartidaPresupuestal getPartida() {
        return partida;
    }

    public void setPartida(PartidaPresupuestal partida) {
        this.partida = partida;
    }

    public List<PartidaPresupuestal> getPartidasList() {
        return partidasList;
    }

    public void setPartidasList(List<PartidaPresupuestal> partidasList) {
        this.partidasList = partidasList;
    }

}
