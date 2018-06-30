
package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispDelegaciones;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispG40;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "indicador")
public class Indicador implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_indicador")
    private Integer idIndicador;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private Integer activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIndicador")
    private List<PorcentajeDelegacion> porcentajeDelegacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIndicador")
    private List<MapaEjecutivoDispDelegaciones> mapaEjecutivoDispDelegacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIndicador")
    private List<MapaEjecutivoDispG40> mapaEjecutivoDispG40List;

    public Indicador() {
    }
    
    public Indicador(Integer idIndicador) {
        this.idIndicador = idIndicador;
    }

    public Integer getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(Integer idIndicador) {
        this.idIndicador = idIndicador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public List<PorcentajeDelegacion> getPorcentajeDelegacionList() {
        return porcentajeDelegacionList;
    }

    public void setPorcentajeDelegacionList(List<PorcentajeDelegacion> porcentajeDelegacionList) {
        this.porcentajeDelegacionList = porcentajeDelegacionList;
    }

    public List<MapaEjecutivoDispDelegaciones> getMapaEjecutivoDispDelegacionesList() {
        return mapaEjecutivoDispDelegacionesList;
    }

    public void setMapaEjecutivoDispDelegacionesList(List<MapaEjecutivoDispDelegaciones> mapaEjecutivoDispDelegacionesList) {
        this.mapaEjecutivoDispDelegacionesList = mapaEjecutivoDispDelegacionesList;
    }

    public List<MapaEjecutivoDispG40> getMapaEjecutivoDispG40List() {
        return mapaEjecutivoDispG40List;
    }

    public void setMapaEjecutivoDispG40List(List<MapaEjecutivoDispG40> mapaEjecutivoDispG40List) {
        this.mapaEjecutivoDispG40List = mapaEjecutivoDispG40List;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIndicador != null ? idIndicador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indicador)) {
            return false;
        }
        Indicador other = (Indicador) object;
        if ((this.idIndicador == null && other.idIndicador != null) || (this.idIndicador != null && !this.idIndicador.equals(other.idIndicador))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.issste.sicabis.ejb.modelo.Indicador[ idIndicador=" + idIndicador + " ]";
    }
    
}
