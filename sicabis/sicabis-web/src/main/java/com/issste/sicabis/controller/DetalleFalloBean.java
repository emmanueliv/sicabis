package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
import com.issste.sicabis.ejb.DTO.FalloPropuestaProcDTO;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.FalloService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.ProcedimientosRcbService;
import com.issste.sicabis.ejb.ln.ProveedorService;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.ln.TipoDocumentosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Fallos;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import com.issste.sicabis.ejb.modelo.Propuestas;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.BitacoraUtil;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

public class DetalleFalloBean {

    @EJB
    private InsumosService insumosService;

    @EJB
    private ProveedorService proveedorService;

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private EstatusService estatusService;

    @EJB
    private ProcedimientosRcbService procedimientosRcbService;

    @EJB
    private FalloService falloService;

    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;

    private Procedimientos procedimientos;
    private Usuarios usuarios;
    private Fallos fallos;
    private FalloPropuestaProcDTO falloPropuestaProcDTO;
    private RespositorioDocumentos respositorioDocumentos;
    private RepositorioDocumentosDTO repositorioDocumentosDTO;
    private RepositorioDocumentosDTO repositorioDocumentosDTOAux;

    private boolean botonActualizar = true;
    private boolean mensajeBorrar;
    private boolean messageGuardar = true;
    private List<ProcedimientoRcb> listaProcRcb;
    private List<Procedimientos> listaProc;
    private String numeroProcedimiento;
    private List<FalloPropuestaProcDTO> listaFalloPropProc;
    private List<FalloPropuestaProcDTO> listaFalloPropProcFilter;
    private List<FalloPropuestaProcDTO> listaFalloPropProcSelect;
    private Integer idProveedor;
    private Integer idFalloPropuestaProcDTO;
    private Integer porcientoTotal = 0;
    private Integer ultimoId = 0;
    private boolean mensajeTabla;
    private Integer idTipoDocumento;
    private List<TipoDocumentos> listaTipoDocs;
    private List<RespositorioDocumentos> listaRepoDocs;
    private StreamedContent file;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private UploadedFile uploadedfile;
    private boolean barchivos;
    private List<Estatus> listaEstatusFallo;
    private List<FalloProcedimientoRcb> listaFalloProcRcb;
    private String numeroFalloB;
    private List<Fallos> listaFallos;
    private String numeroProcedimientoB;
    private boolean btipocompra;
    private boolean bdesierta;
    private List<Proveedores> listaProv;
    private List<Proveedores> listProvIssste;

    private BitacoraTareaEstatus bitacora;
    private BitacoraUtil bitacoraUtil = new BitacoraUtil();

    private String numeroFallo;
    private final Integer idTareaProc = 4;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();

    @EJB
    private TipoDocumentosService tipoDocumentosService;

    public DetalleFalloBean() {

    }

    @PostConstruct
    public void init() {
        bitacora = new BitacoraTareaEstatus();
        listaProcRcb = new ArrayList();
        listaProc = new ArrayList();
        listaFalloPropProc = new ArrayList();
        listaFalloProcRcb = new ArrayList();
        listProvIssste = new ArrayList();
        listaFalloPropProcSelect = new ArrayList();
        listaProv = new ArrayList();
        listaEstatusFallo = estatusService.getEstatusByTarea(idTareaProc);
        fallos = new Fallos();
        fallos = (Fallos) util.getContextAtributte("fallos");
        listaTipoDocs = tipoDocumentosService.obtenerByIdTarea(idTareaProc);
        idTipoDocumento = -1;
        numeroProcedimiento = fallos.getNumeroProcedimiento();
        numeroFallo = fallos.getNumeroFallo();
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        listaFalloPropProc = (List<FalloPropuestaProcDTO>) archivosUtilidades.obtieneObjetoSerializable(fallos.getListaFallosDto());
        listaFalloPropProcSelect = (List<FalloPropuestaProcDTO>) archivosUtilidades.obtieneObjetoSerializable(fallos.getListaFallosDtoSelect());
        if (listaFalloPropProcSelect != null) {
            listaProcRcb = procedimientosRcbService.obtenerByNumeroProc(numeroProcedimiento);
            if (listaProcRcb != null) {
                listaProc.add(listaProcRcb.get(0).getIdProcedimiento());
                if (listaProcRcb.get(0).getIdRcbInsumos().getIdRcb().getIdTipoCompra().getNombre().equals("ISSSTE")) {
                    btipocompra = true;
                } else {
                    btipocompra = false;
                }
            }
            if (fallos.getIdEstatus().getIdEstatus() == 42) {
                barchivos = true;
                botonActualizar = true;
                buscarArchivosByIdProcesoIdTarea(fallos.getIdFallo(), idTareaProc);
            }
            if (listaProcRcb.get(0).getIdProcedimiento().getIdTipoCompra().getIdTipoCompra() == 1) {
                for (FalloPropuestaProcDTO fpd : listaFalloPropProc) {
                    for (Propuestas p : fpd.getListaPropuestas()) {
                        this.listProvIssste.add(p.getIdProveedor());
                    }
                }
            } else {
                this.listaProv = proveedorService.obtenerByAutorizado();
            }

        }
    }

