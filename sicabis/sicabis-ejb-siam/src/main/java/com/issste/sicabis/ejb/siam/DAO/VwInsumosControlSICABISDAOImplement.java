
package com.issste.sicabis.ejb.siam.DAO;

import com.issste.sicabis.ejb.siam.modelo.VwInsumosControlSICABIS;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VwInsumosControlSICABISDAOImplement implements VwInsumosControlSICABISDAO{
    
    @PersistenceContext(unitName = "siam")
    private EntityManager em;

    @Override
    public List<VwInsumosControlSICABIS> obtenerByClave(String clave) {
        List<VwInsumosControlSICABIS> resultList = null;
        try {
            resultList = em.createNamedQuery("VwInsumosControlSICABIS.findByClave").setParameter("clave", clave).getResultList();
        } catch (Exception e) {
            Logger.getLogger(vwInsumosSICABISDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return resultList;
    }
    
}
