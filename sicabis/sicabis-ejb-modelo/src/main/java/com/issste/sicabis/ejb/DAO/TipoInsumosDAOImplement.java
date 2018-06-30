/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoInsumos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author
 */
@Stateless
public class TipoInsumosDAOImplement implements TipoInsumosDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<TipoInsumos> listTipoInsumos() {
        return (List<TipoInsumos>) em.createNamedQuery("TipoInsumos.findAll").getResultList();
        
    }
}
