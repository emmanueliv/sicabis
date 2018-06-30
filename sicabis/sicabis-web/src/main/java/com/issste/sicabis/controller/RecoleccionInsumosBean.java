package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.RecoleccionDTO;
import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.LoteService;
import com.issste.sicabis.ejb.ln.RecoleccionService;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.ln.TipoDocumentosService;
import com.issste.sicabis.ejb.ln.UnidadMedicaService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.DetalleRecoleccion;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Lote;
import com.issste.sicabis.ejb.modelo.Recoleccion;
import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author fabianvr
 */
@ManagedBean(name = "recoleccionInsumosBean")
@ViewScoped
public class RecoleccionInsumosBean {

    @EJB
    private TipoDocumentosService tipoDocumentosService;
    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;
    @EJB
    private UnidadMedicaService unidadMedicaService;
    @EJB
    private LoteService loteService;
    @EJB
    private InsumosService insumosService;
    @EJB
    private RecoleccionService recoleccionService;

    /*
     Implementacion de bitacora
     */
    @EJB
    BitacoraTareaSerice bitacoraService;
    BitacoraTareaEstatus bitacora = new BitacoraTareaEstatus();
    BitacoraUtil bitacoraUtil = new BitacoraUtil();

    private Mensajes mensaje = new Mensajes();
    private RecoleccionDTO recoleccionDTO;
    private RepositorioDocumentosDTO repositorioDocumentosDTO;
    private RepositorioDocumentosDTO repositorioDocumentosDTOAux;
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();
    private RespositorioDocumentos respositorioDocumentos;
    private Recoleccion recoleccion;
    private Usuarios usuarios;
    private List<UnidadesMedicas> unidadMedicaList;
    private List<DetalleRecoleccion> detalleRecoleccionList;
    private Integer numeroUnidad;
    private String nombreUnidad;
    private String clave;
    private String claveInsumo;
    private Integer cantidad;
    private String lote;
    private String oficioRecoleccion;
    private BigDecimal precioPromedio;
    private Date fechaCaducidad;
    private boolean verMensaje;
    private boolean verCargaDocumentos;
    DetalleRecoleccion dr = new DetalleRecoleccion();
    private Integer idInsumo;
    private Insumos insumoEncontrado;
    private UploadedFile uploadedfile;
    private Integer idTipoDocumento;
    private Integer idRemision;
    private List<RespositorioDocumentos> listaRepoDocs;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private List<TipoDocumentos> listaTipoDocs;
    private final Integer idTareaProc = 13;
    private List<Recoleccion> recoList;
    private List<RecoleccionDTO> busquedaList;
    private String descripcion;
    private Date fechaAlta;
    private String folioRecoleccion;
    private boolean verMensaje2;
    private Integer idRecoleccion;
    private Integer numeroU;
    private BigDecimal precioUnitario;
    private Date fechaC;

    public RecoleccionInsumosBean() {
        detalleRecoleccionList = new ArrayList();
        busquedaList = new ArrayList();
        bitacora = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        unidadMedica();
        fechaCaducidad = new Date();
        precioPromedio = BigDecimal.ZERO;
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        listaTipoDocs = tipoDocumentosService.obtenerByIdTarea(idTareaProc);
    }

    public void unidadMedica() {
        unidadMedicaList = unidadMedicaService.unidadMedica();
    }

    public void guardar() {
        if (validadVacio() != false) {
            buscarInsumoByClave();

            if (validaClaveDuplicada() != false) {

                if (idInsumo != null) {
                    DetalleRecoleccion dr;
                    dr = new DetalleRecoleccion();
                    dr.setCantidad(cantidad);
                    dr.setLote(lote);
                    dr.setFechaCaducidad(fechaC);
                    dr.setIdInsumo(insumoEncontrado);
                    dr.setPrecioPromedio(precioUnitario);
                    detalleRecoleccionList.add(dr);
                    clave = "";
                    cantidad = null;
                    lote = "";
                }

            } else {
                mensaje.mensaje("Ya existe una recolección con la misma clave y lote", "amarillo");
                verMensaje = true;
            }
        }
    }

