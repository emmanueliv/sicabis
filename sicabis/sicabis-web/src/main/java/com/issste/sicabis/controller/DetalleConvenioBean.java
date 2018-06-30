package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.ContratoDTO;
import com.issste.sicabis.DTO.InsumosDTO;
import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
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
import com.issste.sicabis.ejb.ln.PartidaService;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.ln.TipoContratoService;
import com.issste.sicabis.ejb.ln.TipoDocumentosService;
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
import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.ejb.modelo.TipoContrato;
import com.issste.sicabis.ejb.modelo.TipoConvenio;
import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.modelo.Usuarios;
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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

public class DetalleConvenioBean {

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
    private ContratoFalloProcedimientoRcbService contratoFalloProcedimientoRcbService;

    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;

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
    private Contratos contratoBusqueda;
    private CompradorContrato compradorContrato;
    private RespositorioDocumentos respositorioDocumentos;
    private RepositorioDocumentosDTO repositorioDocumentosDTO;
    private RepositorioDocumentosDTO repositorioDocumentosDTOAux;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

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
    private String numeroConvenio;
    private Integer noRupa;

    private Integer idEstatus;
    private Integer idTipoContrato;
    private Integer idAlmacen;
    private Integer idDestino;
    private Integer idUnidadMedica;
    private Integer idPartida;
    private Integer idFundamentoLegal;
    private Integer idTipoConvenio;

    private Integer idTipoDocumento;
    private List<TipoDocumentos> listaTipoDocs;
    private List<RespositorioDocumentos> listaRepoDocs;
    private StreamedContent file;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private UploadedFile uploadedfile;
    private boolean barchivos;

    private final Integer idTareaProc = 6;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();
    private String nep;
    private String rcb;
    private String oficio;
    private String siglaTipoInsumos;
    private String contrato;
    private String convenio;
    @EJB
    private TipoDocumentosService tipoDocumentosService;

    public DetalleConvenioBean() {
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        contratos = new Contratos();
        contratos = (Contratos) util.getContextAtributte("contratos");
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        importeTotal = BigDecimal.ZERO;
        listaFalloProcRcb = new ArrayList();
        listaEstatusContrato = estatusService.getByEstatusIdTarea(idTareaProc, "in (61, 69) ");
        listaTipoContrato = tipoContratoService.obtenerTiposContrato();
        listaAlmacen = almacenService.getAlmacenesActivosAndSelect(contratos.getIdAlmacen().getIdAlmacen());
        listaCompradores = compradorService.obtenerCompradores();
        listaDestinos = destinoService.obtenerDestinoByCveAndActivo(contratos.getIdDestino().getIdDestino());
        listaUnidadesMedicas = unidadMedicaService.obtenUnidadesMedicasByActivoAndId(contratos.getIdUnidadesMedicas().getIdUnidadesMedicas());
        listaFundLegal = articulosService.obtenerFundamentoLegalByIdAndActivo(contratos.getIdFundamentoLegal().getIdFundamentoLegal());
        listaPartidaPres = partidaService.obtenerPartidaPresupuestalByIdAndActivo(contratos.getIdPartidaPresupuestal().getIdPartidaPresupuestal());
        listaTipoConvenio = tiposConvenioService.obtenerTipoConvenios();
        listaContFalloProcRcb = new ArrayList();
        listaContFalloProcRcbFilter = new ArrayList();
        listaTipoDocs = tipoDocumentosService.obtenerByIdTarea(idTareaProc);
        List<Contratos> c = contratoService.obtenerByNumeroContratoOrderById(contratos.getNumeroContrato());
        siglaTipoInsumos = c.get(c.size() - 1).getIdContratoFalloProcedimientoRcbList().get(0).getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla();
        convenio = contratos.getNumeroConvenio() + siglaTipoInsumos;
        contrato = contratos.getNumeroContrato() + siglaTipoInsumos;
        this.buscaContratoConvenio();
        if (contratos.getIdEstatus().getIdEstatus().intValue() == 69) {
            barchivos = true;
            listaEstatusContrato = estatusService.getByEstatusIdTarea(idTareaProc, "not in (61) ");
            buscarArchivosByIdProcesoIdTarea(contratos.getIdContrato(), idTareaProc);
        } else {

        }
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
        contratos.setFechaOficioSuficiencia(new Date());
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
        return contratos;
    }

