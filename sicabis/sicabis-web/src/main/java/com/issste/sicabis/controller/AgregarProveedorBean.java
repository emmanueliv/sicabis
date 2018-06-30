package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.FabricanteService;
import com.issste.sicabis.ejb.ln.ProcedimientoService;
import com.issste.sicabis.ejb.ln.PropuestasService;
import com.issste.sicabis.ejb.ln.ProveedorFabricanteService;
import com.issste.sicabis.ejb.ln.ProveedorService;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.ln.TipoDocumentosService;
import com.issste.sicabis.ejb.ln.TipoProveedorService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Fabricante;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import com.issste.sicabis.ejb.modelo.Propuestas;
import com.issste.sicabis.ejb.modelo.ProveedorFabricante;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import com.issste.sicabis.ejb.modelo.TipoProveedor;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

public class AgregarProveedorBean {

    @EJB
    private TipoDocumentosService tipoDocumentosService;

    @EJB
    private TipoProveedorService tipoProveedorService;

    @EJB
    private ProcedimientoService procedimientoService;

    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;

    @EJB
    private PropuestasService propuestasService;

    @EJB
    private ProveedorFabricanteService proveedorFabricanteService;

    @EJB
    private FabricanteService fabricanteService;

    @EJB
    private ProveedorService proveedorService;

    /*
     Implementacion de bitacora
     */
    @EJB
    private BitacoraTareaSerice bitacoraService;

    private BitacoraTareaEstatus bitacora;
    private BitacoraUtil bitacoraUtil = new BitacoraUtil();

    private Procedimientos procedimientos;
    private Usuarios usuarios;
    private ProcedimientoRcb procedimientoRcb;
    private Propuestas propuestas;
    private Proveedores proveedores;
    private Fabricante fabricante;
    private ProveedorFabricante proveedorFabricante;
    private RespositorioDocumentos respositorioDocumentos;
    private RepositorioDocumentosDTO repositorioDocumentosDTO;
    private RepositorioDocumentosDTO repositorioDocumentosDTOAux;

    private boolean botonGuardar = true;
    private boolean messageGuardar = true;
    private boolean mensajeDialog = true;
    private boolean mensajeBorrar;
    private String clave;
    private List<Proveedores> listaProveedor;
    private Integer idProveedor;
    private List<Propuestas> listaPropuestas;
    private List<Propuestas> listaPropuestasFilter;
    private boolean bdescuento = true;
    private boolean bprecio = true;
    private Integer idClasificacion;
    private List<Fabricante> listaFabricantes;
    public boolean bnuevo;
    private List<ProveedorFabricante> listaProvFab;
    private List<ProveedorFabricante> listaProvFabFilter;
    private Integer idFabricante;
    private boolean messageDialog;
    private String nombreFabricante;
    private String registroSanitario;
    private boolean bactualizar;
    private boolean primera;
    private boolean bnuevoProv;
    private String nombreProv;
    private Integer idTipoProv;
    private List<TipoProveedor> listaTipoProv;

    private Integer idTipoDocumento;
    private List<TipoDocumentos> listaTipoDocs;
    private List<RespositorioDocumentos> listaRepoDocs;
    private StreamedContent file;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private UploadedFile uploadedfile;
    private boolean barchivos;
    private BigDecimal precioUni;
    private BigDecimal precioGanadorAnt;

    private BigDecimal precioUnRcb;
    private BigDecimal precioUnDesc;

    private final Integer idTarea = 19;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();

