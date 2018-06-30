package com.issste.sicabis.ejb.service.silodisa.DAO;

import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispG40;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MapaEjecutivoDispG40DAO {

    boolean guardar(MapaEjecutivoDispG40 dispG40);

    List<MapaEjecutivoDispG40> getAll();

    boolean eliminarExistencias();

    List<MapaEjecutivoDispG40> getByClaveUnidad(String claveUnidad);
}
