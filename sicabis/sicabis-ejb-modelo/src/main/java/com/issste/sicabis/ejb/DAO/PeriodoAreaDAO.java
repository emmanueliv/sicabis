/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.PeriodoArea;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Toshiba Manolo
 */
@Local
public interface PeriodoAreaDAO {
    
    List<PeriodoArea> obtenerPeriodos();
    List<PeriodoArea> obtenerPeriodosPorArea(Integer idArea);
    
}
