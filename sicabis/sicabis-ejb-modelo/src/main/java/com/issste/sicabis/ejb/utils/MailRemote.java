//################################################################################
//      ## Fecha de creación: 18/12/15
//      ## Fecha de última modificación: 18/12/15
//      ## Responsable: Emmanuel De la Isla Vértiz
//      ## Módulos asociados: Interfaz Correo
//      ## Id Tickets asociados al cambio: C-R-012150
//################################################################################
package com.issste.sicabis.ejb.utils;

import java.io.File;

public interface MailRemote {
    
    /**
     * Crea un archivo de configuracion para el cliente de correo
     * @param conf Objeto Config con los datos requeridos
     * @throws Exception Error al escribir el archivo
     */
    void escribirConfiguracion(Config conf) throws Exception;
    void sendMail(String[]destinatarios,String asunto,String mensaje)throws Exception;
    void sendMailAlertas(String[]destinatarios,String asunto,String mensaje)throws Exception;
    void sendAttachMail(String[]destinatarios,String asunto,String mensaje,File attachment)throws Exception;
}
