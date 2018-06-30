/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.CompradoresDAO;
import com.issste.sicabis.ejb.modelo.Compradores;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class CompradorService {

    @EJB
    CompradoresDAO comparadoresDAOImpl;

    public void guardarComprador(Compradores comprador) {
        comparadoresDAOImpl.guardarComprador(comprador);
    }

    public Compradores obtenerCompradorByNombre(String nombreComprador) {
        List<Compradores> lista = comparadoresDAOImpl.obtenerCompradorByNombre(nombreComprador);
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public List<Compradores> obtenerCompradores() {
        return comparadoresDAOImpl.obtenerCompradores();
    }

    public List<Compradores> obtenerCompradoresByActivo() {
        return comparadoresDAOImpl.obtenerCompradoresByActivo();
    }

    public List<Compradores> obtenerCompradoresByActivoAndSelect(int idComprador) {
        return comparadoresDAOImpl.obtenerCompradoresByActivoAndSelect(idComprador);
    }
}
