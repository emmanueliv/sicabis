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
@Table(name = "fallos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fallos.findAll", query = "SELECT f FROM Fallos f"),
    @NamedQuery(name = "Fallos.findByIdFallo", query = "SELECT f FROM Fallos f WHERE f.idFallo = :idFallo"),
    @NamedQuery(name = "Fallos.findByActivo", query = "SELECT f FROM Fallos f WHERE f.activo = :activo"),
    @NamedQuery(name = "Fallos.findByNumeroFallo", query = "SELECT f FROM Fallos f WHERE f.numeroFallo = :numeroFallo"),
    @NamedQuery(name = "Fallos.findByImporteTotal", query = "SELECT f FROM Fallos f WHERE f.importeTotal = :importeTotal"),
    @NamedQuery(name = "Fallos.findByAnioAfectacion", query = "SELECT f FROM Fallos f WHERE f.anioAfectacion = :anioAfectacion"),
    @NamedQuery(name = "Fallos.findByFechaFallo", query = "SELECT f FROM Fallos f WHERE f.fechaFallo = :fechaFallo"),
    @NamedQuery(name = "Fallos.findByComentarios", query = "SELECT f FROM Fallos f WHERE f.comentarios = :comentarios"),
    @NamedQuery(name = "Fallos.findByIncomformidad", query = "SELECT f FROM Fallos f WHERE f.incomformidad = :incomformidad"),
    @NamedQuery(name = "Fallos.findByUsuarioAlta", query = "SELECT f FROM Fallos f WHERE f.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Fallos.findByUsuarioBaja", query = "SELECT f FROM Fallos f WHERE f.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Fallos.findByUsuarioModificacion", query = "SELECT f FROM Fallos f WHERE f.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Fallos.findByFechaAlta", query = "SELECT f FROM Fallos f WHERE f.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Fallos.findByFechaBaja", query = "SELECT f FROM Fallos f WHERE f.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Fallos.findByFechaModificacion", query = "SELECT f FROM Fallos f WHERE f.fechaModificacion = :fechaModificacion")})
public class Fallos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fallo")
    private Integer idFallo;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "numero_fallo")
    private String numeroFallo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe_total")
    private BigDecimal importeTotal;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "anio_afectacion")
    private int anioAfectacion;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "fecha_fallo")
    @Temporal(TemporalType.DATE)
    private Date fechaFallo;
    @Size(max = 200)
    @Column(name = "comentarios")
    private String comentarios;
    @Column(name = "incomformidad")
    private Integer incomformidad;
    @Size(max = 200)
    @Column(name = "lista_fallos_dto")
    private String listaFallosDto;
    @Size(max = 200)
    @Column(name = "lista_fallos_dto_select")
    private String listaFallosDtoSelect;
    @Column(name = "numero_procedimiento")
    private String numeroProcedimiento;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFallo")
    private List<FalloProcedimientoRcb> falloProcedimientoRcbList;
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private Estatus idEstatus;

    public Fallos() {
    }

    public Fallos(Integer idFallo) {
        this.idFallo = idFallo;
    }

    public Fallos(Integer idFallo, String numeroFallo, BigDecimal importeTotal, int anioAfectacion, Date fechaFallo) {
        this.idFallo = idFallo;
        this.numeroFallo = numeroFallo;
        this.importeTotal = importeTotal;
        this.anioAfectacion = anioAfectacion;
        this.fechaFallo = fechaFallo;
    }

    public Integer getIdFallo() {
        return idFallo;
    }

    public void setIdFallo(Integer idFallo) {
        this.idFallo = idFallo;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getNumeroFallo() {
        return numeroFallo;
    }

    public void setNumeroFallo(String numeroFallo) {
        this.numeroFallo = numeroFallo;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public int getAnioAfectacion() {
        return anioAfectacion;
    }

    public void setAnioAfectacion(int anioAfectacion) {
        this.anioAfectacion = anioAfectacion;
    }

    public Date getFechaFallo() {
        return fechaFallo;
    }

    public void setFechaFallo(Date fechaFallo) {
        this.fechaFallo = fechaFallo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getIncomformidad() {
        return incomformidad;
    }

    public void setIncomformidad(Integer incomformidad) {
        this.incomformidad = incomformidad;
    }
    
    public String getListaFallosDto() {
        return listaFallosDto;
    }

    public void setListaFallosDto(String listaFallosDto) {
        this.listaFallosDto = listaFallosDto;
    }
    
    public String getListaFallosDtoSelect() {
        return listaFallosDtoSelect;
    }

    public void setListaFallosDtoSelect(String listaFallosDtoSelect) {
        this.listaFallosDtoSelect = listaFallosDtoSelect;
    }
    
    public String getNumeroProcedimiento() {
        return numeroProcedimiento;
    }

    public void setNumeroProcedimiento(String numeroProcedimiento) {
        this.numeroProcedimiento = numeroProcedimiento;
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

    @XmlTransient
    public List<FalloProcedimientoRcb> getFalloProcedimientoRcbList() {
        return falloProcedimientoRcbList;
    }

    public void setFalloProcedimientoRcbList(List<FalloProcedimientoRcb> falloProcedimientoRcbList) {
        this.falloProcedimientoRcbList = falloProcedimientoRcbList;
    }

    public Estatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Estatus idEstatus) {
        this.idEstatus = idEstatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFallo != null ? idFallo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fallos)) {
            return false;
        }
        Fallos other = (Fallos) object;
        if ((this.idFallo == null && other.idFallo != null) || (this.idFallo != null && !this.idFallo.equals(other.idFallo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Fallos[ idFallo=" + idFallo + " ]";
    }
    
}
