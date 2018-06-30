package com.issste.sicabis.ejb.ln;

import com.issste.sicabis.ejb.DAO.FabricanteDAO;
import com.issste.sicabis.ejb.DAO.LoteDAO;
import com.issste.sicabis.ejb.DAO.ProveedorFabricanteDAO;
import com.issste.sicabis.ejb.DAO.ProveedoresDAO;
import com.issste.sicabis.ejb.DAO.RemisionesDAO;
import com.issste.sicabis.ejb.DTO.RemisionesDTO;
import com.issste.sicabis.ejb.modelo.DetalleOrdenSuministro;
import com.issste.sicabis.ejb.modelo.Fabricante;
import com.issste.sicabis.ejb.modelo.Lote;
import com.issste.sicabis.ejb.modelo.ProveedorFabricante;
import com.issste.sicabis.ejb.modelo.Proveedores;
import com.issste.sicabis.ejb.modelo.Remisiones;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author fabianvr
 */
@Stateless
public class RemisionesService {

    @EJB
    private ProveedorFabricanteDAO proveedorFabricanteDAOImplement;
    @EJB
    private ProveedoresDAO proveedoresDAOImplement;
    @EJB
    private FabricanteDAO fabricanteDAOImplement;
    @EJB
    private LoteDAO loteDAOImplement;
    @EJB
    private RemisionesDAO remisionesDAOImplement;

    private List<Remisiones> remisionesList;
    private List<Remisiones> remisionesByRegistro;
    private List<RemisionesDTO> ordenList;
    private List<RemisionesDTO> insumosList;
    private List<RemisionesDTO> controlCalidad;
    private List<RemisionesDTO> remisionRegistroList;
    private List<Fabricante> fabricanteList;
    private List<Fabricante> fabricantesList;
    private List<Fabricante> fabricanteByNumeroList;
    private List<Remisiones> remiList;
    private List<RemisionesDTO> remisionesByControlList;
    private List<Proveedores> proveedoresList;
    private List<Fabricante> fabricanteByNombreList;
    private List<Remisiones> devolucionList;
    private List<Remisiones> remisionBienesList;
    private List<Remisiones> remisionBienesRegistroList;
    private Integer numero;
    private Integer numeroFolio;

    public List<Remisiones> remisiones() {

        remisionesList = null;
        remisionesList = remisionesDAOImplement.getByRemision();
        return remisionesList;
    }

    public List<RemisionesDTO> getOrden(String criterioBusqueda, Integer buscar) {

        ordenList = null;
        ordenList = remisionesDAOImplement.getByOrden(criterioBusqueda, buscar);
        return ordenList;
    }

    public List<Remisiones> getRemisionDevolucion(Integer insumo, String orden) {

        devolucionList = null;
        devolucionList = remisionesDAOImplement.remisionDevolucion(insumo, orden);
        return devolucionList;
    }

    public List<RemisionesDTO> getInsumo(String orden) {

        insumosList = null;
        insumosList = remisionesDAOImplement.getInsumos(orden);
        return insumosList;
    }

    public Integer numeroRegistroControl() {
        Integer n = remisionesDAOImplement.getNumeroRegistroControl();
        if (n != null) {
            numero = n + 1;
        } else {
            numero = 1;
        }
        return numero;
    }

    public Integer guardaRemision(Remisiones remisiones) {
        Integer bandera;
        bandera = remisionesDAOImplement.guardarRemision(remisiones);
        return bandera;
    }

    public boolean actualizarRemision(Remisiones remisiones) {
        boolean bandera;
        bandera = remisionesDAOImplement.actualizarRemision(remisiones);
        return bandera;
    }

    public boolean guardaLote(Lote lote) {
        boolean bandera2 = true;
        bandera2 = loteDAOImplement.guardarLotes(lote);
        return bandera2;
    }

    public DetalleOrdenSuministro getDetalle(Integer renglon, String os) {
        DetalleOrdenSuministro dos = remisionesDAOImplement.getDetalleOrden(renglon, os);
        return dos;
    }

    public boolean remisionExistente(Integer dos, String orden) {
        boolean r;
        r = remisionesDAOImplement.getRemisionExistente(dos, orden);
        return r;
    }

    public List<Remisiones> remisionByRegsitro(String registro, String fechaInicio, String fechaFin) {
        remisionesByRegistro = null;
        remisionesByRegistro = remisionesDAOImplement.remisionByRegistro(registro, fechaInicio, fechaFin);
        return remisionesByRegistro;
    }

    public List<Remisiones> remisionByRegsitroControlCalidad(String registro, String fechaInicio, String fechaFin) {
        remisionesByRegistro = null;
        remisionesByRegistro = remisionesDAOImplement.remisionByRegistroControl(registro, fechaInicio, fechaFin);
        return remisionesByRegistro;
    }

    public List<Remisiones> remisionByRecepcionInsumos(String registro, String fechaInicio, String fechaFin) {
        remisionesByRegistro = null;
        remisionesByRegistro = remisionesDAOImplement.remisionByRecepcionInsumos(registro, fechaInicio, fechaFin);
        return remisionesByRegistro;
    }

