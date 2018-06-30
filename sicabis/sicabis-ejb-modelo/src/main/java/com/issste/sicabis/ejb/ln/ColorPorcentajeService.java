/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ColorPorcentajeDAO;
import com.issste.sicabis.ejb.modelo.ColorPorcentaje;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author 6JWBBG2
 */
@LocalBean
@Stateless
public class ColorPorcentajeService {
    @EJB
    private ColorPorcentajeDAO colorPorcentajeDAOImplement;
    
    
    
    public List<ColorPorcentaje> colorPorcentajeAllActivos(){
        return colorPorcentajeDAOImplement.colorPorcentajeAllActivos();
    }
    
    public List<ColorPorcentaje> colorPorcentajeAllInactivos() {
        return colorPorcentajeDAOImplement.colorPorcentajeAllInactivos();
    }
    
    public List<ColorPorcentaje> colorPorcentajeAll() {
        return colorPorcentajeDAOImplement.colorPorcentajeAll();
    }
    
    public boolean guardarActualizaColorPorcentaje(ColorPorcentaje colorPorcentaje) {
        return colorPorcentajeDAOImplement.guardarActualizaColorPorcentaje(colorPorcentaje);
    }
    
    public boolean eliminaColorPorcentaje(ColorPorcentaje colorPorcentaje) {
        return colorPorcentajeDAOImplement.eliminaColorPorcentaje(colorPorcentaje);
    }
    
    public ColorPorcentaje validaExistenciaFiltroColorPorcentaje(ColorPorcentaje colorPorcentaje) {
        return colorPorcentajeDAOImplement.validaExistenciaFiltroColorPorcentaje(colorPorcentaje);
    }
}
