/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.InsumosViewDto;
import com.issste.sicabis.ejb.DTO.PacientesInsumosViewDto;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Pacientes;
import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.ejb.modelo.SolicitudesInsumosPacientes;
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

/**
 *
 * @author Toshiba Manolo
 */

@Stateless
public class SolicitudesInsumosPacientesDAOImplement implements SolicitudesInsumosPacientesDAO{

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<SolicitudesInsumosPacientes> getListSolInsumosPorIdSolicitud(Integer idSolicitud) {
         List<SolicitudesInsumosPacientes> result = null;
        try {    
            result = (List<SolicitudesInsumosPacientes>) em.createQuery("SELECT s FROM SolicitudesInsumosPacientes s where s.idSolicitud.idSolicitud=:idSolicitud").setParameter("idSolicitud", idSolicitud).getResultList();
            return result;
        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("getListSolInsumosPorIdSolicitud nre: "+ nre.getMessage());

        }
         return new ArrayList<>();
    }

    @Override
    public SolicitudesInsumosPacientes getSolInsumoById(Integer idSolicitudesInsumosPacientes) {
         try {

            return (SolicitudesInsumosPacientes) em.createQuery("Select sip FROM SolicitudesInsumosPacientes sip where sip.idSolicitudesInsumosPacientes=:idSolicitudesInsumosPacientes")
                    .setParameter("idSolicitudesInsumosPacientes", idSolicitudesInsumosPacientes)
                    .getSingleResult();

        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("traeInsumosPorPaciente nre: " + nre.getMessage());

        }
         // Code for handling NonUniqueResultException

        return new SolicitudesInsumosPacientes();
    }

    @Override
    public Integer deleteSolicitudInsumosByIdSolicitudIdPaciente(Integer idSolicitud,Integer idPaciente) {
        Query query = em.createQuery(
                "DELETE FROM SolicitudesInsumosPacientes sip WHERE sip.idSolicitud.idSolicitud=:idSolicitud and sip.idPaciente.idPaciente=:idPaciente");
        return query.setParameter("idSolicitud", idSolicitud).setParameter("idPaciente", idPaciente).executeUpdate();
    }

    @Override
    public Integer deleteSolInsumos(SolicitudesInsumosPacientes solicitudesInsumosPacientes) {
          Query query = em.createQuery(
                "DELETE FROM SolicitudesInsumosPacientes sip WHERE sip.idSolicitudesInsumosPacientes=:idSolicitudesInsumosPacientes");
        return query.setParameter("idSolicitudesInsumosPacientes", solicitudesInsumosPacientes.getIdSolicitudesInsumosPacientes()).executeUpdate();
    }

    @Override
    public SolicitudesInsumosPacientes guardarSolInsumo(SolicitudesInsumosPacientes solicitudesInsumosPacientes) {        
         try {
            if (solicitudesInsumosPacientes.getIdSolicitudesInsumosPacientes() == null) {
                em.persist(solicitudesInsumosPacientes);
                em.flush();
            } else {
                em.merge(solicitudesInsumosPacientes);
            }
        } catch (Exception e) {
            Logger.getLogger(SolicitudesInsumosPacientesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return solicitudesInsumosPacientes;
    }

    @Override
    public SolicitudesInsumosPacientes actualizarSolInsumo(SolicitudesInsumosPacientes solicitudesInsumosPacientes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PacientesInsumosViewDto> buscaPacientesPorIdSolicitud(Integer idSolicitud) {
        System.out.println("idSolicitud query"+idSolicitud);
        
         List<PacientesInsumosViewDto> resultList = new ArrayList<>();
         List<Object[]> objectList = null;
        try {
             Query q = em.createQuery("SELECT s.idPaciente,count(s.idInsumo.idInsumo) FROM SolicitudesInsumosPacientes s where s.idSolicitud.idSolicitud=:idSolicitud group by s.idPaciente");
             q.setParameter("idSolicitud", idSolicitud);
             
             objectList= q.getResultList();	
             
             for (Object[] result : objectList) {                  
                Pacientes paciente = (Pacientes)result[0];
                Long  numInsumos = (Long)result[1];
                int intNuminsumos = numInsumos.intValue();
                PacientesInsumosViewDto p = new PacientesInsumosViewDto();
                p.setPaciente(paciente);
                p.setInsumosSolicitados(intNuminsumos);
                p.setIdSolicitud(idSolicitud);
                resultList.add(p);                
            }
             
             
             
        } catch (Exception e) {
            Logger.getLogger(SolicitudesInsumosPacientesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("buscaPacientesPorIdSolicitud vacio");
        return null;
    }

    @Override
    public List<SolicitudesInsumosPacientes> traeInsumosPorPaciente(Integer idSolicitud, Integer idPaciente) {
         try {

            return (List<SolicitudesInsumosPacientes>) em.createQuery("Select sip FROM SolicitudesInsumosPacientes sip where sip.idSolicitud.idSolicitud =:idSolicitud and sip.idPaciente.idPaciente=:idPaciente")
                    .setParameter("idSolicitud", idSolicitud)
                    .setParameter("idPaciente", idPaciente)
                    .getResultList();

        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.println("traeInsumosPorPaciente nre: " + nre.getMessage());

        }
         // Code for handling NonUniqueResultException

        return new ArrayList<>();
    }

    @Override
    public Integer deleteSolicitudInsumosByIdSolicitud(Integer idSolicitud) {
        Query query = em.createQuery(
                "DELETE FROM SolicitudesInsumosPacientes sip WHERE sip.idSolicitud.idSolicitud=:idSolicitud");
        return query.setParameter("idSolicitud", idSolicitud).executeUpdate();
    }

    @Override
    public List<SolicitudesInsumosPacientes> buscaInsumosPorIdSolicitud(Integer idSolicitud) {
        
           System.out.println("idSolicitud query"+idSolicitud);
        
         List<SolicitudesInsumosPacientes> resultList = new ArrayList<>();
        try {
             Query q = em.createQuery("SELECT s FROM SolicitudesInsumosPacientes s where s.idSolicitud.idSolicitud=:idSolicitud ");
             q.setParameter("idSolicitud", idSolicitud);
             
             resultList= (List<SolicitudesInsumosPacientes>) q.getResultList();	
                                                   
        } catch (Exception e) {
            Logger.getLogger(SolicitudesInsumosPacientesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("buscaInsumosPorIdSolicitud vacio");
        return null;
    }
 
        
    
}
