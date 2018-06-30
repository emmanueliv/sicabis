/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kriosoft
 */
@Stateless
public class DelegacionesDAOImplement implements DelegacionesDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Delegaciones> obtenerDelegaciones() {
        List<Delegaciones> resultList = null;
        try {
            resultList = em.createQuery("Select d FROM Delegaciones d ORDER BY d.nombreDelegacion").getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }
    
        @Override
    public Delegaciones obtenerDelegacionporId(Integer idDelegacion) {
         Delegaciones result = null;
        try {    
            result = (Delegaciones) em.createNamedQuery("Delegaciones.findByIdDelegacion").setParameter("idDelegacion", idDelegacion).getSingleResult();
            em.refresh(result);
            return result;
        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("nre: "+ nre.getMessage());

        }
         return new Delegaciones();
    }

    @Override
    public Delegaciones obtenerDelegacionporNombre(String nombre) {
                 Delegaciones result = null;
        try {    
            result = (Delegaciones) em.createNamedQuery("Delegaciones.findByNombreDelegacion").setParameter("nombreDelegacion", nombre).getSingleResult();
            em.refresh(result);
            return result;
        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("nre: "+ nre.getMessage());

        }
         return new Delegaciones();

    }

}
