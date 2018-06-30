/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.CaracterProcedimiento;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CaracterProcedimientoDAOImplement implements CaracterProcedimientoDAO{
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;
    
    @Override
    public List<CaracterProcedimiento> obtenerCaracterProcedimiento() {
        return (List<CaracterProcedimiento>) em.createQuery("SELECT cp FROM CaracterProcedimiento cp WHERE cp.activo = 1").getResultList();
    }

    @Override
    public List<CaracterProcedimiento> obtenerTipoCPByNombre(String nombre) {
                List<CaracterProcedimiento> resultList = null;
        try {
            resultList = em.createQuery("SELECT cp FROM CaracterProcedimiento cp WHERE cp.activo = 1 AND cp.descripcion = :descripcion").setParameter("descripcion", nombre).getResultList();
        } catch (Exception e) {
            Logger.getLogger(CaracterProcedimientoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public void guardarcaracterCaracterProcedimiento(CaracterProcedimiento cp) {
        try {
            if (cp.getIdCaracterProcedimiento() == null) {
                em.persist(cp);
            } else {
                em.merge(cp);
            }
        } catch (Exception e) {
            Logger.getLogger(CaracterProcedimientoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }
    
}
