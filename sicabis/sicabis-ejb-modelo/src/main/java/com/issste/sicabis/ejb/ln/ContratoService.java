package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.ContratoDAO;
import com.issste.sicabis.ejb.modelo.Contratos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class ContratoService {

    @EJB
    private ContratoDAO contratoDAOImplement;

    public boolean guardaContrato(Contratos contratos) {
        return contratoDAOImplement.guardaContrato(contratos);
    }

    public boolean actualizaContrato(Contratos contratos) {
        return contratoDAOImplement.actualizaContrato(contratos);
    }

    public List<Contratos> obtenerByNumeroContrato(String numeroContrato) {
        return contratoDAOImplement.obtenerByNumeroContrato(numeroContrato);
    }

    public List<Contratos> obtenerContratos(Contratos contratos) {
        return contratoDAOImplement.obtenerContratos(contratos);
    }

    public List<Contratos> obtenerConvenios(Contratos contratos) {
        return contratoDAOImplement.obtenerConvenios(contratos);
    }

    public List<Contratos> obtenerConvenioByNumeroContratoIdContrato(String numeroContrato, Integer idContrato) {
        return contratoDAOImplement.obtenerConvenioByNumeroContratoIdContrato(numeroContrato, idContrato);
    }

    public List<Contratos> contratoById(Integer id) {
        return contratoDAOImplement.contratoById(id);
    }

    public List<Contratos> obtenerByNumeroConvenio(String numeroConvenio) {
        return contratoDAOImplement.obtenerByNumeroConvenio(numeroConvenio);
    }

    public List<Contratos> contratoConvenioById(Integer id) {
        return contratoDAOImplement.contratoConvenioById(id);
    }

    public Integer obtenerByMaxNumeroContratos() {
        return contratoDAOImplement.obtenerByMaxNumeroContratos();
    }

    public Contratos obtenerByOneNumeroContrato(String numeroContrato) {
        return contratoDAOImplement.obtenerByOneNumeroContrato(numeroContrato);
    }

    public Integer obtenerByMaxNumero() {
        return contratoDAOImplement.obtenerByMaxNumero();
    }

    public Integer obtenerByMaxNumeroConvenio() {
        return contratoDAOImplement.obtenerByMaxNumeroConvenio();
    }

    public List<Contratos> obtenerByNumeroContratoOrderById(String numeroContrato) {
        return contratoDAOImplement.obtenerByNumeroContratoOrderById(numeroContrato);
    }
}
