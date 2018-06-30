package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ClasificacionImportanciaService;
import com.issste.sicabis.ejb.ln.ConsumoDiarioSiamService;
import com.issste.sicabis.ejb.ln.DpnInsumosService;
import com.issste.sicabis.ejb.ln.DpnService;
import com.issste.sicabis.ejb.ln.EstatusService;
import com.issste.sicabis.ejb.ln.InsumoDpnService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.OrdenSuministroService;
import com.issste.sicabis.ejb.ln.PeriodoMesService;
import com.issste.sicabis.ejb.ln.UnidadMedicaService;
import com.issste.sicabis.ejb.ln.UsuariosService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.ClasificacionImportancia;
import com.issste.sicabis.ejb.modelo.Dpn;
import com.issste.sicabis.ejb.modelo.DpnInsumoTmp;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.PeriodoMes;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.ejb.modelo.UsuariosTipoUsuarios;
import com.issste.sicabis.ejb.service.silodisa.modelo.CatUnidadMedica;
import com.issste.sicabis.ejb.service.silodisa.service.CatalogoUnidadesMedicasService;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciaPorClaveUmusService;
import com.issste.sicabis.ejb.siam.ln.VwExistenciasSICABISService;
import com.issste.sicabis.utils.ArchivosUtilidades;
import static com.issste.sicabis.utils.ArchivosUtilidades.PATHFILESPROPUESTAS;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

public class ConsultaDPNBean {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private UsuariosService usuariosService;

    @EJB
    private OrdenSuministroService ordenSuministroService;

    @EJB
    private InsumoDpnService insumoDpnService;

    @EJB
    private ConsumoDiarioSiamService consumoDiarioSiamService;

    @EJB
    private CatalogoUnidadesMedicasService catalogoUnidadesMedicasService;

    @EJB
    private EstatusService estatusService;

    @EJB
    private DpnInsumosService dpnInsumosService;

    @EJB
    private InsumosService insumosService;

    @EJB
    private ExistenciaPorClaveUmusService existenciaPorClaveUmusService;

    @EJB
    private VwExistenciasSICABISService vwExistenciasSICABISService;

    @EJB
    private UnidadMedicaService unidadMedicaService;

    @EJB
    private PeriodoMesService periodoMesService;

    @EJB
    private ClasificacionImportanciaService clasificacionImportanciaService;

    @EJB
    private DpnService dpnService;

    private PeriodoMes periodoMes;
    private Dpn dpn;
    private DpnInsumos dpnInsumos;
    private Estatus estatus;
    private Usuarios usuarios;

    private String clave;
    private String idClasificacionImportancia;
    private int anio;
    private int anioAux;
    private int anioActual;
    private int mes;
    private Integer idPeriodoMes;
    private boolean bactual;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer idUnidadMedica;
    private BigDecimal totalPiezas;
    private String claveUnidad;
    private boolean bprimera;
    private boolean bguarda;
    private boolean benvia;
    private boolean bautoriza;
    private String observaciones;
    private boolean busuarioAutoriza;
    private boolean busuarioAdmin;
    private boolean bobservacion;
    private String mesDpn;
    private UploadedFile uploadedFile;

    private List<ClasificacionImportancia> listaClasifImpor;
    private List<Dpn> listaDpnAux;
    private List<DpnInsumos> listaDpnInsumo;
    private List<UnidadesMedicas> listaUnidadesMedicas;
    private List<CatUnidadMedica> listaCatUnidadMedica;
    private List<Dpn> listaDpn;
    private List<UsuariosTipoUsuarios> listaUsuTipoUsu;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();
    private final Integer idTarea = 21;

