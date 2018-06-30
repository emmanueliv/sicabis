/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.TareasDAO;
import com.issste.sicabis.ejb.modelo.Tareas;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class TareasService {
    
    @EJB
    private TareasDAO tareasDAOImpl;
    
    public List<Tareas> obtenerTareas(){
        return tareasDAOImpl.obtenerTareas();
    }

    public void guardarTarea(Tareas tarea) {
        tareasDAOImpl.guardarTarea(tarea);
    }

    public Tareas obtenerTareaByNombre(String nombreTarea) {
        List<Tareas> lista = 
                tareasDAOImpl.obtenerTareaByNombre(nombreTarea);
        if(lista.isEmpty()){
            return null;
        } else {
            return lista.get(0);
        }
    }
    
    public Tareas getByIdTarea(Integer idTarea) {
        return tareasDAOImpl.getByIdTarea(idTarea);
    }
}
