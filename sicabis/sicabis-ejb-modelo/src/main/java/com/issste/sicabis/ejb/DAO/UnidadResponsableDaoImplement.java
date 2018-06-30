/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.UnidadResponsable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UnidadResponsableDaoImplement implements UnidadResponsableDao {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<UnidadResponsable> obtenerUnidadesResponsables() {
        return em.createQuery("SELECT u FROM UnidadResponsable u").getResultList();
    }

}
