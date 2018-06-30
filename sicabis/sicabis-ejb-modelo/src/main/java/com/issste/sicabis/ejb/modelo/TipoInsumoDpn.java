
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "tipo_insumo_dpn")
@NamedQueries({
    @NamedQuery(name = "TipoInsumoDpn.findAll", query = "SELECT tid FROM TipoInsumoDpn tid"),
    @NamedQuery(name = "TipoInsumoDpn.findByIdTipoInsumoDpn", query = "SELECT tid FROM TipoInsumoDpn tid WHERE tid.idTipoInsumoDpn = :idTipoInsumoDpn"),
})
public class TipoInsumoDpn implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_insumo_dpn")
    private Integer idTipoInsumoDpn;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoInsumoDpn")
    private List<InsumoDpn> insumoDpnList;
    
    public TipoInsumoDpn() {
    }

    public TipoInsumoDpn(Integer idTipoInsumoDpn) {
        this.idTipoInsumoDpn = idTipoInsumoDpn;
    }

    public Integer getIdTipoInsumoDpn() {
        return idTipoInsumoDpn;
    }

    public void setIdTipoInsumoDpn(Integer idTipoInsumoDpn) {
        this.idTipoInsumoDpn = idTipoInsumoDpn;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<InsumoDpn> getInsumoDpnList() {
        return insumoDpnList;
    }

    public void setInsumoDpnList(List<InsumoDpn> insumoDpnList) {
        this.insumoDpnList = insumoDpnList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoInsumoDpn != null ? idTipoInsumoDpn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoInsumoDpn)) {
            return false;
        }
        TipoInsumoDpn other = (TipoInsumoDpn) object;
        if ((this.idTipoInsumoDpn == null && other.idTipoInsumoDpn != null) || (this.idTipoInsumoDpn != null && !this.idTipoInsumoDpn.equals(other.idTipoInsumoDpn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.TipoInsumoDpn[ idTipoInsumoDpn=" + idTipoInsumoDpn + " ]";
    }
    
}
