/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.GrupoTerapeutico;
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
public class GrupoTerapeuticoDAOImplement implements GrupoTerapeuticoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;
    
    @Override
    public List<GrupoTerapeutico> getGruposTerapeuticos() {
        List<GrupoTerapeutico> resultList = null;
        try {
            resultList = em.createNamedQuery("GrupoTerapeutico.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(GrupoTerapeuticoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<GrupoTerapeutico> obtenerGpTerapeuticoByNombre(String nombre) {
        List<GrupoTerapeutico> resultList = null;
        try {
            resultList = em.createNamedQuery("GrupoTerapeutico.findByDescripcion").setParameter("descripcion", nombre).getResultList();
        } catch (Exception e) {
            Logger.getLogger(GrupoTerapeuticoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public void guardarGrupoTerapeutico(GrupoTerapeutico gp) {
        try {
            if (gp.getIdGrupoTerapeutico() == null) {
                em.persist(gp);
            } else {
                em.merge(gp);
            }
        } catch (Exception e) {
            Logger.getLogger(GrupoTerapeuticoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }
    
}
