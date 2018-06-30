package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.ContratoDTO;
import com.issste.sicabis.DTO.InsumosDTO;
import com.issste.sicabis.DTO.RemisionDTO;
import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
import com.issste.sicabis.ejb.DTO.RemisionesDTO;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ContratoService;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.FalloProcedimientoRcbService;
import com.issste.sicabis.ejb.ln.OrdenSuministroService;
import com.issste.sicabis.ejb.ln.RemisionesService;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.ln.TipoDocumentosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Clausulado;
import com.issste.sicabis.ejb.modelo.CompradorContrato;
import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Lote;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcbDestinos;
import com.issste.sicabis.ejb.modelo.Remisiones;
import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.service.silodisa.controller.ExistenciaPorClaveUmusController;
import com.issste.sicabis.ejb.service.silodisa.controller.ExistenciasPorClaveCenadiController;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.BitacoraUtil;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.NumeroLetra;
import com.issste.sicabis.utils.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;
import org.xml.sax.SAXException;

/**
 *
 * @author fabianvr
 */
public class RecepcionDocumentalBean implements Serializable {

    @EJB
    private ContratoService contratoService;
    @EJB
    private ExistenciaPorClaveUmusController existenciaPorClaveUmusController;
    @EJB
    private OrdenSuministroService ordenSuministroService;
    @EJB
    private ExistenciasPorClaveCenadiController existenciasPorClaveCenadiController1;
    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;
    @EJB
    private FalloProcedimientoRcbService falloProcedimientoRcbService;
    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;
    @EJB
    private TipoDocumentosService tipoDocumentosService;
    @EJB
    private EstatusService estatusService;
    @EJB
    private RemisionesService remisionesService;

    /*
     Implementacion de bitacora
     */
    @EJB
    BitacoraTareaSerice bitacoraService;
    BitacoraTareaEstatus bitacora = new BitacoraTareaEstatus();
    BitacoraUtil bitacoraUtil = new BitacoraUtil();

    private NumeroLetra numLetra = new NumeroLetra();
    private Estatus estatus;
    private RepositorioDocumentosDTO repositorioDocumentosDTO;
    private RepositorioDocumentosDTO repositorioDocumentosDTOAux;
    private RespositorioDocumentos respositorioDocumentos;
    private Remisiones remisiones;
    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();
    private DetalleOrdenSuministro dos;
    private Usuarios usuarios;
    private Lote loteR = new Lote();
    private List<RemisionesDTO> ordenList;
    private List<RemisionesDTO> insumosList;
    private List<Remisiones> remisionesRegistro;
    private List<Lote> lotes;
    private List<Remisiones> devolucionList;
    private List<TipoDocumentos> listaTipoDocs;
    private List<Remisiones> remisionList;
    private String seleccionarOrden;
    private String criterioBusqueda;
    private String codigoBarras;
    private String fechaInicialText;
    private String fechaFinalText;
    private String registroControl;
    private String disable;
    private String loteC;
    private Integer ordenSuministro;
    private Integer insumo;
    private Integer buscar;
    private Integer lote;
    private Integer insumos;
    private Integer suma;
    private Integer suma2;
    private Integer cantidadSuministroClave;
    private Integer remisionSuma;
    private Integer cantidadLotes;
    private Integer idRemision;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaInicial;
    private Date fechaFinal;
    private Date fechaFabricacionInsumo;
    private Date fechaCaducidadInsumo;
    private boolean verMensaje2;
    private boolean verRemisionesDevolucion;
    private boolean mostrarContrato;
    private boolean mostrarConvenio;
    private boolean mostrarSuministro;
    private boolean id = false;
    private boolean verMensaje;
    private boolean verBotonActualizar;
    private boolean verBotonGuardar = true;
    private boolean verMensajeInsumos;
    private boolean verTablaInsumos;
    private boolean verCargarDocumentos;
    private Integer idTipoDocumento;
    private UploadedFile uploadedfile;
    private final Integer idTareaProc = 8;
    private List<RespositorioDocumentos> listaRepoDocs;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private String os;
    private String descripcionCorta;
    private List<RemisionDTO> remisionesList;
    private List<Remisiones> remiList;
    private boolean verBotonDescargarRemision;
    private List<Remisiones> remisionesDevolucionList;
    private boolean ocultar;
    private Integer sumaLote;
    private Integer sumaLoteCantidadSuministro;
    private Integer sumaLoteTotal;
    private Integer anio;

