package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
import com.issste.sicabis.ejb.DTO.RcbConsultaViewDto;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.CaracterProcedimientoService;
import com.issste.sicabis.ejb.ln.ClasificacionProcedimientoService;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.ProcedimientoService;
import com.issste.sicabis.ejb.ln.ProcedimientosRcbService;
import com.issste.sicabis.ejb.ln.RcbInsumosService;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.ln.TipoCompraService;
import com.issste.sicabis.ejb.ln.TipoDocumentosService;
import com.issste.sicabis.ejb.ln.TipoProcedimientoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.CaracterProcedimiento;
import com.issste.sicabis.ejb.modelo.ClasificacionProcedimiento;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.ejb.modelo.TipoCompra;
import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import com.issste.sicabis.ejb.modelo.TipoProcedimiento;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

public class DetalleProcedimientoBean {

    @EJB
    private RcbInsumosService rcbInsumosService;

    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;

    @EJB
    private TipoDocumentosService tipoDocumentosService;

    @EJB
    private ProcedimientosRcbService procedimientosRcbService;

    @EJB
    private ProcedimientoService procedimientoService;

    @EJB
    private TipoProcedimientoService tipoProcedimientoService;

    @EJB
    private TipoCompraService tipoCompraService;

    @EJB
    private EstatusService estatusService;

    @EJB
    private ClasificacionProcedimientoService clasificacionProcedimientoService;

    @EJB
    private CaracterProcedimientoService caracterProcedimientoService;

    /*
     Implementacion de bitacora
     */
    @EJB
    BitacoraTareaSerice bitacoraService;
    BitacoraTareaEstatus bitacora;
    BitacoraUtil bitacoraUtil = new BitacoraUtil();

    private Procedimientos procedimientos;
    private Usuarios usuarios;
    private RespositorioDocumentos respositorioDocumentos;
    private RepositorioDocumentosDTO repositorioDocumentosDTO;
    private RepositorioDocumentosDTO repositorioDocumentosDTOAux;

    private boolean bcompleta = true;
    private boolean mensajeBorrar;
    private boolean messageGuardar = true;
    private int tipoCompra;
    private List<TipoCompra> listaTipoCompra;
    private List<ClasificacionProcedimiento> listaClasificacionProc;
    private List<CaracterProcedimiento> listaCaracterProc;
    private List<TipoProcedimiento> listaTipoProc;
    private List<Estatus> listaEstatusProc;
    private boolean bTipoCompra;
    private List<ProcedimientoRcb> listaProcRcb;
    private int opcion;
    private BigDecimal importeTotal;
    private List<Procedimientos> listaProcedimientos;
    private int tipoProc;
    private int caracterProc;
    private List<RcbInsumos> listaRcbInsumos;
    private Integer idProcedimiento;
    private boolean bcambio;
    private UploadedFile uploadedfile;
    private Integer idEstatus;
    private String numeroProcedimiento;
    private Integer idClasificacionProcedimiento;
    private Integer idTipoProcedimiento;
    private Integer idCaracterProcedimiento;
    private Integer idTipoDocumento;
    private List<TipoDocumentos> listaTipoDocs;
    private List<RespositorioDocumentos> listaRepoDocs;
    private StreamedContent file;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private List<ProcedimientoRcb> listaProcRcbFilter;
    private String mensajeDialog;
    private boolean botonDialog;
    private FileUploadEvent event1;
    private final Integer idTareaProc = 3;

    private String numeroRCB;
    private List<RcbInsumos> listaRcbInsumosA;
    private List<RcbInsumos> listaRcbInsumosFilterA;
    private List<RcbInsumos> listaRcbInsumosSelectA;
    private List<RcbInsumos> listaProcInsumos;
    private List<ProcedimientoRcb> listaProcRcbA;
    private List<ProcedimientoRcb> listaProcRcbFilterA;
    private List<ProcedimientoRcb> listaProcRcbSelectA;
    private List<ProcedimientoRcb> listaProcRcbTotalA;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();

