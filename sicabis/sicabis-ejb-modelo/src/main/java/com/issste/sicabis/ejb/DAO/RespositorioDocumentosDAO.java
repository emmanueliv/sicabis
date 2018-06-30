/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RespositorioDocumentosDAO {
    
    boolean guardaProcedimiento(RespositorioDocumentos respositorioDocumentos);
    boolean actualizaProcedimiento(RespositorioDocumentos respositorioDocumentos);
    public boolean borrarByIdRespositorioDocumento(Integer idRespositorioDocumento);
    public List<RespositorioDocumentos> obtenerByIdProcesoIdTarea(Integer idProceso, Integer idTarea);
    public List<RespositorioDocumentos> getByOrden(Integer idDetalle);
    
}
