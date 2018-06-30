
package com.issste.sicabis.ejb.utils;

import com.issste.sicabis.ejb.modelo.ConfiguraDpn;
import com.issste.sicabis.ejb.modelo.Dpn;
import java.util.Calendar;
import java.util.Date;


public class AlertasUtil {
    
    public Date sumarRestarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de dÃ­as a aÃ±adir, o restar en caso de dÃ­as<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos dÃ­as aÃ±adidos

    }
    
}
