package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ProveedorFabricante;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fabianvr
 */
@Local
public interface ProveedorFabricanteDAO {

    boolean guardarProveedorFabricante(ProveedorFabricante proveedorFabricante);
    boolean borrarByIdProveedorFabricante(Integer idProcedimientoRcb);
    List<ProveedorFabricante> obtenerByProveedorProcRcb(Integer idProveedor, Integer idProcedimientoRcb);
}
