/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Estatus;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author erik
 */
@Local
public interface EstatusDAO {

    List<Estatus> getAllEstatus();

    Estatus getRemisionEstatus(Integer e);

    List<Estatus> getEstatusByTarea(int idTarea);

    List<Estatus> getEstatusByNombre(String nombreStatus);

    void guardarEstatus(Estatus estatus);
    
    List<Estatus> getByEstatusIdTarea(int idTarea, String opcion);

}
