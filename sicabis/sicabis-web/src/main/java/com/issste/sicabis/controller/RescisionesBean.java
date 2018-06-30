/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.CancelacionesService;
import com.issste.sicabis.ejb.ln.ContratoService;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.ln.RescisionesService;
import com.issste.sicabis.ejb.modelo.Cancelaciones;
import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Remisiones;
import com.issste.sicabis.ejb.modelo.Rescisiones;
import com.issste.sicabis.utils.Mensajes;
import java.io.IOException;
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
@ManagedBean(name = "rescisionesBean")
@ViewScoped
public class RescisionesBean {

    @EJB
    private ContratoService contratoService;
    @EJB
    private RescisionesService rescisionesService;

    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;
    private Rescisiones ress;
    private Mensajes mensaje = new Mensajes();
    List<Rescisiones> recisionList;
    List<Remisiones> remiList;
    private String clave;
    private boolean verMensaje;
    private String nombreProveedor;
    private Date fechaInicio;
    private Date fechaFin;
    private String numeroRescision;
    private String numeroOrden;
    private String contrato;
    private Integer cantidadPiezasSolicitadas;
    private Integer cantidadPiezasRecibida;
    private Integer cantidadPiezasPendientes;
    private BigDecimal importeTotal;
    private BigDecimal importeRecibido;
    private BigDecimal importePendiente;
    private double porcentajeIncumplimiento;
    private BigDecimal precioUnitario;
    private String registroControl;
    private String folioRemision;
    private Date fechaRemision;
    private String estatus;
    private Estatus idEstatus;
    private boolean verRemisiones;
    private Integer idCancelacion;
    private Integer idDetalle;
    private int diasIncumplimiento;
    private Integer idProveedor;
    private double porcentajeProveedor;
    private BigDecimal pena;
    private Integer idRescision;
    private Integer idContrato;
    private Integer seleccionaConsulta;
    private String criterioBusqueda;
    private List<Rescisiones> cancelacionRescisionList;
    private BigDecimal importeContrato;
    private final String id;
    private int rId;
    private String desabilitarBoton;
    private Integer idE;
    private boolean verMensaje2;

    public RescisionesBean() {
        id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idRescision");
        remiList = new ArrayList();
    }

    @PostConstruct
    public void init() {
        rescisiones();
    }

    public void rescisiones() {
        rId = Integer.parseInt(String.valueOf(id));
        recisionList = rescisionesService.rescisionesesById(rId);
        consultaRescisiones();

    }

