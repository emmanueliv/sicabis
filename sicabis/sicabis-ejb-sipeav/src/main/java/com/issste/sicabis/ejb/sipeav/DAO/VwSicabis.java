
package com.issste.sicabis.ejb.sipeav.DAO;

import com.issste.sicabis.ejb.sipeav.modelo.Sicabis;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

@Local
public interface VwSicabis {
    
    List<Sicabis> obtenerByNssCurp(String nss, String curp);
    List<Sicabis> obtenerBeneficiarios(String nss);
    
}
