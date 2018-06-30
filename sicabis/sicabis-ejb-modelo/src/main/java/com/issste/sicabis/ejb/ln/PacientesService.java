/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.PacientesDAO;
import com.issste.sicabis.ejb.modelo.Pacientes;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kriosoft
 */
@Stateless
public class PacientesService {

    @EJB
    private PacientesDAO pacientesDAO;

    public Pacientes obtenerPacientesByCurp(String curp) {
        List<Pacientes> pacientesList = pacientesDAO.obtenerPacientesByCurp(curp);
        if (pacientesList.isEmpty()) {
            return null;
        }
        return pacientesList.get(0);
    }

    public void guardarPaciente(Pacientes paciente) {
        pacientesDAO.guardarPaciente(paciente);
    }

    public List<Pacientes> obtenerPacientes() {
        return pacientesDAO.obtenerPacientes();
    }

    public List<Pacientes> buscaPacientesTypedQuery(Pacientes paciente) {
        return pacientesDAO.buscaPacientesTypedQuery(paciente);
    }

    public Pacientes buscaPacientePorIdPaciente(Integer idPaciente) {
        return pacientesDAO.buscaPacientePorIdPaciente(idPaciente);
    }

    public List<Pacientes> obtenerPacientesByCurpOrNSS(String curp, String NSS) {
        return pacientesDAO.obtenerPacientesByCurpOrNSS(curp, NSS);
    }

}
