package com.issste.sicabis.ejb.DTO;

import com.issste.sicabis.ejb.modelo.FalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Propuestas;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class FalloPropuestaProcDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private ProcedimientoRcb procedimientoRcb;
    private FalloProcedimientoRcb falloProcedimientoRcb;
    private List<Propuestas> listaPropuestas;
    private BigDecimal precioUnitario;
    private BigDecimal descuentoOtorgado;
    private Integer idFalloPropuestaProcDTO;
    private BigDecimal importe;
    private Integer piezas;
    private Integer piezasAdjudicadas;
    private Integer idPadre;
    private boolean bopcionAgrega;
    private boolean bopcionQuitar;
    private Integer porciento;

    public ProcedimientoRcb getProcedimientoRcb() {
        return procedimientoRcb;
    }

    public void setProcedimientoRcb(ProcedimientoRcb procedimientoRcb) {
        this.procedimientoRcb = procedimientoRcb;
    }

    public FalloProcedimientoRcb getFalloProcedimientoRcb() {
        return falloProcedimientoRcb;
    }

    public void setFalloProcedimientoRcb(FalloProcedimientoRcb falloProcedimientoRcb) {
        this.falloProcedimientoRcb = falloProcedimientoRcb;
    }

    public List<Propuestas> getListaPropuestas() {
        return listaPropuestas;
    }

    public void setListaPropuestas(List<Propuestas> listaPropuestas) {
        this.listaPropuestas = listaPropuestas;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getDescuentoOtorgado() {
        return descuentoOtorgado;
    }

    public void setDescuentoOtorgado(BigDecimal descuentoOtorgado) {
        this.descuentoOtorgado = descuentoOtorgado;
    }

    public Integer getIdFalloPropuestaProcDTO() {
        return idFalloPropuestaProcDTO;
    }

    public void setIdFalloPropuestaProcDTO(Integer idFalloPropuestaProcDTO) {
        this.idFalloPropuestaProcDTO = idFalloPropuestaProcDTO;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Integer getPiezas() {
        return piezas;
    }

    public void setPiezas(Integer piezas) {
        this.piezas = piezas;
    }

    public Integer getPiezasAdjudicadas() {
        return piezasAdjudicadas;
    }

    public void setPiezasAdjudicadas(Integer piezasAdjudicadas) {
        this.piezasAdjudicadas = piezasAdjudicadas;
    }

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
    }

    public boolean isBopcionAgrega() {
        return bopcionAgrega;
    }

    public void setBopcionAgrega(boolean bopcionAgrega) {
        this.bopcionAgrega = bopcionAgrega;
    }

    public boolean isBopcionQuitar() {
        return bopcionQuitar;
    }

    public void setBopcionQuitar(boolean bopcionQuitar) {
        this.bopcionQuitar = bopcionQuitar;
    }

    public Integer getPorciento() {
        return porciento;
    }

    public void setPorciento(Integer porciento) {
        this.porciento = porciento;
    }

}
