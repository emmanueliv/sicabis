/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.service.silodisa.DAO.AlertasOperativasDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.AlertasOperativas;
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
public class AlertasOperativasService {

    @EJB
    private AlertasOperativasDAO alertasOperativasDAOImplement;

    public boolean guardar(AlertasOperativas ci) {
        return alertasOperativasDAOImplement.guardar(ci);
    }

    public boolean actualizar(AlertasOperativas ci) {
        return alertasOperativasDAOImplement.actualizar(ci);
    }

    public Integer alertasOperativasByClave(String clave) {
        return alertasOperativasDAOImplement.alertasOperativasByClave(clave);
    }

    public Integer alertasOperativasByClaveAndUmu(String clave, String umu) {
        return alertasOperativasDAOImplement.alertasOperativasByClaveAndUmu(clave, umu);
    }

    public boolean eliminarExistencias() {
        return alertasOperativasDAOImplement.eliminarExistencias();
    }

    public List<AlertasOperativas> getAll() {
        return alertasOperativasDAOImplement.getAll();
    }
}
