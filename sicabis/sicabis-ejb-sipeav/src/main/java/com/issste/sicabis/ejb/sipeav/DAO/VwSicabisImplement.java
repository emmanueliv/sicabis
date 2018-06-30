package com.issste.sicabis.ejb.sipeav.DAO;

import com.issste.sicabis.ejb.sipeav.modelo.Sicabis;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class VwSicabisImplement implements VwSicabis {

    @PersistenceContext(unitName = "sipeav")
    private EntityManager em;

    @Override
    public List<Sicabis> obtenerByNssCurp(String nss, String curp) {
        List<Sicabis> resultList = null;
        String query = "";
        String condicion = "";
        Integer numIssste = 0;
        try {
            if (!(curp == null || curp.isEmpty() || curp.trim().isEmpty())) {
                condicion = condicion + "and s.curpTra = '" + curp + "'";
            }
            if (!(nss == null || nss.isEmpty() || nss.trim().isEmpty())) {
                numIssste = Integer.parseInt(nss);
                condicion = condicion + "and s.numIssste = " + new BigDecimal(numIssste) + " ";
            }
            resultList = em.createQuery("Select s From Sicabis s Where 1=1 " + condicion).getResultList();
        } catch (Exception e) {
            Logger.getLogger(VwSicabisImplement.class.getName()).log(Level.SEVERE, "Error", e);
            return null;
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<Sicabis> obtenerBeneficiarios(String nss) {
        List<Object[]> objectList = null;
        List<Sicabis> listaSicabis = null;
        Sicabis sicabis = null;
        Query query;
        String sQuery = "";
        String condicion = "\n";
        Integer numIssste = Integer.parseInt(nss);
        sQuery = " Select s.nombre, s.apellido_paterno, s.apellido_materno, s.curp,\n"
                + "       s.parentesco_cve, s.ito_id "
                + "From sicabis s "
                + "Where num_issste = " + new BigDecimal(numIssste)
                + "Order by s.ito_id ";

        try {
            query = em.createNativeQuery(sQuery);
            objectList = query.getResultList();
            listaSicabis = new ArrayList();
            for (Object[] result : objectList) {
                sicabis = new Sicabis();
                sicabis.setNombre(String.valueOf(result[0]));
                sicabis.setApellidoPaterno(String.valueOf(result[1]));
                sicabis.setApellidoMaterno(String.valueOf(result[2]));
                sicabis.setCurp(String.valueOf(result[3]));
                sicabis.setParentescoCve(new BigDecimal(Integer.parseInt(String.valueOf(result[4]))));
                sicabis.setItoId(Double.parseDouble(String .valueOf(result[5])));
                listaSicabis.add(sicabis);
            }
        } catch (Exception e) {
            Logger.getLogger(VwSicabisImplement.class.getName()).log(Level.SEVERE, "Error", e);
            return null;
        }
        if (listaSicabis != null && listaSicabis.size() > 0) {
            return listaSicabis;
        }
        return null;
    }

}
