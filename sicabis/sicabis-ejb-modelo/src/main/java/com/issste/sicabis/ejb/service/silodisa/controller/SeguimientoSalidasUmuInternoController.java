package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.SeguimientoSalidasUmuInternoHistoricoService;
import com.issste.sicabis.ejb.modelo.SeguimientoSalidasUmuInternoHistorico;
import com.issste.sicabis.ejb.service.silodisa.modelo.SeguimientoSalidasUmuInterno;
import com.issste.sicabis.ejb.service.silodisa.service.SeguimientoSalidasUmuInternoService;
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
public class SeguimientoSalidasUmuInternoController {

    @EJB
    private SeguimientoSalidasUmuInternoService seguimientoSalidasUmuInternoService;

    @EJB
    private SeguimientoSalidasUmuInternoHistoricoService seguimientoSalidasUmuInternoHistoricoService;

    private ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();
    private SSLUtil sslUtil = new SSLUtil();

    public void obtenerSalidasCenadiUmuGuiaDeDistribucion(Integer opcion) throws MalformedURLException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        sslUtil.validarCertificado();
        String url = cwsu.seguimientoSalidasHaciaLaUnidadMedicaInterno;
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
                seguimientoSalidasUmuInternoService.eliminarExistencias();
                for (int temp = 0; temp < dataSet.getLength(); temp++) {
                    Node unContacto = dataSet.item(temp);
                    if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                        Element elemento = (Element) unContacto;
                        SeguimientoSalidasUmuInterno scugd = new SeguimientoSalidasUmuInterno();
                        SeguimientoSalidasUmuInternoHistorico scugdh = new SeguimientoSalidasUmuInternoHistorico();
                        if (opcion == 1 || opcion == 0) {
                            if (elemento.getElementsByTagName("FECHA").getLength() > 0) {
                                scugd.setFecha(new Date(elemento.getElementsByTagName("FECHA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("FECHA_ADUANA").getLength() > 0) {
                                scugd.setFechaAduana(new Date(elemento.getElementsByTagName("FECHA_ADUANA").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("FECHA_CARGA_UNIDAD").getLength() > 0) {
                                scugd.setFechaCargaUnidad(new Date(elemento.getElementsByTagName("FECHA_CARGA_UNIDAD").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("FOLIOISSSTE").getLength() > 0) {
                                scugd.setFolioissste(Integer.parseInt(elemento.getElementsByTagName("FOLIOISSSTE").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("FECHA_PLANEACION").getLength() > 0) {
                                scugd.setFechaPlaneacion(new Date(elemento.getElementsByTagName("FECHA_PLANEACION").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("FECHA_VALIDACION").getLength() > 0) {
                                scugd.setFechaValidacion(new Date(elemento.getElementsByTagName("FECHA_VALIDACION").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("PEDIDO").getLength() > 0) {
                                scugd.setPedido(elemento.getElementsByTagName("PEDIDO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("CLAVE").getLength() > 0) {
                                scugd.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("UMU").getLength() > 0) {
                                scugd.setUmu(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                            }
                            scugd.setFechaIngreso(fecha_ingreso);
                            boolean guardar = seguimientoSalidasUmuInternoService.guardar(scugd);
                        } if (opcion == 1) {
                            scugdh.setFecha(scugd.getFecha());
                            scugdh.setFechaAduana(scugd.getFechaAduana());
                            scugdh.setFechaCargaUnidad(scugd.getFechaCargaUnidad());
                            scugdh.setFolioissste(scugd.getFolioissste());
                            scugdh.setFechaPlaneacion(scugd.getFechaPlaneacion());
                            scugdh.setFechaValidacion(scugd.getFechaValidacion());
                            scugdh.setPedido(scugd.getPedido());
                            scugdh.setClave(scugd.getClave());
                            scugdh.setUmu(scugd.getUmu());
                            scugdh.setFechaIngreso(fecha_ingreso);
                            System.out.println(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                            boolean guadarHistprico = seguimientoSalidasUmuInternoHistoricoService.guardar(scugdh);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
