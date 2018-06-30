package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.CatDetalleImDAO;
import com.issste.sicabis.ejb.modelo.CatDetalleIm;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class CatDetalleImService {

    @EJB
    private CatDetalleImDAO catDetalleImDAOImplement;
    
    public boolean guardar(CatDetalleIm catDetalleIm) {
        return catDetalleImDAOImplement.guardar(catDetalleIm);
    }
    
    public CatDetalleIm obtenerByIdCatDetalleIm(String idCatDetalleIm) {
        return catDetalleImDAOImplement.obtenerByIdCatDetalleIm(idCatDetalleIm);
    }
    
    public List<CatDetalleIm> obtenerTodos() {
        return catDetalleImDAOImplement.obtenerTodos();
    }
    
    public CatDetalleIm obtenerByIdJefatura(Integer idJefatura) {
        return catDetalleImDAOImplement.obtenerByIdJefatura(idJefatura);
    }
    
    public boolean actualizar(CatDetalleIm catDetalleIm) {
        return catDetalleImDAOImplement.actualizar(catDetalleIm);
    }

}
