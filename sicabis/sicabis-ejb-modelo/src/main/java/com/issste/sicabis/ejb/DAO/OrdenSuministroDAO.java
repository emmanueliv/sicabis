
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import java.util.List;
import javax.ejb.Local;

@Local
public interface OrdenSuministroDAO {
    
    List<OrdenSuministro> obtenerOrdenesSuministroByArea(OrdenSuministro ordenSuministro, Integer idArea);
    boolean guardaOrdenSuministro(OrdenSuministro ordenSuministro);
    List<OrdenSuministro> obtenerOrdenesSuministro(OrdenSuministro ordenSuministro,Integer idArea);
    boolean actualizaOrdenSuministro(OrdenSuministro ordenSuministro);
    List<OrdenSuministro> obtenerByNumeroOrden(String numeroOrden);
    List<OrdenSuministro> obtenerOrdenByNumContrato(Integer idContrato);
    List<OrdenSuministro> obtenerOrdenByPreOrdenSistema();
    Integer obtenerUltimoIdOrden();
    boolean eliminarPreOrdenesSistema();
    List<OrdenSuministro> getOrdenByClave(String clave);
    
}
