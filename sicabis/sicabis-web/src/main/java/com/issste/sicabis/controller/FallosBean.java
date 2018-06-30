package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.FalloPropuestaProcDTO;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.FalloService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.ProcedimientosRcbService;
import com.issste.sicabis.ejb.ln.ProveedorService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.ClasificacionImportancia;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Fallos;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import com.issste.sicabis.ejb.modelo.Propuestas;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.BitacoraUtil;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

public class FallosBean {

    @EJB
    private InsumosService insumosService;

    @EJB
    private ProveedorService proveedorService;

    @EJB
    private FalloService falloService;

    @EJB
    private EstatusService estatusService;

    @EJB
    private ProcedimientosRcbService procedimientosRcbService;

    /*
     Implementacion de bitacora
     */
    @EJB
    private BitacoraTareaSerice bitacoraService;

    private BitacoraTareaEstatus bitacora;
    private BitacoraUtil bitacoraUtil = new BitacoraUtil();

    private Procedimientos procedimientos;
    private Usuarios usuarios;
    private Fallos fallos;
    private FalloPropuestaProcDTO falloPropuestaProcDTO;
    private FalloProcedimientoRcb falloProcedimientoRcb;

    private Integer tabActivo;
    private boolean botonGuardar = true;
    private boolean mensajeBorrar;
    private boolean messageGuardar = true;
    private List<ProcedimientoRcb> listaProcRcb;
    private List<Procedimientos> listaProc;
    private String numeroProcedimiento;
    private List<FalloPropuestaProcDTO> listaFalloPropProc;
    private List<FalloPropuestaProcDTO> listaFalloPropProcFilter;
    private List<FalloPropuestaProcDTO> listaFalloPropProcSelect;
    private Integer idProveedor;
    private Integer idFalloPropuestaProcDTO;
    private Integer porcientoTotal;
    private Integer ultimoId;
    private boolean mensajeTabla;
    private List<Estatus> listaEstatusFallo;
    private List<FalloProcedimientoRcb> listaFalloProcRcb;
    private String numeroFalloB;
    private List<Fallos> listaFallos;
    private String numeroProcedimientoB;
    private Integer idEstatus;
    private boolean btipocompra;
    private List<Proveedores> listaProv;

    private final Integer idTareaProc = 4;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();

    public FallosBean() {
        bitacora = new BitacoraTareaEstatus();
    }

