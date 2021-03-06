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
@Table(name = "clausulado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clausulado.findAll", query = "SELECT c FROM Clausulado c"),
    @NamedQuery(name = "Clausulado.findByIdClausulado", query = "SELECT c FROM Clausulado c WHERE c.idClausulado = :idClausulado"),
    @NamedQuery(name = "Clausulado.findByClaveProcedimiento", query = "SELECT c FROM Clausulado c WHERE c.claveProcedimiento = :claveProcedimiento")})
public class Clausulado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_clausulado")
    private Integer idClausulado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clausula")
    private String clausula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clave_procedimiento")
    private int claveProcedimiento;
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

    public Clausulado() {
    }

    public Clausulado(Integer idClausulado) {
        this.idClausulado = idClausulado;
    }

    public Clausulado(Integer idClausulado, String clausula, int claveProcedimiento) {
        this.idClausulado = idClausulado;
        this.clausula = clausula;
        this.claveProcedimiento = claveProcedimiento;
    }

    public Integer getIdClausulado() {
        return idClausulado;
    }

    public void setIdClausulado(Integer idClausulado) {
        this.idClausulado = idClausulado;
    }

    public String getClausula() {
        return clausula;
    }

    public void setClausula(String clausula) {
        this.clausula = clausula;
    }

    public int getClaveProcedimiento() {
        return claveProcedimiento;
    }

    public void setClaveProcedimiento(int claveProcedimiento) {
        this.claveProcedimiento = claveProcedimiento;
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
        hash += (idClausulado != null ? idClausulado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clausulado)) {
            return false;
        }
        Clausulado other = (Clausulado) object;
        if ((this.idClausulado == null && other.idClausulado != null) || (this.idClausulado != null && !this.idClausulado.equals(other.idClausulado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Clausulado[ idClausulado=" + idClausulado + " ]";
    }

}
