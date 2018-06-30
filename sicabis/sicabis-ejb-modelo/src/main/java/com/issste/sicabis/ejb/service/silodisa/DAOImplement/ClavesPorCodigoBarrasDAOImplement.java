/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.service.silodisa.DAO.ClavesPorCodigoBarrasDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.ClavesPorCodigoBarras;
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
public class ClavesPorCodigoBarrasDAOImplement implements ClavesPorCodigoBarrasDAO{

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(ClavesPorCodigoBarras cpcb) {
        try {
            em.persist(cpcb);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ClavesPorCodigoBarrasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean actualizar(ClavesPorCodigoBarras cpcb) {
        try {
            em.merge(cpcb);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ClavesPorCodigoBarrasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<ClavesPorCodigoBarras> clavesPorCodigoBarrasByClave(String clave) {
        List<ClavesPorCodigoBarras> resultList = null;
        try {
            resultList = em.createQuery("Select cpcb From ClavesPorCodigoBarras cpcb "
                    + "Where cpcb.clave = '" + clave + "' ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ClavesPorCodigoBarrasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
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
            Query q1 = em.createNativeQuery("DELETE FROM claves_por_codigo_barras");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ClavesPorCodigoBarrasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }
}
