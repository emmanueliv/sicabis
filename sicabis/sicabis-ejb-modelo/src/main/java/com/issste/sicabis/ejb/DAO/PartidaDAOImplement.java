/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.PartidaPresupuestal;
import com.issste.sicabis.ejb.modelo.PartidaPresupuestal;
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
public class PartidaDAOImplement implements PartidaDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<PartidaPresupuestal> getPartidaPresupuestales() {
        List<PartidaPresupuestal> resultList = null;
        try {
            resultList = em.createQuery("SELECT pp FROM PartidaPresupuestal pp ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(PartidaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<PartidaPresupuestal> getPartidaPresupuestalesByActivo() {
        List<PartidaPresupuestal> resultList = null;
        try {
            resultList = em.createQuery("SELECT pp FROM PartidaPresupuestal pp WHERE pp.activo = 1 ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(PartidaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<PartidaPresupuestal> obtenerPartidaPresupuestalByNombre(String nombre) {
        return em.createQuery("SELECT pp FROM PartidaPresupuestal pp WHERE pp.descripcion = :nombre AND pp.activo = 1").setParameter("nombre", nombre).getResultList();
    }

    @Override
    public List<PartidaPresupuestal> obtenerPartidaPresupuestalByIdAndActivo(int idPartPre) {
        return em.createNativeQuery("select * from partida_presupuestal where activo = 1\n"
                + "union \n"
                + "select * from partida_presupuestal where id_partida_presupuestal = " + idPartPre + "",PartidaPresupuestal.class).getResultList();
    }

    @Override
    public void guardarPartidaPresupuestal(PartidaPresupuestal partida) {
        try {
            if (partida.getIdPartidaPresupuestal() == null) {
                em.persist(partida);
            } else {
                em.merge(partida);
            }
        } catch (Exception e) {
            Logger.getLogger(PartidaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

}
