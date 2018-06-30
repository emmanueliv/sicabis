/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.utils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author 9RZCBG2
 */
public class Utilidades {

    public BigDecimal multiplica(BigDecimal precio, Integer cantidad) {
        BigDecimal cantidadDecimal = new BigDecimal(cantidad);
        return precio.multiply(cantidadDecimal);
    }

    public Date sumaDiasHabiles(Date fechaAnterior, int diasASumar) {
        Date fechaExtendida;
        Calendar cal = Calendar.getInstance();
//        cal.setTime(fechaAnterior);
//        cal.add(Calendar.DATE, 5);
        fechaExtendida = cal.getTime();
        for (int c = 1; c <= diasASumar; c++) {
            cal.setTime(fechaAnterior);
            cal.add(Calendar.DATE, diasASumar);
            fechaExtendida = cal.getTime();
            if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
                diasASumar = diasASumar + 1;
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
                diasASumar = diasASumar + 1;
            }
        }
        return fechaExtendida;
    }

    public Integer redondearNumero(Double coberturaDias) {
        String[] parts = coberturaDias.toString().split("\\.");
        int decimales = parts[1].length() == 1 ? Integer.parseInt(parts[1] + "0") : Integer.parseInt(parts[1]);
        Integer value = 0;
        if (decimales > 60) {
            value = Integer.parseInt(parts[0]) + 1;
        } else {
            value = Integer.parseInt(parts[0]);
        }
        return value;
    }
    
    public Date sumarRestarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de dÃ­as a aÃ±adir, o restar en caso de dÃ­as<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos dÃ­as aÃ±adidos

    }
    
    public String getNameByMonth(int numberMonth) {
        String month = "";
        switch (numberMonth) {
            case 1:
                month = "ENERO";
                break;
            case 2:
                month = "FEBRERO";
                break;
            case 3:
                month = "MARZO";
                break;
            case 4:
                month = "ABRIL";
                break;
            case 5:
                month = "MAYO";
                break;
            case 6:
                month = "JUNIO";
                break;
            case 7:
                month = "JULIO";
                break;
            case 8:
                month = "AGOSTO";
                break;
            case 9:
                month = "SEPTIEMBRE";
                break;
            case 10:
                month = "OCTUBRE";
                break;
            case 11:
                month = "NOVIEMBRE";
                break;
            case 12:
                month = "DICIEMBRE";
                break;
        }
        return month;
    }
    
    public Date sumarRestarMesFecha(Date fecha, int meses) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MONTH, meses);  // numero de dÃ­as a aÃ±adir, o restar en caso de dÃ­as<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos dÃ­as aÃ±adidos

    }
    
}
