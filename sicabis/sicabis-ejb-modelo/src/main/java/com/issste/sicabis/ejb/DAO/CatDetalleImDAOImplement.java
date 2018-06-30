
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.CatDetalleIm;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CatDetalleImDAOImplement implements CatDetalleImDAO {
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(CatDetalleIm catDetalleIm) {
        try {
            em.persist(catDetalleIm);
            return true;
        } catch (Exception e) {
            Logger.getLogger(CatDetalleImDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public CatDetalleIm obtenerByIdCatDetalleIm(String idCatDetalleIm) {
        try {
            return (CatDetalleIm) em.createNamedQuery("CatDetalleIm.findByIdCatDetalleIm").setParameter("idCatDetalleIm", idCatDetalleIm).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(CatDetalleImDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public List<CatDetalleIm> obtenerTodos() {
        try {
            return em.createNamedQuery("CatDetalleIm.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(CatDetalleImDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public CatDetalleIm obtenerByIdJefatura(Integer idJefatura) {
        List<CatDetalleIm> resultList = null;
        try {
            Query q = em.createQuery("SELECT cdim FROM CatDetalleIm cdim WHERE cdim.activo = 1 AND cdim.idJefatura.idJefatura = :idJefatura ");
            q.setParameter("idJefatura", idJefatura);
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(CatDetalleImDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public boolean actualizar(CatDetalleIm catDetalleIm) {
        try {
            em.merge(catDetalleIm);
            return true;
        } catch (Exception e) {
            Logger.getLogger(CatDetalleImDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }
    
}
