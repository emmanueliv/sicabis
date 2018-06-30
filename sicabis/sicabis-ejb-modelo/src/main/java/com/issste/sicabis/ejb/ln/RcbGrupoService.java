/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.RcbGrupoDAO;
import com.issste.sicabis.ejb.modelo.RcbGrupo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Toshiba Manolo
 */
@Stateful
public class RcbGrupoService {
    
     @EJB
     private RcbGrupoDAO rcbGrupoDAO;
     
    public List<RcbGrupo> getAllRcbGrupo() {
        return rcbGrupoDAO.getAllRcbGrupo();
    }

    public RcbGrupo save(RcbGrupo rcbGrupo) {
        return rcbGrupoDAO.save(rcbGrupo);
    }


    public RcbGrupo update(RcbGrupo rcbGrupo) {
        return rcbGrupoDAO.update(rcbGrupo);
    }
    
    public Integer deleteRcbGruposByIdRcb(Integer idRcb) {
    return  rcbGrupoDAO.deleteRcbGruposByIdRcb(idRcb);
    }
    
}
