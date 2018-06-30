/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.EntradasMymcqCenadiHistoricoDAO;
import com.issste.sicabis.ejb.modelo.EntradasMymcqCenadiHistorico;
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
public class EntradasMymcqCenadiHistoricoService {

    @EJB
    private EntradasMymcqCenadiHistoricoDAO entradasMymcqCenadiHistoricoDAOImplement;

    public boolean guardar(EntradasMymcqCenadiHistorico emch) {
        return entradasMymcqCenadiHistoricoDAOImplement.guardar(emch);
    }

    public List<EntradasMymcqCenadiHistorico> getByFechaIngreso(Date fechaIngreso) {
        return entradasMymcqCenadiHistoricoDAOImplement.getByFechaIngreso(fechaIngreso);
    }

    public List<EntradasMymcqCenadiHistorico> getAll() {
        return entradasMymcqCenadiHistoricoDAOImplement.getAll();
    }
    
    public List<String> getDistinctProveedor() {
        return entradasMymcqCenadiHistoricoDAOImplement.getDistinctProveedor();
    }
    
    public List<String> getDistinctRegistroControl() {
        return entradasMymcqCenadiHistoricoDAOImplement.getDistinctRegistroControl();
    }
    
    public List<String> getDistinctTipoEntrada() {
        return entradasMymcqCenadiHistoricoDAOImplement.getDistinctTipoEntrada();
    }
    
    public List<String> getDistinctOcOracle() {
        return entradasMymcqCenadiHistoricoDAOImplement.getDistinctOcOracle();
    }
    
    public List<String> getDistinctClave() {
        return entradasMymcqCenadiHistoricoDAOImplement.getDistinctClave();
    }
    
}
