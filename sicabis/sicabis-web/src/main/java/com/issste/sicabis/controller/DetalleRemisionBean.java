package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.RemisionDTO;
import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.LoteService;
import com.issste.sicabis.ejb.ln.RemisionesService;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.ln.TipoDocumentosService;
import com.issste.sicabis.ejb.modelo.CanjePermuta;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Lote;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcbDestinos;
import com.issste.sicabis.ejb.modelo.Remisiones;
import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.NumeroLetra;
import com.issste.sicabis.utils.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author fabianvr
 */
public class DetalleRemisionBean {

    @EJB
    private EstatusService estatusService;

    @EJB
    private LoteService loteService;

    @EJB
    private RemisionesService remisionesService;

    @EJB
    private TipoDocumentosService tipoDocumentosService;

    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;

    private Remisiones remisiones;
    private List<Remisiones> remisionList;
    private List<Lote> loteList = new ArrayList<Lote>();
    private String idRemision;
    private String registroControl;
    private String proveedor;
    private String lugarEntrega;
    private String partidaPresupuestal;
    private String grupo;
    private String estatus;
    private String rfc;
    private String fechaRemisionText;
    private String fechaEntregaInicialText;
    private String fechaEnetregaFinalText;
    private String fabricante;
    private String registroSanitario;
    private Integer remision;
    private Integer numeroFabricante;
    private Date fechaRemision;
    private Date fechaEntregaInicial;
    private Date fechaEnetregaFinal;
    private Integer idEstatus;
    private boolean verRecepcionDocumental;
    private boolean verControlCalidadAndBienes;
    private List<RespositorioDocumentos> listaRepoDocs;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private RepositorioDocumentosDTO repositorioDocumentosDTO;
    private RepositorioDocumentosDTO repositorioDocumentosDTOAux;
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();
    private RespositorioDocumentos respositorioDocumentos;
    private String denominacion;
    private String presentacion;

    private List<RemisionDTO> remisionesList;
    private List<RemisionDTO> remisionesListCero;
    private NumeroLetra numLetra = new NumeroLetra();
    private String unidadPieza;
    private boolean verUnidad;
    private boolean verCanjes;
    private List<CanjePermuta> listaCanjes;
    private StreamedContent file;
    private boolean bcanjes;
    JasperPrint jasperPrint = new JasperPrint();

    public DetalleRemisionBean() {
        idRemision = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idRemision");
    }

    @PostConstruct
    public void init() {
        listaCanjes = new ArrayList();
        remisionDetalle();
        remisionesList = new ArrayList();
        remisionesListCero = new ArrayList();
    }

    public void remisionDetalle() {
        remision = Integer.parseInt(String.valueOf(idRemision));
        remisionList = remisionesService.remisionByIdRemision(remision);
        for (Remisiones r : remisionList) {
            registroControl = r.getRegistroControl();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            fechaRemision = r.getFechaRemision();
            fechaRemisionText = format.format(this.fechaRemision);
            if (r.getUnidadPiezaConvenio() != null && !r.getUnidadPiezaConvenio().equals("")) {
                unidadPieza = r.getUnidadPiezaConvenio();
                verUnidad = true;
            }
            if (r.getRegistroControl().contains("P") || r.getRegistroControl().contains("C")) {
                proveedor = r.getIdCanjePermuta().getProveedor().getNombreProveedor() + " " + r.getIdCanjePermuta().getProveedor().getDireccion();
                rfc = r.getIdCanjePermuta().getProveedor().getRfc();
                partidaPresupuestal = r.getIdCanjePermuta().getIdInsumo().getPartidaPresupuestal();
                grupo = r.getIdCanjePermuta().getIdInsumo().getIdGrupo().getGrupo();
                verCanjes = true;
                listaCanjes.add(r.getIdCanjePermuta());
                bcanjes = true;
            } else {
                proveedor = r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getNombreProveedor() + "  " + r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getDireccion();
                fechaEntregaInicial = r.getIdDetalleOrdenSuministro().getFechaEntregaInicial();
                fechaEntregaInicialText = format.format(fechaEntregaInicial);
                fechaEnetregaFinal = r.getIdDetalleOrdenSuministro().getFechaEntregaFinal();
                fechaEnetregaFinalText = format.format(fechaEnetregaFinal);

                for (ProcedimientoRcbDestinos prd : r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getProcedimientoRcbDestinosList()) {
                    lugarEntrega = prd.getIdDestino().getNombre() + "  " + prd.getIdDestino().getDomicilio();
                }
                rfc = r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getRfc();
                partidaPresupuestal = r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getPartidaPresupuestal();
                grupo = r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdGrupo().getGrupo();
            }
            idEstatus = r.getIdEstatus().getIdEstatus();
            Estatus e = estatusService.getRemisionEstatus(idEstatus);
            estatus = e.getNombre();
            if (idEstatus > 82) {
                denominacion = r.getDenominacion();
                if (r.getIdPresentacion() != null) {
                    presentacion = r.getIdPresentacion().getPresentacion();
                }
            }
            System.out.println("idEstatus" + idEstatus);
            loteList = loteService.loteByR(remision);
            if (idEstatus != 82) {
                numeroFabricante = r.getIdFabricante().getIdFabricante();
                fabricante = r.getIdFabricante().getNombre();
                registroSanitario = r.getIdFabricante().getRegistroSanitario();
            }
        }
        Estatus e = estatusService.getRemisionEstatus(idEstatus);
        estatus = e.getNombre();
        oculta();
        buscarArchivosByIdProcesoIdTarea(remision, 8);
    }

