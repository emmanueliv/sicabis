/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DTO;

import com.issste.sicabis.ejb.modelo.RcbInsumos;

/**
 *
 * @author erik
 */
public class RcbInsumosViewDto {
    
    private RcbInsumos rcbInsumo;
    private Boolean rcbInsumoSeleccionado;
    private String descripcionCorta;

    public RcbInsumos getRcbInsumo() {
        return rcbInsumo;
    }

    public void setRcbInsumo(RcbInsumos rcbInsumo) {
        this.rcbInsumo = rcbInsumo;
    }

    public Boolean getRcbInsumoSeleccionado() {
        return rcbInsumoSeleccionado;
    }

    public void setRcbInsumoSeleccionado(Boolean rcbInsumoSeleccionado) {
        this.rcbInsumoSeleccionado = rcbInsumoSeleccionado;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }
    
    
    
}
