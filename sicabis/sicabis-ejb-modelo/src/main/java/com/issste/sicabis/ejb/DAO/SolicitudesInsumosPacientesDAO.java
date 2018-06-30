/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.InsumosViewDto;
import com.issste.sicabis.ejb.DTO.PacientesInsumosViewDto;
import com.issste.sicabis.ejb.modelo.CrInsumos;
import com.issste.sicabis.ejb.modelo.SolicitudesInsumosPacientes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Toshiba Manolo
 */
@Local
public interface SolicitudesInsumosPacientesDAO {
    
    List<SolicitudesInsumosPacientes> getListSolInsumosPorIdSolicitud(Integer idSolicitud);
    SolicitudesInsumosPacientes getSolInsumoById(Integer idSolicitudesInsumosPacientes);
    Integer deleteSolicitudInsumosByIdSolicitudIdPaciente(Integer idSolicitud ,Integer idPaciente);
    Integer deleteSolicitudInsumosByIdSolicitud(Integer idSolicitud);
    Integer deleteSolInsumos(SolicitudesInsumosPacientes solicitudesInsumosPacientes);
    SolicitudesInsumosPacientes guardarSolInsumo(SolicitudesInsumosPacientes solicitudesInsumosPacientes);
    SolicitudesInsumosPacientes actualizarSolInsumo(SolicitudesInsumosPacientes solicitudesInsumosPacientes);
    List<PacientesInsumosViewDto> buscaPacientesPorIdSolicitud(Integer idSolicitud);
    List<SolicitudesInsumosPacientes> buscaInsumosPorIdSolicitud(Integer idSolicitud);
    List<SolicitudesInsumosPacientes> traeInsumosPorPaciente(Integer idSolicitud, Integer idPaciente);
    
}
