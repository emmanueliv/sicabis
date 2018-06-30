
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "dpn_insumo")
@NamedQueries({
    @NamedQuery(name = "DpnInsumos.findByPrevio", query = "SELECT di FROM DpnInsumos di WHERE di.previo = :previo")}
)
public class DpnInsumos implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dpn_insumo")
    private Integer idDpnInsumo;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "piezas_dpn")
    private Integer piezasDpn;
    @Column(name = "piezas_propuestas_dpn")
    private Integer piezasPropuestasDpn;
    @Column(name = "piezas_dpn_anterior")
    private Integer piezasDpnAnterior;
    @Column(name = "existencias_siam")
    private Integer existenciasSiam;
    @Column(name = "existencias_cenadi")
    private Integer existenciasCenadi;
    @Column(name = "salidas_siam")
    private Integer salidasSiam;
    @Column(name = "salidas_siam_3")
    private Integer salidasSiam3;
    @Column(name = "coberturas")
    private Integer coberturas;
    @Column(name = "surtimiento")
    private Integer surtimiento;
    @Column(name = "clave_insumo")
    private String claveInsumo;
    @Column(name = "descripcion_insumo")
    private String descripcionInsumo;
    @Column(name = "clave_unidad")
    private String claveUnidad;
    @Column(name = "nombre_unidad")
    private String nombreUnidad;
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Column(name = "previo")
    private Integer previo;
    @Column(name = "resultado")
    private String resultado;
    @Column(name = "dpn_sugerida")
    private Integer dpnSugerida;
    @JoinColumn(name = "id_dpn", referencedColumnName = "id_dpn")
    @ManyToOne(optional = false)
    private Dpn idDpn;
    @JoinColumn(name = "id_insumo_dpn", referencedColumnName = "id_insumo_dpn")
    @ManyToOne(optional = false)
    private InsumoDpn idInsumoDpn;
    @JoinColumn(name = "id_unidades_medicas", referencedColumnName = "id_unidades_medicas")
    @ManyToOne(optional = false)
    private UnidadesMedicas idUnidadesMedicas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDpnInsumo")
    private List<AlertasEnvio> alertasEnvioList;

    public DpnInsumos() {
    }

    public DpnInsumos(Integer idDpnInsumo) {
        this.idDpnInsumo = idDpnInsumo;
    }

    public Integer getIdDpnInsumo() {
        return idDpnInsumo;
    }

    public void setIdDpnInsumo(Integer idDpnInsumo) {
        this.idDpnInsumo = idDpnInsumo;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getPiezasDpn() {
        return piezasDpn;
    }

    public void setPiezasDpn(Integer piezasDpn) {
        this.piezasDpn = piezasDpn;
    }

    public Integer getPiezasPropuestasDpn() {
        return piezasPropuestasDpn;
    }

    public void setPiezasPropuestasDpn(Integer piezasPropuestasDpn) {
        this.piezasPropuestasDpn = piezasPropuestasDpn;
    }

    public Integer getPiezasDpnAnterior() {
        return piezasDpnAnterior;
    }

    public void setPiezasDpnAnterior(Integer piezasDpnAnterior) {
        this.piezasDpnAnterior = piezasDpnAnterior;
    }

    public Integer getExistenciasSiam() {
        return existenciasSiam;
    }

    public void setExistenciasSiam(Integer existenciasSiam) {
        this.existenciasSiam = existenciasSiam;
    }

    public Integer getExistenciasCenadi() {
        return existenciasCenadi;
    }

    public void setExistenciasCenadi(Integer existenciasCenadi) {
        this.existenciasCenadi = existenciasCenadi;
    }

    public Integer getSalidasSiam() {
        return salidasSiam;
    }

    public void setSalidasSiam(Integer salidasSiam) {
        this.salidasSiam = salidasSiam;
    }

    public Integer getSalidasSiam3() {
        return salidasSiam3;
    }

    public void setSalidasSiam3(Integer salidasSiam3) {
        this.salidasSiam3 = salidasSiam3;
    }

    public Integer getCoberturas() {
        return coberturas;
    }

    public void setCoberturas(Integer coberturas) {
        this.coberturas = coberturas;
    }

    public Integer getSurtimiento() {
        return surtimiento;
    }

    public void setSurtimiento(Integer surtimiento) {
        this.surtimiento = surtimiento;
    }

    public String getClaveInsumo() {
        return claveInsumo;
    }

    public void setClaveInsumo(String claveInsumo) {
        this.claveInsumo = claveInsumo;
    }

    public String getDescripcionInsumo() {
        return descripcionInsumo;
    }

    public void setDescripcionInsumo(String descripcionInsumo) {
        this.descripcionInsumo = descripcionInsumo;
    }

    public String getClaveUnidad() {
        return claveUnidad;
    }

    public void setClaveUnidad(String claveUnidad) {
        this.claveUnidad = claveUnidad;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getPrevio() {
        return previo;
    }

    public void setPrevio(Integer previo) {
        this.previo = previo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Integer getDpnSugerida() {
        return dpnSugerida;
    }

    public void setDpnSugerida(Integer dpnSugerida) {
        this.dpnSugerida = dpnSugerida;
    }

    public Dpn getIdDpn() {
        return idDpn;
    }

    public void setIdDpn(Dpn idDpn) {
        this.idDpn = idDpn;
    }

    public InsumoDpn getIdInsumoDpn() {
        return idInsumoDpn;
    }

    public void setIdInsumoDpn(InsumoDpn idInsumoDpn) {
        this.idInsumoDpn = idInsumoDpn;
    }

    public UnidadesMedicas getIdUnidadesMedicas() {
        return idUnidadesMedicas;
    }

    public void setIdUnidadesMedicas(UnidadesMedicas idUnidadesMedicas) {
        this.idUnidadesMedicas = idUnidadesMedicas;
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
        hash += (idDpnInsumo != null ? idDpnInsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DpnInsumos)) {
            return false;
        }
        DpnInsumos other = (DpnInsumos) object;
        if ((this.idDpnInsumo == null && other.idDpnInsumo != null) || (this.idDpnInsumo != null && !this.idDpnInsumo.equals(other.idDpnInsumo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.DpnInsumos[ idDpnInsumo=" + idDpnInsumo + " ]";
    }
    
}
