package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.RemisionDTO;
import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.CanjeService;
import com.issste.sicabis.ejb.ln.FalloProcedimientoRcbService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.LoteService;
import com.issste.sicabis.ejb.ln.ProveedorService;
import com.issste.sicabis.ejb.ln.RemisionesService;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.ln.TipoDocumentosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.CanjePermuta;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Lote;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcbDestinos;
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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import static java.lang.String.format;
import static java.lang.String.format;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
import org.primefaces.model.UploadedFile;

/**
 *
 * @author fabianvr
 */
@ManagedBean(name = "canjeBean")
@ViewScoped
public class CanjeBean implements Serializable {

    @EJB
    private FalloProcedimientoRcbService falloProcedimientoRcbService;

    @EJB
    private TipoDocumentosService tipoDocumentosService;

    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;

    @EJB
    private LoteService loteService;

    @EJB
    private InsumosService insumosService;

    @EJB
    private CanjeService canjeService;

    @EJB
    private ProveedorService proveedorService;

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

    private RepositorioDocumentosDTO repositorioDocumentosDTO;
    private RepositorioDocumentosDTO repositorioDocumentosDTOAux;
    private RespositorioDocumentos respositorioDocumentos;
    private CanjePermuta canjePermuta;
    private Usuarios usuarios;
    private String clave;
    private String claveEntregado;
    private String lote;
    private Integer cantidad;
    private Integer cantidadCanjePermuta;
    private Date fechaInsumo;
    private Date fechaCanjePermuta;
    private Date fechaCaducidad;
    private boolean verMensaje;
    private BigDecimal precioPromedio;
    private BigDecimal precioRecibido;
    private String tipoCanje;
    private String loteEntregar;
    private boolean verCargaDocumentos;
    private Integer operacion;
    private UploadedFile uploadedfile;
    private Integer idTipoDocumento;
    private Integer idRemision;
    private List<RespositorioDocumentos> listaRepoDocs;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private List<TipoDocumentos> listaTipoDocs;
    private List<CanjePermuta> canjeList;
    private List<CanjePermuta> listaCanjePermuta;
    private List<Proveedores> proveedoresList;
    private List<Remisiones> remisionesList;
    private Proveedores provSelect;
    private Remisiones remision;
    private CanjePermuta cp;
    private JasperPrint jasperPrint;
    private BigDecimal precioU;
    private Date fechaC;
    private Date fechaF;

    private Insumos insumoEntregado;
    private Insumos insumoRecibido;
    private Lote loteAux;

    private final Integer idTareaProc = 14;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();
    private List<Remisiones> remList;
    private ArrayList remisionList;
    private ArrayList remisionListCero;
    private DefaultStreamedContent file;
    private boolean verBotonRemision;
    private Integer anio;

    public CanjeBean() {
        proveedoresList = new ArrayList<>();
        provSelect = new Proveedores();
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        listaTipoDocs = tipoDocumentosService.obtenerByIdTarea(idTareaProc);
        proveedoresList = proveedorService.proveedoresAll();
        listaCanjePermuta = new ArrayList();
        canjeList = new ArrayList();
        precioU = BigDecimal.ZERO;
        fechaC = new Date();
        fechaF = new Date();
        anio = util.getYear();
        //canje();
    }

    public void operacion() {
        if (operacion != -1) {
            if (operacion == 1) {
                tipoCanje = "Canje";
            } else if (operacion == 2) {
                tipoCanje = "Permuta";
            }
        }
    }

    public boolean buscarInsumoByClave(String clave, int opcion) {
        Insumos insumo = insumosService.obtieneByClave(clave);
        if (insumo != null) {
            if (opcion == 1) {
                insumoEntregado = insumo;
            } else if (opcion == 2) {
                insumoRecibido = insumo;
            }
            return true;
        }
        return false;
    }

