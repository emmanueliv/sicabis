package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.ln.RemisionesService;
import com.issste.sicabis.ejb.modelo.Cancelaciones;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Remisiones;
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
@ManagedBean(name = "cancelacionesBean")
@ViewScoped
public class CancelacionesBean {

    @EJB
    private com.issste.sicabis.ejb.ln.CancelacionesService cancelacionesService;

    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;

    @EJB
    private RemisionesService remisionesService;

    private Cancelaciones cancelacion;
    private Mensajes mensaje = new Mensajes();
    List<Cancelaciones> cancelacionList;
    List<Remisiones> remiList;
    private String clave;
    private boolean verMensaje;
    private String nombreProveedor;
    private Date fechaInicio;
    private Date fechaFin;
    private String numeroCancelacion;
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
    private int seleccionaConsulta;
    private String criterioBusqueda;
    private List<Cancelaciones> cancelacionRescisionList;
    private BigDecimal pena;
    private String id;
    private Integer cancelacionId;
    private boolean verMensaje2;
    private String desabilitarBoton;
    private Integer idE;
    private List<Remisiones> listRemisiones;

    public CancelacionesBean() {
        id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCancelacion");
        remiList = new ArrayList();
        System.out.println("detalle" + id);
    }

    @PostConstruct
    public void init() {
        cancelaciones();
    }

    public void cancelaciones() {
        cancelacionId = Integer.parseInt(String.valueOf(id));
        cancelacionList = cancelacionesService.cancelacionById(cancelacionId);
        consultaCancelaciones();

    }

