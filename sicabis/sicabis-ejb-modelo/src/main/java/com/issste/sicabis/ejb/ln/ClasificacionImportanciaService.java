package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ClasificacionImportanciaDAO;
import com.issste.sicabis.ejb.modelo.ClasificacionImportancia;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class ClasificacionImportanciaService {

    @EJB
    private ClasificacionImportanciaDAO clasificacionImportanciaDAOImplement;

    public void guardarClasificacionImportancia(ClasificacionImportancia clasificacionImportancia) {
        clasificacionImportanciaDAOImplement.guardarClasificacionImportancia(clasificacionImportancia);
    }

    public List<ClasificacionImportancia> obtenerByClasificacion(ClasificacionImportancia clasificacionImportancia) {
        return clasificacionImportanciaDAOImplement.obtenerByClasificacion(clasificacionImportancia);
    }

    public Integer obtenerUltimoRegistro() {
        return clasificacionImportanciaDAOImplement.obtenerUltimoRegistro();
    }
}
