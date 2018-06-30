/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Fallos;
import com.issste.sicabis.ejb.modelo.Insumos;
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
 * @author erik
 */
@Stateless
public class InsumosDAOImplemet implements InsumosDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Insumos> obtenerListaInsumos() {
        return em.createQuery("SELECT i FROM Insumos i").getResultList();
    }

    @Override
    public List<Insumos> buscarInsumosPorClave(String clave) {
        System.out.println("buscarInsumosPorClave:" + clave);
        Query q = em.createQuery("SELECT ai.idInsumo FROM AsignacionInsumos ai where ai.idInsumo.clave like :clave ORDER BY ai.idInsumo.clave");
        q.setParameter("clave", clave + "%");
        return (List<Insumos>) q.getResultList();
    }

    @Override
    public List<Insumos> obtenerListaInsumosPorArea(Integer idArea) {
        Query q = em.createQuery("SELECT ai.idInsumo FROM AsignacionInsumos ai where ai.idArea.idArea=:idArea");
        q.setParameter("idArea", idArea);
        return (List<Insumos>) q.getResultList();
    }

    @Override
    public List<Insumos> obtenerListaInsumosPorAreaGrupo(Integer idArea, Integer idGrupo) {
        Query q = em.createQuery("SELECT ai.idInsumo FROM AsignacionInsumos ai WHERE ai.idArea.idArea = :idArea "
                + "AND ai.idInsumo.idGrupo.idGrupo = :idGrupo");
        q.setParameter("idArea", idArea);
        q.setParameter("idGrupo", idGrupo);
        return (List<Insumos>) q.getResultList();
    }

    @Override
    public List<Insumos> obtenerListaInsumosPorAreaGrupos(Integer idArea, List<String> listGrupos) {
        Query q = em.createQuery("SELECT ai.idInsumo FROM AsignacionInsumos ai WHERE ai.idArea.idArea = :idArea "
                + "AND ai.idInsumo.idGrupo.idGrupo IN :listGrupos");
        q.setParameter("idArea", idArea);
        q.setParameter("listGrupos", listGrupos);
        return (List<Insumos>) q.getResultList();
    }

    @Override
    public Integer idInsumoByClave(String clave) {
        Integer idInsumo = 0;
        try {
            idInsumo = (Integer) em.createQuery("Select i.idInsumo From Insumos i "
                    + "Where i.clave = '" + clave + "'").getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(InsumosDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (idInsumo != null) {
            return idInsumo;
        }
        return null;
    }

    @Override
    public List<Insumos> inusmos(String clave) {
        List<Insumos> listaInsumos = null;
        try {
            listaInsumos = em.createQuery("Select i.idInsumo From Insumos i "
                    + "Where i.clave = '" + clave + "'").getResultList();
        } catch (Exception e) {
            Logger.getLogger(InsumosDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (listaInsumos != null) {
            return listaInsumos;
        }
        return null;
    }

    @Override
    public List<Insumos> buscarInsumosPorClaveLike(String clave) {
        System.out.println("buscarInsumosPorClave:" + clave);
        Query q = em.createQuery("SELECT i FROM Insumos i where i.clave LIKE :clave");
        q.setParameter("clave", "%" + clave + "%");
        return (List<Insumos>) q.getResultList();
    }

    @Override
    public Insumos obtieneByClave(String clave) {
        List<Insumos> resultList = null;
        try {
            resultList = em.createQuery("SELECT i FROM Insumos i WHERE i.clave = :clave ").setParameter("clave", clave).getResultList();
        } catch (Exception e) {
            Logger.getLogger(InsumosDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;

    }

    @Override
    public boolean save(Insumos insumo) {
        try {
            em.merge(insumo);
            return true;
        } catch (Exception e) {
            Logger.getLogger(InsumosDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public boolean actualiza(Insumos insumo) {
        try {
            em.merge(insumo);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(InsumosDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

}
