/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.PeriodoAreaDAO;
import com.issste.sicabis.ejb.modelo.PeriodoArea;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Toshiba Manolo
 */
@Stateless
public class PeriodoAreaService {
    
    @EJB
    PeriodoAreaDAO periodoAreaDAO;
    
     public List<PeriodoArea> obtenerPeriodos() {        
        return periodoAreaDAO.obtenerPeriodos();
    }
     
    public List<PeriodoArea> obtenerPeriodosPorArea(Integer idArea) {        
        return periodoAreaDAO.obtenerPeriodosPorArea(idArea);
    }
    
}
