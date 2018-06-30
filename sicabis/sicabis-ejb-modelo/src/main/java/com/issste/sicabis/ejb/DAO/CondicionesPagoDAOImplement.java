/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.CondicionesPago;
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
public class CondicionesPagoDAOImplement implements CondicionesPagoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<CondicionesPago> obtenerCondicionesPago() {
        List<CondicionesPago> resultList = null;
        try {
            resultList = em.createNamedQuery("CondicionesPago.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(CondicionesPagoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<CondicionesPago> obtenerCondicionesByDesc(String desc) {
        List<CondicionesPago> resultList = null;
        try {
            resultList = em.createNamedQuery("CondicionesPago.findByDescripcion").
                    setParameter("descripcion", desc).getResultList();
        } catch (Exception e) {
            Logger.getLogger(CondicionesPagoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public void guardarCondicionPago(CondicionesPago condicion) {
        try {
            if (condicion.getIdCondicion() == null) {
                em.persist(condicion);
            } else {
                em.merge(condicion);
            }
        } catch (Exception e) {
            Logger.getLogger(CondicionesPagoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

}
