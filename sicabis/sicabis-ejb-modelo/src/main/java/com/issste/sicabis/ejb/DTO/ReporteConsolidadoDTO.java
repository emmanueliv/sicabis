package com.issste.sicabis.ejb.DTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author fabianvr
 */
public class ReporteConsolidadoDTO {

    private Integer renglon;
    private String clave;
    private Date fechaRCB;
    private String oficioRCB;
    private Integer cantidadPiezasRCB;
    private BigDecimal precioUnitarioRCB;
    private BigDecimal importeRCB;
    private String oficioSuficiencia;
    private Date fechaOficioSuficiencia;
    private String partida;
    private String nep;
    private String descripcionInsumo;
    private String unidad;
    private String fundamentoLegal;
    private Date fechaFallo;
    private String tipoProcedimeinto;
    private String caracter;
    private String procedimiento;
    private String tipoCompra;
    private String nombreProveedor;
    private String tipoContrato;
    private String contrato;
    private String convenio;
    private String tipoConvenio;
    private Date fechaContrato;
    private Date fechaFormalizacion;
    private Date vigenciaInicial;
    private Date vigenciaFinal;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal importeContrato;
    private String fabricante;
    private String rfc;
    private String tipoProveedor;
    private Date elaboracionConvocatoria;
    private Date publicacionConvocatoria;
    private Date invitacion3Per;
    private Date juntaAclaracionesConvocatoria;
    private Date juntaConclusionAclaracionesConvocatoria;
    private Date aperturaRealizada;
    private Date aperturaProgramada;
    private Date fechaProgramadaFallo;

    public void ReporteConsolidadoDTO() {
    }

    public Integer getRenglon() {
        return renglon;
    }

    public void setRenglon(Integer renglon) {
        this.renglon = renglon;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaRCB() {
        return fechaRCB;
    }

    public void setFechaRCB(Date fechaRCB) {
        this.fechaRCB = fechaRCB;
    }

    public String getOficioRCB() {
        return oficioRCB;
    }

    public void setOficioRCB(String oficioRCB) {
        this.oficioRCB = oficioRCB;
    }

    public Integer getCantidadPiezasRCB() {
        return cantidadPiezasRCB;
    }

    public void setCantidadPiezasRCB(Integer cantidadPiezasRCB) {
        cantidadPiezasRCB = cantidadPiezasRCB != null ? cantidadPiezasRCB : 0;
        this.cantidadPiezasRCB = cantidadPiezasRCB;
    }

    public BigDecimal getPrecioUnitarioRCB() {
        return precioUnitarioRCB;
    }

    public void setPrecioUnitarioRCB(BigDecimal precioUnitarioRCB) {
        this.precioUnitarioRCB = precioUnitarioRCB;
    }

    public BigDecimal getImporteRCB() {
        return importeRCB;
    }

    public void setImporteRCB(BigDecimal importeRCB) {
        this.importeRCB = importeRCB;
    }

    public String getOficioSuficiencia() {
        return oficioSuficiencia;
    }

    public void setOficioSuficiencia(String oficioSuficiencia) {
        this.oficioSuficiencia = oficioSuficiencia;
    }

    public Date getFechaOficioSuficiencia() {
        return fechaOficioSuficiencia;
    }

    public void setFechaOficioSuficiencia(Date fechaOficioSuficiencia) {
        this.fechaOficioSuficiencia = fechaOficioSuficiencia;
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

    public String getDescripcionInsumo() {
        return descripcionInsumo;
    }

    public void setDescripcionInsumo(String descripcionInsumo) {
        this.descripcionInsumo = descripcionInsumo;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getFundamentoLegal() {
        return fundamentoLegal;
    }

    public void setFundamentoLegal(String fundamentoLegal) {
        this.fundamentoLegal = fundamentoLegal;
    }

    public Date getFechaFallo() {
        return fechaFallo;
    }

    public void setFechaFallo(Date fechaFallo) {
        this.fechaFallo = fechaFallo;
    }

    public String getTipoProcedimeinto() {
        return tipoProcedimeinto;
    }

    public void setTipoProcedimeinto(String tipoProcedimeinto) {
        this.tipoProcedimeinto = tipoProcedimeinto;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public String getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(String tipoCompra) {
        this.tipoCompra = tipoCompra;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getTipoConvenio() {
        return tipoConvenio;
    }

    public void setTipoConvenio(String tipoConvenio) {
        this.tipoConvenio = tipoConvenio;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public Date getFechaFormalizacion() {
        return fechaFormalizacion;
    }

    public void setFechaFormalizacion(Date fechaFormalizacion) {
        this.fechaFormalizacion = fechaFormalizacion;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        cantidad = cantidad != null ? cantidad : 0;
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getImporteContrato() {
        return importeContrato;
    }

    public void setImporteContrato(BigDecimal importeContrato) {
        this.importeContrato = importeContrato;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public Date getElaboracionConvocatoria() {
        return elaboracionConvocatoria;
    }

    public void setElaboracionConvocatoria(Date elaboracionConvocatoria) {
        this.elaboracionConvocatoria = elaboracionConvocatoria;
    }

    public Date getPublicacionConvocatoria() {
        return publicacionConvocatoria;
    }

    public void setPublicacionConvocatoria(Date publicacionConvocatoria) {
        this.publicacionConvocatoria = publicacionConvocatoria;
    }

    public Date getInvitacion3Per() {
        return invitacion3Per;
    }

    public void setInvitacion3Per(Date invitacion3Per) {
        invitacion3Per = invitacion3Per != null ? invitacion3Per : new Date("0000-00-00");
        this.invitacion3Per = invitacion3Per;
    }

    public Date getJuntaAclaracionesConvocatoria() {
        return juntaAclaracionesConvocatoria;
    }

    public void setJuntaAclaracionesConvocatoria(Date juntaAclaracionesConvocatoria) {
        this.juntaAclaracionesConvocatoria = juntaAclaracionesConvocatoria;
    }

    public Date getJuntaConclusionAclaracionesConvocatoria() {
        return juntaConclusionAclaracionesConvocatoria;
    }

    public void setJuntaConclusionAclaracionesConvocatoria(Date juntaConclusionAclaracionesConvocatoria) {
        this.juntaConclusionAclaracionesConvocatoria = juntaConclusionAclaracionesConvocatoria;
    }

    public Date getAperturaRealizada() {
        return aperturaRealizada;
    }

    public void setAperturaRealizada(Date aperturaRealizada) {
        this.aperturaRealizada = aperturaRealizada;
    }

    public Date getAperturaProgramada() {
        return aperturaProgramada;
    }

    public void setAperturaProgramada(Date aperturaProgramada) {
        this.aperturaProgramada = aperturaProgramada;
    }

    public Date getFechaProgramadaFallo() {
        return fechaProgramadaFallo;
    }

    public void setFechaProgramadaFallo(Date fechaProgramadaFallo) {
        this.fechaProgramadaFallo = fechaProgramadaFallo;
    }

}
