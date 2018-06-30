/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.controller;

import com.issste.sicabis.DTO.RepositorioDocumentosDTO;
import com.issste.sicabis.ejb.ln.RespositorioDocumentosService;
import com.issste.sicabis.ejb.ln.TipoDocumentosService;
import com.issste.sicabis.ejb.modelo.CrInsumos;
import com.issste.sicabis.ejb.modelo.Insumos;
import com.issste.sicabis.ejb.modelo.RcbInsumos;
import com.issste.sicabis.ejb.modelo.RespositorioDocumentos;
import com.issste.sicabis.ejb.modelo.TipoDocumentos;
import com.issste.sicabis.utils.ArchivosUtilidades;
import com.issste.sicabis.utils.Mensajes;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Toshiba Manolo
 */
@ManagedBean(name = "archivosBean")
@ApplicationScoped
public class ArchivosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private RespositorioDocumentosService respositorioDocumentosService;
    @EJB
    private TipoDocumentosService tipoDocumentosService;

    private UploadedFile uploadedfile;
    private Integer idTipoDocumento;
    private RespositorioDocumentos respositorioDocumentos;
    private RepositorioDocumentosDTO repositorioDocumentosDTO;
    private RepositorioDocumentosDTO repositorioDocumentosDTOAux;
    private ArchivosUtilidades archivosUtilidades = new ArchivosUtilidades();
    private Mensajes mensaje = new Mensajes();
    private Integer idTareaProc ;
    private Integer idProcesoBean;
    private List<RepositorioDocumentosDTO> listaRepoDocsDto;
    private List<RespositorioDocumentos> listaRepoDocs;
    private List<TipoDocumentos> listaTipoDocs;

    @PostConstruct
    public void init() {
        System.out.println("ArchivosBean init");      
    }

    public void onload(Integer idTarea,Integer idProceso,Integer idTipoDoc) {
        idTareaProc=idTarea;
        idProcesoBean=idProceso;
        idTipoDocumento =idTipoDoc;
        listaTipoDocs = tipoDocumentosService.obtenerByIdTarea(idTareaProc);
        buscarArchivosByIdProcesoIdTarea(idProcesoBean, idTareaProc);
    }
    
    public void cambiaTipoDoc() {
        System.out.println("idTipoDocumento--->" + idTipoDocumento);
    }

    public void guardarArchivos(FileUploadEvent event) {
        System.out.println("ArchivosBean guardar archivos");
        uploadedfile = event.getFile();
        boolean bandera = false;
        System.out.println("idTipoDocumento"+idTipoDocumento);
        if (idTipoDocumento != -1) {
            respositorioDocumentos = new RespositorioDocumentos();
            respositorioDocumentos.setActivo(1);
            respositorioDocumentos.setFechaAlta(new Date());
            respositorioDocumentos.setIdProceso(idProcesoBean);
            respositorioDocumentos.setIdTipoDocumento(new TipoDocumentos(idTipoDocumento));
            respositorioDocumentos.setNombre(event.getFile().getFileName());
            respositorioDocumentos.setRuta(archivosUtilidades.PATHFILES);
            respositorioDocumentos.setUsuarioAlta("erik");
            String nombreArchivo = archivosUtilidades.generaNombreArchivo(uploadedfile, idTareaProc, respositorioDocumentos.getIdProceso());
            respositorioDocumentos.setNombreArchivo(nombreArchivo);
            System.out.println("entro guardaProcedimiento");
            if (respositorioDocumentosService.guardaProcedimiento(respositorioDocumentos)) {
                 System.out.println("salio guardaProcedimiento");
                mensaje.mensaje(mensaje.datos_guardados, "verde");
                //se envia el archivo y el id tarea (3 es id tarea procedimiento)
                 System.out.println("ArchivosBean guardaArchivo");
                if (archivosUtilidades.guardaArchivo(uploadedfile, nombreArchivo)) {
                    mensaje.mensaje(mensaje.archivo_bien, "verde");
                    bandera = true;

                } else {
                    mensaje.mensaje(mensaje.archivo_error, "rojo");
                }
            } else {
                mensaje.mensaje(mensaje.error_guardar, "rojo");
            }
        } else {
            mensaje.mensaje(mensaje.tipoDoc_select, "amarillo");
        }
        if (bandera) {
            buscarArchivosByIdProcesoIdTarea(respositorioDocumentos.getIdProceso(), idTareaProc);
        }
        System.out.println("ArchivosBean salio archivos");
    }

    public void buscarArchivosByIdProcesoIdTarea(Integer idProceso, Integer idTarea) {
        listaRepoDocs = respositorioDocumentosService.obtenerByIdProcesoIdTarea(idProceso, idTarea);
        listaRepoDocsDto = new ArrayList();
        System.out.println("");
        if (listaRepoDocs != null) {
            for (RespositorioDocumentos rd : listaRepoDocs) {
                repositorioDocumentosDTO = new RepositorioDocumentosDTO();
                repositorioDocumentosDTO.setIdRespositorioDocumento(rd.getIdRespositorioDocumento());
                repositorioDocumentosDTO.setNombre(rd.getNombre());
                repositorioDocumentosDTO.setNombreArchivo(rd.getNombreArchivo());
                repositorioDocumentosDTO.setIdTipoDocumento(rd.getIdTipoDocumento());
                File file = archivosUtilidades.getFileByName(repositorioDocumentosDTO.getNombreArchivo());
                try {
                    InputStream is = new ByteArrayInputStream(archivosUtilidades.fileToBytes(file));
                    repositorioDocumentosDTO.setFile(new DefaultStreamedContent(is, "text/xml", repositorioDocumentosDTO.getNombre()));
                } catch (IOException ex) {
                    Logger.getLogger(DetalleProcedimientoBean.class.getName()).log(Level.SEVERE, null, ex);
                }
                listaRepoDocsDto.add(repositorioDocumentosDTO);
            }
        }
    }

    public void validaBorrarArchivo(RepositorioDocumentosDTO repositorioDocumentosDTO) {
        repositorioDocumentosDTOAux = repositorioDocumentosDTO;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg2').show();");
    }

    public void borrarArchivo() {
        System.out.println("repositorioDocumentosDTO---->" + repositorioDocumentosDTOAux.getIdRespositorioDocumento());
        List<RepositorioDocumentosDTO> listaRepoDocsDtoAux = new ArrayList();
        for (RepositorioDocumentosDTO rdd : listaRepoDocsDto) {
            if (rdd != repositorioDocumentosDTOAux) {
                listaRepoDocsDtoAux.add(rdd);
            } else {
                respositorioDocumentosService.borrarByIdRespositorioDocumento(rdd.getIdRespositorioDocumento());
                File file = archivosUtilidades.getFileByName(rdd.getNombreArchivo());
                file.delete();
            }
        }
        listaRepoDocsDto = listaRepoDocsDtoAux;
    }
    
    public void upload() {
        if(uploadedfile != null) {
            System.out.println("Succesful "+ uploadedfile.getFileName() + " is uploaded.");
            FacesMessage message = new FacesMessage("Succesful", uploadedfile.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
//    public void handleFileUpload(FileUploadEvent event) {
//        uploadedfile =event.getFile();
//        System.out.println(event.getFile().getFileName() + " is uploaded.");
//        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, message);
//        DateFormat date = new SimpleDateFormat("ddMMyyyy");
//        String strFecha = date.format(new Date());
//        String fechaNombreArchivo = "/"+strFecha+"_"+uploadedfile.getFileName();
//        guardaArchivo(uploadedfile, fechaNombreArchivo);
//        readExcelFile(new File(ArchivosUtilidades.PATHCARGAMASIVA+fechaNombreArchivo));
//        mensaje.mensaje("Se cargo el Archivo correctamente", "verde");
//    }
    
    public boolean guardaArchivo(UploadedFile uploadedFile, String nombreArchivo) {
        String result = "";
        try {
            copyFile(nombreArchivo, uploadedFile.getInputstream());
        } catch (IOException ex) {
            Logger.getLogger(ArchivosUtilidades.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public void copyFile(String fileName, InputStream in) {
        try {

            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(ArchivosUtilidades.PATHCARGAMASIVA + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<RcbInsumos> readRCBExcelFile(File excelFile) {
        InputStream excelStream = null;
        List<RcbInsumos> listRcbInsumo = new ArrayList<>();
                
        try {
            excelStream = new FileInputStream(excelFile);
            // High <span id="IL_AD11" class="IL_AD">level</span> representation of a workbook.
            // Representación del más alto nivel de la hoja excel.
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelStream);
            // We chose the sheet is passed as parameter. 
            // Elegimos la hoja que se pasa por parámetro.
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            // An object that allows us to read a row of the excel sheet, and extract from it the cell contents.
            // Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el contenido de las celdas.
            HSSFRow hssfRow;
            // Initialize the object to read the value of the cell 
            // Inicializo el objeto que leerá el valor de la celda
            HSSFCell cell;
            // I get the number of rows occupied on the sheet
            // Obtengo el número de filas ocupadas en la hoja
            int rows = hssfSheet.getLastRowNum();
            // I get the number of columns occupied on the sheet
            // Obtengo el número de columnas ocupadas en la hoja
            int cols = 0;
            // A string used to store the reading cell
            // Cadena que usamos para almacenar la lectura de la celda
            String cellValue;
            // For this example we'll loop through the rows getting the data we want
            // Para este ejemplo vamos a recorrer las filas obteniendo los datos que queremos            
            z:
            for (int r = 1; r <= rows; r++) {
                hssfRow = hssfSheet.getRow(r);
                if (hssfRow == null) {
                    break;
                } else {
                    System.out.print("Row: " + r + " -> ");
                    RcbInsumos rcbInsumos = new RcbInsumos();
                    for (int c = 0; c < (cols = hssfRow.getLastCellNum()); c++) {
                        /* 
                         We have those cell types (tenemos estos tipos de celda): 
                         CELL_TYPE_BLANK, CELL_TYPE_NUMERIC, CELL_TYPE_BLANK, CELL_TYPE_FORMULA, CELL_TYPE_BOOLEAN, CELL_TYPE_ERROR
                         */
                        cellValue = hssfRow.getCell(c) == null ? ""
                                : (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_STRING) ? hssfRow.getCell(c).getStringCellValue()
                                        : (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_NUMERIC) ? "" + hssfRow.getCell(c).getNumericCellValue()
                                                : (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_BOOLEAN) ? "" + hssfRow.getCell(c).getBooleanCellValue()
                                                        : (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_BLANK) ? "BLANK"
                                                                : (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_FORMULA) ? "FORMULA"
                                                                        : (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_ERROR) ? "ERROR" : "";
                        System.out.print("[Column " + c + ": " + cellValue + "] ");
                        if (c == 0) {
                            rcbInsumos.setClaveInsumo(cellValue);
                            if(rcbInsumos.getClaveInsumo().equals("/")){
                                break z;
                            }
                        } else if (c == 1) {
                            rcbInsumos.setCantidadPiezas(Integer.parseInt(cellValue.replace(".0", "")));
                        } else if (c == 2) {
                            if(cellValue.contains("$")){
                                cellValue = cellValue.replace("$", "");
                                cellValue = cellValue.replaceAll(",", "");
                            }
                            rcbInsumos.setPrecioUnitario(new BigDecimal(cellValue));
                        }
                    }
                    if(rcbInsumos.getPrecioUnitario()!=null){
                       listRcbInsumo.add(rcbInsumos);
                    }
                    System.out.println();
                }
            }
            return listRcbInsumo;
            
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("The file not exists (No se encontró el fichero): " + fileNotFoundException);
        } catch (IOException ex) {
            System.out.println("Error in file procesing (Error al procesar el fichero): " + ex);
            mensaje.mensaje("Error al procesar el Archivo verifique el contenido", "rojo");
        } catch (Exception e){
            System.out.println("Error in file procesing (Error al procesar el fichero): " + e);
            mensaje.mensaje("Error al procesar el Archivo verifique el contenido", "rojo");
        }
        finally {
            try {
                excelStream.close();
            } catch (IOException ex) {
                System.out.println("Error in file processing after close it (Error al procesar el fichero después de cerrarlo): " + ex);
            }
        }
        return new ArrayList<>();
    }
    
        public List<CrInsumos> readCRExcelFile(File excelFile) {
        InputStream excelStream = null;
        List<CrInsumos> listCrInsumo = new ArrayList<>();
                
        try {
            excelStream = new FileInputStream(excelFile);
            // High <span id="IL_AD11" class="IL_AD">level</span> representation of a workbook.
            // Representación del más alto nivel de la hoja excel.
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelStream);
            // We chose the sheet is passed as parameter. 
            // Elegimos la hoja que se pasa por parámetro.
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            // An object that allows us to read a row of the excel sheet, and extract from it the cell contents.
            // Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el contenido de las celdas.
            HSSFRow hssfRow;
            // Initialize the object to read the value of the cell 
            // Inicializo el objeto que leerá el valor de la celda
            HSSFCell cell;
            // I get the number of rows occupied on the sheet
            // Obtengo el número de filas ocupadas en la hoja
            int rows = hssfSheet.getLastRowNum();
            // I get the number of columns occupied on the sheet
            // Obtengo el número de columnas ocupadas en la hoja
            int cols = 0;
            // A string used to store the reading cell
            // Cadena que usamos para almacenar la lectura de la celda
            String cellValue;
            // For this example we'll loop through the rows getting the data we want
            // Para este ejemplo vamos a recorrer las filas obteniendo los datos que queremos            
            for (int r = 1; r <= rows; r++) {
                hssfRow = hssfSheet.getRow(r);
                if (hssfRow == null) {
                    break;
                } else {
                    System.out.print("Row: " + r + " -> ");
                    CrInsumos rcbInsumos = new CrInsumos();
                    for (int c = 0; c < (cols = hssfRow.getLastCellNum()); c++) {
                        /* 
                         We have those cell types (tenemos estos tipos de celda): 
                         CELL_TYPE_BLANK, CELL_TYPE_NUMERIC, CELL_TYPE_BLANK, CELL_TYPE_FORMULA, CELL_TYPE_BOOLEAN, CELL_TYPE_ERROR
                         */
                        cellValue = hssfRow.getCell(c) == null ? ""
                                : (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_STRING) ? hssfRow.getCell(c).getStringCellValue()
                                        : (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_NUMERIC) ? "" + hssfRow.getCell(c).getNumericCellValue()
                                                : (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_BOOLEAN) ? "" + hssfRow.getCell(c).getBooleanCellValue()
                                                        : (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_BLANK) ? "BLANK"
                                                                : (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_FORMULA) ? "FORMULA"
                                                                        : (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_ERROR) ? "ERROR" : "";
                        System.out.print("[Column " + c + ": " + cellValue + "] ");
                        if (c == 0) {
                            Insumos insumo = new Insumos();
                            insumo.setClave(cellValue);
                            rcbInsumos.setIdInsumo(insumo);
                        } else if (c == 1) {
                            rcbInsumos.setCantidadPiezas(Integer.parseInt(cellValue.replace(".0", "")));
                        } else if (c == 2) {
                            rcbInsumos.setPrecioUnitario(new BigDecimal(cellValue));

                        }
                    }
                    if(rcbInsumos.getPrecioUnitario()!=null){
                       listCrInsumo.add(rcbInsumos);
                    }
                    System.out.println();
                }
            }
            return listCrInsumo;
            
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("The file not exists (No se encontró el fichero): " + fileNotFoundException);
        } catch (IOException ex) {
            System.out.println("Error in file procesing (Error al procesar el fichero): " + ex);
            mensaje.mensaje("Error al procesar el Archivo verifique el contenido", "rojo");
        } catch (Exception e){
            System.out.println("Error in file procesing (Error al procesar el fichero): " + e);
            mensaje.mensaje("Error al procesar el Archivo verifique el contenido", "rojo");
        }
        finally {
            try {
                excelStream.close();
            } catch (IOException ex) {
                System.out.println("Error in file processing after close it (Error al procesar el fichero después de cerrarlo): " + ex);
            }
        }
        return new ArrayList<>();
    }
    
    public UploadedFile getUploadedfile() {
        return uploadedfile;
    }

    public void setUploadedfile(UploadedFile uploadedfile) {
        this.uploadedfile = uploadedfile;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public RespositorioDocumentos getRespositorioDocumentos() {
        return respositorioDocumentos;
    }

    public void setRespositorioDocumentos(RespositorioDocumentos respositorioDocumentos) {
        this.respositorioDocumentos = respositorioDocumentos;
    }

    public RepositorioDocumentosDTO getRepositorioDocumentosDTO() {
        return repositorioDocumentosDTO;
    }

    public void setRepositorioDocumentosDTO(RepositorioDocumentosDTO repositorioDocumentosDTO) {
        this.repositorioDocumentosDTO = repositorioDocumentosDTO;
    }

    public RepositorioDocumentosDTO getRepositorioDocumentosDTOAux() {
        return repositorioDocumentosDTOAux;
    }

    public void setRepositorioDocumentosDTOAux(RepositorioDocumentosDTO repositorioDocumentosDTOAux) {
        this.repositorioDocumentosDTOAux = repositorioDocumentosDTOAux;
    }

    public ArchivosUtilidades getArchivosUtilidades() {
        return archivosUtilidades;
    }

    public void setArchivosUtilidades(ArchivosUtilidades archivosUtilidades) {
        this.archivosUtilidades = archivosUtilidades;
    }

    public Mensajes getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensajes mensaje) {
        this.mensaje = mensaje;
    }

    public List<RepositorioDocumentosDTO> getListaRepoDocsDto() {
        return listaRepoDocsDto;
    }

    public void setListaRepoDocsDto(List<RepositorioDocumentosDTO> listaRepoDocsDto) {
        this.listaRepoDocsDto = listaRepoDocsDto;
    }

    public List<RespositorioDocumentos> getListaRepoDocs() {
        return listaRepoDocs;
    }

    public void setListaRepoDocs(List<RespositorioDocumentos> listaRepoDocs) {
        this.listaRepoDocs = listaRepoDocs;
    }

    public List<TipoDocumentos> getListaTipoDocs() {
        return listaTipoDocs;
    }

    public void setListaTipoDocs(List<TipoDocumentos> listaTipoDocs) {
        this.listaTipoDocs = listaTipoDocs;
    }

    public Integer getIdTareaProc() {
        return idTareaProc;
    }

    public void setIdTareaProc(Integer idTareaProc) {
        this.idTareaProc = idTareaProc;
    }

    public Integer getIdProcesoBean() {
        return idProcesoBean;
    }

    public void setIdProcesoBean(Integer idProcesoBean) {
        this.idProcesoBean = idProcesoBean;
    }
    
    
}
