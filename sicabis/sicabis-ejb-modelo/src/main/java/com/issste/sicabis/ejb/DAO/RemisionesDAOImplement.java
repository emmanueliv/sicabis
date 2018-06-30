package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.RemisionesDTO;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Remisiones;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RemisionesDAOImplement implements RemisionesDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<Remisiones> getByRemision() {
        List<Remisiones> resultList = null;
        try {
            resultList = em.createQuery("Select r From Remisiones r").getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<RemisionesDTO> getByOrden(String criterioBusqueda, Integer busqueda) {
        List<RemisionesDTO> resultList = null;
        RemisionesDTO remiList = new RemisionesDTO();
        String sQuery = "";
        Query query;
        List<Object[]> objectList = null;
        try {

            if (busqueda == 1) {
                sQuery = "SELECT os.numeroOrden, os.importe, p.nombreProveedor, dos.fechaEntregaInicial, "
                        + "dos.fechaEntregaFinal,c.numeroContrato, e.nombre, os.idOrdenSuministro,os.cantidadSuministrar  "
                        + "FROM DetalleOrdenSuministro dos "
                        + "JOIN dos.idOrdenSuministro os  "
                        + "JOIN os.idEstatus e  "
                        + "JOIN os.idContrato c "
                        + "JOIN dos.idFalloProcedimientoRcb fpr "
                        + "JOIN fpr.idProveedor p "
                        + "WHERE os.activo = 1 and os.idEstatus.idEstatus = 73 and c.numeroContrato = '" + criterioBusqueda + "' "
                        + "And c.idEstatus.idEstatus != 58 "
                        + "And dos.cancelado = 1 "
                        + "GROUP BY os.numeroOrden, os.importe, p.nombreProveedor, dos.fechaEntregaInicial, "
                        + "dos.fechaEntregaFinal,c.numeroContrato, e.nombre, os.idOrdenSuministro,os.cantidadSuministrar "
                        + "ORDER BY os.idOrdenSuministro";
            } else if (busqueda == 2) {
                sQuery = "Select os.numeroOrden, os.importe, p.nombreProveedor, dos.fechaEntregaInicial, "
                        + "dos.fechaEntregaFinal,c.numeroContrato, e.nombre, os.idOrdenSuministro, os.cantidadSuministrar "
                        + "FROM DetalleOrdenSuministro dos "
                        + "JOIN dos.idOrdenSuministro os  "
                        + "JOIN os.idEstatus e  "
                        + "JOIN os.idContrato c "
                        + "JOIN dos.idFalloProcedimientoRcb fpr "
                        + "JOIN fpr.idProveedor p "
                        + "Where os.activo = 1 and  os.idEstatus.idEstatus = 73 and c.numeroConvenio = '" + criterioBusqueda + "' "
                        + "And c.idPadre != null "
                        + "And c.idEstatus.idEstatus != 58 "
                        + "And dos.cancelado = 1 "
                        + "GROUP BY os.numeroOrden, os.importe, p.nombreProveedor, dos.fechaEntregaInicial, "
                        + "dos.fechaEntregaFinal,c.numeroContrato, e.nombre, os.idOrdenSuministro,os.cantidadSuministrar "
                        + "ORDER BY os.idOrdenSuministro ";
            } else if (busqueda == 3) {
                sQuery = "Select os.numeroOrden, os.importe, p.nombreProveedor, dos.fechaEntregaInicial, "
                        + "dos.fechaEntregaFinal,c.numeroContrato, e.nombre, os.idOrdenSuministro , os.cantidadSuministrar "
                        + "FROM DetalleOrdenSuministro dos "
                        + "JOIN dos.idOrdenSuministro os  "
                        + "JOIN os.idEstatus e  "
                        + "JOIN os.idContrato c "
                        + "JOIN dos.idFalloProcedimientoRcb fpr "
                        + "JOIN fpr.idProveedor p "
                        + "Where os.activo = 1 and  os.idEstatus.idEstatus = 73 and os.numeroOrden = '" + criterioBusqueda + "' "
                        + "And c.idEstatus.idEstatus != 58 "
                        + "And dos.cancelado = 1 "
                        + "GROUP BY os.numeroOrden, os.importe, p.nombreProveedor, dos.fechaEntregaInicial, "
                        + "dos.fechaEntregaFinal,c.numeroContrato, e.nombre, os.idOrdenSuministro, os.cantidadSuministrar "
                        + "ORDER BY os.idOrdenSuministro";
            }
            query = em.createQuery(sQuery);
            System.out.println("query---->" + query);
            objectList = query.getResultList();
            resultList = new ArrayList();
            for (Object[] result : objectList) {
                remiList = new RemisionesDTO();
                String ordenSuministro = String.valueOf(result[0]);
                BigDecimal precio = (BigDecimal) result[1];
                String proveedor = String.valueOf(result[2]);
                Date fechaInicio = (Date) result[3];
                Date fechaFin = (Date) result[4];
                String contrato = String.valueOf(result[5]);
                String estatus = String.valueOf(result[6]);
                int id = Integer.parseInt(String.valueOf(result[7]));
                int cantidad = Integer.parseInt(String.valueOf(result[8]));

                remiList.setOrdenSuministro(ordenSuministro);
                remiList.setImporteTotal(precio);
                remiList.setProveedor(proveedor);
                remiList.setFechaEntregaInicio(fechaInicio);
                remiList.setFechaEntregaFin(fechaFin);
                remiList.setNumeroContrato(contrato);
                remiList.setEstatus(estatus);
                remiList.setIdSuministro(id);
                remiList.setCantidad(cantidad);

                resultList.add(remiList);
            }
        } catch (Exception e) {

            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<RemisionesDTO> getInsumos(String orden) {
        List<RemisionesDTO> resultList = null;
        RemisionesDTO remiList = new RemisionesDTO();
        String sQuery = "";
        Query query;
        List<Object[]> objectList = null;
        try {
            sQuery = "SELECT ri.idInsumo.idInsumo, ri.claveInsumo, ri.descripcionInsumo, dos.cantidadSuministrar, dos.fechaEntregaInicial, dos.fechaEntregaFinal "
                    + "FROM DetalleOrdenSuministro dos "
                    + "JOIN dos.idOrdenSuministro os  "
                    + "JOIN dos.idFalloProcedimientoRcb fpr  "
                    + "JOIN fpr.idProcedimientoRcb pr "
                    + "JOIN pr.idRcbInsumos ri "
                    + "Where os.numeroOrden = '" + orden + "' "
                    + "GROUP BY ri.idInsumo.idInsumo, ri.claveInsumo, ri.descripcionInsumo, dos.cantidadSuministrar, dos.fechaEntregaInicial, dos.fechaEntregaFinal "
                    + "ORDER BY ri.idInsumo.idInsumo";
            query = em.createQuery(sQuery);
            System.out.println("query----->" + query);
            objectList = query.getResultList();
            resultList = new ArrayList();
            for (Object[] result : objectList) {
                remiList = new RemisionesDTO();
                int renglon = Integer.parseInt(String.valueOf(result[0]));
                String clave = String.valueOf(result[1]);
                String descripcion = String.valueOf(result[2]);
                int cantidad = Integer.parseInt(String.valueOf(result[3]));
                Date fechaInicio = (Date) result[4];
                Date fechaFin = (Date) result[5];

                remiList.setRenglon(renglon);
                remiList.setClave(clave);
                remiList.setDescripcion(descripcion);
                remiList.setCantidad((Integer) cantidad);
                remiList.setFechaEntregaInicio(fechaInicio);
                remiList.setFechaEntregaFin(fechaFin);

                resultList.add(remiList);
            }
        } catch (Exception e) {

            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public Integer getNumeroRegistroControl() {
        List<Remisiones> resultList = null;
        List<String> ListRegistro = new ArrayList<>();
        Integer maximo = 0;
        String registroControl = "";
        try {
            resultList = em.createQuery("Select r From Remisiones r").getResultList();
            for (Remisiones iterator : resultList) {
                System.out.println("entre");
                ListRegistro.add(iterator.getRegistroControl());
            }
            String[] parts;
            Collections.sort(ListRegistro);
            if (ListRegistro.size() != 0) {
                registroControl = ListRegistro.get(ListRegistro.size() - 1);
                if (registroControl.contains("P")) {
                    parts = registroControl.split("P");
                    maximo = Integer.parseInt(parts[0]);
                } else if (registroControl.contains("C")) {
                    parts = registroControl.split("C");
                    maximo = Integer.parseInt(parts[0]);
                } else {
                    maximo = Integer.parseInt(registroControl);
                }
            }

        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (maximo != null) {
            return maximo;
        }
        return null;
    }

    @Override
    public Integer guardarRemision(Remisiones remisiones) {

        try {
            em.persist(remisiones);
            em.flush();
            return remisiones.getIdRemision();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public boolean actualizarRemision(Remisiones remisiones) {
        try {
            em.merge(remisiones);
            return true;
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return false;
        }
    }

    @Override
    public DetalleOrdenSuministro getDetalleOrden(Integer dos, String os) {
        List<DetalleOrdenSuministro> resultList = null;

        DetalleOrdenSuministro remiList = new DetalleOrdenSuministro();
        String sQuery = "";
        Query query;
        List<Object> objectList = null;
        try {
            System.out.println("os" + os);
            sQuery = "SELECT dos.idDetalleOrdenSuministro "
                    + "FROM DetalleOrdenSuministro dos "
                    + "Join dos.idOrdenSuministro os "
                    + "JOIN dos.idFalloProcedimientoRcb fpr "
                    + "JOIN fpr.idProcedimientoRcb pr "
                    + "JOIN pr.idRcbInsumos ri "
                    + "JOIN ri.idInsumo i "
                    + "WHERE i.idInsumo = " + dos + " "
                    + "And os.numeroOrden = '" + os + "'";
            query = em.createQuery(sQuery);
            System.out.println("query" + query);
            objectList = query.getResultList();
            resultList = new ArrayList();
            for (Object result : objectList) {

                int renglon = Integer.parseInt(String.valueOf(result));
                System.out.println("renglo" + renglon);
                remiList.setIdDetalleOrdenSuministro(renglon);
                resultList.add(remiList);
            }
        } catch (Exception e) {

            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public boolean getRemisionExistente(Integer insumo, String orden) {
        List<Remisiones> resultList = null;
        RemisionesDTO remiList = new RemisionesDTO();
        String sQuery = "";
        Query query;
        List<Object[]> objectList = null;
        try {
            sQuery = "SELECT r FROM Remisiones r "
                    + "JOIN r.idDetalleOrdenSuministro dos "
                    + "Join dos.idOrdenSuministro os "
                    + "join dos.idFalloProcedimientoRcb fpr "
                    + "join fpr.idProcedimientoRcb pr "
                    + "join pr.idRcbInsumos ri "
                    + "join ri.idInsumo i "
                    + "Where i.idInsumo = " + insumo + " "
                    + "and os.numeroOrden = '" + orden + "' ";
            resultList = em.createQuery(sQuery).getResultList();

        } catch (Exception e) {

            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public List<Remisiones> remisionByRegistro(String registroControl, String fechaInicio, String fechaFin) {
        List<Remisiones> resultList = null;
        try {
            if (!registroControl.equals("") && fechaInicio.equals("") && fechaFin.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where  r.registroControl = '" + registroControl + "' and r.idEstatus.idEstatus in (82,84)").getResultList();
            } else if (!fechaInicio.equals("") && fechaFin.equals("") && registroControl.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where r.idEstatus.idEstatus in (82,84) and r.fechaRemision = '" + fechaInicio + "'").getResultList();
            } else if (!fechaFin.equals("") && fechaInicio.equals("") && registroControl.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where r.idEstatus.idEstatus in (82,84) and r.fechaRemision =  '" + fechaFin + "'").getResultList();
            } else if (!fechaInicio.equals("") && !fechaFin.equals("") && registroControl.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where r.idEstatus.idEstatus in (82,84) and r.fechaRemision Between '" + fechaInicio + "' and '" + fechaFin + "'  ").getResultList();
            } else if (!registroControl.equals("") && !fechaInicio.equals("") && fechaFin.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where  r.registroControl = '" + registroControl + "' and r.idEstatus.idEstatus in (82,84) and r.fechaRemision = '" + fechaInicio + "' ").getResultList();
            } else if (!registroControl.equals("") && fechaInicio.equals("") && !fechaFin.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where  r.registroControl = '" + registroControl + "' and r.idEstatus.idEstatus in (82,84) and r.fechaRemision = '" + fechaFin + "' ").getResultList();
            } else if (registroControl.equals("") && fechaInicio.equals("") && fechaFin.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where r.idEstatus.idEstatus in (82,84)").getResultList();
            } else {
                resultList = em.createQuery("Select r From Remisiones r Where  r.registroControl = '" + registroControl + "' and r.idEstatus.idEstatus in (82,84) and r.fechaRemision Between '" + fechaInicio + "' and '" + fechaFin + "'  ").getResultList();
            }
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            System.out.println("entro");
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<Remisiones> remisionByRegistroControl(String registroControl, String fechaInicio, String fechaFin) {

        List<Remisiones> resultList = null;
        try {
            if (!registroControl.equals("") && fechaInicio.equals("") && fechaFin.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where  r.registroControl = '" + registroControl + "' and r.idEstatus.idEstatus in (83,84)").getResultList();
            } else if (!fechaInicio.equals("") && fechaFin.equals("") && registroControl.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where r.idEstatus.idEstatus in (83,84) and r.fechaRemision = '" + fechaInicio + "'").getResultList();
            } else if (!fechaFin.equals("") && fechaInicio.equals("") && registroControl.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where r.idEstatus.idEstatus in (83,84) and r.fechaRemision =  '" + fechaFin + "'").getResultList();
            } else if (!fechaInicio.equals("") && !fechaFin.equals("") && registroControl.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where r.idEstatus.idEstatus in (83,84) and r.fechaRemision Between '" + fechaInicio + "' and '" + fechaFin + "'  ").getResultList();
            } else if (!registroControl.equals("") && !fechaInicio.equals("") && fechaFin.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where  r.registroControl = '" + registroControl + "' and r.idEstatus.idEstatus in (83,84) and r.fechaRemision = '" + fechaInicio + "' ").getResultList();
            } else if (!registroControl.equals("") && fechaInicio.equals("") && !fechaFin.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where  r.registroControl = '" + registroControl + "' and r.idEstatus.idEstatus in (83,84) and r.fechaRemision = '" + fechaFin + "' ").getResultList();
            } else if (registroControl.equals("") && fechaInicio.equals("") && fechaFin.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where r.idEstatus.idEstatus in (83,84)").getResultList();
            } else {
                resultList = em.createQuery("Select r From Remisiones r Where  r.registroControl = '" + registroControl + "' and r.idEstatus.idEstatus in (82,84) and r.fechaRemision Between '" + fechaInicio + "' and '" + fechaFin + "'  ").getResultList();
            }
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<RemisionesDTO> remisionByControlCalidad() {
        List<RemisionesDTO> resultList = null;
        RemisionesDTO remiList = new RemisionesDTO();
        String sQuery = "";
        Query query;
        List<Object[]> objectList = null;
        try {
            sQuery = "Select r.registro_control,i.clave,i.descripcion,dos.cantidad_suministrar,c.numero_contrato, "
                    + "a.nombre_almacen,os.numero_orden,r.folio_remision,r.fecha_remision, p.nombre_proveedor,e.nombre  from  Remisiones r "
                    + "join estatus e on r.id_estatus = e.id_estatus "
                    + "join detalle_orden_suministro dos on r.id_detalle_orden_suministro = dos.id_detalle_orden_suministro "
                    + "join orden_suministro os on dos.id_orden_suministro = os.id_orden_suministro "
                    + "join contratos c on os.id_contrato = c.id_contrato "
                    + "join almacen a on c.id_almacen = a.id_almacen "
                    + "join fallo_procedimiento_rcb fpr on dos.id_fallo_procedimiento_rcb = fpr.id_fallo_procedimiento_rcb "
                    + "join proveedores p on p.id_proveedor = fpr.id_proveedor  "
                    + "join procedimiento_rcb pr on fpr.id_procedimiento_rcb = pr.id_procedimiento_rcb "
                    + "join rcb_insumos ri on pr.id_rcb_insumos = ri.id_rcb_insumos "
                    + "join insumos i on ri.id_insumo = i.id_insumo "
                    + "where r.activo = 1 and r.id_estatus in (29,30,31) "
                    + "group by 1,2,3,4,5,6,7,8,9,10,11";
            query = em.createNativeQuery(sQuery);
            objectList = query.getResultList();
            resultList = new ArrayList();
            for (Object[] result : objectList) {
                remiList = new RemisionesDTO();
                String registrodControl = String.valueOf(result[0]);
                String clave = String.valueOf(result[1]);
                String descripcion = String.valueOf(result[2]);
                int cantidad = Integer.parseInt(String.valueOf(result[3]));
                String contrato = String.valueOf(result[4]);
                String almacen = String.valueOf(result[5]);
                String ordenSuministro = String.valueOf(result[6]);
                String folio = String.valueOf(result[7]);
                Date fecha = (Date) result[8];
                String proveedor = String.valueOf(result[9]);
                String estatus = String.valueOf(result[10]);

                remiList.setRegistroControl(registrodControl);
                remiList.setClave(clave);
                remiList.setDescripcion(descripcion);
                remiList.setCantidad((Integer) cantidad);
                remiList.setNumeroContrato(contrato);
                remiList.setAlmacen(almacen);
                remiList.setOrdenSuministro(ordenSuministro);
                remiList.setFolioRemision(folio);
                remiList.setFechaRemision(fecha);
                remiList.setProveedor(proveedor);
                remiList.setEstatus(estatus);

                resultList.add(remiList);

            }
        } catch (Exception e) {

            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<RemisionesDTO> remisionByRegistroControl(String registroControl) {
        List<RemisionesDTO> resultList = null;
        RemisionesDTO remiList = new RemisionesDTO();
        String sQuery = "";
        Query query;
        System.out.println("reg" + registroControl);
        List<Object[]> objectList = null;
        try {
            sQuery = "SELECT r.idRemision, ri.idInsumo.idInsumo, r.registroControl, dos.cantidadSuministrar, up.descripcion, "
                    + "fpr.precioUnitario, ri.claveInsumo, "
                    + "ri.descripcionInsumo,(dos.cantidadSuministrar * fpr.precioUnitario) as importe, fl.descripcion , r.cantidadRecibida, "
                    + "p.nombreProveedor, proc.numeroProcedimiento "
                    + "FROM Remisiones r  "
                    + "JOIN r.idEstatus e "
                    + "JOIN r.idDetalleOrdenSuministro dos "
                    + "JOIN dos.idOrdenSuministro os "
                    + "JOIN os.idContrato c "
                    + "JOIN c.idFundamentoLegal fl "
                    + "JOIN c.idAlmacen a "
                    + "JOIN dos.idFalloProcedimientoRcb fpr "
                    + "JOIN fpr.idProveedor p "
                    + "JOIN fpr.idProcedimientoRcb pr "
                    + "JOIN pr.idProcedimiento proc "
                    + "JOIN pr.idRcbInsumos ri "
                    + "JOIN RI.idInsumo i "
                    + "JOIN i.idUnidadPieza up "
                    + "WHERE r.activo = 1 and r.registroControl = '" + registroControl + "' and r.idEstatus.idEstatus = 82 "
                    + "GROUP BY r.idRemision, ri.idInsumo.idInsumo, r.registroControl, dos.cantidadSuministrar, up.descripcion, "
                    + "fpr.precioUnitario, ri.claveInsumo, "
                    + "ri.descripcionInsumo, fl.descripcion, r.cantidadRecibida, p.nombreProveedor, proc.numeroProcedimiento";
            query = em.createQuery(sQuery);
            objectList = query.getResultList();
            resultList = new ArrayList();
            for (Object[] result : objectList) {
                remiList = new RemisionesDTO();
                int idRemision = Integer.parseInt(String.valueOf(result[0]));
                int renglon = Integer.parseInt(String.valueOf(result[1]));
                String registrodControl = String.valueOf(result[2]);
                int cantidad = Integer.parseInt(String.valueOf(result[3]));
                String unidad = String.valueOf(result[4]);
                BigDecimal precio = (BigDecimal) result[5];
                String clave = String.valueOf(result[6]);
                String descripcion = String.valueOf(result[7]);
                BigDecimal importe = (BigDecimal) result[8];
                String articulo = String.valueOf(result[9]);
                int cantidadRecibidaRemision = Integer.parseInt(String.valueOf(result[10]));
                String nombreProveedor = String.valueOf(result[11]);
                String numeroProcedimiento = String.valueOf(result[12]);

                remiList.setIdRemision(idRemision);
                remiList.setRenglon(renglon);
                remiList.setRegistroControl(registrodControl);
                remiList.setCantidad(cantidad);
                remiList.setUnidadPieza(unidad);
                remiList.setImporteTotal(precio);
                remiList.setClave(clave);
                remiList.setDescripcion(descripcion);
                remiList.setImporte(importe);
                remiList.setArticulo(articulo);
                remiList.setCantidadRecibidaRemision(cantidadRecibidaRemision);
                remiList.setProveedor(nombreProveedor);
                remiList.setNumeroProcedimiento(numeroProcedimiento);
                System.out.println("articulo" + articulo);
                resultList.add(remiList);
            }
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<Remisiones> remisionesAll(String registroControl) {
        List<Remisiones> resultList = null;
        try {
            resultList = em.createQuery("Select r From Remisiones r Where r.registroControl = :registroControl ").setParameter("registroControl", registroControl).getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public List<Remisiones> remisionesByRecepcionInsumos() {
        List<Remisiones> resultList = null;
        try {
            resultList = em.createQuery("Select r From Remisiones r Where r.activo = 1 and r.idEstatus.idEstatus = 83 ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<Remisiones> remisionesBienesByRegestro(String registroControl) {
        List<Remisiones> resultList = null;
        try {
            resultList = em.createQuery("Select r From Remisiones r "
                    + "Where r.activo = 1 and r.registroControl = '" + registroControl + "' and r.idEstatus.idEstatus in (83,86) ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<Remisiones> remisionByIdRemision(Integer idRemision) {
        List<Remisiones> resultList = null;
        try {
            resultList = em.createQuery("Select r From Remisiones r Where r.activo = 1 and r.idRemision =" + idRemision + " ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<Remisiones> remisionDevolucion(Integer insumo, String orden) {
        List<Remisiones> resultList = null;
        Remisiones remiList = new Remisiones();
        String sQuery = "";
        Query query;
        try {

            sQuery = "SELECT r FROM Remisiones r "
                    + "JOIN r.idDetalleOrdenSuministro dos "
                    + "JOIN dos.idOrdenSuministro os  "
                    + "JOIN os.idContrato c "
                    + "JOIN dos.idFalloProcedimientoRcb fpr "
                    + "JOIN fpr.idProcedimientoRcb pr "
                    + "JOIN pr.idRcbInsumos ri "
                    + "JOIN ri.idInsumo i "
                    + "WHERE r.idEstatus.idEstatus = 84 AND c.idEstatus.idEstatus != 58 "
                    + "AND os.idEstatus.idEstatus != 74 AND i.idInsumo = " + insumo + " "
                    + "AND os.numeroOrden = '" + orden + "' ";
            System.out.println("squery" + sQuery);
            query = em.createQuery(sQuery);
            resultList = query.getResultList();

        } catch (Exception e) {

            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    @Override
    public Long remisionesesByIdDetalle(Integer detalle, String o) {
        Long resultList = null;
        try {
            resultList = (Long) em.createQuery("Select SUM(r.cantidadRecibida) From Remisiones r "
                    + "Join r.idDetalleOrdenSuministro dos "
                    + "Join dos.idOrdenSuministro os "
                    + "Join dos.idFalloProcedimientoRcb fpr "
                    + "Join fpr.idProcedimientoRcb pr "
                    + "Join pr.idRcbInsumos ri "
                    + "Join ri.idInsumo i "
                    + "Where i.idInsumo = " + detalle + " "
                    + "And os.numeroOrden = '" + o + "'").getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<DetalleOrdenSuministro> remisionesByOrden(Integer i, String orden) {
        List<DetalleOrdenSuministro> resultList = null;
        try {
            resultList = em.createQuery("Select dos From DetalleOrdenSuministro dos "
                    + "Join dos.idOrdenSuministro os "
                    + "Join dos.idFalloProcedimientoRcb fpr "
                    + "Join fpr.idProcedimientoRcb pr "
                    + "Join pr.idRcbInsumos ri "
                    + "Join ri.idInsumo i "
                    + "Where i.idInsumo = " + i + " "
                    + "And os.numeroOrden = '" + orden + "'").getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<Remisiones> remisionByRecepcionInsumos(String registroControl, String fechaInicio, String fechaFin) {

        List<Remisiones> resultList = null;
        try {
            if (!registroControl.equals("") && fechaInicio.equals("") && fechaFin.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where  r.registroControl = '" + registroControl + "' and r.idEstatus.idEstatus in (85,86) ").getResultList();
            } else if (!fechaInicio.equals("") && fechaFin.equals("") && registroControl.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where r.idEstatus.idEstatus in (85,86) and r.fechaRemision = '" + fechaInicio + "'").getResultList();
            } else if (!fechaFin.equals("") && fechaInicio.equals("") && registroControl.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where r.idEstatus.idEstatus in (85,86) and r.fechaRemision =  '" + fechaFin + "'").getResultList();
            } else if (!fechaInicio.equals("") && !fechaFin.equals("") && registroControl.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where r.idEstatus.idEstatus in (85,86) and r.fechaRemision Between '" + fechaInicio + "' and '" + fechaFin + "'  ").getResultList();
            } else if (!registroControl.equals("") && !fechaInicio.equals("") && fechaFin.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where  r.registroControl = '" + registroControl + "' and r.idEstatus.idEstatus in (85,86) and r.fechaRemision = '" + fechaInicio + "' ").getResultList();
            } else if (!registroControl.equals("") && fechaInicio.equals("") && !fechaFin.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where  r.registroControl = '" + registroControl + "' and r.idEstatus.idEstatus in (85,86) and r.fechaRemision = '" + fechaFin + "' ").getResultList();
            } else if (registroControl.equals("") && fechaInicio.equals("") && fechaFin.equals("")) {
                resultList = em.createQuery("Select r From Remisiones r Where r.idEstatus.idEstatus in (85,86)").getResultList();
            } else {
                resultList = em.createQuery("Select r From Remisiones r Where  r.registroControl = '" + registroControl + "' and r.idEstatus.idEstatus in (85,86) and r.fechaRemision Between '" + fechaInicio + "' and '" + fechaFin + "'  ").getResultList();
            }
        } catch (Exception e) {
            System.out.println("e----->" + e);
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            System.out.println("entro");
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<Remisiones> folioExistente(String folio) {
        List<Remisiones> resultList = null;
        try {
            resultList = em.createQuery("Select r From Remisiones r "
                    + "Where r.folioRemision = '" + folio + "'").getResultList();
        } catch (Exception e) {
            Logger.getLogger(PerfilesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<Remisiones> remisionesByResgistroControlDevolucion(String regisgtroControl) {
        List<Remisiones> resultList = null;
        try {
            resultList = em.createQuery("Select r From Remisiones r "
                    + "Where r.registroControl = '" + regisgtroControl + "' and r.idEstatus.idEstatus in (84,183) "
                    + "and r.activo = 1  ").getResultList();
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public List<Remisiones> remisionesByIdOrden(String numeroOrden) {
        List<Remisiones> resultList = null;
        try {
            return resultList = em.createQuery("SELECT r FROM Remisiones r  WHERE r.idDetalleOrdenSuministro.idOrdenSuministro.numeroOrden "
                    + "= :numeroOrden").setParameter("numeroOrden", numeroOrden).getResultList();
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public List<Remisiones> obtenerRemisionesDevolucionAll() {
        try {
            return em.createQuery("SELECT r FROM Remisiones r WHERE r.activo = 1 AND r.idEstatus.idEstatus = 84 ")
                    .getResultList();
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
            return null;
        }
    }

    @Override
    public Remisiones getByRegistroControl(String registroControl) {
        List<Remisiones> resultList = null;
        try {
            resultList = em.createQuery("Select r From Remisiones r "
                    + "Where r.registroControl = :registroControl and r.idEstatus.idEstatus = 82 "
                    + "and r.activo = 1 ").setParameter("registroControl", registroControl).getResultList();
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        System.out.println("vacio");
        return null;
    }

    public Remisiones getByIdCanjePermuta(Integer idCanjePermuta) {
        List<Remisiones> resultList = null;
        try {
            resultList = em.createQuery("Select r From Remisiones r Where r.idCanjePermuta.idCanjePermuta = :idCanjePermuta and r.activo = 1").setParameter("idCanjePermuta", idCanjePermuta).getResultList();
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public Double obtenerPorcentajePiezasPorSuministrar(String contrato, String clave) {
        Object porcentaje = null;
        try {
            String query = "Select ((NVL(Sum((Select sum(f.cantidad_piezas) from contrato_fallo_procedimiento_rcb cfpr \n"
                    + "join fallo_procedimiento_rcb f on f.id_fallo_procedimiento_rcb = cfpr.id_fallo_procedimiento_rcb\n"
                    + "where cfpr.id_fallo_procedimiento_rcb = fpr.id_fallo_procedimiento_rcb)),0) -\n"
                    + "((NVL(Sum((Select sum(dos.cantidad_suministrar) from detalle_orden_suministro dos where dos.id_fallo_procedimiento_rcb = fpr.id_fallo_procedimiento_rcb)),0))-\n"
                    + "(NVL(Sum((Select sum(r.cantidad_recibida) from remisiones r \n"
                    + "join detalle_orden_suministro dos on dos.id_detalle_orden_suministro = r.id_detalle_orden_suministro\n"
                    + "where dos.id_fallo_procedimiento_rcb = fpr.id_fallo_procedimiento_rcb)),0))))\n"
                    + "/ (NVL(Sum((Select sum(f.cantidad_piezas) from contrato_fallo_procedimiento_rcb cfpr \n"
                    + "join fallo_procedimiento_rcb f on f.id_fallo_procedimiento_rcb = cfpr.id_fallo_procedimiento_rcb\n"
                    + "where cfpr.id_fallo_procedimiento_rcb = fpr.id_fallo_procedimiento_rcb)),0))) as cantPen\n"
                    + "from fallo_procedimiento_rcb fpr\n"
                    + "join contrato_fallo_procedimiento_rcb cfpr on cfpr.id_fallo_procedimiento_rcb = fpr.id_fallo_procedimiento_rcb\n"
                    + "join contratos c on c.id_contrato = cfpr.id_contrato\n"
                    + "join procedimiento_rcb pr on pr.id_procedimiento_rcb = fpr.id_procedimiento_rcb\n"
                    + "join rcb_insumos ri on ri.id_rcb_insumos = pr.id_rcb_insumos\n"
                    + "where c.numero_contrato = '" + contrato + "' ";
            if (!clave.equals("")) {
                query = query + " and ri.clave_insumo = '" + clave + "'";
            }
            System.out.println("query-->" + query);
            porcentaje = em.createNativeQuery(query).getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (porcentaje != null) {
            String porcentajeString = porcentaje.toString();
            Double x1 = Double.parseDouble(porcentajeString);
            return x1;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public Double obtenerCantidadEntregadaPorOrden(Integer ordenSuministro) {
        Object piezasEntregadas = null;
        try {
            piezasEntregadas = (Long) em.createQuery("Select SUM(r.cantidadRecibida) From Remisiones r "
                    + "Join r.idDetalleOrdenSuministro dos "
                    + "Where dos.idDetalleOrdenSuministro = " + ordenSuministro + "").getSingleResult();
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (piezasEntregadas != null) {
            String piezasEntregadasString = piezasEntregadas.toString();
            Double x1 = Double.parseDouble(piezasEntregadasString);
            return x1;
        }
        System.out.println("vacio");
        return null;
    }

    @Override
    public Integer getLastRegistroControlByYear(Integer year, String opcion) {
        Integer maximo = 0;
        String registroControl = "";
        try {
//            registroControl = (String) em.createNativeQuery("SELECT FIRST 1 CASE \n"
//                    + "		WHEN registro_control MATCHES '*P' \n"
//                    + "		 THEN LEFT(registro_control, LENGTH(registro_control) -1) \n"
//                    + "		WHEN registro_control MATCHES '*C' \n"
//                    + "		 THEN LEFT(registro_control, LENGTH(registro_control) -1) \n"
//                    + "		ELSE registro_control \n"
//                    + "	   END valor \n"
//                    + "  FROM remisiones \n"
//                    + " WHERE YEAR(fecha_remision) = "+year).getSingleResult();
            registroControl = (String) em.createNativeQuery("SELECT registro_control \n"
                    + "  FROM remisiones \n"
                    + " WHERE id_canje_permuta "+opcion+" \n"
                    + "   AND YEAR(fecha_remision) = "+year+" \n"
                    + "   AND id_remision = (SELECT MAX(r.id_remision) \n"
                    + "                        FROM remisiones r)").getSingleResult();
            maximo = Integer.parseInt(registroControl);
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (maximo != null) {
            return maximo;
        }
        return null;
    }

}
