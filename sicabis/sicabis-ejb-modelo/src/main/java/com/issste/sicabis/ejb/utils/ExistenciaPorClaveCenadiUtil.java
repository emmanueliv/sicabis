/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.utils;

import com.issste.sicabis.ejb.service.silodisa.controller.ExistenciasPorClaveCenadiController;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveCenadi;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciasPorClaveCenadiService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author 5XD9BG2
 */
@Stateless
public class ExistenciaPorClaveCenadiUtil {

    @EJB
    private ExistenciasPorClaveCenadiService existenciasPorClaveCenadiService;

    @EJB
    private ExistenciasPorClaveCenadiController existenciasPorClaveCenadiController;

    private List<ExistenciaPorClaveCenadi> existenciaList;
    private Date fechaIngreso;

    public boolean validaExistenciaClaveCenadi(String clave) throws IOException, MalformedURLException {
        Date fechaActual = new Date();
         existenciaList = existenciasPorClaveCenadiService.existenciaPorClaveCenadiByClave(clave);
        if (existenciaList != null) {
            fechaIngreso = existenciaList.get(0).getFechaIngreso();
            if (fechaActual.getHours() > fechaIngreso.getHours() && 
                    validaHoras(fechaIngreso.getMinutes(), fechaActual.getMinutes()) == true) {
                boolean eliminar = existenciasPorClaveCenadiService.eliminarExistencias();
                if (eliminar == true) {
                    existenciasPorClaveCenadiController.obtenerExistencias(1);
                    return true;
                }
            } else {
                return true;
            }
        } else {
            existenciasPorClaveCenadiController.obtenerExistencias(1);
            return true;
        }
        return false;
    }

    public boolean validaHoras(int minutoCenadi, int minutoActual) {
        int m1 = 60 - minutoCenadi;
        int m2 = 60 - minutoActual;
        int minutosTotal = m1 + m2;
        return minutosTotal <= 60;
    }
}
