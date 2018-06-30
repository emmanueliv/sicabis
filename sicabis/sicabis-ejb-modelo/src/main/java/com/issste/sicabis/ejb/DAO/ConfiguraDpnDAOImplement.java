
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ConfiguraDpn;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ConfiguraDpnDAOImplement implements ConfiguraDpnDAO {
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardaConfiguraDpn(ConfiguraDpn configuraDpn) {
        try {
            if (configuraDpn.getIdConfiguraDpn() == null) {
                em.persist(configuraDpn);
            } else {
                em.merge(configuraDpn);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(ConfiguraDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<ConfiguraDpn> getAllByActivo(Integer activo) {
        List<ConfiguraDpn> resultList = null;
        try {
            resultList =  em.createNamedQuery("ConfiguraDpn.findByActivo").setParameter("activo", activo).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ConfiguraDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
    
}
