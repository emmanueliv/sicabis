/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.reportesDTOsDAO;
import com.issste.sicabis.ejb.DTO.EncabezadosReportesDTO;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class reportesDTOsService {

    @EJB
    private reportesDTOsDAO reportesDTOsDAOImplement;

    public List<EncabezadosReportesDTO> obtenerEntradasCenadiXrangoFecha(Date fechaInicial, Date FechaFinal, String insumo, String proveedor, String tipoInsumo) {
        return reportesDTOsDAOImplement.obtenerEntradasCenadiXrangoFecha(fechaInicial, FechaFinal, insumo, proveedor, tipoInsumo);
    }

    public List<EncabezadosReportesDTO> obtenerReporteClavesProceso(Date fechaInicial, String insumo, String proveedor) {
        return reportesDTOsDAOImplement.obtenerReporteClavesProceso(fechaInicial, insumo, proveedor);
    }

    public List<EncabezadosReportesDTO> obtenerEntradasDevolucionesCenadiXrangoFecha(Date fechaInicial, Date FechaFinal, String insumo, String proveedor, Integer noReporte) {
        return reportesDTOsDAOImplement.obtenerEntradasDevolucionesCenadiXrangoFecha(fechaInicial, FechaFinal, insumo, proveedor, noReporte);
    }

    public List<EncabezadosReportesDTO> obtenerEntradasDevolucionesCenadiXrangoFechayEstatus(Date fechaInicial, Date FechaFinal, String insumo) {
        return reportesDTOsDAOImplement.obtenerEntradasDevolucionesCenadiXrangoFechayEstatus(fechaInicial, FechaFinal, insumo);
    }

    public List<EncabezadosReportesDTO> obtenerPiezasPendientesXanio(Integer anio) {
        return reportesDTOsDAOImplement.obtenerPiezasPendientesXanio(anio);
    }

    public List<EncabezadosReportesDTO> obtenerClaves(Integer noReporte,Date fechaInicial) {
        return reportesDTOsDAOImplement.obtenerClaves(noReporte,fechaInicial);
    }

    public List<EncabezadosReportesDTO> obtenerRemisionesControlCalidadxClave(Date fechaInicial, Date FechaFinal, String insumo, Integer estatus, String lote, Integer idFabricante) {
        return reportesDTOsDAOImplement.obtenerRemisionesControlCalidadxClave(fechaInicial, FechaFinal, insumo, estatus, lote, idFabricante);
    }

    public Integer obtenerIngresoClavesxDia(String insumo, Date fecha) {
        return reportesDTOsDAOImplement.obtenerIngresoClavesxDia(insumo, fecha);
    }

    public Integer obtenerIngresoClavesPendientes(String insumo) {
        return reportesDTOsDAOImplement.obtenerIngresoClavesPendientes(insumo);
    }

    public List<String> obtenerExistenciaCenadi(String insumo) {
        return reportesDTOsDAOImplement.obtenerExistenciaCenadi(insumo);
    }

    public List<EncabezadosReportesDTO> obtenerResumenClaves(Date fechaInicial) {
        return reportesDTOsDAOImplement.obtenerResumenClaves(fechaInicial);
    }

    public List<EncabezadosReportesDTO> obtenerListConcentradoEntradas(Date Fecha) {
        return reportesDTOsDAOImplement.obtenerListConcentradoEntradas(Fecha);
    }

    public List<EncabezadosReportesDTO> obtenerListConcentradoPendientes() {
        return reportesDTOsDAOImplement.obtenerListConcentradoPendientes();
    }

}
