
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ContratoFalloProcedimientoRcbDAO {
    
    boolean borrarByIdContrato(Integer idContrato);

    List<ContratoFalloProcedimientoRcb> obtenerByClaves(String claveInsumo);

    List<ContratoFalloProcedimientoRcb> getContratoByIdFallo(int idFallo);

    List<ContratoFalloProcedimientoRcb> obtenerCfprByClave(String claveInsumo);
}
