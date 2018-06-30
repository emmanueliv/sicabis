package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.OrdenSuministroDTO;
import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.OrdenSuministroService;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.ln.TipoDocumentosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.ArchivosUtilidades;
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
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

public class DetalleOrdenSuministroBean {

    @EJB
    private TipoDocumentosService tipoDocumentosService;

    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;

    @EJB
    private OrdenSuministroService ordenSuministroService;

    @EJB
    private EstatusService estatusService;

    private Usuarios usuarios;
    private OrdenSuministro ordenSuministro;
    private Proveedores proveedores;
    private DetalleOrdenSuministro detalleOrdenSuministro;

    private RespositorioDocumentos respositorioDocumentos;
    private RepositorioDocumentosDTO repositorioDocumentosDTO;
    private RepositorioDocumentosDTO repositorioDocumentosDTOAux;

    private boolean botonGuardar = true;
    private boolean mensajeBorrar;
    private boolean messageGuardar = true;
    private String numeroContrato;
    private boolean bpreorden;
    private String lugarEntrega;
    private Integer idEstatus;
    private Date fechaInicialContrato;
    private Date fechaFinalContrato;

    private List<ContratoFalloProcedimientoRcb> listaConFalloProcRcb;
    private List<DetalleOrdenSuministro> listaDetalleOrden;
    private List<DetalleOrdenSuministro> listaDetalleOrdenSelect;
    private List<DetalleOrdenSuministro> listaDetalleOrdenFilter;
    private List<Contratos> listaContratos;
    private List<Estatus> listaEstatus;
    private List<FalloProcedimientoRcb> listaFalloProcRcb;
    private List<OrdenSuministro> listaOrdenSuministro;
    private String numeroOrden;
    private boolean bedita;
    private List<OrdenSuministroDTO> ordenDetalleList;
    JasperPrint jasperPrint;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Integer idTipoDocumento;
    private List<TipoDocumentos> listaTipoDocs;
    private List<RespositorioDocumentos> listaRepoDocs;
    private StreamedContent file;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private UploadedFile uploadedfile;
    private boolean barchivos;

    private final Integer idTareaProc = 7;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();

    public DetalleOrdenSuministroBean() {
        ordenDetalleList = new ArrayList<>();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        bpreorden = Integer.parseInt(util.getContextAtributte("opcionOrden").toString()) == 1 ? true : false;
        listaFalloProcRcb = new ArrayList();
        listaDetalleOrden = new ArrayList();
        ordenSuministro = (OrdenSuministro) util.getContextAtributte("ordenSuministro");
        this.obtieneDetalleOrden();
        listaEstatus = estatusService.getEstatusByTarea(idTareaProc);
        if (bpreorden) {
            if (idEstatus.intValue() == 72) {
                bedita = true;
                idEstatus = 71;
            }
        } else {
            if (idEstatus.intValue() == 71) {
                bedita = true;
            } else if (idEstatus.intValue() == 73 || idEstatus.intValue() == 74) {
                bpreorden = true;
            }
        }
        listaTipoDocs = tipoDocumentosService.obtenerByIdTarea(idTareaProc);
    }

