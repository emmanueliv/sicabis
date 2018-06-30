
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "tipo_ur")
public class TipoUr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_ur")
    private Integer idTipoUr;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoUr")
    private List<Ur> urList;

    public TipoUr() {
    }

    public TipoUr(Integer idTipoUr) {
        this.idTipoUr = idTipoUr;
    }

    public Integer getIdTipoUr() {
        return idTipoUr;
    }

    public void setIdTipoUr(Integer idTipoUr) {
        this.idTipoUr = idTipoUr;
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

    @XmlTransient
    public List<Ur> getUrList() {
        return urList;
    }

    public void setUrList(List<Ur> urList) {
        this.urList = urList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoUr != null ? idTipoUr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUr)) {
            return false;
        }
        TipoUr other = (TipoUr) object;
        if ((this.idTipoUr == null && other.idTipoUr != null) || (this.idTipoUr != null && !this.idTipoUr.equals(other.idTipoUr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.TipoUr[ idTipoUr=" + idTipoUr + " ]";
    }
    
}
