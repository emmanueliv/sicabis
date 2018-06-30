package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Lote;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fabianvr
 */
@Local
public interface LoteDAO {

    boolean guardarLotes(Lote lote);
    List<Lote> loteByRemision(Integer remision);
    Date loteByClave(String clave, String lote);
    List<Lote> buscarLotes();
    List<Lote> getLoteByCve(String nombreLote);
    List<Lote> lote(String lote);
    List<Lote> loteByLoteInsumo(String lote, String clave);
    Lote getByIdLote(Integer idLote);
}
