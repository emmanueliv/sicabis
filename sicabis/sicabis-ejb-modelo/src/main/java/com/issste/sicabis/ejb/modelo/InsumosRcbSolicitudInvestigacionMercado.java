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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kriosoft
 */
@Entity
@Table(name = "insumos_rcb_solicitud_investigacion_mercado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findAll", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByIdInsumosRcbSolicitudInvestigacionMercado", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.idInsumosRcbSolicitudInvestigacionMercado = :idInsumosRcbSolicitudInvestigacionMercado"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByActivo", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.activo = :activo"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByCantidadMinima", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.cantidadMinima = :cantidadMinima"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByCantidadMaxima", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.cantidadMaxima = :cantidadMaxima"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByModalidadContratacion", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.modalidadContratacion = :modalidadContratacion"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByNormasCumplir", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.normasCumplir = :normasCumplir"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByCaducidadBienes", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.caducidadBienes = :caducidadBienes"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByPlazo", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.plazo = :plazo"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByCondicionesEntrega", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.condicionesEntrega = :condicionesEntrega"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByRegistroSanitario", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.registroSanitario = :registroSanitario"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByFormaPago", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.formaPago = :formaPago"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByGarantias", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.garantias = :garantias"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByTiempo", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.tiempo = :tiempo"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByPenasCivil", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.penasCivil = :penasCivil"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByPenasAtraso", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.penasAtraso = :penasAtraso"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByAnticipo", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.anticipo = :anticipo"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByCapacitacion", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.capacitacion = :capacitacion"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByPuestaMarcha", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.puestaMarcha = :puestaMarcha"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByUsuarioAlta", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByUsuarioBaja", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByUsuarioModificacion", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByFechaAlta", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByFechaBaja", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "InsumosRcbSolicitudInvestigacionMercado.findByFechaModificacion", query = "SELECT i FROM InsumosRcbSolicitudInvestigacionMercado i WHERE i.fechaModificacion = :fechaModificacion")})
public class InsumosRcbSolicitudInvestigacionMercado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_insumos_rcb_solicitud_investigacion_mercado")
    private Integer idInsumosRcbSolicitudInvestigacionMercado;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_minima")
    private int cantidadMinima;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_maxima")
    private int cantidadMaxima;
    @Size(max = 45)
    @Column(name = "modalidad_contratacion")
    private String modalidadContratacion;
    @Size(max = 45)
    @Column(name = "normas_cumplir")
    private String normasCumplir;
    @Basic(optional = false)
    @NotNull
    @Column(name = "caducidad_bienes")
    @Temporal(TemporalType.DATE)
    private Date caducidadBienes;
    @Basic(optional = false)
    //@NotNull
    @Size(min = 1, max = 50)
    @Column(name = "plazo")
    private String plazo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "condiciones_entrega")
    private String condicionesEntrega;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "registro_sanitario")
    private String registroSanitario;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "forma_pago")
    private String formaPago;
    @Basic(optional = false)
    @NotNull
