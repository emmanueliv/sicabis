/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.DTO;

import com.issste.sicabis.ejb.modelo.Grupo;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import com.issste.sicabis.ejb.modelo.UnidadResponsable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kriosoft
 */
public class RcbDTO {
    
    private Integer idRcb;
    private int activo;
    private String numero;
    private String oficioSuficienciaPresupuestal;
    private String nep;
    private BigDecimal importeTotal;
    private String nombreUnidad;
    private String unidadResponsable;
    private String grupo;
    private String clave;
    private Date fechaElaboracion;
    private String destino;
    private int periodo;
    private String claveInsumo;
    private String descripcion;
    private Integer existencias;
    private Integer consumoPromedio;
    private Integer mesesDeCobertura;
    private String presentacionInsumo;
    private Integer cantidadSolicitada;
    private BigDecimal precioUnitario;
    private BigDecimal importe;

    public Integer getIdRcb() {
        return idRcb;
    }

    public void setIdRcb(Integer idRcb) {
        this.idRcb = idRcb;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getOficioSuficienciaPresupuestal() {
        return oficioSuficienciaPresupuestal;
    }

    public void setOficioSuficienciaPresupuestal(String oficioSuficienciaPresupuestal) {
        this.oficioSuficienciaPresupuestal = oficioSuficienciaPresupuestal;
    }

    public String getNep() {
        return nep;
    }

    public void setNep(String nep) {
        this.nep = nep;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public String getUnidadResponsable() {
        return unidadResponsable;
    }

    public void setUnidadResponsable(String unidadResponsable) {
        this.unidadResponsable = unidadResponsable;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getClaveInsumo() {
        return claveInsumo;
    }

    public void setClaveInsumo(String claveInsumo) {
        this.claveInsumo = claveInsumo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getExistencias() {
        return existencias;
    }

    public void setExistencias(Integer existencias) {
        this.existencias = existencias;
    }

    public Integer getConsumoPromedio() {
        return consumoPromedio;
    }

    public void setConsumoPromedio(Integer consumoPromedio) {
        this.consumoPromedio = consumoPromedio;
    }

    public Integer getMesesDeCobertura() {
        return mesesDeCobertura;
    }

    public void setMesesDeCobertura(Integer mesesDeCobertura) {
        this.mesesDeCobertura = mesesDeCobertura;
    }

    public String getPresentacionInsumo() {
        return presentacionInsumo;
    }

    public void setPresentacionInsumo(String presentacionInsumo) {
        this.presentacionInsumo = presentacionInsumo;
    }

    public Integer getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(Integer cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
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


    



    
    
    
}
