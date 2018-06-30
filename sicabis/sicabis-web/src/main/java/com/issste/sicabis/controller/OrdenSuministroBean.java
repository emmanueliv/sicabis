package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.ContratoOrdenDTO;
import com.issste.sicabis.ejb.ln.AreasService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ContratoFalloProcedimientoRcbService;
import com.issste.sicabis.ejb.ln.ContratoService;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.ln.DpnInsumosService;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.FalloProcedimientoRcbService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.OrdenSuministroService;
import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.service.silodisa.modelo.AlertasOperativas;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveCenadi;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaReservaClaveCenadi;
import com.issste.sicabis.ejb.service.silodisa.service.AlertasOperativasService;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciaReservaClaveCenadiService;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciasPorClaveCenadiService;
import com.issste.sicabis.ejb.utils.ExistenciaPorClaveCenadiUtil;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

public class OrdenSuministroBean {

    @EJB
    private ExistenciaReservaClaveCenadiService existenciaReservaClaveCenadiService;

    @EJB
    private EstatusService estatusService1;

    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private DpnInsumosService dpnInsumosService;

    @EJB
    private ExistenciaPorClaveCenadiUtil existenciaPorClaveCenadiUtil;

    @EJB
    private ExistenciasPorClaveCenadiService existenciasPorClaveCenadiService;

    @EJB
    private AlertasOperativasService alertasOperativasService;

    @EJB
    private FalloProcedimientoRcbService falloProcedimientoRcbService;

    @EJB
    private ContratoFalloProcedimientoRcbService contratoFalloProcedimientoRcbService;

    @EJB
    private InsumosService insumosService;

    @EJB
    private AreasService areasService;

    @EJB
    private OrdenSuministroService ordenSuministroService;

    @EJB
    private EstatusService estatusService;

    @EJB
    private ContratoService contratoService;

    private Usuarios usuarios;
    private OrdenSuministro ordenSuministro;
    private OrdenSuministro ordenSuministroBusqueda;
    private Proveedores proveedores;
    private Insumos insumos;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

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
    private int idArea;
    private List<Area> listaAreas;
    private String claveInsumo;
    private boolean bareas;
    private List<ContratoOrdenDTO> listaInsumosDisp;
    private List<ContratoOrdenDTO> listaInsumosDispFilter;
    private List<Integer> listaProv;

    private final Integer idTareaProc = 7;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();

    public OrdenSuministroBean() {
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        bpreorden = Integer.parseInt(util.getContextAtributte("opcionOrden").toString()) == 1 ? true : false;
        listaFalloProcRcb = new ArrayList();
        listaDetalleOrden = new ArrayList();
        listaContratoOrdenDTO = new ArrayList();
        listaInsumosDisp = new ArrayList();
        listaAreas = new ArrayList();
        this.cargarAreas();
        idArea = usuarios.getIdArea().getIdArea();
        if (usuarios.getUsuario().equals("admin")) {
            idArea = -1;
        }
        if (listaAreas.size() == 1) {
            bareas = true;
        }
        ordenSuministro = this.inicializaOrden(ordenSuministro);
        listaEstatus = estatusService.getEstatusByTarea(idTareaProc);
        if (bpreorden) {
            idEstatus = 71;
            if (usuarios.getIdArea() != null) {
                idArea = usuarios.getIdArea().getIdArea();
                bareas = true;
            }
        }
    }

