/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.service.silodisa.DAO.ExistenciasUMUsProgramasDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaUmusProgramas;
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
public class ExistenciasUMUsProgramasService {

    @EJB
    private ExistenciasUMUsProgramasDAO existenciasUMUsProgramasDAOImplement;

    public boolean guardar(ExistenciaUmusProgramas emc) {
        return existenciasUMUsProgramasDAOImplement.guardar(emc);
    }

    public boolean actualizar(ExistenciaUmusProgramas emc) {
        return existenciasUMUsProgramasDAOImplement.actualizar(emc);
    }

    public List<ExistenciaUmusProgramas> detalleExistenciaUmusProgramas(String clave) {
        return existenciasUMUsProgramasDAOImplement.detalleExistenciaUmusProgramas(clave);
    }

    public boolean eliminarExistencias() {
        return existenciasUMUsProgramasDAOImplement.eliminarExistencias();
    }

    public List<ExistenciaUmusProgramas> getAll() {
        return existenciasUMUsProgramasDAOImplement.getAll();
    }
}
