package com.issste.sicabis.ejb.service.silodisa.modelo;

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
 * @author 5XD9BG2
 */
@Entity
@Table(name = "existencia_por_clave_cenadi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findAll", query = "SELECT e FROM ExistenciaPorClaveCenadi e"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findByIdExistenciaPorClaveCenadi", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.idExistenciaPorClaveCenadi = :idExistenciaPorClaveCenadi"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findByClave", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.clave = :clave"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findByNombre", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findByTipo", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.tipo = :tipo"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findByLote", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.lote = :lote"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findByExistencia", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.existencia = :existencia"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findByFechaInventario", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.fechaInventario = :fechaInventario"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findByFechaCaducidad", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findBySubinventario", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.subinventario = :subinventario"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findByLocalizador", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.localizador = :localizador"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findByPrecioUnitario", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findByImporte", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.importe = :importe"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findByPartidaPresupuestal", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.partidaPresupuestal = :partidaPresupuestal"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findByProveedor", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.proveedor = :proveedor"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findByFechaDt", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.fechaDt = :fechaDt"),
    @NamedQuery(name = "ExistenciaPorClaveCenadi.findByFechaIngreso", query = "SELECT e FROM ExistenciaPorClaveCenadi e WHERE e.fechaIngreso = :fechaIngreso")})
public class ExistenciaPorClaveCenadi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_existencia_por_clave_cenadi")
    private Integer idExistenciaPorClaveCenadi;
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

    public ExistenciaPorClaveCenadi() {
    }

    public ExistenciaPorClaveCenadi(Integer idExistenciaPorClaveCenadi) {
        this.idExistenciaPorClaveCenadi = idExistenciaPorClaveCenadi;
    }

    public ExistenciaPorClaveCenadi(Integer idExistenciaPorClaveCenadi, String lote, String existencia) {
        this.idExistenciaPorClaveCenadi = idExistenciaPorClaveCenadi;
        this.lote = lote;
        this.existencia = existencia;
    }

    public Integer getIdExistenciaPorClaveCenadi() {
        return idExistenciaPorClaveCenadi;
    }

    public void setIdExistenciaPorClaveCenadi(Integer idExistenciaPorClaveCenadi) {
        this.idExistenciaPorClaveCenadi = idExistenciaPorClaveCenadi;
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
        hash += (idExistenciaPorClaveCenadi != null ? idExistenciaPorClaveCenadi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExistenciaPorClaveCenadi)) {
            return false;
        }
        ExistenciaPorClaveCenadi other = (ExistenciaPorClaveCenadi) object;
        if ((this.idExistenciaPorClaveCenadi == null && other.idExistenciaPorClaveCenadi != null) || (this.idExistenciaPorClaveCenadi != null && !this.idExistenciaPorClaveCenadi.equals(other.idExistenciaPorClaveCenadi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.ExistenciaPorClaveCenadi[ idExistenciaPorClaveCenadi=" + idExistenciaPorClaveCenadi + " ]";
    }

}
