package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.CorreoElectronico;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CorreoElectronicoDAOImplement implements CorreoElectronicoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(CorreoElectronico correoElectronico) {
        try {
            if (correoElectronico.getIdCorreoElectronico() == null) {
                em.persist(correoElectronico);
            } else {
                em.merge(correoElectronico);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(CorreoElectronicoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

}
