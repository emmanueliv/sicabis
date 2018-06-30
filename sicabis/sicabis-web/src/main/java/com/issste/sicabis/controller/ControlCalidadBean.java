package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.RemisionDTO;
import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
import com.issste.sicabis.ejb.DTO.RemisionesDTO;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.FabricanteService;
import com.issste.sicabis.ejb.ln.LoteService;
import com.issste.sicabis.ejb.ln.PresentacionService;
import com.issste.sicabis.ejb.ln.ProveedorFabricanteService;
import com.issste.sicabis.ejb.ln.RemisionesService;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.ln.TipoDocumentosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Fabricante;
import com.issste.sicabis.ejb.modelo.Lote;
import com.issste.sicabis.ejb.modelo.Presentacion;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcbDestinos;
import com.issste.sicabis.ejb.modelo.ProveedorFabricante;
import com.issste.sicabis.ejb.modelo.Proveedores;
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
import org.primefaces.model.UploadedFile;

/**
 *
 * @author fabianvr
 */
public class ControlCalidadBean {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private ProveedorFabricanteService proveedorFabricanteService;

    @EJB
    private PresentacionService presentacionService;

    @EJB
    private TipoDocumentosService tipoDocumentosService;

    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;

    @EJB
    private FabricanteService fabricanteService;

    @EJB
    private RemisionesService remisionesService;

    @EJB
    private LoteService loteService;

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
    private Fabricante fabricantes;
    private ProveedorFabricante proveedorFabricante;
    private Usuarios usuarios;
    private Remisiones remisiones;
    private Remisiones remFabricante;
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private List<RemisionesDTO> controlCalidadList;
    private List<RemisionesDTO> remisionRegistroList;
    private List<Fabricante> fabricanteList;
    private List<Fabricante> fabricantesList;
    private List<Remisiones> remiList;
    private List<RemisionesDTO> remisionesByControlList;
    private List<TipoDocumentos> listaTipoDocs;
    private List<RespositorioDocumentos> listaRepoDocs;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private List<Remisiones> remisionesRegistro;
    private List<Proveedores> proveedoresList;
    private List<RemisionDTO> remisionesList;
    private List<Remisiones> remList;
    private List<Lote> lotesList;
    private String registroControl;
    private String articulo;
    private String nombreFabricante;
    private String registroSanitario;
    private String inspeccion = "II Normal";
    private String descripcionDefecto;
    private String nombreF;
    private String registroS;
    private String fechaInicialText;
    private String fechaFinalText;
    private final Integer idTareaProc = 8;
    private Integer idFabricante;
    private Integer numeroFabricante;
    private Integer cantidadRecibida;
    private Integer cantidadMuestra;
    private Integer seleccionarDictamen;
    private Integer selecionarProveedor;
    private Integer idTipoDocumento;
    private Integer idRemision;
    private Integer idPresentacion;
    private double defecto = 2.5;
    private boolean mostrarOneMenu;
    private boolean mostrarInputText;
    private boolean mostrarRegistroSanitario;
    private boolean verMensaje;
    private boolean bandera;
    private boolean verDetalleDefecto;
    private boolean verAgregarFabricante;
    private boolean verMensajeFabricanteExiste;
    private boolean verMensaje2;
    private boolean verCargaDocumentos;
    private boolean verBotonDescargarRemision;
    private Integer canjepermuta;
    public boolean bnuevo;
    private Date fechaFin;
    private Date fechaInicio;
    private Date fechaInicial;
    private Date fechaFinal;
    private UploadedFile uploadedfile;

    private String denominacion;
    private List<Presentacion> presentacionList;
    private List<RespositorioDocumentos> documento;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto2;
    private boolean verFolio;
    private Integer folio;
    private boolean verBoton;

    JasperPrint jasperPrint = new JasperPrint();
    private boolean verObservaciones;

    public ControlCalidadBean() {
        remisionesList = new ArrayList();
        lotesList = new ArrayList();
        remFabricante = new Remisiones();
        fabricantes = new Fabricante();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        proveedorFabricante = new ProveedorFabricante();
    }

