
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "alertas_dpn")
public class AlertasDpn implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_alertas_dpn")
    private Integer idAlertasDpn;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "mes")
    private Integer mes;
    @Column(name = "anio")
    private Integer anio;
    @JoinColumn(name = "ur", referencedColumnName = "ur")
    @ManyToOne(optional = false)
    private Ur ur;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios idUsuario;
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private Estatus idEstatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlertasDpn")
    private List<AlertasEnvio> alertasEnvioList;

    public AlertasDpn() {
    }

    public AlertasDpn(Integer idAlertasDpn) {
        this.idAlertasDpn = idAlertasDpn;
    }

    public Integer getIdAlertasDpn() {
        return idAlertasDpn;
    }

    public void setIdAlertasDpn(Integer idAlertasDpn) {
        this.idAlertasDpn = idAlertasDpn;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Ur getUr() {
        return ur;
    }

    public void setUr(Ur ur) {
        this.ur = ur;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Estatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Estatus idEstatus) {
        this.idEstatus = idEstatus;
    }

    @XmlTransient
    public List<AlertasEnvio> getAlertasEnvioList() {
        return alertasEnvioList;
    }

    public void setAlertasEnvioList(List<AlertasEnvio> alertasEnvioList) {
        this.alertasEnvioList = alertasEnvioList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlertasDpn != null ? idAlertasDpn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlertasDpn)) {
            return false;
        }
        AlertasDpn other = (AlertasDpn) object;
        if ((this.idAlertasDpn == null && other.idAlertasDpn != null) || (this.idAlertasDpn != null && !this.idAlertasDpn.equals(other.idAlertasDpn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.AlertasDpn[ idAlertasDpn=" + idAlertasDpn + " ]";
    }
}
