/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.GrupoTerapeutico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriosoft
 */
@Local
public interface GrupoTerapeuticoDAO {

    public List<GrupoTerapeutico> getGruposTerapeuticos();

    public List<GrupoTerapeutico> obtenerGpTerapeuticoByNombre(String nombre);

    public void guardarGrupoTerapeutico(GrupoTerapeutico gp);
    
}
