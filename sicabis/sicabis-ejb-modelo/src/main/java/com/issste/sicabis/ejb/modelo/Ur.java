
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "ur")
public class Ur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ur")
    private Integer ur;
    @Column(name = "num_ur")
    private String numUr;
    @Column(name = "activo")
    private Integer activo;
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "id_delegacion", referencedColumnName = "id_delegacion")
    @ManyToOne(optional = false)
    private Delegaciones idDelegacion;
    @JoinColumn(name = "id_tipo_ur", referencedColumnName = "id_tipo_ur")
    @ManyToOne(optional = false)
    private TipoUr idTipoUr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ur")
    private List<AlertasDpn> alertasDpnList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ur")
    private List<Usuarios> usuariosList;

    public Ur() {
    }

    public Ur(Integer ur) {
        this.ur = ur;
    }

    public Integer getUr() {
        return ur;
    }

    public void setUr(Integer ur) {
        this.ur = ur;
    }

    public String getNumUr() {
        return numUr;
    }

    public void setNumUr(String numUr) {
        this.numUr = numUr;
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

    public Delegaciones getIdDelegacion() {
        return idDelegacion;
    }

    public void setIdDelegacion(Delegaciones idDelegacion) {
        this.idDelegacion = idDelegacion;
    }

    public TipoUr getIdTipoUr() {
        return idTipoUr;
    }

    public void setIdTipoUr(TipoUr idTipoUr) {
        this.idTipoUr = idTipoUr;
    }

    @XmlTransient
    public List<AlertasDpn> getAlertasDpnList() {
        return alertasDpnList;
    }

    public void setAlertasDpnList(List<AlertasDpn> alertasDpnList) {
        this.alertasDpnList = alertasDpnList;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ur != null ? ur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ur)) {
            return false;
        }
        Ur other = (Ur) object;
        if ((this.ur == null && other.ur != null) || (this.ur != null && !this.ur.equals(other.ur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Ur[ ur=" + ur + " ]";
    }
}
