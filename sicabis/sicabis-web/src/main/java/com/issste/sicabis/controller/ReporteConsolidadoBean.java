/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.ReporteConsolidadoDTO;
import com.issste.sicabis.ejb.ln.ReporteConciliacionService;
import com.issste.sicabis.utils.Mensajes;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author fabianvr
 */
public class ReporteConsolidadoBean {

    @EJB
    private ReporteConciliacionService reporteConciliacionService;

    private Mensajes mensaje = new Mensajes();
    private String criterioBUsqueda;
    private Integer busqueda;
    private List<ReporteConsolidadoDTO> reporteList;
    private boolean verMensaje;

    public void ReporteConsolidadoBean() {
    }

    public void iniciarPorcesoReporte() throws JRException, IOException {
        if (busqueda != -1) {
            if (!criterioBUsqueda.equals("")) {
                System.out.println("cb" + criterioBUsqueda + busqueda);
                reporteList = reporteConciliacionService.reporteConcolidadoList(criterioBUsqueda, busqueda);
                System.out.println("repor" + reporteList);
                descargarReporte();
            } else {
                if (busqueda == 1) {
                    verMensaje = true;
                    mensaje.mensaje("Favor de Ingresar un Numero de RCB", "amarillo");
                } else {
                    verMensaje = true;
                    mensaje.mensaje("Favor de Ingresar Clave", "amarillo");
                }
            }
        } else {
            verMensaje = true;
            mensaje.mensaje("No se Escojio un Criterio de Busqueda", "amarillo");
        }
    }

    JasperPrint jasperPrint;

    public void descargarReporte() throws JRException, IOException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(reporteList);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/reporteGeneral.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.xlsx");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter docxExporter = new JRXlsxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public String getCriterioBUsqueda() {
        return criterioBUsqueda;
    }

    public void setCriterioBUsqueda(String criterioBUsqueda) {
        this.criterioBUsqueda = criterioBUsqueda;
    }

    public Integer getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(Integer busqueda) {
        this.busqueda = busqueda;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
    }

}
