/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.PorcentajeDelegacionDAO;
import com.issste.sicabis.ejb.modelo.PorcentajeDelegacion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author 6JWBBG2
 */
@Stateless
@LocalBean
public class PorcentajeDelegacionService {

    @EJB
    private PorcentajeDelegacionDAO porcentajeDelegacionDAOImplement;

    public List<PorcentajeDelegacion> getListaPorcentajeDelegacion() {
        return porcentajeDelegacionDAOImplement.getListaPorcentajeDelegacion();
    }

//    public PorcentajeDelegacion obtenerPorcentajeByClaveDelegacion(String claveDelegacion) {
//        return porcentajeDelegacionDAOImplement.obtenerPorcentajeByClaveDelegacion(claveDelegacion);
//    }

    public boolean actualizarPorcentajeDelegacion(PorcentajeDelegacion delegacion) {
        return porcentajeDelegacionDAOImplement.actualizarPorcentajeDelegacion(delegacion);
    }
    
    public boolean borrarContenidoPorcentajeDelegacion() {
        return porcentajeDelegacionDAOImplement.borrarContenidoPorcentajeDelegacion();
    }
    
}
