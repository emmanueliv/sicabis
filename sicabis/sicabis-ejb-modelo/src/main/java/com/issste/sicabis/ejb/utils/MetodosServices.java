/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.utils;

import com.issste.sicabis.ejb.service.silodisa.modelo.AlertasOperativas;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveUmus;
import com.issste.sicabis.ejb.service.silodisa.service.AlertasOperativasService;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciaPorClaveUmusService;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
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
public class MetodosServices {

    private SSLUtil sslUtil = new SSLUtil();

    public void obtenerAlertasOperativas(String url, AlertasOperativasService alertasOperativasService) throws MalformedURLException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        sslUtil.validarCertificado();
        URL iurl = new URL(url);
        Date fecha_ingreso = new Date();
        HttpURLConnection connection = (HttpURLConnection) iurl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "text / xml; charset = UTF-8");
        connection.setRequestProperty("Content-Length", "" + Integer.toString(url.getBytes().length));
        connection.setRequestProperty("Content-Language", "en-US");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.addRequestProperty("API-KEY", "Jh6mTUWNdAgukWeE3wks");
        System.out.println("wr" + connection);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try {
                DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                domFactory.setNamespaceAware(true);
                DocumentBuilder builder = domFactory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(connection.getInputStream()));
                NodeList dataSet = doc.getElementsByTagName("Table");
                for (int temp = 0; temp < dataSet.getLength(); temp++) {
                    Node unContacto = dataSet.item(temp);
                    if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                        Element elemento = (Element) unContacto;
                        AlertasOperativas ao = new AlertasOperativas();
                        ao.setClaDelegacion(elemento.getElementsByTagName("CLA_DELEGACION").item(0).getTextContent());
                        ao.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                        ao.setClaveUml(elemento.getElementsByTagName("CLAVE_UMU").item(0).getTextContent());
                        ao.setDescripcionDelegacion(elemento.getElementsByTagName("DESCRIPCION_CLAVE").item(0).getTextContent());
                        ao.setExtraordinariosConfirmado(Integer.parseInt(elemento.getElementsByTagName("EXTRAORDINARIOS_CONFIRMADO").item(0).getTextContent()));
                        ao.setExtraordinariosProceso(Integer.parseInt(elemento.getElementsByTagName("EXTRAORDINARIOS_PROCESO").item(0).getTextContent()));
                        ao.setExtraordinariosTransito(Integer.parseInt(elemento.getElementsByTagName("EXTRAORDINARIOS_TRANSITO").item(0).getTextContent()));
                        ao.setFechaIngreso(fecha_ingreso);
                        ao.setNombreDelegacion(elemento.getElementsByTagName("NOMBRE_DELEGACION").item(0).getTextContent());
                        ao.setNombreUmu(elemento.getElementsByTagName("NOMBRE_UMU").item(0).getTextContent());
                        ao.setNumeroUmu(elemento.getElementsByTagName("NUMERO_UMU").item(0).getTextContent());
                        ao.setOrdinariosConfirmados(Integer.parseInt(elemento.getElementsByTagName("ORDINARIOS_CONFIRMADO").item(0).getTextContent()));
                        ao.setOrdinariosProceso(Integer.parseInt(elemento.getElementsByTagName("ORDINARIOS_PROCESO").item(0).getTextContent()));
                        ao.setOrdinariosTransito(Integer.parseInt(elemento.getElementsByTagName("ORDINARIOS_TRANSITO").item(0).getTextContent()));
                        ao.setPeriodoDpn(elemento.getElementsByTagName("PERIODO_DPN").item(0).getTextContent());
                        ao.setPiezasDpn(Integer.parseInt(elemento.getElementsByTagName("PIEZAS_DPN").item(0).getTextContent()));
                        ao.setTotal(Integer.parseInt(elemento.getElementsByTagName("TOTAL").item(0).getTextContent()));
                        boolean guardar = alertasOperativasService.guardar(ao);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void obtenerExistenciaPorClaveUmus(String url, ExistenciaPorClaveUmusService existenciaPorClaveUmusService) throws MalformedURLException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        sslUtil.validarCertificado();
        URL iurl = new URL(url);
        Date fecha_ingreso = new Date();
        HttpURLConnection connection = (HttpURLConnection) iurl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "text / xml; charset = UTF-8");
        connection.setRequestProperty("Content-Length", "" + Integer.toString(url.getBytes().length));
        connection.setRequestProperty("Content-Language", "en-US");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.addRequestProperty("API-KEY", "Jh6mTUWNdAgukWeE3wks");
        System.out.println("wr" + connection);
        connection.connect();
        int responseCode = connection.getResponseCode();
        System.out.println("responseCode-->"+responseCode);
        if (responseCode == 200) {
            try {
                DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                domFactory.setNamespaceAware(true);
                DocumentBuilder builder = domFactory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(connection.getInputStream()));
                NodeList dataSet = doc.getElementsByTagName("Table");
                System.out.println("dataSet--->"+dataSet.getLength());
                for (int temp = 0; temp < dataSet.getLength(); temp++) {
                    System.out.println("entre for----->");
                    Node unContacto = dataSet.item(temp);
                    if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println("entre if()--->");
                        Element elemento = (Element) unContacto;
                        ExistenciaPorClaveUmus epcu = new ExistenciaPorClaveUmus();
                        epcu.setUmu(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                        epcu.setClaDelegacion(elemento.getElementsByTagName("CLA_DELEGACION").item(0).getTextContent());
                        epcu.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                        epcu.setDescUmu(elemento.getElementsByTagName("DESC_UMU").item(0).getTextContent());
                        epcu.setExistencia(elemento.getElementsByTagName("EXISTENCIA").item(0).getTextContent());
                        epcu.setFechaCaducidad(new Date(elemento.getElementsByTagName("FECHA_CADUCIDAD").item(0).getTextContent()));
                        epcu.setFechaInventario(new Date(elemento.getElementsByTagName("FECHA_INVENTARIO").item(0).getTextContent()));
                        epcu.setImporte(new BigDecimal(elemento.getElementsByTagName("IMPORTE").item(0).getTextContent()));
                        epcu.setLote(elemento.getElementsByTagName("LOTE").item(0).getTextContent());
                        epcu.setNombre(elemento.getElementsByTagName("NOMBRE").item(0).getTextContent());
                        epcu.setNombreDelegacion(elemento.getElementsByTagName("NOMBRE_DELEGACION").item(0).getTextContent());
                        epcu.setPrecioUnitario(new BigDecimal(elemento.getElementsByTagName("PRECIO_UNI").item(0).getTextContent()));
                        epcu.setTipo(elemento.getElementsByTagName("TIPO").item(0).getTextContent());
                        epcu.setFechaIngreso(fecha_ingreso);
                        System.out.println("antes---->"+epcu);
                        boolean guardar = existenciaPorClaveUmusService.guardar(epcu);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else{
            System.out.println("nunca--->");
        }
        
    }
}
