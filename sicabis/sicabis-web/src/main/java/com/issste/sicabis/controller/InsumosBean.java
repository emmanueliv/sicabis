/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author erik
 */
@ManagedBean(name = "insumosBean")
@ViewScoped
public class InsumosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;
    @EJB
    private InsumosService insumoService;

    List<Insumos> listaInsumos;
    private String buscaInsumo;
    private Usuarios usuarios;
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private Utilidades util = new Utilidades();

    private int idArea;

    public InsumosBean() {
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        if (usuarios.getIdArea() == null || usuarios.getIdArea().getIdArea() == 10) {
            if (util.getSessionAtributte("idArea") != null) {
                idArea = (int) util.getSessionAtributte("idArea");
            } else {
                idArea = usuarios.getIdArea().getIdArea();
            }
        }
        System.out.println("InsumosBean usuarios.getIdArea():" + usuarios.getIdArea().getIdArea());
        listaInsumos = new ArrayList<>();
    }

    public void seleccionaInsumoDeDialogo(Insumos insumo) {
        System.out.println("seleccionaInsumoDeDialogo");
        RequestContext.getCurrentInstance().closeDialog(insumo);
    }

    public void buscarInsumo() {
        System.out.println("Entro a buscar insumo" + buscaInsumo);
        if (buscaInsumo == null || buscaInsumo.equals("") || buscaInsumo.equals(" ")) {
            System.out.println("Entro a buscar todos los insumos");
            System.out.println("usuarios.getIdArea().getIdArea(): " + idArea);
            listaInsumos = insumoService.traeListaInsumosPorArea(idArea);
        } else {
            System.out.println("Entro a buscar insumo por clave");
            listaInsumos = insumoService.buscarInsumosPorClave(buscaInsumo.trim());
        }
        System.out.println("salio:" + listaInsumos.size());

    }

    public List<Insumos> getListaInsumos() {
        return listaInsumos;
    }

    public void setListaInsumos(List<Insumos> listaInsumos) {
        this.listaInsumos = listaInsumos;
    }

    public String getBuscaInsumo() {
        return buscaInsumo;
    }

    public void setBuscaInsumo(String buscaInsumo) {
        this.buscaInsumo = buscaInsumo;
    }

}