    public void guardarRecoleccion() {
        if (oficioRecoleccion.equals("")) {
            verMensaje = true;
            mensaje.mensaje("Ingresa oficio de recolección para continuar", "amarillo");
        } else {
            Recoleccion r = new Recoleccion();
            r.setActivo(1);
            r.setIdUnidadesMedicas(new UnidadesMedicas(numeroUnidad));
            r.setOficioRecoleccion(oficioRecoleccion);
            for (DetalleRecoleccion dR : detalleRecoleccionList) {
                dR.setIdRecoleccion(r);
                dR.setActivo(1);
            }
            r.setDetalleRecoleccionList(detalleRecoleccionList);
            String folio = String.valueOf(recoleccionService.folioRecoleccion());
            r.setFolioRecoleccion(folio);
            Date f = new Date();
            r.setFechaAlta(f);
            Integer guardar = recoleccionService.guardarRecoleccion(r);
            if (guardar != null) {
                detalleRecoleccionList = new ArrayList();
                clave = "";
                cantidad = null;
                lote = "";
                oficioRecoleccion = "";
                fechaCaducidad = new Date();
                precioPromedio = BigDecimal.ZERO;
                bitacora.setFecha(new Date());
                bitacora.setIdEstatus(0);
                bitacora.setDescripcion(bitacoraUtil.detalleGuardaroBitacora("recoleccion " + r.getIdRecoleccion()));
                bitacora.setIdUsuarios(usuarios.getIdUsuario());
                bitacora.setIdTareaId(idTareaProc);
                bitacoraService.guardarEnBitacora(bitacora);
                verMensaje = true;
                mensaje.mensaje(mensaje.datos_guardados + "con el Folio" + folio, "verde");
                idRemision = guardar;
                verCargaDocumentos = true;
            } else {
                mensaje.mensaje("Ocurrió un error al guardar la información", "rojo");
            }
        }
    }

    public boolean validaClaveDuplicada() {
        if (detalleRecoleccionList != null) {
            for (DetalleRecoleccion dos : detalleRecoleccionList) {
                if (idInsumo.equals(dos.getIdInsumo().getIdInsumo()) && lote.equals(dos.getLote())) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }

    public boolean validadVacio() {
        if (numeroUnidad == null) {
            verMensaje = true;
            mensaje.mensaje("Ingrese el número de la unidad", "amarillo");
            return false;
        }

        if (clave.equals("")) {
            verMensaje = true;
            mensaje.mensaje("Ingresa clave para continuar", "amarillo");
            return false;
        }
        if (cantidad == null) {
            verMensaje = true;
            mensaje.mensaje("Ingresa cantidad para continuar", "amarillo");
            return false;
        }

        if (lote.equals("")) {
            verMensaje = true;
            mensaje.mensaje("Ingresa lote para continuar", "amarillo");
            return false;
        }
        return true;
    }

    public void buscarInsumoByClave() {
        Insumos insumo = insumosService.obtieneByClave(clave);
        if (insumo != null) {
            insumoEncontrado = insumo;
            idInsumo = insumoEncontrado.getIdInsumo();
            claveInsumo = clave;
        } else {
            verMensaje = true;
            mensaje.mensaje("Clave de insumo no encontrada", "amarillo");
        }
    }

    public boolean validadFechaCaducidad() {
        List<Lote> l = loteService.lote(lote);
        List<Insumos> i = insumosService.insumos(clave);
        if (l != null && i != null) {
            fechaCaducidad = loteService.loteByClave(clave, lote);
            if (fechaCaducidad != null) {
                Date fechaActual = new Date();
                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                String fechaInicioText = df.format(fechaActual);
                try {
                    fechaActual = df.parse(fechaInicioText);
                } catch (ParseException ex) {
                }

                String fechaFinalText = df.format(fechaCaducidad);
                try {
                    fechaCaducidad = df.parse(fechaFinalText);
                } catch (ParseException ex) {
                }
                long fechaInicialms = fechaActual.getTime();
                long fechaFinalms = fechaCaducidad.getTime();
                long diferencia = fechaFinalms - fechaInicialms;
                double dias = Math.floor(diferencia / 86400000L);
                int d = ((int) dias);
                if (d > 120) {
                    return true;
                } else {
                    verMensaje = true;
                    mensaje.mensaje("La fecha de caducidad excede los días establecidos por la normativa", "amarillo");
                    return false;
                }
            } else {
                verMensaje = true;
                mensaje.mensaje("La clave no pertence al lote", "amarillo");
                return false;
            }
        } else {
            verMensaje = true;
            if (i == null) {
                mensaje.mensaje("La clave no existe", "amarillo");
            } else if (l == null) {
                mensaje.mensaje("El lote no existe", "amarillo");
            }
            return false;
        }

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
                    bandera = true;
                    bitacora.setFecha(new Date());
                    bitacora.setIdEstatus(idRemision);
                    bitacora.setDescripcion(bitacoraUtil.detalleGuardaroBitacora("archivo remision" + idRemision));
                    bitacora.setIdUsuarios(usuarios.getIdUsuario());
                    bitacora.setIdTareaId(idTareaProc);
                    bitacoraService.guardarEnBitacora(bitacora);
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
                bitacora.setIdEstatus(idRemision);
                bitacora.setDescripcion(bitacoraUtil.detalleEliminarBitacora("archivo " + rdd.getIdRespositorioDocumento()));
                bitacora.setIdUsuarios(usuarios.getIdUsuario());
                bitacora.setIdTareaId(idTareaProc);
                bitacoraService.guardarEnBitacora(bitacora);
            }
        }
        listaRepoDocsDto = listaRepoDocsDtoAux;
    }

