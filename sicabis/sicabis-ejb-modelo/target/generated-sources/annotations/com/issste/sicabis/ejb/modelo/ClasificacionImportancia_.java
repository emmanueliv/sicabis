package com.issste.sicabis.ejb.modelo;

import com.issste.sicabis.ejb.modelo.Insumos;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-22T12:18:02")
@StaticMetamodel(ClasificacionImportancia.class)
public class ClasificacionImportancia_ { 

    public static volatile SingularAttribute<ClasificacionImportancia, String> usuarioModificacion;
    public static volatile ListAttribute<ClasificacionImportancia, Insumos> insumosList;
    public static volatile SingularAttribute<ClasificacionImportancia, String> sigla;
    public static volatile SingularAttribute<ClasificacionImportancia, Date> fechaBaja;
    public static volatile SingularAttribute<ClasificacionImportancia, String> usuarioBaja;
    public static volatile SingularAttribute<ClasificacionImportancia, String> descripcion;
    public static volatile SingularAttribute<ClasificacionImportancia, Date> fechaAlta;
    public static volatile SingularAttribute<ClasificacionImportancia, Date> fechaModificacion;
    public static volatile SingularAttribute<ClasificacionImportancia, String> usuarioAlta;
    public static volatile SingularAttribute<ClasificacionImportancia, Integer> activo;
    public static volatile SingularAttribute<ClasificacionImportancia, Integer> idClasificacionImportancia;

}