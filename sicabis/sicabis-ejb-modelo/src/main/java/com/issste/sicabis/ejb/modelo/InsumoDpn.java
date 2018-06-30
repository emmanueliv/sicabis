
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "insumo_dpn")
@NamedQueries({
    @NamedQuery(name = "InsumoDpn.findAll", query = "SELECT id FROM InsumoDpn id"),
    @NamedQuery(name = "InsumoDpn.findByIdInsumoDpn", query = "SELECT id FROM InsumoDpn id WHERE id.idInsumoDpn = :idInsumoDpn")
})
public class InsumoDpn implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_insumo_dpn")
    private Integer idInsumoDpn;
    @Column(name = "estatus_insumo_dpn")
    private Integer estatusInsumoDpn;
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
    @JoinColumn(name = "id_insumo", referencedColumnName = "id_insumo")
    @ManyToOne(optional = false)
    private Insumos idInsumo;
    @JoinColumn(name = "id_tipo_insumo_dpn", referencedColumnName = "id_tipo_insumo_dpn")
    @ManyToOne(optional = false)
    private TipoInsumoDpn idTipoInsumoDpn;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumoDpn")
    private List<DpnInsumos> dpnInsumosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInsumoDpn")
    private List<UnidadInsumosDpn> unidadInsumoDpnList;

    public InsumoDpn() {
    }

    public InsumoDpn(Integer idInsumoDpn) {
        this.idInsumoDpn = idInsumoDpn;
    }

    public Integer getIdInsumoDpn() {
        return idInsumoDpn;
    }

    public void setIdInsumoDpn(Integer idInsumoDpn) {
        this.idInsumoDpn = idInsumoDpn;
    }

    public Integer getEstatusInsumoDpn() {
        return estatusInsumoDpn;
    }

    public void setEstatusInsumoDpn(Integer estatusInsumoDpn) {
        this.estatusInsumoDpn = estatusInsumoDpn;
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

    public Insumos getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Insumos idInsumo) {
        this.idInsumo = idInsumo;
    }

    public TipoInsumoDpn getIdTipoInsumoDpn() {
        return idTipoInsumoDpn;
    }

    public void setIdTipoInsumoDpn(TipoInsumoDpn idTipoInsumoDpn) {
        this.idTipoInsumoDpn = idTipoInsumoDpn;
    }
    
    @XmlTransient
    public List<DpnInsumos> getDpnInsumosList() {
        return dpnInsumosList;
    }

    public void setDpnInsumosList(List<DpnInsumos> dpnInsumosList) {
        this.dpnInsumosList = dpnInsumosList;
    }

    @XmlTransient
    public List<UnidadInsumosDpn> getUnidadInsumoDpnList() {
        return unidadInsumoDpnList;
    }

    public void setUnidadInsumoDpnList(List<UnidadInsumosDpn> unidadInsumoDpnList) {
        this.unidadInsumoDpnList = unidadInsumoDpnList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInsumoDpn != null ? idInsumoDpn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsumoDpn)) {
            return false;
        }
        InsumoDpn other = (InsumoDpn) object;
        if ((this.idInsumoDpn == null && other.idInsumoDpn != null) || (this.idInsumoDpn != null && !this.idInsumoDpn.equals(other.idInsumoDpn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.InsumoDpn[ idInsumoDpn=" + idInsumoDpn + " ]";
    }
    
}
