package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Almacen;
import com.issste.sicabis.ejb.modelo.CompradorContrato;
import com.issste.sicabis.ejb.modelo.ContratoFalloProcedimientoRcb;
import com.issste.sicabis.ejb.modelo.Destinos;
import com.issste.sicabis.ejb.modelo.Estatus;
import com.issste.sicabis.ejb.modelo.FundamentoLegal;
import com.issste.sicabis.ejb.modelo.OrdenSuministro;
import com.issste.sicabis.ejb.modelo.PartidaPresupuestal;
import com.issste.sicabis.ejb.modelo.TipoContrato;
import com.issste.sicabis.ejb.modelo.TipoConvenio;
import com.issste.sicabis.ejb.modelo.TipoInsumos;
import com.issste.sicabis.ejb.modelo.UnidadesMedicas;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(Contratos.class)
public class Contratos_ { 

    public static volatile ListAttribute<Contratos, CompradorContrato> compradorContratoList;
    public static volatile ListAttribute<Contratos, ContratoFalloProcedimientoRcb> idContratoFalloProcedimientoRcbList;
    public static volatile SingularAttribute<Contratos, Integer> anioAfectacion;
    public static volatile SingularAttribute<Contratos, String> nep;
    public static volatile SingularAttribute<Contratos, Date> vigenciaFinal;
    public static volatile SingularAttribute<Contratos, String> oficioSuficiencia;
    public static volatile SingularAttribute<Contratos, TipoContrato> idTipoContrato;
    public static volatile ListAttribute<Contratos, OrdenSuministro> ordenSuministroList;
    public static volatile SingularAttribute<Contratos, Date> fechaBaja;
    public static volatile SingularAttribute<Contratos, String> usuarioBaja;
    public static volatile SingularAttribute<Contratos, String> condicionPago;
    public static volatile SingularAttribute<Contratos, Date> fechaModificacion;
    public static volatile SingularAttribute<Contratos, PartidaPresupuestal> idPartidaPresupuestal;
    public static volatile SingularAttribute<Contratos, Estatus> idEstatus;
    public static volatile SingularAttribute<Contratos, Date> fechaContrato;
    public static volatile SingularAttribute<Contratos, Date> vigenciaInicial;
    public static volatile SingularAttribute<Contratos, String> numeroContrato;
    public static volatile SingularAttribute<Contratos, BigDecimal> importe;
    public static volatile SingularAttribute<Contratos, Destinos> idDestino;
    public static volatile SingularAttribute<Contratos, String> usuarioModificacion;
    public static volatile SingularAttribute<Contratos, Date> fechaFormalizacion;
    public static volatile SingularAttribute<Contratos, Integer> idPadre;
    public static volatile SingularAttribute<Contratos, TipoInsumos> idTipoInsumos;
    public static volatile SingularAttribute<Contratos, TipoConvenio> idTipoConvenio;
    public static volatile SingularAttribute<Contratos, Integer> idContrato;
    public static volatile SingularAttribute<Contratos, FundamentoLegal> idFundamentoLegal;
    public static volatile SingularAttribute<Contratos, Integer> activo;
    public static volatile SingularAttribute<Contratos, String> numeroConvenio;
    public static volatile SingularAttribute<Contratos, String> notas;
    public static volatile SingularAttribute<Contratos, String> acuerdo;
    public static volatile SingularAttribute<Contratos, UnidadesMedicas> idUnidadesMedicas;
    public static volatile SingularAttribute<Contratos, Date> fechaOficioSuficiencia;
    public static volatile SingularAttribute<Contratos, Date> fechaAlta;
    public static volatile SingularAttribute<Contratos, String> usuarioAlta;
    public static volatile SingularAttribute<Contratos, String> clausula;
    public static volatile SingularAttribute<Contratos, Almacen> idAlmacen;

}