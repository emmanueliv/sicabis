/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ExistenciaReservaClaveCenadiHistorico;
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
public class ExistenciaReservaClaveCenadiHistoricoDAOImplement implements ExistenciaReservaClaveCenadiHistoricoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(ExistenciaReservaClaveCenadiHistorico ercch) {
        try {
            em.persist(ercch);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ExistenciaReservaClaveCenadiHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<ExistenciaReservaClaveCenadiHistorico> getByFechaIngreso(Date fechaIngreso) {
        List<ExistenciaReservaClaveCenadiHistorico> resultList = null;
        try {
            resultList = em.createQuery("SELECT ercch FROM ExistenciaReservaClaveCenadiHistorico ercch WHERE ercch.fechaIngresoAux = :fechaIngreso").setParameter("fechaIngreso", fechaIngreso).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ExistenciaReservaClaveCenadiHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<ExistenciaReservaClaveCenadiHistorico> getByFiltros(Date fechaInicio, Date fechaFin, String tipoClave, String clave, String clave2) {
        List<ExistenciaReservaClaveCenadiHistorico> resultList = null;
        String query = "";
        try {
            query = "  SELECT ercch FROM ExistenciaReservaClaveCenadiHistorico ercch \n"
                    + " WHERE ercch.fechaInventario BETWEEN :fechaInicio and :fechaFin \n";
            if (!tipoClave.equals("-1")) {
                query = query + "AND ercch.tipo = '" + tipoClave + "' \n";
            }
            if (clave != null && !clave.equals("-1")) {
                query = query + "AND ercch.clave = '" + clave + "' \n";
            }
            if (clave2 != null && !clave2.equals("-1")) {
                query = query + "AND ercch.clave = '" + clave2 + "' \n";
            }
            Query q = em.createQuery(query);
            q.setParameter("fechaInicio", fechaInicio);
            q.setParameter("fechaFin", fechaFin);
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(ExistenciaReservaClaveCenadiHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<ExistenciaReservaClaveCenadiHistorico> getAll() {
        List<ExistenciaReservaClaveCenadiHistorico> resultList = null;
        String query = "";
        try {
            query = "SELECT ercch.dpn_year FROM ExistenciaReservaClaveCenadiHistorico ercch ";
            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ExistenciaReservaClaveCenadiHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
}
