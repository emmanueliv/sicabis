/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Toshiba Manolo
 */
@Entity
@Table(name = "rcb_grupo")
@NamedQueries({
    @NamedQuery(name = "RcbGrupo.findAll", query = "SELECT r FROM RcbGrupo r"),
    @NamedQuery(name = "RcbGrupo.findByIdRcbGrupo", query = "SELECT r FROM RcbGrupo r WHERE r.idRcbGrupo = :idRcbGrupo"),
    @NamedQuery(name = "RcbGrupo.findByIdRcb", query = "SELECT r FROM RcbGrupo r WHERE r.idRcb.idRcb = :idRcb"),
    @NamedQuery(name = "RcbGrupo.findByIdGrupo", query = "SELECT r FROM RcbGrupo r WHERE r.idGrupo.idGrupo = :idGrupo")})
public class RcbGrupo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rcb_grupo")
    private Integer idRcbGrupo;
    @JoinColumn(name = "id_rcb", referencedColumnName = "id_rcb")
    @ManyToOne(optional = false)
    private Rcb idRcb;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne(optional = false)
    private Grupo idGrupo;

    public RcbGrupo() {
    }

    public RcbGrupo(Integer idRcbGrupo) {
        this.idRcbGrupo = idRcbGrupo;
    }

    public RcbGrupo(Integer idRcbGrupo, Rcb idRcb, Grupo idGrupo) {
        this.idRcbGrupo = idRcbGrupo;
        this.idRcb = idRcb;
        this.idGrupo = idGrupo;
    }

    

    public Integer getIdRcbGrupo() {
        return idRcbGrupo;
    }

    public void setIdRcbGrupo(Integer idRcbGrupo) {
        this.idRcbGrupo = idRcbGrupo;
    }

    public Rcb getIdRcb() {
        return idRcb;
    }

    public void setIdRcb(Rcb idRcb) {
        this.idRcb = idRcb;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }
    
    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRcbGrupo != null ? idRcbGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RcbGrupo)) {
            return false;
        }
        RcbGrupo other = (RcbGrupo) object;
        if ((this.idRcbGrupo == null && other.idRcbGrupo != null) || (this.idRcbGrupo != null && !this.idRcbGrupo.equals(other.idRcbGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.RcbGrupo[ idRcbGrupo=" + idRcbGrupo + " ]";
    }
    
}
