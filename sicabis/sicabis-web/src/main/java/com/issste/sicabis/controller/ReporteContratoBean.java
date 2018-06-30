package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.ContratoDTO;
import com.issste.sicabis.DTO.InsumosDTO;
import com.issste.sicabis.DTO.OrdenSuministroDTO;
import com.issste.sicabis.DTO.RemisionDTO;
import com.issste.sicabis.ejb.DTO.RemisionesDTO;
import com.issste.sicabis.ejb.ln.ContratoService;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.ln.OrdenSuministroService;
import com.issste.sicabis.ejb.ln.RemisionesService;
import com.issste.sicabis.ejb.modelo.Clausulado;
import com.issste.sicabis.ejb.modelo.CompradorContrato;
import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Lote;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcbDestinos;
import com.issste.sicabis.ejb.modelo.Remisiones;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.NumeroLetra;
import com.issste.sicabis.utils.Utilidades;
import java.io.BufferedReader;
import java.io.IOException;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author fabianvr
 */
@ManagedBean(name = "reporteContratoBean")
@ViewScoped
public class ReporteContratoBean {

    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;

    @EJB
    private RemisionesService remisionesService;

    @EJB
    private OrdenSuministroService ordenSuministroService;

    @EJB
    private com.issste.sicabis.ejb.ln.ContratoService contratoService;

    Clausulado clausulado;
    ArchivosUtilidades archivoUtilidades = new ArchivosUtilidades();
    Utilidades u = new Utilidades();
    NumeroLetra numLetra = new NumeroLetra();
    private List<Contratos> contratoList;
    private Integer cantidadPiezas;
    private BigDecimal precioUnitario;
    private BigDecimal importeTotal;
    private String descripcion;
    private String clave;
    private String unidadPieza;
    private Integer renglon;
    private List<InsumosDTO> insumosList;
    private List<ContratoDTO> conList;
    private List<ContratoDTO> convenioList;
    private List<DetalleOrdenSuministro> ordenSList;
    private List<Remisiones> remiList;
    private List<RemisionDTO> remisionesList;
    private String oficio;
    private String rcb;
    private String nep;
    private List<OrdenSuministroDTO> ordenDetalleList;
    private Utilidades util = new Utilidades();
    JasperPrint jasperPrint = new JasperPrint();

    public ReporteContratoBean() {
        insumosList = new ArrayList();
        conList = new ArrayList();
        convenioList = new ArrayList();
        remisionesList = new ArrayList();
        ordenDetalleList = new ArrayList();
    }

    public void generarReporte() throws JRException, IOException, SQLException {
        contratoList = contratoService.contratoById(8);
        for (Contratos c : contratoList) {
            ContratoDTO con = new ContratoDTO();
            for (CompradorContrato cp : c.getCompradorContratoList()) {
                con.setCompradorApellido(cp.getIdComprador().getApaterno());
                con.setCompradorNombre(cp.getIdComprador().getNombre());
                con.setCompradorApellidoMaterno(cp.getIdComprador().getAmaterno());
            }
            con.setDescripcionFundamento(c.getIdFundamentoLegal().getDescripcion());
            for (ContratoFalloProcedimientoRcb cfpr : c.getIdContratoFalloProcedimientoRcbList()) {
                InsumosDTO i = new InsumosDTO();
                nep = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNep();
                rcb = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNumero();
                oficio = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getOficioSuficienciaPresupuestal();
                con.setSiglaTipoInsumos(cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla());
                con.setNotas(c.getNotas());
                con.setRcb(cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNumero());
                con.setOficioSuficienciaPresupuestal(cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getOficioSuficienciaPresupuestal());
                con.setDireccion(cfpr.getIdFalloProcedimientoRcb().getIdProveedor().getDireccion());
                con.setNombreProveedor(cfpr.getIdFalloProcedimientoRcb().getIdProveedor().getNombreProveedor());
                con.setNumeroProcedimiento(cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdProcedimiento().getNumeroProcedimiento());
                cantidadPiezas = cfpr.getIdFalloProcedimientoRcb().getCantidadModificada();
                precioUnitario = cfpr.getIdFalloProcedimientoRcb().getPrecioUnitario();
                clave = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave();
                if (!cfpr.getDescripcionAmplia().equals("")) {
                    descripcion = cfpr.getDescripcionAmplia();
                } else {
                    descripcion = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getDescripcion();
                }
                if (!cfpr.getNota().equals("")) {
                    descripcion = descripcion + " \n Nota: " + "\n" + cfpr.getNota();
                }
                importeTotal = precioUnitario.multiply(new BigDecimal(cantidadPiezas));
                unidadPieza = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdUnidadPieza().getDescripcion();
                renglon = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdInsumo();

                i.setCantidadPiezas(cantidadPiezas);
                i.setClave(clave);
                i.setDescripcion(descripcion);
                i.setIdClave(renglon);
                i.setImporteClave(importeTotal);
                i.setPrecioUnitario(precioUnitario);
                i.setUnidadPieza(unidadPieza);
                i.setNep(nep);
                i.setRcb(rcb);
                i.setOficio(oficio);
                insumosList.add(i);
                con.setInsumosList(insumosList);
            }
            con.setNoRupa(c.getIdContratoFalloProcedimientoRcbList().get(0).getIdFalloProcedimientoRcb().getIdProveedor().getNoRupa());
            con.setFechaContrato(c.getFechaContrato());
            con.setFundamentoLegal(c.getIdFundamentoLegal().getNombre());
            con.setImporte(c.getImporte());
            con.setNep(c.getNep());
            con.setNumeroContrato(c.getNumeroContrato() + " " + con.getSiglaTipoInsumos());
            con.setPartida(c.getIdPartidaPresupuestal().getPartidaPresupuestal());
            con.setTipoContrato(c.getIdTipoContrato().getDescripcion());
            con.setVigenciaFinal(c.getVigenciaFinal());
            con.setVigenciaInicial(c.getVigenciaInicial());
            con.setAño(c.getAnioAfectacion());
            Clausulado cal = (Clausulado) archivoUtilidades.obtieneObjetoSerializableClausulado(archivoUtilidades.NAMEFILECLAUSULACONTRATO, archivoUtilidades.PATHFILESCLAUSULAS);
            con.setClausulas(cal.getClausula());
            String numero = String.valueOf(c.getImporte());
            con.setImporteLetra(numLetra.convertir(numero, true));
            conList.add(con);
        }
        imprimirContrato();
    }