    @PostConstruct
    public void init() {
        listaProcRcb = new ArrayList();
        listaProc = new ArrayList();
        listaFalloPropProc = new ArrayList();
        listaFalloProcRcb = new ArrayList();
        listaFalloPropProcSelect = new ArrayList();
        listaProv = new ArrayList();
        bitacora = new BitacoraTareaEstatus();
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        listaEstatusFallo = estatusService.getEstatusByTarea(4);
        fallos = new Fallos();
        fallos.setIdEstatus(new Estatus(0));
        fallos.setAnioAfectacion(util.getYear());
        fallos.setFechaFallo(new Date());
        fallos.setActivo(1);
        fallos.setFechaAlta(new Date());
        fallos.setUsuarioAlta(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
        //importeTotal = BigDecimal.ZERO;
    }

    public void obtenerProcedimientoByNumero() {
        boolean bandera = true;
        boolean desierta = false;
        if (!numeroProcedimiento.equals("")) {
            listaFallos = new ArrayList();
            Fallos f = new Fallos();
            f.setNumeroProcedimiento(numeroProcedimiento);
            fallos.setNumeroFallo(numeroProcedimiento);
            f.setNumeroFallo("");
            listaFallos = falloService.obtenerByFalloProcRcb(f);
            if (listaFallos != null) {
                mensaje.mensaje("El fallo para el número de procedimiento ingresado ya fue almacenado previamente", "amarillo");
            } else {
                listaProcRcb = procedimientosRcbService.obtenerByNumeroProc(numeroProcedimiento);
                listaProc.clear();
                listaFalloPropProc.clear();
                if (listaProcRcb.size() > 0) {
                    listaProc.add(listaProcRcb.get(0).getIdProcedimiento());
                    if (listaProcRcb.get(0).getIdProcedimiento().getIdTipoCompra().getNombre().equals("ISSSTE")) {
                        btipocompra = true;
                    } else {
                        btipocompra = false;
                    }
                }
                if (btipocompra) {//compras ISSSTE
                    for (ProcedimientoRcb listaProcRcb1 : listaProcRcb) {
                        falloPropuestaProcDTO = new FalloPropuestaProcDTO();
                        falloPropuestaProcDTO.setProcedimientoRcb(listaProcRcb1);
                        falloPropuestaProcDTO.setListaPropuestas(listaProcRcb1.getPropuestasList());
                        FalloProcedimientoRcb fpr = new FalloProcedimientoRcb();
                        Proveedores provedores = new Proveedores();
                        bandera = false;
                        for (Propuestas prop : falloPropuestaProcDTO.getListaPropuestas()) {
                            if (prop.getGanador().intValue() == 1) {
                                bandera = true;
                                provedores.setActivo(prop.getIdProveedor().getActivo());
                                provedores.setAutorizado(prop.getIdProveedor().getAutorizado());
                                provedores.setIdProveedor(prop.getIdProveedor().getIdProveedor());
                                provedores.setNumeroProveedor(prop.getIdProveedor().getNumeroProveedor());
                                fpr.setIdProveedor(provedores);
                                falloPropuestaProcDTO.setDescuentoOtorgado(prop.getDescuentoOtorgado());
                                falloPropuestaProcDTO.setPrecioUnitario(prop.getPrecioUnitario());
                                fpr.setPrecioUnitario(prop.getPrecioUnitarioDescuento());
                                falloPropuestaProcDTO.setImporte(prop.getImporte());
                                break;
                            }
                        }
                        if (!bandera) {
                            fpr.setIdProveedor(new Proveedores(-1, -1));
                            falloPropuestaProcDTO.setDescuentoOtorgado(BigDecimal.ZERO);
                            falloPropuestaProcDTO.setPrecioUnitario(BigDecimal.ZERO);
                            fpr.setPrecioUnitario(BigDecimal.ZERO);
                            falloPropuestaProcDTO.setImporte(BigDecimal.ZERO);
                            if (falloPropuestaProcDTO.getListaPropuestas().size() > 0) {
                                desierta = true;
                            }
                        }
                        fpr.setPorcentajeAdjudicacion(100);
                        fpr.setCantidadModificada(listaProcRcb1.getCantidadPiezas());
                        ultimoId = listaProcRcb1.getIdProcedimientoRcb();
                        falloPropuestaProcDTO.setIdFalloPropuestaProcDTO(ultimoId);
                        falloPropuestaProcDTO.setFalloProcedimientoRcb(fpr);
                        falloPropuestaProcDTO.setPiezas(listaProcRcb1.getCantidadPiezas());
                        falloPropuestaProcDTO.setPiezasAdjudicadas(listaProcRcb1.getCantidadPiezas());
                        falloPropuestaProcDTO.setIdPadre(0);
                        falloPropuestaProcDTO.setPorciento(100);
                        listaFalloPropProc.add(falloPropuestaProcDTO);
                        if (desierta) {
                            listaFalloPropProcSelect.add(falloPropuestaProcDTO);
                        }
                    }
                } else {//compras IMSS
                    listaProv = proveedorService.obtenerByAutorizado();
                    for (ProcedimientoRcb prcb : listaProcRcb) {
                        falloPropuestaProcDTO = new FalloPropuestaProcDTO();
                        falloPropuestaProcDTO.setProcedimientoRcb(prcb);
                        falloPropuestaProcDTO.setListaPropuestas(prcb.getPropuestasList());
                        FalloProcedimientoRcb fpr = new FalloProcedimientoRcb();

                        fpr.setIdProveedor(new Proveedores(-1, -1));
                        falloPropuestaProcDTO.setDescuentoOtorgado(BigDecimal.ZERO);
                        falloPropuestaProcDTO.setPrecioUnitario(prcb.getPrecioUnitario());
                        fpr.setPrecioUnitario(prcb.getPrecioUnitario());
                        falloPropuestaProcDTO.setImporte(prcb.getImporte());

                        fpr.setPorcentajeAdjudicacion(100);
                        fpr.setCantidadModificada(prcb.getCantidadPiezas());
                        ultimoId = prcb.getIdProcedimientoRcb();
                        falloPropuestaProcDTO.setIdFalloPropuestaProcDTO(ultimoId);
                        falloPropuestaProcDTO.setFalloProcedimientoRcb(fpr);
                        falloPropuestaProcDTO.setPiezas(prcb.getCantidadPiezas());
                        falloPropuestaProcDTO.setPiezasAdjudicadas(prcb.getCantidadPiezas());
                        falloPropuestaProcDTO.setIdPadre(0);
                        falloPropuestaProcDTO.setPorciento(100);
                        listaFalloPropProc.add(falloPropuestaProcDTO);
                    }
                }
            }
        } else {
            mensaje.mensaje("Debe escribir el número de procedimiento", "amarillo");
        }
    }

//    public void obtenerProcedimientoByNumero() {
//        if (!numeroProcedimiento.equals("")) {
//            listaFallos = new ArrayList();
//            Fallos f = new Fallos();
//            f.setNumeroProcedimiento(numeroProcedimiento);
//            fallos.setNumeroFallo(numeroProcedimiento);
//            //f.setNumeroFallo(numeroProcedimiento);
//            listaFallos = falloService.obtenerByFalloProcRcb(f);
//            if (listaFallos != null) {
//                mensaje.mensaje("El fallo para el nÃºmero de procedimiento ingresado ya fue almacenado previamente", "amarillo");
//            } else {
//                listaProcRcb = procedimientosRcbService.obtenerByNumeroProc(numeroProcedimiento);
//                listaProc.clear();
//                listaFalloPropProc.clear();
//                if (listaProcRcb.size() > 0) {
//                    listaProc.add(listaProcRcb.get(0).getIdProcedimiento());
//                }
//                for (ProcedimientoRcb listaProcRcb1 : listaProcRcb) {
//                    falloPropuestaProcDTO = new FalloPropuestaProcDTO();
//                    falloPropuestaProcDTO.setProcedimientoRcb(listaProcRcb1);
//                    falloPropuestaProcDTO.setListaPropuestas(listaProcRcb1.getPropuestasList());
//                    FalloProcedimientoRcb fpr = new FalloProcedimientoRcb();
//                    fpr.setIdProveedor(new Proveedores(-1));
//                    fpr.setPorcentajeAdjudicacion(100);
//                    fpr.setCantidadModificada(listaProcRcb1.getCantidadPiezas());
//                    fpr.setPrecioUnitario(BigDecimal.ZERO);
//                    ultimoId = listaProcRcb1.getIdProcedimientoRcb();
//                    falloPropuestaProcDTO.setIdFalloPropuestaProcDTO(ultimoId);
//                    falloPropuestaProcDTO.setFalloProcedimientoRcb(fpr);
//                    falloPropuestaProcDTO.setDescuentoOtorgado(BigDecimal.ZERO);
//                    falloPropuestaProcDTO.setImporte(BigDecimal.ZERO);
//                    falloPropuestaProcDTO.setPrecioUnitario(BigDecimal.ZERO);
//                    falloPropuestaProcDTO.setPiezas(listaProcRcb1.getCantidadPiezas());
//                    falloPropuestaProcDTO.setPiezasAdjudicadas(listaProcRcb1.getCantidadPiezas());
//                    falloPropuestaProcDTO.setIdPadre(0);
//                    falloPropuestaProcDTO.setPorciento(100);
//                    listaFalloPropProc.add(falloPropuestaProcDTO);
//                }
//            }
//        } else {
//            mensaje.mensaje("Debe escribir el nÃƒÂºmero de procedimiento", "amarillo");
//        }
//    }
    public void cambiaProveedorClave2(Integer idFalloPropuestaProcDTO) {
        this.idFalloPropuestaProcDTO = idFalloPropuestaProcDTO;
    }

    public void cambiaPorcentaje(FalloPropuestaProcDTO fppd) {
        porcientoTotal = 0;
        for (FalloPropuestaProcDTO fppdAux : this.listaFalloPropProc) {
            if (fppd.getIdFalloPropuestaProcDTO().intValue() == fppdAux.getIdFalloPropuestaProcDTO().intValue()
                    || fppdAux.getIdFalloPropuestaProcDTO().intValue() == fppd.getIdPadre()) {
                porcientoTotal = porcientoTotal + fppdAux.getFalloProcedimientoRcb().getPorcentajeAdjudicacion();
            }
        }
        this.idFalloPropuestaProcDTO = fppd.getIdFalloPropuestaProcDTO();
        if (porcientoTotal > 100) {
            this.actualizaDatos(fppd.getFalloProcedimientoRcb().getIdProveedor(), 0, -1, false);
            mensajeTabla = true;
            mensaje.mensaje("El porcentaje por ID es mayor al 100%", "amarillo");
        } else {
            Integer piezas = util.calculaPorciento(fppd.getFalloProcedimientoRcb().getPorcentajeAdjudicacion(), fppd.getPiezas());
            Integer posicion = 0;
            if (fppd.getIdPadre().intValue() == 0) {
                posicion = fppd.getIdFalloPropuestaProcDTO();
            } else {
                posicion = fppd.getIdPadre();
            }
            if (porcientoTotal == 100) {
                this.actualizaDatos(fppd.getFalloProcedimientoRcb().getIdProveedor(), piezas, posicion, false);
            } else {
                this.actualizaDatos(fppd.getFalloProcedimientoRcb().getIdProveedor(), piezas, posicion, true);
            }
        }

    }

    public void onEditLabel(CellEditEvent event) {
        DataTable s = (DataTable) event.getSource();
        FalloPropuestaProcDTO d = (FalloPropuestaProcDTO) s.getRowData();
        this.actualizaDatos(d.getFalloProcedimientoRcb().getIdProveedor(), -1, -1, false);
    }

    public void actualizaDatos(Proveedores prov, Integer piezas, Integer posicion, boolean opcion) {
        Integer idClasificacion = 0;
        Integer idProveedor = prov.getNumeroProveedor();
        prov = proveedorService.getByNumeroProveedor(prov.getNumeroProveedor());
        List<FalloPropuestaProcDTO> listaFalloPropProc = new ArrayList();
        for (FalloPropuestaProcDTO fppd : this.listaFalloPropProc) {
            if (posicion.intValue() != -1) {
                if (fppd.getIdFalloPropuestaProcDTO().intValue() == posicion.intValue()) {
                    fppd.setBopcionAgrega(opcion);
                    fppd.setPorciento(porcientoTotal);
                }
            }
            if (fppd.getIdFalloPropuestaProcDTO().intValue() == idFalloPropuestaProcDTO.intValue()) {
                if (piezas.intValue() != -1) {
                    if (piezas.intValue() == 0) {
                        fppd.setPrecioUnitario(BigDecimal.ZERO);
                        fppd.setDescuentoOtorgado(BigDecimal.ZERO);
                        FalloProcedimientoRcb fpr = new FalloProcedimientoRcb();
                        fpr.setIdProveedor(prov);
                        fpr.setPrecioUnitario(BigDecimal.ZERO);
                        fpr.setPorcentajeAdjudicacion(0);
                        fppd.setFalloProcedimientoRcb(fpr);
                        fppd.setImporte(BigDecimal.ZERO);
                    }
                    fppd.setPiezasAdjudicadas(piezas);
                }
                if (prov.getNumeroProveedor().intValue() != -1) {
                    idClasificacion = fppd.getProcedimientoRcb().getIdProcedimiento().getIdClasificacionProcedimiento().getIdClasificacionProcedimiento();
                    switch (idClasificacion.intValue()) {
                        case 1:
                            if (btipocompra) {
                                for (Propuestas p : fppd.getListaPropuestas()) {
                                    if (p.getIdProveedor().getNumeroProveedor().intValue() == idProveedor.intValue()) {
                                        fppd.setPrecioUnitario(p.getIdProcedimientoRcb().getPrecioUnitario());
                                        fppd.setDescuentoOtorgado(p.getDescuentoOtorgado());
                                        FalloProcedimientoRcb fpr = new FalloProcedimientoRcb();
                                        fpr.setIdProveedor(prov);
                                        fpr.setPrecioUnitario(util.obtieneDescuento(p.getIdProcedimientoRcb().getPrecioUnitario(), p.getDescuentoOtorgado()));
                                        fppd.setFalloProcedimientoRcb(fpr);
                                        fppd.setImporte(fpr.getPrecioUnitario().multiply(new BigDecimal(fppd.getPiezasAdjudicadas())));
                                        break;
                                    }
                                }
                            } else {
                                FalloProcedimientoRcb fpr = fppd.getFalloProcedimientoRcb();
                                fpr.setIdProveedor(prov);
                                fppd.setFalloProcedimientoRcb(fpr);
                            }
                            break;
                        case 2:
                            if (btipocompra) {
                                for (Propuestas p : fppd.getListaPropuestas()) {
                                    if (p.getIdProveedor().getIdProveedor().intValue() == idProveedor.intValue()) {
                                        fppd.setPrecioUnitario(p.getIdProcedimientoRcb().getPrecioUnitario());
                                        fppd.setDescuentoOtorgado(BigDecimal.ZERO);
                                        FalloProcedimientoRcb fpr = new FalloProcedimientoRcb();
                                        fpr.setIdProveedor(prov);
                                        fpr.setPrecioUnitario(util.obtieneDescuento(p.getIdProcedimientoRcb().getPrecioUnitario(), BigDecimal.ZERO));
                                        fppd.setFalloProcedimientoRcb(fpr);
                                        fppd.setImporte(fpr.getPrecioUnitario().multiply(new BigDecimal(fppd.getPiezasAdjudicadas())));
                                        break;
                                    }
                                }
                            } else {
                                FalloProcedimientoRcb fpr = fppd.getFalloProcedimientoRcb();
                                fpr.setIdProveedor(prov);
                                fppd.setFalloProcedimientoRcb(fpr);
                            }
                            break;
                        case 3:
                            if (btipocompra) {
                                for (Propuestas p : fppd.getListaPropuestas()) {
                                    if (p.getIdProveedor().getIdProveedor().intValue() == idProveedor.intValue()) {
                                        fppd.setPrecioUnitario(fppd.getProcedimientoRcb().getImporte());
                                        fppd.setDescuentoOtorgado(p.getDescuentoOtorgado());
                                        FalloProcedimientoRcb fpr = new FalloProcedimientoRcb();
                                        fpr.setIdProveedor(prov);
                                        fpr.setPrecioUnitario(util.obtieneDescuento(fppd.getProcedimientoRcb().getImporte(), p.getDescuentoOtorgado()));
                                        fppd.setFalloProcedimientoRcb(fpr);
                                        fppd.setImporte(fpr.getPrecioUnitario().multiply(new BigDecimal(fppd.getPiezasAdjudicadas())));
                                        break;
                                    }
                                }
                            } else {
                                FalloProcedimientoRcb fpr = fppd.getFalloProcedimientoRcb();
                                fpr.setIdProveedor(prov);
                                fppd.setFalloProcedimientoRcb(fpr);
                            }
                            break;
                    }
                }
            }
            listaFalloPropProc.add(fppd);
        }
        this.listaFalloPropProc.clear();
        this.listaFalloPropProc = listaFalloPropProc;
    }

    public void agregaRenglon(FalloPropuestaProcDTO fppd) {
        boolean bandera = false;
        FalloPropuestaProcDTO aux = new FalloPropuestaProcDTO();
        List<FalloPropuestaProcDTO> listaFalloPropProc = new ArrayList();
        for (FalloPropuestaProcDTO fppdux : this.listaFalloPropProc) {
            if (fppdux.getIdFalloPropuestaProcDTO().intValue() == fppd.getIdFalloPropuestaProcDTO().intValue()) {
                bandera = true;
                fppdux.setBopcionAgrega(false);
                fppdux.setPorciento(100);
                aux.setPiezas(fppd.getPiezas());
                aux.setPiezasAdjudicadas(util.calculaPorciento((100 - porcientoTotal), fppd.getPiezas()));
                aux.setIdPadre(fppd.getIdFalloPropuestaProcDTO());
                aux.setProcedimientoRcb(fppd.getProcedimientoRcb());
                aux.setListaPropuestas(fppd.getListaPropuestas());
                aux.setBopcionQuitar(true);
                ultimoId = ultimoId + 1;
                aux.setIdFalloPropuestaProcDTO(ultimoId);
                aux.setDescuentoOtorgado(fppdux.getDescuentoOtorgado());
                aux.setPrecioUnitario(fppdux.getPrecioUnitario());
                aux.setImporte(aux.getPrecioUnitario().multiply(new BigDecimal(aux.getPiezasAdjudicadas())));

                FalloProcedimientoRcb fpr = new FalloProcedimientoRcb();
                Proveedores prov = new Proveedores(-1);
                prov.setNumeroProveedor(-1);
                fpr.setIdProveedor(prov);
                fpr.setPorcentajeAdjudicacion(100 - porcientoTotal);
                fpr.setCantidadModificada(util.calculaPorciento((100 - porcientoTotal), fppd.getPiezas()));
                fpr.setPrecioUnitario(fppdux.getPrecioUnitario());
                aux.setFalloProcedimientoRcb(fpr);
            }
            listaFalloPropProc.add(fppdux);
            if (bandera) {
                listaFalloPropProc.add(aux);
                bandera = false;
            }
        }
        this.listaFalloPropProc.clear();
        this.listaFalloPropProc = listaFalloPropProc;
    }

    public void quitaRenglon(FalloPropuestaProcDTO fppd) {
        List<FalloPropuestaProcDTO> listaFalloPropProc = new ArrayList();
        for (FalloPropuestaProcDTO fppdaux : this.listaFalloPropProc) {
            if (fppd.getIdPadre().intValue() == fppdaux.getIdFalloPropuestaProcDTO().intValue()) {
                fppdaux.setBopcionAgrega(true);
                listaFalloPropProc.add(fppdaux);
            } else if (fppd.getIdFalloPropuestaProcDTO().intValue() != fppdaux.getIdFalloPropuestaProcDTO()) {
                listaFalloPropProc.add(fppdaux);
            }
        }
        this.listaFalloPropProc.clear();
        this.listaFalloPropProc = listaFalloPropProc;
    }

    public boolean valida() {
        boolean bandera = true;
        if (idEstatus.intValue() == -1) {
            mensaje.mensaje("Debes seleccionar un estatus del fallo", "amarillo");
            bandera = false;
        }

        return bandera;
    }

    public void validaGuardado() {
        System.out.println("id--->" + idEstatus);
        if (idEstatus.intValue() == 42) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlg1').show();");
        } else {
            this.guardarFallo();
        }
    }

