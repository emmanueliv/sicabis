/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DTO;

import java.math.BigDecimal;
import java.util.Date;


public class EntradasCenadiDTO {

    private String nombreProveedor;
    private Date fechaRemision;
    private Integer idRemision;
    private String registroControl;
    private String numeroOrden;
    private String claveInsumo;
    private Integer cantidadPiezas;
    private BigDecimal precioUnitario;
    private BigDecimal importe;
    private String numeroContrato;
    private String noFolioRemision;
    //Parametros de busqueda
    private Date fechaInicial;
    private Date fechaFinal;
    private String tipoClave;
    private Integer numero;
    private String estatus;
    private Integer dpn;
    private Integer existecias;
    private BigDecimal diasInventario;
    private String descripcion;
    private BigDecimal totalPiezas;

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        if (nombreProveedor.equals("") || nombreProveedor == null) {
            nombreProveedor = "";
        }
        this.nombreProveedor = nombreProveedor;
    }

    public Date getFechaRemision() {
        return fechaRemision;
    }

    public void setFechaRemision(Date fechaRemision) {
        this.fechaRemision = fechaRemision;
    }

    public Integer getIdRemision() {
        return idRemision;
    }

    public void setIdRemision(Integer idRemision) {
        this.idRemision = idRemision;
    }

    public String getRegistroControl() {
        return registroControl;
    }

    public void setRegistroControl(String registroControl) {
        this.registroControl = registroControl;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        if (numeroOrden.equals("") || numeroOrden == null) {
            numeroOrden = "";
        }
        this.numeroOrden = numeroOrden;
    }

    public String getClaveInsumo() {
        return claveInsumo;
    }

    public void setClaveInsumo(String claveInsumo) {
        if (claveInsumo.equals("") || claveInsumo == null) {
            claveInsumo = "";
        }
        this.claveInsumo = claveInsumo;
    }

    public Integer getCantidadPiezas() {
        return cantidadPiezas;
    }

    public void setCantidadPiezas(Integer cantidadPiezas) {
        if (cantidadPiezas == 0 || cantidadPiezas == null) {
            cantidadPiezas = 0;
        }
        this.cantidadPiezas = cantidadPiezas;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        if (precioUnitario == new BigDecimal(0) || precioUnitario == null) {
            precioUnitario = new BigDecimal(0);
        }
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        if (importe == new BigDecimal(0) || importe == null) {
            importe = new BigDecimal(0);
        }
        this.importe = importe;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        if (numeroContrato.equals("") || numeroContrato == null) {
            numeroContrato = "";
        }
        this.numeroContrato = numeroContrato;
    }

    public String getNoFolioRemision() {
        return noFolioRemision;
    }

    public void setNoFolioRemision(String noFolioRemision) {
        this.noFolioRemision = noFolioRemision;
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

    public String getTipoClave() {
        return tipoClave;
    }

    public void setTipoClave(String tipoClave) {
        this.tipoClave = tipoClave;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getDpn() {
        return dpn;
    }

    public void setDpn(Integer dpn) {
        this.dpn = dpn;
    }

    public Integer getExistecias() {
        return existecias;
    }

    public void setExistecias(Integer existecias) {
        this.existecias = existecias;
    }

    public BigDecimal getDiasInventario() {
        return diasInventario;
    }

    public void setDiasInventario(BigDecimal diasInventario) {
        this.diasInventario = diasInventario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getTotalPiezas() {
        return totalPiezas;
    }

    public void setTotalPiezas(BigDecimal totalPiezas) {
        this.totalPiezas = totalPiezas;
    }

    
}
