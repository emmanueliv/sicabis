/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DTO;

import com.issste.sicabis.ejb.modelo.CrInsumos;
import java.math.BigDecimal;

/**
 *
 * @author Toshiba Manolo
 */
public class CrInsumosViewDto {
    
    private CrInsumos crInsumo;
    private BigDecimal importeIva;
    private BigDecimal importeSinIva;
    private Boolean crInsumoSeleccionado;

    public CrInsumos getCrInsumo() {
        return crInsumo;
    }

    public void setCrInsumo(CrInsumos crInsumo) {
        this.crInsumo = crInsumo;
    }

    public Boolean getCrInsumoSeleccionado() {
        return crInsumoSeleccionado;
    }

    public void setCrInsumoSeleccionado(Boolean crInsumoSeleccionado) {
        this.crInsumoSeleccionado = crInsumoSeleccionado;
    }

    public BigDecimal getImporteIva() {
        return importeIva;
    }

    public void setImporteIva(BigDecimal importeIva) {
        this.importeIva = importeIva;
    }

    public BigDecimal getImporteSinIva() {
        return importeSinIva;
    }

    public void setImporteSinIva(BigDecimal importeSinIva) {
        this.importeSinIva = importeSinIva;
    }
    
            
    
    
}
