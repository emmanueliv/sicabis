/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.ExistenciaUmusProgramasHistoricoService;
import com.issste.sicabis.ejb.modelo.ExistenciaUmusProgramasHistorico;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaUmusProgramas;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciasUMUsProgramasService;
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
public class ExistenciasUMUsProgramasController {

    @EJB
    private ExistenciasUMUsProgramasService existenciasUMUsProgramasService;

    @EJB
    private ExistenciaUmusProgramasHistoricoService existenciaUmusProgramasHistoricoService;

    private ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();
    private SSLUtil sslUtil = new SSLUtil();

    public void obtenerExistenciasUMUsProgramas(Integer opcion) throws MalformedURLException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        sslUtil.validarCertificado();
        String url = cwsu.existenciasUMUsProgramas;
        URL iurl = new URL(url);
        Date fecha_ingreso = new Date();
        HttpURLConnection connection = cwsu.conectar(url);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try {
                boolean eliminar = existenciasUMUsProgramasService.eliminarExistencias();
                DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                domFactory.setNamespaceAware(true);
                DocumentBuilder builder = domFactory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(connection.getInputStream()));
                System.out.println("doc.getDocumentElement().getNodeName();" + doc.getElementsByTagName("Clave"));
                NodeList dataSet = doc.getElementsByTagName("Table");
                for (int temp = 0; temp < dataSet.getLength(); temp++) {
                    Node unContacto = dataSet.item(temp);
                    if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                        Element elemento = (Element) unContacto;
                        ExistenciaUmusProgramas eup = new ExistenciaUmusProgramas();
                        ExistenciaUmusProgramasHistorico euph = new ExistenciaUmusProgramasHistorico();
                        if (opcion == 1 || opcion == 0) {
                            if (elemento.getElementsByTagName("Clave").getLength() > 0) {
                                eup.setClave(elemento.getElementsByTagName("Clave").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Delegacion").getLength() > 0) {
                                eup.setDelegacion(elemento.getElementsByTagName("Delegacion").item(0).getTextContent());
                            }
                            System.out.println("fecha " + elemento.getElementsByTagName("Fecha").item(0).toString());
                            if (elemento.getElementsByTagName("Fecha").getLength() > 0 && elemento.getElementsByTagName("Fecha").item(0) != null && !elemento.getElementsByTagName("Fecha").item(0).toString().equals("[Fecha: null]")) {
                                eup.setFecha(new Date(elemento.getElementsByTagName("Fecha").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("Fecha_Inventario").getLength() > 0 && !elemento.getElementsByTagName("Fecha_Inventario").item(0).toString().equals("[Fecha_Inventario: null]")) {
                                eup.setFechaInventario(new Date(elemento.getElementsByTagName("Fecha_Inventario").item(0).getTextContent()));
                            }
                            eup.setFechaIngreso(fecha_ingreso);
                            if (elemento.getElementsByTagName("Descripcion").getLength() > 0 && !elemento.getElementsByTagName("Descripcion").item(0).toString().equals("[Descripcion: null]")) {
                                eup.setDescripcion(elemento.getElementsByTagName("Descripcion").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Nombre_Programa").getLength() > 0 && !elemento.getElementsByTagName("Nombre_Programa").item(0).toString().equals("[Nombre_Programa: null]")) {
                                eup.setNombrePrograma(elemento.getElementsByTagName("Nombre_Programa").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Nombre_UMU").getLength() > 0) {
                                eup.setNombreUmu(elemento.getElementsByTagName("Nombre_UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Nombre_UMU").getLength() > 0) {
                                eup.setNumeroUmu(elemento.getElementsByTagName("Numero_UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Existencia").getLength() > 0) {
                                eup.setExistencia(Integer.parseInt(elemento.getElementsByTagName("Existencia").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("Tipo").getLength() > 0) {
                                eup.setTipo(elemento.getElementsByTagName("Tipo").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Clave_Unidad").getLength() > 0) {
                                eup.setClaveUnidad(elemento.getElementsByTagName("Clave_Unidad").item(0).getTextContent());
                            }
                            eup.setActivo(1);
                            boolean guardar = existenciasUMUsProgramasService.guardar(eup);
                        } if (opcion == 1) {
                            if (elemento.getElementsByTagName("Clave").getLength() > 0) {
                                euph.setClave(elemento.getElementsByTagName("Clave").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Delegacion").getLength() > 0) {
                                euph.setDelegacion(elemento.getElementsByTagName("Delegacion").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Fecha").getLength() > 0 && elemento.getElementsByTagName("Fecha").item(0) != null && !elemento.getElementsByTagName("Fecha").item(0).toString().equals("[Fecha: null]")) {
                                euph.setFecha(new Date(elemento.getElementsByTagName("Fecha").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("Fecha_Inventario").getLength() > 0 && !elemento.getElementsByTagName("Fecha_Inventario").item(0).toString().equals("[Fecha_Inventario: null]")) {
                                euph.setFechaInventario(new Date(elemento.getElementsByTagName("Fecha_Inventario").item(0).getTextContent()));
                            }
                            euph.setFechaIngresoAux(fecha_ingreso);
                            if (elemento.getElementsByTagName("Descripcion").getLength() > 0) {
                                euph.setDescripcion(elemento.getElementsByTagName("Descripcion").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Nombre_Programa").getLength() > 0) {
                                euph.setNombrePrograma(elemento.getElementsByTagName("Nombre_Programa").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Nombre_UMU").getLength() > 0) {
                                euph.setNombreUmu(elemento.getElementsByTagName("Nombre_UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Nombre_UMU").getLength() > 0) {
                                euph.setNumeroUmu(elemento.getElementsByTagName("Numero_UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Existencia").getLength() > 0) {
                                euph.setExistencia(Integer.parseInt(elemento.getElementsByTagName("Existencia").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("Tipo").getLength() > 0) {
                                euph.setTipo(elemento.getElementsByTagName("Tipo").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Clave_Unidad").getLength() > 0) {
                                euph.setClaveUnidad(elemento.getElementsByTagName("Clave_Unidad").item(0).getTextContent());
                            }
                            eup.setActivo(1);
                            euph.setActivo(1);
                            boolean guardarHistorico = existenciaUmusProgramasHistoricoService.guardar(euph);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
