package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.RemisionDTO;
import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.CancelacionesService;
import com.issste.sicabis.ejb.ln.ContratoService;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.ln.LoteService;
import com.issste.sicabis.ejb.ln.OrdenSuministroService;
import com.issste.sicabis.ejb.ln.RemisionesService;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.ln.TipoDocumentosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Cancelaciones;
import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Lote;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcbDestinos;
import com.issste.sicabis.ejb.modelo.Remisiones;
import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.BitacoraUtil;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.NumeroLetra;
import com.issste.sicabis.utils.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author fabianvr
 */
public class RecepcionInsumosBean {

    @EJB
    private CancelacionesService cancelacionesService;
    @EJB
    private OrdenSuministroService ordenSuministroService;
    @EJB
    private ContratoService contratoService;
    @EJB
    private LoteService loteService;
    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;
    @EJB
    private TipoDocumentosService tipoDocumentosService;
    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;
    @EJB
    private RemisionesService remisionesService;


    /*
     Implementacion de bitacora
     */
    @EJB
    private BitacoraTareaSerice bitacoraService;
    private BitacoraTareaEstatus bitacora = new BitacoraTareaEstatus();
    private BitacoraUtil bitacoraUtil = new BitacoraUtil();

    private NumeroLetra numLetra = new NumeroLetra();
    private Mensajes mensaje = new Mensajes();
    private Estatus estatus;
    private RepositorioDocumentosDTO repositorioDocumentosDTO;
    private RepositorioDocumentosDTO repositorioDocumentosDTOAux;
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();
    private RespositorioDocumentos respositorioDocumentos;
    private Usuarios usuarios;
    private Remisiones remisiones;
    private List<Remisiones> remisionesList;
    private List<Remisiones> remisionesBienesList;
    private String registroControl;
    private Integer cantidadInicial;
    private Integer cantidadRecibida;
    private boolean verMensaje;
    private Date fechaFin;
    private Date fechaInicio;
    private Date fechaInicial;
    private Date fechaFinal;
    private String fechaInicialText;
    private String fechaFinalText;
    private boolean verMensaje2;
    private boolean verCargaDocumentos;
    private boolean disabledFolio;
    private boolean disabledCantidadRecibida;
    private List<Remisiones> remisionesRegistro;
    private UploadedFile uploadedfile;
    private Integer idTipoDocumento;
    private List<TipoDocumentos> listaTipoDocs;
    private Integer idRemision;
    private List<RespositorioDocumentos> listaRepoDocs;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private final Integer idTareaProc = 8;
    private String folioRemision;
    private Integer idDetalle;
    private List<Remisiones> remList;
    private List<RemisionDTO> remisionList;
    private List<RemisionDTO> remisionListCero;
    private boolean verBotonDescargarRemision;
    private List<Lote> lotesList;
    private StreamedContent file;
    private boolean bcanjes;
    private Integer dictamen;
    private boolean verDetalleDefecto;
    private String descripcion;

    public RecepcionInsumosBean() {
        remisionList = new ArrayList();
        bitacora = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void remsionesInsumos() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        listaTipoDocs = tipoDocumentosService.obtenerByIdTarea(idTareaProc);
        remisionesByRecepcionBienes();
        disabledFolio = true;
    }

    public void remisionesByRecepcionBienes() {
        remisionesList = remisionesService.remisionesByRecepcionBienes();
    }

    public void buscarRemision() {
        System.out.println("Registro de control " + registroControl);
        remisionesBienesList = remisionesService.remisionesBienesByRegistro(registroControl);
        if (remisionesBienesList != null) {
            for (Remisiones r : remisionesBienesList) {
                cantidadInicial = r.getCantidadRecibida();
                cantidadRecibida = r.getCantidadRecibida();
                lotesList = loteService.loteByR(r.getIdRemision());
                habilitarFolio();
            }
        } else {
            verMensaje = true;
            mensaje.mensaje("No hay remisiones actualmente", "amarillo");
        }
    }

