/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.service.silodisa.DAO.SeguimientoSalidasUmuTransitoDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.SeguimientoSalidasUmuTransito;
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
public class SeguimientoSalidasUmuTransitoDAOImplement implements SeguimientoSalidasUmuTransitoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(SeguimientoSalidasUmuTransito ssut) {
        try {
            em.persist(ssut);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(SeguimientoSalidasUmuTransitoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean actualizar(SeguimientoSalidasUmuTransito ssut) {
        try {
            em.merge(ssut);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(SeguimientoSalidasUmuTransitoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<SeguimientoSalidasUmuTransito> seguimientoSalidasUmuTransitoByClave(String clave) {
        List<SeguimientoSalidasUmuTransito> resultList = null;
        try {
            resultList = em.createQuery("Select ssut From SeguimientoSalidasUmuTransito ssut "
                    + "Where ssui.clave = '" + clave + "' ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(SeguimientoSalidasUmuTransitoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
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
            Query q1 = em.createNativeQuery("DELETE FROM seguimiento_salidas_umu_transito");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(SeguimientoSalidasUmuTransitoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }
}
