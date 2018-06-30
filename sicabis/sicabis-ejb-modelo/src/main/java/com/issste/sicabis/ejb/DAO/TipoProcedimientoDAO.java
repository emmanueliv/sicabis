package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoProcedimiento;
import java.util.List;
import javax.ejb.Local;


@Local
public interface TipoProcedimientoDAO {
    
    List<TipoProcedimiento> obtenerTiposProcedimientos();

    List<TipoProcedimiento> obtenerAlmacenByNombre(String nombre);

    void guardarAlmacen(TipoProcedimiento tipoProcedimiento);
    
}
