/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.service.silodisa.DAO.CatalogoUnidadesMedicasDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.CatUnidadMedica;
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
public class CatalogoUnidadesMedicasDAOImplement implements CatalogoUnidadesMedicasDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(CatUnidadMedica cum) {
        try {
            em.persist(cum);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(CatalogoUnidadesMedicasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean actualizar(CatUnidadMedica cum) {
        try {
            em.merge(cum);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(CatalogoUnidadesMedicasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<CatUnidadMedica> catalogoUnidadesMedicasByUmu(String umu) {
        List<CatUnidadMedica> resultList = null;
        try {
            resultList = em.createQuery("Select cum From CatUnidadMedica cum "
                    + "Where cum.umu = '" + umu + "' ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(CatalogoUnidadesMedicasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
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
            Query q1 = em.createNativeQuery("DELETE FROM cat_unidad_medica");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(CatalogoUnidadesMedicasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<CatUnidadMedica> getAll() {
        List<CatUnidadMedica> resultList = null;
        try {
            resultList = em.createQuery("SELECT cum FROM CatUnidadMedica cum ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(CatalogoUnidadesMedicasDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
}
