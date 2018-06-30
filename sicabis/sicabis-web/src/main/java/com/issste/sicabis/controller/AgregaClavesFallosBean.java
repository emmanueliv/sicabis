package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.FalloProcedimientoRcbService;
import com.issste.sicabis.ejb.ln.ProcedimientoService;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Procedimientos;
import com.issste.sicabis.utils.Utilidades;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.context.RequestContext;

public class AgregaClavesFallosBean {

    @EJB
    private ProcedimientoService procedimientoService;
    @EJB
    private FalloProcedimientoRcbService falloProcedimientoRcbService;

    private Integer idProveedor;
    private Integer idTipoInsumo;
    private List<FalloProcedimientoRcb> listaFalloProcRcb;
    private List<FalloProcedimientoRcb> listaFalloProcRcbSelect;
    private List<FalloProcedimientoRcb> listaFalloProcRcbFilter;
    private List<Integer> listId = new ArrayList<>();
    private Utilidades util = new Utilidades();

    public AgregaClavesFallosBean() {

    }

    public void onload() {
        listId = (List<Integer>) util.getContextAtributte("listId");
        System.out.println("idProveedoraaa--->" + idProveedor);
        System.out.println(listId.get(1));
        listaFalloProcRcb = falloProcedimientoRcbService.obtenerByFalloProcRcb(listId.get(0), listId.get(1), listId.get(2));
    }

    public void agregarClaves() {
        //System.out.println("listaFalloProcRcbSelect--->"+listaFalloProcRcbSelect.size());
        RequestContext.getCurrentInstance().closeDialog(listaFalloProcRcbSelect);
    }

    public void cancel() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdTipoInsumo() {
        return idTipoInsumo;
    }

    public void setIdTipoInsumo(Integer idTipoInsumo) {
        this.idTipoInsumo = idTipoInsumo;
    }

    public List<FalloProcedimientoRcb> getListaFalloProcRcb() {
        return listaFalloProcRcb;
    }

    public void setListaFalloProcRcb(List<FalloProcedimientoRcb> listaFalloProcRcb) {
        this.listaFalloProcRcb = listaFalloProcRcb;
    }

    public List<FalloProcedimientoRcb> getListaFalloProcRcbSelect() {
        return listaFalloProcRcbSelect;
    }

    public void setListaFalloProcRcbSelect(List<FalloProcedimientoRcb> listaFalloProcRcbSelect) {
        this.listaFalloProcRcbSelect = listaFalloProcRcbSelect;
    }

    public List<FalloProcedimientoRcb> getListaFalloProcRcbFilter() {
        return listaFalloProcRcbFilter;
    }

    public void setListaFalloProcRcbFilter(List<FalloProcedimientoRcb> listaFalloProcRcbFilter) {
        this.listaFalloProcRcbFilter = listaFalloProcRcbFilter;
    }

}
