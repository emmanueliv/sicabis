package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.ContratoOrdenDTO;
import com.issste.sicabis.ejb.ln.ContratoService;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.OrdenSuministroService;
import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

public class OrdenSuministroBean2 {

    @EJB
    private OrdenSuministroService ordenSuministroService;

    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;

    @EJB
    private EstatusService estatusService;

    @EJB
    private ContratoService contratoService;

    private Usuarios usuarios;
    private OrdenSuministro ordenSuministro;
    private OrdenSuministro ordenSuministroBusqueda;
    private Proveedores proveedores;

    private Integer tabActivo;
    private boolean botonGuardar = true;
    private boolean mensajeBorrar;
    private boolean messageGuardar = true;
    private String numeroContrato;
    private boolean bpreorden;
    private String lugarEntrega;
    private Integer idEstatus;
    private Date fechaInicialContrato;
    private Date fechaFinalContrato;
    private String numeroOrden;

    private List<ContratoFalloProcedimientoRcb> listaConFalloProcRcb;
    private List<DetalleOrdenSuministro> listaDetalleOrden;
    private List<DetalleOrdenSuministro> listaDetalleOrdenSelect;
    private List<DetalleOrdenSuministro> listaDetalleOrdenFilter;
    private List<Contratos> listaContratos;
    private List<Estatus> listaEstatus;
    private List<FalloProcedimientoRcb> listaFalloProcRcb;
    private List<OrdenSuministro> listaOrdenSuministro;
    private List<ContratoOrdenDTO> listaContratoOrdenDTO;
    private List<ContratoOrdenDTO> listaContratoOrdenDTOFilter;

    private final Integer idTareaProc = 7;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();

    public OrdenSuministroBean2() {
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        bpreorden = Integer.parseInt(util.getContextAtributte("opcionOrden").toString()) == 1 ? true : false;
        listaFalloProcRcb = new ArrayList();
        listaDetalleOrden = new ArrayList();
        listaContratoOrdenDTO = new ArrayList();
        ordenSuministro = this.inicializaOrden(ordenSuministro);
        listaEstatus = estatusService.getEstatusByTarea(idTareaProc);
        if (bpreorden) {
            idEstatus = 71;
        }
    }