    public AgregarProveedorBean() {
        listaPropuestas = new ArrayList();
        listaRepoDocsDto = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        procedimientoRcb = (ProcedimientoRcb) util.getContextAtributte("procedimientoRcb");
        this.inicializaPropuesta();
        barchivos = (boolean) util.getContextAtributte("barchivos");
        buscarArchivosByIdProcesoIdTarea(procedimientoRcb.getIdProcedimiento().getIdProcedimiento(), idTarea);
        if (barchivos) {
            botonGuardar = false;
        }
        buscarArchivosByIdProcesoIdTarea(procedimientoRcb.getIdProcedimientoRcb(), idTarea);
        bitacora = new BitacoraTareaEstatus();
        listaProvFab = new ArrayList();
        if (procedimientoRcb.getPropuestasList().size() > 0) {
            List<Propuestas> listaPropuestasAux = new ArrayList();
            bactualizar = true;
            listaPropuestas = procedimientoRcb.getPropuestasList();
            for (Propuestas propuestas : listaPropuestas) {
                Proveedores p = propuestas.getIdProveedor();
                listaProvFab = proveedorFabricanteService.obtenerByProveedorProcRcb(p.getIdProveedor(), procedimientoRcb.getIdProcedimientoRcb());
                p.setProveedorFabricanteList(listaProvFab);
                listaPropuestasAux.add(propuestas);
            }
            listaPropuestas.clear();
            listaPropuestas = listaPropuestasAux;
        } else {
            listaPropuestas = new ArrayList();
        }
        idClasificacion = procedimientoRcb.getIdProcedimiento().getIdClasificacionProcedimiento().getIdClasificacionProcedimiento().intValue();
        if (idClasificacion == 1) {
            bdescuento = false;
            bprecio = false;
        } else if (idClasificacion == 2) {
            bprecio = false;
        } else if (idClasificacion == 3) {
            bdescuento = false;
            propuestas.setPrecioUnitario(procedimientoRcb.getPrecioUnitario());
        }
        listaProveedor = proveedorService.getAllProveedoresByActivo();
        listaTipoProv = tipoProveedorService.obtenerTipoProveedores();
        listaTipoDocs = tipoDocumentosService.obtenerByIdTarea(19);
    }

    public void inicializaPropuesta() {
        propuestas = new Propuestas();
        List<Propuestas> listPropuestas = new ArrayList<>();
        listPropuestas = propuestasService.getPropuestasByIdProcedimientoRcb(procedimientoRcb.getIdProcedimientoRcb());
        for (Propuestas iterator : listPropuestas) {
            if (iterator.getAperturaProgramada() == null) {
                propuestas.setAperturaProgramada(new Date());
            } else {
                propuestas.setAperturaProgramada(iterator.getAperturaProgramada());
            }
            if (iterator.getAperturaRealizada() == null) {
                propuestas.setAperturaRealizada(new Date());
            } else {
                propuestas.setAperturaRealizada(iterator.getAperturaRealizada());
            }
        }
        propuestas.setActivo(1);
        propuestas.setIdProcedimientoRcb(procedimientoRcb);
        propuestas.setDescuentoOtorgado(BigDecimal.ZERO);
        propuestas.setPrecioUnitario(procedimientoRcb.getPrecioUnitario());
        precioUnRcb = procedimientoRcb.getPrecioUnitario();
        precioUnDesc = BigDecimal.ZERO;
    }

    public void cambiaProveedor() {
        System.out.println("idProveedor-->" + idProveedor);
        if (idProveedor.intValue() == -2) {
            bnuevoProv = true;
        } else {
            bnuevoProv = false;
        }
    }

    public void calculoPrecioUnit() {
        switch (idClasificacion) {
            case 1:
                if (propuestas.getDescuentoOtorgado().floatValue() >= 0 && propuestas.getDescuentoOtorgado().floatValue() <= 100 && propuestas.getPrecioUnitario().floatValue() != 0) {
                    precioUnDesc = util.obtieneDescuento(propuestas.getPrecioUnitario(), propuestas.getDescuentoOtorgado());
                }
                break;
            case 2:
                if (propuestas.getPrecioUnitario().floatValue() != 0) {
                    precioUnDesc = propuestas.getPrecioUnitario();
                }
                break;
            case 3:
                if (propuestas.getDescuentoOtorgado().floatValue() >= 0 && propuestas.getDescuentoOtorgado().floatValue() <= 100) {
                    precioUnDesc = util.obtieneDescuento(propuestas.getPrecioUnitario(), propuestas.getDescuentoOtorgado());
                }
                break;
        }
    }

