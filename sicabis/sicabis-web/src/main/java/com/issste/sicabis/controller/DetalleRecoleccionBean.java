/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.RecoleccionDTO;
import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
import com.issste.sicabis.ejb.ln.RecoleccionService;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.modelo.DetalleRecoleccion;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Recoleccion;
import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.utils.ArchivosUtilidades;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author fabianvr
 */
@ManagedBean(name = "detalleRecoleccionBean")
@ViewScoped
public class DetalleRecoleccionBean {

    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;

    @EJB
    private RecoleccionService recoleccionService;
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();
    private String oficioRecoleccion;
    private List<Recoleccion> recoList;
    private RecoleccionDTO recoleccionDTO;
    private String nombreUnidad;
    private Integer numeroUnidad;
    private Integer idRecoleccion;
    private String id;
    private String clave;
    private String descripcion;
    private Date fechaCaducidad;
    private Integer cantidad;
    private String lote;
    private BigDecimal precioPromedio;
    private Date fechaAlta;
    private String folioRecoleccion;
    private List<RecoleccionDTO> busquedaList;
    private RepositorioDocumentosDTO repositorioDocumentosDTOAux;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private List<RespositorioDocumentos> listaRepoDocs;
    private RepositorioDocumentosDTO repositorioDocumentosDTO;
    private Integer recoleccion;
    private int b;
    private Integer idClave;

    public DetalleRecoleccionBean() {
        id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idRecoleccion");
        busquedaList = new ArrayList();
    }

    @PostConstruct
    public void init() {
        buscarRecoleccion();
    }

    public void buscarRecoleccion() {
        System.out.println("id" + id);
        recoleccion = Integer.parseInt(String.valueOf(id));
        recoList = recoleccionService.recoleccioById(recoleccion);

        for (Recoleccion r : recoList) {
            cantidad = 0;
            for (DetalleRecoleccion i : r.getDetalleRecoleccionList()) {
                recoleccionDTO = new RecoleccionDTO();
                recoleccionDTO.setNombreUnidad(r.getIdUnidadesMedicas().getNombre());
                nombreUnidad = r.getIdUnidadesMedicas().getNombre();
                numeroUnidad = r.getIdUnidadesMedicas().getIdUnidadesMedicas();
                idRecoleccion = r.getIdRecoleccion();
                recoleccionDTO.setClave(i.getIdInsumo().getClave());
                clave = i.getIdInsumo().getClave();
                recoleccionDTO.setDescripcion(i.getIdInsumo().getDescripcion());
                descripcion = i.getIdInsumo().getDescripcion();
                idClave = i.getIdInsumo().getIdInsumo();
                recoleccionDTO.setCaducidad(i.getFechaCaducidad());
                fechaCaducidad = i.getFechaCaducidad();
                recoleccionDTO.setCantidad(i.getCantidad());
                recoleccionDTO.setPrecio(i.getPrecioPromedio());
                cantidad += i.getCantidad();
                lote = i.getLote();
                precioPromedio = i.getPrecioPromedio();
                recoleccionDTO.setFechaAlta(r.getFechaAlta());
                fechaAlta = r.getFechaAlta();
                oficioRecoleccion = r.getOficioRecoleccion();
                folioRecoleccion = r.getFolioRecoleccion();
                busquedaList.add(recoleccionDTO);
            }
        }
        b = 13;
        buscarArchivosByIdProcesoIdTarea(recoleccion, b);
    }

    public void buscarArchivosByIdProcesoIdTarea(Integer recoleccion, int b) {
        listaRepoDocs = respositorioDocumentosService.obtenerByIdProcesoIdTarea(recoleccion, b);
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

    public String getOficioRecoleccion() {
        return oficioRecoleccion;
    }

    public void setOficioRecoleccion(String oficioRecoleccion) {
        this.oficioRecoleccion = oficioRecoleccion;
    }

    public List<Recoleccion> getRecoList() {
        return recoList;
    }

    public void setRecoList(List<Recoleccion> recoList) {
        this.recoList = recoList;
    }

    public RecoleccionDTO getRecoleccionDTO() {
        return recoleccionDTO;
    }

    public void setRecoleccionDTO(RecoleccionDTO recoleccionDTO) {
        this.recoleccionDTO = recoleccionDTO;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public Integer getNumeroUnidad() {
        return numeroUnidad;
    }

    public void setNumeroUnidad(Integer numeroUnidad) {
        this.numeroUnidad = numeroUnidad;
    }

    public Integer getIdRecoleccion() {
        return idRecoleccion;
    }

    public void setIdRecoleccion(Integer idRecoleccion) {
        this.idRecoleccion = idRecoleccion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
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

    public void setLote(String lote) {
        this.lote = lote;
    }

    public BigDecimal getPrecioPromedio() {
        return precioPromedio;
    }

    public void setPrecioPromedio(BigDecimal precioPromedio) {
        this.precioPromedio = precioPromedio;
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

    public List<RecoleccionDTO> getBusquedaList() {
        return busquedaList;
    }

    public void setBusquedaList(List<RecoleccionDTO> busquedaList) {
        this.busquedaList = busquedaList;
    }

    public List<RepositorioDocumentosDTO> getListaRepoDocsDto() {
        return listaRepoDocsDto;
    }

    public void setListaRepoDocsDto(List<RepositorioDocumentosDTO> listaRepoDocsDto) {
        this.listaRepoDocsDto = listaRepoDocsDto;
    }

    public List<RespositorioDocumentos> getListaRepoDocs() {
        return listaRepoDocs;
    }

    public void setListaRepoDocs(List<RespositorioDocumentos> listaRepoDocs) {
        this.listaRepoDocs = listaRepoDocs;
    }

}
