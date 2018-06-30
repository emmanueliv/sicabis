/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.modelo;

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
 * @author 9RZCBG2
 */
@Entity
@Table(name = "salidas_cenadi_umu_guia_de_distribucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucion.findAll", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucion s"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucion.findByIdSalidasCenadiUmuGuiaDeDistribucion", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucion s WHERE s.idSalidasCenadiUmuGuiaDeDistribucion = :idSalidasCenadiUmuGuiaDeDistribucion"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucion.findByRemision", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucion s WHERE s.remision = :remision"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucion.findByUmu", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucion s WHERE s.umu = :umu"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucion.findByFolioissste", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucion s WHERE s.folioissste = :folioissste"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucion.findByCantidad", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucion s WHERE s.cantidad = :cantidad"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucion.findByPartidaPresupuestalUmu", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucion s WHERE s.partidaPresupuestalUmu = :partidaPresupuestalUmu"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucion.findByDescUmu", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucion s WHERE s.descUmu = :descUmu"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucion.findByImporte", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucion s WHERE s.importe = :importe"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucion.findBySubinventario", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucion s WHERE s.subinventario = :subinventario"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucion.findByFechaInventario", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucion s WHERE s.fechaInventario = :fechaInventario"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucion.findByFechaIngreso", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucion s WHERE s.fechaIngreso = :fechaIngreso")})
public class SalidasCenadiUmuGuiaDeDistribucion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_salidas_cenadi_umu_guia_de_distribucion")
    private Integer idSalidasCenadiUmuGuiaDeDistribucion;
    @Column(name = "remision")
    private Integer remision;
    @Size(max = 250)
    @Column(name = "umu")
    private String umu;
    @Column(name = "folioissste")
    private Integer folioissste;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Size(max = 250)
    @Column(name = "partida_presupuestal_umu")
    private String partidaPresupuestalUmu;
    @Size(max = 250)
    @Column(name = "desc_umu")
    private String descUmu;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe")
    private BigDecimal importe;
    @Size(max = 250)
    @Column(name = "subinventario")
    private String subinventario;
    @Column(name = "fecha_inventario")
    @Temporal(TemporalType.DATE)
    private Date fechaInventario;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    public SalidasCenadiUmuGuiaDeDistribucion() {
    }

    public SalidasCenadiUmuGuiaDeDistribucion(Integer idSalidasCenadiUmuGuiaDeDistribucion) {
        this.idSalidasCenadiUmuGuiaDeDistribucion = idSalidasCenadiUmuGuiaDeDistribucion;
    }

    public Integer getIdSalidasCenadiUmuGuiaDeDistribucion() {
        return idSalidasCenadiUmuGuiaDeDistribucion;
    }

    public void setIdSalidasCenadiUmuGuiaDeDistribucion(Integer idSalidasCenadiUmuGuiaDeDistribucion) {
        this.idSalidasCenadiUmuGuiaDeDistribucion = idSalidasCenadiUmuGuiaDeDistribucion;
    }

    public Integer getRemision() {
        return remision;
    }

    public void setRemision(Integer remision) {
        this.remision = remision;
    }

    public String getUmu() {
        return umu;
    }

    public void setUmu(String umu) {
        this.umu = umu;
    }

    public Integer getFolioissste() {
        return folioissste;
    }

    public void setFolioissste(Integer folioissste) {
        this.folioissste = folioissste;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getPartidaPresupuestalUmu() {
        return partidaPresupuestalUmu;
    }

    public void setPartidaPresupuestalUmu(String partidaPresupuestalUmu) {
        this.partidaPresupuestalUmu = partidaPresupuestalUmu;
    }

    public String getDescUmu() {
        return descUmu;
    }

    public void setDescUmu(String descUmu) {
        this.descUmu = descUmu;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getSubinventario() {
        return subinventario;
    }

    public void setSubinventario(String subinventario) {
        this.subinventario = subinventario;
    }

    public Date getFechaInventario() {
        return fechaInventario;
    }

    public void setFechaInventario(Date fechaInventario) {
        this.fechaInventario = fechaInventario;
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
        hash += (idSalidasCenadiUmuGuiaDeDistribucion != null ? idSalidasCenadiUmuGuiaDeDistribucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalidasCenadiUmuGuiaDeDistribucion)) {
            return false;
        }
        SalidasCenadiUmuGuiaDeDistribucion other = (SalidasCenadiUmuGuiaDeDistribucion) object;
        if ((this.idSalidasCenadiUmuGuiaDeDistribucion == null && other.idSalidasCenadiUmuGuiaDeDistribucion != null) || (this.idSalidasCenadiUmuGuiaDeDistribucion != null && !this.idSalidasCenadiUmuGuiaDeDistribucion.equals(other.idSalidasCenadiUmuGuiaDeDistribucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.service.silodisa.modelo.SalidasCenadiUmuGuiaDeDistribucion[ idSalidasCenadiUmuGuiaDeDistribucion=" + idSalidasCenadiUmuGuiaDeDistribucion + " ]";
    }
    
}
