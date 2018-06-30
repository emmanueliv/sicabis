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
@Table(name = "fabricante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fabricante.findAll", query = "SELECT f FROM Fabricante f WHERE f.activo = 1"),
    @NamedQuery(name = "Fabricante.findByIdFabricante", query = "SELECT f FROM Fabricante f WHERE f.idFabricante = :idFabricante"),
    @NamedQuery(name = "Fabricante.findByActivo", query = "SELECT f FROM Fabricante f WHERE f.activo = :activo"),
    @NamedQuery(name = "Fabricante.findByNombre", query = "SELECT f FROM Fabricante f WHERE f.nombre = :nombre AND f.activo = 1"),
    @NamedQuery(name = "Fabricante.findByRegistroSanitario", query = "SELECT f FROM Fabricante f WHERE f.registroSanitario = :registroSanitario"),
    @NamedQuery(name = "Fabricante.findByUsuarioAlta", query = "SELECT f FROM Fabricante f WHERE f.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Fabricante.findByUsuarioBaja", query = "SELECT f FROM Fabricante f WHERE f.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Fabricante.findByUsuarioModificacion", query = "SELECT f FROM Fabricante f WHERE f.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Fabricante.findByFechaAlta", query = "SELECT f FROM Fabricante f WHERE f.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Fabricante.findByFechaBaja", query = "SELECT f FROM Fabricante f WHERE f.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Fabricante.findByFechaModificacion", query = "SELECT f FROM Fabricante f WHERE f.fechaModificacion = :fechaModificacion")})
public class Fabricante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_fabricante")
    private Integer idFabricante;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "registro_sanitario")
    private String registroSanitario;
    @Column(name = "pais")
    private String pais;
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
    @OneToMany(mappedBy = "idFabricante")
    private List<Remisiones> remisionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFabricante")
    private List<ProveedorFabricante> proveedorFabricanteList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFabricante")
//    private List<Propuestas> propuestasList;

    public Fabricante() {
    }

    public Fabricante(Integer idFabricante) {
        this.idFabricante = idFabricante;
    }

    public Fabricante(Integer idFabricante, String nombre, String registroSanitario) {
        this.idFabricante = idFabricante;
        this.nombre = nombre;
        this.registroSanitario = registroSanitario;
    }

    public Integer getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(Integer idFabricante) {
        this.idFabricante = idFabricante;
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

    public String getRegistroSanitario() {
        return registroSanitario;
    }

    public void setRegistroSanitario(String registroSanitario) {
        this.registroSanitario = registroSanitario;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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
    public List<Remisiones> getRemisionesList() {
        return remisionesList;
    }

    public void setRemisionesList(List<Remisiones> remisionesList) {
        this.remisionesList = remisionesList;
    }

    @XmlTransient
    public List<ProveedorFabricante> getProveedorFabricanteList() {
        return proveedorFabricanteList;
    }

    public void setProveedorFabricanteList(List<ProveedorFabricante> proveedorFabricanteList) {
        this.proveedorFabricanteList = proveedorFabricanteList;
    }

//    @XmlTransient
//    public List<Propuestas> getPropuestasList() {
//        return propuestasList;
//    }
//
//    public void setPropuestasList(List<Propuestas> propuestasList) {
//        this.propuestasList = propuestasList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFabricante != null ? idFabricante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fabricante)) {
            return false;
        }
        Fabricante other = (Fabricante) object;
        if ((this.idFabricante == null && other.idFabricante != null) || (this.idFabricante != null && !this.idFabricante.equals(other.idFabricante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Fabricante[ idFabricante=" + idFabricante + " ]";
    }
    
}
