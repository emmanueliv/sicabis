/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.modelo.EntradasMymcqCenadiHistorico;
import com.issste.sicabis.ejb.service.silodisa.DAO.EntradasMYMCQCenadiDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.EntradasMymcqCenadi;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class EntradasMYMCQCenadiDAOImplement implements EntradasMYMCQCenadiDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(EntradasMymcqCenadi emc) {
        try {
            em.persist(emc);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(EntradasMYMCQCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean actualizar(EntradasMymcqCenadi emc) {
        try {
            em.merge(emc);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(EntradasMYMCQCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<EntradasMymcqCenadi> detalleEntradasMymcqCenadi(String clave) {
        List<EntradasMymcqCenadi> resultList = null;
        try {
            resultList = em.createQuery("Select emc From EntradasMymcqCenadi emc "
                    + "Where emc.clave = '" + clave + "' ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(EntradasMYMCQCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
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
            Query q1 = em.createNativeQuery("delete entradas_mymcq_cenadi");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(EntradasMYMCQCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<EntradasMymcqCenadi> getAll() {
        List<EntradasMymcqCenadi> resultList = null;
        String query = "";
        try {
            query = "SELECT emc FROM EntradasMymcqCenadi emc WHERE emc.activo = 1 ";
            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(EntradasMYMCQCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<EntradasMymcqCenadiHistorico> getByFiltros(Date fechaInicio, Date fechaFin, String proveedor, String registroControl, String numContratoCualquiera, String tipoEntrada, String ocOracle, String loteCualquiera, String clave) {
        List<EntradasMymcqCenadiHistorico> resultList = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String query = "";
        try {
            query = "SELECT emc FROM EntradasMymcqCenadiHistorico emc WHERE emc.fecha BETWEEN :fechaInicio AND :fechaFin";
            if (!proveedor.equals("")) {
                query = query + " AND emc.proveedor LIKE '%" + proveedor + "%' ";
            }
            if (!registroControl.equals("")) {
                query = query + " AND emc.registroControl = '" + registroControl + "' ";
            }
            if (!numContratoCualquiera.equals("")) {
                query = query + " AND emc.numeroProveedor = '" + numContratoCualquiera + "' ";
            }
            if (!tipoEntrada.equals("")) {
                query = query + " AND emc.tipoEntrada LIKE '%" + tipoEntrada + "%' ";
            }
            if (!ocOracle.equals("")) {
                query = query + " AND emc.ocOracle LIKE '%" + ocOracle + "%' ";
            }
            if (!loteCualquiera.equals("")) {
                query = query + " AND emc.lote = '" + loteCualquiera + "' ";
            }
            if (!clave.equals("-1")) {
                query = query + " AND emc.clave = '" + clave + "' ";
            }
            System.out.println("query-->" + query);
            Query q = em.createQuery(query).setParameter("fechaInicio", fechaInicio).setParameter("fechaFin", fechaFin);
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(EntradasMYMCQCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
}
