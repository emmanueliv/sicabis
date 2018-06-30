package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.FalloProcedimientoRcbDAO;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class FalloProcedimientoRcbService {

    @EJB
    private FalloProcedimientoRcbDAO falloProcedimientoRcbDAOImplement;

    public List<FalloProcedimientoRcb> obtenerByFalloProcRcb(Integer idProveedor,Integer tipoInsumo,Integer noProcedimiento) {
        return falloProcedimientoRcbDAOImplement.obtenerByFalloProcRcb(idProveedor,tipoInsumo,noProcedimiento);
    }

    public FalloProcedimientoRcb obtenerByIdFalloProcedimientoRcb(Integer idFalloProcedimientoRcb) {
        return falloProcedimientoRcbDAOImplement.obtenerByIdFalloProcedimientoRcb(idFalloProcedimientoRcb);
    }

    
    public boolean actualizaCantidadConvenio(FalloProcedimientoRcb falloProcedimientoRcb) {
        return falloProcedimientoRcbDAOImplement.actualizaCantidadConvenio(falloProcedimientoRcb);
    }

    public FalloProcedimientoRcb unbidadPiezaByIdFallo(Integer fpr) {
        return falloProcedimientoRcbDAOImplement.unidadPiezaByInsumoOrdenId(fpr);
    }
    
    public List<FalloProcedimientoRcb> obtenerByIdFalloProcedimientoRcb(String numeroP) {
        return falloProcedimientoRcbDAOImplement.obtenerByIdFalloProcedimientoRcb(numeroP);
    }
    
    public BigDecimal obtenerUltimoPrecio(String clave) {
        return falloProcedimientoRcbDAOImplement.obtenerUltimoPrecio(clave);
    }

}
