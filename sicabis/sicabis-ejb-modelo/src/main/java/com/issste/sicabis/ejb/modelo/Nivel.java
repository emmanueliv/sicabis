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
@Table(name = "nivel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nivel.findAll", query = "SELECT n FROM Nivel n"),
    @NamedQuery(name = "Nivel.findByIdNivel", query = "SELECT n FROM Nivel n WHERE n.idNivel = :idNivel"),
    @NamedQuery(name = "Nivel.findByActivo", query = "SELECT n FROM Nivel n WHERE n.activo = :activo"),
    @NamedQuery(name = "Nivel.findByDescripcion", query = "SELECT n FROM Nivel n WHERE n.descripcion = :descripcion"),
    @NamedQuery(name = "Nivel.findByUsuarioAlta", query = "SELECT n FROM Nivel n WHERE n.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Nivel.findByUsuarioBaja", query = "SELECT n FROM Nivel n WHERE n.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Nivel.findByUsuarioModificacion", query = "SELECT n FROM Nivel n WHERE n.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Nivel.findByFechaAlta", query = "SELECT n FROM Nivel n WHERE n.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Nivel.findByFechaBaja", query = "SELECT n FROM Nivel n WHERE n.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Nivel.findByFechaModificacion", query = "SELECT n FROM Nivel n WHERE n.fechaModificacion = :fechaModificacion")})
public class Nivel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nivel")
    private Integer idNivel;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNivel")
    private List<Insumos> insumosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNivel")
    private List<RcbInsumos> rcbInsumosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNivel")
    private List<UnidadesMedicas> unidadesMedicasList;
    

    public Nivel() {
    }

    public Nivel(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public Nivel(Integer idNivel, String descripcion) {
        this.idNivel = idNivel;
        this.descripcion = descripcion;
    }

    public Integer getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public List<RcbInsumos> getRcbInsumosList() {
        return rcbInsumosList;
    }

    public void setRcbInsumosList(List<RcbInsumos> rcbInsumosList) {
        this.rcbInsumosList = rcbInsumosList;
    }

    public List<UnidadesMedicas> getUnidadesMedicasList() {
        return unidadesMedicasList;
    }

    public void setUnidadesMedicasList(List<UnidadesMedicas> unidadesMedicasList) {
        this.unidadesMedicasList = unidadesMedicasList;
    }
     
    
    

    @XmlTransient
    public List<Insumos> getInsumosList() {
        return insumosList;
    }

    public void setInsumosList(List<Insumos> insumosList) {
        this.insumosList = insumosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNivel != null ? idNivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nivel)) {
            return false;
        }
        Nivel other = (Nivel) object;
        if ((this.idNivel == null && other.idNivel != null) || (this.idNivel != null && !this.idNivel.equals(other.idNivel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Nivel[ idNivel=" + idNivel + " ]";
    }
    
}
