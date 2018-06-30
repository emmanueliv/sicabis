/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.ReporteGeneralDTO;
import com.issste.sicabis.ejb.ln.ContratoFalloProcedimientoRcbService;
import com.issste.sicabis.ejb.ln.ContratoService;
import com.issste.sicabis.ejb.ln.FalloProcedimientoRcbService;
import com.issste.sicabis.ejb.ln.FalloService;
import com.issste.sicabis.ejb.ln.OrdenSuministroService;
import com.issste.sicabis.ejb.ln.ProcedimientoService;
import com.issste.sicabis.ejb.ln.ProcedimientosRcbService;
import com.issste.sicabis.ejb.ln.RcbService;
import com.issste.sicabis.ejb.ln.RemisionesService;
import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Fallos;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.ejb.modelo.Remisiones;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 * Clase de controladora para la presentacion del reporte general de seguimiento
 * (Arbol de flujo de la RCB)
 *
 * @author kriosoft
 */
public class ReporteGenealSeguimientoBean implements Serializable {

    /**
     * Inyeccion de servicios
     */
    @EJB
    private RcbService rcbService;

    @EJB
    private ProcedimientosRcbService procService;

    @EJB
    private FalloProcedimientoRcbService fallosService;

    @EJB
    private ContratoFalloProcedimientoRcbService contratoService;

    @EJB
    private OrdenSuministroService ordenService;

    @EJB
    private RemisionesService remisionesService;

    @EJB
    private ProcedimientoService procedimientoService;

    @EJB
    private FalloService falloService;

    @EJB
    private ContratoService contratoService1;
    
    
    /**
     * Variables
     */
    private boolean arbolVisible;
    private String numeroRcb;
    private Rcb rcb;
    private FalloProcedimientoRcb fallo;
    private List<ContratoFalloProcedimientoRcb> contratos;
    private List<OrdenSuministro> ordenes;
    private List<Remisiones> remisiones;

    /**
     * Clases de utilidades
     */
    private Utilidades util = new Utilidades();
    private Mensajes mensaje = new Mensajes();
    private ReporteGeneralDTO reporteDTO;
    private List<ReporteGeneralDTO> reporteDTOList;

    /**
     * Arbol de flujo de la RCB
     */
    private TreeNode arbol;
    private TreeNode nodoSeleccionado;

    /**
     * Hojas del arbol
     */
    private TreeNode nodoProcedimiento;
    private TreeNode nodoFallo;
    private TreeNode nodoContrato;
    private TreeNode nodoConvenio;
    private TreeNode nodoOrdenSuministro;
    private TreeNode nodoRemisiones;
    private List<Procedimientos> p;
    private List<FalloProcedimientoRcb> f;

    /**
     * *
     * Metodo constructor
     */
    public ReporteGenealSeguimientoBean() {
        rcb = new Rcb();
        fallo = new FalloProcedimientoRcb();
        contratos = new ArrayList<>();
        ordenes = new ArrayList<>();
        reporteDTO = new ReporteGeneralDTO();
        reporteDTOList = new ArrayList<>();
    }

    /**
     * Metodo @PostConstruct
     */
    @PostConstruct
    public void init() {

    }

