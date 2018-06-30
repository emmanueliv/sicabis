
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Contratos;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ContratoDAO {
    
    boolean guardaContrato(Contratos contratos);
    boolean actualizaContrato(Contratos contratos);
    List<Contratos> obtenerByNumeroContrato(String numeroContrato);
    List<Contratos> obtenerContratos(Contratos contratos);
    List<Contratos> obtenerConvenios(Contratos contratos);
    List<Contratos> contratoById(Integer id);    
    List<Contratos> contratoConvenioById(Integer id);
    List<Contratos> obtenerConvenioByNumeroContratoIdContrato(String numeroContrato, Integer idContrato);
    List<Contratos> obtenerByNumeroConvenio(String numeroConvenio);
    Integer obtenerByMaxNumeroContratos();
    Contratos obtenerByOneNumeroContrato(String numeroContrato);
    Integer obtenerByMaxNumero();
    Integer obtenerByMaxNumeroConvenio();
    List<Contratos> obtenerByNumeroContratoOrderById(String numeroContrato);
    
}
