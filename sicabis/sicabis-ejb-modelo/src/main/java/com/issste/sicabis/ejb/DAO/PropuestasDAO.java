
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Propuestas;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PropuestasDAO {
    
    List<Propuestas> getPropuestasByIdProcedimientoRcb(Integer idProcedimientoRcb);
    boolean guardarPropuestas(Propuestas propuestas);
    List<Propuestas> obtenerByProcedimientoRcb(Integer idProcedimientoRcb);
    boolean borrarByIdProcedimientoRcb(Integer idProcedimientoRcb);
    boolean actualizarPropuestas(Propuestas propuestas);
}
