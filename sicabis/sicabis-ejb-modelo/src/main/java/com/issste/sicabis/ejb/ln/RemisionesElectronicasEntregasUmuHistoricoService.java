/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.RemisionesElectronicasEntregasUmuHistoricoDAO;
import com.issste.sicabis.ejb.DTO.AnoRemisionesElectronicasDTO;
import com.issste.sicabis.ejb.DTO.MesRemisionesElectronicasDTO;
import com.issste.sicabis.ejb.modelo.RemisionesElectronicasEntregasUmuHistorico;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author 9RZCBG2
 */
@Stateless
@LocalBean
public class RemisionesElectronicasEntregasUmuHistoricoService {

    @EJB
    private RemisionesElectronicasEntregasUmuHistoricoDAO remisionesElectronicasEntregasUmuHistoricoDAOImplement;

    public boolean guardar(RemisionesElectronicasEntregasUmuHistorico reruh) {
        return remisionesElectronicasEntregasUmuHistoricoDAOImplement.guardar(reruh);
    }

    public List<RemisionesElectronicasEntregasUmuHistorico> getByFechaIngreso(Date fechaIngreso) {
        return remisionesElectronicasEntregasUmuHistoricoDAOImplement.getByFechaIngreso(fechaIngreso);
    }

    public List<RemisionesElectronicasEntregasUmuHistorico> getAll() {
        return remisionesElectronicasEntregasUmuHistoricoDAOImplement.getAll();
    }

    public List<AnoRemisionesElectronicasDTO> getAno() {
        return remisionesElectronicasEntregasUmuHistoricoDAOImplement.getAno();
    }

    public List<RemisionesElectronicasEntregasUmuHistorico> getByFiltros(Date fechaInicio, Date fechaFin, String delegacion, String clave, String folioPedido, String mesDPN, String umu, String tipoInsumo, String remision, String anoDPN, String nombreUmu, String tipoPedido) {
      return remisionesElectronicasEntregasUmuHistoricoDAOImplement.getByFiltros(fechaInicio, fechaFin, delegacion, clave, folioPedido, mesDPN, umu, tipoInsumo, remision, anoDPN, nombreUmu, tipoPedido);
    }
    
    public List<MesRemisionesElectronicasDTO> getMes(){
      return remisionesElectronicasEntregasUmuHistoricoDAOImplement.getMes();
    }
}
