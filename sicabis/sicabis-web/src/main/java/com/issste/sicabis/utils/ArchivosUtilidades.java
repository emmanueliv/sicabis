//################################################################################
//      ## Fecha de creación: 05/10/16
//      ## Fecha de última modificación: 05/10/16
//      ## Responsable: Emmanuel De la Isla Vértiz
//      ## Módulos asociados: Utilidades archivos
//################################################################################
package com.issste.sicabis.utils;

import com.issste.sicabis.ejb.DTO.PropuestasArchivoDTO;
import com.issste.sicabis.ejb.modelo.ProcedimientoRcb;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.UploadedFile;

import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ArchivosUtilidades {

    private Utilidades util = new Utilidades();
    public static final String PATHFILES = "../docSicabis/";
    public static final String PATHFILESFALLOS = "../docSicabis/listasFallos/";
    public static final String PATHFILESPROPUESTAS = "../docSicabis/layoutsPropuestas/";
    public static final String ERROR = "ERROR";
    public static final String PATHCARGAMASIVA = "../docSicabis/docsCargaMasiva";
    public static final String PATHFILESCLAUSULAS = "../docSicabis/clausulado/";
    public static final String PATHFILESCLAUSULASCONTRATO = "../docSicabis/clausulado/contrato_convenio/";
    public static final String NAMEFILECLAUSULACONTRATO = "clausula_contrato";
    public static final String NAMEFILECLAUSULACONVENIO = "clausula_convenio";
    public static final String PATHFILESREPORTEINTERACTIVO = "../docSicabis/layoutsReporteInteractivo/";

    public void copyFile(String fileName, InputStream in) {
        try {

            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(PATHFILES + fileName));
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

    public void copyFilePropuestas(String fileName, InputStream in) {
        try {

            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(PATHFILESPROPUESTAS + fileName));
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

    public String obtieneNombreSinExt(String name) {
        int posision = 0;
        int tamanio = 0;
        String nombreArc;
        String ext;
        int i;
        for (i = name.length() - 1; i >= 0; i--) {
            if (name.charAt(i) == '.') {
                posision = i;
                break;
            }
        }
        nombreArc = name.substring(0, posision);
        //nombreArc = nombreArc + "_" + nameinfo;
        //ext = name.substring(posision, name.length());
        //name = nombreArc + ext;

        return nombreArc;
    }

    public String obtieneExtSinNombre(String name) {
        int posision = 0;
        int tamanio = 0;
        String nombreArc;
        String ext;
        int i;
        for (i = name.length() - 1; i >= 0; i--) {
            if (name.charAt(i) == '.') {
                posision = i;
                break;
            }
        }
        nombreArc = name.substring(0, posision);
        ext = name.substring(posision, name.length());

        return ext;
    }

    /**
     * Elimina un archivo.
     *
     * @param file Nombre del archivo que se desea eliminar (Ejem: File.txt)
     * @return 0 el archivo no existe. 1 el archivo se elimino. -1 ocurrio un
     * error el archivo no pudo ser eliminado.
     */
    public int deleteFile(String file) {
        try {
            File archivo = new File(PATHFILES + file);

            if (!archivo.exists()) {
                return 0;//El archivo no existe
            }
            if (archivo.delete()) {
                return 1;//Todo bien
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;//Error i/o
    }

    /**
     * Elimina un archivo.
     *
     * @param file Nombre del archivo que se desea eliminar (Ejem: File.txt)
     * @return 0 el archivo no existe. 1 el archivo se elimino. -1 ocurrio un
     * error el archivo no pudo ser eliminado.
     */
    public int deleteFileFallos(String file) {
        try {
            System.out.println("file--->" + PATHFILESFALLOS + file);
            File archivo = new File(PATHFILESFALLOS + file);

            if (!archivo.exists()) {
                return 0;//El archivo no existe
            }
            if (archivo.delete()) {
                return 1;//Todo bien
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;//Error i/o
    }

    /**
     * @param nombreArchivo nombre del archivo con extension
     * @return Devuelve el archivo File encontrado
     */
    public File getFileByName(String nombreArchivo) {
        File archivo = new File(PATHFILES + nombreArchivo);
        return archivo;
    }

    /**
     * Convierte un archivo a bytes
     *
     * @param file archivo de tipo File
     * @return byte[] bytes del archivo
     * @throws FileNotFoundException
     * @throws IOException
     */
    public byte[] fileToBytes(File file) throws FileNotFoundException, IOException {
        byte[] bytes = null;

        if (file.exists() && file.isFile()) {
            java.io.InputStream is = new java.io.FileInputStream(file);
            long length = file.length();

            bytes = new byte[(int) length];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            if (offset < bytes.length) {
            }
            is.close();
        } else {
            bytes = new byte[0];
        }
        return bytes;
    }

    public static byte[] readIntoByteArray(InputStream in) throws IOException {
        byte[] buffer = new byte[4096];
        int bytesRead;
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        out.flush();

        return out.toByteArray();
    }

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

    public String generaNombreArchivo(UploadedFile uploadedFile, Integer idTarea, Integer idProceso) {
        Date today = new Date();
        String nombreArchivo = idProceso + "_" + idTarea + "_" + today.getTime();
        System.out.println("nombreArchivo---->" + nombreArchivo);
        return nombreArchivo;
    }

    public String guardaObjetoSerializable(Object object, Integer idTarea, Integer idProceso) {
        //List<FalloPropuestaProcDTO> listaFalloPropProc
        String nombreArchivo = this.generaNombreArchivo(null, idTarea, idProceso);
        try {
            FileOutputStream out = new FileOutputStream(PATHFILESFALLOS + nombreArchivo);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(object);
            oos.flush();
        } catch (Exception e) {
            Logger.getLogger(ArchivosUtilidades.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        return nombreArchivo;
    }

    public Object obtieneObjetoSerializable(String nombreArchivo) {
        try {
            FileInputStream in = new FileInputStream(PATHFILESFALLOS + nombreArchivo);
            ObjectInputStream ois = new ObjectInputStream(in);
            return ois.readObject();
        } catch (Exception e) {
            Logger.getLogger(ArchivosUtilidades.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public String guardaObjetoSerializableClausulado(Object object, Integer idTarea, Integer idProceso, String ruta) {
        //List<FalloPropuestaProcDTO> listaFalloPropProc
        String nombreArchivo = "";
        if (ruta.equals("")) {
            ruta = PATHFILESCLAUSULAS;
            if (idTarea == 6) {
                nombreArchivo = NAMEFILECLAUSULACONVENIO;
            } else {
                nombreArchivo = NAMEFILECLAUSULACONTRATO;
            }
        } else {
            nombreArchivo = this.generaNombreArchivo(null, idTarea, idProceso);
            ruta = PATHFILESCLAUSULASCONTRATO;
        }
        try {
            FileOutputStream out = new FileOutputStream(ruta + nombreArchivo);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(object);
            oos.flush();
        } catch (Exception e) {
            Logger.getLogger(ArchivosUtilidades.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        return nombreArchivo;
    }

    public Object obtieneObjetoSerializableClausulado(String nombreArchivo, String ruta) {
        try {
            FileInputStream in = new FileInputStream(ruta + nombreArchivo);
            ObjectInputStream ois = new ObjectInputStream(in);
            return ois.readObject();
        } catch (Exception e) {
            Logger.getLogger(ArchivosUtilidades.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public List<PropuestasArchivoDTO> leerExcelPropuestas(UploadedFile uploadedFile, String nombreArchivo, List<ProcedimientoRcb> listaProcRcb) {
        FileInputStream file = null;
        int fila = 1;
        int columna = 1;
        String claveAnterior = "";
        PropuestasArchivoDTO propuestasDTO = null;
        List<PropuestasArchivoDTO> listaPropuestasDTO = new ArrayList();
        ProcedimientoRcb procedimientoRcb = null;
        Double d = null;
        boolean bandera = true;
        boolean banderaAgrega = true;
        String clavesMal = "";
        int numClavesBien = 0;
        String clavesBien = "";
        int numClavesMal = 0;
        try {
            copyFilePropuestas(nombreArchivo, uploadedFile.getInputstream());
            file = new FileInputStream(new File(PATHFILESPROPUESTAS + nombreArchivo));
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row;
            y:
            while (rowIterator.hasNext()) {
                propuestasDTO = new PropuestasArchivoDTO();
                banderaAgrega = true;
                if (fila == 1) {
                    row = rowIterator.next();
                    fila++;
                }
                row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell celda;
                columna = 1;
                d = null;
                z:
                while (cellIterator.hasNext()) {
                    celda = cellIterator.next();
                    switch (columna) {
                        case 1:
                            if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                d = celda.getNumericCellValue();
                                if (d.intValue() == 0) {
                                    break y;
                                }
                            }
                            break;
                        case 2:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                propuestasDTO.setClave(celda.getStringCellValue().replace(".", "").trim());
                                if (claveAnterior.equals(propuestasDTO.getClave())) {
                                    if (!bandera) {
                                        banderaAgrega = false;
                                        numClavesMal++;
                                        break z;
                                    } else {
                                        propuestasDTO.setProcedimientoRcb(procedimientoRcb);
                                        numClavesBien++;
                                    }
                                } else {
                                    bandera = false;
                                    claveAnterior = propuestasDTO.getClave();
                                    for (ProcedimientoRcb pr : listaProcRcb) {
                                        if (pr.getIdRcbInsumos().getClaveInsumo().equals(claveAnterior)) {
                                            propuestasDTO.setProcedimientoRcb(pr);
                                            procedimientoRcb = pr;
                                            bandera = true;
                                            if (clavesBien.equals("")) {
                                                clavesBien = claveAnterior;
                                            } else {
                                                clavesBien = clavesBien + ", " + claveAnterior;
                                            }
                                            numClavesBien++;
                                            break;
                                        }
                                    }
                                    if (!bandera) {
                                        if (clavesMal.equals("")) {
                                            clavesMal = claveAnterior;
                                        } else {
                                            clavesMal = clavesMal + ", " + claveAnterior;
                                        }
                                        numClavesMal++;
                                        banderaAgrega = false;
                                        break z;
                                    }
                                }
                            }
                            break;
                        case 3:
                            if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                d = celda.getNumericCellValue();
                                propuestasDTO.setCantidad(d.intValue());
                            }
                            break;
                        case 4:
                            if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                d = celda.getNumericCellValue();
                                propuestasDTO.setPrecioRef(new BigDecimal(d, MathContext.DECIMAL64));
                            }
                            break;
                        case 5:
                            if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                d = celda.getNumericCellValue();
                                propuestasDTO.setPrecioSinIva(new BigDecimal(d, MathContext.DECIMAL64));
                            }
                            break;
                        case 6:
                            if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                d = celda.getNumericCellValue();
                                propuestasDTO.setIva(new BigDecimal(d, MathContext.DECIMAL64));
                            }
                            break;
                        case 7:
                            if (celda.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                d = celda.getNumericCellValue();
                                propuestasDTO.setPrecioDesc(new BigDecimal(d, MathContext.DECIMAL64));
                            }
                            break;
                        case 8:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                propuestasDTO.setNombreProveedor(celda.getStringCellValue());
                            }
                            break;
                        case 9:
                            if (celda.getCellType() == Cell.CELL_TYPE_STRING) {
                                propuestasDTO.setTipoProveedor(celda.getStringCellValue());
                            }
                            break;
                    }
                    columna++;
                }
                if (banderaAgrega) {
                    listaPropuestasDTO.add(propuestasDTO);
                }
                fila++;
            }
            if (listaPropuestasDTO.size() > 0) {
                propuestasDTO = listaPropuestasDTO.get(0);
                propuestasDTO.setClavesMal(clavesMal);
                propuestasDTO.setNumClavesMal(numClavesMal);
                propuestasDTO.setClavesBien(clavesBien);
                propuestasDTO.setNumClavesBien(numClavesBien);
                listaPropuestasDTO.set(0, propuestasDTO);
            }
            file.close();
        } catch (IOException ex) {
            System.out.println("entre en error ");
            Logger.getLogger(ArchivosUtilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPropuestasDTO;
    }
    
}
