/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ConfiguracionesDAO;
import com.issste.sicabis.ejb.modelo.Configuraciones;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class ConfiguracionesService {

    @EJB
    private ConfiguracionesDAO configuracionesDAO;
    
    public int getValorParametroByParam(String parametro){
        Configuraciones config = configuracionesDAO.getValorParametroByParam(parametro);
        String valor = config.getValor();
        return Integer.parseInt(valor);
    }

    public List<Configuraciones> obtenerConfiguraciones() {
        return configuracionesDAO.obtenerConfiguraciones();
    }

    public void guardarConfiguracion(Configuraciones configuraciones) {
        configuracionesDAO.guardarConfiguracion(configuraciones);
    }

    public Configuraciones obtenerConfigByNombre(String descConfig) {
        List<Configuraciones> configList = configuracionesDAO.obtenerConfigByNombre(descConfig);
        if(configList.isEmpty()){
            return null;
        } else {
            return configList.get(0);
        }
    }

}
