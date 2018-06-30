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
@Table(name = "detalle_recoleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleRecoleccion.findAll", query = "SELECT d FROM DetalleRecoleccion d"),
    @NamedQuery(name = "DetalleRecoleccion.findByIdDetalleRecoleccion", query = "SELECT d FROM DetalleRecoleccion d WHERE d.idDetalleRecoleccion = :idDetalleRecoleccion"),
    @NamedQuery(name = "DetalleRecoleccion.findByActivo", query = "SELECT d FROM DetalleRecoleccion d WHERE d.activo = :activo"),
    @NamedQuery(name = "DetalleRecoleccion.findByCantidad", query = "SELECT d FROM DetalleRecoleccion d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetalleRecoleccion.findByFechaCaducidad", query = "SELECT d FROM DetalleRecoleccion d WHERE d.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "DetalleRecoleccion.findByPrecioPromedio", query = "SELECT d FROM DetalleRecoleccion d WHERE d.precioPromedio = :precioPromedio"),
    @NamedQuery(name = "DetalleRecoleccion.findByUsuarioAlta", query = "SELECT d FROM DetalleRecoleccion d WHERE d.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "DetalleRecoleccion.findByUsuarioBaja", query = "SELECT d FROM DetalleRecoleccion d WHERE d.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "DetalleRecoleccion.findByUsuarioModificacion", query = "SELECT d FROM DetalleRecoleccion d WHERE d.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "DetalleRecoleccion.findByFechaAlta", query = "SELECT d FROM DetalleRecoleccion d WHERE d.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "DetalleRecoleccion.findByFechaBaja", query = "SELECT d FROM DetalleRecoleccion d WHERE d.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "DetalleRecoleccion.findByFechaModificaciones", query = "SELECT d FROM DetalleRecoleccion d WHERE d.fechaModificaciones = :fechaModificaciones")})
public class DetalleRecoleccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_recoleccion")
    private Integer idDetalleRecoleccion;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private Integer cantidad;
    @Size(max = 45)
    @Column(name = "lote")
    private String lote;
    @Basic(optional = false)
    @Column(name = "fecha_caducidad")
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidad;
    @Column(name = "precio_promedio")
    private BigDecimal precioPromedio;
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
    @JoinColumn(name = "id_recoleccion", referencedColumnName = "id_recoleccion")
    @ManyToOne(optional = false)
    private Recoleccion idRecoleccion;
    @JoinColumn(name = "id_insumo", referencedColumnName = "id_insumo")
    @ManyToOne(optional = false)
    private Insumos idInsumo;

    public DetalleRecoleccion() {
    }

    public DetalleRecoleccion(Integer idDetalleRecoleccion) {
        this.idDetalleRecoleccion = idDetalleRecoleccion;
    }

    public DetalleRecoleccion(Integer idDetalleRecoleccion, Integer cantidad, Date fechaCaducidad) {
        this.idDetalleRecoleccion = idDetalleRecoleccion;
        this.cantidad = cantidad;
        this.fechaCaducidad = fechaCaducidad;
    }

    public Integer getIdDetalleRecoleccion() {
        return idDetalleRecoleccion;
    }

    public void setIdDetalleRecoleccion(Integer idDetalleRecoleccion) {
        this.idDetalleRecoleccion = idDetalleRecoleccion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public BigDecimal getPrecioPromedio() {
        return precioPromedio;
    }

    public void setPrecioPromedio(BigDecimal precioPromedio) {
        this.precioPromedio = precioPromedio;
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

    public Recoleccion getIdRecoleccion() {
        return idRecoleccion;
    }

    public void setIdRecoleccion(Recoleccion idRecoleccion) {
        this.idRecoleccion = idRecoleccion;
    }

    public Insumos getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Insumos idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleRecoleccion != null ? idDetalleRecoleccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleRecoleccion)) {
            return false;
        }
        DetalleRecoleccion other = (DetalleRecoleccion) object;
        if ((this.idDetalleRecoleccion == null && other.idDetalleRecoleccion != null) || (this.idDetalleRecoleccion != null && !this.idDetalleRecoleccion.equals(other.idDetalleRecoleccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.DetalleRecoleccion[ idDetalleRecoleccion=" + idDetalleRecoleccion + " ]";
    }
    
}
