/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kriosoft
 */
@Entity
@Table(name = "fallo_procedimiento_rcb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FalloProcedimientoRcb.findAll", query = "SELECT f FROM FalloProcedimientoRcb f"),
    @NamedQuery(name = "FalloProcedimientoRcb.findByIdFalloProcedimientoRcb", query = "SELECT f FROM FalloProcedimientoRcb f WHERE f.idFalloProcedimientoRcb = :idFalloProcedimientoRcb"),
    @NamedQuery(name = "FalloProcedimientoRcb.findByActivo", query = "SELECT f FROM FalloProcedimientoRcb f WHERE f.activo = :activo"),
    @NamedQuery(name = "FalloProcedimientoRcb.findByPorcentajeAdjudicacion", query = "SELECT f FROM FalloProcedimientoRcb f WHERE f.porcentajeAdjudicacion = :porcentajeAdjudicacion"),
    @NamedQuery(name = "FalloProcedimientoRcb.findByCantidadPiezas", query = "SELECT f FROM FalloProcedimientoRcb f WHERE f.cantidadPiezas = :cantidadPiezas"),
    @NamedQuery(name = "FalloProcedimientoRcb.findByPrecioUnitario", query = "SELECT f FROM FalloProcedimientoRcb f WHERE f.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "FalloProcedimientoRcb.findByCantidadModificada", query = "SELECT f FROM FalloProcedimientoRcb f WHERE f.cantidadModificada = :cantidadModificada"),
    @NamedQuery(name = "FalloProcedimientoRcb.findByUnidadPieza", query = "SELECT f FROM FalloProcedimientoRcb f WHERE f.idFalloProcedimientoRcb = :idFalloProcedimientoRcb"),
    @NamedQuery(name = "FalloProcedimientoRcb.findByUsuarioAlta", query = "SELECT f FROM FalloProcedimientoRcb f WHERE f.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "FalloProcedimientoRcb.findByUsuarioBaja", query = "SELECT f FROM FalloProcedimientoRcb f WHERE f.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "FalloProcedimientoRcb.findByUsuarioModificacion", query = "SELECT f FROM FalloProcedimientoRcb f WHERE f.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "FalloProcedimientoRcb.findByFechaAlta", query = "SELECT f FROM FalloProcedimientoRcb f WHERE f.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "FalloProcedimientoRcb.findByFechaBaja", query = "SELECT f FROM FalloProcedimientoRcb f WHERE f.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "FalloProcedimientoRcb.findByFechaModificacion", query = "SELECT f FROM FalloProcedimientoRcb f WHERE f.fechaModificacion = :fechaModificacion")})
public class FalloProcedimientoRcb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fallo_procedimiento_rcb")
    private Integer idFalloProcedimientoRcb;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "porcentaje_adjudicacion")
    private Integer porcentajeAdjudicacion;
    @Column(name = "cantidad_piezas")
    private Integer cantidadPiezas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Column(name = "importe")
    private BigDecimal importe;
    @Column(name = "cantidad_modificada")
    private Integer cantidadModificada;
    @Column(name = "cantidad_agregada_convenio")
    private Integer cantidadAgregadaConvenio;
    //@Size(max = 45)
    @Column(name = "unidad_pieza_convenio")
    private String unidadPiezaConvenio;
    @Column(name = "precio_unitario_original")
    private BigDecimal precioUnitarioOriginal;
    @Column(name = "suministrado_orden")
    private Integer suministradoOrden;
    @Column(name = "completado_contrato")
    private Integer completadoContrato;
    @Size(max = 45)
    @Column(name = "usuario_alta")
    private String usuarioAlta;
    @Size(max = 45)
    @Column(name = "usuario_baja")
    private String usuarioBaja;
    @Size(max = 45)
    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    @ManyToOne(optional = false)
    private Proveedores idProveedor;
    @JoinColumn(name = "id_procedimiento_rcb", referencedColumnName = "id_procedimiento_rcb")
    @ManyToOne(optional = false)
    private ProcedimientoRcb idProcedimientoRcb;
    @JoinColumn(name = "id_fallo", referencedColumnName = "id_fallo")
    @ManyToOne(optional = false)
    private Fallos idFallo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFalloProcedimientoRcb")
    private List<ContratoFalloProcedimientoRcb> contratoFalloProcedimientoRcbList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFalloProcedimientoRcb")
    private List<DetalleOrdenSuministro> detalleOrdenSuministroList;

    public FalloProcedimientoRcb() {
    }

    public FalloProcedimientoRcb(Integer idFalloProcedimientoRcb) {
        this.idFalloProcedimientoRcb = idFalloProcedimientoRcb;
    }

    public Integer getIdFalloProcedimientoRcb() {
        return idFalloProcedimientoRcb;
    }

    public void setIdFalloProcedimientoRcb(Integer idFalloProcedimientoRcb) {
        this.idFalloProcedimientoRcb = idFalloProcedimientoRcb;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getPorcentajeAdjudicacion() {
        return porcentajeAdjudicacion;
    }

    public void setPorcentajeAdjudicacion(Integer porcentajeAdjudicacion) {
        this.porcentajeAdjudicacion = porcentajeAdjudicacion;
    }

    public Integer getCantidadPiezas() {
        return cantidadPiezas;
    }

    public void setCantidadPiezas(Integer cantidadPiezas) {
        this.cantidadPiezas = cantidadPiezas;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
    
    public Integer getCantidadModificada() {
        return cantidadModificada;
    }

    public void setCantidadModificada(Integer cantidadModificada) {
        this.cantidadModificada = cantidadModificada;
    }

    public Integer getCantidadAgregadaConvenio() {
        return cantidadAgregadaConvenio;
    }

    public void setCantidadAgregadaConvenio(Integer cantidadAgregadaConvenio) {
        this.cantidadAgregadaConvenio = cantidadAgregadaConvenio;
    }

    public String getUnidadPiezaConvenio() {
        return unidadPiezaConvenio;
    }

    public void setUnidadPiezaConvenio(String unidadPiezaConvenio) {
        this.unidadPiezaConvenio = unidadPiezaConvenio;
    }

    public BigDecimal getPrecioUnitarioOriginal() {
        return precioUnitarioOriginal;
    }

    public void setPrecioUnitarioOriginal(BigDecimal precioUnitarioOriginal) {
        this.precioUnitarioOriginal = precioUnitarioOriginal;
    }

    public Integer getSuministradoOrden() {
        return suministradoOrden;
    }

    public void setSuministradoOrden(Integer suministradoOrden) {
        this.suministradoOrden = suministradoOrden;
    }

    public Integer getCompletadoContrato() {
        return completadoContrato;
    }

    public void setCompletadoContrato(Integer completadoContrato) {
        this.completadoContrato = completadoContrato;
    }
    
    public String getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public String getUsuarioBaja() {
        return usuarioBaja;
    }

    public void setUsuarioBaja(String usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Proveedores getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedores idProveedor) {
        this.idProveedor = idProveedor;
    }

    public ProcedimientoRcb getIdProcedimientoRcb() {
        return idProcedimientoRcb;
    }

    public void setIdProcedimientoRcb(ProcedimientoRcb idProcedimientoRcb) {
        this.idProcedimientoRcb = idProcedimientoRcb;
    }

    public Fallos getIdFallo() {
        return idFallo;
    }

    public void setIdFallo(Fallos idFallo) {
        this.idFallo = idFallo;
    }

    @XmlTransient
    public List<ContratoFalloProcedimientoRcb> getContratoFalloProcedimientoRcbList() {
        return contratoFalloProcedimientoRcbList;
    }

    public void setContratoFalloProcedimientoRcbList(List<ContratoFalloProcedimientoRcb> contratoFalloProcedimientoRcbList) {
        this.contratoFalloProcedimientoRcbList = contratoFalloProcedimientoRcbList;
    }

    @XmlTransient
    public List<DetalleOrdenSuministro> getDetalleOrdenSuministroList() {
        return detalleOrdenSuministroList;
    }

    public void setDetalleOrdenSuministroList(List<DetalleOrdenSuministro> detalleOrdenSuministroList) {
        this.detalleOrdenSuministroList = detalleOrdenSuministroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFalloProcedimientoRcb != null ? idFalloProcedimientoRcb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FalloProcedimientoRcb)) {
            return false;
        }
        FalloProcedimientoRcb other = (FalloProcedimientoRcb) object;
        if ((this.idFalloProcedimientoRcb == null && other.idFalloProcedimientoRcb != null) || (this.idFalloProcedimientoRcb != null && !this.idFalloProcedimientoRcb.equals(other.idFalloProcedimientoRcb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb[ idFalloProcedimientoRcb=" + idFalloProcedimientoRcb + " ]";
    }
    
}
