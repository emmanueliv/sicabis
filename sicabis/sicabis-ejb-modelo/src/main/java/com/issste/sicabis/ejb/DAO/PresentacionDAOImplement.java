package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Presentacion;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fabianvr
 */
@Stateless
public class PresentacionDAOImplement implements PresentacionDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Presentacion> presentacionList() {
        List<Presentacion> resultList = null;
        try {
            resultList = em.createNamedQuery("Presentacion.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(PresentacionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<Presentacion> presentacionByDesc(String desc) {
        List<Presentacion> resultList = null;
        try {
            resultList = em.createNamedQuery("Presentacion.findByPresentacion")
                    .setParameter("presentacion", desc).getResultList();
        } catch (Exception e) {
            Logger.getLogger(PresentacionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
        return resultList;
    }

    @Override
    public void guardarPresentacion(Presentacion presentacion) {
        try {
            if (presentacion.getIdPresentacion() == null) {
                em.persist(presentacion);
            } else {
                em.merge(presentacion);
            }
        } catch (Exception e) {
            System.out.println(e);
            Logger.getLogger(PresentacionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

}
