/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.CaracterProcedimiento;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CaracterProcedimientoDAO {
    
    List<CaracterProcedimiento> obtenerCaracterProcedimiento();

    List<CaracterProcedimiento> obtenerTipoCPByNombre(String nombre);

    void guardarcaracterCaracterProcedimiento(CaracterProcedimiento cp);
    
}
