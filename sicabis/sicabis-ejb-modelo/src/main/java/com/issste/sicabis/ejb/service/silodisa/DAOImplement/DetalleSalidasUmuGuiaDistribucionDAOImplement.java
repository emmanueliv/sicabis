/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.service.silodisa.DAO.DetalleSalidasUmuGuiaDistribucionDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.DetalleSalidasUmuGuiaDistribucion;
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
public class DetalleSalidasUmuGuiaDistribucionDAOImplement implements DetalleSalidasUmuGuiaDistribucionDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(DetalleSalidasUmuGuiaDistribucion dsugd) {
        try {
            em.persist(dsugd);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(DetalleSalidasUmuGuiaDistribucionDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean actualizar(DetalleSalidasUmuGuiaDistribucion dsugd) {
        try {
            em.merge(dsugd);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(DetalleSalidasUmuGuiaDistribucionDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<DetalleSalidasUmuGuiaDistribucion> detalleSalidasUmuGuiaDistribucionByClave(String clave) {
        List<DetalleSalidasUmuGuiaDistribucion> resultList = null;
        try {
            resultList = em.createQuery("Select dsugd From DetalleSalidasUmuGuiaDistribucion dsugd "
                    + "Where dsugd.clave = '" + clave + "' ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(DetalleSalidasUmuGuiaDistribucionDAOImplement.class.getName()).log(Level.SEVERE, "", e);
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
            Query q1 = em.createNativeQuery("delete detalle_salidas_umu_guia_distribucion");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(DetalleSalidasUmuGuiaDistribucionDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }
}
