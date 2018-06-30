/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.ConcentradoDTO;
import com.issste.sicabis.ejb.DTO.ConcentradoPendienteDTO;
import com.issste.sicabis.ejb.DTO.EncabezadosReportesDTO;
import com.issste.sicabis.ejb.ln.reportesDTOsService;
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


public class RptConcentradoBean {

    //EJB's
    @EJB
    private reportesDTOsService reportesDTOsService;

    //Variables
    JasperPrint jasperPrint;
    private Mensajes mensaje;
    private Integer noReporte;
    private Integer rptactivo;
    private Date fecha;
    //Modelo Objetos
    private ConcentradoDTO concentradoDTO;
    private List<ConcentradoDTO> listConcentrado;
    private List<ConcentradoPendienteDTO> listConcentradoPendiente;
    private List<EncabezadosReportesDTO> listInfoEDC;
    private EncabezadosReportesDTO encabezadosReportesDTO;

    public RptConcentradoBean() {
        rptactivo = 0;
        concentradoDTO = new ConcentradoDTO();
        listConcentradoPendiente = new ArrayList<>();
        encabezadosReportesDTO = new EncabezadosReportesDTO();
        listConcentrado = new ArrayList<>();
        listInfoEDC = new ArrayList<>();
        mensaje = new Mensajes();
    }

    @PostConstruct
    public void init() {
        fecha = new Date();
        encabezadosReportesDTO.setFechaFinal(new Date());
        encabezadosReportesDTO.setFechaInicial(new Date());
    }

    public void buscarLista() {
        if (noReporte != -1) {
            listInfoEDC = new ArrayList<>();
            listConcentrado = new ArrayList<>();
            if (noReporte == 1) {
                listInfoEDC = reportesDTOsService.obtenerListConcentradoEntradas(fecha);
                for (EncabezadosReportesDTO iterator : listInfoEDC) {
                    for (ConcentradoDTO iteratorInfo : iterator.getListConcentrado()) {
                        listConcentrado.add(iteratorInfo);
                    }
                }

                if (listConcentrado.size() <= 0) {
                    mensaje.mensaje("No existen registros que contegan los parametros digitados.", "amarillo");
                    rptactivo = 0;
                } else {
                    rptactivo = 1;
                }
                System.out.println("lista:" + listConcentrado.size());
            }
            if (noReporte == 2) {
                listInfoEDC = reportesDTOsService.obtenerListConcentradoPendientes();
                for (EncabezadosReportesDTO iterator : listInfoEDC) {
                    for (ConcentradoPendienteDTO iteratorInfo : iterator.getListConcentradoPendienteDTO()) {
                        listConcentradoPendiente.add(iteratorInfo);
                    }
                }
                if (listConcentradoPendiente.size() <= 0) {
                    mensaje.mensaje("No existen registros que contegan los parametros digitados.", "amarillo");
                    rptactivo = 0;
                } else {
                    rptactivo = 1;
                    mensaje.mensaje("Exportar Reporte", "verde");
                }
                System.out.println("lista:" + listConcentradoPendiente.size());

            }
        } else {
            rptactivo = 0;
            mensaje.mensaje("Selecciona un reporte.", "rojo");
        }
    }

    public void exportarExcel(Integer noReporte) throws JRException, IOException {
        // listEDC = reportesDTOsService.obtenerEntradasDevolucionesCenadiXrangoFecha(fechaInicial, fechaFinal, insumo, proveedor);
        String jasperReporte = "";
        if (noReporte == 1) {
            jasperReporte = "repoteConcentradoEntradas.jasper";
        } else if (noReporte == 2) {
            jasperReporte = "repoteConcentradoPendientes.jasper";
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
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.xlsx");
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
    public ConcentradoDTO getConcentradoDTO() {
        return concentradoDTO;
    }

    public void setConcentradoDTO(ConcentradoDTO concentradoDTO) {
        this.concentradoDTO = concentradoDTO;
    }

    public List<ConcentradoDTO> getListConcentrado() {
        return listConcentrado;
    }

    public void setListConcentrado(List<ConcentradoDTO> listConcentrado) {
        this.listConcentrado = listConcentrado;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
