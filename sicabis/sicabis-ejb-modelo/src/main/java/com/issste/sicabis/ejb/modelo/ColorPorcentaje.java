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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 6JWBBG2
 */
@Entity
@Table(name = "color_porcentaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ColorPorcentaje.findAll", query = "SELECT c FROM ColorPorcentaje c"),
    @NamedQuery(name = "ColorPorcentaje.findByIdColorPorcentaje", query = "SELECT c FROM ColorPorcentaje c WHERE c.idColorPorcentaje = :idColorPorcentaje"),
    @NamedQuery(name = "ColorPorcentaje.findByPorcentajeInicialFinalColor", query = "SELECT c FROM ColorPorcentaje c WHERE c.porcentajeInicial = :porcentajeInicial and c.porcentajeFinal = :porcentajeFinal and c.hexColor = :hexcolor and c.activo = 1"),
    @NamedQuery(name = "ColorPorcentaje.findByEstatusActivo", query = "SELECT c FROM ColorPorcentaje c WHERE c.activo = :estusactivo ORDER BY c.porcentajeInicial"),
    @NamedQuery(name = "ColorPorcentaje.findByPorcentajeInicial", query = "SELECT c FROM ColorPorcentaje c WHERE c.porcentajeInicial = :porcentajeInicial"),
    @NamedQuery(name = "ColorPorcentaje.findByPorcentajeFinal", query = "SELECT c FROM ColorPorcentaje c WHERE c.porcentajeFinal = :porcentajeFinal"),
    @NamedQuery(name = "ColorPorcentaje.findByHexColor", query = "SELECT c FROM ColorPorcentaje c WHERE c.hexColor = :hexColor"),
    @NamedQuery(name = "ColorPorcentaje.findByFechaAlta", query = "SELECT c FROM ColorPorcentaje c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "ColorPorcentaje.findByFechaBaja", query = "SELECT c FROM ColorPorcentaje c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "ColorPorcentaje.findByFechaModificacion", query = "SELECT c FROM ColorPorcentaje c WHERE c.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "ColorPorcentaje.findByUsuarioAlta", query = "SELECT c FROM ColorPorcentaje c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "ColorPorcentaje.findByUsuarioBaja", query = "SELECT c FROM ColorPorcentaje c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "ColorPorcentaje.findByUsuarioModificacion", query = "SELECT c FROM ColorPorcentaje c WHERE c.usuarioModificacion = :usuarioModificacion")})
public class ColorPorcentaje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_color_porcentaje")
    private Integer idColorPorcentaje;
    @Column(name = "activo")
    private Integer activo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje_inicial")
    private BigDecimal porcentajeInicial;
    @Column(name = "porcentaje_final")
    private BigDecimal porcentajeFinal;
    @Size(max = 20)
    @Column(name = "hex_color")
    private String hexColor;
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;
    @Size(max = 50)
    @Column(name = "usuario_alta")
    private String usuarioAlta;
    @Size(max = 50)
    @Column(name = "usuario_baja")
    private String usuarioBaja;
    @Size(max = 50)
    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;

    public ColorPorcentaje() {
    }

    public ColorPorcentaje(Integer idColorPorcentaje) {
        this.idColorPorcentaje = idColorPorcentaje;
    }

    public Integer getIdColorPorcentaje() {
        return idColorPorcentaje;
    }

    public void setIdColorPorcentaje(Integer idColorPorcentaje) {
        this.idColorPorcentaje = idColorPorcentaje;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public BigDecimal getPorcentajeInicial() {
        return porcentajeInicial;
    }

    public void setPorcentajeInicial(BigDecimal porcentajeInicial) {
        this.porcentajeInicial = porcentajeInicial;
    }

    public BigDecimal getPorcentajeFinal() {
        return porcentajeFinal;
    }

    public void setPorcentajeFinal(BigDecimal porcentajeFinal) {
        this.porcentajeFinal = porcentajeFinal;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idColorPorcentaje != null ? idColorPorcentaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ColorPorcentaje)) {
            return false;
        }
        ColorPorcentaje other = (ColorPorcentaje) object;
        if ((this.idColorPorcentaje == null && other.idColorPorcentaje != null) || (this.idColorPorcentaje != null && !this.idColorPorcentaje.equals(other.idColorPorcentaje))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "com.issste.sicabis.ejb.modelo.ColorPorcentaje[ idColorPorcentaje=" + idColorPorcentaje + " ]";
//    }
//    

    @Override
    public String toString() {
        return "ColorPorcentaje{" + "idColorPorcentaje=" + idColorPorcentaje + ", activo=" + activo + ", porcentajeInicial=" + porcentajeInicial + ", porcentajeFinal=" + porcentajeFinal + ", hexColor=" + hexColor + ", fechaAlta=" + fechaAlta + ", fechaBaja=" + fechaBaja + ", fechaModificacion=" + fechaModificacion + ", usuarioAlta=" + usuarioAlta + ", usuarioBaja=" + usuarioBaja + ", usuarioModificacion=" + usuarioModificacion + '}';
    }
    
    
}
