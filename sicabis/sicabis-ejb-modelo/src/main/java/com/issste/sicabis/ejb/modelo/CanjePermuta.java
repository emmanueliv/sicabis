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

/**
 *
 * @author kriosoft
 */
@Entity
@Table(name = "canje_permuta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CanjePermuta.findAll", query = "SELECT c FROM CanjePermuta c"),
    @NamedQuery(name = "CanjePermuta.findByIdCanjePermuta", query = "SELECT c FROM CanjePermuta c WHERE c.idCanjePermuta = :idCanjePermuta"),
    @NamedQuery(name = "CanjePermuta.findByActivo", query = "SELECT c FROM CanjePermuta c WHERE c.activo = :activo"),
    @NamedQuery(name = "CanjePermuta.findByCantidadInsumoOriginal", query = "SELECT c FROM CanjePermuta c WHERE c.cantidadInsumoOriginal = :cantidadInsumoOriginal"),
    @NamedQuery(name = "CanjePermuta.findByCantidadInsumoCanje", query = "SELECT c FROM CanjePermuta c WHERE c.cantidadInsumoCanje = :cantidadInsumoCanje"),
    @NamedQuery(name = "CanjePermuta.findByLote", query = "SELECT c FROM CanjePermuta c WHERE c.lote = :lote"),
    @NamedQuery(name = "CanjePermuta.findByPrecio", query = "SELECT c FROM CanjePermuta c WHERE c.precio = :precio"),
    @NamedQuery(name = "CanjePermuta.findByFechaCaducidad", query = "SELECT c FROM CanjePermuta c WHERE c.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "CanjePermuta.findByUsuarioAlta", query = "SELECT c FROM CanjePermuta c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "CanjePermuta.findByUsuarioBaja", query = "SELECT c FROM CanjePermuta c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "CanjePermuta.findByUsuarioModificacion", query = "SELECT c FROM CanjePermuta c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "CanjePermuta.findByFechaAlta", query = "SELECT c FROM CanjePermuta c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "CanjePermuta.findByFechaBaja", query = "SELECT c FROM CanjePermuta c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "CanjePermuta.findByFechaModificaciones", query = "SELECT c FROM CanjePermuta c WHERE c.fechaModificaciones = :fechaModificaciones")})
public class CanjePermuta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_canje_permuta")
    private Integer idCanjePermuta;
    @Column(name = "activo")
    private Integer activo;
    @Size(max = 45)
    @Column(name = "folio")
    private String folio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_insumo_original")
    private int cantidadInsumoOriginal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_insumo_canje")
    private int cantidadInsumoCanje;
    @Size(max = 45)
    @Column(name = "tipo_canje")
    private String tipoCanje;
    @Size(max = 45)
    @Column(name = "lote")
    private String lote;
    @Size(max = 45)
    @Column(name = "lote_entregado")
    private String loteEntregado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private BigDecimal precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_canje_permuta")
    private BigDecimal precioCanjePermuta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_caducidad")
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_caducidad_canje")
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidadCanje;
    @Basic(optional = false)
    @Column(name = "fecha_fabricacion_recibido")
    @Temporal(TemporalType.DATE)
    private Date fechaFabricacionRecibido;
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
    @Column(name = "fecha_modificaciones")
    @Temporal(TemporalType.DATE)
    private Date fechaModificaciones;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    @ManyToOne(optional = false)
    private Proveedores proveedor;
    @JoinColumn(name = "id_insumo", referencedColumnName = "id_insumo")
    @ManyToOne(optional = false)
    private Insumos idInsumo;
    @JoinColumn(name = "id_insumo_canje", referencedColumnName = "id_insumo")
    @ManyToOne(optional = false)
    private Insumos idInsumoCanje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCanjePermuta")
    private List<Remisiones> remisionesList;

    public CanjePermuta() {
    }

    public CanjePermuta(Integer idCanjePermuta) {
        this.idCanjePermuta = idCanjePermuta;
    }

    public CanjePermuta(Integer idCanjePermuta, int cantidadInsumoOriginal, int cantidadInsumoCanje, BigDecimal precio, Date fechaCaducidad) {
        this.idCanjePermuta = idCanjePermuta;
        this.cantidadInsumoOriginal = cantidadInsumoOriginal;
        this.cantidadInsumoCanje = cantidadInsumoCanje;
        this.precio = precio;
        this.fechaCaducidad = fechaCaducidad;
    }

    public Integer getIdCanjePermuta() {
        return idCanjePermuta;
    }

    public void setIdCanjePermuta(Integer idCanjePermuta) {
        this.idCanjePermuta = idCanjePermuta;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public int getCantidadInsumoOriginal() {
        return cantidadInsumoOriginal;
    }

    public void setCantidadInsumoOriginal(int cantidadInsumoOriginal) {
        this.cantidadInsumoOriginal = cantidadInsumoOriginal;
    }

    public int getCantidadInsumoCanje() {
        return cantidadInsumoCanje;
    }

    public void setCantidadInsumoCanje(int cantidadInsumoCanje) {
        this.cantidadInsumoCanje = cantidadInsumoCanje;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getTipoCanje() {
        return tipoCanje;
    }

    public void setTipoCanje(String tipoCanje) {
        this.tipoCanje = tipoCanje;
    }

    public BigDecimal getPrecioCanjePermuta() {
        return precioCanjePermuta;
    }

    public void setPrecioCanjePermuta(BigDecimal precioCanjePermuta) {
        this.precioCanjePermuta = precioCanjePermuta;
    }

    public Date getFechaCaducidadCanje() {
        return fechaCaducidadCanje;
    }

    public void setFechaCaducidadCanje(Date fechaCaducidadCanje) {
        this.fechaCaducidadCanje = fechaCaducidadCanje;
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

    public Date getFechaModificaciones() {
        return fechaModificaciones;
    }

    public void setFechaModificaciones(Date fechaModificaciones) {
        this.fechaModificaciones = fechaModificaciones;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public Insumos getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Insumos idInsumo) {
        this.idInsumo = idInsumo;
    }

    public Insumos getIdInsumoCanje() {
        return idInsumoCanje;
    }

    public void setIdInsumoCanje(Insumos idInsumoCanje) {
        this.idInsumoCanje = idInsumoCanje;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getLoteEntregado() {
        return loteEntregado;
    }

    public void setLoteEntregado(String loteEntregado) {
        this.loteEntregado = loteEntregado;
    }

    public List<Remisiones> getRemisionesList() {
        return remisionesList;
    }

    public void setRemisionesList(List<Remisiones> remisionesList) {
        this.remisionesList = remisionesList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCanjePermuta != null ? idCanjePermuta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CanjePermuta)) {
            return false;
        }
        CanjePermuta other = (CanjePermuta) object;
        if ((this.idCanjePermuta == null && other.idCanjePermuta != null) || (this.idCanjePermuta != null && !this.idCanjePermuta.equals(other.idCanjePermuta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.CanjePermuta[ idCanjePermuta=" + idCanjePermuta + " ]";
    }

    public Date getFechaFabricacionRecibido() {
        return fechaFabricacionRecibido;
    }

    public void setFechaFabricacionRecibido(Date fechaFabricacionRecibido) {
        this.fechaFabricacionRecibido = fechaFabricacionRecibido;
    }

}