    public void habilitarCantidadLote() {
        disabledCantidadRecibida = Objects.equals(remisionesBienesList.get(0).getCantidadRecibida(), cantidadRecibida);
    }

    public void habilitarFolio() {
        habilitarCantidadLote();
        disabledFolio = !Objects.equals(remisionesBienesList.get(0).getCantidadRecibida(), cantidadRecibida);
    }

    public int validaCantidadLote() {
        int cantidad = 0;
        if (lotesList != null) {
            for (Lote lote : lotesList) {
                cantidad += lote.getCantidadLote();
            }
        }
        return cantidad;
    }

    public void guardarRecepcionBienes() throws JRException, IOException, ParseException {
        Double porcentaje = 0.0;
        List<Contratos> listContratos = new ArrayList<>();
        List<Cancelaciones> listCancelaciones = new ArrayList<>();
        OrdenSuministro orden = new OrdenSuministro();
        DetalleOrdenSuministro dos = new DetalleOrdenSuministro();
        if (validaVacio() != false) {
            if (validaCantidadLote() == cantidadRecibida) {
                Remisiones remisiones = new Remisiones();
                for (Remisiones r : remisionesBienesList) {
                    remisiones = r;
                    idRemision = r.getIdRemision();
                }
                if (folioRemision != null) {
                    remisiones.setFolioRemision(folioRemision);
                }

                remisiones.setActivo(1);
                remisiones.setCantidadRecibida(cantidadRecibida);
                if (!disabledFolio) {
                    if (Objects.equals(cantidadRecibida, cantidadInicial)) {
                        remisiones.setIdEstatus(new Estatus(85));
                    } else {
                        remisiones.setIdEstatus(new Estatus(86));
                    }
                } else {
                    if (dictamen == 1) {
                        remisiones.setIdEstatus(new Estatus(85));
                    } else {
                        remisiones.setIdEstatus(new Estatus(183));
                        remisiones.setDescripcionDefecto(descripcion);
                    }
                }
                boolean valida = true;
                if (remisiones.getIdCanjePermuta() == null) {
                    Date fechaActual = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    String xFecha = format.format(fechaActual);
                    fechaActual = format.parse(xFecha);
                    Date fechaFinRemision = remisiones.getIdDetalleOrdenSuministro().getFechaEntregaFinal();
                    int fechasInt = fechaActual.compareTo(fechaFinRemision);
                    if (!(fechasInt <= 0)) {
                        //valida = false; //revisar regla con Fabian---------------------->
                        mensaje.mensaje("La remisión no esta dentro del rango de fecha de entrega", "amarillo");
                    }
                } else {
                    bcanjes = true;
                }
                if (valida) {
                    // Coloque esta seccion dentro de un if para validar que la fecha de remision estuviese dentro del rango
                    boolean bandera = remisionesService.actualizarRemision(remisiones);
                    if (registroControl.contains("C") || registroControl.contains("P")) {
                        System.out.println("permuta o canje");
                    } else {
                        listContratos = contratoService.obtenerByNumeroContratoOrderById(remisiones.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getNumeroContrato());
                        porcentaje = remisionesService.obtenerPorcentajePiezasPorSuministrar(remisiones.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getNumeroContrato(), remisiones.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getClaveInsumo());
                        if (remisiones.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdEstatus().getIdEstatus() == 75) {
                            if (porcentaje < .40) {
                                orden = remisiones.getIdDetalleOrdenSuministro().getIdOrdenSuministro();
                                //Cancelaciones
                                listCancelaciones = cancelacionesService.cancelaciones(remisiones.getIdDetalleOrdenSuministro().getIdDetalleOrdenSuministro());
                                listCancelaciones.get(0).setActivo(0);
                                cancelacionesService.actualizar(listCancelaciones.get(0));
                                //Ordenes Suministro
                                orden.setIdEstatus(new Estatus(71));
                                ordenSuministroService.actualizaOrdenSuministro(orden);
                                //DetalleOrdenSuministro
                                dos = remisiones.getIdDetalleOrdenSuministro();
                                dos.setCancelado(0);
                                dos.setTotalCancelado(0);
                                detalleOrdenSuministroService.actualizar(dos);
                            }

                        } else if (remisiones.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getIdEstatus().getIdEstatus() == 60) {
                            if (porcentaje < .40) {
                                for (Contratos iterator : listContratos) {
                                    iterator.setIdEstatus(new Estatus(59));
                                    contratoService.actualizaContrato(iterator);
                                }
                            }
                        }
                    }
                    if (bandera != false) {
                        if (remisiones.getIdCanjePermuta() == null) {
                            idDetalle = remisiones.getIdDetalleOrdenSuministro().getIdDetalleOrdenSuministro();
                            detalle();

                            List<DetalleOrdenSuministro> ListRem = detalleOrdenSuministroService.ordenById(remisiones.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdOrdenSuministro());
                            int cantEnt = 0;
                            int cantSum = 0;
                            for (DetalleOrdenSuministro iterator : ListRem) {
                                cantEnt = cantEnt + iterator.getCantidadSuministrada();
                                cantSum = cantSum + iterator.getCantidadSuministrar();
                            }
                            if (cantEnt >= cantSum) {
                                List<OrdenSuministro> o = ordenSuministroService.obtenerByNumeroOrden(remisiones.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getNumeroOrden());
                                o.get(0).setIdEstatus(new Estatus(76));
                                ordenSuministroService.actualizaOrdenSuministro(o.get(0));
                            }
                        }
                        RequestContext.getCurrentInstance().update("recepcionBienes");
                        verMensaje = true;
                        bitacora.setFecha(new Date());
                        bitacora.setIdEstatus(85);
                        bitacora.setDescripcion(bitacoraUtil.detalleGuardaroBitacora("recepcion de bienes"));
                        bitacora.setIdUsuarios(usuarios.getIdUsuario());
                        bitacora.setIdTareaId(idTareaProc);
                        bitacoraService.guardarEnBitacora(bitacora);
                        mensaje.mensaje(mensaje.datos_guardados, "verde");
                        verCargaDocumentos = true;
                        buscarArchivosByIdProcesoIdTarea(idRemision, idTareaProc);
                        verBotonDescargarRemision = true;
                        cantidadRecibida = null;
                        cantidadInicial = null;
                        folioRemision = null;
                        registroControl = null;
                    } else {
                        verMensaje = true;
                        mensaje.mensaje("Error al guardar la remisión");
                    }
                } else {
                    verMensaje = true;
                    mensaje.mensaje("La remisión no se puede guardar debido a que no esta dentro del rango de fecha de entrega", "amarillo");
                }
            } else if (validaCantidadLote() > cantidadRecibida) {
                verMensaje = true;
                mensaje.mensaje("La suma de la cantidad por lote supera a la cantidad recibida en " + (validaCantidadLote() - cantidadRecibida), "amarillo");
            } else if (validaCantidadLote() < cantidadRecibida) {
                verMensaje = true;
                mensaje.mensaje("La cantidad recibida supera a la suma de la cantidad por lote en " + (cantidadRecibida - validaCantidadLote()), "amarillo");
            }
        }
    }

