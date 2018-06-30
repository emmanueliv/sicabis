/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.CondicionesPago;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriosoft
 */
@Local
public interface CondicionesPagoDAO {

    List<CondicionesPago> obtenerCondicionesPago();

    List<CondicionesPago> obtenerCondicionesByDesc(String desc);

    void guardarCondicionPago(CondicionesPago condicion);

}
