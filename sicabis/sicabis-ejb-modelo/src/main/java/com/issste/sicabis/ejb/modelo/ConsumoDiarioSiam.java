
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "consumo_diario_siam")
public class ConsumoDiarioSiam implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_consumo_diario_siam")
    private Integer idConsumoDiarioSiam;
    @Column(name = "clave_insumo")
    private String claveInsumo;
    @Column(name = "clave_unidad")
    private String claveUnidad;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "consumo")
    private Integer consumo;

    public ConsumoDiarioSiam() {
    }

    public ConsumoDiarioSiam(Integer idConsumoDiarioSiam) {
        this.idConsumoDiarioSiam = idConsumoDiarioSiam;
    }

    public Integer getIdConsumoDiarioSiam() {
        return idConsumoDiarioSiam;
    }

    public void setIdConsumoDiarioSiam(Integer idConsumoDiarioSiam) {
        this.idConsumoDiarioSiam = idConsumoDiarioSiam;
    }

    public String getClaveInsumo() {
        return claveInsumo;
    }

    public void setClaveInsumo(String claveInsumo) {
        this.claveInsumo = claveInsumo;
    }

    public String getClaveUnidad() {
        return claveUnidad;
    }

    public void setClaveUnidad(String claveUnidad) {
        this.claveUnidad = claveUnidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getConsumo() {
        return consumo;
    }

    public void setConsumo(Integer consumo) {
        this.consumo = consumo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsumoDiarioSiam != null ? idConsumoDiarioSiam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsumoDiarioSiam)) {
            return false;
        }
        ConsumoDiarioSiam other = (ConsumoDiarioSiam) object;
        if ((this.idConsumoDiarioSiam == null && other.idConsumoDiarioSiam != null) || (this.idConsumoDiarioSiam != null && !this.idConsumoDiarioSiam.equals(other.idConsumoDiarioSiam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.ConsumoDiarioSiam[ idConsumoDiarioSiam=" + idConsumoDiarioSiam + " ]";
    }
}
