/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Planeacion;
import com.issste.sicabis.ejb.modelo.PlaneacionDetalle;
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
 * @author Toshiba Manolo
 */
@Stateless
public class PlaneacionDetalleDAOImplement implements PlaneacionDetalleDAO{
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<PlaneacionDetalle> obtenerPlaneacionDetalle() {
         List<PlaneacionDetalle> resultList = null;
        try {
            resultList = em.createNamedQuery("PlaneacionDetalle.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(PlaneacionDetalleDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public Integer deletePlaneacionDetallePorId(Integer idPlaneacionDetalle) {
        Integer result=0;        
        try {
            Query query = em.createQuery(
                "Delete FROM PlaneacionDetalle p WHERE p.idPlaneacionDetalle = :idPlaneacionDetalle");
            result= query.setParameter("idPlaneacionDetalle", idPlaneacionDetalle).executeUpdate();
            em.flush();
            em.getEntityManagerFactory().getCache().evictAll();
        } catch (Exception e) {
            Logger.getLogger(PlaneacionDetalleDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            result=0;
        }
        return result;
    }

    @Override
    public Integer deletePlaneacionDetallePorIdPlaneacion(Integer idPlaneacion) {
        Integer result=0;        
        try {
            Query query = em.createQuery(
                 "Delete FROM PlaneacionDetalle p WHERE p.idPlaneacion.idPlaneacion = :idPlaneacion");
            result= query.setParameter("idPlaneacion", idPlaneacion).executeUpdate();
            em.flush();
            em.getEntityManagerFactory().getCache().evictAll();
        } catch (Exception e) {
            Logger.getLogger(PlaneacionDetalleDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            result=0;
        }
        return result;
    }


    
    
}
