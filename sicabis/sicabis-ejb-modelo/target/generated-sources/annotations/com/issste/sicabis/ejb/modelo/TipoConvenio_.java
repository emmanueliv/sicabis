package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Contratos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(TipoConvenio.class)
public class TipoConvenio_ { 

    public static volatile SingularAttribute<TipoConvenio, String> nombre;
    public static volatile SingularAttribute<TipoConvenio, String> usuarioModificacion;
    public static volatile SingularAttribute<TipoConvenio, Date> fechaBaja;
    public static volatile SingularAttribute<TipoConvenio, String> usuarioBaja;
    public static volatile SingularAttribute<TipoConvenio, String> descripcion;
    public static volatile SingularAttribute<TipoConvenio, Integer> idTipoConvenio;
    public static volatile SingularAttribute<TipoConvenio, Date> fechaAlta;
    public static volatile SingularAttribute<TipoConvenio, Date> fechaModificacion;
    public static volatile SingularAttribute<TipoConvenio, String> usuarioAlta;
    public static volatile SingularAttribute<TipoConvenio, Integer> activo;
    public static volatile ListAttribute<TipoConvenio, Contratos> contratosList;

}