/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.ConcentradoDTO;
import com.issste.sicabis.ejb.DTO.ConcentradoPendienteDTO;
import com.issste.sicabis.ejb.DTO.ContratoOrdenDTO;
import com.issste.sicabis.ejb.DTO.ControlCalidadDTO;
import com.issste.sicabis.ejb.DTO.EncabezadosReportesDTO;
import com.issste.sicabis.ejb.DTO.EntradasCenadiDTO;
import com.issste.sicabis.ejb.DTO.EntradasDevolucionesCenadiDTO;
import com.issste.sicabis.ejb.DTO.EntradasDevolucionesCenadiEstatusDTO;
import com.issste.sicabis.ejb.DTO.PiezasPendientesAnualDTO;
import com.issste.sicabis.ejb.DTO.ReporteClavesDTO;
import com.issste.sicabis.ejb.ln.DpnInsumosService;
import com.issste.sicabis.ejb.service.silodisa.modelo.AlertasOperativas;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveCenadi;
import com.issste.sicabis.ejb.service.silodisa.service.AlertasOperativasService;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciasPorClaveCenadiService;
import com.issste.sicabis.ejb.utils.ExistenciaPorClaveCenadiUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
public class reportesDTOsDAOImplement implements reportesDTOsDAO {

    @EJB
    private DpnInsumosService dpnInsumosService;

    @EJB
    private ExistenciaPorClaveCenadiUtil existenciaPorClaveCenadiUtil;

    @EJB
    private ExistenciasPorClaveCenadiService existenciasPorClaveCenadiService;

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    public reportesDTOsDAOImplement() {
    }

