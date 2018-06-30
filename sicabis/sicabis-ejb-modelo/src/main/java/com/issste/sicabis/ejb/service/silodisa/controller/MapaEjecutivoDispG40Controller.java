package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.DelegacionesService;
import com.issste.sicabis.ejb.ln.MapaEjecutivoDispG40HistoricoService;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.Indicador;
import com.issste.sicabis.ejb.modelo.MapaEjecutivoDispG40Historico;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispG40;
import com.issste.sicabis.ejb.service.silodisa.service.MapaEjecutivoDispG40Service;
import com.issste.sicabis.ejb.utils.ConexionWebServiceUtil;
import com.issste.sicabis.ejb.utils.SSLUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Stateless
public class MapaEjecutivoDispG40Controller {

    @EJB
    private MapaEjecutivoDispG40Service mapaEjecutivoDispG40Service;

    @EJB
    private MapaEjecutivoDispG40HistoricoService mapaEjecutivoDispG40HistoricoService;

    @EJB
    private DelegacionesService delegacionesService;

    private final SSLUtil sslUtil = new SSLUtil();
    private final ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();

    public void obtenerMapaEjecutivoDispG40(Integer opcion) throws MalformedURLException, IOException {
        sslUtil.validarCertificado();
        String url = cwsu.mapaEjecutivoDisponibilidadG40;
        HttpURLConnection connection = cwsu.conectar(url);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try {
                boolean result = mapaEjecutivoDispG40Service.eliminarExistencias();
                DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                domFactory.setNamespaceAware(true);
                DocumentBuilder builder = domFactory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(connection.getInputStream()));
                NodeList dataSet = doc.getElementsByTagName("Table");
                mapaEjecutivoDispG40Service.eliminarExistencias();
                for (int temp = 0; temp < dataSet.getLength(); temp++) {
                    Node unContacto = dataSet.item(temp);
                    if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                        Element elemento = (Element) unContacto;
                        MapaEjecutivoDispG40 medg40 = new MapaEjecutivoDispG40();
                        MapaEjecutivoDispG40Historico medgh40 = new MapaEjecutivoDispG40Historico();
                        if (opcion == 1 || opcion == 0) {
                            if (elemento.getElementsByTagName("Estado").getLength() > 0) {
                                String nombreEstado = elemento.getElementsByTagName("Estado").item(0).getTextContent();
                                Delegaciones delegacion = null;
                                if (nombreEstado.equals("DISTRITO FEDERAL")) {
                                    delegacion = delegacionesService.obtenerDelegacionporNombre("CIUDAD DE MEXICO");
                                    medg40.setIdDelegacion(delegacion);
                                } else {
                                    delegacion = delegacionesService.obtenerDelegacionporNombre(nombreEstado);
                                    medg40.setIdDelegacion(delegacion);
                                }
                            }
                            if (elemento.getElementsByTagName("UMU").getLength() > 0) {
                                medg40.setUmu(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Nombre").getLength() > 0) {
                                medg40.setNombre(elemento.getElementsByTagName("Nombre").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Claves_Autorizadas").getLength() > 0) {
                                medg40.setClavesAutorizadas(Integer.parseInt(elemento.getElementsByTagName("Claves_Autorizadas").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("Claves_Disponibles").getLength() > 0) {
                                medg40.setClavesDisponibles(Integer.parseInt(elemento.getElementsByTagName("Claves_Disponibles").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("Disponibilidad").getLength() > 0) {
                                String porcentajeDisponibilidad = elemento.getElementsByTagName("Disponibilidad").item(0).getTextContent();
                                if (porcentajeDisponibilidad.contains("%")) {
                                    porcentajeDisponibilidad = porcentajeDisponibilidad.replace("%", "");
                                }
                                medg40.setDisponibilidad(new BigDecimal(porcentajeDisponibilidad));
                            }
                            if (elemento.getElementsByTagName("Fecha").getLength() > 0) {
                                String fecha = elemento.getElementsByTagName("Fecha").item(0).getTextContent();
                                fecha = fecha.trim();
                                int pos = fecha.lastIndexOf(" ");
                                String f = fecha.substring(0, pos);
                                f = f.replace("-", "/");
                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                medg40.setFecha(format.parse(f));
                            }
                            medg40.setActivo(1);
                            medg40.setFechaIngreso(new Date());
                            medg40.setIdIndicador(new Indicador(2));
                            mapaEjecutivoDispG40Service.guardar(medg40);
                        }  if (opcion == 1) {
                            medgh40.setIdDelegacion(medg40.getIdDelegacion().getIdDelegacion());
                            medgh40.setUmu(medg40.getUmu());
                            medgh40.setNombre(medg40.getNombre());
                            medgh40.setClavesAutorizadas(medg40.getClavesAutorizadas());
                            medgh40.setClavesDisponibles(medg40.getClavesDisponibles());
                            medgh40.setDisponibilidad(medg40.getDisponibilidad());
                            medgh40.setFecha(medg40.getFecha());
                            medgh40.setActivo(1);
                            medgh40.setFechaIngreso(new Date());
                            medgh40.setIdIndicador(medg40.getIdIndicador().getIdIndicador());
                            mapaEjecutivoDispG40HistoricoService.guardar(medgh40);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
