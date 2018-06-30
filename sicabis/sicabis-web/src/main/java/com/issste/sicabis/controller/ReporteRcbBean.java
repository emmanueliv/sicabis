/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.InsumosDTO;
import com.issste.sicabis.DTO.RcbDTO;
import com.issste.sicabis.ejb.ln.RcbService;
import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
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
public class ReporteRcbBean implements Serializable {

    @EJB
    RcbService rcbService;

    List<Rcb> rcbList;
    List<RcbDTO> listaRcbDTO;

    public ReporteRcbBean() {
        rcbList = new ArrayList<>();
        listaRcbDTO = new ArrayList<>();
    }

    public void generarReporte() throws JRException, IOException {
        rcbList.add(rcbService.traerRcbId(1));
        for (Rcb rcb : rcbList) {
            RcbDTO rcbDTO = new RcbDTO();
            rcbDTO.setActivo(rcb.getActivo());
            rcbDTO.setPeriodo(rcb.getPeriodo());
            rcbDTO.setIdRcb(rcb.getIdRcb());
            rcbDTO.setImporteTotal(rcb.getImporteTotal());
            rcbDTO.setNombreUnidad(rcb.getIdUnidadResponsable().getNombre());
            rcbDTO.setNumero(rcb.getNumero());
            rcbDTO.setDestino(rcb.getDestino());
            rcbDTO.setFechaElaboracion(rcb.getFechaElaboracion());
            //rcbDTO.setGrupo(rcb.getIdGrupo().getGrupo());
            rcbDTO.setNep(rcb.getNep());
            rcbDTO.setOficioSuficienciaPresupuestal(rcb.getOficioSuficienciaPresupuestal());
            rcbDTO.setClave(rcb.getClave());
            for (RcbInsumos insumo : rcb.getRcbInsumosList()) {
                InsumosDTO insumoDTO = new InsumosDTO();
                insumoDTO.setCantidadPiezas(insumo.getCantidadPiezas());
                insumoDTO.setImporteClave(insumo.getImporte());
                insumoDTO.setPrecioUnitario(insumo.getPrecioUnitario());
               
            }
            listaRcbDTO.add(rcbDTO);
        }
        imprimirReporte();
    }

    public void imprimirReporte() throws JRException, IOException {
        JasperPrint jasperPrint;
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaRcbDTO);
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
