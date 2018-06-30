/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.utils;

import com.issste.sicabis.ejb.modelo.ConfiguraDpn;
import com.issste.sicabis.ejb.modelo.Dpn;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utilidades {

    public static final String PATHFILES = "../tmpFiles/";

    /**
     * Obtiene el parametro enviado por f:param o url ../pagina/?variable=5
     *
     * @param param nombre del parametro
     * @return Object valor del parametro
     */
    public Object geParameter(String param) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.getParameter(param);
    }

    /**
     * Coloca una variable en sesion
     *
     * @param param nombre de la variable
     * @param valor valor de la variable
     */
    public void setSessionMapValue(String param, Object valor) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map session = context.getExternalContext().getSessionMap();
        session.put(param, valor);
    }

    /**
     * Obtiene el valor de una variable en sesion
     *
     * @param param nombre de la variable
     * @return Object valor de la variable
     */
    public Object geSessionMapValue(String param) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map session = context.getExternalContext().getSessionMap();
        return session.get(param);
    }

    /**
     * Obtiene el valor de una variable en sesion
     *
     * @param param nombre de la variable
     * @return Object valor de la variable
     */
    public Object getSessionAtributte(String param) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpSession session = request.getSession(false);
        return session.getAttribute(param);
    }

    /**
     * Ingresa el valor de una variable en context
     *
     * @param param nombre de la variable
     * @param object valor de la variable
     */
    public void setContextAtributte(String param, Object object) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute(param, object);
    }

    /**
     * Obtiene el valor de una variable en context
     *
     * @param param nombre de la variable
     */
    public Object getContextAtributte(String param) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        ServletContext servletContext = request.getServletContext();
        return servletContext.getAttribute(param);
    }

    /**
     * Destruye la variable en sesion
     *
     * @param param nombre de la variable
     */
    public void destroySessionMap(String param) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map session = context.getExternalContext().getSessionMap();
        session.remove(param);
    }

    /**
     * Destruye la variable en contexto
     *
     * @param param nombre de la variable
     */
    public void destroyContextMap(String param) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        ServletContext servletContext = request.getServletContext();
        servletContext.removeAttribute(param);
    }

    public String getTodayDateString() {
        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        return anio + "_" + (mes + 1) + "_" + dia;
    }

    public int getYear() {
        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        return anio;
    }

    public String getYear2Digit() {
        Date fecha = new Date();
        String anio = new SimpleDateFormat("yy").format(fecha);
        return anio;
    }

    public int getMonth(Date fecha) {
        int mes;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(fecha);
        mes = calendar.get(Calendar.MONTH);
        return mes + 1;
    }
    
    public int getYear(Date fecha) {
        int anio;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(fecha);
        anio = calendar.get(Calendar.YEAR);
        return anio;
    }

    public Date sumarRestarDiasFecha(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de dÃ­as a aÃ±adir, o restar en caso de dÃ­as<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos dÃ­as aÃ±adidos

    }

    public Date getFecha(int dia, int mes, int anio) {
        if (mes > 12) {
            mes = 1;
            anio++;
        }
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        StringBuffer strFecha = new StringBuffer("");
        String c = "-";
        if (dia < 10) {
            strFecha.append("0");
        }
        strFecha.append(dia);
        strFecha.append(c);
        if (mes < 10) {
            strFecha.append("0");
        }
        strFecha.append(mes);
        strFecha.append(c);
        strFecha.append(anio);
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha.toString());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return fecha;
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

    public static Date getZeroTimeDate(Date fecha) {
        Date res = fecha;
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        res = calendar.getTime();

        return res;
    }

    public boolean validaCorreo(String correo) {
        String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    public BigDecimal obtieneDescuento2(BigDecimal precio, Integer descuento) {
        BigDecimal porc = new BigDecimal(descuento);
        porc = porc.divide(new BigDecimal(100));
        BigDecimal importe = precio.subtract(precio.multiply(porc));
        return importe;
    }

    public BigDecimal obtieneDescuento(BigDecimal precio, BigDecimal descuento) {
        BigDecimal porc = BigDecimal.ZERO;
        porc = descuento.divide(new BigDecimal(100));
        BigDecimal importe = precio.subtract(precio.multiply(porc));
        return importe;
    }

    public static Integer calculaDiferenciaMeses(Date fechaInicial, Date fechaActual) {
        Long lFechaInicial = fechaInicial.getTime();
        Long lFechaActual = fechaActual.getTime();
        System.out.println("fi" + fechaInicial);
        System.out.println("fa" + fechaActual);
        Long ldiferenciaDias = lFechaActual - lFechaInicial;
        Long diferenciaDias = ldiferenciaDias / (1000 * 60 * 60 * 24);
        Long diferenciaMeses = diferenciaDias / 30;
        System.out.println("diferencia " + diferenciaMeses + "diferencia meses " + diferenciaMeses.intValue());
        return diferenciaMeses.intValue();
    }

    public static boolean validacionFechasControlCalidad(Date fechaInicial) {
        int dias = (int) ((fechaInicial.getTime() - new Date().getTime()) / 86400000);
        if (dias > 270) {
            return true;
        }
        System.out.println("Hay " + dias + " dias de diferencia");
        return false;
    }
    
    public static boolean validacionFechaFabricacion(Date fechaInicial) {
        int dias = (int) ((new Date().getTime() - fechaInicial.getTime()) / 86400000);
        if (dias > 365) {
            return true;
        }
        System.out.println("Hay " + dias + " dias de diferencia");
        return false;
    }

    public static double redondear(double numero, int digitos) {
        double resultado;
        resultado = numero * Math.pow(10, digitos);
        resultado = Math.round(resultado);
        resultado = resultado / Math.pow(10, digitos);
        return resultado;
    }

    public Integer calculaPorciento(Integer porcentaje, Integer total) {
        Integer x = porcentaje * total;
        double x1 = (double) x / 100;
        x1 = this.redondear(x1, 0);
        Integer piezas = (int) x1;
        return piezas;
    }

    public BigDecimal multiplica(BigDecimal precio, Integer cantidad) {
        BigDecimal cantidadDecimal = new BigDecimal(cantidad);
        return precio.multiply(cantidadDecimal);
    }

    public BigDecimal obtienePorcDesc(BigDecimal precioRef, BigDecimal precioDesc) {
        BigDecimal porciento = new BigDecimal(0);
        if (precioDesc.intValue() == 0) {
            return porciento;
        }
        //porciento = precioDesc.divide(precioRef, 10, RoundingMode.HALF_EVEN);
        porciento = new BigDecimal(precioDesc.doubleValue() / precioRef.doubleValue());
        porciento = porciento.multiply(new BigDecimal(100));
        porciento = new BigDecimal(100).subtract(porciento);
        return porciento;
    }

    public String obtieneNumeroContrato(String maxNum) {
        String anio = this.getYear2Digit();
        String numeroContrato = "";
        switch (maxNum.length()) {
            case 1:
                numeroContrato = anio + "000" + maxNum;
                break;
            case 2:
                numeroContrato = anio + "00" + maxNum;
                break;
            case 3:
                numeroContrato = anio + "0" + maxNum;
                break;
            case 4:
                numeroContrato = anio + "" + maxNum;
                break;
        }
        System.out.println("numeroContrato-->" + numeroContrato);
        return numeroContrato;
    }

    public String obtieneNumeroConvenio(String maxNum) {
        String anio = this.getYear2Digit();
        String numeroContrato = "";
        switch (maxNum.length()) {
            case 1:
                numeroContrato = maxNum + "000" + anio;
                break;
            case 2:
                numeroContrato = maxNum + "00" + anio;
                break;
            case 3:
                numeroContrato = maxNum + "0" + anio;
                break;
            case 4:
                numeroContrato = maxNum + "" + anio;
                break;
        }
        System.out.println("numeroContrato-->" + numeroContrato);
        return numeroContrato;
    }

    public Date curp2date(String curp) {
        String anio = curp.substring(4, 6);
        String mes = curp.substring(6, 8);
        String dia = curp.substring(8, 10);
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("YY-MM-DD");
        String strFecha = anio + "-" + mes + "-" + dia;
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return (fecha);
    }

    public Integer sacarGeneroCurp(String curp) {
        String genero = curp.substring(10, 11).toUpperCase();
        Integer tipo = 0;
        if (genero.equals("M")) {
            tipo = 1;
        }
        return tipo;
    }

    public String formateaFecha(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yy");
        return formateador.format(fecha);
    }

    public void ejecutaAlerta() {
        ConfiguraDpn cd = new ConfiguraDpn();
        Date today = new Date();
        Date fechaInicio = null;
        Date fechaModificada = null;
        int diaAux = 0;
        boolean bandera = true;
        if (today.getDay() >= cd.getDiaInicio().intValue()) {
            if (today.getDay() == cd.getDiaInicio()) {
                //ejecuta proceso
                Dpn dpn = new Dpn();
            } else {
                diaAux = cd.getDiaInicio();
                while (bandera) {
                    fechaInicio = new Date(today.getYear(), today.getMonth() + 1, diaAux);
                    fechaModificada = this.sumarRestarDiasFecha(fechaInicio, cd.getNumDias());
                    if (today.getMonth() == fechaModificada.getMonth()) {
                        diaAux = fechaInicio.getDay();
                        if (today.compareTo(fechaModificada) == 0) {
                            //ejecuta proceso
                        } else if (today.before(fechaModificada)) {
                            bandera = false;
                        }
                    } else {
                        bandera = false;
                    }
                }
            }
        }
    }

    public String cambiaClaveUnidad(String claveUnidad) {
        String claveUnidadAux = claveUnidad.substring(0, 3) + "-" + claveUnidad.substring(3, 6) + "-" + claveUnidad.substring(6, 8);
        System.out.println("claveUnidadAux---->" + claveUnidadAux);
        return claveUnidadAux;
    }

    public String generacionContraseña() {
        int Longitud = 10;
        int NumAleatorio1 = 0;
        int NumAleatorio2 = 0;
        String contraseña = "";
        for (int i = 1; i <= Longitud; i++) {
            NumAleatorio1 = 32 + (int) (Math.random() * 92);
            NumAleatorio2 = 47 + (int) (Math.random() * 1872);
            return contraseña = "tE" + NumAleatorio1 + "mpU" + NumAleatorio2;
        }
        return null;
    }

    public Date sumarRestarMesFecha(Date fecha, int meses) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MONTH, meses);  // numero de dÃ­as a aÃ±adir, o restar en caso de dÃ­as<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos dÃ­as aÃ±adidos

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

    public BigDecimal agregarDecimales(BigDecimal numero) {
        String name = numero.toString();
        if (!name.contains(".")) {
            name = name + ".00";
        }
        return new BigDecimal(name);
    }

}
