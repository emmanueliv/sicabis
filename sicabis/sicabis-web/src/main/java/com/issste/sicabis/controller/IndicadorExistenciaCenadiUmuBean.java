/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.ExistenciaClaveUmuDTO;
import com.issste.sicabis.ejb.DTO.HistorialExisteciaClaveUmuDTO;
import com.issste.sicabis.ejb.ln.DelegacionesService;
import com.issste.sicabis.ejb.ln.HistoricoExistenciaPorClaveUmusService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.HistoricoExistenciaPorClaveUmus;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.service.silodisa.modelo.CatInsumos;
import com.issste.sicabis.ejb.service.silodisa.service.CatalogoInsumosService;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciaPorClaveUmusService;
import com.issste.sicabis.utils.Mensajes;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author 5XD9BG2
 */
public class IndicadorExistenciaCenadiUmuBean {

    @EJB
    private CatalogoInsumosService catalogoInsumosService;

    @EJB
    private DelegacionesService delegacionesService;

    @EJB
    private ExistenciaPorClaveUmusService existenciaPorClaveUmusService;

    @EJB
    private HistoricoExistenciaPorClaveUmusService historicoExistenciaPorClaveUmusService;

    private Date fechaInicio;
    private Date fechaFin;
    private String clave;
    private String clave2;
    private String delegacion;
    private String numeroUmu;
    private String nombreUmu;
    private String tipo;
    private List<HistoricoExistenciaPorClaveUmus> listaAux;
    private List<Delegaciones> delegacionList;
    private List<CatInsumos> insumosList;

    private Mensajes mensajes = new Mensajes();

    @PostConstruct
    public void init() {
        clave = "-1";
        clave2 = "-1";
        tipo = "";
        delegacion = "-1";
        nombreUmu = "";
        numeroUmu = "";
        delegacionList = delegacionesService.obtenerDelegaciones();
        insumosList = catalogoInsumosService.getAllCatalogoInsumos();
        fechaInicio = new Date();
        fechaFin = new Date();
        listaAux = historicoExistenciaPorClaveUmusService.getByFiltros(fechaInicio, fechaFin, delegacion, numeroUmu, nombreUmu, clave, clave2, tipo);
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

    public void buscarByFiltros() {
        if (valida()) {
            listaAux = historicoExistenciaPorClaveUmusService.getByFiltros(fechaInicio, fechaFin, delegacion, numeroUmu, nombreUmu, clave, clave2, tipo);
            if (listaAux == null) {
                mensajes.mensaje("No hay registros", "amarillo");
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

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public String getNumeroUmu() {
        return numeroUmu;
    }

    public void setNumeroUmu(String numeroUmu) {
        this.numeroUmu = numeroUmu;
    }

    public String getNombreUmu() {
        return nombreUmu;
    }

    public void setNombreUmu(String nombreUmu) {
        this.nombreUmu = nombreUmu;
    }

    public List<HistoricoExistenciaPorClaveUmus> getListaAux() {
        return listaAux;
    }

    public void setListaAux(List<HistoricoExistenciaPorClaveUmus> listaAux) {
        this.listaAux = listaAux;
    }

    public List<Delegaciones> getDelegacionList() {
        return delegacionList;
    }

    public void setDelegacionList(List<Delegaciones> delegacionList) {
        this.delegacionList = delegacionList;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

    public List<CatInsumos> getInsumosList() {
        return insumosList;
    }

    public void setInsumosList(List<CatInsumos> insumosList) {
        this.insumosList = insumosList;
    }

}
