/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.service.silodisa.DAO.ClavesPorCodigoBarrasDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.ClavesPorCodigoBarras;
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
public class ClavesPorCodigoBarrasService {

    @EJB
    private ClavesPorCodigoBarrasDAO clavesPorCodigoBarrasDAOImplement;

    public boolean guardar(ClavesPorCodigoBarras ci) {
        return clavesPorCodigoBarrasDAOImplement.guardar(ci);
    }

    public boolean actualizar(ClavesPorCodigoBarras ci) {
        return clavesPorCodigoBarrasDAOImplement.actualizar(ci);
    }

    public List<ClavesPorCodigoBarras> alertasOperativasByClave(String clave) {
        return clavesPorCodigoBarrasDAOImplement.clavesPorCodigoBarrasByClave(clave);
    }

    public boolean eliminarExistencias() {
        return clavesPorCodigoBarrasDAOImplement.eliminarExistencias();
    }
}