    public void agregarProveedor() {
        boolean bandera = true;
        boolean banderaProv = true;
        Proveedores prov = null;
        if (idProveedor.intValue() != -1) {
            if (idProveedor.intValue() == -2) {
                if (nombreProv.equals("")) {
                    mensaje.mensaje("Debes ingresar el nombre del proveedor", "amarillo");
                    banderaProv = false;
                    bandera = false;
                }
                if (idTipoProv.intValue() == -1) {
                    mensaje.mensaje("Debes ingresar el tipo de proveedor", "amarillo");
                    banderaProv = false;
                    bandera = false;
                }
            } else {
                banderaProv = false;
            }
            for (Propuestas p : listaPropuestas) {
                if (idProveedor.intValue() == p.getIdProveedor().getIdProveedor().intValue()) {
                    mensaje.mensaje("El proveedor ya fue agregado en la lista", "amarillo");
                    bandera = false;
                    break;
                }
            }
            if (bandera) {
                if (banderaProv) {
                    prov = proveedorService.obtenerProveedorByNombre(nombreProv);
                    if (prov == null) {
                        prov = new Proveedores();
                        prov.setNombreProveedor(nombreProv);
                        prov.setActivo(1);
                        for (TipoProveedor tp : listaTipoProv) {
                            if (tp.getIdTipoProveedor().intValue() == idTipoProv.intValue()) {
                                prov.setIdTipoProveedor(tp);
                                break;
                            }
                        }
                        prov.setAutorizado(0);
                        prov.setUsuarioAlta(usuarios.getUsuario());
                        prov.setFechaAlta(new Date());
                        proveedorService.guardarProveedor(prov);
                        listaProveedor = proveedorService.proveedoresAll();
                    }
                    propuestas.setIdProveedor(prov);
                } else {
                    propuestas.setIdProveedor(proveedorService.obtenerByIdProveedor(idProveedor));
                }
                precioUni = propuestas.getPrecioUnitario();
                switch (idClasificacion) {
                    case 1:
                        if (propuestas.getDescuentoOtorgado().floatValue() < 0 || propuestas.getDescuentoOtorgado().floatValue() > 100 || propuestas.getPrecioUnitario().floatValue() == 0) {
                            mensaje.mensaje("Debes ingresar el descuento otorgado valido y/o el precio unitario", "amarillo");
                        } else {
                            propuestas.setPrecioUnitarioDescuento(util.obtieneDescuento(propuestas.getPrecioUnitario(), propuestas.getDescuentoOtorgado()));
                            propuestas.setImporte(propuestas.getPrecioUnitarioDescuento().multiply(new BigDecimal(procedimientoRcb.getCantidadPiezas())));
                            listaPropuestas.add(propuestas);
                        }
                        break;
                    case 2:
                        if (propuestas.getPrecioUnitario().floatValue() == 0) {
                            mensaje.mensaje("Debes ingresar el precio unitario", "amarillo");
                        } else {
                            propuestas.setPrecioUnitarioDescuento(util.obtieneDescuento(propuestas.getPrecioUnitario(), propuestas.getDescuentoOtorgado()));
                            propuestas.setImporte(propuestas.getPrecioUnitarioDescuento().multiply(new BigDecimal(procedimientoRcb.getCantidadPiezas())));
                            listaPropuestas.add(propuestas);
                        }
                        break;
                    case 3:
                        if (propuestas.getDescuentoOtorgado().floatValue() < 0 || propuestas.getDescuentoOtorgado().floatValue() > 100) {
                            mensaje.mensaje("Debes ingresar el descuento otorgado valido", "amarillo");
                        } else {
                            propuestas.setPrecioUnitarioDescuento(util.obtieneDescuento(propuestas.getPrecioUnitario(), propuestas.getDescuentoOtorgado()));
                            propuestas.setImporte(propuestas.getPrecioUnitarioDescuento().multiply(new BigDecimal(procedimientoRcb.getCantidadPiezas())));
                            listaPropuestas.add(propuestas);
                        }
                        break;
                }
                nombreProv = "";
                idTipoProv = -1;
                bnuevoProv = false;
                idProveedor = -1;
                this.inicializaPropuesta();
                if (idClasificacion == 3) {
                    this.propuestas.setPrecioUnitario(procedimientoRcb.getPrecioUnitario());
                }
            }
        } else {
            mensaje.mensaje("Debes seleccionar el proveedor", "amarillo");
        }

    }

