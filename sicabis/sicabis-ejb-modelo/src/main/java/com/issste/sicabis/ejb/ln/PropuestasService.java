package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.PropuestasDAOImplement;
import com.issste.sicabis.ejb.modelo.Propuestas;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class PropuestasService {

    @EJB
    private PropuestasDAOImplement propuestasDAOImplement;

    public boolean guardarPropuestas(Propuestas propuestas) {
        return propuestasDAOImplement.guardarPropuestas(propuestas);
    }

    public boolean borrarByIdProcedimientoRcb(Integer idProcedimientoRcb) {
        return propuestasDAOImplement.borrarByIdProcedimientoRcb(idProcedimientoRcb);
    }

    public boolean actualizarPropuestas(Propuestas propuestas) {
        return propuestasDAOImplement.actualizarPropuestas(propuestas);
    }

    public List<Propuestas> getPropuestasByIdProcedimientoRcb(Integer idProcedimientoRcb) {
        return propuestasDAOImplement.getPropuestasByIdProcedimientoRcb(idProcedimientoRcb);
    }

}
