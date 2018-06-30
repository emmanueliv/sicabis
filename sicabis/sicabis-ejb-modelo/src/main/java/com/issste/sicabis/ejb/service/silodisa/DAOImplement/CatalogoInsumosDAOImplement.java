/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAOImplement;

import com.issste.sicabis.ejb.service.silodisa.DAO.CatalogoInsumosDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.CatInsumos;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
public class CatalogoInsumosDAOImplement implements CatalogoInsumosDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardar(CatInsumos ci) {
        try {
            em.persist(ci);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(CatalogoInsumosDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public boolean actualizar(CatInsumos ci) {
        try {
            em.merge(ci);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(CatalogoInsumosDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<CatInsumos> catalogoInsumosByClave(String clave) {
        List<CatInsumos> resultList = null;
        try {
            resultList = em.createQuery("Select ci From CatInsumos ci "
                    + "Where ci.clave = '" + clave + "' ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(CatalogoInsumosDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public boolean eliminarExistencias() {
        try {
            Query q1 = em.createNativeQuery("delete cat_insumos");
            q1.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(CatalogoInsumosDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<CatInsumos> getAllCatalogoInsumos() {
       List<CatInsumos> resultList = null;
        try {
            Query q = em.createQuery("SELECT ci FROM CatInsumos ci");
            resultList = (List<CatInsumos>) q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(CatalogoInsumosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("catInsumos vacio");
        return null;
    }
}
