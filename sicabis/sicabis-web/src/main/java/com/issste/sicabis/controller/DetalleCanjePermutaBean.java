/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.CanjeService;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.CanjePermuta;
import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
@ManagedBean(name = "detalleCanjePermutaBean")
@ViewScoped
public class DetalleCanjePermutaBean {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;
    @EJB
    private CanjeService canjeService;
    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;

    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();
    private RepositorioDocumentosDTO repositorioDocumentosDTOAux;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private List<RespositorioDocumentos> listaRepoDocs;
    private RepositorioDocumentosDTO repositorioDocumentosDTO;
    private List<CanjePermuta> canjeList;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private String id;
    private Integer canje;
    private int b;
    private String operacion;

    public DetalleCanjePermutaBean() {
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCanje");
    }

    @PostConstruct
    public void init() {
        consulta();
    }

    public void consulta() {
        System.out.println("id");
        canje = Integer.parseInt(String.valueOf(id));
        canjeList = canjeService.canjePermutaById(canje);
        for (CanjePermuta cp : canjeList) {
            operacion = cp.getTipoCanje();
        }
        b = 14;
        buscarArchivosByIdProcesoIdTarea(canje, b);
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

    public CanjeService getCanjeService() {
        return canjeService;
    }

    public void setCanjeService(CanjeService canjeService) {
        this.canjeService = canjeService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCanje() {
        return canje;
    }

    public void setCanje(Integer canje) {
        this.canje = canje;
    }

    public List<CanjePermuta> getCanjeList() {
        return canjeList;
    }

    public void setCanjeList(List<CanjePermuta> canjeList) {
        this.canjeList = canjeList;
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

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

}
