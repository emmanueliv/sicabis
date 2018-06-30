/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.ClavesPorCodigoBarrasHistoricoService;
import com.issste.sicabis.ejb.modelo.ClavesPorCodigoBarrasHistorico;
import com.issste.sicabis.ejb.service.silodisa.modelo.ClavesPorCodigoBarras;
import com.issste.sicabis.ejb.service.silodisa.service.ClavesPorCodigoBarrasService;
import com.issste.sicabis.ejb.utils.ConexionWebServiceUtil;
import com.issste.sicabis.ejb.utils.SSLUtil;
import java.io.IOException;
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
import org.xml.sax.SAXException;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
public class ClavesPorCodigoBarrasController {

    @EJB
    private ClavesPorCodigoBarrasService clavesPorCodigoBarrasService;

    @EJB
    private ClavesPorCodigoBarrasHistoricoService clavesPorCodigoBarrasHistoricoService;

    private ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();
    private SSLUtil sslUtil = new SSLUtil();

    public void obtenerClavesPorCodigoBarras() throws MalformedURLException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        sslUtil.validarCertificado();
        String url = cwsu.clavesPorCodigoDeBarras;
        URL iurl = new URL(url);
        Date fecha_ingreso = new Date();
        HttpURLConnection connection = cwsu.conectar(url);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try {
                boolean eliminar = clavesPorCodigoBarrasService.eliminarExistencias();
                DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                domFactory.setNamespaceAware(true);
                DocumentBuilder builder = domFactory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(connection.getInputStream()));
                System.out.println("doc.getDocumentElement().getNodeName();" + doc.getElementsByTagName("CLAVE"));
                NodeList dataSet = doc.getElementsByTagName("Table");
                for (int temp = 0; temp < dataSet.getLength(); temp++) {
                    Node unContacto = dataSet.item(temp);
                    if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                        Element elemento = (Element) unContacto;
                        ClavesPorCodigoBarras cpcb = new ClavesPorCodigoBarras();
                        ClavesPorCodigoBarrasHistorico cpcbh = new ClavesPorCodigoBarrasHistorico();

                        if (elemento.getElementsByTagName("CLAVE").getLength() > 0) {
                            cpcb.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("PARTIDA_PRESUPUESTAL").getLength() > 0) {
                            cpcb.setPartidaPresupuestal(elemento.getElementsByTagName("PARTIDA_PRESUPUESTAL").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("CROSS_REFERENCE").getLength() > 0) {
                            cpcb.setCrossReference(elemento.getElementsByTagName("CROSS_REFERENCE").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("NOMBRE").getLength() > 0) {
                            cpcb.setNombre(elemento.getElementsByTagName("NOMBRE").item(0).getTextContent());
                        }
                        cpcb.setFechaIngreso(fecha_ingreso);
                        boolean guardar = clavesPorCodigoBarrasService.guardar(cpcb);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
