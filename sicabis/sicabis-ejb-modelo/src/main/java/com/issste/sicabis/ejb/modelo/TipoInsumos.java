/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 9RZCBG2
 */
@Entity
@Table(name = "tipo_insumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoInsumos.findAll", query = "SELECT t FROM TipoInsumos t"),
    @NamedQuery(name = "TipoInsumos.findByIdTipoInsumos", query = "SELECT t FROM TipoInsumos t WHERE t.idTipoInsumos = :idTipoInsumos"),
    @NamedQuery(name = "TipoInsumos.findByActivo", query = "SELECT t FROM TipoInsumos t WHERE t.activo = :activo"),
    @NamedQuery(name = "TipoInsumos.findBySigla", query = "SELECT t FROM TipoInsumos t WHERE t.sigla = :sigla"),
    @NamedQuery(name = "TipoInsumos.findByNombre", query = "SELECT t FROM TipoInsumos t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoInsumos.findByIvaActivo", query = "SELECT t FROM TipoInsumos t WHERE t.ivaActivo = :ivaActivo")})
public class TipoInsumos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_insumos")
    private Integer idTipoInsumos;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "sigla")
    private String sigla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "iva_activo")
    private Integer ivaActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoInsumos")
    private List<Insumos> insumosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoInsumos")
    private List<Contratos> contratosList;

    public TipoInsumos() {
    }

    public TipoInsumos(Integer idTipoInsumos) {
        this.idTipoInsumos = idTipoInsumos;
    }

    public TipoInsumos(Integer idTipoInsumos, String sigla, String nombre) {
        this.idTipoInsumos = idTipoInsumos;
        this.sigla = sigla;
        this.nombre = nombre;
    }

    public Integer getIdTipoInsumos() {
        return idTipoInsumos;
    }

    public void setIdTipoInsumos(Integer idTipoInsumos) {
        this.idTipoInsumos = idTipoInsumos;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIvaActivo() {
        return ivaActivo;
    }

    public void setIvaActivo(Integer ivaActivo) {
        this.ivaActivo = ivaActivo;
    }

    public List<Insumos> getInsumosList() {
        return insumosList;
    }

    public void setInsumosList(List<Insumos> insumosList) {
        this.insumosList = insumosList;
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
        hash += (idTipoInsumos != null ? idTipoInsumos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoInsumos)) {
            return false;
        }
        TipoInsumos other = (TipoInsumos) object;
        if ((this.idTipoInsumos == null && other.idTipoInsumos != null) || (this.idTipoInsumos != null && !this.idTipoInsumos.equals(other.idTipoInsumos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.TipoInsumos[ idTipoInsumos=" + idTipoInsumos + " ]";
    }

}