    public void guardaProveedor() {
        boolean bandera = true;
        if (listaPropuestas.size() > 0) {
            if (bactualizar) {
                proveedorFabricanteService.borrarByIdProveedorFabricante(procedimientoRcb.getIdProcedimientoRcb());
                propuestasService.borrarByIdProcedimientoRcb(procedimientoRcb.getIdProcedimientoRcb());
            }
            for (Propuestas listaPropuesta : listaPropuestas) {
                listaPropuesta.setGanador(this.calculaResultado(listaPropuesta));
                listaPropuesta.setPrecioUnitario(precioUni);
                if (!propuestasService.guardarPropuestas(listaPropuesta)) {
                    bandera = false;
                } else {
                    if (listaPropuesta.getIdProveedor().getProveedorFabricanteList() != null) {
                        for (ProveedorFabricante provFabList : listaPropuesta.getIdProveedor().getProveedorFabricanteList()) {
                            proveedorFabricanteService.guardarProveedorFabricante(provFabList);
                        }
                    }
                }
            }
            if (bandera) {
                mensaje.mensaje(mensaje.datos_guardados, "verde");
                Procedimientos p = procedimientoRcb.getIdProcedimiento();
                if (p.getVerificaSansiones().intValue() == 0) {
                    p.setVerificaSansiones(1);
                    procedimientoService.actualizaProcedimiento(p);
                }
                bitacora.setFecha(new Date());
                bitacora.setIdEstatus(191);
                bitacora.setIdModulos(procedimientoRcb.getIdProcedimientoRcb());
                bitacora.setDescripcion(bitacoraUtil.detalleActualizarBitacora(" Agregar Propuesta "));
                bitacora.setIdUsuarios(usuarios.getIdUsuario());
                bitacora.setIdTareaId(idTarea);
                bitacoraService.guardarEnBitacora(bitacora);
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlg3').show();");
            } else {
                mensaje.mensaje(mensaje.error_guardar, "rojo");
            }
        } else {
            mensaje.mensaje("Debes agregar al menos un proveedor", "amarillo");
        }
        //return null;
    }

    public Integer calculaResultado(Propuestas propuestas) {
        BigDecimal aceptable = new BigDecimal(0);
        BigDecimal conveniente = new BigDecimal(0);
        BigDecimal precioBajo = new BigDecimal(0);
        BigDecimal precioProp = new BigDecimal(0);
        String tipo = "";
        String tipoAnt = "";
        Propuestas prop = null;

        aceptable = procedimientoRcb.getPrecioUnitario().multiply(new BigDecimal(1.1));
        conveniente = procedimientoRcb.getPrecioUnitario().multiply(new BigDecimal(0.9));
        precioBajo = BigDecimal.ZERO;
        precioProp = propuestas.getPrecioUnitarioDescuento();
        if (precioProp.doubleValue() != 0) {//valida q el precio no sea 0 ya q es desierta
            tipo = propuestas.getIdProveedor().getIdTipoProveedor().getTipo();
            if (prop != null) {
                tipoAnt = prop.getIdProveedor().getIdTipoProveedor().getTipo();
            }
            if (precioBajo.doubleValue() != 0) {//validamos q el precio mas bajo no sea cero, q ya tenga algÃºn valor guardado
                if (precioProp.doubleValue() < precioBajo.doubleValue()) {//si el precio es menor al precio mas bajo
                    if (precioProp.doubleValue() >= conveniente.doubleValue() && precioProp.doubleValue() <= aceptable.doubleValue()) {//el precio este dentro del rango
                        if (tipo.equals("N") || tipo.equals("N/I")) {//si es N o N/I tiene preferencia por ser el mas bajo
                            precioBajo = precioProp;
                            prop = propuestas;
                        } else {
                            if (tipoAnt.equals("I")) {//Si el anterior es I tiene prioridad el nuevo
                                precioBajo = precioProp;
                                prop = propuestas;
                            }
                        }
                    }
                } else if (precioBajo.doubleValue() == precioProp.doubleValue()) {//Si son iguales
                    if (tipoAnt.equals("I") && (tipo.equals("N") || tipo.equals("N/I"))) {
                        precioBajo = precioProp;
                        prop = propuestas;
                    }
                }
            } else {
                if (precioProp.doubleValue() >= conveniente.doubleValue() && precioProp.doubleValue() <= aceptable.doubleValue()) {
                    precioBajo = precioProp;
                    prop = propuestas;
                }
            }
        }

        if (prop != null) {
            return 1;
        }
        return 0;
    }

