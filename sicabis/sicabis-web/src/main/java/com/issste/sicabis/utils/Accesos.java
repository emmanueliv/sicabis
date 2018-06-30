package com.issste.sicabis.utils;

import com.issste.sicabis.ejb.modelo.MenuPerfil;
import com.issste.sicabis.ejb.modelo.Perfiles;

public class Accesos {

    private boolean planeacion = false;
    private boolean adquisicion = false;
    private boolean recepcion = false;
    private boolean catalogoInstitucionalInsumosSalud = false;
    private boolean solicitudInsumosParaLaSalud = false;
    private boolean planeacionMensual = false;
    private boolean planeacionAnual = false;
    private boolean generacionRCB = false;
    private boolean adjudicacion = false;
    private boolean seguimientoContratos = false;
    private boolean penasConvencionales = false;
    private boolean tareaProgramadaActualizacionCatalogo = false;
    private boolean solicitudMensual = false;
    private boolean solicitudSoporteVida = false;
    private boolean consultaSolicitudesUnidadesMedicas = false;
    private boolean distribucionMensual = false;
    private boolean generacionAnteproyectoCR = false;
    private boolean rcbCreacionSeguimiento = false;
    private boolean solicitudInvestigacionMercado = false;
    private boolean generacionPreOrdenSuministro = false;
    private boolean procedimientos = false;
    private boolean presentacionPropuestas = false;
    private boolean fallos = false;
    private boolean contratos = false;
    private boolean convenios = false;
    private boolean ordenesSuministro = false;
    private boolean calculoPenasConvencionales = false;
    private boolean cancelaciones = false;
    private boolean recisiones = false;
    private boolean investigacionMercado = false;
    private boolean recepcionDocumental = false;
    private boolean controlCalidad = false;
    private boolean recepcionInsumosSalud = false;
    private boolean recoleccionInsumos = false;
    private boolean canjes = false;
    private boolean deductivas = false;
    private boolean reportes = false;
    private boolean reporteGeneralSeguimiento = false;
    private boolean administracion = false;
    private boolean usuarios = false;
    private boolean perfiles = false;
    private boolean catalogos = false;
    private boolean almacenes = false;
    private boolean areas = false;
    private boolean caracterProcedimiento = false;
    private boolean clasificaciones = false;
    private boolean clasificacionProcedimientos = false;
    private boolean compradores = false;
    private boolean configuraciones = false;
    private boolean contactos = false;
    private boolean destinos = false;
    private boolean estatus = false;
    private boolean fabricantes = false;
    private boolean fundamentosLegales = false;
    private boolean grupos = false;
    private boolean gruposTerapeuticos = false;
    private boolean pacientes = false;
    private boolean partidasPresupuestales = false;
    private boolean proveedores = false;
    private boolean tareas = false;
    private boolean tiposCompra = false;
    private boolean tiposContrato = false;
    private boolean tiposConvenio = false;
    private boolean tiposDocumento = false;
    private boolean tiposProcedimiento = false;
    private boolean tiposProveedor = false;
    private boolean tiposSolicitud = false;
    private boolean unidadesMedicas = false;
    private boolean distribucionSoporteVida = false;
    private boolean auditoria = false;
    private boolean condicionPago = false;
    private boolean presentaciones = false;
    private boolean clausulado = false;
    private boolean cierreDevoluciones = false;
    private boolean reporteConsolidadoAdjudicaciones = false;
    private boolean indicadores = false;
    private boolean colorPorcentaje = false;
    private boolean disponibilidadEstados = false;
    private boolean disponibilidadDelegacion = false;
    private boolean disponibilidadG40 = false;
    private boolean clasificacionImportancia = false;
    private boolean dpn = false;
    private boolean configuracionDiaMes = false;
    private boolean consultaDpn = false;
    private boolean reporteRecepcion = false;
    private boolean rptEntradasCenadi = false;
    private boolean rptControlCalidad = false;
    private boolean rptPiezasPendientesAnual = false;
    private boolean rptReporteClaves = false;
    private boolean rptReporteDevolucionesEntradas = false;
    private boolean rptReporteDevolucionesEntradasxEstatus = false;
    private boolean configuraDpn = false;
    private boolean existenciasSiamSilodisa = false;
    private boolean alertasDpn = false;
    private boolean configuraInsumosDpn = false;
    private boolean inidcadorProgramaUmu = false;
    private boolean avanceSurtimiento = false;
    private boolean reporteEntregaCenadi = false;
    private boolean reporteDetalleCenadi = false;
    private boolean reporteDetalleUmus = false;
    private boolean reporteGeneralExitenciaUmu = false;
    private boolean reporteGeneralExitenciaCenadi = false;
    private boolean reporteEntregaUmu = false;
    private boolean cancelacionRescision = false;
    private boolean tipoUsuarios = false;
    private boolean rptConcentrado = false;
    private boolean detalleIM = false;

    private boolean tareaProgramadaActualizacionCatalogoC = false;
    private boolean solicitudMensualC = false;
    private boolean solicitudSoporteVidaC = false;
    private boolean solicitudInsumosParaLaSaludC = false;
    private boolean consultaSolicitudesUnidadesMedicasC = false;
    private boolean distribucionMensualC = false;
    private boolean generacionAnteproyectoCRC = false;
    private boolean rcbCreacionSeguimientoC = false;
    private boolean solicitudInvestigacionMercadoC = false;
    private boolean generacionPreOrdenSuministroC = false;
    private boolean procedimientosC = false;
    private boolean presentacionPropuestasC = false;
    private boolean fallosC = false;
    private boolean contratosC = false;
    private boolean conveniosC = false;
    private boolean ordenesSuministroC = false;
    private boolean calculoPenasConvencionalesC = false;
    private boolean cancelacionesC = false;
    private boolean recisionesC = false;
    private boolean investigacionMercadoC = false;
    private boolean recepcionDocumentalC = false;
    private boolean controlCalidadC = false;
    private boolean recepcionInsumosSaludC = false;
    private boolean recoleccionInsumosC = false;
    private boolean canjesC = false;
    private boolean deductivasC = false;
    private boolean usuariosC = false;
    private boolean perfilesC = false;
    private boolean almacenesC = false;
    private boolean areasC = false;
    private boolean caracterProcedimientoC = false;
    private boolean clasificacionesC = false;
    private boolean clasificacionProcedimientosC = false;
    private boolean compradoresC = false;
    private boolean configuracionesC = false;
    private boolean contactosC = false;
    private boolean destinosC = false;
    private boolean estatusC = false;
    private boolean fabricantesC = false;
    private boolean fundamentosLegalesC = false;
    private boolean gruposC = false;
    private boolean gruposTerapeuticosC = false;
    private boolean pacientesC = false;
    private boolean partidasPresupuestalesC = false;
    private boolean proveedoresC = false;
    private boolean tareasC = false;
    private boolean tiposCompraC = false;
    private boolean tiposContratoC = false;
    private boolean tiposConvenioC = false;
    private boolean tiposDocumentoC = false;
    private boolean tiposProcedimientoC = false;
    private boolean tiposProveedorC = false;
    private boolean tiposSolicitudC = false;
    private boolean unidadesMedicasC = false;
    private boolean distribucionSoporteVidaC = false;
    private boolean auditoriaC = false;
    private boolean condicionPagoC = false;
    private boolean presentacionesC = false;
    private boolean clausuladoC = false;
    private boolean cierreDevolucionesC = false;
    private boolean colorPorcentajeC = false;
    private boolean clasificacionImportanciaC = false;
    private boolean configuracionDiaMesC = false;
    private boolean consultaDpnC = false;
    private boolean configuraDpnC = false;
    private boolean alertasDpnC = false;
    private boolean configuraInsumosDpnC = false;
    private boolean cancelacionRescisionC = false;
    private boolean tipoUsuariosC = false;
    private boolean detalleIMC = false;

