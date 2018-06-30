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
@Table(name = "cr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cr.findAll", query = "SELECT c FROM Cr c"),
    @NamedQuery(name = "Cr.findByIdCr", query = "SELECT c FROM Cr c WHERE c.idCr = :idCr"),
    @NamedQuery(name = "Cr.findByActivo", query = "SELECT c FROM Cr c WHERE c.activo = :activo"),
    @NamedQuery(name = "Cr.findByNumeroCr", query = "SELECT c FROM Cr c WHERE c.numeroCr = :numeroCr"),
    @NamedQuery(name = "Cr.findByImporte", query = "SELECT c FROM Cr c WHERE c.importeTotal = :importeTotal"),
    @NamedQuery(name = "Cr.findByUsuarioAlta", query = "SELECT c FROM Cr c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Cr.findByUsuarioBaja", query = "SELECT c FROM Cr c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Cr.findByUsuarioModificacion", query = "SELECT c FROM Cr c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Cr.findByFechaAlta", query = "SELECT c FROM Cr c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Cr.findByFechaBaja", query = "SELECT c FROM Cr c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Cr.findByFechaModificaciones", query = "SELECT c FROM Cr c WHERE c.fechaModificaciones = :fechaModificaciones")})
public class Cr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cr")
    private Integer idCr;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "numero_cr")
    private String numeroCr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe_total")
    private BigDecimal importeTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ejercicio")
    private int ejercicio;
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
    @JoinColumn(name = "id_unidad_responsable", referencedColumnName = "id_unidad_responsable")
    @ManyToOne(optional = false)
    private UnidadResponsable idUnidadResponsable;
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private Estatus idEstatus;
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne(optional = false)
    private Area idArea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCr")
    private List<CrInsumos> crInsumosList;

    public Cr() {
    }

    public Cr(Integer idCr) {
        this.idCr = idCr;
    }

    public Cr(Integer idCr, String numeroCr, BigDecimal importeTotal, int ejercicio) {
        this.idCr = idCr;
        this.numeroCr = numeroCr;
        this.importeTotal = importeTotal;
        this.ejercicio = ejercicio;
    }

    public Integer getIdCr() {
        return idCr;
    }

    public void setIdCr(Integer idCr) {
        this.idCr = idCr;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getNumeroCr() {
        return numeroCr;
    }

    public void setNumeroCr(String numeroCr) {
        this.numeroCr = numeroCr;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
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

    public UnidadResponsable getIdUnidadResponsable() {
        return idUnidadResponsable;
    }

    public void setIdUnidadResponsable(UnidadResponsable idUnidadResponsable) {
        this.idUnidadResponsable = idUnidadResponsable;
    }

    @XmlTransient
    public List<CrInsumos> getCrInsumosList() {
        return crInsumosList;
    }

    public void setCrInsumosList(List<CrInsumos> crInsumosList) {
        this.crInsumosList = crInsumosList;
    }

    public int getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(int ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Estatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Estatus idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCr != null ? idCr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cr)) {
            return false;
        }
        Cr other = (Cr) object;
        if ((this.idCr == null && other.idCr != null) || (this.idCr != null && !this.idCr.equals(other.idCr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Cr[ idCr=" + idCr +"numeroCr "+numeroCr+" importeTotal "+importeTotal.toString()+" ejercicio "+ejercicio+ " ]";
    }
    
}
