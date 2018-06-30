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
public class PiezasPendientesAnualDTO {

    private String contrato;
    private String convenio;
    private String ordenSuministro;
    private String proveedor;
    private String clave;
    private Date periodoEntregaFinal;
    private Date periodoEntregaInicial;
    private BigDecimal pendiente;
    private BigDecimal totalPiezas;

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

    public String getOrdenSuministro() {
        return ordenSuministro;
    }

    public void setOrdenSuministro(String ordenSuministro) {
        this.ordenSuministro = ordenSuministro;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public BigDecimal getPendiente() {
        return pendiente;
    }

    public void setPendiente(BigDecimal pendiente) {
        this.pendiente = pendiente;
    }

    public BigDecimal getTotalPiezas() {
        return totalPiezas;
    }

    public void setTotalPiezas(BigDecimal totalPiezas) {
        this.totalPiezas = totalPiezas;
    }

}
