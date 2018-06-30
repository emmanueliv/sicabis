/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author 9RZCBG2
 */
public class ReporteClavesDTO {

    private Integer numero;
    private String clave;
    private String descripcion;
    private String tipoClave;
    private BigDecimal diasInventario;
    private BigDecimal DPN;
    private BigDecimal ingresoPendiente;
    private BigDecimal ingresoDia;
    private BigDecimal diasInventarioConsiderado;
    private Integer ingresi;
    private Integer disponibleCenadi;
    private BigDecimal coberturaDisponibleEntregaCenadi;
    private String proveedor;
    private Date fechaFinal;
    private Date fechaInicial;

    public ReporteClavesDTO() {
    }

    // Resumen al corte Claves
    private Integer CantidadClaves;
    private Integer CantidadClavesCero;
    private String estatus;
    private BigDecimal porcentajeSNClavesPro;
    private BigDecimal porcentajeClavesPro;
    private Integer CEPMed;
    private Integer CSEPMed;
    private Integer CEPMatCuracion;
    private Integer CSEPMatCuracion;
    private Integer totales;
    private Integer CEPMedCero;
    private Integer CSEPMedCero;
    private Integer CEPMatCuracionCero;
    private Integer CSEPMatCuracionCero;
    private Integer totalesCero;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoClave() {
        return tipoClave;
    }

    public void setTipoClave(String tipoClave) {
        this.tipoClave = tipoClave;
    }

    public BigDecimal getDiasInventario() {
        return diasInventario;
    }

    public void setDiasInventario(BigDecimal diasInventario) {
        this.diasInventario = diasInventario;
    }

    public BigDecimal getDPN() {
        return DPN;
    }

    public void setDPN(BigDecimal DPN) {
        this.DPN = DPN;
    }

    public BigDecimal getIngresoPendiente() {
        return ingresoPendiente;
    }

    public void setIngresoPendiente(BigDecimal ingresoPendiente) {
        this.ingresoPendiente = ingresoPendiente;
    }

    public BigDecimal getIngresoDia() {
        return ingresoDia;
    }

    public void setIngresoDia(BigDecimal ingresoDia) {
        this.ingresoDia = ingresoDia;
    }

    public BigDecimal getDiasInventarioConsiderado() {
        return diasInventarioConsiderado;
    }

    public void setDiasInventarioConsiderado(BigDecimal diasInventarioConsiderado) {
        this.diasInventarioConsiderado = diasInventarioConsiderado;
    }

    public Integer getIngresi() {
        return ingresi;
    }

    public void setIngresi(Integer ingresi) {
        this.ingresi = ingresi;
    }

    public Integer getDisponibleCenadi() {
        return disponibleCenadi;
    }

    public void setDisponibleCenadi(Integer disponibleCenadi) {
        this.disponibleCenadi = disponibleCenadi;
    }

    public BigDecimal getCoberturaDisponibleEntregaCenadi() {
        return coberturaDisponibleEntregaCenadi;
    }

    public void setCoberturaDisponibleEntregaCenadi(BigDecimal coberturaDisponibleEntregaCenadi) {
        this.coberturaDisponibleEntregaCenadi = coberturaDisponibleEntregaCenadi;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public BigDecimal getPorcentajeSNClavesPro() {
        return porcentajeSNClavesPro;
    }

    public void setPorcentajeSNClavesPro(BigDecimal porcentajeSNClavesPro) {
        this.porcentajeSNClavesPro = porcentajeSNClavesPro;
    }

    public BigDecimal getPorcentajeClavesPro() {
        return porcentajeClavesPro;
    }

    public void setPorcentajeClavesPro(BigDecimal porcentajeClavesPro) {
        this.porcentajeClavesPro = porcentajeClavesPro;
    }

    public Integer getCEPMed() {
        return CEPMed;
    }

    public void setCEPMed(Integer CEPMed) {
        this.CEPMed = CEPMed;
    }

    public Integer getCSEPMed() {
        return CSEPMed;
    }

    public void setCSEPMed(Integer CSEPMed) {
        this.CSEPMed = CSEPMed;
    }

    public Integer getCEPMatCuracion() {
        return CEPMatCuracion;
    }

    public void setCEPMatCuracion(Integer CEPMatCuracion) {
        this.CEPMatCuracion = CEPMatCuracion;
    }

    public Integer getCSEPMatCuracion() {
        return CSEPMatCuracion;
    }

    public void setCSEPMatCuracion(Integer CSEPMatCuracion) {
        this.CSEPMatCuracion = CSEPMatCuracion;
    }

    public Integer getTotales() {
        return totales;
    }

    public void setTotales(Integer totales) {
        this.totales = totales;
    }

    public Integer getCEPMedCero() {
        return CEPMedCero;
    }

    public void setCEPMedCero(Integer CEPMedCero) {
        this.CEPMedCero = CEPMedCero;
    }

    public Integer getCSEPMedCero() {
        return CSEPMedCero;
    }

    public void setCSEPMedCero(Integer CSEPMedCero) {
        this.CSEPMedCero = CSEPMedCero;
    }

    public Integer getCEPMatCuracionCero() {
        return CEPMatCuracionCero;
    }

    public void setCEPMatCuracionCero(Integer CEPMatCuracionCero) {
        this.CEPMatCuracionCero = CEPMatCuracionCero;
    }

    public Integer getCSEPMatCuracionCero() {
        return CSEPMatCuracionCero;
    }

    public void setCSEPMatCuracionCero(Integer CSEPMatCuracionCero) {
        this.CSEPMatCuracionCero = CSEPMatCuracionCero;
    }

    public Integer getTotalesCero() {
        return totalesCero;
    }

    public void setTotalesCero(Integer totalesCero) {
        this.totalesCero = totalesCero;
    }

    public Integer getCantidadClaves() {
        return CantidadClaves;
    }

    public void setCantidadClaves(Integer CantidadClaves) {
        this.CantidadClaves = CantidadClaves;
    }

    public Integer getCantidadClavesCero() {
        return CantidadClavesCero;
    }

    public void setCantidadClavesCero(Integer CantidadClavesCero) {
        this.CantidadClavesCero = CantidadClavesCero;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

}
