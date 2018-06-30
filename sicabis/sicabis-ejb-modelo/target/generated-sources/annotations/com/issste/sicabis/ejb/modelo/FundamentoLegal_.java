package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Contratos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(FundamentoLegal.class)
public class FundamentoLegal_ { 

    public static volatile SingularAttribute<FundamentoLegal, String> nombre;
    public static volatile SingularAttribute<FundamentoLegal, String> usuarioModificacion;
    public static volatile SingularAttribute<FundamentoLegal, Date> fechaBaja;
    public static volatile SingularAttribute<FundamentoLegal, String> usuarioBaja;
    public static volatile SingularAttribute<FundamentoLegal, Integer> idPadre;
    public static volatile SingularAttribute<FundamentoLegal, String> descripcion;
    public static volatile SingularAttribute<FundamentoLegal, Date> fechaAlta;
    public static volatile SingularAttribute<FundamentoLegal, Date> fechaModificacion;
    public static volatile SingularAttribute<FundamentoLegal, Integer> idFundamentoLegal;
    public static volatile SingularAttribute<FundamentoLegal, String> usuarioAlta;
    public static volatile ListAttribute<FundamentoLegal, Contratos> contratosList;
    public static volatile SingularAttribute<FundamentoLegal, Integer> activo;

}