//################################################################################
//      ## Fecha de creación: 18/12/15
//      ## Fecha de última modificación: 18/12/15
//      ## Responsable: Emmanuel De la Isla Vértiz
//      ## Módulos asociados: Mensajes
//      ## Id Tickets asociados al cambio: C-R-012150
//################################################################################
package com.issste.sicabis.utils;

import java.util.TimeZone;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensajes {
    private TimeZone time_zone = java.util.TimeZone.getDefault();
    private String locale = "ES";
    private String selecciona_opcion = "--Selecciona una opción--";
    private String todos = "--Todos--";
    private String todas = "--Todas--";
    private String ninguno = "--Ninguno--";
    public String datos_guardados = "Datos guardados correctamente";
    public String datos_eliminados = "Datos eliminados correctamente";
    public String datos_actualizados = "Datos actualizados correctamente";
    public String confirma_eliminar = "En verdad deseas eliminar los datos";
    public String error_guardar = "Error al guardar";
    public String error_modificar = "Error al modificar";
    public String error_borrar = "Error al borrar";
    public String errorarchivo = "Debes seleccionar un archivo para subir";
    public String archivo_bien = "Los archivos guardaron correctamente";
    public String archivo_error = "Los archivos no se guardaron correctamente";
    public String tipoDoc_select = "Debes seleccionar un tipo de documento";
    private String estilobien = "mensajebien";
    private String estilomal = "mensajemal";
    private String estiloadvertencia = "mensajeadvertencia";
    private String estilomensaje;
    private String si = " Si ";
    private String no = " No ";
    private String aceptar = " Aceptar ";
    private String cancelar = " Cancelar ";
    public final int idadmin = 1;
    public final int idevaluador = 2;
    public final int idcapturista = 3;
    public String existe = "El registro ya fue capturado";
    public final String mensajeTablaVacia = "No hay registros";
        

// <editor-fold defaultstate="collapsed" desc="Métodos">
    /**
     *
     * @param men
     */
    public void mensaje(String men) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        if (men != null) {
            if (men.equals("OK")) {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                men = datos_guardados;
                estilomensaje = estilobien;
            } else if (men.equals("OKDEL")) {
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                men = datos_eliminados;
                estilomensaje = estilobien;
            } else {
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                men = excepcionesmysql(men);
                estilomensaje = estilomal;
            }
        }
        message.setSummary(men);
        context.addMessage("Mensaje:", message);

    }

    /**
     *
     * @param men
     * @return
     */
    public String excepcionesmysql(String men) {
        if (men.indexOf("Cannot delete or update a parent row") != -1) {
            men = "No puedes borrar este registro porque tiene datos asociados";
        }
        return men;
    }

    /**
     *
     * @param men
     * @param estilo
     */
    public void mensaje(String men, String estilo) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        if (estilo.equals("verde")) {
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            setEstilomensaje(estilobien);
        } else if (estilo.equals("amarillo")) {
            message.setSeverity(FacesMessage.SEVERITY_WARN);
            setEstilomensaje(estiloadvertencia);
        } else {
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            setEstilomensaje(estilomal);
        }
        message.setSummary(men);
        context.addMessage("Mensaje:", message);
    }
        
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Encapsulados">
    /**
     * @return the selecciona_opcion
     */
    public String getSelecciona_opcion() {
        return selecciona_opcion;
    }

    /**
     * @param selecciona_opcion the selecciona_opcion to set
     */
    public void setSelecciona_opcion(String selecciona_opcion) {
        this.selecciona_opcion = selecciona_opcion;
    }

    /**
     * @return the estilomensaje
     */
    public String getEstilomensaje() {
        return estilomensaje;
    }

    /**
     * @param estilomensaje the estilomensaje to set
     */
    public void setEstilomensaje(String estilomensaje) {
        this.estilomensaje = estilomensaje;
    }

    /**
     * @return the confirma_eliminar
     */
    public String getConfirma_eliminar() {
        return confirma_eliminar;
    }

    /**
     * @param confirma_eliminar the confirma_eliminar to set
     */
    public void setConfirma_eliminar(String confirma_eliminar) {
        this.confirma_eliminar = confirma_eliminar;
    }

    /**
     * @return the si
     */
    public String getSi() {
        return si;
    }

    /**
     * @param si the si to set
     */
    public void setSi(String si) {
        this.si = si;
    }

    /**
     * @return the no
     */
    public String getNo() {
        return no;
    }

    /**
     * @param no the no to set
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        //System.out.println("Locale: "+locale );
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return the time_zone
     */
    public TimeZone getTime_zone() {
        //System.out.println("Time sone: "+time_zone );
        return time_zone;
    }

    /**
     * @param time_zone the time_zone to set
     */
    public void setTime_zone(TimeZone time_zone) {
        this.time_zone = time_zone;
    }

    /**
     * @return the todos
     */
    public String getTodos() {
        return todos;
    }

    /**
     * @param todos the todos to set
     */
    public void setTodos(String todos) {
        this.todos = todos;
    }

    /**
     * @return the todas
     */
    public String getTodas() {
        return todas;
    }

    /**
     * @param todas the todas to set
     */
    public void setTodas(String todas) {
        this.todas = todas;
    }

    /**
     * @return the ninguno
     */
    public String getNinguno() {
        return ninguno;
    }

    /**
     * @param ninguno the ninguno to set
     */
    public void setNinguno(String ninguno) {
        this.ninguno = ninguno;
    }

    /**
     * @return the aceptar
     */
    public String getAceptar() {
        return aceptar;
    }

    /**
     * @param aceptar the aceptar to set
     */
    public void setAceptar(String aceptar) {
        this.aceptar = aceptar;
    }

    /**
     * @return the cancelar
     */
    public String getCancelar() {
        return cancelar;
    }

    /**
     * @param cancelar the cancelar to set
     */
    public void setCancelar(String cancelar) {
        this.cancelar = cancelar;
    }
// </editor-fold>
}