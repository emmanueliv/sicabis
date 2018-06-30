/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ProcedimientoDAO;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class ProcedimientoService {

    @EJB
    private ProcedimientoDAO procedimientoDAOImplement;

    public boolean guardaProcedimiento(Procedimientos procedimiento) {
        return procedimientoDAOImplement.guardaProcedimiento(procedimiento);
    }

    public List<Procedimientos> obtenerByProcedimientos(Procedimientos procedimiento) {
        return procedimientoDAOImplement.obtenerByProcedimientos(procedimiento);
    }

    public boolean actualizaProcedimiento(Procedimientos procedimientos) {
        return procedimientoDAOImplement.actualizaProcedimiento(procedimientos);
    }

    public Procedimientos obtenerByNumeroProcedimiento(String numeroProcedimiento) {
        return procedimientoDAOImplement.obtenerByNumeroProcedimiento(numeroProcedimiento);
    }

    public Procedimientos obtenerByNumeroProcedimientoSeguimiento(String numeroProcedimiento) {
        return procedimientoDAOImplement.obtenerByNumeroProcedimientoSeguimiento(numeroProcedimiento);
    }

    public Procedimientos obtenerByNumeroProcedimientoById(Integer idProcedimiento) {
        return procedimientoDAOImplement.obtenerByNumeroProcedimientoById(idProcedimiento);
    }

}