    public void consultaCancelaciones() {
        verRemisiones = false;
        if (cancelacionList != null) {
            for (Cancelaciones dos : cancelacionList) {
                idE = dos.getIdEstatus().getIdEstatus();
                estatus = dos.getIdEstatus().getNombre();
                idCancelacion = dos.getIdCancelacion();
                numeroCancelacion = dos.getNumeroCancelacion();
                idDetalle = dos.getIdDetalleOrdenSuministro().getIdDetalleOrdenSuministro();
                nombreProveedor = dos.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProveedor().getNombreProveedor();
                fechaInicio = dos.getIdDetalleOrdenSuministro().getFechaEntregaInicial();
                fechaFin = dos.getIdDetalleOrdenSuministro().getFechaEntregaFinal();
                numeroOrden = dos.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getNumeroOrden();
                contrato = dos.getIdDetalleOrdenSuministro().getIdOrdenSuministro().getIdContrato().getNumeroContrato();
                cantidadPiezasSolicitadas = dos.getIdDetalleOrdenSuministro().getCantidadSuministrar();
                fechaFin = dos.getIdDetalleOrdenSuministro().getFechaEntregaFinal();
                clave = dos.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave();
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
                        importeTotal = dos.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario().multiply(new BigDecimal(cantidadPiezasSolicitadas));
                        importeRecibido = dos.getIdDetalleOrdenSuministro().getIdFalloProcedimientoRcb().getPrecioUnitario().multiply(new BigDecimal(r.getCantidadRecibida()));
                        importePendiente = importeTotal.subtract(importeRecibido);
                        addRemision();
                    }
                }

                System.out.println("ver" + verRemisiones);
                if (verRemisiones == false) {
                    verRemisiones = false;
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
            pena = importePendiente.multiply(new BigDecimal(porcentajeIncumplimiento / 100));
        }
        if (cantidadPiezasSolicitadas.equals(cantidadPiezasRecibida)) {
            desabilitarBoton = "true";
        }
        if (idE == 172) {
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
        Cancelaciones c = new Cancelaciones();
        c.setActivo(1);
        c.setPena(pena);
        c.setImporteTotal(importePendiente);
        c.setIdCancelacion(idCancelacion);
        c.setNumeroCancelacion(numeroCancelacion);
        c.setProcentajeIncumplimiento((int) porcentajeIncumplimiento);
        c.setIdDetalleOrdenSuministro(new DetalleOrdenSuministro(idDetalle));
        c.setIdEstatus(new Estatus(172));
        boolean actualizar = cancelacionesService.actualizar(c);
        if (actualizar == true) {
            actualizarDetalle();
            verMensaje = true;
            mensaje.mensaje("La Clave se Actualizo Correctamente", "verde");
        }

        actualizarDetalle();
    }

    public void actualizarDetalle() {
        List<DetalleOrdenSuministro> buscar = detalleOrdenSuministroService.detalle(idDetalle);
        DetalleOrdenSuministro dos = new DetalleOrdenSuministro();
        for (DetalleOrdenSuministro d : buscar) {
            dos = d;
            dos.setActivo(d.getActivo());
            dos.setCancelado(0);
            dos.setCantidadSuministrada(d.getCantidadSuministrada());
            dos.setCantidadSuministrar(d.getCantidadSuministrar());
            dos.setFechaEntregaInicial(d.getFechaEntregaInicial());
            dos.setFechaEntregaFinal(d.getFechaEntregaFinal());
            dos.setIdDetalleOrdenSuministro(idDetalle);
            dos.setIdFalloProcedimientoRcb(d.getIdFalloProcedimientoRcb());
            dos.setIdOrdenSuministro(d.getIdOrdenSuministro());
            dos.setTotalCancelado(cantidadPiezasPendientes);
            boolean actualizar = detalleOrdenSuministroService.actualizar(dos);
        }
    }

    public void selecciona() {
        System.out.println("entro a orden");
        cancelacionRescisionList = cancelacionesService.cancelacionesConsulta(criterioBusqueda, seleccionaConsulta);
        if (cancelacionRescisionList == null) {
            verMensaje2 = true;
            mensaje.mensaje("La orden no existe o no se encunetra dentro del catálogo", "amarillo");
        }

    }

    public String verDettaleCncelacionRescision() throws IOException {
        return "consultaCancelaciones.xhtml?faces-redirect=true&idCancelacion=" + cancelacion.getIdCancelacion();
    }

    public List<Cancelaciones> getCancelacionList() {
        return cancelacionList;
    }

    public void setCancelacionList(List<Cancelaciones> cancelacionList) {
        this.cancelacionList = cancelacionList;
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

    public String getNumeroCancelacion() {
        return numeroCancelacion;
    }

    public void setNumeroCancelacion(String numeroCancelacion) {
        this.numeroCancelacion = numeroCancelacion;
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

    public boolean isVerRemisiones() {
        return verRemisiones;
    }

    public void setVerRemisiones(boolean verRemisiones) {
        this.verRemisiones = verRemisiones;
    }

    public List<Cancelaciones> getCancelacionRescisionList() {
        return cancelacionRescisionList;
    }

    public void setCancelacionRescisionList(List<Cancelaciones> cancelacionRescisionList) {
        this.cancelacionRescisionList = cancelacionRescisionList;
    }

    public BigDecimal getPena() {
        return pena;
    }

    public void setPena(BigDecimal pena) {
        this.pena = pena;
    }

    public Cancelaciones getCancelacion() {
        return cancelacion;
    }

    public void setCancelacion(Cancelaciones cancelacion) {
        this.cancelacion = cancelacion;
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

    public int getSeleccionaConsulta() {
        return seleccionaConsulta;
    }

    public void setSeleccionaConsulta(int seleccionaConsulta) {
        this.seleccionaConsulta = seleccionaConsulta;
    }

    public String getCriterioBusqueda() {
        return criterioBusqueda;
    }

    public void setCriterioBusqueda(String criterioBusqueda) {
        this.criterioBusqueda = criterioBusqueda;
    }

    public Integer getCancelacionId() {
        return cancelacionId;
    }

    public void setCancelacionId(Integer cancelacionId) {
        this.cancelacionId = cancelacionId;
    }

    public boolean isVerMensaje2() {
        return verMensaje2;
    }

    public void setVerMensaje2(boolean verMensaje2) {
        this.verMensaje2 = verMensaje2;
    }

    public String getDesabilitarBoton() {
        return desabilitarBoton;
    }

    public void setDesabilitarBoton(String desabilitarBoton) {
        this.desabilitarBoton = desabilitarBoton;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

}
