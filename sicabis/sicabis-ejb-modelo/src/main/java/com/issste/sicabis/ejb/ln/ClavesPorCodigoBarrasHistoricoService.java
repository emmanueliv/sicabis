/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ClavesPorCodigoBarrasHistoricoDAO;
import com.issste.sicabis.ejb.modelo.ClavesPorCodigoBarrasHistorico;
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
public class ClavesPorCodigoBarrasHistoricoService {
    @EJB
    private ClavesPorCodigoBarrasHistoricoDAO clavesPorCodigoBarrasHistoricoDAOImplement;
   
    
    public boolean guardar(ClavesPorCodigoBarrasHistorico ccbh) {
        return clavesPorCodigoBarrasHistoricoDAOImplement.guardar(ccbh);
    }

    public List<ClavesPorCodigoBarrasHistorico> getByFechaIngreso(Date fechaIngreso) {
        return clavesPorCodigoBarrasHistoricoDAOImplement.getByFechaIngreso(fechaIngreso);
    }
}
