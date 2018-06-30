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
@Table(name = "orden_suministro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdenSuministro.findAll", query = "SELECT o FROM OrdenSuministro o"),
    @NamedQuery(name = "OrdenSuministro.findByIdOrdenSuministro", query = "SELECT o FROM OrdenSuministro o WHERE o.idOrdenSuministro = :idOrdenSuministro"),
    @NamedQuery(name = "OrdenSuministro.findByActivo", query = "SELECT o FROM OrdenSuministro o WHERE o.activo = :activo"),
    @NamedQuery(name = "OrdenSuministro.findByCantidadSuministrar", query = "SELECT o FROM OrdenSuministro o WHERE o.cantidadSuministrar = :cantidadSuministrar"),
    @NamedQuery(name = "OrdenSuministro.findByNumeroOrden", query = "SELECT o FROM OrdenSuministro o WHERE o.numeroOrden = :numeroOrden"),
    @NamedQuery(name = "OrdenSuministro.findByFechaOrden", query = "SELECT o FROM OrdenSuministro o WHERE o.fechaOrden = :fechaOrden"),
    @NamedQuery(name = "OrdenSuministro.findByUsuarioAlta", query = "SELECT o FROM OrdenSuministro o WHERE o.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "OrdenSuministro.findByUsuarioBaja", query = "SELECT o FROM OrdenSuministro o WHERE o.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "OrdenSuministro.findByUsuarioModificacion", query = "SELECT o FROM OrdenSuministro o WHERE o.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "OrdenSuministro.findByFechaAlta", query = "SELECT o FROM OrdenSuministro o WHERE o.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "OrdenSuministro.findByFechaBaja", query = "SELECT o FROM OrdenSuministro o WHERE o.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "OrdenSuministro.findByFechaModificacion", query = "SELECT o FROM OrdenSuministro o WHERE o.fechaModificacion = :fechaModificacion")})
public class OrdenSuministro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_orden_suministro")
    private Integer idOrdenSuministro;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "cantidad_suministrar")
    private Integer cantidadSuministrar;
    @Column(name = "importe")
    private BigDecimal importe;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 200)
    @Column(name = "numero_orden")
    private String numeroOrden;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "fecha_orden")
    @Temporal(TemporalType.DATE)
    private Date fechaOrden;
    @Column(name = "pre_orden")
    private Integer preOrden;
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
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private Estatus idEstatus;
    @JoinColumn(name = "id_contrato", referencedColumnName = "id_contrato")
    @ManyToOne(optional = false)
    private Contratos idContrato;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrdenSuministro")
    private List<DetalleOrdenSuministro> detalleOrdenSuministroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrdenSuministro")
    private List<ProcedimientoRcbDestinosOrden> procedimientoRcbDestinosOrdenList;

    public OrdenSuministro() {
    }

    public OrdenSuministro(Integer idOrdenSuministro) {
        this.idOrdenSuministro = idOrdenSuministro;
    }

    public OrdenSuministro(Integer idOrdenSuministro, String numeroOrden) {
        this.idOrdenSuministro = idOrdenSuministro;
        this.numeroOrden = numeroOrden;
    }

    public Integer getIdOrdenSuministro() {
        return idOrdenSuministro;
    }

    public void setIdOrdenSuministro(Integer idOrdenSuministro) {
        this.idOrdenSuministro = idOrdenSuministro;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getCantidadSuministrar() {
        return cantidadSuministrar;
    }

    public void setCantidadSuministrar(Integer cantidadSuministrar) {
        this.cantidadSuministrar = cantidadSuministrar;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
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

    public Estatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Estatus idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Contratos getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Contratos idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getPreOrden() {
        return preOrden;
    }

    public void setPreOrden(Integer preOrden) {
        this.preOrden = preOrden;
    }

    @XmlTransient
    public List<DetalleOrdenSuministro> getDetalleOrdenSuministroList() {
        return detalleOrdenSuministroList;
    }

    public void setDetalleOrdenSuministroList(List<DetalleOrdenSuministro> detalleOrdenSuministroList) {
        this.detalleOrdenSuministroList = detalleOrdenSuministroList;
    }

    @XmlTransient
    public List<ProcedimientoRcbDestinosOrden> getProcedimientoRcbDestinosOrdenList() {
        return procedimientoRcbDestinosOrdenList;
    }

    public void setProcedimientoRcbDestinosOrdenList(List<ProcedimientoRcbDestinosOrden> procedimientoRcbDestinosOrdenList) {
        this.procedimientoRcbDestinosOrdenList = procedimientoRcbDestinosOrdenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdenSuministro != null ? idOrdenSuministro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenSuministro)) {
            return false;
        }
        OrdenSuministro other = (OrdenSuministro) object;
        if ((this.idOrdenSuministro == null && other.idOrdenSuministro != null) || (this.idOrdenSuministro != null && !this.idOrdenSuministro.equals(other.idOrdenSuministro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.OrdenSuministro[ idOrdenSuministro=" + idOrdenSuministro + " ]";
    }

}