    public String salir() {
        util.destroyContextMap("procedimientoRcb");
        return "propuestas.xhtml?faces-redirect=true";
    }

    public void limpiar() {
        RequestContext.getCurrentInstance().reset("formProp");
    }

    public void editaProveedor(Propuestas propuestas) {
        idProveedor = propuestas.getIdProveedor().getIdProveedor();
        this.propuestas = propuestas;
        quitaProveedor(propuestas);
    }

    public void quitaProveedor(Propuestas propuestas) {
        List<Propuestas> listaPropuestasAux = new ArrayList();
        for (Propuestas p : listaPropuestas) {
            if (p != propuestas) {
                listaPropuestasAux.add(p);
            }
        }
        listaPropuestas.clear();
        listaPropuestas = listaPropuestasAux;
    }

    public void agregaFabricante(Propuestas propuestas) {
        this.inicializaPropuesta();
//        if (idClasificacion == 3) {
//            this.propuestas.setPrecioUnitario(procedimientoRcb.getPrecioUnitario());
//        }
        this.propuestas = propuestas;
        proveedorFabricante = new ProveedorFabricante();
        fabricante = new Fabricante();
        listaFabricantes = fabricanteService.fabricanteList();
        listaProvFab = propuestas.getIdProveedor().getProveedorFabricanteList();
        if (listaProvFab == null) {
            listaProvFab = new ArrayList();
        }
        messageDialog = true;
        messageGuardar = false;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg1').show();");
    }

    public void agregarProvFabricante() {
        boolean bandera = true;
        if (idFabricante.intValue() != -1) {
            proveedorFabricante.setActivo(1);
            proveedorFabricante.setIdProveedor(propuestas.getIdProveedor());
            proveedorFabricante.setFechaAlta(new Date());
            proveedorFabricante.setUsuarioAlta(usuarios.getNombre());
            proveedorFabricante.setIdProcedimientoRcb(procedimientoRcb.getIdProcedimientoRcb());
            if (idFabricante.intValue() == -2) {
                if (nombreFabricante.equals("") || registroSanitario.equals("")) {
                    mensaje.mensaje("Debes ingresar el nombre y el registro sanitario", "amarillo");
                } else {
                    Fabricante faux = null;
                    faux = fabricanteService.fabricanteByNombre(nombreFabricante.toUpperCase());
                    if (faux == null) {
                        fabricante.setNombre(nombreFabricante.toUpperCase());
                        fabricante.setRegistroSanitario(registroSanitario);
                        fabricante.setActivo(1);
                        fabricante.setFechaAlta(new Date());
                        fabricante.setUsuarioAlta(usuarios.getNombre());
                        if (fabricanteService.guardarFabricante(fabricante)) {
                            mensaje.mensaje(mensaje.datos_guardados, "verde");
                            idFabricante = fabricante.getIdFabricante();
                            listaFabricantes = fabricanteService.fabricanteList();
                            bnuevo = false;
                        } else {
                            mensaje.mensaje(mensaje.error_guardar, "rojo");
                        }
                    } else {
                        mensaje.mensaje("El fabricante que deseas agregar ya se encuentra almacenado", "amarillo");
                        bandera = false;
                    }
                }
            } else {
                for (ProveedorFabricante pb : listaProvFab) {
                    if (pb.getIdFabricante().getIdFabricante().intValue() == idFabricante.intValue()) {
                        bandera = false;
                        mensaje.mensaje("El fabricante ya fue agregado en la lista", "amarillo");
                    }
                }
            }
            if (bandera) {
                fabricante = fabricanteService.fabricanteByIdFacbricante(idFabricante);
                proveedorFabricante.setIdFabricante(fabricante);
                listaProvFab.add(proveedorFabricante);
                proveedorFabricante = new ProveedorFabricante();
                idFabricante = -1;
            }
        } else {
            mensaje.mensaje("Debes seleccionar el fabricante", "amarillo");
        }
    }

    public void quitaFabricante(ProveedorFabricante provfab) {
        List<ProveedorFabricante> listaProveedorFabricantes = new ArrayList();
        for (ProveedorFabricante pf : this.listaProvFab) {
            if (pf != provfab) {
                listaProveedorFabricantes.add(pf);
            }
        }
        this.listaProvFab.clear();
        this.listaProvFab = listaProveedorFabricantes;
    }

