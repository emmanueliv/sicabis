
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ProcedimientoRcbDAO {
    
    boolean borrarByIdProcedimiento(Integer idProcedimiento);
    void guardaProcedimientoRcb(ProcedimientoRcb procedimientoRcb);
    List<ProcedimientoRcb> obtenerByNumeroProc(String numeroProcedimiento);
    boolean actualizaProcedimientoRcb(ProcedimientoRcb procedimientoRcb);
    List<ProcedimientoRcb> obtenerByIdNumRcbTipoCompra(String numeroRcb, int tipoCompra);
    List<ProcedimientoRcb> obtenerByIdProcedimiento(Integer idProcedimiento);
    
}
