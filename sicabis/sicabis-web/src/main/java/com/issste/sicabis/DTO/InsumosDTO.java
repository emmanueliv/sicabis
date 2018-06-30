/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.DTO;

import java.math.BigDecimal;

/**
 *
 * @author fabianvr
 */
public class InsumosDTO {

    private String clave;
    private BigDecimal precioUnitario;
    private Integer cantidadPiezas;
    private BigDecimal cantidadPiezasMin;
    private String unidadPieza;
    private BigDecimal importeClave;
    private BigDecimal importeClaveMin;
    private Integer idClave;
    private String descripcion;
    private String nep;
    private String oficio;
    private String rcb;

    public InsumosDTO() {

    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }


    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getCantidadPiezas() {
        return cantidadPiezas;
    }

    public void setCantidadPiezas(Integer cantidadPiezas) {
        this.cantidadPiezas = cantidadPiezas;
    }

    public String getUnidadPieza() {
        return unidadPieza;
    }

    public void setUnidadPieza(String unidadPieza) {
        this.unidadPieza = unidadPieza;
    }

    public BigDecimal getImporteClave() {
        return importeClave;
    }

    public void setImporteClave(BigDecimal importeClave) {
        this.importeClave = importeClave;
    }

    public Integer getIdClave() {
        return idClave;
    }

    public void setIdClave(Integer idClave) {
        this.idClave = idClave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNep() {
        return nep;
    }

    public void setNep(String nep) {
        this.nep = nep;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public String getRcb() {
        return rcb;
    }

    public void setRcb(String rcb) {
        this.rcb = rcb;
    }

    public BigDecimal getCantidadPiezasMin() {
        return cantidadPiezasMin;
    }

    public void setCantidadPiezasMin(BigDecimal cantidadPiezasMin) {
        this.cantidadPiezasMin = cantidadPiezasMin;
    }

    public BigDecimal getImporteClaveMin() {
        return importeClaveMin;
    }

    public void setImporteClaveMin(BigDecimal importeClaveMin) {
        this.importeClaveMin = importeClaveMin;
    }

    
}
