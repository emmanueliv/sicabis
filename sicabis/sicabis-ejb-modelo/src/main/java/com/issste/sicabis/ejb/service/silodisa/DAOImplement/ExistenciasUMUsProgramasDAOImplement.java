/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.service.silodisa.DAO.ExistenciasUMUsProgramasDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaUmusProgramas;
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
public class ExistenciasUMUsProgramasDAOImplement implements ExistenciasUMUsProgramasDAO{

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(ExistenciaUmusProgramas eup) {
        try {
            em.persist(eup);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ExistenciasUMUsProgramasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean actualizar(ExistenciaUmusProgramas eup) {
        try {
            em.merge(eup);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ExistenciasUMUsProgramasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<ExistenciaUmusProgramas> detalleExistenciaUmusProgramas(String clave) {
        List<ExistenciaUmusProgramas> resultList = null;
        try {
            resultList = em.createQuery("Select eup From ExistenciaUmusProgramas eup "
                    + "Where eup.clave = '" + clave + "' ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ExistenciasUMUsProgramasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
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
            Query q1 = em.createNativeQuery("DELETE FROM existencia_umus_programas");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ExistenciasUMUsProgramasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<ExistenciaUmusProgramas> getAll() {
        List<ExistenciaUmusProgramas> resultList = null;
        try {
            resultList = em.createQuery("Select eup From ExistenciaUmusProgramas eup ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ExistenciasUMUsProgramasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }
}
