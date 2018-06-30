/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DTO;

import com.issste.sicabis.ejb.modelo.Rcb;

/**
 *
 * @author erik
 */
public class RcbConsultaViewDto {
    private Rcb rcb;
    private Integer numeroClaves;

    public Rcb getRcb() {
        return rcb;
    }

    public void setRcb(Rcb rcb) {
        this.rcb = rcb;
    }

    public Integer getNumeroClaves() {
        return numeroClaves;
    }

    public void setNumeroClaves(Integer numeroClaves) {
        this.numeroClaves = numeroClaves;
    }
    
    
    
}
