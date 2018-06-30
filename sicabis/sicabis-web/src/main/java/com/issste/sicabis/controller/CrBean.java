/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.CrConsultaViewDto;
import com.issste.sicabis.ejb.ln.AreasService;
import com.issste.sicabis.ejb.ln.CrInsumosService;
import com.issste.sicabis.ejb.ln.CrService;
import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.Cr;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Toshiba Manolo
 */
@ManagedBean(name = "crBean")
@ViewScoped
public class CrBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Utilidades util = new Utilidades();
    private List<CrConsultaViewDto> listCrViewDto = new ArrayList<>();
    private String numCr;
    private Usuarios usuarios;

    @EJB
    private CrService crService;
    @EJB
    private CrInsumosService crInsumosService;
    @EJB
    private AreasService areasService;

    private int idArea;
    private List<Area> areasList;

    public CrBean() {
        usuarios = new Usuarios();
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        System.out.println("CrBean init");
        areasList = new ArrayList<>();
        idArea = usuarios.getIdArea().getIdArea();
        cargarAreas();
    }

    public void buscarCr() {
        System.out.println("entre btnBuscarRcb");
        System.out.println("numRcb: " + numCr);
        listCrViewDto.clear();
        List<Cr> listRcb = new ArrayList<>();
        Cr rcbBusqueda = new Cr();
        rcbBusqueda.setNumeroCr(numCr);
        //listRcb = crService.buscaCrTypedQuery(rcbBusqueda);          
        listRcb = crService.getAllCr();

        for (Cr rcb : listRcb) {
            if (Objects.equals(rcb.getIdArea().getIdArea(), usuarios.getIdArea().getIdArea())) {
                CrConsultaViewDto rcbView = new CrConsultaViewDto();
                rcbView.setCr(rcb);
                rcbView.setNumeroClaves(rcb.getCrInsumosList().size());
                listCrViewDto.add(rcbView);
            } else if (usuarios.getIdArea().getIdArea() == 10) {
                CrConsultaViewDto rcbView = new CrConsultaViewDto();
                rcbView.setCr(rcb);
                rcbView.setNumeroClaves(rcb.getCrInsumosList().size());
                listCrViewDto.add(rcbView);
            }
        }

    }

    public String irRcbDetalle(CrConsultaViewDto crSeleccionada) {
        util.setContextAtributte("idCr", crSeleccionada.getCr().getIdCr());
        util.setSessionMapValue("idArea", crSeleccionada.getCr().getIdArea().getIdArea());
        return "crDetalle.xhtml?faces-redirect=true";

    }

    public void guardarIdArea() {
        System.out.println("idCrArea : " + idArea);
        util.setSessionMapValue("idArea", idArea);
    }

    public void cargarAreas() {
        List<Area> areasListAux = areasService.obtenerAreas();
        for (Area ar : areasListAux) {
            if (ar.getIdArea() != 10 && ar.getIdPadre() != null) {
                if (usuarios.getIdArea().getIdArea() == 16 || usuarios.getIdArea().getIdArea() == 17) {
                    if (ar.getIdArea() >= 11 && ar.getIdArea() <= 14) {
                        areasList.add(ar);
                    }
                } else if (Objects.equals(ar.getIdArea(), usuarios.getIdArea().getIdArea())) {
                    areasList.add(ar);
                    idArea = ar.getIdArea();
                }
            }
            if (usuarios.getIdUsuario() == 1) {
                areasList.add(ar);
            }
        }
    }

    public List<CrConsultaViewDto> getListCrViewDto() {
        return listCrViewDto;
    }

    public void setListCrViewDto(List<CrConsultaViewDto> listCrViewDto) {
        this.listCrViewDto = listCrViewDto;
    }

    public String getNumCr() {
        return numCr;
    }

    public void setNumCr(String numCr) {
        this.numCr = numCr;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public List<Area> getAreasList() {
        return areasList;
    }

    public void setAreasList(List<Area> areasList) {
        this.areasList = areasList;
    }

}
