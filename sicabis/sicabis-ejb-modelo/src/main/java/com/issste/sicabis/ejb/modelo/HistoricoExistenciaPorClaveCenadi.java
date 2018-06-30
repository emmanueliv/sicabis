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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "historico_existencia_por_clave_cenadi")
public class HistoricoExistenciaPorClaveCenadi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historico_existencia_por_clave_cenadi")
    private Integer idHistoricoExistenciaPorClaveCenadi;
    @Size(max = 100)
    @Column(name = "clave")
    private String clave;
    @Size(max = 240)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lote")
    private String lote;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "existencia")
    private String existencia;
    @Column(name = "fecha_inventario")
    @Temporal(TemporalType.DATE)
    private Date fechaInventario;
    @Column(name = "fecha_caducidad")
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidad;
    @Size(max = 45)
    @Column(name = "subinventario")
    private String subinventario;
    @Size(max = 45)
    @Column(name = "localizador")
    private String localizador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Column(name = "importe")
    private BigDecimal importe;
    @Column(name = "cobertura_dias")
    private Integer coberturaDias;
    @Size(max = 50)
    @Column(name = "partida_presupuestal")
    private String partidaPresupuestal;
    @Size(max = 200)
    @Column(name = "proveedor")
    private String proveedor;
    @Column(name = "fecha_dt")
    @Temporal(TemporalType.DATE)
    private Date fechaDt;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    public HistoricoExistenciaPorClaveCenadi() {
    }

    public HistoricoExistenciaPorClaveCenadi(Integer idHistoricoExistenciaPorClaveCenadi) {
        this.idHistoricoExistenciaPorClaveCenadi = idHistoricoExistenciaPorClaveCenadi;
    }

    public Integer getIdHistoricoExistenciaPorClaveCenadi() {
        return idHistoricoExistenciaPorClaveCenadi;
    }

    public void setIdHistoricoExistenciaPorClaveCenadi(Integer idHistoricoExistenciaPorClaveCenadi) {
        this.idHistoricoExistenciaPorClaveCenadi = idHistoricoExistenciaPorClaveCenadi;
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

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getExistencia() {
        return existencia;
    }

    public void setExistencia(String existencia) {
        this.existencia = existencia;
    }

    public Date getFechaInventario() {
        return fechaInventario;
    }

    public void setFechaInventario(Date fechaInventario) {
        this.fechaInventario = fechaInventario;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getSubinventario() {
        return subinventario;
    }

    public void setSubinventario(String subinventario) {
        this.subinventario = subinventario;
    }

    public String getLocalizador() {
        return localizador;
    }

    public void setLocalizador(String localizador) {
        this.localizador = localizador;
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

    public Integer getCoberturaDias() {
        return coberturaDias;
    }

    public void setCoberturaDias(Integer coberturaDias) {
        this.coberturaDias = coberturaDias;
    }

    public String getPartidaPresupuestal() {
        return partidaPresupuestal;
    }

    public void setPartidaPresupuestal(String partidaPresupuestal) {
        this.partidaPresupuestal = partidaPresupuestal;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFechaDt() {
        return fechaDt;
    }

    public void setFechaDt(Date fechaDt) {
        this.fechaDt = fechaDt;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoricoExistenciaPorClaveCenadi != null ? idHistoricoExistenciaPorClaveCenadi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoricoExistenciaPorClaveCenadi)) {
            return false;
        }
        HistoricoExistenciaPorClaveCenadi other = (HistoricoExistenciaPorClaveCenadi) object;
        if ((this.idHistoricoExistenciaPorClaveCenadi == null && other.idHistoricoExistenciaPorClaveCenadi != null) || (this.idHistoricoExistenciaPorClaveCenadi != null && !this.idHistoricoExistenciaPorClaveCenadi.equals(other.idHistoricoExistenciaPorClaveCenadi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.HistoricoExistenciaPorClaveCenadi[ idHistoricoExistenciaPorClaveCenadi=" + idHistoricoExistenciaPorClaveCenadi + " ]";
    }
}
