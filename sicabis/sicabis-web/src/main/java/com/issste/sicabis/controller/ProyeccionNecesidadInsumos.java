/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.ProyeccionDTO;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.PlaneacionDetalle;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Toshiba Manolo
 */
@ManagedBean(name = "proyeccionNecesidadInsumos")
@ViewScoped
public class ProyeccionNecesidadInsumos implements Serializable {

    private final static List<String> VALID_COLUMN_KEYS = Arrays.asList("ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO",
            "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE");
    private List<ColumnModel> columns;
    private String columnTemplate = "ENERO FEBRERO MARZO ABRIL MAYO JUNIO JULIO AGOSTO SEPTIEMBRE OCTUBRE NOVIEMBRE";
    private List<ProyeccionDTO> listProyeccion;
    private List<ProyeccionDTO> listProyeccionFilter;
    private Integer mesesProyeccion;
    private List<PlaneacionDetalle> listplanDetProyec;
    private Mensajes mensaje = new Mensajes();
    private Usuarios usuarios;
    private Utilidades util = new Utilidades();
    private Map<Integer, String> nombreMap;

    @PostConstruct
    public void init() {
        nombreMap = new HashMap<Integer, String>();
        nombreMap.put(1, "ENERO");
        nombreMap.put(2, "FEBRERO");
        nombreMap.put(3, "MARZO");
        nombreMap.put(4, "ABRIL");
        nombreMap.put(5, "MAYO");
        nombreMap.put(6, "JUNIO");
        nombreMap.put(7, "JULIO");
        nombreMap.put(8, "AGOSTO");
        nombreMap.put(9, "SEPTIEMBRE");
        nombreMap.put(10, "OCTUBRE");
        nombreMap.put(11, "NOVIEMBRE");

        mesesProyeccion = (Integer) util.getContextAtributte("mesesProyeccion");
        listplanDetProyec = (List<PlaneacionDetalle>) util.getContextAtributte("listplanDetProyec");
        System.out.println("mes de proyeccion " + mesesProyeccion);
        columnTemplate = "";
        Calendar c = Calendar.getInstance();
        Integer mesFinal = 0;
        for (PlaneacionDetalle iteratorPd : listplanDetProyec) {
            c.setTime(iteratorPd.getIdPlaneacion().getFechaFinal());
            mesFinal = c.get(Calendar.MONTH);
        }
        for (int i = 1; i <= mesesProyeccion; i++) {
            System.out.println("nombre mes" + nombreMap.get(mesFinal));
            columnTemplate = columnTemplate + " " + nombreMap.get(mesFinal);
            System.out.println("columnTemplat:" + columnTemplate);
            mesFinal++;
        }

        Random randomGenerator = new Random();
        listProyeccion = new ArrayList<>();
        for (PlaneacionDetalle plandt : listplanDetProyec) {
            Integer cantProyectada = plandt.getCantidadProyectada();
            Integer cantSolicitada = plandt.getCantidadSolicitada();
            ProyeccionDTO proyeccion = new ProyeccionDTO();
            c.setTime(plandt.getIdPlaneacion().getFechaFinal());
            Integer x = c.get(Calendar.MONTH);
            proyeccion.setInsumo(plandt.getIdInsumo());
            if (x == 1) {
                proyeccion.setENERO(cantProyectada);
                cantProyectada = cantProyectada - cantSolicitada;
                x++;
            } else {
                proyeccion.setENERO(0);
            }
            if (x == 2) {
                proyeccion.setFEBRERO(cantProyectada);
                cantProyectada = cantProyectada - cantSolicitada;
                x++;
            } else {
                proyeccion.setFEBRERO(0);
            }
            if (cantProyectada >= 0 && x == 3) {
                proyeccion.setMARZO(cantProyectada);
                cantProyectada = cantProyectada - cantSolicitada;
                x++;
            } else {
                proyeccion.setMARZO(0);
            }
            if (x == 4) {
                proyeccion.setABRIL(cantProyectada);
                cantProyectada = cantProyectada - cantSolicitada;
                x++;
            } else {
                proyeccion.setABRIL(0);
            }
            if (x == 5) {
                proyeccion.setMAYO(cantProyectada);
                cantProyectada = cantProyectada - cantSolicitada;
                x++;
            } else {
                proyeccion.setMAYO(0);
            }

            if (x == 6) {
                proyeccion.setJUNIO(cantProyectada);
                cantProyectada = cantProyectada - cantSolicitada;
                x++;
            } else {
                proyeccion.setJUNIO(0);
            }
            if (x == 7) {
                proyeccion.setJULIO(cantProyectada);
                cantProyectada = cantProyectada - cantSolicitada;
                x++;
            } else {
                proyeccion.setJULIO(0);
            }
            if (x == 8) {
                proyeccion.setAGOSTO(cantProyectada);
                cantProyectada = cantProyectada - cantSolicitada;
                x++;
            } else {
                proyeccion.setAGOSTO(0);
            }
            if (x == 9) {
                proyeccion.setSEPTIEMBRE(cantProyectada);
                cantProyectada = cantProyectada - cantSolicitada;
                x++;
            } else {
                proyeccion.setSEPTIEMBRE(0);
            }
            if (x == 10) {
                proyeccion.setOCTUBRE(cantProyectada);
                cantProyectada = cantProyectada - cantSolicitada;
                x++;
            } else {
                proyeccion.setOCTUBRE(0);
            }
            if (x == 11) {
                proyeccion.setNOVIEMBRE(cantProyectada);
                cantProyectada = cantProyectada - cantSolicitada;
                x++;
            } else {
                proyeccion.setNOVIEMBRE(0);
            }
            listProyeccion.add(proyeccion);

        }

//        listProyeccion = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//           ProyeccionDTO proyeccion = new ProyeccionDTO();
//                proyeccion.setInsumo(new Insumos(1, "10000"+i, "eeeeeee", "eeeeeee", "eeeeeee", "eeeee", "eeee"));
//                proyeccion.setENERO(123);
//                proyeccion.setFEBRERO(23);
//                listProyeccion.add(proyeccion);
//        }        
        createDynamicColumns();

    }

