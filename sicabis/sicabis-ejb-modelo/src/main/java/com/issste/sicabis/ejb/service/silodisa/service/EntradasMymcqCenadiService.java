/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.modelo.EntradasMymcqCenadiHistorico;
import com.issste.sicabis.ejb.service.silodisa.DAO.EntradasMYMCQCenadiDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.EntradasMymcqCenadi;
import java.util.Date;
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
public class EntradasMymcqCenadiService {

    @EJB
    private EntradasMYMCQCenadiDAO entradasMYMCQCenadiDAOImplement;

    public boolean guardar(EntradasMymcqCenadi emc) {
        return entradasMYMCQCenadiDAOImplement.guardar(emc);
    }

    public boolean actualizar(EntradasMymcqCenadi emc) {
        return entradasMYMCQCenadiDAOImplement.actualizar(emc);
    }

    public List<EntradasMymcqCenadi> detalleEntradasMymcqCenadi(String clave) {
        return entradasMYMCQCenadiDAOImplement.detalleEntradasMymcqCenadi(clave);
    }

    public boolean eliminarExistencias() {
        return entradasMYMCQCenadiDAOImplement.eliminarExistencias();
    }

    public List<EntradasMymcqCenadi> getAll() {
        return entradasMYMCQCenadiDAOImplement.getAll();
    }

    public List<EntradasMymcqCenadiHistorico> getByFiltros(Date fechaInicio, Date fechaFin, String proveedor, String registroControl, String numContratoCualquiera, String tipoEntrada,String ocOracle, String loteCualquiera, String clave) {
        return entradasMYMCQCenadiDAOImplement.getByFiltros(fechaInicio, fechaFin, proveedor, registroControl, numContratoCualquiera, tipoEntrada ,ocOracle, loteCualquiera, clave);
    }

}
