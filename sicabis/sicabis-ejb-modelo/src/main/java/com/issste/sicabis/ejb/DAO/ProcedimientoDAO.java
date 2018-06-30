/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Procedimientos;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ProcedimientoDAO {
    
    boolean guardaProcedimiento(Procedimientos procedimiento);
    List<Procedimientos> obtenerByProcedimientos(Procedimientos procedimiento);
    boolean actualizaProcedimiento(Procedimientos procedimientos);
    Procedimientos obtenerByNumeroProcedimiento(String numeroProcedimiento);
    Procedimientos obtenerByNumeroProcedimientoSeguimiento(String numeroProcedimiento);
    Procedimientos obtenerByNumeroProcedimientoById(Integer idProcedimiento);
        
}
