/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.CrDTO;
import com.issste.sicabis.DTO.InsumosDTO;
import com.issste.sicabis.ejb.ln.CrService;
import com.issste.sicabis.ejb.modelo.Cr;
import com.issste.sicabis.ejb.modelo.CrInsumos;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
 * @author kriosoft
 */
@ManagedBean(name = "reporteCRBean")
@ViewScoped
public class ReporteCRBean implements Serializable{

    @EJB
    CrService crService;

    List<Cr> cr;
    List<CrDTO> listaCrDTO = new ArrayList<>();

    public ReporteCRBean() {
        cr = new ArrayList();
        listaCrDTO = new ArrayList();
    }

    public void generarReporte() throws JRException, IOException {
        cr.add(crService.buscaCrPorNumCr("CR-2016"));
        CrDTO crDTO = new CrDTO();

        for (Cr c : cr) {
            crDTO.setActivo(c.getActivo());
            crDTO.setEjercicio(c.getEjercicio());
            crDTO.setIdCr(c.getIdCr());
            crDTO.setImporteTotal(c.getImporteTotal());
            crDTO.setNombreUnidadResp(c.getIdUnidadResponsable().getNombre());
            crDTO.setNumeroCr(c.getNumeroCr());
            for (CrInsumos insumo : c.getCrInsumosList()) {
                InsumosDTO insumoDTO = new InsumosDTO();
                insumoDTO.setCantidadPiezas(insumo.getCantidadPiezas());
                insumoDTO.setImporteClave(insumo.getImporte());
                insumoDTO.setPrecioUnitario(insumo.getPrecioUnitario());
                crDTO.getListaInsumos().add(insumoDTO);
            }
            listaCrDTO.add(crDTO);
        }
        imprimirReporte();
    }

    public void imprimirReporte() throws JRException, IOException {
        JasperPrint jasperPrint;
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaCrDTO);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/crReport.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=crReport.xls");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JRXlsxExporter docxExporter = new JRXlsxExporter();
        docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        docxExporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();

    }
    
}
