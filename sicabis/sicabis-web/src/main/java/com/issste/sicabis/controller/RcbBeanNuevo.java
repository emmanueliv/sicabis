/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.RcbInsumosViewDto;
import com.issste.sicabis.ejb.ln.AreasService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.GrupoService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.JefaturaService;
import com.issste.sicabis.ejb.ln.RcbGrupoService;
import com.issste.sicabis.ejb.ln.RcbInsumosService;
import com.issste.sicabis.ejb.ln.RcbService;
import com.issste.sicabis.ejb.ln.TipoCompraService;
import com.issste.sicabis.ejb.ln.UnidadResponsableService;
import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Grupo;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Jefatura;
import com.issste.sicabis.ejb.modelo.Perfiles;
import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.ejb.modelo.RcbGrupo;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import com.issste.sicabis.ejb.modelo.TipoCompra;
import com.issste.sicabis.ejb.modelo.UnidadResponsable;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciaReservaClaveCenadiService;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.BitacoraUtil;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.PlaneacionEstatusEnum;
import com.issste.sicabis.utils.Utilidades;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
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
import javax.servlet.ServletContext;
import org.primefaces.component.tabview.Tab;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "rcbBeanNuevo")
@ViewScoped
public class RcbBeanNuevo implements Serializable {

    @EJB
    private JefaturaService jefaturaService;

    private static final long serialVersionUID = 1L;

    private List<RcbInsumosViewDto> listRcbinsumosDto;
    private List<RcbInsumosViewDto> listRcbinsumosDtoFilter;
    private List<TipoCompra> listaTipoCompra;
    private List<UnidadResponsable> listaUnidadResponsable;
    private List<Grupo> listaGrupos;
    private List<Area> areasList;
    Integer tmpIdRcbView;
    private Rcb rcb = new Rcb();
    private List<RcbGrupo> listaRcbGrupo;
    private List<String> gruposSeleccionados;

    @EJB
    private ExistenciaReservaClaveCenadiService existenciaReservaClaveCenadiService;
    @EJB
    private RcbService rcbService;
    @EJB
    private InsumosService insumoService;
    @EJB
    private TipoCompraService tipoCompraService;
    @EJB
    private UnidadResponsableService unidadResponsableService;
    @EJB
    private RcbInsumosService rcbInsumosService;
    @EJB
    private GrupoService grupoService;
    @EJB
    private RcbGrupoService rcbGrupoService;

    @EJB
    private AreasService areasService;

    @ManagedProperty(value = "#{archivosBean}")
    private ArchivosBean archivosBean;

    /*
     Implementacion de bitacora
     */
    @EJB
    private BitacoraTareaSerice bitacoraService;
    private BitacoraTareaEstatus bitacora = new BitacoraTareaEstatus();
    private BitacoraUtil bitacoraUtil = new BitacoraUtil();
    private Perfiles perfil;
    private Mensajes mensaje = new Mensajes();
    private Usuarios usuarios;
    private Utilidades util = new Utilidades();

    private Boolean habilitarNep;
    private Boolean habilitarOficioSuficiencia;
    private Boolean mostrarBtnCargarRCBanterior;
    private Boolean mostrarBtnEliminarTodo;
    private Boolean mostrarBtnAgregarInsumo;
    private Boolean mostrarCampoPrecioUnitario;
    private Boolean mostrarTextoPrecioUnitario;
    private Boolean mostrarBtnModificar;
    private Boolean mostrarBtnEliminar;

    private List<Jefatura> listaJefatura;

    private int idArea;

