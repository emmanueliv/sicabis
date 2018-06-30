/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.BitacoraDTO;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.TareasService;
import com.issste.sicabis.ejb.ln.UsuariosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Tareas;
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

/**
 *
 * @author kriosoft
 */
public class BitacoraTareaBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraService;

    @EJB
    private UsuariosService usuariosService;

    @EJB
    private TareasService tareasService;

    @EJB
    private EstatusService estatusService;

    private Date fechaInicial;
    private Date fechaFinal;
    private BitacoraTareaEstatus bitacoraTarea;
    private List<BitacoraTareaEstatus> bitacoraTareaList;
    private List<BitacoraDTO> listaBitacoraDto;
    private List<Usuarios> usuariosList;
    private List<Tareas> tareasList;
    private List<Estatus> estatusList;

    private int idTarea;

    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();

    public BitacoraTareaBean() {
        bitacoraTarea = new BitacoraTareaEstatus();
        bitacoraTareaList = new ArrayList<>();
        usuariosList = new ArrayList<>();
        tareasList = new ArrayList<>();
        estatusList = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        usuariosList = usuariosService.getUsuarios();
        tareasList = tareasService.obtenerTareas();
    }

    public void consultarBitacora() {
        bitacoraTareaList = new ArrayList<>();
        if (validarFechas()) {
            if (!validarConsulta()) {
                bitacoraTareaList = bitacoraService.obtenerRegistros();
            } else {
                bitacoraTareaList = bitacoraService.obtenerRegistrosByFiltros(bitacoraTarea, fechaInicial, fechaFinal);
            }
            if (bitacoraTareaList == null || bitacoraTareaList.isEmpty()) {
                mensaje.mensaje("No existen resultados", "amarillo");
            } else {
                listaBitacoraDto = new ArrayList();
                BitacoraDTO bitacoraDto = null;
                for (BitacoraTareaEstatus bte : bitacoraTareaList) {
                    bitacoraDto = new BitacoraDTO();
                    bitacoraDto.setDescripcion(bte.getDescripcion());
                    bitacoraDto.setFecha(bte.getFecha());
                    if (bte.getIdTareaId() != null) {
                        bitacoraDto.setTarea(tareasService.getByIdTarea(bte.getIdTareaId()));
                    }
                    bitacoraDto.setEstatus(estatusService.getRemisionEstatus(bte.getIdEstatus()));
                    bitacoraDto.setUsuarios(usuariosService.getByIdUsuario(bte.getIdUsuarios()));
                    listaBitacoraDto.add(bitacoraDto);
                }
            }
        }
    }

    private boolean validarFechas() {
        boolean bandera = true;
        if (fechaInicial == null && fechaFinal != null) {
            mensaje.mensaje("Debes capturar la fecha inicial.", "amarillo");
            bandera = false;
        }
        if (fechaInicial != null && fechaFinal == null) {
            mensaje.mensaje("Debes capturar la fecha final.", "amarillo");
            bandera = false;
        }
        if (fechaInicial != null && fechaFinal != null) {
            if (fechaFinal.before(fechaInicial)) {
                mensaje.mensaje("La fecha final no puede ser menor a la fecha inicial.", "amarillo");
                bandera = false;
            }
        }
        return bandera;
    }

    public boolean validarConsulta() {
        return fechaInicial != null || fechaFinal != null || bitacoraTarea.getIdUsuarios() != 0
                || !bitacoraTarea.getDescripcion().equals("") || bitacoraTarea.getIdTareaId() != 0
                || bitacoraTarea.getIdEstatus() != 0;
    }

    public void limpiar() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/administracion/auditoria/bitacora.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(BitacoraTareaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obtenerStatus() {
        estatusList = new ArrayList<>();
        if (bitacoraTarea.getIdTareaId() != 0) {
            estatusList = estatusService.getEstatusByTarea(bitacoraTarea.getIdTareaId());
        }
    }

    public BitacoraTareaEstatus getBitacoraTarea() {
        return bitacoraTarea;
    }

    public void setBitacoraTarea(BitacoraTareaEstatus bitacoraTarea) {
        this.bitacoraTarea = bitacoraTarea;
    }

    public List<BitacoraTareaEstatus> getBitacoraTareaList() {
        return bitacoraTareaList;
    }

    public void setBitacoraTareaList(List<BitacoraTareaEstatus> bitacoraTareaList) {
        this.bitacoraTareaList = bitacoraTareaList;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public List<Tareas> getTareasList() {
        return tareasList;
    }

    public void setTareasList(List<Tareas> tareasList) {
        this.tareasList = tareasList;
    }

    public List<Estatus> getEstatusList() {
        return estatusList;
    }

    public void setEstatusList(List<Estatus> estatusList) {
        this.estatusList = estatusList;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public List<BitacoraDTO> getListaBitacoraDto() {
        return listaBitacoraDto;
    }

    public void setListaBitacoraDto(List<BitacoraDTO> listaBitacoraDto) {
        this.listaBitacoraDto = listaBitacoraDto;
    }

}
