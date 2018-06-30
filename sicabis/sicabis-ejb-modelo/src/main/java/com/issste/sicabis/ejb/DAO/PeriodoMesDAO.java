
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.PeriodoMes;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PeriodoMesDAO {
    
    List<PeriodoMes> getByAnio(int anio);
    boolean guardarPeriodoMes(PeriodoMes pm);
    PeriodoMes getPeriodoActivo(int anio, int mes);
    PeriodoMes getByFechaCorte(Date fechaCorte);
    PeriodoMes getByFechaCorteMod(Date fechaCorte);
    
}
