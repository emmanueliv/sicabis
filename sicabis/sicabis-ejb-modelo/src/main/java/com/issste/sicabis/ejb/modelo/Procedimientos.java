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
 * @author kriosoft
 */
@Entity
@Table(name = "procedimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Procedimientos.findAll", query = "SELECT p FROM Procedimientos p"),
    @NamedQuery(name = "Procedimientos.findByIdProcedimiento", query = "SELECT p FROM Procedimientos p WHERE p.idProcedimiento = :idProcedimiento"),
    @NamedQuery(name = "Procedimientos.findByActivo", query = "SELECT p FROM Procedimientos p WHERE p.activo = :activo"),
    @NamedQuery(name = "Procedimientos.findByComentarios", query = "SELECT p FROM Procedimientos p WHERE p.comentarios = :comentarios"),
    @NamedQuery(name = "Procedimientos.findByNumeroProcedimiento", query = "SELECT p FROM Procedimientos p WHERE p.numeroProcedimiento = :numeroProcedimiento"),
    @NamedQuery(name = "Procedimientos.findByImporteTotal", query = "SELECT p FROM Procedimientos p WHERE p.importeTotal = :importeTotal"),
    @NamedQuery(name = "Procedimientos.findByFechaProcedimiento", query = "SELECT p FROM Procedimientos p WHERE p.fechaProcedimiento = :fechaProcedimiento"),
    @NamedQuery(name = "Procedimientos.findByUsuarioAlta", query = "SELECT p FROM Procedimientos p WHERE p.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Procedimientos.findByUsuarioBaja", query = "SELECT p FROM Procedimientos p WHERE p.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Procedimientos.findByUsuarioModificacion", query = "SELECT p FROM Procedimientos p WHERE p.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Procedimientos.findByFechaAlta", query = "SELECT p FROM Procedimientos p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Procedimientos.findByFechaBaja", query = "SELECT p FROM Procedimientos p WHERE p.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Procedimientos.findByFechaModificacion", query = "SELECT p FROM Procedimientos p WHERE p.fechaModificacion = :fechaModificacion")})
