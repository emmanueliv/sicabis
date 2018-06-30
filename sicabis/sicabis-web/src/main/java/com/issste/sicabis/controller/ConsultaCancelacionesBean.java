/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.CancelacionesService;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.ln.RescisionesService;
import com.issste.sicabis.ejb.modelo.Cancelaciones;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Remisiones;
import com.issste.sicabis.ejb.modelo.Rescisiones;
import com.issste.sicabis.utils.Mensajes;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author fabianvr
 */
@ManagedBean(name = "consultaCancelacionesBean")
@ViewScoped
public class ConsultaCancelacionesBean {
    
    @EJB
    private CancelacionesService cancelacionesService;
    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;
    Mensajes mensaje = new Mensajes();
    private String penas;
    private Integer idDetalle;
    private List<Cancelaciones> detalleList;
    private String nombreProveedor;
    private Date fechaInicio;
    private Date fechaFin;
    private String numeroOrden;
    private String contrato;
    private Integer cantidadPiezasSolicitadas;
    private Integer cantidadPiezasRecibida;
    private Integer cantidadPiezasPendientes;
    private String registroControl;
    private String folioRemision;
    private Date fechaRemision;
    private Estatus idEstatus;
    private BigDecimal importeTotal;
    private BigDecimal importeRecibido;
    private BigDecimal importePendiente;
    private BigDecimal precioUnitario;
    private double porcentajeIncumplimiento;
    private List<Remisiones> remiList;
    private boolean verMensaje;
    private String clave;
    private boolean verRemision;
    private Integer idProveedor;
    private double porcentajeProveedor;
    private BigDecimal pena;
    private int diasIncumplimiento;
    private Integer idCancelacion;
    private Integer cancelado;
    private String verInicioCancelacion;
    private boolean verCancelado;
    private Integer rescision;
    private boolean verInicio;
    private boolean verInicioR;
    private boolean verRescision;
    private String verInicioRescision;
    private List<Rescisiones> res;
    private List<Cancelaciones> can;

    public ConsultaCancelacionesBean() {
        penas = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCancelacion");
        remiList = new ArrayList();
    }

    @PostConstruct
    public void init() {
        cancelaciones();
    }

    public void cancelaciones() {
        idDetalle = Integer.parseInt(penas);
        detalleList = cancelacionesService.consulta(idDetalle);
        System.out.println("entro");
        verCancelacion();
        consultaCancelaciones();

    }

    public void consultaCancelaciones() {
        verRemision = false;
        for (Cancelaciones dos : detalleList) {
            idProveedor = dos.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getIdProveedor();
            clave = dos.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave();
            nombreProveedor = dos.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getNombreProveedor();
            fechaInicio = dos.getIdDetalleOrdenSuministro().getFechaEntregaInicial();
            fechaFin = dos.getIdDetalleOrdenSuministro().getFechaEntregaFinal();
            numeroOrden = dos.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getNumeroOrden();
            contrato = dos.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getNumeroContrato();
            cantidadPiezasSolicitadas = dos.getIdDetalleOrdenSuministro().getCantidadSuministrar();
            fechaFin = dos.getIdDetalleOrdenSuministro().getFechaEntregaFinal();
            importeTotal = dos.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario().multiply(new BigDecimal(cantidadPiezasSolicitadas));
            System.out.println("si---------------------->" + dos.getIdDetalleOrdenSuministro().getRemisionesList());
            if (dos.getIdDetalleOrdenSuministro().getRemisionesList() != null) {
                for (Remisiones r : dos.getIdDetalleOrdenSuministro().getRemisionesList()) {
                   
                    verRemision = true;
                    cantidadPiezasRecibida = r.getCantidadRecibida();
                    cantidadPiezasPendientes = cantidadPiezasSolicitadas - cantidadPiezasRecibida;
                    registroControl = r.getRegistroControl();
                    folioRemision = r.getFolioRemision();
                    fechaRemision = r.getFechaRemision();
                    idEstatus = r.getIdEstatus();
                    importeRecibido = dos.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario().multiply(new BigDecimal(r.getCantidadRecibida()));
                    importePendiente = importeTotal.subtract(importeRecibido);
                    addRemision();
                }
            }
            if (verRemision == false) {
                verRemision = false;
                System.out.println("no hay Remision");
                cantidadPiezasRecibida = 0;
                cantidadPiezasPendientes = cantidadPiezasSolicitadas;
                importeRecibido = new BigDecimal(0);
                importePendiente = new BigDecimal(cantidadPiezasSolicitadas);

            }
            precioUnitario = dos.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario();

            Date fechaActual = new Date();
            int dA = fechaActual.getDay();
            int mA = fechaActual.getMonth();
            int yA = fechaActual.getYear();
            int dF = fechaFin.getDay();
            int mF = fechaFin.getMonth();
            int yF = fechaFin.getYear();
            int diffDays = 0;
            int dias = 0;
            Calendar fechaInicial = new GregorianCalendar(yF, mF, dF);
            Calendar fechaFinal = new GregorianCalendar(yA, mA, dA);
            while (fechaInicial.before(fechaFinal) && !fechaInicial.equals(fechaFinal)) {
                if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                    diffDays++;
                }
                fechaInicial.add(Calendar.DATE, 1);
            }
            if (diffDays != 0) {
                diffDays = diffDays - 1;
                System.out.println("d" + diffDays);
            }
            System.out.println("d----->" + diffDays);
            diasIncumplimiento = diffDays;
            if (diffDays != 0) {
                if (diffDays == 1) {
                    porcentajeIncumplimiento = 2.5;
                } else if (diffDays == 2) {
                    porcentajeIncumplimiento = 5;
                } else if (diffDays == 3) {
                    porcentajeIncumplimiento = 7.5;
                } else if (diffDays == 4) {
                    porcentajeIncumplimiento = 10;
                } else {
                    porcentajeIncumplimiento = 10;
                }
            }
        }

