
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "representante_legal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepresentanteLegal.findAll", query = "SELECT rl FROM RepresentanteLegal rl ")
})
public class RepresentanteLegal implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_representante_legal")
    private Integer idRepresentanteLegal;
    @Column(name = "representante")
    private String representante;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Column(name = "numero_acta")
    private String numeroActa;
    @Column(name = "fecha_acta")
    @Temporal(TemporalType.DATE)
    private Date fechaActa;
    @Column(name = "numero_notaria")
    private String numeroNotaria;
    @Column(name = "nombre_notario")
    private String nombreNotario;
    @Column(name = "sintesis_acta")
    private String sintesisActa;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRepresentanteLegal")
    private List<Proveedores> proveedoresList;

    public RepresentanteLegal() {
    }

    public RepresentanteLegal(Integer idRepresentanteLegal) {
        this.idRepresentanteLegal = idRepresentanteLegal;
    }

    public Integer getIdRepresentanteLegal() {
        return idRepresentanteLegal;
    }

    public void setIdRepresentanteLegal(Integer idRepresentanteLegal) {
        this.idRepresentanteLegal = idRepresentanteLegal;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroActa() {
        return numeroActa;
    }

    public void setNumeroActa(String numeroActa) {
        this.numeroActa = numeroActa;
    }

    public Date getFechaActa() {
        return fechaActa;
    }

    public void setFechaActa(Date fechaActa) {
        this.fechaActa = fechaActa;
    }

    public String getNumeroNotaria() {
        return numeroNotaria;
    }

    public void setNumeroNotaria(String numeroNotaria) {
        this.numeroNotaria = numeroNotaria;
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
    public List<Proveedores> getProveedoresList() {
        return proveedoresList;
    }

    public void setProveedoresList(List<Proveedores> proveedoresList) {
        this.proveedoresList = proveedoresList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRepresentanteLegal != null ? idRepresentanteLegal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepresentanteLegal)) {
            return false;
        }
        RepresentanteLegal other = (RepresentanteLegal) object;
        if ((this.idRepresentanteLegal == null && other.idRepresentanteLegal != null) || (this.idRepresentanteLegal != null && !this.idRepresentanteLegal.equals(other.idRepresentanteLegal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.RepresentanteLegal[ idRepresentanteLegal=" + idRepresentanteLegal + " ]";
    }
    
}
