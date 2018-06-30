/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Compradores;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriosoft
 */
@Local
public interface CompradoresDAO {

    void guardarComprador(Compradores comprador);

    List<Compradores> obtenerCompradorByNombre(String nombreComprador);

    List<Compradores> obtenerCompradores();

    List<Compradores> obtenerCompradoresByActivo();
    
    List<Compradores> obtenerCompradoresByActivoAndSelect(int idComprador);

}
