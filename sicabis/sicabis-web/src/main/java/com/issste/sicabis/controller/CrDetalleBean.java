/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.CrRDto;
import com.issste.sicabis.DTO.RcbDTO;
import com.issste.sicabis.ejb.DTO.CrInsumosViewDto;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.CrInsumosService;
import com.issste.sicabis.ejb.ln.CrService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Cr;
import com.issste.sicabis.ejb.modelo.CrInsumos;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Grupo;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.TipoCompra;
import com.issste.sicabis.ejb.modelo.UnidadResponsable;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.BitacoraUtil;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.PlaneacionEstatusEnum;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.primefaces.component.tabview.Tab;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.data.FilterEvent;
import org.primefaces.event.data.PageEvent;

/**
 *
 * @author Toshiba Manolo
 */
@ManagedBean(name = "crDetalleBean")
@ViewScoped
public class CrDetalleBean {

    private static final long serialVersionUID = 1L;

    private List<CrInsumos> listCrInsumos;
    private CrInsumos crInsumoSeleccionado;

    private List<CrInsumosViewDto> listCrinsumosDto;
    private List<CrInsumosViewDto> listCrinsumosDtoFilter;
    private List<TipoCompra> listaTipoCompra;
    private List<UnidadResponsable> listaUnidadResponsable;
    private List<Grupo> listaGrupos;
    Integer tmpIdRcbView;
    private Cr cr = new Cr();
    @ManagedProperty(value = "#{archivosBean}")
    private ArchivosBean archivosBean;

    @EJB
    private InsumosService insumoService;
    @EJB
    private CrService crService;
    @EJB
    private CrInsumosService crInsumosService;

    /*
     Implementacion de bitacora
     */
    @EJB
    BitacoraTareaSerice bitacoraService;
    BitacoraTareaEstatus bitacora;
    BitacoraUtil bitacoraUtil = new BitacoraUtil();

    private Mensajes mensaje = new Mensajes();
    private Usuarios usuarios;
    private Utilidades util = new Utilidades();

    private Integer idCr;

    private Boolean deshabilitarBtnCargarRCBanterior;
    private Boolean deshabilitarBtnAgregarInsumo;
    private Boolean deshabilitarBtnEliminarTodo;
    private Boolean mostrarBtnGuardar;
    private Boolean mostrarMsjCrTerminada;
    private Boolean mostrarBtnLimpiarFrmNuevo;
    private Boolean desHabilitarBtnEliminar;
    private Boolean deshabilitarEjercicio;
    private Boolean mostrarBtnImprimir;
    private Boolean filtroActivo;
    private Boolean pageActivo;
    private String lbMsjEsperandoIM;
    private Boolean muestraCelda;
    private Boolean mostrarCargaArchivos;
    private int idArea;

    public CrDetalleBean() {
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        System.out.println("idArea: " + idArea);
        idCr = (Integer) util.getContextAtributte("idCr");
        bitacora = new BitacoraTareaEstatus();
        archivosBean.onload(2, idCr, -1);
        filtroActivo = false;
        pageActivo = false;
        System.out.println("CrDetalleBean init");
        System.out.println("idCr: " + idCr);
        mostrarBtnImprimir = true;
        if (idCr != null) {

            listCrInsumos = new ArrayList<>();
            cr = crService.getCrByID(idCr);
            System.out.println("cr:" + cr.getIdCr());
            listCrinsumosDto = new ArrayList<>();
            BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
            System.out.println("cr.getCrInsumosList():" + cr.getCrInsumosList().size());
            for (CrInsumos rcbInsumo : cr.getCrInsumosList()) {
                CrInsumosViewDto rcbInsumosDto = new CrInsumosViewDto();
                rcbInsumosDto.setCrInsumo(rcbInsumo);
                rcbInsumosDto.setCrInsumoSeleccionado(Boolean.TRUE);
                rcbInsumosDto.setImporteSinIva(
                        rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_010.getValue()
                        || rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_020.getValue()
                        || rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_030.getValue()
                        || rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_040.getValue()
                                ? rcbInsumo.getImporte()
                                : calculoImporteSinIva(rcbInsumo.getImporte()));
                rcbInsumosDto.setImporteIva(
                        rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_010.getValue()
                        || rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_020.getValue()
                        || rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_030.getValue()
                        || rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_040.getValue()
                                ? new BigDecimal(BigInteger.ZERO) : rcbInsumo.getImporte().subtract(rcbInsumosDto.getImporteSinIva()));
                listCrinsumosDto.add(rcbInsumosDto);
                tmpImporteTotal = tmpImporteTotal.add(rcbInsumo.getImporte());
            }
            cr.setImporteTotal(tmpImporteTotal);
        }

        if (cr.getIdEstatus().getIdEstatus() == 22) {
            bloquearForm();
        } else {
            activarForm();
        }

    }

