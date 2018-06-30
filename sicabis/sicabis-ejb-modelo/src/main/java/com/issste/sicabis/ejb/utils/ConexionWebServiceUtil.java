
package com.issste.sicabis.ejb.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author 5XD9BG2
 */
public class ConexionWebServiceUtil {
    
    public final String ruta_base = "https://security.abastoissste.com/wsSICABIS/wsSICABIS.asmx";
    public final String key = "API-KEY";
    public final String pass = "JezHPWOpPtc3yS1m3qUOW4";
//    public final String ruta_base = "https://security.abastoissste.com/TEST/wsSICABIS/wsSICABIS.asmx";
//    public final String key = "API-KEY";
//    public final String pass = "Jh6mTUWNdAgukWeE3wks";
    
    public String alertasOperativas1 = ruta_base + "/Alertas_Operativas1";
    public String alertasOperativas2 = ruta_base + "/Alertas_Operativas2";
    public String alertasOperativas3 = ruta_base + "/Alertas_Operativas3";
    public String alertasOperativas4 = ruta_base + "/Alertas_Operativas4";
    public String alertasOperativas5 = ruta_base + "/Alertas_Operativas5";
    public String catalogoInsumos = ruta_base + "/Cat%C3%A1logo_de_Insumos?";
    public String catalogoUnidadesMedicas = ruta_base + "/Cat%C3%A1logo_de_Unidades_M%C3%A9dicas?";
    public String clavesPorCodigoDeBarras = ruta_base + "/Claves_por_C%C3%B3digo_de_Barras";
    public String detalleSalidasHaciaUnidadMedicaGuiaDistribucion = ruta_base + "/Detalle_de_las_salidas_hacia_la_Unidad_M%C3%A9dica_Gu%C3%ADa_de_Distribuci%C3%B3n";
    public String entradasMYMCQCenadi = ruta_base + "/Entradas_MYMCQ_Cenadi";
    public String existenciaReservaClaveCenadi = ruta_base + "/Existencia_Reserva_por_Clave_en_Cenadi";
    public String existenciasPorClaveCenadi = ruta_base + "/Existencia_por_Clave_en_Cenadi";
    public String existenciaPorClaveEnUMUs1 = ruta_base + "/Existencia_por_Clave_en_UMUs1";
    public String existenciaPorClaveEnUMUs2 = ruta_base + "/Existencia_por_Clave_en_UMUs2";
    public String existenciaPorClaveEnUMUs3 = ruta_base + "/Existencia_por_Clave_en_UMUs3";
    public String existenciaPorClaveEnUMUs4 = ruta_base + "/Existencia_por_Clave_en_UMUs4";
    public String existenciaPorClaveEnUMUs5 = ruta_base + "/Existencia_por_Clave_en_UMUs5";
    public String existenciasUMUsProgramas = ruta_base + "/Existencias_UMUs_Programas";
    public String mapaEjecutivoDisponibilidadDelegaciones = ruta_base + "/Mapa_Ejecutivo_Disponibilidad_Delegaciones";
    public String mapaEjecutivoDisponibilidadEstados = ruta_base + "/Mapa_Ejecutivo_Disponibilidad_Estado";
    public String mapaEjecutivoDisponibilidadG40 = ruta_base + "/Mapa_Ejecutivo_Disponibilidad_G40";
    public String remisionesElectronicasEntregasEnLasUnidadesMedicas = ruta_base + "/Remisiones_electr%C3%B3nicas_de_entregas_en_las_Unidades_M%C3%A9dicas";
    public String salidasCENADIHaciaUnidadMedicaGuiaDistribucion = ruta_base + "/Salidas_del_CENADI_hac%C3%ADa_Unidad_M%C3%A9dica_Gu%C3%ADa_de_Distribuci%C3%B3n";
    public String seguimientoSalidasHaciaLaUnidadMedicaInterno = ruta_base + "/Seguimiento_de_las_salidas_hacia_la_Unidad_M%C3%A9dica_Interno";
    public String seguimientoSalidasHaciaLaUnidadMedicaTransito = ruta_base + "/Seguimiento_de_las_salidas_hacia_la_Unidad_M%C3%A9dica_Tr%C3%A1nsito";
    
    public HttpURLConnection conectar(String url) throws MalformedURLException, IOException {
        URL iurl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) iurl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "text / xml; charset = UTF-8");
        connection.setRequestProperty("Content-Length", "" + Integer.toString(url.getBytes().length));
        connection.setRequestProperty("Content-Language", "en-US");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.addRequestProperty(key, pass);
        System.out.println("wr" + connection);
        return connection;
    }
}
