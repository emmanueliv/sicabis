/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.service.silodisa.DAO.SalidasCenadiUmuGuiaDeDistribucionDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.SalidasCenadiUmuGuiaDeDistribucion;
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
public class SalidasCenadiUmuGuiaDeDistribucionDAOImplement implements SalidasCenadiUmuGuiaDeDistribucionDAO {
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(SalidasCenadiUmuGuiaDeDistribucion scugd) {
        try {
            em.persist(scugd);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(SalidasCenadiUmuGuiaDeDistribucionDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean actualizar(SalidasCenadiUmuGuiaDeDistribucion scugd) {
        try {
            em.merge(scugd);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(SalidasCenadiUmuGuiaDeDistribucionDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<SalidasCenadiUmuGuiaDeDistribucion> salidasCenadiUmuGuiaDeDistribucionByUmu(String umu) {
        List<SalidasCenadiUmuGuiaDeDistribucion> resultList = null;
        try {
            resultList = em.createQuery("Select scugd From SalidasCenadiUmuGuiaDeDistribucion scugd "
                    + "Where scugd.umu = '" + umu + "' ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(SalidasCenadiUmuGuiaDeDistribucionDAOImplement.class.getName()).log(Level.SEVERE, "", e);
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
            Query q1 = em.createNativeQuery("DELETE FROM salidas_cenadi_umu_guia_de_distribucion");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(SalidasCenadiUmuGuiaDeDistribucionDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }
}
