package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.CompradorContrato;
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
public class CompradorContratoDAOImplement implements CompradorContratoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardaCompradorContrato(CompradorContrato compradorContrato) {
        try {
            em.persist(compradorContrato);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(CompradorContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public boolean actualizaCompradorContrato(CompradorContrato compradorContrato) {
        try {
            em.merge(compradorContrato);
            return true;
        } catch (Exception e) {
            Logger.getLogger(CompradorContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public boolean borrarByIdContrato(Integer idContrato) {
        try {
            Query query = em.createQuery("DELETE FROM CompradorContrato cc WHERE cc.idContrato.idContrato=:idContrato");
            query.setParameter("idContrato", idContrato).executeUpdate();
            em.flush();
            em.getEntityManagerFactory().getCache().evictAll();
        } catch (Exception e) {
            Logger.getLogger(CompradorContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
        return true;
    }

    @Override
    public CompradorContrato obtenerByIdContrato(Integer idContrato) {
        try {
            return (CompradorContrato) em.createQuery("SELECT c FROM CompradorContrato c WHERE c.idContrato.idContrato = :idContrato and c.activo = 1").setParameter("idContrato", idContrato).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(CompradorContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public List<CompradorContrato> obtenerListByIdContrato(Integer idContrato) {
        try {
            return (List<CompradorContrato>) em.createQuery("SELECT c FROM CompradorContrato c WHERE c.idContrato.idContrato = :idContrato and c.activo = 1").setParameter("idContrato", idContrato).getResultList();
        } catch (Exception e) {
            Logger.getLogger(CompradorContratoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

}
