/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.EntradasMymcqCenadiHistorico;
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
public class EntradasMymcqCenadiHistoricoDAOImplement implements EntradasMymcqCenadiHistoricoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(EntradasMymcqCenadiHistorico emch) {
        try {
            em.persist(emch);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(EntradasMymcqCenadiHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<EntradasMymcqCenadiHistorico> getByFechaIngreso(Date fechaIngreso) {
        List<EntradasMymcqCenadiHistorico> resultList = null;
        try {
            resultList = em.createQuery("SELECT emch FROM EntradasMymcqCenadiHistorico emch WHERE emch.fechaIngreso = :fechaIngreso").setParameter("fechaIngreso", fechaIngreso).getResultList();
        } catch (Exception e) {
            Logger.getLogger(EntradasMymcqCenadiHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<EntradasMymcqCenadiHistorico> getAll() {
        List<EntradasMymcqCenadiHistorico> resultList = null;
        try {
            resultList = em.createQuery("SELECT emch FROM EntradasMymcqCenadiHistorico emch  ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(EntradasMymcqCenadiHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
    
    @Override
    public List<String> getDistinctProveedor() {
        List<String> resultList = null;
        String query = "";
        try {
            resultList = em.createQuery("SELECT DISTINCT(emch.proveedor) FROM EntradasMymcqCenadiHistorico emch", String.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(EntradasMymcqCenadiHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
    
    @Override
    public List<String> getDistinctRegistroControl() {
        List<String> resultList = null;
        String query = "";
        try {
            resultList = em.createQuery("SELECT DISTINCT(emch.registroControl) FROM EntradasMymcqCenadiHistorico emch WHERE emch.registroControl IS NOT NULL", String.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(EntradasMymcqCenadiHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
    
    @Override
    public List<String> getDistinctTipoEntrada() {
        List<String> resultList = null;
        String query = "";
        try {
            resultList = em.createQuery("SELECT DISTINCT(emch.tipoEntrada) FROM EntradasMymcqCenadiHistorico emch", String.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(EntradasMymcqCenadiHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
    
    @Override
    public List<String> getDistinctOcOracle() {
        List<String> resultList = null;
        String query = "";
        try {
            resultList = em.createQuery("SELECT DISTINCT(emch.ocOracle) FROM EntradasMymcqCenadiHistorico emch", String.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(EntradasMymcqCenadiHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
    
    @Override
    public List<String> getDistinctClave() {
        List<String> resultList = null;
        String query = "";
        try {
            resultList = em.createQuery("SELECT DISTINCT(emch.clave) FROM EntradasMymcqCenadiHistorico emch", String.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(EntradasMymcqCenadiHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
}
