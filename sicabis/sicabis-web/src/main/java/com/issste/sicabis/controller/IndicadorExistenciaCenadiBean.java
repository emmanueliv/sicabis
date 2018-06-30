/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.ExistenciaReservaClaveCenadiHistoricoService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.modelo.ExistenciaReservaClaveCenadiHistorico;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.service.silodisa.modelo.CatInsumos;
import com.issste.sicabis.ejb.service.silodisa.service.CatalogoInsumosService;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciaReservaClaveCenadiService;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciasPorClaveCenadiService;
import com.issste.sicabis.utils.Mensajes;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author 5XD9BG2
 */
public class IndicadorExistenciaCenadiBean {

    @EJB
    private CatalogoInsumosService catalogoInsumosService;

    @EJB
    private ExistenciaReservaClaveCenadiHistoricoService existenciaReservaClaveCenadiHistoricoService;

    @EJB
    private ExistenciaReservaClaveCenadiService existenciaReservaClaveCenadiService;

    @EJB
    private ExistenciasPorClaveCenadiService existenciasPorClaveCenadiService;

    private Date fechaInicio;
    private Date fechaFin;
    private String tipoClave;
    private String clave;
    private String clave2;
    private List<ExistenciaReservaClaveCenadiHistorico> listaAux;
    private List<CatInsumos> listCatInsumo;
    private Mensajes mensajes = new Mensajes();

    @PostConstruct
    public void init() {
        clave = "-1";
        clave2 = "-1";
        tipoClave = "-1";
        listCatInsumo = catalogoInsumosService.getAllCatalogoInsumos();
        fechaInicio = new Date();
        fechaFin = new Date();
        listaAux = existenciaReservaClaveCenadiHistoricoService.getByFiltros(fechaInicio, fechaFin, tipoClave, clave, clave2);
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
            listaAux = existenciaReservaClaveCenadiHistoricoService.getByFiltros(fechaInicio, fechaFin, tipoClave, clave, clave2);
            if (listaAux == null) {
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

    public List<ExistenciaReservaClaveCenadiHistorico> getListaAux() {
        return listaAux;
    }

    public void setListaAux(List<ExistenciaReservaClaveCenadiHistorico> listaAux) {
        this.listaAux = listaAux;
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

    public List<CatInsumos> getListCatInsumo() {
        return listCatInsumo;
    }

    public void setListCatInsumo(List<CatInsumos> listCatInsumo) {
        this.listCatInsumo = listCatInsumo;
    }

}
