/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Contratos;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.TipoConvenio;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
@LocalBean
public class ContratoFalloProcedimientoRcbDAOImplement implements ContratoFalloProcedimientoRcbDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean borrarByIdContrato(Integer idContrato) {
        try {
            Query query = em.createQuery("DELETE FROM ContratoFalloProcedimientoRcb cfpr WHERE cfpr.idContrato.idContrato = :idContrato");
            query.setParameter("idContrato", idContrato).executeUpdate();
            em.flush();
            em.getEntityManagerFactory().getCache().evictAll();
        } catch (Exception e) {
            Logger.getLogger(ContratoFalloProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
        return true;
    }

    @Override
    public List<ContratoFalloProcedimientoRcb> obtenerByClaves(String claveInsumo) {
        List<ContratoFalloProcedimientoRcb> cfpr = null;
        try {
            Query i = em.createQuery("Select cfpr \n"
                    + "From Contratos c Join Fetch c.idContratoFalloProcedimientoRcbList cfpr \n"
                    + "Where c.activo = 1 \n"
                    + "  and c.idEstatus.idEstatus != 51 \n"
                    + "  and c.idPadre = 0 \n"
                    + "  and cfpr.activo = 1 \n"
                    + "  and cfpr.idFalloProcedimientoRcb.idProcedimientoRcb.idRcbInsumos.claveInsumo = :claveInsumo ")
                    .setParameter("claveInsumo", claveInsumo);

            cfpr = i.getResultList();
        } catch (Exception e) {
            Logger.getLogger(ContratoFalloProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (cfpr != null && cfpr.size() > 0) {
            return cfpr;
        }
        return null;
    }

    @Override
    public List<ContratoFalloProcedimientoRcb> getContratoByIdFallo(int idFallo) {
        List<Object[]> objectList = null;
        List<ContratoFalloProcedimientoRcb> listContratoFalloProcedimientoRcb = null;
        ContratoFalloProcedimientoRcb contratoFalloProcedimientoRcb = null;
        Contratos contratos = null;
        Query query;
        String sQuery = "";
        sQuery = "select ctfp.id_contrato,c.numero_contrato,c.numero_convenio \n"
                + "from contrato_fallo_procedimiento_rcb ctfp\n"
                + "join fallo_procedimiento_rcb fpr on fpr.id_fallo_procedimiento_rcb = ctfp.id_fallo_procedimiento_rcb\n"
                + "join contratos c on c.id_contrato = ctfp.id_contrato\n"
                + "join fallos f on f.id_fallo = fpr.id_fallo\n"
                + "where f.id_fallo= " + idFallo + " ";
        try {
            query = em.createNativeQuery(sQuery);
            objectList = query.getResultList();
            listContratoFalloProcedimientoRcb = new ArrayList();
            for (Object result[] : objectList) {
                System.out.println("dentro del ciclo");
                contratoFalloProcedimientoRcb = new ContratoFalloProcedimientoRcb();
                contratos = new Contratos();
                int contrato = Integer.parseInt(String.valueOf(result[0]));
                contratos.setIdContrato(contrato);
                contratos.setNumeroContrato(String.valueOf(result[1]));
                contratos.setNumeroConvenio(String.valueOf(result[2]));
                contratoFalloProcedimientoRcb.setIdContrato(contratos);
                listContratoFalloProcedimientoRcb.add(contratoFalloProcedimientoRcb);
            }
            return listContratoFalloProcedimientoRcb;
        } catch (Exception e) {
            Logger.getLogger(ContratoFalloProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public List<ContratoFalloProcedimientoRcb> obtenerCfprByClave(String claveInsumo) {
        List<ContratoFalloProcedimientoRcb> cfpr = null;
        try {
            Query i = em.createQuery("Select cfpr \n"
                    + "From Contratos c Join Fetch c.idContratoFalloProcedimientoRcbList cfpr \n"
                    + "Where c.activo = 1 \n"
                    + "  and c.idEstatus.idEstatus != 51\n"
                    + "  and c.idPadre = 0 \n"
                    + "  and cfpr.activo = 1 \n"
                    + "  and cfpr.idFalloProcedimientoRcb.completadoContrato = 0"
                    + "  and cfpr.idFalloProcedimientoRcb.idProcedimientoRcb.idRcbInsumos.claveInsumo = :claveInsumo "
                    + "  and cfpr.idFalloProcedimientoRcb.activo = 1"
                    + "  order by c.fechaContrato")
                    .setParameter("claveInsumo", claveInsumo);

            cfpr = i.getResultList();
        } catch (Exception e) {
            Logger.getLogger(ContratoFalloProcedimientoRcbDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (cfpr != null && cfpr.size() > 0) {
            return cfpr;
        }
        return null;
    }

}
