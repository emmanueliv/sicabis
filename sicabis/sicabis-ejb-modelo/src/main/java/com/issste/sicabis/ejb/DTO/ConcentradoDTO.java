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
public class ConcentradoDTO {
    private String contrato;
    private String convenio;
    private String numeroOrden;
    private String proveedor;
    private String procediminento;
    private String insumo;
    private String descripcion;
    private BigDecimal precioUnitario;
    private Integer cantidadAdjudicada;
    private Date fechaInicial;
    private Date fechaFinal;
    private Date fechaIngreso;
    private Date fechaGenerado;
    private Integer cantidadIngresada;
    private Integer cantidadProgramada;
    private Integer cantidadCancelada;
    private Integer cantidadPendiente;
    private Integer numeroProveedor;

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getProcediminento() {
        return procediminento;
    }

    public void setProcediminento(String procediminento) {
        this.procediminento = procediminento;
    }

    public String getInsumo() {
        return insumo;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getCantidadAdjudicada() {
        return cantidadAdjudicada;
    }

    public void setCantidadAdjudicada(Integer cantidadAdjudicada) {
        this.cantidadAdjudicada = cantidadAdjudicada;
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getCantidadIngresada() {
        return cantidadIngresada;
    }

    public void setCantidadIngresada(Integer cantidadIngresada) {
        this.cantidadIngresada = cantidadIngresada;
    }

    public Integer getCantidadCancelada() {
        return cantidadCancelada;
    }

    public void setCantidadCancelada(Integer cantidadCancelada) {
        this.cantidadCancelada = cantidadCancelada;
    }

    public Integer getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(Integer cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }

    public Date getFechaGenerado() {
        return fechaGenerado;
    }

    public void setFechaGenerado(Date fechaGenerado) {
        this.fechaGenerado = fechaGenerado;
    }

    public Integer getNumeroProveedor() {
        return numeroProveedor;
    }

    public void setNumeroProveedor(Integer numeroProveedor) {
        this.numeroProveedor = numeroProveedor;
    }

    public Integer getCantidadProgramada() {
        return cantidadProgramada;
    }

    public void setCantidadProgramada(Integer cantidadProgramada) {
        this.cantidadProgramada = cantidadProgramada;
    }
    
}
