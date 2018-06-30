/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoCompra;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author erik
 */
@Stateless
public class TipoCompraDAOImplement implements TipoCompraDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<TipoCompra> obtenerTiposCompra() {
        List<TipoCompra> resultList = null;
        try {
            resultList = em.createQuery("SELECT t FROM TipoCompra t WHERE t.activo = 1").getResultList();
        } catch (Exception e) {
            Logger.getLogger(TipoCompraDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public void guardarTipoConvenio(TipoCompra tipoCompra) {
        try {
            if (tipoCompra.getIdTipoCompra() == null) {
                em.persist(tipoCompra);
            } else {
                em.merge(tipoCompra);
            }
        } catch (Exception e) {
            Logger.getLogger(TipoCompraDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<TipoCompra> obtenerTipoCompraByNom(String nombreTipoCompra) {
        List<TipoCompra> resultList = null;
        try {
            resultList = em.createQuery("SELECT t FROM TipoCompra t WHERE t.activo = 1 AND t.nombre = :nombre").setParameter("nombre", nombreTipoCompra).getResultList();
        } catch (Exception e) {
            Logger.getLogger(TipoCompraDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

}
