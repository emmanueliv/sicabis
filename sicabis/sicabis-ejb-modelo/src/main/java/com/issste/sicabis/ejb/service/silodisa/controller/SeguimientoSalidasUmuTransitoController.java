/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.SeguimientoSalidasUmuTransitoHistoricoService;
import com.issste.sicabis.ejb.modelo.SeguimientoSalidasUmuTransitoHistorico;
import com.issste.sicabis.ejb.service.silodisa.modelo.SeguimientoSalidasUmuTransito;
import com.issste.sicabis.ejb.service.silodisa.service.SeguimientoSalidasUmuTransitoService;
import com.issste.sicabis.ejb.utils.ConexionWebServiceUtil;
import com.issste.sicabis.ejb.utils.SSLUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
public class SeguimientoSalidasUmuTransitoController {

    @EJB
    private SeguimientoSalidasUmuTransitoService seguimientoSalidasUmuTransitoService;

    @EJB
    private SeguimientoSalidasUmuTransitoHistoricoService seguimientoSalidasUmuTransitoHistoricoService;

    private ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();
    private SSLUtil sslUtil = new SSLUtil();

    public void obtenerSeguimientoSalidasUmuTransito(Integer opcion) throws MalformedURLException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException {
        sslUtil.validarCertificado();
        String url = cwsu.seguimientoSalidasHaciaLaUnidadMedicaTransito;
        URL iurl = new URL(url);
        Date fecha_ingreso = new Date();
        HttpURLConnection connection = cwsu.conectar(url);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try {
                DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                domFactory.setNamespaceAware(true);
                DocumentBuilder builder = domFactory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(connection.getInputStream()));
                System.out.println("doc.getDocumentElement().getNodeName();" + doc.getElementsByTagName("CLAVE"));
                NodeList dataSet = doc.getElementsByTagName("Table");
                seguimientoSalidasUmuTransitoService.eliminarExistencias();
                for (int temp = 0; temp < dataSet.getLength(); temp++) {
                    Node unContacto = dataSet.item(temp);
                    if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                        Element elemento = (Element) unContacto;
                        SeguimientoSalidasUmuTransito ssut = new SeguimientoSalidasUmuTransito();
                        SeguimientoSalidasUmuTransitoHistorico ssuth = new SeguimientoSalidasUmuTransitoHistorico();
                        if (opcion == 1 || opcion == 0) {
                            if (elemento.getElementsByTagName("CLAVE").getLength() > 0) {
                                ssut.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("CODIGOUMU").getLength() > 0) {
                                ssut.setCodigoumu(elemento.getElementsByTagName("CODIGOUMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DELEGACION").getLength() > 0) {
                                ssut.setDelegacion(elemento.getElementsByTagName("DELEGACION").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA").getLength() > 0) {
                                ssut.setFecha(new Date(elemento.getElementsByTagName("FECHA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("FECHA_CAMION").getLength() > 0) {
                                ssut.setFechaCamion(new Date(elemento.getElementsByTagName("FECHA_CAMION").item(0).getTextContent()));
                            }
                            ssut.setFechaIngreso(fecha_ingreso);
                            if (elemento.getElementsByTagName("FOLIOISSSTE").getLength() > 0) {
                                ssut.setFolioissste(Integer.parseInt(elemento.getElementsByTagName("FOLIOISSSTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("IMPORTE").getLength() > 0) {
                                ssut.setImporte(new BigDecimal(elemento.getElementsByTagName("IMPORTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("LOTE").getLength() > 0) {
                                ssut.setLote(elemento.getElementsByTagName("LOTE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("NOMBRE").getLength() > 0) {
                                ssut.setNombre(elemento.getElementsByTagName("NOMBRE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA_COMPROMETIDA").getLength() > 0) {
                                ssut.setFechaComprometida(new Date(elemento.getElementsByTagName("FECHA_COMPROMETIDA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("FECHA_REMISION").getLength() > 0) {
                                ssut.setFechaRemision(new Date(elemento.getElementsByTagName("FECHA_REMISION").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("PRECIO").getLength() > 0) {
                                ssut.setPrecio(elemento.getElementsByTagName("PRECIO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("PRESUPUESTAL").getLength() > 0) {
                                ssut.setPresupuestal(elemento.getElementsByTagName("PRESUPUESTAL").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("REMISION").getLength() > 0) {
                                ssut.setRemision(Integer.parseInt(elemento.getElementsByTagName("REMISION").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("SOLICITADA").getLength() > 0) {
                                ssut.setSolicitada(Integer.parseInt(elemento.getElementsByTagName("SOLICITADA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("TIPO").getLength() > 0) {
                                ssut.setTipo(elemento.getElementsByTagName("TIPO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("UMU").getLength() > 0) {
                                ssut.setUmu(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                            }
                            boolean guardarHistorico = seguimientoSalidasUmuTransitoService.guardar(ssut);
                        } if (opcion == 1) {
                            if (elemento.getElementsByTagName("CLAVE").getLength() > 0) {
                                ssuth.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("CODIGOUMU").getLength() > 0) {
                                ssuth.setCodigoumu(elemento.getElementsByTagName("CODIGOUMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DELEGACION").getLength() > 0) {
                                ssuth.setDelegacion(elemento.getElementsByTagName("DELEGACION").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA").getLength() > 0) {
                                ssuth.setFecha(new Date(elemento.getElementsByTagName("FECHA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("FECHA_CAMION").getLength() > 0) {
                                ssuth.setFechaCamion(new Date(elemento.getElementsByTagName("FECHA_CAMION").item(0).getTextContent()));
                            }
                            ssuth.setFechaIngreso(fecha_ingreso);
                            if (elemento.getElementsByTagName("FOLIOISSSTE").getLength() > 0) {
                                ssuth.setFolioissste(Integer.parseInt(elemento.getElementsByTagName("FOLIOISSSTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("IMPORTE").getLength() > 0) {
                                ssuth.setImporte(new BigDecimal(elemento.getElementsByTagName("IMPORTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("LOTE").getLength() > 0) {
                                ssuth.setLote(elemento.getElementsByTagName("LOTE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("NOMBRE").getLength() > 0) {
                                ssuth.setNombre(elemento.getElementsByTagName("NOMBRE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA_COMPROMETIDA").getLength() > 0) {
                                ssuth.setFechaComprometida(new Date(elemento.getElementsByTagName("FECHA_COMPROMETIDA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("FECHA_REMISION").getLength() > 0) {
                                ssuth.setFechaRemision(new Date(elemento.getElementsByTagName("FECHA_REMISION").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("PRECIO").getLength() > 0) {
                                ssuth.setPrecio(elemento.getElementsByTagName("PRECIO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("PRESUPUESTAL").getLength() > 0) {
                                ssuth.setPresupuestal(elemento.getElementsByTagName("PRESUPUESTAL").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("REMISION").getLength() > 0) {
                                ssuth.setRemision(Integer.parseInt(elemento.getElementsByTagName("REMISION").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("SOLICITADA").getLength() > 0) {
                                ssuth.setSolicitada(Integer.parseInt(elemento.getElementsByTagName("SOLICITADA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("TIPO").getLength() > 0) {
                                ssuth.setTipo(elemento.getElementsByTagName("TIPO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("UMU").getLength() > 0) {
                                ssuth.setUmu(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                            }

                            System.out.println(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                            boolean guardarHistorico = seguimientoSalidasUmuTransitoHistoricoService.guardar(ssuth);

                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
