/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.DAO.EntradasMymcqCenadiHistoricoDAO;
import com.issste.sicabis.ejb.DAO.EntradasMymcqCenadiHistoricoDAOImplement;
import com.issste.sicabis.ejb.ln.EntradasMymcqCenadiHistoricoService;
import com.issste.sicabis.ejb.modelo.EntradasMymcqCenadiHistorico;
import com.issste.sicabis.ejb.service.silodisa.DAO.EntradasMYMCQCenadiDAO;
import com.issste.sicabis.ejb.service.silodisa.DAOImplement.EntradasMYMCQCenadiDAOImplement;
import com.issste.sicabis.ejb.service.silodisa.modelo.EntradasMymcqCenadi;
import com.issste.sicabis.ejb.service.silodisa.service.EntradasMymcqCenadiService;
import com.issste.sicabis.ejb.utils.ConexionWebServiceUtil;
import com.issste.sicabis.ejb.utils.SSLUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
public class EntradasMymcqCenadiController {

    @EJB
    private EntradasMymcqCenadiService entradasMymcqCenadiService;

    @EJB
    private EntradasMymcqCenadiHistoricoService entradasMymcqCenadiHistoricoService;

    private ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();
    private SSLUtil sslUtil = new SSLUtil();

    public void obtenerExistenciaReservaClaveCenadi(Integer opcion) throws MalformedURLException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        sslUtil.validarCertificado();
        String url = cwsu.entradasMYMCQCenadi;
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
                System.out.println("doc.getDocumentElement().getNodeName();" + doc.getElementsByTagName("Clave"));
                NodeList dataSet = doc.getElementsByTagName("Table");
                entradasMymcqCenadiService.eliminarExistencias();
                for (int temp = 0; temp < dataSet.getLength(); temp++) {
                    Node unContacto = dataSet.item(temp);
                    if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
                        Element elemento = (Element) unContacto;
                        EntradasMymcqCenadi emc = new EntradasMymcqCenadi();
                        EntradasMymcqCenadiHistorico emch = new EntradasMymcqCenadiHistorico();
                        if (opcion == 1 || opcion == 0) {
                            if (elemento.getElementsByTagName("Oc_Oracle").getLength() > 0) {
                                emc.setOcOracle(elemento.getElementsByTagName("Oc_Oracle").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Clave").getLength() > 0) {
                                emc.setClave(elemento.getElementsByTagName("Clave").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Fecha_Ingreso").getLength() > 0) {
                                emc.setFechaIngreso(new Date(elemento.getElementsByTagName("Fecha_Ingreso").item(0).getTextContent()));
                            }
                            emc.setFechaIngresoAux(fecha_ingreso);
                            if (elemento.getElementsByTagName("Registro_Control").getLength() > 0) {
                                emc.setRegistroControl(elemento.getElementsByTagName("Registro_Control").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Tipo_Entrada").getLength() > 0) {
                                emc.setTipoEntrada(elemento.getElementsByTagName("Tipo_Entrada").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Contrato_ISSSTE").getLength() > 0) {
                                emc.setContratoIssste(elemento.getElementsByTagName("Contrato_ISSSTE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Presupuestal").getLength() > 0) {
                                emc.setPresupuestal(elemento.getElementsByTagName("Presupuestal").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Proveedor").getLength() > 0) {
                                emc.setProveedor(elemento.getElementsByTagName("Proveedor").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Numero_Proveedor").getLength() > 0) {
                                emc.setNumeroProveedor(elemento.getElementsByTagName("Numero_Proveedor").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Descripcion").getLength() > 0) {
                                emc.setDescripcion(elemento.getElementsByTagName("Descripcion").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Lote").getLength() > 0) {
                                emc.setLote(elemento.getElementsByTagName("Lote").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Caducidad").getLength() > 0) {
                                emc.setCaducidad(elemento.getElementsByTagName("Caducidad").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Cantidad").getLength() > 0) {
                                emc.setCantidad(Integer.parseInt(elemento.getElementsByTagName("Cantidad").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("Precio_Unitario").getLength() > 0) {
                                emc.setPrecioUnitario(elemento.getElementsByTagName("Precio_Unitario").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Precio_Unitario").getLength() > 0) {
                                emc.setImporte(elemento.getElementsByTagName("Precio_Unitario").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Importe_IVA").getLength() > 0) {
                                emc.setImporteIva(elemento.getElementsByTagName("Importe_IVA").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Importe_LIVA").getLength() > 0) {
                                emc.setImporteLiva(elemento.getElementsByTagName("Importe_LIVA").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Importe_TOTAL").getLength() > 0) {
                                emc.setImporteTotal(elemento.getElementsByTagName("Importe_TOTAL").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Fecha_Ingreso").getLength() > 0) {
                                emc.setFecha(new Date(elemento.getElementsByTagName("Fecha_Ingreso").item(0).getTextContent()));
                            }
                            boolean guardar = entradasMymcqCenadiService.guardar(emc);
                        } if (opcion == 1) {
                            if (elemento.getElementsByTagName("Oc_Oracle").getLength() > 0) {
                                emch.setOcOracle(emc.getOcOracle());
                            }
                            if (elemento.getElementsByTagName("Clave").getLength() > 0) {
                                emch.setClave(emc.getClave());
                            }
                            if (elemento.getElementsByTagName("Fecha_Ingreso").getLength() > 0) {
                                emch.setFechaIngreso(emc.getFechaIngreso());
                            }
                            emch.setFechaIngresoAux(fecha_ingreso);
                            if (elemento.getElementsByTagName("Registro_Control").getLength() > 0) {
                                emch.setRegistroControl(emc.getRegistroControl());
                            }
                            if (elemento.getElementsByTagName("Tipo_Entrada").getLength() > 0) {
                                emch.setTipoEntrada(emc.getTipoEntrada());
                            }
                            if (elemento.getElementsByTagName("Contrato_ISSSTE").getLength() > 0) {
                                emch.setContratoIssste(emc.getContratoIssste());
                            }
                            if (elemento.getElementsByTagName("Presupuestal").getLength() > 0) {
                                emch.setPresupuestal(emc.getPresupuestal());
                            }
                            if (elemento.getElementsByTagName("Proveedor").getLength() > 0) {
                                emch.setProveedor(emc.getProveedor());
                            }
                            if (elemento.getElementsByTagName("Numero_Proveedor").getLength() > 0) {
                                emch.setNumeroProveedor(emc.getNumeroProveedor());
                            }
                            if (elemento.getElementsByTagName("Descripcion").getLength() > 0) {
                                emch.setDescripcion(emc.getDescripcion());
                            }
                            if (elemento.getElementsByTagName("Lote").getLength() > 0) {
                                emch.setLote(emc.getLote());
                            }
                            if (elemento.getElementsByTagName("Caducidad").getLength() > 0) {
                                emch.setCaducidad(emc.getCaducidad());
                            }
                            if (elemento.getElementsByTagName("Cantidad").getLength() > 0) {
                                emch.setCantidad(emc.getCantidad());
                            }
                            if (elemento.getElementsByTagName("Precio_Unitario").getLength() > 0) {
                                emch.setPrecioUnitario(emc.getPrecioUnitario());
                            }
                            if (elemento.getElementsByTagName("Precio_Unitario").getLength() > 0) {
                                emch.setImporte(emc.getPrecioUnitario());
                            }
                            if (elemento.getElementsByTagName("Importe_IVA").getLength() > 0) {
                                emch.setImporteIva(emc.getImporteIva());
                            }
                            if (elemento.getElementsByTagName("Importe_LIVA").getLength() > 0) {
                                emch.setImporteLiva(emc.getImporteLiva());
                            }
                            if (elemento.getElementsByTagName("Importe_TOTAL").getLength() > 0) {
                                emch.setImporteTotal(emc.getImporteTotal());
                            }
                            if (elemento.getElementsByTagName("Fecha_Ingreso").getLength() > 0) {
                                emch.setFecha(emc.getFecha());
                            }
                            boolean guadarHistorico = entradasMymcqCenadiHistoricoService.guardar(emch);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
