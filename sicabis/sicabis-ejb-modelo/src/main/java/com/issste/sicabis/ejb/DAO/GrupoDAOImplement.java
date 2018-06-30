/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Grupo;
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
public class GrupoDAOImplement implements GrupoDAO{
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override 
    public List<Grupo> obtenerGrupos() {
        return em.createQuery("SELECT g FROM Grupo g WHERE g.activo = 1").getResultList();
    }

    @Override
    public List<Grupo> obtenerGrupoByNombre(String nombreGrupo) {
        return em.createNamedQuery("Grupo.findByGrupo").setParameter("grupo", nombreGrupo).getResultList();
    }

    @Override
    public void guardarGrupo(Grupo grupo) {
        try {
            if (grupo.getIdGrupo() == null) {
                em.persist(grupo);
            } else {
                em.merge(grupo);
            }
        } catch (Exception e) {
            Logger.getLogger(GrupoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }
}
