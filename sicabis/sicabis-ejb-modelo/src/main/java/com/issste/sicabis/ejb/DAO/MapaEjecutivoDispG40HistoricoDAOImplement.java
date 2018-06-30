/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.MapaEjecutivoDispG40Historico;
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
public class MapaEjecutivoDispG40HistoricoDAOImplement implements MapaEjecutivoDispG40HistoricoDAO {
       @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(MapaEjecutivoDispG40Historico medh) {
        try {
            em.persist(medh);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(MapaEjecutivoDispG40HistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<MapaEjecutivoDispG40Historico> getByFechaIngreso(Date fechaIngreso) {
        List<MapaEjecutivoDispG40Historico> resultList = null;
        try {
            resultList = em.createQuery("SELECT medh FROM MapaEjecutivoDispG40Historico medh WHERE medh.fechaIngreso = :fechaIngreso").setParameter("fechaIngreso", fechaIngreso).getResultList();
        } catch (Exception e) {
            Logger.getLogger(MapaEjecutivoDispG40HistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    } 
}
