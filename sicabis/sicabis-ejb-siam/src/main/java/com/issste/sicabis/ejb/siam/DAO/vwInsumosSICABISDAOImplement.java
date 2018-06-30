
package com.issste.sicabis.ejb.siam.DAO;

import com.issste.sicabis.ejb.siam.modelo.VwInsumosSICABIS;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class vwInsumosSICABISDAOImplement implements vwInsumosSICABISDAO {
    
    @PersistenceContext(unitName = "siam")
    private EntityManager em;

    @Override
    public List<VwInsumosSICABIS> obtenerVwInsumos() {
        List<VwInsumosSICABIS> resultList = null;
        try {
            resultList = em.createNamedQuery("VwInsumosSICABIS.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(vwInsumosSICABISDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return resultList;
    }
    
}
