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
@Table(name = "proveedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedores.findAll", query = "SELECT p FROM Proveedores p "),
    @NamedQuery(name = "Proveedores.findAllByActivo", query = "SELECT p FROM Proveedores p WHERE p.activo = 1"),
    @NamedQuery(name = "Proveedores.findByIdProveedor", query = "SELECT p FROM Proveedores p WHERE p.idProveedor = :idProveedor"),
    @NamedQuery(name = "Proveedores.findByActivo", query = "SELECT p FROM Proveedores p WHERE p.activo = :activo"),
    @NamedQuery(name = "Proveedores.findByNombreProveedor", query = "SELECT p FROM Proveedores p WHERE p.nombreProveedor = :nombreProveedor AND p.activo = 1"),
    @NamedQuery(name = "Proveedores.findByNumero", query = "SELECT p FROM Proveedores p WHERE p.numero = :numero"),
    @NamedQuery(name = "Proveedores.findByNumeroProveedor", query = "SELECT p FROM Proveedores p WHERE p.numeroProveedor = :numeroProveedor AND p.activo = 1"),
    @NamedQuery(name = "Proveedores.findByDireccion", query = "SELECT p FROM Proveedores p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Proveedores.findByRfc", query = "SELECT p FROM Proveedores p WHERE p.rfc = :rfc"),
    @NamedQuery(name = "Proveedores.findByUsuarioAlta", query = "SELECT p FROM Proveedores p WHERE p.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Proveedores.findByUsuarioBaja", query = "SELECT p FROM Proveedores p WHERE p.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Proveedores.findByUsuarioModificacion", query = "SELECT p FROM Proveedores p WHERE p.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Proveedores.findByFechaAlta", query = "SELECT p FROM Proveedores p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Proveedores.findByFechaBaja", query = "SELECT p FROM Proveedores p WHERE p.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Proveedores.findByFechaModificacion", query = "SELECT p FROM Proveedores p WHERE p.fechaModificacion = :fechaModificacion")})
