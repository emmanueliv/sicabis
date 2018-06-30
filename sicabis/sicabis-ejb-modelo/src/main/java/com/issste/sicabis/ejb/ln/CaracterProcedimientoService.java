package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.CaracterProcedimientoDAO;
import com.issste.sicabis.ejb.modelo.CaracterProcedimiento;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CaracterProcedimientoService {

    @EJB
    private CaracterProcedimientoDAO caracterProcedimientoDAOImplement;

    public List<CaracterProcedimiento> obtenerCaracterProcedimiento() {
        return caracterProcedimientoDAOImplement.obtenerCaracterProcedimiento();
    }
    
    
        public CaracterProcedimiento obtenerCPByNombre(String nombre) {
        List<CaracterProcedimiento> list
                = caracterProcedimientoDAOImplement.obtenerTipoCPByNombre(nombre);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void guardarCP(CaracterProcedimiento cp) {
        caracterProcedimientoDAOImplement.guardarcaracterCaracterProcedimiento(cp);
    }
    
}
