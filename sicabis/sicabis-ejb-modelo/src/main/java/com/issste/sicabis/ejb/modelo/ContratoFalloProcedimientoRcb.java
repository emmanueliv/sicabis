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
@Table(name = "contrato_fallo_procedimiento_rcb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoFalloProcedimientoRcb.findAll", query = "SELECT c FROM ContratoFalloProcedimientoRcb c"),
    @NamedQuery(name = "ContratoFalloProcedimientoRcb.findByIdContratoFalloProcedimientoRcb", query = "SELECT c FROM ContratoFalloProcedimientoRcb c WHERE c.idContratoFalloProcedimientoRcb = :idContratoFalloProcedimientoRcb"),
    @NamedQuery(name = "ContratoFalloProcedimientoRcb.findByActivo", query = "SELECT c FROM ContratoFalloProcedimientoRcb c WHERE c.activo = :activo"),
    @NamedQuery(name = "ContratoFalloProcedimientoRcb.findByIdContrato", query = "SELECT c FROM ContratoFalloProcedimientoRcb c WHERE c.idContrato = :idContrato"),
    @NamedQuery(name = "ContratoFalloProcedimientoRcb.findByUsuarioAlta", query = "SELECT c FROM ContratoFalloProcedimientoRcb c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "ContratoFalloProcedimientoRcb.findByUsuarioBaja", query = "SELECT c FROM ContratoFalloProcedimientoRcb c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "ContratoFalloProcedimientoRcb.findByUsuarioModificacion", query = "SELECT c FROM ContratoFalloProcedimientoRcb c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "ContratoFalloProcedimientoRcb.findByFechaAlta", query = "SELECT c FROM ContratoFalloProcedimientoRcb c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "ContratoFalloProcedimientoRcb.findByFechaBaja", query = "SELECT c FROM ContratoFalloProcedimientoRcb c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "ContratoFalloProcedimientoRcb.findByFechaModificacion", query = "SELECT c FROM ContratoFalloProcedimientoRcb c WHERE c.fechaModificacion = :fechaModificacion")})
public class ContratoFalloProcedimientoRcb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contrato_fallo_procedimiento_rcb")
    private Integer idContratoFalloProcedimientoRcb;
    @Column(name = "activo")
    private Integer activo;
    //@Size(max = 200)
    @Column(name = "descripcion_amplia")
    private String descripcionAmplia;
    @Column(name = "nota")
    private String nota;
    @Column(name = "cantidad_agregada_convenio")
    private Integer cantidadAgregadaConvenio;
    @Column(name = "unidad_pieza_convenio")
    private String unidadPiezaConvenio;
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
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
    @JoinColumn(name = "id_fallo_procedimiento_rcb", referencedColumnName = "id_fallo_procedimiento_rcb")
    @ManyToOne(optional = false)
    private FalloProcedimientoRcb idFalloProcedimientoRcb;
    @JoinColumn(name = "id_contrato", referencedColumnName = "id_contrato")
    @ManyToOne(optional = false)
    private Contratos idContrato;

    public ContratoFalloProcedimientoRcb() {
    }

    public ContratoFalloProcedimientoRcb(Integer idContratoFalloProcedimientoRcb) {
        this.idContratoFalloProcedimientoRcb = idContratoFalloProcedimientoRcb;
    }

    public Integer getIdContratoFalloProcedimientoRcb() {
        return idContratoFalloProcedimientoRcb;
    }

    public void setIdContratoFalloProcedimientoRcb(Integer idContratoFalloProcedimientoRcb) {
        this.idContratoFalloProcedimientoRcb = idContratoFalloProcedimientoRcb;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getDescripcionAmplia() {
        return descripcionAmplia;
    }

    public void setDescripcionAmplia(String descripcionAmplia) {
        this.descripcionAmplia = descripcionAmplia;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Integer getCantidadAgregadaConvenio() {
        return cantidadAgregadaConvenio;
    }

    public void setCantidadAgregadaConvenio(Integer cantidadAgregadaConvenio) {
        this.cantidadAgregadaConvenio = cantidadAgregadaConvenio;
    }

    public String getUnidadPiezaConvenio() {
        return unidadPiezaConvenio;
    }

    public void setUnidadPiezaConvenio(String unidadPiezaConvenio) {
        this.unidadPiezaConvenio = unidadPiezaConvenio;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
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

    public FalloProcedimientoRcb getIdFalloProcedimientoRcb() {
        return idFalloProcedimientoRcb;
    }

    public void setIdFalloProcedimientoRcb(FalloProcedimientoRcb idFalloProcedimientoRcb) {
        this.idFalloProcedimientoRcb = idFalloProcedimientoRcb;
    }

    public Contratos getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Contratos idContrato) {
        this.idContrato = idContrato;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContratoFalloProcedimientoRcb != null ? idContratoFalloProcedimientoRcb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoFalloProcedimientoRcb)) {
            return false;
        }
        ContratoFalloProcedimientoRcb other = (ContratoFalloProcedimientoRcb) object;
        if ((this.idContratoFalloProcedimientoRcb == null && other.idContratoFalloProcedimientoRcb != null) || (this.idContratoFalloProcedimientoRcb != null && !this.idContratoFalloProcedimientoRcb.equals(other.idContratoFalloProcedimientoRcb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb[ idContratoFalloProcedimientoRcb=" + idContratoFalloProcedimientoRcb + " ]";
    }
    
}
