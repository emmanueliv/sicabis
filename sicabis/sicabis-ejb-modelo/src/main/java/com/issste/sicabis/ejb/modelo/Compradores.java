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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kriosoft
 */
@Entity
@Table(name = "compradores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compradores.findAll", query = "SELECT c FROM Compradores c "),
    @NamedQuery(name = "Compradores.findByIdComprador", query = "SELECT c FROM Compradores c WHERE c.idComprador = :idComprador"),
    @NamedQuery(name = "Compradores.findByActivo", query = "SELECT c FROM Compradores c WHERE c.activo = :activo"),
    @NamedQuery(name = "Compradores.findByNombre", query = "SELECT c FROM Compradores c WHERE c.nombre = :nombre AND c.activo = 1"),
    @NamedQuery(name = "Compradores.findByApaterno", query = "SELECT c FROM Compradores c WHERE c.apaterno = :apaterno"),
    @NamedQuery(name = "Compradores.findByAmaterno", query = "SELECT c FROM Compradores c WHERE c.amaterno = :amaterno"),
    @NamedQuery(name = "Compradores.findByUsuarioAlta", query = "SELECT c FROM Compradores c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Compradores.findByUsuarioBaja", query = "SELECT c FROM Compradores c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Compradores.findByUsuarioModificacion", query = "SELECT c FROM Compradores c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Compradores.findByFechaAlta", query = "SELECT c FROM Compradores c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Compradores.findByFechaBaja", query = "SELECT c FROM Compradores c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Compradores.findByFechaModificacion", query = "SELECT c FROM Compradores c WHERE c.fechaModificacion = :fechaModificacion")})
public class Compradores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comprador")
    private Integer idComprador;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 200)
    @Column(name = "apaterno")
    private String apaterno;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 200)
    @Column(name = "amaterno")
    private String amaterno;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComprador")
    private List<CompradorContrato> compradorContratoList;

    public Compradores() {
    }

    public Compradores(Integer idComprador) {
        this.idComprador = idComprador;
    }

    public Compradores(Integer idComprador, String nombre, String apaterno, String amaterno) {
        this.idComprador = idComprador;
        this.nombre = nombre;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
    }

    public Integer getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(Integer idComprador) {
        this.idComprador = idComprador;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
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
    public List<CompradorContrato> getCompradorContratoList() {
        return compradorContratoList;
    }

    public void setCompradorContratoList(List<CompradorContrato> compradorContratoList) {
        this.compradorContratoList = compradorContratoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComprador != null ? idComprador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compradores)) {
            return false;
        }
        Compradores other = (Compradores) object;
        if ((this.idComprador == null && other.idComprador != null) || (this.idComprador != null && !this.idComprador.equals(other.idComprador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Compradores[ idComprador=" + idComprador + " ]";
    }

}