    private RemisionesDTO r;
    private RemisionesDTO r2;

    DecimalFormat formateador = new DecimalFormat("###,###.##");
    JasperPrint jasperPrint = new JasperPrint();

    public RecepcionDocumentalBean() {
        lotes = new ArrayList();
        remisionesList = new ArrayList();
        bitacora = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        listaTipoDocs = tipoDocumentosService.obtenerByIdTarea(idTareaProc);
        idTipoDocumento = -1;
        ocultar = true;
        sumaLote = 0;
        sumaLoteCantidadSuministro = 0;
        sumaLoteTotal = 0;
        anio = util.getYear();
    }

    public void limpiar() {
        RequestContext.getCurrentInstance().reset("formaId");
        init();
    }

    public void webService() throws IOException, MalformedURLException, ParserConfigurationException, TransformerException, TransformerConfigurationException, SAXException {
        existenciaPorClaveUmusController.obtenerTodosExistenciaPorClaveUmus(1);
    }

    public void orden() {
        List<OrdenSuministro> orden = new ArrayList<>();
        List<Contratos> contrato = new ArrayList<>();
        boolean bandera = true;
        if (buscar == 3 && ordenSuministroService.obtenerByNumeroOrden(seleccionarOrden) != null) {
            orden = ordenSuministroService.obtenerByNumeroOrden(criterioBusqueda);
            if (!orden.isEmpty() && orden.get(0).getIdEstatus().getIdEstatus() == 74) {
                verMensaje = true;
                mensaje.mensaje("Esta orden esta cancelada.", "amarillo");
                bandera = false;

            } else if (!orden.isEmpty() && orden.get(0).getIdContrato().getIdEstatus().getIdEstatus() == 58) {
                verMensaje = true;
                mensaje.mensaje("EL contrato perteneciente a esta orden esta rescindido.", "amarillo");
                bandera = false;
            }
        } else if (buscar == 2 || buscar == 1 && contratoService.obtenerByNumeroContratoOrderById(criterioBusqueda) != null) {
            contrato = contratoService.obtenerByNumeroContratoOrderById(criterioBusqueda);
            if (!contrato.isEmpty() && contrato.get(0).getIdEstatus().getIdEstatus() == 58) {
                verMensaje = true;
                mensaje.mensaje("Este contrato esta rescindido.", "amarillo");
                bandera = false;
            }
        }
        if (buscar == 4 && bandera) {
            mostrarRemisionByRegisatroControl(criterioBusqueda);
        } else {
            if (buscar != -1 && bandera) {
                if (!criterioBusqueda.equals("")) {
                    System.out.println("entro a orden");
                    ordenList = remisionesService.getOrden(criterioBusqueda, buscar);
                    if (ordenList != null) {
                        for (RemisionesDTO r : ordenList) {
                            cantidadSuministroClave = r.getCantidad();
                        }
                    } else {
                        verMensaje = true;
                        mensaje.mensaje("La orden no existe o no se encuentra dentro del catálogo.", "amarillo");
//                        insumosList = null;
//                        lotes = null;
                    }
                } else {
                    if (buscar == 1) {
                        verMensaje = true;
                        mensaje.mensaje("Ingresar número de contrato", "amarillo");
                    }
                    if (buscar == 2) {
                        verMensaje = true;
                        mensaje.mensaje("Ingresar número de convenio", "amarillo");
                    }
                    if (buscar == 3) {
                        verMensaje = true;
                        mensaje.mensaje("Ingresar número de orden", "amarillo");
                    }
                }
            } else if (bandera) {
                verMensaje = true;
                mensaje.mensaje("Favor de escojer un criterio de búsqueda", "amarillo");
            }

        }
    }

    public void mostrarInsumos() {
        seleccionarOrden = String.valueOf(ordenSuministro);
        insumosList = remisionesService.getInsumo(seleccionarOrden);
        for (RemisionesDTO i : insumosList) {
            RemisionesDTO rem = new RemisionesDTO();
            descripcionCorta = i.getDescripcion();
            rem.setDescripcionCorta(descripcionCorta);
        }
        os = seleccionarOrden;
    }

    public void mostrarInsumos2(SelectEvent e) {
        seleccionarOrden = String.valueOf(((RemisionesDTO) e.getObject()).getOrdenSuministro());
        insumosList = remisionesService.getInsumo(seleccionarOrden);
        for (RemisionesDTO i : insumosList) {
            RemisionesDTO rem = new RemisionesDTO();
            descripcionCorta = i.getDescripcion();
            rem.setDescripcionCorta(descripcionCorta);
        }
        os = seleccionarOrden;
    }