    /**
     * Metodo para consultar la traza del RCB por Numero de RCB
     */
    public void consultarRcb() {
        reporteDTOList = new ArrayList<>();
        if (validarBuscar()) {
            arbol = null;
            rcb = new Rcb();
            List<Rcb> rcbList = rcbService.buscarRcbPorNumRcb(numeroRcb,null);
            if (rcbList != null && !rcbList.isEmpty()) {
                setRcb(rcbList.get(0));
                String prueba = rcbList.get(0).toString();
                arbol = new DefaultTreeNode(getRcb().getNumero());
                p = procService.obtenerByRcb(numeroRcb);
                if (p != null) {
                    for (Procedimientos procedimento : p) {
                        reporteDTO = new ReporteGeneralDTO();
                        reporteDTO.setRcb(getRcb().getNumero());
                        reporteDTO.setProcedimiento(procedimento.getNumeroProcedimiento());
                        nodoProcedimiento = new DefaultTreeNode("Procedimiento " + procedimento.getNumeroProcedimiento(), arbol);
                        f = fallosService.obtenerByIdFalloProcedimientoRcb(procedimento.getNumeroProcedimiento());
                        if (f != null) {
                            for (FalloProcedimientoRcb fpr : f) {
                                reporteDTO.setFallo(fpr.getIdFallo().getNumeroFallo());
                                nodoFallo = new DefaultTreeNode("Fallo " + fpr.getIdFallo().getNumeroFallo(), nodoProcedimiento);
                                contratos = contratoService.getContratoFalloRcbByIdFallo(fpr.getIdFallo().getIdFallo());
                                for (ContratoFalloProcedimientoRcb contrato : contratos) {
                                    reporteDTO.setContrato(contrato.getIdContrato().getNumeroContrato());
                                    nodoContrato = new DefaultTreeNode("Contrato " + contrato.getIdContrato().getNumeroContrato(), nodoFallo);
                                    if (contrato.getIdContrato().getNumeroConvenio() != null && !contrato.getIdContrato().getNumeroConvenio().isEmpty() && !contrato.getIdContrato().getNumeroConvenio().equals("null")) {
                                        nodoConvenio = new DefaultTreeNode("Convenio " + contrato.getIdContrato().getNumeroConvenio(), nodoContrato);
                                        reporteDTO.setConvenio(contrato.getIdContrato().getNumeroConvenio());
                                    }
                                    ordenes = ordenService.obtenerOrdenByNumContrato(contrato.getIdContrato().getIdContrato());
                                    for (OrdenSuministro orden : ordenes) {
                                        reporteDTO.setOrdenSuministro(orden.getNumeroOrden());
                                        nodoOrdenSuministro = new DefaultTreeNode("Orden de suministro " + orden.getNumeroOrden(), nodoContrato);
                                        remisiones = remisionesService.obtenerRemisionByOrden(orden.getNumeroOrden());
                                        for (Remisiones remision : remisiones) {
                                            reporteDTO.setRemision(remision.getRegistroControl());
                                            nodoRemisiones = new DefaultTreeNode("Remision " + remision.getRegistroControl(), nodoOrdenSuministro);
                                        }
                                    }
                                }
                            }
                        }
                        System.out.println(reporteDTO.getProcedimiento());
                        reporteDTOList.add(reporteDTO);
                    }
                    setArbolVisible(true);
                } else {
                    mensaje.mensaje("El RCB no cuenta con procedimientos.", "verde");
                }

            } else {
                mensaje.mensaje("No se encontraron resultados.", "amarillo");
            }
        } else {
            mensaje.mensaje("Debes capturar el Numero de RCB.", "amarillo");
        }
    }

    /**
     * Metodo para validar que sea capturado el numero de rcb
     *
     * @return
     */
    public boolean validarBuscar() {
        return !numeroRcb.equals("");
    }