    public void cargaMenus(Perfiles perfil) {
        //Deshabilitar menus que no corresponden al parfil de usuario
        for (MenuPerfil menuPerfil : perfil.getMenuPerfilList()) {
            switch (menuPerfil.getIdMenu().getIdMenu()) {
                case 1:
                    //Validacion en validarTareas();
                    break;
                case 2:
                    //Validacion en validarTareas();
                    break;
                case 3:
                    //Validacion en validarTareas();
                    break;
                case 4:
                    //Validacion en validarTareas();
                    break;
                case 5:
                    solicitudInsumosParaLaSalud = true;
                    solicitudInsumosParaLaSaludC = menuPerfil.getConsulta() == 1;
                    break;
                case 6:
                    //Validacion en validarTareas();
                    break;
                case 7:
                    //Validacion en validarTareas();
                    break;
                case 8:
                    //Validacion en validarTareas();
                    break;
                case 9:
                    //Validacion en validarTareas();
                    break;
                case 10:
                    //Validacion en validarTareas();
                    break;
                case 11:
                    //Validacion en validarTareas();
                    break;
                case 12:
                    tareaProgramadaActualizacionCatalogo = true;
                    tareaProgramadaActualizacionCatalogoC = menuPerfil.getConsulta() == 1;
                    break;
                case 13:
                    solicitudMensual = true;
                    solicitudMensualC = menuPerfil.getConsulta() == 1;
                    break;
                case 14:
                    solicitudSoporteVida = true;
                    solicitudSoporteVidaC = menuPerfil.getConsulta() == 1;
                    break;
                case 15:
                    consultaSolicitudesUnidadesMedicas = true;
                    consultaSolicitudesUnidadesMedicasC = menuPerfil.getConsulta() == 1;
                    break;
                case 16:
                    distribucionMensual = true;
                    distribucionMensualC = menuPerfil.getConsulta() == 1;
                    break;
                case 17:
                    generacionAnteproyectoCR = true;
                    generacionAnteproyectoCRC = menuPerfil.getConsulta() == 1;
                    break;
                case 18:
                    rcbCreacionSeguimiento = true;
                    rcbCreacionSeguimientoC = menuPerfil.getConsulta() == 1;
                    break;
                case 19:
                    solicitudInvestigacionMercado = true;
                    solicitudInvestigacionMercadoC = menuPerfil.getConsulta() == 1;
                    break;
                case 20:
                    generacionPreOrdenSuministro = true;
                    generacionPreOrdenSuministroC = menuPerfil.getConsulta() == 1;
                    break;
                case 21:
                    procedimientos = true;
                    procedimientosC = menuPerfil.getConsulta() == 1;
                    break;
                case 22:
                    presentacionPropuestas = true;
                    presentacionPropuestasC = menuPerfil.getConsulta() == 1;
                    break;
                case 23:
                    fallos = true;
                    fallosC = menuPerfil.getConsulta() == 1;
                    break;
                case 24:
                    contratos = true;
                    contratosC = menuPerfil.getConsulta() == 1;
                    break;
                case 25:
                    convenios = true;
                    conveniosC = menuPerfil.getConsulta() == 1;
                    break;
                case 26:
                    ordenesSuministro = true;
                    ordenesSuministroC = menuPerfil.getConsulta() == 1;
                    break;
                case 27:
                    calculoPenasConvencionales = true;
                    calculoPenasConvencionalesC = menuPerfil.getConsulta() == 1;
                    break;
                case 28:
                    cancelaciones = true;
                    cancelacionesC = menuPerfil.getConsulta() == 1;
                    break;
                case 29:
                    recisiones = true;
                    recisionesC = menuPerfil.getConsulta() == 1;
                    break;
                case 30:
                    investigacionMercado = true;
                    investigacionMercadoC = menuPerfil.getConsulta() == 1;
                    break;
                case 31:
                    recepcionDocumental = true;
                    recepcionDocumentalC = menuPerfil.getConsulta() == 1;
                    break;
                case 32:
                    controlCalidad = true;
                    controlCalidadC = menuPerfil.getConsulta() == 1;
                    break;
                case 33:
                    recepcionInsumosSalud = true;
                    recepcionInsumosSaludC = menuPerfil.getConsulta() == 1;
                    break;
                case 34:
                    recoleccionInsumos = true;
                    recoleccionInsumosC = menuPerfil.getConsulta() == 1;
                    break;
                case 35:
                    canjes = true;
                    canjesC = menuPerfil.getConsulta() == 1;
                    break;
                case 36:
                    cierreDevoluciones = true;
                    cierreDevolucionesC = menuPerfil.getConsulta() == 1;
                    break;
                case 37:
                    //Validacion en validarTareas();
                    break;
                case 38:
                    reporteGeneralSeguimiento = true;
                    break;
                case 39:
                    //Validacion en validarTareas();
                    break;
                case 40:
                    usuarios = true;
                    usuariosC = menuPerfil.getConsulta() == 1;
                    break;
                case 41:
                    perfiles = true;
                    perfilesC = menuPerfil.getConsulta() == 1;
                    break;
                case 42:
                    //Validacion en validarTareas();
                    break;
                case 43:
                    almacenes = true;
                    almacenesC = menuPerfil.getConsulta() == 1;
                    break;
                case 44:
                    areas = true;
                    areasC = menuPerfil.getConsulta() == 1;
                    break;
                case 45:
                    caracterProcedimiento = true;
                    caracterProcedimientoC = menuPerfil.getConsulta() == 1;
                    break;
                case 46:
                    clasificaciones = true;
                    clasificacionesC = menuPerfil.getConsulta() == 1;
                    break;
                case 47:
                    clasificacionProcedimientos = true;
                    clasificacionProcedimientosC = menuPerfil.getConsulta() == 1;
                    break;
                case 48:
                    compradores = true;
                    compradoresC = menuPerfil.getConsulta() == 1;
                    break;
                case 49:
                    configuraciones = true;
                    configuracionesC = menuPerfil.getConsulta() == 1;
                    break;
                case 50:
                    contactos = true;
                    contactosC = menuPerfil.getConsulta() == 1;
                    break;
                case 51:
                    destinos = true;
                    destinosC = menuPerfil.getConsulta() == 1;
                    break;
                case 52:
                    estatus = true;
                    estatusC = menuPerfil.getConsulta() == 1;
                    break;
                case 53:
                    fabricantes = true;
                    fabricantesC = menuPerfil.getConsulta() == 1;
                    break;
                case 54:
                    fundamentosLegales = true;
                    fundamentosLegalesC = menuPerfil.getConsulta() == 1;
                    break;
                case 55:
                    grupos = true;
                    gruposC = menuPerfil.getConsulta() == 1;
                    break;
                case 56:
                    gruposTerapeuticos = true;
                    gruposTerapeuticosC = menuPerfil.getConsulta() == 1;
                    break;
                case 57:
                    pacientes = true;
                    pacientesC = menuPerfil.getConsulta() == 1;
                    break;
                case 58:
                    partidasPresupuestales = true;
                    partidasPresupuestalesC = menuPerfil.getConsulta() == 1;
                    break;
                case 59:
                    proveedores = true;
                    proveedoresC = menuPerfil.getConsulta() == 1;
                    break;
                case 60:
                    tareas = true;
                    tareasC = menuPerfil.getConsulta() == 1;
                    break;
                case 61:
                    tiposCompra = true;
                    tiposCompraC = menuPerfil.getConsulta() == 1;
                    break;
                case 62:
                    tiposContrato = true;
                    tiposContratoC = menuPerfil.getConsulta() == 1;
                    break;
                case 63:
                    tiposConvenio = true;
                    tiposConvenioC = menuPerfil.getConsulta() == 1;
                    break;
                case 64:
                    tiposDocumento = true;
                    tiposDocumentoC = menuPerfil.getConsulta() == 1;
                    break;
                case 65:
                    tiposProcedimiento = true;
                    tiposProcedimientoC = menuPerfil.getConsulta() == 1;
                    break;
                case 66:
                    tiposProveedor = true;
                    tiposProveedorC = menuPerfil.getConsulta() == 1;
                    break;
                case 67:
                    tiposSolicitud = true;
                    tiposSolicitudC = menuPerfil.getConsulta() == 1;
                    break;
                case 68:
                    unidadesMedicas = true;
                    unidadesMedicasC = menuPerfil.getConsulta() == 1;
                    break;
                case 69:
                    distribucionSoporteVida = true;
                    distribucionSoporteVidaC = menuPerfil.getConsulta() == 1;
                    break;
                case 70:
                    auditoria = true;
                    auditoriaC = menuPerfil.getConsulta() == 1;
                    break;
                case 71:
                    condicionPago = true;
                    condicionPagoC = menuPerfil.getConsulta() == 1;
                    break;
                case 72:
                    presentaciones = true;
                    presentacionesC = menuPerfil.getConsulta() == 1;
                    break;
                case 73:
                    clausulado = true;
                    clausuladoC = menuPerfil.getConsulta() == 1;
                    break;
                case 74:
                    reporteConsolidadoAdjudicaciones = true;
                    break;
                case 75:
                    //Validacion en validarTareas();
                    break;
                case 76:
                    colorPorcentaje = true;
                    colorPorcentajeC = menuPerfil.getConsulta() == 1;
                    break;
                case 77:
                    disponibilidadEstados = true;
                    break;
                case 78:
                    disponibilidadDelegacion = true;
                    break;
                case 79:
                    disponibilidadG40 = true;
                    break;
                case 80:
                    clasificacionImportancia = true;
                    clasificacionImportanciaC = menuPerfil.getConsulta() == 1;
                    break;
                case 81:
                    //Validacion en validarTareas();
                    break;
                case 82:
                    configuracionDiaMes = true;
                    configuracionDiaMesC = menuPerfil.getConsulta() == 1;
                    break;
                case 83:
                    consultaDpn = true;
                    consultaDpnC = menuPerfil.getConsulta() == 1;
                    break;
                case 84:
                    //Validacion en validarTareas();
                    break;
                case 85:
                    rptEntradasCenadi = true;
                    break;
                case 86:
                    configuraDpn = true;
                    configuraDpnC = menuPerfil.getConsulta() == 1;
                default:
                    break;
                case 87:
                    rptPiezasPendientesAnual = true;
                    break;
                case 88:
                    rptReporteClaves = true;
                    break;
                case 89:
                    rptReporteDevolucionesEntradas = true;
                    break;
                case 90:
                    rptReporteDevolucionesEntradasxEstatus = true;
                    break;
                case 91:
                    rptControlCalidad = true;
                    break;
                case 92:
                    existenciasSiamSilodisa = true;
                    break;
                case 93:
                    alertasDpn = true;
                    alertasDpnC = menuPerfil.getConsulta() == 1;
                    break;
                case 94:
                    configuraInsumosDpn = true;
                    configuraInsumosDpnC = menuPerfil.getConsulta() == 1;
                    break;
                case 95:
                    avanceSurtimiento = true;
                    break;
                case 96://Reporte de Entradas de MyMCRQ al CENADI
                    reporteEntregaCenadi = true;
                    break;
                case 97://Reporte Detallado de Existencias en el CENADI
                    reporteDetalleCenadi = true;
                    break;
                case 98://Reporte General de Existencias CENADI
                    reporteGeneralExitenciaCenadi = true;
                    break;
                case 99://Reporte Detallado de Existencias en UMUs
                    reporteDetalleUmus = true;
                    break;
                case 100://Reporte General de Existencias en UMU Programas
                    inidcadorProgramaUmu = true;
                    break;
                case 101://Reporte General de Existencias UMUs
                    reporteGeneralExitenciaUmu = true;
                    break;
                case 102://Reporte de Entregas a Unidades Medicas
                    reporteEntregaUmu = true;
                    break;
                case 103:
                    cancelacionRescision = true;
                    cancelacionRescisionC = menuPerfil.getConsulta() == 1;
                    break;
                case 104:
                    tipoUsuarios = true;
                    tipoUsuariosC = menuPerfil.getConsulta() == 1;
                    break;
                case 105:
                    rptConcentrado = true;
                    break;
                case 106:
                    detalleIM = true;
                    detalleIMC = menuPerfil.getConsulta() == 1;
            }
        }
        //System.out.println("reporteConsolidadoAdjudicaciones-->"+reporteConsolidadoAdjudicaciones);
        validarTareas();
    }

