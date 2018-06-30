/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.DTO;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author kriosoft
 */
public class CrDTO {

    private Integer idCr;
    private Integer activo;
    private String numeroCr;
    private BigDecimal importeTotal;
    private int ejercicio;
    private List<InsumosDTO> listaInsumos;
    private String nombreUnidadResp;

    public Integer getIdCr() {
        return idCr;
    }

    public void setIdCr(Integer idCr) {
        this.idCr = idCr;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getNumeroCr() {
        return numeroCr;
    }

    public void setNumeroCr(String numeroCr) {
        this.numeroCr = numeroCr;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public int getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(int ejercicio) {
        this.ejercicio = ejercicio;
    }

    public List<InsumosDTO> getListaInsumos() {
        return listaInsumos;
    }

    public void setListaInsumos(List<InsumosDTO> listaInsumos) {
        this.listaInsumos = listaInsumos;
    }

    public String getNombreUnidadResp() {
        return nombreUnidadResp;
    }

    public void setNombreUnidadResp(String nombreUnidadResp) {
        this.nombreUnidadResp = nombreUnidadResp;
    }

}
