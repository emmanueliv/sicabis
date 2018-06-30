package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.HistoricoExistenciaPorClaveCenadi;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class HistoricoExistenciaPorClaveCenadiDAOImplement implements HistoricoExistenciaPorClaveCenadiDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(HistoricoExistenciaPorClaveCenadi hepcc) {
        try {
            em.persist(hepcc);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(HistoricoExistenciaPorClaveCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<HistoricoExistenciaPorClaveCenadi> getByFechaIngreso(Date fechaIngreso) {
        List<HistoricoExistenciaPorClaveCenadi> resultList = null;
        try {
            resultList = em.createQuery("SELECT hepcc FROM HistoricoExistenciaPorClaveCenadi hepcc WHERE hepcc.fechaIngreso = :fechaIngreso").setParameter("fechaIngreso", fechaIngreso).getResultList();
        } catch (Exception e) {
            Logger.getLogger(HistoricoExistenciaPorClaveCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<HistoricoExistenciaPorClaveCenadi> getAll() {
        List<HistoricoExistenciaPorClaveCenadi> resultList = null;
        String query = "";
        try {
            query = "SELECT ercch FROM HistoricoExistenciaPorClaveCenadi ercch ";
            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(HistoricoExistenciaPorClaveCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<HistoricoExistenciaPorClaveCenadi> getByFiltros(Date fechaInicio, Date fechaFin, String tipoClave, String clave, String clave2, String subinventario, String localizador, String lote) {
        List<HistoricoExistenciaPorClaveCenadi> resultList = null;
        String query = "";
        try {
            query = "SELECT ercch FROM HistoricoExistenciaPorClaveCenadi ercch WHERE ercch.fechaInventario BETWEEN :fechaInicio and :fechaFin \n";
            if (!tipoClave.equals("-1")) {
                query = query + "AND ercch.tipo = '" + tipoClave + "' \n";
            }
            if (!clave.equals("") && !clave.equals("-1")) {
                query = query + "AND ercch.clave = '" + clave + "' \n";
            }
            if (!clave2.equals("") && !clave2.equals("-1")) {
                query = query + "AND ercch.clave = '" + clave2 + "' \n";
            }
            if (!subinventario.equals("")) {
                query = query + "AND ercch.subinventario LIKE '%" + subinventario + "%' \n";
            }
            if (!localizador.equals("")) {
                query = query + "AND ercch.localizador LIKE '%" + localizador + "%' \n";
            }
            if (!lote.equals("")) {
                query = query + "AND ercch.lote LIKE '%" + lote + "%' \n";
            }
            System.out.println(query);
            Query q = em.createQuery(query);
            q.setParameter("fechaInicio", fechaInicio);
            q.setParameter("fechaFin", fechaFin);
            resultList = q.getResultList();

        } catch (Exception e) {
            Logger.getLogger(HistoricoExistenciaPorClaveCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
    
    public List<String> getDistinctSubinventario() {
        List<String> resultList = null;
        String query = "";
        try {
            resultList = em.createQuery("SELECT DISTINCT(hepcc.subinventario) FROM HistoricoExistenciaPorClaveCenadi hepcc", String.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(HistoricoExistenciaPorClaveCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
    
    public List<String> getDistinctLocalizador() {
        List<String> resultList = null;
        String query = "";
        try {
            resultList = em.createQuery("SELECT DISTINCT(hepcc.localizador) FROM HistoricoExistenciaPorClaveCenadi hepcc", String.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(HistoricoExistenciaPorClaveCenadiDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

}