    public DetalleProcedimientoBean() {
        bitacora = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        listaRcbInsumos = new ArrayList();
        opcion = 0;
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        procedimientos = (Procedimientos) util.getContextAtributte("procedimientos");
        bitacora = new BitacoraTareaEstatus();
        procedimientos.setFechaProcedimiento(new Date());
        if (procedimientos.getInvitacion3Per() == null) {
            procedimientos.setInvitacion3Per(new Date());
        }
        if (procedimientos.getElaboracionConvocatoria() == null) {
            procedimientos.setElaboracionConvocatoria(new Date());
        }
        if (procedimientos.getJuntaAclaracionesConvocatoria() == null) {
            procedimientos.setJuntaAclaracionesConvocatoria(new Date());
        }
        if (procedimientos.getJuntaConclusionAclaracionesConvocatoria() == null) {
            procedimientos.setJuntaConclusionAclaracionesConvocatoria(new Date());
        }
        if (procedimientos.getPublicacionConvocatoria() == null) {
            procedimientos.setPublicacionConvocatoria(new Date());
        }
        idEstatus = procedimientos.getIdEstatus().getIdEstatus();
        numeroProcedimiento = procedimientos.getNumeroProcedimiento();
        idCaracterProcedimiento = procedimientos.getIdCaracterProcedimiento().getIdCaracterProcedimiento();
        idClasificacionProcedimiento = procedimientos.getIdClasificacionProcedimiento().getIdClasificacionProcedimiento();
        idTipoProcedimiento = procedimientos.getIdTipoProcedimiento().getIdTipoProcedimiento();
        listaTipoCompra = tipoCompraService.traeListaTipoCompra();
        listaClasificacionProc = clasificacionProcedimientoService.obtenerClasificacionProcedimiento();
        listaCaracterProc = caracterProcedimientoService.obtenerCaracterProcedimiento();
        listaTipoProc = tipoProcedimientoService.obtenerTiposProcedimientos();
        listaEstatusProc = estatusService.getEstatusByTarea(3);
        listaProcRcb = new ArrayList();
        listaProcRcb = procedimientosRcbService.obtenerByIdProcedimiento(procedimientos.getIdProcedimiento());
        procedimientos.setProcedimientoRcbList(listaProcRcb);
        listaTipoDocs = tipoDocumentosService.obtenerByIdTarea(idTareaProc);
        idTipoDocumento = -1;
        try {
            if (listaProcRcb.size() > 0) {
                bTipoCompra = true;
                tipoCompra = listaProcRcb.get(0).getIdProcedimiento().getIdTipoCompra().getIdTipoCompra();
            } else {
                listaProcRcb = new ArrayList();
            }
        } catch (Exception ex) {
            Logger.getLogger(DetalleProcedimientoBean.class.getName()).log(Level.SEVERE, "fallo proc bean", ex);
        }
        if (procedimientos.getIdEstatus().getIdEstatus() == 32) {
            opcion = 1;
            bcompleta = false;
            buscarArchivosByIdProcesoIdTarea(procedimientos.getIdProcedimiento(), idTareaProc);
        }
        importeTotal = procedimientos.getImporteTotal();
    }

    public void limpiar() {
        RequestContext.getCurrentInstance().reset("formProc");
        init();
    }

    public void existeNumeroProcedimiento() {
        if (!numeroProcedimiento.equals(procedimientos.getNumeroProcedimiento())) {
            boolean bandera = this.validaNumeroProcedimiento();
        }
    }

