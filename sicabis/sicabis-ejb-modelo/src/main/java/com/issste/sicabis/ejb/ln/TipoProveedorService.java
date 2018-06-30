/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.TipoProveedorDAO;
import com.issste.sicabis.ejb.modelo.TipoProveedor;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class TipoProveedorService {

    @EJB
    private TipoProveedorDAO tipoProveedorDAOImplement;

    public List<TipoProveedor> obtenerTipoProveedores() {
        return tipoProveedorDAOImplement.obtenerTipoProveedores();
    }

    public TipoProveedor obtenerTipoDocumentosByNombre(String nombreTipoProveedor) {
        List<TipoProveedor> lista = tipoProveedorDAOImplement.obtenerTipoProveedoresByNombre(nombreTipoProveedor);
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public void guardarTipoDocumento(TipoProveedor tipoProveedor) {
        tipoProveedorDAOImplement.guardarTipoDocumento(tipoProveedor);
    }
    
    public TipoProveedor obtenerByTipo(String tipo){
        return tipoProveedorDAOImplement.obtenerByTipo(tipo);
    }
    
}
