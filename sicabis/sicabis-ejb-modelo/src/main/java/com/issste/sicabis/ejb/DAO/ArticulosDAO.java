/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.FundamentoLegal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriosoft
 */
@Local
public interface ArticulosDAO {

    List<FundamentoLegal> getFundamentos();

    List<FundamentoLegal> getFundamentosByActivo();

    List<FundamentoLegal> obtenerFundamentoLegalByNombre(String nombreFundamentoLegal);

    void guardarFundamentoLegal(FundamentoLegal area);

    List<FundamentoLegal> obtenerFundamentoLegalByIdAndActivo(int fundamentoLegal);

}
