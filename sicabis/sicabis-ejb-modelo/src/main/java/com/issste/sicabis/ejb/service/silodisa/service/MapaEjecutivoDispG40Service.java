package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.service.silodisa.DAO.MapaEjecutivoDispG40DAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispG40;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class MapaEjecutivoDispG40Service {

    @EJB
    private MapaEjecutivoDispG40DAO mapaEjecutivoDispG40DAOImplement;

    public boolean guardar(MapaEjecutivoDispG40 dispG40) {
        return mapaEjecutivoDispG40DAOImplement.guardar(dispG40);
    }

    public List<MapaEjecutivoDispG40> getAll() {
        return mapaEjecutivoDispG40DAOImplement.getAll();
    }

    public boolean eliminarExistencias() {
        return mapaEjecutivoDispG40DAOImplement.eliminarExistencias();
    }

    public List<MapaEjecutivoDispG40> getByClaveUnidad(String claveUnidad) {
        return mapaEjecutivoDispG40DAOImplement.getByClaveUnidad(claveUnidad);
    }
}
