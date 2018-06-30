package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.ContratoOrdenDTO;
import com.issste.sicabis.ejb.modelo.Cancelaciones;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Rescisiones;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fabianvr
 */
@Local
public interface DetalleOrdenSuministroDAO {

    List<DetalleOrdenSuministro> cancelaciones(String criterio);

    List<DetalleOrdenSuministro> detalle(Integer idDetalle);

    Integer cantidadPendientePorContratoInsumo(Integer idInsumo);

    double porcentajeIncumplimientoProveedor(Integer proveedor);

    boolean actualizar(DetalleOrdenSuministro dos);

    Integer obtenerByIdFalloProcRcb(Integer idFalloProcedimientoRcb);

    List<DetalleOrdenSuministro> detalleOrdenById(Integer id);

    List<ContratoOrdenDTO> obtenerDisponibleByClave(String clave_insumo,Integer idArea);

    Integer falloByIdDetalleOrden(Integer dos);

    List<DetalleOrdenSuministro> detalleByFechaFinalPorDia(Date fechaExcedida,Date fechaAnterior);

    List<DetalleOrdenSuministro> detalleByFechaFinalPor5Dia(Date fechaExcedida,Date fechaAnterior);

    List<DetalleOrdenSuministro> obtenerListaOrdenesPendientesporSuministrar(String clave, String contrato);

    List<DetalleOrdenSuministro> obtenerClavesSinAtraso(String clave, String contrato);
}
