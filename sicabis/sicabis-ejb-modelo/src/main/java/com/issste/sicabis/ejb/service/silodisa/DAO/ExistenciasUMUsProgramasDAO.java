/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAO;

import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaUmusProgramas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 9RZCBG2
 */
@Local
public interface ExistenciasUMUsProgramasDAO {

    boolean guardar(ExistenciaUmusProgramas dsugd);

    boolean actualizar(ExistenciaUmusProgramas dsugd);

    List<ExistenciaUmusProgramas> detalleExistenciaUmusProgramas(String clave);

    boolean eliminarExistencias();
    
    List<ExistenciaUmusProgramas> getAll();
    
}
