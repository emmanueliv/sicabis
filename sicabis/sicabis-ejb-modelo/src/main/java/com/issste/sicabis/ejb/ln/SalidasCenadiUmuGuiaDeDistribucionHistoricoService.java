/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.SalidasCenadiUmuGuiaDeDistribucionHistoricoDAO;
import com.issste.sicabis.ejb.modelo.SalidasCenadiUmuGuiaDeDistribucionHistorico;
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
public class SalidasCenadiUmuGuiaDeDistribucionHistoricoService {

    @EJB
    private SalidasCenadiUmuGuiaDeDistribucionHistoricoDAO salidasCenadiUmuGuiaDeDistribucionHistoricoDAOImplement;

    public boolean guardar(SalidasCenadiUmuGuiaDeDistribucionHistorico scugdh) {
        return salidasCenadiUmuGuiaDeDistribucionHistoricoDAOImplement.guardar(scugdh);
    }

    public List<SalidasCenadiUmuGuiaDeDistribucionHistorico> getByFechaIngreso(Date fechaIngreso) {
        return salidasCenadiUmuGuiaDeDistribucionHistoricoDAOImplement.getByFechaIngreso(fechaIngreso);
    }
}
