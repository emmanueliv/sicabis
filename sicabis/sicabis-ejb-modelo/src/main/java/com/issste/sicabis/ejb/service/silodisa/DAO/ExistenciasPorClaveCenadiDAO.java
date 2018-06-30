/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAO;

import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveCenadi;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 5XD9BG2
 */
@Local
public interface ExistenciasPorClaveCenadiDAO {

    boolean guardar(ExistenciaPorClaveCenadi epcc);

    boolean actualizar(ExistenciaPorClaveCenadi epcc);

    List<ExistenciaPorClaveCenadi> existenciaPorClaveCenadiByClave(String clave);

    Integer existenciaSumPorClaveCenadiByClave(String clave);

    boolean eliminarExistencias();
    
    List<ExistenciaPorClaveCenadi> exitenciaAll();
    
    //List<ExistenciaPorClaveCenadi> getByExistenciasPorClaveCenadi(ExistenciaPorClaveCenadi epcc);
}