    public void onClickNuevoRcb() throws IOException {
        System.out.println("onClickNuevoRcb");

    }

    public void irRcbDetalleControls(Integer idRcbSeleccionada) {
        System.out.println("entro irRcbDetalle");
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/rcb/rcbDetalle.xhtml?idrcb=" + idRcbSeleccionada);
        } catch (IOException ex) {
            Logger.getLogger(RcbBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String irRcbDetalleControl(Integer crSeleccionada) {
        util.setContextAtributte("idcr", crSeleccionada);
        return "crDetalle.xhtml?faces-redirect=true";

    }

    public void onTabChange(TabChangeEvent event) {
        Tab activeTab = event.getTab();
        System.out.println("dd" + activeTab.getTitle());
    }

    public void filterListener(FilterEvent filterEvent) {
        System.out.println("filtro activo");
        filtroActivo = true;
    }

    public void pageListener(PageEvent pageEvent) {
        System.out.println("filtro inactivo");
        pageActivo = true;
    }

    public void onCellEdit(CellEditEvent event) {
        CrInsumosViewDto selected = null;
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (listCrinsumosDtoFilter != null) {
            selected = listCrinsumosDtoFilter.get(event.getRowIndex());
            if (newValue != null && !newValue.equals(oldValue)) {
                selected.getCrInsumo().setImporte(selected.getCrInsumo().getPrecioUnitario().multiply(new BigDecimal(selected.getCrInsumo().getCantidadPiezas())));
                selected.setCrInsumo(crInsumosService.actualizarCrInsumo(selected.getCrInsumo()));
                listCrinsumosDtoFilter.get(event.getRowIndex()).setCrInsumo(selected.getCrInsumo());
            }

            BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
            for (Iterator<CrInsumosViewDto> iterator = listCrinsumosDtoFilter.iterator(); iterator.hasNext();) {
                CrInsumosViewDto next = iterator.next();
                tmpImporteTotal = tmpImporteTotal.add(next.getCrInsumo().getImporte());
            }
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("idRowModificar", event.getRowIndex());
            requestContext.addCallbackParam("importe", listCrinsumosDtoFilter.get(event.getRowIndex()).getCrInsumo().getImporte());
            requestContext.addCallbackParam("importeTotal", tmpImporteTotal);
            requestContext.addCallbackParam("idGrupo", listCrinsumosDto.get(event.getRowIndex()).getCrInsumo().getIdInsumo().getIdGrupo().getIdGrupo());

        } else {
            selected = listCrinsumosDto.get(event.getRowIndex());
            if (newValue != null && !newValue.equals(oldValue)) {
                selected.getCrInsumo().setImporte(selected.getCrInsumo().getPrecioUnitario().multiply(new BigDecimal(selected.getCrInsumo().getCantidadPiezas())));
                selected.setCrInsumo(crInsumosService.actualizarCrInsumo(selected.getCrInsumo()));
                listCrinsumosDto.get(event.getRowIndex()).setCrInsumo(selected.getCrInsumo());
            }

            BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
            for (Iterator<CrInsumosViewDto> iterator = listCrinsumosDto.iterator(); iterator.hasNext();) {
                CrInsumosViewDto next = iterator.next();
                tmpImporteTotal = tmpImporteTotal.add(next.getCrInsumo().getImporte());
            }

            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("idRowModificar", event.getRowIndex());
            requestContext.addCallbackParam("importe", listCrinsumosDto.get(event.getRowIndex()).getCrInsumo().getImporte());
            requestContext.addCallbackParam("importeTotal", tmpImporteTotal);
            requestContext.addCallbackParam("idGrupo", listCrinsumosDto.get(event.getRowIndex()).getCrInsumo().getIdInsumo().getIdGrupo().getIdGrupo());

        }
    }

    public void limpiarFrmNuevo() {
        System.out.println("Entro a limpiar");
        RequestContext.getCurrentInstance().reset("formRCB:pnlRCB");
        RequestContext.getCurrentInstance().reset("formRCB:tablaRcbInsumo");
        init();

    }

    public void bloquearForm() {

        deshabilitarBtnCargarRCBanterior = true;
        deshabilitarBtnAgregarInsumo = true;
        deshabilitarBtnEliminarTodo = true;
        mostrarBtnGuardar = false;
        mostrarMsjCrTerminada = true;
        mostrarBtnLimpiarFrmNuevo = false;
        mostrarBtnImprimir = true;
        desHabilitarBtnEliminar = true;
        deshabilitarEjercicio = true;
        lbMsjEsperandoIM = "Cr Terminada";
        muestraCelda = false;
        mostrarCargaArchivos = true;
    }

    public void activarForm() {

        deshabilitarBtnCargarRCBanterior = false;
        deshabilitarBtnAgregarInsumo = false;
        deshabilitarBtnEliminarTodo = false;
        mostrarBtnGuardar = true;
        mostrarMsjCrTerminada = false;
        mostrarBtnLimpiarFrmNuevo = true;
        mostrarBtnImprimir = false;
        desHabilitarBtnEliminar = false;
        deshabilitarEjercicio = false;
        lbMsjEsperandoIM = "Cr Terminada";
        muestraCelda = true;
        mostrarCargaArchivos = false;
    }

    public void eliminarInsumos() {
        System.out.println("eliminar todos Insumos");
        crInsumosService.deleteCrInsumosByIdCr(cr.getIdCr());
        listCrinsumosDto.clear();
        cr.setImporteTotal(null);
    }

    public void eliminarInsumo(CrInsumosViewDto rcbInsumo) {
        System.out.println("eliminoinsumo: " + rcbInsumo.getCrInsumo().getImporte());
        crInsumosService.deleteCrInsumos(rcbInsumo.getCrInsumo());
        boolean remove = listCrinsumosDto.remove(rcbInsumo);
        BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
        for (Iterator<CrInsumosViewDto> iterator = listCrinsumosDto.iterator(); iterator.hasNext();) {
            CrInsumosViewDto next = iterator.next();
            tmpImporteTotal = tmpImporteTotal.add(next.getCrInsumo().getImporte());
        }

        if (filtroActivo && pageActivo) {
            RequestContext.getCurrentInstance().execute("PF('wVtablaCrInsumo').filter()");
        } else if (filtroActivo) {
            RequestContext.getCurrentInstance().execute("PF('wVtablaCrInsumo').filter()");
        }
        rcbInsumo = null;

        cr.setImporteTotal(tmpImporteTotal);
    }

    public void cargarCatalogoInsumos() {

        listCrinsumosDto = new ArrayList<>();
        List<Insumos> listaInsumos = insumoService.traeListaInsumosPorArea(usuarios.getIdArea().getIdArea());
        BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
        for (Insumos insumo : listaInsumos) {
            CrInsumos rcbInsumos = new CrInsumos();
            rcbInsumos.setIdCrInsumos(0);
            rcbInsumos.setIdInsumo(insumo);
            rcbInsumos.setExistencias(0);
            rcbInsumos.setConsumoPromedio(0);
            rcbInsumos.setCantidadPiezas(0);
            rcbInsumos.setPrecioUnitario(BigDecimal.ZERO);
            rcbInsumos.setImporte(rcbInsumos.getPrecioUnitario().multiply(new BigDecimal(rcbInsumos.getCantidadPiezas())));
            CrInsumosViewDto rcbInsumosDto = new CrInsumosViewDto();
            rcbInsumosDto.setCrInsumo(rcbInsumos);
            rcbInsumosDto.setCrInsumoSeleccionado(Boolean.TRUE);
            rcbInsumosDto.setImporteSinIva(
                    rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_010.getValue()
                    || rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_020.getValue()
                    || rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_030.getValue()
                    || rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_040.getValue()
                            ? rcbInsumos.getImporte()
                            : calculoImporteSinIva(rcbInsumos.getImporte()));
            rcbInsumosDto.setImporteIva(
                    rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_010.getValue()
                    || rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_020.getValue()
                    || rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_030.getValue()
                    || rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_040.getValue()
                            ? new BigDecimal(BigInteger.ZERO) : rcbInsumos.getImporte().subtract(rcbInsumosDto.getImporteSinIva()));
            listCrinsumosDto.add(rcbInsumosDto);
            tmpImporteTotal = tmpImporteTotal.add(rcbInsumos.getImporte());
            System.out.println("tmpImporteTotal:" + tmpImporteTotal);
        }
        cr.setImporteTotal(tmpImporteTotal);

    }

    public BigDecimal calculoImporteSinIva(BigDecimal importe) {
        BigDecimal result = new BigDecimal(BigInteger.ZERO);
        BigDecimal iva = new BigDecimal("1.15");
        if (importe.compareTo(result) > 0) {
            result = importe.divide(iva, 2, RoundingMode.HALF_UP);
        }
        return result;
    }

    public void cargarRCBAnterior() {

        System.out.println("usuario area: " + usuarios.getIdArea().getIdArea());
        Integer ultimaRCB = crInsumosService.traerMaxCRPorArea(idArea);
        System.out.println("ultimaRCB: " + ultimaRCB);
        if (ultimaRCB == cr.getIdCr()) {
            mensaje.mensaje("No existen ningun CR anterior registrado en el sistema", "amarillo");
        } else {
            if (listCrInsumos != null) {
                listCrInsumos.clear();
            } else {
                listCrInsumos = new ArrayList<>();
            }
            BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
            System.out.println("traerRcbId ultimaRCB: " + ultimaRCB);
            listCrInsumos = crService.getCrByID(ultimaRCB).getCrInsumosList();
            listCrinsumosDto.clear();
            for (CrInsumos rcbInsumo : listCrInsumos) {
                CrInsumosViewDto rcbInsumosDto = new CrInsumosViewDto();
                rcbInsumosDto.setCrInsumo(rcbInsumo);
                rcbInsumosDto.setCrInsumoSeleccionado(Boolean.TRUE);
                rcbInsumosDto.setImporteSinIva(
                        rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_010.getValue()
                        || rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_020.getValue()
                        || rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_030.getValue()
                        || rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_040.getValue()
                                ? rcbInsumo.getImporte()
                                : calculoImporteSinIva(rcbInsumo.getImporte()));
                rcbInsumosDto.setImporteIva(
                        rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_010.getValue()
                        || rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_020.getValue()
                        || rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_030.getValue()
                        || rcbInsumo.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_040.getValue()
                                ? new BigDecimal(BigInteger.ZERO) : rcbInsumo.getImporte().subtract(rcbInsumosDto.getImporteSinIva()));
                listCrinsumosDto.add(rcbInsumosDto);
                tmpImporteTotal = tmpImporteTotal.add(rcbInsumo.getImporte());
            }

            cr.setImporteTotal(tmpImporteTotal);

        }
    }

    public void seleccionaInsumo() {
        System.out.println("entro a choose");
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("includeViewParams", true);
        options.put("width", "1020");
        options.put("height", "400");
        options.put("contentWidth", "1020");
        options.put("contentHeight", "400");
        System.out.println("entro a choose antes request");
        RequestContext.getCurrentInstance().openDialog("/vistas/rcb/buscaInsumo.xhtml", options, null);
        System.out.println("salio a choose despues request");
    }

    public void insumoSeleccionado(SelectEvent event) {
        Boolean existeInsumo = false;
        Insumos insumo = (Insumos) event.getObject();
        System.out.println("insumo: " + insumo.getClave());
        if (listCrinsumosDto != null) {
            for (CrInsumosViewDto rcbtdo : listCrinsumosDto) {
                if (rcbtdo.getCrInsumo().getIdInsumo().equals(insumo)) {
                    existeInsumo = true;
                    System.out.println("existeInsumo: ");
                    break;
                }
            }
        } else {
            listCrinsumosDto = new ArrayList<>();
            System.out.println("listCrinsumosDto ArrayList: ");
        }
        if (existeInsumo) {
            mensaje.mensaje("El insumo no puede ser agregado, se encuentra dentro de la lista de Insumos", "amarillo");
        } else {
            CrInsumos rcbInsumos = new CrInsumos();
            rcbInsumos.setActivo(1);
            rcbInsumos.setIdCrInsumos(0);
            rcbInsumos.setIdInsumo(insumo);
            rcbInsumos.setExistencias(0);
            rcbInsumos.setConsumoPromedio(0);
            rcbInsumos.setCantidadPiezas(0);
            rcbInsumos.setPrecioUnitario(BigDecimal.ZERO);
            rcbInsumos.setImporte(BigDecimal.ZERO);
            rcbInsumos.setIdTipoCompra(new TipoCompra(1));
            rcbInsumos.setIdCr(cr);
            rcbInsumos = crInsumosService.guardarCrInsumo(rcbInsumos);
            CrInsumosViewDto rcbInsumosDto = new CrInsumosViewDto();
            rcbInsumosDto.setCrInsumo(rcbInsumos);
            rcbInsumosDto.setCrInsumoSeleccionado(Boolean.TRUE);
            rcbInsumosDto.setImporteSinIva(
                    rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_010.getValue()
                    || rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_020.getValue()
                    || rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_030.getValue()
                    || rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_040.getValue()
                            ? rcbInsumos.getImporte()
                            : calculoImporteSinIva(rcbInsumos.getImporte()));
            rcbInsumosDto.setImporteIva(
                    rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_010.getValue()
                    || rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_020.getValue()
                    || rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_030.getValue()
                    || rcbInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_040.getValue()
                            ? new BigDecimal(BigInteger.ZERO) : rcbInsumos.getImporte().subtract(rcbInsumosDto.getImporteSinIva()));
            listCrinsumosDto.add(rcbInsumosDto);
        }
    }

    public String goToNextpage() {
        return "rcb";
    }

    public void guardarRCB() {

        System.out.println("guardarCr");

        Boolean importesCorrectos = true;
        if (listCrinsumosDto.size() > 0) { //valida lista de insumos
            switch (cr.getIdEstatus().getIdEstatus()) {
                case 21:
                    System.out.println("Entro a estatus 21 CR creada ");
                    for (CrInsumosViewDto rcbinsumoValida : listCrinsumosDto) { //valida importes iguales a cero dentro de lista de insumos
                        System.out.println("valida importe: " + rcbinsumoValida.getCrInsumo().getImporte().toString());
                        if (rcbinsumoValida.getCrInsumo().getImporte().compareTo(BigDecimal.ZERO) == 0) {
                            System.out.println("importe: " + rcbinsumoValida.getCrInsumo().getImporte().toString());
                            importesCorrectos = false;
                            break;
                        }
                    }
                    if (importesCorrectos) {
                        cr.setIdEstatus(new Estatus(22)); //se asigna estatus investigacion resuelta
                    }
                    break;
                default:
                    System.out.println("Entro a default IMSS: " + cr.getIdEstatus().getIdEstatus());
                    importesCorrectos = false;
            }

            System.out.println("importesCorrectos: " + importesCorrectos.toString());
            if (importesCorrectos) {
                crService.updateCr(cr);
                bitacora.setFecha(new Date());
                bitacora.setIdEstatus(cr.getIdEstatus().getIdEstatus());
                bitacora.setDescripcion(bitacoraUtil.detalleGuardaroBitacora("cr " + cr.getNumeroCr()));
                bitacora.setIdUsuarios(usuarios.getIdUsuario());
                bitacora.setIdTareaId(cr.getIdCr());
                bitacora.setIdModulos(PlaneacionEstatusEnum.ID_MODULO_CR.getValue());
                bitacoraService.guardarEnBitacora(bitacora);
                mensaje.mensaje(mensaje.datos_guardados, "verde");
                init();
            } else {
                mensaje.mensaje("Las cantidades, los precios por unidad y el importe "
                        + "de los insumos deben ser mayor a cero, verifique las cantidades.", "rojo");
            }
        } else {
            mensaje.mensaje("La lista de insumos no puede estar vacía", "rojo");
        }

    }

    JasperPrint jasperPrint;

    public void imprimirCr() throws JRException, IOException {
        System.out.println("Entro a Imprimir ");
        List<CrRDto> listaImprimir = new ArrayList();
        List<Object[]> lisf = crInsumosService.traerCrGrupo(cr.getIdCr());
        for (Object[] itemobj : lisf) {
            String partidap = (String) itemobj[0];
            BigDecimal importeTotal = (BigDecimal) itemobj[1];
            CrRDto crrDto = new CrRDto();
            Calendar c = Calendar.getInstance(Locale.ENGLISH);
            crrDto.setFecha(c.get(Calendar.YEAR));
            crrDto.setPartida(partidap);
            crrDto.setSubPartida("0000");
            crrDto.setDenominacion("Denominacion");
            crrDto.setMontoAcontratar(importeTotal);
            crrDto.setPeriodoMeses(12);
            crrDto.setDescripcionBreve("descripcion breve");
            crrDto.setJustificacion("justificacion");
            listaImprimir.add(crrDto);

        }

        System.out.println("entro imprimir");
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaImprimir);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/reporteCr.jasper");
        //String reportPath = "C:\\Users\\Toshiba Manolo\\JaspersoftWorkspace\\MyReports\\reporteCr.jasper";
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        System.out.println("lleno jasper");
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
//        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        JRXlsxExporter docxExporter = new JRXlsxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();

        System.out.println("exporto imprimir");
        FacesContext.getCurrentInstance().responseComplete();
        System.out.println("salio imprimir");
        mensaje.mensaje("Impresion realizada", "verde");

    }

    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "PrimeFaces Rocks."));
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

    public Cr getCr() {
        return cr;
    }

    public void setCr(Cr cr) {
        this.cr = cr;
    }

    public Integer getTmpIdRcbView() {
        return tmpIdRcbView;
    }

    public void setTmpIdRcbView(Integer tmpIdRcbView) {
        this.tmpIdRcbView = tmpIdRcbView;
    }

    public List<Grupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<Grupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public List<CrInsumos> getListCrInsumos() {
        return listCrInsumos;
    }

    public void setListCrInsumos(List<CrInsumos> listCrInsumos) {
        this.listCrInsumos = listCrInsumos;
    }

    public CrInsumos getCrInsumoSeleccionado() {
        return crInsumoSeleccionado;
    }

    public void setCrInsumoSeleccionado(CrInsumos crInsumoSeleccionado) {
        this.crInsumoSeleccionado = crInsumoSeleccionado;
    }

    public List<CrInsumosViewDto> getListCrinsumosDto() {
        return listCrinsumosDto;
    }

    public void setListCrinsumosDto(List<CrInsumosViewDto> listCrinsumosDto) {
        this.listCrinsumosDto = listCrinsumosDto;
    }

    public List<CrInsumosViewDto> getListCrinsumosDtoFilter() {
        return listCrinsumosDtoFilter;
    }

    public void setListCrinsumosDtoFilter(List<CrInsumosViewDto> listCrinsumosDtoFilter) {
        this.listCrinsumosDtoFilter = listCrinsumosDtoFilter;
    }

    public Integer getIdCr() {
        return idCr;
    }

    public void setIdCr(Integer idCr) {
        this.idCr = idCr;
    }

    public Boolean getDesHabilitarBtnEliminar() {
        return desHabilitarBtnEliminar;
    }

    public void setDesHabilitarBtnEliminar(Boolean desHabilitarBtnEliminar) {
        this.desHabilitarBtnEliminar = desHabilitarBtnEliminar;
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

    public Boolean getDeshabilitarBtnCargarRCBanterior() {
        return deshabilitarBtnCargarRCBanterior;
    }

    public void setDeshabilitarBtnCargarRCBanterior(Boolean deshabilitarBtnCargarRCBanterior) {
        this.deshabilitarBtnCargarRCBanterior = deshabilitarBtnCargarRCBanterior;
    }

    public Boolean getDeshabilitarBtnAgregarInsumo() {
        return deshabilitarBtnAgregarInsumo;
    }

    public void setDeshabilitarBtnAgregarInsumo(Boolean deshabilitarBtnAgregarInsumo) {
        this.deshabilitarBtnAgregarInsumo = deshabilitarBtnAgregarInsumo;
    }

    public Boolean getDeshabilitarBtnEliminarTodo() {
        return deshabilitarBtnEliminarTodo;
    }

    public void setDeshabilitarBtnEliminarTodo(Boolean deshabilitarBtnEliminarTodo) {
        this.deshabilitarBtnEliminarTodo = deshabilitarBtnEliminarTodo;
    }

    public Boolean getMostrarBtnGuardar() {
        return mostrarBtnGuardar;
    }

    public void setMostrarBtnGuardar(Boolean mostrarBtnGuardar) {
        this.mostrarBtnGuardar = mostrarBtnGuardar;
    }

    public Boolean getMostrarMsjCrTerminada() {
        return mostrarMsjCrTerminada;
    }

    public void setMostrarMsjCrTerminada(Boolean mostrarMsjCrTerminada) {
        this.mostrarMsjCrTerminada = mostrarMsjCrTerminada;
    }

    public Boolean getMostrarBtnLimpiarFrmNuevo() {
        return mostrarBtnLimpiarFrmNuevo;
    }

    public void setMostrarBtnLimpiarFrmNuevo(Boolean mostrarBtnLimpiarFrmNuevo) {
        this.mostrarBtnLimpiarFrmNuevo = mostrarBtnLimpiarFrmNuevo;
    }

    public String getLbMsjEsperandoIM() {
        return lbMsjEsperandoIM;
    }

    public void setLbMsjEsperandoIM(String lbMsjEsperandoIM) {
        this.lbMsjEsperandoIM = lbMsjEsperandoIM;
    }

    public Boolean getDeshabilitarEjercicio() {
        return deshabilitarEjercicio;
    }

    public void setDeshabilitarEjercicio(Boolean deshabilitarEjercicio) {
        this.deshabilitarEjercicio = deshabilitarEjercicio;
    }

    public Boolean getMostrarBtnImprimir() {
        return mostrarBtnImprimir;
    }

    public void setMostrarBtnImprimir(Boolean mostrarBtnImprimir) {
        this.mostrarBtnImprimir = mostrarBtnImprimir;
    }

    public Boolean getMuestraCelda() {
        return muestraCelda;
    }

    public void setMuestraCelda(Boolean muestraCelda) {
        this.muestraCelda = muestraCelda;
    }

    public ArchivosBean getArchivosBean() {
        return archivosBean;
    }

    public void setArchivosBean(ArchivosBean archivosBean) {
        this.archivosBean = archivosBean;
    }

    public Boolean getMostrarCargaArchivos() {
        return mostrarCargaArchivos;
    }

    public void setMostrarCargaArchivos(Boolean mostrarCargaArchivos) {
        this.mostrarCargaArchivos = mostrarCargaArchivos;
    }

    public BitacoraTareaEstatus getBitacora() {
        return bitacora;
    }

    public void setBitacora(BitacoraTareaEstatus bitacora) {
        this.bitacora = bitacora;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

}
