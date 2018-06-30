package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.AlmacenService;
import com.issste.sicabis.ejb.ln.ArticulosService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.CompradorContratoService;
import com.issste.sicabis.ejb.ln.CompradorService;
import com.issste.sicabis.ejb.ln.ContratoService;
import com.issste.sicabis.ejb.ln.DestinoService;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.FalloProcedimientoRcbService;
import com.issste.sicabis.ejb.ln.PartidaService;
import com.issste.sicabis.ejb.ln.TipoContratoService;
import com.issste.sicabis.ejb.ln.TiposConvenioService;
import com.issste.sicabis.ejb.ln.UnidadMedicaService;
import com.issste.sicabis.ejb.modelo.Almacen;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Clausulado;
import com.issste.sicabis.ejb.modelo.CompradorContrato;
import com.issste.sicabis.ejb.modelo.Compradores;
import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.Destinos;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.FundamentoLegal;
import com.issste.sicabis.ejb.modelo.PartidaPresupuestal;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.TipoContrato;
import com.issste.sicabis.ejb.modelo.TipoConvenio;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

public class ConvenioBean {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private FalloProcedimientoRcbService falloProcedimientoRcbService;

    @EJB
    private PartidaService partidaService;

    @EJB
    private ArticulosService articulosService;

    @EJB
    private AlmacenService almacenService;

    @EJB
    private TipoContratoService tipoContratoService;

    @EJB
    private CompradorService compradorService;

    @EJB
    private TiposConvenioService tiposConvenioService;

    @EJB
    private CompradorContratoService compradorContratoService;

    @EJB
    private ContratoService contratoService;

    @EJB
    private UnidadMedicaService unidadMedicaService;

    @EJB
    private DestinoService destinoService;

    @EJB
    private EstatusService estatusService;

    private Usuarios usuarios;
    private Contratos contratos;
    private ContratoFalloProcedimientoRcb contratoFalloProcedimientoRcb;
    private Contratos contratosAux;
    private CompradorContrato compradorContrato;
    private Contratos contratoBusqueda;

    private Integer tabActivo;
    private boolean botonGuardar = true;
    private boolean mensajeBorrar;
    private boolean mensajeGuardar = true;
    private Integer idProveedor;
    private String tipoProveedor;
    private String nombreProveedor;
    private List<Estatus> listaEstatusContrato;
    private List<TipoContrato> listaTipoContrato;
    private List<Almacen> listaAlmacen;
    private List<Destinos> listaDestinos;
    private List<UnidadesMedicas> listaUnidadesMedicas;
    private List<PartidaPresupuestal> listaPartidaPres;
    private List<FundamentoLegal> listaFundLegal;
    private Integer idComprador;
    private String nota;
    private String descripcionAmplia;
    private List<ContratoFalloProcedimientoRcb> listaContFalloProcRcb;
    private List<ContratoFalloProcedimientoRcb> listaContFalloProcRcbFilter;
    private BigDecimal importeTotal;
    private List<Contratos> listaContratos;
    private String numeroContrato;
    private List<TipoConvenio> listaTipoConvenio;
    private List<Compradores> listaCompradores;
    private List<Contratos> listaConvenios;
    private List<FalloProcedimientoRcb> listaFalloProcRcb;
    private List<FalloProcedimientoRcb> listaFalloProcRcbFilter;
    private Integer noRupa;

    private Integer idEstatus;
    private Integer idTipoContrato;
    private Integer idAlmacen;
    private Integer idDestino;
    private Integer idUnidadMedica;
    private Integer idPartida;
    private Integer idFundamentoLegal;
    private Integer idTipoConvenio;

    private final Integer idTareaProc = 6;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    public ConvenioBean() {
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        importeTotal = BigDecimal.ZERO;
        listaFalloProcRcb = new ArrayList();
        listaEstatusContrato = estatusService.getByEstatusIdTarea(idTareaProc, "in (61, 69) ");
        listaTipoContrato = tipoContratoService.obtenerTiposContrato();
        listaAlmacen = almacenService.getAlmacenesByActivo();
        listaCompradores = compradorService.obtenerCompradoresByActivo();
        listaDestinos = destinoService.obtenerDestinosByActivo();
        listaUnidadesMedicas = unidadMedicaService.unidadMedicaByActivo();
        listaFundLegal = articulosService.getFundamentosByActivo();
        listaPartidaPres = partidaService.getPartidaPresupuestalesByActivo();
        listaTipoConvenio = tiposConvenioService.obtenerTipoConvenios();
        listaContFalloProcRcb = new ArrayList();
        listaContFalloProcRcbFilter = new ArrayList();

        contratos = new Contratos();
        contratos = this.inicializaContrato(contratos);
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
        //contratos.setNoRupa(0);
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
        return contratos;
    }

