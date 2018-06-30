/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.modelo;

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
 * @author 5XD9BG2
 */
@Entity
@Table(name = "alertas_operativas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlertasOperativas.findAll", query = "SELECT a FROM AlertasOperativas a"),
    @NamedQuery(name = "AlertasOperativas.findByIdAlertasOperativas", query = "SELECT a FROM AlertasOperativas a WHERE a.idAlertasOperativas = :idAlertasOperativas"),
    @NamedQuery(name = "AlertasOperativas.findByClave", query = "SELECT a FROM AlertasOperativas a WHERE a.clave = :clave"),
    @NamedQuery(name = "AlertasOperativas.findByClaveUml", query = "SELECT a FROM AlertasOperativas a WHERE a.claveUml = :claveUml"),
    @NamedQuery(name = "AlertasOperativas.findByClaDelegacion", query = "SELECT a FROM AlertasOperativas a WHERE a.claDelegacion = :claDelegacion"),
    @NamedQuery(name = "AlertasOperativas.findByDescripcionDelegacion", query = "SELECT a FROM AlertasOperativas a WHERE a.descripcionDelegacion = :descripcionDelegacion"),
    @NamedQuery(name = "AlertasOperativas.findByOrdinariosConfirmados", query = "SELECT a FROM AlertasOperativas a WHERE a.ordinariosConfirmados = :ordinariosConfirmados"),
    @NamedQuery(name = "AlertasOperativas.findByExtraordinariosProceso", query = "SELECT a FROM AlertasOperativas a WHERE a.extraordinariosProceso = :extraordinariosProceso"),
    @NamedQuery(name = "AlertasOperativas.findByExtraordinariosTransito", query = "SELECT a FROM AlertasOperativas a WHERE a.extraordinariosTransito = :extraordinariosTransito"),
    @NamedQuery(name = "AlertasOperativas.findByNombreDelegacion", query = "SELECT a FROM AlertasOperativas a WHERE a.nombreDelegacion = :nombreDelegacion"),
    @NamedQuery(name = "AlertasOperativas.findByNombreUmu", query = "SELECT a FROM AlertasOperativas a WHERE a.nombreUmu = :nombreUmu"),
    @NamedQuery(name = "AlertasOperativas.findByNumeroUmu", query = "SELECT a FROM AlertasOperativas a WHERE a.numeroUmu = :numeroUmu"),
    @NamedQuery(name = "AlertasOperativas.findByExtraordinariosConfirmado", query = "SELECT a FROM AlertasOperativas a WHERE a.extraordinariosConfirmado = :extraordinariosConfirmado"),
    @NamedQuery(name = "AlertasOperativas.findByOrdinariosProceso", query = "SELECT a FROM AlertasOperativas a WHERE a.ordinariosProceso = :ordinariosProceso"),
    @NamedQuery(name = "AlertasOperativas.findByOrdinariosTransito", query = "SELECT a FROM AlertasOperativas a WHERE a.ordinariosTransito = :ordinariosTransito"),
    @NamedQuery(name = "AlertasOperativas.findByPeriodoDpn", query = "SELECT a FROM AlertasOperativas a WHERE a.periodoDpn = :periodoDpn"),
    @NamedQuery(name = "AlertasOperativas.findByPiezasDpn", query = "SELECT a FROM AlertasOperativas a WHERE a.piezasDpn = :piezasDpn"),
    @NamedQuery(name = "AlertasOperativas.findByTotal", query = "SELECT a FROM AlertasOperativas a WHERE a.total = :total"),
    @NamedQuery(name = "AlertasOperativas.findByFechaIngreso", query = "SELECT a FROM AlertasOperativas a WHERE a.fechaIngreso = :fechaIngreso")})
public class AlertasOperativas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_alertas_operativas")
    private Integer idAlertasOperativas;
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

    public AlertasOperativas() {
    }

    public AlertasOperativas(Integer idAlertasOperativas) {
        this.idAlertasOperativas = idAlertasOperativas;
    }

    public Integer getIdAlertasOperativas() {
        return idAlertasOperativas;
    }

    public void setIdAlertasOperativas(Integer idAlertasOperativas) {
        this.idAlertasOperativas = idAlertasOperativas;
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
        hash += (idAlertasOperativas != null ? idAlertasOperativas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlertasOperativas)) {
            return false;
        }
        AlertasOperativas other = (AlertasOperativas) object;
        if ((this.idAlertasOperativas == null && other.idAlertasOperativas != null) || (this.idAlertasOperativas != null && !this.idAlertasOperativas.equals(other.idAlertasOperativas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.service.silodisa.modelo.AlertasOperativas[ idAlertasOperativas=" + idAlertasOperativas + " ]";
    }
    
}
