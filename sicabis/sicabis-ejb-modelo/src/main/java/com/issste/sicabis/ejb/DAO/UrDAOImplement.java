
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.AlertasEnvio;
import com.issste.sicabis.ejb.modelo.Ur;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UrDAOImplement implements UrDAO {
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Ur> getAll() {
        List<Ur> resultList = null;
        try {
            resultList = em.createQuery("SELECT u FROM Ur u WHERE u.activo = 1 ORDER BY u.ur ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(UrDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public Ur getByUr(Integer ur) {
        List<Ur> resultList = null;
        try {
            Query q = em.createQuery("SELECT u FROM Ur u WHERE u.activo = 1 AND u.ur = :ur");
            q.setParameter("ur", ur);
            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(UrDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
    
}
