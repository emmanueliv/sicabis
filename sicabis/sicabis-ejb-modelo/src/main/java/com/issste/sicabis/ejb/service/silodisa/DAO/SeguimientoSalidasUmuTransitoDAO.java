/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAO;

import com.issste.sicabis.ejb.service.silodisa.modelo.SeguimientoSalidasUmuTransito;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 9RZCBG2
 */
@Local
public interface SeguimientoSalidasUmuTransitoDAO {

    boolean guardar(SeguimientoSalidasUmuTransito ssut);

    boolean actualizar(SeguimientoSalidasUmuTransito ssut);

    List<SeguimientoSalidasUmuTransito> seguimientoSalidasUmuTransitoByClave(String clave);

    boolean eliminarExistencias();
}
