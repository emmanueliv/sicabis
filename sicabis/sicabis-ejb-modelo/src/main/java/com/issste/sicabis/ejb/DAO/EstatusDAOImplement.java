/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author erik
 */
@Stateless
public class EstatusDAOImplement implements EstatusDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Estatus> getAllEstatus() {
        return em.createQuery("SELECT e FROM Estatus e WHERE e.activo = 1").getResultList();
    }

    @Override
    public Estatus getRemisionEstatus(Integer e) {
        List<Estatus> resultList = null;
        try {
            resultList = em.createQuery("SELECT e FROM Estatus e WHERE e.idEstatus = :e AND e.activo = 1").setParameter("e", e).getResultList();

        } catch (Exception r) {
            Logger.getLogger(EstatusDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, r);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);

        }
        return null;
    }

    @Override
    public List<Estatus> getEstatusByTarea(int idTarea) {
        List<Estatus> resultList = null;
        try {
            resultList = em.createQuery("Select e From Estatus e Where e.idTarea.idTarea = :idTarea AND e.activo = 1").setParameter("idTarea", idTarea).getResultList();
        } catch (Exception r) {
            Logger.getLogger(EstatusDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, r);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<Estatus> getEstatusByNombre(String nombreStatus) {
        List<Estatus> resultList = null;
        try {
            resultList = em.createNamedQuery("Estatus.findByNombre").setParameter("nombre", nombreStatus).getResultList();
        } catch (Exception r) {
            Logger.getLogger(EstatusDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, r);
        }
        return resultList;
    }

    @Override
    public void guardarEstatus(Estatus estatus) {
        try {
            if (estatus.getIdEstatus() == null) {
                em.persist(estatus);
            } else {
                em.merge(estatus);
            }
        } catch (Exception e) {
            Logger.getLogger(EstatusDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<Estatus> getByEstatusIdTarea(int idTarea, String opcion) {
        List<Estatus> resultList = null;
        try {
            resultList = em.createQuery("Select e From Estatus e Where e.idEstatus " + opcion + " and e.idTarea.idTarea = :idTarea").setParameter("idTarea", idTarea).getResultList();
        } catch (Exception r) {
            Logger.getLogger(EstatusDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, r);
        }
        return resultList;
    }

}
