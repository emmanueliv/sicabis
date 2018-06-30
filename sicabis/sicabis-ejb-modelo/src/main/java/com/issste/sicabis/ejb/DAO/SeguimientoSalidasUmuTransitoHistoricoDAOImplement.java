/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.SeguimientoSalidasUmuTransitoHistorico;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
public class SeguimientoSalidasUmuTransitoHistoricoDAOImplement implements SeguimientoSalidasUmuTransitoHistoricoDAO {
     @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(SeguimientoSalidasUmuTransitoHistorico sdugth) {
        try {
            em.persist(sdugth);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(SeguimientoSalidasUmuTransitoHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<SeguimientoSalidasUmuTransitoHistorico> getByFechaIngreso(Date fechaIngreso) {
        List<SeguimientoSalidasUmuTransitoHistorico> resultList = null;
        try {
            resultList = em.createQuery("SELECT sdugth FROM SeguimientoSalidasUmuTransitoHistorico sdugth WHERE sdugth.fechaIngreso = :fechaIngreso").setParameter("fechaIngreso", fechaIngreso).getResultList();
        } catch (Exception e) {
            Logger.getLogger(SeguimientoSalidasUmuTransitoHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
    
}
