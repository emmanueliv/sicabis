/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import java.util.List;
import javax.ejb.Local;

@Local
public interface TipoDocumentosDAO {
    
    List<TipoDocumentos> obtenerTipoDocumentos();
    
    List<TipoDocumentos> obtenerByIdTarea(Integer idTarea);
    
    List<TipoDocumentos> obtenerTipoDocumentosByNombre(String nombre);
    
    void guardarTipoDocumento(TipoDocumentos tipoDocumento);
    
}
