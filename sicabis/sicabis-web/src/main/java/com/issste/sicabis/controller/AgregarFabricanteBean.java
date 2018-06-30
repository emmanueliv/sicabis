package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.FabricanteService;
import com.issste.sicabis.ejb.modelo.Fabricante;
import com.issste.sicabis.ejb.modelo.Propuestas;
import com.issste.sicabis.ejb.modelo.ProveedorFabricante;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.context.RequestContext;

public class AgregarFabricanteBean {

    @EJB
    private FabricanteService fabricanteService;

    private Fabricante fabricante;
    private ProveedorFabricante proveedorFabricante;
    private Propuestas propuestas;
    private Usuarios usuarios;

    private boolean botonGuardar;
    private boolean messageGuardar = true;
    private Integer idProveedor;
    private String nombreProveedor;
    private String clave;
    private List<Fabricante> listaFabricantes;
    private List<Fabricante> listaFabricanteAux;
    private List<Fabricante> listaFabricanteAuxFilter;
    public boolean bnuevo;
    private Integer idFabricante;
    private String nombreFabricante;
    private String registroSanitario;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();

    public AgregarFabricanteBean() {
    }

    @PostConstruct
    public void init() {
        fabricante = new Fabricante();
        //usuarios = (Usuarios) util.getSessionAtributte("usuario");
        listaFabricantes = fabricanteService.fabricanteList();
        propuestas = new Propuestas();
        //propuestas = (Propuestas) util.getContextAtributte("propuestas");
//        nombreProveedor = propuestas.getIdProveedor().getNombreProveedor();
//        clave = propuestas.getIdProcedimientoRcb().getIdRcbInsumos().getIdInsumo().getClave();
        listaFabricanteAux = new ArrayList();
    }

    public void agregarFabricante() {
        System.out.println("fabricante.getIdFabricante().intValue()--->"+fabricante.getIdFabricante().intValue());
        if (fabricante.getIdFabricante().intValue() != -1) {
            fabricante.setNombre(nombreFabricante.toUpperCase());
            fabricante.setIdFabricante(idFabricante);
            fabricante.setRegistroSanitario(registroSanitario);
            if (fabricante.getIdFabricante().intValue() == -2) {
                if (fabricante.getNombre().equals("") || fabricante.getRegistroSanitario().equals("")) {
                    mensaje.mensaje("Debes ingresar el nombre y el registro sanitario", "amarillo");
                } else {
                    fabricante.setActivo(1);
                    fabricante.setFechaAlta(new Date());
                    //fabricante.setUsuarioAlta(usuarios.getNombre());
                    if (fabricanteService.guardarFabricante(fabricante)) {
                        mensaje.mensaje(mensaje.datos_guardados, "verde");
                        listaFabricantes = fabricanteService.fabricanteList();
                        bnuevo = false;
                    } else {
                        mensaje.mensaje(mensaje.error_guardar, "rojo");
                    }
                }
            } else{
                fabricante = fabricanteService.fabricanteByIdFacbricante(fabricante.getIdFabricante());
            }
            listaFabricanteAux.add(fabricante);
            fabricante = new Fabricante();
        } else {
            mensaje.mensaje("Debes seleccionar el fabricante", "amarillo");
        }
    }
    
    public void limpiar() {
        RequestContext.getCurrentInstance().reset("formfab");
        init();
    }

    public void aceptar() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public void quitaFabricante(Fabricante fabricante) {
        List<Fabricante> listaFabricanteAux = new ArrayList();
        for (Fabricante f : this.listaFabricanteAux) {
            if (f != fabricante) {
                listaFabricanteAux.add(f);
            }
        }
        this.listaFabricanteAux.clear();
        this.listaFabricanteAux = listaFabricanteAux;
    }

    public void cambiaFabricante() {
        if (fabricante.getIdFabricante().intValue() == -2) {
            bnuevo = true;
        } else {
            bnuevo = false;
        }
    }

    public boolean isBotonGuardar() {
        return botonGuardar;
    }

    public void setBotonGuardar(boolean botonGuardar) {
        this.botonGuardar = botonGuardar;
    }

    public boolean isMessageGuardar() {
        return messageGuardar;
    }

    public void setMessageGuardar(boolean messageGuardar) {
        this.messageGuardar = messageGuardar;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public ProveedorFabricante getProveedorFabricante() {
        return proveedorFabricante;
    }

    public void setProveedorFabricante(ProveedorFabricante proveedorFabricante) {
        this.proveedorFabricante = proveedorFabricante;
    }

    public List<Fabricante> getListaFabricantes() {
        return listaFabricantes;
    }

    public void setListaFabricantes(List<Fabricante> listaFabricantes) {
        this.listaFabricantes = listaFabricantes;
    }

    public List<Fabricante> getListaFabricanteAux() {
        return listaFabricanteAux;
    }

    public void setListaFabricanteAux(List<Fabricante> listaFabricanteAux) {
        this.listaFabricanteAux = listaFabricanteAux;
    }

    public List<Fabricante> getListaFabricanteAuxFilter() {
        return listaFabricanteAuxFilter;
    }

    public void setListaFabricanteAuxFilter(List<Fabricante> listaFabricanteAuxFilter) {
        this.listaFabricanteAuxFilter = listaFabricanteAuxFilter;
    }

    public boolean isBnuevo() {
        return bnuevo;
    }

    public void setBnuevo(boolean bnuevo) {
        this.bnuevo = bnuevo;
    }

    public Integer getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(Integer idFabricante) {
        this.idFabricante = idFabricante;
    }

    public String getNombreFabricante() {
        return nombreFabricante;
    }

    public void setNombreFabricante(String nombreFabricante) {
        this.nombreFabricante = nombreFabricante;
    }

    public String getRegistroSanitario() {
        return registroSanitario;
    }

    public void setRegistroSanitario(String registroSanitario) {
        this.registroSanitario = registroSanitario;
    }
    
}