    public void consultaRescisiones() {
        verRemisiones = false;
        for (Rescisiones dos : recisionList) {
            idProveedor = dos.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getIdProveedor();
            estatus = dos.getIdEstatus().getNombre();
            clave = dos.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave();
            idE = dos.getIdEstatus().getIdEstatus();
            idRescision = dos.getIdRescision();
            numeroRescision = dos.getNumeroRecision();
            idDetalle = dos.getIdDetalleOrdenSuministro().getIdDetalleOrdenSuministro();
            nombreProveedor = dos.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getNombreProveedor();
            fechaInicio = dos.getIdDetalleOrdenSuministro().getFechaEntregaInicial();
            fechaFin = dos.getIdDetalleOrdenSuministro().getFechaEntregaFinal();
            numeroOrden = dos.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getNumeroOrden();
            contrato = dos.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getNumeroContrato();
            idContrato = dos.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getIdContrato();
            cantidadPiezasSolicitadas = dos.getIdDetalleOrdenSuministro().getCantidadSuministrar();
            fechaFin = dos.getIdDetalleOrdenSuministro().getFechaEntregaFinal();
            importeTotal = dos.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario().multiply(new BigDecimal(cantidadPiezasSolicitadas));
            if (dos.getIdDetalleOrdenSuministro().getRemisionesList() != null) {

                for (Remisiones r : dos.getIdDetalleOrdenSuministro().getRemisionesList()) {
                    verRemisiones = true;
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
            if (verRemisiones != true) {
                verRemisiones = false;
                System.out.println("no hay Remision");
                cantidadPiezasRecibida = 0;
                cantidadPiezasPendientes = cantidadPiezasSolicitadas;
                importeRecibido = new BigDecimal(0);
                importePendiente = new BigDecimal(cantidadPiezasSolicitadas);
            }

            importeContrato = dos.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getImporte();
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
            diasIncumplimiento = diffDays;
            System.out.println("d----->" + diffDays);
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
        if (cantidadPiezasSolicitadas.equals(cantidadPiezasRecibida)) {
            desabilitarBoton = "true";
        }
        if (idE == 182) {
            desabilitarBoton = "true";
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

    public void actualizar() {
        Rescisiones r = new Rescisiones();
        r.setActivo(1);
        r.setIdRescision(idRescision);
        r.setNumeroRecision(numeroRescision);
        r.setIdDetalleOrdenSuministro(new DetalleOrdenSuministro(idDetalle));
        r.setIdEstatus(new Estatus(182));
        boolean actualizar = rescisionesService.actualizar(r);
        if (actualizar == true) {
            actualizarContrato();
            verMensaje = true;
            mensaje.mensaje("El Contrato de Resindio Correctamente", "verde");
        }

        actualizarContrato();

    }

    public void actualizarContrato() {
        List<Contratos> buscar = contratoService.contratoById(idContrato);
        Contratos con = new Contratos();
        for (Contratos c : buscar) {
            con.setActivo(c.getActivo());
            con.setAcuerdo(c.getAcuerdo());
            con.setAnioAfectacion(c.getAnioAfectacion());
            con.setCondicionPago(c.getCondicionPago());
            //con.setDescuento(c.getDescuento());
            con.setFechaAlta(c.getFechaAlta());
            con.setFechaBaja(c.getFechaBaja());
            con.setFechaFormalizacion(c.getFechaFormalizacion());
            con.setFechaContrato(c.getFechaContrato());
            con.setFechaModificacion(c.getFechaModificacion());
            con.setFechaOficioSuficiencia(c.getFechaOficioSuficiencia());
            con.setIdAlmacen(c.getIdAlmacen());
            con.setIdContrato(idContrato);
            con.setIdDestino(c.getIdDestino());
            con.setIdEstatus(new Estatus(58));
            con.setIdFundamentoLegal(c.getIdFundamentoLegal());
            con.setIdPadre(c.getIdPadre());
            con.setIdPartidaPresupuestal(c.getIdPartidaPresupuestal());
            con.setIdTipoContrato(c.getIdTipoContrato());
            con.setIdTipoConvenio(c.getIdTipoConvenio());
            con.setIdUnidadesMedicas(c.getIdUnidadesMedicas());
            con.setImporte(c.getImporte());
            con.setNep(c.getNep());
            //con.setNoRupa(c.getNoRupa());
            con.setNumeroContrato(c.getNumeroContrato());
            con.setNumeroConvenio(c.getNumeroConvenio());
            con.setOficioSuficiencia(c.getOficioSuficiencia());
            con.setUsuarioAlta(c.getUsuarioAlta());
            con.setUsuarioModificacion(c.getUsuarioModificacion());
            con.setUsuarioBaja(c.getUsuarioBaja());
            con.setVigenciaFinal(c.getVigenciaFinal());
            con.setVigenciaInicial(c.getVigenciaInicial());
            boolean actauliazar = contratoService.actualizaContrato(con);
        }
    }

    public void selecciona() {
        cancelacionRescisionList = rescisionesService.cancelacionesRescisiones(criterioBusqueda, seleccionaConsulta);
        if (cancelacionRescisionList == null) {
            verMensaje2 = true;
            mensaje.mensaje("La Rescision no Existe o no se Encunetra Dentro del Catálogo", "amarillo");
        }

    }

    public String verDettaleCncelacionRescision() throws IOException {
        return "consultaRescisiones.xhtml?faces-redirect=true&idRescision=" + ress.getIdRescision();
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje(boolean verMensaje) {
        this.verMensaje = verMensaje;
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

    public double getPorcentajeIncumplimiento() {
        return porcentajeIncumplimiento;
    }

    public void setPorcentajeIncumplimiento(double porcentajeIncumplimiento) {
        this.porcentajeIncumplimiento = porcentajeIncumplimiento;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public List<Remisiones> getRemiList() {
        return remiList;
    }

    public void setRemiList(List<Remisiones> remiList) {
        this.remiList = remiList;
    }

    public String getNumeroRescision() {
        return numeroRescision;
    }

    public void setNumeroRescision(String numeroRescision) {
        this.numeroRescision = numeroRescision;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Estatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Estatus idEstatus) {
        this.idEstatus = idEstatus;
    }

    public boolean isVerRemisiones() {
        return verRemisiones;
    }

    public void setVerRemisiones(boolean verRemisiones) {
        this.verRemisiones = verRemisiones;
    }

    public Integer getIdCancelacion() {
        return idCancelacion;
    }

    public void setIdCancelacion(Integer idCancelacion) {
        this.idCancelacion = idCancelacion;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getDiasIncumplimiento() {
        return diasIncumplimiento;
    }

    public void setDiasIncumplimiento(int diasIncumplimiento) {
        this.diasIncumplimiento = diasIncumplimiento;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
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

    public Integer getIdRescision() {
        return idRescision;
    }

    public void setIdRescision(Integer idRescision) {
        this.idRescision = idRescision;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getSeleccionaConsulta() {
        return seleccionaConsulta;
    }

    public void setSeleccionaConsulta(Integer seleccionaConsulta) {
        this.seleccionaConsulta = seleccionaConsulta;
    }

    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    public List<Rescisiones> getCancelacionRescisionList() {
        return cancelacionRescisionList;
    }

    public void setCancelacionRescisionList(List<Rescisiones> cancelacionRescisionList) {
        this.cancelacionRescisionList = cancelacionRescisionList;
    }

    public Rescisiones getRess() {
        return ress;
    }

    public void setRess(Rescisiones ress) {
        this.ress = ress;
    }

    public boolean isVerMensaje2() {
        return verMensaje2;
    }

    public void setVerMensaje2(boolean verMensaje2) {
        this.verMensaje2 = verMensaje2;
    }

    public BigDecimal getImporteContrato() {
        return importeContrato;
    }

    public void setImporteContrato(BigDecimal importeContrato) {
        this.importeContrato = importeContrato;
    }

    public String getDesabilitarBoton() {
        return desabilitarBoton;
    }

    public void setDesabilitarBoton(String desabilitarBoton) {
        this.desabilitarBoton = desabilitarBoton;
    }

    
}
