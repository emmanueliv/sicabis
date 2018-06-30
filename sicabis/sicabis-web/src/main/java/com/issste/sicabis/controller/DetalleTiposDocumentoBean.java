/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.TareasService;
import com.issste.sicabis.ejb.ln.TipoDocumentosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Tareas;
import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author kriosoft
 */
public class DetalleTiposDocumentoBean {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoDocumentosService tipoDocumentosService;

    @EJB
    private TareasService tareasService;

    private String nombreDocumento;

    private Usuarios usuarioLogin;
    private TipoDocumentos tipoDocumento;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private List<Tareas> tareaList;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public DetalleTiposDocumentoBean() {
        tareaList = new ArrayList<>();
        tipoDocumento = (TipoDocumentos) util.getSessionAtributte("tipoDocumento");
        nombreDocumento = tipoDocumento.getNombre();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarioLogin = (Usuarios) util.getSessionAtributte("usuario");
        tareaList = tareasService.obtenerTareas();
    }

    public void guardarTipoDocumento() {
        nombreDocumento = tipoDocumento.getNombre();
        if (validar()) {
            if (validarAgregar()) {
                tipoDocumento.setActivo(1);
                tipoDocumento.setFechaModificacion(new Date());
                tipoDocumento.setUsuarioModificacion(usuarioLogin.getUsuario());
                tipoDocumentosService.guardarTipoDocumento(tipoDocumento);
                bitacoraTareaEstatus.setDescripcion("Actualiza tipo documento:" + nombreDocumento + "");
                bitacoraTareaEstatus.setFecha(new Date());
                bitacoraTareaEstatus.setIdModulos(4);
                bitacoraTareaEstatus.setIdUsuarios(usuarioLogin.getIdUsuario());
                bitacoraTareaEstatus.setIdTareaId(16);
                bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                tipoDocumento = new TipoDocumentos();
                mensaje.mensaje("El tipo de documento " + nombreDocumento + " se ha guardado correctamente.", "verde");
            } else {
                mensaje.mensaje("El tipo de documento " + nombreDocumento + " ya existe.", "rojo");
            }
        }
    }

    public boolean validar() {
        boolean bandera = true;
        if (tipoDocumento.getNombre() == null || tipoDocumento.getNombre().equals("")) {
            mensaje.mensaje("Debes capturar el nombre del tipo de documento", "amarillo");
            bandera = false;
        }
        if (tipoDocumento.getIdTarea() == null || tipoDocumento.getIdTarea().equals("")) {
            mensaje.mensaje("Debes seleccionar la tarea", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean validarAgregar() {
        TipoDocumentos td = tipoDocumentosService.obtenerTipoDocumentosByNombre(nombreDocumento);
        if (td != null) {
            if (Objects.equals(td.getIdTipoDocumento(), tipoDocumento.getIdTipoDocumento())) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public TipoDocumentos getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentos tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<Tareas> getTareaList() {
        return tareaList;
    }

    public void setTareaList(List<Tareas> tareaList) {
        this.tareaList = tareaList;
    }

    public Usuarios getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuarios usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

}
