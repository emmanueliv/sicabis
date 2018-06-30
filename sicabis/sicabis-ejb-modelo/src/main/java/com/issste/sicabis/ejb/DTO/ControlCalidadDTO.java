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
public class ControlCalidadDTO {

    private String registroControl;
    private String claveInsumo;
    private Integer cantidadRecibidaControlCalidad;
    private String inspeccion;
    private BigDecimal nivelCalidadAceptable;
    private String lote;
    private String descripcion;
    private String codigoBarrasLote;
    private Date fechaFabricacion;
    private Date fechaCaducidad;
    private Date fechaRemision;
    private Date fechaCalidad;
    private Integer renglon;
    private String contrato;
    private String procedimiento;
    private String numeroOrden;
    private Integer cantidadLote;
    private String estatus;
    private BigDecimal cantidad;
    private String unidadPieza;
    private BigDecimal precioUnitario;
    private BigDecimal importe;
    private String fabricante;
    private String numeroRemision;
    private String proveedor;

    public String getRegistroControl() {
        return registroControl;
    }

    public void setRegistroControl(String registroControl) {
        this.registroControl = registroControl;
    }

    public String getClaveInsumo() {
        return claveInsumo;
    }

    public void setClaveInsumo(String claveInsumo) {
        this.claveInsumo = claveInsumo;
    }

    public Integer getCantidadRecibidaControlCalidad() {
        return cantidadRecibidaControlCalidad;
    }

    public void setCantidadRecibidaControlCalidad(Integer cantidadRecibidaControlCalidad) {
        this.cantidadRecibidaControlCalidad = cantidadRecibidaControlCalidad;
    }

    public String getInspeccion() {
        return inspeccion;
    }

    public void setInspeccion(String inspeccion) {
        this.inspeccion = inspeccion;
    }

    public BigDecimal getNivelCalidadAceptable() {
        return nivelCalidadAceptable;
    }

    public void setNivelCalidadAceptable(BigDecimal nivelCalidadAceptable) {
        this.nivelCalidadAceptable = nivelCalidadAceptable;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getCodigoBarrasLote() {
        return codigoBarrasLote;
    }

    public void setCodigoBarrasLote(String codigoBarrasLote) {
        this.codigoBarrasLote = codigoBarrasLote;
    }

    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(Date fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Integer getCantidadLote() {
        return cantidadLote;
    }

    public void setCantidadLote(Integer cantidadLote) {
        this.cantidadLote = cantidadLote;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }
//
    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadPieza() {
        return unidadPieza;
    }

    public void setUnidadPieza(String unidadPieza) {
        this.unidadPieza = unidadPieza;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

   //
    public Date getFechaRemision() {
        return fechaRemision;
    }

    public void setFechaRemision(Date fechaRemision) {
        this.fechaRemision = fechaRemision;
    }

    public Date getFechaCalidad() {
        return fechaCalidad;
    }

    public void setFechaCalidad(Date fechaCalidad) {
        this.fechaCalidad = fechaCalidad;
    }

    public Integer getRenglon() {
        return renglon;
    }

    public void setRenglon(Integer renglon) {
        this.renglon = renglon;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getNumeroRemision() {
        return numeroRemision;
    }

    public void setNumeroRemision(String numeroRemision) {
        this.numeroRemision = numeroRemision;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    
}
