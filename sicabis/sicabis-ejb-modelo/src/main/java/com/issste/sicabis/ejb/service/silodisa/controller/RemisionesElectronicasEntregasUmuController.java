/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.RemisionesElectronicasEntregasUmuHistoricoService;
import com.issste.sicabis.ejb.modelo.RemisionesElectronicasEntregasUmuHistorico;
import com.issste.sicabis.ejb.service.silodisa.modelo.RemisionesElectronicasEntregasUmu;
import com.issste.sicabis.ejb.service.silodisa.service.RemisionesElectronicasEntregasUmuService;
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
public class RemisionesElectronicasEntregasUmuController {

    @EJB
    private RemisionesElectronicasEntregasUmuService remisionesElectronicasEntregasUmuService;

    @EJB
    private RemisionesElectronicasEntregasUmuHistoricoService remisionesElectronicasEntregasUmuHistoricoService;

    private ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();
    private SSLUtil sslUtil = new SSLUtil();

    public void obtenerDetalleSalidasUmuGuiaDistribucion(Integer opcion) throws MalformedURLException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        sslUtil.validarCertificado();
        String url = cwsu.remisionesElectronicasEntregasEnLasUnidadesMedicas;
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
                for (int temp = 0; temp < dataSet.getLength(); temp++) {
                    Node unContacto = dataSet.item(temp);
                    if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                        Element elemento = (Element) unContacto;
                        RemisionesElectronicasEntregasUmu reeu = new RemisionesElectronicasEntregasUmu();
                        RemisionesElectronicasEntregasUmuHistorico reeuh = new RemisionesElectronicasEntregasUmuHistorico();
                        if (opcion == 1 || opcion == 0) {
                            if (elemento.getElementsByTagName("CLAVE").getLength() > 0) {
                                reeu.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("CODIGOUMU").getLength() > 0) {
                                reeu.setCodigoumu(elemento.getElementsByTagName("CODIGOUMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DELEGACION").getLength() > 0) {
                                reeu.setDelegacion(elemento.getElementsByTagName("DELEGACION").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DPN_MES").getLength() > 0) {
                                reeu.setDpnMes(elemento.getElementsByTagName("DPN_MES").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DPN_YEAR").getLength() > 0) {
                                reeu.setDpnYear(elemento.getElementsByTagName("DPN_YEAR").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("NOMBRE_UMU").getLength() > 0) {
                                reeu.setNombreUmu(elemento.getElementsByTagName("NOMBRE_UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA").getLength() > 0) {
                                reeu.setFecha(new Date(elemento.getElementsByTagName("FECHA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("DESCRIPCION_CLAVE").getLength() > 0) {
                                reeu.setDescripcionClave(elemento.getElementsByTagName("DESCRIPCION_CLAVE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA_CAMION").getLength() > 0) {
                                reeu.setFechaCamion(elemento.getElementsByTagName("FECHA_CAMION").item(0).getTextContent());
                            }
                            reeu.setFechaIngreso(fecha_ingreso);
                            if (elemento.getElementsByTagName("FECHACERT").getLength() > 0) {
                                reeu.setFechacert(elemento.getElementsByTagName("FECHACERT").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FOLIOISSSTE").getLength() > 0) {
                                reeu.setFolioissste(Integer.parseInt(elemento.getElementsByTagName("FOLIOISSSTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("IMPORTE").getLength() > 0) {
                                reeu.setImporte(new BigDecimal(elemento.getElementsByTagName("IMPORTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("LOTE").getLength() > 0) {
                                reeu.setLote(elemento.getElementsByTagName("LOTE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("NOMBRE").getLength() > 0) {
                                reeu.setNombre(elemento.getElementsByTagName("NOMBRE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA_PRERECIBO").getLength() > 0) {
                                reeu.setFechaPrerecibo(elemento.getElementsByTagName("FECHA_PRERECIBO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA_REMISION").getLength() > 0) {
                                reeu.setFechaRemision(elemento.getElementsByTagName("FECHA_REMISION").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("ENTREGADA").getLength() > 0) {
                                reeu.setEntregada(Integer.parseInt(elemento.getElementsByTagName("ENTREGADA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("PRECIO").getLength() > 0) {
                                reeu.setPrecio(new BigDecimal(elemento.getElementsByTagName("PRECIO").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("PRESUPUESTAL").getLength() > 0) {
                                reeu.setPresupuestal(elemento.getElementsByTagName("PRESUPUESTAL").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("REMISION").getLength() > 0) {
                                reeu.setRemision(Integer.parseInt(elemento.getElementsByTagName("REMISION").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("SOLICITADA").getLength() > 0) {
                                reeu.setSolicitada(Integer.parseInt(elemento.getElementsByTagName("SOLICITADA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("TIPO").getLength() > 0) {
                                reeu.setTipo(elemento.getElementsByTagName("TIPO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("TIPOPEDIDO").getLength() > 0) {
                                reeu.setTipopedido(elemento.getElementsByTagName("TIPOPEDIDO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("UMU").getLength() > 0) {
                                reeu.setUmu(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("TRANSACTION_DT").getLength() > 0) {
                                reeu.setTransactionDt(Integer.parseInt(elemento.getElementsByTagName("TRANSACTION_DT").item(0).getTextContent()));
                            }
                            System.out.println(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                            boolean guardar = remisionesElectronicasEntregasUmuService.guardar(reeu);
                        }  if (opcion == 1) {
                            if (elemento.getElementsByTagName("CLAVE").getLength() > 0) {
                                reeuh.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("CODIGOUMU").getLength() > 0) {
                                reeuh.setCodigoumu(elemento.getElementsByTagName("CODIGOUMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DELEGACION").getLength() > 0) {
                                reeuh.setDelegacion(elemento.getElementsByTagName("DELEGACION").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DPN_MES").getLength() > 0) {
                                reeuh.setDpnMes(elemento.getElementsByTagName("DPN_MES").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DPN_YEAR").getLength() > 0) {
                                reeuh.setDpnYear(elemento.getElementsByTagName("DPN_YEAR").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("NOMBRE_UMU").getLength() > 0) {
                                reeuh.setNombreUmu(elemento.getElementsByTagName("NOMBRE_UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA").getLength() > 0) {
                                reeuh.setFecha(new Date(elemento.getElementsByTagName("FECHA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("DESCRIPCION_CLAVE").getLength() > 0) {
                                reeuh.setDescripcionClave(elemento.getElementsByTagName("DESCRIPCION_CLAVE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA_CAMION").getLength() > 0) {
                                reeuh.setFechaCamion(elemento.getElementsByTagName("FECHA_CAMION").item(0).getTextContent());
                            }
                            reeuh.setFechaIngreso(fecha_ingreso);
                            if (elemento.getElementsByTagName("FECHACERT").getLength() > 0) {
                                reeuh.setFechacert(elemento.getElementsByTagName("FECHACERT").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FOLIOISSSTE").getLength() > 0) {
                                reeuh.setFolioissste(Integer.parseInt(elemento.getElementsByTagName("FOLIOISSSTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("IMPORTE").getLength() > 0) {
                                reeuh.setImporte(new BigDecimal(elemento.getElementsByTagName("IMPORTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("LOTE").getLength() > 0) {
                                reeuh.setLote(elemento.getElementsByTagName("LOTE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("NOMBRE").getLength() > 0) {
                                reeuh.setNombre(elemento.getElementsByTagName("NOMBRE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA_PRERECIBO").getLength() > 0) {
                                reeuh.setFechaPrerecibo(elemento.getElementsByTagName("FECHA_PRERECIBO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA_REMISION").getLength() > 0) {
                                reeuh.setFechaRemision(elemento.getElementsByTagName("FECHA_REMISION").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("ENTREGADA").getLength() > 0) {
                                reeuh.setEntregada(Integer.parseInt(elemento.getElementsByTagName("ENTREGADA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("PRECIO").getLength() > 0) {
                                reeuh.setPrecio(new BigDecimal(elemento.getElementsByTagName("PRECIO").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("PRESUPUESTAL").getLength() > 0) {
                                reeuh.setPresupuestal(elemento.getElementsByTagName("PRESUPUESTAL").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("REMISION").getLength() > 0) {
                                reeuh.setRemision(Integer.parseInt(elemento.getElementsByTagName("REMISION").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("SOLICITADA").getLength() > 0) {
                                reeuh.setSolicitada(Integer.parseInt(elemento.getElementsByTagName("SOLICITADA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("TIPO").getLength() > 0) {
                                reeuh.setTipo(elemento.getElementsByTagName("TIPO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("TIPOPEDIDO").getLength() > 0) {
                                reeuh.setTipopedido(elemento.getElementsByTagName("TIPOPEDIDO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("UMU").getLength() > 0) {
                                reeuh.setUmu(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("TRANSACTION_DT").getLength() > 0) {
                                reeuh.setTransactionDt(Integer.parseInt(elemento.getElementsByTagName("TRANSACTION_DT").item(0).getTextContent()));
                            }
                            boolean guardarHistorico = remisionesElectronicasEntregasUmuHistoricoService.guardar(reeuh);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
