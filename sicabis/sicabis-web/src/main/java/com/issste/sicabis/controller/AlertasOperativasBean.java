package com.issste.sicabis.controller;

import com.google.gson.Gson;
import com.issste.sicabis.DTO.MapaDTO;
import com.issste.sicabis.ejb.DTO.AlertasDTO;
import com.issste.sicabis.ejb.ln.AlertasOperativasHistoricoService;
import com.issste.sicabis.ejb.ln.ColorPorcentajeService;
import com.issste.sicabis.ejb.modelo.AlertasOperativasHistorico;
import com.issste.sicabis.ejb.modelo.ColorPorcentaje;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

public class AlertasOperativasBean {

    @EJB
    private ColorPorcentajeService colorPorcentajeService;

    @EJB
    private AlertasOperativasHistoricoService alertasOperativasHistoricoService;

    private List<AlertasOperativasHistorico> alertaOperativaList;
    private List<ColorPorcentaje> listacolores;
    private List<AlertasDTO> listaPorcentajeDel;
    private List<MapaDTO> mapaPorcentajes;
    private String datosEstadosFormatoJSON;
    private List<AlertasDTO> alertaList;

    public AlertasOperativasBean() {
    }

    @PostConstruct
    public void init() {

        alertaList = alertasOperativasHistoricoService.getAll();
        listacolores = colorPorcentajeService.colorPorcentajeAllActivos();
        mapaPorcentajes = listaPorcentajeDelegacion();
        Date fechaActual = new Date();
        mapaPorcentajes = listaPorcentajeDelegacion();
        datosEstadosFormatoJSON = listaAJson(mapaPorcentajes);
    }

    private List<MapaDTO> listaPorcentajeDelegacion() {
        List<AlertasDTO> listaDelegaciones = alertasOperativasHistoricoService.getAll();
        listaPorcentajeDel = listaDelegaciones;
        List<MapaDTO> listaEstados = new ArrayList();
        if (listaDelegaciones == null) {
            System.out.println("vacio listaDelegaciones");
        } else {
            for (AlertasDTO item : listaDelegaciones) {
                MapaDTO dto = new MapaDTO();
                if (item.getEstado().equals("CIUDAD DE MEXICO")) {
                    dto.setNombreE("DISTRITO FEDERAL");
                } else {
                    dto.setNombreE(item.getEstado());
                }
                dto.setFechaActualizacion(new Date());
                dto.setPorcentaje(item.getPorcentajeOrdinario());
                dto.setColorE(colorByRango(item.getPorcentajeOrdinario()));
                listaEstados.add(dto);
            }
            return listaEstados;
        }
        return listaEstados;
    }

    public String colorByRango(BigDecimal porcentaje) {
        String color = "";
        listacolores = colorPorcentajeService.colorPorcentajeAllActivos();
        for (ColorPorcentaje cp : listacolores) {
            BigDecimal porcIniTrun = truncar(cp.getPorcentajeInicial());
            BigDecimal porcFinTrun = truncar(cp.getPorcentajeFinal());
            BigDecimal porcentajeTrun = truncar(porcentaje);
            if (porcentajeTrun.compareTo(porcIniTrun) >= 0 && porcentajeTrun.compareTo(porcFinTrun) <= 0) {
                color = cp.getHexColor();
                break;
            }
        }
        return color;
    }

    public String listaAJson(List<MapaDTO> listaDtos) {
        Gson gson = new Gson();
        String strJSON = gson.toJson(listaDtos);
        return strJSON;
    }

    public BigDecimal truncar(BigDecimal numero) {
        return numero.setScale(2, RoundingMode.FLOOR);
    }

    public List<ColorPorcentaje> getListacolores() {
        return listacolores;
    }

    public void setListacolores(List<ColorPorcentaje> listacolores) {
        this.listacolores = listacolores;
    }

    public List<AlertasDTO> getListaPorcentajeDel() {
        return listaPorcentajeDel;
    }

    public void setListaPorcentajeDel(List<AlertasDTO> listaPorcentajeDel) {
        this.listaPorcentajeDel = listaPorcentajeDel;
    }

    public List<MapaDTO> getMapaPorcentajes() {
        return mapaPorcentajes;
    }

    public void setMapaPorcentajes(List<MapaDTO> mapaPorcentajes) {
        this.mapaPorcentajes = mapaPorcentajes;
    }

    public String getDatosEstadosFormatoJSON() {
        return datosEstadosFormatoJSON;
    }

    public void setDatosEstadosFormatoJSON(String datosEstadosFormatoJSON) {
        this.datosEstadosFormatoJSON = datosEstadosFormatoJSON;
    }

    public List<AlertasDTO> getAlertaList() {
        return alertaList;
    }

    public void setAlertaList(List<AlertasDTO> alertaList) {
        this.alertaList = alertaList;
    }

}
