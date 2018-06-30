/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.service.silodisa.DAO.SeguimientoSalidasUmuInternoDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.SeguimientoSalidasUmuInterno;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
public class SeguimientoSalidasUmuInternoDAOImplement implements SeguimientoSalidasUmuInternoDAO{

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(SeguimientoSalidasUmuInterno ssui) {
        try {
            em.persist(ssui);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(SeguimientoSalidasUmuInternoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean actualizar(SeguimientoSalidasUmuInterno ssui) {
        try {
            em.merge(ssui);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(SeguimientoSalidasUmuInternoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<SeguimientoSalidasUmuInterno> seguimientoSalidasUmuInternoByUmu(String clave) {
        List<SeguimientoSalidasUmuInterno> resultList = null;
        try {
            resultList = em.createQuery("Select ssui From SeguimientoSalidasUmuInterno ssui "
                    + "Where ssui.clave = '" + clave + "' ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(SeguimientoSalidasUmuInternoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }
    
    @Override
    public boolean eliminarExistencias() {
        try {
            Query q1 = em.createNativeQuery("DELETE FROM seguimiento_salidas_umu_interno");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(SeguimientoSalidasUmuInternoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }
}
