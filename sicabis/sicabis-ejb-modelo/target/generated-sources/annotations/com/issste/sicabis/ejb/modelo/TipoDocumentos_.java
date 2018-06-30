package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.ejb.modelo.Tareas;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(TipoDocumentos.class)
public class TipoDocumentos_ { 

    public static volatile SingularAttribute<TipoDocumentos, String> nombre;
    public static volatile SingularAttribute<TipoDocumentos, String> extension;
    public static volatile SingularAttribute<TipoDocumentos, Tareas> idTarea;
    public static volatile SingularAttribute<TipoDocumentos, String> usuarioModificacion;
    public static volatile SingularAttribute<TipoDocumentos, Integer> idTipoDocumento;
    public static volatile SingularAttribute<TipoDocumentos, Date> fechaBaja;
    public static volatile SingularAttribute<TipoDocumentos, String> usuarioBaja;
    public static volatile SingularAttribute<TipoDocumentos, Date> fechaAlta;
    public static volatile SingularAttribute<TipoDocumentos, Date> fechaModificacion;
    public static volatile SingularAttribute<TipoDocumentos, String> usuarioAlta;
    public static volatile SingularAttribute<TipoDocumentos, Integer> activo;
    public static volatile ListAttribute<TipoDocumentos, RespositorioDocumentos> respositorioDocumentosList;

}