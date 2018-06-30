/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.SeguimientoSalidasUmuInternoHistoricoDAO;
import com.issste.sicabis.ejb.modelo.SeguimientoSalidasUmuInternoHistorico;
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
public class SeguimientoSalidasUmuInternoHistoricoService {
    @EJB
    private SeguimientoSalidasUmuInternoHistoricoDAO seguimientoSalidasUmuInternoHistoricoDAOImplement;

    public boolean guardar(SeguimientoSalidasUmuInternoHistorico ssuih) {
        return seguimientoSalidasUmuInternoHistoricoDAOImplement.guardar(ssuih);
    }

    public List<SeguimientoSalidasUmuInternoHistorico> getByFechaIngreso(Date fechaIngreso) {
        return seguimientoSalidasUmuInternoHistoricoDAOImplement.getByFechaIngreso(fechaIngreso);
    }
}