    public void buscaContratoConvenio() {
        listaContratos = contratoService.obtenerConvenioByNumeroContratoIdContrato(contratos.getNumeroContrato(), contratos.getIdContrato());
        List<CompradorContrato> cc = compradorContratoService.obtenerListByIdContrato(contratos.getIdPadre());
        compradorContrato = cc.get(0);
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
        if (contratos.getIdTipoConvenio() != null) {
            idTipoConvenio = contratos.getIdTipoConvenio().getIdTipoConvenio();
        } else {
            idTipoConvenio = 1;
        }
        numeroContrato = contratos.getNumeroContrato();
        numeroConvenio = contratos.getNumeroConvenio();
        importeTotal = contratos.getImporte();
        listaContFalloProcRcb = contratos.getIdContratoFalloProcedimientoRcbList();
        if (listaContFalloProcRcb.size() > 0) {
            Proveedores p = listaContFalloProcRcb.get(0).getIdFalloProcedimientoRcb().getIdProveedor();
            idProveedor = p.getIdProveedor();
            tipoProveedor = p.getIdTipoProveedor().getDescripcion();
            nombreProveedor = p.getNombreProveedor();
            noRupa = p.getNumero();
            FalloProcedimientoRcb fpr = null;
            for (ContratoFalloProcedimientoRcb cfpr : listaContFalloProcRcb) {
                fpr = new FalloProcedimientoRcb();
                fpr = cfpr.getIdFalloProcedimientoRcb();
                if (idEstatus.intValue() == 61) {
                    fpr.setCantidadAgregadaConvenio(cfpr.getCantidadAgregadaConvenio());
                    fpr.setPrecioUnitario(cfpr.getPrecioUnitario());
                    fpr.setUnidadPiezaConvenio(cfpr.getUnidadPiezaConvenio());
                }
                listaFalloProcRcb.add(fpr);
            }
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

    public void existeNumeroConvenio() {
        if (!numeroConvenio.equals(contratos.getNumeroConvenio())) {
            boolean bandera = this.validaNumeroConvenio();
        }
    }

    public boolean validaNumeroConvenio() {
        boolean bandera = true;
        List<Contratos> cAux = contratoService.obtenerByNumeroConvenio(contratos.getNumeroConvenio());
        if (cAux.size() != 0) {
            mensaje.mensaje("El número de convenio ya esta registrado", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean valida() {
        boolean bandera = true;
        if (contratos.getNumeroConvenio().equals("")) {
            mensaje.mensaje("Debes ingresar el número del convenio", "amarillo");
            bandera = false;
        } else {
            if (!numeroConvenio.equals(contratos.getNumeroConvenio())) {
                bandera = this.validaNumeroConvenio();
            }
        }
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
        if (valida()) {
            contratos.setIdEstatus(new Estatus(idEstatus));
            if (idEstatus.intValue() == 61 || idEstatus.intValue() == 69) {
                if (contratoFalloProcedimientoRcbService.borrarByIdContrato(contratos.getIdContrato())) {
                    contratos.setIdAlmacen(new Almacen(idAlmacen));
                    contratos.setIdDestino(new Destinos(idDestino));
                    contratos.setIdFundamentoLegal(new FundamentoLegal(idFundamentoLegal));
                    contratos.setIdPartidaPresupuestal(new PartidaPresupuestal(idPartida));
                    contratos.setIdTipoContrato(new TipoContrato(idTipoContrato));
                    contratos.setIdUnidadesMedicas(new UnidadesMedicas(idUnidadMedica));
                    contratos.setIdTipoConvenio(new TipoConvenio(idTipoConvenio));
                    contratos.setUsuarioModificacion(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
                    contratos.setFechaModificacion(new Date());
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
                    if (contratos.getIdEstatus().getIdEstatus().intValue() == 69) {
                        Clausulado c = (Clausulado) archivosUtilidades.obtieneObjetoSerializableClausulado(archivosUtilidades.NAMEFILECLAUSULACONVENIO, archivosUtilidades.PATHFILESCLAUSULAS);
                        contratos.setClausula(c.getClausula());
                        String nombreArchivo = archivosUtilidades.guardaObjetoSerializableClausulado(contratos, c.getClaveProcedimiento(), c.getClaveProcedimiento(), archivosUtilidades.PATHFILESCLAUSULASCONTRATO);
                        contratos.setClausula(nombreArchivo);
                    }
                    if (contratoService.actualizaContrato(contratos)) {
                        bitacoraTareaEstatus.setDescripcion("Guarda contrato:" + contratos.getNumeroConvenio() + "");
                        bitacoraTareaEstatus.setFecha(new Date());
                        bitacoraTareaEstatus.setIdEstatus(69);
                        bitacoraTareaEstatus.setIdModulos(2);
                        bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                        bitacoraTareaEstatus.setIdTareaId(5);
                        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                        mensaje.mensaje(mensaje.datos_actualizados, "verde");
                        this.recargaPantalla();
                    } else {
                        mensaje.mensaje(mensaje.error_guardar, "rojo");
                    }
                }
            } else {
                if (contratoService.actualizaContrato(contratos)) {
                    bitacoraTareaEstatus.setDescripcion("Actualizar contrato:" + contratos.getNumeroConvenio() + "");
                    bitacoraTareaEstatus.setFecha(new Date());
                    bitacoraTareaEstatus.setIdEstatus(69);
                    bitacoraTareaEstatus.setIdModulos(2);
                    bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                    bitacoraTareaEstatus.setIdTareaId(5);
                    bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                    mensaje.mensaje(mensaje.datos_guardados, "verde");
                    this.recargaPantalla();
                } else {
                    mensaje.mensaje(mensaje.error_guardar, "rojo");
                }
            }
        }
    }

    public void recargaPantalla() {
        util.destroyContextMap("contratos");
        util.setContextAtributte("contratos", this.contratos);
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect("detalleConvenio.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DetalleContratoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void validaCancelaConvenio() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg5').show();");
    }

    public void cancelaConvenio() {
        contratos.setActivo(0);
        contratos.setUsuarioBaja(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
        contratos.setFechaBaja(new Date());
        if (contratoFalloProcedimientoRcbService.borrarByIdContrato(contratos.getIdContrato())) {
            if (contratoService.actualizaContrato(contratos)) {
                mensaje.mensaje(mensaje.datos_eliminados, "verde");
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlg6').show();");
            } else {
                mensaje.mensaje(mensaje.error_borrar, "rojo");
            }
        } else {
            mensaje.mensaje(mensaje.error_borrar, "rojo");
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

    public void generarReporteConvenio() throws JRException, IOException {
        NumeroLetra numLetra = new NumeroLetra();
        ArchivosUtilidades archivoUtilidades = new ArchivosUtilidades();
        Integer cantidadPiezas = 0;
        BigDecimal precioUnitario = BigDecimal.ZERO;
        String clave = "";
        String descripcion = "";
        String unidadPieza = "";
        Integer renglon = 0;
        List<InsumosDTO> insumosList = new ArrayList();
        List<ContratoDTO> convenioList = new ArrayList();
        List<Contratos> contratoList = new ArrayList();
        String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ISSSTE.png");
        contratoList = contratoService.contratoConvenioById(contratos.getIdContrato());
        for (Contratos c : contratoList) {
            ContratoDTO con = new ContratoDTO();
            con.setNumeroConvenio(c.getNumeroConvenio());
            for (CompradorContrato cp : c.getCompradorContratoList()) {
                con.setCompradorApellido(cp.getIdComprador().getApaterno());
                con.setCompradorNombre(cp.getIdComprador().getNombre());
                con.setCompradorApellidoMaterno(cp.getIdComprador().getAmaterno());
            }
            con.setCompradorApellido(compradorContrato.getIdComprador().getApaterno());
            con.setCompradorNombre(compradorContrato.getIdComprador().getNombre());
            con.setCompradorApellidoMaterno(compradorContrato.getIdComprador().getAmaterno());
            con.setDescripcionFundamento(c.getIdFundamentoLegal().getDescripcion());
            for (ContratoFalloProcedimientoRcb cfpr : c.getIdContratoFalloProcedimientoRcbList()) {
                InsumosDTO i = new InsumosDTO();
                nep = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNep();
                rcb = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNumero();
                oficio = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getOficioSuficienciaPresupuestal();
                con.setSiglaTipoInsumos(cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla());
                con.setNotas(c.getNotas());
                con.setRcb(cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNumero());
                con.setOficioSuficienciaPresupuestal(cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getOficioSuficienciaPresupuestal());
                con.setDireccion(cfpr.getIdFalloProcedimientoRcb().getIdProveedor().getDireccion());
                con.setNombreProveedor(cfpr.getIdFalloProcedimientoRcb().getIdProveedor().getNombreProveedor());
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
                renglon = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdInsumo();

                i.setCantidadPiezas(cantidadPiezas);
                i.setClave(clave);
                i.setDescripcion(descripcion);
                i.setIdClave(renglon);
                i.setImporteClave(importeTotal);
                i.setPrecioUnitario(precioUnitario);
                i.setUnidadPieza(unidadPieza);
                i.setNep(nep);
                i.setRcb(rcb);
                i.setOficio(oficio);
                insumosList.add(i);
                con.setInsumosList(insumosList);
            }
            con.setNoRupa(c.getIdContratoFalloProcedimientoRcbList().get(0).getIdFalloProcedimientoRcb().getIdProveedor().getNoRupa());
            con.setFechaContrato(c.getFechaContrato());
            con.setFundamentoLegal(c.getIdFundamentoLegal().getNombre());
            con.setImporte(c.getImporte());
            con.setNep(c.getNep());
            con.setNumeroContrato(c.getNumeroContrato() + con.getSiglaTipoInsumos());
            con.setPartida(c.getIdPartidaPresupuestal().getPartidaPresupuestal());
            con.setTipoContrato(c.getIdTipoContrato().getDescripcion());
            con.setVigenciaFinal(c.getVigenciaFinal());
            con.setVigenciaInicial(c.getVigenciaInicial());
            con.setAño(c.getAnioAfectacion());
            Clausulado cal = (Clausulado) archivoUtilidades.obtieneObjetoSerializableClausulado(archivoUtilidades.NAMEFILECLAUSULACONTRATO, archivoUtilidades.PATHFILESCLAUSULAS);
            con.setClausulas(cal.getClausula());
            String numero = String.valueOf(c.getImporte());
            con.setImporteLetra(numLetra.convertir(numero, true));
            con.setRutaLogo(logoPath);
            convenioList.add(con);
        }

        imprimirConvenio(convenioList);
    }

    public void imprimirConvenio(List<ContratoDTO> convenioList) throws JRException, IOException {
        JasperPrint jasperPrint;
        System.out.println("entro imprimir");
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(convenioList);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/convenioInsumos.jasper");
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

    public Contratos getContratos() {
        return contratos;
    }

    public void setContratos(Contratos contratos) {
        this.contratos = contratos;
    }

    public Contratos getContratosAux() {
        return contratosAux;
    }

    public void setContratosAux(Contratos contratosAux) {
        this.contratosAux = contratosAux;
    }

    public Contratos getContratoBusqueda() {
        return contratoBusqueda;
    }

    public void setContratoBusqueda(Contratos contratoBusqueda) {
        this.contratoBusqueda = contratoBusqueda;
    }

    public RepositorioDocumentosDTO getRepositorioDocumentosDTO() {
        return repositorioDocumentosDTO;
    }

    public void setRepositorioDocumentosDTO(RepositorioDocumentosDTO repositorioDocumentosDTO) {
        this.repositorioDocumentosDTO = repositorioDocumentosDTO;
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

    public List<Contratos> getListaConvenios() {
        return listaConvenios;
    }

    public void setListaConvenios(List<Contratos> listaConvenios) {
        this.listaConvenios = listaConvenios;
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

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
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

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

}
