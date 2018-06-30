/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Perfiles;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PerfilesDAO {

    List<Perfiles> obtenerPerfiles();
    
    boolean guardaPerfil(Perfiles perfiles);
    
    boolean obtenerPerfilByNombre(String nombre, Integer idPerfil);

    Perfiles obtenerPerfilByIdUsuario(Integer idUsuario);
    
    List<Perfiles> getByNombreActivo(String nombre, Integer activo);
    
}
