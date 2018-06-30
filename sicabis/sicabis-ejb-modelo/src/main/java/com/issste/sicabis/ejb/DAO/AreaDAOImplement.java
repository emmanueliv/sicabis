/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kriosoft
 */
@Stateless
public class AreaDAOImplement implements AreaDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Area> getAreas() {
        List<Area> resultList = null;
        try {
            resultList = em.createNamedQuery("Area.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<Area> obtenerAreaByNombre(String nombreArea) {
        return  em.createNamedQuery("Area.findByNombreArea").setParameter("nombreArea", nombreArea).getResultList();
    }

    @Override
    public void guardarArea(Area area) {
        try {
            if (area.getIdArea() == null) {
                em.persist(area);
            } else {
                em.merge(area);
            }
        } catch (Exception e) {
            Logger.getLogger(AreaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<Area> getAreasAdmon() {
        List<Area> resultList = null;
        try {
            resultList = em.createQuery("SELECT a FROM Area a where a.idPadre =10").getResultList();    
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

}
