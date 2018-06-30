/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.ArticulosService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.FundamentoLegal;
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
public class ArticulosBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    ArticulosService articulosService;

    private String nombreFundamentoLegal;

    private Usuarios usuarioLogin;
    private FundamentoLegal funfamento;

    private List<FundamentoLegal> funfamentosList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    public ArticulosBean() {
        funfamento = new FundamentoLegal();
        funfamentosList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catArticulos.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ArticulosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarFundamentoLegales() {
        funfamentosList = new ArrayList<>();
        if (nombreFundamentoLegal == null || nombreFundamentoLegal.equals("")) {
            funfamentosList = articulosService.getFundamentosByActivo();
        } else {
            FundamentoLegal fun = articulosService.obtenerFundamentoLegalByNombre(nombreFundamentoLegal);
            if (fun != null) {
                funfamentosList.add(fun);
            }
        }
        if (funfamentosList == null || funfamentosList.isEmpty()) {
            mensaje.mensaje("No se encontraron fundamentos.", "amarillo");
        }
    }

    public void modificarRedirect(FundamentoLegal fundamento) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("fundamento", fundamento);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleArticulo.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ArticulosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarFundamentoLegal() {
        nombreFundamentoLegal = funfamento.getDescripcion();
        if (valida()) {
            if (validarAgregar()) {
                funfamento.setFechaAlta(new Date());
                funfamento.setUsuarioAlta(usuarioLogin.getUsuario());
                funfamento.setActivo(1);
                articulosService.guardarFundamentoLegal(funfamento);
                funfamento = new FundamentoLegal();
                bitacoraTareaEstatus.setDescripcion("Guardado fundamento legal:" + nombreFundamentoLegal + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(1);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                mensaje.mensaje("El fundamento " + nombreFundamentoLegal + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El fundamento " + nombreFundamentoLegal + " ya existe.", "rojo");
            }
        }
    }

    public boolean validarAgregar() {
        FundamentoLegal f = articulosService.obtenerFundamentoLegalByNombre(nombreFundamentoLegal);
        return f == null;
    }

    public boolean valida() {
        boolean bandera = true;
        if (funfamento.getNombre() == null || funfamento.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del fundamento legal", "amarillo");
            bandera = false;
        }
        if (funfamento.getDescripcion() == null || funfamento.getDescripcion().equals("")) {
            mensaje.mensaje("Debes capturar la descrpción del fundamento legal", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public void mostrarDialogo(FundamentoLegal f) {
        nombreFundamentoLegal = f.getDescripcion();
        funfamento = f;
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarFundamentoLegal').show();");
    }

    public void eliminarFundamentoLegal() {
        nombreFundamentoLegal = funfamento.getDescripcion();
        funfamento.setFechaBaja(new Date());
        funfamento.setUsuarioBaja(usuarioLogin.getUsuario());
        articulosService.guardarFundamentoLegal(funfamento);
        funfamentosList = new ArrayList<>();
        funfamentosList = articulosService.obtenerArticulos();
        bitacoraTareaEstatus.setDescripcion("Borrado funfamento legal:" + nombreFundamentoLegal + "");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(1);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        mensaje.mensaje("El fundamento legal " + nombreFundamentoLegal + " ha sido dado de baja.", "verde");
    }

    public String getNombreFundamentoLegal() {
        return nombreFundamentoLegal;
    }

    public void setNombreFundamentoLegal(String nombreFundamentoLegal) {
        this.nombreFundamentoLegal = nombreFundamentoLegal;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public FundamentoLegal getFunfamento() {
        return funfamento;
    }

    public void setFunfamento(FundamentoLegal funfamento) {
        this.funfamento = funfamento;
    }

    public List<FundamentoLegal> getFunfamentosList() {
        return funfamentosList;
    }

    public void setFunfamentosList(List<FundamentoLegal> funfamentosList) {
        this.funfamentosList = funfamentosList;
    }

}
