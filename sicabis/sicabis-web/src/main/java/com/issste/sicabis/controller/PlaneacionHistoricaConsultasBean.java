/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DAO.AreaDAO;
import com.issste.sicabis.ejb.ln.AreasService;
import com.issste.sicabis.ejb.ln.AsignacionInsumosService;
import com.issste.sicabis.ejb.ln.PlaneacionService;
import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.Planeacion;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Toshiba Manolo
 */
@ManagedBean(name = "consultaPlaneacionBean")
@ViewScoped
public class PlaneacionHistoricaConsultasBean {

    private List<Planeacion> listPlaneaciones;
    private List<Planeacion> listPlaneacionesFilter;

    private Mensajes mensaje = new Mensajes();
    private Usuarios usuarios;
    private Utilidades util = new Utilidades();
    private Area areaSelect;
    private List<Area> listaAreas;
    private String console;
    private Boolean deshabilitaSelectArea;

    private Integer idTipoSolicitud;

    @EJB
    PlaneacionService planeacionService;
    @EJB
    AsignacionInsumosService asignacionInsumosService;
    @EJB
    private AreasService areasService;

    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        idTipoSolicitud = Integer.parseInt(request.getParameter("idTipoSolicitud"));
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        System.out.println("idTipoSolicitud:" + idTipoSolicitud);
        System.out.println("usuarios.getIdArea().getIdArea():" + usuarios.getIdArea().getIdArea());
        cargarAreas();
        deshabilitaSelectArea = false;
        if (usuarios.getIdArea().getMaestra() != null) {
            listPlaneaciones = planeacionService.obtenerPlaneacionesPorTipoSolicitud(idTipoSolicitud);
        } else {
            deshabilitaSelectArea = true;
            listPlaneaciones = planeacionService.obtenerPlaneacionesPorTipoSolicitudArea(idTipoSolicitud, usuarios.getIdArea().getIdArea());
        }
    }

    public void actualizaSelectarea() {
        System.out.println("areaSelect" + areaSelect.getIdArea());
    }

    public void cargarAreas() {
        List<Area> areasListAux = areasService.obtenerAreas();
        listaAreas = new ArrayList<>();
        areaSelect = new Area();
        for (Area ar : areasListAux) {
            if (ar.getIdArea() != 10 && ar.getIdPadre() != null) {
                if (usuarios.getIdArea().getIdArea() == 16 || usuarios.getIdArea().getIdArea() == 17) {
                    if (ar.getIdArea() >= 11 && ar.getIdArea() <= 14) {
                        listaAreas.add(ar);
                    }
                } else if (ar.getIdArea() == usuarios.getIdArea().getIdArea()) {
                    listaAreas.add(ar);
                    areaSelect.setIdArea(usuarios.getIdArea().getIdArea());
                }
            }
            if (usuarios.getIdUsuario() == 1) {
                listaAreas.add(ar);
            }
        }
        areaSelect.setIdArea(usuarios.getIdArea().getIdArea());
    }

    public String seleccionaVistaPorArea() {
        System.out.println("seleccionaVistaPorArea");
        System.out.println("areaSelect" + areaSelect.getIdArea());
        System.out.println("consola" + console);
        String redirecciona = "";
        util.setContextAtributte("idPlaneacion", null);
        util.setContextAtributte("idTipoSolicitud", idTipoSolicitud);
        util.setContextAtributte("idAreaDistribucion", areaSelect.getIdArea());
        switch (areaSelect.getIdArea()) {

            case 11:
                //area de infraestructura
                redirecciona = "distribucionMensualInfraestructura.xhtml?faces-redirect=true";
                break;
            case 12:
                //area de prevencioa
                redirecciona = "distribucionMensualPrevencion.xhtml?faces-redirect=true";
                break;
            case 13:
                //area de regulacion vih
                redirecciona = "distribucionMensualRegulacion.xhtml?faces-redirect=true";
                break;
            case 14:
                //area de regulacion transplates
                redirecciona = "distribucionMensualRegulacion.xhtml?faces-redirect=true";
                break;
            default:
                break;

        }

        return redirecciona;
    }

    public String irPlaneacionDetalle(Planeacion planeacion) {
//           System.out.println("seleccionaVistaPorArea");
        System.out.println("areaSelect irPlaneacionDetalle" + areaSelect.getIdArea());
        System.out.println("planeacion.getIdPlaneacion()" + planeacion.getIdPlaneacion());
        String redirecciona = "";
        util.setContextAtributte("idPlaneacion", planeacion.getIdPlaneacion());
        util.setContextAtributte("idTipoSolicitud", idTipoSolicitud);
        util.setContextAtributte("idAreaDistribucion", planeacion.getIdArea().getIdArea());
        switch (planeacion.getIdArea().getIdArea()) {

            case 11:
                //area de infraestructura
                redirecciona = "distribucionMensualInfraestructura?faces-redirect=true";
                break;
            case 12:
                //area de prevencioa
                redirecciona = "distribucionMensualPrevencion?faces-redirect=true";
                break;
            case 13:
                //area de regulacion vih
                redirecciona = "distribucionMensualRegulacion.xhtml?faces-redirect=true";
                break;
            case 14:
                //area de regulacion transplates
                redirecciona = "distribucionMensualRegulacion.xhtml?faces-redirect=true";
                break;
            default:
                break;

        }

        return redirecciona;

    }

    public String irPlaneacionRegulacion() {
//        List<AsignacionInsumos> asignacion = asignacionInsumosService.obtenerAsignaciones();
//        System.out.println("asignacion"+asignacion.size());
//        System.out.println("asignacion"+asignacion.get(0).getIdInsumo().getDescripcion());
        System.out.println("irPlaneacionDetalle");
        util.setContextAtributte("idPlaneacion", null);
        util.setContextAtributte("idTipoSolicitud", idTipoSolicitud);
        util.setContextAtributte("idAreaDistribucion", areaSelect.getIdArea());
        return "distribucionMensualRegulacion.xhtml?faces-redirect=true";

    }

    public String irPlaneacionRegulacionVih() {
        System.out.println("irPlaneacionDetalle");
        util.setContextAtributte("idPlaneacion", null);
        util.setContextAtributte("idTipoSolicitud", idTipoSolicitud);
        util.setContextAtributte("idAreaDistribucion", areaSelect.getIdArea());
        return "distribucionMensualRegulacion.xhtml?faces-redirect=true";

    }

    public String irPlaneacionPrevencion() {
        System.out.println("irPlaneacionDetalle");
        util.setContextAtributte("idPlaneacion", null);
        util.setContextAtributte("idTipoSolicitud", idTipoSolicitud);
        util.setContextAtributte("idAreaDistribucion", areaSelect.getIdArea());
        return "distribucionMensualPrevencion?faces-redirect=true";

    }

    public String irPlaneacionInfraestructura() {
        System.out.println("irPlaneacionDetalle");
        util.setContextAtributte("idPlaneacion", null);
        util.setContextAtributte("idTipoSolicitud", idTipoSolicitud);
        util.setContextAtributte("idAreaDistribucion", areaSelect.getIdArea());
        return "distribucionMensualInfraestructura?faces-redirect=true";

    }

    public List<Planeacion> getListPlaneaciones() {
        return listPlaneaciones;
    }

    public void setListPlaneaciones(List<Planeacion> listPlaneaciones) {
        this.listPlaneaciones = listPlaneaciones;
    }

    public List<Planeacion> getListPlaneacionesFilter() {
        return listPlaneacionesFilter;
    }

    public void setListPlaneacionesFilter(List<Planeacion> listPlaneacionesFilter) {
        this.listPlaneacionesFilter = listPlaneacionesFilter;
    }

    public Integer getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    public void setIdTipoSolicitud(Integer idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
    }

    public Area getAreaSelect() {
        return areaSelect;
    }

    public void setAreaSelect(Area areaSelect) {
        this.areaSelect = areaSelect;
    }

    public List<Area> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List<Area> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public Boolean getDeshabilitaSelectArea() {
        return deshabilitaSelectArea;
    }

    public void setDeshabilitaSelectArea(Boolean deshabilitaSelectArea) {
        this.deshabilitaSelectArea = deshabilitaSelectArea;
    }

}