    public void cambiaProveedorClave2(Integer idFalloPropuestaProcDTO) {
        this.idFalloPropuestaProcDTO = idFalloPropuestaProcDTO;
    }

    public void cambiaPorcentaje(FalloPropuestaProcDTO fppd) {
        porcientoTotal = 0;
        for (FalloPropuestaProcDTO fppdAux : this.listaFalloPropProc) {
            if (fppd.getIdFalloPropuestaProcDTO().intValue() == fppdAux.getIdFalloPropuestaProcDTO().intValue()
                    || fppdAux.getIdFalloPropuestaProcDTO().intValue() == fppd.getIdPadre()) {
                porcientoTotal = porcientoTotal + fppdAux.getFalloProcedimientoRcb().getPorcentajeAdjudicacion();
            }
        }
        this.idFalloPropuestaProcDTO = fppd.getIdFalloPropuestaProcDTO();
        if (porcientoTotal > 100) {
            this.actualizaDatos(fppd.getFalloProcedimientoRcb().getIdProveedor(), 0, -1, false);
            mensajeTabla = true;
            mensaje.mensaje("El porcentaje por ID es mayor al 100%", "amarillo");
        } else {
            Integer piezas = util.calculaPorciento(fppd.getFalloProcedimientoRcb().getPorcentajeAdjudicacion(), fppd.getPiezas());
            Integer posicion = 0;
            if (fppd.getIdPadre().intValue() == 0) {
                posicion = fppd.getIdFalloPropuestaProcDTO();
            } else {
                posicion = fppd.getIdPadre();
            }
            if (porcientoTotal == 100) {
                this.actualizaDatos(fppd.getFalloProcedimientoRcb().getIdProveedor(), piezas, posicion, false);
            } else {
                this.actualizaDatos(fppd.getFalloProcedimientoRcb().getIdProveedor(), piezas, posicion, true);
            }
        }

    }

    public void onEditLabel(CellEditEvent event) {
        DataTable s = (DataTable) event.getSource();
        FalloPropuestaProcDTO d = (FalloPropuestaProcDTO) s.getRowData();
        cambiaProveedorClave2(d.getIdFalloPropuestaProcDTO());
        this.actualizaDatos(d.getFalloProcedimientoRcb().getIdProveedor(), -1, -1, false);
    }

