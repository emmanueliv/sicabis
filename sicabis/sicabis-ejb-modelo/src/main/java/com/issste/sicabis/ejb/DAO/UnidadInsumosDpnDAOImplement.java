
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.UnidadInsumosDpn;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UnidadInsumosDpnDAOImplement implements UnidadInsumosDpnDAO{
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardarActualizar(UnidadInsumosDpn unidadInsumosDpn) {
        try {
            if (unidadInsumosDpn.getIdUnidadInsumoDpn()== null) {
                em.persist(unidadInsumosDpn);
            } else {
                em.merge(unidadInsumosDpn);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(UnidadInsumosDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<UnidadInsumosDpn> getAll() {
        List<UnidadInsumosDpn> resultList = null;
        try {
            resultList = em.createNamedQuery("UnidadInsumosDpn.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(UnidadInsumosDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<UnidadInsumosDpn> getByIdInsumoDpn(Integer idInsumoDpn) {
        List<UnidadInsumosDpn> resultList = null;
        try {
            resultList = em.createNamedQuery("UnidadInsumosDpn.findByIdInsumoDpn").setParameter("idInsumoDpn", idInsumoDpn).getResultList();
        } catch (Exception e) {
            Logger.getLogger(UnidadInsumosDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<UnidadesMedicas> getUMByIdInsumoDpn(Integer idInsumoDpn) {
        List<UnidadesMedicas> resultList = null;
        try {
            Query q = em.createQuery("SELECT um FROM UnidadInsumosDpn uid JOIN uid.idUnidadesMedicas um WHERE uid.idInsumoDpn.idInsumoDpn = :idInsumoDpn AND uid.activo = 1", UnidadesMedicas.class);
            q.setParameter("idInsumoDpn", idInsumoDpn);
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(UnidadInsumosDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
    
    @Override
    public boolean actualizaByIdInsumoDpn(Integer idInsumoDpn) {
        try {
            em.createQuery("UPDATE UnidadInsumosDpn uid SET uid.activo = 0 WHERE uid.idInsumoDpn.idInsumoDpn = :idInsumoDpn").setParameter("idInsumoDpn", idInsumoDpn).executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(UnidadInsumosDpnDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }
    
}
