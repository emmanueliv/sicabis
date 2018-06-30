/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.service.silodisa.DAO.ExistenciasPorClaveCenadiDAO;
import com.issste.sicabis.ejb.service.silodisa.DAOImplement.ExistenciasPorClaveCenadiDAOImplement;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveCenadi;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author 5XD9BG2
 */
@LocalBean
@Stateless
public class ExistenciasPorClaveCenadiService {

    @EJB
    private ExistenciasPorClaveCenadiDAO existenciasPorClaveCenadiDAOImplement;

    public boolean guardar(ExistenciaPorClaveCenadi epcc) {
        return existenciasPorClaveCenadiDAOImplement.guardar(epcc);
    }

    public boolean actualizar(ExistenciaPorClaveCenadi epcc) {
        return existenciasPorClaveCenadiDAOImplement.actualizar(epcc);
    }

    public List<ExistenciaPorClaveCenadi> existenciaPorClaveCenadiByClave(String clave) {
        return existenciasPorClaveCenadiDAOImplement.existenciaPorClaveCenadiByClave(clave);
    }

    public Integer existenciaSumPorClaveCenadiByClave(String clave) {
        return existenciasPorClaveCenadiDAOImplement.existenciaSumPorClaveCenadiByClave(clave);
    }

    public boolean eliminarExistencias() {
        return existenciasPorClaveCenadiDAOImplement.eliminarExistencias();
    }

    public List<ExistenciaPorClaveCenadi> existenciaAll() {
        return existenciasPorClaveCenadiDAOImplement.exitenciaAll();
    }
}
