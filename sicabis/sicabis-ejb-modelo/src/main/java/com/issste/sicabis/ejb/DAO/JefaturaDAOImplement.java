
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Jefatura;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class JefaturaDAOImplement implements JefaturaDAO {
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Jefatura> getAll() {
        List<Jefatura> resultList = null;
        try {
            Query query = em.createQuery("SELECT j FROM Jefatura j WHERE j.activo = 1 ");
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(JefaturaDAOImplement.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<Jefatura> getByIdArea(Integer idArea) {
        List<Jefatura> resultList = null;
        try {
            Query query = em.createQuery("SELECT j FROM Jefatura j WHERE j.activo = 1 AND j.idArea.idArea = :idArea ");
            query.setParameter("idArea", idArea);
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(JefaturaDAOImplement.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
    
}
