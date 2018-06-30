/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.TipoSolicitudDAO;
import com.issste.sicabis.ejb.modelo.TipoSolicitud;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class TipoSolicitudService {
    
    @EJB
    TipoSolicitudDAO tipoSolicitudDAO;
    
    public List<TipoSolicitud> obtenerTiposSolicitud() {
        return tipoSolicitudDAO.getTiposSolicitud();
    }

    public TipoSolicitud obtenerTSByNombre(String nombre) {
        List<TipoSolicitud> list
                = tipoSolicitudDAO.obtenerTipoSolByNombre(nombre);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void guardarTS(TipoSolicitud tipoSolicitud) {
        tipoSolicitudDAO.guardarTipoSolicitud(tipoSolicitud);
    }
    
    public List<TipoSolicitud> obtenerTipoSolPorIdTipoSolicitud(Integer idTipoSolicitud) {        
        return tipoSolicitudDAO.obtenerTipoSolPorIdTipoSolicitud(idTipoSolicitud);
    }
    
}
