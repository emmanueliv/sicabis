/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoProcedimiento;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TipoProcedimientoDAOImplement implements TipoProcedimientoDAO{
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;
    
    @Override
    public List<TipoProcedimiento> obtenerTiposProcedimientos() {
        return (List<TipoProcedimiento>) em.createNamedQuery("TipoProcedimiento.findAll").getResultList();
    }

    @Override
    public List<TipoProcedimiento> obtenerAlmacenByNombre(String nombre) {
        return (List<TipoProcedimiento>) em.createQuery("SELECT tp FROM TipoProcedimiento tp WHERE tp.activo = 1 AND tp.descripcion = :descripcion").setParameter("descripcion", nombre).getResultList();
    }

    @Override
    public void guardarAlmacen(TipoProcedimiento tipoProcedimiento) {
        try {
            if (tipoProcedimiento.getIdTipoProcedimiento() == null) {
                em.persist(tipoProcedimiento);
            } else {
                em.merge(tipoProcedimiento);
            }
        } catch (Exception e) {
            Logger.getLogger(TipoProcedimientoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }
    
}
