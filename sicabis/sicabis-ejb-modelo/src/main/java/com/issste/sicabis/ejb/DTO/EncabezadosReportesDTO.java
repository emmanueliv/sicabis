/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DTO;

import java.util.Date;
import java.util.List;

public class EncabezadosReportesDTO {

    private Date fechaInicial;
    private Date fechaFinal;
    private Date fechaElaboracion;
    private Integer anio;
    private List<EntradasCenadiDTO> listInfoECD;
    private List<EntradasDevolucionesCenadiDTO> listInfoEDCenadi;
    private List<PiezasPendientesAnualDTO> listInfoPPAD;
    private List<EntradasDevolucionesCenadiEstatusDTO> listInfoEDCenadixEstatus;
    private List<ReporteClavesDTO> listInfoClaves;
    private List<ReporteClavesDTO> lisInfoClavesSinEntrega;
    private List<ReporteClavesDTO> lisInfoClavesConEntrega;
    private List<ControlCalidadDTO> listInfoControlCalidad;
    private List<ConcentradoDTO> listConcentrado;
    private List<ConcentradoPendienteDTO> listConcentradoPendienteDTO;
    private String rutaLogo;
    //Reporte Claves en Proceso
    private Integer proveedores;
    private Integer remisiones;
    private Integer aprobadas;
    private Integer devoluciones;
    private Integer controlCalidad;
    private Integer piezas;
    private Integer claves;

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

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public List<EntradasCenadiDTO> getListInfoECD() {
        return listInfoECD;
    }

    public void setListInfoECD(List<EntradasCenadiDTO> listInfoECD) {
        this.listInfoECD = listInfoECD;
    }

    public List<PiezasPendientesAnualDTO> getListInfoPPAD() {
        return listInfoPPAD;
    }

    public void setListInfoPPAD(List<PiezasPendientesAnualDTO> listInfoPPAD) {
        this.listInfoPPAD = listInfoPPAD;
    }

    public List<EntradasDevolucionesCenadiDTO> getListInfoEDCenadi() {
        return listInfoEDCenadi;
    }

    public void setListInfoEDCenadi(List<EntradasDevolucionesCenadiDTO> listInfoEDCenadi) {
        this.listInfoEDCenadi = listInfoEDCenadi;
    }

    public List<EntradasDevolucionesCenadiEstatusDTO> getListInfoEDCenadixEstatus() {
        return listInfoEDCenadixEstatus;
    }

    public void setListInfoEDCenadixEstatus(List<EntradasDevolucionesCenadiEstatusDTO> listInfoEDCenadixEstatus) {
        this.listInfoEDCenadixEstatus = listInfoEDCenadixEstatus;
    }

    public List<ReporteClavesDTO> getListInfoClaves() {
        return listInfoClaves;
    }

    public void setListInfoClaves(List<ReporteClavesDTO> listInfoClaves) {
        this.listInfoClaves = listInfoClaves;
    }

    public List<ControlCalidadDTO> getListInfoControlCalidad() {
        return listInfoControlCalidad;
    }

    public void setListInfoControlCalidad(List<ControlCalidadDTO> listInfoControlCalidad) {
        this.listInfoControlCalidad = listInfoControlCalidad;
    }

    public String getRutaLogo() {
        return rutaLogo;
    }

    public void setRutaLogo(String rutaLogo) {
        this.rutaLogo = rutaLogo;
    }

    public List<ReporteClavesDTO> getLisInfoClavesSinEntrega() {
        return lisInfoClavesSinEntrega;
    }

    public void setLisInfoClavesSinEntrega(List<ReporteClavesDTO> lisInfoClavesSinEntrega) {
        this.lisInfoClavesSinEntrega = lisInfoClavesSinEntrega;
    }

    public List<ReporteClavesDTO> getLisInfoClavesConEntrega() {
        return lisInfoClavesConEntrega;
    }

    public void setLisInfoClavesConEntrega(List<ReporteClavesDTO> lisInfoClavesConEntrega) {
        this.lisInfoClavesConEntrega = lisInfoClavesConEntrega;
    }

    public List<ConcentradoDTO> getListConcentrado() {
        return listConcentrado;
    }

    public void setListConcentrado(List<ConcentradoDTO> listConcentrado) {
        this.listConcentrado = listConcentrado;
    }

    public List<ConcentradoPendienteDTO> getListConcentradoPendienteDTO() {
        return listConcentradoPendienteDTO;
    }

    public void setListConcentradoPendienteDTO(List<ConcentradoPendienteDTO> listConcentradoPendienteDTO) {
        this.listConcentradoPendienteDTO = listConcentradoPendienteDTO;
    }

    public Integer getProveedores() {
        return proveedores;
    }

    public void setProveedores(Integer proveedores) {
        this.proveedores = proveedores;
    }

    public Integer getRemisiones() {
        return remisiones;
    }

    public void setRemisiones(Integer remisiones) {
        this.remisiones = remisiones;
    }

    public Integer getAprobadas() {
        return aprobadas;
    }

    public void setAprobadas(Integer aprobadas) {
        this.aprobadas = aprobadas;
    }

    public Integer getDevoluciones() {
        return devoluciones;
    }

    public void setDevoluciones(Integer devoluciones) {
        this.devoluciones = devoluciones;
    }

    public Integer getControlCalidad() {
        return controlCalidad;
    }

    public void setControlCalidad(Integer controlCalidad) {
        this.controlCalidad = controlCalidad;
    }

    public Integer getPiezas() {
        return piezas;
    }

    public void setPiezas(Integer piezas) {
        this.piezas = piezas;
    }

    public Integer getClaves() {
        return claves;
    }

    public void setClaves(Integer claves) {
        this.claves = claves;
    }

    
}
