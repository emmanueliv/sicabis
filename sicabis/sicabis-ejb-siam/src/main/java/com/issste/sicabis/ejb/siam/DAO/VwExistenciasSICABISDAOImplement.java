package com.issste.sicabis.ejb.siam.DAO;

import com.issste.sicabis.ejb.ln.ConsumoDiarioSiamService;
import com.issste.sicabis.ejb.modelo.ConsumoDiarioSiam;
import com.issste.sicabis.ejb.siam.modelo.VwExistenciasSICABIS;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class VwExistenciasSICABISDAOImplement implements VwExistenciasSICABISDAO {

    @EJB
    private ConsumoDiarioSiamService consumoDiarioSiamService;

    @PersistenceContext(unitName = "siam")
    private EntityManager em;

    @Override
    public Integer obtenerSumExistenciasByClave(String clave) {
        Integer sumaExistencias = 0;
        Date hoy = new Date();
        try {
            Query q = em.createNativeQuery("select clave_insumo, SUM(Existencia_Corte) \n"
                    + "from vwExistenciasSICABIS \n"
                    + "where clave_insumo = '" + clave + "' \n"
                    + "and fecha_corte = (select Max(fecha_corte) \n"
                    + "                   from vwExistenciasSICABIS \n"
                    + "                   where clave_insumo = '" + clave + "') \n"
                    + "group by clave_insumo");
            q.setParameter("clave", clave);
            List<Object[]> results = q.getResultList();
            for (Object[] result : results) {
                sumaExistencias = Integer.parseInt(String.valueOf(result[1]));
            }
        } catch (Exception e) {
            Logger.getLogger(VwExistenciasSICABISDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (sumaExistencias != 0) {
            return sumaExistencias;
        }
        System.out.println("vacio");
        return 0;
    }

    @Override
    public List<VwExistenciasSICABIS> getByUMUClaveFecha(String clave, String claveUmu, Date fecha, Date fechaIni, Date fechaFin) {
        VwExistenciasSICABIS existSiam = null;
        List<VwExistenciasSICABIS> listaExistSiam = null;
        String sQuery = "";
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        try {
            sQuery = " SELECT clave_insumo, replace(clave_unidad, '-','') as clave_unidad, \n"
                    + "       SUM(Existencia_Corte) existencias \n"
                    + "  FROM vwExistenciasSICABIS \n"
                    + " WHERE convert(date,fecha_corte,112) = '" + formato.format(new Date()) + "' \n";
            if (!clave.equals("")) {
                sQuery = sQuery + "AND clave_insumo = '" + clave + "' \n";
            }
            if (!claveUmu.equals("-1")) {
                sQuery = sQuery + "AND clave_unidad = '" + claveUmu + "' \n";
            }
            sQuery = sQuery + "GROUP BY clave_insumo,clave_unidad \n"
                    + "ORDER BY clave_insumo,clave_unidad";
            System.out.println("query2--->" + sQuery);
            Query q = em.createNativeQuery(sQuery);
            List<Object[]> results = q.getResultList();
            listaExistSiam = new ArrayList();
            for (Object[] result : results) {
                existSiam = new VwExistenciasSICABIS();
                existSiam.setClaveInsumo(String.valueOf(result[0]));
                existSiam.setClaveUnidad(String.valueOf(result[1]));
                existSiam.setExistenciaCorte(Integer.parseInt(String.valueOf(result[2])));
                listaExistSiam.add(existSiam);
            }
        } catch (Exception e) {
            Logger.getLogger(VwExistenciasSICABISDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        if (listaExistSiam != null && listaExistSiam.size() > 0) {
            return listaExistSiam;
        }
        return null;
    }

    @Override
    public Integer getSumaConsumo(String clave, String umu, Date fechaIni, Date fechafin) {
        Integer sumaConsumo = 0;
        String sQuery = "";
        try {
            sQuery = " SELECT SUM(cantidad)\n"
                    + "FROM documento d join movimientos m ON (d.id = m.id_documento)\n"
                    + "     JOIN centros_trabajo ct ON (d.id_centro_trabajo = ct.id)\n"
                    + "	 JOIN insumos i ON (i.id = m.id_insumo)\n"
                    + "	 JOIN tipos_movimientos tm ON (tm.id = d.id_tipo_movimiento)\n"
                    + "WHERE tm.tipo ='S'\n"
                    + "  AND tm.id not in(14,15,16,17,18,19,20,23,24,26,27,46,49,50,\n"
                    + "                   53,57,60) \n"
                    + "  AND i.clave = '" + clave + "' \n"
                    + "  AND ct.clave = '" + umu + "' \n"
                    + "  AND convert(date,d.fecha,112) between '20170423' and '20170423'";
            Query q = em.createNativeQuery(sQuery);
            List<Object[]> results = q.getResultList();
            for (Object[] result : results) {
                sumaConsumo = Integer.parseInt(String.valueOf(result[0]));
                break;
            }
        } catch (Exception e) {
            Logger.getLogger(VwExistenciasSICABISDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
        return sumaConsumo;
    }

    @Override
    public void cargaConsumoDiario(String fechaInicio) {
        List<ConsumoDiarioSiam> listaConsumo = null;
        ConsumoDiarioSiam cds = null;
        String sQuery = "";
        try {
            sQuery = " select replace(ct.clave, '-','') as umu, i.clave as insumo, convert(date,d.fecha,112) as fecha, sum(cantidad) as consumo \n"
                    + "from documentos d WITH(NOLOCK) join movimientos m WITH(NOLOCK) on (d.id = m.id_documento) \n"
                    + "     join centros_trabajo ct WITH(NOLOCK) on (d.id_centro_trabajo = ct.id) \n"
                    + "	 join insumos i WITH(NOLOCK) on (i.id = m.id_insumo) \n"
                    + "	 join tipos_movimientos tm WITH(NOLOCK) on (tm.id = d.id_tipo_movimiento) \n"
                    + "where tm.tipo ='S' \n"
                    + "  and tm.id not in(14,15,16,17,18,19,20,23,24,26,27,46,49,50, \n"
                    + "                   53,57,60) \n"
                    + "  and d.fecha = '" + fechaInicio + "' \n"
                    //                    + "  and d.fecha between '20170426' and '20170529' \n"
                    + "  group by convert(date,d.fecha,112), ct.clave, i.clave ";
            Query q = em.createNativeQuery(sQuery);
            List<Object[]> results = q.getResultList();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date createdDate = null;
            boolean band = true;//consumoDiarioSiamService.eliminaTodo();
            for (Object[] result : results) {
                cds = new ConsumoDiarioSiam();
                cds.setClaveUnidad(String.valueOf(result[0]));
                //cds.setClaveUnidad(cds.getClaveUnidad().replaceAll("-", ""));
                cds.setClaveInsumo(String.valueOf(result[1]));
                cds.setFecha((Date) result[2]);
                cds.setConsumo(Integer.parseInt(String.valueOf(result[3])));
                band = consumoDiarioSiamService.guarda(cds);
            }
        } catch (Exception e) {
            Logger.getLogger(VwExistenciasSICABISDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
    }

    @Override
    public void cargaConsumoDiario3(String fechaInicio) {
        List<ConsumoDiarioSiam> listaConsumo = null;
        ConsumoDiarioSiam cds = null;
        String sQuery = "";
        try {
            sQuery = " select replace(ct.clave, '-','') as umu, i.clave as insumo, convert(date,d.fecha,112) as fecha, sum(cantidad) as consumo \n"
                    + "from documentos d WITH(NOLOCK) join movimientos m WITH(NOLOCK) on (d.id = m.id_documento) \n"
                    + "     join centros_trabajo ct WITH(NOLOCK) on (d.id_centro_trabajo = ct.id) \n"
                    + "	 join insumos i WITH(NOLOCK) on (i.id = m.id_insumo) \n"
                    + "	 join tipos_movimientos tm WITH(NOLOCK) on (tm.id = d.id_tipo_movimiento) \n"
                    + "where tm.tipo ='S' \n"
                    + "  and tm.id not in(14,15,16,17,18,19,20,23,24,26,27,46,49,50, \n"
                    + "                   53,57,60) \n"
                    + "  and d.fecha > '20170901' \n"
                    //                    + "  and d.fecha between '20170520' and '20170822' \n"
                    + "  group by convert(date,d.fecha,112), ct.clave, i.clave ";
            System.out.println("query--->" + sQuery);
            Query q = em.createNativeQuery(sQuery);
            List<Object[]> results = q.getResultList();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date createdDate = null;
            boolean band = consumoDiarioSiamService.eliminaTodo();
            for (Object[] result : results) {
                cds = new ConsumoDiarioSiam();
                cds.setClaveUnidad(String.valueOf(result[0]));
                //cds.setClaveUnidad(cds.getClaveUnidad().replaceAll("-", ""));
                cds.setClaveInsumo(String.valueOf(result[1]));
                cds.setFecha((Date) result[2]);
                cds.setConsumo(Integer.parseInt(String.valueOf(result[3])));
                band = consumoDiarioSiamService.guarda(cds);
                System.out.println("inserte--->");
            }
        } catch (Exception e) {
            Logger.getLogger(VwExistenciasSICABISDAOImplement.class.getName()).log(Level.SEVERE, "", e);
        }
    }

    @Override
    public String cargaConsumoDiarioAux(String fechaInicio, String fechaFin) {
        String avance = "entre metodo";
        List<ConsumoDiarioSiam> listaConsumo = null;
        ConsumoDiarioSiam cds = null;
        String sQuery = "";
        try {
            sQuery = " select replace(ct.clave, '-','') as umu, i.clave as insumo, convert(date,d.fecha,112) as fecha, sum(cantidad) as consumo \n"
                    + "from documentos d WITH(NOLOCK) join movimientos m WITH(NOLOCK) on (d.id = m.id_documento) \n"
                    + "     join centros_trabajo ct WITH(NOLOCK) on (d.id_centro_trabajo = ct.id) \n"
                    + "	 join insumos i WITH(NOLOCK) on (i.id = m.id_insumo) \n"
                    + "	 join tipos_movimientos tm WITH(NOLOCK) on (tm.id = d.id_tipo_movimiento) \n"
                    + "where tm.tipo ='S' \n"
                    + "  and tm.id not in(14,15,16,17,18,19,20,23,24,26,27,46,49,50, \n"
                    + "                   53,57,60) \n"
                    + "  and d.fecha >= '" + fechaInicio + "' \n"
                    + "  and d.fecha <= '" + fechaFin + "' \n"
                    + "  group by convert(date,d.fecha,112), ct.clave, i.clave ";
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyyMMdd");
            Date fini = null;
            Date ffin = null;
            try {
                fini = formatoDelTexto.parse(fechaInicio);
                ffin = formatoDelTexto.parse(fechaFin);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            boolean band = consumoDiarioSiamService.eliminaFecha(fini, ffin);
            System.out.println("borre--->" + band);
            avance = "borre--->" + band;
            System.out.println("query--->" + sQuery);
            Query q = em.createNativeQuery(sQuery);
            List<Object[]> results = q.getResultList();
            avance = "execute consulta";
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            for (Object[] result : results) {
                cds = new ConsumoDiarioSiam();
                cds.setClaveUnidad(String.valueOf(result[0]));
                cds.setClaveInsumo(String.valueOf(result[1]));
                cds.setFecha((Date) result[2]);
                cds.setConsumo(Integer.parseInt(String.valueOf(result[3])));
                band = consumoDiarioSiamService.guarda(cds);
                System.out.println("inserte consumoAux--->");
                avance = "inserte consumoAux--->" + cds.getIdConsumoDiarioSiam();
            }
            if (!results.isEmpty()) {
                avance = "termine guardar---" + results.size();
            }
        } catch (Exception e) {
            Logger.getLogger(VwExistenciasSICABISDAOImplement.class.getName()).log(Level.SEVERE, "", e);
            avance = "error---->" + e;
        }
        return avance;
    }

}
