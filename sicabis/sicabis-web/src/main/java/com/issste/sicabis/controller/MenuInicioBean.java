/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.RcbConsultaViewDto;
import com.issste.sicabis.ejb.ln.RcbService;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.service.silodisa.controller.AlertasOperativasController;
import com.issste.sicabis.ejb.service.silodisa.controller.CatalogoInsumosController;
import com.issste.sicabis.ejb.service.silodisa.controller.CatalogoUnidadesMedicasController;
import com.issste.sicabis.ejb.service.silodisa.controller.ClavesPorCodigoBarrasController;
import com.issste.sicabis.ejb.service.silodisa.controller.DetalleSalidasUmuGuiaDistribucionController;
import com.issste.sicabis.ejb.service.silodisa.controller.EntradasMymcqCenadiController;
import com.issste.sicabis.ejb.service.silodisa.controller.ExistenciaPorClaveUmusController;
import com.issste.sicabis.ejb.service.silodisa.controller.ExistenciaReservaClaveCenadiController;
import com.issste.sicabis.ejb.service.silodisa.controller.ExistenciasPorClaveCenadiController;
import com.issste.sicabis.ejb.service.silodisa.controller.ExistenciasUMUsProgramasController;
import com.issste.sicabis.ejb.service.silodisa.controller.MapaEjecutivoDispDelegacionesController;
import com.issste.sicabis.ejb.service.silodisa.controller.MapaEjecutivoDispG40Controller;
import com.issste.sicabis.ejb.service.silodisa.controller.MapaEjecutivoDisponibilidadEstadosController;
import com.issste.sicabis.ejb.service.silodisa.controller.RemisionesElectronicasEntregasUmuController;
import com.issste.sicabis.ejb.service.silodisa.controller.SalidasCenadiUmuGuiaDeDistribucionController;
import com.issste.sicabis.ejb.service.silodisa.controller.SeguimientoSalidasUmuInternoController;
import com.issste.sicabis.ejb.service.silodisa.controller.SeguimientoSalidasUmuTransitoController;
import com.issste.sicabis.ejb.sheduler.ShedulerJobDAO;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Manolo
 */
public class MenuInicioBean {

    @EJB
    private ExistenciasUMUsProgramasController existenciasUMUsProgramasController;

    @EJB
    private MapaEjecutivoDispG40Controller mapaEjecutivoDispG40Controller1;

    @EJB
    private MapaEjecutivoDispDelegacionesController mapaEjecutivoDispDelegacionesController1;

    @EJB
    private MapaEjecutivoDisponibilidadEstadosController mapaEjecutivoDisponibilidadEstadosController;

    @EJB
    private ShedulerJobDAO shedulerJobDAOImplement;

    @EJB
    private SalidasCenadiUmuGuiaDeDistribucionController salidasCenadiUmuGuiaDeDistribucionController;

    @EJB
    private SeguimientoSalidasUmuTransitoController seguimientoSalidasUmuTransitoController;

    @EJB
    private SeguimientoSalidasUmuInternoController seguimientoSalidasUmuInternoController;

    @EJB
    private RemisionesElectronicasEntregasUmuController remisionesElectronicasEntregasUmuController;

    @EJB
    private MapaEjecutivoDispG40Controller mapaEjecutivoDispG40Controller;

    @EJB
    private MapaEjecutivoDispDelegacionesController mapaEjecutivoDispDelegacionesController;

    @EJB
    private ExistenciaReservaClaveCenadiController existenciaReservaClaveCenadiController;

    @EJB
    private ClavesPorCodigoBarrasController clavesPorCodigoBarrasController;

    @EJB
    private DetalleSalidasUmuGuiaDistribucionController detalleSalidasUmuGuiaDistribucionController;

    @EJB
    private EntradasMymcqCenadiController entradasMymcqCenadiController;

    @EJB
    private CatalogoInsumosController catalogoInsumosController;

    @EJB
    private AlertasOperativasController alertasOperativasController;

    @EJB
    private CatalogoUnidadesMedicasController catalogoUnidadesMedicasController;

    @EJB
    private ExistenciasPorClaveCenadiController existenciasPorClaveCenadiController;

    @EJB
    private ExistenciaPorClaveUmusController existenciaPorClaveUmusController;

    @EJB
    private RcbService rcbService;

    private List<Rcb> listaRcb;
    private List<RcbConsultaViewDto> listRcbViewDto = new ArrayList<>();
    private String numRcb;
    private Integer idEstatusSeleccionado;
    private Integer periodoCubrir;
    private Usuarios usuarios;

    private Utilidades util = new Utilidades();

