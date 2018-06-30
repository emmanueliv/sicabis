/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.CatUnidadMedicaHistoricoDAO;
import com.issste.sicabis.ejb.modelo.CatUnidadMedicaHistorico;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
@LocalBean
public class CatUnidadMedicaHistoricoService {

    @EJB
    private CatUnidadMedicaHistoricoDAO catUnidadMedicaHistoricoDAOImplement;

    public boolean guardar(CatUnidadMedicaHistorico cumh) {
        return catUnidadMedicaHistoricoDAOImplement.guardar(cumh);
    }

    public List<CatUnidadMedicaHistorico> getByFechaIngreso(Date fechaIngreso) {
        return catUnidadMedicaHistoricoDAOImplement.getByFechaIngreso(fechaIngreso);
    }
}
