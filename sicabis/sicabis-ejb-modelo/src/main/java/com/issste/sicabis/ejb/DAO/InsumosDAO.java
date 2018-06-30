/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Fallos;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;

/**
 *
 * @author erik
 */
@Local
public interface InsumosDAO {
    
    List<Insumos> obtenerListaInsumos();
    List<Insumos> buscarInsumosPorClaveLike(String clave);
    List<Insumos> buscarInsumosPorClave(String clave);
    List<Insumos> obtenerListaInsumosPorArea(Integer idArea);
    List<Insumos> obtenerListaInsumosPorAreaGrupo(Integer idArea,Integer idGrupo);
    List<Insumos> obtenerListaInsumosPorAreaGrupos(Integer idArea,List<String> listGrupos);
    Integer idInsumoByClave(String clave);
    List<Insumos> inusmos(String clave);
    Insumos obtieneByClave(String clave);
    boolean save (Insumos insumo);
    boolean actualiza(Insumos insumo);

    
}