    @PostConstruct
    public void init() {
//        listRcbViewDto.clear();
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        listaRcb = rcbService.getRcbPendientes();
//        List<Rcb> listRcb = new ArrayList<>();
//        Rcb rcbBusqueda = new Rcb();
//        rcbBusqueda.setNumero(numRcb);
//        rcbBusqueda.setIdEstatus(new Estatus(16));
//        Calendar c = Calendar.getInstance(Locale.ENGLISH);
//        rcbBusqueda.setPeriodo(c.get(Calendar.YEAR));
//        rcbBusqueda.setIdArea(usuarios.getIdArea());
//        listRcb = rcbService.buscarRcbPorCampos(rcbBusqueda);
//
//        for (Rcb rcb : listRcb) {
//            RcbConsultaViewDto rcbView = new RcbConsultaViewDto();
//            rcbView.setRcb(rcb);
//            rcbView.setNumeroClaves(rcb.getRcbInsumosList().size());
//            listRcbViewDto.add(rcbView);
//        }
    }

    public void irRcbDetalle(RcbConsultaViewDto rcbSeleccionada) {
        util.setContextAtributte("idrcb", rcbSeleccionada.getRcb().getIdRcb());
        util.setContextAtributte("perfilInvestigacion", false);
        util.setContextAtributte("perfilAdjudicacion", false);
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/rcb/rcbDetalle.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(DetalleProcedimientoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualiza() {
        try {
            mapaEjecutivoDisponibilidadEstadosController.obtenerPorcentajePorEstado(2);
            mapaEjecutivoDispDelegacionesController.obtenerMapaEjecutivoDispDelegacion(0);
            mapaEjecutivoDispG40Controller.obtenerMapaEjecutivoDispG40(0);
            existenciaReservaClaveCenadiController.obtenerExistenciaReservaClaveCenadi(0);

//            existenciasPorClaveCenadiController.obtenerExistencias(0);
//            catalogoInsumosController.obtenerCatalogoInsumos();
//            catalogoUnidadesMedicasController.obtnerCatalogoUnidadesMedicas();
            
//            entradasMymcqCenadiController.obtenerExistenciaReservaClaveCenadi(1);
//            clavesPorCodigoBarrasController.obtenerClavesPorCodigoBarras();
//            detalleSalidasUmuGuiaDistribucionController.obtenerDetalleSalidasUmuGuiaDistribucion(1);
//            remisionesElectronicasEntregasUmuController.obtenerDetalleSalidasUmuGuiaDistribucion(1);
//            salidasCenadiUmuGuiaDeDistribucionController.obtenerSalidasCenadiUmuGuiaDeDistribucion(1);
//            seguimientoSalidasUmuInternoController.obtenerSalidasCenadiUmuGuiaDeDistribucion(1);
//            seguimientoSalidasUmuTransitoController.obtenerSeguimientoSalidasUmuTransito(1);
//            existenciasUMUsProgramasController.obtenerExistenciasUMUsProgramas(1);
//            existenciaPorClaveUmusController.obtenerTodosExistenciaPorClaveUmus(2);
//            alertasOperativasController.obtenerTodasAlertasOperativas(1);

//            entradasMymcqCenadiController.obtenerExistenciaReservaClaveCenadi(1);
//            clavesPorCodigoBarrasController.obtenerClavesPorCodigoBarras();
//            detalleSalidasUmuGuiaDistribucionController.obtenerDetalleSalidasUmuGuiaDistribucion();
//            existenciaReservaClaveCenadiController.obtenerExistenciaReservaClaveCenadi(1);
//            remisionesElectronicasEntregasUmuController.obtenerDetalleSalidasUmuGuiaDistribucion(1);
//            existenciaReservaClaveCenadiController.obtenerExistenciaReservaClaveCenadi(1);
//            remisionesElectronicasEntregasUmuController.obtenerDetalleSalidasUmuGuiaDistribucion(1);
//            seguimientoSalidasUmuInternoController.obtenerSalidasCenadiUmuGuiaDeDistribucion();
//            seguimientoSalidasUmuTransitoController.obtenerSeguimientoSalidasUmuTransito();
//            salidasCenadiUmuGuiaDeDistribucionController.obtenerSalidasCenadiUmuGuiaDeDistribucion();
//            catalogoUnidadesMedicasController.obtnerCatalogoUnidadesMedicas();
//           alertasOperativasController.obtenerTodasAlertasOperativas(1);
//            catalogoInsumosController.obtenerCatalogoInsumos();
//            catalogoUnidadesMedicasController.obtnerCatalogoUnidadesMedicas();
//            existenciaPorClaveUmusController.obtenerTodosExistenciaPorClaveUmus(1);
//            existenciasPorClaveCenadiController.obtenerExistencias(1);
            //shedulerJobDAOImplement.ejecutaAlerta();
        } catch (Exception ex) {
            Logger.getLogger(MenuInicioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RcbService getRcbService() {
        return rcbService;
    }

    public void setRcbService(RcbService rcbService) {
        this.rcbService = rcbService;
    }

    public List<Rcb> getListaRcb() {
        return listaRcb;
    }

    public void setListaRcb(List<Rcb> listaRcb) {
        this.listaRcb = listaRcb;
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

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

}
