package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ClasificacionProcedimientoDAO;
import com.issste.sicabis.ejb.modelo.ClasificacionProcedimiento;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ClasificacionProcedimientoService {

    @EJB
    private ClasificacionProcedimientoDAO clasificacionProcedimientoDAOImplement;

    public List<ClasificacionProcedimiento> obtenerClasificacionProcedimiento() {
        return clasificacionProcedimientoDAOImplement.obtenerClasificacionProcedimiento();
    }

    public ClasificacionProcedimiento obtenerClasificacionProcedimientoByNombre(String nombre) {
        List<ClasificacionProcedimiento> list
                = clasificacionProcedimientoDAOImplement.obtenerTipoCPByNombre(nombre);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void guardarClasificacionProcedimiento(ClasificacionProcedimiento cfprod) {
        clasificacionProcedimientoDAOImplement.guardarClasificacionProcedimiento(cfprod);
    }
}
