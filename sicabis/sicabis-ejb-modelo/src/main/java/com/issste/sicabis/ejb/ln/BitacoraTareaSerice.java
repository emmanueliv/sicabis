/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.BitacoraTareaDAO;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class BitacoraTareaSerice {

    @EJB
    BitacoraTareaDAO bitacoraTareaDAO;


    public List<BitacoraTareaEstatus> obtenerRegistros() {
        return bitacoraTareaDAO.obtenerRegistros();
    }

    public void guardarEnBitacora(BitacoraTareaEstatus bt) {
        bitacoraTareaDAO.guardarEnBitacora(bt);
    }


    public List<BitacoraTareaEstatus> obtenerRegistrosByFiltros(
            BitacoraTareaEstatus bt, Date fechaInicial, Date fechaFinal) {
        return bitacoraTareaDAO.consultaBitacoraTarea(bt, fechaInicial, fechaFinal);
    }

}
