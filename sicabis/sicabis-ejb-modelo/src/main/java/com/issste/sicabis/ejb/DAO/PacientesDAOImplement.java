/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Pacientes;
import com.issste.sicabis.ejb.modelo.Pacientes_;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author kriosoft
 */
@Stateless
public class PacientesDAOImplement implements PacientesDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Pacientes> obtenerPacientesByCurp(String curp) {
        List<Pacientes> resultList = null;
        try {
            resultList = em.createQuery("SELECT p FROM Pacientes p WHERE p.activo = 1 and p.curp = :curp").setParameter("curp", curp).getResultList();
        } catch (Exception e) {
            Logger.getLogger(PacientesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public void guardarPaciente(Pacientes paciente) {
        try {
            if (paciente.getIdPaciente() == null) {
                em.persist(paciente);
            } else {
                em.merge(paciente);
            }
        } catch (Exception e) {
            Logger.getLogger(PacientesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<Pacientes> obtenerPacientes() {
        List<Pacientes> resultList = null;
        try {
            resultList = em.createQuery("SELECT p FROM Pacientes p WHERE p.activo = 1 ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(TiposConvenioDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<Pacientes> buscaPacientesTypedQuery(Pacientes pacientes) {
        System.out.println("pacientes:" + pacientes.getNss());
        System.out.println("pacientes:" + pacientes.getCurp());
        CriteriaBuilder criteriaBulder = em.getCriteriaBuilder();
        CriteriaQuery<Pacientes> criteriaQuery = criteriaBulder.createQuery(Pacientes.class);
        Root<Pacientes> root = criteriaQuery.from(Pacientes.class);
        List<Predicate> predicates = new ArrayList<>();

        if (pacientes.getNss() != null && !pacientes.getNss().isEmpty() && !pacientes.getNss().trim().isEmpty()) {
            predicates.add(criteriaBulder.equal(root.get(Pacientes_.nss), pacientes.getNss().toUpperCase()));
        }
        if (pacientes.getCurp() != null && !pacientes.getCurp().isEmpty() && !pacientes.getCurp().trim().isEmpty()) {
            predicates.add(criteriaBulder.equal(root.get(Pacientes_.curp), pacientes.getCurp().toUpperCase()));
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Pacientes> q = em.createQuery(criteriaQuery);
        List<Pacientes> result = q.getResultList();
        System.out.println("buscaPacientesTypedQuery Pacientes:" + result.size());
        return result;
    }

    @Override
    public Pacientes buscaPacientePorIdPaciente(Integer idPaciente) {
        try {

            return (Pacientes) em.createQuery("SELECT p FROM Pacientes p where p.idPaciente=:idPaciente ")
                    .setParameter("idPaciente", idPaciente)
                    .getSingleResult();

        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("traeInsumosPorPaciente nre: " + nre.getMessage());

        }
        // Code for handling NonUniqueResultException

        return new Pacientes();
    }

    @Override
    public List<Pacientes> obtenerPacientesByCurpOrNSS(String curp, String NSS) {
        List<Pacientes> resultList = null;
        try {
            String query = "SELECT p FROM Pacientes p WHERE p.activo = 1 ";
            if (!curp.equals("")) {
                query = query + "AND p.nss = '" + NSS + "' ";
            }
            if (!NSS.equals("")) {
                query = query + "AND p.curp = '" + curp + "' ";
            }
            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(TiposConvenioDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

}
