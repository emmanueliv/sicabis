/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario and u.activo = 1"),
    @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "Usuarios.findByContrasenia", query = "SELECT u FROM Usuarios u WHERE u.contrasenia = :contrasenia"),
    @NamedQuery(name = "Usuarios.findByActivo", query = "SELECT u FROM Usuarios u WHERE u.activo = :activo"),
    @NamedQuery(name = "Usuarios.findByNombre", query = "SELECT u FROM Usuarios u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuarios.findByApellidoPaterno", query = "SELECT u FROM Usuarios u WHERE u.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "Usuarios.findByApellidoMaterno", query = "SELECT u FROM Usuarios u WHERE u.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "Usuarios.findByCorreo", query = "SELECT u FROM Usuarios u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuarios.findByTelefono", query = "SELECT u FROM Usuarios u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuarios.findByUltimoAcceso", query = "SELECT u FROM Usuarios u WHERE u.ultimoAcceso = :ultimoAcceso"),
    @NamedQuery(name = "Usuarios.findByFechaCambioContrasenia", query = "SELECT u FROM Usuarios u WHERE u.fechaCambioContrasenia = :fechaCambioContrasenia"),
    @NamedQuery(name = "Usuarios.findByPrimerAcceso", query = "SELECT u FROM Usuarios u WHERE u.primerAcceso = :primerAcceso"),
    @NamedQuery(name = "Usuarios.findByIdUsuarioSuperior", query = "SELECT u FROM Usuarios u WHERE u.idUsuarioSuperior = :idUsuarioSuperior"),
    @NamedQuery(name = "Usuarios.findByUsuarioAlta", query = "SELECT u FROM Usuarios u WHERE u.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Usuarios.findByUsuarioBaja", query = "SELECT u FROM Usuarios u WHERE u.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Usuarios.findByUsuarioModificacion", query = "SELECT u FROM Usuarios u WHERE u.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Usuarios.findByFechaAlta", query = "SELECT u FROM Usuarios u WHERE u.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Usuarios.findByFechaBaja", query = "SELECT u FROM Usuarios u WHERE u.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Usuarios.findByFechaModificacion", query = "SELECT u FROM Usuarios u WHERE u.fechaModificacion = :fechaModificacion")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "contrasenia")
    private String contrasenia;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "correo")
    private String correo;
    //@Size(max = 45)
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "ultimo_acceso")
    @Temporal(TemporalType.DATE)
    private Date ultimoAcceso;
    @Column(name = "fecha_cambio_contrasenia")
    @Temporal(TemporalType.DATE)
    private Date fechaCambioContrasenia;
    @Column(name = "primer_acceso")
    private Integer primerAcceso;
    @Column(name = "id_usuario_superior")
    private Integer idUsuarioSuperior;
    //@Size(max = 45)
    @Column(name = "usuario_alta")
    private String usuarioAlta;
    //@Size(max = 45)
    @Column(name = "usuario_baja")
    private String usuarioBaja;
//    @Size(max = 45)
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
    @JoinColumn(name = "id_unidad_medica", referencedColumnName = "id_unidades_medicas")
    @ManyToOne(optional = true)
    private UnidadesMedicas idUnidadMedica;
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne(optional = false)
    private Area idArea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<AlertasCorreo> alertasCorreoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<UsuarioPerfil> usuarioPerfilList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<UsuariosTipoUsuarios> usuariosTipoUsuariosList;
    @JoinColumn(name = "id_delegacion", referencedColumnName = "id_delegacion")
    @ManyToOne(optional = false)
    private Delegaciones idDelegacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<AlertasDpn> alertasDpnList;
    @JoinColumn(name = "ur", referencedColumnName = "ur")
    @ManyToOne(optional = false)
    private Ur ur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<CatDetalleIm> catDetalleImList;
    
    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios(Integer idUsuario, String usuario, String contrasenia, String nombre, String apellidoPaterno, String apellidoMaterno, String correo) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public Date getFechaCambioContrasenia() {
        return fechaCambioContrasenia;
    }

    public void setFechaCambioContrasenia(Date fechaCambioContrasenia) {
        this.fechaCambioContrasenia = fechaCambioContrasenia;
    }

    public Integer getPrimerAcceso() {
        return primerAcceso;
    }

    public void setPrimerAcceso(Integer primerAcceso) {
        this.primerAcceso = primerAcceso;
    }

    public Integer getIdUsuarioSuperior() {
        return idUsuarioSuperior;
    }

    public void setIdUsuarioSuperior(Integer idUsuarioSuperior) {
        this.idUsuarioSuperior = idUsuarioSuperior;
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

    public UnidadesMedicas getIdUnidadMedica() {
        return idUnidadMedica;
    }

    public void setIdUnidadMedica(UnidadesMedicas idUnidadMedica) {
        this.idUnidadMedica = idUnidadMedica;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public List<UsuariosTipoUsuarios> getUsuariosTipoUsuariosList() {
        return usuariosTipoUsuariosList;
    }

    public void setUsuariosTipoUsuariosList(List<UsuariosTipoUsuarios> usuariosTipoUsuariosList) {
        this.usuariosTipoUsuariosList = usuariosTipoUsuariosList;
    }

    @XmlTransient
    public List<AlertasCorreo> getAlertasCorreoList() {
        return alertasCorreoList;
    }

    public void setAlertasCorreoList(List<AlertasCorreo> alertasCorreoList) {
        this.alertasCorreoList = alertasCorreoList;
    }

    @XmlTransient
    public List<UsuarioPerfil> getUsuarioPerfilList() {
        return usuarioPerfilList;
    }

    public void setUsuarioPerfilList(List<UsuarioPerfil> usuarioPerfilList) {
        this.usuarioPerfilList = usuarioPerfilList;
    }

    public Delegaciones getIdDelegacion() {
        return idDelegacion;
    }

    public void setIdDelegacion(Delegaciones idDelegacion) {
        this.idDelegacion = idDelegacion;
    }

    @XmlTransient
    public List<AlertasDpn> getAlertasDpnList() {
        return alertasDpnList;
    }

    public void setAlertasDpnList(List<AlertasDpn> alertasDpnList) {
        this.alertasDpnList = alertasDpnList;
    }

    public Ur getUr() {
        return ur;
    }

    public void setUr(Ur ur) {
        this.ur = ur;
    }

    public List<CatDetalleIm> getCatDetalleImList() {
        return catDetalleImList;
    }

    public void setCatDetalleImList(List<CatDetalleIm> catDetalleImList) {
        this.catDetalleImList = catDetalleImList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Usuarios[ idUsuario=" + idUsuario + " ]";
    }

}
