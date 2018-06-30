/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Almacen;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriosoft
 */
@Local
public interface AlmacenDAO {

    List<Almacen> getAlmacenes();

    List<Almacen> getAlmacenesByActivo();

    List<Almacen> obtenerAlmacenByNombre(String nombre);
    
    List<Almacen> getAlmacenesActivosAndSelect(int idAlmacen);

    void guardarAlmacen(Almacen almacen);

}
