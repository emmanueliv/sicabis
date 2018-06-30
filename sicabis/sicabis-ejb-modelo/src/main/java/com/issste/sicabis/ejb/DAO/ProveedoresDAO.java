package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Contactos;
import com.issste.sicabis.ejb.modelo.Proveedores;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ProveedoresDAO {

    List<Proveedores> proveedoresAll();

    Proveedores obtenerByIdProveedor(Integer idProveedor);

    void guardarProveedor(Proveedores proveedor);
    
    Contactos guardarCP(Contactos cp);

    List<Proveedores> obtenerProveedoresByNombre(String nombreProveedor);
    
    List<Proveedores> obtenerByAutorizado();
    
    List<Proveedores> getAllProveedoresByActivo();
    
    List<Proveedores> proveedoresWhitFallo();
    
    void guarda(Proveedores proveedor);
    
    List<Proveedores> getByNombreNumero(String nombreProveedor, Integer numeroProveedor);
    
    Proveedores getByNumeroProveedor(Integer numeroProveedor);

}
