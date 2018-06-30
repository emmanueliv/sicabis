/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.SolicitudesDAO;
import com.issste.sicabis.ejb.modelo.Solicitudes;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author Toshiba Manolo
 */
@Stateless
public class SolicitudesService {
    
        @EJB
        SolicitudesDAO solicitudesDAO ;
        
      
    public List<Solicitudes> buscaSolicitudes() {
       return solicitudesDAO.buscaSolicitudes();
    }

   
    public List<Solicitudes> buscaSolicitudPorNumeroSolicitud(String numeroSolicitud) {
        return solicitudesDAO.buscaSolicitudPorNumeroSolicitud(numeroSolicitud);
    }

    
    public List<Solicitudes> buscaSolicitudesPorEstatus(Integer idEstatus) {
       return solicitudesDAO.buscaSolicitudesPorEstatus(idEstatus);
    }

    
    public List<Solicitudes> buscadorSoicitudesTypedQuery(Solicitudes solicitud) {
        return solicitudesDAO.buscadorSoicitudesTypedQuery(solicitud);
                
    }

    
    public Integer buscarPeriodoAnterior(Date fechaSolicitud) {
       return solicitudesDAO.buscarPeriodoAnterior(fechaSolicitud);
    }

   
    public Solicitudes buscasolictudPorId(Integer idSolicitud) {
        return solicitudesDAO.buscasolictudPorId(idSolicitud);
    }

   
    public Solicitudes save(Solicitudes solicitud) {
        return solicitudesDAO.save(solicitud);
    }

   
    public Solicitudes update(Solicitudes solicitud) {
        return solicitudesDAO.update(solicitud);
    }

    
    public Solicitudes updateEstatusSolicitud(Solicitudes solicitud) {
       return solicitudesDAO.updateEstatusSolicitud(solicitud);
    }
    
    public Integer updateEstatusTipoSolicitud(Solicitudes solicitud) {
           return solicitudesDAO.updateEstatusTipoSolicitud(solicitud);
    }
    
     public List<Solicitudes> buscaSolicitudesPorTipoSolicitud(Integer idTipoSolicitud) {
       return solicitudesDAO.buscaSolicitudesPorTipoSolicitud(idTipoSolicitud);
    }
     
     public List<Solicitudes> buscaSolicitudesMensualesExtraordinarias() {
         return solicitudesDAO.buscaSolicitudesMensualesExtraordinarias();
     }
     
    public List<Solicitudes> buscaSolicitudesPorTipoSolicitudUnidadMedica(Integer idTipoSolicitud, Integer idUnidadMedica) {
        return solicitudesDAO.buscaSolicitudesPorTipoSolicitudUnidadMedica(idTipoSolicitud, idUnidadMedica);
    }
    
    public List<Solicitudes> buscaSolicitudesPorMesAnio(Integer mes, Integer anio){
        return solicitudesDAO.buscaSolicitudesPorMesAnio(mes, anio);
    }
    
    public List<Solicitudes> buscaSolicitudesPorMesAnioTipoSolicitud(Integer mes, Integer anio, Integer idTipoSolicitud) {
        return solicitudesDAO.buscaSolicitudesPorMesAnioTipoSolicitud(mes, anio, idTipoSolicitud);
    }
    
    public List<Solicitudes> buscaSolicitudesPorMesAnioTipoSolicitudArea(Integer mes, Integer anio, Integer idTipoSolicitud, Integer idArea) {
        return solicitudesDAO.buscaSolicitudesPorMesAnioTipoSolicitudArea(mes, anio, idTipoSolicitud, idArea);
    }
    
    public List<Solicitudes> buscaSolicitudesPorMesAnioTipoSolicitudesAreaEstatus(Integer mes, Integer anio, List<String> idTipoSolicitudes, Integer idArea, Integer idEstatus) {
        return solicitudesDAO.buscaSolicitudesPorMesAnioTipoSolicitudesAreaEstatus(mes, anio, idTipoSolicitudes, idArea, idEstatus);
    }
    
}