    public void actualizaDatos(Proveedores prov, Integer piezas, Integer posicion, boolean opcion) {
        Integer idClasificacion = 0;
        Integer idProveedor = prov.getIdProveedor();
        prov = proveedorService.getByNumeroProveedor(prov.getNumeroProveedor());
        List<FalloPropuestaProcDTO> listaFalloPropProc = new ArrayList();
        for (FalloPropuestaProcDTO fppd : this.listaFalloPropProc) {
            if (posicion.intValue() != -1) {
                if (fppd.getIdFalloPropuestaProcDTO().intValue() == posicion.intValue()) {
                    fppd.setBopcionAgrega(opcion);
                    fppd.setPorciento(porcientoTotal);
                }
            }
            if (fppd.getIdFalloPropuestaProcDTO().intValue() == idFalloPropuestaProcDTO.intValue()) {
                if (piezas.intValue() != -1) {
                    if (piezas.intValue() == 0) {
                        fppd.setPrecioUnitario(BigDecimal.ZERO);
                        fppd.setDescuentoOtorgado(BigDecimal.ZERO);
                        FalloProcedimientoRcb fpr = new FalloProcedimientoRcb();
                        fpr.setIdProveedor(prov);
                        fpr.setPrecioUnitario(BigDecimal.ZERO);
                        fpr.setPorcentajeAdjudicacion(0);
                        fppd.setFalloProcedimientoRcb(fpr);
                        fppd.setImporte(BigDecimal.ZERO);
                    }
                    fppd.setPiezasAdjudicadas(piezas);
                }
                if (prov.getNumeroProveedor().intValue() != -1) {
                    idClasificacion = fppd.getProcedimientoRcb().getIdProcedimiento().getIdClasificacionProcedimiento().getIdClasificacionProcedimiento();
                    switch (idClasificacion.intValue()) {
                        case 1:
                            if (btipocompra) {
                                for (Propuestas p : fppd.getListaPropuestas()) {
                                    if (p.getIdProveedor().getIdProveedor().intValue() == idProveedor.intValue()) {
                                        fppd.setPrecioUnitario(p.getIdProcedimientoRcb().getPrecioUnitario());
                                        fppd.setDescuentoOtorgado(p.getDescuentoOtorgado());
                                        FalloProcedimientoRcb fpr = new FalloProcedimientoRcb();
                                        fpr.setIdProveedor(prov);
                                        fpr.setPrecioUnitario(util.obtieneDescuento(p.getIdProcedimientoRcb().getPrecioUnitario(), p.getDescuentoOtorgado()));
                                        fppd.setFalloProcedimientoRcb(fpr);
                                        fppd.setImporte(fpr.getPrecioUnitario().multiply(new BigDecimal(fppd.getPiezasAdjudicadas())));
                                        break;
                                    }
                                }
                            } else {
                                FalloProcedimientoRcb fpr = fppd.getFalloProcedimientoRcb();
                                fpr.setIdProveedor(prov);
                                fppd.setFalloProcedimientoRcb(fpr);
                            }
                            break;
                        case 2:
                            if (btipocompra) {
                                for (Propuestas p : fppd.getListaPropuestas()) {
                                    if (p.getIdProveedor().getIdProveedor().intValue() == idProveedor.intValue()) {
                                        fppd.setPrecioUnitario(p.getIdProcedimientoRcb().getPrecioUnitario());
                                        fppd.setDescuentoOtorgado(BigDecimal.ZERO);
                                        FalloProcedimientoRcb fpr = new FalloProcedimientoRcb();
                                        fpr.setIdProveedor(prov);
                                        fpr.setPrecioUnitario(util.obtieneDescuento(p.getIdProcedimientoRcb().getPrecioUnitario(), BigDecimal.ZERO));
                                        fppd.setFalloProcedimientoRcb(fpr);
                                        fppd.setImporte(fpr.getPrecioUnitario().multiply(new BigDecimal(fppd.getPiezasAdjudicadas())));
                                        break;
                                    }
                                }
                            } else {
                                FalloProcedimientoRcb fpr = fppd.getFalloProcedimientoRcb();
                                fpr.setIdProveedor(prov);
                            }
                            break;
                        case 3:
                            if (btipocompra) {
                                for (Propuestas p : fppd.getListaPropuestas()) {
                                    if (p.getIdProveedor().getIdProveedor().intValue() == idProveedor.intValue()) {
                                        fppd.setPrecioUnitario(fppd.getProcedimientoRcb().getImporte());
                                        fppd.setDescuentoOtorgado(p.getDescuentoOtorgado());
                                        FalloProcedimientoRcb fpr = new FalloProcedimientoRcb();
                                        fpr.setIdProveedor(prov);
                                        fpr.setPrecioUnitario(util.obtieneDescuento(fppd.getProcedimientoRcb().getImporte(), p.getDescuentoOtorgado()));
                                        fppd.setFalloProcedimientoRcb(fpr);
                                        fppd.setImporte(fpr.getPrecioUnitario().multiply(new BigDecimal(fppd.getPiezasAdjudicadas())));
                                        break;
                                    }
                                }
                            } else {
                                FalloProcedimientoRcb fpr = fppd.getFalloProcedimientoRcb();
                                fpr.setIdProveedor(prov);
                            }
                            break;
                    }
                }
            }
            listaFalloPropProc.add(fppd);
        }

        this.listaFalloPropProc.clear();
        this.listaFalloPropProc = listaFalloPropProc;
    }

