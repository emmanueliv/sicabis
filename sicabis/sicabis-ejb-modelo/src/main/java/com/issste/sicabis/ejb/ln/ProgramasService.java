/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ProgramasDAO;
import com.issste.sicabis.ejb.modelo.Programas;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Toshiba Manolo
 */
@Stateless
public class ProgramasService {
    
     @EJB
     ProgramasDAO programasDao;
     
     public List<Programas> obtenerProgramas() {
         return programasDao.obtenerProgramas();
    }
    
}
