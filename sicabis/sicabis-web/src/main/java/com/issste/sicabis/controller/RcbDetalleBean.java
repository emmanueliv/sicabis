/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.RcbDTO;
import com.issste.sicabis.ejb.DTO.RcbInsumosViewDto;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.GrupoService;
import com.issste.sicabis.ejb.ln.InsumosRcbSolicitudInvestigacionMercadoService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.RcbGrupoService;
import com.issste.sicabis.ejb.ln.RcbInsumosService;
import com.issste.sicabis.ejb.ln.RcbService;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.ln.TipoCompraService;
import com.issste.sicabis.ejb.ln.UnidadResponsableService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.ExistenciaReservaClaveCenadiHistorico;
import com.issste.sicabis.ejb.modelo.Grupo;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.ejb.modelo.RcbGrupo;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.ejb.modelo.TipoCompra;
import com.issste.sicabis.ejb.modelo.UnidadResponsable;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaReservaClaveCenadi;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciaReservaClaveCenadiService;
import com.issste.sicabis.utils.BitacoraUtil;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.PlaneacionEstatusEnum;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.primefaces.component.tabview.Tab;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.data.FilterEvent;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author erik
 */
@ManagedBean(name = "rcbBeanDetalles")
@ViewScoped
public class RcbDetalleBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<RcbInsumosViewDto> listRcbinsumosDto;
    private List<RcbInsumosViewDto> listRcbinsumosDtoFilter;
    private List<TipoCompra> listaTipoCompra;
    private List<UnidadResponsable> listaUnidadResponsable;
    private List<Grupo> listaGrupos;
    private List<RcbGrupo> listaRcbGrupo;
    private List<String> gruposSeleccionados;

    private Rcb rcb = new Rcb();

    @ManagedProperty(value = "#{archivosBean}")
    private ArchivosBean archivosBean;
    @EJB
    private ExistenciaReservaClaveCenadiService existenciaReservaClaveCenadiService;
    @EJB
    private InsumosService insumoService;
    @EJB
    private TipoCompraService tipoCompraService;
    @EJB
    private UnidadResponsableService unidadResponsableService;
    @EJB
    private RcbService rcbServicio;
    @EJB
    private RcbInsumosService rcbInsumosService;
    @EJB
    private InsumosRcbSolicitudInvestigacionMercadoService insumosSolicitudesService;
    @EJB
    private GrupoService grupoService;
    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;
    @EJB
    private RcbGrupoService rcbGrupoService;
    @EJB
    private EstatusService estatusService;

    private StreamedContent file;
    /*
     Implementacion de bitacora
     */
    @EJB
    BitacoraTareaSerice bitacoraService;
    BitacoraTareaEstatus bitacora;
    BitacoraUtil bitacoraUtil = new BitacoraUtil();
    private int idArea;

    private Mensajes mensaje = new Mensajes();
    private Usuarios usuarios;
    private Utilidades util = new Utilidades();

    private Boolean mostrarBtnCargarRCBanterior;
    private Boolean mostrarBtnEliminarTodo;
    private Boolean mostrarBtnAgregarInsumo;
    private Boolean mostrarCampoPrecioUnitario;
    private Boolean mostrarTextoPrecioUnitario;
    private Boolean desHabilitarBtnModificar;
    private Boolean desHabilitarBtnEliminar;
    private Boolean desHabilitarBtnCargarRcbAnterior;
    private Boolean desHabilitarBtnEliminarInsumos;
    private Boolean desHabilitarBtnAgregarInsumo;
    private Boolean mostrarBtnLimpiar;
    private Boolean mostrarBtnGuardar;
    private Boolean mostrarBtnInvestMercado;
    private Boolean mostrarMsjEsperandoIM;
    private String lbMsjEsperandoIM;
    private String btnGuardarValue;

    private Boolean deshabilitarselUniResponsable;
    private Boolean deshabilitarInpGrupo;
    private Boolean deshabilitarselTipoCompra;
    private Boolean deshabilitarInpDestino;
    private Boolean deshabilitarInpClave;
    private Boolean deshabilitarFechaelaboracion;
    private Boolean deshabilitarInpPeriod;
    private Boolean desHabilitarNep;
    private Boolean desHabilitarOficioSuficiencia;
    private Boolean requiredHabilitarNep;
    private Boolean requiredHabilitarOficioSuficiencia;

    private Boolean mostrarBtnAceptarIm;
    private Boolean mostrarBtnImResuelta;
    private Boolean mostrarBtnCompletarRcb;
    private Boolean mostrarBtnImprimirRcb;
    private Boolean mostrarCargaArchivos;

    private Boolean filtroActivo;
    private Boolean pageActivo;
    private Integer idrcb;
    private boolean bmigracion;
    private Boolean perfilInvestigacion;
    private Boolean perfilAdjudicacion;

    public RcbDetalleBean() {

    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        if (util.getSessionAtributte("idArea") != null) {
            idArea = (int) util.getSessionAtributte("idArea");
        } else {
            idArea = usuarios.getIdArea().getIdArea();
        }
        bitacora = new BitacoraTareaEstatus();
        gruposSeleccionados = new ArrayList();
//        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
//        Map<String, String> parameterMap = (Map<String, String>) ctx.getRequestParameterMap();
//        if (parameterMap.get("idrcb") != null) {
//            setIdrcb(Integer.parseInt(parameterMap.get("idrcb")));
//            System.out.println("RcbDetalleBean idrcb: " + getIdrcb());
//        }
//        if (parameterMap.get("invest") != null) {
//            invest= parameterMap.get("invest");
//            System.out.println("entro investigacion: " +invest);
//        }
        idrcb = (Integer) util.getContextAtributte("idrcb");
        archivosBean.onload(1, idrcb, -1);
        perfilInvestigacion = (Boolean) util.getContextAtributte("perfilInvestigacion");
        perfilAdjudicacion = (Boolean) util.getContextAtributte("perfilAdjudicacion");

        listaTipoCompra = tipoCompraService.traeListaTipoCompra();
        listaUnidadResponsable = unidadResponsableService.traeListaUnidadesResponsables();
        listaGrupos = grupoService.traeListaGrupos();
        rcb = rcbServicio.traerRcbId(idrcb);
        for (RcbGrupo rcbGrupo : rcb.getRcbGrupoList()) {
            gruposSeleccionados.add(String.valueOf(rcbGrupo.getIdGrupo().getIdGrupo()));
        }
        filtroActivo = false;
        pageActivo = false;
        mostrarCargaArchivos = false;
        listRcbinsumosDto = new ArrayList<>();
        BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
        if (rcb.getIdUnidadResponsable() == null) {
            rcb.setIdUnidadResponsable(new UnidadResponsable());
        }
        for (RcbInsumos rcbInsumo : rcb.getRcbInsumosList()) {
            RcbInsumosViewDto rcbInsumosDto = new RcbInsumosViewDto();
            rcbInsumosDto.setRcbInsumo(rcbInsumo);
            rcbInsumosDto.setRcbInsumoSeleccionado(Boolean.TRUE);
            rcbInsumosDto.setDescripcionCorta(rcbInsumo.getIdInsumo().getDescripcion().substring(0, 20));
            List<ExistenciaReservaClaveCenadi> list = existenciaReservaClaveCenadiService.detalleExistenciaReservaClaveCenadi(rcbInsumo.getClaveInsumo());
            if (list != null) {
                ExistenciaReservaClaveCenadi ercc = list.get(0);
                if (ercc.getDisponibleDeReserva() != null) {
                    rcbInsumo.setExistencias(ercc.getDisponibleDeReserva());
                }
            }

            listRcbinsumosDto.add(rcbInsumosDto);
            tmpImporteTotal = tmpImporteTotal.add(rcbInsumo.getImporte());
        }
        rcb.setImporteTotal(tmpImporteTotal);
        rcb.setIdEstatus(estatusService.getRemisionEstatus(rcb.getIdEstatus().getIdEstatus()));
        if (rcb.getIdTipoCompra().getNombre().equals("ISSSTE")) {
            if (rcb.getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_CREADA.getValue()) { //estatus creada

                mostrarBtnLimpiar = true;
                mostrarBtnGuardar = false;
                mostrarBtnInvestMercado = true;
                mostrarMsjEsperandoIM = false;
                mostrarBtnCargarRCBanterior = true;
                mostrarBtnEliminarTodo = true;
                mostrarBtnAgregarInsumo = true;
                mostrarCampoPrecioUnitario = false;
                mostrarTextoPrecioUnitario = true;
                desHabilitarBtnModificar = false;
                desHabilitarBtnEliminar = false;
                desHabilitarBtnAgregarInsumo = false;
                desHabilitarBtnCargarRcbAnterior = false;
                desHabilitarBtnEliminarInsumos = false;
                deshabilitarselUniResponsable = false;
                deshabilitarInpGrupo = false;
                deshabilitarselTipoCompra = true;
                deshabilitarInpDestino = false;
                deshabilitarInpClave = true;
                deshabilitarFechaelaboracion = false;
                deshabilitarInpPeriod = false;
                desHabilitarOficioSuficiencia = true;
                desHabilitarNep = true;
                requiredHabilitarOficioSuficiencia = false;
                requiredHabilitarNep = false;

            } else if (rcb.getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_INVEST_MERCADO.getValue()
                    || rcb.getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_ESPERANDO_INVEST.getValue()) {
                mostrarBtnLimpiar = false;
                mostrarBtnGuardar = false;
                mostrarBtnInvestMercado = false;
                mostrarMsjEsperandoIM = true;
                lbMsjEsperandoIM = "Esperando Investigación de Mercado";
                mostrarBtnCargarRCBanterior = false;
                mostrarBtnEliminarTodo = false;
                mostrarBtnAgregarInsumo = false;
                mostrarCampoPrecioUnitario = false;
                mostrarTextoPrecioUnitario = true;
                desHabilitarBtnModificar = true;
                desHabilitarBtnEliminar = true;
                desHabilitarBtnAgregarInsumo = true;
                desHabilitarBtnCargarRcbAnterior = true;
                desHabilitarBtnEliminarInsumos = true;
                deshabilitarselUniResponsable = true;
                deshabilitarInpGrupo = true;
                deshabilitarselTipoCompra = true;
                deshabilitarInpDestino = true;
                deshabilitarInpClave = true;
                deshabilitarFechaelaboracion = true;
                deshabilitarInpPeriod = true;
                desHabilitarOficioSuficiencia = true;
                desHabilitarNep = true;
                requiredHabilitarOficioSuficiencia = false;
                requiredHabilitarNep = false;

            } else if (rcb.getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_INVEST_RESUELTA.getValue()) { //estatus resuelta

                mostrarBtnLimpiar = true;
                mostrarBtnGuardar = true;
                mostrarBtnInvestMercado = false;
                mostrarMsjEsperandoIM = false;
                mostrarBtnCargarRCBanterior = false;
                mostrarBtnEliminarTodo = false;
                mostrarBtnAgregarInsumo = false;
                mostrarCampoPrecioUnitario = false;
                mostrarTextoPrecioUnitario = true;
                desHabilitarBtnModificar = false;
                desHabilitarBtnEliminar = true;
                desHabilitarBtnAgregarInsumo = true;
                desHabilitarBtnCargarRcbAnterior = true;
                desHabilitarBtnEliminarInsumos = true;
                deshabilitarselUniResponsable = true;
                deshabilitarInpGrupo = true;
                deshabilitarselTipoCompra = true;
                deshabilitarInpDestino = true;
                deshabilitarInpClave = true;
                deshabilitarFechaelaboracion = true;
                deshabilitarInpPeriod = true;
                desHabilitarOficioSuficiencia = true;
                desHabilitarNep = true;
                requiredHabilitarOficioSuficiencia = true;
                requiredHabilitarNep = true;
                mostrarBtnCompletarRcb = false;

            } else if (rcb.getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_DOC_PENDIENTE.getValue()) { //estatus resuelta

                mostrarBtnLimpiar = false;
                mostrarBtnGuardar = false;
                mostrarBtnInvestMercado = false;
                mostrarBtnCompletarRcb = true;
                mostrarMsjEsperandoIM = false;
                mostrarBtnCargarRCBanterior = false;
                mostrarBtnEliminarTodo = false;
                mostrarBtnAgregarInsumo = false;
                mostrarCampoPrecioUnitario = false;
                mostrarTextoPrecioUnitario = true;
                desHabilitarBtnModificar = true;
                desHabilitarBtnEliminar = true;
                desHabilitarBtnAgregarInsumo = true;
                desHabilitarBtnCargarRcbAnterior = true;
                desHabilitarBtnEliminarInsumos = true;
                deshabilitarselUniResponsable = true;
                deshabilitarInpGrupo = true;
                deshabilitarselTipoCompra = true;
                deshabilitarInpDestino = true;
                deshabilitarInpClave = false;
                deshabilitarFechaelaboracion = true;
                deshabilitarInpPeriod = true;
                desHabilitarOficioSuficiencia = false;
                desHabilitarNep = false;
                requiredHabilitarOficioSuficiencia = true;
                requiredHabilitarNep = true;
                mostrarBtnImprimirRcb = true;
                mostrarCargaArchivos = true;

            } else if (rcb.getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_COMPLETA.getValue()) {
                mostrarBtnLimpiar = false;
                mostrarBtnGuardar = false;
                mostrarBtnInvestMercado = false;
                mostrarBtnCompletarRcb = false;
                mostrarMsjEsperandoIM = true;
                lbMsjEsperandoIM = "RCB Completa";
                mostrarBtnCargarRCBanterior = false;
                mostrarBtnEliminarTodo = false;
                mostrarBtnAgregarInsumo = false;
                mostrarCampoPrecioUnitario = false;
                mostrarTextoPrecioUnitario = true;
                desHabilitarBtnModificar = true;
                desHabilitarBtnEliminar = true;
                desHabilitarBtnAgregarInsumo = true;
                desHabilitarBtnCargarRcbAnterior = true;
                desHabilitarBtnEliminarInsumos = true;
                deshabilitarselUniResponsable = true;
                deshabilitarInpGrupo = true;
                deshabilitarselTipoCompra = true;
                deshabilitarInpDestino = true;
                deshabilitarInpClave = true;
                deshabilitarFechaelaboracion = true;
                deshabilitarInpPeriod = true;
                desHabilitarOficioSuficiencia = true;
                desHabilitarNep = true;
                requiredHabilitarOficioSuficiencia = true;
                requiredHabilitarNep = true;
                mostrarBtnImprimirRcb = true;
                mostrarCargaArchivos = true;

            } else {

                mostrarBtnLimpiar = false;
                mostrarBtnGuardar = false;
                mostrarBtnInvestMercado = false;
                mostrarMsjEsperandoIM = true;
                lbMsjEsperandoIM = "RCB Modificada";
                mostrarBtnCargarRCBanterior = false;
                mostrarBtnEliminarTodo = false;
                mostrarBtnAgregarInsumo = false;
                mostrarCampoPrecioUnitario = false;
                mostrarTextoPrecioUnitario = true;
                desHabilitarBtnModificar = true;
                desHabilitarBtnEliminar = true;
                desHabilitarBtnAgregarInsumo = true;
                desHabilitarBtnCargarRcbAnterior = true;
                desHabilitarBtnEliminarInsumos = true;
                deshabilitarselUniResponsable = true;
                deshabilitarInpGrupo = true;
                deshabilitarselTipoCompra = true;
                deshabilitarInpDestino = true;
                deshabilitarInpClave = true;
                deshabilitarFechaelaboracion = true;
                deshabilitarInpPeriod = true;
                desHabilitarOficioSuficiencia = true;
                desHabilitarNep = true;
                requiredHabilitarOficioSuficiencia = true;
                requiredHabilitarNep = true;
                mostrarCargaArchivos = true;
                mostrarBtnImprimirRcb = false;
                System.out.println("estatus17");

            }

            if (perfilInvestigacion != null) {
                if (perfilInvestigacion && rcb.getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_INVEST_MERCADO.getValue()) {
                    desHabilitarBtnModificar = false;
                    mostrarBtnAceptarIm = true;
                    mostrarMsjEsperandoIM = false;

                } else if (perfilInvestigacion && rcb.getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_ESPERANDO_INVEST.getValue()) {
                    desHabilitarBtnModificar = false;
                    mostrarBtnAceptarIm = false;
                    mostrarMsjEsperandoIM = false;
                    mostrarBtnImResuelta = true;

                } else if (perfilInvestigacion && rcb.getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_INVEST_RESUELTA.getValue()) {
                    desHabilitarBtnModificar = false;
                    mostrarBtnAceptarIm = false;
                    mostrarMsjEsperandoIM = false;
                    mostrarBtnImResuelta = false;
                    mostrarBtnCompletarRcb = true;

                    ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
                    String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
                    try {
                        ctx.redirect(ctxPath + "/vistas/rcb/rcbConsultaInvestigacionMercado.xhtml");
                        util.setContextAtributte("perfilInvestigacion", true);
                    } catch (IOException ex) {
                        Logger.getLogger(RcbBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } else {
            if (rcb.getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_CREADA.getValue()) { //estatus creada
                mostrarBtnLimpiar = true;
                mostrarBtnGuardar = true;
                mostrarBtnInvestMercado = false;
                mostrarMsjEsperandoIM = false;
                mostrarBtnCargarRCBanterior = true;
                mostrarBtnEliminarTodo = true;
                mostrarBtnAgregarInsumo = true;
                mostrarCampoPrecioUnitario = false;
                mostrarTextoPrecioUnitario = true;
                desHabilitarBtnModificar = false;
                desHabilitarBtnEliminar = false;
                desHabilitarBtnAgregarInsumo = false;
                desHabilitarBtnCargarRcbAnterior = false;
                desHabilitarBtnEliminarInsumos = false;
                deshabilitarselUniResponsable = false;
                deshabilitarInpGrupo = false;
                deshabilitarselTipoCompra = true;
                deshabilitarInpDestino = false;
                deshabilitarInpClave = true;
                deshabilitarFechaelaboracion = false;
                deshabilitarInpPeriod = false;
                desHabilitarOficioSuficiencia = true;
                desHabilitarNep = true;
                requiredHabilitarOficioSuficiencia = false;
                requiredHabilitarNep = false;

            } else if (rcb.getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_DOC_PENDIENTE.getValue()) { //estatus documentacion pendiente
                mostrarBtnLimpiar = false;
                mostrarBtnGuardar = true;
                mostrarBtnInvestMercado = false;
                mostrarMsjEsperandoIM = false;
                lbMsjEsperandoIM = "RCB Completa";
                mostrarBtnCargarRCBanterior = false;
                mostrarBtnEliminarTodo = false;
                mostrarBtnAgregarInsumo = false;
                mostrarCampoPrecioUnitario = false;
                mostrarTextoPrecioUnitario = true;
                desHabilitarBtnModificar = true;
                desHabilitarBtnEliminar = true;
                desHabilitarBtnAgregarInsumo = true;
                desHabilitarBtnCargarRcbAnterior = true;
                desHabilitarBtnEliminarInsumos = true;
                deshabilitarselUniResponsable = true;
                deshabilitarInpGrupo = true;
                deshabilitarselTipoCompra = true;
                deshabilitarInpDestino = true;
                deshabilitarInpClave = false;
                deshabilitarFechaelaboracion = true;
                deshabilitarInpPeriod = true;
                desHabilitarOficioSuficiencia = false;
                desHabilitarNep = false;
                requiredHabilitarOficioSuficiencia = true;
                requiredHabilitarNep = true;
                mostrarCargaArchivos = true;
                mostrarBtnImprimirRcb = true;
                System.out.println("estatus15");
            } else if (rcb.getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_COMPLETA.getValue()) {
                mostrarBtnLimpiar = false;
                mostrarBtnGuardar = false;
                mostrarBtnInvestMercado = false;
                mostrarMsjEsperandoIM = true;
                lbMsjEsperandoIM = "RCB Completa";
                mostrarBtnCargarRCBanterior = false;
                mostrarBtnEliminarTodo = false;
                mostrarBtnAgregarInsumo = false;
                mostrarCampoPrecioUnitario = false;
                mostrarTextoPrecioUnitario = true;
                desHabilitarBtnModificar = true;
                desHabilitarBtnEliminar = true;
                desHabilitarBtnAgregarInsumo = true;
                desHabilitarBtnCargarRcbAnterior = true;
                desHabilitarBtnEliminarInsumos = true;
                deshabilitarselUniResponsable = true;
                deshabilitarInpGrupo = true;
                deshabilitarselTipoCompra = true;
                deshabilitarInpDestino = true;
                deshabilitarInpClave = true;
                deshabilitarFechaelaboracion = true;
                deshabilitarInpPeriod = true;
                desHabilitarOficioSuficiencia = true;
                desHabilitarNep = true;
                requiredHabilitarOficioSuficiencia = true;
                requiredHabilitarNep = true;
                mostrarCargaArchivos = true;
                mostrarBtnImprimirRcb = true;
                System.out.println("estatus16");

            } else {
                mostrarBtnLimpiar = false;
                mostrarBtnGuardar = false;
                mostrarBtnInvestMercado = false;
                mostrarMsjEsperandoIM = true;
                lbMsjEsperandoIM = "RCB Modificada";
                mostrarBtnCargarRCBanterior = false;
                mostrarBtnEliminarTodo = false;
                mostrarBtnAgregarInsumo = false;
                mostrarCampoPrecioUnitario = false;
                mostrarTextoPrecioUnitario = true;
                desHabilitarBtnModificar = true;
                desHabilitarBtnEliminar = true;
                desHabilitarBtnAgregarInsumo = true;
                desHabilitarBtnCargarRcbAnterior = true;
                desHabilitarBtnEliminarInsumos = true;
                deshabilitarselUniResponsable = true;
                deshabilitarInpGrupo = true;
                deshabilitarselTipoCompra = true;
                deshabilitarInpDestino = true;
                deshabilitarInpClave = true;
                deshabilitarFechaelaboracion = true;
                deshabilitarInpPeriod = true;
                desHabilitarOficioSuficiencia = true;
                desHabilitarNep = true;
                requiredHabilitarOficioSuficiencia = true;
                requiredHabilitarNep = true;
                mostrarCargaArchivos = true;
                mostrarBtnImprimirRcb = false;
                System.out.println("estatus17");
            }
        }

        if (perfilAdjudicacion) {
            System.out.println("entro a perfil adjudicacion");
            mostrarBtnLimpiar = false;
            mostrarBtnGuardar = true;
            mostrarBtnInvestMercado = false;
            mostrarBtnCompletarRcb = false;
            mostrarMsjEsperandoIM = false;
            lbMsjEsperandoIM = "RCB Completa";
            mostrarBtnCargarRCBanterior = false;
            mostrarBtnEliminarTodo = false;
            mostrarBtnAgregarInsumo = false;
            mostrarCampoPrecioUnitario = false;
            mostrarTextoPrecioUnitario = true;
            desHabilitarBtnModificar = false;
            desHabilitarBtnEliminar = true;
            desHabilitarBtnAgregarInsumo = true;
            desHabilitarBtnCargarRcbAnterior = true;
            desHabilitarBtnEliminarInsumos = true;
            deshabilitarselUniResponsable = true;
            deshabilitarInpGrupo = true;
            deshabilitarselTipoCompra = true;
            deshabilitarInpDestino = true;
            deshabilitarInpClave = true;
            deshabilitarFechaelaboracion = true;
            deshabilitarInpPeriod = true;
            desHabilitarOficioSuficiencia = true;
            desHabilitarNep = true;
            requiredHabilitarOficioSuficiencia = true;
            requiredHabilitarNep = true;
            mostrarBtnImprimirRcb = false;
            mostrarCargaArchivos = true;
        }
    }

    public void onClickNuevoRcb() throws IOException {
        System.out.println("onClickNuevoRcb");
    }

    public void onTabChange(TabChangeEvent event) {
        Tab activeTab = event.getTab();
    }

    public void filterListener(FilterEvent filterEvent) {
        filtroActivo = true;
    }

    public void pageListener(PageEvent pageEvent) {
        pageActivo = true;
    }

    public void limpiarFrmNuevo() {
        RequestContext.getCurrentInstance().reset("formRCB:pnlRCB");
        RequestContext.getCurrentInstance().reset("formRCB:tablaRcbInsumo");
        init();
    }

    public void eliminarInsumos() {
        List<RcbInsumos> listRcbInsumos = rcbInsumosService.getListRCBInsumosByidRCB(rcb.getIdRcb());
        for (RcbInsumos rcbInsumo : listRcbInsumos) {
            insumosSolicitudesService.borrarInsumosRcbSolicitudPorRcbInsumo(rcbInsumo);
            bitacora.setFecha(new Date());
            bitacora.setIdEstatus(rcbInsumo.getIdInsumo().getIdInsumo());
            bitacora.setDescripcion(bitacoraUtil.detalleEliminarBitacora("insumo " + rcbInsumo.getIdInsumo().getIdInsumo()));
            bitacora.setIdUsuarios(usuarios.getIdUsuario());
            bitacora.setIdTareaId(rcbInsumo.getIdInsumo().getIdInsumo());
            bitacoraService.guardarEnBitacora(bitacora);
        }
        rcbInsumosService.borrarInsumosRCBPorIdRcb(rcb.getIdRcb());
        listRcbinsumosDto.clear();
        listRcbinsumosDtoFilter.clear();
        rcb.setImporteTotal(null);
    }

    public void eliminarInsumo(RcbInsumosViewDto rcbInsumo) {
        insumosSolicitudesService.borrarInsumosRcbSolicitudPorRcbInsumo(rcbInsumo.getRcbInsumo());
        rcbInsumosService.borrarRcbInsumo(rcbInsumo.getRcbInsumo());
        boolean remove = listRcbinsumosDto.remove(rcbInsumo);
        BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
        for (Iterator<RcbInsumosViewDto> iterator = listRcbinsumosDto.iterator(); iterator.hasNext();) {
            RcbInsumosViewDto next = iterator.next();
            tmpImporteTotal = tmpImporteTotal.add(next.getRcbInsumo().getImporte());
        }

        if (filtroActivo && pageActivo) {
            RequestContext.getCurrentInstance().execute("PF('wvTablaRcbInsumo').filter()");
        } else if (filtroActivo) {
            RequestContext.getCurrentInstance().execute("PF('wvTablaRcbInsumo').filter()");
        }
        rcbInsumo = null;

        rcb.setImporteTotal(tmpImporteTotal);
    }

    public void cargarRCBAnterior() {
        Integer idRcbAnterior = rcbInsumosService.traerUltimaRcbPorArea(idArea, rcb.getIdTipoCompra().getIdTipoCompra());
        if (idRcbAnterior == null || idRcbAnterior == 0) {
            mensaje.mensaje("No existe un RCB con estatus completo registrado en el sistema", "amarillo");
        } else {
            if (rcb.getIdRcb() > 0) {
                rcbInsumosService.borrarInsumosRCBPorIdRcb(rcb.getIdRcb());
                List<RcbInsumos> listRcbInsumosAnterior = rcbServicio.traerRcbId(idRcbAnterior).getRcbInsumosList();
                for (RcbInsumos rcbInsumo : listRcbInsumosAnterior) {
                    rcbInsumo.setIdRcb(rcb);
                    rcbInsumosService.guardarInsumosRCBPorIdRCB(rcbInsumo);
                }
                BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
                List<RcbInsumos> listRcbInsumosActual = rcbServicio.traerRcbId(rcb.getIdRcb()).getRcbInsumosList();
                listRcbinsumosDto.clear();
                for (RcbInsumos rcbInsumo : listRcbInsumosActual) {
                    RcbInsumosViewDto rcbInsumosDto = new RcbInsumosViewDto();
                    rcbInsumosDto.setRcbInsumo(rcbInsumo);
                    rcbInsumosDto.setRcbInsumoSeleccionado(Boolean.TRUE);
                    listRcbinsumosDto.add(rcbInsumosDto);
                    tmpImporteTotal = tmpImporteTotal.add(rcbInsumo.getImporte());
                }
                rcb.setImporteTotal(tmpImporteTotal);
            }
        }
    }

    public void seleccionaInsumo() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("includeViewParams", true);
        options.put("width", "1020");
        options.put("height", "400");
        options.put("contentWidth", "1020");
        options.put("contentHeight", "400");
        RequestContext.getCurrentInstance().openDialog("/vistas/rcb/buscaInsumo.xhtml", options, null);
    }

    public void insumoSeleccionado(SelectEvent event) {
        Boolean existeInsumo = false;
        Insumos insumo = (Insumos) event.getObject();
        for (RcbInsumosViewDto rcbtdo : listRcbinsumosDto) {
            if (rcbtdo.getRcbInsumo().getIdInsumo().equals(insumo)) {
                existeInsumo = true;
            }
        }
        if (existeInsumo) {
            mensaje.mensaje("El insumo no puede ser agregado, se encuentra dentro de la lista de insumos", "amarillo");
        } else {
            RcbInsumos rcbInsumos = new RcbInsumos();
            rcbInsumos.setIdInsumo(insumo);
            rcbInsumos.setExistencias(0);
            rcbInsumos.setConsumoPromedio(0);
            rcbInsumos.setCantidadPiezas(0);
            rcbInsumos.setPrecioUnitario(BigDecimal.ZERO);
            rcbInsumos.setImporte(BigDecimal.ZERO);
            rcbInsumos.setClaveInsumo(insumo.getClave());
            rcbInsumos.setDescripcionInsumo(insumo.getDescripcion());
            rcbInsumos.setIdClasificacion(insumo.getIdClasificacion());
            rcbInsumos.setIdGrupo(insumo.getIdGrupo());
            rcbInsumos.setIdGrupoTerapeutico(insumo.getIdGrupoTerapeutico());
            rcbInsumos.setIdNivel(insumo.getIdNivel());
            rcbInsumos.setIdUnidadPieza(insumo.getIdUnidadPieza());
            rcbInsumos.setIndicacionesInsumo(insumo.getIndicaciones());
            rcbInsumos.setPartidaPresupuestalInsumo(insumo.getPartidaPresupuestal());
            rcbInsumos.setViaAdministracionDosisInsumo(insumo.getViaAdministracionDosis());
            rcbInsumos.setIdRcb(rcb);
            rcbInsumos = rcbInsumosService.guardarInsumosRCBPorIdRCB(rcbInsumos);
            RcbInsumosViewDto rcbInsumosDto = new RcbInsumosViewDto();
            rcbInsumosDto.setRcbInsumo(rcbInsumos);
            rcbInsumosDto.setRcbInsumoSeleccionado(Boolean.TRUE);
            rcbInsumosDto.setDescripcionCorta(insumo.getDescripcion().substring(0, 20));
            System.out.println("setDescripcionCorta" + rcbInsumosDto.getDescripcionCorta());
            listRcbinsumosDto.add(rcbInsumosDto);
        }
    }

    public void seleccionaInsumoAmodificar(RcbInsumosViewDto rcbInsumo) {
        Map<String, Object> options = new HashMap<String, Object>();
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> values = new ArrayList<String>();
        values.add(String.valueOf(rcbInsumo.getRcbInsumo().getIdRcbInsumos()));
        params.put("idRcbInsumo", values);
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("includeViewParams", true);
        options.put("width", "1020");
        options.put("height", "400");
        options.put("contentWidth", "1020");
        options.put("contentHeight", "400");
        RequestContext.getCurrentInstance().openDialog("/vistas/rcb/rcbDetalleInsumo.xhtml", options, params);
    }

    public void insumoModificadoSeleccionado(SelectEvent event) {
        RcbInsumos rcbInsumos = (RcbInsumos) event.getObject();
        RcbInsumosViewDto rcbInsumosDto = new RcbInsumosViewDto();
        rcbInsumosDto.setRcbInsumo(rcbInsumos);
        rcbInsumosDto.setRcbInsumoSeleccionado(Boolean.TRUE);
        BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
        for (Iterator<RcbInsumosViewDto> iterator = listRcbinsumosDto.iterator(); iterator.hasNext();) {
            RcbInsumosViewDto next = iterator.next();
            if (next.getRcbInsumo().getIdRcbInsumos() == rcbInsumosDto.getRcbInsumo().getIdRcbInsumos()) {
                System.out.println("son iguales: ");
                System.out.println("next: " + next.getRcbInsumo().getCantidadPiezas());
                System.out.println("next: " + rcbInsumosDto.getRcbInsumo().getCantidadPiezas());
                next.setRcbInsumo(rcbInsumos);
                next.setRcbInsumoSeleccionado(Boolean.TRUE);
            }
            tmpImporteTotal = tmpImporteTotal.add(next.getRcbInsumo().getImporte());
        }
        rcb.setImporteTotal(tmpImporteTotal);

    }

    public String goToNextpage() {
        return "rcb";
    }

    public void guardarRCB() {
        rcb.setActivo(1);
        if (rcb.getIdUnidadResponsable().getIdUnidadResponsable() == null) {
            rcb.setIdUnidadResponsable(null);
        }
        listaRcbGrupo = new ArrayList<>();
        for (String cadena : gruposSeleccionados) {
            RcbGrupo rcbGrupo = new RcbGrupo();
            rcbGrupo.setIdRcb(rcb);
            rcbGrupo.setIdGrupo(new Grupo(Integer.parseInt(cadena)));
            listaRcbGrupo.add(rcbGrupo);
        }
        Boolean importesCorrectos = true;
        Boolean documentosCargados = true;
        System.out.println("numeroOficio--->" + rcb.getNumeroOficio());
        if (listRcbinsumosDto.size() > 0) { //valida lista de insumos
            if (validaGrupos()) {
                if (rcb.getIdTipoCompra().getNombre().equals("ISSSTE")) {
                    switch (rcb.getIdEstatus().getIdEstatus()) {
                        case 11:
                            for (RcbInsumosViewDto rcbinsumoValida : listRcbinsumosDto) { //valida importes iguales a cero dentro de lista de insumos
                                if (rcbinsumoValida.getRcbInsumo().getCantidadPiezas() == 0) {
                                    importesCorrectos = false;
                                    break;
                                } else {
                                    if (rcbinsumoValida.getRcbInsumo().getInsumosRcbSolicitudInvestigacionMercadoList().size() == 0) {
                                        importesCorrectos = false;
                                        break;
                                    }
                                }
                            }
                            if (importesCorrectos) {
                                rcb.setIdEstatus(new Estatus(PlaneacionEstatusEnum.RCB_INVEST_MERCADO.getValue())); //se asigna estatus investigacionSolicitada   
                            }

                            break;
                        case 12:
                            rcb.setIdEstatus(new Estatus(PlaneacionEstatusEnum.RCB_ESPERANDO_INVEST.getValue())); //se asigna estatus esperandoinvestigacion
                            break;
                        case 13:
                            rcb.setIdEstatus(new Estatus(PlaneacionEstatusEnum.RCB_INVEST_RESUELTA.getValue())); //se asigna estatus investigacion resuelta
                            break;
                        case 14:
                            for (RcbInsumosViewDto rcbinsumoValida : listRcbinsumosDto) { //valida importes iguales a cero dentro de lista de insumos
                                if (rcbinsumoValida.getRcbInsumo().getImporte().compareTo(BigDecimal.ZERO) == 0) {
                                    importesCorrectos = false;
                                    break;
                                }
                            }
                            if (importesCorrectos) {
                                rcb.setIdEstatus(new Estatus(15)); //se asigna estatus investigacion resuelta
                            }
                            break;
                        case 15:
                            List<RespositorioDocumentos> listDocumentosCargados = respositorioDocumentosService.obtenerByIdProcesoIdTarea(rcb.getIdRcb(), PlaneacionEstatusEnum.ID_MODULO_RCB.getValue());
                            if (listDocumentosCargados != null) {
                                if (listDocumentosCargados.size() < 3) {
                                    documentosCargados = false;
                                }
                            } else {
                                documentosCargados = false;
                            }
                            if (documentosCargados) {
                                rcb.setIdEstatus(new Estatus(PlaneacionEstatusEnum.RCB_COMPLETA.getValue())); //se asigna estatus completa
                            }
                            break;
                        case 16:
                            List<RespositorioDocumentos> listDocumentosCargadosM = respositorioDocumentosService.obtenerByIdProcesoIdTarea(rcb.getIdRcb(), PlaneacionEstatusEnum.ID_MODULO_RCB.getValue());
                            if (listDocumentosCargadosM != null) {
                                if (listDocumentosCargadosM.size() < 4) {
                                    documentosCargados = false;
                                }
                            } else {
                                documentosCargados = false;
                            }
                            if (documentosCargados) {
                                rcb.setIdEstatus(new Estatus(PlaneacionEstatusEnum.RCB_MODIFICADA.getValue())); //se asigna estatus modificada
                            }
                            break;
                        default:
                            mensaje.mensaje("Grave: Error al cambiar el estatus", "rojo");
                            importesCorrectos = false;
                    }
                } else {

                    switch (rcb.getIdEstatus().getIdEstatus()) {
                        case 11:
                            for (RcbInsumosViewDto rcbinsumoValida : listRcbinsumosDto) { //valida importes iguales a cero dentro de lista de insumos
                                if (rcbinsumoValida.getRcbInsumo().getImporte().compareTo(BigDecimal.ZERO) == 0) {
                                    importesCorrectos = false;
                                    break;
                                }
                            }
                            if (importesCorrectos) {
                                rcb.setIdEstatus(new Estatus(PlaneacionEstatusEnum.RCB_DOC_PENDIENTE.getValue())); //se asigna estatus documentacion Pendiente
                            }
                            break;
                        case 15:
                            List<RespositorioDocumentos> listDocumentosCargados = respositorioDocumentosService.obtenerByIdProcesoIdTarea(rcb.getIdRcb(), PlaneacionEstatusEnum.ID_MODULO_RCB.getValue());
                            if (listDocumentosCargados != null) {
                                if (listDocumentosCargados.size() < 3) {
                                    documentosCargados = false;
                                }
                            } else {
                                documentosCargados = false;
                            }
                            if (documentosCargados) {
                                rcb.setIdEstatus(new Estatus(PlaneacionEstatusEnum.RCB_COMPLETA.getValue())); //se asigna estatus completa
                            }
                            break;
                        case 16:
                            List<RespositorioDocumentos> listDocumentosCargadosM = respositorioDocumentosService.obtenerByIdProcesoIdTarea(rcb.getIdRcb(), PlaneacionEstatusEnum.ID_MODULO_RCB.getValue());
                            if (listDocumentosCargadosM != null) {
                                if (listDocumentosCargadosM.size() < 4) {
                                    documentosCargados = false;
                                }
                            } else {
                                documentosCargados = false;
                            }
                            if (documentosCargados) {
                                rcb.setIdEstatus(new Estatus(PlaneacionEstatusEnum.RCB_MODIFICADA.getValue())); //se asigna estatus modificada
                            }
                            break;
                        default:
                            mensaje.mensaje("Grave: Error al cambiar el estatus", "rojo");
                            importesCorrectos = false;
                    }

                }

                if (importesCorrectos) {
                    if (documentosCargados) {
                        rcbServicio.actualizaTablaRCB(rcb);
                        rcbGrupoService.deleteRcbGruposByIdRcb(rcb.getIdRcb());
                        for (RcbGrupo RcbGrupo1 : listaRcbGrupo) {
                            RcbGrupo1.setIdRcb(rcb);
                            rcbGrupoService.save(RcbGrupo1);
                        }
                        bitacora.setFecha(new Date());
                        bitacora.setIdEstatus(rcb.getIdEstatus().getIdEstatus());
                        bitacora.setDescripcion(bitacoraUtil.detalleGuardaroBitacora("rcb " + rcb.getIdRcb()));
                        bitacora.setIdUsuarios(usuarios.getIdUsuario());
                        bitacora.setIdTareaId(rcb.getIdRcb());
                        bitacora.setIdModulos(PlaneacionEstatusEnum.ID_MODULO_RCB.getValue());
                        bitacoraService.guardarEnBitacora(bitacora);
                        mensaje.mensaje(mensaje.datos_guardados, "verde");
                        init();
                    } else {
                        mensaje.mensaje("Debe cargar la evidencia documental", "rojo");
                    }
                } else {
                    mensaje.mensaje("Las cantidades, los precios por unidad y el importe "
                            + "de los insumos deben ser mayor a cero, verifique las cantidades.", "rojo");
                    mensaje.mensaje("En caso de un tipo de compra ISSSTE debe ingresar la información requerida para la investigación de mercado", "rojo");
                }
            } else {
                mensaje.mensaje("El grupo de los insumos no coincide con el grupo de RCB,", "rojo");
            }
        } else {
            mensaje.mensaje("La lista de insumos no puede estar vacía", "rojo");
        }
        if (rcb.getIdUnidadResponsable() == null) {
            rcb.setIdUnidadResponsable(new UnidadResponsable());
        }
    }

    public Boolean validaGrupos() {
        List<String> al = new ArrayList<>();
        Set<String> hs = new HashSet<>();
        for (RcbInsumosViewDto rcbinsumosDto1 : listRcbinsumosDto) {
            al.add(String.valueOf(rcbinsumosDto1.getRcbInsumo().getIdInsumo().getIdGrupo().getIdGrupo()));
        }
        hs.addAll(al);
        al.clear();
        al.addAll(hs);

        final Set<String> s1 = new HashSet<>(al);
        final Set<String> s2 = new HashSet<>(gruposSeleccionados);

        return s1.equals(s2);
    }

    JasperPrint jasperPrint;

    public void imprimirRcb() throws JRException, IOException {
        List<RcbDTO> listaImprimir = new ArrayList();
        for (RcbInsumosViewDto item : listRcbinsumosDto) {
            RcbDTO rcbDTO = new RcbDTO();

            rcbDTO.setClaveInsumo(item.getRcbInsumo().getIdInsumo().getClave());
            rcbDTO.setDescripcion(item.getRcbInsumo().getIdInsumo().getDescripcion());
            rcbDTO.setExistencias(item.getRcbInsumo().getExistencias());
            rcbDTO.setConsumoPromedio(item.getRcbInsumo().getConsumoPromedio());
            rcbDTO.setMesesDeCobertura(item.getRcbInsumo().getMesesCobertura());
            rcbDTO.setPresentacionInsumo(item.getRcbInsumo().getIdInsumo().getIdUnidadPieza().getDescripcion());
            rcbDTO.setCantidadSolicitada(item.getRcbInsumo().getCantidadPiezas());
            rcbDTO.setPrecioUnitario(item.getRcbInsumo().getPrecioUnitario());
            rcbDTO.setImporte(item.getRcbInsumo().getImporte());
            rcbDTO.setUnidadResponsable(item.getRcbInsumo().getIdRcb().getIdUnidadResponsable().getNombre());
            String strGrupo = "";
            for (RcbGrupo rcbGrupoList : rcb.getRcbGrupoList()) {
                strGrupo += rcbGrupoList.getIdGrupo().getGrupo() + ",";
            }
            if (!strGrupo.equals("")) {
                rcbDTO.setGrupo(strGrupo.substring(0, strGrupo.length() - 1));
            }
            rcbDTO.setDestino(item.getRcbInsumo().getIdRcb().getDestino());
            rcbDTO.setClave(item.getRcbInsumo().getIdRcb().getClave());
            rcbDTO.setFechaElaboracion(item.getRcbInsumo().getIdRcb().getFechaElaboracion());
            rcbDTO.setPeriodo(item.getRcbInsumo().getIdRcb().getPeriodo());
            listaImprimir.add(rcbDTO);
        }

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaImprimir);

        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/reporteRcb.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
//        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        JRXlsxExporter docxExporter = new JRXlsxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();

        FacesContext.getCurrentInstance().responseComplete();
        mensaje.mensaje("Impresión realizada", "verde");
    }

    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "PrimeFaces Rocks."));
    }

    public List<RcbInsumosViewDto> getListRcbinsumosDto() {
        return listRcbinsumosDto;
    }

    public void setListRcbinsumosDto(List<RcbInsumosViewDto> listRcbinsumosDto) {
        this.listRcbinsumosDto = listRcbinsumosDto;
    }

    public List<TipoCompra> getListaTipoCompra() {
        return listaTipoCompra;
    }

    public void setListaTipoCompra(List<TipoCompra> listaTipoCompra) {
        this.listaTipoCompra = listaTipoCompra;
    }

    public List<UnidadResponsable> getListaUnidadResponsable() {
        return listaUnidadResponsable;
    }

    public void setListaUnidadResponsable(List<UnidadResponsable> listaUnidadResponsable) {
        this.listaUnidadResponsable = listaUnidadResponsable;
    }

    public Rcb getRcb() {
        return rcb;
    }

    public void setRcb(Rcb rcb) {
        this.rcb = rcb;
    }

    public Boolean getDesHabilitarNep() {
        return desHabilitarNep;
    }

    public void setDesHabilitarNep(Boolean desHabilitarNep) {
        this.desHabilitarNep = desHabilitarNep;
    }

    public Boolean getDesHabilitarOficioSuficiencia() {
        return desHabilitarOficioSuficiencia;
    }

    public void setDesHabilitarOficioSuficiencia(Boolean desHabilitarOficioSuficiencia) {
        this.desHabilitarOficioSuficiencia = desHabilitarOficioSuficiencia;
    }

    public Boolean getDesHabilitarBtnModificar() {
        return desHabilitarBtnModificar;
    }

    public void setDesHabilitarBtnModificar(Boolean desHabilitarBtnModificar) {
        this.desHabilitarBtnModificar = desHabilitarBtnModificar;
    }

    public Boolean getDesHabilitarBtnEliminar() {
        return desHabilitarBtnEliminar;
    }

    public void setDesHabilitarBtnEliminar(Boolean desHabilitarBtnEliminar) {
        this.desHabilitarBtnEliminar = desHabilitarBtnEliminar;
    }

    public Boolean getMostrarBtnAgregarInsumo() {
        return mostrarBtnAgregarInsumo;
    }

    public void setMostrarBtnAgregarInsumo(Boolean mostrarBtnAgregarInsumo) {
        this.mostrarBtnAgregarInsumo = mostrarBtnAgregarInsumo;
    }

    public Boolean getMostrarCampoPrecioUnitario() {
        return mostrarCampoPrecioUnitario;
    }

    public void setMostrarCampoPrecioUnitario(Boolean mostrarCampoPrecioUnitario) {
        this.mostrarCampoPrecioUnitario = mostrarCampoPrecioUnitario;
    }

    public Boolean getMostrarTextoPrecioUnitario() {
        return mostrarTextoPrecioUnitario;
    }

    public void setMostrarTextoPrecioUnitario(Boolean mostrarTextoPrecioUnitario) {
        this.mostrarTextoPrecioUnitario = mostrarTextoPrecioUnitario;
    }

    public Boolean getMostrarBtnCargarRCBanterior() {
        return mostrarBtnCargarRCBanterior;
    }

    public void setMostrarBtnCargarRCBanterior(Boolean mostrarBtnCargarRCBanterior) {
        this.mostrarBtnCargarRCBanterior = mostrarBtnCargarRCBanterior;
    }

    public Boolean getMostrarBtnEliminarTodo() {
        return mostrarBtnEliminarTodo;
    }

    public void setMostrarBtnEliminarTodo(Boolean mostrarBtnEliminarTodo) {
        this.mostrarBtnEliminarTodo = mostrarBtnEliminarTodo;
    }

    public Integer getIdrcb() {
        return idrcb;
    }

    public void setIdrcb(Integer idrcb) {
        this.idrcb = idrcb;
    }

    public String getBtnGuardarValue() {
        return btnGuardarValue;
    }

    public void setBtnGuardarValue(String btnGuardarValue) {
        this.btnGuardarValue = btnGuardarValue;
    }

    public Boolean getMostrarBtnLimpiar() {
        return mostrarBtnLimpiar;
    }

    public void setMostrarBtnLimpiar(Boolean mostrarBtnLimpiar) {
        this.mostrarBtnLimpiar = mostrarBtnLimpiar;
    }

    public Boolean getMostrarBtnGuardar() {
        return mostrarBtnGuardar;
    }

    public void setMostrarBtnGuardar(Boolean mostrarBtnGuardar) {
        this.mostrarBtnGuardar = mostrarBtnGuardar;
    }

    public Boolean getMostrarMsjEsperandoIM() {
        return mostrarMsjEsperandoIM;
    }

    public void setMostrarMsjEsperandoIM(Boolean mostrarMsjEsperandoIM) {
        this.mostrarMsjEsperandoIM = mostrarMsjEsperandoIM;
    }

    public Boolean getDesHabilitarBtnCargarRcbAnterior() {
        return desHabilitarBtnCargarRcbAnterior;
    }

    public void setDesHabilitarBtnCargarRcbAnterior(Boolean desHabilitarBtnCargarRcbAnterior) {
        this.desHabilitarBtnCargarRcbAnterior = desHabilitarBtnCargarRcbAnterior;
    }

    public Boolean getDesHabilitarBtnEliminarInsumos() {
        return desHabilitarBtnEliminarInsumos;
    }

    public void setDesHabilitarBtnEliminarInsumos(Boolean desHabilitarBtnEliminarInsumos) {
        this.desHabilitarBtnEliminarInsumos = desHabilitarBtnEliminarInsumos;
    }

    public Boolean getDesHabilitarBtnAgregarInsumo() {
        return desHabilitarBtnAgregarInsumo;
    }

    public void setDesHabilitarBtnAgregarInsumo(Boolean desHabilitarBtnAgregarInsumo) {
        this.desHabilitarBtnAgregarInsumo = desHabilitarBtnAgregarInsumo;
    }

    public Boolean getDeshabilitarselUniResponsable() {
        return deshabilitarselUniResponsable;
    }

    public void setDeshabilitarselUniResponsable(Boolean deshabilitarselUniResponsable) {
        this.deshabilitarselUniResponsable = deshabilitarselUniResponsable;
    }

    public Boolean getDeshabilitarInpGrupo() {
        return deshabilitarInpGrupo;
    }

    public void setDeshabilitarInpGrupo(Boolean deshabilitarInpGrupo) {
        this.deshabilitarInpGrupo = deshabilitarInpGrupo;
    }

    public Boolean getDeshabilitarselTipoCompra() {
        return deshabilitarselTipoCompra;
    }

    public void setDeshabilitarselTipoCompra(Boolean deshabilitarselTipoCompra) {
        this.deshabilitarselTipoCompra = deshabilitarselTipoCompra;
    }

    public Boolean getDeshabilitarInpDestino() {
        return deshabilitarInpDestino;
    }

    public void setDeshabilitarInpDestino(Boolean deshabilitarInpDestino) {
        this.deshabilitarInpDestino = deshabilitarInpDestino;
    }

    public Boolean getDeshabilitarInpClave() {
        return deshabilitarInpClave;
    }

    public void setDeshabilitarInpClave(Boolean deshabilitarInpClave) {
        this.deshabilitarInpClave = deshabilitarInpClave;
    }

    public Boolean getDeshabilitarFechaelaboracion() {
        return deshabilitarFechaelaboracion;
    }

    public void setDeshabilitarFechaelaboracion(Boolean deshabilitarFechaelaboracion) {
        this.deshabilitarFechaelaboracion = deshabilitarFechaelaboracion;
    }

    public Boolean getDeshabilitarInpPeriod() {
        return deshabilitarInpPeriod;
    }

    public void setDeshabilitarInpPeriod(Boolean deshabilitarInpPeriod) {
        this.deshabilitarInpPeriod = deshabilitarInpPeriod;
    }

    public String getLbMsjEsperandoIM() {
        return lbMsjEsperandoIM;
    }

    public void setLbMsjEsperandoIM(String lbMsjEsperandoIM) {
        this.lbMsjEsperandoIM = lbMsjEsperandoIM;
    }

    public Boolean getRequiredHabilitarNep() {
        return requiredHabilitarNep;
    }

    public void setRequiredHabilitarNep(Boolean requiredHabilitarNep) {
        this.requiredHabilitarNep = requiredHabilitarNep;
    }

    public Boolean getRequiredHabilitarOficioSuficiencia() {
        return requiredHabilitarOficioSuficiencia;
    }

    public void setRequiredHabilitarOficioSuficiencia(Boolean requiredHabilitarOficioSuficiencia) {
        this.requiredHabilitarOficioSuficiencia = requiredHabilitarOficioSuficiencia;
    }

    public Boolean getMostrarBtnInvestMercado() {
        return mostrarBtnInvestMercado;
    }

    public void setMostrarBtnInvestMercado(Boolean mostrarBtnInvestMercado) {
        this.mostrarBtnInvestMercado = mostrarBtnInvestMercado;
    }

    public Boolean getMostrarBtnAceptarIm() {
        return mostrarBtnAceptarIm;
    }

    public void setMostrarBtnAceptarIm(Boolean mostrarBtnAceptarIm) {
        this.mostrarBtnAceptarIm = mostrarBtnAceptarIm;
    }

    public Boolean getMostrarBtnImResuelta() {
        return mostrarBtnImResuelta;
    }

    public void setMostrarBtnImResuelta(Boolean mostrarBtnImResuelta) {
        this.mostrarBtnImResuelta = mostrarBtnImResuelta;
    }

    public Boolean getMostrarBtnCompletarRcb() {
        return mostrarBtnCompletarRcb;
    }

    public void setMostrarBtnCompletarRcb(Boolean mostrarBtnCompletarRcb) {
        this.mostrarBtnCompletarRcb = mostrarBtnCompletarRcb;
    }

    public Boolean getMostrarBtnImprimirRcb() {
        return mostrarBtnImprimirRcb;
    }

    public void setMostrarBtnImprimirRcb(Boolean mostrarBtnImprimirRcb) {
        this.mostrarBtnImprimirRcb = mostrarBtnImprimirRcb;
    }

    public List<RcbInsumosViewDto> getListRcbinsumosDtoFilter() {
        return listRcbinsumosDtoFilter;
    }

    public void setListRcbinsumosDtoFilter(List<RcbInsumosViewDto> listRcbinsumosDtoFilter) {
        this.listRcbinsumosDtoFilter = listRcbinsumosDtoFilter;
    }

    public Boolean getFiltroActivo() {
        return filtroActivo;
    }

    public void setFiltroActivo(Boolean filtroActivo) {
        this.filtroActivo = filtroActivo;
    }

    public Boolean getPageActivo() {
        return pageActivo;
    }

    public void setPageActivo(Boolean pageActivo) {
        this.pageActivo = pageActivo;
    }

    public List<Grupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<Grupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public Boolean getPerfilInvestigacion() {
        return perfilInvestigacion;
    }

    public void setPerfilInvestigacion(Boolean perfilInvestigacion) {
        this.perfilInvestigacion = perfilInvestigacion;
    }

    public Boolean getMostrarCargaArchivos() {
        return mostrarCargaArchivos;
    }

    public void setMostrarCargaArchivos(Boolean mostrarCargaArchivos) {
        this.mostrarCargaArchivos = mostrarCargaArchivos;
    }

    public ArchivosBean getArchivosBean() {
        return archivosBean;
    }

    public void setArchivosBean(ArchivosBean archivosBean) {
        this.archivosBean = archivosBean;
    }

    public BitacoraTareaEstatus getBitacora() {
        return bitacora;
    }

    public void setBitacora(BitacoraTareaEstatus bitacora) {
        this.bitacora = bitacora;
    }

    public StreamedContent getFile() {
        return file;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public List<RcbGrupo> getListaRcbGrupo() {
        return listaRcbGrupo;
    }

    public void setListaRcbGrupo(List<RcbGrupo> listaRcbGrupo) {
        this.listaRcbGrupo = listaRcbGrupo;
    }

    public List<String> getGruposSeleccionados() {
        return gruposSeleccionados;
    }

    public void setGruposSeleccionados(List<String> gruposSeleccionados) {
        this.gruposSeleccionados = gruposSeleccionados;
    }

    public Boolean getPerfilAdjudicacion() {
        return perfilAdjudicacion;
    }

    public void setPerfilAdjudicacion(Boolean perfilAdjudicacion) {
        this.perfilAdjudicacion = perfilAdjudicacion;
    }

    public boolean isBmigracion() {
        return bmigracion;
    }

    public void setBmigracion(boolean bmigracion) {
        this.bmigracion = bmigracion;
    }

}
