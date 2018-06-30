/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.service.silodisa.DAO.RemisionesElectronicasEntregasUmuDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.RemisionesElectronicasEntregasUmu;
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
public class RemisionesElectronicasEntregasUmuDAOImplement implements RemisionesElectronicasEntregasUmuDAO{

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(RemisionesElectronicasEntregasUmu reeu) {
        try {
            em.persist(reeu);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(RemisionesElectronicasEntregasUmuDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean actualizar(RemisionesElectronicasEntregasUmu reeu) {
        try {
            em.merge(reeu);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(RemisionesElectronicasEntregasUmuDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<RemisionesElectronicasEntregasUmu> remisionesElectronicasEntregasUmuByClave(String clave) {
        List<RemisionesElectronicasEntregasUmu> resultList = null;
        try {
            resultList = em.createQuery("Select reeu From RemisionesElectronicasEntregasUmu reeu "
                    + "Where reeu.clave = '" + clave + "' ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(RemisionesElectronicasEntregasUmuDAOImplement.class.getName()).log(Level.SEVERE, "", e);
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
            Query q1 = em.createNativeQuery("DELETE FROM remisiones_electronicas_entregas_umu");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(RemisionesElectronicasEntregasUmuDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }
}
