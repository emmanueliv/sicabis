/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.SolicitudesInsumosPacientesDAO;
import com.issste.sicabis.ejb.DTO.InsumosViewDto;
import com.issste.sicabis.ejb.DTO.PacientesInsumosViewDto;
import com.issste.sicabis.ejb.modelo.SolicitudesInsumosPacientes;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author Toshiba Manolo
 */
@Stateful
public class SolicitudesInsumosPacientesService {
    
    @EJB
    SolicitudesInsumosPacientesDAO solicitudesInsumosPacientesDAO ;
    
    public List<SolicitudesInsumosPacientes> getListSolInsumosPorIdSolicitud(Integer idSolicitud) {
        return solicitudesInsumosPacientesDAO.getListSolInsumosPorIdSolicitud(idSolicitud);
    }


    public SolicitudesInsumosPacientes getSolInsumoById(Integer idSolicitudesInsumosPacientes) {
        return solicitudesInsumosPacientesDAO.getSolInsumoById(idSolicitudesInsumosPacientes);
    }


    public Integer deleteSolicitudInsumosByIdSolicitudIdPaciente(Integer idSolicitud,Integer idPaciente) {
        return solicitudesInsumosPacientesDAO.deleteSolicitudInsumosByIdSolicitudIdPaciente(idSolicitud,idPaciente);
    }


    public Integer deleteSolInsumos(SolicitudesInsumosPacientes solicitudesInsumosPacientes) {
        return solicitudesInsumosPacientesDAO.deleteSolInsumos(solicitudesInsumosPacientes);
    }


    public SolicitudesInsumosPacientes guardarSolInsumo(SolicitudesInsumosPacientes solicitudesInsumosPacientes) {
        return solicitudesInsumosPacientesDAO.guardarSolInsumo(solicitudesInsumosPacientes);
    }


    public SolicitudesInsumosPacientes actualizarSolInsumo(SolicitudesInsumosPacientes solicitudesInsumosPacientes) {
         return solicitudesInsumosPacientesDAO.actualizarSolInsumo(solicitudesInsumosPacientes);
    }
    
    public List<PacientesInsumosViewDto> buscaPacientesPorIdSolicitud(Integer idSolicitud) {
        return solicitudesInsumosPacientesDAO.buscaPacientesPorIdSolicitud(idSolicitud);
    }
    
    public List<SolicitudesInsumosPacientes> buscaInsumosPorIdSolicitud(Integer idSolicitud) {
        return solicitudesInsumosPacientesDAO.buscaInsumosPorIdSolicitud(idSolicitud);
    }
    
    public List<SolicitudesInsumosPacientes> traeInsumosPorPaciente(Integer idSolicitud, Integer idPaciente) {
        return solicitudesInsumosPacientesDAO.traeInsumosPorPaciente(idSolicitud,idPaciente);
    }
    
    public Integer deleteSolicitudInsumosByIdSolicitud(Integer idSolicitud) {
        return solicitudesInsumosPacientesDAO.deleteSolicitudInsumosByIdSolicitud(idSolicitud);
    }

    
}
