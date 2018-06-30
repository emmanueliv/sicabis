/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.PartidaPresupuestal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriosoft
 */
@Local
public interface PartidaDAO {

    List<PartidaPresupuestal> getPartidaPresupuestales();

    List<PartidaPresupuestal> obtenerPartidaPresupuestalByNombre(String nombre);

    void guardarPartidaPresupuestal(PartidaPresupuestal partida);
    
    List<PartidaPresupuestal> getPartidaPresupuestalesByActivo() ;
    
    List<PartidaPresupuestal> obtenerPartidaPresupuestalByIdAndActivo(int idPartPre);
    
}
