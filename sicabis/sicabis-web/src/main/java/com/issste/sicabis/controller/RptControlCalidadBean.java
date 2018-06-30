/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.ControlCalidadDTO;
import com.issste.sicabis.ejb.DTO.EncabezadosReportesDTO;
import com.issste.sicabis.ejb.ln.FabricanteService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.reportesDTOsService;
import com.issste.sicabis.ejb.modelo.Fabricante;
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
public class RptControlCalidadBean {

    @EJB
    private FabricanteService fabricanteService;
    @EJB
    private InsumosService insumosService;
    @EJB
    private reportesDTOsService reportesDTOsService;

    //Varibles
    JasperPrint jasperPrint;
    private Mensajes mensaje;
    private String insumo;
    private Integer estatus;
    private Integer rptactivo;
    private Integer idFabricante;
    private String lote;
    //Objetos y modelo
    private ControlCalidadDTO controlCalidadDTO;
    private List<Insumos> listInsumos;
    private List<ControlCalidadDTO> listControlCalidadDTO;
    private List<EncabezadosReportesDTO> listInfoControlCalidad;
    private List<Fabricante> listFabricante;
    private EncabezadosReportesDTO encabezadosReportesDTO;

    public RptControlCalidadBean() {
        rptactivo = 0;
        listInsumos = new ArrayList<>();
        listControlCalidadDTO = new ArrayList<>();
        listInfoControlCalidad = new ArrayList<>();
        listFabricante = new ArrayList<>();
        encabezadosReportesDTO = new EncabezadosReportesDTO();
        controlCalidadDTO = new ControlCalidadDTO();
        mensaje = new Mensajes();
    }

    @PostConstruct
    public void init() {
        listFabricante = fabricanteService.fabricanteList();
        listInsumos = insumosService.traeListaInsumos();
        encabezadosReportesDTO.setFechaFinal(new Date());
        encabezadosReportesDTO.setFechaInicial(new Date());
    }

    public void buscarLista() {
        if (estatus != -1) {
            listControlCalidadDTO = new ArrayList<>();
            listInfoControlCalidad = reportesDTOsService.obtenerRemisionesControlCalidadxClave(encabezadosReportesDTO.getFechaInicial(), encabezadosReportesDTO.getFechaFinal(), insumo, estatus, lote, idFabricante);
            for (EncabezadosReportesDTO iterator : listInfoControlCalidad) {
                for (ControlCalidadDTO iteratorInfo : iterator.getListInfoControlCalidad()) {
                    listControlCalidadDTO.add(iteratorInfo);
                }
            }
            if (listControlCalidadDTO.size() <= 0) {
                rptactivo = 0;
                mensaje.mensaje("No existen registros que contegan los parametros digitados.", "amarillo");
            } else {
                rptactivo = 1;
            }
            System.out.println("lista:" + listControlCalidadDTO.size());
        } else {
            rptactivo = 0;
            mensaje.mensaje("Seleccione un estaus.", "rojo");
        }
    }

    public void exportarExcel() throws JRException, IOException {
        String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ISSSTE.png");
        for (EncabezadosReportesDTO epd : listInfoControlCalidad) {
            epd.setRutaLogo(logoPath);
        }
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listInfoControlCalidad);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/reporteControlCalidad.jasper");
        //String reportPath = "C:\\Users\\Toshiba Manolo\\JaspersoftWorkspace\\MyReports\\reporteRcb.jasper";
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        System.out.println("lleno jasper");
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=Control de Calidad.xlsx");
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

    public String getInsumo() {
        return insumo;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public ControlCalidadDTO getControlCalidadDTO() {
        return controlCalidadDTO;
    }

    public void setControlCalidadDTO(ControlCalidadDTO controlCalidadDTO) {
        this.controlCalidadDTO = controlCalidadDTO;
    }

    public List<Insumos> getListInsumos() {
        return listInsumos;
    }

    public void setListInsumos(List<Insumos> listInsumos) {
        this.listInsumos = listInsumos;
    }

    public List<ControlCalidadDTO> getListControlCalidadDTO() {
        return listControlCalidadDTO;
    }

    public void setListControlCalidadDTO(List<ControlCalidadDTO> listControlCalidadDTO) {
        this.listControlCalidadDTO = listControlCalidadDTO;
    }

    public List<EncabezadosReportesDTO> getListInfoControlCalidad() {
        return listInfoControlCalidad;
    }

    public void setListInfoControlCalidad(List<EncabezadosReportesDTO> listInfoControlCalidad) {
        this.listInfoControlCalidad = listInfoControlCalidad;
    }

    public EncabezadosReportesDTO getEncabezadosReportesDTO() {
        return encabezadosReportesDTO;
    }

    public void setEncabezadosReportesDTO(EncabezadosReportesDTO encabezadosReportesDTO) {
        this.encabezadosReportesDTO = encabezadosReportesDTO;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Integer getRptactivo() {
        return rptactivo;
    }

    public void setRptactivo(Integer rptactivo) {
        this.rptactivo = rptactivo;
    }

    public Integer getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(Integer idFabricante) {
        this.idFabricante = idFabricante;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public List<Fabricante> getListFabricante() {
        return listFabricante;
    }

    public void setListFabricante(List<Fabricante> listFabricante) {
        this.listFabricante = listFabricante;
    }
}
