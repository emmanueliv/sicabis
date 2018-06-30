/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.DTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Toshiba Manolo
 */
public class CrRDto {
    
   private String partida;
   private String subPartida;
   private String denominacion;
   private BigDecimal montoAcontratar;
   private int periodoMeses;
   private String unidadResponsable;
   private String descripcionBreve;
   private String justificacion;
   private Integer fecha;

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getSubPartida() {
        return subPartida;
    }

    public void setSubPartida(String subPartida) {
        this.subPartida = subPartida;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public BigDecimal getMontoAcontratar() {
        return montoAcontratar;
    }

    public void setMontoAcontratar(BigDecimal montoAcontratar) {
        this.montoAcontratar = montoAcontratar;
    }

    public int getPeriodoMeses() {
        return periodoMeses;
    }

    public void setPeriodoMeses(int periodoMeses) {
        this.periodoMeses = periodoMeses;
    }

    public String getUnidadResponsable() {
        return unidadResponsable;
    }

    public void setUnidadResponsable(String unidadResponsable) {
        this.unidadResponsable = unidadResponsable;
    }

    public String getDescripcionBreve() {
        return descripcionBreve;
    }

    public void setDescripcionBreve(String descripcionBreve) {
        this.descripcionBreve = descripcionBreve;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Integer getFecha() {
        return fecha;
    }

    public void setFecha(Integer fecha) {
        this.fecha = fecha;
    }
    
}
