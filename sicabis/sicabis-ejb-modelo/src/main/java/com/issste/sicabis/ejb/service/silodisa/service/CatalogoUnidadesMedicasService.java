/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.service.silodisa.DAO.CatalogoUnidadesMedicasDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.CatUnidadMedica;
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
public class CatalogoUnidadesMedicasService {

    @EJB
    private CatalogoUnidadesMedicasDAO catalogoUnidadesMedicasDAOImplement;

    public boolean guardar(CatUnidadMedica cum) {
        return catalogoUnidadesMedicasDAOImplement.guardar(cum);
    }

    public boolean actualizar(CatUnidadMedica cum) {
        return catalogoUnidadesMedicasDAOImplement.actualizar(cum);
    }

    public List<CatUnidadMedica> catalogoUnidadesMedicasByUmu(String umu) {
        return catalogoUnidadesMedicasDAOImplement.catalogoUnidadesMedicasByUmu(umu);
    }

    public boolean eliminarExistencias() {
        return catalogoUnidadesMedicasDAOImplement.eliminarExistencias();
    }
    
    public List<CatUnidadMedica> getAll() {
        return catalogoUnidadesMedicasDAOImplement.getAll();
    }
}
