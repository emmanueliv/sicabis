/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.siam.sheduler;

import com.issste.sicabis.ejb.siam.ln.VwExistenciasSICABISService;
import com.issste.sicabis.ejb.siam.ln.vwInsumosSICABISService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

@Stateless
public class ShedulerJobSiamDAOImplement implements ShedulerJobSiamDAO {

    //EJB's
    @EJB
    private vwInsumosSICABISService vwInsumosSICABISService;

    @EJB
    private VwExistenciasSICABISService vwExistenciasSICABISService;

    @Override
    @Schedule(second = "0", minute = "00", hour = "04")
    public void execute() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            if (cal.get(Calendar.DAY_OF_WEEK) != 1 && cal.get(Calendar.DAY_OF_WEEK) != 7) {
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyyMMdd");
                Date fecha = this.sumarRestarDiasFecha(new Date(), -1);
                vwExistenciasSICABISService.cargaConsumoDiario(formatoDeFecha.format(fecha));
            }
        } catch (Exception ex) {
            Logger.getLogger(ShedulerJobSiamDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    @Schedule(second = "0", minute = "00", hour = "01")
    public void executeInsumos() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            if (cal.get(Calendar.DAY_OF_WEEK) != 1 && cal.get(Calendar.DAY_OF_WEEK) != 7) {
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyyMMdd");
                vwInsumosSICABISService.obtenerVwInsumos();
            }
        } catch (Exception ex) {
            Logger.getLogger(ShedulerJobSiamDAOImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Date sumarRestarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de dÃ­as a aÃ±adir, o restar en caso de dÃ­as<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos dÃ­as aÃ±adidos

    }
}
