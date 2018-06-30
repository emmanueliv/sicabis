package com.issste.sicabis.controller;

import com.issste.sicabis.ejb.ln.BitacoraTareaSerice;
import com.issste.sicabis.ejb.ln.PeriodoMesService;
import com.issste.sicabis.ejb.modelo.BitacoraTareaEstatus;
import com.issste.sicabis.ejb.modelo.PeriodoMes;
import com.issste.sicabis.ejb.modelo.Usuarios;
import com.issste.sicabis.utils.Mensajes;
import com.issste.sicabis.utils.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

public class ConfiguracionDiaMesSolicitudesBean implements Serializable {

    @EJB
    private BitacoraTareaSerice bitacoraTareaSerice;

    @EJB
    private PeriodoMesService periodoMesService;

    private Usuarios usuarios;
    private PeriodoMes periodoMes;
    private BitacoraTareaEstatus bitacoraTareaEstatus;

    private int anio;
    private int anioAux;

    private List<PeriodoMes> listaPeriodoMes;

    private Mensajes mensaje = new Mensajes();
    private Utilidades util = new Utilidades();

    @PostConstruct
    public void init() {
        usuarios = (Usuarios) util.getSessionAtributte("usuario");
        listaPeriodoMes = new ArrayList();
        bitacoraTareaEstatus = new BitacoraTareaEstatus();
        anioAux = util.getYear();
        anio = anioAux;
        obtieneListaByAnio(anio);
    }

    public void obtieneListaByAnio(int anio) {
        int activo = 0;
        boolean bactivo = true;
        if (anio > util.getYear()) {
            activo = 1;
            bactivo = false;
        }
        List<PeriodoMes> listaPeriodoMes = new ArrayList();
        PeriodoMes pm = null;
        for (int i = 1; i < 13; i++) {
            if (bactivo) {
                if (i > util.getMonth(new Date())) {
                    activo = 1;
                    bactivo = false;
                }
            }
            pm = new PeriodoMes();
            pm.setMesLetra(util.getNameByMonth(i));
            pm.setFechaInicial(util.getFecha(1, i, anio));
            pm.setFechaFinal(util.getFecha(1, i + 1, anio));
            pm.setFechaFinal(util.sumarRestarDiasFecha(pm.getFechaFinal(), -1));
            pm.setActivo(activo);
            pm.setFechaAlta(new Date());
            pm.setUsuarioAlta(usuarios.getUsuario());
            listaPeriodoMes.add(pm);
        }
        this.listaPeriodoMes = periodoMesService.getByAnio(anio);
        if (this.listaPeriodoMes != null) {
            for (PeriodoMes pmAux : this.listaPeriodoMes) {
                int mes = util.getMonth(pmAux.getFechaCorte());
                listaPeriodoMes.set(mes - 1, pmAux);
            }
            this.listaPeriodoMes.clear();
        }
        this.listaPeriodoMes = listaPeriodoMes;
    }

    public void cambiaAnio() {
        obtieneListaByAnio(anio);
    }

    public void guardar() {
        boolean band = false;
        for (PeriodoMes pm : listaPeriodoMes) {
            if (pm.getFechaCorte() != null) {
                if (periodoMesService.guardarPeriodoMes(pm)) {
                    band = true;
                }
            }
        }
        if (band) {
            mensaje.mensaje(mensaje.datos_guardados, "verde");
            bitacoraTareaEstatus.setDescripcion("Actualizar configuracion día/mes solicitudes");
            bitacoraTareaEstatus.setFecha(new Date());
            bitacoraTareaEstatus.setIdModulos(1);
            bitacoraTareaEstatus.setIdUsuarios(usuarios.getIdUsuario());
            bitacoraTareaEstatus.setIdTareaId(0);
            bitacoraTareaSerice.guardarEnBitacora(bitacoraTareaEstatus);
        }
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public int getAnioAux() {
        return anioAux;
    }

    public void setAnioAux(int anioAux) {
        this.anioAux = anioAux;
    }

    public List<PeriodoMes> getListaPeriodoMes() {
        return listaPeriodoMes;
    }

    public void setListaPeriodoMes(List<PeriodoMes> listaPeriodoMes) {
        this.listaPeriodoMes = listaPeriodoMes;
    }

    public PeriodoMes getPeriodoMes() {
        return periodoMes;
    }

    public void setPeriodoMes(PeriodoMes periodoMes) {
        this.periodoMes = periodoMes;
    }

}
