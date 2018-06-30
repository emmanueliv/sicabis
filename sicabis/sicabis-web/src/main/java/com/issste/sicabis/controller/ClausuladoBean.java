/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ClausuladoService;
import com.issste.sicabis.ejb.modelo.Almacen;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Clausulado;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.ArchivosUtilidades;
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

/**
 *
 * @author kriosoft
 */
public class ClausuladoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private ClausuladoService clausuladoService;

    private String clausuladoDesc;
    private int tipoClausulado;
    private Usuarios usuarioLogin;
    private Clausulado clausulado;

    private List<Clausulado> clausuladoList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    public ClausuladoBean() {
        clausulado = new Clausulado();
        clausuladoList = new ArrayList<>();
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
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/catClausulados.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ClausuladoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarClausulados() {
        clausuladoList = new ArrayList<>();
        if (tipoClausulado == 0) {
            clausuladoList = clausuladoService.obtenerClausulados();
        } else {
            Clausulado cl = clausuladoService.obtenerClausuladoByTipo(tipoClausulado);
            if (cl != null) {
                clausuladoList.add(cl);
            }
        }
        if (clausuladoList == null || clausuladoList.isEmpty()) {
            mensaje.mensaje("No se encontraron clausulados con la opción seleccionada.", "amarillo");
        }
    }

    public void modificarRedirect(Clausulado clausulado) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        util.setSessionMapValue("clausulado", clausulado);
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/catalogos/detalleClausulado.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ClausuladoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardaClausulado() {
        clausulado = new Clausulado();
        clausulado.setClausula(clausuladoDesc);
        clausulado.setClaveProcedimiento(6);
        String nombreArchivo = archivosUtilidades.guardaObjetoSerializableClausulado(clausulado, 6, 6, "");
        clausulado.setClausula(nombreArchivo);
        clausuladoService.modificarClausulado(clausulado);
        bitacoraTareaEstatus.setDescripcion("Guardar Clausulado");
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdModulos(4);
        bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
    }

    public String getClausuladoDesc() {
        return clausuladoDesc;
    }

    public void setClausuladoDesc(String clausuladoDesc) {
        this.clausuladoDesc = clausuladoDesc;
    }

    public int getTipoClausulado() {
        return tipoClausulado;
    }

    public void setTipoClausulado(int tipoClausulado) {
        this.tipoClausulado = tipoClausulado;
    }

    public Clausulado getClausulado() {
        return clausulado;
    }

    public void setClausulado(Clausulado clausulado) {
        this.clausulado = clausulado;
    }

    public List<Clausulado> getClausuladoList() {
        return clausuladoList;
    }

    public void setClausuladoList(List<Clausulado> clausuladoList) {
        this.clausuladoList = clausuladoList;
    }

}
