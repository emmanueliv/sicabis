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

/**
 *
 * @author Toshiba Manolo
 */
@Entity
@Table(name = "periodo_area")
@NamedQueries({
    @NamedQuery(name = "PeriodoArea.findAll", query = "SELECT p FROM PeriodoArea p"),
    @NamedQuery(name = "PeriodoArea.findByIdPeriodoArea", query = "SELECT p FROM PeriodoArea p WHERE p.idPeriodoArea = :idPeriodoArea"),
    @NamedQuery(name = "PeriodoArea.findByActivo", query = "SELECT p FROM PeriodoArea p WHERE p.activo = :activo"),
    @NamedQuery(name = "PeriodoArea.findByDiaInicial", query = "SELECT p FROM PeriodoArea p WHERE p.diaInicial = :diaInicial"),
    @NamedQuery(name = "PeriodoArea.findByDiaFinal", query = "SELECT p FROM PeriodoArea p WHERE p.diaFinal = :diaFinal"),
    @NamedQuery(name = "PeriodoArea.findByIdArea", query = "SELECT p FROM PeriodoArea p WHERE p.idArea = :idArea"),
    @NamedQuery(name = "PeriodoArea.findByUsuarioAlta", query = "SELECT p FROM PeriodoArea p WHERE p.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "PeriodoArea.findByUsuarioBaja", query = "SELECT p FROM PeriodoArea p WHERE p.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "PeriodoArea.findByUsuarioModificacion", query = "SELECT p FROM PeriodoArea p WHERE p.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "PeriodoArea.findByFechaAlta", query = "SELECT p FROM PeriodoArea p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "PeriodoArea.findByFechaBaja", query = "SELECT p FROM PeriodoArea p WHERE p.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "PeriodoArea.findByFechaModificacion", query = "SELECT p FROM PeriodoArea p WHERE p.fechaModificacion = :fechaModificacion")})
public class PeriodoArea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_periodo_area")
    private int idPeriodoArea;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia_inicial")
    private int diaInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia_final")
    private int diaFinal;
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
    
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne(optional = false)
    private Area idArea;

    public PeriodoArea() {
    }
    
    public int getIdPeriodoArea() {
        return idPeriodoArea;
    }

    public void setIdPeriodoArea(int idPeriodoArea) {
        this.idPeriodoArea = idPeriodoArea;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public int getDiaInicial() {
        return diaInicial;
    }

    public void setDiaInicial(int diaInicial) {
        this.diaInicial = diaInicial;
    }

    public int getDiaFinal() {
        return diaFinal;
    }

    public void setDiaFinal(int diaFinal) {
        this.diaFinal = diaFinal;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
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
        hash += (idArea != null ? idArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodoArea)) {
            return false;
        }
        PeriodoArea other = (PeriodoArea) object;
        if ((this.idArea == null && other.idArea != null) || (this.idArea != null && !this.idArea.equals(other.idArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.PeriodoArea[ idArea=" + idArea + " ]";
    }
    
}
