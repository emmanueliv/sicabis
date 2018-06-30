/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.TipoProveedor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriosoft
 */
@Local
public interface TipoProveedorDAO {

    List<TipoProveedor> obtenerTipoProveedores();
    
    List<TipoProveedor> obtenerTipoProveedoresByNombre(String nombre);
    
    void guardarTipoDocumento(TipoProveedor tipoProveedor);
    
    TipoProveedor obtenerByTipo(String tipo);
            
}
