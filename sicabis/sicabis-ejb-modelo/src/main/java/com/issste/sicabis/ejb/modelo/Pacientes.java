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
@Table(name = "pacientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pacientes.findAll", query = "SELECT p FROM Pacientes p"),
    @NamedQuery(name = "Pacientes.findByIdPaciente", query = "SELECT p FROM Pacientes p WHERE p.idPaciente = :idPaciente"),
    @NamedQuery(name = "Pacientes.findByActivo", query = "SELECT p FROM Pacientes p WHERE p.activo = :activo"),
    @NamedQuery(name = "Pacientes.findByNombre", query = "SELECT p FROM Pacientes p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Pacientes.findByApaterno", query = "SELECT p FROM Pacientes p WHERE p.apaterno = :apaterno"),
    @NamedQuery(name = "Pacientes.findByAmaterno", query = "SELECT p FROM Pacientes p WHERE p.amaterno = :amaterno"),
    @NamedQuery(name = "Pacientes.findByExpedinete", query = "SELECT p FROM Pacientes p WHERE p.expedinete = :expedinete"),
    @NamedQuery(name = "Pacientes.findByCurp", query = "SELECT p FROM Pacientes p WHERE p.curp = :curp"),
    @NamedQuery(name = "Pacientes.findByNss", query = "SELECT p FROM Pacientes p WHERE p.nss = :nss"),
    @NamedQuery(name = "Pacientes.findByGenero", query = "SELECT p FROM Pacientes p WHERE p.genero = :genero"),
    @NamedQuery(name = "Pacientes.findByDiagnostico", query = "SELECT p FROM Pacientes p WHERE p.diagnostico = :diagnostico"),
    @NamedQuery(name = "Pacientes.findByUsuarioAlta", query = "SELECT p FROM Pacientes p WHERE p.usuarioAlta = :usuarioAlta"),
    @NamedQuery(name = "Pacientes.findByUsuarioBaja", query = "SELECT p FROM Pacientes p WHERE p.usuarioBaja = :usuarioBaja"),
    @NamedQuery(name = "Pacientes.findByUsuarioModificacion", query = "SELECT p FROM Pacientes p WHERE p.usuarioModificacion = :usuarioModificacion"),
    @NamedQuery(name = "Pacientes.findByFechaAlta", query = "SELECT p FROM Pacientes p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Pacientes.findByFechaBaja", query = "SELECT p FROM Pacientes p WHERE p.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "Pacientes.findByFechaModificacion", query = "SELECT p FROM Pacientes p WHERE p.fechaModificacion = :fechaModificacion")})
public class Pacientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_paciente")
    private Integer idPaciente;
    @Column(name = "activo")
    private Integer activo;
    @Basic(optional = false)
    @NotNull    
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull   
    @Column(name = "apaterno")
    private String apaterno;
    @Basic(optional = false)
    @NotNull   
    @Column(name = "amaterno")
    private String amaterno;    
    @Column(name = "expedinete")
    private String expedinete;
    @Column(name = "curp")
    private String curp;   
    @Column(name = "nss")
    private String nss;
    @Basic(optional = false)
    @NotNull
    @Column(name = "genero")
    private int genero;    
    @Column(name = "diagnostico")
    private String diagnostico;  
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "beneficiario")
    private Integer beneficiario;
    @Column(name = "usuario_alta")
    private String usuarioAlta;    
    @Column(name = "usuario_baja")
    private String usuarioBaja;   
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

    public Pacientes() {
    }

    public Pacientes(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Pacientes(Integer idPaciente, String nombre, String apaterno, String amaterno, int genero) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.genero = genero;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
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

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getExpedinete() {
        return expedinete;
    }

    public void setExpedinete(String expedinete) {
        this.expedinete = expedinete;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Integer getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Integer beneficiario) {
        this.beneficiario = beneficiario;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaciente != null ? idPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacientes)) {
            return false;
        }
        Pacientes other = (Pacientes) object;
        if ((this.idPaciente == null && other.idPaciente != null) || (this.idPaciente != null && !this.idPaciente.equals(other.idPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Pacientes[ idPaciente=" + idPaciente + " ]";
    }
    
}
