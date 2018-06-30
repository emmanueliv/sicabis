/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.service.silodisa.DAO.CatalogoInsumosDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.CatInsumos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author 9RZCBG2
 */
@LocalBean
@Stateless
public class CatalogoInsumosService {

    @EJB
    private CatalogoInsumosDAO catalogoInsumosDAOImplement;

    public boolean guardar(CatInsumos ci) {
        return catalogoInsumosDAOImplement.guardar(ci);
    }

    public boolean actualizar(CatInsumos ci) {
        return catalogoInsumosDAOImplement.actualizar(ci);
    }

    public List<CatInsumos> catalogoInsumosByClave(String clave) {
        return catalogoInsumosDAOImplement.catalogoInsumosByClave(clave);
    }

    public List<CatInsumos> getAllCatalogoInsumos() {
        return catalogoInsumosDAOImplement.getAllCatalogoInsumos();
    }

    public boolean eliminarExistencias() {
        return catalogoInsumosDAOImplement.eliminarExistencias();
    }
}
