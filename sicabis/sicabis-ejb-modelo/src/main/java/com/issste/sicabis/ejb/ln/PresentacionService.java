package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.PresentacionDAO;
import com.issste.sicabis.ejb.modelo.Presentacion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author fabianvr
 */
@Stateless
public class PresentacionService {

    @EJB
    private PresentacionDAO presentacionDAOImplement;

    public List<Presentacion> presentacionList() {
        return presentacionDAOImplement.presentacionList();
    }

    public Presentacion presentacionByDesc(String desc) {
        List<Presentacion> lista
                = presentacionDAOImplement.presentacionByDesc(desc);
        if (lista != null && !lista.isEmpty()) {
            return lista.get(0);
        }
        return null;
    }

    public void guardarPresentacion(Presentacion presentacion) {
        presentacionDAOImplement.guardarPresentacion(presentacion);
    }

}
