/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.CatUnidadMedicaHistoricoService;
import com.issste.sicabis.ejb.modelo.CatUnidadMedicaHistorico;
import com.issste.sicabis.ejb.service.silodisa.modelo.CatUnidadMedica;
import com.issste.sicabis.ejb.service.silodisa.service.CatalogoUnidadesMedicasService;
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
public class CatalogoUnidadesMedicasController {

    @EJB
    private CatUnidadMedicaHistoricoService catUnidadMedicaHistoricoService;
    @EJB
    private CatalogoUnidadesMedicasService catalogoUnidadesMedicasService;

    private ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();
    private SSLUtil sslUtil = new SSLUtil();

    public void obtnerCatalogoUnidadesMedicas() throws MalformedURLException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException {
        sslUtil.validarCertificado();
        String url = cwsu.catalogoUnidadesMedicas;
        Date fecha_ingreso = new Date();
        HttpURLConnection connection = cwsu.conectar(url);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try {
                boolean eliminar = catalogoUnidadesMedicasService.eliminarExistencias();
                DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                domFactory.setNamespaceAware(true);
                DocumentBuilder builder = domFactory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(connection.getInputStream()));
                NodeList dataSet = doc.getElementsByTagName("Table");
                for (int temp = 0; temp < dataSet.getLength(); temp++) {
                    Node unContacto = dataSet.item(temp);
                    if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                        Element elemento = (Element) unContacto;
                        CatUnidadMedica cum = new CatUnidadMedica();
                        CatUnidadMedicaHistorico cumh = new CatUnidadMedicaHistorico();

                        if (elemento.getElementsByTagName("UMU").getLength() > 0) {
                            cum.setUmu(elemento.getElementsByTagName("UMU").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("CIUDAD").getLength() > 0) {
                            cum.setCiudad(elemento.getElementsByTagName("CIUDAD").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("CLAVE_PRESUPUESTAL").getLength() > 0) {
                            cum.setClavePresupuestal(elemento.getElementsByTagName("CLAVE_PRESUPUESTAL").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("DELEGACION").getLength() > 0) {
                            cum.setDelegacion(elemento.getElementsByTagName("DELEGACION").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("DIRECCION").getLength() > 0) {
                            cum.setDireccion(elemento.getElementsByTagName("DIRECCION").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("ESTADO").getLength() > 0) {
                            cum.setEstado(elemento.getElementsByTagName("ESTADO").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("LATITUD").getLength() > 0) {
                            cum.setLatitud(elemento.getElementsByTagName("LATITUD").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("LONGITUD").getLength() > 0) {
                            cum.setLongitud(elemento.getElementsByTagName("LONGITUD").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("MUNICIPIO").getLength() > 0) {
                            cum.setMunicipio(elemento.getElementsByTagName("MUNICIPIO").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("NOMBRE").getLength() > 0) {
                            cum.setNombre(elemento.getElementsByTagName("NOMBRE").item(0).getTextContent());
                        }
                        if (elemento.getElementsByTagName("ESTATUS").getLength() > 0) {
                            cum.setEstatus(elemento.getElementsByTagName("ESTATUS").item(0).getTextContent());
                        }
                        cum.setFechaIngreso(fecha_ingreso);                  
                        boolean guardar = catalogoUnidadesMedicasService.guardar(cum);

                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
