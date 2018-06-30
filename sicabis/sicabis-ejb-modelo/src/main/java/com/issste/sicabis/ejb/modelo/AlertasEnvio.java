package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "alertas_envio")
public class AlertasEnvio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_alertas_envio")
    private Integer idAlertasEnvio;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "consumo")
    private BigDecimal consumo;
    @Column(name = "dpn_sugerida_umu")
    private Integer dpnSugeridaUmu;
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
    @JoinColumn(name = "id_dpn_insumo", referencedColumnName = "id_dpn_insumo")
    @ManyToOne(optional = false)
    private DpnInsumos idDpnInsumo;
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private Estatus idEstatus;
    @JoinColumn(name = "id_alertas_dpn", referencedColumnName = "id_alertas_dpn")
    @ManyToOne(optional = false)
    private AlertasDpn idAlertasDpn;

    public AlertasEnvio() {
    }

    public AlertasEnvio(Integer idAlertasEnvio) {
        this.idAlertasEnvio = idAlertasEnvio;
    }

    public Integer getIdAlertasEnvio() {
        return idAlertasEnvio;
    }

    public void setIdAlertasEnvio(Integer idAlertasEnvio) {
        this.idAlertasEnvio = idAlertasEnvio;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public DpnInsumos getIdDpnInsumo() {
        return idDpnInsumo;
    }

    public void setIdDpnInsumo(DpnInsumos idDpnInsumo) {
        this.idDpnInsumo = idDpnInsumo;
    }

    public Estatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Estatus idEstatus) {
        this.idEstatus = idEstatus;
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

    public BigDecimal getConsumo() {
        return consumo;
    }

    public void setConsumo(BigDecimal consumo) {
        this.consumo = consumo;
    }

    public Integer getDpnSugeridaUmu() {
        return dpnSugeridaUmu;
    }

    public void setDpnSugeridaUmu(Integer dpnSugeridaUmu) {
        this.dpnSugeridaUmu = dpnSugeridaUmu;
    }

    public AlertasDpn getIdAlertasDpn() {
        return idAlertasDpn;
    }

    public void setIdAlertasDpn(AlertasDpn idAlertasDpn) {
        this.idAlertasDpn = idAlertasDpn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlertasEnvio != null ? idAlertasEnvio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlertasEnvio)) {
            return false;
        }
        AlertasEnvio other = (AlertasEnvio) object;
        if ((this.idAlertasEnvio == null && other.idAlertasEnvio != null) || (this.idAlertasEnvio != null && !this.idAlertasEnvio.equals(other.idAlertasEnvio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.AlertasEnvio[ idAlertasEnvio=" + idAlertasEnvio + " ]";
    }
}
