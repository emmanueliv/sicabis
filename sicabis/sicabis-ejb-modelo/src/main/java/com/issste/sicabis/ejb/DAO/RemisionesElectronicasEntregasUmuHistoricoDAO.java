/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.AnoRemisionesElectronicasDTO;
import com.issste.sicabis.ejb.DTO.MesRemisionesElectronicasDTO;
import com.issste.sicabis.ejb.modelo.RemisionesElectronicasEntregasUmuHistorico;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 9RZCBG2
 */
@Local
public interface RemisionesElectronicasEntregasUmuHistoricoDAO {

    boolean guardar(RemisionesElectronicasEntregasUmuHistorico reeuh);

    List<RemisionesElectronicasEntregasUmuHistorico> getByFechaIngreso(Date fechaIngreso);
    List<RemisionesElectronicasEntregasUmuHistorico> getAll();
    List<RemisionesElectronicasEntregasUmuHistorico> getByFiltros(Date fechaInicio, Date fechaFin, String delegacion, String clave, String folioPedido,
            String mesDPN, String umu, String tipoInsumo, String remision, String anoDPN, String nombreUmu, String tipoPedido );
    List<AnoRemisionesElectronicasDTO> getAno();
    List<MesRemisionesElectronicasDTO> getMes();
}
