package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.UnidadMedicaService;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.service.silodisa.modelo.CatUnidadMedica;
import com.issste.sicabis.ejb.service.silodisa.service.CatalogoUnidadesMedicasService;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciaPorClaveUmusService;
import com.issste.sicabis.ejb.siam.ln.VwExistenciasSICABISService;
import com.issste.sicabis.ejb.siam.modelo.VwExistenciasSICABIS;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

public class ExistenciasSiamSilodisaBean {

    @EJB
    private ExistenciaPorClaveUmusService existenciaPorClaveUmusService;

    @EJB
    private VwExistenciasSICABISService vwExistenciasSICABISService;

    @EJB
    private CatalogoUnidadesMedicasService catalogoUnidadesMedicasService;

    @EJB
    private UnidadMedicaService unidadMedicaService;

    private DpnInsumos dpnInsumos;

    private List<UnidadesMedicas> listaUnidadesMedicas;
    private List<CatUnidadMedica> listaCatUnidadMedica;
    private List<DpnInsumos> listaDpnInsumo;
    private String clave;
    private String claveUnidad;
    private Integer idUnidadMedica;
    private String fecha;
    private Date fechaInicio;
    private Date fechaFin;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();

    @PostConstruct
    public void init() {
        fecha = util.formateaFecha(new Date());
        listaUnidadesMedicas = unidadMedicaService.unidadMedica();
        listaCatUnidadMedica = catalogoUnidadesMedicasService.getAll();
        fechaInicio = new Date();
        fechaFin = new Date();
        listaDpnInsumo = new ArrayList();
    }

    public void consultarExistencias() {

        String claveUnidadAux = "-1";
        if (!claveUnidad.equals("-1")) {
            claveUnidadAux = util.cambiaClaveUnidad(claveUnidad);
        }
        List<VwExistenciasSICABIS> listaExistSiam = vwExistenciasSICABISService.getByUMUClaveFecha(clave, claveUnidadAux, new Date(), fechaInicio, fechaFin);
        if (listaExistSiam != null) {
            DpnInsumos dpnInsumosAux = null;
            for (VwExistenciasSICABIS vwes : listaExistSiam) {
                dpnInsumosAux = new DpnInsumos();
                dpnInsumosAux.setClaveInsumo(vwes.getClaveInsumo());
                dpnInsumosAux.setClaveUnidad(vwes.getClaveUnidad());
                dpnInsumosAux.setExistenciasSiam((int) (long) vwes.getExistenciaCorte());
                List<DpnInsumos> tempList = new ArrayList();
                tempList = existenciaPorClaveUmusService.getByUMUClaveFecha(dpnInsumosAux.getClaveInsumo(), dpnInsumosAux.getClaveUnidad(), new Date());
                if (tempList != null) {
                    dpnInsumosAux.setExistenciasCenadi(tempList.get(0).getExistenciasCenadi());
                }
                listaDpnInsumo.add(dpnInsumosAux);
            }
        } else {
            listaDpnInsumo = existenciaPorClaveUmusService.getByUMUClaveFecha("", "-1", new Date());
        }

    }

    public List<UnidadesMedicas> getListaUnidadesMedicas() {
        return listaUnidadesMedicas;
    }

    public void setListaUnidadesMedicas(List<UnidadesMedicas> listaUnidadesMedicas) {
        this.listaUnidadesMedicas = listaUnidadesMedicas;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getIdUnidadMedica() {
        return idUnidadMedica;
    }

    public void setIdUnidadMedica(Integer idUnidadMedica) {
        this.idUnidadMedica = idUnidadMedica;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public DpnInsumos getDpnInsumos() {
        return dpnInsumos;
    }

    public void setDpnInsumos(DpnInsumos dpnInsumos) {
        this.dpnInsumos = dpnInsumos;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public String getClaveUnidad() {
        return claveUnidad;
    }

    public void setClaveUnidad(String claveUnidad) {
        this.claveUnidad = claveUnidad;
    }

    public List<CatUnidadMedica> getListaCatUnidadMedica() {
        return listaCatUnidadMedica;
    }

    public void setListaCatUnidadMedica(List<CatUnidadMedica> listaCatUnidadMedica) {
        this.listaCatUnidadMedica = listaCatUnidadMedica;
    }

    public List<DpnInsumos> getListaDpnInsumo() {
        return listaDpnInsumo;
    }

    public void setListaDpnInsumo(List<DpnInsumos> listaDpnInsumo) {
        this.listaDpnInsumo = listaDpnInsumo;
    }

}
