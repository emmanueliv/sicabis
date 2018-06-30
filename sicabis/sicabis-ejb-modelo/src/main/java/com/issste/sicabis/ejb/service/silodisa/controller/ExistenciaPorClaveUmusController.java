/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.HistoricoExistenciaPorClaveUmusService;
import com.issste.sicabis.ejb.modelo.HistoricoExistenciaPorClaveUmus;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveUmus;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciaPorClaveUmusService;
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
import org.xml.sax.SAXException;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
public class ExistenciaPorClaveUmusController {

    @EJB
    private HistoricoExistenciaPorClaveUmusService historicoExistenciaPorClaveUmusService;

    @EJB
    private ExistenciaPorClaveUmusService existenciaPorClaveUmusService;

    private ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();
    private SSLUtil sslUtil = new SSLUtil();

    public boolean obtenerTodosExistenciaPorClaveUmus(Integer opcion) {
        try {
            existenciaPorClaveUmusService.eliminarExistenciasUmus();
            this.obtenerExistenciaPorClaveUmus(cwsu.existenciaPorClaveEnUMUs1, opcion);
            this.obtenerExistenciaPorClaveUmus(cwsu.existenciaPorClaveEnUMUs2, opcion);
            this.obtenerExistenciaPorClaveUmus(cwsu.existenciaPorClaveEnUMUs3, opcion);
            this.obtenerExistenciaPorClaveUmus(cwsu.existenciaPorClaveEnUMUs4, opcion);
            this.obtenerExistenciaPorClaveUmus(cwsu.existenciaPorClaveEnUMUs5, opcion);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public void obtenerExistenciaPorClaveUmus(String url, Integer opcion) throws MalformedURLException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        sslUtil.validarCertificado();
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
                NodeList dataSet = doc.getElementsByTagName("Table");
                System.out.println("dataSet--->" + dataSet.getLength());
                for (int temp = 0; temp < dataSet.getLength(); temp++) {
                    Node unContacto = dataSet.item(temp);
                    if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                        Element elemento = (Element) unContacto;
                        ExistenciaPorClaveUmus epcu = new ExistenciaPorClaveUmus();
                        if (opcion == 1 || opcion == 2) {
                            if (elemento.getElementsByTagName("UMU").getLength() > 0) {
                                epcu.setUmu(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("CLA_DELEGACION").getLength() > 0) {
                                epcu.setClaDelegacion(elemento.getElementsByTagName("CLA_DELEGACION").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("CLAVE").getLength() > 0) {
                                epcu.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DESC_UMU").getLength() > 0) {
                                epcu.setDescUmu(elemento.getElementsByTagName("DESC_UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("EXISTENCIA").getLength() > 0) {
                                epcu.setExistencia(elemento.getElementsByTagName("EXISTENCIA").item(0).getTextContent());
                            }
//                            if (elemento.getElementsByTagName("FECHA_CADUCIDAD").getLength() > 0) {
//                                epcu.setFechaCaducidad(new Date(elemento.getElementsByTagName("FECHA_CADUCIDAD").item(0).getTextContent()));
//                            }
                            if (elemento.getElementsByTagName("FECHA_INVENTARIO").getLength() > 0) {
                                epcu.setFechaInventario(new Date(elemento.getElementsByTagName("FECHA_INVENTARIO").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("IMPORTE").getLength() > 0) {
                                epcu.setImporte(new BigDecimal(elemento.getElementsByTagName("IMPORTE").item(0).getTextContent()));
                            }
//                            if (elemento.getElementsByTagName("LOTE").getLength() > 0) {
//                                epcu.setLote(elemento.getElementsByTagName("LOTE").item(0).getTextContent());
//                            }
                            if (elemento.getElementsByTagName("NOMBRE").getLength() > 0) {
                                epcu.setNombre(elemento.getElementsByTagName("NOMBRE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("NOMBRE_DELEGACION").getLength() > 0) {
                                epcu.setNombreDelegacion(elemento.getElementsByTagName("NOMBRE_DELEGACION").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("PRECIO_UNI").getLength() > 0) {
                                epcu.setPrecioUnitario(new BigDecimal(elemento.getElementsByTagName("PRECIO_UNI").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("TIPO").getLength() > 0) {
                                epcu.setTipo(elemento.getElementsByTagName("TIPO").item(0).getTextContent());
                            }
                            epcu.setFechaIngreso(fecha_ingreso);
                            boolean guardar = existenciaPorClaveUmusService.guardar(epcu);
                        } if (opcion == 1) {
                            HistoricoExistenciaPorClaveUmus hepcu = this.llenaObjetoHistorico(epcu);
                            Boolean guardar = historicoExistenciaPorClaveUmusService.guardar(hepcu);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("nunca--->");
        }
    }

    public HistoricoExistenciaPorClaveUmus llenaObjetoHistorico(ExistenciaPorClaveUmus epcu) {
        HistoricoExistenciaPorClaveUmus hepcu = new HistoricoExistenciaPorClaveUmus();
        hepcu.setClaDelegacion(epcu.getClaDelegacion());
        hepcu.setClave(epcu.getClave());
        hepcu.setDescUmu(epcu.getDescUmu());
        hepcu.setExistencia(epcu.getExistencia());
        hepcu.setFechaCaducidad(epcu.getFechaCaducidad());
        hepcu.setFechaIngreso(epcu.getFechaIngreso());
        hepcu.setFechaInventario(epcu.getFechaInventario());
        hepcu.setImporte(epcu.getImporte());
        hepcu.setLote(epcu.getLote());
        hepcu.setNombre(epcu.getNombre());
        hepcu.setNombreDelegacion(epcu.getNombreDelegacion());
        hepcu.setPrecioUnitario(epcu.getPrecioUnitario());
        hepcu.setTipo(epcu.getTipo());
        hepcu.setUmu(epcu.getUmu());
        return hepcu;
    }
}
