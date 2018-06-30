/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.EncabezadosReportesDTO;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 9RZCBG2
 */
@Local
public interface reportesDTOsDAO {

    List<EncabezadosReportesDTO> obtenerEntradasCenadiXrangoFecha(Date fechaInicial, Date FechaFinal, String insumo, String proveedor, String tipoInsumo);

    List<EncabezadosReportesDTO> obtenerReporteClavesProceso(Date fechaInicial, String insumo, String proveedor);

    List<EncabezadosReportesDTO> obtenerEntradasDevolucionesCenadiXrangoFecha(Date fechaInicial, Date FechaFinal, String insumo, String proveedor, Integer noReporte);

    List<EncabezadosReportesDTO> obtenerEntradasDevolucionesCenadiXrangoFechayEstatus(Date fechaInicial, Date FechaFinal, String insumo);

    List<EncabezadosReportesDTO> obtenerPiezasPendientesXanio(Integer anio);

    List<EncabezadosReportesDTO> obtenerClaves(Integer noReporte,Date fechaFinal);

    List<EncabezadosReportesDTO> obtenerRemisionesControlCalidadxClave(Date fechaInicial, Date fechaFinal, String insumo, Integer estatus, String lote, Integer idFabricante);

    Integer obtenerIngresoClavesxDia(String insumo, Date fecha);

    Integer obtenerIngresoClavesPendientes(String insumo);

    List<String> obtenerExistenciaCenadi(String insumo);

    List<EncabezadosReportesDTO> obtenerResumenClaves(Date fechaInicial);

    List<EncabezadosReportesDTO> obtenerListConcentradoEntradas(Date Fecha);

    List<EncabezadosReportesDTO> obtenerListConcentradoPendientes();
}
