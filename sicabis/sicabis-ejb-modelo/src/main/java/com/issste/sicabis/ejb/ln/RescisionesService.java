/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.RescisionesDAO;
import com.issste.sicabis.ejb.modelo.Rescisiones;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author fabianvr
 */
@Stateless
public class RescisionesService {

    @EJB
    private RescisionesDAO rescisionesDAOImplement;

    public Integer guardar(Rescisiones r) {
        return rescisionesDAOImplement.guardar(r);
    }

    public boolean actualizar(Rescisiones r) {
        return rescisionesDAOImplement.actualizar(r);
    }

    public List<Rescisiones> rescisiones(Integer detalle) {
        return rescisionesDAOImplement.rescisiones(detalle);
    }

    public List<Rescisiones> cancelacionesRescisiones(String criterio, Integer busqueda) {
        return rescisionesDAOImplement.rescisionesConsulta(criterio, busqueda);
    }

    public List<Rescisiones> recisionesByOrden(String orden) {
        return rescisionesDAOImplement.rescisionesByOrden(orden);
    }

    public List<Rescisiones> rescisionesesById(Integer c) {
        return rescisionesDAOImplement.rescisionesByClave(c);
    }
}