    public void cargarAreas() {
        List<Area> areasListAux = areasService.obtenerAreas();
        for (Area ar : areasListAux) {
            if (ar.getIdArea() != 10 && ar.getIdPadre() != null) {
                if (usuarios.getIdArea().getIdArea() == 16 || usuarios.getIdArea().getIdArea() == 17) {
                    if (ar.getIdArea() >= 11 && ar.getIdArea() <= 14) {
                        listaAreas.add(ar);
                    }
                } else if (Objects.equals(ar.getIdArea(), usuarios.getIdArea().getIdArea())) {
                    listaAreas.add(ar);
                    idArea = ar.getIdArea();
                }
            }
            if (usuarios.getIdUsuario() == 1) {
                listaAreas.add(ar);
            }
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

    public void obtenerClaves() {
        boolean bandera = true;
        Integer existencias = 0;
        if (!claveInsumo.equals("")) {
            insumos = insumosService.obtieneByClave(claveInsumo);
            System.out.println("insumos--->" + insumos);
            if (insumos == null) {
                bandera = false;
            }
        }
        if (bandera) {
            if (idArea != -1 && insumos != null) {
                if (idArea != insumos.getAsignacionInsumosList().get(0).getIdArea().getIdArea()) {
                    mensaje.mensaje("La clave introducida no corresponde al área seleccionada", "amarillo");
                    bandera = false;
                }
            }
            if (bandera) {
                listaInsumosDisp = detalleOrdenSuministroService.obtenerDisponibleByClave(claveInsumo, idArea);
                if (listaInsumosDisp == null) {
                    mensaje.mensaje("No hay insumos disponible para la(s) clave(s)", "amarillo");
                } else {
                    for (ContratoOrdenDTO iterator : listaInsumosDisp) {
                        existencias = 0;
                        List<ExistenciaReservaClaveCenadi> list = existenciaReservaClaveCenadiService.detalleExistenciaReservaClaveCenadi(iterator.getClaveInsumo());
                        if (list != null) {
                            ExistenciaReservaClaveCenadi ercc = list.get(0);
                            if (ercc.getDisponibleDeReserva() != null) {
                                existencias = ercc.getDisponibleDeReserva();
                            }
                        }
                        iterator.setExistencias(existencias);
                        //iterator.setExistencias(existenciasPorClaveCenadiService.existenciaSumPorClaveCenadiByClave(iterator.getClaveInsumo()));
                    }
                }
            }
        } else {
            mensaje.mensaje("La clave introducida no existe", "amarillo");
        }

    }

    public void agregaClave(ContratoOrdenDTO codto) throws IOException {
        Integer existenciaCenadi = 0;
        Integer dpn = 0;
        boolean band = true;
        boolean bandera = false;
        BigDecimal resultado = BigDecimal.ZERO;
        List<ExistenciaPorClaveCenadi> existenciasList = null;
        List<AlertasOperativas> alertasList = null;
        if (codto.getCantidadSuministrar().intValue() <= 0) {
            mensaje.mensaje("Para la clave seleccionada la cantidad a suministrar debe ser mayor a cero", "amarillo");
        } else {
            Integer cantidad = codto.getCantidadSuministrar();
            //obtiene de silodisa info
            //existenciaPorClaveCenadiUtil.validaExistenciaClaveCenadi(codto.getClaveInsumo());
//            existenciaCenadi = existenciasPorClaveCenadiService.existenciaSumPorClaveCenadiByClave(codto.getClaveInsumo());
//            if (existenciasPorClaveCenadiService.existenciaSumPorClaveCenadiByClave(codto.getClaveInsumo()) != 0) {
//                existenciaCenadi = existenciasPorClaveCenadiService.existenciaSumPorClaveCenadiByClave(codto.getClaveInsumo());
//            }
            List<ExistenciaReservaClaveCenadi> list = existenciaReservaClaveCenadiService.detalleExistenciaReservaClaveCenadi(codto.getClaveInsumo());
            if (list != null) {
                ExistenciaReservaClaveCenadi ercc = list.get(0);
                if (ercc.getDisponibleDeReserva() != null) {
                    existenciaCenadi = ercc.getDisponibleDeReserva();
                }
            }
            dpn = dpnInsumosService.getBySumDpnByInsumo(codto.getClaveInsumo(), "");
            if (dpn != 0) {
                Integer suma = cantidad + existenciaCenadi;
                resultado = new BigDecimal(suma).divide(new BigDecimal(dpn), 2, RoundingMode.HALF_UP);
                resultado = resultado.multiply(new BigDecimal(30));
            }
            //resultado = new BigDecimal(0.0);
            if (resultado.intValue() > 51) {
                mensaje.mensaje("Para la clave seleccionada la cantidad a suministrar excede los 51 días de cobertura", "amarillo");
            }
            //consulta piezas de contrato
            listaConFalloProcRcb = contratoFalloProcedimientoRcbService.obtenerCfprByClave(codto.getClaveInsumo());
            if (listaConFalloProcRcb.size() > 0) {
                DetalleOrdenSuministro dos = null;
                ContratoOrdenDTO codtoAux = null;
                for (ContratoFalloProcedimientoRcb cfpr : listaConFalloProcRcb) {
                    dos = new DetalleOrdenSuministro();
                    codtoAux = new ContratoOrdenDTO();
                    codtoAux.setId(cfpr.getIdContratoFalloProcedimientoRcb());

                    dos.setActivo(1);
                    dos.setCancelado(1);
                    dos.setFechaEntregaInicial(new Date());
                    dos.setFechaEntregaFinal(util.sumarRestarDiasFecha(new Date(), 15));
                    //dos.setTotalCancelado(0);
                    dos.setFechaAlta(new Date());
                    dos.setIdFalloProcedimientoRcb(cfpr.getIdFalloProcedimientoRcb());
                    dos.setUsuarioAlta(usuarios.getUsuario());
                    Integer total = 0;
                    Integer suministrado = 0;
                    suministrado = cfpr.getIdFalloProcedimientoRcb().getSuministradoOrden();//detalleOrdenSuministroService.obtenerByIdFalloProcRcb(dos.getIdFalloProcedimientoRcb().getIdFalloProcedimientoRcb());
                    dos.setCantidadSuministrada(suministrado);
                    total = dos.getIdFalloProcedimientoRcb().getCantidadModificada().intValue() + dos.getIdFalloProcedimientoRcb().getCantidadAgregadaConvenio().intValue();
                    if (total <= 0) {
                        total = 0;
                    } else {
                        total = total.intValue() - suministrado.intValue();
                    }
                    if (cantidad.intValue() > total) {
                        dos.setCantidadSuministrar(total);
                        dos.setTotalCancelado(0);
                        codtoAux.setCompletado(1);
                        cantidad = cantidad - total;
                    } else if (cantidad.intValue() == total) {
                        dos.setCantidadSuministrar(total);
                        dos.setTotalCancelado(0);
                        codtoAux.setCompletado(1);
                        bandera = true;
                    } else {
                        dos.setTotalCancelado(total.intValue() - cantidad.intValue());
                        dos.setCantidadSuministrar(cantidad);
                        codtoAux.setCompletado(0);
                        bandera = true;
                    }
                    dos.setImporte(util.multiplica(dos.getIdFalloProcedimientoRcb().getPrecioUnitario(), dos.getCantidadSuministrar()));
                    codtoAux.setClaveInsumo(codto.getClaveInsumo());
                    codtoAux.setLugarEntrega(cfpr.getIdContrato().getIdDestino().getNombre() + ", " + cfpr.getIdContrato().getIdDestino().getDomicilio());
                    codtoAux.setFechaInicialContrato(new Date());
                    codtoAux.setFechaFinalContrato(cfpr.getIdContrato().getVigenciaFinal());
                    codtoAux.setCantidadSuministrar(dos.getCantidadSuministrar());
                    codtoAux.setContratoFalloProcedimientoRcb(cfpr);
                    codtoAux.setDetalleOrdenSuministro(dos);
                    listaContratoOrdenDTO.add(codtoAux);
                    if (bandera) {
                        break;
                    }
                }
            }
            this.opcionClaves(false, codto);
        }
    }

    public void opcionClaves(boolean bopcion, ContratoOrdenDTO codto) {
        List<ContratoOrdenDTO> listaInsumosDispAux = new ArrayList();
        for (ContratoOrdenDTO insumos : listaInsumosDisp) {
            if (codto.getClaveInsumo().equals(insumos.getClaveInsumo())) {
                insumos.setBopcion(bopcion);

            }
            listaInsumosDispAux.add(insumos);
        }
        listaInsumosDisp.clear();
        listaInsumosDisp = listaInsumosDispAux;
    }

    public void quitarClaves(ContratoOrdenDTO contratoOrdenDTO) {
        List<ContratoOrdenDTO> listaContratoOrdenDTOAux = new ArrayList();
        for (ContratoOrdenDTO codto : listaContratoOrdenDTO) {
            if (codto != contratoOrdenDTO) {
                listaContratoOrdenDTOAux.add(codto);
            }
        }
        listaContratoOrdenDTO.clear();
        listaContratoOrdenDTO = listaContratoOrdenDTOAux;
        boolean bandera = true;
        for (ContratoOrdenDTO codto : listaContratoOrdenDTO) {
            if (contratoOrdenDTO.getClaveInsumo().equals(codto.getClaveInsumo())) {
                bandera = false;
            }
        }
        if (bandera) {
            this.opcionClaves(true, contratoOrdenDTO);
        }
    }

    public boolean valida() {
        boolean bandera = true;
        if (!bpreorden) {
            if (idEstatus.intValue() == -1) {
                mensaje.mensaje("Debes seleccionar un estatus", "amarillo");
                bandera = false;
            }
        }
        if (numeroOrden == null || numeroOrden.equals("")) {
            mensaje.mensaje("Debes agregar el número de orden", "amarillo");
            bandera = false;
        }
        if (listaContratoOrdenDTO.size() == 0 || listaContratoOrdenDTO == null) {
            mensaje.mensaje("Debes agregar al menos una clave", "amarillo");
            bandera = false;
        }
        if (!this.validaNumeroOrden()) {
            bandera = false;
        }

        if (bandera) {
            List<Integer> listaProvAux = new ArrayList();
            listaProv = new ArrayList();
            Integer idProv = 0;
            bandera = true;
            for (ContratoOrdenDTO codto : listaContratoOrdenDTO) {
                idProv = codto.getContratoFalloProcedimientoRcb().getIdFalloProcedimientoRcb().getIdProveedor().getIdProveedor();
                if (listaProv.size() == 0) {
                    listaProv.add(idProv);
                } else {
                    listaProvAux.clear();
                    listaProvAux = listaProv;
                    boolean bandera1 = true;
                    for (Integer idProvAux : listaProvAux) {
                        if (idProvAux.intValue() == idProv.intValue()) {
                            bandera1 = false;
                        }
                    }
                    if (bandera1) {
                        listaProv.add(idProv);
                    }
                }
            }
        }
        return bandera;
    }

    public void existeNumeroOrden() {
        boolean bandera = this.validaNumeroOrden();
    }

    public boolean validaNumeroOrden() {
        boolean bandera = true;
        List<OrdenSuministro> cAux = ordenSuministroService.obtenerByNumeroOrden(numeroOrden);
        if (cAux.size() != 0) {
            mensaje.mensaje("El número de orden ya esta registrada", "amarillo");
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
        //String numeroOrden = "";
        BigDecimal importe = BigDecimal.ZERO;
        int x = 0;
        for (Integer idProv : listaProv) {
            ordenSuministro = new OrdenSuministro();
            ordenSuministro.setIdEstatus(new Estatus(idEstatus));
            List<DetalleOrdenSuministro> listaDetalleOrdenAux = new ArrayList();
            if (listaProv.size() == 1) {
                for (ContratoOrdenDTO codto : listaContratoOrdenDTO) {
                    if (codto.getContratoFalloProcedimientoRcb().getIdFalloProcedimientoRcb().getIdProveedor().getIdProveedor().intValue() == idProv.intValue()) {
                        DetalleOrdenSuministro dos = codto.getDetalleOrdenSuministro();
                        FalloProcedimientoRcb fpr = codto.getContratoFalloProcedimientoRcb().getIdFalloProcedimientoRcb();
                        fpr.setCompletadoContrato(codto.getCompletado());
                        fpr.setSuministradoOrden(fpr.getSuministradoOrden() + codto.getCantidadSuministrar());
                        dos.setCantidadSuministrada(0);
                        boolean actualiza = falloProcedimientoRcbService.actualizaCantidadConvenio(fpr);
                        dos.setTotalCancelado(0);
                        ordenSuministro.setIdContrato(codto.getContratoFalloProcedimientoRcb().getIdContrato());
                        ordenSuministro.setCantidadSuministrar(codto.getCantidadSuministrar());
                        importe = importe.add(dos.getImporte());
                        dos.setIdOrdenSuministro(ordenSuministro);
                        listaDetalleOrdenAux.add(dos);
                    }
                }
//                Integer maxNum = ordenSuministroService.obtenerUltimoIdOrden();
//                if (maxNum == null) {
//                    maxNum = 1;
//                } else {
//                    maxNum++;
//                }
//                numeroOrden = "ORDEN_" + maxNum;
                ordenSuministro.setActivo(1);
                ordenSuministro.setNumeroOrden(numeroOrden);
                ordenSuministro.setFechaAlta(new Date());
                ordenSuministro.setFechaOrden(new Date());
                ordenSuministro.setUsuarioAlta(usuarios.getUsuario());
                ordenSuministro.setImporte(importe);
                ordenSuministro.setDetalleOrdenSuministroList(listaDetalleOrdenAux);
                if (ordenSuministroService.guardaOrdenSuministro(ordenSuministro)) {
                    bitacoraTareaEstatus.setDescripcion("Guardar orden suministro:" + ordenSuministro.getNumeroOrden() + "");
                    bitacoraTareaEstatus.setFecha(new Date());
                    bitacoraTareaEstatus.setIdModulos(1);
                    bitacoraTareaEstatus.setIdEstatus(idEstatus);
                    bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                    bitacoraTareaEstatus.setIdTareaId(7);
                    bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                    mensaje.mensaje(mensaje.datos_guardados, "verde");
                } else {
                    mensaje.mensaje(mensaje.error_guardar, "rojo");
                }
            } else {
                ContratoOrdenDTO codto = listaContratoOrdenDTO.get(x);
                if (codto.getContratoFalloProcedimientoRcb().getIdFalloProcedimientoRcb().getIdProveedor().getIdProveedor().intValue() == idProv.intValue()) {
                    DetalleOrdenSuministro dos = codto.getDetalleOrdenSuministro();
                    FalloProcedimientoRcb fpr = codto.getContratoFalloProcedimientoRcb().getIdFalloProcedimientoRcb();
                    fpr.setCompletadoContrato(codto.getCompletado());
                    fpr.setSuministradoOrden(fpr.getSuministradoOrden() + codto.getCantidadSuministrar());
                    boolean actualiza = falloProcedimientoRcbService.actualizaCantidadConvenio(fpr);
                    dos.setTotalCancelado(0);
                    ordenSuministro.setIdContrato(codto.getContratoFalloProcedimientoRcb().getIdContrato());
                    ordenSuministro.setCantidadSuministrar(codto.getCantidadSuministrar());
                    importe = importe.add(dos.getImporte());
                    dos.setIdOrdenSuministro(ordenSuministro);
                    listaDetalleOrdenAux.add(dos);
                    Integer maxNum = ordenSuministroService.obtenerUltimoIdOrden();
                    if (maxNum == null) {
                        maxNum = 1;
                    } else {
                        maxNum++;
                    }
//                    numeroOrden = "ORDEN_" + maxNum;
                    ordenSuministro.setActivo(1);
                    ordenSuministro.setNumeroOrden(numeroOrden);
                    ordenSuministro.setFechaAlta(new Date());
                    ordenSuministro.setFechaOrden(new Date());
                    ordenSuministro.setUsuarioAlta(usuarios.getUsuario());
                    ordenSuministro.setImporte(importe);
                    ordenSuministro.setDetalleOrdenSuministroList(listaDetalleOrdenAux);
                    if (ordenSuministroService.guardaOrdenSuministro(ordenSuministro)) {
                        mensaje.mensaje(mensaje.datos_guardados, "verde");
                        x++;
                        bitacoraTareaEstatus.setDescripcion("Guardar orden suministro:" + ordenSuministro.getNumeroOrden() + "");
                        bitacoraTareaEstatus.setFecha(new Date());
                        bitacoraTareaEstatus.setIdModulos(1);
                        bitacoraTareaEstatus.setIdEstatus(idEstatus);
                        bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                        bitacoraTareaEstatus.setIdTareaId(7);
                        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                    } else {
                        mensaje.mensaje(mensaje.error_guardar, "rojo");
                    }
                }
            }
        }
        cancel();
    }

    public void cancel() {
        RequestContext.getCurrentInstance().reset("formOrden");
        init();
    }

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
        listaOrdenSuministro = ordenSuministroService.obtenerOrdenesSuministroByArea(ordenSuministroBusqueda, usuarios.getIdArea().getIdArea());
        if (listaOrdenSuministro == null) {
            mensajeBorrar = true;
            mensaje.mensaje("No existen registros correspondientes a esta area.", "amarillo");
        }
        for (OrdenSuministro iterator : listaOrdenSuministro) {
            Estatus e = estatusService.getRemisionEstatus(iterator.getIdEstatus().getIdEstatus());
            iterator.setIdEstatus(e);
        }
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
        this.numeroContrato = numeroContrato.toUpperCase();
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
        this.numeroOrden = numeroOrden.toUpperCase();
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

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public List<Area> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List<Area> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public String getClaveInsumo() {
        return claveInsumo;
    }

    public void setClaveInsumo(String claveInsumo) {
        this.claveInsumo = claveInsumo;
    }

    public boolean isBareas() {
        return bareas;
    }

    public void setBareas(boolean bareas) {
        this.bareas = bareas;
    }

    public List<ContratoOrdenDTO> getListaInsumosDisp() {
        return listaInsumosDisp;
    }

    public void setListaInsumosDisp(List<ContratoOrdenDTO> listaInsumosDisp) {
        this.listaInsumosDisp = listaInsumosDisp;
    }

    public List<ContratoOrdenDTO> getListaInsumosDispFilter() {
        return listaInsumosDispFilter;
    }

    public void setListaInsumosDispFilter(List<ContratoOrdenDTO> listaInsumosDispFilter) {
        this.listaInsumosDispFilter = listaInsumosDispFilter;
    }

    public List<Integer> getListaProv() {
        return listaProv;
    }

    public void setListaProv(List<Integer> listaProv) {
        this.listaProv = listaProv;
    }

}
