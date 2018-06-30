/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.CaracterProcedimientoService;
import com.issste.sicabis.ejb.ln.ClasificacionProcedimientoService;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.ProcedimientoService;
import com.issste.sicabis.ejb.ln.ProcedimientosRcbService;
import com.issste.sicabis.ejb.ln.RcbInsumosService;
import com.issste.sicabis.ejb.ln.TipoCompraService;
import com.issste.sicabis.ejb.ln.TipoProcedimientoService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.CaracterProcedimiento;
import com.issste.sicabis.ejb.modelo.ClasificacionProcedimiento;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Perfiles;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import com.issste.sicabis.ejb.modelo.TipoCompra;
import com.issste.sicabis.ejb.modelo.TipoProcedimiento;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.BitacoraUtil;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.jfree.chart.block.Arrangement;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

public class ProcedimientosBean {

    @EJB
    private ProcedimientosRcbService procedimientosRcbService;

    @EJB
    private ProcedimientoService procedimientoService;

    @EJB
    private EstatusService estatusService;

    @EJB
    private TipoProcedimientoService tipoProcedimientoService;

    @EJB
    private ClasificacionProcedimientoService clasificacionProcedimientoService;

    @EJB
    private CaracterProcedimientoService caracterProcedimientoService;

    @EJB
    private TipoCompraService tipoCompraService;

    @EJB
    private RcbInsumosService rcbInsumosService;

    /*
     Implementacion de bitacora
     */
    @EJB
    BitacoraTareaSerice bitacoraService;
    BitacoraTareaEstatus bitacora;
    BitacoraUtil bitacoraUtil = new BitacoraUtil();

    private RcbInsumos rcbInsumos;
    private Procedimientos procedimientos;
    private Procedimientos procedimientosAux;
    private Usuarios usuarios;

    private Integer tabActivo;
    private boolean botonActualizar;
    private boolean botonGuardar;
    private boolean mensajeBorrar;
    private boolean messageGuardar = true;
    private List<RcbInsumos> listaRcbInsumos;
    private int tipoCompra;
    private List<TipoCompra> listaTipoCompra;
    private List<ClasificacionProcedimiento> listaClasificacionProc;
    private List<CaracterProcedimiento> listaCaracterProc;
    private List<TipoProcedimiento> listaTipoProc;
    private List<Estatus> listaEstatusProc;
    private boolean bTipoCompra;
    private List<ProcedimientoRcb> listaProcRcb;
    private BigDecimal importeTotal;
    private List<Procedimientos> listaProcedimientos;
    private int tipoProc;
    private int caracterProc;
    private List<ProcedimientoRcb> listaProcRcbFilter;
    private boolean esConsulta;

    private final Integer idTarea = 3;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();

    public ProcedimientosBean() {

    }

    @PostConstruct
    public void init() {
        botonGuardar = true;
        listaRcbInsumos = new ArrayList();
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        bitacora = new BitacoraTareaEstatus();
        procedimientos = new Procedimientos();

        procedimientos.setIdEstatus(new Estatus(0));
        procedimientos.setIdCaracterProcedimiento(new CaracterProcedimiento(0));
        procedimientos.setIdClasificacionProcedimiento(new ClasificacionProcedimiento(0));
        procedimientos.setIdTipoProcedimiento(new TipoProcedimiento(0));
        procedimientos.setImporteTotal(BigDecimal.ZERO);
        procedimientos.setNumeroProcedimiento("");
        procedimientos.setFechaProcedimiento(new Date());
        procedimientos.setIdTipoCompra(new TipoCompra(tipoCompra));

        procedimientosAux = new Procedimientos();
        procedimientosAux.setIdProcedimiento(0);
        procedimientosAux.setIdCaracterProcedimiento(new CaracterProcedimiento(0));
        procedimientosAux.setIdClasificacionProcedimiento(new ClasificacionProcedimiento(0));
        procedimientosAux.setIdTipoProcedimiento(new TipoProcedimiento(0));
        procedimientosAux.setImporteTotal(BigDecimal.ZERO);
        procedimientosAux.setNumeroProcedimiento("");
        procedimientosAux.setFechaProcedimiento(new Date());

        listaTipoCompra = tipoCompraService.traeListaTipoCompra();
        listaClasificacionProc = clasificacionProcedimientoService.obtenerClasificacionProcedimiento();
        listaCaracterProc = caracterProcedimientoService.obtenerCaracterProcedimiento();
        listaTipoProc = tipoProcedimientoService.obtenerTiposProcedimientos();
        listaEstatusProc = estatusService.getEstatusByTarea(3);
        listaProcRcb = new ArrayList();
        //listaProcRcb = null;
        importeTotal = BigDecimal.ZERO;
        bTipoCompra = false;
    }

