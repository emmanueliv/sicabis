/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Cr;
import com.issste.sicabis.ejb.modelo.Pacientes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kriosoft
 */
@Local
public interface PacientesDAO {

    List<Pacientes> obtenerPacientesByCurp(String curp);

    void guardarPaciente(Pacientes paciente);

    List<Pacientes> obtenerPacientes();
    
    List<Pacientes> buscaPacientesTypedQuery(Pacientes pacientes);
    
    Pacientes buscaPacientePorIdPaciente(Integer idPaciente);
    
    List<Pacientes> obtenerPacientesByCurpOrNSS(String curp,String NSS);
}
