/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ClasificacionProcedimiento;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ClasificacionProcedimientoDAO {
    
    List<ClasificacionProcedimiento> obtenerClasificacionProcedimiento();

    List<ClasificacionProcedimiento> obtenerTipoCPByNombre(String nombre);

    void guardarClasificacionProcedimiento(ClasificacionProcedimiento cfprod);
    
}
