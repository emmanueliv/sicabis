/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoContrato;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kriosoft
 */
@Stateless
public class TipoContratoDAOImplement implements TipoContratoDAO {

        @PersistenceContext(unitName = "sicabis")
    private EntityManager em;
        
    @Override
    public List<TipoContrato> getTiposContrato() {
        List<TipoContrato> resultList = null;
        try {
            resultList = em.createNamedQuery("TipoContrato.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(TipoContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<TipoContrato> obtenerTipoContratoByNombre(String nombre) {
        return  em.createQuery("SELECT tc FROM TipoContrato tc WHERE tc.fechaBaja is null and tc.descripcion = :descripcion").setParameter("descripcion", nombre).getResultList();
    }

    @Override
    public void guardarTipoContrato(TipoContrato tipoContrato) {
        try {
            if (tipoContrato.getIdTipoContrato() == null) {
                em.persist(tipoContrato);
            } else {
                em.merge(tipoContrato);
            }
        } catch (Exception e) {
            Logger.getLogger(TipoContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }
    
}