    public void detalle() {
        DetalleOrdenSuministro dos = new DetalleOrdenSuministro();
        List<DetalleOrdenSuministro> detalleList = detalleOrdenSuministroService.detalle(idDetalle);
        for (DetalleOrdenSuministro d : detalleList) {
            dos.setActivo(d.getActivo());
            dos.setCancelado(d.getCancelado());
            cantidadRecibida = d.getCantidadSuministrada() + cantidadRecibida;
            dos.setCantidadSuministrada(cantidadRecibida);
            dos.setCantidadSuministrar(d.getCantidadSuministrar());
            dos.setFechaAlta(d.getFechaAlta());
            dos.setFechaBaja(d.getFechaBaja());
            dos.setFechaEntregaFinal(d.getFechaEntregaFinal());
            dos.setFechaEntregaInicial(d.getFechaEntregaInicial());
            dos.setFechaModificacion(d.getFechaModificacion());
            dos.setIdDetalleOrdenSuministro(idDetalle);
            dos.setIdFalloProcedimientoRcb(d.getIdFalloProcedimientoRcb());
            dos.setIdOrdenSuministro(d.getIdOrdenSuministro());
            dos.setTotalCancelado(d.getTotalCancelado());
            dos.setUsuarioAlta(d.getUsuarioAlta());
            dos.setUsuarioBaja(d.getUsuarioBaja());
            dos.setUsuarioModificacion(d.getUsuarioModificacion());
            boolean actualiza = detalleOrdenSuministroService.actualizar(dos);
        }
    }

