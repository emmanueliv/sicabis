/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.TipoDocumentosDAO;
import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class TipoDocumentosService {

    @EJB
    private TipoDocumentosDAO tipoDocumentosDAOImplement;

    public List<TipoDocumentos> obtenerTipoDocumentos() {
        return tipoDocumentosDAOImplement.obtenerTipoDocumentos();
    }
    
    public List<TipoDocumentos> obtenerByIdTarea(Integer idTarea) {
        return tipoDocumentosDAOImplement.obtenerByIdTarea(idTarea);
    }

    public void guardarTipoDocumento(TipoDocumentos tipoDocumento) {
        tipoDocumentosDAOImplement.guardarTipoDocumento(tipoDocumento);
    }
    
    public TipoDocumentos obtenerTipoDocumentosByNombre(String nombre){
        List<TipoDocumentos>  lista = tipoDocumentosDAOImplement.obtenerTipoDocumentosByNombre(nombre);
        if(lista.isEmpty()){
            return null;
        } else {
            return lista.get(0);
        }
    }
}
