/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.EstatusDAO;
import com.issste.sicabis.ejb.modelo.Estatus;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author erik
 */
@Stateful
public class EstatusService {

    @EJB
    private EstatusDAO estatusDAOImplement;

    public List<Estatus> traeEstatusList() {
        return estatusDAOImplement.getAllEstatus();
    }

    public Estatus getRemisionEstatus(Integer e){
        return estatusDAOImplement.getRemisionEstatus(e);
    }
    
    public List<Estatus> getEstatusByTarea(int idTarea){
        return estatusDAOImplement.getEstatusByTarea(idTarea);
    }

    public Estatus getEstatusByNombre(String nombreStatus) {
        List<Estatus> estatusList = estatusDAOImplement.getEstatusByNombre(nombreStatus);
        if(estatusList.isEmpty()){
            return null;
        } else {
            return estatusList.get(0);
        }
    }

    public void guardarEstatus(Estatus estatus) {
        estatusDAOImplement.guardarEstatus(estatus);
    }
    
    public List<Estatus> getByEstatusIdTarea(int idTarea, String opcion){
        return estatusDAOImplement.getByEstatusIdTarea(idTarea, opcion);
    }
}
