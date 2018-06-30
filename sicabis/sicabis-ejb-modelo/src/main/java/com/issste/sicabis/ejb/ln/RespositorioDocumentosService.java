package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.RespositorioDocumentosDAO;
import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class RespositorioDocumentosService {

    @EJB
    private RespositorioDocumentosDAO respositorioDocumentosDAOImplement;

    public boolean guardaProcedimiento(RespositorioDocumentos respositorioDocumentos) {
        return respositorioDocumentosDAOImplement.guardaProcedimiento(respositorioDocumentos);
    }

    public boolean borrarByIdRespositorioDocumento(Integer idRespositorioDocumento) {
        return respositorioDocumentosDAOImplement.borrarByIdRespositorioDocumento(idRespositorioDocumento);
    }

    public List<RespositorioDocumentos> obtenerByIdProcesoIdTarea(Integer idProceso, Integer idTarea) {
        return respositorioDocumentosDAOImplement.obtenerByIdProcesoIdTarea(idProceso, idTarea);
    }

    public List<RespositorioDocumentos> getByOrden(Integer idDetalle) {
        return respositorioDocumentosDAOImplement.getByOrden(idDetalle);
    }
}