    public boolean validadVacio() {
        if (provSelect == null) {
            verMensaje = true;
            mensaje.mensaje("Debe seleccionar un proveedor", "amarillo");
            return false;
        }
        if (operacion == -1) {
            verMensaje = true;
            mensaje.mensaje("Favor de escojer la operación", "amarillo");
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
        if (claveEntregado.equals("")) {
            verMensaje = true;
            mensaje.mensaje("Ingresa clave para continuar", "amarillo");
            return false;
        }
        if (cantidadCanjePermuta == null) {
            verMensaje = true;
            mensaje.mensaje("Ingresa cantidad recibida para continuar", "amarillo");
            return false;
        }
        if (loteEntregar.equals("")) {
            verMensaje = true;
            mensaje.mensaje("Ingresa lote para continuar", "amarillo");
            return false;
        }
//        if (operacion.equals("2")) {
//            if (precioRecibido == null) {
//                verMensaje = true;
//                mensaje.mensaje("Ingresa Precio Para Continuar", "amarillo");
//                return false;
//            }
//        } else {
//            precioRecibido = precioPromedio;
//        }
        return true;
    }

    public boolean valida() {
        if (operacion == 1) {
            if (!clave.equals(claveEntregado)) {
                verMensaje = true;
                mensaje.mensaje("Las claves no coinciden.", "amarillo");
                return false;

            }
            if (!lote.equals(loteEntregar)) {
                verMensaje = true;
                mensaje.mensaje("Los lotes deben conincidir.", "amarillo");
                return false;
            }
        } else if (operacion == 2) {
            if (clave.equals(claveEntregado)) {
                verMensaje = true;
                mensaje.mensaje("Las claves deben de ser diferentes.", "amarillo");
                return false;
            }
        }
        if (listaCanjePermuta != null || !listaCanjePermuta.isEmpty()) {
            if (listaCanjePermuta.size() >= 25) {
                verMensaje = true;
                mensaje.mensaje("No se pueden agregar mas de 25 claves", "amarillo");
                return false;
            }
        }
        return true;
    }

    public void agregar() {
        CanjePermuta cp = null;
        Lote lote = null;
        loteAux = new Lote();
        if (validadVacio() != false) {
            if (valida() != false) {
                List<Lote> listaLote = loteService.lote(this.lote);
                if (listaLote != null) {
                    if (buscarInsumoByClave(this.clave, 1)) {
                        if (buscarInsumoByClave(this.claveEntregado, 2)) {
                            listaLote = loteService.getloteByLoteInsumo(this.lote, this.claveEntregado);
                            if (listaLote != null && !listaLote.isEmpty()) {
                                loteAux = listaLote.get(0);
                                cp = new CanjePermuta();
                                cp.setActivo(1);
                                cp.setProveedor(provSelect);
                                cp.setTipoCanje(tipoCanje);
                                cp.setFechaAlta(new Date());
                                cp.setUsuarioAlta(usuarios.getUsuario());
                                cp.setFolio(loteAux.getCodigoBarrasLote());

                                cp.setCantidadInsumoCanje(cantidadCanjePermuta);
                                cp.setFechaCaducidadCanje(fechaC);
                                cp.setLoteEntregado(loteAux.getLote());
                                cp.setIdInsumoCanje(insumoRecibido);
                                cp.setPrecioCanjePermuta(precioU);
                                cp.setFechaFabricacionRecibido(fechaF);

                                cp.setCantidadInsumoOriginal(cantidad);
                                cp.setIdInsumo(insumoEntregado);
                                cp.setLote(this.loteEntregar);
                                listaLote = loteService.getloteByLoteInsumo(this.loteEntregar, this.clave);
                                if (listaLote != null && !listaLote.isEmpty()) {
                                    cp.setFechaCaducidad(listaLote.get(0).getFechaCaducidad());
                                } else {
                                    verMensaje = true;
                                    mensaje.mensaje("La clave y el lote entregado no estan asociados", "amarillo");
                                }
                                if (operacion == 1) {
                                    cp.setPrecio(cp.getPrecioCanjePermuta());
                                } else {
                                    cp.setPrecio(falloProcedimientoRcbService.obtenerUltimoPrecio(clave));
                                }
                                listaCanjePermuta.add(cp);
                                this.limpia();
                            } else {
                                verMensaje = true;
                                mensaje.mensaje("La clave y el lote recibido no estan asociados", "amarillo");
                            }
                        } else {
                            verMensaje = true;
                            mensaje.mensaje("La clave a recibir no existe", "amarillo");
                        }
                    } else {
                        verMensaje = true;
                        mensaje.mensaje("La clave a entregar no existe", "amarillo");
                    }
                } else {
                    verMensaje = true;
                    mensaje.mensaje("El lote no existe", "amarillo");
                }
            }
        }
    }

    public void limpia() {
        provSelect = null;
        operacion = -1;

        clave = "";
        cantidad = null;
        loteEntregar = "";

        claveEntregado = "";
        cantidadCanjePermuta = null;
        lote = "";
        precioU = BigDecimal.ZERO;
        fechaC = new Date();
        fechaF = new Date();
    }

    public void quitarClaves(CanjePermuta canjePermuta) {
        List<CanjePermuta> listaCPAux = new ArrayList();
        //importeTotal = BigDecimal.ZERO;
        for (CanjePermuta cp : listaCanjePermuta) {
            if (cp != canjePermuta) {
                listaCPAux.add(cp);
                //importeTotal = pr.getImporte().add(importeTotal);
            }
        }
        listaCanjePermuta.clear();
        listaCanjePermuta = listaCPAux;

    }

    public void confirmaGuardar() {
        if (listaCanjePermuta != null && !listaCanjePermuta.isEmpty()) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlg1').show();");
        } else {
            verMensaje = true;
            mensaje.mensaje("No has ingresado ningún canje o pemuta a ingresar", "verde");
        }
    }

