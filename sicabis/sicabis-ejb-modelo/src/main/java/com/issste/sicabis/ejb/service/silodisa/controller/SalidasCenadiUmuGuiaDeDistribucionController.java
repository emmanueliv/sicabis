package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.SalidasCenadiUmuGuiaDeDistribucionHistoricoService;
import com.issste.sicabis.ejb.modelo.SalidasCenadiUmuGuiaDeDistribucionHistorico;
import com.issste.sicabis.ejb.service.silodisa.modelo.SalidasCenadiUmuGuiaDeDistribucion;
import com.issste.sicabis.ejb.service.silodisa.service.SalidasCenadiUmuGuiaDeDistribucionService;
import com.issste.sicabis.ejb.utils.ConexionWebServiceUtil;
import com.issste.sicabis.ejb.utils.SSLUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
public class SalidasCenadiUmuGuiaDeDistribucionController {

    @EJB
    private SalidasCenadiUmuGuiaDeDistribucionService salidasCenadiUmuGuiaDeDistribucionService;

    @EJB
    private SalidasCenadiUmuGuiaDeDistribucionHistoricoService salidasCenadiUmuGuiaDeDistribucionHistoricoService;

    private ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();
    private SSLUtil sslUtil = new SSLUtil();

    public void obtenerSalidasCenadiUmuGuiaDeDistribucion(Integer opcion) throws MalformedURLException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        sslUtil.validarCertificado();
        String url = cwsu.salidasCENADIHaciaUnidadMedicaGuiaDistribucion;
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
                        SalidasCenadiUmuGuiaDeDistribucion scugd = new SalidasCenadiUmuGuiaDeDistribucion();
                        SalidasCenadiUmuGuiaDeDistribucionHistorico scugdh = new SalidasCenadiUmuGuiaDeDistribucionHistorico();
                        if (opcion == 1 || opcion == 0) {
                            if (elemento.getElementsByTagName("CANTIDAD").getLength() > 0) {
                                scugd.setCantidad(Integer.parseInt(elemento.getElementsByTagName("CANTIDAD").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("DESC_UMU").getLength() > 0) {
                                scugd.setDescUmu(elemento.getElementsByTagName("DESC_UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA_INVENTARIO").item(0).getTextContent() == null) {
                                scugd.setFechaInventario(new Date("0001/01/01"));
                            } else {
                                String fecha = elemento.getElementsByTagName("FECHA_INVENTARIO").item(0).getTextContent();
                                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                                scugd.setFechaInventario(formatoDelTexto.parse(fecha));
                            }
                            if (elemento.getElementsByTagName("FOLIOISSSTE").getLength() > 0) {
                                scugd.setFolioissste(Integer.parseInt(elemento.getElementsByTagName("FOLIOISSSTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("IMPORTE").getLength() > 0) {
                                scugd.setImporte(new BigDecimal(elemento.getElementsByTagName("IMPORTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("PARTIDA_PRESUPUESTAL_UMU").getLength() > 0) {
                                scugd.setPartidaPresupuestalUmu(elemento.getElementsByTagName("PARTIDA_PRESUPUESTAL_UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("REMISION").getLength() > 0) {
                                scugd.setRemision(Integer.parseInt(elemento.getElementsByTagName("REMISION").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("SUBINVENTARIO").getLength() > 0) {
                                scugd.setSubinventario(elemento.getElementsByTagName("SUBINVENTARIO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("UMU").getLength() > 0) {
                                scugd.setUmu(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                            }
                            boolean guardar = salidasCenadiUmuGuiaDeDistribucionService.guardar(scugd);
                        } if (opcion == 1) {
//
                            if (elemento.getElementsByTagName("CANTIDAD").getLength() > 0) {
                                scugdh.setCantidad(Integer.parseInt(elemento.getElementsByTagName("CANTIDAD").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("DESC_UMU").getLength() > 0) {
                                scugdh.setDescUmu(elemento.getElementsByTagName("DESC_UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA_INVENTARIO").item(0).getTextContent() == null) {
                                scugdh.setFechaInventario(new Date("0001/01/01"));
                            } else {
                                String fecha = elemento.getElementsByTagName("FECHA_INVENTARIO").item(0).getTextContent();
                                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                                scugdh.setFechaInventario(formatoDelTexto.parse(fecha));
                            }
                            if (elemento.getElementsByTagName("FOLIOISSSTE").getLength() > 0) {
                                scugdh.setFolioissste(Integer.parseInt(elemento.getElementsByTagName("FOLIOISSSTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("IMPORTE").getLength() > 0) {
                                scugdh.setImporte(new BigDecimal(elemento.getElementsByTagName("IMPORTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("PARTIDA_PRESUPUESTAL_UMU").getLength() > 0) {
                                scugdh.setPartidaPresupuestalUmu(elemento.getElementsByTagName("PARTIDA_PRESUPUESTAL_UMU").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("REMISION").getLength() > 0) {
                                scugdh.setRemision(Integer.parseInt(elemento.getElementsByTagName("REMISION").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("SUBINVENTARIO").getLength() > 0) {
                                scugdh.setSubinventario(elemento.getElementsByTagName("SUBINVENTARIO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("UMU").getLength() > 0) {
                                scugdh.setUmu(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                            }
                            System.out.println(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                            boolean guadarHistorico = salidasCenadiUmuGuiaDeDistribucionHistoricoService.guardar(scugdh);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
