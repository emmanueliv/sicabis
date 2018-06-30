/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.RcbConsultaViewDto;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.RcbService;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
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
@ManagedBean(name = "invMercadoBean")
@ViewScoped
public class InvestigacionMercadoBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private RcbService rcbService;
    @EJB
    private EstatusService estatusService;
    private List<RcbConsultaViewDto> listRcbViewDto= new ArrayList<>();
    private String numRcb;
    private String periodoCubrir;
    private List<Estatus> listaEstatusRcb;
    private Integer idEstatusSeleccionado;
    private Utilidades util = new Utilidades();

    @PostConstruct
    public void init() {
        System.out.println("invMercadoBean init");
        List<Rcb> listRcbInvestigacionSolicitada = rcbService.traeRcbListPorEstatus(12);
        List<Rcb> listRcbEsperandoInvestigacion = rcbService.traeRcbListPorEstatus(13);
        if (listRcbInvestigacionSolicitada != null) {
            for (Rcb rcb : listRcbInvestigacionSolicitada) {
                RcbConsultaViewDto rcbView = new RcbConsultaViewDto();
                rcbView.setRcb(rcb);
                rcbView.setNumeroClaves(rcb.getRcbInsumosList().size());
                listRcbViewDto.add(rcbView);
            }
        }
        if (listRcbEsperandoInvestigacion != null) {
            for (Rcb rcb : listRcbEsperandoInvestigacion) {
                RcbConsultaViewDto rcbView = new RcbConsultaViewDto();
                rcbView.setRcb(rcb);
                rcbView.setNumeroClaves(rcb.getRcbInsumosList().size());
                listRcbViewDto.add(rcbView);
            }
        }
        
    }

    public void onClickConsultaSolInvesMercadoRcbs() {
        System.out.println("entre al onclick de onClickConsultaRcbs");
        listRcbViewDto.clear();
        List<Rcb> listRcbInvestigacionSolicitada = rcbService.traeRcbListPorEstatus(12);
        List<Rcb> listRcbEsperandoInvestigacion = rcbService.traeRcbListPorEstatus(13);
        if (listRcbInvestigacionSolicitada != null) {
            for (Rcb rcb : listRcbInvestigacionSolicitada) {
                RcbConsultaViewDto rcbView = new RcbConsultaViewDto();
                rcbView.setRcb(rcb);
                rcbView.setNumeroClaves(rcb.getRcbInsumosList().size());
                listRcbViewDto.add(rcbView);
            }
        }
        if (listRcbEsperandoInvestigacion != null) {
            for (Rcb rcb : listRcbEsperandoInvestigacion) {
                RcbConsultaViewDto rcbView = new RcbConsultaViewDto();
                rcbView.setRcb(rcb);
                rcbView.setNumeroClaves(rcb.getRcbInsumosList().size());
                listRcbViewDto.add(rcbView);
            }
        }
        System.out.println("sali al onclick de onClickConsultaRcbs");
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        System.out.println("entree oncell");
    }


    public void irRcbDetalles(RcbConsultaViewDto rcbSeleccionada) {
        System.out.println("entro irRcbDetalle");
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/rcb/rcbDetalle.xhtml?idrcb=" + rcbSeleccionada.getRcb().getIdRcb()+"&invest=1");
            util.setContextAtributte("perfilInvestigacion", true);
        } catch (IOException ex) {
            Logger.getLogger(RcbBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String irRcbDetalle(RcbConsultaViewDto rcbSeleccionada) {
        util.setContextAtributte("idrcb", rcbSeleccionada.getRcb().getIdRcb());
        util.setContextAtributte("perfilInvestigacion", true);
        util.setContextAtributte("perfilAdjudicacion",false);
        return "rcbDetalle.xhtml?faces-redirect=true";

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

    public String getPeriodoCubrir() {
        return periodoCubrir;
    }

    public void setPeriodoCubrir(String periodoCubrir) {
        this.periodoCubrir = periodoCubrir;
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

}
