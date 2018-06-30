/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.DetalleSalidasUmuGuiaDistribucionHistoricoService;
import com.issste.sicabis.ejb.modelo.DetalleSalidasUmuGuiaDistribucionHistorico;
import com.issste.sicabis.ejb.service.silodisa.modelo.DetalleSalidasUmuGuiaDistribucion;
import com.issste.sicabis.ejb.service.silodisa.service.DetalleSalidasUmuGuiaDistribucionService;
import com.issste.sicabis.ejb.utils.ConexionWebServiceUtil;
import com.issste.sicabis.ejb.utils.SSLUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DetalleSalidasUmuGuiaDistribucionController {

    @EJB
    private DetalleSalidasUmuGuiaDistribucionService detalleSalidasUmuGuiaDistribucionService;

    @EJB
    private DetalleSalidasUmuGuiaDistribucionHistoricoService detalleSalidasUmuGuiaDistribucionHistoricoService;

    private ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();
    private SSLUtil sslUtil = new SSLUtil();

    public void obtenerDetalleSalidasUmuGuiaDistribucion(Integer opcion) throws MalformedURLException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        sslUtil.validarCertificado();
        String url = cwsu.detalleSalidasHaciaUnidadMedicaGuiaDistribucion;
        URL iurl = new URL(url);
        Date fecha_ingreso = new Date();
        HttpURLConnection connection = cwsu.conectar(url);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try {
                boolean eliminar = detalleSalidasUmuGuiaDistribucionService.eliminarExistencias();
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
                        DetalleSalidasUmuGuiaDistribucion dsugd = new DetalleSalidasUmuGuiaDistribucion();
                        DetalleSalidasUmuGuiaDistribucionHistorico dsugdh = new DetalleSalidasUmuGuiaDistribucionHistorico();
                        if (opcion == 1 || opcion == 0) {
                            if (elemento.getElementsByTagName("CLAVE").getLength() > 0) {
                                dsugd.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("CODIGOUMU").getLength() > 0) {
                                dsugd.setCodigoumu(elemento.getElementsByTagName("CODIGOUMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DELEGACION").getLength() > 0) {
                                dsugd.setDelegacion(elemento.getElementsByTagName("DELEGACION").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DPN_MES").getLength() > 0) {
                                dsugd.setDpnMes(elemento.getElementsByTagName("DPN_MES").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DPN_YEAR").getLength() > 0) {
                                dsugd.setDpnYear(elemento.getElementsByTagName("DPN_YEAR").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("ESTATUS").getLength() > 0) {
                                dsugd.setEstatus(elemento.getElementsByTagName("ESTATUS").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA").getLength() > 0) {
                                dsugd.setFecha(new Date(elemento.getElementsByTagName("FECHA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("FECHASALIDA").getLength() > 0) {
                                dsugd.setFechasalida(new Date(elemento.getElementsByTagName("FECHASALIDA").item(0).getTextContent()));
                            }
                            dsugd.setFechaIngreso(fecha_ingreso);
                            if (elemento.getElementsByTagName("FOLIOISSSTE").getLength() > 0) {
                                dsugd.setFolioissste(Integer.parseInt(elemento.getElementsByTagName("FOLIOISSSTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("IMPORTE").getLength() > 0) {
                                dsugd.setImporte(new BigDecimal(elemento.getElementsByTagName("IMPORTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("LOTE").getLength() > 0) {
                                dsugd.setLote(elemento.getElementsByTagName("LOTE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("NOMBRE").getLength() > 0) {
                                dsugd.setNombre(elemento.getElementsByTagName("NOMBRE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("PRECIO").getLength() > 0) {
                                dsugd.setPrecio(new BigDecimal(elemento.getElementsByTagName("PRECIO").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("PRESUPUESTAL").getLength() > 0) {
                                dsugd.setPresupuestal(elemento.getElementsByTagName("PRESUPUESTAL").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("REMISION").getLength() > 0) {
                                dsugd.setRemision(Integer.parseInt(elemento.getElementsByTagName("REMISION").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("SOLICITADA").getLength() > 0) {
                                dsugd.setSolicitada(Integer.parseInt(elemento.getElementsByTagName("SOLICITADA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("TIPO").getLength() > 0) {
                                dsugd.setTipo(elemento.getElementsByTagName("TIPO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("TIPOPEDIDO").getLength() > 0) {
                                dsugd.setTipopedido(elemento.getElementsByTagName("TIPOPEDIDO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("UMU").getLength() > 0) {
                                dsugd.setUmu(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                            }
                            boolean guardar = detalleSalidasUmuGuiaDistribucionService.guardar(dsugd);
                        } if (opcion == 1) {
                            if (elemento.getElementsByTagName("CLAVE").getLength() > 0) {
                                dsugdh.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("CODIGOUMU").getLength() > 0) {
                                dsugdh.setCodigoumu(elemento.getElementsByTagName("CODIGOUMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DELEGACION").getLength() > 0) {
                                dsugdh.setDelegacion(elemento.getElementsByTagName("DELEGACION").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DPN_MES").getLength() > 0) {
                                dsugdh.setDpnMes(elemento.getElementsByTagName("DPN_MES").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("DPN_YEAR").getLength() > 0) {
                                dsugdh.setDpnYear(elemento.getElementsByTagName("DPN_YEAR").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("ESTATUS").getLength() > 0) {
                                dsugdh.setEstatus(elemento.getElementsByTagName("ESTATUS").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA").getLength() > 0) {
                                dsugdh.setFecha(new Date(elemento.getElementsByTagName("FECHA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("FECHASALIDA").getLength() > 0) {
                                dsugdh.setFechasalida(new Date(elemento.getElementsByTagName("FECHASALIDA").item(0).getTextContent()));
                            }
                            dsugdh.setFechaIngreso(fecha_ingreso);
                            if (elemento.getElementsByTagName("FOLIOISSSTE").getLength() > 0) {
                                dsugdh.setFolioissste(Integer.parseInt(elemento.getElementsByTagName("FOLIOISSSTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("IMPORTE").getLength() > 0) {
                                dsugdh.setImporte(new BigDecimal(elemento.getElementsByTagName("IMPORTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("LOTE").getLength() > 0) {
                                dsugdh.setLote(elemento.getElementsByTagName("LOTE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("NOMBRE").getLength() > 0) {
                                dsugdh.setNombre(elemento.getElementsByTagName("NOMBRE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("PRECIO").getLength() > 0) {
                                dsugdh.setPrecio(new BigDecimal(elemento.getElementsByTagName("PRECIO").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("PRESUPUESTAL").getLength() > 0) {
                                dsugdh.setPresupuestal(elemento.getElementsByTagName("PRESUPUESTAL").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("REMISION").getLength() > 0) {
                                dsugdh.setRemision(Integer.parseInt(elemento.getElementsByTagName("REMISION").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("SOLICITADA").getLength() > 0) {
                                dsugdh.setSolicitada(Integer.parseInt(elemento.getElementsByTagName("SOLICITADA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("TIPO").getLength() > 0) {
                                dsugdh.setTipo(elemento.getElementsByTagName("TIPO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("TIPOPEDIDO").getLength() > 0) {
                                dsugdh.setTipopedido(elemento.getElementsByTagName("TIPOPEDIDO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("UMU").getLength() > 0) {
                                dsugdh.setUmu(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                            }
                            boolean guadarHistorico = detalleSalidasUmuGuiaDistribucionHistoricoService.guardar(dsugdh);
                            System.out.println(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
