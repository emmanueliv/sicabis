/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 9RZCBG2
 */
@Entity
@Table(name = "existencia_reserva_clave_cenadi_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExistenciaReservaClaveCenadiHistorico.findAll", query = "SELECT e FROM ExistenciaReservaClaveCenadiHistorico e"),
    @NamedQuery(name = "ExistenciaReservaClaveCenadiHistorico.findByIdExistenciaReservaClaveCenadiHistorico", query = "SELECT e FROM ExistenciaReservaClaveCenadiHistorico e WHERE e.idExistenciaReservaClaveCenadiHistorico = :idExistenciaReservaClaveCenadiHistorico"),
    @NamedQuery(name = "ExistenciaReservaClaveCenadiHistorico.findByClave", query = "SELECT e FROM ExistenciaReservaClaveCenadiHistorico e WHERE e.clave = :clave"),
    @NamedQuery(name = "ExistenciaReservaClaveCenadiHistorico.findByNombre", query = "SELECT e FROM ExistenciaReservaClaveCenadiHistorico e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "ExistenciaReservaClaveCenadiHistorico.findByTipo", query = "SELECT e FROM ExistenciaReservaClaveCenadiHistorico e WHERE e.tipo = :tipo"),
    @NamedQuery(name = "ExistenciaReservaClaveCenadiHistorico.findByExistencia", query = "SELECT e FROM ExistenciaReservaClaveCenadiHistorico e WHERE e.existencia = :existencia"),
    @NamedQuery(name = "ExistenciaReservaClaveCenadiHistorico.findByDisponibleDeReserva", query = "SELECT e FROM ExistenciaReservaClaveCenadiHistorico e WHERE e.disponibleDeReserva = :disponibleDeReserva"),
    @NamedQuery(name = "ExistenciaReservaClaveCenadiHistorico.findByFechaInventario", query = "SELECT e FROM ExistenciaReservaClaveCenadiHistorico e WHERE e.fechaInventario = :fechaInventario"),
    @NamedQuery(name = "ExistenciaReservaClaveCenadiHistorico.findByPartidaPresupuestal", query = "SELECT e FROM ExistenciaReservaClaveCenadiHistorico e WHERE e.partidaPresupuestal = :partidaPresupuestal"),
    @NamedQuery(name = "ExistenciaReservaClaveCenadiHistorico.findByFechaDt", query = "SELECT e FROM ExistenciaReservaClaveCenadiHistorico e WHERE e.fechaDt = :fechaDt"),
    @NamedQuery(name = "ExistenciaReservaClaveCenadiHistorico.findByDpn", query = "SELECT e FROM ExistenciaReservaClaveCenadiHistorico e WHERE e.dpn = :dpn"),
    @NamedQuery(name = "ExistenciaReservaClaveCenadiHistorico.findByActivo", query = "SELECT e FROM ExistenciaReservaClaveCenadiHistorico e WHERE e.activo = :activo"),
    @NamedQuery(name = "ExistenciaReservaClaveCenadiHistorico.findByFechaIngresoAux", query = "SELECT e FROM ExistenciaReservaClaveCenadiHistorico e WHERE e.fechaIngresoAux = :fechaIngresoAux")})
public class ExistenciaReservaClaveCenadiHistorico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_existencia_reserva_clave_cenadi_historico")
    private Integer idExistenciaReservaClaveCenadiHistorico;
    @Size(max = 250)
    @Column(name = "clave")
    private String clave;
    @Size(max = 5000)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 250)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "existencia")
    private Integer existencia;
    @Column(name = "disponible_de_reserva")
    private Integer disponibleDeReserva;
    @Column(name = "fecha_inventario")
    @Temporal(TemporalType.DATE)
    private Date fechaInventario;
    @Size(max = 250)
    @Column(name = "partida_presupuestal")
    private String partidaPresupuestal;
    @Column(name = "fecha_dt")
    @Temporal(TemporalType.DATE)
    private Date fechaDt;
    @Column(name = "dpn")
    private Integer dpn;
    @Column(name = "cobertura_dias")
    private Integer coberturaDias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private int activo;
    @Column(name = "fecha_ingreso_aux")
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoAux;

    public ExistenciaReservaClaveCenadiHistorico() {
    }

    public ExistenciaReservaClaveCenadiHistorico(Integer idExistenciaReservaClaveCenadiHistorico) {
        this.idExistenciaReservaClaveCenadiHistorico = idExistenciaReservaClaveCenadiHistorico;
    }

    public ExistenciaReservaClaveCenadiHistorico(Integer idExistenciaReservaClaveCenadiHistorico, int activo) {
        this.idExistenciaReservaClaveCenadiHistorico = idExistenciaReservaClaveCenadiHistorico;
        this.activo = activo;
    }

    public Integer getIdExistenciaReservaClaveCenadiHistorico() {
        return idExistenciaReservaClaveCenadiHistorico;
    }

    public void setIdExistenciaReservaClaveCenadiHistorico(Integer idExistenciaReservaClaveCenadiHistorico) {
        this.idExistenciaReservaClaveCenadiHistorico = idExistenciaReservaClaveCenadiHistorico;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public Integer getDisponibleDeReserva() {
        return disponibleDeReserva;
    }

    public void setDisponibleDeReserva(Integer disponibleDeReserva) {
        this.disponibleDeReserva = disponibleDeReserva;
    }

    public Date getFechaInventario() {
        return fechaInventario;
    }

    public void setFechaInventario(Date fechaInventario) {
        this.fechaInventario = fechaInventario;
    }

    public String getPartidaPresupuestal() {
        return partidaPresupuestal;
    }

    public void setPartidaPresupuestal(String partidaPresupuestal) {
        this.partidaPresupuestal = partidaPresupuestal;
    }

    public Date getFechaDt() {
        return fechaDt;
    }

    public void setFechaDt(Date fechaDt) {
        this.fechaDt = fechaDt;
    }

    public Integer getDpn() {
        return dpn;
    }

    public void setDpn(Integer dpn) {
        this.dpn = dpn;
    }

    public Integer getCoberturaDias() {
        return coberturaDias;
    }

    public void setCoberturaDias(Integer coberturaDias) {
        this.coberturaDias = coberturaDias;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public Date getFechaIngresoAux() {
        return fechaIngresoAux;
    }

    public void setFechaIngresoAux(Date fechaIngresoAux) {
        this.fechaIngresoAux = fechaIngresoAux;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExistenciaReservaClaveCenadiHistorico != null ? idExistenciaReservaClaveCenadiHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExistenciaReservaClaveCenadiHistorico)) {
            return false;
        }
        ExistenciaReservaClaveCenadiHistorico other = (ExistenciaReservaClaveCenadiHistorico) object;
        if ((this.idExistenciaReservaClaveCenadiHistorico == null && other.idExistenciaReservaClaveCenadiHistorico != null) || (this.idExistenciaReservaClaveCenadiHistorico != null && !this.idExistenciaReservaClaveCenadiHistorico.equals(other.idExistenciaReservaClaveCenadiHistorico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.ExistenciaReservaClaveCenadiHistorico[ idExistenciaReservaClaveCenadiHistorico=" + idExistenciaReservaClaveCenadiHistorico + " ]";
    }

}
