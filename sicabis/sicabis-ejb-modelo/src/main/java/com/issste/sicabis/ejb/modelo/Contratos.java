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
@Table(name = "contratos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contratos.findAll", query = "SELECT c FROM Contratos c"),
    @NamedQuery(name = "Contratos.findByIdContrato", query = "SELECT c FROM Contratos c WHERE c.idContrato = :idContrato"),
    @NamedQuery(name = "Contratos.findByActivo", query = "SELECT c FROM Contratos c WHERE c.activo = :activo"),
    @NamedQuery(name = "Contratos.findByIdPadre", query = "SELECT c FROM Contratos c WHERE c.idPadre = :idPadre"),
    @NamedQuery(name = "Contratos.findByNumeroContrato", query = "SELECT c FROM Contratos c WHERE c.numeroContrato = :numeroContrato"),
    @NamedQuery(name = "Contratos.findByVigenciaInicial", query = "SELECT c FROM Contratos c WHERE c.vigenciaInicial = :vigenciaInicial"),
    @NamedQuery(name = "Contratos.findByVigenciaFinal", query = "SELECT c FROM Contratos c WHERE c.vigenciaFinal = :vigenciaFinal"),
    @NamedQuery(name = "Contratos.findByOficioSuficiencia", query = "SELECT c FROM Contratos c WHERE c.oficioSuficiencia = :oficioSuficiencia"),
    @NamedQuery(name = "Contratos.findByNep", query = "SELECT c FROM Contratos c WHERE c.nep = :nep"),
    @NamedQuery(name = "Contratos.findByFechaOficioSuficiencia", query = "SELECT c FROM Contratos c WHERE c.fechaOficioSuficiencia = :fechaOficioSuficiencia"),
    @NamedQuery(name = "Contratos.findByUsuarioAlta", query = "SELECT c FROM Contratos c WHERE c.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Contratos.findByUsuarioBaja", query = "SELECT c FROM Contratos c WHERE c.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Contratos.findByUsuarioModificacion", query = "SELECT c FROM Contratos c WHERE c.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Contratos.findByFechaAlta", query = "SELECT c FROM Contratos c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Contratos.findByFechaBaja", query = "SELECT c FROM Contratos c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Contratos.findByFechaModificacion", query = "SELECT c FROM Contratos c WHERE c.fechaModificacion = :fechaModificacion")})
