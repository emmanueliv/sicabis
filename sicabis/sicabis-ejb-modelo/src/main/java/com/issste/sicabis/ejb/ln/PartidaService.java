/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.PartidaDAO;
import com.issste.sicabis.ejb.modelo.PartidaPresupuestal;
import com.issste.sicabis.ejb.modelo.PartidaPresupuestal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class PartidaService {

    @EJB
    PartidaDAO partidaDAOImpl;

    public List<PartidaPresupuestal> obtenerPartidaPresupuestales() {
        return partidaDAOImpl.getPartidaPresupuestales();
    }

     public List<PartidaPresupuestal> obtenerPartidaPresupuestalByIdAndActivo(int idPartPre) {
        return partidaDAOImpl.obtenerPartidaPresupuestalByIdAndActivo(idPartPre);
    }

    public List<PartidaPresupuestal> getPartidaPresupuestalesByActivo() {
        return partidaDAOImpl.getPartidaPresupuestalesByActivo();
    }

    public PartidaPresupuestal obtenerAreaByNombre(String nombre) {
        List<PartidaPresupuestal> list
                = partidaDAOImpl.obtenerPartidaPresupuestalByNombre(nombre);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void guardarPartidaP(PartidaPresupuestal partida) {
        partidaDAOImpl.guardarPartidaPresupuestal(partida);
    }
}