    //Se agregan validaciones, el orden va de nodo a menu padre
    public void validarTareas() {
        //Menu padre 4
        if (tareaProgramadaActualizacionCatalogo) {
            catalogoInstitucionalInsumosSalud = true;
        }

        //Menu padre 5
        if (solicitudMensual || solicitudSoporteVida) {
            solicitudInsumosParaLaSalud = true;
        }

        //Menu padre 6
        if (consultaSolicitudesUnidadesMedicas
                || distribucionMensual
                || distribucionSoporteVida) {
            planeacionMensual = true;
        }

        //Menú DPN
        if (configuracionDiaMes || consultaDpn || alertasDpn || configuraInsumosDpn) {
            dpn = true;
        }

        //Menu padre 7
        if (generacionAnteproyectoCR) {
            planeacionAnual = true;
        }

        //Menu padre 8
        if (rcbCreacionSeguimiento || solicitudInvestigacionMercado) {
            generacionRCB = true;
        }

        //Menu padre 1
        if (catalogoInstitucionalInsumosSalud
                || solicitudInsumosParaLaSalud
                || planeacionMensual
                || dpn
                || planeacionAnual
                || generacionRCB
                || generacionPreOrdenSuministro) {
            planeacion = true;
        }

        //Menu padre 9
        if (procedimientos || presentacionPropuestas || fallos) {
            adjudicacion = true;
        }

        //Menu padre 10
        if (contratos || convenios ) {
            seguimientoContratos = true;
        }

        //Menu padre 11
        if (calculoPenasConvencionales || cancelaciones || recisiones || cancelacionRescision) {
            penasConvencionales = true;
        }

        //Menu padre 2
        if (adjudicacion
                || seguimientoContratos
                || penasConvencionales
                || investigacionMercado) {
            adquisicion = true;
        }

        //El cierre de devoluciones lo puede realizar cualquiera de las sig. 3 areas. 
        if (controlCalidad || recepcionInsumosSalud
                || recoleccionInsumos) {
            cierreDevoluciones = true;
        }

        //Menu padre 3
        if (recepcionDocumental
                || controlCalidad
                || recepcionInsumosSalud
                || recoleccionInsumos
                || canjes
                || cierreDevoluciones) {
            recepcion = true;
        }
        //Menu padre 75 Indicadores

        if (disponibilidadEstados
                || disponibilidadDelegacion
                || disponibilidadG40
                || avanceSurtimiento
                || reporteEntregaCenadi
                || reporteDetalleCenadi
                || reporteGeneralExitenciaCenadi
                || reporteDetalleUmus
                || inidcadorProgramaUmu
                || reporteGeneralExitenciaUmu
                || reporteEntregaUmu) {
            indicadores = true;
        }

        //Menu padre 84 Reportes Recepción
        if (rptEntradasCenadi || rptControlCalidad || rptPiezasPendientesAnual || rptReporteClaves || rptReporteDevolucionesEntradas || rptReporteDevolucionesEntradasxEstatus || rptConcentrado) {
            reporteRecepcion = true;
        }

        //Menu padre 37
        if (reporteGeneralSeguimiento || reporteConsolidadoAdjudicaciones || indicadores || existenciasSiamSilodisa || reporteRecepcion) {
            reportes = true;
        }

        //Menu padre 42
        if (almacenes
                || areas
                || caracterProcedimiento
                || clasificaciones
                || clasificacionProcedimientos
                || compradores
                || configuraciones
                || contactos
                || destinos
                || estatus
                || fabricantes
                || fundamentosLegales
                || grupos
                || gruposTerapeuticos
                || pacientes
                || partidasPresupuestales
                || proveedores
                || tareas
                || tiposCompra
                || tiposContrato
                || tiposConvenio
                || tiposDocumento
                || tiposProcedimiento
                || tiposProveedor
                || tiposSolicitud
                || unidadesMedicas
                || condicionPago
                || presentaciones
                || clausulado
                || colorPorcentaje
                || clasificacionImportancia
                || configuraDpn
                || tipoUsuarios
                || detalleIM) {
            catalogos = true;
        }

        //Menu padre 39
        if (usuarios || perfiles || catalogos || auditoria) {
            administracion = true;
        }
    }

