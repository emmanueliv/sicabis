package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FalloProcedimientoRcbDAO {

    List<FalloProcedimientoRcb> obtenerByFalloProcRcb(Integer idProveedor,Integer tipoInsumo,Integer noProcedimiento);

    FalloProcedimientoRcb obtenerByIdFalloProcedimientoRcb(Integer idFalloProcedimientoRcb);

    boolean actualizaCantidadConvenio(FalloProcedimientoRcb falloProcedimientoRcb);

    FalloProcedimientoRcb unidadPiezaByInsumoOrdenId(Integer falloProcedimientoRcbId);

    List<FalloProcedimientoRcb> obtenerByIdFalloProcedimientoRcb(String numeroP);
    
    BigDecimal obtenerUltimoPrecio(String clave);

}
