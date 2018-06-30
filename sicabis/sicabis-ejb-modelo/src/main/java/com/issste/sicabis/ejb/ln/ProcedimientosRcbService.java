/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ProcedimientoRcbDAOImplement;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class ProcedimientosRcbService {

    @EJB
    private ProcedimientoRcbDAOImplement procedimientoRcbDAOImplement;

    public boolean borrarByIdProcedimiento(Integer idProcedimiento) {
        return procedimientoRcbDAOImplement.borrarByIdProcedimiento(idProcedimiento);
    }

    public void guardaProcedimientoRcb(ProcedimientoRcb procedimientoRcb) {
        procedimientoRcbDAOImplement.guardaProcedimientoRcb(procedimientoRcb);
    }

    public List<ProcedimientoRcb> obtenerByNumeroProc(String numeroProcedimiento) {
        return procedimientoRcbDAOImplement.obtenerByNumeroProc(numeroProcedimiento);
    }

    public boolean actualizaProcedimientoRcb(ProcedimientoRcb procedimientoRcb) {
        return procedimientoRcbDAOImplement.actualizaProcedimientoRcb(procedimientoRcb);
    }

    public List<ProcedimientoRcb> obtenerByIdNumRcbTipoCompra(String numeroRcb, int tipoCompra) {
        return procedimientoRcbDAOImplement.obtenerByIdNumRcbTipoCompra(numeroRcb, tipoCompra);
    }

    public List<ProcedimientoRcb> obtenerByIdProcedimiento(Integer idProcedimiento) {
        return procedimientoRcbDAOImplement.obtenerByIdProcedimiento(idProcedimiento);
    }

    public List<Procedimientos> obtenerByRcb(String numeroRcb) {
        return procedimientoRcbDAOImplement.obtenerByRcb(numeroRcb);
    }
}
