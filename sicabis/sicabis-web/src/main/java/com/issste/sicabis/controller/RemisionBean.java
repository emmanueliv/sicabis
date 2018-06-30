package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.RemisionesDTO;
import com.issste.sicabis.ejb.ln.RemisionesService;
import com.issste.sicabis.ejb.modelo.Remisiones;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

/**
 *
 * @author fabianvr
 */
public class RemisionBean {

    @EJB
    private RemisionesService remisionesService;

    private List<Remisiones> remisionesList;

    public RemisionBean() {
        System.out.println("aqui ando");
        remisionesList = null;

    }

    @PostConstruct
    public void init() {
        this.remisiones();
    }

    public List<Remisiones> remisiones() {
        System.out.println("entro a remisiones");

        remisionesList = remisionesService.remisiones();
        

        return remisionesList;

    }

    public List<Remisiones> getRemisionesList() {
        return remisionesList;
    }

    public void setRemisionesList(List<Remisiones> remisionesList) {
        this.remisionesList = remisionesList;
    }

    public RemisionesService getRemisionesService() {
        return remisionesService;
    }

    public void setRemisionesService(RemisionesService remisionesService) {
        this.remisionesService = remisionesService;
    }

}