    public void cancel() {
        RequestContext.getCurrentInstance().reset("formProc");
        init();
    }

    public void existeNumeroProcedimiento() {
        boolean bandera = this.validaNumeroProcedimiento();
    }

    public boolean validaNumeroProcedimiento() {
        boolean bandera = true;
        Procedimientos pAux = procedimientoService.obtenerByNumeroProcedimiento(procedimientos.getNumeroProcedimiento());
        if (pAux != null) {
            mensaje.mensaje("El número de procedimiento ya esta registrado", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean valida() {
        boolean bandera = true;
        if (procedimientos.getNumeroProcedimiento().equals("")) {
            mensaje.mensaje("Debes capturar el número del procedimiento", "amarillo");
            bandera = false;
        } else {
            bandera = this.validaNumeroProcedimiento();
        }
        if (procedimientos.getIdEstatus().getIdEstatus() == -1) {
            mensaje.mensaje("Debes seleccionar un estatus del procedimiento", "amarillo");
            bandera = false;

        } else if (procedimientos.getIdEstatus().getIdEstatus() == 32) {
            if (listaProcRcb == null) {
                mensaje.mensaje("Debes agregar al menos una clave al procedimiento", "amarillo");
                bandera = false;
            }
        }
        if (procedimientos.getIdClasificacionProcedimiento().getIdClasificacionProcedimiento() == -1) {
            mensaje.mensaje("Debes seleccionar una clasificación del procedimiento", "amarillo");
            bandera = false;
        }
        if (procedimientos.getIdCaracterProcedimiento().getIdCaracterProcedimiento() == -1) {
            mensaje.mensaje("Debes seleccionar una clasificación del procedimiento", "amarillo");
            bandera = false;
        }
        if (procedimientos.getIdTipoProcedimiento().getIdTipoProcedimiento() == -1) {
            mensaje.mensaje("Debes seleccionar una clasificación del procedimiento", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public void validaGuardado() {
        if (valida()) {
            if (procedimientos.getIdEstatus().getIdEstatus() == 32) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlg1').show();");
            } else {
                this.guardarActualizarProcedimiento();
            }
        }
    }

    public void guardarActualizarProcedimiento() {
        procedimientos.setImporteTotal(importeTotal);
        procedimientos.setUsuarioAlta(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
        procedimientos.setFechaAlta(new Date());
        procedimientos.setActivo(1);
        procedimientos.setVerificaSansiones(0);
        procedimientos.setIdTipoCompra(new TipoCompra(tipoCompra));
        List<ProcedimientoRcb> listaProcRcbAux = new ArrayList();
        List<ProcedimientoRcb> listaProcRcbActualizar = new ArrayList();
        for (ProcedimientoRcb pr : listaProcRcb) {
            if (pr.getIdProcedimientoRcb() != null) {
                pr.setFechaBaja(new Date());
                pr.setUsuarioBaja(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
                pr.setActivo(0);
                ProcedimientoRcb prAux = this.nuevoProcRcb(pr);
                listaProcRcbActualizar.add(pr);
                listaProcRcbAux.add(prAux);
            } else {
                pr.setUsuarioAlta(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
                pr.setIdProcedimiento(procedimientos);
                listaProcRcbAux.add(pr);
            }
        }
        listaProcRcb.clear();
        listaProcRcb = listaProcRcbAux;
        procedimientos.setProcedimientoRcbList(listaProcRcb);
        boolean bandera = procedimientoService.guardaProcedimiento(procedimientos);
        if (bandera) {
            for (ProcedimientoRcb prAct : listaProcRcbActualizar) {
                procedimientosRcbService.actualizaProcedimientoRcb(prAct);
            }
            bitacora.setFecha(new Date());
            bitacora.setIdEstatus(procedimientos.getIdEstatus().getIdEstatus());
            bitacora.setIdModulos(procedimientos.getIdProcedimiento());
            bitacora.setDescripcion(bitacoraUtil.detalleActualizarBitacora("Guarda procedimiento"));
            bitacora.setIdUsuarios(usuarios.getIdUsuario());
            bitacora.setIdTareaId(idTarea);
            bitacoraService.guardarEnBitacora(bitacora);
            if (procedimientos.getIdEstatus().getIdEstatus() == 31) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlg3').show();");
            } else {
                cancel();
            }
            mensaje.mensaje(mensaje.datos_guardados, "verde");
        } else {
            mensaje.mensaje(mensaje.error_guardar, "rojo");
        }
        tabActivo = 0;
        messageGuardar = true;
    }

    public ProcedimientoRcb nuevoProcRcb(ProcedimientoRcb pr) {
        ProcedimientoRcb prAux = new ProcedimientoRcb();
        prAux.setActivo(1);
        prAux.setCantidadPiezas(pr.getCantidadPiezas());
        prAux.setCantidadPiezasOriginal(pr.getCantidadPiezasOriginal());
        prAux.setConsumoPromedio(pr.getConsumoPromedio());
        prAux.setDesierta(0);
        prAux.setDesiertaParcial(0);
        prAux.setExistencias(pr.getExistencias());
        prAux.setFechaAlta(new Date());
        prAux.setIdProcedimiento(procedimientos);
        prAux.setIdRcbInsumos(pr.getIdRcbInsumos());
        prAux.setImporte(pr.getImporte());
        prAux.setMesesCobertura(pr.getMesesCobertura());
        prAux.setPrecioUnitario(pr.getPrecioUnitario());
        prAux.setUsuarioAlta(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
        return prAux;
    }

    public void obtenerProcedimientos() {
        procedimientosAux.setIdTipoProcedimiento(new TipoProcedimiento(tipoProc));
        procedimientosAux.setIdCaracterProcedimiento(new CaracterProcedimiento(caracterProc));
        listaProcedimientos = procedimientoService.obtenerByProcedimientos(procedimientosAux);
        tabActivo = 1;
    }

    public String verDetalle() {
        util.setContextAtributte("procedimientos", this.procedimientos);
        return "detalleProcedimiento.xhtml?faces-redirect=true";
    }

    public void agregaClaves() {
        if (tipoCompra != -1) {
            Map<String, Object> options = new HashMap<String, Object>();
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            List<String> values = new ArrayList<String>();
            values.add(String.valueOf(tipoCompra));
            params.put("tipoCompra", values);
            options.put("resizable", false);
            options.put("draggable", false);
            options.put("includeViewParams", true);
            options.put("width", "70%");
            options.put("height", "500");
            options.put("contentWidth", "95%");
            options.put("contentHeight", "500");
            options.put("modal", true);
            RequestContext.getCurrentInstance().openDialog("/vistas/adquisicion/agregaClaves.xhtml", options, params);
        } else {
            messageGuardar = true;
            mensaje.mensaje("Debe seleccionar un tipo de compra primero", "amarillo");
        }
    }

    public void clavesAgregadas(SelectEvent event) {
        List<ProcedimientoRcb> listaClavesSelect = (List<ProcedimientoRcb>) event.getObject();
        List<ProcedimientoRcb> listaProcRcbAux = listaProcRcb;
        if (listaClavesSelect != null) {
            boolean bandera = false;
            ProcedimientoRcb procRCB = null;
            for (ProcedimientoRcb insumosNuevos : listaClavesSelect) {
                System.out.println("" + insumosNuevos.getIdRcbInsumos().getIdRcb().getIdRcb());
                for (ProcedimientoRcb pr : listaProcRcbAux) {
                    if (pr.getIdRcbInsumos().getIdRcbInsumos().intValue() == insumosNuevos.getIdRcbInsumos().getIdRcbInsumos().intValue()) {
                        bandera = true;
                        mensaje.mensaje("La clave " + pr.getIdRcbInsumos().getIdRcb().getIdRcb().intValue() + " ya fue agregada a la lista", "amarillo");
                        break;
                    }
                }
                if (!bandera) {
                    procRCB = new ProcedimientoRcb();
                    procRCB = insumosNuevos;
                    importeTotal = insumosNuevos.getImporte().add(importeTotal);
                    listaProcRcb.add(procRCB);
                }
                bandera = false;
            }
            bTipoCompra = true;
        }
    }

    public void quitarClaves(ProcedimientoRcb procedimientoRcb) {
        List<ProcedimientoRcb> listaProcRcbAux = new ArrayList();
        importeTotal = BigDecimal.ZERO;
        for (ProcedimientoRcb pr : listaProcRcb) {
            if (pr != procedimientoRcb) {
                listaProcRcbAux.add(pr);
                importeTotal = pr.getImporte().add(importeTotal);
            }
        }
        listaProcRcb.clear();
        listaProcRcb = listaProcRcbAux;
        if (listaProcRcb.size() == 0) {
            bTipoCompra = false;
        }
    }

    public Integer getTabActivo() {
        return tabActivo;
    }

    public void setTabActivo(Integer tabActivo) {
        this.tabActivo = tabActivo;
    }

    public boolean isBotonActualizar() {
        return botonActualizar;
    }

    public void setBotonActualizar(boolean botonActualizar) {
        this.botonActualizar = botonActualizar;
    }

    public boolean isBotonGuardar() {
        return botonGuardar;
    }

    public void setBotonGuardar(boolean botonGuardar) {
        this.botonGuardar = botonGuardar;
    }

    public boolean isMensajeBorrar() {
        return mensajeBorrar;
    }

    public void setMensajeBorrar(boolean mensajeBorrar) {
        this.mensajeBorrar = mensajeBorrar;
    }

    public boolean isMessageGuardar() {
        return messageGuardar;
    }

    public void setMessageGuardar(boolean messageGuardar) {
        this.messageGuardar = messageGuardar;
    }

    public List<RcbInsumos> getListaRcbInsumos() {
        return listaRcbInsumos;
    }

    public void setListaRcbInsumos(List<RcbInsumos> listaRcbInsumos) {
        this.listaRcbInsumos = listaRcbInsumos;
    }

    public int getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(int tipoCompra) {
        this.tipoCompra = tipoCompra;
    }

    public List<TipoCompra> getListaTipoCompra() {
        return listaTipoCompra;
    }

    public void setListaTipoCompra(List<TipoCompra> listaTipoCompra) {
        this.listaTipoCompra = listaTipoCompra;
    }

    public List<ClasificacionProcedimiento> getListaClasificacionProc() {
        return listaClasificacionProc;
    }

    public void setListaClasificacionProc(List<ClasificacionProcedimiento> listaClasificacionProc) {
        this.listaClasificacionProc = listaClasificacionProc;
    }

    public List<CaracterProcedimiento> getListaCaracterProc() {
        return listaCaracterProc;
    }

    public void setListaCaracterProc(List<CaracterProcedimiento> listaCaracterProc) {
        this.listaCaracterProc = listaCaracterProc;
    }

    public List<TipoProcedimiento> getListaTipoProc() {
        return listaTipoProc;
    }

    public void setListaTipoProc(List<TipoProcedimiento> listaTipoProc) {
        this.listaTipoProc = listaTipoProc;
    }

    public Procedimientos getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(Procedimientos procedimientos) {
        this.procedimientos = procedimientos;
    }

    public List<Estatus> getListaEstatusProc() {
        return listaEstatusProc;
    }

    public void setListaEstatusProc(List<Estatus> listaEstatusProc) {
        this.listaEstatusProc = listaEstatusProc;
    }

    public boolean isbTipoCompra() {
        return bTipoCompra;
    }

    public void setbTipoCompra(boolean bTipoCompra) {
        this.bTipoCompra = bTipoCompra;
    }

    public List<ProcedimientoRcb> getListaProcRcb() {
        return listaProcRcb;
    }

    public void setListaProcRcb(List<ProcedimientoRcb> listaProcRcb) {
        this.listaProcRcb = listaProcRcb;
    }

    public List<Procedimientos> getListaProcedimientos() {
        return listaProcedimientos;
    }

    public void setListaProcedimientos(List<Procedimientos> listaProcedimientos) {
        this.listaProcedimientos = listaProcedimientos;
    }

    public Procedimientos getProcedimientosAux() {
        return procedimientosAux;
    }

    public void setProcedimientosAux(Procedimientos procedimientosAux) {
        this.procedimientosAux = procedimientosAux;
    }

    public int getTipoProc() {
        return tipoProc;
    }

    public void setTipoProc(int tipoProc) {
        this.tipoProc = tipoProc;
    }

    public int getCaracterProc() {
        return caracterProc;
    }

    public void setCaracterProc(int caracterProc) {
        this.caracterProc = caracterProc;
    }

    public List<ProcedimientoRcb> getListaProcRcbFilter() {
        return listaProcRcbFilter;
    }

    public void setListaProcRcbFilter(List<ProcedimientoRcb> listaProcRcbFilter) {
        this.listaProcRcbFilter = listaProcRcbFilter;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public boolean isEsConsulta() {
        return esConsulta;
    }

    public void setEsConsulta(boolean esConsulta) {
        this.esConsulta = esConsulta;
    }

}