    public void precioUnitarioByClave() {
        precioPromedio = recoleccionService.precioPromedioByClave(clave);
    }

    public void buscarRecoleccion() {
        busquedaList = new ArrayList<>();
        recoList = recoleccionService.buscarRecoleccion(oficioRecoleccion);
        if (recoList != null) {
            for (Recoleccion r : recoList) {

                recoleccionDTO = new RecoleccionDTO();
                recoleccionDTO.setNombreUnidad(r.getIdUnidadesMedicas().getNombre());
                nombreUnidad = r.getIdUnidadesMedicas().getNombre();
                numeroUnidad = r.getIdUnidadesMedicas().getIdUnidadesMedicas();
                idRecoleccion = r.getIdRecoleccion();
                recoleccionDTO.setIdRecoleccion(idRecoleccion);
                cantidad = 0;
                for (DetalleRecoleccion i : r.getDetalleRecoleccionList()) {
                    recoleccionDTO.setClave(i.getIdInsumo().getClave());
                    clave = i.getIdInsumo().getClave();
                    recoleccionDTO.setDescripcion(i.getIdInsumo().getDescripcion());
                    descripcion = i.getIdInsumo().getDescripcion();
                    recoleccionDTO.setCaducidad(i.getFechaCaducidad());
                    fechaCaducidad = i.getFechaCaducidad();
                    recoleccionDTO.setPrecio(i.getPrecioPromedio());
                    cantidad += i.getCantidad();
                    lote = i.getLote();
                    precioPromedio = i.getPrecioPromedio();
                }
                recoleccionDTO.setCantidad(cantidad);
                recoleccionDTO.setFechaAlta(r.getFechaAlta());
                fechaAlta = r.getFechaAlta();
                oficioRecoleccion = r.getOficioRecoleccion();
                folioRecoleccion = r.getFolioRecoleccion();
                busquedaList.add(recoleccionDTO);
            }
        } else {
            verMensaje2 = true;
            mensaje.mensaje("No hay recolecciones", "amarillo");
        }
        oficioRecoleccion = "";
    }

    public void mostrarFabricante() {
        numeroUnidad = numeroU;
    }

    public String verDettaleRemision() throws IOException {
        return "detalleRecoleccion.xhtml?faces-redirect=true&idRecoleccion=" + this.recoleccionDTO.getIdRecoleccion();
    }

