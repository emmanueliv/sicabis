/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ExistenciaUmusProgramasHistoricoDAO;
import com.issste.sicabis.ejb.modelo.CatInsumosHistorico;
import com.issste.sicabis.ejb.modelo.ExistenciaUmusProgramasHistorico;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
@LocalBean
public class ExistenciaUmusProgramasHistoricoService {

    @EJB
    private ExistenciaUmusProgramasHistoricoDAO existenciaUmusProgramasHistoricoDAOImplement;

    public boolean guardar(ExistenciaUmusProgramasHistorico cih) {
        return existenciaUmusProgramasHistoricoDAOImplement.guardar(cih);
    }

    public List<ExistenciaUmusProgramasHistorico> getByFechaIngreso(Date fechaIngreso) {
        return existenciaUmusProgramasHistoricoDAOImplement.getByFechaIngreso(fechaIngreso);
    }

    public List<ExistenciaUmusProgramasHistorico> getAll() {
        return existenciaUmusProgramasHistoricoDAOImplement.getAll();
    }

    public List<ExistenciaUmusProgramasHistorico> buscarByFiltros(Date fechaInicial, Date fechaFinal, String delegacion, String numeroUmu, String clave, String nombreClave) {
        return existenciaUmusProgramasHistoricoDAOImplement.buscarByFiltros(fechaInicial, fechaFinal, delegacion, numeroUmu, clave, nombreClave);
    }
}
