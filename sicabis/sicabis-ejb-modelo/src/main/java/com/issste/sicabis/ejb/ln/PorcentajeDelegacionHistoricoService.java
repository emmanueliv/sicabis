/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.PorcentajeDelegacionHistoricoDAO;
import com.issste.sicabis.ejb.modelo.PorcentajeDelegacionHistorico;
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
public class PorcentajeDelegacionHistoricoService {

    @EJB
    private PorcentajeDelegacionHistoricoDAO porcentajeDelegacionHistoricoDAOImplements;

    public List<PorcentajeDelegacionHistorico> getByFechaIngreso(Date fecha_actualizacion) {
        return porcentajeDelegacionHistoricoDAOImplements.getByFechaIngreso(fecha_actualizacion);
    }

    public boolean guardar(PorcentajeDelegacionHistorico pdh) {
        return porcentajeDelegacionHistoricoDAOImplements.guardar(pdh);
    }

}
