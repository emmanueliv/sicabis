/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kriosoft
 */
@Entity
@Table(name = "cr_insumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrInsumos.findAll", query = "SELECT c FROM CrInsumos c"),
    @NamedQuery(name = "CrInsumos.findByIdCrInsumos", query = "SELECT c FROM CrInsumos c WHERE c.idCrInsumos = :idCrInsumos"),
    @NamedQuery(name = "CrInsumos.findByActivo", query = "SELECT c FROM CrInsumos c WHERE c.activo = :activo"),
    @NamedQuery(name = "CrInsumos.findByExistencias", query = "SELECT c FROM CrInsumos c WHERE c.existencias = :existencias"),
    @NamedQuery(name = "CrInsumos.findByConsumoPromedio", query = "SELECT c FROM CrInsumos c WHERE c.consumoPromedio = :consumoPromedio"),
    @NamedQuery(name = "CrInsumos.findByMesesCobertura", query = "SELECT c FROM CrInsumos c WHERE c.mesesCobertura = :mesesCobertura"),
    @NamedQuery(name = "CrInsumos.findByPrecioUnitario", query = "SELECT c FROM CrInsumos c WHERE c.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "CrInsumos.findByImporte", query = "SELECT c FROM CrInsumos c WHERE c.importe = :importe"),
    @NamedQuery(name = "CrInsumos.findByUsuarioAlta", query = "SELECT c FROM CrInsumos c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "CrInsumos.findByUsuarioBaja", query = "SELECT c FROM CrInsumos c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "CrInsumos.findByUsuarioModificacion", query = "SELECT c FROM CrInsumos c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "CrInsumos.findByFechaAlta", query = "SELECT c FROM CrInsumos c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "CrInsumos.findByFechaBaja", query = "SELECT c FROM CrInsumos c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "CrInsumos.findByFechaModificacion", query = "SELECT c FROM CrInsumos c WHERE c.fechaModificacion = :fechaModificacion")})
public class CrInsumos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cr_insumos")
    private Integer idCrInsumos;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_piezas")
    private int cantidadPiezas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existencias")
    private int existencias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consumo_promedio")
    private int consumoPromedio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "meses_cobertura")
    private int mesesCobertura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe")
    private BigDecimal importe;
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
    @JoinColumn(name = "id_tipo_compra", referencedColumnName = "id_tipo_compra")
    @ManyToOne(optional = false)
    private TipoCompra idTipoCompra;
    @JoinColumn(name = "id_insumo", referencedColumnName = "id_insumo")
    @ManyToOne(optional = false)
    private Insumos idInsumo;
    @JoinColumn(name = "id_cr", referencedColumnName = "id_cr")
    @ManyToOne(optional = false)
    private Cr idCr;

    public CrInsumos() {
    }

    public CrInsumos(Integer idCrInsumos) {
        this.idCrInsumos = idCrInsumos;
    }

    public CrInsumos(Integer idCrInsumos, int existencias, int consumoPromedio, int mesesCobertura, BigDecimal precioUnitario, BigDecimal importe) {
        this.idCrInsumos = idCrInsumos;
        this.existencias = existencias;
        this.consumoPromedio = consumoPromedio;
        this.mesesCobertura = mesesCobertura;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
    }

    public Integer getIdCrInsumos() {
        return idCrInsumos;
    }

    public void setIdCrInsumos(Integer idCrInsumos) {
        this.idCrInsumos = idCrInsumos;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public int getConsumoPromedio() {
        return consumoPromedio;
    }

    public void setConsumoPromedio(int consumoPromedio) {
        this.consumoPromedio = consumoPromedio;
    }

    public int getMesesCobertura() {
        return mesesCobertura;
    }

    public void setMesesCobertura(int mesesCobertura) {
        this.mesesCobertura = mesesCobertura;
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

    public TipoCompra getIdTipoCompra() {
        return idTipoCompra;
    }

    public void setIdTipoCompra(TipoCompra idTipoCompra) {
        this.idTipoCompra = idTipoCompra;
    }

    public Insumos getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Insumos idInsumo) {
        this.idInsumo = idInsumo;
    }

    public Cr getIdCr() {
        return idCr;
    }

    public void setIdCr(Cr idCr) {
        this.idCr = idCr;
    }

    public int getCantidadPiezas() {
        return cantidadPiezas;
    }

    public void setCantidadPiezas(int cantidadPiezas) {
        this.cantidadPiezas = cantidadPiezas;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCrInsumos != null ? idCrInsumos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrInsumos)) {
            return false;
        }
        CrInsumos other = (CrInsumos) object;
        if ((this.idCrInsumos == null && other.idCrInsumos != null) || (this.idCrInsumos != null && !this.idCrInsumos.equals(other.idCrInsumos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.CrInsumos[ idCrInsumos=" + idCrInsumos + " ]";
    }
    
}
