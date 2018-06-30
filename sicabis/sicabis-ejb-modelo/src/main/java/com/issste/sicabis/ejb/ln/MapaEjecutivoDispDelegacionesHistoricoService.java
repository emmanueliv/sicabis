/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.MapaEjecutivoDispDelegacionesHistoricoDAO;
import com.issste.sicabis.ejb.modelo.MapaEjecutivoDispDelegacionesHistorico;
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
public class MapaEjecutivoDispDelegacionesHistoricoService {
    @EJB
    private MapaEjecutivoDispDelegacionesHistoricoDAO mapaEjecutivoDispDelegacionesHistoricoDAOImplement;
    
    public boolean guardar(MapaEjecutivoDispDelegacionesHistorico meddh) {
        return mapaEjecutivoDispDelegacionesHistoricoDAOImplement.guardar(meddh);
    }

    public List<MapaEjecutivoDispDelegacionesHistorico> getByFechaIngreso(Date fechaIngreso) {
        return mapaEjecutivoDispDelegacionesHistoricoDAOImplement.getByFechaIngreso(fechaIngreso);
    }
}
