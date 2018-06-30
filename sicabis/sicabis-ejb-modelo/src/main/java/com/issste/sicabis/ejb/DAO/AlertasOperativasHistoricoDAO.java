/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.AlertasDTO;
import com.issste.sicabis.ejb.modelo.AlertasOperativasHistorico;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 9RZCBG2
 */
@Local
public interface AlertasOperativasHistoricoDAO {

    boolean guardar(AlertasOperativasHistorico aoh);

    List<AlertasOperativasHistorico> getByFechaIngreso(Date fechaIngreso);

    List<AlertasDTO> getAll();
}
