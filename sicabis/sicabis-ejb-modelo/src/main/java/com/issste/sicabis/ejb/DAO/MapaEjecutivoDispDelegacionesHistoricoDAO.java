/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.MapaEjecutivoDispDelegacionesHistorico;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 9RZCBG2
 */
@Local
public interface MapaEjecutivoDispDelegacionesHistoricoDAO {

    boolean guardar(MapaEjecutivoDispDelegacionesHistorico meddh);

    List<MapaEjecutivoDispDelegacionesHistorico> getByFechaIngreso(Date fechaIngreso);
}
