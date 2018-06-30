/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.NivelDAO;
import com.issste.sicabis.ejb.modelo.Nivel;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Toshiba Manolo
 */
@Stateful
public class NivelService {
    
     @EJB
     private NivelDAO nivelDAO;
     
     public List<Nivel> getAllNivel() {
         return nivelDAO.getAllNivel();
     }
    
}
