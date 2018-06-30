/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.utils;

import com.issste.sicabis.ejb.DTO.ContratoOrdenDTO;
import com.issste.sicabis.ejb.ln.ContratoFalloProcedimientoRcbService;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.ln.DpnInsumosService;
import com.issste.sicabis.ejb.ln.FalloProcedimientoRcbService;
import com.issste.sicabis.ejb.ln.OrdenSuministroService;
import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.service.silodisa.modelo.AlertasOperativas;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveCenadi;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciasPorClaveCenadiService;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
public class OrdenesSuministroAuto {

    //EJB's
    @EJB
    private ExistenciasPorClaveCenadiService existenciasPorClaveCenadiService;
    @EJB
    private DpnInsumosService dpnInsumosService;
    @EJB
    private ContratoFalloProcedimientoRcbService contratoFalloProcedimientoRcbService;
    @EJB
    private FalloProcedimientoRcbService falloProcedimientoRcbService;
    @EJB
    private OrdenSuministroService ordenSuministroService;
    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;

    //Objetos
    private Utilidades util;
    private List<ContratoFalloProcedimientoRcb> listaConFalloProcRcb;
    private List<ContratoOrdenDTO> listaContratoOrdenDTO;
    private List<ContratoOrdenDTO> listaRemisionesOrden;
    private List<Integer> listaProv;
    private OrdenSuministro ordenSuministro;
    private List<DetalleOrdenSuministro> listDetalleOrdenSuministro;
    private List<ContratoOrdenDTO> listaInsumosDisp;

    public OrdenesSuministroAuto() {
        util = new Utilidades();
        listaConFalloProcRcb = new ArrayList<>();
        listaContratoOrdenDTO = new ArrayList<>();
        listaInsumosDisp = new ArrayList<>();
        listaProv = new ArrayList();
        ordenSuministro = new OrdenSuministro();
        listDetalleOrdenSuministro = new ArrayList<>();
    }

    public boolean generacionOrdenesAutomaticas() throws IOException {
        this.eliminarOrdenes();
        listaRemisionesOrden = new ArrayList<>();
        listaRemisionesOrden = detalleOrdenSuministroService.obtenerDisponibleByClave("", null);
        for (ContratoOrdenDTO iterator : listaRemisionesOrden) {
            agregaClave(iterator.getClaveInsumo());
        }
        guardaOrden();
        return true;
    }

    public void eliminarOrdenes() {
        List<OrdenSuministro> listOrden = ordenSuministroService.obtenerOrdenByPreOrdenSistema();
        for (OrdenSuministro iterator : listOrden) {
            for (DetalleOrdenSuministro iterator2 : iterator.getDetalleOrdenSuministroList()) {
                FalloProcedimientoRcb fpr = new FalloProcedimientoRcb();
                fpr = iterator2.getIdFalloProcedimientoRcb();
                Integer sum = fpr.getSuministradoOrden();
                fpr.setSuministradoOrden(sum - iterator2.getCantidadSuministrar());
                if (fpr.getCantidadPiezas() != fpr.getSuministradoOrden()) {
                    fpr.setCompletadoContrato(0);
                }
                falloProcedimientoRcbService.actualizaCantidadConvenio(fpr);
            }
        }

        boolean eliminar = ordenSuministroService.eliminarPreOrdenesSistema();
    }

