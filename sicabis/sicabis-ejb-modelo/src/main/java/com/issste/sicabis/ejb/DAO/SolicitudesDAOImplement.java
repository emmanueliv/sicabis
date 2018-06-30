/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Solicitudes;
import com.issste.sicabis.ejb.modelo.Solicitudes_;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.ArrayList;
import java.util.Date;
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

/**
 *
 * @author Toshiba Manolo
 */
@Stateless
public class SolicitudesDAOImplement implements SolicitudesDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Solicitudes> buscaSolicitudes() {
        List<Solicitudes> resultList = null;
        try {
            resultList = em.createNamedQuery("Solicitudes.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(UsuariosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<Solicitudes> buscaSolicitudPorNumeroSolicitud(String numeroSolicitud) {
        List<Solicitudes> resultList = null;
        try {
            resultList = em.createNamedQuery("Solicitudes.findByNumeroSolicitud").setParameter("numeroSolicitud", numeroSolicitud).getResultList();
        } catch (Exception e) {
            Logger.getLogger(TareasDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }

    @Override
    public List<Solicitudes> buscaSolicitudesPorEstatus(Integer idEstatus) {
        List<Solicitudes> resultList = null;
        try {
            Query q = em.createQuery("SELECT s FROM Solicitudes s WHERE s.idEstatus.idEstatus = :idEstatus");
            q.setParameter("idEstatus", idEstatus);

            resultList = (List<Solicitudes>) q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(RcbDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("buscaSolicitudesPorEstatus vacio");
        return null;
    }

    @Override
    public List<Solicitudes> buscadorSoicitudesTypedQuery(Solicitudes solicitud) {
        CriteriaBuilder criteriaBulder = em.getCriteriaBuilder();
        CriteriaQuery<Solicitudes> criteriaQuery = criteriaBulder.createQuery(Solicitudes.class);
        Root<Solicitudes> root = criteriaQuery.from(Solicitudes.class);
        List<Predicate> predicates = new ArrayList<>();

        if (solicitud.getNumeroSolicitud() != null) {
            predicates.add(criteriaBulder.like(root.get(Solicitudes_.numeroSolicitud), "%" + solicitud.getNumeroSolicitud().toUpperCase() + "%"));
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Solicitudes> q = em.createQuery(criteriaQuery);
        List<Solicitudes> result = q.getResultList();
        System.out.println("buscadorSoicitudesTypedQuery:" + result.size());
        return result;
    }

    @Override
    public Integer buscarPeriodoAnterior(Date fechaSolicitud) {

        try {

            System.out.println("buscarPeriodoAnterior");
            return (Integer) em.createQuery("select max(s.idSolicitud) FROM Solicitudes s where s.idTipoSolicitud.idTipoSolicitud=1 and s.fechaSolicitud <:fechaSolicitud and s.idEstatus.idEstatus=142")
                    .setParameter("fechaSolicitud", fechaSolicitud)
                    .getSingleResult();

        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("buscarPeriodoAnterior nre: " + nre.getMessage());

        }
        System.out.println("salio buscarPeriodoAnterior");
        // Code for handling NonUniqueResultException

        return 0;
    }

    @Override
    public Solicitudes buscasolictudPorId(Integer idSolicitud) {
        Solicitudes result = null;
        try {
            result = (Solicitudes) em.createNamedQuery("Solicitudes.findByIdSolicitud").setParameter("idSolicitud", idSolicitud).getSingleResult();
            em.refresh(result);
            return result;
        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("nre: " + nre.getMessage());

        }
        return new Solicitudes();
    }

    @Override
    public Solicitudes save(Solicitudes solicitud) {
        em.persist(solicitud);
        em.flush();
        return solicitud;
    }

    @Override
    public Solicitudes update(Solicitudes solicitud) {
        em.merge(solicitud);
        return solicitud;
    }

    @Override
    public Solicitudes updateEstatusSolicitud(Solicitudes solicitud) {
        try {

            Solicitudes result = em.find(Solicitudes.class, solicitud.getIdSolicitud());
            result.setIdEstatus(solicitud.getIdEstatus());
            System.out.println("Solicitudes result"+ result.getIdEstatus().getIdEstatus());
            return result;
//            return (Integer) query.executeUpdate();
        } catch (NoResultException nre) {
            return new Solicitudes();
        }
    }

    @Override
    public List<Solicitudes> buscaSolicitudesPorTipoSolicitud(Integer idTipoSolicitud) {
         List<Solicitudes> resultList = null;
        try {
            Query q = em.createQuery("SELECT s FROM Solicitudes s WHERE s.idTipoSolicitud.idTipoSolicitud = :idTipoSolicitud");
            q.setParameter("idTipoSolicitud", idTipoSolicitud);

            resultList = (List<Solicitudes>) q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(RcbDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("buscaSolicitudesPorEstatus vacio");
        return null;
    }

    @Override
    public List<Solicitudes> buscaSolicitudesPorTipoSolicitudUnidadMedica(Integer idTipoSolicitud, Integer idUnidadMedica) {
         List<Solicitudes> resultList = null;
        try {
            Query q = em.createQuery("SELECT s FROM Solicitudes s WHERE s.idTipoSolicitud.idTipoSolicitud = :idTipoSolicitud and s.idUnidadesMedicas.idUnidadesMedicas=:idUnidadMedica");
            q.setParameter("idTipoSolicitud", idTipoSolicitud);
            q.setParameter("idUnidadMedica", idUnidadMedica);
            resultList = (List<Solicitudes>) q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(RcbDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("buscaSolicitudesPorEstatus vacio");
        return null;
    }

    @Override
    public List<Solicitudes> buscaSolicitudesPorMesAnio(Integer mes, Integer anio) {
         List<Solicitudes> resultList = null;
        try {
            Query q = em.createQuery("SELECT s FROM Solicitudes s WHERE FUNCTION('MONTH',s.fechaSolicitud)=:mes and FUNCTION('YEAR',s.fechaSolicitud)=:anio and s.idEstatus.idEstatus=152");
            q.setParameter("mes", mes);
            q.setParameter("anio", anio);
            resultList = (List<Solicitudes>) q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(RcbDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("buscaSolicitudesPorMesAnio vacio");
        return new ArrayList<>();
    }

    @Override
    public List<Solicitudes> buscaSolicitudesPorMesAnioTipoSolicitud(Integer mes, Integer anio, Integer idTipoSolicitud) {
         List<Solicitudes> resultList = null;
        try {
            Query q = em.createQuery("SELECT s FROM Solicitudes s WHERE FUNCTION('MONTH',s.fechaSolicitud)=:mes and FUNCTION('YEAR',s.fechaSolicitud)=:anio and s.idEstatus.idEstatus=152 and s.idTipoSolicitud.idTipoSolicitud=:idTipoSolicitud");
            q.setParameter("mes", mes);
            q.setParameter("anio", anio);
            q.setParameter("idTipoSolicitud", idTipoSolicitud);
            resultList = (List<Solicitudes>) q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(RcbDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("buscaSolicitudesPorMesAnio vacio");
        return new ArrayList<>();
    }

    @Override
    public List<Solicitudes> buscaSolicitudesPorMesAnioTipoSolicitudArea(Integer mes, Integer anio, Integer idTipoSolicitud, Integer idArea) {
        List<Solicitudes> resultList = null;
        try {
            Query q = em.createQuery("SELECT s FROM Solicitudes s WHERE FUNCTION('MONTH',s.fechaSolicitud)=:mes and FUNCTION('YEAR',s.fechaSolicitud)=:anio and s.idEstatus.idEstatus=152 and s.idTipoSolicitud.idTipoSolicitud=:idTipoSolicitud and s.idArea.idArea=:idArea");
            q.setParameter("mes", mes);
            q.setParameter("anio", anio);
            q.setParameter("idTipoSolicitud", idTipoSolicitud);
            q.setParameter("idArea", idArea);
            resultList = (List<Solicitudes>) q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(RcbDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("buscaSolicitudesPorMesAnioTipoSolicitudArea vacio");
        return new ArrayList<>();
    }

    @Override
    public Integer updateEstatusTipoSolicitud(Solicitudes solicitud) {
         try {

            Solicitudes result = em.find(Solicitudes.class, solicitud.getIdSolicitud());
            result.setIdEstatus(solicitud.getIdEstatus());
            result.setIdTipoSolicitud(solicitud.getIdTipoSolicitud());
            System.out.println("updateEstatusTipoSolicitud result"+ result.getIdEstatus().getIdEstatus());
            return 1;
//            return (Integer) query.executeUpdate();
        } catch (NoResultException nre) {
            return 0;
        }
    }

    @Override
    public List<Solicitudes> buscaSolicitudesMensualesExtraordinarias() {
        List<Solicitudes> resultList = null;
        try {
            Query q = em.createQuery("SELECT s FROM Solicitudes s WHERE s.idTipoSolicitud.idTipoSolicitud IN (1,3) order by s.fechaSolicitud");

            resultList = (List<Solicitudes>) q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(RcbDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("buscaSolicitudesPorEstatus vacio");
        return null;
    }

    @Override
    public List<Solicitudes> buscaSolicitudesPorMesAnioTipoSolicitudesAreaEstatus(Integer mes, Integer anio, List<String> idTipoSolicitudes, Integer idArea, Integer idEstatus) {
         List<Solicitudes> resultList = null;
        try {
            Query q = em.createQuery("SELECT s FROM Solicitudes s WHERE FUNCTION('MONTH',s.fechaSolicitud)=:mes and FUNCTION('YEAR',s.fechaSolicitud)=:anio and s.idEstatus.idEstatus=:idEstatus and s.idTipoSolicitud.idTipoSolicitud IN :idTipoSolicitudes and s.idArea.idArea=:idArea");
            q.setParameter("mes", mes);
            q.setParameter("anio", anio);
            q.setParameter("idTipoSolicitudes", idTipoSolicitudes);
            q.setParameter("idArea", idArea);
            q.setParameter("idEstatus", idEstatus);
            resultList = (List<Solicitudes>) q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(RcbDAOImplemet.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("buscaSolicitudesPorMesAnioTipoSolicitudArea vacio");
        return new ArrayList<>();
    }

}
