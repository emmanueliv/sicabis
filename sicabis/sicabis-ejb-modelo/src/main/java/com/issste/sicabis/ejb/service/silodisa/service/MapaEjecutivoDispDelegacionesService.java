package com.issste.sicabis.ejb.service.silodisa.service;

import com.issste.sicabis.ejb.service.silodisa.DAO.MapaEjecutivoDispDelegacionesDAO;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispDelegaciones;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class MapaEjecutivoDispDelegacionesService {

    @EJB
    private MapaEjecutivoDispDelegacionesDAO mapaEjecutivoDispDelegacionesDAOImplement;

    public boolean guardar(MapaEjecutivoDispDelegaciones dispDelegaciones) {
        return mapaEjecutivoDispDelegacionesDAOImplement.guardar(dispDelegaciones);
    }

    public List<MapaEjecutivoDispDelegaciones> getAll() {
        return mapaEjecutivoDispDelegacionesDAOImplement.getAll();
    }

    public boolean eliminarExistencias() {
        return mapaEjecutivoDispDelegacionesDAOImplement.eliminarExistencias();
    }

    public List<MapaEjecutivoDispDelegaciones> getByDelegacion(String delegacion) {
        return mapaEjecutivoDispDelegacionesDAOImplement.getByDelegacion(delegacion);
    }
}