    public boolean isPlaneacion() {
        return planeacion;
    }

    public void setPlaneacion(boolean planeacion) {
        this.planeacion = planeacion;
    }

    public boolean isAdquisicion() {
        return adquisicion;
    }

    public void setAdquisicion(boolean adquisicion) {
        this.adquisicion = adquisicion;
    }

    public boolean isRecepcion() {
        return recepcion;
    }

    public void setRecepcion(boolean recepcion) {
        this.recepcion = recepcion;
    }

    public boolean isCatalogoInstitucionalInsumosSalud() {
        return catalogoInstitucionalInsumosSalud;
    }

    public void setCatalogoInstitucionalInsumosSalud(boolean catalogoInstitucionalInsumosSalud) {
        this.catalogoInstitucionalInsumosSalud = catalogoInstitucionalInsumosSalud;
    }

    public boolean isSolicitudInsumosParaLaSalud() {
        return solicitudInsumosParaLaSalud;
    }

    public void setSolicitudInsumosParaLaSalud(boolean solicitudInsumosParaLaSalud) {
        this.solicitudInsumosParaLaSalud = solicitudInsumosParaLaSalud;
    }

    public boolean isPlaneacionMensual() {
        return planeacionMensual;
    }

    public void setPlaneacionMensual(boolean planeacionMensual) {
        this.planeacionMensual = planeacionMensual;
    }

    public boolean isPlaneacionAnual() {
        return planeacionAnual;
    }

    public void setPlaneacionAnual(boolean planeacionAnual) {
        this.planeacionAnual = planeacionAnual;
    }

    public boolean isGeneracionRCB() {
        return generacionRCB;
    }

    public void setGeneracionRCB(boolean generacionRCB) {
        this.generacionRCB = generacionRCB;
    }

    public boolean isAdjudicacion() {
        return adjudicacion;
    }

    public void setAdjudicacion(boolean adjudicacion) {
        this.adjudicacion = adjudicacion;
    }

    public boolean isSeguimientoContratos() {
        return seguimientoContratos;
    }

    public void setSeguimientoContratos(boolean seguimientoContratos) {
        this.seguimientoContratos = seguimientoContratos;
    }

    public boolean isPenasConvencionales() {
        return penasConvencionales;
    }

    public void setPenasConvencionales(boolean penasConvencionales) {
        this.penasConvencionales = penasConvencionales;
    }

    public boolean isTareaProgramadaActualizacionCatalogo() {
        return tareaProgramadaActualizacionCatalogo;
    }

    public void setTareaProgramadaActualizacionCatalogo(boolean tareaProgramadaActualizacionCatalogo) {
        this.tareaProgramadaActualizacionCatalogo = tareaProgramadaActualizacionCatalogo;
    }

    public boolean isSolicitudMensual() {
        return solicitudMensual;
    }

    public void setSolicitudMensual(boolean solicitudMensual) {
        this.solicitudMensual = solicitudMensual;
    }

    public boolean isSolicitudSoporteVida() {
        return solicitudSoporteVida;
    }

    public void setSolicitudSoporteVida(boolean solicitudSoporteVida) {
        this.solicitudSoporteVida = solicitudSoporteVida;
    }

    public boolean isConsultaSolicitudesUnidadesMedicas() {
        return consultaSolicitudesUnidadesMedicas;
    }

    public void setConsultaSolicitudesUnidadesMedicas(boolean consultaSolicitudesUnidadesMedicas) {
        this.consultaSolicitudesUnidadesMedicas = consultaSolicitudesUnidadesMedicas;
    }

    public boolean isDistribucionMensual() {
        return distribucionMensual;
    }

    public void setDistribucionMensual(boolean distribucionMensual) {
        this.distribucionMensual = distribucionMensual;
    }

    public boolean isGeneracionAnteproyectoCR() {
        return generacionAnteproyectoCR;
    }

    public void setGeneracionAnteproyectoCR(boolean generacionAnteproyectoCR) {
        this.generacionAnteproyectoCR = generacionAnteproyectoCR;
    }

    public boolean isRcbCreacionSeguimiento() {
        return rcbCreacionSeguimiento;
    }

    public void setRcbCreacionSeguimiento(boolean rcbCreacionSeguimiento) {
        this.rcbCreacionSeguimiento = rcbCreacionSeguimiento;
    }

    public boolean isSolicitudInvestigacionMercado() {
        return solicitudInvestigacionMercado;
    }

    public void setSolicitudInvestigacionMercado(boolean solicitudInvestigacionMercado) {
        this.solicitudInvestigacionMercado = solicitudInvestigacionMercado;
    }

    public boolean isGeneracionPreOrdenSuministro() {
        return generacionPreOrdenSuministro;
    }

    public void setGeneracionPreOrdenSuministro(boolean generacionPreOrdenSuministro) {
        this.generacionPreOrdenSuministro = generacionPreOrdenSuministro;
    }

    public boolean isProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(boolean procedimientos) {
        this.procedimientos = procedimientos;
    }

    public boolean isPresentacionPropuestas() {
        return presentacionPropuestas;
    }

    public void setPresentacionPropuestas(boolean presentacionPropuestas) {
        this.presentacionPropuestas = presentacionPropuestas;
    }

    public boolean isFallos() {
        return fallos;
    }

    public void setFallos(boolean fallos) {
        this.fallos = fallos;
    }

    public boolean isContratos() {
        return contratos;
    }

    public void setContratos(boolean contratos) {
        this.contratos = contratos;
    }

    public boolean isConvenios() {
        return convenios;
    }

    public void setConvenios(boolean convenios) {
        this.convenios = convenios;
    }

    public boolean isOrdenesSuministro() {
        return ordenesSuministro;
    }

    public void setOrdenesSuministro(boolean ordenesSuministro) {
        this.ordenesSuministro = ordenesSuministro;
    }

    public boolean isCalculoPenasConvencionales() {
        return calculoPenasConvencionales;
    }

    public void setCalculoPenasConvencionales(boolean calculoPenasConvencionales) {
        this.calculoPenasConvencionales = calculoPenasConvencionales;
    }

    public boolean isCancelaciones() {
        return cancelaciones;
    }

    public void setCancelaciones(boolean cancelaciones) {
        this.cancelaciones = cancelaciones;
    }

    public boolean isRecisiones() {
        return recisiones;
    }

    public void setRecisiones(boolean recisiones) {
        this.recisiones = recisiones;
    }

    public boolean isInvestigacionMercado() {
        return investigacionMercado;
    }