    public RcbBeanNuevo() {
        areasList = new ArrayList<>();
        perfil = new Perfiles();
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        perfil = (Perfiles) util.getSessionAtributte("perfil");
        if (usuarios.getIdArea() != null) {
            idArea = usuarios.getIdArea().getIdArea();

        }
        listaRcbGrupo = new ArrayList();
        bitacora = new BitacoraTareaEstatus();
        habilitarNep = true;
        habilitarOficioSuficiencia = true;
        mostrarBtnCargarRCBanterior = true;
        mostrarBtnEliminarTodo = true;
        mostrarBtnAgregarInsumo = true;
        mostrarCampoPrecioUnitario = false;
        mostrarTextoPrecioUnitario = true;
        mostrarBtnModificar = true;
        mostrarBtnEliminar = true;

        listaTipoCompra = tipoCompraService.traeListaTipoCompra();
        listaUnidadResponsable = unidadResponsableService.traeListaUnidadesResponsables();
        listaJefatura = jefaturaService.getAll();
        listaGrupos = grupoService.traeListaGrupos();
        cargarAreas();
        rcb = new Rcb();
        rcb.setNumero("RCB-" + String.valueOf(rcbService.traeIdMaxRcb() != null ? rcbService.traeIdMaxRcb() : "1"));
        rcb.setIdTipoCompra(new TipoCompra());
        rcb.setIdUnidadResponsable(new UnidadResponsable());
        rcb.setIdJefatura(new Jefatura());
        rcb.setNumeroOficio("");
        listaRcbGrupo = new ArrayList<>();
        rcb.setDestino("");
        rcb.setClave("");
        rcb.setFechaElaboracion(new Date());
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        rcb.setPeriodo(c.get(Calendar.YEAR));
        tmpIdRcbView = 0;

//        listRcbinsumosDto = new ArrayList<>();
//        System.out.println("usuarios.getIdArea().getIdArea(): "+usuarios.getIdArea().getIdArea());
//        List<Insumos> listaInsumos = insumoService.traeListaInsumosPorArea(usuarios.getIdArea().getIdArea());
//        BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
//        for (Insumos insumo : listaInsumos) {
//            RcbInsumos rcbInsumos = new RcbInsumos();
//            rcbInsumos.setIdInsumo(insumo);
//            rcbInsumos.setExistencias(0);
//            rcbInsumos.setConsumoPromedio(0);
//            rcbInsumos.setCantidadPiezas(0);
//            rcbInsumos.setPrecioUnitario(BigDecimal.ZERO);
//            rcbInsumos.setImporte(rcbInsumos.getPrecioUnitario().multiply(new BigDecimal(rcbInsumos.getCantidadPiezas())));
//            RcbInsumosViewDto rcbInsumosDto = new RcbInsumosViewDto();
//            rcbInsumosDto.setRcbInsumo(rcbInsumos);
//            rcbInsumosDto.setRcbInsumoSeleccionado(Boolean.TRUE);
//            rcbInsumosDto.setDescripcionCorta(insumo.getDescripcion().substring(0, 20));
//            listRcbinsumosDto.add(rcbInsumosDto);
//            tmpImporteTotal = tmpImporteTotal.add(rcbInsumos.getImporte());
//            System.out.println("tmpImporteTotal:"+tmpImporteTotal);
//        }
//        rcb.setImporteTotal(tmpImporteTotal);
    }

    public void guardarIdArea() {
        System.out.println("idArea : " + idArea);
        util.setSessionMapValue("idArea", idArea);
        listaJefatura = jefaturaService.getByIdArea(idArea);
        if (listaJefatura == null) {
            listaJefatura = jefaturaService.getAll();
        }
    }

