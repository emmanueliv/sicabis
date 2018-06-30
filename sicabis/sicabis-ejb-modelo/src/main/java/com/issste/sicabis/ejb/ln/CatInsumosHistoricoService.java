/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.CatInsumosHistoricoDAO;
import com.issste.sicabis.ejb.modelo.CatInsumosHistorico;
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
public class CatInsumosHistoricoService {

    @EJB
    private CatInsumosHistoricoDAO catInsumosHistoricoDAOImplements;

    public boolean guardar(CatInsumosHistorico cih) {
        return catInsumosHistoricoDAOImplements.guardar(cih);
    }

    public List<CatInsumosHistorico> getByFechaIngreso(Date fechaIngreso) {
        return catInsumosHistoricoDAOImplements.getByFechaIngreso(fechaIngreso);
    }
}
