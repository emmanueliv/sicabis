/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.EncabezadosReportesDTO;
import com.issste.sicabis.ejb.DTO.EntradasCenadiDTO;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.ProveedorService;
import com.issste.sicabis.ejb.ln.TipoInsumosService;
import com.issste.sicabis.ejb.ln.reportesDTOsService;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.TipoInsumos;
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

public class RptEntradasCenadiBean {

    @EJB
    private reportesDTOsService reportesDTOsService;

    @EJB
    private TipoInsumosService tipoInsumosService;

    @EJB
    private ProveedorService proveedorService;

    @EJB
    private InsumosService insumosService;

    //Variables
    JasperPrint jasperPrint;
    private Mensajes mensaje;
    private String insumo;
    private String proveedor;
    private String reporte;
    private Integer rptactivo;
    private String tipoInsumo;
    //Modelo Objetos
    private EncabezadosReportesDTO encabezadosReportesDTO;
    private EntradasCenadiDTO entradasCenadiDTO;
    private List<EntradasCenadiDTO> listECDtO;
    private List<EncabezadosReportesDTO> listInfoECDTO;
    private List<Proveedores> listProveedores;
    private List<Insumos> listInsumos;
    private List<TipoInsumos> listTipoInsumos;

    public RptEntradasCenadiBean() {
        rptactivo = 0;
        tipoInsumo = "1";
        encabezadosReportesDTO = new EncabezadosReportesDTO();
        entradasCenadiDTO = new EntradasCenadiDTO();
        mensaje = new Mensajes();
        listProveedores = new ArrayList<>();
        listInsumos = new ArrayList<>();
        listECDtO = new ArrayList<>();
        listInfoECDTO = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        listTipoInsumos = tipoInsumosService.listTipoInsumos();
        listInsumos = insumosService.traeListaInsumos();
        listProveedores = proveedorService.proveedoresAll();
        encabezadosReportesDTO.setFechaInicial(new Date());
        encabezadosReportesDTO.setFechaFinal(new Date());
    }

    public void buscarLista() {
        listECDtO = new ArrayList<>();
        listInfoECDTO = new ArrayList<>();
        if (!reporte.equals("-1")) {
            if (reporte.equals("1")) {
                listInfoECDTO = reportesDTOsService.obtenerEntradasCenadiXrangoFecha(encabezadosReportesDTO.getFechaInicial(), encabezadosReportesDTO.getFechaFinal(), insumo, proveedor, tipoInsumo);
            } else if (reporte.equals("2")) {
                listInfoECDTO = reportesDTOsService.obtenerReporteClavesProceso(encabezadosReportesDTO.getFechaInicial(), insumo, proveedor);
            }
            for (EncabezadosReportesDTO iterator : listInfoECDTO) {
                for (EntradasCenadiDTO iteratorInfo : iterator.getListInfoECD()) {
                    listECDtO.add(iteratorInfo);
                }
            }
            if (listECDtO.size() <= 0) {
                rptactivo = 0;
                mensaje.mensaje("No existen registros que contegan los parametros digitados.", "amarillo");
            } else {
                rptactivo = 1;
            }
            System.out.println("lista:" + listECDtO.size());
        } else {
            mensaje.mensaje("Seleccione un reporte.", "amarillo");
        }
    }

    public void exportarExcel() throws JRException, IOException {
        String nombreReporte = "";
        String nombreArchivo = "";
        if (reporte.equals("1")) {
            nombreReporte = "reporteEntradaCenadi.jasper";
            nombreArchivo = "Reporte de Entradas al Cenadi";
        } else if (reporte.equals("2")) {
            nombreReporte = "reporteClavesEnProceso.jasper";
            nombreArchivo = "Reporte de claves en proceso";
        }
        String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/ISSSTE.png");
        for (EncabezadosReportesDTO epd : listInfoECDTO) {
            epd.setRutaLogo(logoPath);
        }
        //listECDtO = reportesDTOsService.obtenerEntradasCenadiXrangoFecha(fechaInicial, fechaFinal, insumo, proveedor);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listInfoECDTO);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/" + nombreReporte + "");
        //String reportPath = "C:\\Users\\Toshiba Manolo\\JaspersoftWorkspace\\MyReports\\reporteRcb.jasper";
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        System.out.println("lleno jasper");
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename="+nombreArchivo+".xlsx");
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

    //getters and setters
    public EntradasCenadiDTO getEntradasCenadiDTO() {
        return entradasCenadiDTO;
    }

    public void setEntradasCenadiDTO(EntradasCenadiDTO entradasCenadiDTO) {
        this.entradasCenadiDTO = entradasCenadiDTO;
    }

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

    public List<EntradasCenadiDTO> getListECDtO() {
        return listECDtO;
    }

    public void setListECDtO(List<EntradasCenadiDTO> listECDtO) {
        this.listECDtO = listECDtO;
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

    public String getTipoInsumo() {
        return tipoInsumo;
    }

    public void setTipoInsumo(String tipoInsumo) {
        this.tipoInsumo = tipoInsumo;
    }

    public List<TipoInsumos> getListTipoInsumos() {
        return listTipoInsumos;
    }

    public void setListTipoInsumos(List<TipoInsumos> listTipoInsumos) {
        this.listTipoInsumos = listTipoInsumos;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

}
