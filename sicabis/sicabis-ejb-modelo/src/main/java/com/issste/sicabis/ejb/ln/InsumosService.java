/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.InsumosDAO;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Insumos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author erik
 */
@Stateful
public class InsumosService {

    @EJB
    private InsumosDAO insumosDao;

    public List<Insumos> traeListaInsumos() {
        return insumosDao.obtenerListaInsumos();
    }

    public List<Insumos> traeListaInsumosPorArea(Integer idArea) {
        return insumosDao.obtenerListaInsumosPorArea(idArea);
    }

    public List<Insumos> traeListaInsumosPorAreaGrupo(Integer idArea, Integer idGrupo) {
        return insumosDao.obtenerListaInsumosPorAreaGrupo(idArea, idGrupo);
    }

    public List<Insumos> traeListaInsumosPorAreaGrupos(Integer idArea, List<String> listGrupos) {
        return insumosDao.obtenerListaInsumosPorAreaGrupos(idArea, listGrupos);
    }

    public List<Insumos> buscarInsumosPorClave(String clave) {
        return insumosDao.buscarInsumosPorClave(clave);
    }

    public List<Insumos> buscarInsumosPorClaveLike(String clave) {
        return insumosDao.buscarInsumosPorClaveLike(clave);
    }

    public Integer idInsumosByClave(String clave) {
        return insumosDao.idInsumoByClave(clave);
    }

    public List<Insumos> insumos(String clave) {
        return insumosDao.inusmos(clave);
    }

    public Insumos obtieneByClave(String clave) {
        return insumosDao.obtieneByClave(clave);
    }

    public boolean save(Insumos insumo) {
        return insumosDao.save(insumo);
    }

    public boolean actualiza(Insumos insumo) {
        return insumosDao.actualiza(insumo);
    }
}
