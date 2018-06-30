package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.CanjePermuta;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fabianvr
 */
@Local
public interface CanjeDAO {

    Integer guardarCanje(CanjePermuta canje);

    Integer folioCanje();

    BigDecimal precioInsumoCanje(String clave);

    List<CanjePermuta> canjePermuta();

    List<CanjePermuta> canjePermutaById(Integer id);

}