    public List<Remisiones> remisionByRegsitroRecepcionInsumos(String registro, String fechaInicio, String fechaFin) {
        remisionesByRegistro = null;
        remisionesByRegistro = remisionesDAOImplement.remisionByRegistro(registro, fechaInicio, fechaFin);
        return remisionesByRegistro;
    }

    public List<RemisionesDTO> remisionByControlCalidad() {
        controlCalidad = null;
        controlCalidad = remisionesDAOImplement.remisionByControlCalidad();
        return controlCalidad;
    }

    public List<RemisionesDTO> remisionByRegistroControl(String registroControl) {
        remisionRegistroList = null;
        remisionRegistroList = remisionesDAOImplement.remisionByRegistroControl(registroControl);
        return remisionRegistroList;
    }

    public List<Fabricante> fabricanteByNumero(Integer numero, Integer nombre) {
        fabricanteList = null;
        fabricanteList = fabricanteDAOImplement.fabricanteByNumero(numero, nombre);
        return fabricanteList;
    }

    public List<Fabricante> fabricanteList() {
        fabricantesList = null;
        fabricantesList = fabricanteDAOImplement.fabricanteList();
        return fabricantesList;
    }

    public List<Remisiones> remisionesAll(String registroControl) {
        remiList = null;
        remiList = remisionesDAOImplement.remisionesAll(registroControl);
        return remiList;
    }

//    public List<RemisionesDTO> remisionesByControlCalidad() {
//        remisionesByControlList = null;
//        remisionesByControlList = remisionesDAOImplement.remisionesByControlCalidad();
//        return remisionesByControlList;
//    }
    public List<Proveedores> proveedoresAll() {
        proveedoresList = null;
        proveedoresList = proveedoresDAOImplement.proveedoresAll();
        return proveedoresList;
    }

    public boolean guardaFabricante(Fabricante fabricante) {
        boolean banderaF = true;
        banderaF = fabricanteDAOImplement.guardarFabricante(fabricante);
        return banderaF;
    }

    public boolean guardaFabricanteProveedor(ProveedorFabricante proveedorFabricante) {
        boolean banderaPF = true;
        banderaPF = proveedorFabricanteDAOImplement.guardarProveedorFabricante(proveedorFabricante);
        return banderaPF;
    }

    public List<Remisiones> remisionesByRecepcionBienes() {
        remisionBienesList = null;
        remisionBienesList = remisionesDAOImplement.remisionesByRecepcionInsumos();
        return remisionBienesList;
    }

    public List<Remisiones> remisionesBienesByRegistro(String registroControl) {
        remisionBienesRegistroList = null;
        remisionBienesRegistroList = remisionesDAOImplement.remisionesBienesByRegestro(registroControl);
        return remisionBienesRegistroList;
    }

    public List<Remisiones> remisionByIdRemision(Integer idRemision) {
        List<Remisiones> remisionList;
        remisionList = remisionesDAOImplement.remisionByIdRemision(idRemision);
        return remisionList;

    }

    public List<Lote> loteByRemision(Integer remision) {
        List<Lote> loteList;
        loteList = loteDAOImplement.loteByRemision(remision);
        return loteList;
    }

    public Integer remisionesByDetalle(Integer detalle, String o) {
        Long remisionesDetalle;
        remisionesDetalle = remisionesDAOImplement.remisionesesByIdDetalle(detalle, o);
        Integer r = (int) (long) remisionesDetalle;
        if (r == null) {
            r = 0;
        }
        return r;
    }

    public List<DetalleOrdenSuministro> remisionesByOrnden(Integer insumo, String orden) {
        List<DetalleOrdenSuministro> remisiones;
        remisiones = remisionesDAOImplement.remisionesByOrden(insumo, orden);
        return remisiones;
    }

    public List<Remisiones> folioExistente(String folio) {
        return remisionesDAOImplement.folioExistente(folio);
    }

    public List<Remisiones> remisionByRegistroControlDevolucion(String registroControl) {
        return remisionesDAOImplement.remisionesByResgistroControlDevolucion(registroControl);
    }

    public List<Remisiones> obtenerRemisionByOrden(String orden) {
        return remisionesDAOImplement.remisionesByIdOrden(orden);
    }

    public List<Remisiones> obtenerRemisionesDevolucionAll() {
        return remisionesDAOImplement.obtenerRemisionesDevolucionAll();
    }

    public Remisiones getByRegistroControl(String registroControl) {
        return remisionesDAOImplement.getByRegistroControl(registroControl);
    }

    public Remisiones getByIdCanjePermuta(Integer idCanjePermuta) {
        return remisionesDAOImplement.getByIdCanjePermuta(idCanjePermuta);
    }

    public Double obtenerPorcentajePiezasPorSuministrar(String contrato,String clave) {
        return remisionesDAOImplement.obtenerPorcentajePiezasPorSuministrar(contrato,clave);
    }

    public Double obtenerCantidadEntregadaPorOrden(Integer ordenSuministro) {
        return remisionesDAOImplement.obtenerCantidadEntregadaPorOrden(ordenSuministro);
    }
    
    public Integer getLastRegistroControlByYear(Integer year, String opcion) {
        Integer n = remisionesDAOImplement.getLastRegistroControlByYear(year, opcion);
        if (n != null) {
            numero = n + 1;
        } else {
            numero = 1;
        }
        return numero;
    }

}
