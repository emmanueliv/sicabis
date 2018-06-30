/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.service.silodisa.DAO.SeguimientoSalidasUmuInternoDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.SeguimientoSalidasUmuInterno;
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
public class SeguimientoSalidasUmuInternoService {

    @EJB
    private SeguimientoSalidasUmuInternoDAO seguimientoSalidasUmuInternoDAOImplement;

    public boolean guardar(SeguimientoSalidasUmuInterno scugd) {
        return seguimientoSalidasUmuInternoDAOImplement.guardar(scugd);
    }

    public boolean actualizar(SeguimientoSalidasUmuInterno scugd) {
        return seguimientoSalidasUmuInternoDAOImplement.actualizar(scugd);
    }

    public List<SeguimientoSalidasUmuInterno> seguimientoSalidasUmuInternoByUmu(String clave) {
        return seguimientoSalidasUmuInternoDAOImplement.seguimientoSalidasUmuInternoByUmu(clave);
    }

    public boolean eliminarExistencias() {
        return seguimientoSalidasUmuInternoDAOImplement.eliminarExistencias();
    }
}
