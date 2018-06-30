/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.RemisionesDTO;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Remisiones;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fabianvr
 */
@Local
public interface RemisionesDAO {

    List<Remisiones> getByRemision();

    List<RemisionesDTO> getByOrden(String criterioBusqueda, Integer busqueda);

    List<RemisionesDTO> getInsumos(String orden);

    Integer getNumeroRegistroControl();

    Integer guardarRemision(Remisiones remisiones);

    boolean actualizarRemision(Remisiones remisiones);

    DetalleOrdenSuministro getDetalleOrden(Integer dos, String os);

    boolean getRemisionExistente(Integer dos, String orden);

    List<Remisiones> remisionByRegistro(String registroControl, String fechaInicio, String fechaFin);

    List<Remisiones> remisionByRegistroControl(String registroControl, String fechaInicio, String fechaFin);

    List<Remisiones> remisionByRecepcionInsumos(String registroControl, String fechaInicio, String fechaFin);

    List<RemisionesDTO> remisionByControlCalidad();

    List<RemisionesDTO> remisionByRegistroControl(String registroControl);

    List<Remisiones> remisionesAll(String registroControl);
//    List<RemisionesDTO> remisionesByControlCalidad();

    List<Remisiones> remisionesByRecepcionInsumos();

    List<Remisiones> remisionesBienesByRegestro(String registroControl);

    List<Remisiones> remisionByIdRemision(Integer idRemision);

    List<Remisiones> remisionDevolucion(Integer insumo, String orden);

    Long remisionesesByIdDetalle(Integer detalle, String o);

    List<DetalleOrdenSuministro> remisionesByOrden(Integer i, String orden);

    List<Remisiones> folioExistente(String folio);

    List<Remisiones> remisionesByResgistroControlDevolucion(String regisgtroControl);

    List<Remisiones> remisionesByIdOrden(String numeroOrden);

    List<Remisiones> obtenerRemisionesDevolucionAll();

    Remisiones getByRegistroControl(String registroControl);

    Remisiones getByIdCanjePermuta(Integer idCanjePermuta);

    Double obtenerPorcentajePiezasPorSuministrar(String contrato,String clave);

    Double obtenerCantidadEntregadaPorOrden(Integer ordenSuministro);
    
    Integer getLastRegistroControlByYear(Integer year, String opcion);

}
