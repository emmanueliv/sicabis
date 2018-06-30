/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kriosoft
 */
@Entity
@Table(name = "planeacion_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlaneacionDetalle.findAll", query = "SELECT p FROM PlaneacionDetalle p"),
    @NamedQuery(name = "PlaneacionDetalle.findByIdPlaneacionDetalle", query = "SELECT p FROM PlaneacionDetalle p WHERE p.idPlaneacionDetalle = :idPlaneacionDetalle"),
    @NamedQuery(name = "PlaneacionDetalle.findByActivo", query = "SELECT p FROM PlaneacionDetalle p WHERE p.activo = :activo"),
    @NamedQuery(name = "PlaneacionDetalle.findByExistenciasCenadi", query = "SELECT p FROM PlaneacionDetalle p WHERE p.existenciasCenadi = :existenciasCenadi"),
    @NamedQuery(name = "PlaneacionDetalle.findByInsumosPendientesContratos", query = "SELECT p FROM PlaneacionDetalle p WHERE p.insumosPendientesContratos = :insumosPendientesContratos"),
    @NamedQuery(name = "PlaneacionDetalle.findByCantidadPromedioMensual", query = "SELECT p FROM PlaneacionDetalle p WHERE p.cantidadPromedioMensual = :cantidadPromedioMensual"),
    @NamedQuery(name = "PlaneacionDetalle.findByCantidadSolicitada", query = "SELECT p FROM PlaneacionDetalle p WHERE p.cantidadSolicitada = :cantidadSolicitada"),
    @NamedQuery(name = "PlaneacionDetalle.findByCantidadProyectada", query = "SELECT p FROM PlaneacionDetalle p WHERE p.cantidadProyectada = :cantidadProyectada"),
    @NamedQuery(name = "PlaneacionDetalle.findByNecesidadPeriodoSiguiente", query = "SELECT p FROM PlaneacionDetalle p WHERE p.necesidadPeriodoSiguiente = :necesidadPeriodoSiguiente"),
    @NamedQuery(name = "PlaneacionDetalle.findByUsuarioAlta", query = "SELECT p FROM PlaneacionDetalle p WHERE p.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "PlaneacionDetalle.findByUsuarioBaja", query = "SELECT p FROM PlaneacionDetalle p WHERE p.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "PlaneacionDetalle.findByUsuarioModificacion", query = "SELECT p FROM PlaneacionDetalle p WHERE p.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "PlaneacionDetalle.findByFechaAlta", query = "SELECT p FROM PlaneacionDetalle p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "PlaneacionDetalle.findByFechaBaja", query = "SELECT p FROM PlaneacionDetalle p WHERE p.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "PlaneacionDetalle.findByFechaModificacion", query = "SELECT p FROM PlaneacionDetalle p WHERE p.fechaModificacion = :fechaModificacion")})
public class PlaneacionDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_planeacion_detalle")
    private Integer idPlaneacionDetalle;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "existencias_cenadi")
    private Integer existenciasCenadi;
    @Column(name = "insumos_pendientes_contratos")
    private Integer insumosPendientesContratos;
    @Column(name = "cantidad_promedio_mensual")
    private Integer cantidadPromedioMensual;
    @Column(name = "cantidad_solicitada")
    private Integer cantidadSolicitada;
    @Column(name = "cantidad_proyectada")
    private Integer cantidadProyectada;
    @Column(name = "necesidad_periodo_siguiente")
    private Integer necesidadPeriodoSiguiente;
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
    @JoinColumn(name = "id_planeacion", referencedColumnName = "id_planeacion")
    @ManyToOne(optional = false)
    private Planeacion idPlaneacion;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @ManyToOne(optional = false)
    private Pacientes idPaciente;
    @JoinColumn(name = "id_insumo", referencedColumnName = "id_insumo")
    @ManyToOne(optional = false)
    private Insumos idInsumo;
    @JoinColumn(name = "id_unidades_medicas", referencedColumnName = "id_unidades_medicas")
    @ManyToOne(optional = false)
    private UnidadesMedicas idUnidadesMedicas;
    @Transient
    private Integer distribucionAnterior;

    public PlaneacionDetalle() {
    }

    public PlaneacionDetalle(Integer idPlaneacionDetalle) {
        this.idPlaneacionDetalle = idPlaneacionDetalle;
    }

    public Integer getIdPlaneacionDetalle() {
        return idPlaneacionDetalle;
    }

    public void setIdPlaneacionDetalle(Integer idPlaneacionDetalle) {
        this.idPlaneacionDetalle = idPlaneacionDetalle;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getExistenciasCenadi() {
        return existenciasCenadi;
    }

    public void setExistenciasCenadi(Integer existenciasCenadi) {
        this.existenciasCenadi = existenciasCenadi;
    }

    public Integer getInsumosPendientesContratos() {
        return insumosPendientesContratos;
    }

    public void setInsumosPendientesContratos(Integer insumosPendientesContratos) {
        this.insumosPendientesContratos = insumosPendientesContratos;
    }

    public Integer getCantidadPromedioMensual() {
        return cantidadPromedioMensual;
    }

    public void setCantidadPromedioMensual(Integer cantidadPromedioMensual) {
        this.cantidadPromedioMensual = cantidadPromedioMensual;
    }

    public Integer getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(Integer cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Integer getCantidadProyectada() {
        return cantidadProyectada;
    }

    public void setCantidadProyectada(Integer cantidadProyectada) {
        this.cantidadProyectada = cantidadProyectada;
    }

    public Integer getNecesidadPeriodoSiguiente() {
        return necesidadPeriodoSiguiente;
    }

    public void setNecesidadPeriodoSiguiente(Integer necesidadPeriodoSiguiente) {
        this.necesidadPeriodoSiguiente = necesidadPeriodoSiguiente;
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

    public Planeacion getIdPlaneacion() {
        return idPlaneacion;
    }

    public void setIdPlaneacion(Planeacion idPlaneacion) {
        this.idPlaneacion = idPlaneacion;
    }

    public Pacientes getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Pacientes idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Insumos getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Insumos idInsumo) {
        this.idInsumo = idInsumo;
    }

    public UnidadesMedicas getIdUnidadesMedicas() {
        return idUnidadesMedicas;
    }

    public void setIdUnidadesMedicas(UnidadesMedicas idUnidadesMedicas) {
        this.idUnidadesMedicas = idUnidadesMedicas;
    }

    public Integer getDistribucionAnterior() {
        return distribucionAnterior;
    }

    public void setDistribucionAnterior(Integer distribucionAnterior) {
        this.distribucionAnterior = distribucionAnterior;
    }
    
     
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlaneacionDetalle != null ? idPlaneacionDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaneacionDetalle)) {
            return false;
        }
        PlaneacionDetalle other = (PlaneacionDetalle) object;
        if ((this.idPlaneacionDetalle == null && other.idPlaneacionDetalle != null) || (this.idPlaneacionDetalle != null && !this.idPlaneacionDetalle.equals(other.idPlaneacionDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.PlaneacionDetalle[ idPlaneacionDetalle=" + idPlaneacionDetalle + " ]";
    }
    
}
