/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kriosoft
 */
@Entity
@Table(name = "fundamento_legal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FundamentoLegal.findAll", query = "SELECT f FROM FundamentoLegal f "),
    @NamedQuery(name = "FundamentoLegal.findAllByActivo", query = "SELECT f FROM FundamentoLegal f WHERE f.activo = 1"),
    @NamedQuery(name = "FundamentoLegal.findByIdFundamentoLegal", query = "SELECT f FROM FundamentoLegal f WHERE f.idFundamentoLegal = :idFundamentoLegal"),
    @NamedQuery(name = "FundamentoLegal.findByDescripcion", query = "SELECT f FROM FundamentoLegal f WHERE f.descripcion = :descripcion AND f.fechaBaja is null"),
    @NamedQuery(name = "FundamentoLegal.findByNombre", query = "SELECT f FROM FundamentoLegal f WHERE f.nombre = :nombre AND f.fechaBaja is null"),
    @NamedQuery(name = "FundamentoLegal.findByUsuarioAlta", query = "SELECT f FROM FundamentoLegal f WHERE f.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "FundamentoLegal.findByUsuarioBaja", query = "SELECT f FROM FundamentoLegal f WHERE f.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "FundamentoLegal.findByUsuarioModificacion", query = "SELECT f FROM FundamentoLegal f WHERE f.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "FundamentoLegal.findByFechaAlta", query = "SELECT f FROM FundamentoLegal f WHERE f.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "FundamentoLegal.findByFechaBaja", query = "SELECT f FROM FundamentoLegal f WHERE f.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "FundamentoLegal.findByFechaModificacion", query = "SELECT f FROM FundamentoLegal f WHERE f.fechaModificacion = :fechaModificacion")})
public class FundamentoLegal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fundamento_legal")
    private Integer idFundamentoLegal;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1000)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "id_padre")
    private Integer idPadre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFundamentoLegal")
    private List<Contratos> contratosList;

    public FundamentoLegal() {
    }

    public FundamentoLegal(Integer idFundamentoLegal) {
        this.idFundamentoLegal = idFundamentoLegal;
    }

    public Integer getIdFundamentoLegal() {
        return idFundamentoLegal;
    }

    public void setIdFundamentoLegal(Integer idFundamentoLegal) {
        this.idFundamentoLegal = idFundamentoLegal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
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
    public List<Contratos> getContratosList() {
        return contratosList;
    }

    public void setContratosList(List<Contratos> contratosList) {
        this.contratosList = contratosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFundamentoLegal != null ? idFundamentoLegal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FundamentoLegal)) {
            return false;
        }
        FundamentoLegal other = (FundamentoLegal) object;
        if ((this.idFundamentoLegal == null && other.idFundamentoLegal != null) || (this.idFundamentoLegal != null && !this.idFundamentoLegal.equals(other.idFundamentoLegal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.FundamentoLegal[ idFundamentoLegal=" + idFundamentoLegal + " ]";
    }

}
