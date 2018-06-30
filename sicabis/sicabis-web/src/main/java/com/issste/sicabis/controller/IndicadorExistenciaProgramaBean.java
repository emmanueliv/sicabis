package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.DelegacionesService;
import com.issste.sicabis.ejb.ln.ExistenciaUmusProgramasHistoricoService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.ExistenciaUmusProgramasHistorico;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaUmusProgramas;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciasUMUsProgramasService;
import com.issste.sicabis.utils.Mensajes;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

public class IndicadorExistenciaProgramaBean implements Serializable {

    @EJB
    private InsumosService insumosService;
    @EJB
    private DelegacionesService delegacionesService;
    @EJB
    private ExistenciaUmusProgramasHistoricoService existenciaUmusProgramasHistoricoService;
    @EJB
    private ExistenciasUMUsProgramasService existenciasUMUsProgramasService;

    private List<ExistenciaUmusProgramasHistorico> existenciaList;
    private Date fechaInicial;
    private Date fechaFinal;
    private String delegacion;
    private String numeroUmu;
    private String clave;
    private String nombreClave;
    private List<Delegaciones> delegacionList;
    private List<Insumos> insumosList;

    private Mensajes mensajes = new Mensajes();

    @PostConstruct
    public void init() {
        existenciaList = existenciaUmusProgramasHistoricoService.getAll();
        delegacionList = delegacionesService.obtenerDelegaciones();
        insumosList = insumosService.traeListaInsumos();
        fechaInicial = new Date();
        fechaFinal = new Date();
    }

    public boolean valida() {
        boolean bandera = true;
        if (fechaInicial == null) {
            bandera = false;
            mensajes.mensaje("Debes ingresar la fecha inicial", "amarillo");
        }
        if (fechaFinal == null) {
            bandera = false;
            mensajes.mensaje("Debes ingresar la fecha final", "amarillo");
        }
        if (fechaInicial != null && fechaFinal != null) {
            if (fechaFinal.compareTo(fechaInicial) < 0) {
                bandera = false;
                mensajes.mensaje("La fecha final es menor que la fecha inicial", "amarillo");
            }
        }
        return bandera;
    }

    public void buscarByFiltros() {
        if (valida()) {
            existenciaList = existenciaUmusProgramasHistoricoService.buscarByFiltros(fechaInicial, fechaFinal, delegacion, numeroUmu, clave, nombreClave);
            if (existenciaList == null) {
                mensajes.mensaje("No hay datos registrados", "amarillo");
            }
        }
    }

    public List<ExistenciaUmusProgramasHistorico> getExistenciaList() {
        return existenciaList;
    }

    public void setExistenciaList(List<ExistenciaUmusProgramasHistorico> existenciaList) {
        this.existenciaList = existenciaList;
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

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public String getNumeroUmu() {
        return numeroUmu;
    }

    public void setNumeroUmu(String numeroUmu) {
        this.numeroUmu = numeroUmu;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreClave() {
        return nombreClave;
    }

    public void setNombreClave(String nombreClave) {
        this.nombreClave = nombreClave;
    }

    public List<Delegaciones> getDelegacionList() {
        return delegacionList;
    }

    public void setDelegacionList(List<Delegaciones> delegacionList) {
        this.delegacionList = delegacionList;
    }

    public List<Insumos> getInsumosList() {
        return insumosList;
    }

    public void setInsumosList(List<Insumos> insumosList) {
        this.insumosList = insumosList;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

}
