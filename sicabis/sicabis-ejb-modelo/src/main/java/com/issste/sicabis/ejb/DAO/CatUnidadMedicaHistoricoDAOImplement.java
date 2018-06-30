/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.CatUnidadMedicaHistorico;
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
public class CatUnidadMedicaHistoricoDAOImplement implements CatUnidadMedicaHistoricoDAO {
   
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(CatUnidadMedicaHistorico cumh) {
        try {
            em.persist(cumh);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(CatUnidadMedicaHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<CatUnidadMedicaHistorico> getByFechaIngreso(Date fechaIngreso) {
        List<CatUnidadMedicaHistorico> resultList = null;
        try {
            resultList = em.createQuery("SELECT cumh FROM CatUnidadMedicaHistorico cumh WHERE cumh.fechaIngreso = :fechaIngreso").setParameter("fechaIngreso", fechaIngreso).getResultList();
        } catch (Exception e) {
            Logger.getLogger(CatUnidadMedicaHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    } 
}
