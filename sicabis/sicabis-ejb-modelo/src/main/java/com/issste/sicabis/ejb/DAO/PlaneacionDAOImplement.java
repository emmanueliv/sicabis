/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Planeacion;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import com.issste.sicabis.ejb.modelo.Programas;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Toshiba Manolo
 */

@Stateless
public class PlaneacionDAOImplement implements PlaneacionDAO{
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Planeacion> obtenerPlaneaciones() {
         List<Planeacion> resultList = null;
        try {
            resultList = em.createNamedQuery("Planeacion.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(PlaneacionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }
    
    @Override
    public Planeacion save(Planeacion planeacion) {
        em.persist(planeacion);
        em.flush();
        return planeacion;
    }

    @Override
    public List<Planeacion> obtenerPlaneacionPorIdPlaneacion(Integer idPlaneacion) {
        List<Planeacion> resultList = null;
        try {
            resultList = em.createNamedQuery("Planeacion.findByIdPlaneacion")
                    .setParameter("idPlaneacion", idPlaneacion)
                    .getResultList();
        } catch (Exception e) {
            Logger.getLogger(PlaneacionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public Planeacion update(Planeacion planeacion) {
        em.merge(planeacion);
        return planeacion;
    }

    @Override
    public List<Planeacion> obtenerPlaneacionesPorTipoSolicitudArea(Integer idTipoSolicitud,Integer idArea) {
         List<Planeacion> resultList = null;
        try {
            resultList = em.createQuery("SELECT p FROM Planeacion p WHERE p.idArea.idArea=:idArea and p.idTipoSolicitud.idTipoSolicitud=:idTipoSolicitud ")
                    .setParameter("idArea", idArea)
                    .setParameter("idTipoSolicitud", idTipoSolicitud)
                    .getResultList();
        } catch (Exception e) {
            Logger.getLogger(PlaneacionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return new ArrayList<>();
                
    }

    @Override
    public List<Planeacion> obtenerPlaneacionesPorTipoSolicitud(Integer idTipoSolicitud) {
         List<Planeacion> resultList = null;
        try {
            resultList = em.createQuery("SELECT p FROM Planeacion p WHERE p.idTipoSolicitud.idTipoSolicitud=:idTipoSolicitud ")
                    .setParameter("idTipoSolicitud", idTipoSolicitud)
                    .getResultList();
        } catch (Exception e) {
            Logger.getLogger(PlaneacionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Planeacion> obtenerExistenciasPendiemtes(Date fechaInicial, Date fechaFinal) {
        List<Planeacion> resultList = null;
        try {
            resultList = em.createQuery("SELECT d  FROM DetalleOrdenSuministro d")
                    .getResultList();
        } catch (Exception e) {
            Logger.getLogger(PlaneacionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return new ArrayList<>();
    }

    @Override
    public Integer obtenerPlaneacionesAnteriorPorTipoSolicitudArea(Integer idTipoSolicitud, Integer idArea) {
        Integer result = 0;
        try {
            result = (Integer) em.createQuery("SELECT max(p.idPlaneacion) FROM Planeacion p WHERE p.idArea.idArea=:idArea and p.idTipoSolicitud.idTipoSolicitud=:idTipoSolicitud ")
                    .setParameter("idArea", idArea)
                    .setParameter("idTipoSolicitud", idTipoSolicitud)
                    .getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(PlaneacionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        
        if (result != null && result > 0) {
            return result;
        }
        return 0;
    }

    @Override
    public Planeacion updateParcial(Planeacion planeacion) {
         try {
            
        Planeacion result = em.find(Planeacion.class, planeacion.getIdPlaneacion());
        result.setNumeroPlaneacion(planeacion.getNumeroPlaneacion());
        return result;
//            return (Integer) query.executeUpdate();
        } catch (NoResultException nre) {
            return null;
        }
    }
     
    
}
