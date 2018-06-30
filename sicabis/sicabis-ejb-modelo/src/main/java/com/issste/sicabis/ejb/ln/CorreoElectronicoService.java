package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.CorreoElectronicoDAO;
import com.issste.sicabis.ejb.modelo.CorreoElectronico;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class CorreoElectronicoService {

    @EJB
    private CorreoElectronicoDAO correoElectronicoDAOImplement;
    
    public boolean guardar(CorreoElectronico correoElectronico) {
        return correoElectronicoDAOImplement.guardar(correoElectronico);
    }
    

}
