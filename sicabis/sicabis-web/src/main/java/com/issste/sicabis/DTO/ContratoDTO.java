/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.DTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fabianvr
 */
public class ContratoDTO {

    private String nombreProveedor;
    private String direccion;
    private String telefonoProv;
    private String rfcProv;
    private String numeroContrato;
    private String tipoContrato;
    private Date fechaContrato;
    private String partida;
    private String clavePresupuestal;
    private String nep;
    private String numeroProcedimiento;
    private String fundamentoLegal;
    private String descripcionFundamento;
    private Date vigenciaInicial;
    private Date vigenciaFinal;
    private String compradorNombre;
    private String compradorApellido;
    private String compradorApellidoMaterno;
    private String compradorIniciales;
    private BigDecimal importe;
    private BigDecimal importeMin;
    private List<InsumosDTO> insumosList;
    private Integer año;
    private String numeroConvenio;
    private String clausulas;
    private String importeLetra;
    private Integer noRupa;
    private String rcb;
    private String oficioSuficienciaPresupuestal;
    private Date fechaSuficienciaPresupuestal;
    private String notas;
    private String siglaTipoInsumos;
    private String rutaLogo;
    
    
    public ContratoDTO(){
    
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public String getNep() {
        return nep;
    }

    public void setNep(String nep) {
        this.nep = nep;
    }

    public String getNumeroProcedimiento() {
        return numeroProcedimiento;
    }

    public void setNumeroProcedimiento(String numeroProcedimiento) {
        this.numeroProcedimiento = numeroProcedimiento;
    }

    public String getFundamentoLegal() {
        return fundamentoLegal;
    }

    public void setFundamentoLegal(String fundamentoLegal) {
        this.fundamentoLegal = fundamentoLegal;
    }

    public String getDescripcionFundamento() {
        return descripcionFundamento;
    }

    public void setDescripcionFundamento(String descripcionFundamento) {
        this.descripcionFundamento = descripcionFundamento;
    }

    public Date getVigenciaInicial() {
        return vigenciaInicial;
    }

    public void setVigenciaInicial(Date vigenciaInicial) {
        this.vigenciaInicial = vigenciaInicial;
    }

    public Date getVigenciaFinal() {
        return vigenciaFinal;
    }

    public void setVigenciaFinal(Date vigenciaFinal) {
        this.vigenciaFinal = vigenciaFinal;
    }

    public String getCompradorNombre() {
        return compradorNombre;
    }

    public void setCompradorNombre(String compradorNombre) {
        this.compradorNombre = compradorNombre;
    }

    public String getCompradorApellido() {
        return compradorApellido;
    }

    public void setCompradorApellido(String compradorApellido) {
        this.compradorApellido = compradorApellido;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public List<InsumosDTO> getInsumosList() {
        return insumosList;
    }

    public void setInsumosList(List<InsumosDTO> insumosList) {
        this.insumosList = insumosList;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public String getNumeroConvenio() {
        return numeroConvenio;
    }

    public void setNumeroConvenio(String numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
    }

    public String getClausulas() {
        return clausulas;
    }

    public void setClausulas(String clausulas) {
        this.clausulas = clausulas;
    }

    public String getImporteLetra() {
        return importeLetra;
    }

    public void setImporteLetra(String importeLetra) {
        this.importeLetra = importeLetra;
    } 

    public Integer getNoRupa() {
        return noRupa;
    }

    public void setNoRupa(Integer noRupa) {
        this.noRupa = noRupa;
    }

    public String getRcb() {
        return rcb;
    }

    public void setRcb(String rcb) {
        this.rcb = rcb;
    }

    public String getOficioSuficienciaPresupuestal() {
        return oficioSuficienciaPresupuestal;
    }

    public void setOficioSuficienciaPresupuestal(String oficioSuficienciaPresupuestal) {
        this.oficioSuficienciaPresupuestal = oficioSuficienciaPresupuestal;
    }

    public String getCompradorApellidoMaterno() {
        return compradorApellidoMaterno;
    }

    public void setCompradorApellidoMaterno(String compradorApellidoMaterno) {
        this.compradorApellidoMaterno = compradorApellidoMaterno;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    } 

    public String getSiglaTipoInsumos() {
        return siglaTipoInsumos;
    }

    public void setSiglaTipoInsumos(String siglaTipoInsumos) {
        this.siglaTipoInsumos = siglaTipoInsumos;
    }

    public String getRutaLogo() {
        return rutaLogo;
    }

    public void setRutaLogo(String rutaLogo) {
        this.rutaLogo = rutaLogo;
    }

    public String getTelefonoProv() {
        return telefonoProv;
    }

    public void setTelefonoProv(String telefonoProv) {
        this.telefonoProv = telefonoProv;
    }

    public String getRfcProv() {
        return rfcProv;
    }

    public void setRfcProv(String rfcProv) {
        this.rfcProv = rfcProv;
    }

    public String getClavePresupuestal() {
        return clavePresupuestal;
    }

    public void setClavePresupuestal(String clavePresupuestal) {
        this.clavePresupuestal = clavePresupuestal;
    }

    public BigDecimal getImporteMin() {
        return importeMin;
    }

    public void setImporteMin(BigDecimal importeMin) {
        this.importeMin = importeMin;
    }

    public Date getFechaSuficienciaPresupuestal() {
        return fechaSuficienciaPresupuestal;
    }

    public void setFechaSuficienciaPresupuestal(Date fechaSuficienciaPresupuestal) {
        this.fechaSuficienciaPresupuestal = fechaSuficienciaPresupuestal;
    }

    public String getCompradorIniciales() {
        return compradorIniciales;
    }

    public void setCompradorIniciales(String compradorIniciales) {
        this.compradorIniciales = compradorIniciales;
    }
    
}
