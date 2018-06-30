/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Clausulado;
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
public class ClausuladoDAOImplement implements ClausuladoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Clausulado> obtenerClausulados() {
        List<Clausulado> resultList = null;
        try {
            resultList = em.createNamedQuery("Clausulado.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ClausuladoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<Clausulado> obtenerClausuladoByTipo(Integer tipo) {
        List<Clausulado> resultList = null;
        try {
            resultList = em.createNamedQuery("Clausulado.findByIdClausulado").
                    setParameter("idClausulado", tipo).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ClausuladoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public void modificarClausulado(Clausulado clausulado) {
        try {
            em.merge(clausulado);
        } catch (Exception e) {
            Logger.getLogger(ClausuladoDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

}
