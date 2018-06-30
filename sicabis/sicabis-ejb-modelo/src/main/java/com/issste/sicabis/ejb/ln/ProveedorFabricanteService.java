package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ProveedorFabricanteDAO;
import com.issste.sicabis.ejb.modelo.ProveedorFabricante;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class ProveedorFabricanteService {

    @EJB
    private ProveedorFabricanteDAO proveedorFabricanteDAOImplement;

    public boolean guardarProveedorFabricante(ProveedorFabricante proveedorFabricante) {
        return proveedorFabricanteDAOImplement.guardarProveedorFabricante(proveedorFabricante);
    }

    public boolean borrarByIdProveedorFabricante(Integer idProcedimientoRcb) {
        return proveedorFabricanteDAOImplement.borrarByIdProveedorFabricante(idProcedimientoRcb);
    }
    
    public List<ProveedorFabricante> obtenerByProveedorProcRcb(Integer idProveedor, Integer idProcedimientoRcb) {
        return proveedorFabricanteDAOImplement.obtenerByProveedorProcRcb(idProveedor, idProcedimientoRcb);
    }

}
