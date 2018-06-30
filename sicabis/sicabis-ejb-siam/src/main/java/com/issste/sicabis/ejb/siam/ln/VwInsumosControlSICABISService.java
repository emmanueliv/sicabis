package com.issste.sicabis.ejb.siam.ln;

import com.issste.sicabis.ejb.siam.DAO.VwInsumosControlSICABISDAO;
import com.issste.sicabis.ejb.siam.modelo.VwInsumosControlSICABIS;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class VwInsumosControlSICABISService {

    @EJB
    private VwInsumosControlSICABISDAO vwInsumosControlSICABISDAOImplement;

    public List<VwInsumosControlSICABIS> obtenerByClave(String clave) {
        return vwInsumosControlSICABISDAOImplement.obtenerByClave(clave);
    }

}
