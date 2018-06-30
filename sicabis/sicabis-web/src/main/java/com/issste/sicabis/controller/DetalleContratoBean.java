package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.ContratoDTO;
import com.issste.sicabis.DTO.InsumosDTO;
import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
import com.issste.sicabis.ejb.DTO.ContratoOrdenDTO;
import com.issste.sicabis.ejb.ln.AlmacenService;
import com.issste.sicabis.ejb.ln.ArticulosService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.CompradorContratoService;
import com.issste.sicabis.ejb.ln.CompradorService;
import com.issste.sicabis.ejb.ln.ContratoFalloProcedimientoRcbService;
import com.issste.sicabis.ejb.ln.ContratoService;
import com.issste.sicabis.ejb.ln.DestinoService;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.FalloProcedimientoRcbService;
import com.issste.sicabis.ejb.ln.OrdenSuministroService;
import com.issste.sicabis.ejb.ln.PartidaService;
import com.issste.sicabis.ejb.ln.ProcedimientoService;
import com.issste.sicabis.ejb.ln.ProveedorService;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.ln.TipoContratoService;
import com.issste.sicabis.ejb.ln.TipoDocumentosService;
import com.issste.sicabis.ejb.ln.TipoInsumosService;
import com.issste.sicabis.ejb.ln.UnidadMedicaService;
import com.issste.sicabis.ejb.modelo.Almacen;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Clausulado;
import com.issste.sicabis.ejb.modelo.CompradorContrato;
import com.issste.sicabis.ejb.modelo.Compradores;
import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.Destinos;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.FundamentoLegal;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.PartidaPresupuestal;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.ejb.modelo.TipoContrato;
import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import com.issste.sicabis.ejb.modelo.TipoInsumos;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.service.silodisa.modelo.AlertasOperativas;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveCenadi;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.NumeroLetra;
import com.issste.sicabis.utils.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

public class DetalleContratoBean {

    @EJB
    private ProcedimientoService procedimientoService;

    @EJB
    private TipoInsumosService tipoInsumosService;

    @EJB
    private FalloProcedimientoRcbService falloProcedimientoRcbService;

    @EJB
    private OrdenSuministroService ordenSuministroService;

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private TipoDocumentosService tipoDocumentosService;

    @EJB
    private ContratoFalloProcedimientoRcbService contratoFalloProcedimientoRcbService;

    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;

    @EJB
    private PartidaService partidaService;

    @EJB
    private ArticulosService articulosService;

    @EJB
    private AlmacenService almacenService;

    @EJB
    private TipoContratoService tipoContratoService;

    @EJB
    private CompradorContratoService compradorContratoService;

    @EJB
    private ContratoService contratoService;

    @EJB
    private UnidadMedicaService unidadMedicaService;

    @EJB
    private DestinoService destinoService;

    @EJB
    private CompradorService compradorService;

    @EJB
    private EstatusService estatusService;

    @EJB
    private ProveedorService proveedorService;

    private Usuarios usuarios;
    private Contratos contratos;
    private ContratoFalloProcedimientoRcb contratoFalloProcedimientoRcb;
    private CompradorContrato compradorContrato;
    private Contratos contratosAux;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private RespositorioDocumentos respositorioDocumentos;
    private RepositorioDocumentosDTO repositorioDocumentosDTO;
    private RepositorioDocumentosDTO repositorioDocumentosDTOAux;

    private boolean botonGuardar;
    private boolean mensajeBorrar;
    private boolean mensajeGuardar = true;
    private Integer idProveedor;
    private List<Proveedores> listaProveedor;
    private List<FalloProcedimientoRcb> listaFalloProcRcb;
    private List<FalloProcedimientoRcb> listaFalloProcRcbFilter;
    private List<Estatus> listaEstatusContrato;
    private List<TipoContrato> listaTipoContrato;
    private List<Almacen> listaAlmacen;
    private List<CompradorContrato> listaCompradorContrato;
    private List<Destinos> listaDestinos;
    private List<UnidadesMedicas> listaUnidadesMedicas;
    private List<PartidaPresupuestal> listaPartidaPres;
    private List<FundamentoLegal> listaFundLegal;
    private List<ContratoOrdenDTO> listaContratoOrdenDTO;
    private String tipoProveedor;
    private Integer idTipoInsumo;
    private List<TipoInsumos> listTipoinsumos;
    private Integer idComprador;
    private List<Compradores> listaCompradores;
    private String nota;
    private String descripcionAmplia;
    private List<ContratoFalloProcedimientoRcb> listaContFalloProcRcb;
    private Procedimientos proc;
    private BigDecimal importeTotal;
    private boolean bproveedor;
    private List<Contratos> listaContratos;
    private List<Integer> listId;
    private String numeroContrato;
    private Integer noRupa;

    private Integer idEstatus;
    private Integer idTipoContrato;
    private Integer idAlmacen;
    private Integer idDestino;
    private Integer idUnidadMedica;
    private Integer idPartida;
    private Integer idFundamentoLegal;

    private Integer idTipoDocumento;
    private List<TipoDocumentos> listaTipoDocs;
    private List<RespositorioDocumentos> listaRepoDocs;
    private StreamedContent file;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private UploadedFile uploadedfile;
    private boolean barchivos;

    private final Integer idTareaProc = 5;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();
    private NumeroLetra numLetra = new NumeroLetra();
    private String nep;
    private String rcb;
    private String oficio;
    private String siglaTipoInsumos;
    private String contrato;
    private String noProcedimiento;
    private Integer numeroProveedor;

