/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Rescisiones;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fabianvr
 */
@Local
public interface RescisionesDAO {
    
    Integer guardar(Rescisiones r);
    boolean actualizar(Rescisiones r);
    List<Rescisiones> rescisiones(Integer detalle);
    List<Rescisiones> rescisionesConsulta(String criterio, Integer busqueda);
    List<Rescisiones> rescisionesByOrden(String criterio);
    List<Rescisiones> rescisionesByClave(Integer c);
}
