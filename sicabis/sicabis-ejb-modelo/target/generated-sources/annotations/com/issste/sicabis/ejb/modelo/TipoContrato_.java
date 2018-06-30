package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Contratos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(TipoContrato.class)
public class TipoContrato_ { 

    public static volatile SingularAttribute<TipoContrato, String> usuarioModificacion;
    public static volatile SingularAttribute<TipoContrato, Date> fechaBaja;
    public static volatile SingularAttribute<TipoContrato, String> usuarioBaja;
    public static volatile SingularAttribute<TipoContrato, String> descripcion;
    public static volatile SingularAttribute<TipoContrato, Date> fechaAlta;
    public static volatile SingularAttribute<TipoContrato, Date> fechaModificacion;
    public static volatile SingularAttribute<TipoContrato, Integer> idTipoContrato;
    public static volatile SingularAttribute<TipoContrato, String> usuarioAlta;
    public static volatile ListAttribute<TipoContrato, Contratos> contratosList;

}