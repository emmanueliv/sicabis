/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Grupo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author erik
 */
@Local
public interface GrupoDAO {
    
    List<Grupo> obtenerGrupos();

    List<Grupo> obtenerGrupoByNombre(String nombreGrupo);

    void guardarGrupo(Grupo grupo);
    
}