public class Contratos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contrato")
    private Integer idContrato;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "id_padre")
    private Integer idPadre;
    @Size(max = 100)
    @Column(name = "numero_contrato")
    private String numeroContrato;
    @Size(max = 100)
    @Column(name = "numero_convenio")
    private String numeroConvenio;
    @Column(name = "importe")
    private BigDecimal importe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vigencia_inicial")
    @Temporal(TemporalType.DATE)
    private Date vigenciaInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vigencia_final")
    @Temporal(TemporalType.DATE)
    private Date vigenciaFinal;
    @Size(max = 100)
    @Column(name = "oficio_suficiencia")
    private String oficioSuficiencia;
    @Size(max = 45)
    @Column(name = "nep")
    private String nep;
    @Column(name = "fecha_oficio_suficiencia")
    @Temporal(TemporalType.DATE)
    private Date fechaOficioSuficiencia;
    @Size(max = 45)
    @Column(name = "acuerdo")
    private String acuerdo;
    @Size(max = 45)
    @Column(name = "condicion_pago")
    private String condicionPago;
    @Column(name = "fecha_contrato")
    @Temporal(TemporalType.DATE)
    private Date fechaContrato;
    @Column(name = "fecha_formalizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaFormalizacion;
    @Column(name = "anio_afectacion")
    private int anioAfectacion;
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
    @Basic(optional = false)
    @Column(name = "clausula")
    private String clausula;
    @Basic(optional = false)
    @Column(name = "notas")
    private String notas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContrato")
    private List<OrdenSuministro> ordenSuministroList;
    @JoinColumn(name = "id_tipo_convenio", referencedColumnName = "id_tipo_convenio")
    @ManyToOne(optional = false)
    private TipoConvenio idTipoConvenio;
    @JoinColumn(name = "id_tipo_contrato", referencedColumnName = "id_tipo_contrato")
    @ManyToOne(optional = false)
    private TipoContrato idTipoContrato;
    @JoinColumn(name = "id_fundamento_legal", referencedColumnName = "id_fundamento_legal")
    @ManyToOne(optional = false)
    private FundamentoLegal idFundamentoLegal;
    @JoinColumn(name = "id_estatus", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private Estatus idEstatus;
    @JoinColumn(name = "id_almacen", referencedColumnName = "id_almacen")
    @ManyToOne(optional = false)
    private Almacen idAlmacen;
    @JoinColumn(name = "id_partida_presupuestal", referencedColumnName = "id_partida_presupuestal")
    @ManyToOne(optional = false)
    private PartidaPresupuestal idPartidaPresupuestal;
    @JoinColumn(name = "id_unidades_medicas", referencedColumnName = "id_unidades_medicas")
    @ManyToOne(optional = false)
    private UnidadesMedicas idUnidadesMedicas;
    @JoinColumn(name = "id_destino", referencedColumnName = "id_destino")
    @ManyToOne(optional = false)
    private Destinos idDestino;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContrato")
    private List<ContratoFalloProcedimientoRcb> idContratoFalloProcedimientoRcbList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContrato")
    private List<CompradorContrato> compradorContratoList;
    @JoinColumn(name = "id_tipo_insumos", referencedColumnName = "id_tipo_insumos")
    @ManyToOne
    private TipoInsumos idTipoInsumos;

    public Contratos() {
    }

    public Contratos(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Contratos(Integer idContrato, Date vigenciaInicial, Date vigenciaFinal) {
        this.idContrato = idContrato;
        this.vigenciaInicial = vigenciaInicial;
        this.vigenciaFinal = vigenciaFinal;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato.toUpperCase();
    }

    public String getNumeroConvenio() {
        return numeroConvenio;
    }

    public void setNumeroConvenio(String numeroConvenio) {
        this.numeroConvenio = numeroConvenio.toUpperCase();
    }

    public Date getVigenciaInicial() {
        return vigenciaInicial;
    }

    public void setVigenciaInicial(Date vigenciaInicial) {
        this.vigenciaInicial = vigenciaInicial;
    }

    public Date getVigenciaFinal() {
        return vigenciaFinal;
    }

    public void setVigenciaFinal(Date vigenciaFinal) {
        this.vigenciaFinal = vigenciaFinal;
    }

    public String getOficioSuficiencia() {
        return oficioSuficiencia;
    }

    public void setOficioSuficiencia(String oficioSuficiencia) {
        this.oficioSuficiencia = oficioSuficiencia;
    }

    public String getNep() {
        return nep;
    }

    public void setNep(String nep) {
        this.nep = nep;
    }

    public Date getFechaOficioSuficiencia() {
        return fechaOficioSuficiencia;
    }

    public void setFechaOficioSuficiencia(Date fechaOficioSuficiencia) {
        this.fechaOficioSuficiencia = fechaOficioSuficiencia;
    }

    public String getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(String acuerdo) {
        this.acuerdo = acuerdo;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getCondicionPago() {
        return condicionPago;
    }

    public void setCondicionPago(String condicionPago) {
        this.condicionPago = condicionPago;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public Date getFechaFormalizacion() {
        return fechaFormalizacion;
    }

    public void setFechaFormalizacion(Date fechaFormalizacion) {
        this.fechaFormalizacion = fechaFormalizacion;
    }

    public int getAnioAfectacion() {
        return anioAfectacion;
    }

    public void setAnioAfectacion(int anioAfectacion) {
        this.anioAfectacion = anioAfectacion;
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

    public String getClausula() {
        return clausula;
    }

    public void setClausula(String clausula) {
        this.clausula = clausula;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @XmlTransient
    public List<OrdenSuministro> getOrdenSuministroList() {
        return ordenSuministroList;
    }

    public void setOrdenSuministroList(List<OrdenSuministro> ordenSuministroList) {
        this.ordenSuministroList = ordenSuministroList;
    }

    public TipoConvenio getIdTipoConvenio() {
        return idTipoConvenio;
    }

    public void setIdTipoConvenio(TipoConvenio idTipoConvenio) {
        this.idTipoConvenio = idTipoConvenio;
    }

    public TipoContrato getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(TipoContrato idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    public FundamentoLegal getIdFundamentoLegal() {
        return idFundamentoLegal;
    }

    public void setIdFundamentoLegal(FundamentoLegal idFundamentoLegal) {
        this.idFundamentoLegal = idFundamentoLegal;
    }

    public Estatus getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Estatus idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Almacen getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Almacen idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public PartidaPresupuestal getIdPartidaPresupuestal() {
        return idPartidaPresupuestal;
    }

    public void setIdPartidaPresupuestal(PartidaPresupuestal idPartidaPresupuestal) {
        this.idPartidaPresupuestal = idPartidaPresupuestal;
    }

    public UnidadesMedicas getIdUnidadesMedicas() {
        return idUnidadesMedicas;
    }

    public void setIdUnidadesMedicas(UnidadesMedicas idUnidadesMedicas) {
        this.idUnidadesMedicas = idUnidadesMedicas;
    }

    public Destinos getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Destinos idDestino) {
        this.idDestino = idDestino;
    }

    @XmlTransient
    public List<ContratoFalloProcedimientoRcb> getIdContratoFalloProcedimientoRcbList() {
        return idContratoFalloProcedimientoRcbList;
    }

    public void setIdContratoFalloProcedimientoRcbList(List<ContratoFalloProcedimientoRcb> idContratoFalloProcedimientoRcbList) {
        this.idContratoFalloProcedimientoRcbList = idContratoFalloProcedimientoRcbList;
    }

    @XmlTransient
    public List<CompradorContrato> getCompradorContratoList() {
        return compradorContratoList;
    }

    public void setCompradorContratoList(List<CompradorContrato> compradorContratoList) {
        this.compradorContratoList = compradorContratoList;
    }

    public TipoInsumos getIdTipoInsumos() {
        return idTipoInsumos;
    }

    public void setIdTipoInsumos(TipoInsumos idTipoInsumos) {
        this.idTipoInsumos = idTipoInsumos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContrato != null ? idContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contratos)) {
            return false;
        }
        Contratos other = (Contratos) object;
        if ((this.idContrato == null && other.idContrato != null) || (this.idContrato != null && !this.idContrato.equals(other.idContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Contratos[ idContrato=" + idContrato + " ]";
    }

}
