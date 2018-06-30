package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.ContratoOrdenDTO;
import com.issste.sicabis.ejb.ln.AlmacenService;
import com.issste.sicabis.ejb.ln.ArticulosService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.CompradorContratoService;
import com.issste.sicabis.ejb.ln.CompradorService;
import com.issste.sicabis.ejb.ln.ContratoService;
import com.issste.sicabis.ejb.ln.DestinoService;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.FalloProcedimientoRcbService;
import com.issste.sicabis.ejb.ln.OrdenSuministroService;
import com.issste.sicabis.ejb.ln.PartidaService;
import com.issste.sicabis.ejb.ln.ProcedimientoService;
import com.issste.sicabis.ejb.ln.ProveedorService;
import com.issste.sicabis.ejb.ln.TipoContratoService;
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
import com.issste.sicabis.ejb.modelo.TipoContrato;
import com.issste.sicabis.ejb.modelo.TipoInsumos;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.service.silodisa.modelo.AlertasOperativas;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveCenadi;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

public class ContratoBean {

    @EJB
    private FalloProcedimientoRcbService falloProcedimientoRcbService;

    @EJB
    private OrdenSuministroService ordenSuministroService;

    @EJB
    private ProcedimientoService procedimientoService;

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

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

    @EJB
    private TipoInsumosService tipoInsumosService;

    private Usuarios usuarios;
    private Contratos contratos;
    private ContratoFalloProcedimientoRcb contratoFalloProcedimientoRcb;
    private CompradorContrato compradorContrato;
    private Contratos contratosAux;

    private Integer tabActivo;
    private boolean botonGuardar = true;
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
    private String tipoProveedor;
    private Integer idComprador;
    private List<Compradores> listaCompradores;
    private String nota;
    private String descripcionAmplia;
    private List<ContratoFalloProcedimientoRcb> listaContFalloProcRcb;
    private List<ContratoFalloProcedimientoRcb> listaContFalloProcRcbFilter;
    private BigDecimal importeTotal;
    private boolean bproveedor;
    private List<Contratos> listaContratos;
    private Integer numeroContrato;
    private Integer numeroFallo;
    private Integer noRupa;
    private Integer idTipoInsumo;
    private List<TipoInsumos> listTipoinsumos;
    private List<ContratoOrdenDTO> listaContratoOrdenDTO;
    private List<Integer> listId;
    private BigDecimal importeContrato = new BigDecimal(0);
    private final Integer idTareaProc = 5;
    private String noProcedimiento;
    private Integer numeroProveedor;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private Procedimientos proc;

