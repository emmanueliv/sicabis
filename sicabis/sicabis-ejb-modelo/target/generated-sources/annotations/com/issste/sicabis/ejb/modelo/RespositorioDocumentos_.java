package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(RespositorioDocumentos.class)
public class RespositorioDocumentos_ { 

    public static volatile SingularAttribute<RespositorioDocumentos, String> nombre;
    public static volatile SingularAttribute<RespositorioDocumentos, String> nombreArchivo;
    public static volatile SingularAttribute<RespositorioDocumentos, String> usuarioModificacion;
    public static volatile SingularAttribute<RespositorioDocumentos, Integer> idProceso;
    public static volatile SingularAttribute<RespositorioDocumentos, Date> fechaBaja;
    public static volatile SingularAttribute<RespositorioDocumentos, TipoDocumentos> idTipoDocumento;
    public static volatile SingularAttribute<RespositorioDocumentos, String> usuarioBaja;
    public static volatile SingularAttribute<RespositorioDocumentos, String> ruta;
    public static volatile SingularAttribute<RespositorioDocumentos, Date> fechaAlta;
    public static volatile SingularAttribute<RespositorioDocumentos, Date> fechaModificacion;
    public static volatile SingularAttribute<RespositorioDocumentos, Integer> idRespositorioDocumento;
    public static volatile SingularAttribute<RespositorioDocumentos, String> usuarioAlta;
    public static volatile SingularAttribute<RespositorioDocumentos, Integer> activo;

}