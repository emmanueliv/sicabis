/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.CrInsumosDAO;
import com.issste.sicabis.ejb.modelo.CrInsumos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author Toshiba Manolo
 */
@Stateful
public class CrInsumosService {
    
    @EJB
    private CrInsumosDAO crInsumosDAO;
    
    public List<CrInsumos> getListCrInsumosByidCr(Integer idCr) {
        return crInsumosDAO.getListCrInsumosByidCr(idCr);
    }

    public CrInsumos getCrInsumoById(Integer idCrInsumos) {
        return crInsumosDAO.getCrInsumoById(idCrInsumos);
    }

    public Integer traerMaxCRPorArea(Integer idArea) {
        return crInsumosDAO.traerMaxCRPorArea(idArea);
    }

    public Integer deleteCrInsumosByIdCr(Integer idCr) {
        return crInsumosDAO.deleteCrInsumosByIdCr(idCr);
    }

    public Integer deleteCrInsumos(CrInsumos crInsumo) {
        return crInsumosDAO.deleteCrInsumos(crInsumo);
    }

    public CrInsumos guardarCrInsumo(CrInsumos crInsumo) {
        return crInsumosDAO.guardarCrInsumo(crInsumo);
    }

    public CrInsumos actualizarCrInsumo(CrInsumos crInsumo) {
        return crInsumosDAO.actualizarCrInsumo(crInsumo);
    }
    
    public List<Object[]> traerCrGrupo(Integer idCr) {
        return  crInsumosDAO.traerCrGrupo(idCr);
    }
    
    
}
