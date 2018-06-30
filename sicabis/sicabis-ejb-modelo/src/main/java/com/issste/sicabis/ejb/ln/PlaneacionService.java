/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.PlaneacionDAO;
import com.issste.sicabis.ejb.modelo.Planeacion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author Toshiba Manolo
 */
@Stateful
public class PlaneacionService {
    
     @EJB
     private PlaneacionDAO planeacionDao;
     
     public List<Planeacion> obtenerPlaneaciones() {
        return planeacionDao.obtenerPlaneaciones();
     }
     
    public Planeacion save(Planeacion planeacion) {
        return planeacionDao.save(planeacion);
    }
    
    public Planeacion update(Planeacion planeacion) {
        return planeacionDao.update(planeacion);
    }
      
    public List<Planeacion> obtenerPlaneacionPorIdPlaneacion(Integer idplaneacion) {
        return planeacionDao.obtenerPlaneacionPorIdPlaneacion(idplaneacion);
    }
    
    public List<Planeacion> obtenerPlaneacionesPorTipoSolicitudArea(Integer idTipoSolicitud, Integer idArea) {
        return planeacionDao.obtenerPlaneacionesPorTipoSolicitudArea(idTipoSolicitud, idArea);

    }
    
     public List<Planeacion> obtenerPlaneacionesPorTipoSolicitud(Integer idTipoSolicitud) {
        return planeacionDao.obtenerPlaneacionesPorTipoSolicitud(idTipoSolicitud);
     }
     
     
     public Integer obtenerPlaneacionesAnteriorPorTipoSolicitudArea(Integer idTipoSolicitud, Integer idArea) {
        return planeacionDao.obtenerPlaneacionesAnteriorPorTipoSolicitudArea(idTipoSolicitud, idArea);
     }
     
     public Planeacion updateParcial(Planeacion planeacion) {
       return planeacionDao.updateParcial(planeacion);
     }
     
     
    
}
