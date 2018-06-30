
package com.issste.sicabis.ejb.service.silodisa.modelo;

import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.Indicador;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mapa_ejecutivo_disp_delegaciones")
@NamedQueries({
    @NamedQuery(name = "MapaEjecutivoDispDelegaciones.findAll", query = "SELECT medd FROM MapaEjecutivoDispDelegaciones medd")})
public class MapaEjecutivoDispDelegaciones implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mapa_ejecutivo_disp_delegaciones")
    private Integer idMapaEjecutivoDispDelegaciones;
    @Column(name = "estado")
    private String estado;
    @Column(name = "delegacion")
    private String delegacion;
    @Column(name = "claves_autorizadas")
    private Integer clavesAutorizadas;
    @Column(name = "claves_disponibles")
    private Integer clavesDisponibles;
    @Column(name = "disponibilidad")
    private BigDecimal disponibilidad;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @JoinColumn(name = "id_delegacion", referencedColumnName = "id_delegacion")
    @ManyToOne(optional = false)
    private Delegaciones idDelegacion;
    @JoinColumn(name = "id_indicador", referencedColumnName = "id_indicador")
    @ManyToOne(optional = false)
    private Indicador idIndicador;

    public MapaEjecutivoDispDelegaciones() {
    }

    public MapaEjecutivoDispDelegaciones(Integer idMapaEjecutivoDispDelegaciones) {
        this.idMapaEjecutivoDispDelegaciones = idMapaEjecutivoDispDelegaciones;
    }

    public Integer getIdMapaEjecutivoDispDelegaciones() {
        return idMapaEjecutivoDispDelegaciones;
    }

    public void setIdMapaEjecutivoDispDelegaciones(Integer idMapaEjecutivoDispDelegaciones) {
        this.idMapaEjecutivoDispDelegaciones = idMapaEjecutivoDispDelegaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public Integer getClavesAutorizadas() {
        return clavesAutorizadas;
    }

    public void setClavesAutorizadas(Integer clavesAutorizadas) {
        this.clavesAutorizadas = clavesAutorizadas;
    }

    public Integer getClavesDisponibles() {
        return clavesDisponibles;
    }

    public void setClavesDisponibles(Integer clavesDisponibles) {
        this.clavesDisponibles = clavesDisponibles;
    }

    public BigDecimal getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(BigDecimal disponibilidad) {
        this.disponibilidad = disponibilidad.setScale(2, RoundingMode.HALF_UP);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Delegaciones getIdDelegacion() {
        return idDelegacion;
    }

    public void setIdDelegacion(Delegaciones idDelegacion) {
        this.idDelegacion = idDelegacion;
    }

    public Indicador getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(Indicador idIndicador) {
        this.idIndicador = idIndicador;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMapaEjecutivoDispDelegaciones != null ? idMapaEjecutivoDispDelegaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MapaEjecutivoDispDelegaciones)) {
            return false;
        }
        MapaEjecutivoDispDelegaciones other = (MapaEjecutivoDispDelegaciones) object;
        if ((this.idMapaEjecutivoDispDelegaciones == null && other.idMapaEjecutivoDispDelegaciones != null) || (this.idMapaEjecutivoDispDelegaciones != null && !this.idMapaEjecutivoDispDelegaciones.equals(other.idMapaEjecutivoDispDelegaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispDelegaciones[ idMapaEjecutivoDispDelegaciones=" + idMapaEjecutivoDispDelegaciones + " ]";
    }
    
}
