/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.EntradasMymcqCenadiHistorico;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 9RZCBG2
 */
@Local
public interface EntradasMymcqCenadiHistoricoDAO {

    boolean guardar(EntradasMymcqCenadiHistorico emch);
    List<EntradasMymcqCenadiHistorico> getByFechaIngreso(Date fechaIngreso);
    List<EntradasMymcqCenadiHistorico> getAll();
    List<String> getDistinctProveedor();
    List<String> getDistinctRegistroControl();
    List<String> getDistinctTipoEntrada();
    List<String> getDistinctOcOracle();
    List<String> getDistinctClave();
    
}
