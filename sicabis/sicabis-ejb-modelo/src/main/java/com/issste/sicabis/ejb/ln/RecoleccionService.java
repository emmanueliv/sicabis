/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.RecoleccionDAO;
import com.issste.sicabis.ejb.DAO.UnidadMedicaDAO;
import com.issste.sicabis.ejb.modelo.Recoleccion;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author fabianvr
 */
@Stateless
public class RecoleccionService {

    @EJB
    private RecoleccionDAO recoleccionDAOImplement;

    public Integer guardarRecoleccion(Recoleccion recoleccion) {
        Integer r = recoleccionDAOImplement.guardarRecoleccion(recoleccion);
        return r;
    }

    public List<Recoleccion> recoleccion() {
        List<Recoleccion> r = recoleccionDAOImplement.recoleccion();
        return r;
    }

    public Integer folioRecoleccion() {
        Integer numero;
        Integer n = recoleccionDAOImplement.folioRecoleccion();
        if (n != null) {
            numero = n + 1;
        } else {
            numero = 1;
        }
        return numero;
    }

    public BigDecimal precioPromedioByClave(String Clave) {
        BigDecimal precio;
        precio = recoleccionDAOImplement.precioPromedioByClave(Clave);
        return precio;
    }

    public List<Recoleccion> buscarRecoleccion(String folio) {
        return recoleccionDAOImplement.buscarRecoleccion(folio);
    }

    public List<Recoleccion> recoleccioById(Integer id) {
        return recoleccionDAOImplement.recoleccionById(id);
    }
}