    public void oculta() {
        if (idEstatus == 82) {
            verRecepcionDocumental = true;
            verControlCalidadAndBienes = false;
        } else if (idEstatus == 83 || idEstatus == 84 || idEstatus == 85) {
            verRecepcionDocumental = true;
            verControlCalidadAndBienes = true;
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
        System.out.println("repositorioDocumentosDTO---->" + repositorioDocumentosDTOAux.getIdRespositorioDocumento());
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

    public void descargarRemision() throws JRException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        remisionesList = new ArrayList();
        remisionesListCero = new ArrayList();
        String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ISSSTE.png");
        for (Remisiones r : remisionList) {
            RemisionDTO rem = new RemisionDTO();
            RemisionDTO remCero = new RemisionDTO();
            rem.setFolioRemision(r.getFolioRemision());
            remCero.setFolioRemision(r.getFolioRemision());
            String numero = "";
            String cad = "";
            String fab = "";
            String lote = "";
            String cantPorLote = "";
            for (Lote l : r.getLoteList()) {
                cad = cad + format.format(l.getFechaCaducidad()) + "\n";
                fab = fab + format.format(l.getFechaFabricacion()) + "\n";
                lote = lote + l.getLote() + "\n";
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
            
            if (idEstatus == 85) {
                rem.setFolioRemision(r.getFolioRemision());
                rem.setTextoFolio("FOLIO");
            }
            if (r.getIdCanjePermuta() == null) {
                rem.setNep(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNep());
                rem.setSigla(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla());
                rem.setExpedienteCompra(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNumero());
                remCero.setNep(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNep());
                remCero.setSigla(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla());
            } else {
                rem.setNep("");
                rem.setSigla(r.getIdCanjePermuta().getIdInsumo().getIdTipoInsumos().getSigla());
                remCero.setNep("");
                remCero.setSigla(r.getIdCanjePermuta().getIdInsumo().getIdTipoInsumos().getSigla());
            }
            rem.setCantidad(r.getCantidadRecibida());
            rem.setFechaRemision(r.getFechaRemision());
            rem.setRegistroControl(r.getRegistroControl());
            rem.setAnio(String.valueOf(util.getYear(r.getFechaRemision())));
            rem.setSecuencia("DM");
            rem.setRutaLogo(logoPath);
            remCero.setCantidad(r.getCantidadRecibida());
            remCero.setFechaRemision(r.getFechaRemision());
            remCero.setRegistroControl(r.getRegistroControl());
            remCero.setAnio(String.valueOf(util.getYear(r.getFechaRemision())));
            remCero.setSecuencia("DM");
            remCero.setFolioRemision(r.getFolioRemision());
            remCero.setRutaLogo(logoPath);

            if (r.getIdCanjePermuta() == null) {
                rem.setFolioRemision(r.getFolioRemision());
                rem.setSigla(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla());
                rem.setNep(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNep());
                rem.setArticulo(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getIdFundamentoLegal().getDescripcion());
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
                rem.setGrupo(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdGrupo().getGrupo()); //modifco grupo
                BigDecimal importe = util.agregarDecimales(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario().multiply(new BigDecimal(r.getCantidadRecibida())));
                if (importe.toString().length() <= 6) {
                    rem.setImporte(importe.toString());
                } else {
                    rem.setImporte(formateador.format(importe.doubleValue()));
                }
                rem.setImporteU(importe);
                rem.setNombreProveedor(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getNombreProveedor());
                rem.setNumeroContrato(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getNumeroContrato());
                rem.setNumeroOrden(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getNumeroOrden());
                rem.setPartida(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getIdPartidaPresupuestal().getPartidaPresupuestal());
                BigDecimal precioUnitario = util.agregarDecimales(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario());
                if (precioUnitario.toString().length() <= 6) {
                    rem.setPrecioUnitario(precioUnitario.toString());
                } else {
                    rem.setPrecioUnitario(formateador.format(precioUnitario.doubleValue()));
                }
                rem.setPrecioU(precioUnitario);
                rem.setRenglon(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdInsumo());
                rem.setRfc(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getRfc());
                rem.setUnidad(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdUnidadPieza().getDescripcion());
                numero = String.valueOf(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario().multiply(new BigDecimal(r.getCantidadRecibida())));
                rem.setNumeroLetra(numLetra.convertir(numero, true));
                rem.setUnidadMedica(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getIdUnidadesMedicas().getNombre());
                rem.setNumeroPorveedor(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getNumero());
                rem.setRutaLogo(logoPath);
                rem.setDestino("CENADI");
                rem.setDireccionDestino("CENTRO NACIONAL DE DISTRIBUCIÓN, CARRETERA AL LAGO DE GUADALUPE KM. 25.5 SAN PEDRO BARRIENTOS TLANEPANTLA DE BAZ ESTADO DE MÉXICO. C.P. 54010");
                rem.setUnidadMedica("DIRECCIÓN MÉDICA");
                remisionesList.add(rem);
                imprimirRemision();
            } else {
                rem.setFolioRemision(r.getFolioRemision());
                rem.setArticulo("");
                rem.setFechaFin(r.getFechaRemision());
                rem.setFechaInicio(r.getFechaRemision());
                rem.setFechaInicio(r.getFechaRemision());
                rem.setFechaFin(r.getFechaRemision());
                rem.setClave(r.getIdCanjePermuta().getIdInsumoCanje().getClave());
                rem.setDescripcion(r.getIdCanjePermuta().getIdInsumoCanje().getDescripcion());
                rem.setDestino("CENADI");
                rem.setDireccionDestino("CENTRO NACIONAL DE DISTRIBUCIÓN, CARRETERA AL LAGO DE GUADALUPE KM. 25.5 SAN PEDRO BARRIENTOS TLANEPANTLA DE BAZ ESTADO DE MÉXICO. C.P. 54010");
                rem.setFechaInicio(null);
                rem.setFechaFin(null);
                rem.setNumeroPorveedor(r.getIdCanjePermuta().getProveedor().getNumero());
                rem.setNombreProveedor(r.getIdCanjePermuta().getProveedor().getNombreProveedor());
                rem.setDireccion(r.getIdCanjePermuta().getProveedor().getDireccion());
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
                rem.setCaducidad(formatDate.format(r.getIdCanjePermuta().getFechaCaducidad()));
                rem.setFabricacion(formatDate.format(r.getIdCanjePermuta().getFechaFabricacionRecibido()));
                rem.setRfc(r.getIdCanjePermuta().getProveedor().getRfc());
                rem.setGrupo(r.getIdCanjePermuta().getIdInsumoCanje().getIdGrupo().getGrupo());
                BigDecimal importe = util.agregarDecimales(r.getIdCanjePermuta().getPrecioCanjePermuta().multiply(new BigDecimal(r.getCantidadRecibida())));
                if (importe.toString().length() <= 6) {
                    rem.setImporte(importe.toString());
                } else {
                    rem.setImporte(formateador.format(importe.doubleValue()));
                }
                rem.setNumeroContrato("");
                rem.setNumeroOrden("");
                rem.setPartida("");
                BigDecimal precioUnitario = util.agregarDecimales(r.getIdCanjePermuta().getPrecioCanjePermuta());
                if (precioUnitario.toString().length() <= 6) {
                    rem.setPrecioUnitario(precioUnitario.toString());
                } else {
                    rem.setPrecioUnitario(formateador.format(precioUnitario.doubleValue()));
                }
                rem.setRenglon(r.getIdCanjePermuta().getIdInsumoCanje().getIdInsumo());
                rem.setUnidad(r.getIdCanjePermuta().getIdInsumoCanje().getIdUnidadPieza().getDescripcion());
                numero = String.valueOf(rem.getImporte());
                rem.setNumeroLetra(numLetra.convertir(numero, true));
                rem.setUnidadMedica("DIRECCIÓN MÉDICA");
                rem.setRutaLogo(logoPath);
                remisionesList.add(rem);

                remCero.setFechaInicio(r.getFechaRemision());
                remCero.setFechaFin(r.getFechaRemision());
                remCero.setArticulo("");
                remCero.setCaducidad(formatDate.format(r.getIdCanjePermuta().getFechaCaducidad()));
                remCero.setFabricacion(formatDate.format(r.getIdCanjePermuta().getFechaFabricacionRecibido()));
                remCero.setFolioRemision(r.getFolioRemision());
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
                remCero.setPrecioUnitario("0.00");
                remCero.setImporte("0.00");
                remCero.setNumeroLetra("SIN COSTO POR TRATARSE DE CANJE FÍSICO");
                remCero.setRutaLogo(logoPath);
                remisionesListCero.add(remCero);
                imprimirCanjes();
            }
        }
    }

    public void imprimirRemision() throws JRException, IOException {
        System.out.println("entro imprimir");
        String file = "";
        file = "remisiones.jasper";
//        if (idEstatus == 85) {
//            file = "remisionesFolio.jasper";
//        } else {
//            file = "remisiones.jasper";
//        }
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(remisionesList);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/" + file + "");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        System.out.println("lleno jasper");
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=remision.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        System.out.println("exporto imprimir");
        FacesContext.getCurrentInstance().responseComplete();
        System.out.println("salio imprimir");
    }

    JasperPrint jasperPrintCero;

    public void imprimirCanjes() throws JRException, IOException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(remisionesList);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/canjes.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        beanCollectionDataSource = new JRBeanCollectionDataSource(remisionesListCero);
        reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/canjesCero.jasper");
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

    public List<Remisiones> getRemisionList() {
        return remisionList;
    }

    public void setRemisionList(List<Remisiones> remisionList) {
        this.remisionList = remisionList;
    }

    public String getRegistroControl() {
        return registroControl;
    }

    public void setRegistroControl(String registroControl) {
        this.registroControl = registroControl;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getLugarEntrega() {
        return lugarEntrega;
    }

    public void setLugarEntrega(String lugarEntrega) {
        this.lugarEntrega = lugarEntrega;
    }

    public Date getFechaRemision() {
        return fechaRemision;
    }

    public void setFechaRemision(Date fechaRemision) {
        this.fechaRemision = fechaRemision;
    }

    public Date getFechaEntregaInicial() {
        return fechaEntregaInicial;
    }

    public void setFechaEntregaInicial(Date fechaEntregaInicial) {
        this.fechaEntregaInicial = fechaEntregaInicial;
    }

    public Date getFechaEnetregaFinal() {
        return fechaEnetregaFinal;
    }

    public void setFechaEnetregaFinal(Date fechaEnetregaFinal) {
        this.fechaEnetregaFinal = fechaEnetregaFinal;
    }

    public String getPartidaPresupuestal() {
        return partidaPresupuestal;
    }

    public void setPartidaPresupuestal(String partidaPresupuestal) {
        this.partidaPresupuestal = partidaPresupuestal;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getFechaRemisionText() {
        return fechaRemisionText;
    }

    public void setFechaRemisionText(String fechaRemisionText) {
        this.fechaRemisionText = fechaRemisionText;
    }

    public String getFechaEntregaInicialText() {
        return fechaEntregaInicialText;
    }

    public void setFechaEntregaInicialText(String fechaEntregaInicialText) {
        this.fechaEntregaInicialText = fechaEntregaInicialText;
    }

    public String getFechaEnetregaFinalText() {
        return fechaEnetregaFinalText;
    }

    public void setFechaEnetregaFinalText(String fechaEnetregaFinalText) {
        this.fechaEnetregaFinalText = fechaEnetregaFinalText;
    }

    public List<Lote> getLoteList() {
        return loteList;
    }

    public void setLoteList(List<Lote> loteList) {
        this.loteList = loteList;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getRegistroSanitario() {
        return registroSanitario;
    }

    public void setRegistroSanitario(String registroSanitario) {
        this.registroSanitario = registroSanitario;
    }

    public Integer getNumeroFabricante() {
        return numeroFabricante;
    }

    public void setNumeroFabricante(Integer numeroFabricante) {
        this.numeroFabricante = numeroFabricante;
    }

    public boolean isVerRecepcionDocumental() {
        return verRecepcionDocumental;
    }

    public void setVerRecepcionDocumental(boolean verRecepcionDocumental) {
        this.verRecepcionDocumental = verRecepcionDocumental;
    }

    public boolean isVerControlCalidadAndBienes() {
        return verControlCalidadAndBienes;
    }

    public void setVerControlCalidadAndBienes(boolean verControlCalidadAndBienes) {
        this.verControlCalidadAndBienes = verControlCalidadAndBienes;
    }

    public String getIdRemision() {
        return idRemision;
    }

    public void setIdRemision(String idRemision) {
        this.idRemision = idRemision;
    }

    public Integer getRemision() {
        return remision;
    }

    public void setRemision(Integer remision) {
        this.remision = remision;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
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

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getUnidadPieza() {
        return unidadPieza;
    }

    public void setUnidadPieza(String unidadPieza) {
        this.unidadPieza = unidadPieza;
    }

    public boolean isVerUnidad() {
        return verUnidad;
    }

    public void setVerUnidad(boolean verUnidad) {
        this.verUnidad = verUnidad;
    }

    public boolean isVerCanjes() {
        return verCanjes;
    }

    public void setVerCanjes(boolean verCanjes) {
        this.verCanjes = verCanjes;
    }

    public List<CanjePermuta> getListaCanjes() {
        return listaCanjes;
    }

    public void setListaCanjes(List<CanjePermuta> listaCanjes) {
        this.listaCanjes = listaCanjes;
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

}
