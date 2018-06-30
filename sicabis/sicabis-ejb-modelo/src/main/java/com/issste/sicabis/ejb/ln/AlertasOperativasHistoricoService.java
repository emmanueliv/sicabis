/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.AlertasOperativasHistoricoDAO;
import com.issste.sicabis.ejb.DTO.AlertasDTO;
import com.issste.sicabis.ejb.modelo.AlertasOperativasHistorico;
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
public class AlertasOperativasHistoricoService {

    @EJB
    private AlertasOperativasHistoricoDAO alertasOperativasHistoricoDAOImplement;

    public boolean guardar(AlertasOperativasHistorico aoh) {
        return alertasOperativasHistoricoDAOImplement.guardar(aoh);
    }

    public List<AlertasOperativasHistorico> getByFechaIngreso(Date fechaIngreso) {
        return alertasOperativasHistoricoDAOImplement.getByFechaIngreso(fechaIngreso);
    }

    public List<AlertasDTO> getAll() {
        return alertasOperativasHistoricoDAOImplement.getAll();
    }
}
