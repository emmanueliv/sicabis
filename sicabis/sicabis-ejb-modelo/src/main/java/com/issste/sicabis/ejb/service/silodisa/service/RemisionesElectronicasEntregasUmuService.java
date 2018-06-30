/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.service.silodisa.DAO.RemisionesElectronicasEntregasUmuDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.RemisionesElectronicasEntregasUmu;
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
public class RemisionesElectronicasEntregasUmuService {

    @EJB
    private RemisionesElectronicasEntregasUmuDAO remisionesElectronicasEntregasUmuDAOImplement;

    public boolean guardar(RemisionesElectronicasEntregasUmu reeu) {
        return remisionesElectronicasEntregasUmuDAOImplement.guardar(reeu);
    }

    public boolean actualizar(RemisionesElectronicasEntregasUmu reeu) {
        return remisionesElectronicasEntregasUmuDAOImplement.actualizar(reeu);
    }

    public List<RemisionesElectronicasEntregasUmu> remisionesElectronicasEntregasUmuByClave(String clave) {
        return remisionesElectronicasEntregasUmuDAOImplement.remisionesElectronicasEntregasUmuByClave(clave);
    }

    public boolean eliminarExistencias() {
        return remisionesElectronicasEntregasUmuDAOImplement.eliminarExistencias();
    }
}
