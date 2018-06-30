/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.service.silodisa.DAO.SeguimientoSalidasUmuTransitoDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.SeguimientoSalidasUmuTransito;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author 9RZCBG2
 */
@LocalBean
@Stateless
public class SeguimientoSalidasUmuTransitoService {

    @EJB
    private SeguimientoSalidasUmuTransitoDAO seguimientoSalidasUmuTransitoDAOImplement;

    public boolean guardar(SeguimientoSalidasUmuTransito scugd) {
        return seguimientoSalidasUmuTransitoDAOImplement.guardar(scugd);
    }

    public boolean actualizar(SeguimientoSalidasUmuTransito scugd) {
        return seguimientoSalidasUmuTransitoDAOImplement.actualizar(scugd);
    }

    public List<SeguimientoSalidasUmuTransito> seguimientoSalidasUmuInternoByUmu(String clave) {
        return seguimientoSalidasUmuTransitoDAOImplement.seguimientoSalidasUmuTransitoByClave(clave);
    }

    public boolean eliminarExistencias() {
        return seguimientoSalidasUmuTransitoDAOImplement.eliminarExistencias();
    }
}
