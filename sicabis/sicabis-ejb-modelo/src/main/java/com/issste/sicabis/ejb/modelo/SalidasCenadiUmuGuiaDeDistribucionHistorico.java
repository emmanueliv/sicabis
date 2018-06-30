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
 * @author 9RZCBG2
 */
@Entity
@Table(name = "salidas_cenadi_umu_guia_de_distribucion_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucionHistorico.findAll", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucionHistorico s"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucionHistorico.findByIdSalidasCenadiUmuGuiaDeDistribucionHistorico", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucionHistorico s WHERE s.idSalidasCenadiUmuGuiaDeDistribucionHistorico = :idSalidasCenadiUmuGuiaDeDistribucionHistorico"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucionHistorico.findByRemision", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucionHistorico s WHERE s.remision = :remision"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucionHistorico.findByUmu", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucionHistorico s WHERE s.umu = :umu"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucionHistorico.findByFolioissste", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucionHistorico s WHERE s.folioissste = :folioissste"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucionHistorico.findByCantidad", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucionHistorico s WHERE s.cantidad = :cantidad"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucionHistorico.findByPartidaPresupuestalUmu", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucionHistorico s WHERE s.partidaPresupuestalUmu = :partidaPresupuestalUmu"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucionHistorico.findByDescUmu", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucionHistorico s WHERE s.descUmu = :descUmu"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucionHistorico.findByImporte", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucionHistorico s WHERE s.importe = :importe"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucionHistorico.findBySubinventario", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucionHistorico s WHERE s.subinventario = :subinventario"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucionHistorico.findByFechaInventario", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucionHistorico s WHERE s.fechaInventario = :fechaInventario"),
    @NamedQuery(name = "SalidasCenadiUmuGuiaDeDistribucionHistorico.findByFechaIngreso", query = "SELECT s FROM SalidasCenadiUmuGuiaDeDistribucionHistorico s WHERE s.fechaIngreso = :fechaIngreso")})
public class SalidasCenadiUmuGuiaDeDistribucionHistorico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_salidas_cenadi_umu_guia_de_distribucion_historico")
    private Integer idSalidasCenadiUmuGuiaDeDistribucionHistorico;
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

    public SalidasCenadiUmuGuiaDeDistribucionHistorico() {
    }

    public SalidasCenadiUmuGuiaDeDistribucionHistorico(Integer idSalidasCenadiUmuGuiaDeDistribucionHistorico) {
        this.idSalidasCenadiUmuGuiaDeDistribucionHistorico = idSalidasCenadiUmuGuiaDeDistribucionHistorico;
    }

    public Integer getIdSalidasCenadiUmuGuiaDeDistribucionHistorico() {
        return idSalidasCenadiUmuGuiaDeDistribucionHistorico;
    }

    public void setIdSalidasCenadiUmuGuiaDeDistribucionHistorico(Integer idSalidasCenadiUmuGuiaDeDistribucionHistorico) {
        this.idSalidasCenadiUmuGuiaDeDistribucionHistorico = idSalidasCenadiUmuGuiaDeDistribucionHistorico;
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
        hash += (idSalidasCenadiUmuGuiaDeDistribucionHistorico != null ? idSalidasCenadiUmuGuiaDeDistribucionHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalidasCenadiUmuGuiaDeDistribucionHistorico)) {
            return false;
        }
        SalidasCenadiUmuGuiaDeDistribucionHistorico other = (SalidasCenadiUmuGuiaDeDistribucionHistorico) object;
        if ((this.idSalidasCenadiUmuGuiaDeDistribucionHistorico == null && other.idSalidasCenadiUmuGuiaDeDistribucionHistorico != null) || (this.idSalidasCenadiUmuGuiaDeDistribucionHistorico != null && !this.idSalidasCenadiUmuGuiaDeDistribucionHistorico.equals(other.idSalidasCenadiUmuGuiaDeDistribucionHistorico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.SalidasCenadiUmuGuiaDeDistribucionHistorico[ idSalidasCenadiUmuGuiaDeDistribucionHistorico=" + idSalidasCenadiUmuGuiaDeDistribucionHistorico + " ]";
    }
    
}
