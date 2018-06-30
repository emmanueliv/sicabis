/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.EncabezadosReportesDTO;
import com.issste.sicabis.ejb.DTO.EntradasDevolucionesCenadiEstatusDTO;
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

/**
 *
 * @author 9RZCBG2
 */
public class RptReporteDevolucionesEntradasxEstatusBean {

    @EJB
    private reportesDTOsService reportesDTOsService;

    @EJB
    private InsumosService insumosService;

    //Variables
    JasperPrint jasperPrint;
    private Mensajes mensaje;
    private boolean filtroActivo;
    private boolean pageActivo;
    private String insumo;
    private Integer rptactivo;

    //Objetos y Modelo
    private EntradasDevolucionesCenadiEstatusDTO entradasDevolucionesCenadiEstatusDTO;
    private List<EntradasDevolucionesCenadiEstatusDTO> listDCEE;
    private List<Insumos> listInsumos;
    private List<EncabezadosReportesDTO> listInfoDCEE;
    private EncabezadosReportesDTO encabezadosReportesDTO;

    public RptReporteDevolucionesEntradasxEstatusBean() {
        rptactivo = 0;
        entradasDevolucionesCenadiEstatusDTO = new EntradasDevolucionesCenadiEstatusDTO();
        listDCEE = new ArrayList<>();
        listInfoDCEE = new ArrayList<>();
        listInsumos = new ArrayList<>();
        mensaje = new Mensajes();
        encabezadosReportesDTO = new EncabezadosReportesDTO();
        encabezadosReportesDTO.setFechaInicial(new Date());
        encabezadosReportesDTO.setFechaFinal(new Date());
    }

    @PostConstruct
    public void init() {
        listInsumos = insumosService.traeListaInsumos();
    }

    public void buscarLista() {
        listInfoDCEE = new ArrayList<>();
        listDCEE = new ArrayList<>();
        listInfoDCEE = reportesDTOsService.obtenerEntradasDevolucionesCenadiXrangoFechayEstatus(encabezadosReportesDTO.getFechaInicial(), encabezadosReportesDTO.getFechaFinal(), insumo);
        for (EncabezadosReportesDTO iterator : listInfoDCEE) {
            for (EntradasDevolucionesCenadiEstatusDTO iteratorInfo : iterator.getListInfoEDCenadixEstatus()) {
                listDCEE.add(iteratorInfo);
            }
        }
        if (listDCEE.size() <= 0) {
            mensaje.mensaje("No existen registros que contegan los parametros digitados.", "amarillo");
            rptactivo = 0;
        } else {
            rptactivo = 1;
        }
        System.out.println("lista:" + listDCEE.size());
    }

    public void exportarExcel() throws JRException, IOException {
        String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ISSSTE.png");
        for (EncabezadosReportesDTO epd : listInfoDCEE) {
            epd.setRutaLogo(logoPath);
        }
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listInfoDCEE);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/repoteEntradasDevolucionesxEstatus.jasper");
        //String reportPath = "C:\\Users\\Toshiba Manolo\\JaspersoftWorkspace\\MyReports\\reporteRcb.jasper";
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        System.out.println("lleno jasper");
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=Reporte anual de remisiones generadas por estatus.xlsx");
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

    //Getters and Setters
    public EntradasDevolucionesCenadiEstatusDTO getEntradasDevolucionesCenadiEstatusDTO() {
        return entradasDevolucionesCenadiEstatusDTO;
    }

    public void setEntradasDevolucionesCenadiEstatusDTO(EntradasDevolucionesCenadiEstatusDTO entradasDevolucionesCenadiEstatusDTO) {
        this.entradasDevolucionesCenadiEstatusDTO = entradasDevolucionesCenadiEstatusDTO;
    }

    public List<EntradasDevolucionesCenadiEstatusDTO> getListDCEE() {
        return listDCEE;
    }

    public void setListDCEE(List<EntradasDevolucionesCenadiEstatusDTO> listDCEE) {
        this.listDCEE = listDCEE;
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

    public List<EncabezadosReportesDTO> getListInfoDCEE() {
        return listInfoDCEE;
    }

    public void setListInfoDCEE(List<EncabezadosReportesDTO> listInfoDCEE) {
        this.listInfoDCEE = listInfoDCEE;
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
