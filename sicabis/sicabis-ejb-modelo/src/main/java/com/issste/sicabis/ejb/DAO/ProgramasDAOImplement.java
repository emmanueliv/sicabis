/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.PeriodoArea;
import com.issste.sicabis.ejb.modelo.Programas;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Toshiba Manolo
 */
@Stateless
public class ProgramasDAOImplement implements ProgramasDAO{
    
     @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Programas> obtenerProgramas() {
         List<Programas> resultList = null;
        try {
            resultList = em.createNamedQuery("Programas.findAll").getResultList();
        } catch (Exception e) {
            Logger.getLogger(ProgramasDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        return resultList;
    }
     
}
