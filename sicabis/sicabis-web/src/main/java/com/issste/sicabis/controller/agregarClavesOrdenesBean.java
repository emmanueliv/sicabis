package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.DTO.ContratoOrdenDTO;
import com.issste.sicabis.ejb.ln.ContratoFalloProcedimientoRcbService;
import com.issste.sicabis.ejb.ln.DetalleOrdenSuministroService;
import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.context.RequestContext;

public class agregarClavesOrdenesBean {

    @EJB
    private DetalleOrdenSuministroService detalleOrdenSuministroService;

    @EJB
    private ContratoFalloProcedimientoRcbService contratoFalloProcedimientoRcbService;

    private Usuarios usuarios;

    private String claveInsumo;
    private List<ContratoFalloProcedimientoRcb> listaConFalloProcRcb;
    private List<ContratoOrdenDTO> listaContratoOrdenDTO;
    private List<ContratoOrdenDTO> listaContratoOrdenDTOFilter;
    private List<ContratoOrdenDTO> listaContratoOrdenDTOSelect;
    private String clave;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();

    public agregarClavesOrdenesBean() {
    }
    
    @PostConstruct
    public void init(){
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        listaConFalloProcRcb = new ArrayList();
        listaContratoOrdenDTO = new ArrayList();
    }

    public void obtenerClaves() {
        listaConFalloProcRcb = contratoFalloProcedimientoRcbService.obtenerByClaves(claveInsumo);
        if (listaConFalloProcRcb.size() > 0) {
            //proveedores = listaConFalloProcRcb.get(0).getIdFalloProcedimientoRcb().getIdProveedor();
            DetalleOrdenSuministro dos = null;
            ContratoOrdenDTO codto = null;
            for (ContratoFalloProcedimientoRcb cfpr : listaConFalloProcRcb) {
                dos = new DetalleOrdenSuministro();
                codto = new ContratoOrdenDTO();
                codto.setId(cfpr.getIdContratoFalloProcedimientoRcb());

                dos.setActivo(1);
                dos.setCancelado(1);
                dos.setFechaEntregaInicial(new Date());
                dos.setFechaEntregaFinal(new Date());
                //dos.setTotalCancelado(0);
                dos.setFechaAlta(new Date());
                dos.setIdFalloProcedimientoRcb(cfpr.getIdFalloProcedimientoRcb());
                dos.setUsuarioAlta(usuarios.getNombre() + "_" + usuarios.getApellidoPaterno() + "_" + usuarios.getApellidoPaterno());
                Integer total = 0;
                Integer suministrado = 0;
                suministrado = detalleOrdenSuministroService.obtenerByIdFalloProcRcb(dos.getIdFalloProcedimientoRcb().getIdFalloProcedimientoRcb());
                dos.setCantidadSuministrada(suministrado);
                dos.setCantidadSuministrar(0);
                if (suministrado != -1) {
                    total = dos.getIdFalloProcedimientoRcb().getCantidadModificada().intValue() + dos.getIdFalloProcedimientoRcb().getCantidadAgregadaConvenio().intValue();
                    total = total.intValue() - suministrado.intValue();
                    dos.setTotalCancelado(total);
                    dos.setCantidadSuministrar(total);
                }
                if (total > 0) {
                    dos.setImporte(util.multiplica(dos.getIdFalloProcedimientoRcb().getPrecioUnitario(), dos.getCantidadSuministrar()));
                    //listaDetalleOrden.add(dos);
                    codto.setLugarEntrega(cfpr.getIdContrato().getIdDestino().getNombre() + ", " + cfpr.getIdContrato().getIdDestino().getDomicilio());
                    codto.setFechaInicialContrato(new Date());
                    codto.setFechaFinalContrato(cfpr.getIdContrato().getVigenciaFinal());
                    codto.setContratoFalloProcedimientoRcb(cfpr);
                    codto.setDetalleOrdenSuministro(dos);
                    listaContratoOrdenDTO.add(codto);
                }
            }
        }
    }

    public void agregarClaves() {
        RequestContext.getCurrentInstance().closeDialog(listaContratoOrdenDTOSelect);
    }

    public String getClaveInsumo() {
        return claveInsumo;
    }

    public void setClaveInsumo(String claveInsumo) {
        this.claveInsumo = claveInsumo;
    }

    public List<ContratoFalloProcedimientoRcb> getListaConFalloProcRcb() {
        return listaConFalloProcRcb;
    }

    public void setListaConFalloProcRcb(List<ContratoFalloProcedimientoRcb> listaConFalloProcRcb) {
        this.listaConFalloProcRcb = listaConFalloProcRcb;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
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

    public List<ContratoOrdenDTO> getListaContratoOrdenDTOSelect() {
        return listaContratoOrdenDTOSelect;
    }

    public void setListaContratoOrdenDTOSelect(List<ContratoOrdenDTO> listaContratoOrdenDTOSelect) {
        this.listaContratoOrdenDTOSelect = listaContratoOrdenDTOSelect;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
}
