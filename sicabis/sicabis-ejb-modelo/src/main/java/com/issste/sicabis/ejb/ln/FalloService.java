package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.FalloDAO;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Fallos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class FalloService {

    @EJB
    private FalloDAO falloDAOImplement;
    
    public boolean guardaFallos(Fallos fallos){
        return falloDAOImplement.guardaFallos(fallos);
    }
    
    public boolean actualizaFallo(Fallos fallos){
        return falloDAOImplement.actualizaFallo(fallos);
    }
    
    public Fallos obtenerByNumeroFallo(String numeroFallo){
        return falloDAOImplement.obtenerByNumeroFallo(numeroFallo);
    }
    
    public List<Fallos> obtenerByFalloProcRcb(Fallos fallos){
        return falloDAOImplement.obtenerByFalloProcRcb(fallos);
    }
    
    public Fallos obtenerByNumProcRcb(String numeroProcedimiento) {
        return falloDAOImplement.obtenerByNumProcRcb(numeroProcedimiento);
    }
}
