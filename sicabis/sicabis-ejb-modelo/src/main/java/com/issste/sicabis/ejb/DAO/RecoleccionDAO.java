/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Recoleccion;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fabianvr
 */
@Local
public interface RecoleccionDAO {

    List<Recoleccion> recoleccion();
    Integer folioRecoleccion();
    Integer guardarRecoleccion(Recoleccion recoleccion);
    BigDecimal precioPromedioByClave(String clave);
    List<Recoleccion> buscarRecoleccion(String folio);
    List<Recoleccion> recoleccionById(Integer id);
   
    
}