    @PostConstruct
    public void ControlCalidad() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        listaTipoDocs = tipoDocumentosService.obtenerByIdTarea(idTareaProc);
        idTipoDocumento = -1;
        controlCalidad();
        presentacion();
        verBoton = true;
    }

    public void desabilitarBoton() {
        if (folio != null && folio != -1) {
            verBoton = folio == 1;
        }
    }

    public void controlCalidad() {
        controlCalidadList = remisionesService.remisionByControlCalidad();
    }

    public void fabricante() {
        fabricantesList = fabricanteService.fabricantesByIdRemisionList(idRemision);
    }

    public void presentacion() {
        presentacionList = presentacionService.presentacionList();
    }

    public void remisionByRegistro() {
        if (!registroControl.equals("")) {
            Remisiones r = remisionesService.getByRegistroControl(registroControl);
            if (registroControl.contains("P") || registroControl.contains("C")) {
                canjepermuta = 0;
                remisionRegistroList = null;
                if (r != null) {
                    RemisionesDTO rdto = new RemisionesDTO();
                    rdto.setIdRemision(r.getIdRemision());
                    rdto.setRenglon(r.getIdCanjePermuta().getIdInsumoCanje().getIdInsumo());
                    rdto.setRegistroControl(registroControl);
                    rdto.setCantidad(r.getIdCanjePermuta().getCantidadInsumoCanje());
                    rdto.setUnidadPieza(r.getIdCanjePermuta().getIdInsumoCanje().getIdUnidadPieza().getDescripcion());
                    rdto.setImporteTotal(r.getIdCanjePermuta().getPrecioCanjePermuta());
                    rdto.setClave(r.getIdCanjePermuta().getIdInsumoCanje().getClave());
                    rdto.setDescripcion(r.getIdCanjePermuta().getIdInsumoCanje().getDescripcion());
                    rdto.setImporte(rdto.getImporteTotal().multiply(new BigDecimal(rdto.getCantidad())));
                    rdto.setArticulo(null);
                    rdto.setCantidadRecibidaRemision(r.getCantidadRecibida());
                    remisionRegistroList = new ArrayList();
                    remisionRegistroList.add(rdto);
                    if (r.getLoteList() != null) {
                        Lote lote = loteService.getByIdLote(r.getLoteList().get(0).getIdLotePadre());
                        fabricantesList = fabricanteService.fabricantesByIdRemisionList(lote.getIdRemision().getIdRemision());

                        lotesList = r.getLoteList();
                    }
                }
            } else {
                remisionRegistroList = remisionesService.remisionByRegistroControl(registroControl);
                if (remisionRegistroList != null) {
                    idRemision = remisionRegistroList.get(0).getIdRemision();
                    lotesList = loteService.loteByR(idRemision);
                    documento = respositorioDocumentosService.getByOrden(r.getIdDetalleOrdenSuministro().getIdRepositorioDocumentos());
                    System.out.println("do" + documento);
                    if (documento != null) {
                        buscarArchivosByIdProcesoIdTareaRemision();
                        verFolio = true;
                    }
                    fabricante();
                }
            }
            if (remisionRegistroList != null) {
                for (Lote lot : lotesList) {
                    lot.setCantidadRecibidaControlCalidad(0);
                    if (Utilidades.validacionFechaFabricacion(lot.getFechaFabricacion())) {
                        verMensaje = true;
                        mensaje.mensaje("Fecha de fabricación mayor a 12 meses", "amarillo");
                        verFolio = true;
                        verBoton = false;
                    }
                    if (!Utilidades.validacionFechasControlCalidad(lot.getFechaCaducidad())) {
                        verMensaje = true;
                        verFolio = true;
                        mensaje.mensaje("Fecha de caducidad menor a 9 meses", "amarillo");
                        verBoton = false;
                    }
                    //-------------------------------------------------
                }
                for (RemisionesDTO rD : remisionRegistroList) {
                    articulo = rD.getArticulo();
                    cantidadRecibida = rD.getCantidadRecibidaRemision();
                    idRemision = rD.getIdRemision();
                }
                mostrarInputText = true;
                mostrarOneMenu = true;
                mostrarRegistroSanitario = true;
            } else {
                verMensaje = true;
                mensaje.mensaje("No hay remisiones", "amarillo");
                mostrarInputText = false;
                mostrarOneMenu = false;
                mostrarRegistroSanitario = false;
            }
        } else {
            verMensaje = true;
            mensaje.mensaje("Ingrese registro de control");
        }
    }

    public void buscarFabricante() {
        if (numeroFabricante != null) {
            idFabricante = null;
            idFabricante = numeroFabricante;
            verAgregarFabricante = false;
            RequestContext.getCurrentInstance().update(":nuevoControl:fabricantes");
        }
        if (numeroFabricante != null) {
            fabricanteList = remisionesService.fabricanteByNumero(numeroFabricante, idFabricante);
            if (fabricanteList != null) {
                for (Fabricante f : fabricanteList) {
                    registroSanitario = f.getRegistroSanitario();
                }
            } else {
                verMensaje = true;
                mensaje.mensaje("El fabricante no existe");
                idFabricante = -1;
                registroSanitario = null;
                if (idFabricante == -1) {
                    verAgregarFabricante = true;
                    provedoresAll();
                }
            }
        }
    }

    public void buscarFabricanteById() {
        if (idFabricante != -1) {
            if (idFabricante != null) {
                numeroFabricante = null;
                numeroFabricante = idFabricante;
                verAgregarFabricante = false;
            }
            if (numeroFabricante == null && idFabricante == null) {
//                fabricantesList = remisionesService.fabricanteList();
                idFabricante = null;
                RequestContext.getCurrentInstance().update(":nuevoControl:fabricantes");
            }
        } else {
            numeroFabricante = null;
        }
        if (numeroFabricante != null || idFabricante != null) {
            fabricanteList = remisionesService.fabricanteByNumero(numeroFabricante, idFabricante);
            if (fabricanteList != null) {
                for (Fabricante f : fabricanteList) {
                    registroSanitario = f.getRegistroSanitario();
                }
            }
        }
    }

    public void mostrarDefecto() {
        if (seleccionarDictamen != null || seleccionarDictamen != -1) {
            if (seleccionarDictamen == 1) {
                verDetalleDefecto = false;
                verObservaciones = false;
                descripcionDefecto = "";
                seleccionarDictamen = 1;
            } else if (seleccionarDictamen == 2) {
                verDetalleDefecto = true;
                seleccionarDictamen = 2;
            } else {
                verDetalleDefecto = true;
                seleccionarDictamen = 3;
            }
        }
    }

    public void mostrarObservaciones() {
        if (idPresentacion != null || idPresentacion != -1) {
            if (idPresentacion == 15) {
                verObservaciones = true;
            } else {
                verObservaciones = false;
            }
        }

    }

    public void agregarFabricante() {
        if (idFabricante == -2) {
            if (fabricantes.getNombre().equals("") || fabricantes.getRegistroSanitario().equals("")) {
                mensaje.mensaje("Debes ingresar el nombre y el registro sanitario", "amarillo");
            } else {
                fabricantes.setIdFabricante(null);
                fabricantes.setActivo(1);
                fabricantes.setFechaAlta(new Date());
                remFabricante = remisionesService.getByRegistroControl(registroControl);
                //fabricante.setUsuarioAlta(usuarios.getNombre());
                if (fabricanteService.guardarFabricante(fabricantes)) {
                    fabricantes = fabricanteService.fabricanteByNombre(fabricantes.getNombre());
                    proveedorFabricante.setActivo(1);
                    proveedorFabricante.setFechaAlta(new Date());
                    proveedorFabricante.setIdProveedor(remFabricante.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor());
                    proveedorFabricante.setIdFabricante(fabricantes);
                    proveedorFabricante.setIdProcedimientoRcb(remFabricante.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdProcedimientoRcb());
                    proveedorFabricanteService.guardarProveedorFabricante(proveedorFabricante);
                    fabricante();
                    bitacoraTareaEstatus.setDescripcion("Guardado Fabricante");
                    bitacoraTareaEstatus.setFecha(new Date());
                    bitacoraTareaEstatus.setIdEstatus(0);
                    bitacoraTareaEstatus.setIdModulos(3);
                    bitacoraTareaEstatus.setIdTareaId(0);
                    bitacoraService.guardarEnBitacora(bitacoraTareaEstatus);
                    mensaje.mensaje(mensaje.datos_guardados, "verde");
                    bnuevo = false;
                } else {
                    mensaje.mensaje(mensaje.error_guardar, "rojo");
                }
            }
        } else {
            fabricantes = fabricanteService.fabricanteByIdFacbricante(fabricantes.getIdFabricante());
        }
    }

    public void guardarControlCalidad() {
        if (validaNull() != false) {
            Remisiones remisiones = new Remisiones();
            Fabricante f = new Fabricante();
            for (RemisionesDTO r : remisionRegistroList) {
                remisiones.setIdRemision(r.getIdRemision());
                idRemision = r.getIdRemision();
                remiList = remisionesService.remisionesAll(r.getRegistroControl());
            }
            for (Remisiones remisionesList : remiList) {
                remisiones = remisionesList;
            }
            f.setIdFabricante(idFabricante);
            remisiones.setIdFabricante(f);
            remisiones.setCantidadRecibida(cantidadRecibida);
            remisiones.setInspeccion(inspeccion);
            remisiones.setDenominacion(denominacion);
            remisiones.setIdPresentacion(new Presentacion(idPresentacion));
            if (seleccionarDictamen == 1) {
                remisiones.setIdEstatus(new Estatus(83));
            } else if (seleccionarDictamen == 2) {
                remisiones.setNivelCalidadAceptable(BigDecimal.valueOf(defecto));
                remisiones.setDescripcionDefecto(descripcionDefecto);
                remisiones.setIdEstatus(new Estatus(84));
            } else {
                remisiones.setDescripcionDefecto(descripcionDefecto);
                remisiones.setIdEstatus(new Estatus(183));
            }
            boolean bandera1 = remisionesService.actualizarRemision(remisiones);
            if (bandera1 != false) {
                for (Lote lote : lotesList) {
                    if (seleccionarDictamen == 2) {
                        lote.setActivo(0);
                    }
                    loteService.guardarLote(lote);
                }
                verMensaje = true;
                mensaje.mensaje(mensaje.datos_guardados, "verde");
                verCargaDocumentos = true;
                buscarArchivosByIdProcesoIdTarea(idRemision, idTareaProc);
                bitacora.setFecha(new Date());
                bitacora.setIdEstatus(83);
                bitacora.setDescripcion(bitacoraUtil.detalleGuardaroBitacora("remision (ctrl calidad)"));
                bitacora.setIdUsuarios(usuarios.getIdUsuario());
                bitacora.setIdTareaId(idTareaProc);
                bitacoraService.guardarEnBitacora(bitacora);
                verBotonDescargarRemision = true;
                registroControl = null;
                inspeccion = null;
                cantidadRecibida = null;
                cantidadMuestra = null;
                descripcionDefecto = null;
                verDetalleDefecto = false;
                registroSanitario = null;
                articulo = null;
            } else {
                verMensaje = true;
                mensaje.mensaje("Error al guardar la remisión");
            }
        }
    }

    public boolean validaNull() {
        if (seleccionarDictamen == 3) {
            if (registroControl.contains("P") || registroControl.contains("C")) {
                verMensaje = true;
                mensaje.mensaje("No se puede realizar operacion en un canje o permuta.");
                return false;
            }
        }
        if (registroControl == null || registroControl.equals("")) {
            verMensaje = true;
            mensaje.mensaje("Ingrese registro de control");
            return false;
        }

        if (idFabricante == null || idFabricante == -1) {
            verMensaje = true;
            mensaje.mensaje("Selecciona un fabricante");
            return false;
        }
        if (cantidadRecibida == null) {
            verMensaje = true;
            mensaje.mensaje("Ingrese cantidad recibida");
            return false;
        }

        if (inspeccion == null || inspeccion.equals("")) {
            verMensaje = true;
            mensaje.mensaje("Ingrese nivel de inspección");
            return false;
        }
        if (seleccionarDictamen == null || seleccionarDictamen == -1) {

            verMensaje = true;
            mensaje.mensaje("Seleccione un dictamen");
            return false;

        }
        if (seleccionarDictamen == 2) {
            if (idPresentacion == null
                    || idPresentacion == 15) {
                if (descripcionDefecto == null || descripcionDefecto.equals("")) {

                    verMensaje = true;
                    mensaje.mensaje("Ingrese la descripción del defecto");
                    return false;
                }
            }
        }

        for (Lote lote : lotesList) {
            if (lote.getCantidadRecibidaControlCalidad() != null) {
                if (lote.getCantidadRecibidaControlCalidad() > cantidadRecibida) {
                    verMensaje = true;
                    mensaje.mensaje("La cantidad de muestra a verificar no puede ser mayor a la cantidad recibida");
                    return false;
                }
            } else {
                mensaje.mensaje("Debes capturar la cantidad de muestra a verificar por lote.");
                return false;
            }

        }

        return true;
    }

    public void provedoresAll() {
        proveedoresList = remisionesService.proveedoresAll();
    }

    public void remisionByRegistroControl() {
        if (fechaFin != null && fechaInicio != null) {
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
                mensaje.mensaje("La fecha fin debe ser posterior a la fecha de inicio", "amarillo");

            } else {
                this.fechaInicialText = format.format(this.fechaInicial);
                this.fechaFinalText = format.format(this.fechaFinal);
            }

            remisionesRegistro = remisionesService.remisionByRegsitroControlCalidad(registroControl, fechaInicialText, fechaFinalText);
            if (remisionesRegistro == null) {
                verMensaje2 = true;
                mensaje.mensaje("No hay remisiones", "amarillo");
            }
        } else {
            this.fechaInicialText = "";
            this.fechaFinalText = "";
            remisionesRegistro = remisionesService.remisionByRegsitroControlCalidad(registroControl, fechaInicialText, fechaFinalText);
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
                mensaje.mensaje(mensaje.datos_guardados, "verde");
                if (archivosUtilidades.guardaArchivo(uploadedfile, nombreArchivo)) {
                    mensaje.mensaje(mensaje.archivo_bien, "verde");
                    bitacora.setFecha(new Date());
                    bitacora.setIdEstatus(84);
                    bitacora.setDescripcion(bitacoraUtil.detalleGuardaroBitacora("archivo " + nombreArchivo));
                    bitacora.setIdUsuarios(usuarios.getIdUsuario());
                    bitacora.setIdTareaId(idTareaProc);
                    bitacoraService.guardarEnBitacora(bitacora);
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
                bitacora.setIdEstatus(84);
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
        remList = remisionesService.remisionByIdRemision(idRemision);
        for (Remisiones r : remList) {
            RemisionDTO rem = new RemisionDTO();
            rem.setArticulo(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getIdFundamentoLegal().getDescripcion());
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
                rem.setCantidadPorLote(cantPorLote);
                rem.setLote(lote);
            }
            rem.setNep(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNep());
            rem.setSigla(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla());
            rem.setCantidad(r.getCantidadRecibida());
            rem.setClave(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave());
            rem.setDescripcion(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getDescripcion());
            for (ProcedimientoRcbDestinos prd : r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getProcedimientoRcbDestinosList()) {
                rem.setDestino(prd.getIdDestino().getNombre());
                rem.setDireccionDestino(prd.getIdDestino().getDomicilio());
            }
            if (r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getProcedimientoRcbDestinosList() != null
                    || r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getProcedimientoRcbDestinosList().isEmpty()) {
                rem.setDestino("");
                rem.setDireccionDestino("");
            }
            rem.setDireccion(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getDireccion());
            rem.setFechaFin(r.getIdDetalleOrdenSuministro().getFechaEntregaFinal());
            rem.setFechaInicio(r.getIdDetalleOrdenSuministro().getFechaEntregaInicial());
            rem.setFechaRemision(r.getFechaRemision());
            rem.setGrupo(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdGrupo().getGrupo()); //se modifico por cambio de rcb
            BigDecimal importe = util.agregarDecimales(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario().multiply(new BigDecimal(r.getCantidadRecibida())));
            rem.setImporte(formateador.format(importe.doubleValue()));
            if (importe.toString().length() < 5) {
                rem.setImporte(importe.toString());
            } else {
                rem.setImporte(formateador.format(importe.doubleValue()));
            }
            rem.setNombreProveedor(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getNombreProveedor());
            rem.setNumeroContrato(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getNumeroContrato());
            rem.setNumeroOrden(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getNumeroOrden());
            rem.setPartida(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getIdPartidaPresupuestal().getPartidaPresupuestal());
            BigDecimal precioUnitario = util.agregarDecimales(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario());
            if (precioUnitario.toString().length() < 5) {
                rem.setPrecioUnitario(precioUnitario.toString());
            } else {
                rem.setPrecioUnitario(formateador.format(precioUnitario.doubleValue()));
            }
            rem.setRegistroControl(r.getRegistroControl());
            rem.setRenglon(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdInsumo());
            rem.setRfc(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getRfc());
            rem.setUnidad(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdUnidadPieza().getDescripcion());
            String numero = String.valueOf(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario().multiply(new BigDecimal(r.getCantidadRecibida())));
            rem.setNumeroLetra(numLetra.convertir(numero, true));
            rem.setUnidadMedica(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getIdUnidadesMedicas().getNombre());
            rem.setNumeroPorveedor(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getNumero());
            remisionesList.add(rem);
        }
        imprimirRemision();
    }

    public void imprimirRemision() throws JRException, IOException {
        System.out.println("entro imprimir");
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(remisionesList);
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

    public void buscarArchivosByIdProcesoIdTareaRemision() {
        listaRepoDocsDto2 = new ArrayList();
        System.out.println("");
        if (documento != null) {
            for (RespositorioDocumentos rd : documento) {
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
                listaRepoDocsDto2.add(repositorioDocumentosDTO);
            }
        }
    }

    public Integer getCanjepermuta() {
        return canjepermuta;
    }

    public void setCanjepermuta(Integer canjepermuta) {
        this.canjepermuta = canjepermuta;
    }

    public List<RemisionesDTO> getControlCalidadList() {
        return controlCalidadList;
    }

    public void setControlCalidadList(List<RemisionesDTO> controlCalidadList) {
        this.controlCalidadList = controlCalidadList;
    }

    public String getRegistroControl() {
        return registroControl;
    }

    public void setRegistroControl(String registroControl) {
        this.registroControl = registroControl;
    }

    public List<RemisionesDTO> getRemisionRegistroList() {
        return remisionRegistroList;
    }

    public void setRemisionRegistroList(List<RemisionesDTO> remisionRegistroList) {
        this.remisionRegistroList = remisionRegistroList;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public Integer getNumeroFabricante() {
        return numeroFabricante;
    }

    public void setNumeroFabricante(Integer numeroFabricante) {
        this.numeroFabricante = numeroFabricante;
    }

    public String getNombreFabricante() {
        return nombreFabricante;
    }

    public void setNombreFabricante(String nombreFabricante) {
        this.nombreFabricante = nombreFabricante;
    }

    public List<Fabricante> getFabricantesList() {
        return fabricantesList;
    }

    public void setFabricantesList(List<Fabricante> fabricantesList) {
        this.fabricantesList = fabricantesList;
    }

    public Integer getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(Integer idFabricante) {
        this.idFabricante = idFabricante;
    }

    public boolean isMostrarOneMenu() {
        return mostrarOneMenu;
    }

    public void setMostrarOneMenu(boolean mostrarOneMenu) {
        this.mostrarOneMenu = mostrarOneMenu;
    }

    public boolean isMostrarInputText() {
        return mostrarInputText;
    }

    public void setMostrarInputText(boolean mostrarInputText) {
        this.mostrarInputText = mostrarInputText;
    }

    public String getRegistroSanitario() {
        return registroSanitario;
    }

    public void setRegistroSanitario(String registroSanitario) {
        this.registroSanitario = registroSanitario;
    }

    public boolean isMostrarRegistroSanitario() {
        return mostrarRegistroSanitario;
    }

    public void setMostrarRegistroSanitario(boolean mostrarRegistroSanitario) {
        this.mostrarRegistroSanitario = mostrarRegistroSanitario;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
    }

    public String getInspeccion() {
        return inspeccion;
    }

    public void setInspeccion(String inspeccion) {
        this.inspeccion = inspeccion;
    }

    public Integer getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(Integer cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public Integer getCantidadMuestra() {
        return cantidadMuestra;
    }

    public void setCantidadMuestra(Integer cantidadMuestra) {
        this.cantidadMuestra = cantidadMuestra;
    }

    public Integer getSeleccionarDictamen() {
        return seleccionarDictamen;
    }

    public void setSeleccionarDictamen(Integer seleccionarDictamen) {
        this.seleccionarDictamen = seleccionarDictamen;
    }

    public boolean isVerDetalleDefecto() {
        return verDetalleDefecto;
    }

    public void setVerDetalleDefecto(boolean verDetalleDefecto) {
        this.verDetalleDefecto = verDetalleDefecto;
    }

    public String getDescripcionDefecto() {
        return descripcionDefecto;
    }

    public void setDescripcionDefecto(String descripcionDefecto) {
        this.descripcionDefecto = descripcionDefecto;
    }

    public double getDefecto() {
        return defecto;
    }

    public void setDefecto(double defecto) {
        this.defecto = defecto;
    }

    public List<RemisionesDTO> getRemisionesByControlList() {
        return remisionesByControlList;
    }

    public void setRemisionesByControlList(List<RemisionesDTO> remisionesByControlList) {
        this.remisionesByControlList = remisionesByControlList;
    }

    public boolean isVerAgregarFabricante() {
        return verAgregarFabricante;
    }

    public void setVerAgregarFabricante(boolean verAgregarFabricante) {
        this.verAgregarFabricante = verAgregarFabricante;
    }

    public String getNombreF() {
        return nombreF;
    }

    public void setNombreF(String nombreF) {
        this.nombreF = nombreF;
    }

    public String getRegistroS() {
        return registroS;
    }

    public void setRegistroS(String registroS) {
        this.registroS = registroS;
    }

    public List<Proveedores> getProveedoresList() {
        return proveedoresList;
    }

    public void setProveedoresList(List<Proveedores> proveedoresList) {
        this.proveedoresList = proveedoresList;
    }

    public Integer getSelecionarProveedor() {
        return selecionarProveedor;
    }

    public void setSelecionarProveedor(Integer selecionarProveedor) {
        this.selecionarProveedor = selecionarProveedor;
    }

    public boolean isVerMensajeFabricanteExiste() {
        return verMensajeFabricanteExiste;
    }

    public void setVerMensajeFabricanteExiste(boolean verMensajeFabricanteExiste) {
        this.verMensajeFabricanteExiste = verMensajeFabricanteExiste;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

    public List<TipoDocumentos> getListaTipoDocs() {
        return listaTipoDocs;
    }

    public void setListaTipoDocs(List<TipoDocumentos> listaTipoDocs) {
        this.listaTipoDocs = listaTipoDocs;
    }

    public BitacoraTareaEstatus getBitacora() {
        return bitacora;
    }

    public void setBitacora(BitacoraTareaEstatus bitacora) {
        this.bitacora = bitacora;
    }

    public boolean isVerBotonDescargarRemision() {
        return verBotonDescargarRemision;
    }

    public void setVerBotonDescargarRemision(boolean verBotonDescargarRemision) {
        this.verBotonDescargarRemision = verBotonDescargarRemision;
    }

    public List<Lote> getLotesList() {
        return lotesList;
    }

    public void setLotesList(List<Lote> lotesList) {
        this.lotesList = lotesList;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public List<Presentacion> getPresentacionList() {
        return presentacionList;
    }

    public void setPresentacionList(List<Presentacion> presentacionList) {
        this.presentacionList = presentacionList;
    }

    public Integer getIdPresentacion() {
        return idPresentacion;
    }

    public void setIdPresentacion(Integer idPresentacion) {
        this.idPresentacion = idPresentacion;
    }

    public boolean isBnuevo() {
        return bnuevo;
    }

    public void setBnuevo(boolean bnuevo) {
        this.bnuevo = bnuevo;
    }

    public Fabricante getFabricantes() {
        return fabricantes;
    }

    public void setFabricantes(Fabricante fabricantes) {
        this.fabricantes = fabricantes;
    }

    public List<RepositorioDocumentosDTO> getListaRepoDocsDto2() {
        return listaRepoDocsDto2;
    }

    public void setListaRepoDocsDto2(List<RepositorioDocumentosDTO> listaRepoDocsDto2) {
        this.listaRepoDocsDto2 = listaRepoDocsDto2;
    }

    public boolean isVerFolio() {
        return verFolio;
    }

    public void setVerFolio(boolean verFolio) {
        this.verFolio = verFolio;
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public boolean isVerBoton() {
        return verBoton;
    }

    public void setVerBoton(boolean verBoton) {
        this.verBoton = verBoton;
    }

    public boolean isVerObservaciones() {
        return verObservaciones;
    }

    public void setVerObservaciones(boolean verObservaciones) {
        this.verObservaciones = verObservaciones;
    }

}
