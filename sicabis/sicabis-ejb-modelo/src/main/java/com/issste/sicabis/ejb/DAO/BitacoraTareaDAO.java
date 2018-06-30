/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriosoft
 */
@Local
public interface BitacoraTareaDAO {

    List<BitacoraTareaEstatus> obtenerRegistros();

    void guardarEnBitacora(BitacoraTareaEstatus bt);

    List<BitacoraTareaEstatus> consultaBitacoraTarea(BitacoraTareaEstatus bt,
            Date fechaInicial, Date fechaFinal);

}