//    //@Size(min = 1, max = 45)
    @Column(name = "garantias")
    private String garantias;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "tiempo")
    private int tiempo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "penas_civil")
    private String penasCivil;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "penas_atraso")
    private String penasAtraso;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "anticipo")
    private String anticipo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "capacitacion")
    private String capacitacion;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "puesta_marcha")
    //@Temporal(TemporalType.DATE)
    private String puestaMarcha;
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
    @JoinColumn(name = "id_rcb_insumos", referencedColumnName = "id_rcb_insumos")
    @ManyToOne(optional = false)
    private RcbInsumos idRcbInsumos;

    public InsumosRcbSolicitudInvestigacionMercado() {
    }

    public InsumosRcbSolicitudInvestigacionMercado(Integer idInsumosRcbSolicitudInvestigacionMercado) {
        this.idInsumosRcbSolicitudInvestigacionMercado = idInsumosRcbSolicitudInvestigacionMercado;
    }

    public InsumosRcbSolicitudInvestigacionMercado(Integer idInsumosRcbSolicitudInvestigacionMercado, int cantidadMinima, int cantidadMaxima, Date caducidadBienes, String plazo, String condicionesEntrega, String registroSanitario, String formaPago, String garantias, int tiempo, String penasCivil, String penasAtraso, String anticipo, String capacitacion, String puestaMarcha) {
        this.idInsumosRcbSolicitudInvestigacionMercado = idInsumosRcbSolicitudInvestigacionMercado;
        this.cantidadMinima = cantidadMinima;
        this.cantidadMaxima = cantidadMaxima;
        this.caducidadBienes = caducidadBienes;
        this.plazo = plazo;
        this.condicionesEntrega = condicionesEntrega;
        this.registroSanitario = registroSanitario;
        this.formaPago = formaPago;
        this.garantias = garantias;
        this.tiempo = tiempo;
        this.penasCivil = penasCivil;
        this.penasAtraso = penasAtraso;
        this.anticipo = anticipo;
        this.capacitacion = capacitacion;
        this.puestaMarcha = puestaMarcha;
    }

    public Integer getIdInsumosRcbSolicitudInvestigacionMercado() {
        return idInsumosRcbSolicitudInvestigacionMercado;
    }

    public void setIdInsumosRcbSolicitudInvestigacionMercado(Integer idInsumosRcbSolicitudInvestigacionMercado) {
        this.idInsumosRcbSolicitudInvestigacionMercado = idInsumosRcbSolicitudInvestigacionMercado;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public int getCantidadMaxima() {
        return cantidadMaxima;
    }

    public void setCantidadMaxima(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }

    public String getModalidadContratacion() {
        return modalidadContratacion;
    }

    public void setModalidadContratacion(String modalidadContratacion) {
        this.modalidadContratacion = modalidadContratacion;
    }

    public String getNormasCumplir() {
        return normasCumplir;
    }

    public void setNormasCumplir(String normasCumplir) {
        this.normasCumplir = normasCumplir;
    }

    public Date getCaducidadBienes() {
        return caducidadBienes;
    }

    public void setCaducidadBienes(Date caducidadBienes) {
        this.caducidadBienes = caducidadBienes;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getCondicionesEntrega() {
        return condicionesEntrega;
    }

    public void setCondicionesEntrega(String condicionesEntrega) {
        this.condicionesEntrega = condicionesEntrega;
    }

    public String getRegistroSanitario() {
        return registroSanitario;
    }

    public void setRegistroSanitario(String registroSanitario) {
        this.registroSanitario = registroSanitario;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getGarantias() {
        return garantias;
    }

    public void setGarantias(String garantias) {
        this.garantias = garantias;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getPenasCivil() {
        return penasCivil;
    }

    public void setPenasCivil(String penasCivil) {
        this.penasCivil = penasCivil;
    }

    public String getPenasAtraso() {
        return penasAtraso;
    }

    public void setPenasAtraso(String penasAtraso) {
        this.penasAtraso = penasAtraso;
    }

    public String getAnticipo() {
        return anticipo;
    }

    public void setAnticipo(String anticipo) {
        this.anticipo = anticipo;
    }

    public String getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(String capacitacion) {
        this.capacitacion = capacitacion;
    }

    public String getPuestaMarcha() {
        return puestaMarcha;
    }

    public void setPuestaMarcha(String puestaMarcha) {
        this.puestaMarcha = puestaMarcha;
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

    public RcbInsumos getIdRcbInsumos() {
        return idRcbInsumos;
    }

    public void setIdRcbInsumos(RcbInsumos idRcbInsumos) {
        this.idRcbInsumos = idRcbInsumos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInsumosRcbSolicitudInvestigacionMercado != null ? idInsumosRcbSolicitudInvestigacionMercado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsumosRcbSolicitudInvestigacionMercado)) {
            return false;
        }
        InsumosRcbSolicitudInvestigacionMercado other = (InsumosRcbSolicitudInvestigacionMercado) object;
        if ((this.idInsumosRcbSolicitudInvestigacionMercado == null && other.idInsumosRcbSolicitudInvestigacionMercado != null) || (this.idInsumosRcbSolicitudInvestigacionMercado != null && !this.idInsumosRcbSolicitudInvestigacionMercado.equals(other.idInsumosRcbSolicitudInvestigacionMercado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.InsumosRcbSolicitudInvestigacionMercado[ idInsumosRcbSolicitudInvestigacionMercado=" + idInsumosRcbSolicitudInvestigacionMercado + " ]";
    }
    
}
