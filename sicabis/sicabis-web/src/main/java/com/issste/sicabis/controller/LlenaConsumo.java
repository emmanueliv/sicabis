package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.ConsumoDiarioSiamService;
import com.issste.sicabis.ejb.modelo.ConsumoDiarioSiam;
import com.issste.sicabis.ejb.siam.ln.VwExistenciasSICABISService;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

public class LlenaConsumo implements Serializable {

    @EJB
    private ConsumoDiarioSiamService consumoDiarioSiamService;

    @EJB
    private VwExistenciasSICABISService vwExistenciasSICABISService;

    private String fechaS;
    private String fechaF;
    private String label1;
    private String label2;
    private String query;
    
    private List<ConsumoDiarioSiam> consumoDiarioSiamList;

    public LlenaConsumo() {
    }

    public void llenaConsumo() {
        System.out.println("fechaS--->" + fechaS);
//        Date fecha = new Date(fechaS);
//        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        label1 = "empeze";
        System.out.println("empece consumo");
        label2 = vwExistenciasSICABISService.cargaConsumoDiarioAux(fechaS, fechaF);
        System.out.println("sali consumo");
    }
    
    public void ejecutaQuery(){
        ResultSet resultSet = consumoDiarioSiamService.ejecutaQuery(query);
        JSONArray jsonArray = new JSONArray();
        try {
            while (resultSet.next()) {
                int total_rows = resultSet.getMetaData().getColumnCount();
                for (int i = 0; i < total_rows; i++) {
                    JSONObject obj = new JSONObject();
                    obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                            .toLowerCase(), resultSet.getObject(i + 1));
                    jsonArray.put(obj);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LlenaConsumo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("----->"+jsonArray);
        label1 = jsonArray.toString();
    }
    
    public void ejecutaQueryIUD(){
        int valor = consumoDiarioSiamService.ejecutaUpdate(query);
        System.out.println("----->"+valor);
        label1 = valor + "";
    }
    
    public void obtieneConsumo(){
        consumoDiarioSiamList = consumoDiarioSiamService.getByFechas(fechaS, fechaF);
    }

    public String getFechaS() {
        return fechaS;
    }

    public void setFechaS(String fechaS) {
        this.fechaS = fechaS;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public String getFechaF() {
        return fechaF;
    }

    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<ConsumoDiarioSiam> getConsumoDiarioSiamList() {
        return consumoDiarioSiamList;
    }

    public void setConsumoDiarioSiamList(List<ConsumoDiarioSiam> consumoDiarioSiamList) {
        this.consumoDiarioSiamList = consumoDiarioSiamList;
    }

}
