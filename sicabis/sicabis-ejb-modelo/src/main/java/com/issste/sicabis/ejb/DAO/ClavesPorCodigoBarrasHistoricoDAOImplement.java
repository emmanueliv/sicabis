/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.AlertasOperativasHistorico;
import com.issste.sicabis.ejb.modelo.ClavesPorCodigoBarrasHistorico;
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
public class ClavesPorCodigoBarrasHistoricoDAOImplement implements ClavesPorCodigoBarrasHistoricoDAO {
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(ClavesPorCodigoBarrasHistorico ccbh) {
        try {
            em.persist(ccbh);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ClavesPorCodigoBarrasHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<ClavesPorCodigoBarrasHistorico> getByFechaIngreso(Date fechaIngreso) {
        List<ClavesPorCodigoBarrasHistorico> resultList = null;
        try {
            resultList = em.createQuery("SELECT ccbh FROM ClavesPorCodigoBarrasHistorico ccbh WHERE ccbh.fechaIngreso = :fechaIngreso").setParameter("fechaIngreso", fechaIngreso).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ClavesPorCodigoBarrasHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
}
