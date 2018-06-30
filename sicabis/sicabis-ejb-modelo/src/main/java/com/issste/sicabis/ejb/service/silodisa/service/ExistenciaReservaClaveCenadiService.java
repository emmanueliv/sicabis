/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.service.silodisa.DAO.ExistenciaReservaPorClaveCenadDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaReservaClaveCenadi;
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
public class ExistenciaReservaClaveCenadiService {

    @EJB
    private ExistenciaReservaPorClaveCenadDAO existenciaReservaPorClaveCenadDAOImplement;

    public boolean guardar(ExistenciaReservaClaveCenadi ercc) {
        return existenciaReservaPorClaveCenadDAOImplement.guardar(ercc);
    }

    public boolean actualizar(ExistenciaReservaClaveCenadi ercc) {
        return existenciaReservaPorClaveCenadDAOImplement.actualizar(ercc);
    }

    public List<ExistenciaReservaClaveCenadi> detalleExistenciaReservaClaveCenadi(String clave) {
        return existenciaReservaPorClaveCenadDAOImplement.detalleExistenciaReservaClaveCenadi(clave);
    }

    public boolean eliminarExistencias() {
        return existenciaReservaPorClaveCenadDAOImplement.eliminarExistencias();
    }

    public List<ExistenciaReservaClaveCenadi> existenciasReservaAll() {
        return existenciaReservaPorClaveCenadDAOImplement.existenciaReservaAll();
    }
}
