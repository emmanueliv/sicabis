/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ExistenciaReservaClaveCenadiHistorico;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 9RZCBG2
 */
@Local
public interface ExistenciaReservaClaveCenadiHistoricoDAO {

    boolean guardar(ExistenciaReservaClaveCenadiHistorico hepcc);

    List<ExistenciaReservaClaveCenadiHistorico> getByFechaIngreso(Date fechaIngreso);
    List<ExistenciaReservaClaveCenadiHistorico> getByFiltros(Date fechaInicio, Date fechaFin, String tipoClave, String clave, String clave2);
    List<ExistenciaReservaClaveCenadiHistorico> getAll();
    
}