    public void onClickNuevoRcb() throws IOException {
        System.out.println("onClickNuevoRcb");

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

    public String irRcbDetalleControl(Integer rcbSeleccionada) {
        util.setContextAtributte("idrcb", rcbSeleccionada);
        util.setContextAtributte("perfilInvestigacion", false);
        util.setContextAtributte("perfilAdjudicacion", false);
        return "rcbDetalle.xhtml?faces-redirect=true";

    }

    public void onTabChange(TabChangeEvent event) {
        Tab activeTab = event.getTab();
        System.out.println("dd" + activeTab.getTitle());
    }

    public void limpiarFrmNuevo() {
        System.out.println("Entro a limpiar");
        listRcbinsumosDto.clear();
        RequestContext.getCurrentInstance().reset("formRCB:pnlRCB");
        RequestContext.getCurrentInstance().reset("formRCB:tablaRcbInsumo");
        init();

    }

    public void eliminarInsumos() {
        listRcbinsumosDto.clear();
        rcb.setImporteTotal(null);
    }

    public void cargarCatalogoInsumos() {
        listRcbinsumosDto = new ArrayList<>();
        System.out.println("idArea:" + idArea);
        System.out.println("getRcbGrupoList: " + gruposSeleccionados.size());
        List<Insumos> listaInsumos = new ArrayList<>();
        if (idArea != 0) {
            System.out.println("idArea:" + idArea);
            listaInsumos = insumoService.traeListaInsumosPorAreaGrupos(idArea, gruposSeleccionados);
        } else {
            listaInsumos = insumoService.traeListaInsumosPorAreaGrupos(usuarios.getIdArea().getIdArea(), gruposSeleccionados);
        }
        BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
        for (Insumos insumo : listaInsumos) {
            RcbInsumos rcbInsumos = new RcbInsumos();
            rcbInsumos.setIdInsumo(insumo);
            rcbInsumos.setExistencias(0);
            rcbInsumos.setConsumoPromedio(0);
            rcbInsumos.setCantidadPiezas(0);
            rcbInsumos.setPrecioUnitario(BigDecimal.ZERO);
            rcbInsumos.setImporte(rcbInsumos.getPrecioUnitario().multiply(new BigDecimal(rcbInsumos.getCantidadPiezas())));
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

            RcbInsumosViewDto rcbInsumosDto = new RcbInsumosViewDto();
            rcbInsumosDto.setRcbInsumo(rcbInsumos);
            rcbInsumosDto.setRcbInsumoSeleccionado(Boolean.TRUE);
            rcbInsumosDto.setDescripcionCorta(insumo.getDescripcion().substring(0, 20));
            listRcbinsumosDto.add(rcbInsumosDto);
            tmpImporteTotal = tmpImporteTotal.add(rcbInsumos.getImporte());
            System.out.println("tmpImporteTotal:" + tmpImporteTotal);
        }
        rcb.setImporteTotal(tmpImporteTotal);

    }

    public void cargarRCBAnterior() {
        System.out.println("usuario area: " + usuarios.getIdArea().getIdArea());
        Integer ultimaRCB = rcbInsumosService.traerUltimaRcbPorArea(usuarios.getIdArea().getIdArea(), rcb.getIdTipoCompra().getIdTipoCompra());
        System.out.println("ultimaRCB: " + ultimaRCB);
        System.out.println("rcb.getIdTipoCompra().getIdTipoCompra(): " + rcb.getIdTipoCompra().getIdTipoCompra());
        if (ultimaRCB == null) {
            mensaje.mensaje("No existen ningun RCB registrado en el sistema", "amarillo");
        } else {
            if (listRcbinsumosDto != null) {
                listRcbinsumosDto.clear();
            } else {
                listRcbinsumosDto = new ArrayList<>();
            }
            BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
            System.out.println("traerRcbId ultimaRCB: " + ultimaRCB);
            List<RcbInsumos> listRcbInsumos = rcbService.traerRcbId(ultimaRCB).getRcbInsumosList();
            System.out.println("traerRcbId listRcbInsumos: " + listRcbInsumos.size());
            for (RcbInsumos rcbInsumo : listRcbInsumos) {
                RcbInsumosViewDto rcbInsumosDto = new RcbInsumosViewDto();
                rcbInsumosDto.setRcbInsumo(rcbInsumo);
                rcbInsumosDto.setRcbInsumoSeleccionado(Boolean.TRUE);
                rcbInsumosDto.setDescripcionCorta(rcbInsumo.getIdInsumo().getDescripcion().substring(0, 20));
                listRcbinsumosDto.add(rcbInsumosDto);
                tmpImporteTotal = tmpImporteTotal.add(rcbInsumo.getImporte());
            }
            rcb.setImporteTotal(tmpImporteTotal);

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
        Boolean ivaActivo = false;
        Insumos insumo = (Insumos) event.getObject();
        System.out.println("insumo: " + insumo.getClave());
        if (listRcbinsumosDto != null) {
            for (RcbInsumosViewDto rcbtdo : listRcbinsumosDto) {
                if (rcbtdo.getRcbInsumo().getIdInsumo().equals(insumo)) {
                    existeInsumo = true;
                    break;
                }
                if (insumo.getIdTipoInsumos().getIvaActivo() != rcbtdo.getRcbInsumo().getIdInsumo().getIdTipoInsumos().getIvaActivo()) {
                    ivaActivo = true;
                    break;
                }
            }
        } else {
            listRcbinsumosDto = new ArrayList<>();
        }
        if (existeInsumo) {
            mensaje.mensaje("El insumo no puede ser agregado, se encuentra dentro de la lista de insumos", "amarillo");
        } else if (ivaActivo) {
            mensaje.mensaje("El insumo debe pertenecer al mismo tipo de insumo (Con iva o sin iva)", "amarillo");
        } else {
            RcbInsumos rcbInsumos = new RcbInsumos();
            rcbInsumos.setIdInsumo(insumo);
//            Integer existencias = 0;
//            List<ExistenciaReservaClaveCenadi> list = existenciaReservaClaveCenadiService.detalleExistenciaReservaClaveCenadi(insumo.getClave());
//            if (list != null) {
//                existencias = list.get(0).getDisponibleDeReserva();
//            }
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

            RcbInsumosViewDto rcbInsumosDto = new RcbInsumosViewDto();
            rcbInsumosDto.setRcbInsumo(rcbInsumos);
            rcbInsumosDto.setRcbInsumoSeleccionado(Boolean.TRUE);
            rcbInsumosDto.setDescripcionCorta(insumo.getDescripcion().substring(0, 20));
            System.out.println("setDescripcionCorta" + rcbInsumosDto.getDescripcionCorta());
            listRcbinsumosDto.add(rcbInsumosDto);
        }
    }

    public String goToNextpage() {
        return "rcb";
    }

    public void guardarRCB() {

        rcb.setActivo(1);
        rcb.setImporteTotal(BigDecimal.ZERO);
        rcb.setIdEstatus(new Estatus(PlaneacionEstatusEnum.RCB_CREADA.getValue()));
        rcb.setIdArea(new Area(idArea));
        rcb.setIdUnidadResponsable(null);
        for (String cadena : gruposSeleccionados) {
            RcbGrupo rcbGrupo = new RcbGrupo();
            rcbGrupo.setIdRcb(rcb);
            rcbGrupo.setIdGrupo(new Grupo(Integer.parseInt(cadena)));
            listaRcbGrupo.add(rcbGrupo);
        }
        DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        String strFechaElaboracion = df.format(rcb.getFechaElaboracion());
        List<RcbInsumos> listaRcbInsumos = new ArrayList<>();
        if (listRcbinsumosDto != null) {
            if (listRcbinsumosDto.size() > 0) {
                if (validaGrupos()) {
                    for (RcbInsumosViewDto next : listRcbinsumosDto) {
                        if (next.getRcbInsumoSeleccionado()) {
                            next.getRcbInsumo().setActivo(1);
                            next.getRcbInsumo().setIdRcb(rcb);
                            listaRcbInsumos.add(next.getRcbInsumo());
                        }
                    }
                    if (listaRcbInsumos.size() > 0) {
                        rcb.setRcbInsumosList(listaRcbInsumos);
                        rcb.setRcbGrupoList(listaRcbGrupo);
                        rcb.setNumero("-1");
                        if (rcb.getIdJefatura().getIdJefatura() == null) {
                            rcb.setIdJefatura(null);
                        }
                        if (rcbService.guardarRcb(rcb)) {
                            rcb.setNumero("RCB-" + rcb.getIdRcb());
                            tmpIdRcbView = rcbService.actualizarRcbMerge(rcb).getIdRcb();
                            bitacora.setFecha(new Date());
                            bitacora.setIdEstatus(rcb.getIdEstatus().getIdEstatus());
                            bitacora.setDescripcion(bitacoraUtil.detalleGuardaroBitacora("rcb " + rcb.getIdRcb()));
                            bitacora.setIdUsuarios(usuarios.getIdUsuario());
                            bitacora.setIdTareaId(rcb.getIdRcb());
                            bitacora.setIdModulos(PlaneacionEstatusEnum.ID_MODULO_RCB.getValue());
                            bitacoraService.guardarEnBitacora(bitacora);
                            mensaje.mensaje(mensaje.datos_guardados + ",el número de Rcb generado es: " + rcb.getNumero(), "verde");
                            if (rcb.getIdUnidadResponsable() == null) {
                                rcb.setIdUnidadResponsable(new UnidadResponsable());
                            }
                            RequestContext.getCurrentInstance().execute("PF('wdContCaptura').show()");
                        } else {
                            mensaje.mensaje("Ocurrio un error al almacenar la RCB, recargar página y volver a intentarlo nuevamente, si el error persiste contactar al administrador", "rojo");
                        }
                    } else {
                        mensaje.mensaje("Seleccione los insumos por requerir", "rojo");
                    }
                } else {
                    mensaje.mensaje("El grupo de los insumos no corresponde a los grupos seleccionados en el RCB, verifique", "rojo");
                }
            } else {
                mensaje.mensaje("La lista de insumos no puede estar vacía", "rojo");
            }
        } else {
            mensaje.mensaje("La lista de insumos no puede estar vacía", "rojo");
        }

    }

    public Boolean validaGrupos() {

        List<String> al = new ArrayList<>();
        Set<String> hs = new HashSet<>();
        for (RcbInsumosViewDto rcbinsumosDto1 : listRcbinsumosDto) {
            if (rcbinsumosDto1.getRcbInsumoSeleccionado()) {
                al.add(String.valueOf(rcbinsumosDto1.getRcbInsumo().getIdInsumo().getIdGrupo().getIdGrupo()));
            }
        }
        hs.addAll(al);
        al.clear();
        al.addAll(hs);

        final Set<String> s1 = new HashSet<>(al);
        final Set<String> s2 = new HashSet<>(gruposSeleccionados);

        return s1.equals(s2);

    }

    public void handleFileUpload(FileUploadEvent event) {
        listRcbinsumosDto = new ArrayList<>();
        archivosBean.setUploadedfile(event.getFile());
        System.out.println(event.getFile().getFileName() + " is uploaded.");
//        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, message);
        DateFormat date = new SimpleDateFormat("ddMMyyyy");
        String strFecha = date.format(new Date());
        String fechaNombreArchivo = "/" + strFecha + "_" + archivosBean.getUploadedfile().getFileName();
        archivosBean.guardaArchivo(archivosBean.getUploadedfile(), fechaNombreArchivo);
        List<RcbInsumos> lisExcel = archivosBean.readRCBExcelFile(new File(ArchivosUtilidades.PATHCARGAMASIVA + fechaNombreArchivo));
        StringBuffer clavesNotFound = new StringBuffer();
        BigDecimal tmpImporteTotal = new BigDecimal(BigInteger.ZERO);
        for (RcbInsumos rcbInsumos : lisExcel) {
            List<Insumos> validaInsumo = insumoService.buscarInsumosPorClave(rcbInsumos.getClaveInsumo());
            if (validaInsumo != null && validaInsumo.size() > 0) {
                Insumos insumo = validaInsumo.get(0);
                rcbInsumos.setIdInsumo(insumo);
                rcbInsumos.setExistencias(0);
                rcbInsumos.setConsumoPromedio(0);
                //rcbInsumos.setCantidadPiezas(0);
                //rcbInsumos.setPrecioUnitario(BigDecimal.ZERO);
                rcbInsumos.setImporte(rcbInsumos.getPrecioUnitario().multiply(new BigDecimal(rcbInsumos.getCantidadPiezas())));
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

                RcbInsumosViewDto rcbInsumosDto = new RcbInsumosViewDto();
                rcbInsumosDto.setRcbInsumo(rcbInsumos);
                rcbInsumosDto.setRcbInsumoSeleccionado(Boolean.TRUE);
                rcbInsumosDto.setDescripcionCorta(insumo.getDescripcion().substring(0, 20));
                listRcbinsumosDto.add(rcbInsumosDto);
                tmpImporteTotal = tmpImporteTotal.add(rcbInsumos.getImporte());
            } else {
                clavesNotFound.append(rcbInsumos.getClaveInsumo());
                clavesNotFound.append(", ");
                //mensaje.mensaje("No se encontro en el catálogo:" + rcbInsumos.getClaveInsumo(), "amarillo");
            }
        }
        rcb.setImporteTotal(tmpImporteTotal);
        if (listRcbinsumosDto.size() > 0) {
            mensaje.mensaje("Se cargo el archivo correctamente", "verde");
        }
        if (!clavesNotFound.toString().equals("")) {
            mensaje.mensaje("No se encontro en el catálogo las siguientes claves:" + clavesNotFound.toString(), "amarillo");
        }
    }
    
    private StreamedContent file;
    
    public StreamedContent getFile(int opcion) {
        if (opcion == 1) {
            InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/layouts/RcbLayout.xls");
            file = new DefaultStreamedContent(stream, "application/vnd.ms-excel", "RcbLayout.xls");
        } 
        return file;
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

    public Boolean getHabilitarNep() {
        return habilitarNep;
    }

    public void setHabilitarNep(Boolean habilitarNep) {
        this.habilitarNep = habilitarNep;
    }

    public Boolean getHabilitarOficioSuficiencia() {
        return habilitarOficioSuficiencia;
    }

    public void setHabilitarOficioSuficiencia(Boolean habilitarOficioSuficiencia) {
        this.habilitarOficioSuficiencia = habilitarOficioSuficiencia;
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

    public BitacoraTareaEstatus getBitacora() {
        return bitacora;
    }

    public void setBitacora(BitacoraTareaEstatus bitacora) {
        this.bitacora = bitacora;
    }

    public List<Area> getAreasList() {
        return areasList;
    }

    public void setAreasList(List<Area> areasList) {
        this.areasList = areasList;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Perfiles getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfiles perfil) {
        this.perfil = perfil;
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

    public ArchivosBean getArchivosBean() {
        return archivosBean;
    }

    public void setArchivosBean(ArchivosBean archivosBean) {
        this.archivosBean = archivosBean;
    }

    public List<Jefatura> getListaJefatura() {
        return listaJefatura;
    }

    public void setListaJefatura(List<Jefatura> listaJefatura) {
        this.listaJefatura = listaJefatura;
    }

}