    public ContratoBean() {
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        listaContratoOrdenDTO = new ArrayList();
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        importeTotal = BigDecimal.ZERO;
        noProcedimiento = "";
        listaProveedor = proveedorService.proveedoresWhitFallo();
        listTipoinsumos = tipoInsumosService.listTipoInsumos();
        listaFalloProcRcb = new ArrayList();
        listaEstatusContrato = estatusService.getByEstatusIdTarea(idTareaProc, "in (51, 59) ");
        listaTipoContrato = tipoContratoService.obtenerTiposContrato();
        listaAlmacen = almacenService.getAlmacenesByActivo();
        listaCompradores = compradorService.obtenerCompradoresByActivo();
        listaDestinos = destinoService.obtenerDestinosByActivo();
        listaUnidadesMedicas = unidadMedicaService.unidadMedicaByActivo();
        listaFundLegal = articulosService.getFundamentosByActivo();
        listaPartidaPres = partidaService.getPartidaPresupuestalesByActivo();
        listaContFalloProcRcb = new ArrayList();
        listaContFalloProcRcbFilter = new ArrayList();
        contratos = new Contratos();
        contratos = this.inicializaContrato(contratos);
        bproveedor = false;
        contratosAux = this.inicializaContrato(contratosAux);
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
        contratos.setNumeroContrato("");
        contratos.setUsuarioAlta(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        String strFecha = "" + c.get(Calendar.YEAR) + "-12-31";
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha);
        } catch (ParseException ex) {
            Logger.getLogger(ContratoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        contratos.setVigenciaInicial(new Date());
        contratos.setVigenciaFinal(fecha);
        contratos.setNotas("");
        contratos.setClausula("");
        return contratos;
    }

    public void cambiaProveedor() {
        for (Proveedores p : listaProveedor) {
            if (idProveedor.intValue() == p.getIdProveedor().intValue()) {
                tipoProveedor = p.getIdTipoProveedor().getDescripcion();
                noRupa = p.getNoRupa();
                numeroProveedor = p.getNumeroProveedor();
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
        if (proc != null) {
            listId.add(proc.getIdProcedimiento());
        }
        if (idProveedor != -1 && idTipoInsumo != -1 && !noProcedimiento.equals("") && proc != null) {
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
                mensaje.mensaje("Debe seleccionar un proveedor,tipo de insumo y no. procedimiento primero", "amarillo");
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
                    importeContrato = importeTotal;
                    noRupa = fpr.getIdProveedor().getNoRupa();
                    //numeroProveedor = fpr.getIdProveedor().getNumeroProveedor();
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
        if (contratos.getIdEstatus().getIdEstatus().intValue() == -1) {
            mensaje.mensaje("Debes seleccionar un estatus del contrato", "amarillo");
            bandera = false;
        } else if (contratos.getIdEstatus().getIdEstatus().intValue() == 59) {
            if (listaContFalloProcRcb.size() == 0) {
                mensaje.mensaje("Debes ingresar al menos una clave en el contrato", "amarillo");
                bandera = false;
            }
            if (contratos.getAcuerdo().equals("")) {
                mensaje.mensaje("Debes ingresar el acuerdo del contrato", "amarillo");
                bandera = false;
            }
            if (contratos.getAnioAfectacion() == 0) {
                mensaje.mensaje("Debes ingresar el añoo de afectación", "amarillo");
                bandera = false;
            }
            if (contratos.getCondicionPago().equals("")) {
                mensaje.mensaje("Debes ingresar la condición de pago", "amarillo");
                bandera = false;
            }

            if (idComprador.intValue() == -1) {
                mensaje.mensaje("Debes ingresar el comprador", "amarillo");
                bandera = false;
            }
        }
        if (contratos.getIdAlmacen().getIdAlmacen() == -1) {
            mensaje.mensaje("Debes ingresar el almacen", "amarillo");
            bandera = false;
        }
        if (contratos.getIdDestino().getIdDestino() == -1) {
            mensaje.mensaje("Debes ingresar el destino", "amarillo");
            bandera = false;
        }
        if (contratos.getIdFundamentoLegal().getIdFundamentoLegal().intValue() == -1) {
            mensaje.mensaje("Debes ingresar el fundamento legal", "amarillo");
            bandera = false;
        }
        if (contratos.getIdPartidaPresupuestal().getIdPartidaPresupuestal().intValue() == -1) {
            mensaje.mensaje("Debes ingresar la partida presupuestal", "amarillo");
            bandera = false;
        }
        if (contratos.getIdTipoContrato().getIdTipoContrato().intValue() == -1) {
            mensaje.mensaje("Debes ingresar el tipo de contrato", "amarillo");
            bandera = false;
        }
        if (contratos.getIdUnidadesMedicas().getIdUnidadesMedicas().intValue() == -1) {
            mensaje.mensaje("Debes ingresar la unidad médica", "amarillo");
            bandera = false;
        }

        return bandera;
    }

    public void validaGuardado() {
        if (contratos.getIdEstatus().getIdEstatus().intValue() == 59) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlg1').show();");
        } else {
            this.guardaContrato();
        }
    }

    public void guardaContrato() {
        if (valida()) {//
            contratos.setImporte(importeTotal);
            contratos.setIdContratoFalloProcedimientoRcbList(listaContFalloProcRcb);
            Integer num = contratoService.obtenerByMaxNumero();
            if (num == null) {
                num = 0;
            }
            if (num != -1) {
                num++;
                if (num.toString().length() == 6) {
                    contratos.setNumeroContrato(num.toString());
                } else {
                    contratos.setNumeroContrato(util.obtieneNumeroContrato(num.toString()));
                }
                if (contratos.getIdEstatus().getIdEstatus().intValue() == 59) {
                    Clausulado c = (Clausulado) archivosUtilidades.obtieneObjetoSerializableClausulado(archivosUtilidades.NAMEFILECLAUSULACONTRATO, archivosUtilidades.PATHFILESCLAUSULAS);
                    contratos.setClausula(c.getClausula());
                    String nombreArchivo = archivosUtilidades.guardaObjetoSerializableClausulado(contratos, c.getClaveProcedimiento(), c.getClaveProcedimiento(), archivosUtilidades.PATHFILESCLAUSULASCONTRATO);
                    contratos.setClausula(nombreArchivo);
                }
                contratos.setIdTipoInsumos(new TipoInsumos(idTipoInsumo));
                if (contratoService.guardaContrato(contratos)) {
                    bitacoraTareaEstatus.setDescripcion("Guarda contrato:" + contratos.getNumeroContrato() + "");
                    bitacoraTareaEstatus.setFecha(new Date());
                    bitacoraTareaEstatus.setIdEstatus(59);
                    bitacoraTareaEstatus.setIdModulos(2);
                    bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                    bitacoraTareaEstatus.setIdTareaId(5);
                    bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                    mensaje.mensaje(mensaje.datos_guardados, "verde");
                    mensaje.mensaje("El número de contrato asignado es: " + contratos.getNumeroContrato(), "verde");
                    if (idComprador.intValue() != -1) {
                        compradorContrato = new CompradorContrato();
                        compradorContrato.setActivo(1);
                        compradorContrato.setFechaAlta(new Date());
                        compradorContrato.setIdComprador(new Compradores(idComprador));
                        compradorContrato.setIdContrato(contratos);
                        if (compradorContratoService.guardaCompradorContrato(compradorContrato)) {
                            bitacoraTareaEstatus.setDescripcion("Guarda compradorContrato:" + idComprador + "");
                            bitacoraTareaEstatus.setFecha(new Date());
                            bitacoraTareaEstatus.setIdModulos(2);
                            bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                            bitacoraTareaEstatus.setIdTareaId(5);
                            bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                            mensaje.mensaje("Comprador almacenado", "verde");
                        } else {
                            mensaje.mensaje("Error al guardar comprador", "rojo");
                        }
                    }
                    if (contratos.getIdEstatus().getIdEstatus() == 59 && contratos.getIdTipoContrato().getIdTipoContrato() == 2) {
                        List<OrdenSuministro> listOrden = ordenSuministroService.obtenerOrdenByNumContrato(contratos.getIdContrato());
                        if (listOrden.isEmpty()) {
                            for (ContratoFalloProcedimientoRcb iterator : listaContFalloProcRcb) {
                                generarOrdenCerrada(iterator);
                            }
                            guardarOrdenes();
                        }
                    }
                    if (contratos.getIdEstatus().getIdEstatus() == 51) {
                        RequestContext context = RequestContext.getCurrentInstance();
                        context.execute("PF('dlg3').show();");
                    } else {
                        this.limpiar();
                    }
                } else {
                    mensaje.mensaje(mensaje.error_guardar, "rojo");
                }
            } else {
                mensaje.mensaje("Error al obtener el último número de contrato", "rojo");
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

    public String verDetalleContrato() {
        util.setContextAtributte("contratos", this.contratos);
        return "detalleContrato.xhtml?faces-redirect=true";
    }

    public void obtenerContratos() {
        listaContratos = contratoService.obtenerContratos(contratosAux);
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

    public Integer getTabActivo() {
        return tabActivo;
    }

    public void setTabActivo(Integer tabActivo) {
        this.tabActivo = tabActivo;
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

    public List<ContratoFalloProcedimientoRcb> getListaContFalloProcRcbFilter() {
        return listaContFalloProcRcbFilter;
    }

    public void setListaContFalloProcRcbFilter(List<ContratoFalloProcedimientoRcb> listaContFalloProcRcbFilter) {
        this.listaContFalloProcRcbFilter = listaContFalloProcRcbFilter;
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

    public Integer getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(Integer numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Integer getNumeroFallo() {
        return numeroFallo;
    }

    public void setNumeroFallo(Integer numeroFallo) {
        this.numeroFallo = numeroFallo;
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

    public Integer getNoRupa() {
        return noRupa;
    }

    public void setNoRupa(Integer noRupa) {
        this.noRupa = noRupa;
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

    public BigDecimal getImporteContrato() {
        return importeContrato;
    }

    public void setImporteContrato(BigDecimal importeContrato) {
        this.importeContrato = importeContrato;
    }

    public Procedimientos getProc() {
        return proc;
    }

    public void setProc(Procedimientos proc) {
        this.proc = proc;
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
