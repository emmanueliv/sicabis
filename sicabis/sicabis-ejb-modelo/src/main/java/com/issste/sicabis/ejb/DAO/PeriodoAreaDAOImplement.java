/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Almacen;
import com.issste.sicabis.ejb.modelo.PeriodoArea;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Toshiba Manolo
 */
@Stateless
public class PeriodoAreaDAOImplement implements PeriodoAreaDAO{
    
     @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<PeriodoArea> obtenerPeriodos() {
        List<PeriodoArea> resultList = null;
        try {
            resultList = em.createNamedQuery("PeriodoArea.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(PeriodoAreaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<PeriodoArea> obtenerPeriodosPorArea(Integer idArea) {
                List<PeriodoArea> resultList = null;
        try {
            resultList = em.createQuery("SELECT p FROM PeriodoArea p WHERE p.idArea.idArea = :idArea")
                    .setParameter("idArea", idArea)
                    .getResultList();
        } catch (Exception e) {
            Logger.getLogger(PeriodoAreaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }
    
}
