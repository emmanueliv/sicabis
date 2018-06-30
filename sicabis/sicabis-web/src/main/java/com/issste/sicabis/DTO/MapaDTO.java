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
 * @author 6JWBBG2
 */
public class MapaDTO {
    
    private BigDecimal porcentaje;
    private String nombreE;
    private String colorE;
    private String claveE;
    private Date fechaActualizacion;
    
    public String getNombreE() {
        return nombreE;
    }

  
    public MapaDTO() {
    }

    
    
    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getColorE() {
        return colorE;
    }

    public void setColorE(String colorE) {
        this.colorE = colorE;
    }

    public String getClaveE() {
        return claveE;
    }

    public void setClaveE(String claveE) {
        this.claveE = claveE;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    
    
    
}
