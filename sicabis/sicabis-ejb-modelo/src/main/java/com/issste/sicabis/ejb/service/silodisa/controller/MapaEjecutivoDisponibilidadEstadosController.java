/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.DelegacionesService;
import com.issste.sicabis.ejb.ln.PorcentajeDelegacionHistoricoService;
import com.issste.sicabis.ejb.ln.PorcentajeDelegacionService;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.Indicador;
import com.issste.sicabis.ejb.modelo.PorcentajeDelegacion;
import com.issste.sicabis.ejb.modelo.PorcentajeDelegacionHistorico;
import com.issste.sicabis.ejb.utils.ConexionWebServiceUtil;
import com.issste.sicabis.ejb.utils.SSLUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author 6JWBBG2
 */
@Stateless
public class MapaEjecutivoDisponibilidadEstadosController {

    @EJB
    private PorcentajeDelegacionHistoricoService porcentajeDelegacionHistoricoService;
    @EJB
    private DelegacionesService delegacionesService;
    @EJB
    private PorcentajeDelegacionService porcentajeDelegacionService;

    private final SSLUtil sslUtil = new SSLUtil();
    private final ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();

    public void obtenerPorcentajePorEstado(Integer opcion) throws MalformedURLException, IOException {

        sslUtil.validarCertificado();
        String url = cwsu.mapaEjecutivoDisponibilidadEstados;
        HttpURLConnection connection = cwsu.conectar(url);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try {
                boolean result = porcentajeDelegacionService.borrarContenidoPorcentajeDelegacion();
                System.out.println(" Se borro el contenido de la table porcentaje delegacion ? " + result);
                DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                domFactory.setNamespaceAware(true);
                DocumentBuilder builder = domFactory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(connection.getInputStream()));
                NodeList dataSet = doc.getElementsByTagName("Table");
                for (int temp = 0; temp < dataSet.getLength(); temp++) {
                    Node unContacto = dataSet.item(temp);
                    if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                        Element elemento = (Element) unContacto;
                        PorcentajeDelegacionHistorico porcentajeDelegacionHistorico = new PorcentajeDelegacionHistorico();
                        PorcentajeDelegacion porcentajeDelegacion = new PorcentajeDelegacion();
                        if (elemento.getElementsByTagName("Estado").getLength() > 0) {
                            String nombreEstado = elemento.getElementsByTagName("Estado").item(0).getTextContent();
                            Delegaciones delegacion;
                            if (nombreEstado.equals("DISTRITO FEDERAL")) {
                                delegacion = delegacionesService.obtenerDelegacionporNombre("CIUDAD DE MEXICO");
                                porcentajeDelegacion.setIdDelegacion(delegacion);
                                porcentajeDelegacionHistorico.setIdDelegacion(delegacion);
                            } else {
                                delegacion = delegacionesService.obtenerDelegacionporNombre(nombreEstado);
                                porcentajeDelegacion.setIdDelegacion(delegacion);
                                porcentajeDelegacionHistorico.setIdDelegacion(delegacion);
                            }
                        }
                        if (elemento.getElementsByTagName("Claves_DPN").getLength() > 0) {
                            porcentajeDelegacion.setClavesDPN(elemento.getElementsByTagName("Claves_DPN").item(0).getTextContent());
                            porcentajeDelegacionHistorico.setClavesDpn(elemento.getElementsByTagName("Claves_DPN").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("Claves_en_UMU").getLength() > 0) {
                            porcentajeDelegacion.setClavesEnUMU(elemento.getElementsByTagName("Claves_en_UMU").item(0).getTextContent());
                            porcentajeDelegacionHistorico.setClavesUmu(elemento.getElementsByTagName("Claves_en_UMU").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("Disponibilidad").getLength() > 0) {
                            String porcentajeDisponibilidad = elemento.getElementsByTagName("Disponibilidad").item(0).getTextContent();
                            if (porcentajeDisponibilidad.contains("%")) {
                                porcentajeDisponibilidad = porcentajeDisponibilidad.replace("%", "");
                            }
                            porcentajeDelegacion.setPorcentaje(new BigDecimal(porcentajeDisponibilidad));
                            porcentajeDelegacionHistorico.setPorcentaje(new BigDecimal(porcentajeDisponibilidad));
                        }
                        if (elemento.getElementsByTagName("Fecha").getLength() > 0) {
                            String fecha = elemento.getElementsByTagName("Fecha").item(0).getTextContent();
                            fecha = fecha.trim();
                            int pos = fecha.lastIndexOf(" ");
                            String f = fecha.substring(0, pos);
                            f = f.replace("-", "/");
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            porcentajeDelegacion.setFechaActualizacion(format.parse(f));
                            porcentajeDelegacionHistorico.setFechaActualizacion(format.parse(f));
                        }

                        porcentajeDelegacion.setIdIndicador(new Indicador(1));
                        porcentajeDelegacionHistorico.setIdIndicador(new Indicador(1));
                        if (result) {
                            porcentajeDelegacionService.actualizarPorcentajeDelegacion(porcentajeDelegacion);
                            if (opcion != 0) {
                                porcentajeDelegacionHistoricoService.guardar(porcentajeDelegacionHistorico);
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
