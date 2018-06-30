/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.DelegacionesDAO;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class DelegacionesService {

    @EJB
    DelegacionesDAO delegacionesDAO;

    public List<Delegaciones> obtenerDelegaciones() {
        return delegacionesDAO.obtenerDelegaciones();
    }

    public Delegaciones obtenerDelegacionporId(Integer idDelegacion) {
        return delegacionesDAO.obtenerDelegacionporId(idDelegacion);
    }
    
    public Delegaciones obtenerDelegacionporNombre(String nombre) {
        return delegacionesDAO.obtenerDelegacionporNombre(nombre);
    }
    

}
