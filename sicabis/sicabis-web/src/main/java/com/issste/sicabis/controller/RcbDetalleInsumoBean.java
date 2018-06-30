/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.CatDetalleImService;
import com.issste.sicabis.ejb.ln.DpnInsumosService;
import com.issste.sicabis.ejb.ln.InsumosRcbSolicitudInvestigacionMercadoService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.RcbInsumosService;
import com.issste.sicabis.ejb.ln.TipoContratoService;
import com.issste.sicabis.ejb.modelo.CatDetalleIm;
import com.issste.sicabis.ejb.modelo.Fabricante;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.InsumosRcbSolicitudInvestigacionMercado;
import com.issste.sicabis.ejb.modelo.ProveedorFabricante;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import com.issste.sicabis.ejb.modelo.TipoContrato;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.service.silodisa.controller.AlertasOperativasController;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaReservaClaveCenadi;
import com.issste.sicabis.ejb.service.silodisa.service.AlertasOperativasService;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciaReservaClaveCenadiService;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciasPorClaveCenadiService;
import com.issste.sicabis.ejb.siam.ln.VwExistenciasSICABISService;
import com.issste.sicabis.ejb.utils.ExistenciaPorClaveCenadiUtil;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.PlaneacionEstatusEnum;
import com.issste.sicabis.utils.Utilidades;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.context.RequestContext;

/**
 *
 * @author erik
 */
