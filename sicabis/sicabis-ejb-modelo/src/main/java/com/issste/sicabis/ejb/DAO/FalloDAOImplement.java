package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Fallos;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FalloDAOImplement implements FalloDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardaFallos(Fallos fallos) {
        try {
            em.persist(fallos);
            return true;
        } catch (Exception e) {
            Logger.getLogger(FalloDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public boolean actualizaFallo(Fallos fallos) {
        try {
            //em.getEntityManagerFactory().getCache().evictAll();
            em.merge(fallos);
            return true;
        } catch (Exception e) {
            Logger.getLogger(FalloDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public Fallos obtenerByNumeroFallo(String numeroFallo) {
        try {
            return (Fallos) em.createNamedQuery("Fallos.findByNumeroFallo").setParameter("numeroFallo", numeroFallo).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(FalloDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public List<Fallos> obtenerByFalloProcRcb(Fallos fallos) {
        List<Fallos> resultList = null;
        String query = "";
        try {
            query = "  SELECT f \n"
                    + "  FROM Fallos f \n"
                    + " WHERE f.activo = 1 \n";

            if (!fallos.getNumeroProcedimiento().equals("")) {
                query = query + "   AND f.numeroProcedimiento = '" + fallos.getNumeroProcedimiento() + "' \n";
            }
            if (!fallos.getNumeroFallo().equals("")) {
                query = query + "   AND f.numeroFallo = '" + fallos.getNumeroFallo() + "' ";
            }

            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(FalloDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public Fallos obtenerByNumProcRcb(String numeroProcedimiento) {
        List<Fallos> resultList = null;
        String query = "";
        try {
            resultList = em.createQuery(
                    "  SELECT f FROM Fallos f WHERE f.activo = 1 AND f.idEstatus.idEstatus = 42 AND f.numeroProcedimiento = :numeroProcedimiento ")
                    .setParameter("numeroProcedimiento", numeroProcedimiento).getResultList();
        } catch (Exception e) {
            Logger.getLogger(FalloDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

}
