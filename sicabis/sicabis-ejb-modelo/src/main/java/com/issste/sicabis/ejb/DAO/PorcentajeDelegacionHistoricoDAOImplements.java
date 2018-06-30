/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.PorcentajeDelegacionHistorico;
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
public class PorcentajeDelegacionHistoricoDAOImplements  implements PorcentajeDelegacionHistoricoDAO{

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(PorcentajeDelegacionHistorico pdh) {
        try {
            em.persist(pdh);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(PorcentajeDelegacionHistoricoDAOImplements.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<PorcentajeDelegacionHistorico> getByFechaIngreso(Date fecha_actualizacion) {
        List<PorcentajeDelegacionHistorico> resultList = null;
        try {
            resultList = em.createQuery("SELECT pdh FROM PorcentajeDelegacionHistorico pdh WHERE pdh.fechaActualizacion = :fechaActualizacion").setParameter("fechaActualizacion", fecha_actualizacion).getResultList();
        } catch (Exception e) {
            Logger.getLogger(PorcentajeDelegacionHistoricoDAOImplements.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
}
