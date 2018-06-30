
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoInsumoDpn;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TipoInsumoDpnDAOImplement implements TipoInsumoDpnDAO{
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<TipoInsumoDpn> getAll() {
        List<TipoInsumoDpn> resultList = null;
        try {
            resultList = em.createNamedQuery("TipoInsumoDpn.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(TipoInsumoDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public TipoInsumoDpn getById(Integer idTipoInsumoDpn) {
        List<TipoInsumoDpn> resultList = null;
        try {
            resultList = em.createNamedQuery("TipoInsumoDpn.findByIdTipoInsumoDpn").setParameter("idTipoInsumoDpn", idTipoInsumoDpn).getResultList();
        } catch (Exception e) {
            Logger.getLogger(TipoInsumoDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
    
}
