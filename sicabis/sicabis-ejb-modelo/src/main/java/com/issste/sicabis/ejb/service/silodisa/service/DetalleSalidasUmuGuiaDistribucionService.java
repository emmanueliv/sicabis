/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.service.silodisa.DAO.DetalleSalidasUmuGuiaDistribucionDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.DetalleSalidasUmuGuiaDistribucion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author 9RZCBG2
 */
@LocalBean
@Stateless
public class DetalleSalidasUmuGuiaDistribucionService {

    @EJB
    private DetalleSalidasUmuGuiaDistribucionDAO detalleSalidasUmuGuiaDistribucionDAOImplement;

    public boolean guardar(DetalleSalidasUmuGuiaDistribucion dsugd) {
        return detalleSalidasUmuGuiaDistribucionDAOImplement.guardar(dsugd);
    }

    public boolean actualizar(DetalleSalidasUmuGuiaDistribucion dsugd) {
        return detalleSalidasUmuGuiaDistribucionDAOImplement.actualizar(dsugd);
    }

    public List<DetalleSalidasUmuGuiaDistribucion> catalogoUnidadesMedicasByUmu(String clave) {
        return detalleSalidasUmuGuiaDistribucionDAOImplement.detalleSalidasUmuGuiaDistribucionByClave(clave);
    }

    public boolean eliminarExistencias() {
        return detalleSalidasUmuGuiaDistribucionDAOImplement.eliminarExistencias();
    }
}
