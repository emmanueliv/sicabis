/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TipoDocumentosDAOImplement implements TipoDocumentosDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<TipoDocumentos> obtenerTipoDocumentos() {
        return (List<TipoDocumentos>) em.createNamedQuery("TipoDocumentos.findAll").getResultList();
    }

    @Override
    public List<TipoDocumentos> obtenerTipoDocumentosByNombre(String nombre) {
        return (List<TipoDocumentos>) em.createNamedQuery("TipoDocumentos.findByNombre").setParameter("nombre", nombre).getResultList();
    }
    
    @Override
    public List<TipoDocumentos> obtenerByIdTarea(Integer idTarea) {
        return (List<TipoDocumentos>) em.createNamedQuery("TipoDocumentos.findByIdTarea").setParameter("idTarea", idTarea).getResultList();
    }

    @Override
    public void guardarTipoDocumento(TipoDocumentos tipoDocumento) {
        try {
            if (tipoDocumento.getIdTipoDocumento() == null) {
                em.persist(tipoDocumento);
            } else {
                em.merge(tipoDocumento);
            }
        } catch (Exception e) {
            Logger.getLogger(TipoDocumentosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

}