    public void obtieneDetalleOrden() {
        numeroOrden = ordenSuministro.getNumeroOrden();
        idEstatus = ordenSuministro.getIdEstatus().getIdEstatus();
        Contratos contratos = ordenSuministro.getIdContrato();
        numeroContrato = contratos.getNumeroContrato();
        lugarEntrega = contratos.getIdDestino().getNombre() + ", " + contratos.getIdDestino().getDomicilio();
        fechaInicialContrato = new Date();
        fechaFinalContrato = contratos.getVigenciaFinal();
        listaConFalloProcRcb = contratos.getIdContratoFalloProcedimientoRcbList();
        if (listaConFalloProcRcb.size() > 0) {
            proveedores = listaConFalloProcRcb.get(0).getIdFalloProcedimientoRcb().getIdProveedor();
        }
        listaDetalleOrden = ordenSuministro.getDetalleOrdenSuministroList();
        List<DetalleOrdenSuministro> listaDetalleOrdenAux = new ArrayList();
        for (DetalleOrdenSuministro dos : listaDetalleOrden) {
            Integer total = 0;
            Integer suministrado = 0;
            suministrado = detalleOrdenSuministroService.obtenerByIdFalloProcRcb(dos.getIdFalloProcedimientoRcb().getIdFalloProcedimientoRcb());
            dos.setCantidadSuministrada(suministrado);
            if (suministrado != -1) {
                Integer canMof = 0;
                Integer canCanAgr = 0;
                if (dos.getIdFalloProcedimientoRcb().getCantidadModificada() != null) {
                    canMof = dos.getIdFalloProcedimientoRcb().getCantidadModificada().intValue();
                }
                if (dos.getIdFalloProcedimientoRcb().getCantidadAgregadaConvenio() != null) {
                    canCanAgr = dos.getIdFalloProcedimientoRcb().getCantidadAgregadaConvenio().intValue();
                }
                total = canMof + canCanAgr;
                total = total.intValue() - suministrado.intValue();
                dos.setTotalCancelado(total);
                //dos.setCantidadSuministrar(total);
            }
            listaDetalleOrdenAux.add(dos);
        }
        listaDetalleOrden.clear();
        listaDetalleOrden = listaDetalleOrdenAux;
    }

    public void existeNumeroOrden() {
        if (!numeroOrden.equals(ordenSuministro.getNumeroOrden())) {
            boolean bandera = this.validaNumeroOrden();
        }
    }

