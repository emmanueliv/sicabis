/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Cr;
import com.issste.sicabis.ejb.modelo.Cr_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Toshiba Manolo
 */
@Stateless
public class CrDAOImplement implements CrDAO{
    
    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Cr> getAllCr() {
        return em.createQuery("SELECT c FROM Cr c").getResultList();    
    }

    @Override
    public Cr buscaCrPorNumCr(String numeroCr) {
        Cr result = null;
        try {    
            result = (Cr) em.createNamedQuery("Cr.findByNumeroCr").setParameter("numeroCr", numeroCr).getSingleResult();
            em.refresh(result);
            return result;
        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("nre: "+ nre.getMessage());

        }
         return new Cr();
    }


    @Override
    public List<Cr> buscaCrTypedQuery(Cr cr) {
        System.out.println("cr:"+ cr.getNumeroCr());       
        CriteriaBuilder criteriaBulder = em.getCriteriaBuilder();
        CriteriaQuery<Cr> criteriaQuery = criteriaBulder.createQuery(Cr.class);
        Root<Cr> root = criteriaQuery.from(Cr.class);
        List<Predicate> predicates = new ArrayList<>();

        if (cr.getNumeroCr()!=null) {
            predicates.add(criteriaBulder.like(root.get(Cr_.numeroCr), "%"+cr.getNumeroCr().toUpperCase()+"%"));
        }
        if(cr.getEjercicio()>0){            
           predicates.add(criteriaBulder.equal(root.get(Cr_.ejercicio), cr.getEjercicio()));
        }
        
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));               
        TypedQuery<Cr> q = em.createQuery(criteriaQuery);
        List<Cr> result = q.getResultList();
        System.out.println("buscaRcbTypedQuery Cr:"+ result.size());
        return result;
    }


    @Override
    public Cr getCrByID(Integer idCr) {
        Cr result = null;
        try {    
            System.out.println("getCrByID;");
            result = (Cr) em.createNamedQuery("Cr.findByIdCr").setParameter("idCr", idCr).getSingleResult();
            em.refresh(result);
            return result;
        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("nre: "+ nre.getMessage());

        }
         return new Cr();
    }

    @Override
    public Cr save(Cr cr) {
        em.persist(cr);
        em.flush();
        return cr;
    }

    @Override
    public Cr update(Cr cr) {
        em.merge(cr);
        return cr;
    }


    @Override
    public Integer updateCr(Cr cr) {
        try {
            
        Cr result = em.find(Cr.class, cr.getIdCr());
        result.setIdEstatus(cr.getIdEstatus());
        return 1;
//            return (Integer) query.executeUpdate();
        } catch (NoResultException nre) {
            return 0;
        }
    }

    @Override
    public Cr buscaCrPorEjercicio(Integer ejercicio,Integer idArea) {
          Cr result = null;
        try {    
            result = (Cr) em.createQuery("SELECT c FROM Cr c WHERE c.idArea.idArea=:idArea and c.ejercicio=:ejercicio ")
                    .setParameter("ejercicio", ejercicio)
                    .setParameter("idArea", idArea)
                    .getSingleResult();
            //em.refresh(result);
            return result;
        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("nre: "+ nre.getMessage());

        }
        System.out.println("salio catch");
         return new Cr();
    }
    
    
}
