/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.AreasService;
import com.issste.sicabis.ejb.ln.AsignacionInsumosService;
import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.ClasificacionImportanciaService;
import com.issste.sicabis.ejb.ln.ClasificacionService;
import com.issste.sicabis.ejb.ln.GrupoService;
import com.issste.sicabis.ejb.ln.GrupoTerapeuticoService;
import com.issste.sicabis.ejb.ln.InsumoDpnService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.InsumosSiamService;
import com.issste.sicabis.ejb.ln.NivelService;
import com.issste.sicabis.ejb.ln.ProgramasService;
import com.issste.sicabis.ejb.ln.TipoInsumosService;
import com.issste.sicabis.ejb.ln.UnidadPiezaService;
import com.issste.sicabis.ejb.modelo.Area;
import com.issste.sicabis.ejb.modelo.AsignacionInsumos;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.Clasificacion;
import com.issste.sicabis.ejb.modelo.ClasificacionImportancia;
import com.issste.sicabis.ejb.modelo.Grupo;
import com.issste.sicabis.ejb.modelo.GrupoTerapeutico;
import com.issste.sicabis.ejb.modelo.InsumoDpn;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.InsumosSiam;
import com.issste.sicabis.ejb.modelo.Nivel;
import com.issste.sicabis.ejb.modelo.Programas;
import com.issste.sicabis.ejb.modelo.TipoInsumos;
import com.issste.sicabis.ejb.modelo.UnidadPieza;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Toshiba Manolo
 */
