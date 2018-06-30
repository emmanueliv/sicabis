/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ExistenciaUmusProgramasHistorico;
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
public class ExistenciaUmusProgramasHistoricoDAOImplement implements ExistenciaUmusProgramasHistoricoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(ExistenciaUmusProgramasHistorico euph) {
        try {
            em.persist(euph);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(ExistenciaUmusProgramasHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<ExistenciaUmusProgramasHistorico> getByFechaIngreso(Date fechaIngreso) {
        List<ExistenciaUmusProgramasHistorico> resultList = null;
        try {
            resultList = em.createQuery("SELECT euph FROM ExistenciaUmusProgramasHistorico euph WHERE euph.fechaIngresoAux = :fechaIngreso").setParameter("fechaIngreso", fechaIngreso).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ExistenciaUmusProgramasHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<ExistenciaUmusProgramasHistorico> getAll() {
        List<ExistenciaUmusProgramasHistorico> resultList = null;
        try {
            resultList = em.createQuery("SELECT euph FROM ExistenciaUmusProgramasHistorico euph ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ExistenciaUmusProgramasHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<ExistenciaUmusProgramasHistorico> buscarByFiltros(Date fechaInicial, Date fechaFinal, String delegacion, String numeroUmu, String clave, String nombreClave) {
        List<ExistenciaUmusProgramasHistorico> resultList = null;
        try {
            String query = "SELECT euph FROM ExistenciaUmusProgramasHistorico euph WHERE euph.fechaInventario BETWEEN :fechaInicial AND :fechaFinal \n";
            if (!delegacion.equals("-1")) {
                query = query + "and euph.delegacion = '" + delegacion + "' \n";
            }
            if (!numeroUmu.equals("")) {
                query = query + "and euph.numeroUmu = '" + numeroUmu + "' \n";
            }
            if (!clave.equals("-1")) {
                query = query + "and euph.clave = '" + clave + "' \n";
            }
            if (!nombreClave.equals("-1")) {
                query = query + "and euph.clave = '" + nombreClave + "' \n";
            }
            Query q = em.createQuery(query).setParameter("fechaInicial", fechaInicial).setParameter("fechaFinal", fechaFinal);
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(ExistenciaUmusProgramasHistoricoDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
}
