/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Fabricante;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.CrInsumos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Toshiba Manolo
 */
@Local
public interface CrInsumosDAO {
    
    List<CrInsumos> getListCrInsumosByidCr(Integer idCr);
    CrInsumos getCrInsumoById(Integer idCrInsumos);
    Integer traerMaxCRPorArea(Integer idArea);
    Integer deleteCrInsumosByIdCr(Integer idCr);
    Integer deleteCrInsumos(CrInsumos crInsumo);
    CrInsumos guardarCrInsumo(CrInsumos crInsumo);
    CrInsumos actualizarCrInsumo(CrInsumos crInsumo);
    List<Object[]> traerCrGrupo(Integer idCr);
}
