/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.AreaDAO;
import com.issste.sicabis.ejb.modelo.Area;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateful
public class AreasService {
    
    @EJB
    AreaDAO areaDAOImpl;
            
    public List<Area> obtenerAreas(){
        return areaDAOImpl.getAreas();
    }
    
    public Area obtenerAreaByNombre(String nombreArea){
        List<Area> areaList = 
            areaDAOImpl.obtenerAreaByNombre(nombreArea);
        if(areaList.isEmpty()){
            return null;
        } else {
            return areaList.get(0);
        }
    }
    
    public void guardarArea(Area area){
        areaDAOImpl.guardarArea(area);
    }
    
    public List<Area> getAreasAdmon() {
        return areaDAOImpl.getAreasAdmon();
    }
}
