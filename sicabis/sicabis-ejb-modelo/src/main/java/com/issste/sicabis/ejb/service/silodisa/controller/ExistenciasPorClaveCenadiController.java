/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.HistoricoExistenciaPorClaveCenadiService;
import com.issste.sicabis.ejb.modelo.HistoricoExistenciaPorClaveCenadi;
import com.issste.sicabis.ejb.service.silodisa.modelo.ExistenciaPorClaveCenadi;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciaReservaClaveCenadiService;
import com.issste.sicabis.ejb.service.silodisa.service.ExistenciasPorClaveCenadiService;
import com.issste.sicabis.ejb.utils.ConexionWebServiceUtil;
import com.issste.sicabis.ejb.utils.SSLUtil;
import com.issste.sicabis.ejb.utils.Utilidades;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author 5XD9BG2
 */
@Stateless
public class ExistenciasPorClaveCenadiController {

    @EJB
    private ExistenciaReservaClaveCenadiService existenciaReservaClaveCenadiService;

    @EJB
    private HistoricoExistenciaPorClaveCenadiService historicoExistenciaPorClaveCenadiService;

    @EJB
    private ExistenciasPorClaveCenadiService existenciasPorClaveCenadiService;

    private final SSLUtil sslUtil = new SSLUtil();
    private final ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();
    private Utilidades util = new Utilidades();

//    public static void main(String[] args){
//        ExistenciasPorClaveCenadiController epccc = new ExistenciasPorClaveCenadiController();
//        try {
//            epccc.obtenerExistencias();
//        } catch (IOException ex) {
//            Logger.getLogger(ExistenciasPorClaveCenadiController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void obtenerExistencias(Integer opcion) throws MalformedURLException, IOException {
        sslUtil.validarCertificado();
        String url = cwsu.existenciasPorClaveCenadi;
        Date fecha_ingreso = new Date();
        HttpURLConnection connection = cwsu.conectar(url);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try {
                //borrar 
                existenciasPorClaveCenadiService.eliminarExistencias();
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
                        ExistenciaPorClaveCenadi epcc = new ExistenciaPorClaveCenadi();
                        if (opcion == 1 || opcion == 0) {
                            if (elemento.getElementsByTagName("CLAVE").getLength() > 0) {
                                epcc.setClave(elemento.getElementsByTagName("CLAVE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("EXISTENCIA").getLength() > 0) {
                                epcc.setExistencia(elemento.getElementsByTagName("EXISTENCIA").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("FECHA_CADUCIDAD").getLength() > 0) {
                                String fechaCaducidadText = elemento.getElementsByTagName("FECHA_CADUCIDAD").item(0).getTextContent();
                                Date fechaCaducidad = new Date(fechaCaducidadText);
                                epcc.setFechaCaducidad(fechaCaducidad);
                            }
                            if (elemento.getElementsByTagName("FECHA_DT").getLength() > 0) {
                                String fechaDtText = elemento.getElementsByTagName("FECHA_DT").item(0).getTextContent();
                                Date fechaDt = new Date(fechaDtText);
                                epcc.setFechaDt(fechaDt);
                            }
                            if (elemento.getElementsByTagName("FECHA_INVENTARIO").getLength() > 0) {
                                String fechaInventarioText = elemento.getElementsByTagName("FECHA_INVENTARIO").item(0).getTextContent();
                                Date fechaInventario = new Date(fechaInventarioText);
                                epcc.setFechaInventario(fechaInventario);
                            }
                            if (elemento.getElementsByTagName("IMPORTE").getLength() > 0) {
                                String importeText = elemento.getElementsByTagName("IMPORTE").item(0).getTextContent();
                                System.out.println("importe " + importeText);
                                epcc.setImporte(new BigDecimal(importeText));
                            }
                            if (elemento.getElementsByTagName("LOCALIZADOR").getLength() > 0) {
                                epcc.setLocalizador(elemento.getElementsByTagName("LOCALIZADOR").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("LOTE").getLength() > 0) {
                                epcc.setLote(elemento.getElementsByTagName("LOTE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("NOMBRE").getLength() > 0) {
                                epcc.setNombre(elemento.getElementsByTagName("NOMBRE").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("PARTIDA_PRESUPUESTAL").getLength() > 0) {
                                epcc.setPartidaPresupuestal(elemento.getElementsByTagName("PARTIDA_PRESUPUESTAL").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("PRECIO_UNITARIO").getLength() > 0) {
                                epcc.setPrecioUnitario(new BigDecimal(elemento.getElementsByTagName("PRECIO_UNITARIO").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("PROVEEDOR").getLength() > 0) {
                                epcc.setProveedor(elemento.getElementsByTagName("PROVEEDOR").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("PRECIO_UNITARIO").getLength() > 0) {
                                epcc.setPrecioUnitario(new BigDecimal(elemento.getElementsByTagName("PRECIO_UNITARIO").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("SUBINVENTARIO").getLength() > 0) {
                                epcc.setSubinventario(elemento.getElementsByTagName("SUBINVENTARIO").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("PRECIO_UNITARIO").getLength() > 0) {
                                epcc.setPrecioUnitario(new BigDecimal(elemento.getElementsByTagName("PRECIO_UNITARIO").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("TIPO").getLength() > 0) {
                                epcc.setTipo(elemento.getElementsByTagName("TIPO").item(0).getTextContent());
                            }

                            Integer existencia = epcc.getExistencia() != null ? Integer.parseInt(epcc.getExistencia()) : 0;
                            Integer dpn = existenciasPorClaveCenadiService.existenciaSumPorClaveCenadiByClave(epcc.getClave());
                            Integer cobertura = (existencia) * 30;
                            BigDecimal coberturaDias = new BigDecimal(0);
                            if (dpn != 0 && cobertura != 0) {
                                coberturaDias = new BigDecimal(cobertura).divide(new BigDecimal(dpn), 2, RoundingMode.HALF_UP);
                            }
                            epcc.setCoberturaDias(util.redondearNumero(coberturaDias.doubleValue()));
                            epcc.setFechaIngreso(fecha_ingreso);
                            existenciasPorClaveCenadiService.guardar(epcc);
                        }  if (opcion == 1) {
                            HistoricoExistenciaPorClaveCenadi hepcc = this.llenaObjetoHistorico(epcc);
                            historicoExistenciaPorClaveCenadiService.guardar(hepcc);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public HistoricoExistenciaPorClaveCenadi llenaObjetoHistorico(ExistenciaPorClaveCenadi epcc) {
        HistoricoExistenciaPorClaveCenadi hepcc = new HistoricoExistenciaPorClaveCenadi();
        hepcc.setClave(epcc.getClave());
        hepcc.setExistencia(epcc.getExistencia());
        hepcc.setFechaCaducidad(epcc.getFechaCaducidad());
        hepcc.setFechaDt(epcc.getFechaDt());
        hepcc.setFechaIngreso(epcc.getFechaIngreso());
        hepcc.setFechaInventario(epcc.getFechaInventario());
        hepcc.setImporte(epcc.getImporte());
        hepcc.setLocalizador(epcc.getLocalizador());
        hepcc.setLote(epcc.getLote());
        hepcc.setNombre(epcc.getNombre());
        hepcc.setPartidaPresupuestal(epcc.getPartidaPresupuestal());
        hepcc.setPrecioUnitario(epcc.getPrecioUnitario());
        hepcc.setProveedor(epcc.getProveedor());
        hepcc.setSubinventario(epcc.getSubinventario());
        hepcc.setTipo(epcc.getTipo());
        hepcc.setCoberturaDias(epcc.getCoberturaDias());
        return hepcc;
    }

}