    public void seleccionarInsumo2(SelectEvent e) {
        insumos = ((RemisionesDTO) e.getObject()).getRenglon();
        if (insumos != null) {
            devolucionList = remisionesService.getRemisionDevolucion(insumos, os);
            if (devolucionList != null) {
                RequestContext.getCurrentInstance().execute("PF('modificarEncuestaDialog').show();");
            }
        }
        if (devolucionList == null) {
            List<Remisiones> re = remisionesService.remisiones();
            if (re != null) {
                Boolean rem = remisionesService.remisionExistente(insumos, os);
                if (rem != true) {
                    Integer r = remisionesService.remisionesByDetalle(insumos, os);
                    List<DetalleOrdenSuministro> remisionInsumos = remisionesService.remisionesByOrnden(insumos, os);
                    for (DetalleOrdenSuministro dos : remisionInsumos) {
                        if (r.equals(dos.getCantidadSuministrar())) {
                            verMensaje = true;
                            mensaje.mensaje("El insumo con clave" + " " + dos.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave() + " " + "ha sido completado", "amarillo");
                            verMensajeInsumos = true;
                        }
                    }
                }
            }
        }
    }

    public void seleccionarInsumo() {
        insumos = insumo;
        if (insumos != null) {
            devolucionList = remisionesService.getRemisionDevolucion(insumos, os);
            if (devolucionList != null) {
                RequestContext.getCurrentInstance().execute("PF('modificarEncuestaDialog').show();");
            }
        }
        if (devolucionList == null) {
            List<Remisiones> re = remisionesService.remisiones();
            if (re != null) {
                Boolean rem = remisionesService.remisionExistente(insumos, os);
                if (rem != true) {
                    Integer r = remisionesService.remisionesByDetalle(insumos, os);
                    List<DetalleOrdenSuministro> remisionInsumos = remisionesService.remisionesByOrnden(insumos, os);
                    for (DetalleOrdenSuministro dos : remisionInsumos) {
                        if (r.equals(dos.getCantidadSuministrar())) {
                            verMensaje = true;
                            mensaje.mensaje("El insumo con clave" + " " + dos.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave() + " " + "ha sido completado", "amarillo");
                            verMensajeInsumos = true;
                        }
                    }
                }
            }
        }
    }

    public void mostrarDevolucion() {
        verRemisionesDevolucion = true;
        disable = "true";
        verBotonActualizar = true;
        verBotonGuardar = false;
        insumo = insumos;
    }

    public void ocultarDevolucion() {
        disable = "false";
        verRemisionesDevolucion = false;
        verBotonActualizar = false;
        verBotonGuardar = true;
        insumo = insumos;
    }

    public void mostrarRemisionByRegisatroControl(String registro) {
        devolucionList = remisionesService.remisionByRegistroControlDevolucion(registro);
        if (devolucionList != null) {
            for (Remisiones re : devolucionList) {
                lotes = re.getLoteList();
            }
            mostrarDevolucion();
            ocultar = false;
        } else {
            verMensaje = true;
            mensaje.mensaje("El registro de control ingresado no existe.", "amarillo");
        }
    }

    public void seleccionarRemisionDevolucion() {
        if (idRemision != null) {
            remisionList = remisionesService.remisionByIdRemision(idRemision);
        }
    }

