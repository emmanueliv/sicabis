/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.CondicionesPagoDAO;
import com.issste.sicabis.ejb.modelo.CondicionesPago;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class CondicionesPagoService {

    @EJB
    private CondicionesPagoDAO condicionesDAO;

    public List<CondicionesPago> obtenerCondicionesPago() {
        return condicionesDAO.obtenerCondicionesPago();
    }

    public CondicionesPago obtenerCondicionesByDesc(String desc) {
        List<CondicionesPago> lista = condicionesDAO.obtenerCondicionesByDesc(desc);
        if (!lista.isEmpty()) {
            return lista.get(0);
        }
        return null;
    }

    public void guardarCondicionPago(CondicionesPago condicion) {
        condicionesDAO.guardarCondicionPago(condicion);
    }

}
