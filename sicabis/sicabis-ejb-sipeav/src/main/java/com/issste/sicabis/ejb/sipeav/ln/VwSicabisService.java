package com.issste.sicabis.ejb.sipeav.ln;

import com.issste.sicabis.ejb.sipeav.DAO.VwSicabis;
import com.issste.sicabis.ejb.sipeav.modelo.Sicabis;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class VwSicabisService {

    @EJB
    private VwSicabis vwSicabisImplement;
    
    public List<Sicabis> obtenerByNssCurp(String nss, String curp) {
        return vwSicabisImplement.obtenerByNssCurp(nss, curp);
    }
    
    public List<Sicabis> obtenerBeneficiarios(String nss) {
        return vwSicabisImplement.obtenerBeneficiarios(nss);
    }

}
