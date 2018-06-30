/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Configuraciones;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriosoft
 */
@Local
public interface ConfiguracionesDAO {
    
    Configuraciones getValorParametroByParam(String parametro);

    List<Configuraciones> obtenerConfiguraciones();

    void guardarConfiguracion(Configuraciones configuraciones);

    List<Configuraciones> obtenerConfigByNombre(String descConfig);
    
}
