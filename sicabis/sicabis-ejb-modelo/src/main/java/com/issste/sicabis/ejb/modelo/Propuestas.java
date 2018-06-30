/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "propuestas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Propuestas.findAll", query = "SELECT p FROM Propuestas p"),
    @NamedQuery(name = "Propuestas.findByIdPropuesta", query = "SELECT p FROM Propuestas p WHERE p.idPropuesta = :idPropuesta"),
    @NamedQuery(name = "Propuestas.findByActivo", query = "SELECT p FROM Propuestas p WHERE p.activo = :activo"),
    @NamedQuery(name = "Propuestas.findByDescuentoOtorgado", query = "SELECT p FROM Propuestas p WHERE p.descuentoOtorgado = :descuentoOtorgado"),
    @NamedQuery(name = "Propuestas.findByPrecioUnitario", query = "SELECT p FROM Propuestas p WHERE p.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "Propuestas.findByUsuarioAlta", query = "SELECT p FROM Propuestas p WHERE p.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Propuestas.findByUsuarioBaja", query = "SELECT p FROM Propuestas p WHERE p.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Propuestas.findByUsuarioModificacion", query = "SELECT p FROM Propuestas p WHERE p.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Propuestas.findByFechaAlta", query = "SELECT p FROM Propuestas p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Propuestas.findByFechaBaja", query = "SELECT p FROM Propuestas p WHERE p.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Propuestas.findByFechaModificacion", query = "SELECT p FROM Propuestas p WHERE p.fechaModificacion = :fechaModificacion")})
public class Propuestas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_propuesta")
    private Integer idPropuesta;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "descuento_otorgado")
    private BigDecimal descuentoOtorgado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Column(name = "precio_unitario_descuento")
    private BigDecimal precioUnitarioDescuento;
    @Column(name = "importe")
    private BigDecimal importe;
    @Column(name = "ganador")
    private Integer ganador;
    @Column(name = "apertura_programada")
    @Temporal(TemporalType.DATE)
    private Date aperturaProgramada;
    @Column(name = "apertura_realizada")
    @Temporal(TemporalType.DATE)
    private Date aperturaRealizada;
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
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    @ManyToOne(optional = false)
    private Proveedores idProveedor;
    @JoinColumn(name = "id_procedimiento_rcb", referencedColumnName = "id_procedimiento_rcb")
    @ManyToOne(optional = false)
    private ProcedimientoRcb idProcedimientoRcb;
//    @JoinColumn(name = "id_fabricante", referencedColumnName = "id_fabricante")
//    @ManyToOne(optional = false)
//    private Fabricante idFabricante;

    public Propuestas() {
    }

    public Propuestas(Integer idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public Integer getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(Integer idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public BigDecimal getDescuentoOtorgado() {
        return descuentoOtorgado;
    }

    public void setDescuentoOtorgado(BigDecimal descuentoOtorgado) {
        this.descuentoOtorgado = descuentoOtorgado;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getPrecioUnitarioDescuento() {
        return precioUnitarioDescuento;
    }

    public void setPrecioUnitarioDescuento(BigDecimal precioUnitarioDescuento) {
        this.precioUnitarioDescuento = precioUnitarioDescuento;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Integer getGanador() {
        return ganador;
    }

    public void setGanador(Integer ganador) {
        this.ganador = ganador;
    }

    public Date getAperturaProgramada() {
        return aperturaProgramada;
    }

    public void setAperturaProgramada(Date aperturaProgramada) {
        this.aperturaProgramada = aperturaProgramada;
    }

    public Date getAperturaRealizada() {
        return aperturaRealizada;
    }

    public void setAperturaRealizada(Date aperturaRealizada) {
        this.aperturaRealizada = aperturaRealizada;
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

    public Proveedores getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedores idProveedor) {
        this.idProveedor = idProveedor;
    }

    public ProcedimientoRcb getIdProcedimientoRcb() {
        return idProcedimientoRcb;
    }

    public void setIdProcedimientoRcb(ProcedimientoRcb idProcedimientoRcb) {
        this.idProcedimientoRcb = idProcedimientoRcb;
    }

//    public Fabricante getIdFabricante() {
//        return idFabricante;
//    }
//
//    public void setIdFabricante(Fabricante idFabricante) {
//        this.idFabricante = idFabricante;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPropuesta != null ? idPropuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Propuestas)) {
            return false;
        }
        Propuestas other = (Propuestas) object;
        if ((this.idPropuesta == null && other.idPropuesta != null) || (this.idPropuesta != null && !this.idPropuesta.equals(other.idPropuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Propuestas[ idPropuesta=" + idPropuesta + " ]";
    }

}
