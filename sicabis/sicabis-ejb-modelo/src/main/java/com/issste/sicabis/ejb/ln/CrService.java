/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.CrDAO;
import com.issste.sicabis.ejb.modelo.Cr;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author Toshiba Manolo
 */
@Stateful
public class CrService {
    
     @EJB
     private CrDAO crDAO;
     
    public List<Cr> getAllCr() {
        return crDAO.getAllCr();
    }

    public Cr buscaCrPorNumCr(String numeroCr) {
        return crDAO.buscaCrPorNumCr(numeroCr);
    }
    
    public Cr buscaCrPorEjercicio(Integer ejercicio,Integer idArea){
      return crDAO.buscaCrPorEjercicio(ejercicio,idArea);
    }

    public List<Cr> buscaCrTypedQuery(Cr cr) {
        return crDAO.buscaCrTypedQuery(cr);
    }

    public Cr getCrByID(Integer idCr) {
       return crDAO.getCrByID(idCr);
    }

    public Cr save(Cr cr) {
        return crDAO.save(cr);
    }

    public Cr update(Cr cr) {
        return crDAO.update(cr);
    }


    public Integer updateCr(Cr cr) {
        return crDAO.updateCr(cr);
    }
     
    
}
