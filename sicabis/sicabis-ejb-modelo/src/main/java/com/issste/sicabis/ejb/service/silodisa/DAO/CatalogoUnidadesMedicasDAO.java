/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.DAO;

import com.issste.sicabis.ejb.service.silodisa.modelo.CatUnidadMedica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 9RZCBG2
 */
@Local
public interface CatalogoUnidadesMedicasDAO {

    boolean guardar(CatUnidadMedica cum);

    boolean actualizar(CatUnidadMedica cum);

    List<CatUnidadMedica> catalogoUnidadesMedicasByUmu(String umu);

    boolean eliminarExistencias();
    
    List<CatUnidadMedica> getAll();
}
