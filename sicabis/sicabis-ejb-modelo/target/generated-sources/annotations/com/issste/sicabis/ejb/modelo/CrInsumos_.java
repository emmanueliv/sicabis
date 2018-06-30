package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Cr;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.TipoCompra;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(CrInsumos.class)
public class CrInsumos_ { 

    public static volatile SingularAttribute<CrInsumos, BigDecimal> importe;
    public static volatile SingularAttribute<CrInsumos, TipoCompra> idTipoCompra;
    public static volatile SingularAttribute<CrInsumos, String> usuarioModificacion;
    public static volatile SingularAttribute<CrInsumos, Insumos> idInsumo;
    public static volatile SingularAttribute<CrInsumos, Integer> activo;
    public static volatile SingularAttribute<CrInsumos, Integer> cantidadPiezas;
    public static volatile SingularAttribute<CrInsumos, BigDecimal> precioUnitario;
    public static volatile SingularAttribute<CrInsumos, Date> fechaBaja;
    public static volatile SingularAttribute<CrInsumos, Cr> idCr;
    public static volatile SingularAttribute<CrInsumos, String> usuarioBaja;
    public static volatile SingularAttribute<CrInsumos, Integer> existencias;
    public static volatile SingularAttribute<CrInsumos, Integer> mesesCobertura;
    public static volatile SingularAttribute<CrInsumos, Date> fechaAlta;
    public static volatile SingularAttribute<CrInsumos, Integer> consumoPromedio;
    public static volatile SingularAttribute<CrInsumos, Date> fechaModificacion;
    public static volatile SingularAttribute<CrInsumos, Integer> idCrInsumos;
    public static volatile SingularAttribute<CrInsumos, String> usuarioAlta;

}