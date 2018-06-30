/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.CancelacionesDAO;
import com.issste.sicabis.ejb.modelo.Cancelaciones;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author fabianvr
 */
@Stateless
public class CancelacionesService {

    @EJB
    private CancelacionesDAO cancelacionesDAOImplement;

    public Integer guardar(Cancelaciones c) {
        return cancelacionesDAOImplement.guardar(c);
    }

    public boolean actualizar(Cancelaciones c) {
        return cancelacionesDAOImplement.actualizar(c);
    }

    public List<Cancelaciones> cancelaciones(Integer detalle) {
        return cancelacionesDAOImplement.cancelacion(detalle);
    }

    public List<Cancelaciones> cancelacionesConsulta(String criterio, Integer busqueda) {
        return cancelacionesDAOImplement.cancelacionesByConsulta(criterio, busqueda);
    }

    public List<Cancelaciones> consulta(Integer can) {
        return cancelacionesDAOImplement.consulta(can);
    }

    public List<Cancelaciones> cancelacionesByOrden(String orden) {
        return cancelacionesDAOImplement.cancelacionesByOrden(orden);
    }

    public List<Cancelaciones> cancelacionById(Integer can) {
        return cancelacionesDAOImplement.cancelacionesByClave(can);
    }
}
