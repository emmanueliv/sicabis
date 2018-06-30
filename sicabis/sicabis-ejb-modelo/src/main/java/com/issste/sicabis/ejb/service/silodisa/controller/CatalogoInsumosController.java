/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.CatInsumosHistoricoService;
import com.issste.sicabis.ejb.modelo.CatInsumosHistorico;
import com.issste.sicabis.ejb.service.silodisa.modelo.CatInsumos;
import com.issste.sicabis.ejb.service.silodisa.service.CatalogoInsumosService;
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
public class CatalogoInsumosController {

    @EJB
    private CatalogoInsumosService catalogoInsumosService;

    @EJB
    private CatInsumosHistoricoService catInsumosHistoricoService;

    private ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();
    private SSLUtil sslUtil = new SSLUtil();

    public void obtenerCatalogoInsumos() throws MalformedURLException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        sslUtil.validarCertificado();
        String url = cwsu.catalogoInsumos;
        Date fecha_ingreso = new Date();
        HttpURLConnection connection = cwsu.conectar("https://security.abastoissste.com/wsSICABIS/wsSICABIS.asmx/Cat%C3%A1logo_de_Insumos?");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try {
                boolean eliminar = catalogoInsumosService.eliminarExistencias();
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
                        CatInsumos ci = new CatInsumos();
                        if (elemento.getElementsByTagName("CLAVE").getLength() > 0) {
                            ci.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("DESCRIPTION").getLength() > 0) {
                            ci.setDescripcion(elemento.getElementsByTagName("DESCRIPTION").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("DESCRIPTION").getLength() > 0) {
                            ci.setDescripcionLarga(elemento.getElementsByTagName("LONG_DESCRIPTION").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("DESCRIPTION").getLength() > 0) {
                            ci.setPartidaPresupuestal(elemento.getElementsByTagName("PARTIDA_PRESUPUESTAL").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("PESO").getLength() > 0) {
                            ci.setPeso(elemento.getElementsByTagName("PESO").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("SUBINVENTARIO").getLength() > 0) {
                            ci.setSubinventario(elemento.getElementsByTagName("SUBINVENTARIO").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("TIPO_CARGA").getLength() > 0) {
                            ci.setTipoCarga(elemento.getElementsByTagName("TIPO_CARGA").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("TIPO_MEDICAMENTO").getLength() > 0) {
                            ci.setTipoMedicamento(elemento.getElementsByTagName("TIPO_MEDICAMENTO").item(0).getTextContent());
                        }
                        ci.setFechaIngreso(fecha_ingreso);
                       boolean guardar = catalogoInsumosService.guardar(ci);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
