/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DTO;

import com.issste.sicabis.ejb.modelo.Insumos;

/**
 *
 * @author Toshiba Manolo
 */
public class InsumosViewDto {
    
    private Insumos insumo;
    private Integer idSolicitud;
    private Integer cantidadInsumo;

    public Insumos getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumos insumo) {
        this.insumo = insumo;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Integer getCantidadInsumo() {
        return cantidadInsumo;
    }

    public void setCantidadInsumo(Integer cantidadInsumo) {
        this.cantidadInsumo = cantidadInsumo;
    }
    
    
    
    
    
}
