
package com.issste.sicabis.DTO;

import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.Tareas;
import com.issste.sicabis.ejb.modelo.Usuarios;
import java.io.Serializable;
import java.util.Date;


public class BitacoraDTO implements Serializable{
    
    private Tareas tarea;
    private Usuarios usuarios;
    private Estatus estatus;
    private String descripcion;
    private Date fecha;

    public Tareas getTarea() {
        return tarea;
    }

    public void setTarea(Tareas tarea) {
        this.tarea = tarea;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
