/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Nivel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Toshiba Manolo
 */
@Stateless
public class NivelDAOImplement implements NivelDAO{
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Nivel> getAllNivel() {
        return em.createQuery("SELECT n FROM Nivel n").getResultList();    
    }
    
}
