package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Propuestas;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
@LocalBean
public class PropuestasDAOImplement implements PropuestasDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardarPropuestas(Propuestas propuestas) {
        try {
            em.persist(propuestas);
            em.getEntityManagerFactory().getCache().evictAll();
            em.flush();
            em.clear();
            return true;
        } catch (Exception e) {
            Logger.getLogger(PropuestasDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<Propuestas> obtenerByProcedimientoRcb(Integer idProcedimientoRcb) {
        List<Propuestas> resultList = null;
        String query = "";
        boolean bandera = false;
        try {
            query = "  Select pr \n"
                    + "  From Propuestas p \n"
                    + " Where p.activo = 1 \n"
                    + "   and p.idProcedimientoRcb.idProcedimientoRcb = :idProcedimientoRcb \n";

            resultList = em.createQuery(query).setParameter("idProcedimientoRcb", idProcedimientoRcb).getResultList();
        } catch (Exception e) {
            Logger.getLogger(PropuestasDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public boolean borrarByIdProcedimientoRcb(Integer idProcedimientoRcb) {
        try {
            Query query = em.createQuery("DELETE FROM Propuestas p WHERE p.idProcedimientoRcb.idProcedimientoRcb=:idProcedimientoRcb");
            query.setParameter("idProcedimientoRcb", idProcedimientoRcb).executeUpdate();
            em.flush();
        } catch (Exception e) {
            Logger.getLogger(PropuestasDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean actualizarPropuestas(Propuestas propuestas) {
        try {
            em.merge(propuestas);
            em.flush();
            em.getEntityManagerFactory().getCache().evictAll();
            return true;
        } catch (Exception e) {
            Logger.getLogger(PropuestasDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<Propuestas> getPropuestasByIdProcedimientoRcb(Integer idProcedimientoRcb) {
        try {
            return (List<Propuestas>) em.createQuery("SELECT p FROM Propuestas p WHERE p.idProcedimientoRcb.idProcedimientoRcb=:idProcedimientoRcb").setParameter("idProcedimientoRcb", idProcedimientoRcb).getResultList();
        } catch (Exception e) {
            Logger.getLogger(PropuestasDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }
}