    public boolean validaNumeroProcedimiento() {
        boolean bandera = true;
        Procedimientos pAux = procedimientoService.obtenerByNumeroProcedimiento(procedimientos.getNumeroProcedimiento());
        if (pAux != null) {
            mensaje.mensaje("El número de procedimiento ya esta registrado", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public String regresar() {
        util.destroyContextMap("procedimientos");
        return "procedimiento.xhtml";
    }

    public boolean valida() {
        boolean bandera = true;
        if (procedimientos.getNumeroProcedimiento().equals("")) {
            mensaje.mensaje("Debes capturar el número del procedimiento", "amarillo");
            bandera = false;
        } else {
            if (!numeroProcedimiento.equals(procedimientos.getNumeroProcedimiento())) {
                bandera = this.validaNumeroProcedimiento();
            }
        }
        if (idEstatus.intValue() == -1) {
            mensaje.mensaje("Debes seleccionar un estatus del procedimiento", "amarillo");
            bandera = false;

        } else if (idEstatus.intValue() == 32) {
            if (listaProcRcb == null) {
                mensaje.mensaje("Debes agregar al menos una clave al procedimiento", "amarillo");
                bandera = false;
            }
        }
        if (idClasificacionProcedimiento.intValue() == -1) {
            mensaje.mensaje("Debes seleccionar una clasificación del procedimiento", "amarillo");
            bandera = false;
        }
        if (idCaracterProcedimiento.intValue() == -1) {
            mensaje.mensaje("Debes seleccionar una clasificación del procedimiento", "amarillo");
            bandera = false;
        }
        if (idTipoProcedimiento.intValue() == -1) {
            mensaje.mensaje("Debes seleccionar una clasificación del procedimiento", "amarillo");
            bandera = false;
        }

        return bandera;
    }

    public boolean validarGuardado() {
        if (idEstatus == 32) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlg1').show();");
        } else {
            this.guardarActualizarProcedimiento();
        }
        return true;
    }

    public void actualizarCamposConvocatoria() {
        if (procedimientoService.actualizaProcedimiento(procedimientos)) {
            mensaje.mensaje("Campos actualizados exitosamente.", "verde");
        }
    }

    public void guardarActualizarProcedimiento() {
        Procedimientos procedimientosAux = new Procedimientos();
        if (valida()) {
            boolean bandera = true;
            procedimientosAux.setIdProcedimiento(procedimientos.getIdProcedimiento());
            procedimientosAux.setVerificaSansiones(0);
            procedimientosAux.setActivo(1);
            procedimientosAux.setFechaAlta(procedimientos.getFechaAlta());
            procedimientosAux.setFechaProcedimiento(procedimientos.getFechaProcedimiento());
            procedimientosAux.setIdCaracterProcedimiento(new CaracterProcedimiento(idCaracterProcedimiento));
            procedimientosAux.setIdClasificacionProcedimiento(new ClasificacionProcedimiento(idClasificacionProcedimiento));
            procedimientosAux.setIdTipoProcedimiento(new TipoProcedimiento(idTipoProcedimiento));
            procedimientosAux.setIdEstatus(new Estatus(idEstatus));
            procedimientosAux.setUsuarioModificacion(usuarios.getNombre());
            procedimientosAux.setFechaModificacion(new Date());
            procedimientosAux.setUsuarioAlta(procedimientos.getUsuarioAlta());
            procedimientosAux.setImporteTotal(importeTotal);
            procedimientosAux.setNumeroProcedimiento(procedimientos.getNumeroProcedimiento());
            for (ProcedimientoRcb pr : listaProcRcb) {
                procedimientosAux.setIdTipoCompra(pr.getIdProcedimiento().getIdTipoCompra());
            }
            List<ProcedimientoRcb> listaProcRcbActualizar = new ArrayList();
            if (bcambio) {
                if (procedimientosRcbService.borrarByIdProcedimiento(procedimientos.getIdProcedimiento())) {
                    for (ProcedimientoRcb pr : listaProcRcb) {
                        if (pr.getDesierta().intValue() != 0 || pr.getDesiertaParcial().intValue() != 0) {
                            pr.setFechaBaja(new Date());
                            pr.setUsuarioBaja(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
                            pr.setActivo(0);
                            listaProcRcbActualizar.add(pr);
                            ProcedimientoRcb prAux = this.nuevoProcRcb(pr);
                            procedimientosRcbService.guardaProcedimientoRcb(prAux);
                        } else {
                            pr.setUsuarioAlta(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
                            pr.setIdProcedimiento(procedimientos);
                            procedimientosRcbService.guardaProcedimientoRcb(pr);
                        }
                    }
                } else {
                    mensaje.mensaje(mensaje.error_guardar, "rojo");
                }
                bcambio = false;
            }

            bandera = procedimientoService.actualizaProcedimiento(procedimientosAux);
            if (bandera) {
                for (ProcedimientoRcb prAct : listaProcRcbActualizar) {
                    procedimientosRcbService.actualizaProcedimientoRcb(prAct);
                }
                bitacora.setFecha(new Date());
                bitacora.setIdEstatus(procedimientos.getIdEstatus().getIdEstatus());
                bitacora.setIdModulos(procedimientos.getIdProcedimiento());
                bitacora.setDescripcion(bitacoraUtil.detalleActualizarBitacora("Actualiza procedimiento"));
                bitacora.setIdUsuarios(usuarios.getIdUsuario());
                bitacora.setIdTareaId(idTareaProc);
                bitacoraService.guardarEnBitacora(bitacora);
                procedimientosAux.setProcedimientoRcbList(listaProcRcb);
                mensaje.mensaje(mensaje.datos_actualizados, "verde");
                if (procedimientosAux.getIdEstatus().getIdEstatus().intValue() == 32) {
                    bcompleta = false;
                } else {
                    this.recargaPantalla(procedimientosAux);
                }
            } else {
                mensaje.mensaje(mensaje.error_guardar, "rojo");
            }
        }
        messageGuardar = true;
    }

    public void recargaPantalla(Procedimientos procedimientosAux) {
        util.destroyContextMap("procedimientos");
        util.setContextAtributte("procedimientos", procedimientosAux);
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            messageGuardar = true;
            mensaje.mensaje("Procedimiento almacenado exitosamente.", "verde");
            ctx.redirect("detalleProcedimiento.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DetalleContratoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ProcedimientoRcb nuevoProcRcb(ProcedimientoRcb pr) {
        ProcedimientoRcb prAux = new ProcedimientoRcb();
        prAux.setActivo(1);
        prAux.setCantidadPiezas(pr.getCantidadPiezas());
        prAux.setCantidadPiezasOriginal(pr.getCantidadPiezasOriginal());
        prAux.setConsumoPromedio(pr.getConsumoPromedio());
        prAux.setDesierta(0);
        prAux.setDesiertaParcial(0);
        prAux.setExistencias(pr.getExistencias());
        prAux.setFechaAlta(new Date());
        prAux.setIdProcedimiento(procedimientos);
        prAux.setIdRcbInsumos(pr.getIdRcbInsumos());
        prAux.setImporte(pr.getImporte());
        prAux.setMesesCobertura(pr.getMesesCobertura());
        prAux.setPrecioUnitario(pr.getPrecioUnitario());
        prAux.setUsuarioAlta(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
        return prAux;
    }

    public void agregaClaves() {
        System.out.println("entre en agrega--->");
        if (tipoCompra != -1) {
            Map<String, Object> options = new HashMap<String, Object>();
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            List<String> values = new ArrayList<String>();
            values.add(String.valueOf(tipoCompra));
            params.put("tipoCompra", values);
            options.put("resizable", false);
            options.put("draggable", false);
            options.put("includeViewParams", true);
            options.put("width", "70%");
            options.put("height", "500");
            options.put("contentWidth", "95%");
            options.put("contentHeight", "500");
            options.put("modal", true);
            RequestContext.getCurrentInstance().openDialog("/vistas/adquisicion/agregaClaves.xhtml", options, params);
        } else {
            messageGuardar = true;
            mensaje.mensaje("Debe seleccionar un tipo de compra primero", "amarillo");
        }
    }

    public void clavesAgregadas(SelectEvent event) {
        List<ProcedimientoRcb> listaClavesSelect = (List<ProcedimientoRcb>) event.getObject();
        List<ProcedimientoRcb> listaProcRcbAux = listaProcRcb;
        if (listaClavesSelect != null) {
            boolean bandera = false;
            ProcedimientoRcb procRCB = null;
            for (ProcedimientoRcb insumosNuevos : listaClavesSelect) {
                for (ProcedimientoRcb pr : listaProcRcbAux) {
                    if (pr.getIdRcbInsumos().getIdRcbInsumos().intValue() == insumosNuevos.getIdRcbInsumos().getIdRcbInsumos().intValue()) {
                        bandera = true;
                        mensaje.mensaje("La clave " + pr.getIdRcbInsumos().getIdRcb().getIdRcb().intValue() + " ya fue agregada a la lista", "amarillo");
                        break;
                    }
                }
                if (!bandera) {
                    bcambio = true;
                    procRCB = new ProcedimientoRcb();
                    procRCB = insumosNuevos;
                    importeTotal = insumosNuevos.getImporte().add(importeTotal);
                    listaProcRcb.add(procRCB);
                }
                bandera = false;
            }
            bTipoCompra = true;
        }
    }
    
    public void clavesAgregadasAux(List<ProcedimientoRcb> listaClavesSelect) {
        List<ProcedimientoRcb> listaProcRcbAux = listaProcRcb;
        if (listaClavesSelect != null) {
            boolean bandera = false;
            ProcedimientoRcb procRCB = null;
            for (ProcedimientoRcb insumosNuevos : listaClavesSelect) {
                for (ProcedimientoRcb pr : listaProcRcbAux) {
                    if (pr.getIdRcbInsumos().getIdRcbInsumos().intValue() == insumosNuevos.getIdRcbInsumos().getIdRcbInsumos().intValue()) {
                        bandera = true;
                        mensaje.mensaje("La clave " + pr.getIdRcbInsumos().getIdRcb().getIdRcb().intValue() + " ya fue agregada a la lista", "amarillo");
                        break;
                    }
                }
                if (!bandera) {
                    bcambio = true;
                    procRCB = new ProcedimientoRcb();
                    procRCB = insumosNuevos;
                    importeTotal = insumosNuevos.getImporte().add(importeTotal);
                    listaProcRcb.add(procRCB);
                }
                bandera = false;
            }
            bTipoCompra = true;
        }
    }

    public void quitarClaves(ProcedimientoRcb procedimientoRcb) {
        List<ProcedimientoRcb> listaProcRcbAux = new ArrayList();
        importeTotal = BigDecimal.ZERO;
        for (ProcedimientoRcb pr : listaProcRcb) {
            if (pr != procedimientoRcb) {
                listaProcRcbAux.add(pr);
                importeTotal = pr.getImporte().add(importeTotal);
            }
        }
        listaProcRcb = new ArrayList();
        listaProcRcb = listaProcRcbAux;
        if (listaProcRcb.size() == 0) {
            bTipoCompra = false;
        }
        bcambio = true;
    }

    public void cambiaTipoDoc() {
        System.out.println("idTipoDocumento--->" + idTipoDocumento);
    }

    public void guardarArchivos(FileUploadEvent event) {
        uploadedfile = event.getFile();
        event1 = event;
    }

    public void carga(FileUploadEvent event) {
        boolean bandera = false;
        if (idTipoDocumento != -1 && event != null) {
            respositorioDocumentos = new RespositorioDocumentos();
            respositorioDocumentos.setActivo(1);
            respositorioDocumentos.setFechaAlta(new Date());
            respositorioDocumentos.setIdProceso(procedimientos.getIdProcedimiento());
            respositorioDocumentos.setIdTipoDocumento(new TipoDocumentos(idTipoDocumento));
            respositorioDocumentos.setNombre(event.getFile().getFileName());
            respositorioDocumentos.setRuta(archivosUtilidades.PATHFILES);
            respositorioDocumentos.setUsuarioAlta(usuarios.getNombre());
            String nombreArchivo = archivosUtilidades.generaNombreArchivo(uploadedfile, idTareaProc, respositorioDocumentos.getIdProceso());
            respositorioDocumentos.setNombreArchivo(nombreArchivo);
            if (respositorioDocumentosService.guardaProcedimiento(respositorioDocumentos)) {
                mensaje.mensaje(mensaje.datos_guardados, "verde");
                //se envia el archivo y el id tarea (3 es id tarea procedimiento)
                if (archivosUtilidades.guardaArchivo(uploadedfile, nombreArchivo)) {
                    bitacora.setFecha(new Date());
                    bitacora.setIdEstatus(procedimientos.getIdEstatus().getIdEstatus());
                    bitacora.setDescripcion(bitacoraUtil.detalleEliminarBitacora("archivo "));
                    bitacora.setIdUsuarios(usuarios.getIdUsuario());
                    bitacora.setIdTareaId(idTareaProc);
                    bitacoraService.guardarEnBitacora(bitacora);
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
            buscarArchivosByIdProcesoIdTarea(respositorioDocumentos.getIdProceso(), idTareaProc);
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

    public void abredialogo() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg3').show();");
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

    public void irRcbDetalleAdjudicacion(Integer id) {
        util.setContextAtributte("idrcb", id);
        util.setContextAtributte("perfilInvestigacion", false);
        util.setContextAtributte("perfilAdjudicacion", true);
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ctx.redirect(ctxPath + "/vistas/rcb/rcbDetalle.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(DetalleProcedimientoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obtenerClaves() {
        listaRcbInsumosA = rcbInsumosService.getListClavesByNumeroTipoCompra(numeroRCB.toUpperCase(), tipoCompra);
        listaProcRcbA = procedimientosRcbService.obtenerByIdNumRcbTipoCompra(numeroRCB, tipoCompra);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg3').show();");
    }
    
    public void agregarClaves() {
        listaProcRcbTotalA = new ArrayList();
        ProcedimientoRcb procRCB = null;
        if (listaRcbInsumosSelectA.size() > 0) {
            for (RcbInsumos insumosNuevos : listaRcbInsumosSelectA) {
                procRCB = new ProcedimientoRcb();
                procRCB.setActivo(1);
                procRCB.setCantidadPiezas(insumosNuevos.getCantidadPiezas());
                procRCB.setIdRcbInsumos(insumosNuevos);
                procRCB.setPrecioUnitario(insumosNuevos.getPrecioUnitario());
                procRCB.setImporte(insumosNuevos.getImporte());
                procRCB.setExistencias(insumosNuevos.getExistencias());
                procRCB.setConsumoPromedio(insumosNuevos.getConsumoPromedio());
                procRCB.setMesesCobertura(insumosNuevos.getMesesCobertura());
                procRCB.setFechaAlta(new Date());
                procRCB.setDesierta(0);
                procRCB.setDesiertaParcial(0);
                listaProcRcbTotalA.add(procRCB);
            }
        }
        if (listaProcRcbSelectA.size() > 0) {
            for (ProcedimientoRcb procRcbNuevo : listaProcRcbSelectA) {
                procRCB = new ProcedimientoRcb();
                procRCB = procRcbNuevo;
                listaProcRcbTotalA.add(procRCB);
            }
        }
        this.clavesAgregadasAux(listaProcRcbTotalA);
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

    public int getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(int tipoCompra) {
        this.tipoCompra = tipoCompra;
    }

    public List<TipoCompra> getListaTipoCompra() {
        return listaTipoCompra;
    }

    public void setListaTipoCompra(List<TipoCompra> listaTipoCompra) {
        this.listaTipoCompra = listaTipoCompra;
    }

    public List<ClasificacionProcedimiento> getListaClasificacionProc() {
        return listaClasificacionProc;
    }

    public void setListaClasificacionProc(List<ClasificacionProcedimiento> listaClasificacionProc) {
        this.listaClasificacionProc = listaClasificacionProc;
    }

    public List<CaracterProcedimiento> getListaCaracterProc() {
        return listaCaracterProc;
    }

    public void setListaCaracterProc(List<CaracterProcedimiento> listaCaracterProc) {
        this.listaCaracterProc = listaCaracterProc;
    }

    public List<TipoProcedimiento> getListaTipoProc() {
        return listaTipoProc;
    }

    public void setListaTipoProc(List<TipoProcedimiento> listaTipoProc) {
        this.listaTipoProc = listaTipoProc;
    }

    public List<Estatus> getListaEstatusProc() {
        return listaEstatusProc;
    }

    public void setListaEstatusProc(List<Estatus> listaEstatusProc) {
        this.listaEstatusProc = listaEstatusProc;
    }

    public boolean isbTipoCompra() {
        return bTipoCompra;
    }

    public void setbTipoCompra(boolean bTipoCompra) {
        this.bTipoCompra = bTipoCompra;
    }

    public List<ProcedimientoRcb> getListaProcRcb() {
        return listaProcRcb;
    }

    public void setListaProcRcb(List<ProcedimientoRcb> listaProcRcb) {
        this.listaProcRcb = listaProcRcb;
    }

    public List<Procedimientos> getListaProcedimientos() {
        return listaProcedimientos;
    }

    public void setListaProcedimientos(List<Procedimientos> listaProcedimientos) {
        this.listaProcedimientos = listaProcedimientos;
    }

    public int getTipoProc() {
        return tipoProc;
    }

    public void setTipoProc(int tipoProc) {
        this.tipoProc = tipoProc;
    }

    public int getCaracterProc() {
        return caracterProc;
    }

    public void setCaracterProc(int caracterProc) {
        this.caracterProc = caracterProc;
    }

    public Procedimientos getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(Procedimientos procedimientos) {
        this.procedimientos = procedimientos;
    }

    public Integer getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(Integer idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public boolean isBcompleta() {
        return bcompleta;
    }

    public void setBcompleta(boolean bcompleta) {
        this.bcompleta = bcompleta;
    }

    public UploadedFile getUploadedfile() {
        return uploadedfile;
    }

    public void setUploadedfile(UploadedFile uploadedfile) {
        this.uploadedfile = uploadedfile;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getNumeroProcedimiento() {
        return numeroProcedimiento;
    }

    public void setNumeroProcedimiento(String numeroProcedimiento) {
        this.numeroProcedimiento = numeroProcedimiento;
    }

    public Integer getIdClasificacionProcedimiento() {
        return idClasificacionProcedimiento;
    }

    public void setIdClasificacionProcedimiento(Integer idClasificacionProcedimiento) {
        this.idClasificacionProcedimiento = idClasificacionProcedimiento;
    }

    public Integer getIdTipoProcedimiento() {
        return idTipoProcedimiento;
    }

    public void setIdTipoProcedimiento(Integer idTipoProcedimiento) {
        this.idTipoProcedimiento = idTipoProcedimiento;
    }

    public Integer getIdCaracterProcedimiento() {
        return idCaracterProcedimiento;
    }

    public void setIdCaracterProcedimiento(Integer idCaracterProcedimiento) {
        this.idCaracterProcedimiento = idCaracterProcedimiento;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
        this.carga(event1);
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

    public List<RepositorioDocumentosDTO> getListaRepoDocsDto() {
        return listaRepoDocsDto;
    }

    public void setListaRepoDocsDto(List<RepositorioDocumentosDTO> listaRepoDocsDto) {
        this.listaRepoDocsDto = listaRepoDocsDto;
    }

    public List<ProcedimientoRcb> getListaProcRcbFilter() {
        return listaProcRcbFilter;
    }

    public void setListaProcRcbFilter(List<ProcedimientoRcb> listaProcRcbFilter) {
        this.listaProcRcbFilter = listaProcRcbFilter;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getMensajeDialog() {
        return mensajeDialog;
    }

    public void setMensajeDialog(String mensajeDialog) {
        this.mensajeDialog = mensajeDialog;
    }

    public boolean isBotonDialog() {
        return botonDialog;
    }

    public void setBotonDialog(boolean botonDialog) {
        this.botonDialog = botonDialog;
    }

    public List<RcbInsumos> getListaRcbInsumos() {
        return listaRcbInsumos;
    }

    public void setListaRcbInsumos(List<RcbInsumos> listaRcbInsumos) {
        this.listaRcbInsumos = listaRcbInsumos;
    }

    public String getNumeroRCB() {
        return numeroRCB;
    }

    public void setNumeroRCB(String numeroRCB) {
        this.numeroRCB = numeroRCB;
    }

    public List<RcbInsumos> getListaRcbInsumosA() {
        return listaRcbInsumosA;
    }

    public void setListaRcbInsumosA(List<RcbInsumos> listaRcbInsumosA) {
        this.listaRcbInsumosA = listaRcbInsumosA;
    }

    public List<RcbInsumos> getListaRcbInsumosFilterA() {
        return listaRcbInsumosFilterA;
    }

    public void setListaRcbInsumosFilterA(List<RcbInsumos> listaRcbInsumosFilterA) {
        this.listaRcbInsumosFilterA = listaRcbInsumosFilterA;
    }

    public List<RcbInsumos> getListaRcbInsumosSelectA() {
        return listaRcbInsumosSelectA;
    }

    public void setListaRcbInsumosSelectA(List<RcbInsumos> listaRcbInsumosSelectA) {
        this.listaRcbInsumosSelectA = listaRcbInsumosSelectA;
    }

    public List<ProcedimientoRcb> getListaProcRcbA() {
        return listaProcRcbA;
    }

    public void setListaProcRcbA(List<ProcedimientoRcb> listaProcRcbA) {
        this.listaProcRcbA = listaProcRcbA;
    }

    public List<ProcedimientoRcb> getListaProcRcbFilterA() {
        return listaProcRcbFilterA;
    }

    public void setListaProcRcbFilterA(List<ProcedimientoRcb> listaProcRcbFilterA) {
        this.listaProcRcbFilterA = listaProcRcbFilterA;
    }

    public List<ProcedimientoRcb> getListaProcRcbSelectA() {
        return listaProcRcbSelectA;
    }

    public void setListaProcRcbSelectA(List<ProcedimientoRcb> listaProcRcbSelectA) {
        this.listaProcRcbSelectA = listaProcRcbSelectA;
    }

    public List<ProcedimientoRcb> getListaProcRcbTotalA() {
        return listaProcRcbTotalA;
    }

    public void setListaProcRcbTotalA(List<ProcedimientoRcb> listaProcRcbTotalA) {
        this.listaProcRcbTotalA = listaProcRcbTotalA;
    }

}
