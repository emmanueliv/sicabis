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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 9RZCBG2
 */
@Entity
@Table(name = "alertas_operativas_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlertasOperativasHistorico.findAll", query = "SELECT a FROM AlertasOperativasHistorico a"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByIdAlertasOperativasHistorico", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.idAlertasOperativasHistorico = :idAlertasOperativasHistorico"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByClave", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.clave = :clave"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByClaveUml", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.claveUml = :claveUml"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByClaDelegacion", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.claDelegacion = :claDelegacion"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByDescripcionDelegacion", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.descripcionDelegacion = :descripcionDelegacion"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByOrdinariosConfirmados", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.ordinariosConfirmados = :ordinariosConfirmados"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByExtraordinariosProceso", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.extraordinariosProceso = :extraordinariosProceso"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByExtraordinariosTransito", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.extraordinariosTransito = :extraordinariosTransito"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByNombreDelegacion", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.nombreDelegacion = :nombreDelegacion"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByNombreUmu", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.nombreUmu = :nombreUmu"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByNumeroUmu", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.numeroUmu = :numeroUmu"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByExtraordinariosConfirmado", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.extraordinariosConfirmado = :extraordinariosConfirmado"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByOrdinariosProceso", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.ordinariosProceso = :ordinariosProceso"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByOrdinariosTransito", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.ordinariosTransito = :ordinariosTransito"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByPeriodoDpn", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.periodoDpn = :periodoDpn"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByPiezasDpn", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.piezasDpn = :piezasDpn"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByTotal", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.total = :total"),
    @NamedQuery(name = "AlertasOperativasHistorico.findByFechaIngreso", query = "SELECT a FROM AlertasOperativasHistorico a WHERE a.fechaIngreso = :fechaIngreso")})
public class AlertasOperativasHistorico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_alertas_operativas_historico")
    private Integer idAlertasOperativasHistorico;
    @Size(max = 50)
    @Column(name = "clave")
    private String clave;
    @Size(max = 50)
    @Column(name = "clave_uml")
    private String claveUml;
    @Size(max = 50)
    @Column(name = "cla_delegacion")
    private String claDelegacion;
    @Size(max = 5000)
    @Column(name = "descripcion_delegacion")
    private String descripcionDelegacion;
    @Column(name = "ordinarios_confirmados")
    private Integer ordinariosConfirmados;
    @Column(name = "extraordinarios_proceso")
    private Integer extraordinariosProceso;
    @Column(name = "extraordinarios_transito")
    private Integer extraordinariosTransito;
    @Size(max = 100)
    @Column(name = "nombre_delegacion")
    private String nombreDelegacion;
    @Size(max = 100)
    @Column(name = "nombre_umu")
    private String nombreUmu;
    @Size(max = 30)
    @Column(name = "numero_umu")
    private String numeroUmu;
    @Column(name = "extraordinarios_confirmado")
    private Integer extraordinariosConfirmado;
    @Column(name = "ordinarios_proceso")
    private Integer ordinariosProceso;
    @Column(name = "ordinarios_transito")
    private Integer ordinariosTransito;
    @Size(max = 100)
    @Column(name = "periodo_dpn")
    private String periodoDpn;
    @Column(name = "piezas_dpn")
    private Integer piezasDpn;
    @Column(name = "total")
    private Integer total;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    public AlertasOperativasHistorico() {
    }

    public AlertasOperativasHistorico(Integer idAlertasOperativasHistorico) {
        this.idAlertasOperativasHistorico = idAlertasOperativasHistorico;
    }

    public Integer getIdAlertasOperativasHistorico() {
        return idAlertasOperativasHistorico;
    }

    public void setIdAlertasOperativasHistorico(Integer idAlertasOperativasHistorico) {
        this.idAlertasOperativasHistorico = idAlertasOperativasHistorico;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClaveUml() {
        return claveUml;
    }

    public void setClaveUml(String claveUml) {
        this.claveUml = claveUml;
    }

    public String getClaDelegacion() {
        return claDelegacion;
    }

    public void setClaDelegacion(String claDelegacion) {
        this.claDelegacion = claDelegacion;
    }

    public String getDescripcionDelegacion() {
        return descripcionDelegacion;
    }

    public void setDescripcionDelegacion(String descripcionDelegacion) {
        this.descripcionDelegacion = descripcionDelegacion;
    }

    public Integer getOrdinariosConfirmados() {
        return ordinariosConfirmados;
    }

    public void setOrdinariosConfirmados(Integer ordinariosConfirmados) {
        this.ordinariosConfirmados = ordinariosConfirmados;
    }

    public Integer getExtraordinariosProceso() {
        return extraordinariosProceso;
    }

    public void setExtraordinariosProceso(Integer extraordinariosProceso) {
        this.extraordinariosProceso = extraordinariosProceso;
    }

    public Integer getExtraordinariosTransito() {
        return extraordinariosTransito;
    }

    public void setExtraordinariosTransito(Integer extraordinariosTransito) {
        this.extraordinariosTransito = extraordinariosTransito;
    }

    public String getNombreDelegacion() {
        return nombreDelegacion;
    }

    public void setNombreDelegacion(String nombreDelegacion) {
        this.nombreDelegacion = nombreDelegacion;
    }

    public String getNombreUmu() {
        return nombreUmu;
    }

    public void setNombreUmu(String nombreUmu) {
        this.nombreUmu = nombreUmu;
    }

    public String getNumeroUmu() {
        return numeroUmu;
    }

    public void setNumeroUmu(String numeroUmu) {
        this.numeroUmu = numeroUmu;
    }

    public Integer getExtraordinariosConfirmado() {
        return extraordinariosConfirmado;
    }

    public void setExtraordinariosConfirmado(Integer extraordinariosConfirmado) {
        this.extraordinariosConfirmado = extraordinariosConfirmado;
    }

    public Integer getOrdinariosProceso() {
        return ordinariosProceso;
    }

    public void setOrdinariosProceso(Integer ordinariosProceso) {
        this.ordinariosProceso = ordinariosProceso;
    }

    public Integer getOrdinariosTransito() {
        return ordinariosTransito;
    }

    public void setOrdinariosTransito(Integer ordinariosTransito) {
        this.ordinariosTransito = ordinariosTransito;
    }

    public String getPeriodoDpn() {
        return periodoDpn;
    }

    public void setPeriodoDpn(String periodoDpn) {
        this.periodoDpn = periodoDpn;
    }

    public Integer getPiezasDpn() {
        return piezasDpn;
    }

    public void setPiezasDpn(Integer piezasDpn) {
        this.piezasDpn = piezasDpn;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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
        hash += (idAlertasOperativasHistorico != null ? idAlertasOperativasHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlertasOperativasHistorico)) {
            return false;
        }
        AlertasOperativasHistorico other = (AlertasOperativasHistorico) object;
        if ((this.idAlertasOperativasHistorico == null && other.idAlertasOperativasHistorico != null) || (this.idAlertasOperativasHistorico != null && !this.idAlertasOperativasHistorico.equals(other.idAlertasOperativasHistorico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.AlertasOperativasHistorico[ idAlertasOperativasHistorico=" + idAlertasOperativasHistorico + " ]";
    }
    
}
