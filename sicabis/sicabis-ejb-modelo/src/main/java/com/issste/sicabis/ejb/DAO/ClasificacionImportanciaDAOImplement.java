package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ClasificacionImportancia;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClasificacionImportanciaDAOImplement implements ClasificacionImportanciaDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public void guardarClasificacionImportancia(ClasificacionImportancia clasificacionImportancia) {
        try {
            if (clasificacionImportancia.getIdClasificacionImportancia() == null) {
                em.persist(clasificacionImportancia);
            } else {
                em.merge(clasificacionImportancia);
            }
        } catch (Exception e) {
            Logger.getLogger(ClasificacionImportanciaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
    }

    @Override
    public List<ClasificacionImportancia> obtenerByClasificacion(ClasificacionImportancia clasificacionImportancia) {
        List<ClasificacionImportancia> resultList = null;
        String query = "";
        boolean bandera = false;
        try {
            query = "  Select ci \n"
                    + "  From ClasificacionImportancia ci \n"
                    + " Where ci.activo = 1 \n";

            if (clasificacionImportancia.getSigla() != null && !clasificacionImportancia.getSigla().equals("")) {
                query = query + "   and ci.sigla = '" + clasificacionImportancia.getSigla() + "' \n";
            }
            if (clasificacionImportancia.getDescripcion() != null && !clasificacionImportancia.getDescripcion().equals("")) {
                query = query + "   and ci.descripcion = '" + clasificacionImportancia.getDescripcion() + "' \n";
            }
            resultList = em.createQuery(query).getResultList();
        } catch (Exception e) {
            Logger.getLogger(ClasificacionImportanciaDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public Integer obtenerUltimoRegistro() {
        return em.createNativeQuery("SELECT FIRST 1 id_clasificacion_importancia FROM clasificacion_importancia ORDER BY id_clasificacion_importancia DESC").getFirstResult();
    }

}