    public void setInvestigacionMercado(boolean investigacionMercado) {
        this.investigacionMercado = investigacionMercado;
    }

    public boolean isRecepcionDocumental() {
        return recepcionDocumental;
    }

    public void setRecepcionDocumental(boolean recepcionDocumental) {
        this.recepcionDocumental = recepcionDocumental;
    }

    public boolean isControlCalidad() {
        return controlCalidad;
    }

    public void setControlCalidad(boolean controlCalidad) {
        this.controlCalidad = controlCalidad;
    }

    public boolean isRecepcionInsumosSalud() {
        return recepcionInsumosSalud;
    }

    public void setRecepcionInsumosSalud(boolean recepcionInsumosSalud) {
        this.recepcionInsumosSalud = recepcionInsumosSalud;
    }

    public boolean isRecoleccionInsumos() {
        return recoleccionInsumos;
    }

    public void setRecoleccionInsumos(boolean recoleccionInsumos) {
        this.recoleccionInsumos = recoleccionInsumos;
    }

    public boolean isCanjes() {
        return canjes;
    }

    public void setCanjes(boolean canjes) {
        this.canjes = canjes;
    }

    public boolean isDeductivas() {
        return deductivas;
    }

    public void setDeductivas(boolean deductivas) {
        this.deductivas = deductivas;
    }

    public boolean isReportes() {
        return reportes;
    }

    public void setReportes(boolean reportes) {
        this.reportes = reportes;
    }

    public boolean isReporteGeneralSeguimiento() {
        return reporteGeneralSeguimiento;
    }

    public void setReporteGeneralSeguimiento(boolean reporteGeneralSeguimiento) {
        this.reporteGeneralSeguimiento = reporteGeneralSeguimiento;
    }

    public boolean isAdministracion() {
        return administracion;
    }

    public void setAdministracion(boolean administracion) {
        this.administracion = administracion;
    }

    public boolean isUsuarios() {
        return usuarios;
    }

    public void setUsuarios(boolean usuarios) {
        this.usuarios = usuarios;
    }

    public boolean isPerfiles() {
        return perfiles;
    }

    public void setPerfiles(boolean perfiles) {
        this.perfiles = perfiles;
    }

    public boolean isCatalogos() {
        return catalogos;
    }

    public void setCatalogos(boolean catalogos) {
        this.catalogos = catalogos;
    }

    public boolean isAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(boolean almacenes) {
        this.almacenes = almacenes;
    }

    public boolean isAreas() {
        return areas;
    }

    public void setAreas(boolean areas) {
        this.areas = areas;
    }

    public boolean isCaracterProcedimiento() {
        return caracterProcedimiento;
    }

    public void setCaracterProcedimiento(boolean caracterProcedimiento) {
        this.caracterProcedimiento = caracterProcedimiento;
    }

    public boolean isClasificaciones() {
        return clasificaciones;
    }

    public void setClasificaciones(boolean clasificaciones) {
        this.clasificaciones = clasificaciones;
    }

    public boolean isClasificacionProcedimientos() {
        return clasificacionProcedimientos;
    }

    public void setClasificacionProcedimientos(boolean clasificacionProcedimientos) {
        this.clasificacionProcedimientos = clasificacionProcedimientos;
    }

    public boolean isCompradores() {
        return compradores;
    }

    public void setCompradores(boolean compradores) {
        this.compradores = compradores;
    }

    public boolean isConfiguraciones() {
        return configuraciones;
    }

    public void setConfiguraciones(boolean configuraciones) {
        this.configuraciones = configuraciones;
    }

    public boolean isContactos() {
        return contactos;
    }

    public void setContactos(boolean contactos) {
        this.contactos = contactos;
    }

    public boolean isDestinos() {
        return destinos;
    }

