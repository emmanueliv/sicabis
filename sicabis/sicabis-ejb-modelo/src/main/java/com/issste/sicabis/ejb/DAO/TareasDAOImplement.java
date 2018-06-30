/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Tareas;
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
public class TareasDAOImplement implements TareasDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Tareas> obtenerTareas() {
        List<Tareas> resultList = null;
        try {
            resultList = em.createNamedQuery("Tareas.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(TareasDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public void guardarTarea(Tareas tarea) {
        try {
            if(tarea.getIdTarea() == null){
                em.persist(tarea);
            } else {
                em.merge(tarea);
            }
        } catch (Exception e) {
            Logger.getLogger(TareasDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<Tareas> obtenerTareaByNombre(String nombreTarea) {
        List<Tareas> resultList = null;
        try {
            resultList = em.createNamedQuery("Tareas.findByDescripcion").setParameter("descripcion", nombreTarea).getResultList();
        } catch (Exception e) {
            Logger.getLogger(TareasDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public Tareas getByIdTarea(Integer idTarea) {
        List<Tareas> resultList = null;
        try {
            resultList = em.createNamedQuery("Tareas.findByIdTarea").getResultList();
        } catch (Exception e) {
            Logger.getLogger(TareasDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

}
