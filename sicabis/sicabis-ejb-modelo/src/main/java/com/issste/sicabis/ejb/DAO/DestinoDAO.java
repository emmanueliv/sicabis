/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Destinos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriosoft
 */
@Local
public interface DestinoDAO {

    List<Destinos> obtenerDestinos();

    List<Destinos> obtenerDestinoByCve(String claveDestino);

    void guardarDestino(Destinos destino);
    
    List<Destinos> obtenerDestinosByActivo();
    
    List<Destinos> obtenerDestinoByCveAndActivo(int claveDestino);
    
}
