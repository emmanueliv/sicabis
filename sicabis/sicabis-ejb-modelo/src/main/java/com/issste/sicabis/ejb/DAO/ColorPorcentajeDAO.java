/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ColorPorcentaje;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 6JWBBG2
 */
@Local
public interface ColorPorcentajeDAO {
    
    List<ColorPorcentaje> colorPorcentajeAllActivos();
    
    List<ColorPorcentaje> colorPorcentajeAllInactivos();
    
    List<ColorPorcentaje> colorPorcentajeAll();
    
    boolean guardarActualizaColorPorcentaje(ColorPorcentaje colorPorcentaje);
    
    boolean eliminaColorPorcentaje(ColorPorcentaje colorPorcentaje);
    
    ColorPorcentaje validaExistenciaFiltroColorPorcentaje(ColorPorcentaje colorPorcentaje);
    
    
    
    
}