public class RcbDetalleInsumoBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private CatDetalleImService catDetalleImService;
    @EJB
    private TipoContratoService tipoContratoService;
    @EJB
    private ExistenciaReservaClaveCenadiService existenciaReservaClaveCenadiService;
    @EJB
    private ExistenciasPorClaveCenadiService existenciasPorClaveCenadiService;
    @EJB
    private DpnInsumosService dpnInsumosService;
    @EJB
    private AlertasOperativasService alertasOperativasService;
    @EJB
    private AlertasOperativasController alertasOperativasController;
    @EJB
    private VwExistenciasSICABISService vwExistenciasSICABISService;
    @EJB
    private InsumosService insumoService;
    @EJB
    private RcbInsumosService rcbInsumosService;
    @EJB
    InsumosRcbSolicitudInvestigacionMercadoService insumosRcbSolicitudInvesService;

    private Usuarios usuarios;
    private Utilidades util = new Utilidades();
    private String idRcbInsumo;
    private RcbInsumos rcbInsumo = new RcbInsumos();
    private InsumosRcbSolicitudInvestigacionMercado insumRcbSolInveMercado = new InsumosRcbSolicitudInvestigacionMercado();
    private Boolean perfilInvestigacion;
    private Boolean perfilAdjudicacion;
    private BigDecimal porcentajeIva = new BigDecimal(0.16);
    private ExistenciaPorClaveCenadiUtil existenciaPorClaveCenadiUtil;

    private Mensajes mensaje = new Mensajes();
    private Boolean mostrarPanelInvestigacion;

    private Boolean deshabilitarInpDescripcion;
    private Boolean deshabilitarInpClave;
    private Boolean deshabilitarInpExistencias;
    private Boolean deshabilitarInpConsumoPromedio;
    private Boolean deshabilitarInpMesCobertura;
    private Boolean deshabilitarInpUnidad;
    private Boolean deshabilitarInpCantidad;
    private Boolean deshabilitarInpPrecioUnitario;
    private Boolean deshabilitarInpImporte;
    private Boolean deshabilitarInpCantidadMinima;
    private Boolean deshabilitarInpCantidadMaxima;
    private Boolean deshabilitarInpModalidaContratacion;
    private Boolean deshabilitarInpNormasCumplir;
    private Boolean deshabilitarInpCaducidadBienes;
    private Boolean deshabilitarInpPlazo;
    private Boolean deshabilitarInpCondicionesEntrega;
    private Boolean deshabilitarInpRegSanitario;
    private Boolean deshabilitarInpFormaPago;
    private Boolean deshabilitarInpGarantias;
    private Boolean deshabilitarInpTiempo;
    private Boolean deshabilitarInpCapacitacion;
    private Boolean deshabilitarInpPuestaEnMarcha;
    private Boolean deshabilitarInpPenasConv;
    private Boolean mostrarbtnGuardarInsumoRcb;
    private boolean iva;

    private boolean brequerido;
    private List<TipoContrato> listaTipoContrato;

    @PostConstruct
    public void init() {
        existenciaPorClaveCenadiUtil = new ExistenciaPorClaveCenadiUtil();
        System.out.println("idRcbInsumo entroinit:" + idRcbInsumo);
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        perfilInvestigacion = (Boolean) util.getContextAtributte("perfilInvestigacion");
        perfilAdjudicacion = (Boolean) util.getContextAtributte("perfilAdjudicacion");
        System.out.println("entro investigacion: " + perfilInvestigacion);
        insumRcbSolInveMercado.setIdInsumosRcbSolicitudInvestigacionMercado(0);
        insumRcbSolInveMercado.setActivo(1);
        insumRcbSolInveMercado.setAnticipo("0");
        insumRcbSolInveMercado.setCaducidadBienes(new Date());
        insumRcbSolInveMercado.setCantidadMaxima(0);

        insumRcbSolInveMercado.setCapacitacion("");
        insumRcbSolInveMercado.setCondicionesEntrega("");
        insumRcbSolInveMercado.setFormaPago("");
        insumRcbSolInveMercado.setGarantias("");
        insumRcbSolInveMercado.setModalidadContratacion("");
        insumRcbSolInveMercado.setNormasCumplir("");
        insumRcbSolInveMercado.setPenasAtraso("");
        insumRcbSolInveMercado.setPenasCivil(" ");
        insumRcbSolInveMercado.setPlazo("");
        insumRcbSolInveMercado.setPuestaMarcha("");
        insumRcbSolInveMercado.setRegistroSanitario("");
        insumRcbSolInveMercado.setTiempo(0);
        incializaElementosForm();
        listaTipoContrato = tipoContratoService.obtenerTiposContrato();

    }

    public void onload() {
        System.out.println("idRcbInsumo entroonload:" + idRcbInsumo);
        rcbInsumo = rcbInsumosService.getRcbInsumosById(Integer.parseInt(idRcbInsumo));
        if (rcbInsumo.getIdRcb().getIdJefatura() != null) {
            System.out.println("-->" + rcbInsumo.getIdRcb().getIdJefatura().getIdJefatura());
            CatDetalleIm cdim = catDetalleImService.obtenerByIdJefatura(rcbInsumo.getIdRcb().getIdJefatura().getIdJefatura());
            if (cdim != null) {
                insumRcbSolInveMercado.setCondicionesEntrega(cdim.getCondicionesEntrega());
                insumRcbSolInveMercado.setFormaPago(cdim.getFormaPago());
                insumRcbSolInveMercado.setGarantias(cdim.getGarantias());
                insumRcbSolInveMercado.setNormasCumplir(cdim.getNormasCumplir());
                insumRcbSolInveMercado.setPenasAtraso(cdim.getPenasAtraso());
                insumRcbSolInveMercado.setPlazo(cdim.getPlazo());
            }
        }
        List<InsumosRcbSolicitudInvestigacionMercado> listaInsumosSolicitudes = insumosRcbSolicitudInvesService.
                traerLasSolicitudesPorIdRcbInsumo(rcbInsumo.getIdRcbInsumos());
        System.out.println("listaInsumosSolicitudes:" + listaInsumosSolicitudes.size());
        iva = false;
        System.out.println("ActivoIva" + rcbInsumo.getIdInsumo().getIdTipoInsumos().getIvaActivo());
        if (rcbInsumo.getIdInsumo().getIdTipoInsumos().getIvaActivo() == 1) {
            iva = true;
        }
        if (listaInsumosSolicitudes.size() > 0) {
            insumRcbSolInveMercado = listaInsumosSolicitudes.get(0);
        } else {
            insumRcbSolInveMercado.setIdRcbInsumos(rcbInsumo);
        }
        if (rcbInsumo.getIdRcb().getIdTipoCompra().getNombre().equals("ISSSTE")) {
            mostrarPanelInvestigacion = true;
            if (rcbInsumo.getIdRcb().getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_CREADA.getValue()) {
                deshabilitarInpImporte = true;
            } else if (rcbInsumo.getIdRcb().getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_INVEST_MERCADO.getValue()) {
                deshabilitarInpImporte = true;
            } else if (rcbInsumo.getIdRcb().getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_ESPERANDO_INVEST.getValue()) {
                deshabilitarInpImporte = true;
            } else if (rcbInsumo.getIdRcb().getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_INVEST_RESUELTA.getValue()) {
                bloquearDetalleInsumo();
                deshabilitarInpCantidad = false;
                deshabilitarInpPrecioUnitario = false;
                brequerido = true;
                mostrarbtnGuardarInsumoRcb = true;
            }
        } else {
            if (rcbInsumo.getIdRcb().getIdEstatus().getIdEstatus() == PlaneacionEstatusEnum.RCB_CREADA.getValue()) {
                brequerido = true;
            }
        }
        if (perfilInvestigacion) {
            bloquearDetalleInsumo();
        }
        if (perfilAdjudicacion) {
            bloqueoPerfilAdjudicacion();
        }
//        try {
//            existenciaPorClaveCenadiUtil.validaExistenciaClaveCenadi(rcbInsumo.getClaveInsumo());
//        } catch (Exception ex) {
//            System.out.println("Error: " + ex);
//        }
        //Integer existencias = existenciasPorClaveCenadiService.existenciaSumPorClaveCenadiByClave(rcbInsumo.getClaveInsumo());
        Integer existencias = 0;
        List<ExistenciaReservaClaveCenadi> list = existenciaReservaClaveCenadiService.detalleExistenciaReservaClaveCenadi(rcbInsumo.getClaveInsumo());
        if (list != null) {
            ExistenciaReservaClaveCenadi ercc = list.get(0);
            if (ercc.getDisponibleDeReserva() != null) {
                existencias = ercc.getDisponibleDeReserva();
            }
        }
        rcbInsumo.setExistencias(existencias);
        Integer piezasDpn = obtenerDPN(rcbInsumo.getClaveInsumo());
        rcbInsumo.setConsumoPromedio(piezasDpn);
        Double mesesCobertura = 0.0;
        if (existencias == null || piezasDpn == 0) {
            existencias = 0;
        } else {
            mesesCobertura = existencias.doubleValue() / piezasDpn;
        }
        rcbInsumo.setMesesCobertura(mesesCobertura.intValue());
        System.out.println("idRcbInsumo salio onload:" + idRcbInsumo);
    }

    public Integer obtenerDPN(String rcb) {
        Integer piezasDpn = null;
//        alertasOperativasController.obtenerTodasAlertasOperativas();
        piezasDpn = dpnInsumosService.getBySumDpnByInsumo(rcb, "");
        return piezasDpn;
    }

    public void seleccionaInsumoDeDialogo(Insumos insumo) {
        System.out.println("seleccionaInsumoDeDialogo");
        RequestContext.getCurrentInstance().closeDialog(insumo);
    }

    public void calcularImporte() {
        System.out.println("entro a calcular importe");
        insumRcbSolInveMercado.setCantidadMaxima(rcbInsumo.getCantidadPiezas());
        calcularPorcentaje();
        rcbInsumo.setImporte(rcbInsumo.getPrecioUnitario().multiply(new BigDecimal(rcbInsumo.getCantidadPiezas())));
        if (rcbInsumo.getIdInsumo().getIdTipoInsumos().getIvaActivo() == 1) {
            rcbInsumo.setIva(rcbInsumo.getImporte().multiply(porcentajeIva));
            iva = true;
        }
        System.out.println("IvaRCB:" + rcbInsumo.getIva());
        System.out.println("rcbInsumo." + rcbInsumo.getImporte().toString());
    }

    public void calcularImporteModificado() {
        System.out.println("entro a calcular importe");
        rcbInsumo.setImporteModificado(rcbInsumo.getPrecioUnitarioModificado().multiply(new BigDecimal(rcbInsumo.getCantidadPiezasModificada())));
        if (rcbInsumo.getIdInsumo().getIdTipoInsumos().getIvaActivo() == 1) {
            rcbInsumo.setIva(rcbInsumo.getImporte().multiply(porcentajeIva));
        }
        System.out.println("rcbInsumo." + rcbInsumo.getImporte().toString());
        System.out.println("IvaRCB:" + rcbInsumo.getIva());
    }

    public void calcularPorcentaje() {
        System.out.println("entro a calcular Porcentaje");
        double rpta;
        rpta = insumRcbSolInveMercado.getCantidadMaxima() * 40 / 100.0;
        insumRcbSolInveMercado.setCantidadMinima((int) Math.round(rpta));
        if (insumRcbSolInveMercado.getCantidadMinima() <= 1) {
            insumRcbSolInveMercado.setCantidadMinima(1);
        }
        System.out.println("rcbInsumo." + insumRcbSolInveMercado.getCantidadMinima());
    }

    public void prueba() {
        System.out.println("entro aprueba");
        List<Proveedores> listProv = rcbInsumosService.getProveedores();
        for (Proveedores proveedor : listProv) {
            System.out.println("proveedor: " + proveedor.getNombreProveedor());
            for (ProveedorFabricante provFabric : proveedor.getProveedorFabricanteList()) {

                System.out.println("fabricante: " + provFabric.getIdFabricante().getNombre());
            }
        }

        List<Fabricante> listaFabricante = rcbInsumosService.getFabricantes();
        for (Fabricante fabricante : listaFabricante) {
            System.out.println("fabricanteÃ±ista " + fabricante.getNombre());
            for (ProveedorFabricante provFabric : fabricante.getProveedorFabricanteList()) {

                System.out.println("proveedor: " + provFabric.getIdProveedor().getNombreProveedor());
            }

        }

    }

    public void guardarInsumoRcb() {
        System.out.println("Guardo insumorcb");
        System.out.println("rcbInsumo: " + rcbInsumo.getCantidadPiezas());
        System.out.println("insumRcbSolInveMercado: " + insumRcbSolInveMercado.getCantidadMinima());
        if (PlaneacionEstatusEnum.RCB_MODIFICADA.getValue() != rcbInsumo.getIdRcb().getIdEstatus().getIdEstatus()) {
            rcbInsumo.setCantidadPiezasModificada(rcbInsumo.getCantidadPiezas());
            rcbInsumo.setPrecioUnitarioModificado(rcbInsumo.getPrecioUnitario());
            rcbInsumo.setImporteModificado(rcbInsumo.getImporte());
            if (rcbInsumo.getIdInsumo().getIdTipoInsumos().getIvaActivo() == 1) {
                rcbInsumo.setIva(rcbInsumo.getImporte().multiply(porcentajeIva));
            }
        }
        rcbInsumo = rcbInsumosService.actualizarRcbInsumo(rcbInsumo);
        if (rcbInsumo.getIdRcb().getIdTipoCompra().getNombre().equals("ISSSTE")) {
            insumRcbSolInveMercado = insumosRcbSolicitudInvesService.guardarInsumosSolicitud(insumRcbSolInveMercado);
        }
        RequestContext.getCurrentInstance().closeDialog(rcbInsumo);
        mensaje.mensaje(mensaje.datos_guardados, "verde");

    }

    public void bloquearDetalleInsumo() {
        deshabilitarInpDescripcion = true;
        deshabilitarInpClave = true;
        deshabilitarInpExistencias = true;
        deshabilitarInpConsumoPromedio = true;
        deshabilitarInpMesCobertura = true;
        deshabilitarInpUnidad = true;
        deshabilitarInpCantidad = true;
        deshabilitarInpPrecioUnitario = true;
        deshabilitarInpImporte = true;
        deshabilitarInpCantidadMinima = true;
        deshabilitarInpCantidadMaxima = true;
        deshabilitarInpModalidaContratacion = true;
        deshabilitarInpNormasCumplir = true;
        deshabilitarInpCaducidadBienes = true;
        deshabilitarInpPlazo = true;
        deshabilitarInpCondicionesEntrega = true;
        deshabilitarInpRegSanitario = true;
        deshabilitarInpFormaPago = true;
        deshabilitarInpGarantias = true;
        deshabilitarInpTiempo = true;
        deshabilitarInpCapacitacion = true;
        deshabilitarInpPuestaEnMarcha = true;
        deshabilitarInpPenasConv = true;
        mostrarbtnGuardarInsumoRcb = false;
    }

    public void bloqueoPerfilAdjudicacion() {
        deshabilitarInpDescripcion = true;
        deshabilitarInpClave = true;
        deshabilitarInpExistencias = true;
        deshabilitarInpConsumoPromedio = true;
        deshabilitarInpMesCobertura = true;
        deshabilitarInpUnidad = true;
        deshabilitarInpCantidad = false;
        deshabilitarInpPrecioUnitario = false;
        deshabilitarInpImporte = true;
        deshabilitarInpCantidadMinima = true;
        deshabilitarInpCantidadMaxima = true;
        deshabilitarInpModalidaContratacion = true;
        deshabilitarInpNormasCumplir = true;
        deshabilitarInpCaducidadBienes = true;
        deshabilitarInpPlazo = true;
        deshabilitarInpCondicionesEntrega = true;
        deshabilitarInpRegSanitario = true;
        deshabilitarInpFormaPago = true;
        deshabilitarInpGarantias = true;
        deshabilitarInpTiempo = true;
        deshabilitarInpCapacitacion = true;
        deshabilitarInpPuestaEnMarcha = true;
        deshabilitarInpPenasConv = true;
        mostrarbtnGuardarInsumoRcb = true;
    }

    public void incializaElementosForm() {
        deshabilitarInpDescripcion = true;
        deshabilitarInpClave = true;
        deshabilitarInpExistencias = true;
        deshabilitarInpConsumoPromedio = true;
        deshabilitarInpMesCobertura = true;
        deshabilitarInpUnidad = true;
        deshabilitarInpCantidad = false;
        deshabilitarInpPrecioUnitario = false;
        deshabilitarInpImporte = true;
        deshabilitarInpCantidadMinima = false;
        deshabilitarInpCantidadMaxima = false;
        deshabilitarInpModalidaContratacion = false;
        deshabilitarInpNormasCumplir = false;
        deshabilitarInpCaducidadBienes = false;
        deshabilitarInpPlazo = false;
        deshabilitarInpCondicionesEntrega = false;
        deshabilitarInpRegSanitario = false;
        deshabilitarInpFormaPago = false;
        deshabilitarInpGarantias = false;
        deshabilitarInpTiempo = false;
        deshabilitarInpCapacitacion = false;
        deshabilitarInpPuestaEnMarcha = false;
        deshabilitarInpPenasConv = false;
        mostrarbtnGuardarInsumoRcb = true;
    }

    public String getIdRcbInsumo() {
        return idRcbInsumo;
    }

    public void setIdRcbInsumo(String idRcbInsumo) {
        this.idRcbInsumo = idRcbInsumo;
    }

    public RcbInsumos getRcbInsumo() {
        return rcbInsumo;
    }

    public void setRcbInsumo(RcbInsumos rcbInsumo) {
        this.rcbInsumo = rcbInsumo;
    }

    public InsumosRcbSolicitudInvestigacionMercado getInsumRcbSolInveMercado() {
        return insumRcbSolInveMercado;
    }

    public void setInsumRcbSolInveMercado(InsumosRcbSolicitudInvestigacionMercado insumRcbSolInveMercado) {
        this.insumRcbSolInveMercado = insumRcbSolInveMercado;
    }

    public InsumosRcbSolicitudInvestigacionMercadoService getInsumosRcbSolicitudInvesService() {
        return insumosRcbSolicitudInvesService;
    }

    public void setInsumosRcbSolicitudInvesService(InsumosRcbSolicitudInvestigacionMercadoService insumosRcbSolicitudInvesService) {
        this.insumosRcbSolicitudInvesService = insumosRcbSolicitudInvesService;
    }

    public Boolean getMostrarPanelInvestigacion() {
        return mostrarPanelInvestigacion;
    }

    public void setMostrarPanelInvestigacion(Boolean mostrarPanelInvestigacion) {
        this.mostrarPanelInvestigacion = mostrarPanelInvestigacion;
    }

    public Boolean getDeshabilitarInpDescripcion() {
        return deshabilitarInpDescripcion;
    }

    public void setDeshabilitarInpDescripcion(Boolean deshabilitarInpDescripcion) {
        this.deshabilitarInpDescripcion = deshabilitarInpDescripcion;
    }

    public Boolean getDeshabilitarInpClave() {
        return deshabilitarInpClave;
    }

    public void setDeshabilitarInpClave(Boolean deshabilitarInpClave) {
        this.deshabilitarInpClave = deshabilitarInpClave;
    }

    public Boolean getDeshabilitarInpExistencias() {
        return deshabilitarInpExistencias;
    }

    public void setDeshabilitarInpExistencias(Boolean deshabilitarInpExistencias) {
        this.deshabilitarInpExistencias = deshabilitarInpExistencias;
    }

    public Boolean getDeshabilitarInpConsumoPromedio() {
        return deshabilitarInpConsumoPromedio;
    }

    public void setDeshabilitarInpConsumoPromedio(Boolean deshabilitarInpConsumoPromedio) {
        this.deshabilitarInpConsumoPromedio = deshabilitarInpConsumoPromedio;
    }

    public Boolean getDeshabilitarInpMesCobertura() {
        return deshabilitarInpMesCobertura;
    }

    public void setDeshabilitarInpMesCobertura(Boolean deshabilitarInpMesCobertura) {
        this.deshabilitarInpMesCobertura = deshabilitarInpMesCobertura;
    }

    public Boolean getDeshabilitarInpUnidad() {
        return deshabilitarInpUnidad;
    }

    public void setDeshabilitarInpUnidad(Boolean deshabilitarInpUnidad) {
        this.deshabilitarInpUnidad = deshabilitarInpUnidad;
    }

    public Boolean getDeshabilitarInpCantidad() {
        return deshabilitarInpCantidad;
    }

    public void setDeshabilitarInpCantidad(Boolean deshabilitarInpCantidad) {
        this.deshabilitarInpCantidad = deshabilitarInpCantidad;
    }

    public Boolean getDeshabilitarInpPrecioUnitario() {
        return deshabilitarInpPrecioUnitario;
    }

    public void setDeshabilitarInpPrecioUnitario(Boolean deshabilitarInpPrecioUnitario) {
        this.deshabilitarInpPrecioUnitario = deshabilitarInpPrecioUnitario;
    }

    public Boolean getDeshabilitarInpImporte() {
        return deshabilitarInpImporte;
    }

    public void setDeshabilitarInpImporte(Boolean deshabilitarInpImporte) {
        this.deshabilitarInpImporte = deshabilitarInpImporte;
    }

    public Boolean getDeshabilitarInpCantidadMinima() {
        return deshabilitarInpCantidadMinima;
    }

    public void setDeshabilitarInpCantidadMinima(Boolean deshabilitarInpCantidadMinima) {
        this.deshabilitarInpCantidadMinima = deshabilitarInpCantidadMinima;
    }

    public Boolean getDeshabilitarInpCantidadMaxima() {
        return deshabilitarInpCantidadMaxima;
    }

    public void setDeshabilitarInpCantidadMaxima(Boolean deshabilitarInpCantidadMaxima) {
        this.deshabilitarInpCantidadMaxima = deshabilitarInpCantidadMaxima;
    }

    public Boolean getDeshabilitarInpModalidaContratacion() {
        return deshabilitarInpModalidaContratacion;
    }

    public void setDeshabilitarInpModalidaContratacion(Boolean deshabilitarInpModalidaContratacion) {
        this.deshabilitarInpModalidaContratacion = deshabilitarInpModalidaContratacion;
    }

    public Boolean getDeshabilitarInpNormasCumplir() {
        return deshabilitarInpNormasCumplir;
    }

    public void setDeshabilitarInpNormasCumplir(Boolean deshabilitarInpNormasCumplir) {
        this.deshabilitarInpNormasCumplir = deshabilitarInpNormasCumplir;
    }

    public Boolean getDeshabilitarInpCaducidadBienes() {
        return deshabilitarInpCaducidadBienes;
    }

    public void setDeshabilitarInpCaducidadBienes(Boolean deshabilitarInpCaducidadBienes) {
        this.deshabilitarInpCaducidadBienes = deshabilitarInpCaducidadBienes;
    }

    public Boolean getDeshabilitarInpPlazo() {
        return deshabilitarInpPlazo;
    }

    public void setDeshabilitarInpPlazo(Boolean deshabilitarInpPlazo) {
        this.deshabilitarInpPlazo = deshabilitarInpPlazo;
    }

    public Boolean getDeshabilitarInpCondicionesEntrega() {
        return deshabilitarInpCondicionesEntrega;
    }

    public void setDeshabilitarInpCondicionesEntrega(Boolean deshabilitarInpCondicionesEntrega) {
        this.deshabilitarInpCondicionesEntrega = deshabilitarInpCondicionesEntrega;
    }

    public Boolean getDeshabilitarInpRegSanitario() {
        return deshabilitarInpRegSanitario;
    }

    public void setDeshabilitarInpRegSanitario(Boolean deshabilitarInpRegSanitario) {
        this.deshabilitarInpRegSanitario = deshabilitarInpRegSanitario;
    }

    public Boolean getDeshabilitarInpFormaPago() {
        return deshabilitarInpFormaPago;
    }

    public void setDeshabilitarInpFormaPago(Boolean deshabilitarInpFormaPago) {
        this.deshabilitarInpFormaPago = deshabilitarInpFormaPago;
    }

    public Boolean getDeshabilitarInpGarantias() {
        return deshabilitarInpGarantias;
    }

    public void setDeshabilitarInpGarantias(Boolean deshabilitarInpGarantias) {
        this.deshabilitarInpGarantias = deshabilitarInpGarantias;
    }

    public Boolean getDeshabilitarInpTiempo() {
        return deshabilitarInpTiempo;
    }

    public void setDeshabilitarInpTiempo(Boolean deshabilitarInpTiempo) {
        this.deshabilitarInpTiempo = deshabilitarInpTiempo;
    }

    public Boolean getDeshabilitarInpCapacitacion() {
        return deshabilitarInpCapacitacion;
    }

    public void setDeshabilitarInpCapacitacion(Boolean deshabilitarInpCapacitacion) {
        this.deshabilitarInpCapacitacion = deshabilitarInpCapacitacion;
    }

    public Boolean getDeshabilitarInpPuestaEnMarcha() {
        return deshabilitarInpPuestaEnMarcha;
    }

    public void setDeshabilitarInpPuestaEnMarcha(Boolean deshabilitarInpPuestaEnMarcha) {
        this.deshabilitarInpPuestaEnMarcha = deshabilitarInpPuestaEnMarcha;
    }

    public Boolean getDeshabilitarInpPenasConv() {
        return deshabilitarInpPenasConv;
    }

    public void setDeshabilitarInpPenasConv(Boolean deshabilitarInpPenasConv) {
        this.deshabilitarInpPenasConv = deshabilitarInpPenasConv;
    }

    public Boolean getMostrarbtnGuardarInsumoRcb() {
        return mostrarbtnGuardarInsumoRcb;
    }

    public void setMostrarbtnGuardarInsumoRcb(Boolean mostrarbtnGuardarInsumoRcb) {
        this.mostrarbtnGuardarInsumoRcb = mostrarbtnGuardarInsumoRcb;
    }

    public Boolean getPerfilInvestigacion() {
        return perfilInvestigacion;
    }

    public void setPerfilInvestigacion(Boolean perfilInvestigacion) {
        this.perfilInvestigacion = perfilInvestigacion;
    }

    public Boolean getPerfilAdjudicacion() {
        return perfilAdjudicacion;
    }

    public void setPerfilAdjudicacion(Boolean perfilAdjudicacion) {
        this.perfilAdjudicacion = perfilAdjudicacion;
    }

    public boolean isIva() {
        return iva;
    }

    public void setIva(boolean bIva) {
        this.iva = bIva;
    }

    public boolean isBrequerido() {
        return brequerido;
    }

    public void setBrequerido(boolean brequerido) {
        this.brequerido = brequerido;
    }

    public List<TipoContrato> getListaTipoContrato() {
        return listaTipoContrato;
    }

    public void setListaTipoContrato(List<TipoContrato> listaTipoContrato) {
        this.listaTipoContrato = listaTipoContrato;
    }

}