        porcentajeProveedor = detalleOrdenSuministroService.porcentajeProveedor(idProveedor);
        pena = importeTotal.multiply(new BigDecimal(0.10));
        pena = pena.setScale(2, BigDecimal.ROUND_DOWN);

    }

    

    public void verCancelacion() {
        if (res != null) {

            can = cancelacionesService.cancelaciones(idDetalle);
            if (can != null) {
                for (Cancelaciones c : can) {
                    if (c.getIdEstatus().getIdEstatus() == 153) {
                        verInicio = true;
                        verInicioCancelacion = "true";
                    } else if (c.getIdEstatus().getIdEstatus() == 154) {
                        verCancelado = true;
                        verInicioCancelacion = "true";
                    }
                }
            }
        }
    }

    public void addRemision() {
        Remisiones r = new Remisiones();
        r.setRegistroControl(registroControl);
        r.setFolioRemision(folioRemision);
        r.setFechaRemision(fechaRemision);
        r.setIdEstatus(idEstatus);
        r.setCantidadRecibida(cantidadPiezasRecibida);
        remiList.add(r);
    }

  

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public Integer getCantidadPiezasSolicitadas() {
        return cantidadPiezasSolicitadas;
    }

    public void setCantidadPiezasSolicitadas(Integer cantidadPiezasSolicitadas) {
        this.cantidadPiezasSolicitadas = cantidadPiezasSolicitadas;
    }

    public Integer getCantidadPiezasRecibida() {
        return cantidadPiezasRecibida;
    }

    public void setCantidadPiezasRecibida(Integer cantidadPiezasRecibida) {
        this.cantidadPiezasRecibida = cantidadPiezasRecibida;
    }

    public Integer getCantidadPiezasPendientes() {
        return cantidadPiezasPendientes;
    }

    public void setCantidadPiezasPendientes(Integer cantidadPiezasPendientes) {
        this.cantidadPiezasPendientes = cantidadPiezasPendientes;
    }

    public String getRegistroControl() {
        return registroControl;
    }

    public void setRegistroControl(String registroControl) {
        this.registroControl = registroControl;
    }

    public String getFolioRemision() {
        return folioRemision;
    }

    public void setFolioRemision(String folioRemision) {
        this.folioRemision = folioRemision;
    }

    public Date getFechaRemision() {
        return fechaRemision;
    }

    public void setFechaRemision(Date fechaRemision) {
        this.fechaRemision = fechaRemision;
    }

    public Estatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Estatus idEstatus) {
        this.idEstatus = idEstatus;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public BigDecimal getImporteRecibido() {
        return importeRecibido;
    }

    public void setImporteRecibido(BigDecimal importeRecibido) {
        this.importeRecibido = importeRecibido;
    }

    public BigDecimal getImportePendiente() {
        return importePendiente;
    }

    public void setImportePendiente(BigDecimal importePendiente) {
        this.importePendiente = importePendiente;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getPorcentajeIncumplimiento() {
        return porcentajeIncumplimiento;
    }

    public void setPorcentajeIncumplimiento(double porcentajeIncumplimiento) {
        this.porcentajeIncumplimiento = porcentajeIncumplimiento;
    }

    public List<Remisiones> getRemiList() {
        return remiList;
    }

    public void setRemiList(List<Remisiones> remiList) {
        this.remiList = remiList;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isVerRemision() {
        return verRemision;
    }

    public void setVerRemision(boolean verRemision) {
        this.verRemision = verRemision;
    }

    public double getPorcentajeProveedor() {
        return porcentajeProveedor;
    }

    public void setPorcentajeProveedor(double porcentajeProveedor) {
        this.porcentajeProveedor = porcentajeProveedor;
    }

    public BigDecimal getPena() {
        return pena;
    }

    public void setPena(BigDecimal pena) {
        this.pena = pena;
    }

    public int getDiasIncumplimiento() {
        return diasIncumplimiento;
    }

    public void setDiasIncumplimiento(int diasIncumplimiento) {
        this.diasIncumplimiento = diasIncumplimiento;
    }

    public String getPenas() {
        return penas;
    }

    public void setPenas(String penas) {
        this.penas = penas;
    }

    public String getVerInicioCancelacion() {
        return verInicioCancelacion;
    }

    public void setVerInicioCancelacion(String verInicioCancelacion) {
        this.verInicioCancelacion = verInicioCancelacion;
    }

    public boolean isVerCancelado() {
        return verCancelado;
    }

    public void setVerCancelado(boolean verCancelado) {
        this.verCancelado = verCancelado;
    }

    public boolean isVerInicio() {
        return verInicio;
    }

    public void setVerInicio(boolean verInicio) {
        this.verInicio = verInicio;
    }

    public boolean isVerInicioR() {
        return verInicioR;
    }

    public void setVerInicioR(boolean verInicioR) {
        this.verInicioR = verInicioR;
    }

    public boolean isVerRescision() {
        return verRescision;
    }

    public void setVerRescision(boolean verRescision) {
        this.verRescision = verRescision;
    }

    public String getVerInicioRescision() {
        return verInicioRescision;
    }

    public void setVerInicioRescision(String verInicioRescision) {
        this.verInicioRescision = verInicioRescision;
    }

}