    @Override
    public List<EncabezadosReportesDTO> obtenerEntradasCenadiXrangoFecha(Date fechaInicial, Date FechaFinal, String insumo, String proveedor, String tipoInsumo) {
        List<EntradasCenadiDTO> entradaCenadiList = new ArrayList<>();
        List<EncabezadosReportesDTO> listReport = new ArrayList<>();
        EntradasCenadiDTO entradasCenadiDTO;
        EncabezadosReportesDTO encabezadosReportesDTO;
        List<Object[]> objectList = null;
        String numero = "";
        Integer secuencial = 1;
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        String consul = "SELECT os.numeroOrden,os.importe, p.nombreProveedor, os.cantidadSuministrar,\n"
                + "r.fechaRemision, r.registroControl,r.idRemision,c.numeroContrato,ri.claveInsumo,ri.precioUnitario,r.folioRemision,p.numero\n"
                + "FROM Remisiones r \n"
                + "JOIN r.idDetalleOrdenSuministro dos \n"
                + "JOIN dos.idOrdenSuministro os \n"
                + "JOIN os.idContrato c \n"
                + "JOIN dos.idFalloProcedimientoRcb fpr\n"
                + "JOIN fpr.idProcedimientoRcb pr\n"
                + "JOIN pr.idRcbInsumos ri\n"
                + "JOIN ri.idInsumo i \n"
                + "JOIN fpr.idProveedor p \n"
                + "WHERE r.fechaRemision between '" + formatoDeFecha.format(fechaInicial) + "' and '" + formatoDeFecha.format(FechaFinal) + "'\n "
                + "AND r.idEstatus.idEstatus = 85 ";
        if (!proveedor.equals("-1")) {
            consul = consul + "and p.nombreProveedor = '" + proveedor + "' ";
        }
        if (!insumo.equals("-1")) {
            consul = consul + "and ri.claveInsumo = '" + insumo + "' ";
        }
        if (!tipoInsumo.equals("-1")) {
            consul = consul + "and i.idTipoInsumos.idTipoInsumos = " + tipoInsumo + " ";
        }
        try {
            Query query = em.createQuery(consul, EntradasCenadiDTO.class);
            objectList = query.getResultList();
            for (Object[] result : objectList) {
                entradasCenadiDTO = new EntradasCenadiDTO();
                entradasCenadiDTO.setNumeroOrden(String.valueOf(result[0]));
                entradasCenadiDTO.setImporte(new BigDecimal(Integer.parseInt(String.valueOf(result[1]))));
                if (String.valueOf(result[2]).equals("")) {
                    entradasCenadiDTO.setNombreProveedor("Sin nombre");
                } else {
                    entradasCenadiDTO.setNombreProveedor(String.valueOf(result[2]));
                }
                entradasCenadiDTO.setNumeroContrato(String.valueOf(result[7]));
                entradasCenadiDTO.setCantidadPiezas(Integer.parseInt(String.valueOf(result[3])));
                entradasCenadiDTO.setFechaRemision(new Date(String.valueOf(result[4])));
                if (String.valueOf(result[5]).equals("") || String.valueOf(result[5]) == null) {
                    entradasCenadiDTO.setRegistroControl(String.valueOf(result[7]));
                } else {
                    entradasCenadiDTO.setRegistroControl(String.valueOf(result[5]));
                }
                entradasCenadiDTO.setClaveInsumo(String.valueOf(result[8]));
                entradasCenadiDTO.setPrecioUnitario(new BigDecimal(Double.parseDouble(String.valueOf(result[9]))));
                if (String.valueOf(result[5]).contains("P")) {
                    entradasCenadiDTO.setNoFolioRemision("CANJE");
                } else if (String.valueOf(result[5]).contains("P")) {
                    entradasCenadiDTO.setNoFolioRemision("PERMUTA");
                } else {
                    entradasCenadiDTO.setNoFolioRemision(String.valueOf(result[10]));
                }
                if (secuencial.toString().length() == 1) {
                    numero = "000" + secuencial;
                } else if (secuencial.toString().length() == 2) {
                    numero = "00" + secuencial;
                } else if (secuencial.toString().length() == 3) {
                    numero = "0" + secuencial;
                } else if (secuencial.toString().length() == 4) {
                    numero = "" + secuencial;
                }
                String numeroCompleto = "";
                if (tipoInsumo.equals("1")) {
                    numeroCompleto = "7";
                    numero = numeroCompleto + numero;
                } else if (tipoInsumo.equals("2")) {
                    numeroCompleto = "9";
                    numero = numeroCompleto + numero;
                }
                entradasCenadiDTO.setIdRemision(Integer.parseInt(numero));
                secuencial++;
                entradaCenadiList.add(entradasCenadiDTO);
            }
            encabezadosReportesDTO = new EncabezadosReportesDTO();
            encabezadosReportesDTO.setFechaElaboracion(new Date());
            encabezadosReportesDTO.setFechaFinal(FechaFinal);
            encabezadosReportesDTO.setFechaInicial(fechaInicial);
            encabezadosReportesDTO.setListInfoECD(entradaCenadiList);
            listReport.add(encabezadosReportesDTO);

        } catch (Exception e) {
            Logger.getLogger(reportesDTOsDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return listReport;
    }

    @Override
    public List<EncabezadosReportesDTO> obtenerReporteClavesProceso(Date fechaInicial, String insumo, String proveedor) {
        List<EntradasCenadiDTO> entradaCenadiList = new ArrayList<>();
        List<EncabezadosReportesDTO> listReport = new ArrayList<>();
        int numero = 1;
        BigDecimal totalPiezas = BigDecimal.ZERO;
        //Sub Reporte
        Integer cantidadPiezasTotal = 0;
        List<String> proveedoresList = new ArrayList<>();
        List<String> aprobadasList = new ArrayList<>();
        List<String> devolucionList = new ArrayList<>();
        List<String> controlCalidadList = new ArrayList<>();
        List<String> clavesList = new ArrayList<>();
        //
        EntradasCenadiDTO entradasCenadiDTO;
        EncabezadosReportesDTO encabezadosReportesDTO;
        List<Object[]> objectList = null;
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (proveedor.equals("-1")) {
            proveedor = null;
        }
        if (insumo.equals("-1")) {
            insumo = null;
        }
        String consul = "select Unique r.registro_control, p.nombre_proveedor,c.numero_contrato,os.numero_orden,i.clave,i.descripcion,dos.fecha_entrega_inicial, "
                + "dos.fecha_entrega_final,ercn.tipo,os.cantidad_suministrar,e.nombre,ercn.dpn,ercn.existencia,((NVL(Sum(r.cantidad_recibida),0) + ercn.existencia)/ 30) as inventario "
                + " from remisiones r "
                + "join detalle_orden_suministro dos on dos.id_detalle_orden_suministro = r.id_detalle_orden_suministro "
                + "join orden_suministro os on os.id_orden_suministro = dos.id_orden_suministro "
                + "join contratos c on c.id_contrato = os.id_contrato "
                + "join fallo_procedimiento_rcb fpc on fpc.id_fallo_procedimiento_rcb = dos.id_fallo_procedimiento_rcb "
                + "join procedimiento_rcb pr on pr.id_procedimiento_rcb = fpc.id_procedimiento_rcb "
                + "join rcb_insumos ri on ri.id_rcb_insumos = pr.id_rcb_insumos "
                + "join insumos i on i.id_insumo = ri.id_insumo "
                + "join estatus e on e.id_estatus = r.id_estatus "
                + "join existencia_reserva_clave_cenadi ercn on ercn.clave = i.clave "
                + "join proveedores p on p.id_proveedor = fpc.id_proveedor "
                + " where r.activo = 1 "
                + "and r.fecha_remision between '" + formatoDeFecha.format(fechaInicial) + "' and '" + formatoDeFecha.format(fechaInicial) + "' "
                + "and r.id_estatus in (81,83,84,82,85,86,87) "
                + "group by r.registro_control, p.nombre_proveedor,c.numero_contrato,os.numero_orden,i.clave,i.descripcion,dos.fecha_entrega_inicial,dos.fecha_entrega_final,ercn.tipo,os.cantidad_suministrar,e.nombre,ercn.dpn,ercn.existencia ";
        try {

            Query query = em.createNativeQuery(consul);
            objectList = query.getResultList();
            for (Object[] result : objectList) {
                entradasCenadiDTO = new EntradasCenadiDTO();
                entradasCenadiDTO.setNumero(numero);
                entradasCenadiDTO.setRegistroControl(String.valueOf(result[0]));
                if (String.valueOf(result[1]).equals("")) {
                    entradasCenadiDTO.setNombreProveedor("Sin nombre");
                } else {
                    entradasCenadiDTO.setNombreProveedor(String.valueOf(result[1]));
                }
                if (!proveedoresList.contains(String.valueOf(result[1]))) {
                    proveedoresList.add(String.valueOf(result[1]));
                }
                entradasCenadiDTO.setNumeroContrato(String.valueOf(result[2]));
                entradasCenadiDTO.setNumeroOrden(String.valueOf(result[3]));
                entradasCenadiDTO.setClaveInsumo(String.valueOf(result[4]));
                if (!clavesList.contains(String.valueOf(result[4]))) {
                    clavesList.add(String.valueOf(result[4]));
                }
                entradasCenadiDTO.setDescripcion(String.valueOf(result[5]));
                entradasCenadiDTO.setFechaInicial(format.parse(String.valueOf(result[6])));
                entradasCenadiDTO.setFechaFinal(format.parse(String.valueOf(result[7])));
                entradasCenadiDTO.setTipoClave(String.valueOf(result[8]));
                entradasCenadiDTO.setCantidadPiezas(Integer.parseInt(String.valueOf(result[9])));
                cantidadPiezasTotal += entradasCenadiDTO.getCantidadPiezas();
                if (String.valueOf(result[10]).equals("CONTROL DE CALIDAD")) {
                    entradasCenadiDTO.setEstatus("APROBADO");
                    controlCalidadList.add("APROBADO");
                } else if (String.valueOf(result[10]).equals("DEVOLUCIÓN")) {
                    entradasCenadiDTO.setEstatus("DEVOLUCION");
                    devolucionList.add("DEVOLUCION");
                } else if (String.valueOf(result[10]).equals("APROBADO")) {
                    entradasCenadiDTO.setEstatus("APROBADO");
                    aprobadasList.add("APROBADO");
                } else if (String.valueOf(result[10]).equals("PROCESADA")) {
                    entradasCenadiDTO.setEstatus("APROBADO");
                    aprobadasList.add("APROBADO");
                }
                entradasCenadiDTO.setDpn(Integer.parseInt(String.valueOf(result[11])));
                entradasCenadiDTO.setExistecias(Integer.parseInt(String.valueOf(result[12])));
                entradasCenadiDTO.setDiasInventario(new BigDecimal(String.valueOf(result[13])));
                numero++;
                totalPiezas = totalPiezas.add(new BigDecimal(entradasCenadiDTO.getCantidadPiezas()));
                entradasCenadiDTO.setTotalPiezas(totalPiezas);
                entradaCenadiList.add(entradasCenadiDTO);
            }
            encabezadosReportesDTO = new EncabezadosReportesDTO();
            encabezadosReportesDTO.setFechaElaboracion(new Date());
            encabezadosReportesDTO.setFechaInicial(fechaInicial);
            encabezadosReportesDTO.setListInfoECD(entradaCenadiList);
            encabezadosReportesDTO.setProveedores(proveedoresList.size());
            encabezadosReportesDTO.setRemisiones(entradaCenadiList.size());
            encabezadosReportesDTO.setAprobadas(aprobadasList.size());
            encabezadosReportesDTO.setDevoluciones(devolucionList.size());
            encabezadosReportesDTO.setControlCalidad(controlCalidadList.size());
            encabezadosReportesDTO.setPiezas(cantidadPiezasTotal);
            encabezadosReportesDTO.setClaves(clavesList.size());
            listReport.add(encabezadosReportesDTO);

        } catch (Exception e) {
            Logger.getLogger(reportesDTOsDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return listReport;
    }

    @Override
    public List<EncabezadosReportesDTO> obtenerEntradasDevolucionesCenadiXrangoFecha(Date fechaInicial, Date FechaFinal, String insumo, String proveedor, Integer noReporte) {
        List<EntradasDevolucionesCenadiDTO> entradaDevolucionCenadiList = new ArrayList<>();
        List<EncabezadosReportesDTO> listReport = new ArrayList<>();
        EntradasDevolucionesCenadiDTO entradasDevolucionesCenadiDTO;
        EncabezadosReportesDTO encabezadosReportesDTO;
        List<Object[]> objectList = null;
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ContratoOrdenDTO codto = new ContratoOrdenDTO();
        Integer existenciaCenadi = 0;
        Integer dpn = 0;
        BigDecimal resultado = new BigDecimal(0);
        List<ExistenciaPorClaveCenadi> existenciasList = null;
        List<AlertasOperativas> alertasList = null;
        boolean bandera = true;
        //Totales
        List<String> totalProveedoresList = new ArrayList<>();
        BigDecimal cantidasPiezas = BigDecimal.ZERO;
        Integer remisiones = 0;
        Integer devoluciones = 0;
        List<String> clavesIngresadasList = new ArrayList<>();
        String consul = "select r.registro_control, pro.nombre_proveedor,r.fecha_remision,c.numero_contrato,ri.clave_insumo,ri.descripcion_insumo,r.cantidad_recibida \n"
                + ",dos.fecha_entrega_inicial,dos.fecha_entrega_final,r.id_estatus,p.numero_procedimiento,dos.cantidad_suministrar,NVL(ercc.existencia,0),NVL(ercc.dpn,0)"
                + " from remisiones r\n"
                + "join detalle_orden_suministro dos on dos.id_detalle_orden_suministro = r.id_detalle_orden_suministro\n"
                + "join orden_suministro os on os.id_orden_suministro = dos.id_orden_suministro\n"
                + "join contratos c on c.id_contrato = os.id_contrato\n"
                + "join fallo_procedimiento_rcb fpr on fpr.id_fallo_procedimiento_rcb = dos.id_fallo_procedimiento_rcb\n"
                + "join procedimiento_rcb pr on pr.id_procedimiento_rcb = fpr.id_procedimiento_rcb\n"
                + "join procedimientos p on p.id_procedimiento = pr.id_procedimiento\n"
                + "join proveedores pro on pro.id_proveedor = fpr.id_proveedor\n"
                + "join rcb_insumos ri on ri.id_rcb_insumos = pr.id_rcb_insumos\n"
                + "left join existencia_reserva_clave_cenadi ercc on ercc.clave = ri.clave_insumo\n"
                + "WHERE r.fecha_remision between '" + formatoDeFecha.format(fechaInicial) + "' and '" + formatoDeFecha.format(FechaFinal) + "' \n";
        if (!proveedor.equals("-1")) {
            consul = consul + " and pro.nombre_proveedor = '" + proveedor + "'";
        }
        if (!insumo.equals("-1")) {
            consul = consul + " and ri.clave_insumo = '" + insumo + "'";
        }
        if (noReporte == 1) {
            consul = consul + " and r.id_estatus in (85,83)";
        }
        if (noReporte == 2) {
            consul = consul + " and r.id_estatus in (83,84,85,86)";
        }
        try {
            Query query = em.createNativeQuery(consul);
            objectList = query.getResultList();
            for (Object[] result : objectList) {
                entradasDevolucionesCenadiDTO = new EntradasDevolucionesCenadiDTO();
                entradasDevolucionesCenadiDTO.setRegistroControl(String.valueOf(result[0]));
                if (String.valueOf(result[1]).equals("")) {
                    entradasDevolucionesCenadiDTO.setProveedor("Sin nombre");
                } else {
                    entradasDevolucionesCenadiDTO.setProveedor(String.valueOf(result[1]));
                    if (!totalProveedoresList.contains(entradasDevolucionesCenadiDTO.getProveedor())) {
                        totalProveedoresList.add(entradasDevolucionesCenadiDTO.getProveedor());
                    }
                }
                entradasDevolucionesCenadiDTO.setFecha(format.parse(String.valueOf(result[2])));
                entradasDevolucionesCenadiDTO.setContrato(String.valueOf(result[3]));
                entradasDevolucionesCenadiDTO.setClave(String.valueOf(result[4]));
                if (!clavesIngresadasList.contains(entradasDevolucionesCenadiDTO.getClave())) {
                    clavesIngresadasList.add(entradasDevolucionesCenadiDTO.getClave());
                }
                entradasDevolucionesCenadiDTO.setDescripcion(String.valueOf(result[5]));
                entradasDevolucionesCenadiDTO.setPiezasRecibidas(Integer.parseInt(String.valueOf(result[6])));
                entradasDevolucionesCenadiDTO.setPeriodoEntregaInicial(format.parse(String.valueOf(result[7])));
                entradasDevolucionesCenadiDTO.setPeriodoEntregaFinal(format.parse(String.valueOf(result[8])));
                if (String.valueOf(result[9]).equals("85") || (String.valueOf(result[9]).equals("83")) || (String.valueOf(result[9]).equals("86"))) {
                    entradasDevolucionesCenadiDTO.setEstatus("CLAVE INGRESADA");
                } else {
                    entradasDevolucionesCenadiDTO.setEstatus("DEVOLUCION");
                    devoluciones++;
                }
                entradasDevolucionesCenadiDTO.setProcedimiento(String.valueOf(result[10]));
                Integer cantidad;
                if (String.valueOf(result[9]).equals("85") || (String.valueOf(result[9]).equals("83")) || (String.valueOf(result[9]).equals("86"))) {
                    entradasDevolucionesCenadiDTO.setCantidad(Integer.parseInt(String.valueOf(result[6])));
                    cantidad = entradasDevolucionesCenadiDTO.getCantidad();
                } else {
                    entradasDevolucionesCenadiDTO.setCantidad(Integer.parseInt(String.valueOf(result[11])));
                    cantidad = entradasDevolucionesCenadiDTO.getCantidad();
                }
                if (noReporte == 1) {
                    Integer ingresoDia = obtenerIngresoClavesxDia(entradasDevolucionesCenadiDTO.getClave(), format.parse(String.valueOf(result[2])));
                    Integer pendientes = obtenerIngresoClavesPendientes(entradasDevolucionesCenadiDTO.getClave());
                    entradasDevolucionesCenadiDTO.setDPN(Integer.parseInt(String.valueOf(result[13])));
                    resultado = new BigDecimal(ingresoDia).add(new BigDecimal(Integer.parseInt(String.valueOf(result[13]))));
                    if (entradasDevolucionesCenadiDTO.getDPN() != 0) {
                        resultado = resultado.divide(new BigDecimal(Integer.parseInt(String.valueOf(result[13]))), RoundingMode.HALF_UP);
                    } else {
                        resultado = BigDecimal.ZERO;
                    }
                    entradasDevolucionesCenadiDTO.setCoberturaDeEntrega(resultado.multiply(new BigDecimal(30)));
                    cantidasPiezas = cantidasPiezas.add(new BigDecimal(entradasDevolucionesCenadiDTO.getCantidad()));
                    entradasDevolucionesCenadiDTO.setCantidadPiezas(cantidasPiezas);
                } else {
                    cantidasPiezas = cantidasPiezas.add(new BigDecimal(entradasDevolucionesCenadiDTO.getCantidad()));
                    entradasDevolucionesCenadiDTO.setCoberturaDeEntrega(resultado);
                    entradasDevolucionesCenadiDTO.setCantidadPiezas(cantidasPiezas);
                    entradasDevolucionesCenadiDTO.setTotalProveedores(totalProveedoresList.size());
                    entradasDevolucionesCenadiDTO.setClavesIngresadas(clavesIngresadasList.size());
                    entradasDevolucionesCenadiDTO.setRemisionesGeneradas(remisiones);
                    entradasDevolucionesCenadiDTO.setDevoluciones(devoluciones);
                    entradasDevolucionesCenadiDTO.setRemisionesAjustadas(0);
                    remisiones++;
                }

                entradaDevolucionCenadiList.add(entradasDevolucionesCenadiDTO);
            }
            encabezadosReportesDTO = new EncabezadosReportesDTO();
            encabezadosReportesDTO.setFechaElaboracion(new Date());
            encabezadosReportesDTO.setFechaFinal(FechaFinal);
            encabezadosReportesDTO.setFechaInicial(fechaInicial);
            encabezadosReportesDTO.setListInfoEDCenadi(entradaDevolucionCenadiList);
            listReport.add(encabezadosReportesDTO);

        } catch (Exception e) {
            Logger.getLogger(reportesDTOsDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return listReport;
    }

    @Override
    public List<EncabezadosReportesDTO> obtenerEntradasDevolucionesCenadiXrangoFechayEstatus(Date fechaInicial, Date fechaFinal, String insumo) {
        List<EntradasDevolucionesCenadiEstatusDTO> listPPAlistInfoEDCenadixEstatus = new ArrayList<>();
        EntradasDevolucionesCenadiEstatusDTO entradasDevolucionesCenadiEstatusDTO;
        List<EncabezadosReportesDTO> listReport = new ArrayList<>();
        EntradasDevolucionesCenadiDTO entradasDevolucionesCenadiDTO;
        EncabezadosReportesDTO encabezadosReportesDTO;
        List<Object[]> objectList = null;
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<ExistenciaPorClaveCenadi> existenciasList = null;
        List<AlertasOperativas> alertasList = null;
        String consul = "Select r.registro_control, r.folio_remision,r.fecha_remision,ri.descripcion_insumo,\n"
                + "c.numero_contrato,p.numero_procedimiento,ri.clave_insumo,r.cantidad_recibida,dos.fecha_entrega_inicial,dos.fecha_entrega_final,r.id_estatus,pro.nombre_proveedor\n"
                + "from remisiones r\n"
                + "join detalle_orden_suministro dos on dos.id_detalle_orden_suministro = r.id_detalle_orden_suministro\n"
                + "join fallo_procedimiento_rcb fpr on fpr.id_fallo_procedimiento_rcb = dos.id_fallo_procedimiento_rcb\n"
                + "join proveedores pro on pro.id_proveedor = fpr.id_proveedor\n"
                + "join procedimiento_rcb pr on pr.id_procedimiento_rcb = fpr.id_procedimiento_rcb\n"
                + "join procedimientos p on p.id_procedimiento = pr.id_procedimiento\n"
                + "join rcb_insumos ri on ri.id_rcb_insumos = pr.id_rcb_insumos\n"
                + "join orden_suministro os on os.id_orden_suministro = dos.id_orden_suministro\n"
                + "join contratos c on c.id_contrato = os.id_contrato\n"
                + "where r.fecha_remision between '" + formatoDeFecha.format(fechaInicial) + "' and '" + formatoDeFecha.format(fechaFinal) + "'\n";
        if (!insumo.equals("-1")) {
            consul = consul + " and ri.clave_insumo = '" + insumo + "'";
        } else {
            consul = consul + " order by r.id_estatus";
        }
        try {
            Query query = em.createNativeQuery(consul);
            objectList = query.getResultList();
            for (Object[] result : objectList) {
                entradasDevolucionesCenadiEstatusDTO = new EntradasDevolucionesCenadiEstatusDTO();
                entradasDevolucionesCenadiEstatusDTO.setRegistroControl(String.valueOf(result[0]));
                if (!String.valueOf(result[1]).equals("null")) {
                    entradasDevolucionesCenadiEstatusDTO.setRemision(String.valueOf(result[1]));
                } else {
                    entradasDevolucionesCenadiEstatusDTO.setRemision("Sin folio.");
                }
                entradasDevolucionesCenadiEstatusDTO.setFechaRemision(format.parse(String.valueOf(result[2])));
                entradasDevolucionesCenadiEstatusDTO.setFechaCalidad(entradasDevolucionesCenadiEstatusDTO.getFechaRemision());
                entradasDevolucionesCenadiEstatusDTO.setDescripcion(String.valueOf(result[3]));
                entradasDevolucionesCenadiEstatusDTO.setContrato(String.valueOf(result[4]));
                entradasDevolucionesCenadiEstatusDTO.setProcedimiento(String.valueOf(result[5]));
                entradasDevolucionesCenadiEstatusDTO.setClave(String.valueOf(result[6]));
                entradasDevolucionesCenadiEstatusDTO.setCantidad(Integer.parseInt(String.valueOf(result[7])));
                entradasDevolucionesCenadiEstatusDTO.setFechaEntrega(format.parse(String.valueOf(result[8])));
                entradasDevolucionesCenadiEstatusDTO.setFechaMaxima(format.parse(String.valueOf(result[9])));
                if (!String.valueOf(result[11]).equals("")) {
                    entradasDevolucionesCenadiEstatusDTO.setProveedor(String.valueOf(result[11]));
                } else {
                    entradasDevolucionesCenadiEstatusDTO.setProveedor("Sin proveedor.");
                }
                if (String.valueOf(result[10]).equals("85") || String.valueOf(result[10]).equals("86")) {
                    entradasDevolucionesCenadiEstatusDTO.setEstatus("P");
                } else if (String.valueOf(result[10]).equals("84")) {
                    entradasDevolucionesCenadiEstatusDTO.setEstatus("D");
                } else if (String.valueOf(result[10]).equals("82")) {
                    entradasDevolucionesCenadiEstatusDTO.setEstatus("C");
                } else if (String.valueOf(result[10]).equals("83")) {
                    entradasDevolucionesCenadiEstatusDTO.setEstatus("A");
                } else {
                    entradasDevolucionesCenadiEstatusDTO.setEstatus("PENDIENTE");
                }
                listPPAlistInfoEDCenadixEstatus.add(entradasDevolucionesCenadiEstatusDTO);
            }
            encabezadosReportesDTO = new EncabezadosReportesDTO();
            encabezadosReportesDTO.setFechaElaboracion(new Date());
            encabezadosReportesDTO.setFechaInicial(fechaInicial);
            encabezadosReportesDTO.setFechaFinal(fechaFinal);
            encabezadosReportesDTO.setListInfoEDCenadixEstatus(listPPAlistInfoEDCenadixEstatus);
            listReport.add(encabezadosReportesDTO);

        } catch (Exception e) {
            Logger.getLogger(reportesDTOsDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return listReport;
    }

    @Override
    public List<EncabezadosReportesDTO> obtenerPiezasPendientesXanio(Integer anio) {
        List<PiezasPendientesAnualDTO> listPPA = new ArrayList<>();
        PiezasPendientesAnualDTO piezasPendientesAnualDTO;
        List<EncabezadosReportesDTO> listReport = new ArrayList<>();
        EntradasDevolucionesCenadiDTO entradasDevolucionesCenadiDTO;
        EncabezadosReportesDTO encabezadosReportesDTO;
        List<Object[]> objectList = null;
        BigDecimal totalPiezas = BigDecimal.ZERO;
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<ExistenciaPorClaveCenadi> existenciasList = null;
        List<AlertasOperativas> alertasList = null;
        String consul = "Select c.numero_contrato,c.numero_convenio,os.numero_orden ,ri.clave_insumo,pro.nombre_proveedor, dos.fecha_entrega_inicial,dos.fecha_entrega_final,dos.cantidad_suministrar "
                + "from detalle_orden_suministro dos\n"
                + "join fallo_procedimiento_rcb fpr on fpr.id_fallo_procedimiento_rcb = dos.id_fallo_procedimiento_rcb\n"
                + "join procedimiento_rcb pr on pr.id_procedimiento_rcb = fpr.id_procedimiento_rcb\n"
                + "join rcb_insumos ri on ri.id_rcb_insumos = pr.id_rcb_insumos\n"
                + "join proveedores pro on pro.id_proveedor = fpr.id_proveedor\n"
                + "join orden_suministro os on os.id_orden_suministro = dos.id_orden_suministro\n"
                + "join contratos c on c.id_contrato = os.id_contrato\n"
                + " where not exists (select r.id_detalle_orden_suministro from remisiones r where r.id_detalle_orden_suministro = dos.id_detalle_orden_suministro)\n"
                + "and dos.fecha_entrega_inicial between '" + anio + "/01/01' and '" + anio + "/12/31' or  dos.fecha_entrega_final between '" + anio + "/01/01' and '" + anio + "/12/31' \n";
        try {
            Query query = em.createNativeQuery(consul);
            objectList = query.getResultList();
            for (Object[] result : objectList) {
                piezasPendientesAnualDTO = new PiezasPendientesAnualDTO();
                piezasPendientesAnualDTO.setContrato(String.valueOf(result[0]));
                if (String.valueOf(result[1]).equals("null")) {
                    piezasPendientesAnualDTO.setConvenio("Sin convenio");
                } else {
                    piezasPendientesAnualDTO.setConvenio(String.valueOf(result[1]));
                }
                piezasPendientesAnualDTO.setOrdenSuministro(String.valueOf(result[2]));
                piezasPendientesAnualDTO.setClave(String.valueOf(result[3]));
                if (String.valueOf(result[4]).equals("")) {
                    piezasPendientesAnualDTO.setProveedor("Sin nombre");
                } else {
                    piezasPendientesAnualDTO.setProveedor(String.valueOf(result[4]));
                }
                piezasPendientesAnualDTO.setPeriodoEntregaInicial(format.parse(String.valueOf(result[5])));
                piezasPendientesAnualDTO.setPeriodoEntregaFinal(format.parse(String.valueOf(result[6])));
                piezasPendientesAnualDTO.setPendiente(new BigDecimal(Integer.parseInt(String.valueOf(result[7]))));
                totalPiezas = totalPiezas.add(piezasPendientesAnualDTO.getPendiente());
                piezasPendientesAnualDTO.setTotalPiezas(totalPiezas);
                listPPA.add(piezasPendientesAnualDTO);
            }
            encabezadosReportesDTO = new EncabezadosReportesDTO();
            encabezadosReportesDTO.setFechaElaboracion(new Date());
            encabezadosReportesDTO.setAnio(anio);
            encabezadosReportesDTO.setListInfoPPAD(listPPA);
            listReport.add(encabezadosReportesDTO);

        } catch (Exception e) {
            Logger.getLogger(reportesDTOsDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return listReport;
    }

    @Override
    public List<EncabezadosReportesDTO> obtenerClaves(Integer noReporte, Date fechaInicial) {
        List<ReporteClavesDTO> clavesList = new ArrayList<>();
        List<ReporteClavesDTO> clavesTempList = new ArrayList<>();
        List<EncabezadosReportesDTO> listReport = new ArrayList<>();
        ReporteClavesDTO reporteClavesDTO;
        EncabezadosReportesDTO encabezadosReportesDTO;
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.setTime(fechaInicial);
        List<Object[]> objectList = null;
        List<String> listExistenciaCenadi = new ArrayList<>();
        Integer numero = 1;
        Integer dpn = 0;
        BigDecimal resultado = new BigDecimal(0);
        List<ExistenciaPorClaveCenadi> existenciasList = null;
        List<AlertasOperativas> alertasList = null;
        boolean bandera = true;
        String consul = "select i.clave,i.descripcion, NVL(Sum(ercc.dpn),0) as DPN,ercc.tipo,ercc.existencia,dos.fecha_entrega_inicial "
                + ",dos.fecha_entrega_final,(dos.cantidad_suministrar-dos.cantidad_suministrada) as pendiente,p.nombre_proveedor\n"
                + " from existencia_reserva_clave_cenadi ercc \n"
                + "left join insumos i on i.clave = ercc.clave left join rcb_insumos ri on (ri.clave_insumo = i.clave)\n"
                + "left join procedimiento_rcb pr on (pr.id_rcb_insumos = ri.id_rcb_insumos)\n"
                + "left join fallo_procedimiento_rcb fpr on (fpr.id_procedimiento_rcb = pr.id_procedimiento_rcb)\n"
                + "left join proveedores p on (p.id_proveedor = fpr.id_proveedor)\n"
                + "left join detalle_orden_suministro dos on (dos.id_fallo_procedimiento_rcb = fpr.id_fallo_procedimiento_rcb)\n"
                + "where dos.fecha_entrega_inicial between '" + c.get(Calendar.YEAR) + "/01/01' and '" + formatoDeFecha.format(fechaInicial) + "' \n"
                + "group by i.clave,i.id_tipo_insumos,dos.fecha_entrega_inicial,i.descripcion,ercc.tipo,ercc.existencia,dos.fecha_entrega_final,pendiente,p.nombre_proveedor\n"
                + "order by dos.fecha_entrega_inicial,ercc.tipo";
        try {
            Query query = em.createNativeQuery(consul);
            objectList = query.getResultList();
            for (Object[] result : objectList) {
                reporteClavesDTO = new ReporteClavesDTO();
                reporteClavesDTO.setClave(String.valueOf(result[0]));
                reporteClavesDTO.setDescripcion(String.valueOf(result[1]));
                reporteClavesDTO.setFechaInicial(format.parse(String.valueOf(result[5])));
                reporteClavesDTO.setFechaFinal(format.parse(String.valueOf(result[6])));
                if (noReporte == 5) {
                    reporteClavesDTO.setIngresoPendiente(new BigDecimal(String.valueOf(result[7])));
                }
                reporteClavesDTO.setProveedor(String.valueOf(result[8]));
                dpn = Integer.parseInt(String.valueOf(result[2]));
                reporteClavesDTO.setDPN(new BigDecimal(dpn));
                if (noReporte != 4 && noReporte != 5) {
//                    if (bandera) {
//                        existenciaPorClaveCenadiUtil.validaExistenciaClaveCenadi(reporteClavesDTO.getClave());
//                    }
                    reporteClavesDTO.setTipoClave(String.valueOf(result[3]));
                    reporteClavesDTO.setDisponibleCenadi(Integer.parseInt(String.valueOf(result[4])));
                    reporteClavesDTO.setIngresoDia(new BigDecimal(obtenerIngresoClavesxDia(reporteClavesDTO.getClave(), format.parse(String.valueOf(result[5])))));
                    reporteClavesDTO.setIngresi(reporteClavesDTO.getIngresoDia().intValueExact());
                    reporteClavesDTO.setIngresoPendiente(new BigDecimal(obtenerIngresoClavesPendientes(reporteClavesDTO.getClave())));
                    resultado = reporteClavesDTO.getIngresoDia().add(new BigDecimal(reporteClavesDTO.getDisponibleCenadi()));
                    if (resultado.intValueExact() == 0 || reporteClavesDTO.getDPN().intValueExact() == 0) {
                        resultado = new BigDecimal(0);
                    } else {
                        resultado = resultado.divide(reporteClavesDTO.getDPN(), RoundingMode.HALF_UP);
                    }
                    reporteClavesDTO.setDiasInventario(resultado.multiply(new BigDecimal(30)));
                    reporteClavesDTO.setDiasInventarioConsiderado(resultado.multiply(new BigDecimal(30)));
                    reporteClavesDTO.setCoberturaDisponibleEntregaCenadi(resultado.multiply(new BigDecimal(30)));
                    bandera = false;
                }
                clavesTempList.add(reporteClavesDTO);
            }
            if (noReporte == 3) {
                for (ReporteClavesDTO iterator : clavesTempList) {
                    if (iterator.getCoberturaDisponibleEntregaCenadi().intValueExact() <= 75) {
                        iterator.setNumero(numero);
                        numero++;
                        clavesList.add(iterator);
                    } else {
                        System.out.println(iterator.getCoberturaDisponibleEntregaCenadi() + "clave:" + iterator.getClave());
                    }
                }
            }
            if (noReporte == 1) {
                for (ReporteClavesDTO iterator : clavesTempList) {
                    if (iterator.getDisponibleCenadi() == 0) {
                        iterator.setNumero(numero);
                        numero++;
                        clavesList.add(iterator);
                    }
                }
            }

            if (noReporte == 2) {
                for (ReporteClavesDTO iterator : clavesTempList) {
                    if (iterator.getCoberturaDisponibleEntregaCenadi().intValueExact() <= 30) {
                        clavesList.add(iterator);
                        iterator.setNumero(numero);
                        numero++;
                    } else {
                        System.out.println(iterator.getCoberturaDisponibleEntregaCenadi() + "clave:" + iterator.getClave());
                    }
                }
            }
            if (noReporte == 5) {
                clavesList = clavesTempList;
            }
            encabezadosReportesDTO = new EncabezadosReportesDTO();
            encabezadosReportesDTO.setFechaElaboracion(new Date());
            encabezadosReportesDTO.setFechaInicial(fechaInicial);
            encabezadosReportesDTO.setListInfoClaves(clavesList);
            listReport.add(encabezadosReportesDTO);

        } catch (Exception e) {
            Logger.getLogger(reportesDTOsDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return listReport;
    }

    @Override
    public List<String> obtenerExistenciaCenadi(String insumo) {
        List<String> listExistencias = new ArrayList<>();
        List<Object[]> objectList = null;
        String consul = "select  i.clave,NVL(exi.tipo,'Sin tipo') as tipoClave ,NVL(Sum(Cast(exi.existencia as Integer)),0) as existe\n"
                + "from insumos i \n"
                + "left join existencia_por_clave_cenadi exi on (i.clave = exi.clave)\n"
                + "where i.clave = '" + insumo + "'\n"
                + "group by i.clave,i.descripcion,exi.tipo";
        try {
            Query query = em.createNativeQuery(consul);
            objectList = query.getResultList();
            for (Object[] result : objectList) {
                listExistencias.add(String.valueOf(result[0]));
                listExistencias.add(String.valueOf(result[1]));
                listExistencias.add(String.valueOf(result[2]));
            }

        } catch (Exception e) {
            Logger.getLogger(reportesDTOsDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return listExistencias;
    }

    @Override
    public Integer obtenerIngresoClavesPendientes(String insumo) {
        List<Object[]> objectList = null;
        Integer pendientes = 0;
        String consul = "Select NVL(Sum(dos.cantidad_suministrar),0)\n"
                + "from\n"
                + " detalle_orden_suministro dos\n"
                + "join fallo_procedimiento_rcb fpr on fpr.id_fallo_procedimiento_rcb = dos.id_fallo_procedimiento_rcb\n"
                + "join procedimiento_rcb pr on pr.id_procedimiento_rcb = fpr.id_procedimiento_rcb\n"
                + "join procedimientos p on p.id_procedimiento = pr.id_procedimiento\n"
                + "join rcb_insumos ri on ri.id_rcb_insumos = pr.id_rcb_insumos\n"
                + "where ri.clave_insumo = '" + insumo + "' \n"
                + "and not exists (select r.id_detalle_orden_suministro from remisiones r where r.id_detalle_orden_suministro = dos.id_detalle_orden_suministro and r.id_estatus != 84)";
        try {
            Query query = em.createNativeQuery(consul);
            objectList = query.getResultList();
            if (new BigDecimal(Integer.parseInt(String.valueOf(objectList.get(0)))).toString().equals("null")) {
                pendientes = 0;
            }
            pendientes = Integer.parseInt(String.valueOf(objectList.get(0)));

        } catch (Exception e) {
            Logger.getLogger(reportesDTOsDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return pendientes;
    }

    @Override
    public Integer obtenerIngresoClavesxDia(String insumo, Date fecha) {
        List<Object[]> objectList = null;
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        Integer ingreso = 0;
        String consul = "Select NVL(Sum(r.cantidad_recibida),0) as suma\n"
                + "from remisiones r\n"
                + "join detalle_orden_suministro dos on dos.id_detalle_orden_suministro = r.id_detalle_orden_suministro\n"
                + "join fallo_procedimiento_rcb fpr on fpr.id_fallo_procedimiento_rcb = dos.id_fallo_procedimiento_rcb\n"
                + "join procedimiento_rcb pr on pr.id_procedimiento_rcb = fpr.id_procedimiento_rcb\n"
                + "join procedimientos p on p.id_procedimiento = pr.id_procedimiento\n"
                + "join rcb_insumos ri on ri.id_rcb_insumos = pr.id_rcb_insumos\n"
                + "where ri.clave_insumo = '" + insumo + "' and r.fecha_remision = '" + formatoDeFecha.format(fecha) + "'";
        try {
            Query query = em.createNativeQuery(consul);
            objectList = query.getResultList();
            ingreso = Integer.parseInt(String.valueOf(objectList.get(0)));

        } catch (Exception e) {
            Logger.getLogger(reportesDTOsDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return ingreso;
    }

    @Override
    public List<EncabezadosReportesDTO> obtenerRemisionesControlCalidadxClave(Date fechaInicial, Date fechaFinal, String insumo, Integer estatus, String lote, Integer idFabricante) {
        List<ControlCalidadDTO> listControlCalidadDTO = new ArrayList<>();
        ControlCalidadDTO controlCalidadDTO;
        List<EncabezadosReportesDTO> listReport = new ArrayList<>();
        EncabezadosReportesDTO encabezadosReportesDTO;
        List<Object[]> objectList = null;
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String consul = "SELECT r.registro_control,ri.clave_insumo,r.cantidad_recibida_control_calidad,r.inspeccion,r.nivel_calidad_aceptable,"
                + "l.lote,codigo_barras_lote,l.fecha_fabricacion,l.fecha_caducidad,l.cantidad_lote,r.fecha_remision,r.id_estatus,ri.descripcion_insumo"
                + ",dos.cantidad_suministrar,up.descripcion,ri.precio_unitario,fpr.importe,f.nombre,ri.id_rcb_insumos,os.numero_orden,(select numero_procedimiento from procedimientos where id_procedimiento = pr.id_procedimiento) as numero_procedimiento,c.numero_contrato,"
                + "(select nombre_proveedor from proveedores where id_proveedor = fpr.id_proveedor) as nombre,r.folio_remision "
                + "from lote l "
                + "join remisiones r on r.id_remision = l.id_remision "
                + "join detalle_orden_suministro dos on dos.id_detalle_orden_suministro = r.id_detalle_orden_suministro "
                + "join orden_suministro os on os.id_orden_suministro = dos.id_orden_suministro "
                + "join contratos c on c.id_contrato = os.id_contrato "
                + "join fallo_procedimiento_rcb fpr on fpr.id_fallo_procedimiento_rcb = dos.id_fallo_procedimiento_rcb "
                + "join procedimiento_rcb pr on pr.id_procedimiento_rcb = fpr.id_procedimiento_rcb "
                + "join rcb_insumos ri on ri.id_rcb_insumos = pr.id_rcb_insumos "
                + "join unidad_pieza up on up.id_unidad_pieza = ri.id_unidad_pieza "
                + "join fabricante f on f.id_fabricante = r.id_fabricante "
                + "where r.fecha_remision between '" + formatoDeFecha.format(fechaInicial) + "' and '" + formatoDeFecha.format(fechaFinal) + "' ";
        if (!insumo.equals("-1")) {
            consul = consul + "and ri.clave_insumo = '" + insumo + "' ";
        }
        if (!estatus.toString().equals("-1") && !estatus.toString().equals("81")) {
            consul = consul + "and r.id_estatus = " + estatus + " ";
        }
        if (estatus.toString().equals("81")) {
            consul = consul + "and r.id_estatus in (82,83,84) ";
        }
        if (!lote.equals("")) {
            consul = consul + "and l.lote = '" + lote + "' ";
        }
        if (!idFabricante.toString().equals("-1")) {
            consul = consul + "and r.id_fabricante = " + idFabricante + " ";
        }
        try {
            Query query = em.createNativeQuery(consul);
            objectList = query.getResultList();
            for (Object[] result : objectList) {
                controlCalidadDTO = new ControlCalidadDTO();
                controlCalidadDTO.setRegistroControl(String.valueOf(result[0]));
                controlCalidadDTO.setClaveInsumo(String.valueOf(result[1]));
                if (!String.valueOf(result[2]).equals("null")) {
                    controlCalidadDTO.setCantidadRecibidaControlCalidad(Integer.parseInt(String.valueOf(result[2])));
                } else {
                    controlCalidadDTO.setCantidadRecibidaControlCalidad(0);
                }
                if (!String.valueOf(result[3]).equals("null")) {
                    controlCalidadDTO.setInspeccion(String.valueOf(result[3]));
                } else {
                    controlCalidadDTO.setInspeccion("Sin inspección");
                }
                if (!String.valueOf(result[4]).equals("null")) {
                    controlCalidadDTO.setNivelCalidadAceptable(new BigDecimal(Double.parseDouble(String.valueOf(result[4]))));
                } else {
                    controlCalidadDTO.setNivelCalidadAceptable(BigDecimal.ZERO);
                }
                if (!String.valueOf(result[5]).equals("null")) {
                    controlCalidadDTO.setLote(String.valueOf(result[5]));
                } else {
                    controlCalidadDTO.setLote("Sin lote");
                }
                controlCalidadDTO.setCodigoBarrasLote(String.valueOf(result[6]));

                controlCalidadDTO.setFechaFabricacion(format.parse(String.valueOf(result[7])));
                controlCalidadDTO.setFechaCaducidad(format.parse(String.valueOf(result[8])));
                controlCalidadDTO.setCantidadLote(Integer.parseInt(String.valueOf(result[9])));
                String Estatus = "";
                if (String.valueOf(result[11]).equals("82")) {
                    Estatus = "Control de Calidad";
                } else if (String.valueOf(result[11]).equals("83")) {
                    Estatus = "Aprobado";
                } else if (String.valueOf(result[11]).equals("84")) {
                    Estatus = "Devolución";
                }
                controlCalidadDTO.setEstatus(Estatus);
                controlCalidadDTO.setDescripcion(String.valueOf(result[12]));
                controlCalidadDTO.setCantidad(new BigDecimal(String.valueOf(result[13])));
                controlCalidadDTO.setUnidadPieza(String.valueOf(result[14]));
                controlCalidadDTO.setPrecioUnitario(new BigDecimal(String.valueOf(result[15])));
                controlCalidadDTO.setImporte(new BigDecimal(String.valueOf(result[16])));
                controlCalidadDTO.setFabricante(String.valueOf(result[17]));
                controlCalidadDTO.setFechaRemision(controlCalidadDTO.getFechaCaducidad());
                controlCalidadDTO.setRenglon(Integer.parseInt(String.valueOf(result[18])));
                controlCalidadDTO.setNumeroOrden(String.valueOf(result[19]));
                controlCalidadDTO.setProcedimiento(String.valueOf(result[20]));
                controlCalidadDTO.setContrato(String.valueOf(result[21]));
                controlCalidadDTO.setFechaCalidad(controlCalidadDTO.getFechaRemision());
                controlCalidadDTO.setProveedor(String.valueOf(result[22]));
                controlCalidadDTO.setNumeroRemision(String.valueOf(result[23]));
                listControlCalidadDTO.add(controlCalidadDTO);
            }
            encabezadosReportesDTO = new EncabezadosReportesDTO();
            encabezadosReportesDTO.setFechaElaboracion(new Date());
            encabezadosReportesDTO.setFechaInicial(fechaInicial);
            encabezadosReportesDTO.setFechaFinal(fechaFinal);
            encabezadosReportesDTO.setListInfoControlCalidad(listControlCalidadDTO);
            listReport.add(encabezadosReportesDTO);

        } catch (Exception e) {
            Logger.getLogger(reportesDTOsDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return listReport;
    }

    @Override
    public List<EncabezadosReportesDTO> obtenerResumenClaves(Date fechaInicial) {
        List<ReporteClavesDTO> listReporteClavesDTO = new ArrayList<>();
        List<ReporteClavesDTO> listSubReporteClaves = new ArrayList<>();
        ReporteClavesDTO reporteClavesDTO;
        List<EncabezadosReportesDTO> listReport = new ArrayList<>();
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        c.setTime(fechaInicial);
        EncabezadosReportesDTO encabezadosReportesDTO;
        List<Object[]> objectList = null;
        Integer existenciaCenadi = 0;
        Integer dpn = 0;
        BigDecimal resultado = new BigDecimal(0);
        List<AlertasOperativas> alertasList = null;
        String consul = "select i.clave,NVL(eccc.tipo,'Sin tipo') as tipo, NVL(Sum(Cast(eccc.existencia as Integer)),0) as existe, i.id_tipo_insumos,\n"
                + "  NVL(sum(dos.cantidad_suministrar),0) as entregas,dos.fecha_entrega_inicial,NVL(Sum(eccc.dpn),0) as DPN,r.fecha_remision \n"
                + "from existencia_reserva_clave_cenadi eccc\n"
                + "left join insumos i on i.clave = eccc.clave\n"
                + "left join rcb_insumos ri on (ri.clave_insumo = i.clave)\n"
                + "left join procedimiento_rcb pr on (pr.id_rcb_insumos = ri.id_rcb_insumos)\n"
                + "left join fallo_procedimiento_rcb fpr on (fpr.id_procedimiento_rcb = pr.id_procedimiento_rcb)\n"
                + "left join detalle_orden_suministro dos on (dos.id_fallo_procedimiento_rcb = fpr.id_fallo_procedimiento_rcb)\n"
                + "where dos.fecha_entrega_inicial between '" + c.get(Calendar.YEAR) + "/01/01' and '" + formatoDeFecha.format(fechaInicial) + "' \n"
                + "group by i.id_tipo_insumos,i.clave,eccc.tipo,dos.fecha_entrega_inicial,r.fecha_remision ";
        try {
            Query query = em.createNativeQuery(consul);
            objectList = query.getResultList();
            for (Object[] result : objectList) {
                reporteClavesDTO = new ReporteClavesDTO();
                reporteClavesDTO.setClave(String.valueOf(result[0]));
                reporteClavesDTO.setTipoClave(String.valueOf(result[1]));
                reporteClavesDTO.setDisponibleCenadi(Integer.parseInt(String.valueOf(result[2])));
                if (!String.valueOf(result[3]).equals("null")) {
                    reporteClavesDTO.setNumero(Integer.parseInt(String.valueOf(result[3])));//Uso de variable temporal para asignacion de clasificacion de insumos
                } else {
                    reporteClavesDTO.setNumero(0);//Uso de variable temporal para asignacion de clasificacion de insumos
                }
                if (!String.valueOf(result[4]).equals("null")) {
                    reporteClavesDTO.setTotales(Integer.parseInt(String.valueOf(result[4])));//Uso de variable temporal para asignacion de entregas
                } else {
                    reporteClavesDTO.setTotales(0);//Uso de variable temporal para asignacion de entregas
                }
                dpn = Integer.parseInt(String.valueOf(result[6]));
                existenciaCenadi = 0;
                resultado = BigDecimal.ZERO;
                reporteClavesDTO.setIngresoDia(new BigDecimal(obtenerIngresoClavesxDia(reporteClavesDTO.getClave(), format.parse(String.valueOf(result[7])))));
                reporteClavesDTO.setIngresi(reporteClavesDTO.getIngresoDia().intValueExact());
                resultado = reporteClavesDTO.getIngresoDia().add(new BigDecimal(reporteClavesDTO.getDisponibleCenadi()));
                if (resultado.intValueExact() == 0 || dpn == 0) {
                    resultado = new BigDecimal(0);
                } else {
                    resultado = resultado.divide(new BigDecimal(dpn), RoundingMode.HALF_UP);
                }
                reporteClavesDTO.setCoberturaDisponibleEntregaCenadi(resultado);//Uso de variable para parametrizacion de campo cob = o < a 30 dias
                listReporteClavesDTO.add(reporteClavesDTO);
            }
            int xConEntregaMedCero = 0;
            int xSinEntregaMedCero = 0;
            int xConEntregaCurCero = 0;
            int xSinEntregaCurCero = 0;
            int xConEntregaMedCob = 0;
            int xSinEntregaMedCob = 0;
            int xConEntregaCurCob = 0;
            int xSinEntregaCurCob = 0;
            int clavesCero = 0;
            int clavesCobertura = 0;
            for (ReporteClavesDTO iterator : listReporteClavesDTO) {
                if (iterator.getDisponibleCenadi() == 0) {
                    clavesCero++;
                    if (iterator.getNumero() == 1) {
                        if (iterator.getTotales() == 0) {
                            xSinEntregaMedCero++;
                        } else {
                            xConEntregaMedCero++;
                        }
                    } else {
                        if (iterator.getTotales() == 0) {
                            xSinEntregaCurCero++;
                        } else {
                            xConEntregaCurCero++;
                        }
                    }
                } else if (iterator.getCoberturaDisponibleEntregaCenadi().intValueExact() <= 30) {
                    clavesCobertura++;
                    if (iterator.getNumero() == 1) {
                        if (iterator.getTotales() == 0) {
                            xSinEntregaMedCob++;
                        } else {
                            xConEntregaMedCob++;
                        }
                    } else {
                        if (iterator.getTotales() == 0) {
                            xSinEntregaCurCob++;
                        } else {
                            xConEntregaCurCob++;
                        }
                    }
                }
            }
            encabezadosReportesDTO = new EncabezadosReportesDTO();
            reporteClavesDTO = new ReporteClavesDTO();
            listSubReporteClaves = new ArrayList<>();
            //Sub reporte Principal
            //Fila Cero subReporte claves
            reporteClavesDTO.setCantidadClavesCero(clavesCero);
            reporteClavesDTO.setCSEPMedCero(xSinEntregaMedCero);
            reporteClavesDTO.setCEPMedCero(xConEntregaMedCero);
            reporteClavesDTO.setCEPMatCuracionCero(xConEntregaCurCero);
            reporteClavesDTO.setCSEPMatCuracionCero(xSinEntregaCurCero);
            reporteClavesDTO.setTotalesCero(xSinEntregaMedCero + xConEntregaMedCero + xConEntregaCurCero + xSinEntregaCurCero);
            //Fila Cobertura#0 dias subReporte claves
            reporteClavesDTO.setCantidadClaves(clavesCobertura);
            reporteClavesDTO.setCSEPMed(xSinEntregaMedCob);
            reporteClavesDTO.setCEPMed(xConEntregaMedCob);
            reporteClavesDTO.setCSEPMatCuracion(xSinEntregaCurCob);
            reporteClavesDTO.setCEPMatCuracion(xConEntregaCurCob);
            reporteClavesDTO.setTotales(xSinEntregaMedCob + xConEntregaMedCob + xSinEntregaCurCob + xConEntregaCurCob);
            listSubReporteClaves.add(reporteClavesDTO);
            encabezadosReportesDTO.setListInfoClaves(listSubReporteClaves);
            Integer total;
            Double porcentajeMulti;
            //Sub reporte Claves sin Entrega
            reporteClavesDTO = new ReporteClavesDTO();
            listSubReporteClaves = new ArrayList<>();
            reporteClavesDTO.setTotalesCero(xSinEntregaCurCero + xSinEntregaMedCero);
            reporteClavesDTO.setTotales(xSinEntregaCurCob + xSinEntregaMedCob);
            reporteClavesDTO.setEstatus("Sin Entrega Programada");
            Double porcentaje;
            total = reporteClavesDTO.getTotales() + reporteClavesDTO.getTotalesCero();
            porcentajeMulti = Double.parseDouble("." + reporteClavesDTO.getTotalesCero().toString());
            porcentaje = total * porcentajeMulti;
            reporteClavesDTO.setPorcentajeClavesPro(new BigDecimal(porcentaje * 100));//Ceros
            total = reporteClavesDTO.getTotales() + reporteClavesDTO.getTotalesCero();
            porcentajeMulti = Double.parseDouble("." + reporteClavesDTO.getTotales().toString());
            porcentaje = total * porcentajeMulti;
            reporteClavesDTO.setPorcentajeSNClavesPro(new BigDecimal(porcentaje * 100));
            listSubReporteClaves.add(reporteClavesDTO);
            encabezadosReportesDTO.setLisInfoClavesSinEntrega(listSubReporteClaves);

            //Sub reporte Claves con entrega
            reporteClavesDTO = new ReporteClavesDTO();
            listSubReporteClaves = new ArrayList<>();
            reporteClavesDTO.setTotalesCero(xConEntregaCurCero + xConEntregaMedCero);
            reporteClavesDTO.setTotales(xConEntregaCurCob + xConEntregaMedCob);
            reporteClavesDTO.setEstatus("Con Entrega Programada");
            total = reporteClavesDTO.getTotales() + reporteClavesDTO.getTotalesCero();
            porcentajeMulti = Double.parseDouble("." + reporteClavesDTO.getTotalesCero().toString());
            porcentaje = total * porcentajeMulti;
            reporteClavesDTO.setPorcentajeClavesPro(new BigDecimal(porcentaje * 100));//Ceros
            total = reporteClavesDTO.getTotales() + reporteClavesDTO.getTotalesCero();
            porcentajeMulti = Double.parseDouble("." + reporteClavesDTO.getTotales().toString());
            porcentaje = total * porcentajeMulti;
            reporteClavesDTO.setPorcentajeSNClavesPro(new BigDecimal(porcentaje * 100));
            listSubReporteClaves.add(reporteClavesDTO);
            encabezadosReportesDTO.setLisInfoClavesConEntrega(listSubReporteClaves);

            encabezadosReportesDTO.setFechaInicial(new Date());
            encabezadosReportesDTO.setFechaElaboracion(new Date());
            listReport.add(encabezadosReportesDTO);

        } catch (Exception e) {
            Logger.getLogger(reportesDTOsDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return listReport;
    }

    @Override
    public List<EncabezadosReportesDTO> obtenerListConcentradoEntradas(Date Fecha) {
        List<ConcentradoDTO> concentradiList = new ArrayList<>();
        List<EncabezadosReportesDTO> listReport = new ArrayList<>();
        ConcentradoDTO concentradoDTO;
        EncabezadosReportesDTO encabezadosReportesDTO;
        List<Object[]> objectList = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        String consul = "select c.numero_contrato,NVL(c.numero_convenio,0),os.numero_orden,p.nombre_proveedor,"
                + "f.numero_procedimiento,ri.clave_insumo,ri.descripcion_insumo,fpr.precio_unitario,dos.cantidad_suministrar,"
                + "dos.fecha_entrega_inicial,dos.fecha_entrega_final,r.fecha_remision,dos.cantidad_suministrada,NVL(dos.total_cancelado,0),(dos.cantidad_suministrar - dos.cantidad_suministrada) as pendiente,p.numero\n"
                + "from remisiones r\n"
                + "join detalle_orden_suministro dos on dos.id_detalle_orden_suministro = r.id_detalle_orden_suministro\n"
                + "join orden_suministro os on os.id_orden_suministro = dos.id_orden_suministro\n"
                + "join contratos c on c.id_contrato = os.id_contrato\n"
                + "join fallo_procedimiento_rcb fpr on fpr.id_fallo_procedimiento_rcb = dos.id_fallo_procedimiento_rcb\n"
                + "join proveedores p on p.id_proveedor = fpr.id_proveedor\n"
                + "join fallos f on f.id_fallo = fpr.id_fallo\n"
                + "join procedimiento_rcb pr on pr.id_procedimiento_rcb = fpr.id_procedimiento_rcb\n"
                + "join rcb_insumos ri on ri.id_rcb_insumos = pr.id_rcb_insumos\n"
                + "join rcb rcb on rcb.id_rcb = ri.id_rcb\n"
                + "where r.fecha_remision = '" + formatoDeFecha.format(Fecha) + "'";

        try {
            Query query = em.createNativeQuery(consul);
            objectList = query.getResultList();
            for (Object[] result : objectList) {
                concentradoDTO = new ConcentradoDTO();
                concentradoDTO.setContrato(String.valueOf(result[0]));
                concentradoDTO.setConvenio(String.valueOf(result[1]));
                concentradoDTO.setNumeroOrden(String.valueOf(result[2]));
                concentradoDTO.setProveedor(String.valueOf(result[3]));
                concentradoDTO.setProcediminento(String.valueOf(result[4]));
                concentradoDTO.setInsumo(String.valueOf(result[5]));
                concentradoDTO.setDescripcion(String.valueOf(result[6]));
                concentradoDTO.setPrecioUnitario(new BigDecimal(String.valueOf(result[7])));
                concentradoDTO.setCantidadAdjudicada(Integer.parseInt(String.valueOf(result[8])));
                concentradoDTO.setFechaInicial(format.parse(String.valueOf(result[9])));
                concentradoDTO.setFechaFinal(format.parse(String.valueOf(result[10])));
                concentradoDTO.setFechaIngreso(format.parse(String.valueOf(result[11])));
                concentradoDTO.setCantidadIngresada(Integer.parseInt(String.valueOf(result[12])));
                concentradoDTO.setCantidadCancelada(Integer.parseInt(String.valueOf(result[13])));
                concentradoDTO.setCantidadPendiente(Integer.parseInt(String.valueOf(result[14])));
                concentradoDTO.setNumeroProveedor(Integer.parseInt(String.valueOf(result[15])));
                concentradoDTO.setFechaGenerado(new Date());
                concentradiList.add(concentradoDTO);
            }
            encabezadosReportesDTO = new EncabezadosReportesDTO();
            encabezadosReportesDTO.setFechaElaboracion(new Date());
            encabezadosReportesDTO.setFechaInicial(new Date());
            encabezadosReportesDTO.setListConcentrado(concentradiList);
            listReport.add(encabezadosReportesDTO);

        } catch (Exception e) {
            Logger.getLogger(reportesDTOsDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return listReport;
    }

    @Override
    public List<EncabezadosReportesDTO> obtenerListConcentradoPendientes() {
        List<ConcentradoPendienteDTO> concentradoPendienteList = new ArrayList<>();
        List<EncabezadosReportesDTO> listReport = new ArrayList<>();
        ConcentradoPendienteDTO concentradoPendienteDTO;
        EncabezadosReportesDTO encabezadosReportesDTO;
        List<Object[]> objectList = null;
        Calendar c = Calendar.getInstance(Locale.ENGLISH);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        String consul = "select ri.clave_insumo,"
                + "NVL((select dos.cantidad_suministrar from detalle_orden_suministro dos where dos.id_detalle_orden_suministro = dors.id_detalle_orden_suministro and dos.fecha_entrega_inicial between '" + c.get(Calendar.YEAR) + "/01/01' and '" + c.get(Calendar.YEAR) + "/01/31'),0) as enero,"
                + "NVL((select dos.cantidad_suministrar from detalle_orden_suministro dos where dos.id_detalle_orden_suministro = dors.id_detalle_orden_suministro and dos.fecha_entrega_inicial between '" + c.get(Calendar.YEAR) + "/02/01' and '" + c.get(Calendar.YEAR) + "/02/28'),0) as febrero,"
                + "NVL((select dos.cantidad_suministrar from detalle_orden_suministro dos where dos.id_detalle_orden_suministro = dors.id_detalle_orden_suministro and dos.fecha_entrega_inicial between '" + c.get(Calendar.YEAR) + "/03/01' and '" + c.get(Calendar.YEAR) + "/03/31'),0) as marzo,"
                + "NVL((select dos.cantidad_suministrar from detalle_orden_suministro dos where dos.id_detalle_orden_suministro = dors.id_detalle_orden_suministro and dos.fecha_entrega_inicial between '" + c.get(Calendar.YEAR) + "/04/01' and '" + c.get(Calendar.YEAR) + "/04/30'),0) as abril,"
                + "NVL((select dos.cantidad_suministrar from detalle_orden_suministro dos where dos.id_detalle_orden_suministro = dors.id_detalle_orden_suministro and dos.fecha_entrega_inicial between '" + c.get(Calendar.YEAR) + "/05/01' and '" + c.get(Calendar.YEAR) + "/05/31'),0) as mayo,"
                + "NVL((select dos.cantidad_suministrar from detalle_orden_suministro dos where dos.id_detalle_orden_suministro = dors.id_detalle_orden_suministro and dos.fecha_entrega_inicial between '" + c.get(Calendar.YEAR) + "/06/01' and '" + c.get(Calendar.YEAR) + "/06/30'),0) as junio,"
                + "NVL((select dos.cantidad_suministrar from detalle_orden_suministro dos where dos.id_detalle_orden_suministro = dors.id_detalle_orden_suministro and dos.fecha_entrega_inicial between '" + c.get(Calendar.YEAR) + "/07/01' and '" + c.get(Calendar.YEAR) + "/07/31'),0) as julio,"
                + "NVL((select dos.cantidad_suministrar from detalle_orden_suministro dos where dos.id_detalle_orden_suministro = dors.id_detalle_orden_suministro and dos.fecha_entrega_inicial between '" + c.get(Calendar.YEAR) + "/08/01' and '" + c.get(Calendar.YEAR) + "/08/31'),0) as agosto,"
                + "NVL((select dos.cantidad_suministrar from detalle_orden_suministro dos where dos.id_detalle_orden_suministro = dors.id_detalle_orden_suministro and dos.fecha_entrega_inicial between '" + c.get(Calendar.YEAR) + "/09/01' and '" + c.get(Calendar.YEAR) + "/09/30'),0) as septiembre,"
                + "NVL((select dos.cantidad_suministrar from detalle_orden_suministro dos where dos.id_detalle_orden_suministro = dors.id_detalle_orden_suministro and dos.fecha_entrega_inicial between '" + c.get(Calendar.YEAR) + "/10/01' and '" + c.get(Calendar.YEAR) + "/10/31'),0) as octubre,"
                + "NVL((select dos.cantidad_suministrar from detalle_orden_suministro dos where dos.id_detalle_orden_suministro = dors.id_detalle_orden_suministro and dos.fecha_entrega_inicial between '" + c.get(Calendar.YEAR) + "/11/01' and '" + c.get(Calendar.YEAR) + "/11/30'),0) as noviembre,"
                + "NVL((select dos.cantidad_suministrar from detalle_orden_suministro dos where dos.id_detalle_orden_suministro = dors.id_detalle_orden_suministro and dos.fecha_entrega_inicial between '" + c.get(Calendar.YEAR) + "/12/01' and '" + c.get(Calendar.YEAR) + "/12/31'),0) as diciembre\n"
                + "from remisiones r\n"
                + "join detalle_orden_suministro dors on r.id_detalle_orden_suministro = dors.id_detalle_orden_suministro\n"
                + "join fallo_procedimiento_rcb fpr on fpr.id_fallo_procedimiento_rcb = dors.id_fallo_procedimiento_rcb\n"
                + "join procedimiento_rcb pr on pr.id_procedimiento_rcb = fpr.id_procedimiento_rcb\n"
                + "join rcb_insumos ri on ri.id_rcb_insumos = pr.id_rcb_insumos";
        try {
            Query query = em.createNativeQuery(consul);
            objectList = query.getResultList();
            for (Object[] result : objectList) {
                concentradoPendienteDTO = new ConcentradoPendienteDTO();
                concentradoPendienteDTO.setClave(String.valueOf(result[0]));
                concentradoPendienteDTO.setEnero(Integer.parseInt(String.valueOf(result[1])));
                concentradoPendienteDTO.setFebrero(Integer.parseInt(String.valueOf(result[2])));
                concentradoPendienteDTO.setMarzo(Integer.parseInt(String.valueOf(result[3])));
                concentradoPendienteDTO.setAbril(Integer.parseInt(String.valueOf(result[4])));
                concentradoPendienteDTO.setMayo(Integer.parseInt(String.valueOf(result[5])));
                concentradoPendienteDTO.setJunio(Integer.parseInt(String.valueOf(result[6])));
                concentradoPendienteDTO.setJulio(Integer.parseInt(String.valueOf(result[7])));
                concentradoPendienteDTO.setAgosto(Integer.parseInt(String.valueOf(result[8])));
                concentradoPendienteDTO.setSeptiembre(Integer.parseInt(String.valueOf(result[9])));
                concentradoPendienteDTO.setOctubre(Integer.parseInt(String.valueOf(result[10])));
                concentradoPendienteDTO.setNoviembre(Integer.parseInt(String.valueOf(result[11])));
                concentradoPendienteDTO.setDiciembre(Integer.parseInt(String.valueOf(result[12])));
                concentradoPendienteList.add(concentradoPendienteDTO);
            }
            encabezadosReportesDTO = new EncabezadosReportesDTO();
            encabezadosReportesDTO.setFechaElaboracion(new Date());
            encabezadosReportesDTO.setFechaInicial(new Date());
            encabezadosReportesDTO.setListConcentradoPendienteDTO(concentradoPendienteList);
            listReport.add(encabezadosReportesDTO);

        } catch (Exception e) {
            Logger.getLogger(reportesDTOsDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return listReport;
    }

}
