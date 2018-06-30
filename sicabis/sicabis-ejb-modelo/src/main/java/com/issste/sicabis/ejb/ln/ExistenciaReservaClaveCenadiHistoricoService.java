/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ExistenciaReservaClaveCenadiHistoricoDAO;
import com.issste.sicabis.ejb.modelo.ExistenciaReservaClaveCenadiHistorico;
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
public class ExistenciaReservaClaveCenadiHistoricoService {

    @EJB
    private ExistenciaReservaClaveCenadiHistoricoDAO existenciaReservaClaveCenadiHistoricoDAOImplement;

    public boolean guardar(ExistenciaReservaClaveCenadiHistorico ercch) {
        return existenciaReservaClaveCenadiHistoricoDAOImplement.guardar(ercch);
    }

    public List<ExistenciaReservaClaveCenadiHistorico> getByFechaIngreso(Date fechaIngreso) {
        return existenciaReservaClaveCenadiHistoricoDAOImplement.getByFechaIngreso(fechaIngreso);
    }

    public List<ExistenciaReservaClaveCenadiHistorico> getByFiltros(Date fechaInicio, Date fechaFin, String tipoClave, String clave, String clave2) {
        return existenciaReservaClaveCenadiHistoricoDAOImplement.getByFiltros(fechaInicio, fechaFin, tipoClave, clave,clave2);
    }

    public List<ExistenciaReservaClaveCenadiHistorico> getAll() {
        return existenciaReservaClaveCenadiHistoricoDAOImplement.getAll();
    }
}
