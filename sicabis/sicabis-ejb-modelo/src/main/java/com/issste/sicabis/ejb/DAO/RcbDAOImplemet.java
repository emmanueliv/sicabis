/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.ejb.modelo.Rcb_;
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
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class RcbDAOImplemet implements RcbDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Rcb> getAllRcb() {
        return em.createQuery("SELECT r FROM Rcb r").getResultList();
    }

    @Override
    public List<Rcb> getRcbByNumRcb(String numeroRcb) {

        List<Rcb> resultList = null;
        try {
            Query q = em.createQuery("SELECT r FROM Rcb r WHERE r.numero = :numeroRcb");
            q.setParameter("numeroRcb", numeroRcb);

            resultList = (List<Rcb>) q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(RcbDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("getRcbByNumRcb vacio");
        return null;

    }

    @Override
    public Integer getMaxRCB() {
        return (Integer) em.createQuery("select max(r.idRcb) from Rcb r").getSingleResult();
    }

    @Override
    public Rcb getRCBByID(Integer idRcb) {
        Rcb result = null;
        try {
            result = (Rcb) em.createNamedQuery("Rcb.findByIdRcb").setParameter("idRcb", idRcb).getSingleResult();
            em.refresh(result);
            return result;
        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("nre: " + nre.getMessage());

        }
        // Code for handling NonUniqueResultException

        return new Rcb();
    }

    public Rcb getRCByNum(String numero) {
        Rcb result = null;
        try {
            result = (Rcb) em.createNamedQuery("Rcb.findByNumero").setParameter("numero", numero).getSingleResult();
            em.refresh(result);
            return result;
        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("nre: " + nre.getMessage());

        }
        // Code for handling NonUniqueResultException

        return new Rcb();
    }

    @Override
    public boolean save(Rcb rcb) {
        try {
            em.persist(rcb);
            em.flush();
            return true;
        } catch (Exception e) {
            Logger.getLogger(RcbDAOImplemet.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
//        return rcb;
    }

    @Override
    public Integer getAnteriorRCB(Integer idRcb) {
        Query query = em.createQuery("SELECT r.idRcb FROM Rcb r WHERE r.idRcb < :idRcb ORDER BY r.idRcb DESC");
        query.setParameter("idRcb", idRcb);
        try {
            return (Integer) query.setMaxResults(1).getSingleResult();
        } catch (NoResultException nre) {
            return 0;
        }

    }

    @Override
    public Rcb update(Rcb rcb) {
        em.merge(rcb);
        return rcb;
    }

    @Override
    public Integer updateEstatusRcb(Rcb rcb) {
        Query query = em.createQuery("UPDATE Rcb r SET r.idEstatus =:idEstatus where r.idRcb =:idRcb");
        query.setParameter("idEstatus", rcb.getIdEstatus());
        query.setParameter("idRcb", rcb.getIdRcb());
        try {
            return (Integer) query.executeUpdate();
        } catch (NoResultException nre) {
            return 0;
        }
    }

    @Override
    public List<Rcb> getRcbByEstatus(Integer idEstatus) {
        List<Rcb> resultList = null;
        try {
            Query q = em.createQuery("SELECT r FROM Rcb r WHERE r.idEstatus.idEstatus = :idEstatus");
            q.setParameter("idEstatus", idEstatus);

            resultList = (List<Rcb>) q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(RcbDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("getRcbByEstatus vacio");
        return null;
    }

    @Override
    public Integer updateRcb(Rcb rcb) {
//        Query query = em.createQuery("UPDATE Rcb r SET  r=:idRcb");
//        query.setParameter("idRcb", rcb);
        try {

            Rcb result = em.find(Rcb.class, rcb.getIdRcb());
            result.setIdEstatus(rcb.getIdEstatus());
            result.setNep(rcb.getNep());
            result.setOficioSuficienciaPresupuestal(rcb.getOficioSuficienciaPresupuestal());
            result.setFechaOficioSuficiencia(rcb.getFechaOficioSuficiencia());
            result.setDiasOficio(rcb.getDiasOficio());
            result.setNumeroOficio(rcb.getNumeroOficio());
            result.setClave(rcb.getClave());
            result.setDestino(rcb.getDestino());

            return 1;
//            return (Integer) query.executeUpdate();
        } catch (NoResultException nre) {
            return 0;
        }
    }

    @Override
    public List<Rcb> buscaRcbPorNumRcb(String numeroRcb, Integer idArea) {
        System.out.println("buscarInsumosPorClave:" + numeroRcb);
        String query = "SELECT r FROM Rcb r WHERE r.numero LIKE :numeroRcb";
        if (idArea != null && idArea != 1) {
            query = query + "AND r.idArea : idArea";
        }
        Query q = em.createQuery(query);
        q.setParameter("numeroRcb", "%" + numeroRcb + "%");
        q.setParameter("idArea", idArea);
        return (List<Rcb>) q.getResultList();
    }

    @Override
    public List<Rcb> buscaRcbTypedQuery(Rcb rcb) {
        System.out.println("rcb numero:" + rcb.getNumero());
        System.out.println("rcb estatus:" + rcb.getIdEstatus().getIdEstatus());
        System.out.println("rcb: periodo:" + rcb.getPeriodo());
        CriteriaBuilder criteriaBulder = em.getCriteriaBuilder();
        CriteriaQuery<Rcb> criteriaQuery = criteriaBulder.createQuery(Rcb.class);
        Root<Rcb> root = criteriaQuery.from(Rcb.class);
        List<Predicate> predicates = new ArrayList<>();

        if (rcb.getNumero() != null) {
            predicates.add(criteriaBulder.like(root.get(Rcb_.numero), "%" + rcb.getNumero().toUpperCase() + "%"));
        }
        if (rcb.getIdArea() != null && rcb.getIdArea().getIdArea() != 10) {
            predicates.add(criteriaBulder.equal(root.get(Rcb_.idArea), rcb.getIdArea()));
        }
        if (rcb.getPeriodo() > 0) {
            predicates.add(criteriaBulder.equal(root.get(Rcb_.periodo), rcb.getPeriodo()));
        }
        if (rcb.getIdEstatus().getIdEstatus() > 10) {
            predicates.add(criteriaBulder.equal(root.get(Rcb_.idEstatus), rcb.getIdEstatus()));
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Rcb> q = em.createQuery(criteriaQuery);
        List<Rcb> result = q.getResultList();
        System.out.println("buscaRcbTypedQuery:" + result.size());
        return result;
    }

    @Override
    public List<Rcb> getRcbProcedimiento() {
        List<Rcb> resultList = null;
        try {
            Query q = em.createQuery("SELECT r FROM Rcb r WHERE r.numero = :numeroRcb");

            resultList = (List<Rcb>) q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(RcbDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("getRcbByNumRcb vacio");
        return null;
    }

    @Override
    public List<Rcb> getRcbPendientes() {
        List<Rcb> resultList = null;
        try {
            resultList = em.createNativeQuery("SELECT DISTINCT r.* \n"
                    + "  FROM rcb r JOIN rcb_insumos ri ON (r.id_rcb = ri.id_rcb) \n"
                    + "       LEFT JOIN procedimiento_rcb pr ON (pr.id_rcb_insumos = ri.id_rcb_insumos) \n"
                    + "WHERE pr.id_rcb_insumos IS NULL \n"
                    + "  AND r.id_estatus = 16 \n"
                    + "  AND r.activo = 1 \n"
                    + "  AND ri.activo = 1 \n"
                    + "ORDER BY r.fecha_elaboracion ", Rcb.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(RcbDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

}
