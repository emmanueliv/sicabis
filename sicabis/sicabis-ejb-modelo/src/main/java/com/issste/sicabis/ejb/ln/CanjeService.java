package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.CanjeDAO;
import com.issste.sicabis.ejb.modelo.CanjePermuta;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author fabianvr
 */
@Stateless
public class CanjeService {

    @EJB
    private CanjeDAO canjeDAOImplement;

    public Integer guardarCanje(CanjePermuta canje) {
        return canjeDAOImplement.guardarCanje(canje);
    }

    public Integer folioCanje() {

        Integer f = canjeDAOImplement.folioCanje();
        if (f == null) {
            f = 1;
        } else {
            f = f + 1;
        }
        return f;
    }

    public BigDecimal precioInsumoCanje(String clave) {
        return canjeDAOImplement.precioInsumoCanje(clave);
    }

    public List<CanjePermuta> canjePermuta() {
        return canjeDAOImplement.canjePermuta();
    }

    public List<CanjePermuta> canjePermutaById(Integer id) {
        return canjeDAOImplement.canjePermutaById(id);
    }

}
