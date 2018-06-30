/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.AlmacenDAO;
import com.issste.sicabis.ejb.modelo.Almacen;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class AlmacenService {
    
    @EJB
    AlmacenDAO almacenDAO;
    
    public List<Almacen> obtenerAlmacenes() {
        return almacenDAO.getAlmacenes();
    }
    
    public List<Almacen> getAlmacenesByActivo() {
        return almacenDAO.getAlmacenesByActivo();
    }
    
    public Almacen obtenerAlmacenByNombre(String nombre) {
        List<Almacen> list
                = almacenDAO.obtenerAlmacenByNombre(nombre);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
    
    public void guardarAlmacen(Almacen almacen) {
        almacenDAO.guardarAlmacen(almacen);
    }
    
    public List<Almacen> getAlmacenesActivosAndSelect(int idAlmacen) {
        return almacenDAO.getAlmacenesActivosAndSelect(idAlmacen);
    }
}
