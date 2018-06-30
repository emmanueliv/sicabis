/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.service.silodisa.DAO.AlertasOperativasDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.AlertasOperativas;
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
public class AlertasOperativasDAOImplement implements AlertasOperativasDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(AlertasOperativas ao) {
        try {
            em.persist(ao);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AlertasOperativasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean actualizar(AlertasOperativas ao) {
        try {
            em.merge(ao);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AlertasOperativasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public Integer alertasOperativasByClave(String clave) {
        Object suma = 0;
        try {
            suma = em.createNativeQuery("Select NVL(Sum(ao.piezas_dpn),0) as suma From alertas_operativas ao \n"
                    + "Where ao.clave = '" + clave + "' ").getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(AlertasOperativasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return Integer.parseInt(String.valueOf(suma));
    }

    @Override
    public Integer alertasOperativasByClaveAndUmu(String clave, String umu) {
        Object suma = 0;
        try {
            suma = em.createNativeQuery("Select NVL(Sum(ao.piezas_dpn),0) as suma From alertas_operativas ao \n"
                    + "Where ao.clave = '" + clave + "' and ao.clave_uml = '" + umu + "'").getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(AlertasOperativasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return Integer.parseInt(String.valueOf(suma));
    }

    @Override
    public boolean eliminarExistencias() {
        try {
            Query q1 = em.createNativeQuery("delete alertas_operativas");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(AlertasOperativasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<AlertasOperativas> getAll() {
        List<AlertasOperativas> resultList = null;
        try {
            resultList = em.createQuery("Select al From AlertasOperativas al ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(AlertasOperativasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

}
