/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.EncabezadosReportesDTO;
import com.issste.sicabis.ejb.DTO.PiezasPendientesAnualDTO;
import com.issste.sicabis.ejb.ln.reportesDTOsService;
import com.issste.sicabis.utils.Mensajes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import org.primefaces.event.data.FilterEvent;
import org.primefaces.event.data.PageEvent;

/**
 *
 * @author 9RZCBG2
 */
public class RptPiezasPendientesAnualBean {

    @EJB
    private reportesDTOsService reportesDTOsService;

    //Variables
    JasperPrint jasperPrint;
    private Mensajes mensaje;
    private boolean filtroActivo;
    private boolean pageActivo;
    private Integer anio;
    private Integer anioAnterior;
    private Integer rptactivo;
    //Objetos y Modelo
    private PiezasPendientesAnualDTO piezasPendientesAnualDTO;
    private List<PiezasPendientesAnualDTO> listPPA;
    List<EncabezadosReportesDTO> listInfoPPA;
    EncabezadosReportesDTO encabezadosReportesDTO;

    public RptPiezasPendientesAnualBean() {
        rptactivo = 0;
        mensaje = new Mensajes();
        listPPA = new ArrayList<>();
        listInfoPPA = new ArrayList<>();
        piezasPendientesAnualDTO = new PiezasPendientesAnualDTO();
        encabezadosReportesDTO = new EncabezadosReportesDTO();
    }

    @PostConstruct
    public void init() {
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        setAnio(c.get(Calendar.YEAR));
        setAnioAnterior(anio - 1);

    }

    public void buscarLista() {
        if (encabezadosReportesDTO.getAnio() != -1) {
            listInfoPPA = new ArrayList<>();
            listPPA = new ArrayList<>();
            listInfoPPA = reportesDTOsService.obtenerPiezasPendientesXanio(encabezadosReportesDTO.getAnio());
            for (EncabezadosReportesDTO iterator : listInfoPPA) {
                for (PiezasPendientesAnualDTO iteratorInfo : iterator.getListInfoPPAD()) {
                    listPPA.add(iteratorInfo);
                }
            }
            if (listPPA.size() <= 0) {
                mensaje.mensaje("No existen registros que contegan los parametros digitados.", "amarillo");
                rptactivo = 0;
            } else {
                rptactivo = 1;
            }
            System.out.println("lista:" + listPPA.size());
        } else {
            rptactivo = 0;
            mensaje.mensaje("Seleccione un año.", "rojo");
        }
    }

    public void exportarExcel(Integer Anio) throws JRException, IOException {
        String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ISSSTE.png");
        for (EncabezadosReportesDTO epd : listInfoPPA) {
            epd.setRutaLogo(logoPath);
        }
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listInfoPPA);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/reportePiezasPendientes.jasper");
        //String reportPath = "C:\\Users\\Toshiba Manolo\\JaspersoftWorkspace\\MyReports\\reporteRcb.jasper";
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        System.out.println("lleno jasper");
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=Reporte de Piezas Pendientes.xlsx");
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

    public PiezasPendientesAnualDTO getPiezasPendientesAnualDTO() {
        return piezasPendientesAnualDTO;
    }

    public void setPiezasPendientesAnualDTO(PiezasPendientesAnualDTO piezasPendientesAnualDTO) {
        this.piezasPendientesAnualDTO = piezasPendientesAnualDTO;
    }

    public List<PiezasPendientesAnualDTO> getListPPA() {
        return listPPA;
    }

    public void setListPPA(List<PiezasPendientesAnualDTO> listPPA) {
        this.listPPA = listPPA;
    }

    public void filterListener(FilterEvent filterEvent) {
        System.out.println("filtro activo");
        filtroActivo = true;
    }

    public void pageListener(PageEvent pageEvent) {
        System.out.println("filtro inactivo");
        pageActivo = true;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getAnioAnterior() {
        return anioAnterior;
    }

    public void setAnioAnterior(Integer anioAnterior) {
        this.anioAnterior = anioAnterior;
    }

    public List<EncabezadosReportesDTO> getListInfoPPA() {
        return listInfoPPA;
    }

    public void setListInfoPPA(List<EncabezadosReportesDTO> listInfoPPA) {
        this.listInfoPPA = listInfoPPA;
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
