/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.EncabezadosReportesDTO;
import com.issste.sicabis.ejb.DTO.EntradasDevolucionesCenadiDTO;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.ProveedorService;
import com.issste.sicabis.ejb.ln.reportesDTOsService;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.utils.Mensajes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author 9RZCBG2
 */
public class RptReporteDevolucionesEntradasBean {

    @EJB
    private reportesDTOsService reportesDTOsService;
    @EJB
    private ProveedorService proveedorService;
    @EJB
    private InsumosService insumosService;

    //Variables
    JasperPrint jasperPrint;
    private Mensajes mensaje;
    private String insumo;
    private String proveedor;
    private List<Proveedores> listProveedores;
    private List<Insumos> listInsumos;
    private Integer noReporte;
    private Integer rptactivo;
    //Modelo Objetos
    private EntradasDevolucionesCenadiDTO entradasDevolucionesCenadiDTO;
    List<EntradasDevolucionesCenadiDTO> listEDC;
    List<EncabezadosReportesDTO> listInfoEDC;
    EncabezadosReportesDTO encabezadosReportesDTO;

    public RptReporteDevolucionesEntradasBean() {
        rptactivo = 0;
        entradasDevolucionesCenadiDTO = new EntradasDevolucionesCenadiDTO();
        encabezadosReportesDTO = new EncabezadosReportesDTO();
        listInsumos = new ArrayList<>();
        listProveedores = new ArrayList<>();
        listEDC = new ArrayList<>();
        listInfoEDC = new ArrayList<>();
        mensaje = new Mensajes();
    }

    @PostConstruct
    public void init() {
        listProveedores = proveedorService.proveedoresAll();
        listInsumos = insumosService.traeListaInsumos();
        encabezadosReportesDTO.setFechaInicial(new Date());
    }

    public void buscarLista() {
        if (noReporte != -1) {
            listInfoEDC = new ArrayList<>();
            listEDC = new ArrayList<>();
            listInfoEDC = reportesDTOsService.obtenerEntradasDevolucionesCenadiXrangoFecha(encabezadosReportesDTO.getFechaInicial(), encabezadosReportesDTO.getFechaInicial(), insumo, proveedor, noReporte);
            for (EncabezadosReportesDTO iterator : listInfoEDC) {
                for (EntradasDevolucionesCenadiDTO iteratorInfo : iterator.getListInfoEDCenadi()) {
                    listEDC.add(iteratorInfo);
                }
            }
            if (listEDC.size() <= 0) {
                mensaje.mensaje("No existen registros que contegan los parametros digitados.", "amarillo");
                rptactivo = 0;
            } else {
                rptactivo = 1;
            }
            System.out.println("lista:" + listEDC.size());
        } else {
            rptactivo = 0;
            mensaje.mensaje("Selecciona un reporte.", "rojo");
        }
    }

    public void exportarExcel(Integer noReporte) throws JRException, IOException {
        String nombre = "";
        // listEDC = reportesDTOsService.obtenerEntradasDevolucionesCenadiXrangoFecha(fechaInicial, fechaFinal, insumo, proveedor);
        String jasperReporte = "";
        if (noReporte == 1) {
            jasperReporte = "reporteEntredas.jasper";
            nombre ="Reporte de Entredas";
        } else {
            jasperReporte = "repoteEntradasDevoluciones.jasper";
            nombre = "Reporte de Entredas y Devoluciones";
        }
        String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ISSSTE.png");
        for (EncabezadosReportesDTO epd : listInfoEDC) {
            epd.setRutaLogo(logoPath);
        }
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listInfoEDC);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/" + jasperReporte + "");
        //String reportPath = "C:\\Users\\Toshiba Manolo\\JaspersoftWorkspace\\MyReports\\reporteRcb.jasper";
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        System.out.println("lleno jasper");
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename="+nombre+".xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
//        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        JRXlsxExporter docxExporter = new JRXlsxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();

        System.out.println("exporto imprimir");
        FacesContext.getCurrentInstance().responseComplete();
        System.out.println("salio imprimir");
        mensaje.mensaje("Exportacion realizada", "verde");
    }

    //Getters y Setters
    public String getInsumo() {
        return insumo;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public List<Proveedores> getListProveedores() {
        return listProveedores;
    }

    public void setListProveedores(List<Proveedores> listProveedores) {
        this.listProveedores = listProveedores;
    }

    public List<Insumos> getListInsumos() {
        return listInsumos;
    }

    public void setListInsumos(List<Insumos> listInsumos) {
        this.listInsumos = listInsumos;
    }

    public EntradasDevolucionesCenadiDTO getEntradasDevolucionesCenadiDTO() {
        return entradasDevolucionesCenadiDTO;
    }

    public void setEntradasDevolucionesCenadiDTO(EntradasDevolucionesCenadiDTO entradasDevolucionesCenadiDTO) {
        this.entradasDevolucionesCenadiDTO = entradasDevolucionesCenadiDTO;
    }

    public List<EntradasDevolucionesCenadiDTO> getListEDC() {
        return listEDC;
    }

    public void setListEDC(List<EntradasDevolucionesCenadiDTO> listEDC) {
        this.listEDC = listEDC;
    }

    public Integer getNoReporte() {
        return noReporte;
    }

    public void setNoReporte(Integer noReporte) {
        this.noReporte = noReporte;
    }

    public EncabezadosReportesDTO getEncabezadosReportesDTO() {
        return encabezadosReportesDTO;
    }

    public void setEncabezadosReportesDTO(EncabezadosReportesDTO encabezadosReportesDTO) {
        this.encabezadosReportesDTO = encabezadosReportesDTO;
    }

    public Integer getRptactivo() {
        return rptactivo;
    }

    public void setRptactivo(Integer rptactivo) {
        this.rptactivo = rptactivo;
    }

}
