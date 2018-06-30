/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.MapaEjecutivoDispG40HistoricoDAO;
import com.issste.sicabis.ejb.modelo.MapaEjecutivoDispG40Historico;
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
public class MapaEjecutivoDispG40HistoricoService {
    @EJB
    private MapaEjecutivoDispG40HistoricoDAO mapaEjecutivoDispG40HistoricoDAOImplement;

    public boolean guardar(MapaEjecutivoDispG40Historico medh) {
        return mapaEjecutivoDispG40HistoricoDAOImplement.guardar(medh);
    }

    public List<MapaEjecutivoDispG40Historico> getByFechaIngreso(Date fechaIngreso) {
        return mapaEjecutivoDispG40HistoricoDAOImplement.getByFechaIngreso(fechaIngreso);
    }
}
