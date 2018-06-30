/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Cancelaciones;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fabianvr
 */
@Local
public interface CancelacionesDAO {

    Integer guardar(Cancelaciones c);
    boolean actualizar(Cancelaciones c);
    List<Cancelaciones> cancelacion(Integer detalle);
    List<Cancelaciones> cancelacionesByConsulta(String criterio, Integer busqueda);
    List<Cancelaciones> consulta(Integer can);
    List<Cancelaciones> cancelacionesByOrden(String criterio);
    List<Cancelaciones> cancelacionesByClave(Integer can);
}
