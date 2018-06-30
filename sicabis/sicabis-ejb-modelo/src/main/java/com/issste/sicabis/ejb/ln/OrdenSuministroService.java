package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.OrdenSuministroDAO;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

@Stateless
@LocalBean
public class OrdenSuministroService {

    @EJB
    private OrdenSuministroDAO ordenSuministroDAOImplement;

    public boolean guardaOrdenSuministro(OrdenSuministro ordenSuministro) {
        return ordenSuministroDAOImplement.guardaOrdenSuministro(ordenSuministro);
    }

    public List<OrdenSuministro> obtenerOrdenesSuministro(OrdenSuministro ordenSuministro, Integer idArea) {
        return ordenSuministroDAOImplement.obtenerOrdenesSuministro(ordenSuministro, idArea);
    }

    public boolean actualizaOrdenSuministro(OrdenSuministro ordenSuministro) {
        return ordenSuministroDAOImplement.actualizaOrdenSuministro(ordenSuministro);
    }

    public List<OrdenSuministro> obtenerByNumeroOrden(String numeroOrden) {
        return ordenSuministroDAOImplement.obtenerByNumeroOrden(numeroOrden);
    }

    public List<OrdenSuministro> obtenerOrdenByNumContrato(Integer idContrato) {
        return ordenSuministroDAOImplement.obtenerOrdenByNumContrato(idContrato);
    }

    public Integer obtenerUltimoIdOrden() {
        return ordenSuministroDAOImplement.obtenerUltimoIdOrden();
    }

    public boolean eliminarPreOrdenesSistema() {
        return ordenSuministroDAOImplement.eliminarPreOrdenesSistema();
    }

    public List<OrdenSuministro> getOrdenByClave(String clave) {
        return ordenSuministroDAOImplement.getOrdenByClave(clave);
    }

    public List<OrdenSuministro> obtenerOrdenByPreOrdenSistema() {
        return ordenSuministroDAOImplement.obtenerOrdenByPreOrdenSistema();
    }

    public List<OrdenSuministro> obtenerOrdenesSuministroByArea(OrdenSuministro ordenSuministro, Integer idArea) {
        return ordenSuministroDAOImplement.obtenerOrdenesSuministroByArea(ordenSuministro, idArea);
    }

}
