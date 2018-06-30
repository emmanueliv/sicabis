/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ClausuladoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Clausulado;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author kriosoft
 */
public class DetalleClausuladoBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private ClausuladoService clausuladoService;

    private String clausuladoDesc;
    //private String clausuladoAux;

    private Clausulado clausulado;
    private Usuarios usuarioLogin;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();

    public DetalleClausuladoBean() {
        clausulado = new Clausulado();
        usuarioLogin = new Usuarios();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        clausulado = (Clausulado) util.getSessionAtributte("clausulado");
        //clausuladoAux = clausulado.getClausula();
        String ruta = archivosUtilidades.PATHFILESCLAUSULAS;
        if (clausulado.getClaveProcedimiento() == 6) {
            clausuladoDesc = "Convenio";
        } else {
            clausuladoDesc = "Contrato";
        }
        Clausulado c = (Clausulado) archivosUtilidades.obtieneObjetoSerializableClausulado(clausulado.getClausula(), ruta);
        //System.out.println("--->"+clausulado.getClausula());
        clausulado.setClausula(c.getClausula());
    }

    public void guardarClausulado() {
        if (valida()) {
            clausulado.setFechaModificacion(new Date());
            clausulado.setUsuarioModificacion(usuarioLogin.getUsuario());
            String nombreArchivo = archivosUtilidades.guardaObjetoSerializableClausulado(clausulado, clausulado.getClaveProcedimiento(), clausulado.getClaveProcedimiento(), "");
            clausulado.setClausula(nombreArchivo);
            clausuladoService.modificarClausulado(clausulado);
            bitacoraTareaEstatus.setDescripcion("Actualiza clausulado");
            bitacoraTareaEstatus.setFecha(new Date());
            bitacoraTareaEstatus.setIdModulos(4);
            bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
            bitacoraTareaEstatus.setIdTareaId(16);
            bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
            clausulado = new Clausulado();
            mensaje.mensaje("El clausulado se ha modificado correctamente.", "verde");
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (clausulado.getClausula() == null || clausulado.getClausula().equals("")) {
            mensaje.mensaje("Debes capturar el clausulado.", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public String getClausuladoDesc() {
        return clausuladoDesc;
    }

    public void setClausuladoDesc(String clausuladoDesc) {
        this.clausuladoDesc = clausuladoDesc;
    }

    public Clausulado getClausulado() {
        return clausulado;
    }

    public void setClausulado(Clausulado clausulado) {
        this.clausulado = clausulado;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Utilidades getUtil() {
        return util;
    }

    public void setUtil(Utilidades util) {
        this.util = util;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

}
