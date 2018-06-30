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
public class EntradasDevolucionesCenadiDTO {

    private String registroControl;
    private Date fecha;
    private String proveedor;
    private String contrato;
    private String clave;
    private String descripcion;
    private Integer piezasRecibidas;
    private String estatus;
    private String procedimiento;
    private Integer cantidad;
    private Integer DPN;
    private BigDecimal coberturaDeEntrega;
    private Date periodoEntregaFinal;
    private Date periodoEntregaInicial;
    private BigDecimal totalPiezas;
    //totales

    private Integer totalProveedores;
    private Integer clavesIngresadas;
    private BigDecimal cantidadPiezas;
    private Integer remisionesGeneradas;
    private Integer devoluciones;
    private Integer remisionesAjustadas;

    public String getRegistroControl() {
        return registroControl;
    }

    public void setRegistroControl(String registroControl) {
        this.registroControl = registroControl;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

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

    public Integer getPiezasRecibidas() {
        return piezasRecibidas;
    }

    public void setPiezasRecibidas(Integer piezasRecibidas) {
        this.piezasRecibidas = piezasRecibidas;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getDPN() {
        return DPN;
    }

    public void setDPN(Integer DPN) {
        this.DPN = DPN;
    }

    public BigDecimal getCoberturaDeEntrega() {
        return coberturaDeEntrega;
    }

    public void setCoberturaDeEntrega(BigDecimal coberturaDeEntrega) {
        this.coberturaDeEntrega = coberturaDeEntrega;
    }

    public Date getPeriodoEntregaFinal() {
        return periodoEntregaFinal;
    }

    public void setPeriodoEntregaFinal(Date periodoEntregaFinal) {
        this.periodoEntregaFinal = periodoEntregaFinal;
    }

    public Date getPeriodoEntregaInicial() {
        return periodoEntregaInicial;
    }

    public void setPeriodoEntregaInicial(Date periodoEntregaInicial) {
        this.periodoEntregaInicial = periodoEntregaInicial;
    }

    public Integer getTotalProveedores() {
        return totalProveedores;
    }

    public void setTotalProveedores(Integer totalProveedores) {
        this.totalProveedores = totalProveedores;
    }

    public Integer getClavesIngresadas() {
        return clavesIngresadas;
    }

    public void setClavesIngresadas(Integer clavesIngresadas) {
        this.clavesIngresadas = clavesIngresadas;
    }

    public BigDecimal getCantidadPiezas() {
        return cantidadPiezas;
    }

    public void setCantidadPiezas(BigDecimal cantidadPiezas) {
        this.cantidadPiezas = cantidadPiezas;
    }

    public Integer getRemisionesGeneradas() {
        return remisionesGeneradas;
    }

    public void setRemisionesGeneradas(Integer remisionesGeneradas) {
        this.remisionesGeneradas = remisionesGeneradas;
    }

    public Integer getDevoluciones() {
        return devoluciones;
    }

    public void setDevoluciones(Integer devoluciones) {
        this.devoluciones = devoluciones;
    }

    public Integer getRemisionesAjustadas() {
        return remisionesAjustadas;
    }

    public void setRemisionesAjustadas(Integer remisionesAjustadas) {
        this.remisionesAjustadas = remisionesAjustadas;
    }

    public BigDecimal getTotalPiezas() {
        return totalPiezas;
    }

    public void setTotalPiezas(BigDecimal totalPiezas) {
        this.totalPiezas = totalPiezas;
    }

}