    public void actualizarRemisionDevolucion() {
        Remisiones remisiones = new Remisiones();
        for (Remisiones r : devolucionList) {
            for (Lote lote1 : lotes) {
                lote1.setIdRemision(remisiones);
                lote1.setActivo(1);
                System.out.println("cantidad" + lote1.getCantidadLote());
                suma = 0;
                suma += lote1.getCantidadLote();
            }
            if (r.getCantidadRecibida() >= suma) {
                idRemision = r.getIdRemision();
                eliminarLote(r.getIdRemision());
                remisiones.setActivo(1);
                remisiones.setUnidadPiezaConvenio(r.getUnidadPiezaConvenio());
                remisiones.setCantidadRecibida(suma);
                remisiones.setDescripcionDefecto(r.getDescripcionDefecto());
                remisiones.setFechaRemision(r.getFechaRemision());
                remisiones.setIdDetalleOrdenSuministro(r.getIdDetalleOrdenSuministro());
                remisiones.setIdEstatus(new Estatus(82));
                //remisiones.setCanjePermuta(0);
                System.out.println("idRemision=======================>" + r.getIdRemision());
                remisiones.setIdRemision(r.getIdRemision());
                remisiones.setInspeccion(r.getInspeccion());
                remisiones.setLoteList(lotes);
                remisiones.setNivelCalidadAceptable(r.getNivelCalidadAceptable());
                remisiones.setRegistroControl(r.getRegistroControl());
                boolean actualizarDevolucion = remisionesService.actualizarRemision(remisiones);
                if (actualizarDevolucion == true) {
                    verMensaje = true;
                    mensaje.mensaje(mensaje.datos_actualizados, "verde");
                    verCargarDocumentos = true;
                    buscarArchivosByIdProcesoIdTarea(idRemision, idTareaProc);
                    bitacora.setFecha(new Date());
                    bitacora.setIdEstatus(82);
                    bitacora.setDescripcion(bitacoraUtil.detalleActualizarBitacora("remisiÃ³n"));
                    bitacora.setIdUsuarios(usuarios.getIdUsuario());
                    bitacora.setIdTareaId(idTareaProc);
                    bitacoraService.guardarEnBitacora(bitacora);
                    lotes.clear();
                    if (insumosList != null) {
                        insumosList.clear();
                    }
                    verBotonDescargarRemision = true;
                    codigoBarras = null;
                    cantidadLotes = null;
                }
            } else {
                verMensaje = true;
                mensaje.mensaje("La cantidad de lotes sobrepasa la cantidad recibida de la Remisión", "amarillo");
            }
        }

        seleccionarInsumo();
    }

    public void eliminarLote(Integer r) {
        Lote lote = new Lote();
        Remisiones remisiones = new Remisiones();
        List<Lote> loteList = remisionesService.loteByRemision(r);
        for (Remisiones re : devolucionList) {
            remisiones.setActivo(1);
            remisiones.setCantidadRecibida(suma);
            remisiones.setDescripcionDefecto(re.getDescripcionDefecto());
            remisiones.setFechaRemision(re.getFechaRemision());
            remisiones.setFolioRemision(re.getFolioRemision());
            remisiones.setIdDetalleOrdenSuministro(re.getIdDetalleOrdenSuministro());
            remisiones.setIdEstatus(new Estatus(82));
            //remisiones.setCanjePermuta(0);
            remisiones.setIdRemision(re.getIdRemision());
            remisiones.setInspeccion(re.getInspeccion());
            if (loteList != null && !loteList.isEmpty()) {
                for (Lote lote1 : loteList) {
                    lote1.setIdRemision(remisiones);
                    lote1.setActivo(0);
                }
            }
            remisiones.setUnidadPiezaConvenio(re.getUnidadPiezaConvenio());
            remisiones.setLoteList(loteList);
            remisiones.setNivelCalidadAceptable(re.getNivelCalidadAceptable());
            remisiones.setRegistroControl(re.getRegistroControl());
            boolean actualizarDevolucion = remisionesService.actualizarRemision(remisiones);
            if (actualizarDevolucion) {
                bitacora.setFecha(new Date());
                bitacora.setIdEstatus(82);
                bitacora.setDescripcion(bitacoraUtil.detalleEliminarBitacora("lote"));
                bitacora.setIdUsuarios(usuarios.getIdUsuario());
                bitacora.setIdTareaId(idTareaProc);
                bitacoraService.guardarEnBitacora(bitacora);
            }

        }

    }

