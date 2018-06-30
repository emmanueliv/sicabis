/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoSolicitud;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriosoft
 */
@Local
public interface TipoSolicitudDAO {

    List<TipoSolicitud> getTiposSolicitud();

    List<TipoSolicitud> obtenerTipoSolByNombre(String nombre);
    
    List<TipoSolicitud> obtenerTipoSolPorIdTipoSolicitud(Integer idTipoSolicitud);

    void guardarTipoSolicitud(TipoSolicitud tipoSolicitud);
    
}
