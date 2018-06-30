/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.DetalleSalidasUmuGuiaDistribucionHistoricoDAO;
import com.issste.sicabis.ejb.modelo.DetalleSalidasUmuGuiaDistribucionHistorico;
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
public class DetalleSalidasUmuGuiaDistribucionHistoricoService {

    @EJB
    private DetalleSalidasUmuGuiaDistribucionHistoricoDAO detalleSalidasUmuGuiaDistribucionHistoricoDAOImplement;

    public boolean guardar(DetalleSalidasUmuGuiaDistribucionHistorico dsugdh) {
        return detalleSalidasUmuGuiaDistribucionHistoricoDAOImplement.guardar(dsugdh);
    }

    public List<DetalleSalidasUmuGuiaDistribucionHistorico> getByFechaIngreso(Date fechaIngreso) {
        return detalleSalidasUmuGuiaDistribucionHistoricoDAOImplement.getByFechaIngreso(fechaIngreso);
    }
}
