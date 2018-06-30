/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.DTO;

import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import org.primefaces.model.StreamedContent;

public class RepositorioDocumentosDTO {
    
    private Integer idRespositorioDocumento;
    private String nombre;
    private String nombreArchivo;
    private TipoDocumentos idTipoDocumento;
    private StreamedContent file;

    public Integer getIdRespositorioDocumento() {
        return idRespositorioDocumento;
    }

    public void setIdRespositorioDocumento(Integer idRespositorioDocumento) {
        this.idRespositorioDocumento = idRespositorioDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public TipoDocumentos getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(TipoDocumentos idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
    
    
}
