/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAO;

import com.issste.sicabis.ejb.modelo.EntradasMymcqCenadiHistorico;
import com.issste.sicabis.ejb.service.silodisa.modelo.EntradasMymcqCenadi;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 9RZCBG2
 */
@Local
public interface EntradasMYMCQCenadiDAO {

    boolean guardar(EntradasMymcqCenadi dsugd);
    boolean actualizar(EntradasMymcqCenadi dsugd);
    List<EntradasMymcqCenadi> detalleEntradasMymcqCenadi(String clave);
    boolean eliminarExistencias();
    List<EntradasMymcqCenadi> getAll();
    List<EntradasMymcqCenadiHistorico> getByFiltros(Date fechaInicio, Date fechaFin,String proveedor, String registroControl, String numContratoCualquiera, String tipoEntrada,String ocOracle,  String loteCualquiera,String clave); 
}