    public void guardarFallo() {
        BigDecimal importeTotal = BigDecimal.ZERO;
        Insumos insumo = new Insumos();
        if (valida()) {
            ProcedimientoRcb procedimientoRcb = null;
            List<FalloPropuestaProcDTO> listaFalloPropProcAux = new ArrayList();
            boolean bandera = false;
            boolean bandera1 = true;
            boolean bandProv = true;
            boolean bdesierta = true;
            String idClaves = "";
            if (idEstatus.intValue() == 42) {
                for (FalloPropuestaProcDTO fppd : listaFalloPropProc) {
                    if (btipocompra) {
                        int provedorAutorizado = -1;
                        for (Propuestas p : fppd.getListaPropuestas()) {
                            provedorAutorizado = p.getIdProveedor().getAutorizado();
                        }
                        if (provedorAutorizado == 0 || fppd.getFalloProcedimientoRcb().getIdProveedor().getNumeroProveedor().intValue() == -1) {
                            bandProv = false;
                            if (idClaves.equals("")) {
                                idClaves = "" + fppd.getProcedimientoRcb().getIdProcedimientoRcb();
                            } else {
                                idClaves = idClaves + ", " + fppd.getProcedimientoRcb().getIdProcedimientoRcb();
                            }
                            if (listaFalloPropProcSelect.size() != 0) {
                                bandProv = true;
                            }
                        }
                    } else {
                        if (fppd.getFalloProcedimientoRcb().getIdProveedor().getNumeroProveedor().intValue() == -1) {
                            bandProv = false;
                            if (idClaves.equals("")) {
                                idClaves = "" + fppd.getProcedimientoRcb().getIdProcedimientoRcb();
                            } else {
                                idClaves = idClaves + ", " + fppd.getProcedimientoRcb().getIdProcedimientoRcb();
                            }
                        }
                    }
                }
                if (bandProv) {
                    for (FalloPropuestaProcDTO fppd : listaFalloPropProc) {
                        bandera1 = true;
                        if (fppd.getIdPadre().intValue() == 0) {
                            bandera = true;
                        }

                        if (bandera) {
                            for (FalloPropuestaProcDTO fppds : listaFalloPropProcSelect) {
                                if (fppd.getIdFalloPropuestaProcDTO().intValue() == fppds.getIdFalloPropuestaProcDTO().intValue()) {
                                    procedimientoRcb = new ProcedimientoRcb();
                                    procedimientoRcb = fppds.getProcedimientoRcb();
                                    procedimientoRcb.setDesierta(1);
                                    procedimientosRcbService.actualizaProcedimientoRcb(procedimientoRcb);
                                    bandera = false;
                                    bandera1 = false;
                                    bdesierta = false;
                                    break;
                                }
                            }
                            if (bandera1) {
                                FalloProcedimientoRcb fpr = fppd.getFalloProcedimientoRcb();
                                fpr.setActivo(1);
                                fpr.setCantidadAgregadaConvenio(0);
                                fpr.setCompletadoContrato(0);
                                fpr.setSuministradoOrden(0);
                                fpr.setPrecioUnitarioOriginal(BigDecimal.ZERO);
                                fpr.setUnidadPiezaConvenio("");
                                //fpr.setPrecioUnitario(fppd.getProcedimientoRcb().getPrecioUnitario());
                                fpr.setPrecioUnitario(util.obtieneDescuento(fppd.getPrecioUnitario(), fppd.getDescuentoOtorgado()));
                                fpr.setCantidadPiezas(fppd.getPiezas());
                                fpr.setCantidadModificada(fppd.getPiezasAdjudicadas());
                                fpr.setFechaAlta(new Date());
                                fpr.setUsuarioAlta(usuarios.getNombre());
                                fpr.setIdProcedimientoRcb(fppd.getProcedimientoRcb());
                                fpr.setImporte(util.multiplica(fpr.getPrecioUnitario(), fpr.getCantidadModificada()));
                                fpr.setIdFallo(fallos);
                                listaFalloProcRcb.add(fpr);
                                fppd.setFalloProcedimientoRcb(fpr);
                                importeTotal = importeTotal.add(fppd.getImporte());
                                procedimientoRcb = new ProcedimientoRcb();
                                procedimientoRcb = fppd.getProcedimientoRcb();
                                if (fppd.getIdPadre() == 0) {
                                    if (fppd.getPorciento().intValue() < 100) {
                                        procedimientoRcb.setCantidadPiezasOriginal(procedimientoRcb.getCantidadPiezas());
                                        procedimientoRcb.setCantidadPiezas(fppd.getPiezas() - fppd.getPiezasAdjudicadas());
                                        procedimientoRcb.setDesiertaParcial(1);
                                        procedimientoRcb.setImporte(new BigDecimal(procedimientoRcb.getCantidadPiezas()).multiply(procedimientoRcb.getPrecioUnitario()));
                                    } else if (fppd.getPorciento().intValue() == 100 && bdesierta && listaFalloPropProcSelect.size() != 0) {
                                        procedimientoRcb.setDesierta(1);
                                        procedimientoRcb.setDesiertaParcial(1);
                                    } else {
                                        procedimientoRcb.setDesierta(2);
                                        procedimientoRcb.setDesiertaParcial(2);
                                    }
                                    procedimientosRcbService.actualizaProcedimientoRcb(procedimientoRcb);
                                }
                                listaFalloPropProcAux.add(fppd);
                            }
                        }
                    }
                }
                fallos.setFalloProcedimientoRcbList(listaFalloProcRcb);
            }
            if (bandProv) {
                fallos.setIdEstatus(new Estatus(idEstatus));
                fallos.setImporteTotal(importeTotal);
                fallos.setNumeroProcedimiento(numeroProcedimiento);
                String nombreArchivo = archivosUtilidades.guardaObjetoSerializable(listaFalloPropProc, idTareaProc, 1);
                if (nombreArchivo != null) {
                    fallos.setListaFallosDto(nombreArchivo);
                    nombreArchivo = archivosUtilidades.guardaObjetoSerializable(listaFalloPropProcSelect, idTareaProc, 2);
                    if (nombreArchivo != null) {
                        fallos.setListaFallosDtoSelect(nombreArchivo);
                        if (falloService.guardaFallos(fallos)) {
                            for (FalloProcedimientoRcb iterator : fallos.getFalloProcedimientoRcbList()) {
                                insumo = insumosService.obtieneByClave(iterator.getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave());
                                insumo.setPrecioUnitario(iterator.getPrecioUnitario());
                                boolean actualiza = insumosService.actualiza(insumo);
                            }
                            mensaje.mensaje(mensaje.datos_guardados, "verde");
                            bitacora.setFecha(new Date());
                            bitacora.setIdEstatus(fallos.getIdEstatus().getIdEstatus());
                            bitacora.setDescripcion(bitacoraUtil.detalleGuardaroBitacora("fallo " + fallos.getIdFallo()));
                            bitacora.setIdUsuarios(usuarios.getIdUsuario());
                            bitacora.setIdTareaId(idTareaProc);
                            bitacoraService.guardarEnBitacora(bitacora);
                            if (fallos.getIdEstatus().getIdEstatus() == 41) {
                                RequestContext context = RequestContext.getCurrentInstance();
                                context.execute("PF('dlg3').show();");
                            }
                            if (fallos.getIdEstatus().getIdEstatus() == 42) {
                                VerDetalleFallo();
                            }
                        } else {
                            mensaje.mensaje(mensaje.error_guardar, "rojo");
                        }
                    } else {
                        mensaje.mensaje("Error al guardar lista 2", "rojo");
                    }
                } else {
                    mensaje.mensaje("Error al guardar lista 1", "rojo");
                }
            } else {
                if (btipocompra) {
                    mensaje.mensaje("Debes seleccionar un proveedor o bien seleccionar un proveedor autorizado para las siguientes claves: " + idClaves, "amarillo");
                } else {
                    mensaje.mensaje("Debes seleccionar un proveedor para las siguientes claves: " + idClaves, "amarillo");
                }
            }
        }
        if (fallos.getIdEstatus().getIdEstatus() == 42) {
            init();
        }
    }