public class Procedimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_procedimiento")
    private Integer idProcedimiento;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "verifica_sansiones")
    private Integer verificaSansiones;
    @Size(max = 300)
    @Column(name = "comentarios")
    private String comentarios;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "numero_procedimiento")
    private String numeroProcedimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    //@NotNull
    @Column(name = "importe_total")
    private BigDecimal importeTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_procedimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaProcedimiento;
    @Column(name = "elaboracion_convocatoria")
    @Temporal(TemporalType.DATE)
    private Date elaboracionConvocatoria;
    @Column(name = "publicacion_convocatoria")
    @Temporal(TemporalType.DATE)
    private Date publicacionConvocatoria;
    @Column(name = "invitacion_3_per")
    @Temporal(TemporalType.DATE)
    private Date invitacion3Per;
    @Column(name = "junta_aclaraciones_convocatoria")
    @Temporal(TemporalType.DATE)
    private Date juntaAclaracionesConvocatoria;
    @Column(name = "junta_conclusion_aclaraciones_convocatoria")
    @Temporal(TemporalType.DATE)
    private Date juntaConclusionAclaracionesConvocatoria;
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
    @JoinColumn(name = "id_tipo_procedimiento", referencedColumnName = "id_tipo_procedimiento")
    @ManyToOne(optional = false)
    private TipoProcedimiento idTipoProcedimiento;
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private Estatus idEstatus;
    @JoinColumn(name = "id_clasificacion_procedimiento", referencedColumnName = "id_clasificacion_procedimiento")
    @ManyToOne(optional = false)
    private ClasificacionProcedimiento idClasificacionProcedimiento;
    @JoinColumn(name = "id_caracter_procedimiento", referencedColumnName = "id_caracter_procedimiento")
    @ManyToOne(optional = false)
    private CaracterProcedimiento idCaracterProcedimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProcedimiento")
    private List<ProcedimientoRcb> procedimientoRcbList;
    @JoinColumn(name = "id_tipo_compra", referencedColumnName = "id_tipo_compra")
    @ManyToOne(optional = false)
    private TipoCompra idTipoCompra;

    public Procedimientos() {
    }

    public Procedimientos(Integer idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public Procedimientos(Integer idProcedimiento, String numeroProcedimiento, BigDecimal importeTotal, Date fechaProcedimiento) {
        this.idProcedimiento = idProcedimiento;
        this.numeroProcedimiento = numeroProcedimiento;
        this.importeTotal = importeTotal;
        this.fechaProcedimiento = fechaProcedimiento;
    }

    public Integer getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(Integer idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getVerificaSansiones() {
        return verificaSansiones;
    }

    public void setVerificaSansiones(Integer verificaSansiones) {
        this.verificaSansiones = verificaSansiones;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getNumeroProcedimiento() {
        return numeroProcedimiento;
    }

    public void setNumeroProcedimiento(String numeroProcedimiento) {
        this.numeroProcedimiento = numeroProcedimiento.toUpperCase();
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Date getFechaProcedimiento() {
        return fechaProcedimiento;
    }

    public void setFechaProcedimiento(Date fechaProcedimiento) {
        this.fechaProcedimiento = fechaProcedimiento;
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

    public TipoProcedimiento getIdTipoProcedimiento() {
        return idTipoProcedimiento;
    }

    public void setIdTipoProcedimiento(TipoProcedimiento idTipoProcedimiento) {
        this.idTipoProcedimiento = idTipoProcedimiento;
    }

    public Estatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Estatus idEstatus) {
        this.idEstatus = idEstatus;
    }

    public ClasificacionProcedimiento getIdClasificacionProcedimiento() {
        return idClasificacionProcedimiento;
    }

    public void setIdClasificacionProcedimiento(ClasificacionProcedimiento idClasificacionProcedimiento) {
        this.idClasificacionProcedimiento = idClasificacionProcedimiento;
    }

    public CaracterProcedimiento getIdCaracterProcedimiento() {
        return idCaracterProcedimiento;
    }

    public void setIdCaracterProcedimiento(CaracterProcedimiento idCaracterProcedimiento) {
        this.idCaracterProcedimiento = idCaracterProcedimiento;
    }

    @XmlTransient
    public List<ProcedimientoRcb> getProcedimientoRcbList() {
        return procedimientoRcbList;
    }

    public void setProcedimientoRcbList(List<ProcedimientoRcb> procedimientoRcbList) {
        this.procedimientoRcbList = procedimientoRcbList;
    }

    public TipoCompra getIdTipoCompra() {
        return idTipoCompra;
    }

    public void setIdTipoCompra(TipoCompra idTipoCompra) {
        this.idTipoCompra = idTipoCompra;
    }

    public Date getElaboracionConvocatoria() {
        return elaboracionConvocatoria;
    }

    public void setElaboracionConvocatoria(Date elaboracionConvocatoria) {
        this.elaboracionConvocatoria = elaboracionConvocatoria;
    }

    public Date getPublicacionConvocatoria() {
        return publicacionConvocatoria;
    }

    public void setPublicacionConvocatoria(Date publicacionConvocatoria) {
        this.publicacionConvocatoria = publicacionConvocatoria;
    }

    public Date getInvitacion3Per() {
        return invitacion3Per;
    }

    public void setInvitacion3Per(Date invitacion3Per) {
        this.invitacion3Per = invitacion3Per;
    }

    public Date getJuntaAclaracionesConvocatoria() {
        return juntaAclaracionesConvocatoria;
    }

    public void setJuntaAclaracionesConvocatoria(Date juntaAclaracionesConvocatoria) {
        this.juntaAclaracionesConvocatoria = juntaAclaracionesConvocatoria;
    }

    public Date getJuntaConclusionAclaracionesConvocatoria() {
        return juntaConclusionAclaracionesConvocatoria;
    }

    public void setJuntaConclusionAclaracionesConvocatoria(Date juntaConclusionAclaracionesConvocatoria) {
        this.juntaConclusionAclaracionesConvocatoria = juntaConclusionAclaracionesConvocatoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcedimiento != null ? idProcedimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procedimientos)) {
            return false;
        }
        Procedimientos other = (Procedimientos) object;
        if ((this.idProcedimiento == null && other.idProcedimiento != null) || (this.idProcedimiento != null && !this.idProcedimiento.equals(other.idProcedimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Procedimientos[ idProcedimiento=" + idProcedimiento + " ]";
    }

}
