/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.MapaEjecutivoDispG40Historico;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 9RZCBG2
 */
@Local
public interface MapaEjecutivoDispG40HistoricoDAO {

    boolean guardar(MapaEjecutivoDispG40Historico medgh);

    List<MapaEjecutivoDispG40Historico> getByFechaIngreso(Date fechaIngreso);
}
