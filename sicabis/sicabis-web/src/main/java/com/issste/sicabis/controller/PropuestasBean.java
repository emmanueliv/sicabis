package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.PropuestasArchivoDTO;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.FalloService;
import com.issste.sicabis.ejb.ln.ProcedimientoService;
import com.issste.sicabis.ejb.ln.ProcedimientosRcbService;
import com.issste.sicabis.ejb.ln.PropuestasService;
import com.issste.sicabis.ejb.ln.ProveedorFabricanteService;
import com.issste.sicabis.ejb.ln.ProveedorService;
import com.issste.sicabis.ejb.ln.TipoProveedorService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Fallos;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import com.issste.sicabis.ejb.modelo.Propuestas;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.TipoProveedor;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

public class PropuestasBean {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private PropuestasService propuestasService;

    @EJB
    private ProveedorFabricanteService proveedorFabricanteService;

    @EJB
    private TipoProveedorService tipoProveedorService;

    @EJB
    private ProveedorService proveedorService;

    @EJB
    private FalloService falloService;

    @EJB
    private ProcedimientoService procedimientoService;

    @EJB
    private ProcedimientosRcbService procedimientosRcbService;

    private Procedimientos procedimientos;
    private Usuarios usuarios;
    private Fallos fallos;
    private Propuestas propuestas;
    private Proveedores proveedores;
    private TipoProveedor tp;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Integer tabActivo;
    private boolean botonGuardar;
    private boolean messageGuardar = true;
    private boolean messageConsulta;
    private List<ProcedimientoRcb> listaProcRcb;
    private List<ProcedimientoRcb> listaProcRcbFilter;
    private boolean mensajeBorrar;
    private String numeroProcedimiento;
    private boolean bsansiones;
    private UploadedFile uploadedfile;
    private List<PropuestasArchivoDTO> listaPropuestasDTO;
    private List<PropuestasArchivoDTO> listaPropuestasDTOFilter;
    private List<Propuestas> listaPropuestas;
    private boolean bactivo = true;
    private boolean bexiste;
    private boolean bresultado;
    private String clavesBien;
    private String clavesMal;
    private int numClavesBien;
    private int numClavesMal;

    private final Integer idTarea = 19;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();

    public PropuestasBean() {
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        botonGuardar = true;
        listaProcRcb = new ArrayList();
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        fallos = new Fallos();
        
        //bsansiones = true;
    }
    
    private StreamedContent file;

    public StreamedContent getFile(int opcion) {
        if (opcion == 1) {
            InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/layouts/PropuestasLayout.xlsx");
            file = new DefaultStreamedContent(stream, "application/vnd.ms-excel", "PropuestasLayout.xlsx");
        } else if (opcion == 2) {
            InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/layouts/Propuesta_ejemplo_llenado.pdf");
            file = new DefaultStreamedContent(stream, "application/pdf", "Propuesta_ejemplo_llenado.pdf");
        }
        return file;
    }

    public void obtenerProcedimientoByNumero() {
        messageGuardar = true;
        messageConsulta = false;
        try {
            if (!numeroProcedimiento.equals("")) {
                procedimientos = procedimientoService.obtenerByNumeroProcedimiento(numeroProcedimiento);
                if (procedimientos != null) {
                    if (procedimientos.getIdEstatus().getIdEstatus() == 32 && procedimientos.getActivo() == 1) {
                        bsansiones = procedimientos.getVerificaSansiones() == 1 ? true : false;
                        listaProcRcb = procedimientosRcbService.obtenerByIdProcedimiento(procedimientos.getIdProcedimiento());//procedimientosRcbService.obtenerByNumeroProc(numeroProcedimiento);
                        if (listaProcRcb.get(0).getIdProcedimiento().getIdTipoCompra().getNombre().equals("ISSSTE")) {
                            if (procedimientos.getVerificaSansiones().intValue() == 1) {
                                bexiste = true;
                            }
                            fallos = falloService.obtenerByNumProcRcb(numeroProcedimiento);
                            bactivo = false;
                        } else {
                            mensaje.mensaje("El tipo de compra para el procedimiento ingresado no es tipo ISSSTE por lo tanto no se puede ingresar propuestas", "amarillo");
                        }
                    } else {
                        mensaje.mensaje("El procedimiento se encuentra en estatus Captura", "amarillo");
                    }
                } else {
                    mensaje.mensaje("El número de procedimiento no existe", "amarillo");
                }
            } else {
                mensaje.mensaje("Debe escribir el número de procedimiento", "amarillo");
            }
        } catch (Exception e) {
            System.out.println("falle--->");
        }
    }

