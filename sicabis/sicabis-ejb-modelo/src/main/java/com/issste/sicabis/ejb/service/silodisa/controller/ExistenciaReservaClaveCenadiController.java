/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.ExistenciaReservaClaveCenadiHistoricoService;
import com.issste.sicabis.ejb.modelo.ExistenciaReservaClaveCenadiHistorico;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaReservaClaveCenadi;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciaReservaClaveCenadiService;
import com.issste.sicabis.ejb.utils.ConexionWebServiceUtil;
import com.issste.sicabis.ejb.utils.SSLUtil;
import com.issste.sicabis.ejb.utils.Utilidades;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class ExistenciaReservaClaveCenadiController {

    @EJB
    private ExistenciaReservaClaveCenadiService existenciaReservaClaveCenadiService;

    @EJB
    private ExistenciaReservaClaveCenadiHistoricoService existenciaReservaClaveCenadiHistoricoService;

    private ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();
    private SSLUtil sslUtil = new SSLUtil();
    private Utilidades util = new Utilidades();

    public void obtenerExistenciaReservaClaveCenadi(Integer opcion) throws MalformedURLException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        sslUtil.validarCertificado();
        String url = cwsu.existenciaReservaClaveCenadi;
        URL iurl = new URL(url);
        Date fecha_ingreso = new Date();
        HttpURLConnection connection = cwsu.conectar(url);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try {
                boolean eliminar = existenciaReservaClaveCenadiService.eliminarExistencias();
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
                        ExistenciaReservaClaveCenadi ercc = new ExistenciaReservaClaveCenadi();
                        ExistenciaReservaClaveCenadiHistorico ercch = new ExistenciaReservaClaveCenadiHistorico();
                        if (opcion == 1 || opcion == 0) {
                            if (elemento.getElementsByTagName("CLAVE").getLength() > 0) {
                                ercc.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA_DT").getLength() > 0) {
                                ercc.setFechaDt(new Date(elemento.getElementsByTagName("FECHA_DT").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("FECHA_INVENTARIO").getLength() > 0) {
                                ercc.setFechaInventario(new Date(elemento.getElementsByTagName("FECHA_INVENTARIO").item(0).getTextContent()));
                            }
                            ercc.setFechaIngresoAux(fecha_ingreso);
                            if (elemento.getElementsByTagName("PARTIDA_PRESUPUESTAL").getLength() > 0) {
                                ercc.setPartidaPresupuestal(elemento.getElementsByTagName("PARTIDA_PRESUPUESTAL").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DISPONIBLE_DE_RESERVA").getLength() > 0) {
                                ercc.setDisponibleDeReserva(Integer.parseInt(elemento.getElementsByTagName("DISPONIBLE_DE_RESERVA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("NOMBRE").getLength() > 0) {
                                ercc.setNombre(elemento.getElementsByTagName("NOMBRE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DPN").getLength() > 0) {
                                ercc.setDpn(Integer.parseInt(elemento.getElementsByTagName("DPN").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("EXISTENCIA").getLength() > 0) {
                                ercc.setExistencia(Integer.parseInt(elemento.getElementsByTagName("EXISTENCIA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("TIPO").getLength() > 0) {
                                ercc.setTipo(elemento.getElementsByTagName("TIPO").item(0).getTextContent());
                            }
                            //Integer disponible = ercc.getDisponibleDeReserva() != null ? ercc.getDisponibleDeReserva() : 0;
                            Integer existencia = ercc.getExistencia() != null ? ercc.getExistencia() : 0;
                            Integer dpn = ercc.getDpn() != null ? ercc.getDpn() : 0;
                            Integer cobertura = (existencia) * 30;
                            BigDecimal coberturaDias = new BigDecimal(0);
                            if (dpn != 0 && cobertura != 0) {
                                coberturaDias = new BigDecimal(cobertura).divide(new BigDecimal(dpn), 2, RoundingMode.HALF_UP);
                            }
                            ercc.setCoberturaDias(util.redondearNumero(coberturaDias.doubleValue()));
                            boolean guardar = existenciaReservaClaveCenadiService.guardar(ercc);
                        } if (opcion == 1) {
                            //Historico Existencia
                            if (elemento.getElementsByTagName("CLAVE").getLength() > 0) {
                                ercch.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA_DT").getLength() > 0) {
                                ercch.setFechaDt(new Date(elemento.getElementsByTagName("FECHA_DT").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("FECHA_INVENTARIO").getLength() > 0) {
                                ercch.setFechaInventario(new Date(elemento.getElementsByTagName("FECHA_INVENTARIO").item(0).getTextContent()));
                            }
                            ercch.setFechaIngresoAux(fecha_ingreso);
                            if (elemento.getElementsByTagName("PARTIDA_PRESUPUESTAL").getLength() > 0) {
                                ercch.setPartidaPresupuestal(elemento.getElementsByTagName("PARTIDA_PRESUPUESTAL").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DISPONIBLE_DE_RESERVA").getLength() > 0) {
                                ercch.setDisponibleDeReserva(Integer.parseInt(elemento.getElementsByTagName("DISPONIBLE_DE_RESERVA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("NOMBRE").getLength() > 0) {
                                ercch.setNombre(elemento.getElementsByTagName("NOMBRE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DPN").getLength() > 0) {
                                ercch.setDpn(Integer.parseInt(elemento.getElementsByTagName("DPN").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("EXISTENCIA").getLength() > 0) {
                                ercch.setExistencia(Integer.parseInt(elemento.getElementsByTagName("EXISTENCIA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("TIPO").getLength() > 0) {
                                ercch.setTipo(elemento.getElementsByTagName("TIPO").item(0).getTextContent());
                            }
                            ercch.setCoberturaDias(ercc.getCoberturaDias());
                            boolean guadarHistorico = existenciaReservaClaveCenadiHistoricoService.guardar(ercch);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
