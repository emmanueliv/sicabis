/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.AsignacionInsumosDAO;
import com.issste.sicabis.ejb.modelo.AsignacionInsumos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Toshiba Manolo
 */
@Stateless
public class AsignacionInsumosService {
    
    @EJB
    private AsignacionInsumosDAO asignacionInsumosDAO;
    
    public List<AsignacionInsumos> obtenerAsignaciones() {
        return asignacionInsumosDAO.obtenerAsignaciones();
    }
    
    public AsignacionInsumos save(AsignacionInsumos asignacionInsumos) {
        return asignacionInsumosDAO.save(asignacionInsumos);
    }
    
}
