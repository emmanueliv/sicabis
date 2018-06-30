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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author 84JBBG2
 */
@Entity
@Table(name = "historico_existencia_por_clave_umus")
public class HistoricoExistenciaPorClaveUmus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historico_existencia_por_clave_umus")
    private Integer idHistoricoExistenciaPorClaveUmus;
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
    @Size(min = 1, max = 50)
    @Column(name = "clave")
    private String clave;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 30)
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

    public HistoricoExistenciaPorClaveUmus() {
    }

    public HistoricoExistenciaPorClaveUmus(Integer idHistoricoExistenciaPorClaveUmus) {
        this.idHistoricoExistenciaPorClaveUmus = idHistoricoExistenciaPorClaveUmus;
    }

    public HistoricoExistenciaPorClaveUmus(Integer idHistoricoExistenciaPorClaveUmus, String clave) {
        this.idHistoricoExistenciaPorClaveUmus = idHistoricoExistenciaPorClaveUmus;
        this.clave = clave;
    }

    public Integer getIdExistenciaPorClaveUmus() {
        return idHistoricoExistenciaPorClaveUmus;
    }

    public void setIdExistenciaPorClaveUmus(Integer idHistoricoExistenciaPorClaveUmus) {
        this.idHistoricoExistenciaPorClaveUmus = idHistoricoExistenciaPorClaveUmus;
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
        hash += (idHistoricoExistenciaPorClaveUmus != null ? idHistoricoExistenciaPorClaveUmus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoExistenciaPorClaveUmus)) {
            return false;
        }
        HistoricoExistenciaPorClaveUmus other = (HistoricoExistenciaPorClaveUmus) object;
        if ((this.idHistoricoExistenciaPorClaveUmus == null && other.idHistoricoExistenciaPorClaveUmus != null) || (this.idHistoricoExistenciaPorClaveUmus != null && !this.idHistoricoExistenciaPorClaveUmus.equals(other.idHistoricoExistenciaPorClaveUmus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.service.modelo.HistoricoExistenciaPorClaveUmus[ idHistoricoExistenciaPorClaveUmus=" + idHistoricoExistenciaPorClaveUmus + " ]";
    }

}
