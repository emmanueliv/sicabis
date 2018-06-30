/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.TipoContratoDAO;
import com.issste.sicabis.ejb.modelo.Almacen;
import com.issste.sicabis.ejb.modelo.TipoContrato;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class TipoContratoService {
    
   @EJB 
   TipoContratoDAO tipoContratoDAOImpl;
   
   public List<TipoContrato> obtenerTiposContrato() {
        return tipoContratoDAOImpl.getTiposContrato();
    }

    public TipoContrato obtenerTipoContratoByNombre(String nombre) {
        List<TipoContrato> list
                = tipoContratoDAOImpl.obtenerTipoContratoByNombre(nombre);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void guardarTipoContrato(TipoContrato tipoContrato) {
        tipoContratoDAOImpl.guardarTipoContrato(tipoContrato);
    }

}