    public void buscaContratoConvenio() {
        listaFalloProcRcb.clear();
        listaContratos = contratoService.obtenerByNumeroContrato(numeroContrato);
        if (listaContratos.size() > 0) {
            contratos = listaContratos.get(0);
            if (contratos.getIdEstatus().getIdEstatus() != 58) {
                if (contratos.getIdEstatus().getIdEstatus().intValue() == 61) {
                    ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
                    try {
                        util.setContextAtributte("contratos", this.contratos);
                        ctx.redirect("detalleConvenio.xhtml");
                    } catch (IOException ex) {
                        Logger.getLogger(ConvenioBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    contratos.setIdPadre(contratos.getIdContrato());
                    //contratos.setNumeroConvenio("");
                    contratos.setUsuarioAlta(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
                    contratos.setFechaAlta(new Date());
                    contratos.setFechaModificacion(null);
                    contratos.setUsuarioModificacion("");
                    compradorContrato = compradorContratoService.obtenerByIdContrato(contratos.getIdContrato());
                    listaCompradores = new ArrayList<>();
                    listaAlmacen = new ArrayList<>();
                    listaDestinos = new ArrayList<>();
                    listaFundLegal = new ArrayList<>();
                    listaPartidaPres = new ArrayList<>();
                    listaUnidadesMedicas = new ArrayList<>();
                    if (compradorContrato != null) {
                        idComprador = compradorContrato.getIdComprador().getIdComprador();
                        listaCompradores = compradorService.obtenerCompradoresByActivoAndSelect(idComprador);
                    }

                    idEstatus = contratos.getIdEstatus().getIdEstatus();
                    idAlmacen = contratos.getIdAlmacen().getIdAlmacen();
                    listaAlmacen = almacenService.getAlmacenesActivosAndSelect(idAlmacen);
                    idTipoContrato = contratos.getIdTipoContrato().getIdTipoContrato();
                    idDestino = contratos.getIdDestino().getIdDestino();
                    listaDestinos = destinoService.obtenerDestinoByCveAndActivo(idDestino);
                    idFundamentoLegal = contratos.getIdFundamentoLegal().getIdFundamentoLegal();
                    listaFundLegal = articulosService.obtenerFundamentoLegalByIdAndActivo(idFundamentoLegal);
                    idPartida = contratos.getIdPartidaPresupuestal().getIdPartidaPresupuestal();
                    listaPartidaPres = partidaService.obtenerPartidaPresupuestalByIdAndActivo(idPartida);
                    idUnidadMedica = contratos.getIdUnidadesMedicas().getIdUnidadesMedicas();
                    listaUnidadesMedicas = unidadMedicaService.obtenUnidadesMedicasByActivoAndId(idUnidadMedica);

                    importeTotal = contratos.getImporte();
                    listaContFalloProcRcb = contratos.getIdContratoFalloProcedimientoRcbList();
                    if (listaContFalloProcRcb.size() > 0) {
                        Proveedores p = listaContFalloProcRcb.get(0).getIdFalloProcedimientoRcb().getIdProveedor();
                        idProveedor = p.getIdProveedor();
                        tipoProveedor = p.getIdTipoProveedor().getDescripcion();
                        nombreProveedor = p.getNombreProveedor();
                        noRupa = p.getNoRupa();
                        FalloProcedimientoRcb fpr = null;
                        for (ContratoFalloProcedimientoRcb cfpr : listaContFalloProcRcb) {
                            fpr = new FalloProcedimientoRcb();
                            fpr = cfpr.getIdFalloProcedimientoRcb();
                            listaFalloProcRcb.add(fpr);
                        }
                    }
                }
            } else {
                listaContratos.clear();
                mensaje.mensaje("No se pueden realizar convenios de este contrato ya que esta rescindido", "amarillo");
                numeroContrato = "";
            }
        } else {
            mensaje.mensaje("No se encontro ningún contrato con ese número", "amarillo");
            numeroContrato = "";
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
        if (listaContratos.size() == 0) {
            mensaje.mensaje("Debes seleccionar un contrato", "amarillo");
            bandera = false;
        }
        if (idEstatus.intValue() == -1) {
            mensaje.mensaje("Debes seleccionar un estatus del contrato", "amarillo");
            bandera = false;
        } else if (idEstatus.intValue() == 69) {
            if (listaContFalloProcRcb.size() == 0) {
                mensaje.mensaje("Debes ingresar al menos una clave en el contrato", "amarillo");
                bandera = false;
            }
            if (contratos.getAcuerdo().equals("")) {
                mensaje.mensaje("Debes ingresar el acuerdo del contrato", "amarillo");
                bandera = false;
            }
            if (contratos.getAnioAfectacion() == 0) {
                mensaje.mensaje("Debes ingresar el año de afectación", "amarillo");
                bandera = false;
            }
            if (contratos.getCondicionPago().equals("")) {
                mensaje.mensaje("Debes ingresar la condición de pago", "amarillo");
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
            mensaje.mensaje("Debes ingresar la unidad médica", "amarillo");
            bandera = false;
        }
        if (idTipoConvenio.intValue() == -1) {
            mensaje.mensaje("Debes ingresar el tipo de convenio", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public void validaGuardado() {
        if (idEstatus.intValue() == 69) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlg1').show();");
        } else {
            this.guardaConvenio();
        }
    }

    public void guardaConvenio() {
        boolean bandera = true;
        if (valida()) {
            contratos.setIdContrato(null);
            contratos.setIdEstatus(new Estatus(idEstatus));
            contratos.setIdAlmacen(new Almacen(idAlmacen));
            contratos.setIdDestino(new Destinos(idDestino));
            contratos.setIdFundamentoLegal(new FundamentoLegal(idFundamentoLegal));
            contratos.setIdPartidaPresupuestal(new PartidaPresupuestal(idPartida));
            contratos.setIdTipoContrato(new TipoContrato(idTipoContrato));
            contratos.setIdUnidadesMedicas(new UnidadesMedicas(idUnidadMedica));
            contratos.setIdTipoConvenio(new TipoConvenio(idTipoConvenio));
            List<ContratoFalloProcedimientoRcb> listaAux = new ArrayList();
            for (ContratoFalloProcedimientoRcb cfpr : listaContFalloProcRcb) {
                cfpr.setIdContrato(contratos);
                for (FalloProcedimientoRcb fpraux : listaFalloProcRcb) {
                    if (cfpr.getIdFalloProcedimientoRcb().getIdFalloProcedimientoRcb().intValue() == fpraux.getIdFalloProcedimientoRcb().intValue()) {
                        cfpr.setCantidadAgregadaConvenio(fpraux.getCantidadAgregadaConvenio());
                        cfpr.setPrecioUnitario(fpraux.getPrecioUnitario());
                        cfpr.setUnidadPiezaConvenio(fpraux.getUnidadPiezaConvenio());
                        cfpr.setIdFalloProcedimientoRcb(fpraux);
                        if (idEstatus.intValue() == 69) {
                            falloProcedimientoRcbService.actualizaCantidadConvenio(fpraux);
                        }
                    }
                }
                listaAux.add(cfpr);
            }
            contratos.setIdContratoFalloProcedimientoRcbList(listaAux);
            Integer num = contratoService.obtenerByMaxNumeroConvenio();
            if (num == null) {
                num = 0;
            }
            if (num != -1) {
                String parts = "";
                if (num.toString().length() == 6) {
                    parts = num.toString().substring(0, 4);
                    num = Integer.parseInt(parts);
                }
                num++;
                contratos.setNumeroConvenio(util.obtieneNumeroConvenio(num.toString()));
                if (contratos.getIdEstatus().getIdEstatus().intValue() == 69) {
                    Clausulado c = (Clausulado) archivosUtilidades.obtieneObjetoSerializableClausulado(archivosUtilidades.NAMEFILECLAUSULACONVENIO, archivosUtilidades.PATHFILESCLAUSULAS);
                    contratos.setClausula(c.getClausula());
                    String nombreArchivo = archivosUtilidades.guardaObjetoSerializableClausulado(contratos, c.getClaveProcedimiento(), c.getClaveProcedimiento(), archivosUtilidades.PATHFILESCLAUSULASCONTRATO);
                    contratos.setClausula(nombreArchivo);
                }
                if (contratoService.guardaContrato(contratos)) {
                    bitacoraTareaEstatus.setDescripcion("Guardar convenio:" + contratos.getNumeroConvenio() + "");
                    bitacoraTareaEstatus.setFecha(new Date());
                    bitacoraTareaEstatus.setIdEstatus(69);
                    bitacoraTareaEstatus.setIdModulos(1);
                    bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                    bitacoraTareaEstatus.setIdTareaId(5);
                    bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                    mensaje.mensaje(mensaje.datos_guardados, "verde");
                    mensaje.mensaje("El número de convenio asignado es: " + contratos.getNumeroConvenio(), "verde");
                    if (idEstatus.intValue() == 61) {
                        RequestContext context = RequestContext.getCurrentInstance();
                        context.execute("PF('dlg3').show();");
                    }
                } else {
                    mensaje.mensaje(mensaje.error_guardar, "rojo");
                }
            } else {
                mensaje.mensaje("Error al obtener el último número de contrato", "rojo");
            }
        }
    }

    public void validaCantidad(FalloProcedimientoRcb fpr) {
        if (fpr.getCantidadAgregadaConvenio().intValue() <= fpr.getCantidadModificada().intValue()) {
            mensaje.mensaje("La cantidad de piezas ingresadas debe ser mayor a la cantidad original", "amarillo");
        }
    }

    public void obtenerConvenios() {
        listaConvenios = contratoService.obtenerConvenios(contratosAux);
    }

    public String verDetalleContratoConvenio() {
        util.setContextAtributte("contratos", this.contratoBusqueda);
        if (contratoBusqueda.getIdPadre().intValue() == 0) {
            return "detalleContrato.xhtml?faces-redirect=true";
        } else {
            return "detalleConvenio.xhtml?faces-redirect=true";
        }
    }

    public String verDetalleConvenio() {
        util.setContextAtributte("contratos", this.contratos);
        return "detalleConvenio.xhtml?faces-redirect=true";
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

    public ContratoFalloProcedimientoRcb getContratoFalloProcedimientoRcb() {
        return contratoFalloProcedimientoRcb;
    }

    public void setContratoFalloProcedimientoRcb(ContratoFalloProcedimientoRcb contratoFalloProcedimientoRcb) {
        this.contratoFalloProcedimientoRcb = contratoFalloProcedimientoRcb;
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

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
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

    public Integer getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(Integer idComprador) {
        this.idComprador = idComprador;
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

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public List<Contratos> getListaContratos() {
        return listaContratos;
    }

    public void setListaContratos(List<Contratos> listaContratos) {
        this.listaContratos = listaContratos;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public List<TipoConvenio> getListaTipoConvenio() {
        return listaTipoConvenio;
    }

    public void setListaTipoConvenio(List<TipoConvenio> listaTipoConvenio) {
        this.listaTipoConvenio = listaTipoConvenio;
    }

    public List<Compradores> getListaCompradores() {
        return listaCompradores;
    }

    public void setListaCompradores(List<Compradores> listaCompradores) {
        this.listaCompradores = listaCompradores;
    }

    public Contratos getContratosAux() {
        return contratosAux;
    }

    public void setContratosAux(Contratos contratosAux) {
        this.contratosAux = contratosAux;
    }

    public List<Contratos> getListaConvenios() {
        return listaConvenios;
    }

    public void setListaConvenios(List<Contratos> listaConvenios) {
        this.listaConvenios = listaConvenios;
    }

    public Contratos getContratoBusqueda() {
        return contratoBusqueda;
    }

    public void setContratoBusqueda(Contratos contratoBusqueda) {
        this.contratoBusqueda = contratoBusqueda;
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

    public Integer getIdUnidadMedica() {
        return idUnidadMedica;
    }

    public void setIdUnidadMedica(Integer idUnidadMedica) {
        this.idUnidadMedica = idUnidadMedica;
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

    public Integer getIdTipoConvenio() {
        return idTipoConvenio;
    }

    public void setIdTipoConvenio(Integer idTipoConvenio) {
        this.idTipoConvenio = idTipoConvenio;
    }

    public List<FalloProcedimientoRcb> getListaFalloProcRcb() {
        return listaFalloProcRcb;
    }

    public void setListaFalloProcRcb(List<FalloProcedimientoRcb> listaFalloProcRcb) {
        this.listaFalloProcRcb = listaFalloProcRcb;
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

}
