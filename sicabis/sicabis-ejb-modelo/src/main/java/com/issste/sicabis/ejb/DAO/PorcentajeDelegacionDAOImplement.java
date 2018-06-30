/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.PorcentajeDelegacion;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 6JWBBG2
 */
@Stateless
public class PorcentajeDelegacionDAOImplement implements PorcentajeDelegacionDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<PorcentajeDelegacion> getListaPorcentajeDelegacion() {
        List<PorcentajeDelegacion> resultList = null;
        try {
            resultList = em.createNamedQuery("PorcentajeDelegacion.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(PorcentajeDelegacionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

//    @Override
//    public PorcentajeDelegacion obtenerPorcentajeByClaveDelegacion(String claveDelegacion) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public boolean actualizarPorcentajeDelegacion(PorcentajeDelegacion delegacion) {
        boolean result = false;
        try {
            em.persist(delegacion);
            result = true;
        } catch (Exception e) {
            Logger.getLogger(PorcentajeDelegacionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return result;
    }

    @Override
    public boolean borrarContenidoPorcentajeDelegacion() {
        try {
            Query q1 = em.createNativeQuery("DELETE FROM porcentaje_delegacion");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(PorcentajeDelegacionDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

}