    public void guardarRemision(String codigo, Integer lote) throws JRException, IOException {
        if (validaVacio() != false) {
            if (validadCantidadSuministro() != false) {
                if (validadSuma() != false) {
                    Remisiones remisiones = new Remisiones();
                    dos = remisionesService.getDetalle(insumos, os);
                    Integer fpr = detalleOrdenSuministroService.falloByIdDetalleOrden(dos.getIdDetalleOrdenSuministro());
                    FalloProcedimientoRcb unidad = falloProcedimientoRcbService.unbidadPiezaByIdFallo(fpr);
                    remisiones.setActivo(1);
                    //int numero = remisionesService.numeroRegistroControl();
                    int numero = remisionesService.getLastRegistroControlByYear(anio, "IS NULL");
                    String n = String.valueOf(numero);
                    remisiones.setRegistroControl(n);
                    for (Lote lote1 : lotes) {
                        lote1.setIdRemision(remisiones);
                        lote1.setActivo(1);
                        sumaLote = lote1.getCantidadLote() + sumaLote;
                    }
                    System.out.println("sumaLote" + sumaLote);
                    remisiones.setCantidadRecibida(sumaLote);
                    remisiones.setIdEstatus(new Estatus(82));
                    Date d = new Date();
                    remisiones.setFechaRemision(d);
                    remisiones.setIdDetalleOrdenSuministro(dos);
                    remisiones.setLoteList(lotes);
                    System.out.println("unidad" + unidad);
                    remisiones.setUnidadPiezaConvenio(unidad.getUnidadPiezaConvenio());
                    Integer bandera = remisionesService.guardaRemision(remisiones);
                    if (bandera != null) {
                        idRemision = bandera;
//                        descargarRemision();
                        verCargarDocumentos = true;
                        buscarArchivosByIdProcesoIdTarea(idRemision, idTareaProc);
                        bitacora.setFecha(new Date());
                        bitacora.setIdEstatus(82);
                        bitacora.setDescripcion(bitacoraUtil.detalleActualizarBitacora("remision"));
                        bitacora.setIdUsuarios(usuarios.getIdUsuario());
                        bitacora.setIdTareaId(idTareaProc);
                        bitacoraService.guardarEnBitacora(bitacora);
                        lotes.clear();
                        insumosList.clear();
                        verBotonDescargarRemision = true;
                        codigoBarras = null;
                        cantidadLotes = null;
                        verMensaje = true;
                        mensaje.mensaje(mensaje.datos_guardados, "verde");
                    } else {
                        verMensaje = true;
                        mensaje.mensaje("Error al guardar la remisión");
                    }
                }
            }
        }

    }