    public void setDestinos(boolean destinos) {
        this.destinos = destinos;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public boolean isFabricantes() {
        return fabricantes;
    }

    public void setFabricantes(boolean fabricantes) {
        this.fabricantes = fabricantes;
    }

    public boolean isFundamentosLegales() {
        return fundamentosLegales;
    }

    public void setFundamentosLegales(boolean fundamentosLegales) {
        this.fundamentosLegales = fundamentosLegales;
    }

    public boolean isGrupos() {
        return grupos;
    }

    public void setGrupos(boolean grupos) {
        this.grupos = grupos;
    }

    public boolean isGruposTerapeuticos() {
        return gruposTerapeuticos;
    }

    public void setGruposTerapeuticos(boolean gruposTerapeuticos) {
        this.gruposTerapeuticos = gruposTerapeuticos;
    }

    public boolean isPacientes() {
        return pacientes;
    }

    public void setPacientes(boolean pacientes) {
        this.pacientes = pacientes;
    }

    public boolean isPartidasPresupuestales() {
        return partidasPresupuestales;
    }

    public void setPartidasPresupuestales(boolean partidasPresupuestales) {
        this.partidasPresupuestales = partidasPresupuestales;
    }

    public boolean isProveedores() {
        return proveedores;
    }

    public void setProveedores(boolean proveedores) {
        this.proveedores = proveedores;
    }

    public boolean isTareas() {
        return tareas;
    }

    public void setTareas(boolean tareas) {
        this.tareas = tareas;
    }

    public boolean isTiposCompra() {
        return tiposCompra;
    }

    public void setTiposCompra(boolean tiposCompra) {
        this.tiposCompra = tiposCompra;
    }

    public boolean isTiposContrato() {
        return tiposContrato;
    }

    public void setTiposContrato(boolean tiposContrato) {
        this.tiposContrato = tiposContrato;
    }

    public boolean isTiposConvenio() {
        return tiposConvenio;
    }

    public void setTiposConvenio(boolean tiposConvenio) {
        this.tiposConvenio = tiposConvenio;
    }

    public boolean isTiposDocumento() {
        return tiposDocumento;
    }

    public void setTiposDocumento(boolean tiposDocumento) {
        this.tiposDocumento = tiposDocumento;
    }

    public boolean isTiposProcedimiento() {
        return tiposProcedimiento;
    }

    public void setTiposProcedimiento(boolean tiposProcedimiento) {
        this.tiposProcedimiento = tiposProcedimiento;
    }

    public boolean isTiposProveedor() {
        return tiposProveedor;
    }

    public void setTiposProveedor(boolean tiposProveedor) {
        this.tiposProveedor = tiposProveedor;
    }

    public boolean isTiposSolicitud() {
        return tiposSolicitud;
    }

    public void setTiposSolicitud(boolean tiposSolicitud) {
        this.tiposSolicitud = tiposSolicitud;
    }

    public boolean isUnidadesMedicas() {
        return unidadesMedicas;
    }

    public void setUnidadesMedicas(boolean unidadesMedicas) {
        this.unidadesMedicas = unidadesMedicas;
    }

    public boolean isDistribucionSoporteVida() {
        return distribucionSoporteVida;
    }

    public void setDistribucionSoporteVida(boolean distribucionSoporteVida) {
        this.distribucionSoporteVida = distribucionSoporteVida;
    }

    public boolean isAuditoria() {
        return auditoria;
    }

    public void setAuditoria(boolean auditoria) {
        this.auditoria = auditoria;
    }

    public boolean isCondicionPago() {
        return condicionPago;
    }

    public void setCondicionPago(boolean condicionPago) {
        this.condicionPago = condicionPago;
    }

    public boolean isPresentaciones() {
        return presentaciones;
    }

    public void setPresentaciones(boolean presentaciones) {
        this.presentaciones = presentaciones;
    }

    public boolean isClausulado() {
        return clausulado;
    }

    public void setClausulado(boolean clausulado) {
        this.clausulado = clausulado;
    }

    public boolean isCierreDevoluciones() {
        return cierreDevoluciones;
    }

    public void setCierreDevoluciones(boolean cierreDevoluciones) {
        this.cierreDevoluciones = cierreDevoluciones;
    }

    public boolean isReporteConsolidadoAdjudicaciones() {
        return reporteConsolidadoAdjudicaciones;
    }

    public void setReporteConsolidadoAdjudicaciones(boolean reporteConsolidadoAdjudicaciones) {
        this.reporteConsolidadoAdjudicaciones = reporteConsolidadoAdjudicaciones;
    }

    public boolean isIndicadores() {
        return indicadores;
    }

    public void setIndicadores(boolean indicadores) {
        this.indicadores = indicadores;
    }

    public boolean isColorPorcentaje() {
        return colorPorcentaje;
    }

    public void setColorPorcentaje(boolean colorPorcentaje) {
        this.colorPorcentaje = colorPorcentaje;
    }

    public boolean isDisponibilidadEstados() {
        return disponibilidadEstados;
    }

    public void setDisponibilidadEstados(boolean disponibilidadEstados) {
        this.disponibilidadEstados = disponibilidadEstados;
    }

    public boolean isDisponibilidadDelegacion() {
        return disponibilidadDelegacion;
    }

    public void setDisponibilidadDelegacion(boolean disponibilidadDelegacion) {
        this.disponibilidadDelegacion = disponibilidadDelegacion;
    }

    public boolean isDisponibilidadG40() {
        return disponibilidadG40;
    }

    public void setDisponibilidadG40(boolean disponibilidadG40) {
        this.disponibilidadG40 = disponibilidadG40;
    }

    public boolean isClasificacionImportancia() {
        return clasificacionImportancia;
    }

    public void setClasificacionImportancia(boolean clasificacionImportancia) {
        this.clasificacionImportancia = clasificacionImportancia;
    }

    public boolean isDpn() {
        return dpn;
    }

    public void setDpn(boolean dpn) {
        this.dpn = dpn;
    }

    public boolean isConfiguracionDiaMes() {
        return configuracionDiaMes;
    }

    public void setConfiguracionDiaMes(boolean configuracionDiaMes) {
        this.configuracionDiaMes = configuracionDiaMes;
    }

    public boolean isConsultaDpn() {
        return consultaDpn;
    }

    public void setConsultaDpn(boolean consultaDpn) {
        this.consultaDpn = consultaDpn;
    }

    public boolean isReporteRecepcion() {
        return reporteRecepcion;
    }

    public void setReporteRecepcion(boolean reporteRecepcion) {
        this.reporteRecepcion = reporteRecepcion;
    }

    public boolean isRptEntradasCenadi() {
        return rptEntradasCenadi;
    }

    public void setRptEntradasCenadi(boolean rptEntradasCenadi) {
        this.rptEntradasCenadi = rptEntradasCenadi;
    }

    public boolean isConfiguraDpn() {
        return configuraDpn;
    }

    public void setConfiguraDpn(boolean configuraDpn) {
        this.configuraDpn = configuraDpn;
    }

    public boolean isRptControlCalidad() {
        return rptControlCalidad;
    }

    public void setRptControlCalidad(boolean rptControlCalidad) {
        this.rptControlCalidad = rptControlCalidad;
    }

    public boolean isRptPiezasPendientesAnual() {
        return rptPiezasPendientesAnual;
    }

    public void setRptPiezasPendientesAnual(boolean rptPiezasPendientesAnual) {
        this.rptPiezasPendientesAnual = rptPiezasPendientesAnual;
    }

    public boolean isRptReporteClaves() {
        return rptReporteClaves;
    }

    public void setRptReporteClaves(boolean rptReporteClaves) {
        this.rptReporteClaves = rptReporteClaves;
    }

    public boolean isRptReporteDevolucionesEntradas() {
        return rptReporteDevolucionesEntradas;
    }

    public void setRptReporteDevolucionesEntradas(boolean rptReporteDevolucionesEntradas) {
        this.rptReporteDevolucionesEntradas = rptReporteDevolucionesEntradas;
    }

    public boolean isRptReporteDevolucionesEntradasxEstatus() {
        return rptReporteDevolucionesEntradasxEstatus;
    }

    public void setRptReporteDevolucionesEntradasxEstatus(boolean rptReporteDevolucionesEntradasxEstatus) {
        this.rptReporteDevolucionesEntradasxEstatus = rptReporteDevolucionesEntradasxEstatus;
    }

    public boolean isExistenciasSiamSilodisa() {
        return existenciasSiamSilodisa;
    }

    public void setExistenciasSiamSilodisa(boolean existenciasSiamSilodisa) {
        this.existenciasSiamSilodisa = existenciasSiamSilodisa;
    }

    public boolean isAlertasDpn() {
        return alertasDpn;
    }

    public void setAlertasDpn(boolean alertasDpn) {
        this.alertasDpn = alertasDpn;
    }

    public boolean isConfiguraInsumosDpn() {
        return configuraInsumosDpn;
    }

    public void setConfiguraInsumosDpn(boolean configuraInsumosDpn) {
        this.configuraInsumosDpn = configuraInsumosDpn;
    }

    public boolean isInidcadorProgramaUmu() {
        return inidcadorProgramaUmu;
    }

    public void setInidcadorProgramaUmu(boolean inidcadorProgramaUmu) {
        this.inidcadorProgramaUmu = inidcadorProgramaUmu;
    }

    public boolean isAvanceSurtimiento() {
        return avanceSurtimiento;
    }

    public void setAvanceSurtimiento(boolean avanceSurtimiento) {
        this.avanceSurtimiento = avanceSurtimiento;
    }

    public boolean isReporteEntregaCenadi() {
        return reporteEntregaCenadi;
    }

    public void setReporteEntregaCenadi(boolean reporteEntregaCenadi) {
        this.reporteEntregaCenadi = reporteEntregaCenadi;
    }

    public boolean isReporteDetalleCenadi() {
        return reporteDetalleCenadi;
    }

    public void setReporteDetalleCenadi(boolean reporteDetalleCenadi) {
        this.reporteDetalleCenadi = reporteDetalleCenadi;
    }

    public boolean isReporteDetalleUmus() {
        return reporteDetalleUmus;
    }

    public void setReporteDetalleUmus(boolean reporteDetalleUmus) {
        this.reporteDetalleUmus = reporteDetalleUmus;
    }

    public boolean isReporteGeneralExitenciaUmu() {
        return reporteGeneralExitenciaUmu;
    }

    public void setReporteGeneralExitenciaUmu(boolean reporteGeneralExitenciaUmu) {
        this.reporteGeneralExitenciaUmu = reporteGeneralExitenciaUmu;
    }

    public boolean isReporteGeneralExitenciaCenadi() {
        return reporteGeneralExitenciaCenadi;
    }

    public void setReporteGeneralExitenciaCenadi(boolean reporteGeneralExitenciaCenadi) {
        this.reporteGeneralExitenciaCenadi = reporteGeneralExitenciaCenadi;
    }

    public boolean isReporteEntregaUmu() {
        return reporteEntregaUmu;
    }

    public void setReporteEntregaUmu(boolean reporteEntregaUmu) {
        this.reporteEntregaUmu = reporteEntregaUmu;
    }

    public boolean isCancelacionRescision() {
        return cancelacionRescision;
    }

    public void setCancelacionRescision(boolean cancelacionRescision) {
        this.cancelacionRescision = cancelacionRescision;
    }

    public boolean isTipoUsuarios() {
        return tipoUsuarios;
    }

    public void setTipoUsuarios(boolean tipoUsuarios) {
        this.tipoUsuarios = tipoUsuarios;
    }

    public boolean isRptConcentrado() {
        return rptConcentrado;
    }

    public void setRptConcentrado(boolean rptConcentrado) {
        this.rptConcentrado = rptConcentrado;
    }

    public boolean isProcedimientosC() {
        return procedimientosC;
    }

    public void setProcedimientosC(boolean procedimientosC) {
        this.procedimientosC = procedimientosC;
    }

    public boolean isTareaProgramadaActualizacionCatalogoC() {
        return tareaProgramadaActualizacionCatalogoC;
    }

    public void setTareaProgramadaActualizacionCatalogoC(boolean tareaProgramadaActualizacionCatalogoC) {
        this.tareaProgramadaActualizacionCatalogoC = tareaProgramadaActualizacionCatalogoC;
    }

    public boolean isSolicitudMensualC() {
        return solicitudMensualC;
    }

    public void setSolicitudMensualC(boolean solicitudMensualC) {
        this.solicitudMensualC = solicitudMensualC;
    }

    public boolean isSolicitudSoporteVidaC() {
        return solicitudSoporteVidaC;
    }

    public void setSolicitudSoporteVidaC(boolean solicitudSoporteVidaC) {
        this.solicitudSoporteVidaC = solicitudSoporteVidaC;
    }

    public boolean isConsultaSolicitudesUnidadesMedicasC() {
        return consultaSolicitudesUnidadesMedicasC;
    }

    public void setConsultaSolicitudesUnidadesMedicasC(boolean consultaSolicitudesUnidadesMedicasC) {
        this.consultaSolicitudesUnidadesMedicasC = consultaSolicitudesUnidadesMedicasC;
    }

    public boolean isDistribucionMensualC() {
        return distribucionMensualC;
    }

    public void setDistribucionMensualC(boolean distribucionMensualC) {
        this.distribucionMensualC = distribucionMensualC;
    }

    public boolean isGeneracionAnteproyectoCRC() {
        return generacionAnteproyectoCRC;
    }

    public void setGeneracionAnteproyectoCRC(boolean generacionAnteproyectoCRC) {
        this.generacionAnteproyectoCRC = generacionAnteproyectoCRC;
    }

    public boolean isRcbCreacionSeguimientoC() {
        return rcbCreacionSeguimientoC;
    }

    public void setRcbCreacionSeguimientoC(boolean rcbCreacionSeguimientoC) {
        this.rcbCreacionSeguimientoC = rcbCreacionSeguimientoC;
    }

    public boolean isSolicitudInvestigacionMercadoC() {
        return solicitudInvestigacionMercadoC;
    }

    public void setSolicitudInvestigacionMercadoC(boolean solicitudInvestigacionMercadoC) {
        this.solicitudInvestigacionMercadoC = solicitudInvestigacionMercadoC;
    }

    public boolean isGeneracionPreOrdenSuministroC() {
        return generacionPreOrdenSuministroC;
    }

    public void setGeneracionPreOrdenSuministroC(boolean generacionPreOrdenSuministroC) {
        this.generacionPreOrdenSuministroC = generacionPreOrdenSuministroC;
    }

    public boolean isPresentacionPropuestasC() {
        return presentacionPropuestasC;
    }

    public void setPresentacionPropuestasC(boolean presentacionPropuestasC) {
        this.presentacionPropuestasC = presentacionPropuestasC;
    }

    public boolean isFallosC() {
        return fallosC;
    }

    public void setFallosC(boolean fallosC) {
        this.fallosC = fallosC;
    }

    public boolean isContratosC() {
        return contratosC;
    }

    public void setContratosC(boolean contratosC) {
        this.contratosC = contratosC;
    }

    public boolean isConveniosC() {
        return conveniosC;
    }

    public void setConveniosC(boolean conveniosC) {
        this.conveniosC = conveniosC;
    }

    public boolean isOrdenesSuministroC() {
        return ordenesSuministroC;
    }

    public void setOrdenesSuministroC(boolean ordenesSuministroC) {
        this.ordenesSuministroC = ordenesSuministroC;
    }

    public boolean isCalculoPenasConvencionalesC() {
        return calculoPenasConvencionalesC;
    }

    public void setCalculoPenasConvencionalesC(boolean calculoPenasConvencionalesC) {
        this.calculoPenasConvencionalesC = calculoPenasConvencionalesC;
    }

    public boolean isCancelacionesC() {
        return cancelacionesC;
    }

    public void setCancelacionesC(boolean cancelacionesC) {
        this.cancelacionesC = cancelacionesC;
    }

    public boolean isRecisionesC() {
        return recisionesC;
    }

    public void setRecisionesC(boolean recisionesC) {
        this.recisionesC = recisionesC;
    }

    public boolean isInvestigacionMercadoC() {
        return investigacionMercadoC;
    }

    public void setInvestigacionMercadoC(boolean investigacionMercadoC) {
        this.investigacionMercadoC = investigacionMercadoC;
    }

    public boolean isRecepcionDocumentalC() {
        return recepcionDocumentalC;
    }

    public void setRecepcionDocumentalC(boolean recepcionDocumentalC) {
        this.recepcionDocumentalC = recepcionDocumentalC;
    }

    public boolean isControlCalidadC() {
        return controlCalidadC;
    }

    public void setControlCalidadC(boolean controlCalidadC) {
        this.controlCalidadC = controlCalidadC;
    }

    public boolean isRecepcionInsumosSaludC() {
        return recepcionInsumosSaludC;
    }

    public void setRecepcionInsumosSaludC(boolean recepcionInsumosSaludC) {
        this.recepcionInsumosSaludC = recepcionInsumosSaludC;
    }

    public boolean isRecoleccionInsumosC() {
        return recoleccionInsumosC;
    }

    public void setRecoleccionInsumosC(boolean recoleccionInsumosC) {
        this.recoleccionInsumosC = recoleccionInsumosC;
    }

    public boolean isCanjesC() {
        return canjesC;
    }

    public void setCanjesC(boolean canjesC) {
        this.canjesC = canjesC;
    }

    public boolean isDeductivasC() {
        return deductivasC;
    }

    public void setDeductivasC(boolean deductivasC) {
        this.deductivasC = deductivasC;
    }

    public boolean isUsuariosC() {
        return usuariosC;
    }

    public void setUsuariosC(boolean usuariosC) {
        this.usuariosC = usuariosC;
    }

    public boolean isPerfilesC() {
        return perfilesC;
    }

    public void setPerfilesC(boolean perfilesC) {
        this.perfilesC = perfilesC;
    }

    public boolean isAlmacenesC() {
        return almacenesC;
    }

    public void setAlmacenesC(boolean almacenesC) {
        this.almacenesC = almacenesC;
    }

    public boolean isAreasC() {
        return areasC;
    }

    public void setAreasC(boolean areasC) {
        this.areasC = areasC;
    }

    public boolean isCaracterProcedimientoC() {
        return caracterProcedimientoC;
    }

    public void setCaracterProcedimientoC(boolean caracterProcedimientoC) {
        this.caracterProcedimientoC = caracterProcedimientoC;
    }

    public boolean isClasificacionesC() {
        return clasificacionesC;
    }

    public void setClasificacionesC(boolean clasificacionesC) {
        this.clasificacionesC = clasificacionesC;
    }

    public boolean isClasificacionProcedimientosC() {
        return clasificacionProcedimientosC;
    }

    public void setClasificacionProcedimientosC(boolean clasificacionProcedimientosC) {
        this.clasificacionProcedimientosC = clasificacionProcedimientosC;
    }

    public boolean isCompradoresC() {
        return compradoresC;
    }

    public void setCompradoresC(boolean compradoresC) {
        this.compradoresC = compradoresC;
    }

    public boolean isConfiguracionesC() {
        return configuracionesC;
    }

    public void setConfiguracionesC(boolean configuracionesC) {
        this.configuracionesC = configuracionesC;
    }

    public boolean isContactosC() {
        return contactosC;
    }

    public void setContactosC(boolean contactosC) {
        this.contactosC = contactosC;
    }

    public boolean isDestinosC() {
        return destinosC;
    }

    public void setDestinosC(boolean destinosC) {
        this.destinosC = destinosC;
    }

    public boolean isEstatusC() {
        return estatusC;
    }

    public void setEstatusC(boolean estatusC) {
        this.estatusC = estatusC;
    }

    public boolean isFabricantesC() {
        return fabricantesC;
    }

    public void setFabricantesC(boolean fabricantesC) {
        this.fabricantesC = fabricantesC;
    }

    public boolean isFundamentosLegalesC() {
        return fundamentosLegalesC;
    }

    public void setFundamentosLegalesC(boolean fundamentosLegalesC) {
        this.fundamentosLegalesC = fundamentosLegalesC;
    }

    public boolean isGruposC() {
        return gruposC;
    }

    public void setGruposC(boolean gruposC) {
        this.gruposC = gruposC;
    }

    public boolean isGruposTerapeuticosC() {
        return gruposTerapeuticosC;
    }

    public void setGruposTerapeuticosC(boolean gruposTerapeuticosC) {
        this.gruposTerapeuticosC = gruposTerapeuticosC;
    }

    public boolean isPacientesC() {
        return pacientesC;
    }

    public void setPacientesC(boolean pacientesC) {
        this.pacientesC = pacientesC;
    }

    public boolean isPartidasPresupuestalesC() {
        return partidasPresupuestalesC;
    }

    public void setPartidasPresupuestalesC(boolean partidasPresupuestalesC) {
        this.partidasPresupuestalesC = partidasPresupuestalesC;
    }

    public boolean isProveedoresC() {
        return proveedoresC;
    }

    public void setProveedoresC(boolean proveedoresC) {
        this.proveedoresC = proveedoresC;
    }

    public boolean isTareasC() {
        return tareasC;
    }

    public void setTareasC(boolean tareasC) {
        this.tareasC = tareasC;
    }

    public boolean isTiposCompraC() {
        return tiposCompraC;
    }

    public void setTiposCompraC(boolean tiposCompraC) {
        this.tiposCompraC = tiposCompraC;
    }

    public boolean isTiposContratoC() {
        return tiposContratoC;
    }

    public void setTiposContratoC(boolean tiposContratoC) {
        this.tiposContratoC = tiposContratoC;
    }

    public boolean isTiposConvenioC() {
        return tiposConvenioC;
    }

    public void setTiposConvenioC(boolean tiposConvenioC) {
        this.tiposConvenioC = tiposConvenioC;
    }

    public boolean isTiposDocumentoC() {
        return tiposDocumentoC;
    }

    public void setTiposDocumentoC(boolean tiposDocumentoC) {
        this.tiposDocumentoC = tiposDocumentoC;
    }

    public boolean isTiposProcedimientoC() {
        return tiposProcedimientoC;
    }

    public void setTiposProcedimientoC(boolean tiposProcedimientoC) {
        this.tiposProcedimientoC = tiposProcedimientoC;
    }

    public boolean isTiposProveedorC() {
        return tiposProveedorC;
    }

    public void setTiposProveedorC(boolean tiposProveedorC) {
        this.tiposProveedorC = tiposProveedorC;
    }

    public boolean isTiposSolicitudC() {
        return tiposSolicitudC;
    }

    public void setTiposSolicitudC(boolean tiposSolicitudC) {
        this.tiposSolicitudC = tiposSolicitudC;
    }

    public boolean isUnidadesMedicasC() {
        return unidadesMedicasC;
    }

    public void setUnidadesMedicasC(boolean unidadesMedicasC) {
        this.unidadesMedicasC = unidadesMedicasC;
    }

    public boolean isDistribucionSoporteVidaC() {
        return distribucionSoporteVidaC;
    }

    public void setDistribucionSoporteVidaC(boolean distribucionSoporteVidaC) {
        this.distribucionSoporteVidaC = distribucionSoporteVidaC;
    }

    public boolean isAuditoriaC() {
        return auditoriaC;
    }

    public void setAuditoriaC(boolean auditoriaC) {
        this.auditoriaC = auditoriaC;
    }

    public boolean isCondicionPagoC() {
        return condicionPagoC;
    }

    public void setCondicionPagoC(boolean condicionPagoC) {
        this.condicionPagoC = condicionPagoC;
    }

    public boolean isPresentacionesC() {
        return presentacionesC;
    }

    public void setPresentacionesC(boolean presentacionesC) {
        this.presentacionesC = presentacionesC;
    }

    public boolean isClausuladoC() {
        return clausuladoC;
    }

    public void setClausuladoC(boolean clausuladoC) {
        this.clausuladoC = clausuladoC;
    }

    public boolean isCierreDevolucionesC() {
        return cierreDevolucionesC;
    }

    public void setCierreDevolucionesC(boolean cierreDevolucionesC) {
        this.cierreDevolucionesC = cierreDevolucionesC;
    }

    public boolean isColorPorcentajeC() {
        return colorPorcentajeC;
    }

    public void setColorPorcentajeC(boolean colorPorcentajeC) {
        this.colorPorcentajeC = colorPorcentajeC;
    }

    public boolean isClasificacionImportanciaC() {
        return clasificacionImportanciaC;
    }

    public void setClasificacionImportanciaC(boolean clasificacionImportanciaC) {
        this.clasificacionImportanciaC = clasificacionImportanciaC;
    }

    public boolean isConfiguracionDiaMesC() {
        return configuracionDiaMesC;
    }

    public void setConfiguracionDiaMesC(boolean configuracionDiaMesC) {
        this.configuracionDiaMesC = configuracionDiaMesC;
    }

    public boolean isConsultaDpnC() {
        return consultaDpnC;
    }

    public void setConsultaDpnC(boolean consultaDpnC) {
        this.consultaDpnC = consultaDpnC;
    }

    public boolean isConfiguraDpnC() {
        return configuraDpnC;
    }

    public void setConfiguraDpnC(boolean configuraDpnC) {
        this.configuraDpnC = configuraDpnC;
    }

    public boolean isAlertasDpnC() {
        return alertasDpnC;
    }

    public void setAlertasDpnC(boolean alertasDpnC) {
        this.alertasDpnC = alertasDpnC;
    }

    public boolean isConfiguraInsumosDpnC() {
        return configuraInsumosDpnC;
    }

    public void setConfiguraInsumosDpnC(boolean configuraInsumosDpnC) {
        this.configuraInsumosDpnC = configuraInsumosDpnC;
    }

    public boolean isCancelacionRescisionC() {
        return cancelacionRescisionC;
    }

    public void setCancelacionRescisionC(boolean cancelacionRescisionC) {
        this.cancelacionRescisionC = cancelacionRescisionC;
    }

    public boolean isTipoUsuariosC() {
        return tipoUsuariosC;
    }

    public void setTipoUsuariosC(boolean tipoUsuariosC) {
        this.tipoUsuariosC = tipoUsuariosC;
    }

    public boolean isSolicitudInsumosParaLaSaludC() {
        return solicitudInsumosParaLaSaludC;
    }

    public void setSolicitudInsumosParaLaSaludC(boolean solicitudInsumosParaLaSaludC) {
        this.solicitudInsumosParaLaSaludC = solicitudInsumosParaLaSaludC;
    }

    public boolean isDetalleIM() {
        return detalleIM;
    }

    public void setDetalleIM(boolean detalleIM) {
        this.detalleIM = detalleIM;
    }

    public boolean isDetalleIMC() {
        return detalleIMC;
    }

    public void setDetalleIMC(boolean detalleIMC) {
        this.detalleIMC = detalleIMC;
    }

}
