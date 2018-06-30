package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.modelo.Dpn;
import com.issste.sicabis.ejb.modelo.DpnInsumoTmp;
import com.issste.sicabis.ejb.modelo.DpnInsumos;
import com.issste.sicabis.ejb.modelo.InsumoDpn;
import com.issste.sicabis.ejb.modelo.UnidadInsumosDpn;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DpnInsumosDAOImplement implements DpnInsumosDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public boolean guardaActualiza(DpnInsumos dpnInsumos) {
        try {
            if (dpnInsumos.getIdDpnInsumo() == null) {
                System.out.println("guarda");
                em.persist(dpnInsumos);
            } else {
                System.out.println("actualiza");
                em.merge(dpnInsumos);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public List<DpnInsumos> getListaAll(String clave, String claveUmu) {
        List<DpnInsumos> resultList = null;
        DpnInsumos di = null;
        String query = "";
        boolean bandera = false;
        try {
            query = "  SELECT di \n"
                    + "  FROM DpnInsumos di \n"
                    + " WHERE di.previo = 1 \n";
            if (!clave.equals("")) {
                query = query + "AND di.claveInsumo = '" + clave + "' \n";
            }
            if (!claveUmu.equals("-1")) {
                query = query + "AND di.claveUnidad = '" + claveUmu + "' \n";
            }
            //System.out.println("dpinsumos-->" + query);
            resultList = em.createQuery(query).getResultList();
//            resultList = new ArrayList();
//            for (Object[] result : results) {
//                di = new DpnInsumos();
//                di.setClaveInsumo(String.valueOf(result[0]));
//                di.setClaveUnidad(String.valueOf(result[1]));
//                di.setExistenciasSiam(Integer.parseInt(String.valueOf(result[2])));
//                di.setExistenciasCenadi(Integer.parseInt(String.valueOf(result[3])));
//                resultList.add(di);
//            }
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public DpnInsumos getByIdDpnClaveUmu(String clave, String claveUmu, Integer previo) {
        List<DpnInsumos> resultList = null;
        try {
            //Query query = em.createQuery("SELECT di FROM DpnInsumos di WHERE di.idDpn.idDpn = :idDpn AND di.claveInsumo = :clave AND di.claveUnidad = :claveUmu");
            Query query = em.createQuery("SELECT di FROM DpnInsumos di WHERE di.claveInsumo = :clave AND di.claveUnidad = :claveUmu AND di.activo = 1 AND di.previo = :previo");
            //query.setParameter("idDpn", idDpn);
            query.setParameter("clave", clave);
            query.setParameter("claveUmu", claveUmu);
            query.setParameter("previo", previo);
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public boolean eliminaPrevio() {
        try {
            em.createQuery("DELETE FROM DpnInsumos di WHERE di.previo = 1").executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public Integer getByDpnByInsumo(String clave) {
        Object suma = 0;
        try {
            suma = em.createNativeQuery("SELECT di.piezas_dpn as piezas FROM dpn d \n"
                    + "JOIN dpn_insumo di on di.id_dpn = d.id_dpn "
                    + "WHERE d.id_estatus = 213 \n"
                    + "AND di.clave_insumo = '" + clave + "'\n"
                    + "AND d.activo = 1").getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (suma == null) {
            return 0;
        }
        return Integer.parseInt(String.valueOf(suma));
    }

    @Override
    public Integer getBySumDpnByInsumo(String clave, String clavePresupuestal) {
        Object suma = 0;
        String query = "SELECT Sum(di.piezas_dpn) as piezas FROM dpn_insumo di \n"
                + "WHERE di.clave_insumo = '" + clave + "' AND di.activo = 1 \n";
        if (!clavePresupuestal.equals("")) {
            query = query + "AND di.clave_unidad = '"+clavePresupuestal+"' ";
        }
        try {
            suma = em.createNativeQuery(query).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (suma == null) {
            return 0;
        }
        return Integer.parseInt(String.valueOf(suma));
    }

    @Override
    public DpnInsumos getUltimaDpnByUnidadClave(String clave, String claveUmu) {
        DpnInsumos di = null;
        String query = "";
        try {
            Query q = em.createQuery("SELECT di FROM DpnInsumos di JOIN di.idDpn d WHERE d.activo = 1 AND d.idEstatus.idEstatus = 213 AND di.claveInsumo = :clave AND di.claveUnidad = :claveUmu");
            q.setParameter("clave", clave);
            q.setParameter("claveUmu", claveUmu);
            di = (DpnInsumos) q.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        System.out.println("lista ultima--->" + di);
        if (di != null) {
            return di;
        }
        return null;
    }

    @Override
    public List<DpnInsumos> llenaDpnInsumos(Dpn idDpn, String clave, String claveUmu) {
        List<DpnInsumos> resultList = null;
        DpnInsumos di = null;
        String query = "";
        boolean bandera = false;
        try {
//            query = "  SELECT id.id_insumo_dpn, i.clave, i.descripcion, NVL(i.precio_unitario,0), \n"
//                    + "       um.id_unidades_medicas, um.clave_presupuestal, um.nombre \n"
//                    + "  FROM insumos i join insumo_dpn id on(i.id_insumo = id.id_insumo), \n"
//                    + "       unidades_medicas um \n"
//                    + " WHERE id.estatus_insumo_dpn = 1 \n";

            query = "  SELECT id.id_insumo_dpn, i.clave, i.descripcion, NVL(i.precio_unitario,0), \n"
                    + "       um.id_cat_unidad_medica, um.clave_presupuestal, um.nombre \n"
                    + "  FROM insumos i join insumo_dpn id on(i.id_insumo = id.id_insumo), \n"
                    + "       cat_unidad_medica um \n"
                    + " WHERE id.estatus_insumo_dpn = 1 \n";
            if (!clave.equals("")) {
                query = query + "AND i.clave = '" + clave + "' \n";
            }
            if (!claveUmu.equals("-1")) {
                query = query + "AND um.clave_presupuestal = '" + claveUmu + "' \n";
            }
            query = query + "ORDER BY 2, 6";
            System.out.println("dpinsumos-->" + query);
            List<Object[]> results = em.createNativeQuery(query).getResultList();
            resultList = new ArrayList();
            for (Object[] result : results) {
                if (idDpn != null) {
                    di = this.getByIdDpnClaveUmu(String.valueOf(result[1]), String.valueOf(result[4]), 1);
                    if (di == null) {
                        di = new DpnInsumos();
                        di.setActivo(1);
                        di.setIdInsumoDpn(new InsumoDpn(Integer.parseInt(String.valueOf(result[0]))));
                        di.setClaveInsumo(String.valueOf(result[1]));
                        di.setDescripcionInsumo(String.valueOf(result[2]));
                        di.setPrecioUnitario((BigDecimal) result[3]);
                        di.setIdUnidadesMedicas(new UnidadesMedicas(Integer.parseInt(String.valueOf(result[4]))));
                        di.setClaveUnidad(String.valueOf(result[5]));
                        di.setNombreUnidad(String.valueOf(result[6]));
                        di.setPrevio(1);
                        di.setExistenciasCenadi(0);
                        di.setExistenciasSiam(0);
                        di.setPiezasPropuestasDpn(0);
                        di.setSalidasSiam(0);
                        this.guardaActualiza(di);
                    }
                    di.setIdDpn(idDpn);
                } else {
                    di = new DpnInsumos();
                    di.setActivo(1);
                    di.setIdInsumoDpn(new InsumoDpn(Integer.parseInt(String.valueOf(result[0]))));
                    di.setClaveInsumo(String.valueOf(result[1]));
                    di.setDescripcionInsumo(String.valueOf(result[2]));
                    di.setPrecioUnitario((BigDecimal) result[3]);
                    di.setIdUnidadesMedicas(new UnidadesMedicas(Integer.parseInt(String.valueOf(result[4]))));
                    di.setClaveUnidad(String.valueOf(result[5]));
                    di.setNombreUnidad(String.valueOf(result[6]));
                    di.setPrevio(1);
                    di.setExistenciasCenadi(0);
                    di.setExistenciasSiam(0);
                    di.setPiezasPropuestasDpn(0);
                    di.setSalidasSiam(0);
                    this.guardaActualiza(di);
                }
                resultList.add(di);
            }
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public boolean actualizaDpnInsumosByProcedure(String clave, String claveUmu, Date fechaInicio, Date fechaFin, Date fechaInicio2, Date fechaInicio3) {
        try {
            System.out.println("clave--->" + clave);
            System.out.println("claveUmu--->" + claveUmu);
            if (clave.equals("")) {
                clave = null;
            }
            if (claveUmu.equals("-1")) {
                claveUmu = null;
            }
            Query q = em.createNativeQuery("EXECUTE PROCEDURE sp_calculo_dpn(?, ?, ?, ?, ?, ?)");
            q.setParameter(1, clave);
            q.setParameter(2, claveUmu);
            q.setParameter(3, fechaInicio);
            q.setParameter(4, fechaFin);
            q.setParameter(5, fechaInicio2);
            q.setParameter(6, fechaInicio3);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<DpnInsumos> getByPrevio(Integer previo) {
        List<DpnInsumos> resultList = null;
        try {
            Query query = em.createNamedQuery("DpnInsumos.findByPrevio");
            query.setParameter("previo", previo);
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public boolean actualizaIdDpnPrevio(Dpn dpn) {
        try {
            em.createQuery("UPDATE DpnInsumos di SET di.idDpn = :dpn WHERE di.previo = 1").setParameter("dpn", dpn).executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<DpnInsumos> getListaDpnInsumos(Integer idDpn, String clave, String claveUmu) {
        List<DpnInsumos> resultList = null;
        String query = "";
        try {
            query = "  SELECT di \n"
                    + "  FROM DpnInsumos di \n"
                    + " WHERE di.idDpn.idDpn = " + idDpn + " \n";
            if (!clave.equals("")) {
                query = query + "  AND di.claveInsumo = '" + clave + "' \n";
            }
            if (!claveUmu.equals("-1")) {
                query = query + "  AND di.claveUnidad = '" + claveUmu + "' \n";
            }
            System.out.println("dpinsumos-->" + query);
            resultList = em.createQuery(query).getResultList();
//            if (clave == null || clave.equals("")) {
//                clave = null;
//            }
//            if (claveUmu == null || claveUmu.equals("")) {
//                claveUmu = null;
//            }
//            Query q = em.createNativeQuery("SELECT di \n"
//                    + "  FROM dpn_insumo di \n"
//                    + " WHERE id_dpn = ? \n"
//                    + "   AND (? IS NULL OR di.clave_insumo = ?) \n"
//                    + "   AND (? IS NULL OR di.clave_unidad = ?) \n" ,
//                    DpnInsumos.class);
//            q.setParameter(1, idDpn);
//            q.setParameter(2, clave);
//            q.setParameter(3, clave);
//            q.setParameter(4, claveUmu);
//            q.setParameter(5, claveUmu);
//            resultList = q.getResultList();
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public boolean actualizaDpnInsumos() {
        try {
            em.createQuery("UPDATE DpnInsumos di SET di.previo = 0 WHERE di.previo = 1").executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            return false;
        }
    }

    @Override
    public List<DpnInsumos> llenaDpnInsumosAnterior(Dpn idDpn, String clave, String claveUmu) {
        List<DpnInsumos> resultList = null;
        List<DpnInsumos> dpnInsumoList = null;
        DpnInsumos di = null;
        String query = "";
        boolean bandera = false;
        try {
            query = "  SELECT di.* \n"
                    + "  FROM dpn_insumo di JOIN dpn d ON (di.id_dpn = d.id_dpn) \n"
                    + "       JOIN insumo_dpn id ON (di.id_insumo_dpn = id.id_insumo_dpn) \n"
                    + " WHERE d.activo = 1 \n"
                    + "   AND id.estatus_insumo_dpn = 1 \n";
            if (!clave.equals("")) {
                query = query + "AND di.clave_insumo = '" + clave + "' \n";
            }
            if (!claveUmu.equals("-1")) {
                query = query + "AND di.clave_unidad = '" + claveUmu + "' \n";
            }
            resultList = em.createNativeQuery(query, DpnInsumos.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            dpnInsumoList = new ArrayList();
            for (DpnInsumos r : resultList) {
                if (idDpn != null) {
                    di = this.getByIdDpnClaveUmu(r.getClaveInsumo(), r.getClaveUnidad(), 1);
                    if (di == null) {
                        di = new DpnInsumos();
                        di.setActivo(1);
                        di.setIdInsumoDpn(r.getIdInsumoDpn());
                        di.setClaveInsumo(r.getClaveInsumo());
                        di.setDescripcionInsumo(r.getDescripcionInsumo());
                        di.setPrecioUnitario(r.getPrecioUnitario());
                        di.setIdUnidadesMedicas(r.getIdUnidadesMedicas());
                        di.setClaveUnidad(r.getClaveUnidad());
                        di.setNombreUnidad(r.getNombreUnidad());
                        di.setPrevio(1);
                        di.setExistenciasCenadi(0);
                        di.setExistenciasSiam(0);
                        di.setPiezasPropuestasDpn(0);
                        di.setPiezasDpn(-1);
                        di.setSalidasSiam(0);
                        di.setPiezasDpnAnterior(r.getPiezasDpn());
                        di.setIdDpn(idDpn);
                        this.guardaActualiza(di);
                    }
                } else {
                    di = new DpnInsumos();
                    di.setActivo(1);
                    di.setIdInsumoDpn(r.getIdInsumoDpn());
                    di.setClaveInsumo(r.getClaveInsumo());
                    di.setDescripcionInsumo(r.getDescripcionInsumo());
                    di.setPrecioUnitario(r.getPrecioUnitario());
                    di.setIdUnidadesMedicas(r.getIdUnidadesMedicas());
                    di.setClaveUnidad(r.getClaveUnidad());
                    di.setNombreUnidad(r.getNombreUnidad());
                    di.setPrevio(1);
                    di.setExistenciasCenadi(0);
                    di.setExistenciasSiam(0);
                    di.setPiezasPropuestasDpn(0);
                    di.setPiezasDpn(-1);
                    di.setSalidasSiam(0);
                    di.setPiezasDpnAnterior(r.getPiezasDpn());
                    this.guardaActualiza(di);
                }
                dpnInsumoList.add(di);
            }
            if (dpnInsumoList.size() > 0) {
                return dpnInsumoList;
            }
        }
        return null;
    }

    @Override
    public List<DpnInsumos> llenaDpnInsumosActivos(Dpn idDpn, String clave, String claveUmu) {
        List<UnidadInsumosDpn> resultList = null;
        List<DpnInsumos> dpnInsumoList = null;
        DpnInsumos di = null;
        String query = "";
        boolean bandera = false;
        try {
            if (clave.equals("")) {
                clave = null;
            }
            if (claveUmu.equals("-1")) {
                claveUmu = null;
            }
            query = "  SELECT ui \n"
                    + "  FROM UnidadInsumosDpn ui \n"
                    + " WHERE ui.activo = 1 \n"
                    + "   AND ui.idInsumoDpn.estatusInsumoDpn = 1 \n"
                    + "   AND (:clave IS NULL OR ui.idInsumoDpn.idInsumo.clave = :clave) \n"
                    + "   AND (:claveUmu IS NULL OR ui.idUnidadesMedicas.clavePresupuestal = :claveUmu) \n";
            //+ "GROUP BY ui.idInsumoDpn, ui.idUnidadesMedicas";

            resultList = em.createQuery(query, UnidadInsumosDpn.class).setParameter("clave", clave).setParameter("claveUmu", claveUmu).getResultList();
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        //System.out.println("resultList--->"+resultList);
        if (resultList != null && resultList.size() > 0) {
            dpnInsumoList = new ArrayList();
            for (UnidadInsumosDpn r : resultList) {
                if (idDpn != null) {
                    di = this.getByIdDpnClaveUmu(r.getIdInsumoDpn().getIdInsumo().getClave(), r.getIdUnidadesMedicas().getClavePresupuestal(), 1);
                    if (di == null) {
                        di = new DpnInsumos();
                        di.setActivo(1);
                        di.setIdInsumoDpn(r.getIdInsumoDpn());
                        di.setClaveInsumo(r.getIdInsumoDpn().getIdInsumo().getClave());
                        di.setDescripcionInsumo(r.getIdInsumoDpn().getIdInsumo().getDescripcion());
                        di.setPrecioUnitario(r.getIdInsumoDpn().getIdInsumo().getPrecioUnitario());
                        di.setIdUnidadesMedicas(r.getIdUnidadesMedicas());
                        di.setClaveUnidad(r.getIdUnidadesMedicas().getClavePresupuestal());
                        di.setNombreUnidad(r.getIdUnidadesMedicas().getNombre());
                        di.setPrevio(1);
                        di.setExistenciasCenadi(0);
                        di.setExistenciasSiam(0);
                        di.setPiezasPropuestasDpn(0);
                        di.setPiezasDpn(-1);
                        di.setSalidasSiam(0);
                        DpnInsumos diAnt = this.getUltimaDpnByUnidadClave(di.getClaveInsumo(), di.getClaveUnidad());
                        di.setPiezasDpnAnterior(0);//pendiente
                        if (diAnt != null) {
                            di.setPiezasDpnAnterior(diAnt.getPiezasDpn());
                        }
                        di.setIdDpn(idDpn);
                        this.guardaActualiza(di);
                    }
                } else {
                    di = new DpnInsumos();
                    di.setActivo(1);
                    di.setIdInsumoDpn(r.getIdInsumoDpn());
                    di.setClaveInsumo(r.getIdInsumoDpn().getIdInsumo().getClave());
                    di.setDescripcionInsumo(r.getIdInsumoDpn().getIdInsumo().getDescripcion());
                    di.setPrecioUnitario(r.getIdInsumoDpn().getIdInsumo().getPrecioUnitario());
                    di.setIdUnidadesMedicas(r.getIdUnidadesMedicas());
                    di.setClaveUnidad(r.getIdUnidadesMedicas().getClavePresupuestal());
                    di.setNombreUnidad(r.getIdUnidadesMedicas().getNombre());
                    di.setPrevio(1);
                    di.setExistenciasCenadi(0);
                    di.setExistenciasSiam(0);
                    di.setPiezasPropuestasDpn(0);
                    di.setPiezasDpn(-1);
                    di.setSalidasSiam(0);
                    DpnInsumos diAnt = this.getUltimaDpnByUnidadClave(di.getClaveInsumo(), di.getClaveUnidad());
                    di.setPiezasDpnAnterior(0);//pendiente
                    if (diAnt != null) {
                        di.setPiezasDpnAnterior(diAnt.getPiezasDpn());
                    }
                    this.guardaActualiza(di);
                }
                dpnInsumoList.add(di);
            }
            //System.out.println("dpnInsumoList--->"+dpnInsumoList);
            if (dpnInsumoList.size() > 0) {
                return dpnInsumoList;
            }
        }
        return null;
    }

    @Override
    public List<DpnInsumos> getByUnidadMedicaOrDelegacion(Integer idUnidadMedica, Integer idDelegacion) {
        List<DpnInsumos> listDpnInsumo = null;
        String query = "select * from dpn_insumo di\n"
                + "join unidades_medicas um on um.clave_presupuestal = di.clave_unidad\n"
                + "join delegaciones d on d.id_delegacion = um.id_delegacion\n"
                + "where di.activo = 1\n";
        if (idDelegacion != 0) {
            query = query + "and d.id_delegacion = " + idDelegacion + " ";
        }
        if (idUnidadMedica != 0) {
            query = query + "and um.id_unidades_medicas = " + idUnidadMedica + "";
        }
        try {
            listDpnInsumo = em.createNativeQuery(query, DpnInsumos.class).getResultList();
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (listDpnInsumo == null) {
            return null;
        } else {
            return listDpnInsumo;
        }
    }

    @Override
    public DpnInsumos getByIdDpnClaveInsumoClaveUnidad(Integer idDpn, String clave, String claveUmu) {
        List<DpnInsumos> resultList = null;
        try {
            Query query = em.createQuery("SELECT di FROM DpnInsumos di WHERE di.claveInsumo = :clave AND di.claveUnidad = :claveUmu AND di.idDpn.idDpn = :idDpn ");
            query.setParameter("idDpn", idDpn);
            query.setParameter("clave", clave);
            query.setParameter("claveUmu", claveUmu);
            resultList = query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.ALL.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public boolean guardaTmp(DpnInsumoTmp dpnInsumoTmp) {
        try {
            Query q1 = em.createNativeQuery("DELETE FROM dpn_insumo_tmp");
            q1.executeUpdate();
            em.persist(dpnInsumoTmp);
            return true;
        } catch (Exception e) {
            Logger.getLogger(DpnInsumosDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

}