    public void generarReporteConvenio() throws JRException, IOException {
        contratoList = contratoService.contratoConvenioById(1);
        for (Contratos c : contratoList) {
            ContratoDTO con = new ContratoDTO();
            con.setNumeroConvenio(c.getNumeroConvenio());
            for (CompradorContrato cp : c.getCompradorContratoList()) {
                con.setCompradorApellido(cp.getIdComprador().getApaterno());
                con.setCompradorNombre(cp.getIdComprador().getNombre());
                con.setCompradorApellidoMaterno(cp.getIdComprador().getAmaterno());
            }
            con.setDescripcionFundamento(c.getIdFundamentoLegal().getDescripcion());
            for (ContratoFalloProcedimientoRcb cfpr : c.getIdContratoFalloProcedimientoRcbList()) {
                InsumosDTO i = new InsumosDTO();
                nep = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNep();
                rcb = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNumero();
                oficio = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getOficioSuficienciaPresupuestal();
                con.setNotas(c.getNotas());
                con.setRcb(cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNumero());
                con.setOficioSuficienciaPresupuestal(cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getOficioSuficienciaPresupuestal());
                con.setDireccion(cfpr.getIdFalloProcedimientoRcb().getIdProveedor().getDireccion());
                con.setNombreProveedor(cfpr.getIdFalloProcedimientoRcb().getIdProveedor().getNombreProveedor());
                con.setNumeroProcedimiento(cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdProcedimiento().getNumeroProcedimiento());
                cantidadPiezas = cfpr.getIdFalloProcedimientoRcb().getCantidadModificada();
                precioUnitario = cfpr.getIdFalloProcedimientoRcb().getPrecioUnitario();
                clave = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave();
                if (!cfpr.getDescripcionAmplia().equals("")) {
                    descripcion = cfpr.getDescripcionAmplia();
                } else {
                    descripcion = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getDescripcion();
                }
                if (!cfpr.getNota().equals("")) {
                    descripcion = descripcion + " \n Nota: " + "\n" + cfpr.getNota();
                }
                importeTotal = precioUnitario.multiply(new BigDecimal(cantidadPiezas));
                unidadPieza = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdUnidadPieza().getDescripcion();
                renglon = cfpr.getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdInsumo();

                i.setCantidadPiezas(cantidadPiezas);
                i.setClave(clave);
                i.setDescripcion(descripcion);
                i.setIdClave(renglon);
                i.setImporteClave(importeTotal);
                i.setPrecioUnitario(precioUnitario);
                i.setUnidadPieza(unidadPieza);
                i.setNep(nep);
                i.setRcb(rcb);
                i.setOficio(oficio);
                insumosList.add(i);
                con.setInsumosList(insumosList);
            }
            con.setNoRupa(c.getIdContratoFalloProcedimientoRcbList().get(0).getIdFalloProcedimientoRcb().getIdProveedor().getNoRupa());
            con.setFechaContrato(c.getFechaContrato());
            con.setFundamentoLegal(c.getIdFundamentoLegal().getNombre());
            con.setImporte(c.getImporte());
            con.setNep(c.getNep());
            con.setNumeroContrato(c.getNumeroContrato());
            con.setPartida(c.getIdPartidaPresupuestal().getPartidaPresupuestal());
            con.setTipoContrato(c.getIdTipoContrato().getDescripcion());
            con.setVigenciaFinal(c.getVigenciaFinal());
            con.setVigenciaInicial(c.getVigenciaInicial());
            con.setAño(c.getAnioAfectacion());
            Clausulado cal = (Clausulado) archivoUtilidades.obtieneObjetoSerializableClausulado(archivoUtilidades.NAMEFILECLAUSULACONTRATO, archivoUtilidades.PATHFILESCLAUSULAS);
            con.setClausulas(cal.getClausula());
            String numero = String.valueOf(c.getImporte());
            con.setImporteLetra(numLetra.convertir(numero, true));
            convenioList.add(con);
        }
        imprimirConvenio();
    }

