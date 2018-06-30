/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ReporteConsolidadoDAO;
import com.issste.sicabis.ejb.DTO.ReporteConsolidadoDTO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author fabianvr
 */
@Stateless
public class ReporteConciliacionService {

    @EJB
    private ReporteConsolidadoDAO reporteConsolidadoDAOImplement;

    public List<ReporteConsolidadoDTO> reporteConcolidadoList(String criterioBusqueda, Integer busqueda) {
        return reporteConsolidadoDAOImplement.reporteConsolidado(criterioBusqueda, busqueda);
    }

}
