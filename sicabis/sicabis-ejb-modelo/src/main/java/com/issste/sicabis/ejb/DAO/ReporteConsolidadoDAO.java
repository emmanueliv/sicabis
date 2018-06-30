/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.ReporteConsolidadoDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fabianvr
 */
@Local
public interface ReporteConsolidadoDAO {

    List<ReporteConsolidadoDTO> reporteConsolidado(String criterioBusqueda, Integer busqueda);
}