    public OrdenSuministro inicializaOrden(OrdenSuministro ordenSuministro) {
        ordenSuministro = new OrdenSuministro();
        ordenSuministro.setActivo(1);
        ordenSuministro.setNumeroOrden("");
        ordenSuministro.setFechaOrden(new Date());
        ordenSuministro.setFechaAlta(new Date());
        ordenSuministro.setUsuarioAlta(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
        return ordenSuministro;
    }

    public void existeNumeroOrden() {
        boolean bandera = this.validaNumeroOrden();
    }

    public boolean validaNumeroOrden() {
        boolean bandera = true;
        List<OrdenSuministro> cAux = ordenSuministroService.obtenerByNumeroOrden(ordenSuministro.getNumeroOrden());
        if (cAux.size() != 0) {
            mensaje.mensaje("El número de orden ya esta registrada", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public boolean valida() {
        boolean bandera = true;
        if (ordenSuministro.getNumeroOrden().equals("")) {
            mensaje.mensaje("Debes ingresar el número de la orden", "amarillo");
            bandera = false;
        } else {
            bandera = this.validaNumeroOrden();
        }
        if (!bpreorden) {
            if (idEstatus.intValue() == -1) {
                mensaje.mensaje("Debes seleccionar un estatus", "amarillo");
                bandera = false;
            }
        }
        if (listaDetalleOrdenSelect.size() == 0 || listaDetalleOrdenSelect == null) {
            mensaje.mensaje("Debes seleccionar al menos una clave del listado", "amarillo");
            bandera = false;
        }
        return bandera;
    }

    public void validaGuardado() {
        if (valida()) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlg1').show();");
        }
    }

    public void guardaOrden() {
        ordenSuministro.setIdEstatus(new Estatus(idEstatus));
        List<DetalleOrdenSuministro> listaDetalleOrdenAux = new ArrayList();
        for (DetalleOrdenSuministro dos : listaDetalleOrdenSelect) {
            dos.setTotalCancelado(0);
            dos.setIdOrdenSuministro(ordenSuministro);
            listaDetalleOrdenAux.add(dos);
        }
        ordenSuministro.setDetalleOrdenSuministroList(listaDetalleOrdenAux);
        if (ordenSuministroService.guardaOrdenSuministro(ordenSuministro)) {
            mensaje.mensaje(mensaje.datos_guardados, "verde");
            this.limpiar();
        } else {
            mensaje.mensaje(mensaje.error_guardar, "rojo");
        }
    }

    public void agregaClaves() {
        Map<String, Object> options = new HashMap<String, Object>();
//        Map<String, List<String>> params = new HashMap<String, List<String>>();
//        List<String> values = new ArrayList<String>();
//        values.add(String.valueOf("1"));
//        params.put("clave", values);
        options.put("resizable", false);
        options.put("draggable", false);
        //options.put("includeViewParams", true);
        options.put("width", "70%");
        options.put("height", "500");
        options.put("contentWidth", "95%");
        options.put("contentHeight", "500");
        options.put("modal", true);
        RequestContext.getCurrentInstance().openDialog("/vistas/adquisicion/agregarClavesOrdenes.xhtml", options, null);
    }

    public void clavesAgregadas(SelectEvent event) {
        List<ContratoOrdenDTO> listaContratoOrdenDTOSelect = (List<ContratoOrdenDTO>) event.getObject();
        if (listaContratoOrdenDTOSelect != null) {
            boolean bandera = false;
            ContratoOrdenDTO contratoOrdenDTO = null;
            for (ContratoOrdenDTO insumosNuevos : listaContratoOrdenDTOSelect) {
                for (ContratoOrdenDTO codto : listaContratoOrdenDTO) {
                    if(insumosNuevos.getContratoFalloProcedimientoRcb().getIdContratoFalloProcedimientoRcb().intValue() == codto.getContratoFalloProcedimientoRcb().getIdContratoFalloProcedimientoRcb().intValue()){
                        bandera = true;
                        mensaje.mensaje("La clave " + insumosNuevos.getContratoFalloProcedimientoRcb().getIdFalloProcedimientoRcb().getIdProcedimientoRcb().getIdRcbInsumos().getIdRcb().getIdRcb().intValue() + " ya fue agregada a la lista", "amarillo");
                        break;
                    }
                }
                if (!bandera) {
                    contratoOrdenDTO = insumosNuevos;
                    listaContratoOrdenDTO.add(contratoOrdenDTO);
                }
            }
        }
    }
    
    public void quitarClaves(ContratoOrdenDTO contratoOrdenDTO){
        List<ContratoOrdenDTO> listaContratoOrdenDTOAux = new ArrayList();
        //importeTotal = BigDecimal.ZERO;
        for (ContratoOrdenDTO codto : listaContratoOrdenDTO) {
            if (codto != contratoOrdenDTO) {
                listaContratoOrdenDTOAux.add(codto);
                //importeTotal = pr.getImporte().add(importeTotal);
            }
        }
        listaContratoOrdenDTO.clear();
        listaContratoOrdenDTO = listaContratoOrdenDTOAux;
    }

//    public void buscarContratoByNumero() {
//        listaContratos = contratoService.obtenerByNumeroContrato(numeroContrato);
//        if (listaContratos.size() > 0) {
//            Contratos contratos = listaContratos.get(0);
//            ordenSuministro.setIdContrato(contratos);
//            lugarEntrega = contratos.getIdDestino().getNombre() + ", " + contratos.getIdDestino().getDomicilio();
//            fechaInicialContrato = new Date();
//            fechaFinalContrato = contratos.getVigenciaFinal();
//            listaConFalloProcRcb = contratos.getIdContratoFalloProcedimientoRcbList();
//            if (listaConFalloProcRcb.size() > 0) {
//                proveedores = listaConFalloProcRcb.get(0).getIdFalloProcedimientoRcb().getIdProveedor();
//                DetalleOrdenSuministro dos = null;
//                for (ContratoFalloProcedimientoRcb cfpr : listaConFalloProcRcb) {
//                    dos = new DetalleOrdenSuministro();
//                    dos.setActivo(1);
//                    dos.setCancelado(1);
//                    dos.setFechaEntregaInicial(new Date());
//                    dos.setFechaEntregaFinal(new Date());
//                    //dos.setTotalCancelado(0);
//                    dos.setFechaAlta(new Date());
//                    dos.setIdFalloProcedimientoRcb(cfpr.getIdFalloProcedimientoRcb());
//                    dos.setUsuarioAlta(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
//                    Integer total = 0;
//                    Integer suministrado = 0;
//                    suministrado = detalleOrdenSuministroService.obtenerByIdFalloProcRcb(dos.getIdFalloProcedimientoRcb().getIdFalloProcedimientoRcb());
//                    dos.setCantidadSuministrada(suministrado);
//                    dos.setCantidadSuministrar(0);
//                    if (suministrado != -1) {
//                        total = dos.getIdFalloProcedimientoRcb().getCantidadModificada().intValue() + dos.getIdFalloProcedimientoRcb().getCantidadAgregadaConvenio().intValue();
//                        total = total.intValue() - suministrado.intValue();
//                        dos.setTotalCancelado(total);
//                        dos.setCantidadSuministrar(total);
//                    }
//                    dos.setImporte(util.multiplica(dos.getIdFalloProcedimientoRcb().getPrecioUnitario(), dos.getCantidadSuministrar()));
//                    listaDetalleOrden.add(dos);
//                }
//            }
//        } else {
//            mensaje.mensaje("No se encontro ningún contrato con ese número", "amarillo");
//            numeroContrato = "";
//        }
//    }

    public void validaCampos(CellEditEvent event) {
        DataTable s = (DataTable) event.getSource();
        DetalleOrdenSuministro dos = (DetalleOrdenSuministro) s.getRowData();
        dos.setImporte(util.multiplica(dos.getIdFalloProcedimientoRcb().getPrecioUnitario(), dos.getCantidadSuministrar()));
        if (dos.getFechaEntregaInicial().after(dos.getFechaEntregaFinal())) {
            dos.setFechaEntregaInicial(new Date());
            dos.setFechaEntregaFinal(new Date());
            mensaje.mensaje("La fecha inicial es mayor a la fecha final", "amarillo");
        }
    }

    public void obtenerOrdenes() {
        ordenSuministroBusqueda = new OrdenSuministro();
        ordenSuministroBusqueda.setNumeroOrden(numeroOrden);
        Contratos c = new Contratos();
        c.setNumeroContrato(numeroContrato);
        ordenSuministroBusqueda.setIdContrato(c);
        listaOrdenSuministro = ordenSuministroService.obtenerOrdenesSuministro(ordenSuministroBusqueda,usuarios.getIdUsuario());
    }

    public String verDetalleOrden() {
        util.setContextAtributte("ordenSuministro", this.ordenSuministro);
        return "detalleOrdenSuministro.xhtml?faces-redirect=true";
    }

    public void limpiar() {
        RequestContext.getCurrentInstance().reset("formOrden");
        init();
    }

    public OrdenSuministro getOrdenSuministro() {
        return ordenSuministro;
    }

    public void setOrdenSuministro(OrdenSuministro ordenSuministro) {
        this.ordenSuministro = ordenSuministro;
    }

    public Integer getTabActivo() {
        return tabActivo;
    }

    public void setTabActivo(Integer tabActivo) {
        this.tabActivo = tabActivo;
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

    public List<ContratoFalloProcedimientoRcb> getListaConFalloProcRcb() {
        return listaConFalloProcRcb;
    }

    public void setListaConFalloProcRcb(List<ContratoFalloProcedimientoRcb> listaConFalloProcRcb) {
        this.listaConFalloProcRcb = listaConFalloProcRcb;
    }

    public List<DetalleOrdenSuministro> getListaDetalleOrden() {
        return listaDetalleOrden;
    }

    public void setListaDetalleOrden(List<DetalleOrdenSuministro> listaDetalleOrden) {
        this.listaDetalleOrden = listaDetalleOrden;
    }

    public List<DetalleOrdenSuministro> getListaDetalleOrdenSelect() {
        return listaDetalleOrdenSelect;
    }

    public void setListaDetalleOrdenSelect(List<DetalleOrdenSuministro> listaDetalleOrdenSelect) {
        this.listaDetalleOrdenSelect = listaDetalleOrdenSelect;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public boolean isBpreorden() {
        return bpreorden;
    }

    public void setBpreorden(boolean bpreorden) {
        this.bpreorden = bpreorden;
    }

    public Proveedores getProveedores() {
        return proveedores;
    }

    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }

    public String getLugarEntrega() {
        return lugarEntrega;
    }

    public void setLugarEntrega(String lugarEntrega) {
        this.lugarEntrega = lugarEntrega;
    }

    public List<Estatus> getListaEstatus() {
        return listaEstatus;
    }

    public void setListaEstatus(List<Estatus> listaEstatus) {
        this.listaEstatus = listaEstatus;
    }

    public List<DetalleOrdenSuministro> getListaDetalleOrdenFilter() {
        return listaDetalleOrdenFilter;
    }

    public void setListaDetalleOrdenFilter(List<DetalleOrdenSuministro> listaDetalleOrdenFilter) {
        this.listaDetalleOrdenFilter = listaDetalleOrdenFilter;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Date getFechaInicialContrato() {
        return fechaInicialContrato;
    }

    public void setFechaInicialContrato(Date fechaInicialContrato) {
        this.fechaInicialContrato = fechaInicialContrato;
    }

    public Date getFechaFinalContrato() {
        return fechaFinalContrato;
    }

    public void setFechaFinalContrato(Date fechaFinalContrato) {
        this.fechaFinalContrato = fechaFinalContrato;
    }

    public OrdenSuministro getOrdenSuministroBusqueda() {
        return ordenSuministroBusqueda;
    }

    public void setOrdenSuministroBusqueda(OrdenSuministro ordenSuministroBusqueda) {
        this.ordenSuministroBusqueda = ordenSuministroBusqueda;
    }

    public List<OrdenSuministro> getListaOrdenSuministro() {
        return listaOrdenSuministro;
    }

    public void setListaOrdenSuministro(List<OrdenSuministro> listaOrdenSuministro) {
        this.listaOrdenSuministro = listaOrdenSuministro;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public List<ContratoOrdenDTO> getListaContratoOrdenDTO() {
        return listaContratoOrdenDTO;
    }

    public void setListaContratoOrdenDTO(List<ContratoOrdenDTO> listaContratoOrdenDTO) {
        this.listaContratoOrdenDTO = listaContratoOrdenDTO;
    }

    public List<ContratoOrdenDTO> getListaContratoOrdenDTOFilter() {
        return listaContratoOrdenDTOFilter;
    }

    public void setListaContratoOrdenDTOFilter(List<ContratoOrdenDTO> listaContratoOrdenDTOFilter) {
        this.listaContratoOrdenDTOFilter = listaContratoOrdenDTOFilter;
    }
    
}
