
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.CatDetalleIm;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CatDetalleImDAO {
    
    boolean guardar(CatDetalleIm catDetalleIm);
    
    CatDetalleIm obtenerByIdCatDetalleIm(String idCatDetalleIm);
    
    List<CatDetalleIm> obtenerTodos();
    
    CatDetalleIm obtenerByIdJefatura(Integer idJefatura);
    
    boolean actualizar(CatDetalleIm catDetalleIm);
    
}
