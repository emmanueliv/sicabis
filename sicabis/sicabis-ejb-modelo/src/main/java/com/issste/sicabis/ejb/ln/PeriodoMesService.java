package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.PeriodoMesDAO;
import com.issste.sicabis.ejb.modelo.PeriodoMes;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class PeriodoMesService {

    @EJB
    private PeriodoMesDAO periodoMesDAOImplement;
    
    public List<PeriodoMes> getByAnio(int anio) {
        return periodoMesDAOImplement.getByAnio(anio);
    }
    
    public boolean guardarPeriodoMes(PeriodoMes pm) {
        return periodoMesDAOImplement.guardarPeriodoMes(pm);
    }
    
    public PeriodoMes getPeriodoActivo(int anio, int mes) {
        return periodoMesDAOImplement.getPeriodoActivo(anio, mes);
    }
    
    public PeriodoMes getByFechaCorte(Date fechaCorte) {
        return periodoMesDAOImplement.getByFechaCorte(fechaCorte);
    }
    
    public PeriodoMes getByFechaCorteMod(Date fechaCorte) {
        return periodoMesDAOImplement.getByFechaCorteMod(fechaCorte);
    }

}