    public boolean validaVacio() {
        if (registroControl == null || registroControl.equals("")) {
            verMensaje = true;
            mensaje.mensaje("Ingrese registro de control");
            return false;
        }
        if (cantidadRecibida == null) {
            verMensaje = true;
            mensaje.mensaje("Ingrese cantidad recibida");
            return false;
        }
        if (!disabledFolio) {
            if (folioRemision == null) {
                verMensaje = true;
                mensaje.mensaje("Ingresa folio de remisión");
                return false;
            }
        }
        if (disabledFolio) {
            if (folioRemision != null) {
                List<Remisiones> existe = remisionesService.folioExistente(folioRemision);
                if (existe != null) {
                    for (Remisiones r : existe) {
                        if (folioRemision.equals(r.getFolioRemision())) {
                            verMensaje = true;
                            mensaje.mensaje("El folio ingresado ya esta registrado, favor de ingresar un folio diferente", "amarillo");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void remisionByRegistroInsumos() {
        if (fechaFin != null || fechaInicio != null) {
            this.fechaInicial = fechaInicio;
            this.fechaFinal = fechaFin;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (fechaFin == null) {
                this.fechaInicialText = format.format(this.fechaInicial);
                fechaFinalText = "";
            } else if (fechaInicio == null) {
                this.fechaFinalText = format.format(this.fechaFinal);
                fechaInicialText = "";
            } else if (fechaFin.before(fechaInicio)) {
                verMensaje2 = true;
                mensaje.mensaje("La fecha fin debe ser posterior a la fecha inicio", "amarillo");
            } else {
                this.fechaInicialText = format.format(this.fechaInicial);
                this.fechaFinalText = format.format(this.fechaFinal);
            }
            remisionesRegistro = remisionesService.remisionByRecepcionInsumos(registroControl, fechaInicialText, fechaFinalText);
            if (remisionesRegistro == null) {
                verMensaje2 = true;
                mensaje.mensaje("No hay remisiones", "amarillo");
            }
        } else {
            this.fechaInicialText = "";
            this.fechaFinalText = "";
            remisionesRegistro = remisionesService.remisionByRecepcionInsumos(registroControl, fechaInicialText, fechaFinalText);
            if (remisionesRegistro == null) {
                verMensaje2 = true;
                mensaje.mensaje("No hay remisiones", "amarillo");
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
            respositorioDocumentos.setIdProceso(idRemision);
            respositorioDocumentos.setIdTipoDocumento(new TipoDocumentos(idTipoDocumento));
            respositorioDocumentos.setNombre(event.getFile().getFileName());
            respositorioDocumentos.setRuta(archivosUtilidades.PATHFILES);
            respositorioDocumentos.setUsuarioAlta(usuarios.getNombre());
            String nombreArchivo = archivosUtilidades.generaNombreArchivo(uploadedfile, idTareaProc, respositorioDocumentos.getIdProceso());
            respositorioDocumentos.setNombreArchivo(nombreArchivo);
            if (respositorioDocumentosService.guardaProcedimiento(respositorioDocumentos)) {
                bitacora.setFecha(new Date());
                bitacora.setIdEstatus(85);
                bitacora.setDescripcion("Recepcion de Insumos");
                bitacora.setIdUsuarios(usuarios.getIdUsuario());
                bitacora.setIdTareaId(idTareaProc);
                bitacoraService.guardarEnBitacora(bitacora);
                mensaje.mensaje(mensaje.datos_guardados, "verde");
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
            buscarArchivosByIdProcesoIdTarea(respositorioDocumentos.getIdProceso(), idTareaProc);
        }
    }

    public void buscarArchivosByIdProcesoIdTarea(Integer idProceso, Integer idTarea) {
        listaRepoDocs = respositorioDocumentosService.obtenerByIdProcesoIdTarea(idProceso, idTarea);
        listaRepoDocsDto = new ArrayList();
        System.out.println("");
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
                bitacora.setFecha(new Date());
                bitacora.setIdEstatus(85);
                bitacora.setDescripcion(bitacoraUtil.detalleEliminarBitacora("archivo " + rdd.getIdRespositorioDocumento()));
                bitacora.setIdUsuarios(usuarios.getIdUsuario());
                bitacora.setIdTareaId(idTareaProc);
                bitacoraService.guardarEnBitacora(bitacora);
            }
        }
        listaRepoDocsDto = listaRepoDocsDtoAux;
    }

    public String verDettaleRemision() throws IOException {
        return "detalleRemision.xhtml?faces-redirect=true&idRemision=" + this.remisiones.getIdRemision();
    }

    public void descargarRemision() throws JRException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        remList = remisionesService.remisionByIdRemision(idRemision);
        remisionList = new ArrayList();
        remisionListCero = new ArrayList();
        String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ISSSTE.png");
        for (Remisiones r : remList) {
            RemisionDTO rem = new RemisionDTO();
            RemisionDTO remCero = new RemisionDTO();
            rem.setFolioRemision(r.getFolioRemision());
            remCero.setFolioRemision(r.getFolioRemision());
            String numero = "";
            for (Lote l : r.getLoteList()) {
                String cad = "";
                cad = cad + format.format(l.getFechaCaducidad()) + "\n";
                String fab = "";
                fab = fab + format.format(l.getFechaFabricacion()) + "\n";
                String lote = "";
                lote = lote + l.getLote() + "\n";
                String cantPorLote = "";
                cantPorLote = cantPorLote + l.getCantidadLote() + "\n";
                rem.setCaducidad(cad);
                rem.setFabricacion(fab);
                rem.setLote(lote);
                rem.setCantidadPorLote(cantPorLote);

                remCero.setCaducidad(cad);
                remCero.setFabricacion(fab);
                remCero.setLote(lote);
                remCero.setCantidadPorLote(cantPorLote);
            }
            if (r.getRegistroControl().contains("P") || r.getRegistroControl().contains("C")) {
                rem.setNep("");
                remCero.setNep("");
                remCero.setSigla(r.getIdCanjePermuta().getIdInsumo().getIdTipoInsumos().getSigla());
                remCero.setSigla(r.getIdCanjePermuta().getIdInsumo().getIdTipoInsumos().getSigla());
            } else {
                remCero.setNep(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNep());
                rem.setNep(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNep());
                rem.setSigla(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla());
                remCero.setSigla(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla());
            }
            rem.setCantidad(r.getCantidadRecibida());
            rem.setFechaRemision(r.getFechaRemision());
            rem.setRegistroControl(r.getRegistroControl());
            rem.setAnio(String.valueOf(r.getFechaRemision().getYear()));
            remCero.setCantidad(r.getCantidadRecibida());
            remCero.setFechaRemision(r.getFechaRemision());
            remCero.setRegistroControl(r.getRegistroControl());
            remCero.setAnio(String.valueOf(r.getFechaRemision().getYear()));
            remCero.setRutaLogo(logoPath);
            if (r.getIdCanjePermuta() == null) {
                rem.setNep(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNep());
                rem.setSigla(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla());
                rem.setArticulo(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getIdFundamentoLegal().getDescripcion());
                rem.setClave(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave());
                rem.setDescripcion(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getDescripcion());
                for (ProcedimientoRcbDestinos prd : r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getProcedimientoRcbDestinosList()) {
                    rem.setDestino("CENADI");
                    rem.setDireccionDestino("AV. CENADI");
                }
                if (r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getProcedimientoRcbDestinosList() != null
                        || r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getProcedimientoRcbDestinosList().isEmpty()) {
                    rem.setDestino("");
                    rem.setDireccionDestino("");
                }
                rem.setDireccion(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getDireccion());
                rem.setFechaFin(r.getIdDetalleOrdenSuministro().getFechaEntregaFinal());
                rem.setFechaInicio(r.getIdDetalleOrdenSuministro().getFechaEntregaInicial());
                rem.setGrupo(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdGrupo().getGrupo()); //modifco grupo
                BigDecimal importe = util.agregarDecimales(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario().multiply(new BigDecimal(r.getCantidadRecibida())));
                rem.setImporte(formateador.format(importe.doubleValue()));
                rem.setNombreProveedor(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getNombreProveedor());
                rem.setNumeroContrato(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getNumeroContrato());
                rem.setNumeroOrden(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getNumeroOrden());
                rem.setPartida(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getIdPartidaPresupuestal().getPartidaPresupuestal());
                BigDecimal precioUnitario = r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario();
                rem.setPrecioUnitario(formateador.format(precioUnitario.doubleValue()));
                rem.setRenglon(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdInsumo());
                rem.setRfc(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getRfc());
                rem.setUnidad(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdUnidadPieza().getDescripcion());
                numero = String.valueOf(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario().multiply(new BigDecimal(r.getCantidadRecibida())));
                rem.setNumeroLetra(numLetra.convertir(numero, true));
                rem.setUnidadMedica(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getIdUnidadesMedicas().getNombre());
                rem.setNumeroPorveedor(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getNumero());
                rem.setRutaLogo(logoPath);
                remisionList.add(rem);
                imprimirRemision();
            } else {
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
                rem.setCaducidad(formatDate.format(r.getIdCanjePermuta().getFechaCaducidad()));
                rem.setFabricacion(formatDate.format(r.getIdCanjePermuta().getFechaFabricacionRecibido()));
                rem.setArticulo("");
                rem.setClave(r.getIdCanjePermuta().getIdInsumoCanje().getClave());
                rem.setDescripcion(r.getIdCanjePermuta().getIdInsumoCanje().getDescripcion());
                rem.setDestino("CENADI");
                rem.setDireccionDestino("CENTRO NACIONAL DE DISTRIBUCIÓN, CARRETERA AL LAGO DE GUADALUPE KM. 25.5 SAN PEDRO BARRIENTOS TLANEPANTLA DE BAZ ESTADO DE MÉXICO. C.P. 54010");
                rem.setFechaInicio(r.getFechaRemision());
                rem.setFechaFin(r.getFechaRemision());
                rem.setNumeroPorveedor(r.getIdCanjePermuta().getProveedor().getNumero());
                rem.setNombreProveedor(r.getIdCanjePermuta().getProveedor().getNombreProveedor());
                rem.setDireccion(r.getIdCanjePermuta().getProveedor().getDireccion());
                rem.setRfc(r.getIdCanjePermuta().getProveedor().getRfc());
                rem.setGrupo(r.getIdCanjePermuta().getIdInsumoCanje().getIdGrupo().getGrupo());
                BigDecimal importe = util.agregarDecimales(r.getIdCanjePermuta().getPrecioCanjePermuta().multiply(new BigDecimal(r.getCantidadRecibida())));
                rem.setImporte(formateador.format(importe.doubleValue()));
                rem.setNumeroContrato("");
                rem.setNumeroOrden("");
                rem.setPartida("");
                BigDecimal precioUnitario = util.agregarDecimales(r.getIdCanjePermuta().getPrecioCanjePermuta());
                rem.setPrecioUnitario(formateador.format(precioUnitario.doubleValue()));
                rem.setRenglon(r.getIdCanjePermuta().getIdInsumoCanje().getIdInsumo());
                rem.setUnidad(r.getIdCanjePermuta().getIdInsumoCanje().getIdUnidadPieza().getDescripcion());
                numero = String.valueOf(rem.getImporte());
                rem.setNumeroLetra(numLetra.convertir(numero, true));
                rem.setUnidadMedica("DIRECCIÓN MÉDICA");
                rem.setRutaLogo(logoPath);
                remisionList.add(rem);

                remCero.setCaducidad(formatDate.format(r.getIdCanjePermuta().getFechaCaducidad()));
                remCero.setFabricacion(formatDate.format(r.getIdCanjePermuta().getFechaFabricacionRecibido()));
                remCero.setArticulo("");
                remCero.setClave(r.getIdCanjePermuta().getIdInsumoCanje().getClave());
                remCero.setDescripcion(r.getIdCanjePermuta().getIdInsumoCanje().getDescripcion());
                remCero.setDestino("CENADI");
                remCero.setDireccionDestino("CENTRO NACIONAL DE DISTRIBUCIÓN, CARRETERA AL LAGO DE GUADALUPE KM. 25.5 SAN PEDRO BARRIENTOS TLANEPANTLA DE BAZ ESTADO DE MÉXICO. C.P. 54010");
                remCero.setFechaInicio(r.getFechaRemision());
                remCero.setFechaFin(r.getFechaRemision());
                remCero.setNumeroPorveedor(r.getIdCanjePermuta().getProveedor().getNumero());
                remCero.setNombreProveedor(r.getIdCanjePermuta().getProveedor().getNombreProveedor());
                remCero.setDireccion(r.getIdCanjePermuta().getProveedor().getDireccion());
                remCero.setRfc(r.getIdCanjePermuta().getProveedor().getRfc());
                remCero.setGrupo(r.getIdCanjePermuta().getIdInsumoCanje().getIdGrupo().getGrupo());
                remCero.setNumeroContrato("");
                remCero.setNumeroOrden("");
                remCero.setPartida("");
                remCero.setRenglon(r.getIdCanjePermuta().getIdInsumoCanje().getIdInsumo());
                remCero.setUnidad(r.getIdCanjePermuta().getIdInsumoCanje().getIdUnidadPieza().getDescripcion());
                remCero.setUnidadMedica("DIRECCIÓN MÉDICA");
                remCero.setPrecioUnitario("0.00");
                remCero.setImporte("0.00");
                remCero.setNumeroLetra("SIN COSTO POR TRATARSE DE CANJE FÍSICO");
                remCero.setRutaLogo(logoPath);
                remisionListCero.add(remCero);
                imprimirCanjes();
            }
        }
    }

    JasperPrint jasperPrint;

    public void imprimirRemision() throws JRException, IOException {
        System.out.println("entro imprimir");
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(remisionList);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/remisiones.jasper");
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

    JasperPrint jasperPrintCero;

    public void imprimirCanjes() throws JRException, IOException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(remisionList);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/canjes.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        beanCollectionDataSource = new JRBeanCollectionDataSource(remisionListCero);
        reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/canjes.jasper");
        jasperPrintCero = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        this.generarReporteCompleto();
    }

    public byte[] generarReporteCompleto() throws JRException {
        List<JasperPrint> jasperPrintList = new ArrayList<>();
        jasperPrintList.add(jasperPrint);
        jasperPrintList.add(jasperPrintCero);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
        exporter.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS, Boolean.TRUE);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
        exporter.exportReport();
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        file = new DefaultStreamedContent(is, "application/pdf", "canjes.pdf");
        return baos.toByteArray();
    }

    public void mostrarDefecto() {
        if (dictamen != null || dictamen != -1) {
            if (dictamen == 1) {
                verDetalleDefecto = false;
                dictamen = 1;
            } else if (dictamen == 2) {
                verDetalleDefecto = true;
                dictamen = 2;
                disabledFolio = true;
            }
        }
    }

    public List<Remisiones> getRemisionesList() {
        return remisionesList;
    }

    public void setRemisionesList(List<Remisiones> remisionesList) {
        this.remisionesList = remisionesList;
    }

    public String getRegistroControl() {
        return registroControl;
    }

    public void setRegistroControl(String registroControl) {
        this.registroControl = registroControl;
    }

    public Integer getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(Integer cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public Integer getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(Integer cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public List<Remisiones> getRemisionesBienesList() {
        return remisionesBienesList;
    }

    public void setRemisionesBienesList(List<Remisiones> remisionesBienesList) {
        this.remisionesBienesList = remisionesBienesList;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getFechaInicialText() {
        return fechaInicialText;
    }

    public void setFechaInicialText(String fechaInicialText) {
        this.fechaInicialText = fechaInicialText;
    }

    public String getFechaFinalText() {
        return fechaFinalText;
    }

    public void setFechaFinalText(String fechaFinalText) {
        this.fechaFinalText = fechaFinalText;
    }

    public boolean isVerMensaje2() {
        return verMensaje2;
    }

    public void setVerMensaje2(boolean verMensaje2) {
        this.verMensaje2 = verMensaje2;
    }

    public List<Remisiones> getRemisionesRegistro() {
        return remisionesRegistro;
    }

    public void setRemisionesRegistro(List<Remisiones> remisionesRegistro) {
        this.remisionesRegistro = remisionesRegistro;
    }

    public Remisiones getRemisiones() {
        return remisiones;
    }

    public void setRemisiones(Remisiones remisiones) {
        this.remisiones = remisiones;
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

    public List<RepositorioDocumentosDTO> getListaRepoDocsDto() {
        return listaRepoDocsDto;
    }

    public void setListaRepoDocsDto(List<RepositorioDocumentosDTO> listaRepoDocsDto) {
        this.listaRepoDocsDto = listaRepoDocsDto;
    }

    public boolean isVerCargaDocumentos() {
        return verCargaDocumentos;
    }

    public void setVerCargaDocumentos(boolean verCargaDocumentos) {
        this.verCargaDocumentos = verCargaDocumentos;
    }

    public String getFolioRemision() {
        return folioRemision;
    }

    public void setFolioRemision(String folioRemision) {
        this.folioRemision = folioRemision;
    }

    public boolean isVerBotonDescargarRemision() {
        return verBotonDescargarRemision;
    }

    public void setVerBotonDescargarRemision(boolean verBotonDescargarRemision) {
        this.verBotonDescargarRemision = verBotonDescargarRemision;
    }

    public boolean isDisabledFolio() {
        return disabledFolio;
    }

    public void setDisabledFolio(boolean disabledFolio) {
        this.disabledFolio = disabledFolio;
    }

    public List<Lote> getLotesList() {
        return lotesList;
    }

    public void setLotesList(List<Lote> lotesList) {
        this.lotesList = lotesList;
    }

    public boolean isDisabledCantidadRecibida() {
        return disabledCantidadRecibida;
    }

    public void setDisabledCantidadRecibida(boolean disabledCantidadRecibida) {
        this.disabledCantidadRecibida = disabledCantidadRecibida;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public boolean isBcanjes() {
        return bcanjes;
    }

    public void setBcanjes(boolean bcanjes) {
        this.bcanjes = bcanjes;
    }

    public Integer getDictamen() {
        return dictamen;
    }

    public void setDictamen(Integer dictamen) {
        this.dictamen = dictamen;
    }

    public boolean isVerDetalleDefecto() {
        return verDetalleDefecto;
    }

    public void setVerDetalleDefecto(boolean verDetalleDefecto) {
        this.verDetalleDefecto = verDetalleDefecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
