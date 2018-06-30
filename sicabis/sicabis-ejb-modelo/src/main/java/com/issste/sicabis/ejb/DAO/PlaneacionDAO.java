/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Planeacion;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Toshiba Manolo
 */
@Local
public interface PlaneacionDAO {
    List<Planeacion> obtenerPlaneaciones();
    List<Planeacion> obtenerPlaneacionesPorTipoSolicitudArea(Integer idTipoSolicitud,Integer idArea);
    Integer obtenerPlaneacionesAnteriorPorTipoSolicitudArea(Integer idTipoSolicitud,Integer idArea);
    List<Planeacion> obtenerPlaneacionesPorTipoSolicitud(Integer idTipoSolicitud);
    List<Planeacion> obtenerPlaneacionPorIdPlaneacion(Integer idPlaneacion);
    List<Planeacion> obtenerExistenciasPendiemtes(Date fechaInicial,Date fechaFinal);
    Planeacion save(Planeacion planeacion);
    Planeacion update(Planeacion planeacion);
    Planeacion updateParcial(Planeacion planeacion);
}