    private void createDynamicColumns() {
        String[] columnKeys = columnTemplate.split(" ");
        columns = new ArrayList<ColumnModel>();

        for (String columnKey : columnKeys) {
            String key = columnKey.trim();

            if (VALID_COLUMN_KEYS.contains(key)) {
                columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
            }
        }
    }

    public List<ColumnModel> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    public String getColumnTemplate() {
        return columnTemplate;
    }

    public void setColumnTemplate(String columnTemplate) {
        this.columnTemplate = columnTemplate;
    }

    public List<ProyeccionDTO> getListProyeccion() {
        return listProyeccion;
    }

    public void setListProyeccion(List<ProyeccionDTO> listProyeccion) {
        this.listProyeccion = listProyeccion;
    }

    public List<ProyeccionDTO> getListProyeccionFilter() {
        return listProyeccionFilter;
    }

    public void setListProyeccionFilter(List<ProyeccionDTO> listProyeccionFilter) {
        this.listProyeccionFilter = listProyeccionFilter;
    }

    public Integer getMesesProyeccion() {
        return mesesProyeccion;
    }

    public void setMesesProyeccion(Integer mesesProyeccion) {
        this.mesesProyeccion = mesesProyeccion;
    }

    public Map<Integer, String> getNombreMap() {
        return nombreMap;
    }

    public void setNombreMap(Map<Integer, String> nombreMap) {
        this.nombreMap = nombreMap;
    }

    public List<PlaneacionDetalle> getListplanDetProyec() {
        return listplanDetProyec;
    }

    public void setListplanDetProyec(List<PlaneacionDetalle> listplanDetProyec) {
        this.listplanDetProyec = listplanDetProyec;
    }

    static public class ColumnModel implements Serializable {

        private String header;
        private String property;

        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }

        public String getHeader() {
            return header;
        }

        public String getProperty() {
            return property;
        }
    }

}
