/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.ejb.modelo.Solicitudes;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Toshiba Manolo
 */
@Local
public interface SolicitudesDAO {
    
    List<Solicitudes> buscaSolicitudes();
    List<Solicitudes> buscaSolicitudesPorTipoSolicitud(Integer idTipoSolicitud);
    List<Solicitudes> buscaSolicitudesMensualesExtraordinarias();
    List<Solicitudes> buscaSolicitudesPorTipoSolicitudUnidadMedica(Integer idTipoSolicitud,Integer idUnidadMedica);
    List<Solicitudes> buscaSolicitudPorNumeroSolicitud(String numeroSolicitud);
    List<Solicitudes> buscaSolicitudesPorMesAnio(Integer mes, Integer anio);
    List<Solicitudes> buscaSolicitudesPorMesAnioTipoSolicitud(Integer mes, Integer anio,Integer idTipoSolicitud);
    List<Solicitudes> buscaSolicitudesPorMesAnioTipoSolicitudArea(Integer mes, Integer anio,Integer idTipoSolicitud,Integer idArea);
    List<Solicitudes> buscaSolicitudesPorMesAnioTipoSolicitudesAreaEstatus(Integer mes, Integer anio,List<String> idTipoSolicitudes,Integer idArea,Integer idEstatus);
    List<Solicitudes> buscaSolicitudesPorEstatus(Integer idEstatus);
    List<Solicitudes> buscadorSoicitudesTypedQuery(Solicitudes solicitud);
    Integer buscarPeriodoAnterior(Date fechaSolicitud);
    Solicitudes buscasolictudPorId(Integer idSolicitud);
    Solicitudes save(Solicitudes solicitud);
    Solicitudes update(Solicitudes solicitud);
    Solicitudes updateEstatusSolicitud(Solicitudes solicitud);
    Integer updateEstatusTipoSolicitud(Solicitudes solicitud);
    
}
