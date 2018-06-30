
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "jefatura")
public class Jefatura implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_jefatura")
    private Integer idJefatura;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne
    private Area idArea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idJefatura")
    private List<Rcb> rcbList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idJefatura")
    private List<CatDetalleIm> catDetalleImList;

    public Jefatura() {
    }

    public Jefatura(Integer idJefatura) {
        this.idJefatura = idJefatura;
    }

    public Integer getIdJefatura() {
        return idJefatura;
    }

    public void setIdJefatura(Integer idJefatura) {
        this.idJefatura = idJefatura;
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

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public List<CatDetalleIm> getCatDetalleImList() {
        return catDetalleImList;
    }

    public void setCatDetalleImList(List<CatDetalleIm> catDetalleImList) {
        this.catDetalleImList = catDetalleImList;
    }

    @XmlTransient
    public List<Rcb> getRcbList() {
        return rcbList;
    }

    public void setRcbList(List<Rcb> rcbList) {
        this.rcbList = rcbList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJefatura != null ? idJefatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jefatura)) {
            return false;
        }
        Jefatura other = (Jefatura) object;
        if ((this.idJefatura == null && other.idJefatura != null) || (this.idJefatura != null && !this.idJefatura.equals(other.idJefatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Jefatura[ idJefatura=" + idJefatura + " ]";
    }
    
}