    public DetalleContratoBean() {
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        listaContratoOrdenDTO = new ArrayList<>();
        listTipoinsumos = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        contratos = new Contratos();
        contratos = (Contratos) util.getContextAtributte("contratos");
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        importeTotal = BigDecimal.ZERO;
        listaProveedor = proveedorService.obtenerByAutorizado();
        listaFalloProcRcb = new ArrayList();
        listTipoinsumos = tipoInsumosService.listTipoInsumos();
        listaEstatusContrato = estatusService.getByEstatusIdTarea(idTareaProc, "in (51, 59) ");
        listaTipoContrato = tipoContratoService.obtenerTiposContrato();
        numeroContrato = contratos.getNumeroContrato();
        if (contratos.getIdEstatus().getIdEstatus() == 59) {
            listaAlmacen = almacenService.obtenerAlmacenes();
            listaCompradores = compradorService.obtenerCompradores();
            listaDestinos = destinoService.obtenerDestinos();
            listaUnidadesMedicas = unidadMedicaService.unidadMedica();
            listaFundLegal = articulosService.obtenerArticulos();
            listaPartidaPres = partidaService.obtenerPartidaPresupuestales();
        } else {
            listaAlmacen = almacenService.getAlmacenesByActivo();
            listaCompradores = compradorService.obtenerCompradoresByActivo();
            listaDestinos = destinoService.obtenerDestinosByActivo();
            listaUnidadesMedicas = unidadMedicaService.unidadMedicaByActivo();
            listaFundLegal = articulosService.getFundamentosByActivo();
            listaPartidaPres = partidaService.getPartidaPresupuestalesByActivo();
        }
        listaContFalloProcRcb = new ArrayList();
        bproveedor = false;
        compradorContrato = compradorContratoService.obtenerByIdContrato(contratos.getIdContrato());
        if (compradorContrato != null) {
            idComprador = compradorContrato.getIdComprador().getIdComprador();
        }
        idEstatus = contratos.getIdEstatus().getIdEstatus();
        idAlmacen = contratos.getIdAlmacen().getIdAlmacen();
        idTipoContrato = contratos.getIdTipoContrato().getIdTipoContrato();
        idDestino = contratos.getIdDestino().getIdDestino();
        idFundamentoLegal = contratos.getIdFundamentoLegal().getIdFundamentoLegal();
        idPartida = contratos.getIdPartidaPresupuestal().getIdPartidaPresupuestal();
        idUnidadMedica = contratos.getIdUnidadesMedicas().getIdUnidadesMedicas();
        contratosAux = this.inicializaContrato(contratosAux);
        listaContFalloProcRcb = contratos.getIdContratoFalloProcedimientoRcbList();
        if (idEstatus != 51) {
            noProcedimiento = listaContFalloProcRcb.get(0).getIdFalloProcedimientoRcb().getIdFallo().getNumeroFallo();
            idTipoInsumo = listaContFalloProcRcb.get(0).getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getIdTipoInsumos();
            idProveedor = listaContFalloProcRcb.get(0).getIdFalloProcedimientoRcb().getIdProveedor().getIdProveedor();
            siglaTipoInsumos = contratos.getIdContratoFalloProcedimientoRcbList().get(0).getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla();
            contrato = numeroContrato + siglaTipoInsumos;
        } else {
            contrato = numeroContrato;
        }
        
        if(contratos.getIdTipoInsumos() != null){
            idTipoInsumo = contratos.getIdTipoInsumos().getIdTipoInsumos();
        }
        try {
            if (listaContFalloProcRcb.size() > 0) {
                Proveedores p = listaContFalloProcRcb.get(0).getIdFalloProcedimientoRcb().getIdProveedor();
                noProcedimiento = listaContFalloProcRcb.get(0).getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdProcedimiento().getNumeroProcedimiento();
                idProveedor = p.getIdProveedor();
                tipoProveedor = p.getIdTipoProveedor().getDescripcion();
                bproveedor = true;
                noRupa = p.getNoRupa();
                numeroProveedor = p.getNumeroProveedor();
                FalloProcedimientoRcb fpr = null;
                for (ContratoFalloProcedimientoRcb cfpr : listaContFalloProcRcb) {
                    fpr = new FalloProcedimientoRcb();
                    fpr = cfpr.getIdFalloProcedimientoRcb();
                    importeTotal = fpr.getImporte().add(importeTotal);
                    listaFalloProcRcb.add(fpr);
                }
            }
        } catch (NullPointerException e) {
            Logger.getLogger(DetalleContratoBean.class.getName()).log(Level.SEVERE, "Error", e);
        }
        if (contratos.getIdEstatus().getIdEstatus().intValue() != 51) {
            bproveedor = true;
            barchivos = true;
            botonGuardar = true;
            listaEstatusContrato = estatusService.getByEstatusIdTarea(idTareaProc, "not in (51, 58) ");
            buscarArchivosByIdProcesoIdTarea(contratos.getIdContrato(), idTareaProc);
        }
        listaTipoDocs = tipoDocumentosService.obtenerByIdTarea(idTareaProc);
    }

