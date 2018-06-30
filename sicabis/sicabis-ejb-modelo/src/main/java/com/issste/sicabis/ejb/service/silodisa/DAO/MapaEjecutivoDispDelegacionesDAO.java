package com.issste.sicabis.ejb.service.silodisa.DAO;

import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispDelegaciones;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MapaEjecutivoDispDelegacionesDAO {

    boolean guardar(MapaEjecutivoDispDelegaciones dispDelegaciones);

    List<MapaEjecutivoDispDelegaciones> getAll();

    boolean eliminarExistencias();
    
    List<MapaEjecutivoDispDelegaciones> getByDelegacion(String delegacion);
}
