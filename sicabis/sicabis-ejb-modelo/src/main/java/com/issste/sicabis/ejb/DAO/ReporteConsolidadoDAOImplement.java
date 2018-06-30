/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.DAO;

import com.issste.sicabis.ejb.DTO.ReporteConsolidadoDTO;
import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Propuestas;
import com.issste.sicabis.ejb.modelo.ProveedorFabricante;
import com.issste.sicabis.ejb.modelo.Rcb;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import com.issste.sicabis.ejb.utils.ErrorUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fabianvr
 */
@Stateless
public class ReporteConsolidadoDAOImplement implements ReporteConsolidadoDAO {

    @PersistenceContext(unitName = "sicabis")
    private EntityManager em;

    @Override
    public List<ReporteConsolidadoDTO> reporteConsolidado(String criterioBusqueda, Integer busqueda) {
        List<RcbInsumos> resultList = null;
        List<ReporteConsolidadoDTO> r = null;
        ReporteConsolidadoDTO remiList = new ReporteConsolidadoDTO();
        String sQuery = "";
        Query query;
        List<Object[]> objectList = null;
        try {
            if (busqueda == 1) {
                sQuery = "Select rcb From RcbInsumos rcb where rcb.idRcb.numero = '" + criterioBusqueda + "'";
            } else if (busqueda == 2) {
                sQuery = "Select rcb From RcbInsumos rcb where rcb.claveInsumo = '" + criterioBusqueda + "'";
            }
            resultList = em.createQuery(sQuery).getResultList();
            r = new ArrayList();
            System.out.println("rcb" + resultList);
            for (RcbInsumos ri : resultList) {
                remiList.setRenglon(ri.getIdRcbInsumos());
                remiList.setClave(ri.getClaveInsumo());
                remiList.setFechaRCB(ri.getIdRcb().getFechaOficioSuficiencia());
                remiList.setCantidadPiezasRCB(ri.getCantidadPiezas());
                remiList.setOficioRCB(ri.getIdRcb().getNumero());
                remiList.setPrecioUnitarioRCB(ri.getPrecioUnitario());
                remiList.setImporteRCB(ri.getImporte());
                remiList.setOficioSuficiencia(ri.getIdRcb().getOficioSuficienciaPresupuestal());
                remiList.setFechaOficioSuficiencia(ri.getIdRcb().getFechaOficioSuficiencia());
                remiList.setPartida(ri.getPartidaPresupuestalInsumo());
                remiList.setNep(ri.getIdRcb().getNep());
                remiList.setDescripcionInsumo(ri.getDescripcionInsumo());
                remiList.setUnidad(ri.getIdInsumo().getIdUnidadPieza().getDescripcion());
                if (ri.getProcedimientoRcbList() != null && !ri.getProcedimientoRcbList().isEmpty()) {
                    for (ProcedimientoRcb pr : ri.getProcedimientoRcbList()) {
                        remiList.setCaracter(pr.getIdProcedimiento().getIdCaracterProcedimiento().getDescripcion());
                        remiList.setTipoProcedimeinto(pr.getIdProcedimiento().getIdTipoProcedimiento().getDescripcion());
                        remiList.setProcedimiento(pr.getIdProcedimiento().getNumeroProcedimiento());
                        remiList.setTipoCompra(pr.getIdProcedimiento().getIdTipoCompra().getNombre());
                        remiList.setJuntaAclaracionesConvocatoria(pr.getIdProcedimiento().getJuntaAclaracionesConvocatoria());
                        remiList.setElaboracionConvocatoria(pr.getIdProcedimiento().getElaboracionConvocatoria());
                        remiList.setPublicacionConvocatoria(pr.getIdProcedimiento().getPublicacionConvocatoria());
                        remiList.setInvitacion3Per(pr.getIdProcedimiento().getInvitacion3Per());
                        remiList.setJuntaConclusionAclaracionesConvocatoria(pr.getIdProcedimiento().getJuntaConclusionAclaracionesConvocatoria());
                        if (pr.getFalloProcedimientoRcbList() != null && !pr.getFalloProcedimientoRcbList().isEmpty()) {
                            for (FalloProcedimientoRcb fpr : pr.getFalloProcedimientoRcbList()) {
                                remiList.setNombreProveedor(fpr.getIdProveedor().getNombreProveedor());
                                remiList.setRfc(fpr.getIdProveedor().getRfc());
                                remiList.setTipoProveedor(fpr.getIdProveedor().getIdTipoProveedor().getDescripcion());
                                if (fpr.getIdProveedor().getProveedorFabricanteList() != null && !fpr.getIdProveedor().getProveedorFabricanteList().isEmpty()) {
                                    for (ProveedorFabricante pf : fpr.getIdProveedor().getProveedorFabricanteList()) {
                                        remiList.setFabricante(pf.getIdFabricante().getNombre());
                                    }
                                }
                                remiList.setFechaFallo(fpr.getIdFallo().getFechaFallo());
                                remiList.setFechaProgramadaFallo(fpr.getIdFallo().getFechaAlta());
                                remiList.setCantidad(fpr.getCantidadPiezas());
                                remiList.setPrecioUnitario(fpr.getPrecioUnitario());
                                for (Propuestas iterator : fpr.getIdProcedimientoRcb().getPropuestasList()) {
                                    remiList.setAperturaProgramada(iterator.getAperturaProgramada());
                                    remiList.setAperturaRealizada(iterator.getAperturaRealizada());
                                }
                                if (fpr.getContratoFalloProcedimientoRcbList() != null && !fpr.getContratoFalloProcedimientoRcbList().isEmpty()) {
                                    for (ContratoFalloProcedimientoRcb cfpr : fpr.getContratoFalloProcedimientoRcbList()) {
                                        remiList.setFundamentoLegal(cfpr.getIdContrato().getIdFundamentoLegal().getNombre());
                                        remiList.setTipoContrato(cfpr.getIdContrato().getIdTipoContrato().getDescripcion());
                                        if (cfpr.getIdContrato().getIdPadre() != 0) {
                                            remiList.setTipoConvenio(cfpr.getIdContrato().getIdTipoConvenio().getDescripcion());
                                            remiList.setConvenio(cfpr.getIdContrato().getNumeroConvenio());
                                        }
                                        remiList.setContrato(cfpr.getIdContrato().getNumeroContrato());

                                        remiList.setFechaContrato(cfpr.getIdContrato().getFechaContrato());
                                        remiList.setFechaFormalizacion(cfpr.getIdContrato().getFechaFormalizacion());
                                        remiList.setVigenciaInicial(cfpr.getIdContrato().getVigenciaInicial());
                                        remiList.setVigenciaFinal(cfpr.getIdContrato().getVigenciaFinal());
                                        remiList.setImporteContrato(cfpr.getIdContrato().getImporte());
                                    }
                                }
                            }
                        }
                    }
                }
                r.add(remiList);
            }
        } catch (Exception e) {
            Logger.getLogger(RemisionesDAOImplement.class.getName()).log(Level.SEVERE, ErrorUtil.CODE_0101, e);
        }
        if (r != null && r.size() > 0) {
            return r;
        }
        return null;
    }

    private void sQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
