/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.utils;

import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.modelo.Insumos;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Toshiba Manolo
 */
@ManagedBean(name="insumosAutoCompleteService", eager = true)
@ApplicationScoped
public class InsumosAutoCompleteService {
    @EJB
    private InsumosService insumoService;
    private List<Insumos> listInsumos;

    @PostConstruct
    public void init() {        
        
    }
    public List<Insumos> getListInsumos() {
        return listInsumos;
    }

    public void setListInsumos(List<Insumos> listInsumos) {
        this.listInsumos = listInsumos;
    }
    
    
    
}
