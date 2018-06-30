/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.HistoricoExistenciaPorClaveCenadiService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.modelo.HistoricoExistenciaPorClaveCenadi;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.service.silodisa.modelo.CatInsumos;
import com.issste.sicabis.ejb.service.silodisa.service.CatalogoInsumosService;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciasPorClaveCenadiService;
import com.issste.sicabis.utils.Mensajes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author CF19BG2
 */
public class IndicadorExistenciaCenadiSetBean {

    @EJB
    private CatalogoInsumosService catalogoInsumosService;
    @EJB
    private HistoricoExistenciaPorClaveCenadiService historicoExistenciaPorClaveCenadiService;
    @EJB
    private ExistenciasPorClaveCenadiService existenciasPorClaveCenadiService;

    private List<HistoricoExistenciaPorClaveCenadi> historicoListConsul;
    private Date fechaInicio;
    private Date fechaFin;
    private String tipoClave;
    private String clave;
    private String clave2;
    private List<CatInsumos> insumosList;
    private String subinventario;
    private String localizador;
    private String lote;

    private Mensajes mensajes = new Mensajes();

    @PostConstruct
    public void init() {
        tipoClave = "-1";
        clave = "-1";
        clave2 = "-1";
        subinventario = "";
        localizador = "";
        lote = "";
        historicoListConsul = new ArrayList();
        insumosList = catalogoInsumosService.getAllCatalogoInsumos();
        fechaInicio = new Date();
        fechaFin = new Date();
        historicoListConsul = historicoExistenciaPorClaveCenadiService.getByFiltros(fechaInicio, fechaFin, tipoClave, clave, clave2, subinventario, localizador, lote);
    }

    public boolean valida() {
        boolean bandera = true;
        if (fechaInicio == null) {
            bandera = false;
            mensajes.mensaje("Debes ingresar la fecha inicial", "amarillo");
        }
        if (fechaFin == null) {
            bandera = false;
            mensajes.mensaje("Debes ingresar la fecha final", "amarillo");
        }
        if (fechaInicio != null && fechaFin != null) {
            if (fechaFin.compareTo(fechaInicio) < 0) {
                bandera = false;
                mensajes.mensaje("La fecha final es menor que la fecha inicial", "amarillo");
            }
        }
        return bandera;
    }

    public void buscarClaveByFiltros() {
        if (valida()) {
            historicoListConsul = historicoExistenciaPorClaveCenadiService.getByFiltros(fechaInicio, fechaFin, tipoClave, clave, clave2, subinventario, localizador, lote);
            System.out.println("listaAux" + historicoListConsul);
            if (historicoListConsul == null) {
                mensajes.mensaje("No hay registros para la búsqueda seleccionada", "amarillo");
            }
        }
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTipoClave() {
        return tipoClave;
    }

    public void setTipoClave(String tipoClave) {
        this.tipoClave = tipoClave;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave2() {
        return clave2;
    }

    public void setClave2(String clave2) {
        this.clave2 = clave2;
    }

    public List<HistoricoExistenciaPorClaveCenadi> getHistoricoListConsul() {
        return historicoListConsul;
    }

    public void setHistoricoListConsul(List<HistoricoExistenciaPorClaveCenadi> historicoListConsul) {
        this.historicoListConsul = historicoListConsul;
    }

    public List<CatInsumos> getInsumosList() {
        return insumosList;
    }

    public void setInsumosList(List<CatInsumos> insumosList) {
        this.insumosList = insumosList;
    }

    public String getSubinventario() {
        return subinventario;
    }

    public void setSubinventario(String subinventario) {
        this.subinventario = subinventario;
    }

    public String getLocalizador() {
        return localizador;
    }

    public void setLocalizador(String localizador) {
        this.localizador = localizador;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

}
