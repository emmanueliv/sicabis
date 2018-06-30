
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "configura_dpn")
@NamedQueries({
    @NamedQuery(name = "ConfiguraDpn.findAll", query = "SELECT cd FROM ConfiguraDpn cd"),
    @NamedQuery(name = "ConfiguraDpn.findByActivo", query = "SELECT cd FROM ConfiguraDpn cd WHERE cd.activo = :activo"),
})
public class ConfiguraDpn implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_configura_dpn")
    private Integer idConfiguraDpn;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "dia_inicio")
    private Integer diaInicio;
    @Column(name = "num_dias")
    private Integer numDias;
    @Column(name = "min_piezas")
    private Integer minPiezas;
    @Basic(optional = false)
    @Column(name = "url_link")
    private String urlLink;
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

    public ConfiguraDpn() {
    }

    public ConfiguraDpn(Integer idConfiguraDpn) {
        this.idConfiguraDpn = idConfiguraDpn;
    }

    public Integer getIdConfiguraDpn() {
        return idConfiguraDpn;
    }

    public void setIdConfiguraDpn(Integer idConfiguraDpn) {
        this.idConfiguraDpn = idConfiguraDpn;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(Integer diaInicio) {
        this.diaInicio = diaInicio;
    }

    public Integer getNumDias() {
        return numDias;
    }

    public void setNumDias(Integer numDias) {
        this.numDias = numDias;
    }

    public Integer getMinPiezas() {
        return minPiezas;
    }

    public void setMinPiezas(Integer minPiezas) {
        this.minPiezas = minPiezas;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
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
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfiguraDpn != null ? idConfiguraDpn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfiguraDpn)) {
            return false;
        }
        ConfiguraDpn other = (ConfiguraDpn) object;
        if ((this.idConfiguraDpn == null && other.idConfiguraDpn != null) || (this.idConfiguraDpn != null && !this.idConfiguraDpn.equals(other.idConfiguraDpn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.ConfiguraDpn[ idConfiguraDpn=" + idConfiguraDpn + " ]";
    }
    
    
}
