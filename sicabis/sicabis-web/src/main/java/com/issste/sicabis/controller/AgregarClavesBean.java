package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.ProcedimientosRcbService;
import com.issste.sicabis.ejb.ln.RcbInsumosService;
import com.issste.sicabis.ejb.ln.RcbService;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import com.issste.sicabis.utils.Mensajes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.context.RequestContext;

public class AgregarClavesBean {

    @EJB
    private RcbInsumosService rcbInsumosService;

    @EJB
    private ProcedimientosRcbService procedimientosRcbService;

    @EJB
    private RcbService rcbService;

    private String numeroRCB;
    private List<RcbInsumos> listaRcbInsumos;
    private List<RcbInsumos> listaRcbInsumosFilter;
    private int tipoCompra;
    private List<RcbInsumos> listaRcbInsumosSelect;
    private List<RcbInsumos> listaProcInsumos;
    private List<ProcedimientoRcb> listaProcRcb;
    private List<ProcedimientoRcb> listaProcRcbFilter;
    private List<ProcedimientoRcb> listaProcRcbSelect;
    private List<ProcedimientoRcb> listaProcRcbTotal;
    private Mensajes mensaje = new Mensajes();

    public AgregarClavesBean() {
        listaRcbInsumos = new ArrayList();
    }

    public void obtenerClaves() {
        listaRcbInsumos = rcbInsumosService.getListClavesByNumeroTipoCompra(numeroRCB.toUpperCase(), tipoCompra);
//        Rcb rcb = rcbService.getRCByNum(numeroRCB);
//        System.out.println(rcb.getNumero());
//        if (rcb.getNumero() != null) {
            listaProcRcb = procedimientosRcbService.obtenerByIdNumRcbTipoCompra(numeroRCB, tipoCompra);
//        } else {
//            mensaje.mensaje("Ingrese un RCB existente", "Amarillo");
//        }
    }

    public void agregarClaves() {
        listaProcRcbTotal = new ArrayList();
        ProcedimientoRcb procRCB = null;
        if (listaRcbInsumosSelect.size() > 0) {
            for (RcbInsumos insumosNuevos : listaRcbInsumosSelect) {
                procRCB = new ProcedimientoRcb();
                procRCB.setActivo(1);
                procRCB.setCantidadPiezas(insumosNuevos.getCantidadPiezas());
                procRCB.setIdRcbInsumos(insumosNuevos);
                procRCB.setPrecioUnitario(insumosNuevos.getPrecioUnitario());
                procRCB.setImporte(insumosNuevos.getImporte());
                procRCB.setExistencias(insumosNuevos.getExistencias());
                procRCB.setConsumoPromedio(insumosNuevos.getConsumoPromedio());
                procRCB.setMesesCobertura(insumosNuevos.getMesesCobertura());
                procRCB.setFechaAlta(new Date());
                procRCB.setDesierta(0);
                procRCB.setDesiertaParcial(0);
                listaProcRcbTotal.add(procRCB);
            }
        }
        if (listaProcRcbSelect.size() > 0) {
            for (ProcedimientoRcb procRcbNuevo : listaProcRcbSelect) {
                procRCB = new ProcedimientoRcb();
                procRCB = procRcbNuevo;
                listaProcRcbTotal.add(procRCB);
            }
        }

        RequestContext.getCurrentInstance().closeDialog(listaProcRcbTotal);
    }

    public void cancel() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public String getNumeroRCB() {
        return numeroRCB;
    }

    public void setNumeroRCB(String numeroRCB) {
        this.numeroRCB = numeroRCB.toUpperCase();
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

    public List<RcbInsumos> getListaRcbInsumosSelect() {
        return listaRcbInsumosSelect;
    }

    public void setListaRcbInsumosSelect(List<RcbInsumos> listaRcbInsumosSelect) {
        this.listaRcbInsumosSelect = listaRcbInsumosSelect;
    }

    public List<RcbInsumos> getListaProcInsumos() {
        return listaProcInsumos;
    }

    public void setListaProcInsumos(List<RcbInsumos> listaProcInsumos) {
        this.listaProcInsumos = listaProcInsumos;
    }

    public List<RcbInsumos> getListaRcbInsumosFilter() {
        return listaRcbInsumosFilter;
    }

    public void setListaRcbInsumosFilter(List<RcbInsumos> listaRcbInsumosFilter) {
        this.listaRcbInsumosFilter = listaRcbInsumosFilter;
    }

    public List<ProcedimientoRcb> getListaProcRcb() {
        return listaProcRcb;
    }

    public void setListaProcRcb(List<ProcedimientoRcb> listaProcRcb) {
        this.listaProcRcb = listaProcRcb;
    }

    public List<ProcedimientoRcb> getListaProcRcbFilter() {
        return listaProcRcbFilter;
    }

    public void setListaProcRcbFilter(List<ProcedimientoRcb> listaProcRcbFilter) {
        this.listaProcRcbFilter = listaProcRcbFilter;
    }

    public List<ProcedimientoRcb> getListaProcRcbSelect() {
        return listaProcRcbSelect;
    }

    public void setListaProcRcbSelect(List<ProcedimientoRcb> listaProcRcbSelect) {
        this.listaProcRcbSelect = listaProcRcbSelect;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

}
