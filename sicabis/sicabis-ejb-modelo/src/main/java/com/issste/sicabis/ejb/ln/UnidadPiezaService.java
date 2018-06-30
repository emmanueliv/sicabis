/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.UnidadPiezaDAO;
import com.issste.sicabis.ejb.modelo.UnidadPieza;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Toshiba Manolo
 */
@Stateful
public class UnidadPiezaService {
    
    @EJB
    private UnidadPiezaDAO unidadPiezaDAO;
    
    public List<UnidadPieza> getAllUnidadPiezas() {
        return unidadPiezaDAO.getAllUnidadPiezas();
    }
    
}
