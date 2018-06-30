/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Delegaciones;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriosoft
 */
@Local
public interface DelegacionesDAO {
    
    List<Delegaciones> obtenerDelegaciones();
    Delegaciones obtenerDelegacionporId(Integer idDelegacion);
    Delegaciones obtenerDelegacionporNombre(String nombre);
    
}
