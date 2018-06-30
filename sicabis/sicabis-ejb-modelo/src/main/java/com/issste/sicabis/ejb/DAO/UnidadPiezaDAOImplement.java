/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.UnidadPieza;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Toshiba Manolo
 */
@Stateless
public class UnidadPiezaDAOImplement implements UnidadPiezaDAO{
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<UnidadPieza> getAllUnidadPiezas() {
        return em.createQuery("SELECT up FROM UnidadPieza up").getResultList();    
    }
    
}
