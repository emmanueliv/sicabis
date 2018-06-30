
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "dpn")
@NamedQueries({
    @NamedQuery(name = "Dpn.findAllDesc", query = "SELECT d FROM Dpn d ORDER BY d.idDpn DESC")
})
public class Dpn implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dpn")
    private Integer idDpn;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "total_piezas_dpn")
    private BigDecimal totalPiezasDpn;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "dpn_mes")
    private String dpnMes;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "anio")
    private Integer anio;
    @Column(name = "mes")
    private Integer mes;
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
    @JoinColumn(name = "id_periodo_mes", referencedColumnName = "id_periodo_mes")
    @ManyToOne(optional = false)
    private PeriodoMes idPeriodoMes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDpn")
    private List<DpnInsumos> dpnInsumosList;
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private Estatus idEstatus;

    public Dpn() {
    }

    public Dpn(Integer idDpn) {
        this.idDpn = idDpn;
    }

    public Integer getIdDpn() {
        return idDpn;
    }

    public void setIdDpn(Integer idDpn) {
        this.idDpn = idDpn;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public BigDecimal getTotalPiezasDpn() {
        return totalPiezasDpn;
    }

    public void setTotalPiezasDpn(BigDecimal totalPiezasDpn) {
        this.totalPiezasDpn = totalPiezasDpn;
    }

    public String getDpnMes() {
        return dpnMes;
    }

    public void setDpnMes(String dpnMes) {
        this.dpnMes = dpnMes;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public PeriodoMes getIdPeriodoMes() {
        return idPeriodoMes;
    }

    public void setIdPeriodoMes(PeriodoMes idPeriodoMes) {
        this.idPeriodoMes = idPeriodoMes;
    }

    @XmlTransient
    public List<DpnInsumos> getDpnInsumosList() {
        return dpnInsumosList;
    }

    public void setDpnInsumosList(List<DpnInsumos> dpnInsumosList) {
        this.dpnInsumosList = dpnInsumosList;
    }

    public Estatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Estatus idEstatus) {
        this.idEstatus = idEstatus;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDpn != null ? idDpn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rcb)) {
            return false;
        }
        Dpn other = (Dpn) object;
        if ((this.idDpn == null && other.idDpn != null) || (this.idDpn != null && !this.idDpn.equals(other.idDpn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Dpn[ idDpn=" + idDpn + " ]";
    }
    
}
