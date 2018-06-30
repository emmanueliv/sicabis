/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author CF19BG2
 */
public class AlertasDTO {

    private String estado;
    private Integer umu;
    private Integer claves;
    private Integer piezas;
    private Integer ordinarios;
    private Integer extraodinarios;
    private BigDecimal porcentajeOrdinario;
    private BigDecimal porcentajeExtraordinario;
    private BigDecimal disponible;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getUmu() {
        return umu;
    }

    public void setUmu(Integer umu) {
        this.umu = umu;
    }

    public Integer getClaves() {
        return claves;
    }

    public void setClaves(Integer claves) {
        this.claves = claves;
    }

    public Integer getPiezas() {
        return piezas;
    }

    public void setPiezas(Integer piezas) {
        this.piezas = piezas;
    }

    public Integer getOrdinarios() {
        return ordinarios;
    }

    public void setOrdinarios(Integer ordinarios) {
        this.ordinarios = ordinarios;
    }

    public Integer getExtraodinarios() {
        return extraodinarios;
    }

    public void setExtraodinarios(Integer extraodinarios) {
        this.extraodinarios = extraodinarios;
    }

    public BigDecimal getPorcentajeOrdinario() {
        return porcentajeOrdinario;
    }

    public void setPorcentajeOrdinario(BigDecimal porcentajeOrdinario) {
        this.porcentajeOrdinario = porcentajeOrdinario;
    }

    public BigDecimal getPorcentajeExtraordinario() {
        return porcentajeExtraordinario;
    }

    public void setPorcentajeExtraordinario(BigDecimal porcentajeExtraordinario) {
        this.porcentajeExtraordinario = porcentajeExtraordinario;
    }

    public BigDecimal getDisponible() {
        return disponible;
    }

    public void setDisponible(BigDecimal disponible) {
        this.disponible = disponible.setScale(2, RoundingMode.HALF_UP);
    }

}
