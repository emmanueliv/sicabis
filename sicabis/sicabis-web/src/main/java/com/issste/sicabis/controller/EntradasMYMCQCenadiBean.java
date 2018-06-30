package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.EntradasMymcqCenadiHistoricoService;
import com.issste.sicabis.ejb.modelo.EntradasMymcqCenadiHistorico;
import com.issste.sicabis.ejb.service.silodisa.modelo.CatInsumos;
import com.issste.sicabis.ejb.service.silodisa.service.CatalogoInsumosService;
import com.issste.sicabis.ejb.service.silodisa.service.EntradasMymcqCenadiService;
import com.issste.sicabis.utils.Mensajes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

public class EntradasMYMCQCenadiBean {

    @EJB
    private CatalogoInsumosService catalogoInsumosService;

    @EJB
    private EntradasMymcqCenadiHistoricoService entradasMymcqCenadiHistoricoService;

    @EJB
    private EntradasMymcqCenadiService entradasMymcqCenadiService;

    private List<EntradasMymcqCenadiHistorico> exitenciaListHistoricoConsul;
    private List<CatInsumos> listCatInsumos;
    private Date fechaInicio;
    private Date fechaFin;
    private String proveedor;
    private String registroControl;
    private String numContratoCualquiera;
    private String ocOracle;
    private String loteCualquiera;
    private String clave;
    private String tipoEntrada;

    private Mensajes mensajes = new Mensajes();

    @PostConstruct
    public void init() {
        listCatInsumos = new ArrayList<>();
        proveedor = "";
        registroControl = "";
        numContratoCualquiera = "";
        tipoEntrada = "";
        ocOracle = "";
        loteCualquiera = "";
        clave = "-1";
        listCatInsumos = catalogoInsumosService.getAllCatalogoInsumos();
        fechaInicio = new Date();
        fechaFin = new Date();
        exitenciaListHistoricoConsul = entradasMymcqCenadiService.getByFiltros(fechaInicio, fechaFin, proveedor, registroControl, numContratoCualquiera, tipoEntrada, ocOracle, loteCualquiera, clave);
    }

    public boolean valida() {
        boolean bandera = true;
        if (fechaInicio == null) {
            bandera = false;
            mensajes.mensaje("Debes ingresar la fecha inicial", "amarillo");
        }
        if (fechaFin == null) {
            bandera = false;
            mensajes.mensaje("Debes ingresar la fecha final", "amarillo");
        }
        if (fechaInicio != null && fechaFin != null) {
            if (fechaFin.compareTo(fechaInicio) < 0) {
                bandera = false;
                mensajes.mensaje("La fecha final es menor que la fecha inicial", "amarillo");
            }
        }
        return bandera;
    }

    public void buscarClaveMyMByFiltros() {
        if (valida()) {
            exitenciaListHistoricoConsul = entradasMymcqCenadiService.getByFiltros(fechaInicio, fechaFin, proveedor, registroControl, numContratoCualquiera, tipoEntrada, ocOracle, loteCualquiera, clave);
            if (exitenciaListHistoricoConsul == null) {
                mensajes.mensaje("No hay datos registrados", "amarillo");
            }
        }
    }

    public List<CatInsumos> getListCatInsumos() {
        return listCatInsumos;
    }

    public void setListCatInsumos(List<CatInsumos> listCatInsumos) {
        this.listCatInsumos = listCatInsumos;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getRegistroControl() {
        return registroControl;
    }

    public void setRegistroControl(String registroControl) {
        this.registroControl = registroControl;
    }

    public String getNumContratoCualquiera() {
        return numContratoCualquiera;
    }

    public void setNumContratoCualquiera(String numContratoCualquiera) {
        this.numContratoCualquiera = numContratoCualquiera;
    }

    public String getOcOracle() {
        return ocOracle;
    }

    public void setOcOracle(String ocOracle) {
        this.ocOracle = ocOracle;
    }

    public String getLoteCualquiera() {
        return loteCualquiera;
    }

    public void setLoteCualquiera(String loteCualquiera) {
        this.loteCualquiera = loteCualquiera;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public List<EntradasMymcqCenadiHistorico> getExitenciaListHistoricoConsul() {
        return exitenciaListHistoricoConsul;
    }

    public void setExitenciaListHistoricoConsul(List<EntradasMymcqCenadiHistorico> exitenciaListHistoricoConsul) {
        this.exitenciaListHistoricoConsul = exitenciaListHistoricoConsul;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }
}
