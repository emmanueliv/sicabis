/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.RcbGrupo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Toshiba Manolo
 */
@Stateless
public class RcbGrupoDAOImplement implements RcbGrupoDAO{    
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<RcbGrupo> getAllRcbGrupo() {
        return em.createQuery("SELECT r FROM RcbGrupo r").getResultList();    
    }

    @Override
    public RcbGrupo save(RcbGrupo rcbGrupo) {
        em.persist(rcbGrupo);
        em.flush();
        return rcbGrupo;
    }

    @Override
    public RcbGrupo update(RcbGrupo rcbGrupo) {
        em.merge(rcbGrupo);
        return rcbGrupo;
    }

    @Override
    public Integer deleteRcbGruposByIdRcb(Integer idRcb) {
         Query query = em.createQuery(
                "DELETE FROM RcbGrupo ri WHERE ri.idRcb.idRcb=:idRcb");
        return query.setParameter("idRcb", idRcb).executeUpdate();
    }
    
    
    
}
