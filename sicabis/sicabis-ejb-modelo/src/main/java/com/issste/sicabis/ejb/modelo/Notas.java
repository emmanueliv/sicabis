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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kriosoft
 */
@Entity
@Table(name = "notas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notas.findAll", query = "SELECT n FROM Notas n"),
    @NamedQuery(name = "Notas.findByIdNotas", query = "SELECT n FROM Notas n WHERE n.idNotas = :idNotas"),
    @NamedQuery(name = "Notas.findByActivo", query = "SELECT n FROM Notas n WHERE n.activo = :activo"),
    @NamedQuery(name = "Notas.findByDescripcion", query = "SELECT n FROM Notas n WHERE n.descripcion = :descripcion"),
    @NamedQuery(name = "Notas.findByUsuarioAlta", query = "SELECT n FROM Notas n WHERE n.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Notas.findByUsuarioBaja", query = "SELECT n FROM Notas n WHERE n.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Notas.findByUsuarioModificacion", query = "SELECT n FROM Notas n WHERE n.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Notas.findByFechaAlta", query = "SELECT n FROM Notas n WHERE n.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Notas.findByFechaBaja", query = "SELECT n FROM Notas n WHERE n.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Notas.findByFechaModificaciones", query = "SELECT n FROM Notas n WHERE n.fechaModificaciones = :fechaModificaciones")})
public class Notas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_notas")
    private Integer idNotas;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(name = "descripcion")
    private String descripcion;
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
    @Column(name = "fecha_modificaciones")
    @Temporal(TemporalType.DATE)
    private Date fechaModificaciones;
    @JoinColumn(name = "id_procedimiento_rcb", referencedColumnName = "id_procedimiento_rcb")
    @ManyToOne(optional = false)
    private ProcedimientoRcb idProcedimientoRcb;

    public Notas() {
    }

    public Notas(Integer idNotas) {
        this.idNotas = idNotas;
    }

    public Notas(Integer idNotas, String descripcion) {
        this.idNotas = idNotas;
        this.descripcion = descripcion;
    }

    public Integer getIdNotas() {
        return idNotas;
    }

    public void setIdNotas(Integer idNotas) {
        this.idNotas = idNotas;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Date getFechaModificaciones() {
        return fechaModificaciones;
    }

    public void setFechaModificaciones(Date fechaModificaciones) {
        this.fechaModificaciones = fechaModificaciones;
    }

    public ProcedimientoRcb getIdProcedimientoRcb() {
        return idProcedimientoRcb;
    }

    public void setIdProcedimientoRcb(ProcedimientoRcb idProcedimientoRcb) {
        this.idProcedimientoRcb = idProcedimientoRcb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotas != null ? idNotas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notas)) {
            return false;
        }
        Notas other = (Notas) object;
        if ((this.idNotas == null && other.idNotas != null) || (this.idNotas != null && !this.idNotas.equals(other.idNotas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Notas[ idNotas=" + idNotas + " ]";
    }
    
}
