/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ArticulosDAO;
import com.issste.sicabis.ejb.modelo.FundamentoLegal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class ArticulosService {

    @EJB
    ArticulosDAO articuloDAOimpl;

    public List<FundamentoLegal> obtenerArticulos() {
        return articuloDAOimpl.getFundamentos();
    }

    public List<FundamentoLegal> obtenerFundamentoLegalByIdAndActivo(int fundamentoLegal) {
        return  articuloDAOimpl.obtenerFundamentoLegalByIdAndActivo(fundamentoLegal);
    }

    public List<FundamentoLegal> getFundamentosByActivo() {
        return articuloDAOimpl.getFundamentosByActivo();
    }

    public FundamentoLegal obtenerFundamentoLegalByNombre(String nombreFundamentoLegal) {
        List<FundamentoLegal> areaList
                = articuloDAOimpl.obtenerFundamentoLegalByNombre(nombreFundamentoLegal);
        if (areaList.isEmpty()) {
            return null;
        } else {
            return areaList.get(0);
        }
    }

    public void guardarFundamentoLegal(FundamentoLegal area) {
        articuloDAOimpl.guardarFundamentoLegal(area);
    }
}