    public void guardar() {
        if (clave.equals(claveEntregado)) {
            for (CanjePermuta cp : listaCanjePermuta) {
                guardarCanjePermuta(cp);
            }

        }
    }

    public void guardarCanjePermuta(CanjePermuta cp) {
        Integer guardar = canjeService.guardarCanje(cp);
        if (guardar != null) {
            verMensaje = true;
            mensaje.mensaje(mensaje.datos_guardados, "verde");
            guardaBitacora(cp.getIdCanjePermuta(), "canje permuta " + cp.getIdCanjePermuta());
            generarNuevasRemisiones(cp);
            verCargaDocumentos = true;
            buscarArchivosByIdProcesoIdTarea(idRemision, idTareaProc);
            listaCanjePermuta = new ArrayList();
        } else {
            listaCanjePermuta = new ArrayList();
            mensaje.mensaje("No se almaceno el canje.", "amarillo");
        }
        //canje();
    }

    public void generarNuevasRemisiones(CanjePermuta cp) {
        Remisiones remision = null;
        try {
            remision = new Remisiones();
            //Integer regControl = remisionesService.numeroRegistroControl();
            Integer regControl = remisionesService.getLastRegistroControlByYear(anio, "IS NOT NULL");
            if (cp.getTipoCanje().equals("Canje")) {
                remision.setRegistroControl(regControl + "C");
            } else {
                remision.setRegistroControl(regControl + "P");
            }
            remision.setActivo(1);
            remision.setCantidadRecibida(cp.getCantidadInsumoCanje());
            remision.setFechaRemision(new Date());
            remision.setIdEstatus(new Estatus(82));
            remision.setIdCanjePermuta(cp);
            remision.setUsuarioAlta(usuarios.getUsuario());
            remision.setFechaAlta(new Date());

            List<Lote> listaLoteAux = new ArrayList();
            Lote lote = loteAux;
            lote.setIdLotePadre(loteAux.getIdLote());
            lote.setIdLote(null);
            lote.setIdRemision(remision);
            lote.setCantidadLote(remision.getCantidadRecibida());
            lote.setUsuarioAlta(usuarios.getUsuario());
            lote.setFechaAlta(new Date());
            listaLoteAux.add(lote);
            remision.setLoteList(listaLoteAux);
            Integer idRem = remisionesService.guardaRemision(remision);
            idRemision = idRem;
            if (idRemision != null) {
                verBotonRemision = true;
            }
            guardaBitacora(remision.getIdRemision(), "Guarda remisión " + remision.getRegistroControl());
        } catch (Exception ex) {
            Logger.getLogger(CanjeBean.class.getName()).log(Level.SEVERE, null, ex);
            mensaje.mensaje("Ocurrió un error al guardar la remisión", "amarillo");
        }
    }