    public void cambiaFabricante() {
        if (idFabricante == -2) {
            bnuevo = true;
        } else {
            bnuevo = false;
        }
    }

    public void limpiarDialog() {
        RequestContext.getCurrentInstance().reset("formfab");
        init();
    }

    public void fabricantesAgregados() {
        List<Propuestas> listP = new ArrayList();
        Proveedores proveedorAux = new Proveedores();
        proveedorAux = propuestas.getIdProveedor();
        proveedorAux.setProveedorFabricanteList(listaProvFab);
        for (Propuestas p : listaPropuestas) {
            if (p.getIdProveedor().getIdProveedor().intValue() == propuestas.getIdProveedor().getIdProveedor().intValue()) {
                p.setIdProveedor(proveedorAux);
                listP.add(p);
            } else {
                listP.add(p);
            }
        }
        listaPropuestas.clear();
        listaPropuestas = listP;
        messageDialog = false;
        messageGuardar = true;
        this.inicializaPropuesta();
        if (idClasificacion == 3) {
            this.propuestas.setPrecioUnitario(procedimientoRcb.getPrecioUnitario());
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
            respositorioDocumentos.setIdProceso(procedimientoRcb.getIdProcedimientoRcb());
            respositorioDocumentos.setIdTipoDocumento(new TipoDocumentos(idTipoDocumento));
            respositorioDocumentos.setNombre(event.getFile().getFileName());
            respositorioDocumentos.setRuta(archivosUtilidades.PATHFILES);
            respositorioDocumentos.setUsuarioAlta(usuarios.getNombre());
            String nombreArchivo = archivosUtilidades.generaNombreArchivo(uploadedfile, idTarea, respositorioDocumentos.getIdProceso());
            respositorioDocumentos.setNombreArchivo(nombreArchivo);
            if (respositorioDocumentosService.guardaProcedimiento(respositorioDocumentos)) {
                mensaje.mensaje(mensaje.datos_guardados, "verde");
                //se envia el archivo y el id tarea (4 es id tarea fallo)
                if (archivosUtilidades.guardaArchivo(uploadedfile, nombreArchivo)) {
                    mensaje.mensaje(mensaje.archivo_bien, "verde");
                    bandera = true;
                    botonGuardar = true;
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
            buscarArchivosByIdProcesoIdTarea(procedimientoRcb.getIdProcedimientoRcb(), idTarea);
            botonGuardar = true;
            //RequestContext.getCurrentInstance().update("botonGuardar");
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
            }
        }
        listaRepoDocsDto = listaRepoDocsDtoAux;
    }

    public void actualizarPropuesta() {
        List<Propuestas> listPropuestas = new ArrayList<>();
        listPropuestas = propuestasService.getPropuestasByIdProcedimientoRcb(procedimientoRcb.getIdProcedimientoRcb());
        for (Propuestas iterator : listPropuestas) {
            iterator.setAperturaProgramada(propuestas.getAperturaProgramada());
            iterator.setAperturaRealizada(propuestas.getAperturaRealizada());
            if (propuestasService.actualizarPropuestas(iterator)) {
                mensaje.mensaje("Actualizacion efectuada exitosamente.", "verde");
            }
        }
    }

    public Procedimientos getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(Procedimientos procedimientos) {
        this.procedimientos = procedimientos;
    }

    public boolean isBotonGuardar() {
        return botonGuardar;
    }

    public void setBotonGuardar(boolean botonGuardar) {
        this.botonGuardar = botonGuardar;
    }

    public boolean isMessageGuardar() {
        return messageGuardar;
    }

    public void setMessageGuardar(boolean messageGuardar) {
        this.messageGuardar = messageGuardar;
    }

    public boolean isMensajeBorrar() {
        return mensajeBorrar;
    }

    public void setMensajeBorrar(boolean mensajeBorrar) {
        this.mensajeBorrar = mensajeBorrar;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public ProcedimientoRcb getProcedimientoRcb() {
        return procedimientoRcb;
    }

    public void setProcedimientoRcb(ProcedimientoRcb procedimientoRcb) {
        this.procedimientoRcb = procedimientoRcb;
    }

    public List<Proveedores> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(List<Proveedores> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Propuestas getPropuestas() {
        return propuestas;
    }

    public void setPropuestas(Propuestas propuestas) {
        this.propuestas = propuestas;
    }

    public List<Propuestas> getListaPropuestas() {
        return listaPropuestas;
    }

    public void setListaPropuestas(List<Propuestas> listaPropuestas) {
        this.listaPropuestas = listaPropuestas;
    }

    public List<Propuestas> getListaPropuestasFilter() {
        return listaPropuestasFilter;
    }

    public void setListaPropuestasFilter(List<Propuestas> listaPropuestasFilter) {
        this.listaPropuestasFilter = listaPropuestasFilter;
    }

    public boolean isBdescuento() {
        return bdescuento;
    }

    public void setBdescuento(boolean bdescuento) {
        this.bdescuento = bdescuento;
    }

    public boolean isBprecio() {
        return bprecio;
    }

    public void setBprecio(boolean bprecio) {
        this.bprecio = bprecio;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public ProveedorFabricante getProveedorFabricante() {
        return proveedorFabricante;
    }

    public void setProveedorFabricante(ProveedorFabricante proveedorFabricante) {
        this.proveedorFabricante = proveedorFabricante;
    }

    public Integer getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(Integer idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public List<Fabricante> getListaFabricantes() {
        return listaFabricantes;
    }

    public void setListaFabricantes(List<Fabricante> listaFabricantes) {
        this.listaFabricantes = listaFabricantes;
    }

    public boolean isBnuevo() {
        return bnuevo;
    }

    public void setBnuevo(boolean bnuevo) {
        this.bnuevo = bnuevo;
    }

    public List<ProveedorFabricante> getListaProvFab() {
        return listaProvFab;
    }

    public void setListaProvFab(List<ProveedorFabricante> listaProvFab) {
        this.listaProvFab = listaProvFab;
    }

    public List<ProveedorFabricante> getListaProvFabFilter() {
        return listaProvFabFilter;
    }

    public void setListaProvFabFilter(List<ProveedorFabricante> listaProvFabFilter) {
        this.listaProvFabFilter = listaProvFabFilter;
    }

    public Integer getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(Integer idFabricante) {
        this.idFabricante = idFabricante;
    }

    public boolean isMessageDialog() {
        return messageDialog;
    }

    public void setMessageDialog(boolean messageDialog) {
        this.messageDialog = messageDialog;
    }

    public String getNombreFabricante() {
        return nombreFabricante;
    }

    public void setNombreFabricante(String nombreFabricante) {
        this.nombreFabricante = nombreFabricante;
    }

    public String getRegistroSanitario() {
        return registroSanitario;
    }

    public void setRegistroSanitario(String registroSanitario) {
        this.registroSanitario = registroSanitario;
    }

    public boolean isBactualizar() {
        return bactualizar;
    }

    public void setBactualizar(boolean bactualizar) {
        this.bactualizar = bactualizar;
    }

    public boolean isMensajeDialog() {
        return mensajeDialog;
    }

    public void setMensajeDialog(boolean mensajeDialog) {
        this.mensajeDialog = mensajeDialog;
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

    public boolean isBnuevoProv() {
        return bnuevoProv;
    }

    public void setBnuevoProv(boolean bnuevoProv) {
        this.bnuevoProv = bnuevoProv;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    public Integer getIdTipoProv() {
        return idTipoProv;
    }

    public void setIdTipoProv(Integer idTipoProv) {
        this.idTipoProv = idTipoProv;
    }

    public List<TipoProveedor> getListaTipoProv() {
        return listaTipoProv;
    }

    public void setListaTipoProv(List<TipoProveedor> listaTipoProv) {
        this.listaTipoProv = listaTipoProv;
    }

    public BigDecimal getPrecioUnRcb() {
        return precioUnRcb;
    }

    public void setPrecioUnRcb(BigDecimal precioUnRcb) {
        this.precioUnRcb = precioUnRcb;
    }

    public BigDecimal getPrecioUnDesc() {
        return precioUnDesc;
    }

    public void setPrecioUnDesc(BigDecimal precioUnDesc) {
        this.precioUnDesc = precioUnDesc;
    }

}
