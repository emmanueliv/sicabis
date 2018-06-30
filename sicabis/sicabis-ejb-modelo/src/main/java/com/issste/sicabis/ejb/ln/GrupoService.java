/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.GrupoDAO;
import com.issste.sicabis.ejb.modelo.Grupo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author erik
 */
@Stateful
public class GrupoService {
    
     @EJB
    private GrupoDAO grupoDao;
     
     
     public List<Grupo> traeListaGrupos() {
        return grupoDao.obtenerGrupos();
    }

    public Grupo obtenerGrupoByNombre(String nombreGrupo) {
        List<Grupo> lista = grupoDao.obtenerGrupoByNombre(nombreGrupo);
        if(lista.isEmpty()){
            return null;
        } else {
            return lista.get(0);
        }
    }

    public void guardarGrupo(Grupo grupo) {
        grupoDao.guardarGrupo(grupo);
    }
     
    
}
