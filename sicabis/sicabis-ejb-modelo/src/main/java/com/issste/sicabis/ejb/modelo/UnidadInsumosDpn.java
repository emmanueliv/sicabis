
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "unidad_insumos_dpn")
@NamedQueries({
    @NamedQuery(name = "UnidadInsumosDpn.findAll", query = "SELECT uid FROM UnidadInsumosDpn uid"),
    @NamedQuery(name = "UnidadInsumosDpn.findByIdInsumoDpn", query = "SELECT uid FROM UnidadInsumosDpn uid WHERE uid.idInsumoDpn.idInsumoDpn = :idInsumoDpn")
})
public class UnidadInsumosDpn implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_unidad_insumo_dpn")
    private Integer idUnidadInsumoDpn;
    @Column(name = "activo")
    private Integer activo;
    @JoinColumn(name = "id_unidades_medicas", referencedColumnName = "id_unidades_medicas")
    @ManyToOne(optional = false)
    private UnidadesMedicas idUnidadesMedicas;
    @JoinColumn(name = "id_insumo_dpn", referencedColumnName = "id_insumo_dpn")
    @ManyToOne(optional = false)
    private InsumoDpn idInsumoDpn;

    public UnidadInsumosDpn() {
    }

    public UnidadInsumosDpn(Integer idUnidadInsumoDpn) {
        this.idUnidadInsumoDpn = idUnidadInsumoDpn;
    }

    public Integer getIdUnidadInsumoDpn() {
        return idUnidadInsumoDpn;
    }

    public void setIdUnidadInsumoDpn(Integer idUnidadInsumoDpn) {
        this.idUnidadInsumoDpn = idUnidadInsumoDpn;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public UnidadesMedicas getIdUnidadesMedicas() {
        return idUnidadesMedicas;
    }

    public void setIdUnidadesMedicas(UnidadesMedicas idUnidadesMedicas) {
        this.idUnidadesMedicas = idUnidadesMedicas;
    }

    public InsumoDpn getIdInsumoDpn() {
        return idInsumoDpn;
    }

    public void setIdInsumoDpn(InsumoDpn idInsumoDpn) {
        this.idInsumoDpn = idInsumoDpn;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadInsumoDpn != null ? idUnidadInsumoDpn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadInsumosDpn)) {
            return false;
        }
        UnidadInsumosDpn other = (UnidadInsumosDpn) object;
        if ((this.idUnidadInsumoDpn == null && other.idUnidadInsumoDpn != null) || (this.idUnidadInsumoDpn != null && !this.idUnidadInsumoDpn.equals(other.idUnidadInsumoDpn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.UnidadInsumosDpn[ idUnidadInsumoDpn=" + idUnidadInsumoDpn + " ]";
    }
    
}
