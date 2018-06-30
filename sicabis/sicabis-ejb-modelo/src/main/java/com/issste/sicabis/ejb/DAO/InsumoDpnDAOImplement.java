package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.InsumoDpn;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class InsumoDpnDAOImplement implements InsumoDpnDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardarActualizar(InsumoDpn insumoDpn) {
        try {
            if (insumoDpn.getIdInsumoDpn() == null) {
                em.persist(insumoDpn);
            } else {
                em.merge(insumoDpn);
            }
        } catch (Exception e) {
            Logger.getLogger(InsumoDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
        return true;
    }

    @Override
    public List<InsumoDpn> getall() {
        List<InsumoDpn> resultList = null;
        try {
            resultList = em.createNamedQuery("InsumoDpn.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(InsumoDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<InsumoDpn> getByInsumoDpn(InsumoDpn insumoDpn) {
        List<InsumoDpn> resultList = null;
        String query = "";
        try {
            if (insumoDpn.getIdInsumo() != null) {
                query = " AND id.idInsumo.idInsumo = " + insumoDpn.getIdInsumo().getIdInsumo();
            }
            if (insumoDpn.getEstatusInsumoDpn() != null) {
                query = query + " AND id.estatusInsumoDpn = " + insumoDpn.getEstatusInsumoDpn();
            }
            if (insumoDpn.getIdTipoInsumoDpn() != null) {
                query = query + " AND id.idTipoInsumoDpn.idTipoInsumoDpn = " + insumoDpn.getIdTipoInsumoDpn().getIdTipoInsumoDpn();
            }
            resultList = em.createQuery("SELECT id FROM InsumoDpn id WHERE id.idInsumo.activo = 1 " + query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(InsumoDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public InsumoDpn getByIdInsumoDpn(Integer idInsumoDpn) {
        List<InsumoDpn> resultList = null;
        try {
            resultList = em.createNamedQuery("InsumoDpn.findByIdInsumoDpn").setParameter("idInsumoDpn", idInsumoDpn).getResultList();
        } catch (Exception e) {
            Logger.getLogger(InsumoDpnDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

}
