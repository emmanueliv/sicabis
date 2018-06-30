/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ContratoFalloProcedimientoRcbDAO;
import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ContratoFalloProcedimientoRcbService {

    @EJB
    private ContratoFalloProcedimientoRcbDAO contratoFalloProcedimientoRcbDAOImplement;
    
    public boolean borrarByIdContrato(Integer idContrato){
        return contratoFalloProcedimientoRcbDAOImplement.borrarByIdContrato(idContrato);
    }

    public List<ContratoFalloProcedimientoRcb> obtenerByClaves(String claveInsumo){
        return contratoFalloProcedimientoRcbDAOImplement.obtenerByClaves(claveInsumo);
    }
    
    public List<ContratoFalloProcedimientoRcb> getContratoFalloRcbByIdFallo(int idFallo){
        return contratoFalloProcedimientoRcbDAOImplement.getContratoByIdFallo(idFallo);
    }
    
    public List<ContratoFalloProcedimientoRcb> obtenerCfprByClave(String claveInsumo) {
        return contratoFalloProcedimientoRcbDAOImplement.obtenerCfprByClave(claveInsumo);
    }

}
