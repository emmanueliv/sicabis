/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Cr;
import com.issste.sicabis.ejb.modelo.Rcb;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Toshiba Manolo
 */
@Local
public interface CrDAO {
    
    List<Cr> getAllCr();
    Cr buscaCrPorNumCr(String numeroCr);
    Cr buscaCrPorEjercicio(Integer ejercicio,Integer idArea);
    List<Cr> buscaCrTypedQuery(Cr cr);
    Cr getCrByID(Integer idCr);
    Cr save(Cr cr);
    Cr update(Cr cr);
    Integer updateCr(Cr cr);
    
}