    public Contratos inicializaContrato(Contratos contratos) {
        contratos = new Contratos();
        contratos.setIdContrato(0);
        contratos.setActivo(1);
        contratos.setAcuerdo("");
        contratos.setAnioAfectacion(util.getYear());
        contratos.setCondicionPago("");
        //contratos.setDescuento(0);
        contratos.setFechaAlta(new Date());
        contratos.setFechaContrato(new Date());
        contratos.setFechaFormalizacion(new Date());
        contratos.setIdAlmacen(new Almacen(0));
        contratos.setIdDestino(new Destinos(0));
        contratos.setIdEstatus(new Estatus(0));
        contratos.setIdFundamentoLegal(new FundamentoLegal(0));
        contratos.setIdPadre(0);
        contratos.setIdPartidaPresupuestal(new PartidaPresupuestal(0));
        contratos.setIdTipoContrato(new TipoContrato(0));
        contratos.setIdUnidadesMedicas(new UnidadesMedicas(0));
        contratos.setImporte(BigDecimal.ZERO);
        contratos.setNep("");
        //contratos.setNoRupa(0);
        contratos.setNumeroContrato("");
        contratos.setUsuarioAlta(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
        contratos.setVigenciaInicial(new Date());
        contratos.setVigenciaFinal(new Date());
        contratos.setNotas("");
        contratos.setClausula("");
        return contratos;
    }

    public void cambiaProveedor() {
        for (Proveedores p : listaProveedor) {
            if (idProveedor.intValue() == p.getIdProveedor()) {
                tipoProveedor = p.getIdTipoProveedor().getDescripcion();
                noRupa = p.getNoRupa();
                break;
            }
        }
    }

    public void agregaClaves() {
        listId = new ArrayList<>();
        proc = new Procedimientos();
        proc = procedimientoService.obtenerByNumeroProcedimientoSeguimiento(noProcedimiento.toUpperCase());
        listId.add(idProveedor);
        listId.add(idTipoInsumo);
        List<FalloProcedimientoRcb> templist = new ArrayList<>();
        if (proc != null) {
            listId.add(proc.getIdProcedimiento());
            templist = falloProcedimientoRcbService.obtenerByFalloProcRcb(listId.get(0), listId.get(1), listId.get(2));
        }
        if (idProveedor != -1 && idTipoInsumo != -1 && !noProcedimiento.equals("") && proc != null && templist != null) {
            util.setContextAtributte("listId", listId);
            Map<String, Object> options = new HashMap<String, Object>();
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            List<String> values = new ArrayList<String>();
            values.add(String.valueOf(idProveedor));
            params.put("idProveedor", values);
            options.put("resizable", false);
            options.put("draggable", false);
            options.put("includeViewParams", true);
            options.put("width", "70%");
            options.put("height", "500");
            options.put("contentWidth", "95%");
            options.put("contentHeight", "500");
            options.put("modal", true);
            RequestContext.getCurrentInstance().openDialog("/vistas/adquisicion/agregaClavesFallos.xhtml", options, params);
        } else {
            if (proc == null) {
                mensaje.mensaje("El no. procedimiento ingresado no existe o tiene claves disponibles", "amarillo");
            } else {
                if (templist == null) {
                    mensaje.mensaje("El no. procedimiento ingresado no existe o tiene claves disponibles", "amarillo");
                } else {
                    mensaje.mensaje("Debe seleccionar un proveedor,tipo de insumo y no. procedimiento primero", "amarillo");
                }
            }
        }
    }

    public void clavesAgregadas(SelectEvent event) {
        List<FalloProcedimientoRcb> listaFalloProcRcb = (List<FalloProcedimientoRcb>) event.getObject();
        boolean bandera = false;
        ContratoFalloProcedimientoRcb contratoFalloProcedimientoRcb = null;
        FalloProcedimientoRcb fpraux = null;
        if (listaFalloProcRcb != null) {
            for (FalloProcedimientoRcb fpr : listaFalloProcRcb) {
                bandera = false;
                for (ContratoFalloProcedimientoRcb cfpr : listaContFalloProcRcb) {
                    if (fpr.getIdFalloProcedimientoRcb().intValue() == cfpr.getIdFalloProcedimientoRcb().getIdFalloProcedimientoRcb().intValue()) {
                        bandera = true;
                        mensaje.mensaje("La clave " + fpr.getIdFalloProcedimientoRcb().intValue() + " ya fue agregada a la lista", "amarillo");
                        break;
                    }
                }
                if (!bandera) {
                    fpraux = new FalloProcedimientoRcb();
                    fpraux.setActivo(fpr.getActivo());
                    fpraux.setCantidadModificada(fpr.getCantidadModificada());
                    fpraux.setCantidadPiezas(fpr.getCantidadPiezas());
                    fpraux.setFechaAlta(fpr.getFechaAlta());
                    fpraux.setIdFalloProcedimientoRcb(fpr.getIdFalloProcedimientoRcb());
                    fpraux.setIdProcedimientoRcb(fpr.getIdProcedimientoRcb());
                    fpraux.setIdProveedor(fpr.getIdProveedor());
                    fpraux.setImporte(fpr.getImporte());
                    fpraux.setIdFallo(fpr.getIdFallo());
                    fpraux.setPorcentajeAdjudicacion(fpr.getPorcentajeAdjudicacion());
                    fpraux.setPrecioUnitario(fpr.getPrecioUnitario());
                    fpraux.setUsuarioAlta(fpr.getUsuarioAlta());
                    this.listaFalloProcRcb.add(fpraux);

                    contratoFalloProcedimientoRcb = new ContratoFalloProcedimientoRcb();
                    contratoFalloProcedimientoRcb.setIdFalloProcedimientoRcb(fpr);
                    contratoFalloProcedimientoRcb.setActivo(1);
                    contratoFalloProcedimientoRcb.setFechaAlta(new Date());
                    contratoFalloProcedimientoRcb.setUsuarioAlta(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
                    contratoFalloProcedimientoRcb.setNota("");
                    contratoFalloProcedimientoRcb.setDescripcionAmplia("");
                    importeTotal = fpr.getImporte().add(importeTotal);
                    contratoFalloProcedimientoRcb.setIdContrato(contratos);
                    listaContFalloProcRcb.add(contratoFalloProcedimientoRcb);
                }
            }
            bproveedor = true;
        }
    }

    public void quitarClaves(FalloProcedimientoRcb fpr) {
        List<ContratoFalloProcedimientoRcb> listaCfpr = new ArrayList();
        List<FalloProcedimientoRcb> listaFpr = new ArrayList();
        importeTotal = BigDecimal.ZERO;
        for (ContratoFalloProcedimientoRcb cfpraux : listaContFalloProcRcb) {
            if (cfpraux.getIdFalloProcedimientoRcb().getIdFalloProcedimientoRcb().intValue() != fpr.getIdFalloProcedimientoRcb().intValue()) {
                listaCfpr.add(cfpraux);
                importeTotal = importeTotal.add(fpr.getImporte());
                break;
            }
        }
        for (FalloProcedimientoRcb fpraux : listaFalloProcRcb) {
            if (fpr != fpraux) {
                listaFpr.add(fpraux);
            }
        }

        listaFalloProcRcb.clear();
        listaFalloProcRcb = listaFpr;
        listaContFalloProcRcb.clear();
        listaContFalloProcRcb = listaCfpr;
        if (listaContFalloProcRcb.size() == 0) {
            bproveedor = false;
        }
    }

    public void abreDialogNotaDescripcion(FalloProcedimientoRcb fpr) {
        contratoFalloProcedimientoRcb = new ContratoFalloProcedimientoRcb();
        for (ContratoFalloProcedimientoRcb cfpr : listaContFalloProcRcb) {
            if (cfpr.getIdFalloProcedimientoRcb().getIdFalloProcedimientoRcb().intValue() == fpr.getIdFalloProcedimientoRcb().intValue()) {
                contratoFalloProcedimientoRcb = cfpr;
            }
        }
        descripcionAmplia = contratoFalloProcedimientoRcb.getDescripcionAmplia();
        nota = contratoFalloProcedimientoRcb.getNota();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg4').show();");
    }

    public void agregaNotaDescripcion() {
        List<ContratoFalloProcedimientoRcb> listaPrinc = new ArrayList();
        for (ContratoFalloProcedimientoRcb cfpr : listaContFalloProcRcb) {
            if (cfpr.getIdFalloProcedimientoRcb().getIdFalloProcedimientoRcb().intValue() == contratoFalloProcedimientoRcb.getIdFalloProcedimientoRcb().getIdFalloProcedimientoRcb().intValue()) {
                cfpr.setNota(nota);
                cfpr.setDescripcionAmplia(descripcionAmplia);
            }
            listaPrinc.add(cfpr);
        }
        listaContFalloProcRcb.clear();
        listaContFalloProcRcb = listaPrinc;
        nota = "";
        descripcionAmplia = "";
    }

    public boolean valida() {
        boolean bandera = true;
        if (idEstatus.intValue() == -1) {
            mensaje.mensaje("Debes seleccionar un estatus del contrato", "amarillo");
            bandera = false;
        } else if (idEstatus.intValue() == 59) {
            if (listaContFalloProcRcb.size() == 0) {
                mensaje.mensaje("Debes ingresar al menos una clave en el contrato", "amarillo");
                bandera = false;
            }
            if (contratos.getAcuerdo().equals("")) {
                mensaje.mensaje("Debes ingresar el acuerdo del contrato", "amarillo");
                bandera = false;
            }
            if (contratos.getAnioAfectacion() == 0) {
                mensaje.mensaje("Debes ingresar el aÃƒÂ±o de afectaciÃƒÂ³n", "amarillo");
                bandera = false;
            }
            if (contratos.getCondicionPago().equals("")) {
                mensaje.mensaje("Debes ingresar la condiciÃƒÂ³n de pago", "amarillo");
                bandera = false;
            }
            if (idComprador.intValue() == -1) {
                mensaje.mensaje("Debes ingresar la unidad mÃƒÂ©dica", "amarillo");
                bandera = false;
            }
        }
        if (idAlmacen.intValue() == -1) {
            mensaje.mensaje("Debes ingresar el almacen", "amarillo");
            bandera = false;
        }
        if (idDestino.intValue() == -1) {
            mensaje.mensaje("Debes ingresar el destino", "amarillo");
            bandera = false;
        }
        if (idFundamentoLegal.intValue() == -1) {
            mensaje.mensaje("Debes ingresar el fundamento legal", "amarillo");
            bandera = false;
        }
        if (idPartida.intValue() == -1) {
            mensaje.mensaje("Debes ingresar la partida presupuestal", "amarillo");
            bandera = false;
        }
        if (idTipoContrato.intValue() == -1) {
            mensaje.mensaje("Debes ingresar el tipo de contrato", "amarillo");
            bandera = false;
        }
        if (idUnidadMedica.intValue() == -1) {
            mensaje.mensaje("Debes ingresar la unidad mÃƒÂ©dica", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public void validaGuardado() {
        if (idEstatus.intValue() == 59) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlg1').show();");
        } else {
            this.guardaContrato();
        }
    }

    public void guardaContrato() {
        boolean bandera = true;
        if (valida()) {
            contratos.setIdEstatus(new Estatus(idEstatus));
            if (idEstatus.intValue() == 51 || idEstatus.intValue() == 59) {
                contratos.setImporte(importeTotal);
                if (contratoFalloProcedimientoRcbService.borrarByIdContrato(contratos.getIdContrato())) {
                    contratos.setIdContratoFalloProcedimientoRcbList(listaContFalloProcRcb);
                    contratos.setIdAlmacen(new Almacen(idAlmacen));
                    contratos.setIdDestino(new Destinos(idDestino));
                    contratos.setIdFundamentoLegal(new FundamentoLegal(idFundamentoLegal));
                    contratos.setIdPartidaPresupuestal(new PartidaPresupuestal(idPartida));
                    contratos.setIdTipoContrato(new TipoContrato(idTipoContrato));
                    contratos.setIdUnidadesMedicas(new UnidadesMedicas(idUnidadMedica));
                    if (contratos.getIdEstatus().getIdEstatus().intValue() == 59) {
                        Clausulado c = (Clausulado) archivosUtilidades.obtieneObjetoSerializableClausulado(archivosUtilidades.NAMEFILECLAUSULACONTRATO, archivosUtilidades.PATHFILESCLAUSULAS);
                        contratos.setClausula(c.getClausula());
                        String nombreArchivo = archivosUtilidades.guardaObjetoSerializableClausulado(contratos, c.getClaveProcedimiento(), c.getClaveProcedimiento(), archivosUtilidades.PATHFILESCLAUSULASCONTRATO);
                        contratos.setClausula(nombreArchivo);
                    }
                    if (contratoService.actualizaContrato(contratos)) {
                        bitacoraTareaEstatus.setDescripcion("Actualiza contrato:" + contratos.getNumeroContrato() + "");
                        bitacoraTareaEstatus.setFecha(new Date());
                        bitacoraTareaEstatus.setIdModulos(4);
                        bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                        bitacoraTareaEstatus.setIdTareaId(16);
                        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                        mensaje.mensaje(mensaje.datos_guardados, "verde");
                        if (idComprador.intValue() != -1) {
                            bandera = compradorContratoService.borrarByIdContrato(contratos.getIdContrato());
                            compradorContrato = new CompradorContrato();
                            compradorContrato.setActivo(1);
                            compradorContrato.setFechaAlta(new Date());
                            compradorContrato.setIdComprador(new Compradores(idComprador));
                            compradorContrato.setIdContrato(contratos);
                            if (compradorContratoService.guardaCompradorContrato(compradorContrato)) {
                                mensaje.mensaje("Comprador almacenado", "verde");
                            } else {
                                mensaje.mensaje("Error al guardar comprador", "rojo");
                            }
                        }
                        this.recargaPantalla();
                    } else {
                        mensaje.mensaje(mensaje.error_guardar, "rojo");
                    }
                }
            } else {
                if (contratoService.actualizaContrato(contratos)) {
                    mensaje.mensaje(mensaje.datos_guardados, "verde");
                    this.recargaPantalla();
                } else {
                    mensaje.mensaje(mensaje.error_guardar, "rojo");
                }
            }
            if (contratos.getIdEstatus().getIdEstatus() == 59 && contratos.getIdTipoContrato().getIdTipoContrato() == 2) {
                List<OrdenSuministro> listOrden = ordenSuministroService.obtenerOrdenByNumContrato(contratos.getIdContrato());
                if (listOrden != null) {
                    for (ContratoFalloProcedimientoRcb iterator : listaContFalloProcRcb) {
                        generarOrdenCerrada(iterator);
                    }
                    guardarOrdenes();
                }
            }
        }
    }

    public void generarOrdenCerrada(ContratoFalloProcedimientoRcb cfpr) {
        Integer existenciaCenadi = 0;
        Integer dpn = 0;
        boolean band = true;
        boolean bandera = false;
        List<ExistenciaPorClaveCenadi> existenciasList = null;
        List<AlertasOperativas> alertasList = null;
        Integer cantidad = cfpr.getIdFalloProcedimientoRcb().getCantidadPiezas();
        String clave = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getClaveInsumo();
        DetalleOrdenSuministro dos = new DetalleOrdenSuministro();
        ContratoOrdenDTO codtoAux = new ContratoOrdenDTO();
        dos = new DetalleOrdenSuministro();
        codtoAux = new ContratoOrdenDTO();
        codtoAux.setId(cfpr.getIdContratoFalloProcedimientoRcb());

        dos.setActivo(1);
        dos.setCancelado(1);
        dos.setFechaEntregaInicial(new Date());
        dos.setFechaEntregaFinal(new Date());
        //dos.setTotalCancelado(0);
        dos.setFechaAlta(new Date());
        dos.setIdFalloProcedimientoRcb(cfpr.getIdFalloProcedimientoRcb());
        dos.setUsuarioAlta(usuarios.getUsuario());
        Integer suministrado = 0;
        suministrado = cfpr.getIdFalloProcedimientoRcb().getSuministradoOrden();//detalleOrdenSuministroService.obtenerByIdFalloProcRcb(dos.getIdFalloProcedimientoRcb().getIdFalloProcedimientoRcb());
        dos.setCantidadSuministrada(suministrado);
        dos.setCantidadSuministrar(cantidad);
        dos.setTotalCancelado(0);
        codtoAux.setCompletado(1);
        dos.setImporte(util.multiplica(dos.getIdFalloProcedimientoRcb().getPrecioUnitario(), dos.getCantidadSuministrar()));
        codtoAux.setClaveInsumo(clave);
        codtoAux.setLugarEntrega(cfpr.getIdContrato().getIdDestino().getNombre() + ", " + cfpr.getIdContrato().getIdDestino().getDomicilio());
        codtoAux.setFechaInicialContrato(new Date());
        codtoAux.setFechaFinalContrato(cfpr.getIdContrato().getVigenciaFinal());
        codtoAux.setCantidadSuministrar(dos.getCantidadSuministrar());
        codtoAux.setContratoFalloProcedimientoRcb(cfpr);
        codtoAux.setDetalleOrdenSuministro(dos);
        listaContratoOrdenDTO.add(codtoAux);
    }

    public void guardarOrdenes() {
        int x = 0;
        List<Integer> listaProv = new ArrayList();
        Integer idProv = 0;
        String numeroOrden = "";
        BigDecimal importe = BigDecimal.ZERO;
        for (ContratoOrdenDTO codto : listaContratoOrdenDTO) {
            idProv = codto.getContratoFalloProcedimientoRcb().getIdFalloProcedimientoRcb().getIdProveedor().getIdProveedor();
            if (listaProv.size() == 0) {
                listaProv.add(idProv);
            }
            for (Integer idProvAux : listaProv) {
                if (idProvAux.intValue() != idProv.intValue()) {
                    listaProv.add(idProv);
                }
            }
        }
        for (Integer iterator : listaProv) {
            OrdenSuministro ordenSuministro = new OrdenSuministro();
            ordenSuministro.setIdEstatus(new Estatus(73));
            List<DetalleOrdenSuministro> listaDetalleOrdenAux = new ArrayList();
            if (listaProv.size() == 1) {
                for (ContratoOrdenDTO codto : listaContratoOrdenDTO) {
                    if (codto.getContratoFalloProcedimientoRcb().getIdFalloProcedimientoRcb().getIdProveedor().getIdProveedor().intValue() == idProv.intValue()) {
                        DetalleOrdenSuministro dos = codto.getDetalleOrdenSuministro();
                        FalloProcedimientoRcb fpr = codto.getContratoFalloProcedimientoRcb().getIdFalloProcedimientoRcb();
                        fpr.setCompletadoContrato(codto.getCompletado());
                        fpr.setSuministradoOrden(fpr.getSuministradoOrden() + codto.getCantidadSuministrar());
                        boolean actualiza = falloProcedimientoRcbService.actualizaCantidadConvenio(fpr);
                        dos.setTotalCancelado(0);
                        ordenSuministro.setIdContrato(codto.getContratoFalloProcedimientoRcb().getIdContrato());
                        ordenSuministro.setCantidadSuministrar(codto.getCantidadSuministrar());
                        importe = importe.add(dos.getImporte());
                        dos.setIdOrdenSuministro(ordenSuministro);
                        listaDetalleOrdenAux.add(dos);
                    }
                }
                Integer maxNum = ordenSuministroService.obtenerUltimoIdOrden();
                if (maxNum == null) {
                    maxNum = 1;
                } else {
                    maxNum++;
                }
                numeroOrden = "ORDEN_" + maxNum;
                ordenSuministro.setActivo(1);
                ordenSuministro.setNumeroOrden(numeroOrden);
                ordenSuministro.setFechaAlta(new Date());
                ordenSuministro.setFechaOrden(new Date());
                ordenSuministro.setUsuarioAlta(usuarios.getUsuario());
                ordenSuministro.setImporte(importe);
                ordenSuministro.setDetalleOrdenSuministroList(listaDetalleOrdenAux);
                if (ordenSuministroService.guardaOrdenSuministro(ordenSuministro)) {
                    bitacoraTareaEstatus.setDescripcion("Guardar orden suministro:" + ordenSuministro.getNumeroOrden() + "");
                    bitacoraTareaEstatus.setFecha(new Date());
                    bitacoraTareaEstatus.setIdModulos(1);
                    bitacoraTareaEstatus.setIdEstatus(73);
                    bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                    bitacoraTareaEstatus.setIdTareaId(7);
                    bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                    mensaje.mensaje(mensaje.datos_guardados, "verde");
                }
            } else {
                ContratoOrdenDTO codto = listaContratoOrdenDTO.get(x);
                if (codto.getContratoFalloProcedimientoRcb().getIdFalloProcedimientoRcb().getIdProveedor().getIdProveedor().intValue() == idProv.intValue()) {
                    DetalleOrdenSuministro dos = codto.getDetalleOrdenSuministro();
                    FalloProcedimientoRcb fpr = codto.getContratoFalloProcedimientoRcb().getIdFalloProcedimientoRcb();
                    fpr.setCompletadoContrato(codto.getCompletado());
                    fpr.setSuministradoOrden(fpr.getSuministradoOrden() + codto.getCantidadSuministrar());
                    boolean actualiza = falloProcedimientoRcbService.actualizaCantidadConvenio(fpr);
                    dos.setTotalCancelado(0);
                    ordenSuministro.setIdContrato(codto.getContratoFalloProcedimientoRcb().getIdContrato());
                    ordenSuministro.setCantidadSuministrar(codto.getCantidadSuministrar());
                    importe = importe.add(dos.getImporte());
                    dos.setIdOrdenSuministro(ordenSuministro);
                    listaDetalleOrdenAux.add(dos);
                    Integer maxNum = ordenSuministroService.obtenerUltimoIdOrden();
                    if (maxNum == null) {
                        maxNum = 1;
                    } else {
                        maxNum++;
                    }
                    numeroOrden = "ORDEN_" + maxNum;
                    ordenSuministro.setActivo(1);
                    ordenSuministro.setNumeroOrden(numeroOrden);
                    ordenSuministro.setFechaAlta(new Date());
                    ordenSuministro.setFechaOrden(new Date());
                    ordenSuministro.setUsuarioAlta(usuarios.getUsuario());
                    ordenSuministro.setImporte(importe);
                    ordenSuministro.setDetalleOrdenSuministroList(listaDetalleOrdenAux);
                    if (ordenSuministroService.guardaOrdenSuministro(ordenSuministro)) {
                        x++;
                        bitacoraTareaEstatus.setDescripcion("Guardar orden suministro:" + ordenSuministro.getNumeroOrden() + "");
                        bitacoraTareaEstatus.setFecha(new Date());
                        bitacoraTareaEstatus.setIdModulos(1);
                        bitacoraTareaEstatus.setIdEstatus(73);
                        bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                        bitacoraTareaEstatus.setIdTareaId(7);
                        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                    }
                }
            }
        }
    }

    public void recargaPantalla() {
        util.destroyContextMap("contratos");
        util.setContextAtributte("contratos", this.contratos);
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect("detalleContrato.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DetalleContratoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generarReporte() throws JRException, IOException {
        ArchivosUtilidades archivoUtilidades = new ArchivosUtilidades();
        Integer fila = 1;
        Integer cantidadPiezas = 0;
        BigDecimal precioUnitario = BigDecimal.ZERO;
        BigDecimal importeMin = BigDecimal.ZERO;
        String clave = "";
        String descripcion = "";
        String unidadPieza = "";
        Integer renglon = 0;
        List<InsumosDTO> insumosList = new ArrayList();
        List<ContratoDTO> conList = new ArrayList();
        List<Contratos> contratoList = new ArrayList();
        String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ISSSTE.png");
        contratoList = contratoService.contratoById(contratos.getIdContrato());
        for (Contratos c : contratoList) {
            ContratoDTO con = new ContratoDTO();
            CompradorContrato cp = compradorContratoService.obtenerByIdContrato(c.getIdContrato());
            System.out.println("cp--->"+cp);
            if(cp != null){
                con.setCompradorApellido(cp.getIdComprador().getApaterno());
                con.setCompradorNombre(cp.getIdComprador().getNombre());
                con.setCompradorApellidoMaterno(cp.getIdComprador().getAmaterno());
                con.setCompradorIniciales(String.valueOf(con.getCompradorNombre().charAt(0))+con.getCompradorApellido().charAt(0)+con.getCompradorApellidoMaterno().charAt(0));
                System.out.println("iniciales---->"+con.getCompradorIniciales());
            }
//            for (CompradorContrato cp : c.getCompradorContratoList()) {
//                con.setCompradorApellido(cp.getIdComprador().getApaterno());
//                con.setCompradorNombre(cp.getIdComprador().getNombre());
//                con.setCompradorApellidoMaterno(cp.getIdComprador().getAmaterno());
//            }
            con.setDescripcionFundamento(c.getIdFundamentoLegal().getDescripcion());
            for (ContratoFalloProcedimientoRcb cfpr : c.getIdContratoFalloProcedimientoRcbList()) {
                InsumosDTO i = new InsumosDTO();
                nep = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNep();
                System.out.println("nep---->"+nep);
                rcb = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNumero();
                oficio = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getOficioSuficienciaPresupuestal();
                Date fechaoficio = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getFechaOficioSuficiencia();
                con.setSiglaTipoInsumos(cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla());
                con.setNotas(c.getNotas());
                con.setRcb(cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNumero());
                con.setOficioSuficienciaPresupuestal(cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getOficioSuficienciaPresupuestal());
                con.setFechaSuficienciaPresupuestal(fechaoficio);
                con.setNep(nep);
                con.setDireccion(cfpr.getIdFalloProcedimientoRcb().getIdProveedor().getCalle()+", "+cfpr.getIdFalloProcedimientoRcb().getIdProveedor().getColonia()+", "+cfpr.getIdFalloProcedimientoRcb().getIdProveedor().getDelegacion());
                con.setNombreProveedor(cfpr.getIdFalloProcedimientoRcb().getIdProveedor().getNombreProveedor());
                con.setRfcProv(cfpr.getIdFalloProcedimientoRcb().getIdProveedor().getRfc());
                con.setTelefonoProv(cfpr.getIdFalloProcedimientoRcb().getIdProveedor().getTelefono2());
                con.setNumeroProcedimiento(cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdProcedimiento().getNumeroProcedimiento());
                cantidadPiezas = cfpr.getIdFalloProcedimientoRcb().getCantidadModificada();
                precioUnitario = cfpr.getIdFalloProcedimientoRcb().getPrecioUnitario();
                clave = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave();
                if (!cfpr.getDescripcionAmplia().equals("")) {
                    descripcion = cfpr.getDescripcionAmplia();
                } else {
                    descripcion = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getDescripcion();
                }
                if (!cfpr.getNota().equals("")) {
                    descripcion = descripcion + " \n Nota: " + "\n" + cfpr.getNota();
                }
                if (cantidadPiezas != null) {
                    importeTotal = precioUnitario.multiply(new BigDecimal(cantidadPiezas));
                }
                unidadPieza = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdUnidadPieza().getDescripcion();
                renglon = fila;
                fila++;
                i.setCantidadPiezas(cantidadPiezas);
                Integer min = (40*cantidadPiezas)/100;
                i.setCantidadPiezasMin(new BigDecimal(min));
                i.setClave(clave);
                i.setDescripcion(descripcion);
                i.setIdClave(renglon);
                i.setImporteClave(importeTotal);
                i.setImporteClaveMin(precioUnitario.multiply(i.getCantidadPiezasMin()));
                importeMin = importeMin.add(i.getImporteClaveMin());
                i.setPrecioUnitario(precioUnitario);
                i.setUnidadPieza(unidadPieza);
                i.setNep(nep);
                i.setRcb(rcb);
                i.setOficio(oficio);
                insumosList.add(i);
//                for (int j = 0; j < 10; j++) {
//                    insumosList.add(i);
//                }
                con.setInsumosList(insumosList);
            }
            con.setNoRupa(c.getIdContratoFalloProcedimientoRcbList().get(0).getIdFalloProcedimientoRcb().getIdProveedor().getNoRupa());
            con.setFechaContrato(c.getFechaContrato());
            con.setFundamentoLegal(c.getIdFundamentoLegal().getNombre());
            con.setImporte(c.getImporte());
            con.setImporteMin(importeMin);
            
            con.setNumeroContrato(c.getNumeroContrato() + " " + con.getSiglaTipoInsumos());
            con.setPartida(c.getIdPartidaPresupuestal().getPartidaPresupuestal());
            con.setClavePresupuestal(c.getIdPartidaPresupuestal().getDescripcion());
            con.setTipoContrato(c.getIdTipoContrato().getDescripcion());
            con.setVigenciaFinal(c.getVigenciaFinal());
            con.setVigenciaInicial(c.getVigenciaInicial());
            con.setAño(c.getAnioAfectacion());
            Clausulado cal = (Clausulado) archivoUtilidades.obtieneObjetoSerializableClausulado(archivoUtilidades.NAMEFILECLAUSULACONTRATO, archivoUtilidades.PATHFILESCLAUSULAS);
            con.setClausulas(cal.getClausula());
            String numero = String.valueOf(c.getImporte());
            con.setImporteLetra(numLetra.convertir(numero, true));
            con.setRutaLogo(logoPath);
            conList.add(con);
        }
        imprimirContrato(conList);
    }

    public void imprimirContrato(List<ContratoDTO> conList) throws JRException, IOException {
        JasperPrint jasperPrint;
        System.out.println("entro imprimir");
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(conList);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/con2P.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        System.out.println("lleno jasper");
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

        System.out.println("exporto imprimir");
        FacesContext.getCurrentInstance().responseComplete();
        System.out.println("salio imprimir");
    }

    public String verDetalleContrato() {
        util.setContextAtributte("contratos", this.contratos);
        return "detalleContrato.xhtml?faces-redirect=true";
    }

    public void obtenerContratos() {
        listaContratos = contratoService.obtenerContratos(contratosAux);
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
            respositorioDocumentos.setIdProceso(contratos.getIdContrato());
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
            buscarArchivosByIdProcesoIdTarea(contratos.getIdContrato(), idTareaProc);
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
        RequestContext.getCurrentInstance().reset("formContrato");
        init();
    }

    public Contratos getContratos() {
        return contratos;
    }

    public void setContratos(Contratos contratos) {
        this.contratos = contratos;
    }

    public boolean isBotonGuardar() {
        return botonGuardar;
    }

    public void setBotonGuardar(boolean botonGuardar) {
        this.botonGuardar = botonGuardar;
    }

    public boolean isMensajeBorrar() {
        return mensajeBorrar;
    }

    public void setMensajeBorrar(boolean mensajeBorrar) {
        this.mensajeBorrar = mensajeBorrar;
    }

    public boolean isMensajeGuardar() {
        return mensajeGuardar;
    }

    public void setMensajeGuardar(boolean mensajeGuardar) {
        this.mensajeGuardar = mensajeGuardar;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public List<Proveedores> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(List<Proveedores> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public List<FalloProcedimientoRcb> getListaFalloProcRcb() {
        return listaFalloProcRcb;
    }

    public void setListaFalloProcRcb(List<FalloProcedimientoRcb> listaFalloProcRcb) {
        this.listaFalloProcRcb = listaFalloProcRcb;
    }

    public List<Estatus> getListaEstatusContrato() {
        return listaEstatusContrato;
    }

    public void setListaEstatusContrato(List<Estatus> listaEstatusContrato) {
        this.listaEstatusContrato = listaEstatusContrato;
    }

    public List<TipoContrato> getListaTipoContrato() {
        return listaTipoContrato;
    }

    public void setListaTipoContrato(List<TipoContrato> listaTipoContrato) {
        this.listaTipoContrato = listaTipoContrato;
    }

    public List<Almacen> getListaAlmacen() {
        return listaAlmacen;
    }

    public void setListaAlmacen(List<Almacen> listaAlmacen) {
        this.listaAlmacen = listaAlmacen;
    }

    public List<CompradorContrato> getListaCompradorContrato() {
        return listaCompradorContrato;
    }

    public void setListaCompradorContrato(List<CompradorContrato> listaCompradorContrato) {
        this.listaCompradorContrato = listaCompradorContrato;
    }

    public List<Destinos> getListaDestinos() {
        return listaDestinos;
    }

    public void setListaDestinos(List<Destinos> listaDestinos) {
        this.listaDestinos = listaDestinos;
    }

    public List<UnidadesMedicas> getListaUnidadesMedicas() {
        return listaUnidadesMedicas;
    }

    public void setListaUnidadesMedicas(List<UnidadesMedicas> listaUnidadesMedicas) {
        this.listaUnidadesMedicas = listaUnidadesMedicas;
    }

    public List<PartidaPresupuestal> getListaPartidaPres() {
        return listaPartidaPres;
    }

    public void setListaPartidaPres(List<PartidaPresupuestal> listaPartidaPres) {
        this.listaPartidaPres = listaPartidaPres;
    }

    public List<FundamentoLegal> getListaFundLegal() {
        return listaFundLegal;
    }

    public void setListaFundLegal(List<FundamentoLegal> listaFundLegal) {
        this.listaFundLegal = listaFundLegal;
    }

    public String getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public Integer getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(Integer idComprador) {
        this.idComprador = idComprador;
    }

    public List<Compradores> getListaCompradores() {
        return listaCompradores;
    }

    public void setListaCompradores(List<Compradores> listaCompradores) {
        this.listaCompradores = listaCompradores;
    }

    public ContratoFalloProcedimientoRcb getContratoFalloProcedimientoRcb() {
        return contratoFalloProcedimientoRcb;
    }

    public void setContratoFalloProcedimientoRcb(ContratoFalloProcedimientoRcb contratoFalloProcedimientoRcb) {
        this.contratoFalloProcedimientoRcb = contratoFalloProcedimientoRcb;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getDescripcionAmplia() {
        return descripcionAmplia;
    }

    public void setDescripcionAmplia(String descripcionAmplia) {
        this.descripcionAmplia = descripcionAmplia;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public List<ContratoFalloProcedimientoRcb> getListaContFalloProcRcb() {
        return listaContFalloProcRcb;
    }

    public void setListaContFalloProcRcb(List<ContratoFalloProcedimientoRcb> listaContFalloProcRcb) {
        this.listaContFalloProcRcb = listaContFalloProcRcb;
    }

    public boolean isBproveedor() {
        return bproveedor;
    }

    public void setBproveedor(boolean bproveedor) {
        this.bproveedor = bproveedor;
    }

    public List<Contratos> getListaContratos() {
        return listaContratos;
    }

    public void setListaContratos(List<Contratos> listaContratos) {
        this.listaContratos = listaContratos;
    }

    public Contratos getContratosAux() {
        return contratosAux;
    }

    public void setContratosAux(Contratos contratosAux) {
        this.contratosAux = contratosAux;
    }

    public List<FalloProcedimientoRcb> getListaFalloProcRcbFilter() {
        return listaFalloProcRcbFilter;
    }

    public void setListaFalloProcRcbFilter(List<FalloProcedimientoRcb> listaFalloProcRcbFilter) {
        this.listaFalloProcRcbFilter = listaFalloProcRcbFilter;
    }

    public RespositorioDocumentos getRespositorioDocumentos() {
        return respositorioDocumentos;
    }

    public void setRespositorioDocumentos(RespositorioDocumentos respositorioDocumentos) {
        this.respositorioDocumentos = respositorioDocumentos;
    }

    public RepositorioDocumentosDTO getRepositorioDocumentosDTO() {
        return repositorioDocumentosDTO;
    }

    public void setRepositorioDocumentosDTO(RepositorioDocumentosDTO repositorioDocumentosDTO) {
        this.repositorioDocumentosDTO = repositorioDocumentosDTO;
    }

    public RepositorioDocumentosDTO getRepositorioDocumentosDTOAux() {
        return repositorioDocumentosDTOAux;
    }

    public void setRepositorioDocumentosDTOAux(RepositorioDocumentosDTO repositorioDocumentosDTOAux) {
        this.repositorioDocumentosDTOAux = repositorioDocumentosDTOAux;
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

    public UploadedFile getUploadedfile() {
        return uploadedfile;
    }

    public void setUploadedfile(UploadedFile uploadedfile) {
        this.uploadedfile = uploadedfile;
    }

    public boolean isBarchivos() {
        return barchivos;
    }

    public void setBarchivos(boolean barchivos) {
        this.barchivos = barchivos;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Integer getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(Integer idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public Integer getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Integer idDestino) {
        this.idDestino = idDestino;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getIdFundamentoLegal() {
        return idFundamentoLegal;
    }

    public void setIdFundamentoLegal(Integer idFundamentoLegal) {
        this.idFundamentoLegal = idFundamentoLegal;
    }

    public Integer getIdUnidadMedica() {
        return idUnidadMedica;
    }

    public void setIdUnidadMedica(Integer idUnidadMedica) {
        this.idUnidadMedica = idUnidadMedica;
    }

    public Integer getNoRupa() {
        return noRupa;
    }

    public void setNoRupa(Integer noRupa) {
        this.noRupa = noRupa;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public Integer getIdTipoInsumo() {
        return idTipoInsumo;
    }

    public void setIdTipoInsumo(Integer idTipoInsumo) {
        this.idTipoInsumo = idTipoInsumo;
    }

    public List<TipoInsumos> getListTipoinsumos() {
        return listTipoinsumos;
    }

    public void setListTipoinsumos(List<TipoInsumos> listTipoinsumos) {
        this.listTipoinsumos = listTipoinsumos;
    }

    public String getNoProcedimiento() {
        return noProcedimiento;
    }

    public void setNoProcedimiento(String noProcedimiento) {
        this.noProcedimiento = noProcedimiento;
    }

    public Integer getNumeroProveedor() {
        return numeroProveedor;
    }

    public void setNumeroProveedor(Integer numeroProveedor) {
        this.numeroProveedor = numeroProveedor;
    }

}
