/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.AlertasOperativasHistoricoService;
import com.issste.sicabis.ejb.modelo.AlertasOperativasHistorico;
import com.issste.sicabis.ejb.service.silodisa.modelo.AlertasOperativas;
import com.issste.sicabis.ejb.service.silodisa.service.AlertasOperativasService;
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
public class AlertasOperativasController {

    @EJB
    private AlertasOperativasService alertasOperativasService;

    @EJB
    private AlertasOperativasHistoricoService alertasOperativasHistoricoService;

    private ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();
    private SSLUtil sslUtil = new SSLUtil();

    public boolean obtenerTodasAlertasOperativas(Integer opcion) {
        try {
            this.obtenerAlertasOperativas(cwsu.alertasOperativas1, opcion);
            this.obtenerAlertasOperativas(cwsu.alertasOperativas2, opcion);
            this.obtenerAlertasOperativas(cwsu.alertasOperativas3, opcion);
            this.obtenerAlertasOperativas(cwsu.alertasOperativas4, opcion);
            this.obtenerAlertasOperativas(cwsu.alertasOperativas5, opcion);
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
            return false;
        }
        return true;
    }

    public void obtenerAlertasOperativas(String url, Integer opcion) throws MalformedURLException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        sslUtil.validarCertificado();
        URL iurl = new URL(url);
        Date fecha_ingreso = new Date();
        HttpURLConnection connection = cwsu.conectar(url);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try {
                boolean eliminar = alertasOperativasService.eliminarExistencias();
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
                        AlertasOperativasHistorico aoh = new AlertasOperativasHistorico();
                        if (opcion == 1 || opcion == 0) {
                            if (elemento.getElementsByTagName("CLA_DELEGACION").getLength() > 0) {
                                ao.setClaDelegacion(elemento.getElementsByTagName("CLA_DELEGACION").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("CLAVE").getLength() > 0) {
                                ao.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DESCRIPCION_CLAVE").getLength() > 0) {
                                ao.setClaveUml(elemento.getElementsByTagName("CLAVE_UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("UMU").getLength() > 0) {
                                ao.setDescripcionDelegacion(elemento.getElementsByTagName("DESCRIPCION_CLAVE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("EXTRAORDINARIOS_CONFIRMADO").getLength() > 0) {
                                ao.setExtraordinariosConfirmado(Integer.parseInt(elemento.getElementsByTagName("EXTRAORDINARIOS_CONFIRMADO").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("EXTRAORDINARIOS_PROCESO").getLength() > 0) {
                                ao.setExtraordinariosProceso(Integer.parseInt(elemento.getElementsByTagName("EXTRAORDINARIOS_PROCESO").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("EXTRAORDINARIOS_TRANSITO").getLength() > 0) {
                                ao.setExtraordinariosTransito(Integer.parseInt(elemento.getElementsByTagName("EXTRAORDINARIOS_TRANSITO").item(0).getTextContent()));
                            }
                            ao.setFechaIngreso(fecha_ingreso);
                            if (elemento.getElementsByTagName("NOMBRE_DELEGACION").getLength() > 0) {
                                ao.setNombreDelegacion(elemento.getElementsByTagName("NOMBRE_DELEGACION").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("NOMBRE_UMU").getLength() > 0) {
                                ao.setNombreUmu(elemento.getElementsByTagName("NOMBRE_UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("NUMERO_UMU").getLength() > 0) {
                                ao.setNumeroUmu(elemento.getElementsByTagName("NUMERO_UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("ORDINARIOS_CONFIRMADO").getLength() > 0) {
                                ao.setOrdinariosConfirmados(Integer.parseInt(elemento.getElementsByTagName("ORDINARIOS_CONFIRMADO").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("ORDINARIOS_PROCESO").getLength() > 0) {
                                ao.setOrdinariosProceso(Integer.parseInt(elemento.getElementsByTagName("ORDINARIOS_PROCESO").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("ORDINARIOS_TRANSITO").getLength() > 0) {
                                ao.setOrdinariosTransito(Integer.parseInt(elemento.getElementsByTagName("ORDINARIOS_TRANSITO").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("PERIODO_DPN").getLength() > 0) {
                                ao.setPeriodoDpn(elemento.getElementsByTagName("PERIODO_DPN").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("PIEZAS_DPN").getLength() > 0) {
                                ao.setPiezasDpn(Integer.parseInt(elemento.getElementsByTagName("PIEZAS_DPN").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("TOTAL").getLength() > 0) {
                                ao.setTotal(Integer.parseInt(elemento.getElementsByTagName("TOTAL").item(0).getTextContent()));
                            }
                            boolean guardar = alertasOperativasService.guardar(ao);
                        }
                        if (opcion == 1) {
                            AlertasOperativasHistorico alerta = llenaObjetoHistorico(ao);
                            alertasOperativasHistoricoService.guardar(alerta);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public AlertasOperativasHistorico llenaObjetoHistorico(AlertasOperativas ao) {
        AlertasOperativasHistorico a = new AlertasOperativasHistorico();
        a.setClaDelegacion(ao.getClaDelegacion());
        a.setClave(ao.getClave());
        a.setClaveUml(ao.getClaveUml());
        a.setDescripcionDelegacion(ao.getDescripcionDelegacion());
        a.setExtraordinariosConfirmado(ao.getExtraordinariosConfirmado());
        a.setExtraordinariosProceso(ao.getExtraordinariosProceso());
        a.setExtraordinariosTransito(ao.getExtraordinariosTransito());
        a.setFechaIngreso(ao.getFechaIngreso());
        a.setNombreDelegacion(ao.getNombreDelegacion());
        a.setNombreUmu(ao.getNombreUmu());
        a.setNumeroUmu(ao.getNumeroUmu());
        a.setOrdinariosConfirmados(ao.getOrdinariosConfirmados());
        a.setOrdinariosProceso(ao.getOrdinariosProceso());
        a.setOrdinariosTransito(ao.getOrdinariosTransito());
        a.setPeriodoDpn(ao.getPeriodoDpn());
        a.setPiezasDpn(ao.getPiezasDpn());
        a.setTotal(ao.getTotal());
        return a;
    }
}
