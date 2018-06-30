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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 84JBBG2
 */
@Entity
@Table(name = "cat_detalle_im")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatDetalleIm.findAll", query = "SELECT c FROM CatDetalleIm c WHERE c.activo = 1 "),
    @NamedQuery(name = "CatDetalleIm.findByIdCatDetalleIm", query = "SELECT c FROM CatDetalleIm c WHERE c.idCatDetalleIm = :idCatDetalleIm AND c.activo = 1"),
    @NamedQuery(name = "CatDetalleIm.findByNormasCumplir", query = "SELECT c FROM CatDetalleIm c WHERE c.normasCumplir = :normasCumplir"),
    @NamedQuery(name = "CatDetalleIm.findByPlazo", query = "SELECT c FROM CatDetalleIm c WHERE c.plazo = :plazo"),
    @NamedQuery(name = "CatDetalleIm.findByCondicionesEntrega", query = "SELECT c FROM CatDetalleIm c WHERE c.condicionesEntrega = :condicionesEntrega"),
    @NamedQuery(name = "CatDetalleIm.findByFormaPago", query = "SELECT c FROM CatDetalleIm c WHERE c.formaPago = :formaPago"),
    @NamedQuery(name = "CatDetalleIm.findByGarantias", query = "SELECT c FROM CatDetalleIm c WHERE c.garantias = :garantias"),
    @NamedQuery(name = "CatDetalleIm.findByPenasAtraso", query = "SELECT c FROM CatDetalleIm c WHERE c.penasAtraso = :penasAtraso"),
    @NamedQuery(name = "CatDetalleIm.findByUsuarioAlta", query = "SELECT c FROM CatDetalleIm c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "CatDetalleIm.findByUsuarioBaja", query = "SELECT c FROM CatDetalleIm c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "CatDetalleIm.findByUsuarioModificacion", query = "SELECT c FROM CatDetalleIm c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "CatDetalleIm.findByFechaAlta", query = "SELECT c FROM CatDetalleIm c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "CatDetalleIm.findByFechaBaja", query = "SELECT c FROM CatDetalleIm c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "CatDetalleIm.findByFechaModificacion", query = "SELECT c FROM CatDetalleIm c WHERE c.fechaModificacion = :fechaModificacion")})
public class CatDetalleIm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cat_detalle_im")
    private Integer idCatDetalleIm;
    @Column(name = "activo")
    private Integer activo;
    @Size(max = 45)
    @Column(name = "normas_cumplir")
    private String normasCumplir;
    @Column(name = "plazo")
    private String plazo;
    @Size(max = 145)
    @Column(name = "condiciones_entrega")
    private String condicionesEntrega;
    @Size(max = 145)
    @Column(name = "forma_pago")
    private String formaPago;
    @Size(max = 145)
    @Column(name = "garantias")
    private String garantias;
    @Size(max = 145)
    @Column(name = "penas_atraso")
    private String penasAtraso;
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
    @JoinColumn(name = "id_jefatura", referencedColumnName = "id_jefatura")
    @ManyToOne
    private Jefatura idJefatura;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuarios idUsuario;

    public CatDetalleIm() {
    }

    public CatDetalleIm(Integer idCatDetalleIm) {
        this.idCatDetalleIm = idCatDetalleIm;
    }

    public Integer getIdCatDetalleIm() {
        return idCatDetalleIm;
    }

    public void setIdCatDetalleIm(Integer idCatDetalleIm) {
        this.idCatDetalleIm = idCatDetalleIm;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getNormasCumplir() {
        return normasCumplir;
    }

    public void setNormasCumplir(String normasCumplir) {
        this.normasCumplir = normasCumplir;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getCondicionesEntrega() {
        return condicionesEntrega;
    }

    public void setCondicionesEntrega(String condicionesEntrega) {
        this.condicionesEntrega = condicionesEntrega;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getGarantias() {
        return garantias;
    }

    public void setGarantias(String garantias) {
        this.garantias = garantias;
    }

    public String getPenasAtraso() {
        return penasAtraso;
    }

    public void setPenasAtraso(String penasAtraso) {
        this.penasAtraso = penasAtraso;
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
        this.fechaAlta = new Date();
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

    public Jefatura getIdJefatura() {
        return idJefatura;
    }

    public void setIdJefatura(Jefatura idJefatura) {
        this.idJefatura = idJefatura;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatDetalleIm != null ? idCatDetalleIm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatDetalleIm)) {
            return false;
        }
        CatDetalleIm other = (CatDetalleIm) object;
        if ((this.idCatDetalleIm == null && other.idCatDetalleIm != null) || (this.idCatDetalleIm != null && !this.idCatDetalleIm.equals(other.idCatDetalleIm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.CatDetalleIm[ idCatDetalleIm=" + idCatDetalleIm + " ]";
    }
    
}
