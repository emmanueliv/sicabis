/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Clasificacion;
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
public class ClasificacionDAOImplement implements ClasificacionDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Clasificacion> obtenerClasificaciones() {
        List<Clasificacion> resultList = null;
        try {
            resultList = em.createNamedQuery("Clasificacion.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public void guardarClasificacion(Clasificacion clasificacion) {
        try {
            if (clasificacion.getIdClasificacion() == null) {
                em.persist(clasificacion);
            } else {
                em.merge(clasificacion);
            }
        } catch (Exception e) {
            Logger.getLogger(ClasificacionDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<Clasificacion> obtenerClasificacionByNombre(String nombreClasificacion) {
        List<Clasificacion> resultList = null;
        try {
            resultList = em.createNamedQuery("Clasificacion.findByDescripcion").setParameter("descripcion", nombreClasificacion).getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

}
