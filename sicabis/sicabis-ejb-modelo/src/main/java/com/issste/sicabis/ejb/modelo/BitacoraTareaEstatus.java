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
@Table(name = "bitacora_tarea_estatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BitacoraTareaEstatus.findAll", query = "SELECT b FROM BitacoraTareaEstatus b"),
    @NamedQuery(name = "BitacoraTareaEstatus.findByIdBitacoraTareaEstatus", query = "SELECT b FROM BitacoraTareaEstatus b WHERE b.idBitacoraTareaEstatus = :idBitacoraTareaEstatus"),
    @NamedQuery(name = "BitacoraTareaEstatus.findByIdTareaId", query = "SELECT b FROM BitacoraTareaEstatus b WHERE b.idTareaId = :idTareaId"),
    @NamedQuery(name = "BitacoraTareaEstatus.findByIdUsuarios", query = "SELECT b FROM BitacoraTareaEstatus b WHERE b.idUsuarios = :idUsuarios"),
    @NamedQuery(name = "BitacoraTareaEstatus.findByIdModulos", query = "SELECT b FROM BitacoraTareaEstatus b WHERE b.idModulos = :idModulos"),
    @NamedQuery(name = "BitacoraTareaEstatus.findByIdEstatus", query = "SELECT b FROM BitacoraTareaEstatus b WHERE b.idEstatus = :idEstatus"),
    @NamedQuery(name = "BitacoraTareaEstatus.findByDescripcion", query = "SELECT b FROM BitacoraTareaEstatus b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "BitacoraTareaEstatus.findByFecha", query = "SELECT b FROM BitacoraTareaEstatus b WHERE b.fecha = :fecha")})
public class BitacoraTareaEstatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bitacora_tarea_estatus")
    private Integer idBitacoraTareaEstatus;
    @Column(name = "id_tarea_id")
    private Integer idTareaId;
    @Basic(optional = true)
    @Column(name = "id_usuarios")
    private int idUsuarios;
    @Column(name = "id_modulos")
    private Integer idModulos;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "id_estatus")
    private int idEstatus;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public BitacoraTareaEstatus() {
    }

    public BitacoraTareaEstatus(Integer idBitacoraTareaEstatus) {
        this.idBitacoraTareaEstatus = idBitacoraTareaEstatus;
    }

    public BitacoraTareaEstatus(Integer idBitacoraTareaEstatus, int idUsuarios, int idEstatus) {
        this.idBitacoraTareaEstatus = idBitacoraTareaEstatus;
        this.idUsuarios = idUsuarios;
        this.idEstatus = idEstatus;
    }

    public Integer getIdBitacoraTareaEstatus() {
        return idBitacoraTareaEstatus;
    }

    public void setIdBitacoraTareaEstatus(Integer idBitacoraTareaEstatus) {
        this.idBitacoraTareaEstatus = idBitacoraTareaEstatus;
    }

    public Integer getIdTareaId() {
        return idTareaId;
    }

    public void setIdTareaId(Integer idTareaId) {
        this.idTareaId = idTareaId;
    }

    public int getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(int idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public Integer getIdModulos() {
        return idModulos;
    }

    public void setIdModulos(Integer idModulos) {
        this.idModulos = idModulos;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(int idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBitacoraTareaEstatus != null ? idBitacoraTareaEstatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BitacoraTareaEstatus)) {
            return false;
        }
        BitacoraTareaEstatus other = (BitacoraTareaEstatus) object;
        if ((this.idBitacoraTareaEstatus == null && other.idBitacoraTareaEstatus != null) || (this.idBitacoraTareaEstatus != null && !this.idBitacoraTareaEstatus.equals(other.idBitacoraTareaEstatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus[ idBitacoraTareaEstatus=" + idBitacoraTareaEstatus + " ]";
    }
    
}
