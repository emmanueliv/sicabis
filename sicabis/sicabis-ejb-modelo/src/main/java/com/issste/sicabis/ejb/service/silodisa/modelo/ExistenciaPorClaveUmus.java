/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author 9RZCBG2
 */
@Entity
@Table(name = "existencia_por_clave_umus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExistenciaPorClaveUmus.findAll", query = "SELECT e FROM ExistenciaPorClaveUmus e"),
    @NamedQuery(name = "ExistenciaPorClaveUmus.findByIdExistenciaPorClaveUmus", query = "SELECT e FROM ExistenciaPorClaveUmus e WHERE e.idExistenciaPorClaveUmus = :idExistenciaPorClaveUmus"),
    @NamedQuery(name = "ExistenciaPorClaveUmus.findByFechaInventario", query = "SELECT e FROM ExistenciaPorClaveUmus e WHERE e.fechaInventario = :fechaInventario"),
    @NamedQuery(name = "ExistenciaPorClaveUmus.findByNombreDelegacion", query = "SELECT e FROM ExistenciaPorClaveUmus e WHERE e.nombreDelegacion = :nombreDelegacion"),
    @NamedQuery(name = "ExistenciaPorClaveUmus.findByClaDelegacion", query = "SELECT e FROM ExistenciaPorClaveUmus e WHERE e.claDelegacion = :claDelegacion"),
    @NamedQuery(name = "ExistenciaPorClaveUmus.findByUmu", query = "SELECT e FROM ExistenciaPorClaveUmus e WHERE e.umu = :umu"),
    @NamedQuery(name = "ExistenciaPorClaveUmus.findByDescUmu", query = "SELECT e FROM ExistenciaPorClaveUmus e WHERE e.descUmu = :descUmu"),
    @NamedQuery(name = "ExistenciaPorClaveUmus.findByClave", query = "SELECT e FROM ExistenciaPorClaveUmus e WHERE e.clave = :clave"),
    @NamedQuery(name = "ExistenciaPorClaveUmus.findByNombre", query = "SELECT e FROM ExistenciaPorClaveUmus e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "ExistenciaPorClaveUmus.findByTipo", query = "SELECT e FROM ExistenciaPorClaveUmus e WHERE e.tipo = :tipo"),
    @NamedQuery(name = "ExistenciaPorClaveUmus.findByLote", query = "SELECT e FROM ExistenciaPorClaveUmus e WHERE e.lote = :lote"),
    @NamedQuery(name = "ExistenciaPorClaveUmus.findByFechaCaducidad", query = "SELECT e FROM ExistenciaPorClaveUmus e WHERE e.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "ExistenciaPorClaveUmus.findByExistencia", query = "SELECT e FROM ExistenciaPorClaveUmus e WHERE e.existencia = :existencia"),
    @NamedQuery(name = "ExistenciaPorClaveUmus.findByPrecioUnitario", query = "SELECT e FROM ExistenciaPorClaveUmus e WHERE e.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "ExistenciaPorClaveUmus.findByImporte", query = "SELECT e FROM ExistenciaPorClaveUmus e WHERE e.importe = :importe"),
    @NamedQuery(name = "ExistenciaPorClaveUmus.findByFechaIngreso", query = "SELECT e FROM ExistenciaPorClaveUmus e WHERE e.fechaIngreso = :fechaIngreso")})
public class ExistenciaPorClaveUmus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_existencia_por_clave_umus")
    private Integer idExistenciaPorClaveUmus;
    @Column(name = "fecha_inventario")
    @Temporal(TemporalType.DATE)
    private Date fechaInventario;
    @Size(max = 50)
    @Column(name = "nombre_delegacion")
    private String nombreDelegacion;
    @Size(max = 50)
    @Column(name = "cla_delegacion")
    private String claDelegacion;
    @Size(max = 20)
    @Column(name = "umu")
    private String umu;
    @Size(max = 100)
    @Column(name = "desc_umu")
    private String descUmu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clave")
    private String clave;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 100)
    @Column(name = "lote")
    private String lote;
    @Column(name = "fecha_caducidad")
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidad;
    @Size(max = 20)
    @Column(name = "existencia")
    private String existencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Column(name = "importe")
    private BigDecimal importe;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    public ExistenciaPorClaveUmus() {
    }

    public ExistenciaPorClaveUmus(Integer idExistenciaPorClaveUmus) {
        this.idExistenciaPorClaveUmus = idExistenciaPorClaveUmus;
    }

    public ExistenciaPorClaveUmus(Integer idExistenciaPorClaveUmus, String clave) {
        this.idExistenciaPorClaveUmus = idExistenciaPorClaveUmus;
        this.clave = clave;
    }

    public Integer getIdExistenciaPorClaveUmus() {
        return idExistenciaPorClaveUmus;
    }

    public void setIdExistenciaPorClaveUmus(Integer idExistenciaPorClaveUmus) {
        this.idExistenciaPorClaveUmus = idExistenciaPorClaveUmus;
    }

    public Date getFechaInventario() {
        return fechaInventario;
    }

    public void setFechaInventario(Date fechaInventario) {
        this.fechaInventario = fechaInventario;
    }

    public String getNombreDelegacion() {
        return nombreDelegacion;
    }

    public void setNombreDelegacion(String nombreDelegacion) {
        this.nombreDelegacion = nombreDelegacion;
    }

    public String getClaDelegacion() {
        return claDelegacion;
    }

    public void setClaDelegacion(String claDelegacion) {
        this.claDelegacion = claDelegacion;
    }

    public String getUmu() {
        return umu;
    }

    public void setUmu(String umu) {
        this.umu = umu;
    }

    public String getDescUmu() {
        return descUmu;
    }

    public void setDescUmu(String descUmu) {
        this.descUmu = descUmu;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getExistencia() {
        return existencia;
    }

    public void setExistencia(String existencia) {
        this.existencia = existencia;
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExistenciaPorClaveUmus != null ? idExistenciaPorClaveUmus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExistenciaPorClaveUmus)) {
            return false;
        }
        ExistenciaPorClaveUmus other = (ExistenciaPorClaveUmus) object;
        if ((this.idExistenciaPorClaveUmus == null && other.idExistenciaPorClaveUmus != null) || (this.idExistenciaPorClaveUmus != null && !this.idExistenciaPorClaveUmus.equals(other.idExistenciaPorClaveUmus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveUmus[ idExistenciaPorClaveUmus=" + idExistenciaPorClaveUmus + " ]";
    }
    
}
