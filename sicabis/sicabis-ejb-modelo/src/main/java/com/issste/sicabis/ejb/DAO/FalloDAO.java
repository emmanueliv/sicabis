
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Fallos;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FalloDAO {
    
    boolean guardaFallos(Fallos fallos);
    boolean actualizaFallo(Fallos fallos);
    Fallos obtenerByNumeroFallo(String numeroFallo);
    List<Fallos> obtenerByFalloProcRcb(Fallos fallos);
    Fallos obtenerByNumProcRcb (String numeroProcedimiento);
}
