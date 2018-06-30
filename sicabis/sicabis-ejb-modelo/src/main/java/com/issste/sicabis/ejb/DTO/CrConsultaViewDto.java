/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DTO;

import com.issste.sicabis.ejb.modelo.Cr;

/**
 *
 * @author Toshiba Manolo
 */
public class CrConsultaViewDto {
    
    private Cr cr;
    private Integer numeroClaves;

    public Cr getCr() {
        return cr;
    }

    public void setCr(Cr cr) {
        this.cr = cr;
    }

    public Integer getNumeroClaves() {
        return numeroClaves;
    }

    public void setNumeroClaves(Integer numeroClaves) {
        this.numeroClaves = numeroClaves;
    }
    
    
    
}