    public boolean validadSuma() {
        for (Lote l : lotes) {
            sumaLoteTotal = l.getCantidadLote();
        }
        Boolean re = remisionesService.remisionExistente(insumos, os);
        if (re != true) {
            Integer remiList = remisionesService.remisionesByDetalle(insumos, os);
            if (remiList != null) {
                remiList = remiList + sumaLoteTotal;
                List<DetalleOrdenSuministro> remisionInsumos = remisionesService.remisionesByOrnden(insumos, os);
                if (remisionInsumos != null) {
                    for (DetalleOrdenSuministro r : remisionInsumos) {
                        if (remiList > r.getCantidadSuministrar()) {
                            verMensaje = true;
                            mensaje.mensaje("El número de insumos sobrepasa la cantidad solicitada en la orden de suministro", "amarillo");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean validadCantidadSuministro() {
        List<DetalleOrdenSuministro> remisionInsumos = remisionesService.remisionesByOrnden(insumos, os);
        if (remisionInsumos != null) {
            for (DetalleOrdenSuministro r : remisionInsumos) {
                for (Lote l : lotes) {
                    sumaLoteCantidadSuministro += l.getCantidadLote();
                    System.out.println("cantidad" + sumaLoteCantidadSuministro);
                }
                if (sumaLoteCantidadSuministro > r.getCantidadSuministrar()) {
                    verMensaje = true;
                    mensaje.mensaje("El número de insumos sobrepasa la cantidad solicitada en la orden de suministro", "amarillo");
                    sumaLoteCantidadSuministro = 0;
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            mensaje.mensaje("La cantidad es incorrecta");
            return false;
        }
        return true;
    }

    public boolean validaVacio() {
        if (buscar == null) {
            verMensaje = true;
            mensaje.mensaje("Escoja un criterio de búsqueda");
            return false;
        }
        if (criterioBusqueda == null || criterioBusqueda.equals("")) {
            verMensaje = true;
            mensaje.mensaje("Ingrese número de contrato");
            return false;
        }
        if (insumos == null) {
            verMensaje = true;
            mensaje.mensaje("Escoja orden de suministro y posteriormente una clave");
            return false;
        }
        if (lotes == null || lotes.isEmpty()) {
            verMensaje = true;
            mensaje.mensaje("Agrege código de barras y cantidad de lotes");
            return false;
        }
        return true;

    }

    public void deleteAction(Lote lote) {
        if (lote != null) {
            List<Lote> loteAux = new ArrayList();
            for (Lote l : lotes) {
                if (l != lote) {
                    loteAux.add(l);
                }
            }
            lotes.clear();
            lotes = loteAux;
        } else {
            verMensaje = true;
            mensaje.mensaje("Tienes que insertar código de barras y cantidad de lotes", "amarillo");
        }
    }

    public void remisionByRegistro() {
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
                mensaje.mensaje("La fecha fin debe ser posterior a la fecha de inicio", "amarillo");
            } else {
                this.fechaInicialText = format.format(this.fechaInicial);
                this.fechaFinalText = format.format(this.fechaFinal);
            }
            remisionesRegistro = remisionesService.remisionByRegsitro(registroControl, fechaInicialText, fechaFinalText);
            if (remisionesRegistro == null) {
                verMensaje2 = true;
                mensaje.mensaje("No hay remisiones", "amarillo");
            } else {
                for (Remisiones r : remisionesRegistro) {
                }
            }
        } else {
            this.fechaInicialText = "";
            this.fechaFinalText = "";
            remisionesRegistro = remisionesService.remisionByRegsitro(registroControl, fechaInicialText, fechaFinalText);
            if (remisionesRegistro == null) {
                verMensaje2 = true;
                mensaje.mensaje("No hay remisiones", "amarillo");
            } else {
                for (Remisiones r : remisionesRegistro) {
                    System.out.println("REGISTROOO" + r.getRegistroControl());
                }
            }
        }
    }

    public void addLote() {
        String fechaFabricacionText = "";
        String fechaCaducidadText = "";
        int year = 0;
        int month = 0;
        int dayOfMonth = 0;
        String msj = "";
        boolean band = true;
        if (!codigoBarras.equals("") && cantidadLotes != null) {
            int pos;
            codigoBarras = codigoBarras.trim();
            pos = codigoBarras.lastIndexOf(" ");
            if (pos != 0) {
                loteC = codigoBarras.substring(0, pos);
            } else {
                codigoBarras = "";
            }
            if (pos != 0) {
                System.out.println("leng" + codigoBarras.length());
                codigoBarras = codigoBarras.substring(pos, codigoBarras.length());
            } else {
                codigoBarras = "";
            }
            if (!codigoBarras.equals("") && codigoBarras.trim().length() > 15) {
                //if (codigoBarras.length() == 21) {
                String fechaFabricacion = codigoBarras.substring(0, codigoBarras.length());
                String fA = fechaFabricacion.trim();
                String fechaCaducidad = codigoBarras.substring(9, codigoBarras.length());

                String anioFechaFabricacion = fA.substring(0, 4);
                String mesFabricacion = fA.substring(4, 6);
                String diaFabricacion = fA.substring(6, 8);
                year = Integer.parseInt(anioFechaFabricacion);
                month = Integer.parseInt(mesFabricacion);
                dayOfMonth = Integer.parseInt(diaFabricacion);
                Calendar calendar = Calendar.getInstance();
                calendar.setLenient(false);
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month - 1);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                Date date = null;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    date = calendar.getTime();
                    fechaFabricacionText = sdf.format(date);
                } catch (IllegalArgumentException e) {
                    band = false;
                    msj = "La fecha de fabricación es invalida";
                }

                String anioCaducidad = fechaCaducidad.substring(0, 4);
                String mesCaducidad = fechaCaducidad.substring(4, 6);
                String diaCaducidad = fechaCaducidad.substring(6, 8);

                year = Integer.parseInt(anioFechaFabricacion);
                month = Integer.parseInt(mesFabricacion);
                dayOfMonth = Integer.parseInt(diaFabricacion);
                calendar = Calendar.getInstance();
                calendar.setLenient(false);
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month - 1);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                try {
                    date = calendar.getTime();
                    fechaCaducidadText = sdf.format(date);
                } catch (IllegalArgumentException e) {
                    band = false;
                    msj = "La fecha de caducidad es invalida";
                }
                if (band) {
                    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
                    fechaFabricacionInsumo = null;
                    fechaCaducidadInsumo = null;
                    try {
                        fechaFabricacionInsumo = formatoDelTexto.parse(fechaFabricacionText);
                        fechaCaducidadInsumo = formatoDelTexto.parse(fechaCaducidadText);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    Lote r = new Lote();
                    r = new Lote();
                    r.setLote(loteC);
                    r.setCodigoBarrasLote(codigoBarras);
                    r.setCantidadLote(cantidadLotes);
                    r.setFechaFabricacion(fechaFabricacionInsumo);
                    r.setFechaCaducidad(fechaCaducidadInsumo);
                    lotes.add(r);
                } else {
                    verMensaje = true;
                    mensaje.mensaje(msj, "amarillo");
                }
            } else {
                verMensaje = true;
                mensaje.mensaje("Ingresa un marbete con el formato correcto");
            }
        } else {
            verMensaje = true;
            mensaje.mensaje("Inserte código de barras y número de lotes", "amarillo");
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
                    bandera = true;
                    bitacora.setFecha(new Date());
                    bitacora.setIdEstatus(82);
                    bitacora.setDescripcion(bitacoraUtil.detalleGuardaroBitacora("archivos"));
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
                bitacora.setFecha(new Date());
                bitacora.setIdEstatus(82);
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
        remiList = remisionesService.remisionByIdRemision(idRemision);
        String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ISSSTE.png");
        for (Remisiones r : remiList) {
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
            rem.setSigla(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla());
            rem.setNep(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNep());
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
            rem.setGrupo(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdGrupo().getGrupo());// modifcacion de rcb
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
            rem.setRutaLogo(logoPath);
            Integer anio = c.get(Calendar.YEAR);
            rem.setAnio(anio.toString());
            //rem.setLeyendaValorComercial(r.getCanjePermuta() != null ? (r.getCanjePermuta() == 1 ? "SIN VALOR COMERCIAL" : "") : "");
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
        contratoList = contratoService.contratoConvenioById(10);
        for (Contratos c : contratoList) {
            ContratoDTO con = new ContratoDTO();
            con.setNumeroConvenio(c.getNumeroConvenio());
            for (CompradorContrato cp : c.getCompradorContratoList()) {
                con.setCompradorApellido(cp.getIdComprador().getApaterno());
                con.setCompradorNombre(cp.getIdComprador().getNombre());
                con.setCompradorApellidoMaterno(cp.getIdComprador().getAmaterno());
            }
            con.setDescripcionFundamento(c.getIdFundamentoLegal().getDescripcion());
            for (ContratoFalloProcedimientoRcb cfpr : c.getIdContratoFalloProcedimientoRcbList()) {
                InsumosDTO i = new InsumosDTO();
                String nep = "";//cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNep();
                String rcb = "";//cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNumero();
                String oficio = ""; //cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getOficioSuficienciaPresupuestal();
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
                BigDecimal importeTotal = precioUnitario.multiply(new BigDecimal(cantidadPiezas));
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
            con.setNumeroContrato(c.getNumeroContrato());
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

    public void cambiarCantidadLote(Lote l) {
        System.out.println("lll" + l.getCantidadLote());
        if (suma == null) {
            suma = 0;
        }
        suma2 += cantidadLotes;
    }

    public Integer getBuscar() {
        return buscar;
    }

    public void setBuscar(Integer buscar) {
        this.buscar = buscar;
    }

    public boolean isMostrarContrato() {
        return mostrarContrato;
    }

    public void setMostrarContrato(boolean mostrarContrato) {
        this.mostrarContrato = mostrarContrato;
    }

    public boolean isMostrarConvenio() {
        return mostrarConvenio;
    }

    public void setMostrarConvenio(boolean mostrarConvenio) {
        this.mostrarConvenio = mostrarConvenio;
    }

    public boolean isMostrarSuministro() {
        return mostrarSuministro;
    }

    public void setMostrarSuministro(boolean mostrarSuministro) {
        this.mostrarSuministro = mostrarSuministro;
    }

    public RemisionesService getRemisionesService() {
        return remisionesService;
    }

    public void setRemisionesService(RemisionesService remisionesService) {
        this.remisionesService = remisionesService;
    }

    public List<RemisionesDTO> getOrdenList() {
        return ordenList;
    }

    public void setOrdenList(List<RemisionesDTO> ordenList) {
        this.ordenList = ordenList;
    }

    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    public String getSeleccionarOrden() {
        return seleccionarOrden;
    }

    public void setSeleccionarOrden(String seleccionarOrden) {
        this.seleccionarOrden = seleccionarOrden;
    }

    public List<RemisionesDTO> getInsumosList() {
        return insumosList;
    }

    public void setInsumosList(List<RemisionesDTO> insumosList) {
        this.insumosList = insumosList;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Integer getLote() {
        return lote;
    }

    public void setLote(Integer lote) {
        this.lote = lote;
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
    }

    public Lote getLoteR() {
        return loteR;
    }

    public void setLoteR(Lote loteR) {
        this.loteR = loteR;
    }

    public Integer getOrdenSuministro() {
        return ordenSuministro;
    }

    public void setOrdenSuministro(Integer ordenSuministro) {
        this.ordenSuministro = ordenSuministro;
    }

    public String getRegistroControl() {
        return registroControl;
    }

    public void setRegistroControl(String registroControl) {
        this.registroControl = registroControl;
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

    public boolean isVerMensaje2() {
        return verMensaje2;
    }

    public void setVerMensaje2(boolean verMensaje2) {
        this.verMensaje2 = verMensaje2;
    }

    public boolean isVerRemisionesDevolucion() {
        return verRemisionesDevolucion;
    }

    public void setVerRemisionesDevolucion(boolean verRemisionesDevolucion) {
        this.verRemisionesDevolucion = verRemisionesDevolucion;
    }

    public List<Remisiones> getDevolucionList() {
        return devolucionList;
    }

    public void setDevolucionList(List<Remisiones> devolucionList) {
        this.devolucionList = devolucionList;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    public boolean isVerBotonActualizar() {
        return verBotonActualizar;
    }

    public void setVerBotonActualizar(boolean verBotonActualizar) {
        this.verBotonActualizar = verBotonActualizar;
    }

    public boolean isVerBotonGuardar() {
        return verBotonGuardar;
    }

    public void setVerBotonGuardar(boolean verBotonGuardar) {
        this.verBotonGuardar = verBotonGuardar;
    }

    public Integer getInsumo() {
        return insumo;
    }

    public void setInsumo(Integer insumo) {
        this.insumo = insumo;
    }

    public Integer getIdRemision() {
        return idRemision;
    }

    public void setIdRemision(Integer idRemision) {
        this.idRemision = idRemision;
    }

    public Integer getCantidadLotes() {
        return cantidadLotes;
    }

    public void setCantidadLotes(Integer cantidadLotes) {
        this.cantidadLotes = cantidadLotes;
    }

    public boolean isVerMensajeInsumos() {
        return verMensajeInsumos;
    }

    public void setVerMensajeInsumos(boolean verMensajeInsumos) {
        this.verMensajeInsumos = verMensajeInsumos;
    }

    public boolean isVerTablaInsumos() {
        return verTablaInsumos;
    }

    public void setVerTablaInsumos(boolean verTablaInsumos) {
        this.verTablaInsumos = verTablaInsumos;
    }

    public Date getFechaFabricacionInsumo() {
        return fechaFabricacionInsumo;
    }

    public void setFechaFabricacionInsumo(Date fechaFabricacionInsumo) {
        this.fechaFabricacionInsumo = fechaFabricacionInsumo;
    }

    public Date getFechaCaducidadInsumo() {
        return fechaCaducidadInsumo;
    }

    public void setFechaCaducidadInsumo(Date fechaCaducidadInsumo) {
        this.fechaCaducidadInsumo = fechaCaducidadInsumo;
    }

    public boolean isVerCargarDocumentos() {
        return verCargarDocumentos;
    }

    public void setVerCargarDocumentos(boolean verCargarDocumentos) {
        this.verCargarDocumentos = verCargarDocumentos;
    }

    public List<TipoDocumentos> getListaTipoDocs() {
        return listaTipoDocs;
    }

    public void setListaTipoDocs(List<TipoDocumentos> listaTipoDocs) {
        this.listaTipoDocs = listaTipoDocs;
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public List<RepositorioDocumentosDTO> getListaRepoDocsDto() {
        return listaRepoDocsDto;
    }

    public void setListaRepoDocsDto(List<RepositorioDocumentosDTO> listaRepoDocsDto) {
        this.listaRepoDocsDto = listaRepoDocsDto;
    }

    public BitacoraTareaEstatus getBitacora() {
        return bitacora;
    }

    public void setBitacora(BitacoraTareaEstatus bitacora) {
        this.bitacora = bitacora;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public boolean isVerBotonDescargarRemision() {
        return verBotonDescargarRemision;
    }

    public void setVerBotonDescargarRemision(boolean verBotonDescargarRemision) {
        this.verBotonDescargarRemision = verBotonDescargarRemision;
    }

    public boolean isOcultar() {
        return ocultar;
    }

    public void setOcultar(boolean ocultar) {
        this.ocultar = ocultar;
    }

    public RemisionesDTO getR() {
        return r;
    }

    public void setR(RemisionesDTO r) {
        this.r = r;
    }

    public RemisionesDTO getR2() {
        return r2;
    }

    public void setR2(RemisionesDTO r2) {
        this.r2 = r2;
    }

}
