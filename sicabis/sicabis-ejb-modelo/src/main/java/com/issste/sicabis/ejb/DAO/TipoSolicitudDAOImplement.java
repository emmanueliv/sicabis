/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoSolicitud;
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
public class TipoSolicitudDAOImplement implements TipoSolicitudDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<TipoSolicitud> getTiposSolicitud() {
        List<TipoSolicitud> resultList = null;
        try {
            resultList = (List<TipoSolicitud>) em.createQuery("SELECT t FROM TipoSolicitud t WHERE t.activo = 1").getResultList();
        } catch (Exception e) {
            Logger.getLogger(TipoSolicitudDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<TipoSolicitud> obtenerTipoSolByNombre(String nombre) {
        List<TipoSolicitud> resultList = null;
        try {
            resultList = em.createQuery("SELECT t FROM TipoSolicitud t WHERE t.descripcion = :descripcion AND t.activo = 1").setParameter("descripcion", nombre).getResultList();
        } catch (Exception e) {
            Logger.getLogger(TipoSolicitudDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public void guardarTipoSolicitud(TipoSolicitud TipoSolicitud) {
        try {
            if (TipoSolicitud.getIdTipoSolicitud() == null) {
                em.persist(TipoSolicitud);
            } else {
                em.merge(TipoSolicitud);
            }
        } catch (Exception e) {
            Logger.getLogger(TipoSolicitudDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<TipoSolicitud> obtenerTipoSolPorIdTipoSolicitud(Integer idTipoSolicitud) {
        List<TipoSolicitud> resultList = null;
        try {
            resultList = em.createQuery("SELECT t FROM TipoSolicitud t WHERE t.idTipoSolicitud=:idTipoSolicitud").setParameter("idTipoSolicitud", idTipoSolicitud).getResultList();
        } catch (Exception e) {
            Logger.getLogger(TipoSolicitudDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

}