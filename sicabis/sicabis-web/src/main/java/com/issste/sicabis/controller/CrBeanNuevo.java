/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.CrInsumosViewDto;
import com.issste.sicabis.ejb.DTO.RcbInsumosViewDto;
import com.issste.sicabis.ejb.ln.AreasService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.CrInsumosService;
import com.issste.sicabis.ejb.ln.CrService;
import com.issste.sicabis.ejb.ln.GrupoService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.RcbInsumosService;
import com.issste.sicabis.ejb.ln.RcbService;
import com.issste.sicabis.ejb.ln.TipoCompraService;
import com.issste.sicabis.ejb.ln.UnidadResponsableService;
import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Cr;
import com.issste.sicabis.ejb.modelo.CrInsumos;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Grupo;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import com.issste.sicabis.ejb.modelo.TipoCompra;
import com.issste.sicabis.ejb.modelo.UnidadResponsable;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.BitacoraUtil;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.PlaneacionEstatusEnum;
import com.issste.sicabis.utils.Utilidades;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import org.primefaces.component.tabview.Tab;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Toshiba Manolo
 */
@ManagedBean(name = "crBeanNuevo")
@ViewScoped
public class CrBeanNuevo implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<CrInsumos> listCrInsumos;
    private List<CrInsumos> listCrInsumosSelected;
    private List<CrInsumos> listCrInsumosFiltered;
    private CrInsumos crInsumoSeleccionado;

    private List<RcbInsumosViewDto> listRcbinsumosDtoFilter;
    private List<CrInsumosViewDto> listCrinsumosDto;
    private List<CrInsumosViewDto> listCrinsumosDtoFilter;
    private List<TipoCompra> listaTipoCompra;
    private List<UnidadResponsable> listaUnidadResponsable;
    private List<Grupo> listaGrupos;
    Integer tmpIdRcbView;
    private Cr cr = new Cr();
    private int idArea;
    private List<Area> areasList;

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
    private GrupoService grupoService;
    @EJB
    private CrService crService;
    @EJB
    private CrInsumosService crInsumosService;
    @EJB
    private AreasService areasService;

    /*
     Implementacion de bitacora
     */
    @EJB
    BitacoraTareaSerice bitacoraService;
    BitacoraTareaEstatus bitacora;
    BitacoraUtil bitacoraUtil = new BitacoraUtil();

    @ManagedProperty(value = "#{archivosBean}")
    private ArchivosBean archivosBean;

    private Mensajes mensaje = new Mensajes();
    private Usuarios usuarios;
    private Utilidades util = new Utilidades();
    private Integer idArea2;
    private Boolean mostrarBtnCargarRCBanterior;
    private Boolean mostrarBtnEliminarTodo;
    private Boolean mostrarBtnAgregarInsumo;
    private Boolean mostrarBtnModificar;
    private Boolean mostrarBtnEliminar;

    public CrBeanNuevo() {
        bitacora = new BitacoraTareaEstatus();
        usuarios = new Usuarios();
        listCrinsumosDto = new ArrayList<>();

    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        idArea2 = usuarios.getIdArea().getIdArea();
        idArea = usuarios.getIdArea().getIdArea();
        mostrarBtnCargarRCBanterior = true;
        mostrarBtnEliminarTodo = true;
        mostrarBtnAgregarInsumo = true;
        mostrarBtnModificar = true;
        mostrarBtnEliminar = true;
        System.out.println("CrBeanNuevo init");

        listaTipoCompra = tipoCompraService.traeListaTipoCompra();
        listaUnidadResponsable = unidadResponsableService.traeListaUnidadesResponsables();
        listaGrupos = grupoService.traeListaGrupos();
        areasList = new ArrayList<>();
        cargarAreas();
        listCrInsumos = new ArrayList<>();
        cr = new Cr();
        cr.setNumeroCr("CR-");
        cr.setIdUnidadResponsable(new UnidadResponsable());
        cr.setEjercicio(Calendar.getInstance().get(Calendar.YEAR) + 1);
        tmpIdRcbView = 0;
        bitacora = new BitacoraTareaEstatus();

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
        util.setContextAtributte("idCr", crSeleccionada);
        return "crDetalle.xhtml?faces-redirect=true";

    }

    public void onTabChange(TabChangeEvent event) {
        Tab activeTab = event.getTab();
        System.out.println("dd" + activeTab.getTitle());
    }

    public void limpiarFrmNuevo() {
        System.out.println("Entro a limpiar");
        listCrInsumos.clear();
        RequestContext.getCurrentInstance().reset("formRCB:pnlRCB");
        RequestContext.getCurrentInstance().reset("formRCB:tablaRcbInsumo");
        init();

    }

    public void eliminarInsumos() {
        listCrinsumosDto.clear();
        cr.setImporteTotal(null);
    }

    public void cargarCatalogoInsumos() {

        listCrinsumosDto = new ArrayList<>();
        System.out.println("usuarios.getIdArea().getIdArea(): " + usuarios.getIdArea().getIdArea());
        List<Insumos> listaInsumos = insumoService.traeListaInsumosPorArea(idArea);
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
        if (ultimaRCB == null) {
            mensaje.mensaje("No existen ningun CR registrado en el sistema", "amarillo");
        } else {
            if (listCrInsumos != null) {
                listCrInsumos.clear();
            } else {
                listCrInsumos = new ArrayList<>();
            }
            BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
            System.out.println("traerRcbId ultimaRCB: " + ultimaRCB);
            listCrInsumos = crService.getCrByID(ultimaRCB).getCrInsumosList();
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
                    break;
                }
            }
        } else {
            listCrinsumosDto = new ArrayList<>();
        }
        if (existeInsumo) {
            mensaje.mensaje("El insumo no puede ser agregado, se encuentra dentro de la lista de insumos", "amarillo");
        } else {
            CrInsumos rcbInsumos = new CrInsumos();
            rcbInsumos.setIdCrInsumos(0);
            rcbInsumos.setIdInsumo(insumo);
            rcbInsumos.setExistencias(0);
            rcbInsumos.setConsumoPromedio(0);
            rcbInsumos.setCantidadPiezas(0);
            rcbInsumos.setPrecioUnitario(BigDecimal.ZERO);
            rcbInsumos.setImporte(BigDecimal.ZERO);
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

        cr.setActivo(1);
        cr.setImporteTotal(BigDecimal.ZERO);
        cr.setIdUnidadResponsable(new UnidadResponsable(1));
        cr.setFechaAlta(new Date());
        cr.setIdEstatus(new Estatus(21));
        cr.setIdArea(new Area(idArea));
        System.out.println("guardarCr" + cr.toString());
        System.out.println("listCrInsumos" + listCrInsumos.size());
        List<CrInsumos> listaRcbInsumos = new ArrayList<>();
        if (listCrinsumosDto != null) {

            if (listCrinsumosDto.size() > 0) {
                for (CrInsumosViewDto next : listCrinsumosDto) {
                    if (next.getCrInsumoSeleccionado()) {
                        next.getCrInsumo().setActivo(1);
                        next.getCrInsumo().setIdTipoCompra(new TipoCompra(1));
                        next.getCrInsumo().setIdCr(cr);
                        listaRcbInsumos.add(next.getCrInsumo());
                    }
                }
                if (listaRcbInsumos.size() > 0) {

                    cr.setCrInsumosList(listaRcbInsumos);
                    System.out.println("Entro a guardar");
                    cr.setNumeroCr("CR-" + String.valueOf(cr.getEjercicio()).substring(2));
                    Cr validaCr = crService.buscaCrPorEjercicio(cr.getEjercicio(), idArea);
                    System.out.println("validaCr.getNumeroCr():" + validaCr.getNumeroCr());
                    if (validaCr.getNumeroCr() == null) {
                        System.out.println("entro valida");
                        Cr tmpRcb = crService.save(cr);
                        System.out.println("tmpRcb" + tmpRcb);
                        tmpIdRcbView = tmpRcb.getIdCr();
                        bitacora.setFecha(new Date());
                        bitacora.setIdEstatus(cr.getIdEstatus().getIdEstatus());
                        bitacora.setDescripcion(bitacoraUtil.detalleGuardaroBitacora("cr " + tmpRcb.getNumeroCr()));
                        bitacora.setIdUsuarios(usuarios.getIdUsuario());
                        bitacora.setIdTareaId(tmpRcb.getIdCr());
                        bitacora.setIdModulos(PlaneacionEstatusEnum.ID_MODULO_CR.getValue());
                        bitacoraService.guardarEnBitacora(bitacora);
                        mensaje.mensaje(mensaje.datos_guardados + ",el número de Cr generado es: " + tmpRcb.getNumeroCr(), "verde");
                        RequestContext.getCurrentInstance().execute("PF('wdContCaptura').show()");
                    } else {
                        mensaje.mensaje("El Cr para el ejercicio ingresado, ya se encuentra registrado.", "rojo");
                    }

                } else {
                    mensaje.mensaje("Seleccione los insumos por requerir", "rojo");
                }

            } else {
                mensaje.mensaje("La lista de insumos no puede estar vacía", "rojo");
            }
        } else {
            mensaje.mensaje("La lista de insumos no puede estar vacía", "rojo");
        }

    }

    public void guardarIdArea() {
        System.out.println("idCrArea : " + idArea);
        util.setSessionMapValue("idArea", idArea);
    }

    public void cargarAreas() {
        List<Area> areasListAux = areasService.obtenerAreas();
        for (Area ar : areasListAux) {
            if (ar.getIdArea() != 10 && ar.getIdPadre() != null) {
                if (usuarios.getIdArea().getIdArea() == 16 || usuarios.getIdArea().getIdArea() == 17) {
                    if (ar.getIdArea() >= 11 && ar.getIdArea() <= 14) {
                        areasList.add(ar);
                    }
                } else if (Objects.equals(ar.getIdArea(), usuarios.getIdArea().getIdArea())) {
                    areasList.add(ar);
                    idArea = ar.getIdArea();
                }
            }
            if (usuarios.getIdUsuario() == 1) {
                areasList.add(ar);
            }
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        listCrinsumosDto = new ArrayList<>();
        archivosBean.setUploadedfile(event.getFile());
        System.out.println(event.getFile().getFileName() + " is uploaded.");
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        DateFormat date = new SimpleDateFormat("ddMMyyyy");
        String strFecha = date.format(new Date());
        String fechaNombreArchivo = "/" + strFecha + "_" + archivosBean.getUploadedfile().getFileName();
        archivosBean.guardaArchivo(archivosBean.getUploadedfile(), fechaNombreArchivo);
        List<CrInsumos> lisExcel = archivosBean.readCRExcelFile(new File(ArchivosUtilidades.PATHCARGAMASIVA + fechaNombreArchivo));

        BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
        for (CrInsumos crInsumos : lisExcel) {
            List<Insumos> validaInsumo = insumoService.buscarInsumosPorClave(crInsumos.getIdInsumo().getClave());
            if (validaInsumo != null) {
                if (validaInsumo.size() > 0) {
                    Insumos insumo = validaInsumo.get(0);
                    crInsumos.setIdCrInsumos(0);
                    crInsumos.setIdInsumo(insumo);
                    crInsumos.setExistencias(0);
                    crInsumos.setConsumoPromedio(0);
//                    crInsumos.setCantidadPiezas(crInsumos.getCantidadPiezas());
//                    crInsumos.setPrecioUnitario(crInsumos.getPrecioUnitario());
                    crInsumos.setImporte(crInsumos.getPrecioUnitario().multiply(new BigDecimal(crInsumos.getCantidadPiezas())));
                    CrInsumosViewDto crInsumosDto = new CrInsumosViewDto();
                    crInsumosDto.setCrInsumo(crInsumos);
                    crInsumosDto.setCrInsumoSeleccionado(Boolean.TRUE);
                    crInsumosDto.setImporteSinIva(
                            crInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_010.getValue()
                            || crInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_020.getValue()
                            || crInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_030.getValue()
                            || crInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_040.getValue()
                                    ? crInsumos.getImporte()
                                    : calculoImporteSinIva(crInsumos.getImporte()));
                    crInsumosDto.setImporteIva(
                            crInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_010.getValue()
                            || crInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_020.getValue()
                            || crInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_030.getValue()
                            || crInsumos.getIdInsumo().getIdGrupo().getIdGrupo() == PlaneacionEstatusEnum.ID_GRUPO_040.getValue()
                                    ? new BigDecimal(BigInteger.ZERO) : crInsumos.getImporte().subtract(crInsumosDto.getImporteSinIva()));
                    tmpImporteTotal = tmpImporteTotal.add(crInsumos.getImporte());
                    listCrinsumosDto.add(crInsumosDto);
                }
            } else {
                mensaje.mensaje("No se encontro en el catálogo:" + crInsumos.getIdInsumo().getClave(), "amarillo");
            }
        }
        cr.setImporteTotal(tmpImporteTotal);
        if (listCrinsumosDto.size() > 0) {
            mensaje.mensaje("Se cargo el archivo correctamente", "verde");
        }
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

    public Boolean getMostrarBtnModificar() {
        return mostrarBtnModificar;
    }

    public void setMostrarBtnModificar(Boolean mostrarBtnModificar) {
        this.mostrarBtnModificar = mostrarBtnModificar;
    }

    public Boolean getMostrarBtnEliminar() {
        return mostrarBtnEliminar;
    }

    public void setMostrarBtnEliminar(Boolean mostrarBtnEliminar) {
        this.mostrarBtnEliminar = mostrarBtnEliminar;
    }

    public Boolean getMostrarBtnAgregarInsumo() {
        return mostrarBtnAgregarInsumo;
    }

    public void setMostrarBtnAgregarInsumo(Boolean mostrarBtnAgregarInsumo) {
        this.mostrarBtnAgregarInsumo = mostrarBtnAgregarInsumo;
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

    public Integer getTmpIdRcbView() {
        return tmpIdRcbView;
    }

    public void setTmpIdRcbView(Integer tmpIdRcbView) {
        this.tmpIdRcbView = tmpIdRcbView;
    }

    public List<RcbInsumosViewDto> getListRcbinsumosDtoFilter() {
        return listRcbinsumosDtoFilter;
    }

    public void setListRcbinsumosDtoFilter(List<RcbInsumosViewDto> listRcbinsumosDtoFilter) {
        this.listRcbinsumosDtoFilter = listRcbinsumosDtoFilter;
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

    public List<CrInsumos> getListCrInsumosSelected() {
        return listCrInsumosSelected;
    }

    public void setListCrInsumosSelected(List<CrInsumos> listCrInsumosSelected) {
        this.listCrInsumosSelected = listCrInsumosSelected;
    }

    public CrInsumos getCrInsumoSeleccionado() {
        return crInsumoSeleccionado;
    }

    public void setCrInsumoSeleccionado(CrInsumos crInsumoSeleccionado) {
        this.crInsumoSeleccionado = crInsumoSeleccionado;
    }

    public List<CrInsumos> getListCrInsumosFiltered() {
        return listCrInsumosFiltered;
    }

    public void setListCrInsumosFiltered(List<CrInsumos> listCrInsumosFiltered) {
        this.listCrInsumosFiltered = listCrInsumosFiltered;
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

    public List<Area> getAreasList() {
        return areasList;
    }

    public void setAreasList(List<Area> areasList) {
        this.areasList = areasList;
    }

    public ArchivosBean getArchivosBean() {
        return archivosBean;
    }

    public void setArchivosBean(ArchivosBean archivosBean) {
        this.archivosBean = archivosBean;
    }

}