    @PostConstruct
    public void init() {
        dpn = new Dpn();
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        if (usuarios.getUsuario().equals("admin")) {//cambiar por perfil 1
            busuarioAdmin = true;
        }
        listaUsuTipoUsu = usuariosService.obtenerTiposUsuariosByIdUsuario(usuarios.getIdUsuario());
        if (listaUsuTipoUsu != null) {
            for (UsuariosTipoUsuarios utu : listaUsuTipoUsu) {
                if (utu.getIdTipoUsuario().getNombre().equals("AUTORIZADPN")) {
                    busuarioAutoriza = true;
                    break;
                }
            }
        }
        listaClasifImpor = new ArrayList();
        listaDpnInsumo = new ArrayList();
        anio = util.getYear();
        mes = util.getMonth(new Date());
        ClasificacionImportancia ci = new ClasificacionImportancia();
        ci.setSigla("");
        ci.setDescripcion("");
        listaClasifImpor = clasificacionImportanciaService.obtenerByClasificacion(ci);
        this.inicializa();
    }

    public void inicializa() {
        anioAux = util.getYear();
        listaDpnAux = dpnService.getByAnio(anio);
        listaUnidadesMedicas = unidadMedicaService.unidadMedicaByActivo();
        listaCatUnidadMedica = catalogoUnidadesMedicasService.getAll();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
    }

    public void cambiaAnio() {
        if (anio == anioActual) {
            bactual = true;
        } else {
            bactual = false;
        }
        listaDpnAux = dpnService.getByAnio(anio);
    }

    public void llenaConsumo() {
        Date fecha = new Date(2017, 04, 19);
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("empece consumo");
        vwExistenciasSICABISService.cargaConsumoDiario3(formatoDeFecha.format(fecha));
        System.out.println("sali consumo");
    }

    public boolean obtienePeriodo() {
        periodoMes = periodoMesService.getPeriodoActivo(anio, mes);
        if (periodoMes != null) {
            if (periodoMes.getActivo() == 0) {
                fechaInicio = periodoMes.getFechaInicial();
                fechaFin = new Date();//periodoMes.getFechaFinal();
                mesDpn = util.getNameByMonth(periodoMes.getFechaFinal().getMonth() + 2);
            } else if (periodoMes.getActivo() == 1) {
                fechaInicio = periodoMes.getFechaFinal();
                fechaFin = new Date();
                periodoMes = periodoMesService.getByFechaCorte(new Date());
                mesDpn = util.getNameByMonth(fechaInicio.getMonth() + 3);
            }
            return true;
        } else {
            mensaje.mensaje("No hay periodos capturados debes dar de alta el periodo para el mes correspondiente", "amarillo");
            return false;
        }
    }

    public void cargaDpn(FileUploadEvent event) {
        boolean bnuevo = false;
        if (this.obtienePeriodo()) {
            totalPiezas = BigDecimal.ZERO;
            boolean bcontinua = true;
            uploadedFile = event.getFile();
            dpn = dpnService.getByIdPeriodoMes(periodoMes.getIdPeriodoMes());
            if (dpn == null) {
                dpn = new Dpn();
                estatus = estatusService.getRemisionEstatus(211);
                dpn.setActivo(0);
                dpn.setIdEstatus(estatus);
                dpn.setIdPeriodoMes(new PeriodoMes(periodoMes.getIdPeriodoMes()));
                dpn.setTotalPiezasDpn(BigDecimal.ZERO);
                dpn.setUsuarioAlta(usuarios.getUsuario());
                dpn.setFechaAlta(new Date());
                dpn.setFecha(new Date());
                dpn.setDpnMes(mesDpn + " (PREVIO)");
                dpnService.guardaActualiza(dpn);
                this.guardaBitacora("Guarda previo DPN:" + clave + "", 211, dpn.getIdDpn());
                bnuevo = true;
                this.leerArchivoDpn(bnuevo);
            } else {
                if (dpn.getIdEstatus().getIdEstatus() == 213) {
                    mensaje.mensaje("La DPN del mes ya se encuentra cargada y autorizada", "amarillo");
                } else {
                    //leer excel
                    this.leerArchivoDpn(bnuevo);
                }
            }
        }
    }