    public void descargarRemision() throws JRException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        remiList = remisionesService.remisionByIdRemision(1);
        for (Remisiones r : remiList) {
            RemisionDTO rem = new RemisionDTO();
            rem.setArticulo(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getIdFundamentoLegal().getDescripcion());
            for (Lote l : r.getLoteList()) {
                String cad = "";
                cad = cad + format.format(l.getFechaCaducidad()) + "\n";
                String fab = "";
                fab = fab + format.format(l.getFechaFabricacion()) + "\n";
                String lote = "";
                lote = lote + l.getLote() + "\n";
                String cantPorLote = "";
                cantPorLote = cantPorLote + l.getCantidadLote() + "\n";
                rem.setCaducidad(cad);
                rem.setFabricacion(fab);
                rem.setLote(lote);
                rem.setCantidadPorLote(cantPorLote);
            }
            rem.setSigla(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdTipoInsumos().getSigla());
            rem.setNep(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getNep());
            rem.setCantidad(r.getCantidadRecibida());
            rem.setClave(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave());
            rem.setDescripcion(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getDescripcion());
            for (ProcedimientoRcbDestinos prd : r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getProcedimientoRcbDestinosList()) {
                rem.setDestino(prd.getIdDestino().getNombre());
                rem.setDireccionDestino(prd.getIdDestino().getDomicilio());
            }
            if (r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getProcedimientoRcbDestinosList() != null
                    || r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getProcedimientoRcbDestinosList().isEmpty()) {
                rem.setDestino("");
                rem.setDireccionDestino("");
            }
            rem.setDireccion(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getDireccion());
            rem.setFechaFin(r.getIdDetalleOrdenSuministro().getFechaEntregaFinal());
            rem.setFechaInicio(r.getIdDetalleOrdenSuministro().getFechaEntregaInicial());
            rem.setFechaRemision(r.getFechaRemision());
            rem.setGrupo(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdGrupo().getGrupo());//modificacion de modelo rcb
            BigDecimal importe = util.agregarDecimales(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario().multiply(new BigDecimal(r.getCantidadRecibida())));
            if (importe.toString().length() < 5) {
                rem.setImporte(importe.toString());
            } else {
                rem.setImporte(formateador.format(importe.doubleValue()));
            }
            rem.setNombreProveedor(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getNombreProveedor());
            rem.setNumeroContrato(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getNumeroContrato());
            rem.setNumeroOrden(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getNumeroOrden());
            rem.setPartida(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getIdPartidaPresupuestal().getPartidaPresupuestal());
            BigDecimal precioUnitario = util.agregarDecimales(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario());
            if (precioUnitario.toString().length() < 5) {
                rem.setPrecioUnitario(precioUnitario.toString());
            } else {
                rem.setPrecioUnitario(formateador.format(precioUnitario.doubleValue()));
            }
            rem.setRegistroControl(r.getRegistroControl());
            rem.setRenglon(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdInsumo());
            rem.setRfc(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getRfc());
            rem.setUnidad(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getIdUnidadPieza().getDescripcion());
            String numero = String.valueOf(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario().multiply(new BigDecimal(r.getCantidadRecibida())));
            rem.setNumeroLetra(numLetra.convertir(numero, true));
            rem.setUnidadMedica(r.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getIdUnidadesMedicas().getNombre());
            rem.setNumeroPorveedor(r.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getNumero());
            remisionesList.add(rem);
        }
        imprimirRemision();
    }

    public void imprimirContrato() throws JRException, IOException {
        System.out.println("con ----- " + conList);
        System.out.println("entro imprimir");
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(conList);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/contratoInsumo.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        System.out.println("lleno jasper");
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

        System.out.println("exporto imprimir");
        FacesContext.getCurrentInstance().responseComplete();
        System.out.println("salio imprimir");
    }

    public void imprimirConvenio() throws JRException, IOException {
        System.out.println("entro imprimir");
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(convenioList);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/convenioInsumos.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        System.out.println("lleno jasper");
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

        System.out.println("exporto imprimir");
        FacesContext.getCurrentInstance().responseComplete();
        System.out.println("salio imprimir");
    }

    public void imprimirRemision() throws JRException, IOException {
        System.out.println("entro imprimir");
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(remisionesList);
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/remisiones.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
        System.out.println("lleno jasper");
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

        System.out.println("exporto imprimir");
        FacesContext.getCurrentInstance().responseComplete();
        System.out.println("salio imprimir");
    }

}
