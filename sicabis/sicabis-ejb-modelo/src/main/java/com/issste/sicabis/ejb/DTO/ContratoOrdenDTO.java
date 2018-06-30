
package com.issste.sicabis.ejb.DTO;

import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import java.util.Date;


public class ContratoOrdenDTO {
    
    private ContratoFalloProcedimientoRcb contratoFalloProcedimientoRcb;
    private DetalleOrdenSuministro detalleOrdenSuministro;
    private Date fechaInicialContrato;
    private Date fechaFinalContrato;
    private String lugarEntrega;
    private Integer id;
    
    private String claveInsumo;
    private Integer total;
    private Integer suministrado;
    private Integer disponible;
    private String descripcionInsumo;
    private Integer cantidadSuministrar;
    private Integer completado;
    private boolean bopcion;
    private int numOrden;
    private Integer existencias;

    public ContratoFalloProcedimientoRcb getContratoFalloProcedimientoRcb() {
        return contratoFalloProcedimientoRcb;
    }

    public void setContratoFalloProcedimientoRcb(ContratoFalloProcedimientoRcb contratoFalloProcedimientoRcb) {
        this.contratoFalloProcedimientoRcb = contratoFalloProcedimientoRcb;
    }

    public DetalleOrdenSuministro getDetalleOrdenSuministro() {
        return detalleOrdenSuministro;
    }

    public void setDetalleOrdenSuministro(DetalleOrdenSuministro detalleOrdenSuministro) {
        this.detalleOrdenSuministro = detalleOrdenSuministro;
    }

    public Date getFechaInicialContrato() {
        return fechaInicialContrato;
    }

    public void setFechaInicialContrato(Date fechaInicialContrato) {
        this.fechaInicialContrato = fechaInicialContrato;
    }

    public Date getFechaFinalContrato() {
        return fechaFinalContrato;
    }

    public void setFechaFinalContrato(Date fechaFinalContrato) {
        this.fechaFinalContrato = fechaFinalContrato;
    }

    public String getLugarEntrega() {
        return lugarEntrega;
    }

    public void setLugarEntrega(String lugarEntrega) {
        this.lugarEntrega = lugarEntrega;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClaveInsumo() {
        return claveInsumo;
    }

    public void setClaveInsumo(String claveInsumo) {
        this.claveInsumo = claveInsumo;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSuministrado() {
        return suministrado;
    }

    public void setSuministrado(Integer disponible) {
        this.suministrado = disponible;
    }

    public Integer getDisponible() {
        return disponible;
    }

    public void setDisponible(Integer disponible) {
        this.disponible = disponible;
    }

    public String getDescripcionInsumo() {
        return descripcionInsumo;
    }

    public void setDescripcionInsumo(String descripcionInsumo) {
        this.descripcionInsumo = descripcionInsumo;
    }

    public Integer getCantidadSuministrar() {
        return cantidadSuministrar;
    }

    public void setCantidadSuministrar(Integer cantidadSuministrar) {
        this.cantidadSuministrar = cantidadSuministrar;
    }

    public Integer getCompletado() {
        return completado;
    }

    public void setCompletado(Integer completado) {
        this.completado = completado;
    }

    public boolean isBopcion() {
        return bopcion;
    }

    public void setBopcion(boolean bopcion) {
        this.bopcion = bopcion;
    }

    public int getNumOrden() {
        return numOrden;
    }

    public void setNumOrden(int numOrden) {
        this.numOrden = numOrden;
    }

    public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }
}
