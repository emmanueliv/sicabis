/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.SeguimientoSalidasUmuTransitoHistoricoDAO;
import com.issste.sicabis.ejb.modelo.SeguimientoSalidasUmuTransitoHistorico;
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
public class SeguimientoSalidasUmuTransitoHistoricoService {
    @EJB
    private SeguimientoSalidasUmuTransitoHistoricoDAO seguimientoSalidasUmuTransitoHistoricoDAOImplement;

    public boolean guardar(SeguimientoSalidasUmuTransitoHistorico ssuth) {
        return seguimientoSalidasUmuTransitoHistoricoDAOImplement.guardar(ssuth);
    }

    public List<SeguimientoSalidasUmuTransitoHistorico> getByFechaIngreso(Date fechaIngreso) {
        return seguimientoSalidasUmuTransitoHistoricoDAOImplement.getByFechaIngreso(fechaIngreso);
    }
}