    /**
     * Metodo que va llenando los nodos del arbol
     */
    public void onNodeSelect() {
        try {
            llenarNodo();
        } catch (IOException ex) {
            Logger.getLogger(ReporteGenealSeguimientoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que direccina a los detalle de los nodos del arbol
     *
     * @throws java.io.IOException
     */
    public void llenarNodo() throws IOException {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        String ruta = "";
        String leyenda = "";
        int posicion = 0;
        Integer seleccion = nodoSeleccionado.getRowKey().length();
        if(nodoSeleccionado.getData().toString().startsWith("Convenio")) {
        seleccion = 6;
        }
        System.out.println("convenio"
               +nodoSeleccionado.getRowKey().length());
        switch (seleccion.intValue()) {
            case 1:
                Procedimientos procedimientos = new Procedimientos();
                for (Procedimientos pro : p) {
                    leyenda = "Procedimiento " + pro.getNumeroProcedimiento();
                    if (nodoSeleccionado.getData().toString().equals(leyenda)) {
                        procedimientos = procedimientoService.obtenerByNumeroProcedimiento(pro.getNumeroProcedimiento());
                    }
                }
                util.setContextAtributte("procedimientos", procedimientos);
                ruta = "/vistas/adquisicion/detalleProcedimiento.xhtml";
                break;
            case 3:
                Fallos fallos = new Fallos();
                for (FalloProcedimientoRcb fpr : f) {
                    leyenda = "Fallo " + fpr.getIdFallo().getNumeroFallo();
                    if (nodoSeleccionado.getData().toString().equals(leyenda)) {
                        fallos = falloService.obtenerByNumeroFallo(fpr.getIdFallo().getNumeroFallo());
                    }
                }
                util.setContextAtributte("fallos", fallos);
                ruta = "/vistas/adquisicion/detalleFallo.xhtml";
                break;

            case 5:
                Contratos contrato = new Contratos();
                for (ContratoFalloProcedimientoRcb con : contratos) {
                    leyenda = "Contrato " + con.getIdContrato().getNumeroContrato();
                    if (nodoSeleccionado.getData().toString().equals(leyenda)) {
                        System.out.println("contrato"+con.getIdContrato().getNumeroContrato());
                        contrato = contratoService1.obtenerByOneNumeroContrato(con.getIdContrato().getNumeroContrato());
                    }
                    posicion++;
                }
                util.setContextAtributte("contratos", contrato);
                ruta = "/vistas/adquisicion/detalleContrato.xhtml";
                break;
            case 6:
                Contratos convenio = new Contratos();
                for (ContratoFalloProcedimientoRcb con : contratos) {
                    leyenda = "Convenio " + con.getIdContrato().getNumeroConvenio();
                    if (nodoSeleccionado.getData().toString().equals(leyenda)) {
                        System.out.println("contrato"+con.getIdContrato().getNumeroContrato());
                        convenio = contratoService1.obtenerByOneNumeroContrato(con.getIdContrato().getNumeroContrato());
                    }
                    posicion++;
                }
                util.setContextAtributte("contratos", convenio);
                ruta = "/vistas/adquisicion/detalleConvenio.xhtml";
                break;
            case 7:
                OrdenSuministro ordenSuministro = new OrdenSuministro();
                for (OrdenSuministro or : ordenes) {
                    leyenda = "Orden de suministro " + or.getNumeroOrden();
                    if (nodoSeleccionado.getData().toString().equals(leyenda)) {
                        ordenSuministro = ordenes.get(posicion);
                    }
                    posicion++;
                }
                util.setContextAtributte("opcionOrden", 0);
                util.setContextAtributte("ordenSuministro", ordenSuministro);
                ruta = "/vistas/adquisicion/detalleOrdenSuministro.xhtml";
                break;
            case 9:
                Remisiones remision = new Remisiones();
                for (Remisiones rem : remisiones) {
                    leyenda = "Remision " + rem.getRegistroControl();
                    if (nodoSeleccionado.getData().toString().equals(leyenda)) {
                        remision = remisiones.get(posicion);
                    }
                    posicion++;
                }
                util.setContextAtributte("remisiones", remision);
                ctx.redirect(ctxPath + "/vistas/recepcion/detalleRemision.xhtml?faces-redirect=true&idRemision="
                        + remision.getIdRemision());
                break;
        }
        ctx.redirect(ctxPath + ruta);
    }

    /**
     * Getters y Setters
     *
     * @return
     */
    public String getNumeroRcb() {
        return numeroRcb;
    }

    public void setNumeroRcb(String numeroRcb) {
        this.numeroRcb = numeroRcb;
    }

    public Rcb getRcb() {
        return rcb;
    }

    public void setRcb(Rcb rcb) {
        this.rcb = rcb;
    }

    public TreeNode getArbol() {
        return arbol;
    }

    public void setArbol(TreeNode arbol) {
        this.arbol = arbol;
    }

    public boolean isArbolVisible() {
        return arbolVisible;
    }

    public void setArbolVisible(boolean arbolVisible) {
        this.arbolVisible = arbolVisible;
    }

    public TreeNode getNodoSeleccionado() {
        return nodoSeleccionado;
    }

    public void setNodoSeleccionado(TreeNode nodoSeleccionado) {
        this.nodoSeleccionado = nodoSeleccionado;
    }

    public ReporteGeneralDTO getReporteDTO() {
        return reporteDTO;
    }

    public void setReporteDTO(ReporteGeneralDTO reporteDTO) {
        this.reporteDTO = reporteDTO;
    }

    public List<ReporteGeneralDTO> getReporteDTOList() {
        return reporteDTOList;
    }

    public void setReporteDTOList(List<ReporteGeneralDTO> reporteDTOList) {
        this.reporteDTOList = reporteDTOList;
    }

}
