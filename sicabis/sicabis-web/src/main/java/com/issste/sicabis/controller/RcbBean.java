/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.RcbConsultaViewDto;
import com.issste.sicabis.ejb.ln.AreasService;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.RcbService;
import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author erik
 */
@ManagedBean(name = "rcbBean")
@ViewScoped
public class RcbBean implements Serializable {

    /**
     * Creates a new instance of RcbBean
     */
    private static final long serialVersionUID = 1L;

    @EJB
    private RcbService rcbService;
    @EJB
    private EstatusService estatusService;
    @EJB
    private AreasService areasService;

    private List<RcbConsultaViewDto> listRcbViewDto = new ArrayList<>();
    private String numRcb;
    private Integer periodoCubrir;
    private List<Estatus> listaEstatusRcb;
    private Integer idEstatusSeleccionado;
    private Utilidades util = new Utilidades();
    private Usuarios usuarios;

    private int idArea;
    private List<Area> areasList;

    public RcbBean() {
        usuarios = new Usuarios();
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        idArea = usuarios.getIdArea().getIdArea();
        System.out.println("RcbBean init");
        listaEstatusRcb = estatusService.getEstatusByTarea(1);
        areasList = new ArrayList<>();
        cargarAreas();

    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        System.out.println("entree oncell");
    }

    public void btnBuscarRcb() {
        listRcbViewDto.clear();
        List<Rcb> listRcb = new ArrayList<>();
        Rcb rcbBusqueda = new Rcb();
        rcbBusqueda.setNumero(numRcb);
        rcbBusqueda.setIdEstatus(new Estatus(idEstatusSeleccionado));
        rcbBusqueda.setPeriodo(periodoCubrir != null ? periodoCubrir : 0);
        rcbBusqueda.setIdArea(new Area(idArea));
        listRcb = rcbService.buscarRcbPorCampos(rcbBusqueda);

        for (Rcb rcb : listRcb) {
            RcbConsultaViewDto rcbView = new RcbConsultaViewDto();
            rcbView.setRcb(rcb);
            rcbView.setNumeroClaves(rcb.getRcbInsumosList().size());
            listRcbViewDto.add(rcbView);
        }
    }

    public void irRcbDetalles(RcbConsultaViewDto rcbSeleccionada) {
        System.out.println("entro irRcbDetalle");
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/rcb/rcbDetalle.xhtml?idrcb=" + rcbSeleccionada.getRcb().getIdRcb());
            util.setContextAtributte("perfilInvestigacion", true);
        } catch (IOException ex) {
            Logger.getLogger(RcbBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String irRcbDetalle(RcbConsultaViewDto rcbSeleccionada) {
        util.setContextAtributte("idrcb", rcbSeleccionada.getRcb().getIdRcb());
        util.setContextAtributte("perfilInvestigacion", false);
        util.setContextAtributte("perfilAdjudicacion", false);
        return "rcbDetalle.xhtml?faces-redirect=true";

    }

    public String irRcbDetalleAdjudicacion(RcbConsultaViewDto rcbSeleccionada) {
        util.setContextAtributte("idrcb", rcbSeleccionada.getRcb().getIdRcb());
        util.setContextAtributte("perfilInvestigacion", false);
        util.setContextAtributte("perfilAdjudicacion", true);
        return "rcbDetalle.xhtml?faces-redirect=true";

    }

    public void cargarAreas() {
        List<Area> areasListAux = areasService.obtenerAreas();
        for (Area ar : areasListAux) {
            if (ar.getIdArea() != 10 && ar.getIdPadre() != null) {
                if (idArea == 16 || idArea == 17) {
                    if (ar.getIdArea() >= 11 && ar.getIdArea() <= 14) {
                        areasList.add(ar);
                    }
                } else if (ar.getIdArea() == idArea) {
                    areasList.add(ar);
                    idArea = ar.getIdArea();
                }
            }
            if (usuarios.getIdUsuario() == 1) {
                areasList.add(ar);
            }
        }
    }

    public void guardarIdArea() {
        System.out.println("idArea : " + idArea);
        util.setSessionMapValue("idArea", idArea);
    }

    public List<RcbConsultaViewDto> getListRcbViewDto() {
        return listRcbViewDto;
    }

    public void setListRcbViewDto(List<RcbConsultaViewDto> listRcbViewDto) {
        this.listRcbViewDto = listRcbViewDto;
    }

    public String getNumRcb() {
        return numRcb;
    }

    public void setNumRcb(String numRcb) {
        this.numRcb = numRcb;
    }

    public List<Estatus> getListaEstatusRcb() {
        return listaEstatusRcb;
    }

    public void setListaEstatusRcb(List<Estatus> listaEstatusRcb) {
        this.listaEstatusRcb = listaEstatusRcb;
    }

    public Integer getIdEstatusSeleccionado() {
        return idEstatusSeleccionado;
    }

    public void setIdEstatusSeleccionado(Integer idEstatusSeleccionado) {
        this.idEstatusSeleccionado = idEstatusSeleccionado;
    }

    public Integer getPeriodoCubrir() {
        return periodoCubrir;
    }

    public void setPeriodoCubrir(Integer periodoCubrir) {
        this.periodoCubrir = periodoCubrir;
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
