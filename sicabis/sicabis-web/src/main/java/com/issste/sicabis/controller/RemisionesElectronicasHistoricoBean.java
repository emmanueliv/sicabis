package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.AnoRemisionesElectronicasDTO;
import com.issste.sicabis.ejb.DTO.MesRemisionesElectronicasDTO;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.RemisionesElectronicasEntregasUmuHistoricoService;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.RemisionesElectronicasEntregasUmuHistorico;
import com.issste.sicabis.ejb.service.silodisa.modelo.CatInsumos;
import com.issste.sicabis.ejb.service.silodisa.service.CatalogoInsumosService;
import com.issste.sicabis.utils.Mensajes;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

public class RemisionesElectronicasHistoricoBean {

    @EJB
    private CatalogoInsumosService catalogoInsumosService;

    @EJB
    private RemisionesElectronicasEntregasUmuHistoricoService remisionesElectronicasEntregasUmuHistoricoService;

    private Date fechaInicio;
    private Date fechaFin;
    private List<RemisionesElectronicasEntregasUmuHistorico> exitenciaList;
    private String delegacion;
    private String clave;
    private String dpnMes;
    private String folioPedido;
    private String umu;
    private String tipoInsumo;
    private String dpnYear;
    private String nombreUmu;
    private String tipoPedido;
    private String remision;
    private String mesDPN;
    private String anoDPN;
    private List<RemisionesElectronicasEntregasUmuHistorico> exitenciaConsultaList;
    private List<CatInsumos> existeciaInsumosList;

    private Mensajes mensajes = new Mensajes();

    @PostConstruct
    public void init() {
        delegacion = "-1";
        clave = "-1";
        folioPedido = "";
        mesDPN = "";
        tipoInsumo = "";
        anoDPN = "";
        tipoPedido = "";
        exitenciaList = remisionesElectronicasEntregasUmuHistoricoService.getAll();
        existeciaInsumosList = catalogoInsumosService.getAllCatalogoInsumos();
        fechaInicio = new Date();
        fechaFin = new Date();
        remision = "";
        exitenciaList = remisionesElectronicasEntregasUmuHistoricoService.getByFiltros(fechaInicio, fechaFin, delegacion, clave, folioPedido, mesDPN, umu, tipoInsumo, remision, anoDPN, nombreUmu, tipoPedido);
    }

    public boolean valida() {
        boolean bandera = true;
        if (fechaInicio == null) {
            bandera = false;
            mensajes.mensaje("Debes ingresar la fecha inicial", "amarillo");
        }
        if (fechaFin == null) {
            bandera = false;
            mensajes.mensaje("Debes ingresar la fecha final", "amarillo");
        }
        if (fechaInicio != null && fechaFin != null) {
            if (fechaFin.compareTo(fechaInicio) < 0) {
                bandera = false;
                mensajes.mensaje("La fecha final es menor que la fecha inicial", "amarillo");
            }
        }
        return bandera;
    }

    public void buscarRemisionElectronicaByFiltros() {
        if (valida()) {
            exitenciaList = remisionesElectronicasEntregasUmuHistoricoService.getByFiltros(fechaInicio, fechaFin, delegacion, clave, folioPedido, mesDPN, umu, tipoInsumo, remision, anoDPN, nombreUmu, tipoPedido);
            if (exitenciaList == null) {
                mensajes.mensaje("No hay datos registrados", "amarillo");
            }
        }
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<RemisionesElectronicasEntregasUmuHistorico> getExitenciaList() {
        return exitenciaList;
    }

    public void setExitenciaList(List<RemisionesElectronicasEntregasUmuHistorico> exitenciaList) {
        this.exitenciaList = exitenciaList;
    }

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDpnMes() {
        return dpnMes;
    }

    public void setDpnMes(String dpnMes) {
        this.dpnMes = dpnMes;
    }

    public String getUmu() {
        return umu;
    }

    public void setUmu(String umu) {
        this.umu = umu;
    }

    public String getTipoInsumo() {
        return tipoInsumo;
    }

    public void setTipoInsumo(String tipoInsumo) {
        this.tipoInsumo = tipoInsumo;
    }

    public String getDpnYear() {
        return dpnYear;
    }

    public void setDpnYear(String dpnYear) {
        this.dpnYear = dpnYear;
    }

    public String getNombreUmu() {
        return nombreUmu;
    }

    public void setNombreUmu(String nombreUmu) {
        this.nombreUmu = nombreUmu;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public List<RemisionesElectronicasEntregasUmuHistorico> getExitenciaConsultaList() {
        return exitenciaConsultaList;
    }

    public void setExitenciaConsultaList(List<RemisionesElectronicasEntregasUmuHistorico> exitenciaConsultaList) {
        this.exitenciaConsultaList = exitenciaConsultaList;
    }

    public String getMesDPN() {
        return mesDPN;
    }

    public void setMesDPN(String mesDPN) {
        this.mesDPN = mesDPN;
    }

    public String getAnoDPN() {
        return anoDPN;
    }

    public void setAnoDPN(String anoDPN) {
        this.anoDPN = anoDPN;
    }

    public String getRemision() {
        return remision;
    }

    public void setRemision(String remision) {
        this.remision = remision;
    }

    public String getFolioPedido() {
        return folioPedido;
    }

    public void setFolioPedido(String folioPedido) {
        this.folioPedido = folioPedido;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

    public List<CatInsumos> getExisteciaInsumosList() {
        return existeciaInsumosList;
    }

    public void setExisteciaInsumosList(List<CatInsumos> existeciaInsumosList) {
        this.existeciaInsumosList = existeciaInsumosList;
    }

}
