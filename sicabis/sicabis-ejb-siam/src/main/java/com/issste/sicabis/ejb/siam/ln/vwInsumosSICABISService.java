package com.issste.sicabis.ejb.siam.ln;

import com.issste.sicabis.ejb.siam.DAO.vwInsumosSICABISDAO;
import com.issste.sicabis.ejb.siam.modelo.VwInsumosSICABIS;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class vwInsumosSICABISService {

    @EJB
    private vwInsumosSICABISDAO vwInsumosSICABISDAOImplement;
    
    public List<VwInsumosSICABIS> obtenerVwInsumos() {
        return vwInsumosSICABISDAOImplement.obtenerVwInsumos();
    }

}