@ManagedBean(name = "agregarInsumosBean")
@ViewScoped
public class AgregarInsumoBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @EJB
    private InsumoDpnService insumoDpnService;
    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;
    @EJB
    private TipoInsumosService tipoInsumosService;
    @EJB
    private ClasificacionImportanciaService clasificacionImportanciaService;
    @EJB
    private InsumosSiamService insumosSiamService;
    @EJB
    private ClasificacionService clasificacionService;
    @EJB
    private GrupoTerapeuticoService grupoTerapeuticoService;
    @EJB
    private NivelService nivelService;
    @EJB
    private UnidadPiezaService unidadPiezaService;
    @EJB
    private GrupoService grupoService;
    @EJB
    private InsumosService insumosService;
    @EJB
    private AreasService areaService;
    @EJB
    private AsignacionInsumosService asignacionInsumosService;
    @EJB
    private ProgramasService programasService;

    //Varibles
    private String clave;
    private Integer idTipoInsumo;
    private Integer idClasificacionImp;

    //Objetos
    private Insumos insumo;
    private Utilidades util;
    private Mensajes mensaje = new Mensajes();
    private AsignacionInsumos asignacionInsumos = new AsignacionInsumos();
    private List<Clasificacion> listClasificaciones;
    private List<GrupoTerapeutico> listGrupoTeraputico;
    private List<Nivel> listNivel;
    private List<UnidadPieza> listUnidadPieza;
    private List<Grupo> listGrupo;
    private List<Programas> listProgramas;
    private List<Area> listArea;
    private List<ClasificacionImportancia> listClaImportancia;
    private List<TipoInsumos> listTipoInsumos;
    private BitacoraTareaEstatus bitacoraTareaEstatus;
    private Usuarios usuarios;

    public AgregarInsumoBean() {
        usuarios = new Usuarios();
        util = new Utilidades();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        listArea = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        insumo = new Insumos();
        listClaImportancia = new ArrayList<>();
        listTipoInsumos = new ArrayList<>();
        insumo.setIdClasificacion(new Clasificacion());
        insumo.setIdGrupoTerapeutico(new GrupoTerapeutico());
        insumo.setIdNivel(new Nivel());
        insumo.setIdUnidadPieza(new UnidadPieza());
        insumo.setIdGrupo(new Grupo());
        asignacionInsumos.setIdArea(new Area());
        asignacionInsumos.setIdPrograma(new Programas());
        listClasificaciones = clasificacionService.obtenerClasificaciones();
        listGrupoTeraputico = grupoTerapeuticoService.obtenerGruposTerapeuticos();
        listNivel = nivelService.getAllNivel();
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        listUnidadPieza = unidadPiezaService.getAllUnidadPiezas();
        listGrupo = grupoService.traeListaGrupos();
        cargarAreas();
        listProgramas = programasService.obtenerProgramas();
        listTipoInsumos = tipoInsumosService.listTipoInsumos();
        listClaImportancia = clasificacionImportanciaService.obtenerByClasificacion(new ClasificacionImportancia());

    }

    public void cargarAreas() {
        List<Area> areasListAux = areaService.obtenerAreas();
        for (Area ar : areasListAux) {
            if (ar.getIdArea() != 10 && ar.getIdPadre() != null) {
                if (usuarios.getIdArea().getIdArea() == 16 || usuarios.getIdArea().getIdArea() == 17) {
                    if (ar.getIdArea() >= 11 && ar.getIdArea() <= 14) {
                        listArea.add(ar);
                    }
                } else if (Objects.equals(ar.getIdArea(), usuarios.getIdArea().getIdArea())) {
                    listArea.add(ar);
                    asignacionInsumos.setIdArea(new Area(ar.getIdArea()));
                }
            }
            if (usuarios.getIdUsuario() == 1) {
                listArea.add(ar);
            }
        }
    }

    public void onload() {
        if (!clave.equals("0")) {
            InsumosSiam is = insumosSiamService.obtenerByClave(clave);
            if (is != null) {
                insumo.setClave(is.getClave());
                insumo.setDescripcion(is.getDescripcion());
                insumo.setIdGrupo(new Grupo(is.getIdGrupo()));
                insumo.setIdNivel(new Nivel(is.getIdNivel()));
                insumo.setIndicaciones(is.getIndicaciones());
                insumo.setPartidaPresupuestal(is.getPartidaPresupuestal());
                insumo.setViaAdministracionDosis(is.getViaAdministracionDosis());
            }
        }
        //listaFalloProcRcb = falloProcedimientoRcbService.obtenerByFalloProcRcb(idProveedor);
    }

    public void guardarInsumo() {
        insumo.setFechaAlta(new Date());
        asignacionInsumos.setIdInsumo(insumo);
        insumo.setIdClasificacionImportancia(new ClasificacionImportancia(idClasificacionImp));
        insumo.setIdTipoInsumos(new TipoInsumos(idTipoInsumo));
        insumo.setActivo(1);
        List<AsignacionInsumos> asilist = new ArrayList<>();
        asilist.add(asignacionInsumos);
        insumo.setAsignacionInsumosList(asilist);
        boolean band = insumosService.save(insumo);
        if (band) {
            InsumoDpn insumoDpn = new InsumoDpn();
            insumoDpn.setIdInsumo(insumo);
            insumoDpn.setFechaAlta(new Date());
            insumoDpn.setUsuarioAlta(usuarios.getUsuario());
            insumoDpnService.guardarActualizar(insumoDpn);
            bitacoraTareaEstatus.setDescripcion("Guardado insumo:" + clave + "");
            bitacoraTareaEstatus.setFecha(new Date());
            bitacoraTareaEstatus.setIdModulos(1);
            bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
            bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
            if (clave.equals(insumo.getClave())) {
                band = insumosSiamService.borrarByClave(clave);
            }
            mensaje.mensaje(mensaje.datos_guardados, "verde");
            RequestContext.getCurrentInstance().closeDialog(band);
        }
    }

    public void guardarSelelects() {
        System.out.println("getIdClasificacion : " + insumo.getIdClasificacion());
    }

    public Insumos getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumos insumo) {
        this.insumo = insumo;
    }

    public List<Clasificacion> getListClasificaciones() {
        return listClasificaciones;
    }

    public void setListClasificaciones(List<Clasificacion> listClasificaciones) {
        this.listClasificaciones = listClasificaciones;
    }

    public List<GrupoTerapeutico> getListGrupoTeraputico() {
        return listGrupoTeraputico;
    }

    public void setListGrupoTeraputico(List<GrupoTerapeutico> listGrupoTeraputico) {
        this.listGrupoTeraputico = listGrupoTeraputico;
    }

    public List<Nivel> getListNivel() {
        return listNivel;
    }

    public void setListNivel(List<Nivel> listNivel) {
        this.listNivel = listNivel;
    }

    public List<UnidadPieza> getListUnidadPieza() {
        return listUnidadPieza;
    }

    public void setListUnidadPieza(List<UnidadPieza> listUnidadPieza) {
        this.listUnidadPieza = listUnidadPieza;
    }

    public List<Grupo> getListGrupo() {
        return listGrupo;
    }

    public void setListGrupo(List<Grupo> listGrupo) {
        this.listGrupo = listGrupo;
    }

    public AsignacionInsumos getAsignacionInsumos() {
        return asignacionInsumos;
    }

    public void setAsignacionInsumos(AsignacionInsumos asignacionInsumos) {
        this.asignacionInsumos = asignacionInsumos;
    }

    public List<Programas> getListProgramas() {
        return listProgramas;
    }

    public void setListProgramas(List<Programas> listProgramas) {
        this.listProgramas = listProgramas;
    }

    public List<Area> getListArea() {
        return listArea;
    }

    public void setListArea(List<Area> listArea) {
        this.listArea = listArea;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public List<ClasificacionImportancia> getListClaImportancia() {
        return listClaImportancia;
    }

    public void setListClaImportancia(List<ClasificacionImportancia> listClaImportancia) {
        this.listClaImportancia = listClaImportancia;
    }

    public List<TipoInsumos> getListTipoInsumos() {
        return listTipoInsumos;
    }

    public void setListTipoInsumos(List<TipoInsumos> listTipoInsumos) {
        this.listTipoInsumos = listTipoInsumos;
    }

    public Integer getIdTipoInsumo() {
        return idTipoInsumo;
    }

    public void setIdTipoInsumo(Integer idTipoInsumo) {
        this.idTipoInsumo = idTipoInsumo;
    }

    public Integer getIdClasificacionImp() {
        return idClasificacionImp;
    }

    public void setIdClasificacionImp(Integer idClasificacionImp) {
        this.idClasificacionImp = idClasificacionImp;
    }

}
