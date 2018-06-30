
package com.issste.sicabis.ejb.service.silodisa.modelo;

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
@Table(name = "existencia_umus_programas")
public class ExistenciaUmusProgramas implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_existencia_umus_programas")
    private Integer idExistenciaUmusProgramas;
    @Column(name = "delegacion")
    private String delegacion;
    @Column(name = "clave_unidad")
    private String claveUnidad;
    @Column(name = "numero_umu")
    private String numeroUmu;
    @Column(name = "nombre_umu")
    private String nombreUmu;
    @Column(name = "clave")
    private String clave;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "nombre_programa")
    private String nombrePrograma;
    @Column(name = "existencia")
    private Integer existencia;
    @Column(name = "fecha_inventario")
    @Temporal(TemporalType.DATE)
    private Date fechaInventario;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "fecha_ingreso_aux")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    public ExistenciaUmusProgramas() {
    }

    public ExistenciaUmusProgramas(Integer idExistenciaUmusProgramas) {
        this.idExistenciaUmusProgramas = idExistenciaUmusProgramas;
    }

    public Integer getIdExistenciaUmusProgramas() {
        return idExistenciaUmusProgramas;
    }

    public void setIdExistenciaUmusProgramas(Integer idExistenciaUmusProgramas) {
        this.idExistenciaUmusProgramas = idExistenciaUmusProgramas;
    }

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public String getClaveUnidad() {
        return claveUnidad;
    }

    public void setClaveUnidad(String claveUnidad) {
        this.claveUnidad = claveUnidad;
    }

    public String getNumeroUmu() {
        return numeroUmu;
    }

    public void setNumeroUmu(String numeroUmu) {
        this.numeroUmu = numeroUmu;
    }

    public String getNombreUmu() {
        return nombreUmu;
    }

    public void setNombreUmu(String nombreUmu) {
        this.nombreUmu = nombreUmu;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public Date getFechaInventario() {
        return fechaInventario;
    }

    public void setFechaInventario(Date fechaInventario) {
        this.fechaInventario = fechaInventario;
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
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExistenciaUmusProgramas != null ? idExistenciaUmusProgramas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExistenciaUmusProgramas)) {
            return false;
        }
        ExistenciaUmusProgramas other = (ExistenciaUmusProgramas) object;
        if ((this.idExistenciaUmusProgramas == null && other.idExistenciaUmusProgramas != null) || (this.idExistenciaUmusProgramas != null && !this.idExistenciaUmusProgramas.equals(other.idExistenciaUmusProgramas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaUmusProgramas[ idExistenciaUmusProgramas=" + idExistenciaUmusProgramas + " ]";
    }
    
}
