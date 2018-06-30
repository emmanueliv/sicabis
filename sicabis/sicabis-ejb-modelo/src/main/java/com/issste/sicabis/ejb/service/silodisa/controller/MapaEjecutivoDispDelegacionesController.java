package com.issste.sicabis.ejb.service.silodisa.controller;

import com.issste.sicabis.ejb.ln.DelegacionesService;
import com.issste.sicabis.ejb.ln.MapaEjecutivoDispDelegacionesHistoricoService;
import com.issste.sicabis.ejb.ln.MapaEjecutivoDispG40HistoricoService;
import com.issste.sicabis.ejb.modelo.Delegaciones;
import com.issste.sicabis.ejb.modelo.Indicador;
import com.issste.sicabis.ejb.modelo.MapaEjecutivoDispDelegacionesHistorico;
import com.issste.sicabis.ejb.service.silodisa.modelo.MapaEjecutivoDispDelegaciones;
import com.issste.sicabis.ejb.service.silodisa.service.MapaEjecutivoDispDelegacionesService;
import com.issste.sicabis.ejb.utils.ConexionWebServiceUtil;
import com.issste.sicabis.ejb.utils.SSLUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
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

@Stateless
public class MapaEjecutivoDispDelegacionesController {

    @EJB
    private MapaEjecutivoDispDelegacionesService mapaEjecutivoDispDelegacionesService;

    @EJB
    private MapaEjecutivoDispDelegacionesHistoricoService mapaEjecutivoDispDelegacionesHistoricoService;

    @EJB
    private DelegacionesService delegacionesService;

    private final SSLUtil sslUtil = new SSLUtil();
    private final ConexionWebServiceUtil cwsu = new ConexionWebServiceUtil();

    public void obtenerMapaEjecutivoDispDelegacion(Integer opcion) throws MalformedURLException, IOException {
        sslUtil.validarCertificado();
        String url = cwsu.mapaEjecutivoDisponibilidadDelegaciones;
        HttpURLConnection connection = cwsu.conectar(url);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try {
                boolean eliminar = mapaEjecutivoDispDelegacionesService.eliminarExistencias();
                //boolean result = false;//porcentajeDelegacionService.borrarContenidoPorcentajeDelegacion();
                DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                domFactory.setNamespaceAware(true);
                DocumentBuilder builder = domFactory.newDocumentBuilder();
                Document doc = builder.parse(new InputSource(connection.getInputStream()));
                NodeList dataSet = doc.getElementsByTagName("Table");
                for (int temp = 0; temp < dataSet.getLength(); temp++) {
                    Node unContacto = dataSet.item(temp);
                    if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                        Element elemento = (Element) unContacto;
                        MapaEjecutivoDispDelegaciones medd = new MapaEjecutivoDispDelegaciones();
                        MapaEjecutivoDispDelegacionesHistorico meddh = new MapaEjecutivoDispDelegacionesHistorico();
                        if (opcion == 1 || opcion == 0) {
                            if (elemento.getElementsByTagName("Estado").getLength() > 0) {
                                String nombreEstado = elemento.getElementsByTagName("Estado").item(0).getTextContent();
                                Delegaciones delegacion = null;
                                if (nombreEstado.equals("DISTRITO FEDERAL")) {
                                    delegacion = delegacionesService.obtenerDelegacionporNombre("CIUDAD DE MEXICO");
                                    medd.setIdDelegacion(delegacion);
                                } else {
                                    delegacion = delegacionesService.obtenerDelegacionporNombre(nombreEstado);
                                    medd.setIdDelegacion(delegacion);
                                }
                            }
                            if (elemento.getElementsByTagName("Delegacion").getLength() > 0) {
                                medd.setDelegacion(elemento.getElementsByTagName("Delegacion").item(0).getTextContent());
                            }
                            if (elemento.getElementsByTagName("Claves_Autorizadas").getLength() > 0) {
                                medd.setClavesAutorizadas(Integer.parseInt(elemento.getElementsByTagName("Claves_Autorizadas").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("Claves_Disponibles").getLength() > 0) {
                                medd.setClavesDisponibles(Integer.parseInt(elemento.getElementsByTagName("Claves_Disponibles").item(0).getTextContent()));
                            }
                            if (elemento.getElementsByTagName("Disponibilidad").getLength() > 0) {
                                String porcentajeDisponibilidad = elemento.getElementsByTagName("Disponibilidad").item(0).getTextContent();
                                if (porcentajeDisponibilidad.contains("%")) {
                                    porcentajeDisponibilidad = porcentajeDisponibilidad.replace("%", "");
                                }
                                medd.setDisponibilidad(new BigDecimal(porcentajeDisponibilidad));
                            }
                            if (elemento.getElementsByTagName("Fecha").getLength() > 0) {
                                String fecha = elemento.getElementsByTagName("Fecha").item(0).getTextContent();
                                fecha = fecha.trim();
                                int pos = fecha.lastIndexOf(" ");
                                String f = fecha.substring(0, pos);
                                f = f.replace("-", "/");
                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                medd.setFecha(format.parse(f));
                            }
                            medd.setActivo(1);
                            medd.setFechaIngreso(new Date());
                            medd.setIdIndicador(new Indicador(2));
                            mapaEjecutivoDispDelegacionesService.guardar(medd);
                        }  if (opcion == 1) {
                            meddh.setIdDelegacion(medd.getIdDelegacion().getIdDelegacion());
                            meddh.setDelegacion(medd.getDelegacion());
                            meddh.setClavesAutorizadas(medd.getClavesAutorizadas());
                            meddh.setClavesDisponibles(medd.getClavesDisponibles());
                            meddh.setDisponibilidad(medd.getDisponibilidad());
                            meddh.setFecha(medd.getFecha());
                            meddh.setActivo(1);
                            meddh.setFechaIngreso(new Date());
                            meddh.setIdIndicador(medd.getIdIndicador().getIdIndicador());
                            mapaEjecutivoDispDelegacionesHistoricoService.guardar(meddh);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
