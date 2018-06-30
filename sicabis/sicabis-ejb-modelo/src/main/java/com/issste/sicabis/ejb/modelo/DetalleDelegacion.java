
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "detalle_delegacion")
@XmlRootElement
public class DetalleDelegacion implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_delegacion")
    private Integer idDetalleDelegacion;
    @Column(name = "estado_umu_delegacion")
    private String estadoUmuDelegacion;
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaActualizacion;
    @JoinColumn(name = "id_porcentaje_delegacion", referencedColumnName = "id_porcentaje_delegacion")
    @ManyToOne(optional = false)
    private PorcentajeDelegacion idPorcentajeDelegacion;
    @JoinColumn(name = "id_delegacion", referencedColumnName = "id_delegacion")
    @ManyToOne(optional = false)
    private Delegaciones idDelegacion;

    public Integer getIdDetalleDelegacion() {
        return idDetalleDelegacion;
    }

    public void setIdDetalleDelegacion(Integer idDetalleDelegacion) {
        this.idDetalleDelegacion = idDetalleDelegacion;
    }

    public String getEstadoUmuDelegacion() {
        return estadoUmuDelegacion;
    }

    public void setEstadoUmuDelegacion(String estadoUmuDelegacion) {
        this.estadoUmuDelegacion = estadoUmuDelegacion;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public PorcentajeDelegacion getIdPorcentajeDelegacion() {
        return idPorcentajeDelegacion;
    }

    public void setIdPorcentajeDelegacion(PorcentajeDelegacion idPorcentajeDelegacion) {
        this.idPorcentajeDelegacion = idPorcentajeDelegacion;
    }

    public Delegaciones getIdDelegacion() {
        return idDelegacion;
    }

    public void setIdDelegacion(Delegaciones idDelegacion) {
        this.idDelegacion = idDelegacion;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleDelegacion != null ? idDetalleDelegacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PorcentajeDelegacion)) {
            return false;
        }
        DetalleDelegacion other = (DetalleDelegacion) object;
        if ((this.idDetalleDelegacion == null && other.idDetalleDelegacion != null) || (this.idDetalleDelegacion != null && !this.idDetalleDelegacion.equals(other.idDetalleDelegacion))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.DetalleDelegacion[ idDetalleDelegacion=" + idDetalleDelegacion + " ]";
    }
    
}
