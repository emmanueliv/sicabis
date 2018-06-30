/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ClasificacionDAO;
import com.issste.sicabis.ejb.modelo.Clasificacion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class ClasificacionService {

    @EJB
    ClasificacionDAO clasificacionDAOImpl;

    public List<Clasificacion> obtenerClasificaciones() {
        return clasificacionDAOImpl.obtenerClasificaciones();
    }

    public void guardarClasificacion(Clasificacion clasificacion) {
        clasificacionDAOImpl.guardarClasificacion(clasificacion);
    }

    public Clasificacion obtenerClasificacionByNombre(String nombreClasificacion) {
        List<Clasificacion> clasList = 
                clasificacionDAOImpl.obtenerClasificacionByNombre(nombreClasificacion);
        if(clasList.isEmpty()){
            return null;
        } else {
            return clasList.get(0);
        }
    }

}