    public void agregaClave(String claveInsumos) throws IOException {
        listaConFalloProcRcb = new ArrayList<>();
        listaContratoOrdenDTO = new ArrayList<>();
        listaInsumosDisp = new ArrayList<>();
        listaProv = new ArrayList();
        ordenSuministro = new OrdenSuministro();
        listDetalleOrdenSuministro = new ArrayList<>();
        Integer existenciaCenadi = 0;
        Integer dpn = 0;
        boolean band = true;
        boolean bandera = false;
        BigDecimal resultado;
        List<ExistenciaPorClaveCenadi> existenciasList = null;
        if (existenciasPorClaveCenadiService.existenciaSumPorClaveCenadiByClave(claveInsumos) != 0) {
            existenciaCenadi = existenciasPorClaveCenadiService.existenciaSumPorClaveCenadiByClave(claveInsumos);
        }
        if (dpnInsumosService.getBySumDpnByInsumo(claveInsumos, "") != 0) {
            dpn = dpnInsumosService.getBySumDpnByInsumo(claveInsumos, "");
            Integer suma = existenciaCenadi;
            resultado = new BigDecimal(suma).divide(new BigDecimal(dpn), 2, RoundingMode.HALF_UP);
            resultado = resultado.multiply(new BigDecimal(30));
        }
        resultado = new BigDecimal(0.0);
        if (resultado.intValue() < 51) {
            Integer cantidad = resultado.multiply(new BigDecimal(dpn)).divide(new BigDecimal(30)).intValue();
            cantidad = cantidad * 30;
            //consulta piezas de contrato
            listaConFalloProcRcb = contratoFalloProcedimientoRcbService.obtenerCfprByClave(claveInsumos);
            if (listaConFalloProcRcb.size() > 0) {
                DetalleOrdenSuministro dos = new DetalleOrdenSuministro();
                ContratoOrdenDTO codtoAux = new ContratoOrdenDTO();
                for (ContratoFalloProcedimientoRcb cfpr : listaConFalloProcRcb) {
                    dos = new DetalleOrdenSuministro();
                    codtoAux = new ContratoOrdenDTO();
                    codtoAux.setId(cfpr.getIdContratoFalloProcedimientoRcb());

                    dos.setActivo(1);
                    dos.setCancelado(1);
                    dos.setFechaEntregaInicial(new Date());
                    dos.setFechaEntregaFinal(new Date());
                    //dos.setTotalCancelado(0);
                    dos.setFechaAlta(new Date());
                    dos.setIdFalloProcedimientoRcb(cfpr.getIdFalloProcedimientoRcb());
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
                        dos.setCantidadSuministrar(cantidad.intValue());
                        codtoAux.setCompletado(0);
                        bandera = true;
                    }
                    if (existenciaCenadi == 0) {
                        Double exisCero = cfpr.getIdFalloProcedimientoRcb().getCantidadPiezas().doubleValue() / 12;
                        exisCero = exisCero * 2.5;
                        dos.setTotalCancelado(0);
                        dos.setCantidadSuministrar(new BigDecimal(exisCero).intValue());
                        codtoAux.setCompletado(0);
                    }
                    dos.setImporte(util.multiplica(dos.getIdFalloProcedimientoRcb().getPrecioUnitario(), dos.getCantidadSuministrar()));
                    codtoAux.setClaveInsumo(claveInsumos);
                    codtoAux.setLugarEntrega(cfpr.getIdContrato().getIdDestino().getNombre() + ", " + cfpr.getIdContrato().getIdDestino().getDomicilio());
                    codtoAux.setFechaInicialContrato(new Date());
                    codtoAux.setFechaFinalContrato(cfpr.getIdContrato().getVigenciaFinal());
                    codtoAux.setCantidadSuministrar(dos.getCantidadSuministrar());
                    codtoAux.setContratoFalloProcedimientoRcb(cfpr);
                    codtoAux.setDetalleOrdenSuministro(dos);
                    listaContratoOrdenDTO.add(codtoAux);

                }
            }
        }
    }

    public void guardaOrden() {
        Integer idProv = 0;
        boolean bandera = true;
        String numeroOrden = "";
        BigDecimal importe = BigDecimal.ZERO;
        Date fechaFinal;
        Calendar cal = Calendar.getInstance();
        int x = 0;
        for (ContratoOrdenDTO codto : listaContratoOrdenDTO) {
            idProv = codto.getContratoFalloProcedimientoRcb().getIdFalloProcedimientoRcb().getIdProveedor().getIdProveedor();
            if (listaProv.size() == 0) {
                listaProv.add(idProv);
            }
            for (Integer idProvAux : listaProv) {
                if (idProvAux.intValue() != idProv.intValue()) {
                    listaProv.add(idProv);
                }
            }
        }
        for (Integer iterator : listaProv) {
            ordenSuministro = new OrdenSuministro();
            ordenSuministro.setIdEstatus(new Estatus(71));
            List<DetalleOrdenSuministro> listaDetalleOrdenAux = new ArrayList();
            if (listaProv.size() == 1) {
                for (ContratoOrdenDTO codto : listaContratoOrdenDTO) {
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
                    }
                }
                Integer maxNum = ordenSuministroService.obtenerUltimoIdOrden();
                if (maxNum == null) {
                    maxNum = 1;
                } else {
                    maxNum++;
                }
                numeroOrden = "ORDEN_" + maxNum;
                ordenSuministro.setActivo(1);
                ordenSuministro.setPreOrden(1);
                ordenSuministro.setNumeroOrden(numeroOrden);
                ordenSuministro.setFechaAlta(new Date());
                ordenSuministro.setFechaOrden(new Date());
                ordenSuministro.setImporte(importe);
                ordenSuministro.setDetalleOrdenSuministroList(listaDetalleOrdenAux);
                if (ordenSuministroService.guardaOrdenSuministro(ordenSuministro)) {
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
                    numeroOrden = "ORDEN_" + maxNum;
                    ordenSuministro.setActivo(1);
                    ordenSuministro.setPreOrden(1);
                    ordenSuministro.setNumeroOrden(numeroOrden);
                    ordenSuministro.setFechaAlta(new Date());
                    ordenSuministro.setFechaOrden(new Date());
                    ordenSuministro.setImporte(importe);
                    ordenSuministro.setDetalleOrdenSuministroList(listaDetalleOrdenAux);
                    if (ordenSuministroService.guardaOrdenSuministro(ordenSuministro)) {

                    }
                }
            }
        }
    }
}
