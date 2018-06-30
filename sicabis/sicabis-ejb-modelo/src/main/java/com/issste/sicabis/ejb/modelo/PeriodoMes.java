
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "periodo_mes")
@NamedQueries({
    @NamedQuery(name = "PeriodoMes.findByFechaCorte", query = "SELECT pm FROM PeriodoMes pm WHERE pm.fechaCorte > :fechaCorte ORDER BY pm.fechaCorte "),
    @NamedQuery(name = "PeriodoMes.findByFechaCorteMod", query = "SELECT pm FROM PeriodoMes pm WHERE pm.fechaCorte = :fechaCorte")
})
public class PeriodoMes implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_periodo_mes")
    private Integer idPeriodoMes;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Column(name = "fecha_corte")
    @Temporal(TemporalType.DATE)
    private Date fechaCorte;
    @Column(name = "mes_letra")
    private String mesLetra;
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
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne(optional = false)
    private Area idArea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeriodoMes")
    private List<Dpn> dpnList;

    public PeriodoMes() {
    }

    public PeriodoMes(Integer idPeriodoMes) {
        this.idPeriodoMes = idPeriodoMes;
    }

    public Integer getIdPeriodoMes() {
        return idPeriodoMes;
    }

    public void setIdPeriodoMes(int idPeriodoMes) {
        this.idPeriodoMes = idPeriodoMes;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public String getMesLetra() {
        return mesLetra;
    }

    public void setMesLetra(String mesLetra) {
        this.mesLetra = mesLetra;
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

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    @XmlTransient
    public List<Dpn> getDpnList() {
        return dpnList;
    }

    public void setDpnList(List<Dpn> dpnList) {
        this.dpnList = dpnList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodoMes != null ? idPeriodoMes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodoMes)) {
            return false;
        }
        PeriodoMes other = (PeriodoMes) object;
        if ((this.idPeriodoMes == null && other.idPeriodoMes != null) || (this.idPeriodoMes != null && !this.idPeriodoMes.equals(other.idPeriodoMes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.PeriodoMes[ idPeriodoMes=" + idPeriodoMes + " ]";
    }
    
}