public class Proveedores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "autorizado")
    private Integer autorizado;
    @Column(name = "numero_proveedor")
    private Integer numeroProveedor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;
    @Column(name = "razon_social")
    private String razonSocial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private int numero;
    @Size(max = 200)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 50)
    @Column(name = "rfc")
    private String rfc;
    @Size(max = 30)
    @Column(name = "correo")
    private String correo;
    @Column(name = "no_rupa")
    private Integer noRupa;
    @Column(name = "id_padre")
    private Integer idPadre;
    @Column(name = "calle")
    private String calle;
    @Column(name = "colonia")
    private String colonia;
    @Column(name = "cp")
    private String cp;
    @Column(name = "delegacion")
    private String delegacion;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "localidad")
    private String localidad;
    @Column(name = "telefono2")
    private String telefono2;
    @Column(name = "telefono3")
    private String telefono3;
    @Column(name = "fax1")
    private String fax1;
    @Column(name = "fax2")
    private String fax2;
    @Column(name = "notificaciones")
    private String notificaciones;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "num_acta_escritura")
    private String numActaEscritura;
    @Column(name = "fecha_acta")
    @Temporal(TemporalType.DATE)
    private Date fechaActa;
    @Column(name = "num_notaria")
    private String numNotaria;
    @Column(name = "nombre_notario")
    private String nombreNotario;
    @Column(name = "sintesis_acta")
    private String sintesisActa;
    @Column(name = "vigencia_sociedad")
    private String vigenciaSociedad;
    @Column(name = "objeto_empresa")
    private String objetoEmpresa;
    @Column(name = "reforma_num_acta")
    private String reformaNumActa;
    @Column(name = "reforma_fecha_acta")
    @Temporal(TemporalType.DATE)
    private Date reformaFechaActa;
    @Column(name = "reforma_num_notaria")
    private String reformaNumNotaria;
    @Column(name = "reforma_nombre_notario")
    private String reformaNombreNotario;
    @Column(name = "reforma_sintesis_acta")
    private String reformaSintesisActa;
    @Column(name = "mipymes")
    private Integer mipymes;
    @Column(name = "micro")
    private Integer micro;
    @Column(name = "pequena")
    private Integer pequena;
    @Column(name = "mediana")
    private Integer mediana;
    @Column(name = "tope_maximo")
    private BigDecimal topeMaximo;
    @Column(name = "estatus_sii_plus")
    private String estatus_sii_plus;
    @Column(name = "representante")
    private String representante;
    @Column(name = "rep_correo_electronico")
    private String repCorreoElectronico;
    @Column(name = "rep_num_acta_escritura")
    private String repNumActaEscritura;
    @Column(name = "rep_fecha_acta")
    @Temporal(TemporalType.DATE)
    private Date repFechaActa;
    @Column(name = "rep_num_notaria")
    private String repNumNotaria;
    @Column(name = "rep_nombre_notario")
    private String repNombreNotario;
    @Column(name = "rep_sintesis_acta")
    private String repSintesisActa;
    @Column(name = "giro")
    private String giro;
    @Column(name = "giro_descripcion")
    private String giroDescripcion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedor")
    private List<ContactosProveedores> contactosProveedoresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedor")
    private List<FalloProcedimientoRcb> falloProcedimientoRcbList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedor")
    private List<ProveedorFabricante> proveedorFabricanteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedor")
    private List<Propuestas> propuestasList;
    @JoinColumn(name = "id_tipo_proveedor", referencedColumnName = "id_tipo_proveedor")
    @ManyToOne(optional = false)
    private TipoProveedor idTipoProveedor;
    @JoinColumn(name = "id_giro", referencedColumnName = "id_giro")
    @ManyToOne(optional = false)
    private Giro idGiro;
    @JoinColumn(name = "id_representante_legal", referencedColumnName = "id_representante_legal")
    @ManyToOne(optional = false)
    private RepresentanteLegal idRepresentanteLegal;

    public Proveedores() {
    }

    public Proveedores(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedores(Integer idProveedor, Integer numeroProveedor) {
        this.idProveedor = idProveedor;
        this.numeroProveedor = numeroProveedor;
    }
    
    public Proveedores(Integer idProveedor, String nombreProveedor, int numero) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.numero = numero;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Integer getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(Integer autorizado) {
        this.autorizado = autorizado;
    }

    public Integer getNumeroProveedor() {
        return numeroProveedor;
    }

    public void setNumeroProveedor(Integer numeroProveedor) {
        this.numeroProveedor = numeroProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getNoRupa() {
        return noRupa;
    }

    public void setNoRupa(Integer noRupa) {
        this.noRupa = noRupa;
    }

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getTelefono3() {
        return telefono3;
    }

    public void setTelefono3(String telefono3) {
        this.telefono3 = telefono3;
    }

    public String getFax1() {
        return fax1;
    }

    public void setFax1(String fax1) {
        this.fax1 = fax1;
    }

    public String getFax2() {
        return fax2;
    }

    public void setFax2(String fax2) {
        this.fax2 = fax2;
    }

    public String getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(String notificaciones) {
        this.notificaciones = notificaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNumActaEscritura() {
        return numActaEscritura;
    }

    public void setNumActaEscritura(String numActaEscritura) {
        this.numActaEscritura = numActaEscritura;
    }

    public Date getFechaActa() {
        return fechaActa;
    }

    public void setFechaActa(Date fechaActa) {
        this.fechaActa = fechaActa;
    }

    public String getNumNotaria() {
        return numNotaria;
    }

    public void setNumNotaria(String numNotaria) {
        this.numNotaria = numNotaria;
    }

    public String getNombreNotario() {
        return nombreNotario;
    }

    public void setNombreNotario(String nombreNotario) {
        this.nombreNotario = nombreNotario;
    }

    public String getSintesisActa() {
        return sintesisActa;
    }

    public void setSintesisActa(String sintesisActa) {
        this.sintesisActa = sintesisActa;
    }

    public String getVigenciaSociedad() {
        return vigenciaSociedad;
    }

    public void setVigenciaSociedad(String vigenciaSociedad) {
        this.vigenciaSociedad = vigenciaSociedad;
    }

    public String getObjetoEmpresa() {
        return objetoEmpresa;
    }

    public void setObjetoEmpresa(String objetoEmpresa) {
        this.objetoEmpresa = objetoEmpresa;
    }

    public String getReformaNumActa() {
        return reformaNumActa;
    }

    public void setReformaNumActa(String reformaNumActa) {
        this.reformaNumActa = reformaNumActa;
    }

    public Date getReformaFechaActa() {
        return reformaFechaActa;
    }

    public void setReformaFechaActa(Date reformaFechaActa) {
        this.reformaFechaActa = reformaFechaActa;
    }

    public String getReformaNumNotaria() {
        return reformaNumNotaria;
    }

    public void setReformaNumNotaria(String reformaNumNotaria) {
        this.reformaNumNotaria = reformaNumNotaria;
    }

    public String getReformaNombreNotario() {
        return reformaNombreNotario;
    }

    public void setReformaNombreNotario(String reformaNombreNotario) {
        this.reformaNombreNotario = reformaNombreNotario;
    }

    public String getReformaSintesisActa() {
        return reformaSintesisActa;
    }

    public void setReformaSintesisActa(String reformaSintesisActa) {
        this.reformaSintesisActa = reformaSintesisActa;
    }

    public Integer getMipymes() {
        return mipymes;
    }

    public void setMipymes(Integer mipymes) {
        this.mipymes = mipymes;
    }

    public Integer getMicro() {
        return micro;
    }

    public void setMicro(Integer micro) {
        this.micro = micro;
    }

    public Integer getPequena() {
        return pequena;
    }

    public void setPequena(Integer pequena) {
        this.pequena = pequena;
    }

    public Integer getMediana() {
        return mediana;
    }

    public void setMediana(Integer mediana) {
        this.mediana = mediana;
    }

    public BigDecimal getTopeMaximo() {
        return topeMaximo;
    }

    public void setTopeMaximo(BigDecimal topeMaximo) {
        this.topeMaximo = topeMaximo;
    }

    public String getEstatus_sii_plus() {
        return estatus_sii_plus;
    }

    public void setEstatus_sii_plus(String estatus_sii_plus) {
        this.estatus_sii_plus = estatus_sii_plus;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getRepCorreoElectronico() {
        return repCorreoElectronico;
    }

    public void setRepCorreoElectronico(String repCorreoElectronico) {
        this.repCorreoElectronico = repCorreoElectronico;
    }

    public String getRepNumActaEscritura() {
        return repNumActaEscritura;
    }

    public void setRepNumActaEscritura(String repNumActaEscritura) {
        this.repNumActaEscritura = repNumActaEscritura;
    }

    public Date getRepFechaActa() {
        return repFechaActa;
    }

    public void setRepFechaActa(Date repFechaActa) {
        this.repFechaActa = repFechaActa;
    }

    public String getRepNumNotaria() {
        return repNumNotaria;
    }

    public void setRepNumNotaria(String repNumNotaria) {
        this.repNumNotaria = repNumNotaria;
    }

    public String getRepNombreNotario() {
        return repNombreNotario;
    }

    public void setRepNombreNotario(String repNombreNotario) {
        this.repNombreNotario = repNombreNotario;
    }

    public String getRepSintesisActa() {
        return repSintesisActa;
    }

    public void setRepSintesisActa(String repSintesisActa) {
        this.repSintesisActa = repSintesisActa;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    public String getGiroDescripcion() {
        return giroDescripcion;
    }

    public void setGiroDescripcion(String giroDescripcion) {
        this.giroDescripcion = giroDescripcion;
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
    public List<ContactosProveedores> getContactosProveedoresList() {
        return contactosProveedoresList;
    }

    public void setContactosProveedoresList(List<ContactosProveedores> contactosProveedoresList) {
        this.contactosProveedoresList = contactosProveedoresList;
    }

    @XmlTransient
    public List<FalloProcedimientoRcb> getFalloProcedimientoRcbList() {
        return falloProcedimientoRcbList;
    }

    public void setFalloProcedimientoRcbList(List<FalloProcedimientoRcb> falloProcedimientoRcbList) {
        this.falloProcedimientoRcbList = falloProcedimientoRcbList;
    }

    @XmlTransient
    public List<ProveedorFabricante> getProveedorFabricanteList() {
        return proveedorFabricanteList;
    }

    public void setProveedorFabricanteList(List<ProveedorFabricante> proveedorFabricanteList) {
        this.proveedorFabricanteList = proveedorFabricanteList;
    }

    @XmlTransient
    public List<Propuestas> getPropuestasList() {
        return propuestasList;
    }

    public void setPropuestasList(List<Propuestas> propuestasList) {
        this.propuestasList = propuestasList;
    }

    public TipoProveedor getIdTipoProveedor() {
        return idTipoProveedor;
    }

    public void setIdTipoProveedor(TipoProveedor idTipoProveedor) {
        this.idTipoProveedor = idTipoProveedor;
    }

    public Giro getIdGiro() {
        return idGiro;
    }

    public void setIdGiro(Giro idGiro) {
        this.idGiro = idGiro;
    }

    public RepresentanteLegal getIdRepresentanteLegal() {
        return idRepresentanteLegal;
    }

    public void setIdRepresentanteLegal(RepresentanteLegal idRepresentanteLegal) {
        this.idRepresentanteLegal = idRepresentanteLegal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedores)) {
            return false;
        }
        Proveedores other = (Proveedores) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Proveedores[ idProveedor=" + idProveedor + " ]";
    }

    public void setIdProveedor(Proveedores provedor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
