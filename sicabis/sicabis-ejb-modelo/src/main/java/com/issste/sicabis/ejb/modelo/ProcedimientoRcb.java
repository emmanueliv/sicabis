/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fabianvr
 */
@Entity
@Table(name = "procedimiento_rcb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcedimientoRcb.findAll", query = "SELECT p FROM ProcedimientoRcb p"),
    @NamedQuery(name = "ProcedimientoRcb.findByIdProcedimientoRcb", query = "SELECT p FROM ProcedimientoRcb p WHERE p.idProcedimientoRcb = :idProcedimientoRcb"),
    @NamedQuery(name = "ProcedimientoRcb.findByActivo", query = "SELECT p FROM ProcedimientoRcb p WHERE p.activo = :activo"),
    @NamedQuery(name = "ProcedimientoRcb.findByCantidadPiezas", query = "SELECT p FROM ProcedimientoRcb p WHERE p.cantidadPiezas = :cantidadPiezas"),
    @NamedQuery(name = "ProcedimientoRcb.findByDesierta", query = "SELECT p FROM ProcedimientoRcb p WHERE p.desierta = :desierta"),
    @NamedQuery(name = "ProcedimientoRcb.findByDesiertaParcial", query = "SELECT p FROM ProcedimientoRcb p WHERE p.desiertaParcial = :desiertaParcial"),
    @NamedQuery(name = "ProcedimientoRcb.findByPrecioUnitario", query = "SELECT p FROM ProcedimientoRcb p WHERE p.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "ProcedimientoRcb.findByImporte", query = "SELECT p FROM ProcedimientoRcb p WHERE p.importe = :importe"),
    @NamedQuery(name = "ProcedimientoRcb.findByExistencias", query = "SELECT p FROM ProcedimientoRcb p WHERE p.existencias = :existencias"),
    @NamedQuery(name = "ProcedimientoRcb.findByConsumoPromedio", query = "SELECT p FROM ProcedimientoRcb p WHERE p.consumoPromedio = :consumoPromedio"),
    @NamedQuery(name = "ProcedimientoRcb.findByMesesCobertura", query = "SELECT p FROM ProcedimientoRcb p WHERE p.mesesCobertura = :mesesCobertura"),
    @NamedQuery(name = "ProcedimientoRcb.findByUsuarioAlta", query = "SELECT p FROM ProcedimientoRcb p WHERE p.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "ProcedimientoRcb.findByUsuarioBaja", query = "SELECT p FROM ProcedimientoRcb p WHERE p.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "ProcedimientoRcb.findByUsuarioModificacion", query = "SELECT p FROM ProcedimientoRcb p WHERE p.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "ProcedimientoRcb.findByFechaAlta", query = "SELECT p FROM ProcedimientoRcb p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "ProcedimientoRcb.findByFechaBaja", query = "SELECT p FROM ProcedimientoRcb p WHERE p.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "ProcedimientoRcb.findByFechaModificacion", query = "SELECT p FROM ProcedimientoRcb p WHERE p.fechaModificacion = :fechaModificacion")})
public class ProcedimientoRcb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_procedimiento_rcb")
    private Integer idProcedimientoRcb;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_piezas")
    private int cantidadPiezas;
    @Column(name = "cantidad_piezas_original")
    private int cantidadPiezasOriginal;
    @Column(name = "desierta")
    private Integer desierta;
    @Column(name = "desierta_parcial")
    private Integer desiertaParcial;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe")
    private BigDecimal importe;
    @Basic(optional = false)
    @Column(name = "existencias")
    private int existencias;
    @Basic(optional = false)
    @Column(name = "consumo_promedio")
    private int consumoPromedio;
    @Basic(optional = false)
    @Column(name = "meses_cobertura")
    private int mesesCobertura;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProcedimientoRcb")
    private List<FalloProcedimientoRcb> falloProcedimientoRcbList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProcedimientoRcb")
    private List<ProcedimientoRcbDestinos> procedimientoRcbDestinosList;
    @JoinColumn(name = "id_rcb_insumos", referencedColumnName = "id_rcb_insumos")
    @ManyToOne(optional = false)
    private RcbInsumos idRcbInsumos;
    @JoinColumn(name = "id_procedimiento", referencedColumnName = "id_procedimiento")
    @ManyToOne(optional = false)
    private Procedimientos idProcedimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProcedimientoRcb")
    private List<Propuestas> propuestasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProcedimientoRcb")
    private List<Notas> notasList;

    public ProcedimientoRcb() {
    }

    public ProcedimientoRcb(Integer idProcedimientoRcb) {
        this.idProcedimientoRcb = idProcedimientoRcb;
    }

    public ProcedimientoRcb(Integer idProcedimientoRcb, int cantidadPiezas, BigDecimal precioUnitario, BigDecimal importe, int existencias, int consumoPromedio, int mesesCobertura) {
        this.idProcedimientoRcb = idProcedimientoRcb;
        this.cantidadPiezas = cantidadPiezas;
        this.precioUnitario = precioUnitario;
        this.importe = importe;
        this.existencias = existencias;
        this.consumoPromedio = consumoPromedio;
        this.mesesCobertura = mesesCobertura;
    }

    public Integer getIdProcedimientoRcb() {
        return idProcedimientoRcb;
    }

    public void setIdProcedimientoRcb(Integer idProcedimientoRcb) {
        this.idProcedimientoRcb = idProcedimientoRcb;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public int getCantidadPiezas() {
        return cantidadPiezas;
    }

    public void setCantidadPiezas(int cantidadPiezas) {
        this.cantidadPiezas = cantidadPiezas;
    }

    public int getCantidadPiezasOriginal() {
        return cantidadPiezasOriginal;
    }

    public void setCantidadPiezasOriginal(int cantidadPiezasOriginal) {
        this.cantidadPiezasOriginal = cantidadPiezasOriginal;
    }
    
    public Integer getDesierta() {
        return desierta;
    }

    public void setDesierta(Integer desierta) {
        this.desierta = desierta;
    }

    public Integer getDesiertaParcial() {
        return desiertaParcial;
    }

    public void setDesiertaParcial(Integer desiertaParcial) {
        this.desiertaParcial = desiertaParcial;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public int getConsumoPromedio() {
        return consumoPromedio;
    }

    public void setConsumoPromedio(int consumoPromedio) {
        this.consumoPromedio = consumoPromedio;
    }

    public int getMesesCobertura() {
        return mesesCobertura;
    }

    public void setMesesCobertura(int mesesCobertura) {
        this.mesesCobertura = mesesCobertura;
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

    @XmlTransient
    public List<FalloProcedimientoRcb> getFalloProcedimientoRcbList() {
        return falloProcedimientoRcbList;
    }

    public void setFalloProcedimientoRcbList(List<FalloProcedimientoRcb> falloProcedimientoRcbList) {
        this.falloProcedimientoRcbList = falloProcedimientoRcbList;
    }

    @XmlTransient
    public List<ProcedimientoRcbDestinos> getProcedimientoRcbDestinosList() {
        return procedimientoRcbDestinosList;
    }

    public void setProcedimientoRcbDestinosList(List<ProcedimientoRcbDestinos> procedimientoRcbDestinosList) {
        this.procedimientoRcbDestinosList = procedimientoRcbDestinosList;
    }

    public RcbInsumos getIdRcbInsumos() {
        return idRcbInsumos;
    }

    public void setIdRcbInsumos(RcbInsumos idRcbInsumos) {
        this.idRcbInsumos = idRcbInsumos;
    }

    public Procedimientos getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(Procedimientos idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    @XmlTransient
    public List<Propuestas> getPropuestasList() {
        return propuestasList;
    }

    public void setPropuestasList(List<Propuestas> propuestasList) {
        this.propuestasList = propuestasList;
    }

    @XmlTransient
    public List<Notas> getNotasList() {
        return notasList;
    }

    public void setNotasList(List<Notas> notasList) {
        this.notasList = notasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcedimientoRcb != null ? idProcedimientoRcb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcedimientoRcb)) {
            return false;
        }
        ProcedimientoRcb other = (ProcedimientoRcb) object;
        if ((this.idProcedimientoRcb == null && other.idProcedimientoRcb != null) || (this.idProcedimientoRcb != null && !this.idProcedimientoRcb.equals(other.idProcedimientoRcb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.ProcedimientoRcb[ idProcedimientoRcb=" + idProcedimientoRcb + " ]";
    }
    
}
