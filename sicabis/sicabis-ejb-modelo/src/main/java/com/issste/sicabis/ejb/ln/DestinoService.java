/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.DestinoDAO;
import com.issste.sicabis.ejb.modelo.Destinos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class DestinoService {

    @EJB
    private DestinoDAO destinoDAOImpl;

    public void guardarDestino(Destinos destino) {
        destinoDAOImpl.guardarDestino(destino);
    }

    public List<Destinos> obtenerDestinos() {
        return destinoDAOImpl.obtenerDestinos();
    }

    public List<Destinos> obtenerDestinosByActivo() {
        return destinoDAOImpl.obtenerDestinosByActivo();
    }

    public Destinos obtenerDestinoByCve(String claveDestino) {
        List<Destinos> destinosList
                = destinoDAOImpl.obtenerDestinoByCve(claveDestino);
        if (destinosList.isEmpty()) {
            return null;
        } else {
            return destinosList.get(0);
        }
    }

    public List<Destinos> obtenerDestinoByCveAndActivo(int claveDestino) {
        return destinoDAOImpl.obtenerDestinoByCveAndActivo(claveDestino);
    }

}
