/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.PlaneacionDetalleDAO;
import com.issste.sicabis.ejb.DAO.RcbDAO;
import com.issste.sicabis.ejb.modelo.PlaneacionDetalle;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Toshiba Manolo
 */
@Stateful
public class PlaneacionDetalleService {
    
    @EJB
     PlaneacionDetalleDAO planeacionDetalleDAO;
    
     public List<PlaneacionDetalle> obtenerPlaneacionDetalle(){
        return planeacionDetalleDAO.obtenerPlaneacionDetalle();
     }
     
   
    public Integer deletePlaneacionDetallePorId(Integer idPlaneacionDetalle) {
       return planeacionDetalleDAO.deletePlaneacionDetallePorId(idPlaneacionDetalle);
    }

    
    public Integer deletePlaneacionDetallePorIdPlaneacion(Integer idPlaneacion) {
         return planeacionDetalleDAO.deletePlaneacionDetallePorIdPlaneacion(idPlaneacion);
    }

    
    
}
