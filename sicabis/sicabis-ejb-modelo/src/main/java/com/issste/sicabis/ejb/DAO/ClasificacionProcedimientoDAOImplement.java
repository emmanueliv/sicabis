/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ClasificacionProcedimiento;
import com.issste.sicabis.ejb.modelo.TipoSolicitud;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClasificacionProcedimientoDAOImplement implements ClasificacionProcedimientoDAO{
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<ClasificacionProcedimiento> obtenerClasificacionProcedimiento() {
        return (List<ClasificacionProcedimiento>) em.createNamedQuery("ClasificacionProcedimiento.findAll").getResultList();
    }

    @Override
    public List<ClasificacionProcedimiento> obtenerTipoCPByNombre(String nombre) {
                List<ClasificacionProcedimiento> resultList = null;
        try {
            resultList = em.createNamedQuery("ClasificacionProcedimiento.findByDescripcion").setParameter("descripcion", nombre).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ClasificacionProcedimientoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public void guardarClasificacionProcedimiento(ClasificacionProcedimiento cfprod) {
        try {
            if (cfprod.getIdClasificacionProcedimiento() == null) {
                em.persist(cfprod);
            } else {
                em.merge(cfprod);
            }
        } catch (Exception e) {
            Logger.getLogger(ClasificacionProcedimientoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }
    
}