    public void agregaRenglon(FalloPropuestaProcDTO fppd) {
        boolean bandera = false;
        FalloPropuestaProcDTO aux = new FalloPropuestaProcDTO();
        List<FalloPropuestaProcDTO> listaFalloPropProc = new ArrayList();
        for (FalloPropuestaProcDTO fppdux : this.listaFalloPropProc) {
            if (fppdux.getIdFalloPropuestaProcDTO().intValue() == fppd.getIdFalloPropuestaProcDTO().intValue()) {
                bandera = true;
                porcientoTotal = fppd.getFalloProcedimientoRcb().getPorcentajeAdjudicacion();
                fppdux.setBopcionAgrega(false);
                aux.setPiezas(fppd.getPiezas());
                aux.setPiezasAdjudicadas(util.calculaPorciento((100 - porcientoTotal), fppd.getPiezas()));
                aux.setIdPadre(fppd.getIdFalloPropuestaProcDTO());
                aux.setProcedimientoRcb(fppd.getProcedimientoRcb());
                aux.setListaPropuestas(fppd.getListaPropuestas());
                aux.setBopcionQuitar(true);
                ultimoId = ultimoId + 1;
                aux.setIdFalloPropuestaProcDTO(ultimoId);
                aux.setDescuentoOtorgado(BigDecimal.ZERO);
                aux.setPrecioUnitario(BigDecimal.ZERO);
                aux.setImporte(BigDecimal.ZERO);

                FalloProcedimientoRcb fpr = new FalloProcedimientoRcb();
                Proveedores prov = new Proveedores(-1);
                prov.setNumeroProveedor(-1);
                fpr.setIdProveedor(prov);
                fpr.setPorcentajeAdjudicacion(100 - porcientoTotal);
                fpr.setCantidadModificada(util.calculaPorciento((100 - porcientoTotal), fppd.getPiezas()));
                fpr.setPrecioUnitario(BigDecimal.ZERO);
                aux.setFalloProcedimientoRcb(fpr);
            }
            listaFalloPropProc.add(fppdux);
            if (bandera) {
                listaFalloPropProc.add(aux);
                bandera = false;
            }
        }
        this.listaFalloPropProc.clear();
        this.listaFalloPropProc = listaFalloPropProc;
    }

    public void quitaRenglon(FalloPropuestaProcDTO fppd) {
        List<FalloPropuestaProcDTO> listaFalloPropProc = new ArrayList();
        for (FalloPropuestaProcDTO fppdaux : this.listaFalloPropProc) {
            if (fppd.getIdPadre().intValue() == fppdaux.getIdFalloPropuestaProcDTO().intValue()) {
                fppdaux.setBopcionAgrega(true);
                listaFalloPropProc.add(fppdaux);
            } else if (fppd.getIdFalloPropuestaProcDTO().intValue() != fppdaux.getIdFalloPropuestaProcDTO()) {
                listaFalloPropProc.add(fppdaux);
            }
        }
        this.listaFalloPropProc.clear();
        this.listaFalloPropProc = listaFalloPropProc;
    }

    public void existeNumeroFallo() {
        if (!numeroFallo.equals(fallos.getNumeroFallo())) {
            boolean bandera = this.validaNumeroFallo();
        }
    }