    public String VerDetalleFallo() {
        util.setContextAtributte("fallos", this.fallos);
        return "detalleFallo.xhtml?faces-redirect=true";
    }

    public void limpiar() {
        RequestContext.getCurrentInstance().reset("formFallo");
        init();
    }

    public void obtenerFallos() {
        Fallos f = new Fallos();
        f.setNumeroFallo(numeroFalloB);
        f.setNumeroProcedimiento(numeroProcedimientoB);
        listaFallos = falloService.obtenerByFalloProcRcb(f);
    }

    public String verDetalleProc() {
        util.setContextAtributte("procedimientos", this.procedimientos);
        return "detalleProcedimiento.xhtml?faces-redirect=true";
    }

    public Fallos getFallos() {
        return fallos;
    }

    public void setFallos(Fallos fallos) {
        this.fallos = fallos;
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

    public List<ProcedimientoRcb> getListaProcRcb() {
        return listaProcRcb;
    }

    public void setListaProcRcb(List<ProcedimientoRcb> listaProcRcb) {
        this.listaProcRcb = listaProcRcb;
    }

    public String getNumeroProcedimiento() {
        return numeroProcedimiento;
    }

    public void setNumeroProcedimiento(String numeroProcedimiento) {
        this.numeroProcedimiento = numeroProcedimiento.toUpperCase();
    }

    public List<Procedimientos> getListaProc() {
        return listaProc;
    }

    public void setListaProc(List<Procedimientos> listaProc) {
        this.listaProc = listaProc;
    }

    public List<FalloPropuestaProcDTO> getListaFalloPropProc() {
        return listaFalloPropProc;
    }

    public void setListaFalloPropProc(List<FalloPropuestaProcDTO> listaFalloPropProc) {
        this.listaFalloPropProc = listaFalloPropProc;
    }

    public List<FalloPropuestaProcDTO> getListaFalloPropProcFilter() {
        return listaFalloPropProcFilter;
    }

    public void setListaFalloPropProcFilter(List<FalloPropuestaProcDTO> listaFalloPropProcFilter) {
        this.listaFalloPropProcFilter = listaFalloPropProcFilter;
    }

    public List<FalloPropuestaProcDTO> getListaFalloPropProcSelect() {
        return listaFalloPropProcSelect;
    }

    public void setListaFalloPropProcSelect(List<FalloPropuestaProcDTO> listaFalloPropProcSelect) {
        this.listaFalloPropProcSelect = listaFalloPropProcSelect;
    }

    public Integer getIdFalloPropuestaProcDTO() {
        return idFalloPropuestaProcDTO;
    }

    public void setIdFalloPropuestaProcDTO(Integer idFalloPropuestaProcDTO) {
        this.idFalloPropuestaProcDTO = idFalloPropuestaProcDTO;
    }

    public boolean isMensajeTabla() {
        return mensajeTabla;
    }

    public void setMensajeTabla(boolean mensajeTabla) {
        this.mensajeTabla = mensajeTabla;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public List<Estatus> getListaEstatusFallo() {
        return listaEstatusFallo;
    }

    public void setListaEstatusFallo(List<Estatus> listaEstatusFallo) {
        this.listaEstatusFallo = listaEstatusFallo;
    }

    public String getNumeroFalloB() {
        return numeroFalloB;
    }

    public void setNumeroFalloB(String numeroFalloB) {
        this.numeroFalloB = numeroFalloB.toUpperCase();
    }

    public List<Fallos> getListaFallos() {
        return listaFallos;
    }

    public void setListaFallos(List<Fallos> listaFallos) {
        this.listaFallos = listaFallos;
    }

    public String getNumeroProcedimientoB() {
        return numeroProcedimientoB;
    }

    public void setNumeroProcedimientoB(String numeroProcedimientoB) {
        this.numeroProcedimientoB = numeroProcedimientoB.toUpperCase();
    }

    public BitacoraTareaEstatus getBitacora() {
        return bitacora;
    }

    public void setBitacora(BitacoraTareaEstatus bitacora) {
        this.bitacora = bitacora;
    }

    public Procedimientos getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(Procedimientos procedimientos) {
        this.procedimientos = procedimientos;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public boolean isBtipocompra() {
        return btipocompra;
    }

    public void setBtipocompra(boolean btipocompra) {
        this.btipocompra = btipocompra;
    }

    public List<Proveedores> getListaProv() {
        return listaProv;
    }

    public void setListaProv(List<Proveedores> listaProv) {
        this.listaProv = listaProv;
    }

}