    public boolean validaNumeroOrden() {
        boolean bandera = true;
        List<OrdenSuministro> cAux = ordenSuministroService.obtenerByNumeroOrden(ordenSuministro.getNumeroOrden());
        if (cAux.size() != 0) {
            mensaje.mensaje("El número de orden ya esta registrada", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean valida() {
        boolean bandera = true;
        if (ordenSuministro.getNumeroOrden().equals("")) {
            mensaje.mensaje("Debes ingresar el número de la orden", "amarillo");
            bandera = false;
        } else {
            if (!numeroOrden.equals(ordenSuministro.getNumeroOrden())) {
                bandera = this.validaNumeroOrden();
            }
        }
        if (!bpreorden) {
            if (idEstatus.intValue() == -1) {
                mensaje.mensaje("Debes seleccionar un estatus", "amarillo");
                bandera = false;
            }
        }
        return bandera;
    }

    public void validaGuardado() {
        if (valida()) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlg1').show();");
        }
    }

    public void guardaOrden() {
        ordenSuministro.setIdEstatus(new Estatus(idEstatus));
        ordenSuministro.setFechaModificacion(new Date());
        ordenSuministro.setUsuarioModificacion(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
        List<DetalleOrdenSuministro> listaDetalleOrdenAux = new ArrayList();
        for (DetalleOrdenSuministro dos : listaDetalleOrden) {
            dos.setTotalCancelado(0);
            dos.setFechaModificacion(new Date());
            dos.setUsuarioModificacion(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
            dos.setIdOrdenSuministro(ordenSuministro);
            listaDetalleOrdenAux.add(dos);
        }
        ordenSuministro.setDetalleOrdenSuministroList(listaDetalleOrdenAux);
        if (ordenSuministroService.actualizaOrdenSuministro(ordenSuministro)) {
            bitacoraTareaEstatus.setDescripcion("Actualiza orden:" + ordenSuministro.getNumeroOrden() + "");
            bitacoraTareaEstatus.setFecha(new Date());
            bitacoraTareaEstatus.setIdModulos(1);
            bitacoraTareaEstatus.setIdEstatus(idEstatus);
            bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
            bitacoraTareaEstatus.setIdTareaId(7);
            bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
            mensaje.mensaje(mensaje.datos_actualizados, "verde");
            this.recargaPantalla();
        } else {
            mensaje.mensaje(mensaje.error_guardar, "rojo");
        }
    }

    public void recargaPantalla() {
        util.destroyContextMap("ordenSuministro");
        util.setContextAtributte("ordenSuministro", this.ordenSuministro);
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect("detalleOrdenSuministro.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DetalleContratoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void validaCampos(CellEditEvent event) {
        DataTable s = (DataTable) event.getSource();
        DetalleOrdenSuministro dos = (DetalleOrdenSuministro) s.getRowData();
        dos.setImporte(util.multiplica(dos.getIdFalloProcedimientoRcb().getPrecioUnitario(), dos.getCantidadSuministrar()));
        if (dos.getFechaEntregaInicial().after(dos.getFechaEntregaFinal())) {
            dos.setFechaEntregaInicial(new Date());
            dos.setFechaEntregaFinal(new Date());
            mensaje.mensaje("La fecha inicial es mayor a la fecha final", "amarillo");
        }
    }

    public void limpiar() {
        RequestContext.getCurrentInstance().reset("formOrden");
        init();
    }

    public void descargarOrdenSuministro() throws JRException, IOException {
        List<DetalleOrdenSuministro> ordenSList = detalleOrdenSuministroService.ordenById(ordenSuministro.getIdOrdenSuministro());
        String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ISSSTE.png");
        for (DetalleOrdenSuministro os : ordenSList) {
            OrdenSuministroDTO od = new OrdenSuministroDTO();
            od.setClave(os.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave());
            od.setDescripcion(os.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getDescripcion());
            od.setCorreoElectronico(os.getIdFalloProcedimientoRcb().getIdProveedor().getCorreo());
            od.setDomicilio(os.getIdFalloProcedimientoRcb().getIdProveedor().getDireccion());
            od.setDomicilioEntrega(os.getIdOrdenSuministro().getIdContrato().getIdUnidadesMedicas().getNombre());
            od.setFax(os.getIdFalloProcedimientoRcb().getIdProveedor().getCorreo());
            od.setFechaEntrega(os.getFechaEntregaInicial());
            od.setFechaEntregaFinal(os.getFechaEntregaFinal());
            od.setFechaOrden(os.getIdOrdenSuministro().getFechaOrden());
            od.setNombreProveedor(os.getIdFalloProcedimientoRcb().getIdProveedor().getNombreProveedor());
            od.setNumeroContrato(os.getIdOrdenSuministro().getIdContrato().getNumeroContrato());
            od.setNumeroOrden(os.getIdOrdenSuministro().getNumeroOrden());
            od.setProcedimiento(os.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdProcedimiento().getNumeroProcedimiento());
            od.setRfc(os.getIdFalloProcedimientoRcb().getIdProveedor().getRfc());
            od.setRenglon(os.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdInsumo());
            od.setCantidad(os.getCantidadSuministrar());
            od.setImporte(os.getIdFalloProcedimientoRcb().getPrecioUnitario().multiply(new BigDecimal(os.getCantidadSuministrar())));
            od.setPrecio(os.getIdFalloProcedimientoRcb().getPrecioUnitario());
            od.setUnidad(os.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdUnidadPieza().getDescripcion());
            od.setRutaLogo(logoPath);
            ordenDetalleList.add(od);
            imprimirOrden();
        }
    }

    public void imprimirOrden() throws JRException, IOException {
        System.out.println("entro imprimir");
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(ordenDetalleList);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/orden.jasper");
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

    public void abreArchivos(DetalleOrdenSuministro dos) {
        detalleOrdenSuministro = dos;
        if (ordenSuministro.getIdEstatus().getIdEstatus().intValue() != 76) {
            barchivos = true;
        }
        if (dos.getIdRepositorioDocumentos() != null) {
            buscarArchivosByIdProcesoIdTarea(dos.getIdRepositorioDocumentos());
        }
    }

    public void cambiaTipoDoc() {
        System.out.println("idTipoDocumento--->" + idTipoDocumento);
    }

    public void buscarArchivosByIdProcesoIdTarea(Integer idRespositorioDocumento) {
        listaRepoDocs = respositorioDocumentosService.getByOrden(idRespositorioDocumento);
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
        barchivos = false;
    }

    public void guardarArchivos(FileUploadEvent event) {
        uploadedfile = event.getFile();
        boolean bandera = false;
        if (idTipoDocumento != -1) {
            respositorioDocumentos = new RespositorioDocumentos();
            respositorioDocumentos.setActivo(1);
            respositorioDocumentos.setFechaAlta(new Date());
            respositorioDocumentos.setIdProceso(detalleOrdenSuministro.getIdDetalleOrdenSuministro());
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
            buscarArchivosByIdProcesoIdTarea(detalleOrdenSuministro.getIdRepositorioDocumentos());
            this.actualizaDetalleOrden(respositorioDocumentos.getIdRespositorioDocumento());
        }
    }

    public void validaBorrarArchivo(RepositorioDocumentosDTO repositorioDocumentosDTO) {
        repositorioDocumentosDTOAux = repositorioDocumentosDTO;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg3').show();");
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
        this.actualizaDetalleOrden(null);
    }

    public void actualizaDetalleOrden(Integer idRespositorioDocumento) {
        detalleOrdenSuministro.setIdRepositorioDocumentos(idRespositorioDocumento);
        detalleOrdenSuministroService.actualizar(detalleOrdenSuministro);
    }

    public OrdenSuministro getOrdenSuministro() {
        return ordenSuministro;
    }

    public void setOrdenSuministro(OrdenSuministro ordenSuministro) {
        this.ordenSuministro = ordenSuministro;
    }

    public Proveedores getProveedores() {
        return proveedores;
    }

    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
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

    public boolean isMessageGuardar() {
        return messageGuardar;
    }

    public void setMessageGuardar(boolean messageGuardar) {
        this.messageGuardar = messageGuardar;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public boolean isBpreorden() {
        return bpreorden;
    }

    public void setBpreorden(boolean bpreorden) {
        this.bpreorden = bpreorden;
    }

    public String getLugarEntrega() {
        return lugarEntrega;
    }

    public void setLugarEntrega(String lugarEntrega) {
        this.lugarEntrega = lugarEntrega;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Date getFechaInicialContrato() {
        return fechaInicialContrato;
    }

    public void setFechaInicialContrato(Date fechaInicialContrato) {
        this.fechaInicialContrato = fechaInicialContrato;
    }

    public Date getFechaFinalContrato() {
        return fechaFinalContrato;
    }

    public void setFechaFinalContrato(Date fechaFinalContrato) {
        this.fechaFinalContrato = fechaFinalContrato;
    }

    public List<DetalleOrdenSuministro> getListaDetalleOrden() {
        return listaDetalleOrden;
    }

    public void setListaDetalleOrden(List<DetalleOrdenSuministro> listaDetalleOrden) {
        this.listaDetalleOrden = listaDetalleOrden;
    }

    public List<DetalleOrdenSuministro> getListaDetalleOrdenSelect() {
        return listaDetalleOrdenSelect;
    }

    public void setListaDetalleOrdenSelect(List<DetalleOrdenSuministro> listaDetalleOrdenSelect) {
        this.listaDetalleOrdenSelect = listaDetalleOrdenSelect;
    }

    public List<DetalleOrdenSuministro> getListaDetalleOrdenFilter() {
        return listaDetalleOrdenFilter;
    }

    public void setListaDetalleOrdenFilter(List<DetalleOrdenSuministro> listaDetalleOrdenFilter) {
        this.listaDetalleOrdenFilter = listaDetalleOrdenFilter;
    }

    public List<Estatus> getListaEstatus() {
        return listaEstatus;
    }

    public void setListaEstatus(List<Estatus> listaEstatus) {
        this.listaEstatus = listaEstatus;
    }

    public List<OrdenSuministro> getListaOrdenSuministro() {
        return listaOrdenSuministro;
    }

    public void setListaOrdenSuministro(List<OrdenSuministro> listaOrdenSuministro) {
        this.listaOrdenSuministro = listaOrdenSuministro;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public boolean isBedita() {
        return bedita;
    }

    public void setBedita(boolean bedita) {
        this.bedita = bedita;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
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

}