    public boolean validaNumeroFallo() {
        boolean bandera = true;
        Fallos pAux = falloService.obtenerByNumeroFallo(fallos.getNumeroFallo());
        if (pAux != null) {
            mensaje.mensaje("El número de fallo ya esta registrado", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean valida() {
        boolean bandera = true;
        if (fallos.getNumeroFallo().equals("")) {
            mensaje.mensaje("Debes ingresar el número del fallo", "amarillo");
            bandera = false;
        } else {
            if (!numeroFallo.equals(fallos.getNumeroFallo())) {
                bandera = this.validaNumeroFallo();
            }
        }
        if (fallos.getIdEstatus().getIdEstatus() == -1) {
            mensaje.mensaje("Debes seleccionar un estatus del fallo", "amarillo");
            bandera = false;
        }
        if (fallos.getIdEstatus().getIdEstatus() == 42) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlg1').show();");
        }

        return bandera;
    }

    public void validaGuardado() {
        if (fallos.getIdEstatus().getIdEstatus() == 42) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlg1').show();");
        } else {
            this.actualizaFallo();
        }
    }

    public void actualizaFallo() {
        Insumos insumo = new Insumos();
        BigDecimal importeTotal = BigDecimal.ZERO;
        int autorizado = 0;
        int i = 0;
        if (valida()) {
            ProcedimientoRcb procedimientoRcb = null;
            List<FalloPropuestaProcDTO> listaFalloPropProcAux = new ArrayList();
            boolean bandera = false;
            boolean bandera1 = true;
            boolean bandProv = true;
            boolean bdesierta = true;
            String idClaves = "";
            if (fallos.getIdEstatus().getIdEstatus() == 42) {
                for (FalloPropuestaProcDTO fppd : listaFalloPropProc) {
                    for (ProcedimientoRcb pr : listaProcRcb) {
                        if (btipocompra) {
                            for (Propuestas p : pr.getPropuestasList()) {
                                autorizado = p.getIdProveedor().getAutorizado();
                                fppd.getFalloProcedimientoRcb().getIdProveedor().setAutorizado(autorizado);
                                if (fppd.getFalloProcedimientoRcb().getIdProveedor().getNumeroProveedor().intValue() == -1) {
                                    fppd.getFalloProcedimientoRcb().getIdProveedor().setAutorizado(0);
                                }
                                if (fppd.getFalloProcedimientoRcb().getIdProveedor().getAutorizado() == 0//fallo
                                        || fppd.getFalloProcedimientoRcb().getIdProveedor().getNumeroProveedor().intValue() == -1) {
                                    bandProv = false;
                                    if (listaFalloPropProcSelect.size() != 0) {
                                        bandProv = true;
                                    }
                                    if (idClaves.equals("")) {
                                        idClaves = "" + fppd.getProcedimientoRcb().getIdProcedimientoRcb();
                                    } else {
                                        idClaves = idClaves + ", " + fppd.getProcedimientoRcb().getIdProcedimientoRcb();
                                    }
                                }
                            }
                        } else {
                            if (fppd.getFalloProcedimientoRcb().getIdProveedor().getNumeroProveedor().intValue() == -1) {
                                bandProv = false;
                                if (idClaves.equals("")) {
                                    idClaves = "" + fppd.getProcedimientoRcb().getIdProcedimientoRcb();
                                } else {
                                    idClaves = idClaves + ", " + fppd.getProcedimientoRcb().getIdProcedimientoRcb();
                                }
                            }
                        }
                    }
                }
                if (bandProv) {
                    if (listaFalloPropProcSelect.size() == 0) {
                        listaFalloPropProcSelect = (List<FalloPropuestaProcDTO>) archivosUtilidades.obtieneObjetoSerializable(fallos.getListaFallosDtoSelect());
                    }
                    for (FalloPropuestaProcDTO fppd : listaFalloPropProc) {
                        bandera1 = true;
                        if (fppd.getIdPadre().intValue() == 0) {
                            bandera = true;
                        }
                        if (bandera) {
                            for (FalloPropuestaProcDTO fppds : listaFalloPropProcSelect) {
                                if (fppd.getIdFalloPropuestaProcDTO().intValue() == fppds.getIdFalloPropuestaProcDTO().intValue()) {
                                    procedimientoRcb = new ProcedimientoRcb();
                                    procedimientoRcb = fppds.getProcedimientoRcb();
                                    procedimientoRcb.setDesierta(1);
                                    procedimientosRcbService.actualizaProcedimientoRcb(procedimientoRcb);
                                    bandera = false;
                                    bandera1 = false;
                                    bdesierta = false;
                                    break;
                                }
                            }
                            if (bandera1) {
                                FalloProcedimientoRcb fpr = fppd.getFalloProcedimientoRcb();
                                fpr.setActivo(1);
                                fpr.setCantidadAgregadaConvenio(0);
                                fpr.setCompletadoContrato(0);
                                fpr.setSuministradoOrden(0);
                                fpr.setPrecioUnitarioOriginal(BigDecimal.ZERO);
                                fpr.setUnidadPiezaConvenio("");
                                fpr.setCantidadPiezas(fppd.getPiezas());
                                fpr.setCantidadModificada(fppd.getPiezasAdjudicadas());
                                fpr.setFechaAlta(new Date());
                                fpr.setPrecioUnitario(fppd.getProcedimientoRcb().getPrecioUnitario());
                                fpr.setUsuarioAlta(usuarios.getNombre());
                                fpr.setIdProcedimientoRcb(fppd.getProcedimientoRcb());
                                fpr.setImporte(util.multiplica(fpr.getPrecioUnitario(), fpr.getCantidadModificada()));
                                fpr.setIdFallo(fallos);
                                listaFalloProcRcb.add(fpr);
                                fppd.setFalloProcedimientoRcb(fpr);
                                importeTotal = importeTotal.add(fppd.getImporte());
                                procedimientoRcb = new ProcedimientoRcb();
                                procedimientoRcb = fppd.getProcedimientoRcb();
                                if (fppd.getIdPadre() == 0) {
                                    if (fppd.getPorciento().intValue() < 100) {
                                        procedimientoRcb.setCantidadPiezasOriginal(procedimientoRcb.getCantidadPiezas());
                                        procedimientoRcb.setCantidadPiezas(fppd.getPiezas() - fppd.getPiezasAdjudicadas());
                                        procedimientoRcb.setDesiertaParcial(1);
                                        procedimientoRcb.setImporte(new BigDecimal(procedimientoRcb.getCantidadPiezas()).multiply(procedimientoRcb.getPrecioUnitario()));
                                    } else if (fppd.getPorciento().intValue() == 100 && listaFalloPropProcSelect.size() != 0 && bdesierta) {
                                        procedimientoRcb.setDesierta(1);
                                        procedimientoRcb.setDesiertaParcial(1);
                                    } else {
                                        procedimientoRcb.setDesierta(2);
                                        procedimientoRcb.setDesiertaParcial(2);
                                    }
                                    procedimientosRcbService.actualizaProcedimientoRcb(procedimientoRcb);
                                }
                                listaFalloPropProcAux.add(fppd);
                            }
                        }
                    }

                }
                fallos.setFalloProcedimientoRcbList(listaFalloProcRcb);
            }

            if (bandProv) {
                fallos.setImporteTotal(importeTotal);
                fallos.setListaFallosDto(archivosUtilidades.guardaObjetoSerializable(listaFalloPropProc, idTareaProc, 1));
                fallos.setListaFallosDtoSelect(archivosUtilidades.guardaObjetoSerializable(listaFalloPropProcSelect, idTareaProc, 2));
                fallos.setIdEstatus(new Estatus(fallos.getIdEstatus().getIdEstatus()));
                if (falloService.actualizaFallo(fallos)) {
                    for (FalloProcedimientoRcb iterator : fallos.getFalloProcedimientoRcbList()) {
                        insumo = insumosService.obtieneByClave(iterator.getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave());
                        insumo.setPrecioUnitario(iterator.getPrecioUnitario());
                        insumosService.actualiza(insumo);
                    }
                    mensaje.mensaje(mensaje.datos_guardados, "verde");
                    bitacora.setFecha(new Date());
                    bitacora.setIdEstatus(fallos.getIdEstatus().getIdEstatus());
                    bitacora.setDescripcion(bitacoraUtil.detalleGuardaroBitacora("fallo " + fallos.getIdFallo()));
                    bitacora.setIdUsuarios(usuarios.getIdUsuario());
                    bitacora.setIdTareaId(idTareaProc);
                    bitacoraTareaSerice.guardarEnBitacora(bitacora);
                    util.setContextAtributte("fallos", fallos);
                    limpiar();
                } else {
                    mensaje.mensaje(mensaje.error_guardar, "rojo");
                    RequestContext.getCurrentInstance().update("formDialog");
                }
            } else {
                if (btipocompra) {
                    mensaje.mensaje("Debes seleccionar un proveedor o bien seleccionar un proveedor autorizado para las siguientes claves: " + idClaves, "amarillo");
                } else {
                    mensaje.mensaje("Debes seleccionar un proveedor para las siguientes claves: " + idClaves, "amarillo");
                }
            }
        }
    }

    public void cambiaTipoDoc() {
        System.out.println("idTipoDocumento--->" + idTipoDocumento);
    }

    public void guardarArchivos(FileUploadEvent event) {
        uploadedfile = event.getFile();
        boolean bandera = false;
        if (idTipoDocumento != -1) {
            respositorioDocumentos = new RespositorioDocumentos();
            respositorioDocumentos.setActivo(1);
            respositorioDocumentos.setFechaAlta(new Date());
            respositorioDocumentos.setIdProceso(fallos.getIdFallo());
            respositorioDocumentos.setIdTipoDocumento(new TipoDocumentos(idTipoDocumento));
            respositorioDocumentos.setNombre(event.getFile().getFileName());
            respositorioDocumentos.setRuta(archivosUtilidades.PATHFILES);
            respositorioDocumentos.setUsuarioAlta(usuarios.getNombre());
            String nombreArchivo = archivosUtilidades.generaNombreArchivo(uploadedfile, idTareaProc, respositorioDocumentos.getIdProceso());
            respositorioDocumentos.setNombreArchivo(nombreArchivo);
            if (respositorioDocumentosService.guardaProcedimiento(respositorioDocumentos)) {
                mensaje.mensaje(mensaje.datos_guardados, "verde");
                //se envia el archivo y el id tarea (4 es id tarea fallo)
                if (archivosUtilidades.guardaArchivo(uploadedfile, nombreArchivo)) {
                    mensaje.mensaje(mensaje.archivo_bien, "verde");
                    bandera = true;

                } else {
                    mensaje.mensaje(mensaje.archivo_error, "rojo");
                }
            } else {
                mensaje.mensaje(mensaje.error_guardar, "rojo");
            }
        } else {
            mensaje.mensaje(mensaje.tipoDoc_select, "amarillo");
        }
        if (bandera) {
            buscarArchivosByIdProcesoIdTarea(fallos.getIdFallo(), idTareaProc);
        }
    }

    public void buscarArchivosByIdProcesoIdTarea(Integer idProceso, Integer idTarea) {
        listaRepoDocs = respositorioDocumentosService.obtenerByIdProcesoIdTarea(idProceso, idTarea);
        listaRepoDocsDto = new ArrayList();
        if (listaRepoDocs != null) {
            for (RespositorioDocumentos rd : listaRepoDocs) {
                repositorioDocumentosDTO = new RepositorioDocumentosDTO();
                repositorioDocumentosDTO.setIdRespositorioDocumento(rd.getIdRespositorioDocumento());
                repositorioDocumentosDTO.setNombre(rd.getNombre());
                repositorioDocumentosDTO.setNombreArchivo(rd.getNombreArchivo());
                repositorioDocumentosDTO.setIdTipoDocumento(rd.getIdTipoDocumento());
                File file = archivosUtilidades.getFileByName(repositorioDocumentosDTO.getNombreArchivo());
                try {
                    InputStream is = new ByteArrayInputStream(archivosUtilidades.fileToBytes(file));
                    repositorioDocumentosDTO.setFile(new DefaultStreamedContent(is, "text/xml", repositorioDocumentosDTO.getNombre()));
                } catch (IOException ex) {
                    Logger.getLogger(DetalleProcedimientoBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                listaRepoDocsDto.add(repositorioDocumentosDTO);
            }
        }
    }

    public void validaBorrarArchivo(RepositorioDocumentosDTO repositorioDocumentosDTO) {
        repositorioDocumentosDTOAux = repositorioDocumentosDTO;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg2').show();");
    }

    public void borrarArchivo() {
        List<RepositorioDocumentosDTO> listaRepoDocsDtoAux = new ArrayList();
        for (RepositorioDocumentosDTO rdd : listaRepoDocsDto) {
            if (rdd != repositorioDocumentosDTOAux) {
                listaRepoDocsDtoAux.add(rdd);
            } else {
                respositorioDocumentosService.borrarByIdRespositorioDocumento(rdd.getIdRespositorioDocumento());
                File file = archivosUtilidades.getFileByName(rdd.getNombreArchivo());
                file.delete();
            }
        }
        listaRepoDocsDto = listaRepoDocsDtoAux;
    }

    public void limpiar() {
        RequestContext.getCurrentInstance().reset("formFallo");
        init();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg1').hide();");
    }

    public String verDetalleProc() {
        util.setContextAtributte("procedimientos", this.procedimientos);
        return "detalleProcedimiento.xhtml?faces-redirect=true";
    }

    public Fallos getFallos() {
        return fallos;
    }

    public void setFallos(Fallos fallos) {
        this.fallos = fallos;
    }

    public boolean isBotonActualizar() {
        return botonActualizar;
    }

    public void setBotonActualizar(boolean botonActualizar) {
        this.botonActualizar = botonActualizar;
    }

    public boolean isMensajeBorrar() {
        return mensajeBorrar;
    }

    public void setMensajeBorrar(boolean mensajeBorrar) {
        this.mensajeBorrar = mensajeBorrar;
    }

    public boolean isMessageGuardar() {
        return messageGuardar;
    }

    public void setMessageGuardar(boolean messageGuardar) {
        this.messageGuardar = messageGuardar;
    }

    public List<Procedimientos> getListaProc() {
        return listaProc;
    }

    public void setListaProc(List<Procedimientos> listaProc) {
        this.listaProc = listaProc;
    }

    public String getNumeroProcedimiento() {
        return numeroProcedimiento;
    }

    public void setNumeroProcedimiento(String numeroProcedimiento) {
        this.numeroProcedimiento = numeroProcedimiento;
    }

    public List<FalloPropuestaProcDTO> getListaFalloPropProc() {
        return listaFalloPropProc;
    }

    public void setListaFalloPropProc(List<FalloPropuestaProcDTO> listaFalloPropProc) {
        this.listaFalloPropProc = listaFalloPropProc;
    }

    public List<FalloPropuestaProcDTO> getListaFalloPropProcFilter() {
        return listaFalloPropProcFilter;
    }

    public void setListaFalloPropProcFilter(List<FalloPropuestaProcDTO> listaFalloPropProcFilter) {
        this.listaFalloPropProcFilter = listaFalloPropProcFilter;
    }

    public List<FalloPropuestaProcDTO> getListaFalloPropProcSelect() {
        return listaFalloPropProcSelect;
    }

    public void setListaFalloPropProcSelect(List<FalloPropuestaProcDTO> listaFalloPropProcSelect) {
        this.listaFalloPropProcSelect = listaFalloPropProcSelect;
    }

    public Integer getIdFalloPropuestaProcDTO() {
        return idFalloPropuestaProcDTO;
    }

    public void setIdFalloPropuestaProcDTO(Integer idFalloPropuestaProcDTO) {
        this.idFalloPropuestaProcDTO = idFalloPropuestaProcDTO;
    }

    public boolean isMensajeTabla() {
        return mensajeTabla;
    }

    public void setMensajeTabla(boolean mensajeTabla) {
        this.mensajeTabla = mensajeTabla;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public List<TipoDocumentos> getListaTipoDocs() {
        return listaTipoDocs;
    }

    public void setListaTipoDocs(List<TipoDocumentos> listaTipoDocs) {
        this.listaTipoDocs = listaTipoDocs;
    }

    public List<RespositorioDocumentos> getListaRepoDocs() {
        return listaRepoDocs;
    }

    public void setListaRepoDocs(List<RespositorioDocumentos> listaRepoDocs) {
        this.listaRepoDocs = listaRepoDocs;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public List<RepositorioDocumentosDTO> getListaRepoDocsDto() {
        return listaRepoDocsDto;
    }

    public void setListaRepoDocsDto(List<RepositorioDocumentosDTO> listaRepoDocsDto) {
        this.listaRepoDocsDto = listaRepoDocsDto;
    }

    public boolean isBarchivos() {
        return barchivos;
    }

    public void setBarchivos(boolean barchivos) {
        this.barchivos = barchivos;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public List<Estatus> getListaEstatusFallo() {
        return listaEstatusFallo;
    }

    public void setListaEstatusFallo(List<Estatus> listaEstatusFallo) {
        this.listaEstatusFallo = listaEstatusFallo;
    }

    public Procedimientos getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(Procedimientos procedimientos) {
        this.procedimientos = procedimientos;
    }

    public boolean isBtipocompra() {
        return btipocompra;
    }

    public void setBtipocompra(boolean btipocompra) {
        this.btipocompra = btipocompra;
    }

    public List<Proveedores> getListaProv() {
        return listaProv;
    }

    public void setListaProv(List<Proveedores> listaProv) {
        this.listaProv = listaProv;
    }

    public List<Proveedores> getListProvIssste() {
        return listProvIssste;
    }

    public void setListProvIssste(List<Proveedores> listProvIssste) {
        this.listProvIssste = listProvIssste;
    }

}
