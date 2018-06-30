package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.DelegacionesService;
import com.issste.sicabis.ejb.ln.HistoricoExistenciaPorClaveUmusService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.HistoricoExistenciaPorClaveUmus;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.service.silodisa.modelo.CatInsumos;
import com.issste.sicabis.ejb.service.silodisa.service.CatalogoInsumosService;
import com.issste.sicabis.utils.Mensajes;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

public class IndicadorExistenciaDetalleClaveUmuBean implements Serializable {

    @EJB
    private CatalogoInsumosService catalogoInsumosService;

    @EJB
    private HistoricoExistenciaPorClaveUmusService historicoExistenciaPorClaveUmusService;

    @EJB
    private DelegacionesService delegacionesService;

    private List<HistoricoExistenciaPorClaveUmus> existenciaList;
    private List<CatInsumos> insumosList;
    private List<Delegaciones> delegacionList;
    private Date fechaInicio;
    private Date fechaFin;
    private String delegacion;
    private String numeroUmu;
    private String nombreUmu;
    private String clave;
    private String clave2;
    private String lote;

    private Mensajes mensajes = new Mensajes();

    public IndicadorExistenciaDetalleClaveUmuBean() {
    }

    @PostConstruct
    public void init() {
        clave = "-1";
        clave2 = "-1";
        nombreUmu = "";
        numeroUmu = "";
        lote = "";
        delegacion = "-1";
        insumosList = catalogoInsumosService.getAllCatalogoInsumos();
        delegacionList = delegacionesService.obtenerDelegaciones();
        fechaInicio = new Date();
        fechaFin = new Date();
        existenciaList = historicoExistenciaPorClaveUmusService.getByFiltrosDetalle(fechaInicio, fechaFin, delegacion, numeroUmu, nombreUmu, clave, clave2, lote);
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
            existenciaList = historicoExistenciaPorClaveUmusService.getByFiltrosDetalle(fechaInicio, fechaFin, delegacion, numeroUmu, nombreUmu, clave, clave2, lote);
            if (existenciaList == null) {
                mensajes.mensaje("No hay datos registrados", "amarillo");
            }
        }
    }

    public List<HistoricoExistenciaPorClaveUmus> getExistenciaList() {
        return existenciaList;
    }

    public void setExistenciaList(List<HistoricoExistenciaPorClaveUmus> existenciaList) {
        this.existenciaList = existenciaList;
    }

    public List<Delegaciones> getDelegacionList() {
        return delegacionList;
    }

    public void setDelegacionList(List<Delegaciones> delegacionList) {
        this.delegacionList = delegacionList;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getClave2() {
        return clave2;
    }

    public void setClave2(String clave2) {
        this.clave2 = clave2;
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
