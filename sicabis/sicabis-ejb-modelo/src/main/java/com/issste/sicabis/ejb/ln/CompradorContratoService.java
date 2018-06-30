package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.CompradorContratoDAOImplement;
import com.issste.sicabis.ejb.modelo.CompradorContrato;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class CompradorContratoService {

    @EJB
    private CompradorContratoDAOImplement compradorContratoDAOImplement;

    public boolean guardaCompradorContrato(CompradorContrato compradorContrato) {
        return compradorContratoDAOImplement.guardaCompradorContrato(compradorContrato);
    }

    public boolean actualizaCompradorContrato(CompradorContrato compradorContrato) {
        return compradorContratoDAOImplement.actualizaCompradorContrato(compradorContrato);
    }

    public boolean borrarByIdContrato(Integer idContrato) {
        return compradorContratoDAOImplement.borrarByIdContrato(idContrato);
    }

    public CompradorContrato obtenerByIdContrato(Integer idContrato) {
        return compradorContratoDAOImplement.obtenerByIdContrato(idContrato);
    }

    public List<CompradorContrato> obtenerListByIdContrato(Integer idContrato) {
        return compradorContratoDAOImplement.obtenerListByIdContrato(idContrato);
    }

}