    public Integer getNumeroUnidad() {
        return numeroUnidad;
    }

    public void setNumeroUnidad(Integer numeroUnidad) {
        this.numeroUnidad = numeroUnidad;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String Lote) {
        this.lote = Lote;
    }

    public String getOficioRecoleccion() {
        return oficioRecoleccion;
    }

    public void setOficioRecoleccion(String oficioRecoleccion) {
        this.oficioRecoleccion = oficioRecoleccion;
    }

    public BigDecimal getPrecioPromedio() {
        return precioPromedio;
    }

    public void setPrecioPromedio(BigDecimal precioPromedio) {
        this.precioPromedio = precioPromedio;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public List<UnidadesMedicas> getUnidadMedicaList() {
        return unidadMedicaList;
    }

    public void setUnidadMedicaList(List<UnidadesMedicas> unidadMedicaList) {
        this.unidadMedicaList = unidadMedicaList;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
    }

    public boolean isVerCargaDocumentos() {
        return verCargaDocumentos;
    }

    public void setVerCargaDocumentos(boolean verCargaDocumentos) {
        this.verCargaDocumentos = verCargaDocumentos;
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

    public List<TipoDocumentos> getListaTipoDocs() {
        return listaTipoDocs;
    }

    public void setListaTipoDocs(List<TipoDocumentos> listaTipoDocs) {
        this.listaTipoDocs = listaTipoDocs;
    }

    public List<Recoleccion> getRecoList() {
        return recoList;
    }

    public void setRecoList(List<Recoleccion> recoList) {
        this.recoList = recoList;
    }

    public List<RecoleccionDTO> getBusquedaList() {
        return busquedaList;
    }

    public void setBusquedaList(List<RecoleccionDTO> busquedaList) {
        this.busquedaList = busquedaList;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFolioRecoleccion() {
        return folioRecoleccion;
    }

    public void setFolioRecoleccion(String folioRecoleccion) {
        this.folioRecoleccion = folioRecoleccion;
    }

    public boolean isVerMensaje2() {
        return verMensaje2;
    }

    public void setVerMensaje2(boolean verMensaje2) {
        this.verMensaje2 = verMensaje2;
    }

    public Recoleccion getRecoleccion() {
        return recoleccion;
    }

    public void setRecoleccion(Recoleccion recoleccion) {
        this.recoleccion = recoleccion;
    }

    public Integer getIdRecoleccion() {
        return idRecoleccion;
    }

    public void setIdRecoleccion(Integer idRecoleccion) {
        this.idRecoleccion = idRecoleccion;
    }

    public RecoleccionDTO getRecoleccionDTO() {
        return recoleccionDTO;
    }

    public void setRecoleccionDTO(RecoleccionDTO recoleccionDTO) {
        this.recoleccionDTO = recoleccionDTO;
    }

    public BitacoraTareaEstatus getBitacora() {
        return bitacora;
    }

    public void setBitacora(BitacoraTareaEstatus bitacora) {
        this.bitacora = bitacora;
    }

    public Integer getNumeroU() {
        return numeroU;
    }

    public void setNumeroU(Integer numeroU) {
        this.numeroU = numeroU;
    }

    public List<DetalleRecoleccion> getDetalleRecoleccionList() {
        return detalleRecoleccionList;
    }

    public void setDetalleRecoleccionList(List<DetalleRecoleccion> detalleRecoleccionList) {
        this.detalleRecoleccionList = detalleRecoleccionList;
    }

    public Integer getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Integer idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getClaveInsumo() {
        return claveInsumo;
    }

    public void setClaveInsumo(String claveInsumo) {
        this.claveInsumo = claveInsumo;
    }

    public Insumos getInsumoEncontrado() {
        return insumoEncontrado;
    }

    public void setInsumoEncontrado(Insumos insumoEncontrado) {
        this.insumoEncontrado = insumoEncontrado;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Date getFechaC() {
        return fechaC;
    }

    public void setFechaC(Date fechaC) {
        this.fechaC = fechaC;
    }

}
