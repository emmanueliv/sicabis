/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.AnoRemisionesElectronicasDTO;
import com.issste.sicabis.ejb.DTO.MesRemisionesElectronicasDTO;
import com.issste.sicabis.ejb.modelo.RemisionesElectronicasEntregasUmuHistorico;
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
public class RemisionesElectronicasEntregasUmuHistoricoDAOImplement implements RemisionesElectronicasEntregasUmuHistoricoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(RemisionesElectronicasEntregasUmuHistorico reeuh) {
        try {
            em.persist(reeuh);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(RemisionesElectronicasEntregasUmuHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<RemisionesElectronicasEntregasUmuHistorico> getByFechaIngreso(Date fechaIngreso) {
        List<RemisionesElectronicasEntregasUmuHistorico> resultList = null;
        try {
            resultList = em.createQuery("SELECT reeuh FROM RemisionesElectronicasEntregasUmuHistorico reeuh WHERE reeuh.fechaIngreso = :fechaIngreso").setParameter("fechaIngreso", fechaIngreso).getResultList();
        } catch (Exception e) {
            Logger.getLogger(RemisionesElectronicasEntregasUmuHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<RemisionesElectronicasEntregasUmuHistorico> getAll() {
        List<RemisionesElectronicasEntregasUmuHistorico> resultList = null;
        String query = "";
        try {
            query = "SELECT reeuh FROM RemisionesElectronicasEntregasUmuHistorico reeuh  ";
            System.out.println("query");
            resultList = em.createQuery(query).getResultList();
            //resultList = em.createQuery("SELECT reeuh FROM RemisionesElectronicasEntregasUmuHistorico reeuh ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(RemisionesElectronicasEntregasUmuHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<RemisionesElectronicasEntregasUmuHistorico> getByFiltros(Date fechaInicio, Date fechaFin, String delegacion, String clave, String folioPedido,
            String mesDPN, String umu, String tipoInsumo, String remision, String anoDPN, String nombreUmu, String tipoPedido) {
      List<RemisionesElectronicasEntregasUmuHistorico> resultList = null;
        String query = "";
        try {
            query = "SELECT reeuh FROM RemisionesElectronicasEntregasUmuHistorico reeuh WHERE reeuh.fecha BETWEEN :fechaInicio and :fechaFin ";
            if (!delegacion.equals("-1")) {
                query = query + "AND reeuh.delegacion = '" + delegacion + "' ";
            }
            if (!clave.equals("-1")) {
                query = query + "AND reeuh.clave = '" + clave + "' ";
            }
            if (!folioPedido.equals("")) {
                query = query + "AND reeuh.folioissste = " + folioPedido + " ";
            }
            if (!mesDPN.equals("")) {
                query = query + "AND reeuh.dpnMes LIKE '%" + mesDPN + "%' ";
            }
            if (!tipoInsumo.equals("")) {
                query = query + "AND reeuh.tipo LIKE '%" + tipoInsumo + "%' ";
            }
            if (!remision.equals("")) {
                query = query + "AND reeuh.remision = " + remision + " ";
            }
            if (!anoDPN.equals("")) {
                query = query + "AND reeuh.dpnYear LIKE '%" + anoDPN + "%' ";
            }
            if (!tipoPedido.equals("")) {
                query = query + "AND reeuh.tipopedido LIKE '%" + tipoPedido + "%' ";
            }
            Query q = em.createQuery(query);
            q.setParameter("fechaInicio", fechaInicio);
            q.setParameter("fechaFin", fechaFin);
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(RemisionesElectronicasEntregasUmuHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<AnoRemisionesElectronicasDTO> getAno() {
        List<AnoRemisionesElectronicasDTO> resultList = null;
        String query = "";
        try {
            query = "SELECT distinct (reeuh.dpnYear) FROM RemisionesElectronicasEntregasUmuHistorico reeuh ";
            System.out.println("query");
            resultList = em.createQuery(query).getResultList();
            //resultList = em.createQuery("SELECT reeuh FROM RemisionesElectronicasEntregasUmuHistorico reeuh ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(RemisionesElectronicasEntregasUmuHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<MesRemisionesElectronicasDTO> getMes() {
        List<MesRemisionesElectronicasDTO> resultList = null;
        String query = "";
        try {
            query = "SELECT distinct (reeuh.dpnMes) FROM RemisionesElectronicasEntregasUmuHistorico reeuh ";
            System.out.println("query");
            resultList = em.createQuery(query).getResultList();
            //resultList = em.createQuery("SELECT reeuh FROM RemisionesElectronicasEntregasUmuHistorico reeuh ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(RemisionesElectronicasEntregasUmuHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
}
