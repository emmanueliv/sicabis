/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.TipoProcedimientoDAO;
import com.issste.sicabis.ejb.modelo.Almacen;
import com.issste.sicabis.ejb.modelo.TipoProcedimiento;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class TipoProcedimientoService {

    @EJB
    private TipoProcedimientoDAO tipoProcedimientoDAOImplement;

    public List<TipoProcedimiento> obtenerTiposProcedimientos() {
        return tipoProcedimientoDAOImplement.obtenerTiposProcedimientos();
    }
    
        public TipoProcedimiento obtenerTipoProcedimientoByNombre(String nombre) {
        List<TipoProcedimiento> list
                = tipoProcedimientoDAOImplement.obtenerAlmacenByNombre(nombre);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void guardarTipoProcedimiento(TipoProcedimiento tipoProcedimiento) {
        tipoProcedimientoDAOImplement.guardarAlmacen(tipoProcedimiento);
    }
    
}