    public void guardaBitacora(Integer id, String descripcion) {
        bitacora.setFecha(new Date());
        bitacora.setIdEstatus(id);
        bitacora.setDescripcion(bitacoraUtil.detalleGuardaroBitacora(descripcion));
        bitacora.setIdUsuarios(usuarios.getIdUsuario());
        bitacora.setIdTareaId(idTareaProc);
        bitacoraService.guardarEnBitacora(bitacora);
    }

    public void imprimirRemision() throws JRException, IOException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(remisionesList);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/remisiones.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public boolean validarLoteRemision(Remisiones remision) {
        boolean condicion;
        for (Lote lote : remision.getLoteList()) {
            System.out.println("Lote:" + lote.getLote());
        }
        return true;
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
                    bandera = true;
                    bitacora.setFecha(new Date());
                    bitacora.setIdEstatus(respositorioDocumentos.getIdRespositorioDocumento());
                    bitacora.setDescripcion(bitacoraUtil.detalleGuardaroBitacora("archivo " + respositorioDocumentos.getIdRespositorioDocumento()));
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
                    Logger.getLogger(DetalleProcedimientoBean.class
                            .getName()).log(Level.SEVERE, null, ex);
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
                bitacora.setIdEstatus(rdd.getIdRespositorioDocumento());
                bitacora.setDescripcion(bitacoraUtil.detalleEliminarBitacora("archivo " + rdd.getIdRespositorioDocumento()));
                bitacora.setIdUsuarios(usuarios.getIdUsuario());
                bitacora.setIdTareaId(idTareaProc);
                bitacoraService.guardarEnBitacora(bitacora);
            }
        }
        listaRepoDocsDto = listaRepoDocsDtoAux;
    }

    public void canje() {
        canjeList = canjeService.canjePermuta();
        if (canjeList != null) {
            for (CanjePermuta cp : canjeList) {
                Remisiones r = remisionesService.getByIdCanjePermuta(cp.getIdCanjePermuta());
                cp.setFolio(r.getRegistroControl());
            }
        }
    }

    public String verDettaleRemision() throws IOException {
        return "detalleCanje.xhtml?faces-redirect=true&idCanje=" + this.canjePermuta.getIdCanjePermuta();
    }

    public void descargarRemision() throws JRException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        DecimalFormat formateador = new DecimalFormat("###,###.##");
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
                rem.setArticulo(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getIdFundamentoLegal().getDescripcion());
                rem.setClave(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave());
                rem.setDescripcion(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getDescripcion());
                for (ProcedimientoRcbDestinos prd : r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getProcedimientoRcbDestinosList()) {
                    rem.setDestino("CENADI");
                    rem.setDireccionDestino("AV. CENADI");
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
                rem.setArticulo("");
                rem.setClave(r.getIdCanjePermuta().getIdInsumoCanje().getClave());
                rem.setDescripcion(r.getIdCanjePermuta().getIdInsumoCanje().getDescripcion());
                rem.setDestino("CENADI");
                rem.setDireccionDestino("CENTRO NACIONAL DE DISTRIBUCIÓN, CARRETERA AL LAGO DE GUADALUPE KM. 25.5 SAN PEDRO BARRIENTOS TLANEPANTLA DE BAZ ESTADO DE MÉXICO. C.P. 54010");
                rem.setFechaInicio(null);
                rem.setFechaFin(null);
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

                remCero.setArticulo("");
                remCero.setClave(r.getIdCanjePermuta().getIdInsumoCanje().getClave());
                remCero.setDescripcion(r.getIdCanjePermuta().getIdInsumoCanje().getDescripcion());
                remCero.setDestino("CENADI");
                remCero.setDireccionDestino("CENTRO NACIONAL DE DISTRIBUCIÓN, CARRETERA AL LAGO DE GUADALUPE KM. 25.5 SAN PEDRO BARRIENTOS TLANEPANTLA DE BAZ ESTADO DE MÉXICO. C.P. 54010");
                remCero.setFechaInicio(null);
                remCero.setFechaFin(null);
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
                BigDecimal b = new BigDecimal("0.00");
                remCero.setPrecioUnitario("0.00");
                remCero.setImporte("0.00");
                remCero.setNumeroLetra("SIN COSTO POR TRATARSE DE CANJE FÍSICO");
                remCero.setRutaLogo(logoPath);
                remisionListCero.add(remCero);
                imprimirCanjes();
            }
        }
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
    }

    public String getClaveEntregado() {
        return claveEntregado;
    }

    public void setClaveEntregado(String claveEntregado) {
        this.claveEntregado = claveEntregado;
    }

    public Integer getCantidadCanjePermuta() {
        return cantidadCanjePermuta;
    }

    public void setCantidadCanjePermuta(Integer cantidadCanjePermuta) {
        this.cantidadCanjePermuta = cantidadCanjePermuta;
    }

    public BigDecimal getPrecioRecibido() {
        return precioRecibido;
    }

    public void setPrecioRecibido(BigDecimal precioRecibido) {
        this.precioRecibido = precioRecibido;
    }

    public String getTipoCanje() {
        return tipoCanje;
    }

    public void setTipoCanje(String tipoCanje) {
        this.tipoCanje = tipoCanje;
    }

    public String getLoteEntregar() {
        return loteEntregar;
    }

    public void setLoteEntregar(String loteEntregar) {
        this.loteEntregar = loteEntregar;
    }

    public Date getFechaInsumo() {
        return fechaInsumo;
    }

    public void setFechaInsumo(Date fechaInsumo) {
        this.fechaInsumo = fechaInsumo;
    }

    public Date getFechaCanjePermuta() {
        return fechaCanjePermuta;
    }

    public void setFechaCanjePermuta(Date fechaCanjePermuta) {
        this.fechaCanjePermuta = fechaCanjePermuta;
    }

    public BigDecimal getPrecioPromedio() {
        return precioPromedio;
    }

    public void setPrecioPromedio(BigDecimal precioPromedio) {
        this.precioPromedio = precioPromedio;
    }

    public Integer getOperacion() {
        return operacion;
    }

    public void setOperacion(Integer operacion) {
        this.operacion = operacion;
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

    public CanjePermuta getCanjePermuta() {
        return canjePermuta;
    }

    public void setCanjePermuta(CanjePermuta canjePermuta) {
        this.canjePermuta = canjePermuta;
    }

    public List<CanjePermuta> getCanjeList() {
        return canjeList;
    }

    public void setCanjeList(List<CanjePermuta> canjeList) {
        this.canjeList = canjeList;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public List<Proveedores> getProveedoresList() {
        return proveedoresList;
    }

    public void setProveedoresList(List<Proveedores> proveedoresList) {
        this.proveedoresList = proveedoresList;
    }

    public Proveedores getProvSelect() {
        return provSelect;
    }

    public void setProvSelect(Proveedores provSelect) {
        this.provSelect = provSelect;
    }

    public Integer getIdRemision() {
        return idRemision;
    }

    public void setIdRemision(Integer idRemision) {
        this.idRemision = idRemision;
    }

    public List<CanjePermuta> getListaCanjePermuta() {
        return listaCanjePermuta;
    }

    public void setListaCanjePermuta(List<CanjePermuta> listaCanjePermuta) {
        this.listaCanjePermuta = listaCanjePermuta;
    }

    public BigDecimal getPrecioU() {
        return precioU;
    }

    public void setPrecioU(BigDecimal precioU) {
        this.precioU = precioU;
    }

    public Date getFechaC() {
        return fechaC;
    }

    public void setFechaC(Date fechaC) {
        this.fechaC = fechaC;
    }

    public Date getFechaF() {
        return fechaF;
    }

    public void setFechaF(Date fechaF) {
        this.fechaF = fechaF;
    }

    public DefaultStreamedContent getFile() {
        return file;
    }

    public void setFile(DefaultStreamedContent file) {
        this.file = file;
    }

    public boolean isVerBotonRemision() {
        return verBotonRemision;
    }

    public void setVerBotonRemision(boolean verBotonRemision) {
        this.verBotonRemision = verBotonRemision;
    }

}
