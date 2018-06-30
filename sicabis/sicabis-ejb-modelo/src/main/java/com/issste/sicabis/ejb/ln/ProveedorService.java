package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ProveedoresDAO;
import com.issste.sicabis.ejb.modelo.Proveedores;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ProveedorService {

    @EJB
    private ProveedoresDAO proveedoresDAOImplement;

    public List<Proveedores> proveedoresAll() {
        return proveedoresDAOImplement.proveedoresAll();
    }

    public Proveedores obtenerByIdProveedor(Integer idProveedor) {
        return proveedoresDAOImplement.obtenerByIdProveedor(idProveedor);
    }

    public void guardarProveedor(Proveedores proveedor) {
        proveedoresDAOImplement.guardarProveedor(proveedor);
    }

    public Proveedores obtenerProveedorByNombre(String nombreProveedor) {
        List<Proveedores> lista = proveedoresDAOImplement.obtenerProveedoresByNombre(nombreProveedor);
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public List<Proveedores> obtenerByAutorizado() {
        return proveedoresDAOImplement.obtenerByAutorizado();
    }

    public List<Proveedores> getAllProveedoresByActivo() {
        return proveedoresDAOImplement.getAllProveedoresByActivo();
    }

    public List<Proveedores> proveedoresWhitFallo() {
        return  proveedoresDAOImplement.proveedoresWhitFallo();
    }
    
    public void guarda(Proveedores proveedor) {
        proveedoresDAOImplement.guarda(proveedor);
    }
    
    public List<Proveedores> getByNombreNumero(String nombreProveedor, Integer numeroProveedor) {
        return proveedoresDAOImplement.getByNombreNumero(nombreProveedor, numeroProveedor);
    }
    
    public Proveedores getByNumeroProveedor(Integer numeroProveedor) {
        return proveedoresDAOImplement.getByNumeroProveedor(numeroProveedor);
    }

}
