package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.ContratoOrdenDTO;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Remisiones;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author fabianvr
 */
@Stateless
public class DetalleOrdenSuministroDAOImplement implements DetalleOrdenSuministroDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<DetalleOrdenSuministro> cancelaciones(String criterio) {
        List<DetalleOrdenSuministro> Insumo = null;
        Date hoy = new Date();
        List<Remisiones> remi;
        System.out.println("hoy" + hoy);
        try {
            Query i = em.createQuery("Select dos From DetalleOrdenSuministro dos "
                    + "Join dos.idOrdenSuministro os "
                    + "Join dos.idFalloProcedimientoRcb fpr "
                    + "Join fpr.idProcedimientoRcb pr "
                    + "Join pr.idRcbInsumos ri "
                    + "Join ri.idInsumo i "
                    + "Where os.numeroOrden = '" + criterio + "' "
                    + "And :time > dos.fechaEntregaFinal ");
            i.setParameter("time", hoy, TemporalType.DATE);
            System.out.println("em" + i);
            Insumo = i.getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (Insumo != null && Insumo.size() > 0) {
            return Insumo;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<DetalleOrdenSuministro> detalle(Integer idDetalle) {
        List<DetalleOrdenSuministro> Insumo = null;
        try {
            Query i = em.createQuery("Select dos From DetalleOrdenSuministro dos "
                    + "Where dos.idDetalleOrdenSuministro = " + idDetalle + " ");

            System.out.println("em" + i);
            Insumo = i.getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (Insumo != null && Insumo.size() > 0) {
            return Insumo;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public double porcentajeIncumplimientoProveedor(Integer proveedor) {
        double Insumo = 0.0;
        Date hoy = new Date();
        try {
            Query i = em.createQuery("Select 100 - ((Sum((dos.cantidadSuministrada * 100)/dos.cantidadSuministrar))/ Count(dos.idDetalleOrdenSuministro)) "
                    + "From DetalleOrdenSuministro dos "
                    + "Join dos.idFalloProcedimientoRcb fpr "
                    + "Join fpr.idProveedor p "
                    + "Where p.idProveedor = " + proveedor + " "
                    + "And :time > dos.fechaEntregaFinal ");
            i.setParameter("time", hoy, TemporalType.DATE);
            Insumo = ((Long) i.getSingleResult()).doubleValue();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (Insumo != 0.0) {
            return Insumo;
        }
        System.out.println("vacio");
        return 0.0;
    }

    @Override
    public boolean actualizar(DetalleOrdenSuministro dos) {
        try {
            em.merge(dos);
            return true;
        } catch (Exception e) {
            Logger.getLogger(DetalleOrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public Integer obtenerByIdFalloProcRcb(Integer idFalloProcedimientoRcb) {
        long suma = 0;
        Integer cantidadSuministrada = 0;
        try {
            suma = (Long) em.createQuery("Select SUM(dos.cantidadSuministrar - dos.cantidadSuministrada) From DetalleOrdenSuministro dos Where dos.activo = 1 and dos.idOrdenSuministro.idEstatus.idEstatus = 73 and dos.idFalloProcedimientoRcb.idFalloProcedimientoRcb = :idFalloProcedimientoRcb ").setParameter("idFalloProcedimientoRcb", idFalloProcedimientoRcb).getSingleResult();
        } catch (NullPointerException e) {
            //Logger.getLogger(DetalleOrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return 0;
        } catch (Exception e) {
            Logger.getLogger(DetalleOrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return -1;
        }
        cantidadSuministrada = (int) (long) suma;
        System.out.println("cantidad--->" + cantidadSuministrada);
        if (cantidadSuministrada == null) {
            return 0;
        }
        return cantidadSuministrada;
    }

    @Override
    public Integer cantidadPendientePorContratoInsumo(Integer idInsumo) {
        Integer result = 0;
        try {
            Query i = em.createNativeQuery("select Sum(dos.cantidad_suministrar - dos.cantidad_suministrada) from detalle_orden_suministro dos\n"
                    + "join fallo_procedimiento_rcb fpr on fpr.id_fallo_procedimiento_rcb = dos.id_fallo_procedimiento_rcb\n"
                    + "join procedimiento_rcb pr on pr.id_procedimiento_rcb = fpr.id_procedimiento_rcb\n"
                    + "join rcb_insumos ri on ri.id_rcb_insumos = pr.id_rcb_insumos\n"
                    + "where ri.id_insumo = "+idInsumo+"");
            result = (Integer) i.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(DetalleOrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (result != null && result > 0) {
            return result;
        }
        System.out.println("cantidadPendientePorContratoInsumo vacio");
        return 0;
    }

    @Override
    public List<DetalleOrdenSuministro> detalleOrdenById(Integer id) {
        List<DetalleOrdenSuministro> Insumo = null;
        try {
            Query i = em.createQuery("Select dos From DetalleOrdenSuministro dos "
                    + "Where dos.idOrdenSuministro.idOrdenSuministro = " + id + " ");

            System.out.println("em" + i);
            Insumo = i.getResultList();
        } catch (Exception e) {
            Logger.getLogger(DetalleOrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (Insumo != null && Insumo.size() > 0) {
            return Insumo;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<ContratoOrdenDTO> obtenerDisponibleByClave(String clave_insumo, Integer idArea) {
        List<Object[]> objectList = null;
        List<ContratoOrdenDTO> listaDTOs = null;
        ContratoOrdenDTO dto = null;
        Query query;
        String sQuery = "";
        String condicion = "\n";
        if (!clave_insumo.equals("")) {
            condicion = "    and ri.clave_insumo = '" + clave_insumo + "' \n";
        }
        if (idArea != -1) {
            condicion = "    and r.id_area = " + idArea + " \n";
        }
        sQuery = "  select ri.clave_insumo, ri.descripcion_insumo, sum(fpr.cantidad_modificada + fpr.cantidad_agregada_convenio) as total,\n"
                + "        sum (NVL(fpr.suministrado_orden,0)) as suministrado_orden\n"
                + "   from contratos c, contrato_fallo_procedimiento_rcb cfpr, \n"
                + "        fallo_procedimiento_rcb fpr,\n"
                + "        procedimiento_rcb pr, rcb_insumos ri,rcb r\n"
                + "  where c.id_contrato = cfpr.id_contrato\n"
                + "    and cfpr.id_fallo_procedimiento_rcb = fpr.id_fallo_procedimiento_rcb\n"
                + "    and fpr.id_procedimiento_rcb = pr.id_procedimiento_rcb\n"
                + "    and pr.id_rcb_insumos = ri.id_rcb_insumos\n"
                + "    and ri.id_rcb = r.id_rcb\n"
                + "    and c.activo = 1\n"
                + "    and fpr.activo = 1\n"
                + "    and c.id_estatus != 51 \n"
                + "    and c.id_estatus != 58 \n"
                + "    and c.id_padre = 0\n"
                + "    and fpr.completado_contrato = 0"
                + condicion
                + "group by ri.clave_insumo, ri.descripcion_insumo";
        try {
            System.out.println("sQuery--->" + sQuery);
            query = em.createNativeQuery(sQuery);
            objectList = query.getResultList();
            listaDTOs = new ArrayList();
            for (Object[] result : objectList) {
                dto = new ContratoOrdenDTO();
                dto.setClaveInsumo(String.valueOf(result[0]));
                dto.setDescripcionInsumo(String.valueOf(result[1]));
                dto.setTotal(Integer.parseInt(String.valueOf(result[2])));
                dto.setSuministrado(Integer.parseInt(String.valueOf(result[3])));
                dto.setDisponible(dto.getTotal().intValue() - dto.getSuministrado().intValue());
                dto.setCantidadSuministrar(0);
                dto.setBopcion(true);
                listaDTOs.add(dto);
            }
        } catch (Exception e) {
            Logger.getLogger(DetalleOrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (listaDTOs != null && listaDTOs.size() > 0) {
            return listaDTOs;
        }
        return null;
    }

    @Override
    public Integer falloByIdDetalleOrden(Integer dos) {
        Integer result = 0;
        try {
            Query i = em.createQuery("Select dos.idFalloProcedimientoRcb.idFalloProcedimientoRcb From DetalleOrdenSuministro dos "
                    + "Where dos.idDetalleOrdenSuministro = " + dos + " "
            );

            result = (Integer) i.getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(DetalleOrdenSuministroDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (result != null && result > 0) {
            return result;
        }
        System.out.println("id vacio");
        return 0;
    }

    @Override
    public List<DetalleOrdenSuministro> detalleByFechaFinalPorDia(Date fechaExcedida, Date fechaAnterior) {
        List<DetalleOrdenSuministro> listDetalleOrd = null;
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Query i = em.createQuery("Select dos From DetalleOrdenSuministro dos "
                    + "Where dos.fechaEntregaFinal between '" + formatoDeFecha.format(fechaAnterior) + "' and '" + formatoDeFecha.format(fechaExcedida) + "'");

            System.out.println("em" + i);
            listDetalleOrd = i.getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (listDetalleOrd != null && listDetalleOrd.size() > 0) {
            return listDetalleOrd;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<DetalleOrdenSuministro> detalleByFechaFinalPor5Dia(Date fechaExcedida, Date fechaAnterior) {
        List<DetalleOrdenSuministro> listDetalleOrd = null;
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Query i = em.createQuery("Select dos From DetalleOrdenSuministro dos "
                    + "Where dos.fechaEntregaFinal between '" + formatoDeFecha.format(fechaAnterior) + "' and '" + formatoDeFecha.format(fechaExcedida) + "'");

            System.out.println("em" + i);
            listDetalleOrd = i.getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (listDetalleOrd != null && listDetalleOrd.size() > 0) {
            return listDetalleOrd;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<DetalleOrdenSuministro> obtenerListaOrdenesPendientesporSuministrar(String clave, String contrato) {
        List<DetalleOrdenSuministro> listDetalleOr = null;
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        try {
            String query = "Select dos From DetalleOrdenSuministro dos "
                    + "Join dos.idOrdenSuministro os "
                    + "Join os.idContrato c "
                    + "Join dos.idFalloProcedimientoRcb fpr "
                    + "Join fpr.idProcedimientoRcb pr "
                    + "Join pr.idRcbInsumos ri "
                    + "Where dos.fechaEntregaFinal < '" + formatoDeFecha.format(new Date().getTime()) + "' "
                    + "And os.idEstatus.idEstatus != 76 ";
            if (!clave.equals("")) {
                query = query + "And ri.claveInsumo = '" + clave + "' ";
            }
            if (!contrato.equals("")) {
                query = query + "And c.numeroContrato = '" + contrato + "'";
            }
            query = query + " Order by c.numeroContrato ";
            Query i = em.createQuery(query);
            System.out.println("em" + i);
            listDetalleOr = i.getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (listDetalleOr != null && listDetalleOr.size() > 0) {
            return listDetalleOr;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<DetalleOrdenSuministro> obtenerClavesSinAtraso(String clave, String contrato) {
        List<DetalleOrdenSuministro> listDetalleOr = null;
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        try {
            String query = "Select dos From DetalleOrdenSuministro dos "
                    + "Join dos.idOrdenSuministro os "
                    + "Join os.idContrato c "
                    + "Join dos.idFalloProcedimientoRcb fpr "
                    + "Join fpr.idProcedimientoRcb pr "
                    + "Join pr.idRcbInsumos ri "
                    + "Where dos.fechaEntregaFinal > '" + formatoDeFecha.format(new Date().getTime()) + "'"
                    //                    + "And os.idEstatus.idEstatus != 76 ";
                    + "And os.idEstatus.idEstatus = os.idEstatus.idEstatus ";
            if (!clave.equals("")) {
                query = query + "And ri.claveInsumo = '" + clave + "' ";
            }
            if (!contrato.equals("")) {
                query = query + "And c.numeroContrato = '" + contrato + "'";
            }
            query = query + " Order by c.numeroContrato ";
            Query i = em.createQuery(query);
            System.out.println("em" + i);
            listDetalleOr = i.getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (listDetalleOr != null && listDetalleOr.size() > 0) {
            return listDetalleOr;
        }
        System.out.println("vacio");
        return null;
    }

}
