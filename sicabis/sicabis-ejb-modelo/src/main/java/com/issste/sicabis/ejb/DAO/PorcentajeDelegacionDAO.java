/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.PorcentajeDelegacion;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 6JWBBG2
 */
@Local
public interface PorcentajeDelegacionDAO {
    
    List<PorcentajeDelegacion> getListaPorcentajeDelegacion();

    boolean borrarContenidoPorcentajeDelegacion();
    
    boolean actualizarPorcentajeDelegacion(PorcentajeDelegacion delegacion);

}