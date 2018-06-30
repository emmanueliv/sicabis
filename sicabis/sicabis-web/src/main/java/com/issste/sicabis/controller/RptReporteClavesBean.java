/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.EncabezadosReportesDTO;
import com.issste.sicabis.ejb.DTO.ReporteClavesDTO;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.reportesDTOsService;
import com.issste.sicabis.ejb.modelo.Insumos;
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

public class RptReporteClavesBean {

    @EJB
    private reportesDTOsService reportesDTOsService;

    @EJB
    private InsumosService insumosService;
    //Varibles
    JasperPrint jasperPrint;
    private Mensajes mensaje;
    private Integer brptactivo;
    private String insumo;
    private Integer noReporte;

    //Objetos y modelo
    private ReporteClavesDTO reporteClavesDTO;
    private List<Insumos> listInsumos;
    private List<ReporteClavesDTO> listReporteClavesDTO;
    private List<EncabezadosReportesDTO> listInfoReporteClavesDTO;
    private EncabezadosReportesDTO encabezadosReportesDTO;

    public RptReporteClavesBean() {
        brptactivo = 0;
        listInsumos = new ArrayList<>();
        listReporteClavesDTO = new ArrayList<>();
        listInfoReporteClavesDTO = new ArrayList<>();
        encabezadosReportesDTO = new EncabezadosReportesDTO();
        reporteClavesDTO = new ReporteClavesDTO();
        mensaje = new Mensajes();
    }

    @PostConstruct
    public void init() {
        encabezadosReportesDTO.setFechaInicial(new Date());
    }

    public void buscarLista() {
        if (noReporte != -1) {
            listReporteClavesDTO = new ArrayList<>();
            if (noReporte != 4) {
                listInfoReporteClavesDTO = reportesDTOsService.obtenerClaves(noReporte, encabezadosReportesDTO.getFechaInicial());
            } else if (noReporte == 4) {
                listInfoReporteClavesDTO = reportesDTOsService.obtenerResumenClaves(encabezadosReportesDTO.getFechaInicial());
            }
            for (EncabezadosReportesDTO iterator : listInfoReporteClavesDTO) {
                for (ReporteClavesDTO iteratorInfo : iterator.getListInfoClaves()) {
                    listReporteClavesDTO.add(iteratorInfo);
                }
            }
            if (listReporteClavesDTO.size() <= 0) {
                brptactivo = 0;
                mensaje.mensaje("No existen registros que contegan los parametros digitados.", "amarillo");
            } else {
                brptactivo = 1;
            }
            System.out.println("lista:" + listReporteClavesDTO.size());
        } else {
            brptactivo = 0;
            mensaje.mensaje("Eliga un reporte para poder continuar.", "rojo");
        }
        if (noReporte == 4 && listReporteClavesDTO.size() == 1) {
            mensaje.mensaje("Exportar reporte.", "verde");
        }
    }

    public void exportarExcel(Integer noReporte) throws JRException, IOException {
        String nameFile = "";
        String nombreArchivo = "";
        if (noReporte == 1) {
            nameFile = "reporteClavesSinExistencias.jasper";
            nombreArchivo = "Reporte de claves sin existencia";
        } else if (noReporte == 2) {
            nameFile = "reporteClavesCoberturaFechas.jasper";
            nombreArchivo = "Reporte de claves con cobertura menor a 30 dias";
        } else if (noReporte == 3) {
            nameFile = "reporteClavesCobertura75Dias.jasper";
            nombreArchivo = "Reporte de claves con cobertura menor a 75 dias";
        } else if (noReporte == 4) {
            nameFile = "reporteResumenClaves.jasper";
            nombreArchivo = "Resumen Claves";
        } else if (noReporte == 5) {
            nameFile = "reporteTotalClavesResumen.jasper";
            nombreArchivo = "Total de Claves";
        }

        String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ISSSTE.png");
        for (EncabezadosReportesDTO epd : listInfoReporteClavesDTO) {
            epd.setRutaLogo(logoPath);
        }
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listInfoReporteClavesDTO);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/" + nameFile + "");
        //String reportPath = "C:\\Users\\Toshiba Manolo\\JaspersoftWorkspace\\MyReports\\reporteRcb.jasper";
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        System.out.println("lleno jasper");
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + nombreArchivo + ".xlsx");
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
    public reportesDTOsService getReportesDTOsService() {
        return reportesDTOsService;
    }

    public void setReportesDTOsService(reportesDTOsService reportesDTOsService) {
        this.reportesDTOsService = reportesDTOsService;
    }

    public ReporteClavesDTO getReporteClavesDTO() {
        return reporteClavesDTO;
    }

    public void setReporteClavesDTO(ReporteClavesDTO reporteClavesDTO) {
        this.reporteClavesDTO = reporteClavesDTO;
    }

    public List<Insumos> getListInsumos() {
        return listInsumos;
    }

    public void setListInsumos(List<Insumos> listInsumos) {
        this.listInsumos = listInsumos;
    }

    public String getInsumo() {
        return insumo;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public Integer getNoReporte() {
        return noReporte;
    }

    public void setNoReporte(Integer noReporte) {
        this.noReporte = noReporte;
    }

    public List<ReporteClavesDTO> getListReporteClavesDTO() {
        return listReporteClavesDTO;
    }

    public void setListReporteClavesDTO(List<ReporteClavesDTO> listReporteClavesDTO) {
        this.listReporteClavesDTO = listReporteClavesDTO;
    }

    public Integer getBrptactivo() {
        return brptactivo;
    }

    public void setBrptactivo(Integer brptactivo) {
        this.brptactivo = brptactivo;
    }

    public List<EncabezadosReportesDTO> getListInfoReporteClavesDTO() {
        return listInfoReporteClavesDTO;
    }

    public void setListInfoReporteClavesDTO(List<EncabezadosReportesDTO> listInfoReporteClavesDTO) {
        this.listInfoReporteClavesDTO = listInfoReporteClavesDTO;
    }

    public EncabezadosReportesDTO getEncabezadosReportesDTO() {
        return encabezadosReportesDTO;
    }

    public void setEncabezadosReportesDTO(EncabezadosReportesDTO encabezadosReportesDTO) {
        this.encabezadosReportesDTO = encabezadosReportesDTO;
    }
}
