package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.DetalleOrdenSuministroDAO;
import com.issste.sicabis.ejb.DTO.ContratoOrdenDTO;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author fabianvr
 */
@Stateless
public class DetalleOrdenSuministroService {

    @EJB
    private DetalleOrdenSuministroDAO detalleOrdenSuministroDAOImplement;

    public List<DetalleOrdenSuministro> cancelacionesRescisiones(String criterio) {
        return detalleOrdenSuministroDAOImplement.cancelaciones(criterio);
    }

    public List<DetalleOrdenSuministro> detalle(Integer idDetalle) {
        return detalleOrdenSuministroDAOImplement.detalle(idDetalle);
    }

    public double porcentajeProveedor(Integer proveedor) {
        return detalleOrdenSuministroDAOImplement.porcentajeIncumplimientoProveedor(proveedor);
    }

    public boolean actualizar(DetalleOrdenSuministro dos) {
        return detalleOrdenSuministroDAOImplement.actualizar(dos);
    }

    public Integer obtenerByIdFalloProcRcb(Integer idFalloProcedimientoRcb) {
        return detalleOrdenSuministroDAOImplement.obtenerByIdFalloProcRcb(idFalloProcedimientoRcb);
    }

    public Integer cantidadPendientePorContratoInsumo(Integer idInsumo) {
        return detalleOrdenSuministroDAOImplement.cantidadPendientePorContratoInsumo(idInsumo);
    }

    public List<DetalleOrdenSuministro> ordenById(Integer id) {
        return detalleOrdenSuministroDAOImplement.detalleOrdenById(id);
    }

    public List<ContratoOrdenDTO> obtenerDisponibleByClave(String clave_insumo, Integer idArea) {
        return detalleOrdenSuministroDAOImplement.obtenerDisponibleByClave(clave_insumo, idArea);
    }

    public Integer falloByIdDetalleOrden(Integer dos) {
        return detalleOrdenSuministroDAOImplement.falloByIdDetalleOrden(dos);
    }

    public List<DetalleOrdenSuministro> detalleByFechaFinalPorDia(Date fechaExcedida, Date fechaAnterior) {
        return detalleOrdenSuministroDAOImplement.detalleByFechaFinalPorDia(fechaExcedida, fechaAnterior);
    }

    public List<DetalleOrdenSuministro> detalleByFechaFinalPor5Dia(Date fechaExcedida, Date fechaAnterior) {
        return detalleOrdenSuministroDAOImplement.detalleByFechaFinalPor5Dia(fechaExcedida, fechaAnterior);
    }

    public List<DetalleOrdenSuministro> obtenerListaOrdenesPendientesporSuministrar(String clave, String contrato) {
        return detalleOrdenSuministroDAOImplement.obtenerListaOrdenesPendientesporSuministrar(clave, contrato);
    }

    public List<DetalleOrdenSuministro> obtenerClavesSinAtraso(String clave, String contrato) {
        return detalleOrdenSuministroDAOImplement.obtenerClavesSinAtraso(clave, contrato);
    }

}
