package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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

/**
 *
 * @author fabianvr
 */
@Entity
@Table(name = "presentacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Presentacion.findAll", query = "SELECT p FROM Presentacion p WHERE p.activo = 1"),
    @NamedQuery(name = "Presentacion.findByIdPresentacion", query = "SELECT p FROM Presentacion p WHERE p.idPresentacion = :idPresentacion"),
    @NamedQuery(name = "Presentacion.findByActivo", query = "SELECT p FROM Presentacion p WHERE p.activo = :activo"),
    @NamedQuery(name = "Presentacion.findByPresentacion", query = "SELECT p FROM Presentacion p WHERE p.presentacion = :presentacion AND p.activo = 1"),
    @NamedQuery(name = "Presentacion.findByUsuarioAlta", query = "SELECT p FROM Presentacion p WHERE p.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Presentacion.findByUsuarioBaja", query = "SELECT p FROM Presentacion p WHERE p.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Presentacion.findByUsuarioModificacion", query = "SELECT p FROM Presentacion p WHERE p.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Presentacion.findByFechaAlta", query = "SELECT p FROM Presentacion p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Presentacion.findByFechaBaja", query = "SELECT p FROM Presentacion p WHERE p.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Presentacion.findByFechaModificacion", query = "SELECT p FROM Presentacion p WHERE p.fechaModificacion = :fechaModificacion")})
public class Presentacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_presentacion")
    private Integer idPresentacion;
    @Column(name = "activo")
    private Integer activo;
    @Size(max = 45)
    @Column(name = "presentacion")
    private String presentacion;
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
    @OneToMany(mappedBy = "idPresentacion")
    private List<Remisiones> remisionesList;
    
    public Presentacion() {
    }

    public Presentacion(Integer idPresentacion) {
        this.idPresentacion = idPresentacion;
    }

    public Integer getIdPresentacion() {
        return idPresentacion;
    }

    public void setIdPresentacion(Integer idPresentacion) {
        this.idPresentacion = idPresentacion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
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

    public List<Remisiones> getRemisionesList() {
        return remisionesList;
    }

    public void setRemisionesList(List<Remisiones> remisionesList) {
        this.remisionesList = remisionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPresentacion != null ? idPresentacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Presentacion)) {
            return false;
        }
        Presentacion other = (Presentacion) object;
        if ((this.idPresentacion == null && other.idPresentacion != null) || (this.idPresentacion != null && !this.idPresentacion.equals(other.idPresentacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Presentacion[ idPresentacion=" + idPresentacion + " ]";
    }
    
}
