package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.InsumoDpnService;
import com.issste.sicabis.ejb.ln.InsumosService;
import com.issste.sicabis.ejb.ln.TipoInsumoDpnService;
import com.issste.sicabis.ejb.ln.UnidadInsumosDpnService;
import com.issste.sicabis.ejb.ln.UnidadMedicaService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.InsumoDpn;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.TipoInsumoDpn;
import com.issste.sicabis.ejb.modelo.UnidadInsumosDpn;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

public class ConfiguraInsumoDpnBean {

    @EJB
    private UnidadMedicaService unidadMedicaService;

    @EJB
    private UnidadInsumosDpnService unidadInsumosDpnService;

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private InsumosService insumosService;

    @EJB
    private InsumoDpnService insumoDpnService;

    @EJB
    private TipoInsumoDpnService tipoInsumoDpnService;

    private Usuarios usuarios;
    private InsumoDpn insumoDpn;
    private InsumoDpn insumoDpnDialog;

    private List<TipoInsumoDpn> listaTipoInsumoDpn;
    private List<InsumoDpn> listaInsumoDpn;
    private List<InsumoDpn> listaInsumoDpnSelect;
    private String clave;
    private Integer idTipoInsumoDpn;
    private Integer idEstatusInsumoDpn;
    private Integer idTipoInsumoDpnBuscar;
    private Integer idEstatusInsumoDpnBuscar;
    private List<UnidadInsumosDpn> listaUnidadInsumoDpn;
    private List<UnidadesMedicas> listaUnidadesMedicas;
    private List<UnidadesMedicas> listaUnidadesMedicasSelect;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        listaInsumoDpn = new ArrayList();
        listaInsumoDpnSelect = new ArrayList();
        listaTipoInsumoDpn = tipoInsumoDpnService.getAll();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        listaUnidadesMedicas = unidadMedicaService.getByConcentradora(1);
    }

    public void buscar() {
        listaInsumoDpn = new ArrayList();
        insumoDpn = new InsumoDpn();
        if (!clave.equals("")) {
            Insumos i = insumosService.obtieneByClave(clave);
            insumoDpn.setIdInsumo(i);
        }
        if (idEstatusInsumoDpnBuscar.intValue() != -1) {
            insumoDpn.setEstatusInsumoDpn(idEstatusInsumoDpnBuscar);
        }
        if (idTipoInsumoDpnBuscar.intValue() != -1) {
            insumoDpn.setIdTipoInsumoDpn(tipoInsumoDpnService.getById(idTipoInsumoDpnBuscar));
        }
        listaInsumoDpn = insumoDpnService.getByInsumoDpn(insumoDpn);
    }

    public void guardar() {
        if (idTipoInsumoDpn.intValue() != -1 && idEstatusInsumoDpn.intValue() != -1) {
            if (listaInsumoDpnSelect != null && listaInsumoDpnSelect.size() > 0) {
                for (InsumoDpn id : listaInsumoDpnSelect) {
                    id.setEstatusInsumoDpn(idEstatusInsumoDpn);
                    id.setIdTipoInsumoDpn(new TipoInsumoDpn(idTipoInsumoDpn));
                    if (id.getIdInsumoDpn() == null) {
                        id.setUsuarioAlta(usuarios.getUsuario());
                        id.setFechaAlta(new Date());
                    } else {
                        id.setUsuarioModificacion(usuarios.getUsuario());
                        id.setFechaModificacion(new Date());
                    }
                    insumoDpnService.guardarActualizar(id);
                    bitacoraTareaEstatus.setDescripcion("Guardar insumos DPN:" + id.getIdInsumo().getClave() + "");
                    bitacoraTareaEstatus.setFecha(new Date());
                    bitacoraTareaEstatus.setIdModulos(1);
                    bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
                    bitacoraTareaEstatus.setIdTareaId(21);
                    bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
                }
                mensaje.mensaje(mensaje.datos_guardados, "verde");
            } else {
                mensaje.mensaje("Debes seleccionar al menos una clave", "amarillo");
            }
        } else {
            mensaje.mensaje("Debes seleccionar el estatus y el tipo de insumo ", "amarillo");
        }

    }

    public void agregarUnidades(InsumoDpn insumoDpn) {
        System.out.println("insumoDpn-->"+insumoDpn);
        insumoDpnDialog = insumoDpn;
        listaUnidadesMedicasSelect = unidadInsumosDpnService.getUMByIdInsumoDpn(insumoDpnDialog.getIdInsumoDpn());
    }
    
    public void guardarUnidadInsumoDpn(){
        boolean bandera = unidadInsumosDpnService.actualizaByIdInsumoDpn(insumoDpnDialog.getIdInsumoDpn());
        UnidadInsumosDpn uid = null;
        if(bandera){
            for (UnidadesMedicas ums : listaUnidadesMedicasSelect) {
                uid = new UnidadInsumosDpn();
                uid.setActivo(1);
                uid.setIdInsumoDpn(insumoDpnDialog);
                uid.setIdUnidadesMedicas(ums);
                bandera = unidadInsumosDpnService.guardarActualizar(uid);
                //if(!bandera)
            }
        }
    }

    public List<TipoInsumoDpn> getListaTipoInsumoDpn() {
        return listaTipoInsumoDpn;
    }

    public void setListaTipoInsumoDpn(List<TipoInsumoDpn> listaTipoInsumoDpn) {
        this.listaTipoInsumoDpn = listaTipoInsumoDpn;
    }

    public List<InsumoDpn> getListaInsumoDpn() {
        return listaInsumoDpn;
    }

    public void setListaInsumoDpn(List<InsumoDpn> listaInsumoDpn) {
        this.listaInsumoDpn = listaInsumoDpn;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public List<InsumoDpn> getListaInsumoDpnSelect() {
        return listaInsumoDpnSelect;
    }

    public void setListaInsumoDpnSelect(List<InsumoDpn> listaInsumoDpnSelect) {
        this.listaInsumoDpnSelect = listaInsumoDpnSelect;
    }

    public Integer getIdTipoInsumoDpn() {
        return idTipoInsumoDpn;
    }

    public void setIdTipoInsumoDpn(Integer idTipoInsumoDpn) {
        this.idTipoInsumoDpn = idTipoInsumoDpn;
    }

    public Integer getIdEstatusInsumoDpn() {
        return idEstatusInsumoDpn;
    }

    public void setIdEstatusInsumoDpn(Integer idEstatusInsumoDpn) {
        this.idEstatusInsumoDpn = idEstatusInsumoDpn;
    }

    public Integer getIdTipoInsumoDpnBuscar() {
        return idTipoInsumoDpnBuscar;
    }

    public void setIdTipoInsumoDpnBuscar(Integer idTipoInsumoDpnBuscar) {
        this.idTipoInsumoDpnBuscar = idTipoInsumoDpnBuscar;
    }

    public Integer getIdEstatusInsumoDpnBuscar() {
        return idEstatusInsumoDpnBuscar;
    }

    public void setIdEstatusInsumoDpnBuscar(Integer idEstatusInsumoDpnBuscar) {
        this.idEstatusInsumoDpnBuscar = idEstatusInsumoDpnBuscar;
    }

    public List<UnidadInsumosDpn> getListaUnidadInsumoDpn() {
        return listaUnidadInsumoDpn;
    }

    public void setListaUnidadInsumoDpn(List<UnidadInsumosDpn> listaUnidadInsumoDpn) {
        this.listaUnidadInsumoDpn = listaUnidadInsumoDpn;
    }

    public List<UnidadesMedicas> getListaUnidadesMedicas() {
        return listaUnidadesMedicas;
    }

    public void setListaUnidadesMedicas(List<UnidadesMedicas> listaUnidadesMedicas) {
        this.listaUnidadesMedicas = listaUnidadesMedicas;
    }

    public List<UnidadesMedicas> getListaUnidadesMedicasSelect() {
        return listaUnidadesMedicasSelect;
    }

    public void setListaUnidadesMedicasSelect(List<UnidadesMedicas> listaUnidadesMedicasSelect) {
        this.listaUnidadesMedicasSelect = listaUnidadesMedicasSelect;
    }
    
}