    public String agregarProveedor(ProcedimientoRcb procedimientoRcb) {
        boolean barchivos = false;
        util.setContextAtributte("procedimientoRcb", procedimientoRcb);
        if (fallos != null) {
            barchivos = true;
        }
        util.setContextAtributte("barchivos", barchivos);
        return "agregarProveedor.xhtml?faces-redirect=true";
    }

    public void cambiaOpcion() {
        System.out.println("bsansiones-->" + bsansiones);
    }

    public void validaCargaArchivo(FileUploadEvent event) {
        uploadedfile = event.getFile();
        if (bexiste) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlg3').show();");
        } else {
            this.cargaPorArchivo();
        }
    }

    public void cargaPorArchivo() {
        String nombreArchivo = archivosUtilidades.generaNombreArchivo(uploadedfile, idTarea, 1);
        listaPropuestas = new ArrayList();
        listaPropuestasDTO = archivosUtilidades.leerExcelPropuestas(uploadedfile, nombreArchivo, listaProcRcb);
        for (PropuestasArchivoDTO pdto : listaPropuestasDTO) {
            propuestas = new Propuestas();
            propuestas.setIdProcedimientoRcb(pdto.getProcedimientoRcb());
            propuestas.setActivo(1);
            propuestas.setPrecioUnitario(pdto.getPrecioRef());
            propuestas.setPrecioUnitarioDescuento(pdto.getPrecioDesc());
            propuestas.setDescuentoOtorgado(util.obtienePorcDesc(pdto.getPrecioRef(), pdto.getPrecioDesc()));
            propuestas.setImporte(util.multiplica(propuestas.getPrecioUnitarioDescuento(), propuestas.getIdProcedimientoRcb().getCantidadPiezas()));

            proveedores = proveedorService.obtenerProveedorByNombre(pdto.getNombreProveedor());
            if (proveedores == null) {
                proveedores = new Proveedores();
                proveedores.setNombreProveedor(pdto.getNombreProveedor());
                proveedores.setActivo(1);
                tp = tipoProveedorService.obtenerByTipo(pdto.getTipoProveedor());
                proveedores.setIdTipoProveedor(tp);
                proveedores.setAutorizado(0);
                proveedores.setUsuarioAlta(usuarios.getUsuario());
                proveedores.setFechaAlta(new Date());
                proveedorService.guardarProveedor(proveedores);
            }
            propuestas.setIdProveedor(proveedores);
            listaPropuestas.add(propuestas);
        }
        numClavesBien = listaPropuestasDTO.get(0).getNumClavesBien();
        clavesBien = listaPropuestasDTO.get(0).getClavesBien();
        numClavesMal = listaPropuestasDTO.get(0).getNumClavesMal();
        clavesMal = listaPropuestasDTO.get(0).getClavesMal();
        for (ProcedimientoRcb pr : listaProcRcb) {
            proveedorFabricanteService.borrarByIdProveedorFabricante(pr.getIdProcedimientoRcb());
            propuestasService.borrarByIdProcedimientoRcb(pr.getIdProcedimientoRcb());
        }
        for (Propuestas propuestas : listaPropuestas) {
            propuestas.setGanador(0);
            propuestasService.guardarPropuestas(propuestas);
        }

        mensaje.mensaje(mensaje.datos_guardados, "verde");
        Procedimientos p = procedimientos;
        if (procedimientos.getVerificaSansiones().intValue() == 0) {
            procedimientos.setVerificaSansiones(1);
            procedimientoService.actualizaProcedimiento(p);
        }
        bresultado = true;
        this.obtenerProcedimientoByNumero();
        this.calculaResultado();
    }

    public void calculaResultado() {
        messageGuardar = false;
        messageConsulta = true;
        BigDecimal aceptable = new BigDecimal(0);
        BigDecimal conveniente = new BigDecimal(0);
        BigDecimal precioBajo = new BigDecimal(0);
        BigDecimal precioProp = new BigDecimal(0);
        String tipo = "";
        String tipoAnt = "";
        Propuestas prop = null;
        //listaProcRcb = procedimientosRcbService.obtenerByNumeroProc(numeroProcedimiento);
        for (ProcedimientoRcb pr : listaProcRcb) {
            aceptable = pr.getPrecioUnitario().multiply(new BigDecimal(1.1));
            conveniente = pr.getPrecioUnitario().multiply(new BigDecimal(0.9));
            precioBajo = BigDecimal.ZERO;
            if (pr.getPropuestasList().size() > 0) {
                for (Propuestas propuestas : pr.getPropuestasList()) {
                    precioProp = propuestas.getPrecioUnitarioDescuento();
                    if (precioProp.doubleValue() != 0) {//valida q el precio no sea 0 ya q es desierta
                        tipo = propuestas.getIdProveedor().getIdTipoProveedor().getTipo();
                        if (prop != null) {
                            tipoAnt = prop.getIdProveedor().getIdTipoProveedor().getTipo();
                        }
                        if (precioBajo.doubleValue() != 0) {//validamos q el precio mas bajo no sea cero, q ya tenga algún valor guardado
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
                }
                if (prop != null) {
                    prop.setGanador(1);
                    if (!propuestasService.actualizarPropuestas(prop)) {
                        System.out.println("error actualizando");
                    }
                }
            }
        }
    }

//    public void obtenerResultadoPropuestas() {
//        messageGuardar = false;
//        messageConsulta = true;
//        BigDecimal aceptable = new BigDecimal(0);
//        BigDecimal conveniente = new BigDecimal(0);
//        BigDecimal precioBajo = new BigDecimal(0);
//        BigDecimal precioProp = new BigDecimal(0);
//        String tipo = "";
//        String tipoAnt = "";
//        Propuestas prop = null;
//        if (!numeroProcedimiento.equals("")) {
//            procedimientos = procedimientoService.obtenerByNumeroProcedimiento(numeroProcedimiento);
//            if (procedimientos != null) {
//                listaProcRcb = procedimientosRcbService.obtenerByNumeroProc(numeroProcedimiento);
//                if (listaProcRcb.get(0).getIdRcbInsumos().getIdRcb().getIdTipoCompra().getNombre().equals("ISSSTE")) {
//                    for (ProcedimientoRcb pr : listaProcRcb) {
//                        aceptable = pr.getPrecioUnitario().multiply(new BigDecimal(1.1));
//                        conveniente = pr.getPrecioUnitario().multiply(new BigDecimal(0.9));
//                        precioBajo = BigDecimal.ZERO;
//                        if (pr.getPropuestasList().size() > 0) {
//
//                            for (Propuestas propuestas : pr.getPropuestasList()) {
//                                precioProp = propuestas.getPrecioUnitarioDescuento();
//                                if (precioProp.doubleValue() != 0) {//valida q el precio no sea 0 ya q es desierta
//                                    tipo = propuestas.getIdProveedor().getIdTipoProveedor().getTipo();
//                                    if (prop != null) {
//                                        tipoAnt = prop.getIdProveedor().getIdTipoProveedor().getTipo();
//                                    }
//                                    if (precioBajo.doubleValue() != 0) {//validamos q el precio mas bajo no sea cero, q ya tenga algún valor guardado
//                                        if (precioProp.doubleValue() < precioBajo.doubleValue()) {//si el precio es menor al precio mas bajo
//                                            if (precioProp.doubleValue() >= conveniente.doubleValue() && precioProp.doubleValue() <= aceptable.doubleValue()) {//el precio este dentro del rango
//                                                if (tipo.equals("N") || tipo.equals("N/I")) {//si es N o N/I tiene preferencia por ser el mas bajo
//                                                    precioBajo = precioProp;
//                                                    prop = propuestas;
//                                                } else {
//                                                    if (tipoAnt.equals("I")) {//Si el anterior es I tiene prioridad el nuevo
//                                                        precioBajo = precioProp;
//                                                        prop = propuestas;
//                                                    }
//                                                }
//                                            }
//                                        } else if (precioBajo.doubleValue() == precioProp.doubleValue()) {//Si son iguales
//                                            if (tipoAnt.equals("I") && (tipo.equals("N") || tipo.equals("N/I"))) {
//                                                precioBajo = precioProp;
//                                                prop = propuestas;
//                                            }
//                                        }
//                                    } else {
//                                        precioBajo = precioProp;
//                                        prop = propuestas;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    mensaje.mensaje("El tipo de compra para el procedimiento ingresado no es tipo ISSSTE por lo tanto no tiene propuestas", "amarillo");
//                }
//            } else {
//                mensaje.mensaje("El nÃºmero de procedimiento no existe", "amarillo");
//            }
//        } else {
//            mensaje.mensaje("Debe escribir el nÃºmero de procedimiento", "amarillo");
//        }
//    }
    public void obtenerResultadoPropuestas() {
        messageGuardar = false;
        messageConsulta = true;
        PropuestasArchivoDTO padto = null;
        //List<PropuestasArchivoDTO> listaPadto = new ArrayList();
        listaPropuestasDTO = new ArrayList();
        if (!numeroProcedimiento.equals("")) {
            procedimientos = procedimientoService.obtenerByNumeroProcedimientoSeguimiento(numeroProcedimiento.toUpperCase());
            if (procedimientos != null) {
                if (procedimientos.getIdEstatus().getIdEstatus() == 32 && procedimientos.getActivo() == 1) {
                    listaProcRcb = procedimientos.getProcedimientoRcbList();//procedimientosRcbService.obtenerByNumeroProc(numeroProcedimiento);
                    System.out.println(listaProcRcb.get(0).getIdProcedimiento().getIdTipoCompra().getNombre());
                    if (listaProcRcb.get(0).getIdProcedimiento().getIdTipoCompra().getNombre().equals("ISSSTE")) {
                        for (ProcedimientoRcb pr : listaProcRcb) {
                            padto = new PropuestasArchivoDTO();
                            padto.setProcedimientoRcb(pr);
                            padto.setPropuestas(null);
                            if (pr.getPropuestasList().size() > 0) {
                                for (Propuestas propuestas : pr.getPropuestasList()) {
                                    if (propuestas.getGanador().intValue() == 1) {
                                        padto.setPropuestas(propuestas);
                                    }
                                }
                                if (padto.getPropuestas() == null) {
                                    padto.setDesierta("Desierta");
                                } else {
                                    padto.setDesierta("Evaluada");
                                }
                            } else {
                                padto.setDesierta("Pendiente de evaluación");
                                //listaPropuestasDTO.add(padto);
                            }
                            listaPropuestasDTO.add(padto);
                        }
                    } else {
                        mensaje.mensaje("El tipo de compra para el procedimiento ingresado no es tipo ISSSTE por lo tanto no tiene propuestas", "amarillo");
                    }
                } else {
                    mensaje.mensaje("El procedimiento se encuentra en estatus Captura", "amarillo");
                }
            } else {
                mensaje.mensaje("El número de procedimiento no existe", "amarillo");
            }
        } else {
            mensaje.mensaje("Debe escribir el número de procedimiento", "amarillo");
        }
    }

    public boolean valida() {
        return false;
    }

    public void validaGuardado() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg1').show();");
    }

    public void limpiar() {
        RequestContext.getCurrentInstance().reset("formProc");
        init();
    }

    public Procedimientos getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(Procedimientos procedimientos) {
        this.procedimientos = procedimientos;
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

    public boolean isMessageGuardar() {
        return messageGuardar;
    }

    public void setMessageGuardar(boolean messageGuardar) {
        this.messageGuardar = messageGuardar;
    }

    public List<ProcedimientoRcb> getListaProcRcb() {
        return listaProcRcb;
    }

    public void setListaProcRcb(List<ProcedimientoRcb> listaProcRcb) {
        this.listaProcRcb = listaProcRcb;
    }

    public List<ProcedimientoRcb> getListaProcRcbFilter() {
        return listaProcRcbFilter;
    }

    public void setListaProcRcbFilter(List<ProcedimientoRcb> listaProcRcbFilter) {
        this.listaProcRcbFilter = listaProcRcbFilter;
    }

    public boolean isMensajeBorrar() {
        return mensajeBorrar;
    }

    public void setMensajeBorrar(boolean mensajeBorrar) {
        this.mensajeBorrar = mensajeBorrar;
    }

    public String getNumeroProcedimiento() {
        return numeroProcedimiento;
    }

    public void setNumeroProcedimiento(String numeroProcedimiento) {
        this.numeroProcedimiento = numeroProcedimiento.toUpperCase();
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isBsansiones() {
        return bsansiones;
    }

    public void setBsansiones(boolean bsansiones) {
        this.bsansiones = bsansiones;
    }

    public boolean isBactivo() {
        return bactivo;
    }

    public void setBactivo(boolean bactivo) {
        this.bactivo = bactivo;
    }

    public boolean isBresultado() {
        return bresultado;
    }

    public void setBresultado(boolean bresultado) {
        this.bresultado = bresultado;
    }

    public String getClavesBien() {
        return clavesBien;
    }

    public void setClavesBien(String clavesBien) {
        this.clavesBien = clavesBien;
    }

    public String getClavesMal() {
        return clavesMal;
    }

    public void setClavesMal(String clavesMal) {
        this.clavesMal = clavesMal;
    }

    public int getNumClavesBien() {
        return numClavesBien;
    }

    public void setNumClavesBien(int numClavesBien) {
        this.numClavesBien = numClavesBien;
    }

    public int getNumClavesMal() {
        return numClavesMal;
    }

    public void setNumClavesMal(int numClavesMal) {
        this.numClavesMal = numClavesMal;
    }

    public List<PropuestasArchivoDTO> getListaPropuestasDTO() {
        return listaPropuestasDTO;
    }

    public void setListaPropuestasDTO(List<PropuestasArchivoDTO> listaPropuestasDTO) {
        this.listaPropuestasDTO = listaPropuestasDTO;
    }

    public List<PropuestasArchivoDTO> getListaPropuestasDTOFilter() {
        return listaPropuestasDTOFilter;
    }

    public void setListaPropuestasDTOFilter(List<PropuestasArchivoDTO> listaPropuestasDTOFilter) {
        this.listaPropuestasDTOFilter = listaPropuestasDTOFilter;
    }

}
