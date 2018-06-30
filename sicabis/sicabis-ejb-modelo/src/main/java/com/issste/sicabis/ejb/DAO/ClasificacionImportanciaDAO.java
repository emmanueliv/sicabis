package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ClasificacionImportancia;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ClasificacionImportanciaDAO {

    void guardarClasificacionImportancia(ClasificacionImportancia clasificacionImportancia);

    List<ClasificacionImportancia> obtenerByClasificacion(ClasificacionImportancia clasificacionImportancia);

    Integer obtenerUltimoRegistro();
}