    public void leerArchivoDpn(boolean bnuevo) {
        String nombreArchivo = archivosUtilidades.generaNombreArchivo(uploadedFile, idTarea, 1);
        FileInputStream file = null;
        int fila = 1;
        int columna = 1;
        Double d = null;
        boolean bguarda = true;
        String claveInsumo = null;
        String claveUnidad = null;
        Integer piezasDpn = null;
        DpnInsumos di = null;
        try {
            archivosUtilidades.copyFilePropuestas(nombreArchivo, uploadedFile.getInputstream());
            file = new FileInputStream(new File(PATHFILESPROPUESTAS + nombreArchivo));
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row;
            y:
            while (rowIterator.hasNext()) {
                if (fila == 1) {
                    row = rowIterator.next();
                    fila++;
                }
                row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell celda;
                columna = 1;
                claveInsumo = null;
                claveUnidad = null;
                piezasDpn = null;
                bguarda = true;
                z:
                while (cellIterator.hasNext()) {
                    celda = cellIterator.next();
                    d = null;
                    switch (columna) {
                        case 1:
                            try {
                                if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                    claveInsumo = celda.getStringCellValue();
                                } else {
                                    bguarda = false;
                                    break z;
                                }
                            } catch (Exception e) {
                                bguarda = false;
                                break z;
                            }
                            break;
                        case 2:
                            try {
                                if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                    claveUnidad = celda.getStringCellValue();
                                } else {
                                    bguarda = false;
                                    break z;
                                }
                            } catch (Exception e) {
                                bguarda = false;
                                break z;
                            }
                            break;
                        case 9:
                            try {
                                if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                    d = celda.getNumericCellValue();
                                    piezasDpn = d.intValue();
                                } else if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                    piezasDpn = Integer.parseInt(celda.getStringCellValue());
                                } else {
                                    bguarda = false;
                                    break z;
                                }
                            } catch (Exception e) {
                                bguarda = false;
                                break z;
                            }
                            break;
                    }
                    columna++;
                }
                if (bguarda) {
                    if (bnuevo) {
                        this.asignaDpnInsumo(claveInsumo, claveUnidad, piezasDpn);
                    } else {
                        di = dpnInsumosService.getByIdDpnClaveInsumoClaveUnidad(dpn.getIdDpn(), claveInsumo, claveUnidad);
                        if (di != null) {
                            di.setPiezasDpn(piezasDpn);
                            if (!dpnInsumosService.guardaActualiza(di)) {
                                Logger.getLogger(ConsultaDPNBean.class.getName()).log(Level.INFO, "FALLO AL ACTUALIZAR: " + claveInsumo + " y " + claveUnidad);
                            }
                        } else {
                            this.asignaDpnInsumo(claveInsumo, claveUnidad, piezasDpn);
                        }
                    }
                }
                fila++;
            }
            mensaje.mensaje("Se cargo la información del archivo excel en la DPN " + dpn.getDpnMes(), "verde");
        } catch (IOException ex) {
            System.out.println("entre en error ");
            Logger.getLogger(ConsultaDPNBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void asignaDpnInsumo(String claveInsumo, String claveUnidad, Integer piezasDpn) {
        DpnInsumoTmp dit = new DpnInsumoTmp();
        dit.setIdDpn(dpn.getIdDpn());
        dit.setClaveInsumo(claveInsumo);
        dit.setClaveUnidad(claveUnidad);
        dit.setPiezasDpn(piezasDpn);
        Date fechaInicio2 = util.sumarRestarMesFecha(fechaFin, -2);
        Date fechaInicio3 = util.sumarRestarMesFecha(fechaFin, -3);
        dit.setFechaIni1(fechaInicio);
        dit.setFechaIni2(fechaInicio2);
        dit.setFechaIni3(fechaInicio3);
        dit.setFechaFin(fechaFin);
        if (!dpnInsumosService.guardaTmp(dit)) {
            Logger.getLogger(ConsultaDPNBean.class.getName()).log(Level.INFO, "FALLO AL GUARDAR: " + claveInsumo + " y " + claveUnidad);
        }
    }

    public void consultar() {
        if (this.obtienePeriodo()) {
            if (idPeriodoMes == null) {
                mensaje.mensaje("No hay ningún periodo capturado", "amarillo");
            } else if (idPeriodoMes == -2) {//actual
                totalPiezas = BigDecimal.ZERO;
                boolean bcontinua = true;
                dpn = dpnService.getDpnPrevio();
                if (dpn == null) {
                    dpn = new Dpn();
                    estatus = estatusService.getRemisionEstatus(211);
                    dpn.setActivo(0);
                    dpn.setIdEstatus(estatus);
                    dpn.setIdPeriodoMes(new PeriodoMes(periodoMes.getIdPeriodoMes()));
                    dpn.setTotalPiezasDpn(BigDecimal.ZERO);
                    dpn.setUsuarioAlta(usuarios.getUsuario());
                    dpn.setFechaAlta(new Date());
                    dpn.setFecha(new Date());
                    dpn.setDpnMes(mesDpn + " (PREVIO)");
                    dpnService.guardaActualiza(dpn);
                    this.guardaBitacora("Guarda previo DPN:" + clave + "", 211, dpn.getIdDpn());
                    bprimera = true;
                } else {
                    observaciones = dpn.getObservaciones();
                    //cambiar si es usuario master si podria en 212
                    if (dpn.getIdEstatus().getIdEstatus() == 212 || dpn.getIdEstatus().getIdEstatus() == 213) {
                        bcontinua = false;
                    }
                }
                if (bcontinua) {//bcontinua
                    System.out.println("llena insumos---->");
                    listaDpnInsumo = dpnInsumosService.llenaDpnInsumosActivos(dpn, clave, claveUnidad);
                    System.out.println("sali llena insumos---->");
                    if (listaDpnInsumo != null) {
                        Date fechaInicio2 = util.sumarRestarMesFecha(fechaFin, -2);
                        Date fechaInicio3 = util.sumarRestarMesFecha(fechaFin, -3);
                        System.out.println("procedure entre---->");
                        dpnInsumosService.actualizaDpnInsumosByProcedure(clave, claveUnidad, fechaInicio, fechaFin, fechaInicio2, fechaInicio3);
                        System.out.println("procedure sali---->");
                        listaDpnInsumo = dpnInsumosService.getListaAll(clave, claveUnidad);
                        if (listaDpnInsumo != null) {
                            Integer consumo = 0;
                            Integer existencias = 0;
                            double porcentajeAbajo = .9;
                            double porcentajeArriba = 1.1;
                            double dpnAbajo;
                            double dpnArriba;
                            DpnInsumos diAnt = null;
                            List<OrdenSuministro> listaOrdenSuministro = null;
                            boolean bvalida = true;
                            for (DpnInsumos di : listaDpnInsumo) {
                                bvalida = true;
                                System.out.println("di.getExistenciasCenadi()-->" + di.getExistenciasCenadi());
                                if (di.getExistenciasCenadi() == 0) {
                                    if (ordenSuministroService.getOrdenByClave(di.getClaveInsumo()) == null) {
                                        bvalida = false;
                                    }
                                }
                                if (bvalida) {
                                    if (di.getIdInsumoDpn().getIdTipoInsumoDpn() != null) {
//                                    diAnt = dpnInsumosService.getUltimaDpnByUnidadClave(clave, claveUnidad);
//                                    di.setPiezasDpnAnterior(0);
//                                    if (diAnt != null) {
//                                        di.setPiezasDpnAnterior(diAnt.getPiezasDpn());
//                                    }
                                        if (di.getIdInsumoDpn().getIdTipoInsumoDpn().getIdTipoInsumoDpn() != 1) {
                                            di.setPiezasPropuestasDpn(di.getPiezasDpnAnterior());
                                            di.setResultado("C/N/E");
                                        } else {
                                            if (di.getSalidasSiam() < di.getSalidasSiam3()) {
                                                consumo = di.getSalidasSiam3();
                                            } else {
                                                consumo = di.getSalidasSiam();
                                            }
                                            existencias = di.getExistenciasCenadi();
//                                    if (di.getExistenciasCenadi() <= di.getExistenciasSiam()) {
//                                        existencias = di.getExistenciasCenadi();
//                                    } else {
//                                        existencias = di.getExistenciasSiam();
//                                    }
                                            if (di.getCoberturas() < 30) {
                                                di.setPiezasPropuestasDpn(di.getPiezasDpnAnterior());
                                                di.setResultado("1. DPN anterior cobertura menor a 30 días");
                                            } else {
                                                if (di.getSalidasSiam3() == 0) {
                                                    di.setPiezasPropuestasDpn(0);
                                                    di.setResultado("2. DPN cero cobertura mayor a 30 días y consumo cero");
                                                } else {
                                                    di.setPiezasPropuestasDpn(consumo - existencias);
                                                    if (di.getPiezasPropuestasDpn() >= 0) {
                                                        di.setPiezasPropuestasDpn(di.getPiezasPropuestasDpn() + di.getPiezasDpnAnterior());
                                                        di.setPiezasPropuestasDpn(di.getPiezasPropuestasDpn() / 2);
                                                        dpnAbajo = di.getPiezasDpnAnterior() * porcentajeAbajo;
                                                        dpnArriba = di.getPiezasDpnAnterior() * porcentajeArriba;
                                                        if (di.getPiezasPropuestasDpn() < (int) dpnAbajo) {
                                                            di.setPiezasPropuestasDpn((int) dpnAbajo);
                                                            di.setResultado("3. DPN topada -10%");
                                                        } else if (di.getPiezasPropuestasDpn() > (int) dpnArriba) {
                                                            di.setPiezasPropuestasDpn((int) dpnArriba);
                                                            di.setResultado("4. DPN topada +10%");
                                                        } else {
                                                            di.setResultado("5. DPN nueva");
                                                        }
                                                    } else {
                                                        dpnAbajo = di.getPiezasDpnAnterior() * porcentajeAbajo;
                                                        di.setPiezasPropuestasDpn((int) dpnAbajo);
                                                        di.setResultado("6. DPN -10% consumo menor q existencias");
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        di.setResultado("7. SIN ASIGNAR");
                                    }
                                } else {
                                    di.setResultado("8. Existencia cero sin orden de suministro");
                                }
                                if (di.getPiezasDpn() == -1) {
                                    di.setPiezasDpn(di.getPiezasPropuestasDpn());
                                }
                                totalPiezas = totalPiezas.add(new BigDecimal(di.getPiezasDpn()));
                                di.setIdDpn(dpn);
                            }
                        }
                        bguarda = true;
                    } else {
                        mensaje.mensaje("No existe el registro en el sistema", "amarillo");
                    }
                } else {
                    mensaje.mensaje("Para el periodo actual ya existe una DPN enviada a autorización o autorizada", "amarillo");
                }
            } else if (idPeriodoMes == -1) {
                mensaje.mensaje("Debes seleccionar un periodo", "amarillo");
            } else {
                System.out.println("consulte esp---->");
                listaDpnInsumo = dpnInsumosService.getListaDpnInsumos(idPeriodoMes, clave, claveUnidad);
                System.out.println("sali consulta esp---->" + listaDpnInsumo.size());
                if (listaDpnInsumo != null) {
                    dpnInsumos = listaDpnInsumo.get(0);
                    dpn = dpnInsumos.getIdDpn();
                    observaciones = dpn.getObservaciones();
                    totalPiezas = dpn.getTotalPiezasDpn();
                    if (dpnInsumos.getPrevio() == 1) {
                        if (dpn.getIdEstatus().getIdEstatus() == 211 || dpn.getIdEstatus().getIdEstatus() == 214) {
                            bguarda = true;
                            benvia = true;
                        } else if (dpn.getIdEstatus().getIdEstatus() == 212) {
                            if (busuarioAutoriza || busuarioAdmin) {
                                bguarda = true;
                                bobservacion = true;
                                bautoriza = true;
                            }
                        }
                        System.out.println("final---->" + listaDpnInsumo.size());
                    } else {
                        bguarda = false;
                        benvia = false;
                    }
                }
            }
        }
//        } else {
//            mensaje.mensaje("No hay ningÃƒÂºn periodo capturado", "amarillo");
//        }
    }

    public void guardar() {
        if (!bprimera) {
            dpn.setFechaModificacion(new Date());
            dpn.setUsuarioModificacion(usuarios.getUsuario());
        }
        System.out.println("guarda---->" + listaDpnInsumo.size());
        for (DpnInsumos di : listaDpnInsumo) {
            if (dpnService.actualizaDpnInsumo(di)) {
                System.out.println("bien");
            }
        }
        //dpn.setDpnInsumosList(listaDpnInsumo);
        dpn.setObservaciones(observaciones);
        if (clave.equals("") && claveUnidad.equals("-1")) {
            dpn.setTotalPiezasDpn(totalPiezas);
        }
        System.out.println("entre antes de guardar----->" + dpn.getIdDpn());
        if (dpnService.actualizaDpn(dpn)) {
            this.guardaBitacora("Actualiza previo DPN:" + clave + "", 211, dpn.getIdDpn());
            mensaje.mensaje(mensaje.datos_guardados, "verde");
        }
        if (dpn.getIdEstatus().getIdEstatus() != 212) {
            benvia = true;
        }
    }

    public void enviarAutoriza() {
        estatus = estatusService.getRemisionEstatus(212);
        dpn.setIdEstatus(estatus);
        dpn.setObservaciones(observaciones);
        //dpn.setDpnInsumosList(listaDpnInsumo);
        dpn.setUsuarioModificacion(usuarios.getUsuario());
        dpn.setFechaModificacion(new Date());
        for (DpnInsumos di : listaDpnInsumo) {
            if (dpnService.actualizaDpnInsumo(di)) {
                System.out.println("bien");
            }
        }
        if (dpnService.actualizaDpn(dpn)) {
            this.guardaBitacora("Actualiza y envía a autorizar DPN", 212, dpn.getIdDpn());
            mensaje.mensaje(mensaje.datos_guardados, "verde");
            this.reload();
        }
    }

    public void observar() {
        estatus = estatusService.getRemisionEstatus(214);
        dpn.setObservaciones(observaciones);
        dpn.setIdEstatus(estatus);
        //dpn.setDpnInsumosList(listaDpnInsumo);
        dpn.setUsuarioModificacion(usuarios.getUsuario());
        dpn.setFechaModificacion(new Date());
        for (DpnInsumos di : listaDpnInsumo) {
            if (dpnService.actualizaDpnInsumo(di)) {
                System.out.println("bien");
            }
        }
        if (dpnService.actualizaDpn(dpn)) {
            this.guardaBitacora("Actualiza DPN observaciones", 214, dpn.getIdDpn());
            mensaje.mensaje(mensaje.datos_guardados, "verde");
            this.reload();
        }
    }

    public void autoriza() {
        System.out.println("entre autoriza------>");
        estatus = estatusService.getRemisionEstatus(213);
        dpn.setDpnMes(dpn.getDpnMes().replace(" (PREVIO)", ""));
        dpn.setObservaciones(observaciones);
        dpn.setIdEstatus(estatus);
        //dpn.setDpnInsumosList(listaDpnInsumo);
        dpn.setUsuarioModificacion(usuarios.getUsuario());
        dpn.setFechaModificacion(new Date());
        dpn.setActivo(1);
        boolean bandera = true;
        System.out.println("actualize ultima dpn------->");
        bandera = dpnService.actualizaUltimaDpn();
        System.out.println("sali actualize ultima dpn------->");
        for (DpnInsumos di : listaDpnInsumo) {
            if (dpnService.actualizaDpnInsumo(di)) {
                System.out.println("bien");
            }
        }
        System.out.println("antes actualizar------>");
        if (dpnService.actualizaDpn(dpn)) {
            System.out.println("pase actualizar, actualiza isnumos------->");
            bandera = dpnInsumosService.actualizaDpnInsumos();
            System.out.println("pase insumos------------>");
            listaDpnAux = dpnService.getByAnio(anio);
            mensaje.mensaje(mensaje.datos_guardados, "verde");
            this.guardaBitacora("Actualiza DPN autorizada", 213, dpn.getIdDpn());
            this.reload();

        }
    }

    public void guardaBitacora(String descripcion, Integer estatus, Integer id) {
        bitacoraTareaEstatus.setDescripcion(descripcion);
        bitacoraTareaEstatus.setFecha(new Date());
        bitacoraTareaEstatus.setIdEstatus(estatus);
        bitacoraTareaEstatus.setIdModulos(id);
        bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
        bitacoraTareaEstatus.setIdTareaId(idTarea);
        bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
    }

    public void reload() {
        bguarda = false;
        benvia = false;
        bobservacion = false;
        bautoriza = false;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getIdClasificacionImportancia() {
        return idClasificacionImportancia;
    }

    public void setIdClasificacionImportancia(String idClasificacionImportancia) {
        this.idClasificacionImportancia = idClasificacionImportancia;
    }

    public List<ClasificacionImportancia> getListaClasifImpor() {
        return listaClasifImpor;
    }

    public void setListaClasifImpor(List<ClasificacionImportancia> listaClasifImpor) {
        this.listaClasifImpor = listaClasifImpor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getAnioAux() {
        return anioAux;
    }

    public void setAnioAux(int anioAux) {
        this.anioAux = anioAux;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public List<Dpn> getListaDpnAux() {
        return listaDpnAux;
    }

    public void setListaDpnAux(List<Dpn> listaDpnAux) {
        this.listaDpnAux = listaDpnAux;
    }

    public Integer getIdPeriodoMes() {
        return idPeriodoMes;
    }

    public void setIdPeriodoMes(Integer idPeriodoMes) {
        this.idPeriodoMes = idPeriodoMes;
    }

    public List<DpnInsumos> getListaDpnInsumo() {
        return listaDpnInsumo;
    }

    public void setListaDpnInsumo(List<DpnInsumos> listaDpnInsumo) {
        this.listaDpnInsumo = listaDpnInsumo;
    }

    public boolean isBactual() {
        return bactual;
    }

    public void setBactual(boolean bactual) {
        this.bactual = bactual;
    }

    public Integer getIdUnidadMedica() {
        return idUnidadMedica;
    }

    public void setIdUnidadMedica(Integer idUnidadMedica) {
        this.idUnidadMedica = idUnidadMedica;
    }

    public List<UnidadesMedicas> getListaUnidadesMedicas() {
        return listaUnidadesMedicas;
    }

    public void setListaUnidadesMedicas(List<UnidadesMedicas> listaUnidadesMedicas) {
        this.listaUnidadesMedicas = listaUnidadesMedicas;
    }

    public List<CatUnidadMedica> getListaCatUnidadMedica() {
        return listaCatUnidadMedica;
    }

    public void setListaCatUnidadMedica(List<CatUnidadMedica> listaCatUnidadMedica) {
        this.listaCatUnidadMedica = listaCatUnidadMedica;
    }

    public String getClaveUnidad() {
        return claveUnidad;
    }

    public void setClaveUnidad(String claveUnidad) {
        this.claveUnidad = claveUnidad;
    }

    public List<Dpn> getListaDpn() {
        return listaDpn;
    }

    public void setListaDpn(List<Dpn> listaDpn) {
        this.listaDpn = listaDpn;
    }

    public boolean isBguarda() {
        return bguarda;
    }

    public void setBguarda(boolean bguarda) {
        this.bguarda = bguarda;
    }

    public boolean isBenvia() {
        return benvia;
    }

    public void setBenvia(boolean benvia) {
        this.benvia = benvia;
    }

    public boolean isBautoriza() {
        return bautoriza;
    }

    public void setBautoriza(boolean bautoriza) {
        this.bautoriza = bautoriza;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isBobservacion() {
        return bobservacion;
    }

    public void setBobservacion(boolean bobservacion) {
        this.bobservacion = bobservacion;
    }

    public BigDecimal getTotalPiezas() {
        return totalPiezas;
    }

    public void setTotalPiezas(BigDecimal totalPiezas) {
        this.totalPiezas = totalPiezas;
    }

}
