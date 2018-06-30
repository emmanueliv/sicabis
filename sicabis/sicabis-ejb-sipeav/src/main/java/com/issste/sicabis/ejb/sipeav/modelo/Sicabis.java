/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.issste.sicabis.ejb.sipeav.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Manolo
 */
@Entity
@Table(name = "sicabis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sicabis.findAll", query = "SELECT s FROM Sicabis s"),
    @NamedQuery(name = "Sicabis.findByNumIssste", query = "SELECT s FROM Sicabis s WHERE s.numIssste = :numIssste"),
    @NamedQuery(name = "Sicabis.findByRfcTra", query = "SELECT s FROM Sicabis s WHERE s.rfcTra = :rfcTra"),
    @NamedQuery(name = "Sicabis.findByCurpTra", query = "SELECT s FROM Sicabis s WHERE s.curpTra = :curpTra"),
    @NamedQuery(name = "Sicabis.findByTipoDh", query = "SELECT s FROM Sicabis s WHERE s.tipoDh = :tipoDh"),
    @NamedQuery(name = "Sicabis.findByApaTra", query = "SELECT s FROM Sicabis s WHERE s.apaTra = :apaTra"),
    @NamedQuery(name = "Sicabis.findByAmaTra", query = "SELECT s FROM Sicabis s WHERE s.amaTra = :amaTra"),
    @NamedQuery(name = "Sicabis.findByNomTra", query = "SELECT s FROM Sicabis s WHERE s.nomTra = :nomTra"),
    @NamedQuery(name = "Sicabis.findByCalleTra", query = "SELECT s FROM Sicabis s WHERE s.calleTra = :calleTra"),
    @NamedQuery(name = "Sicabis.findByNumExtTra", query = "SELECT s FROM Sicabis s WHERE s.numExtTra = :numExtTra"),
    @NamedQuery(name = "Sicabis.findByCodigoPostal", query = "SELECT s FROM Sicabis s WHERE s.codigoPostal = :codigoPostal"),
    @NamedQuery(name = "Sicabis.findByColonia", query = "SELECT s FROM Sicabis s WHERE s.colonia = :colonia"),
    @NamedQuery(name = "Sicabis.findByCveCol", query = "SELECT s FROM Sicabis s WHERE s.cveCol = :cveCol"),
    @NamedQuery(name = "Sicabis.findByPoblaTra", query = "SELECT s FROM Sicabis s WHERE s.poblaTra = :poblaTra"),
    @NamedQuery(name = "Sicabis.findByCveClin", query = "SELECT s FROM Sicabis s WHERE s.cveClin = :cveClin"),
    @NamedQuery(name = "Sicabis.findByNomClin", query = "SELECT s FROM Sicabis s WHERE s.nomClin = :nomClin"),
    @NamedQuery(name = "Sicabis.findByDtoEstado", query = "SELECT s FROM Sicabis s WHERE s.dtoEstado = :dtoEstado"),
    @NamedQuery(name = "Sicabis.findByClinicaEdo", query = "SELECT s FROM Sicabis s WHERE s.clinicaEdo = :clinicaEdo"),
    @NamedQuery(name = "Sicabis.findByNomMupio", query = "SELECT s FROM Sicabis s WHERE s.nomMupio = :nomMupio"),
    @NamedQuery(name = "Sicabis.findByFechaBajaTra", query = "SELECT s FROM Sicabis s WHERE s.fechaBajaTra = :fechaBajaTra"),
    @NamedQuery(name = "Sicabis.findByItoId", query = "SELECT s FROM Sicabis s WHERE s.itoId = :itoId"),
    @NamedQuery(name = "Sicabis.findByCurp", query = "SELECT s FROM Sicabis s WHERE s.curp = :curp"),
    @NamedQuery(name = "Sicabis.findByApellidoPaterno", query = "SELECT s FROM Sicabis s WHERE s.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "Sicabis.findByApellidoMaterno", query = "SELECT s FROM Sicabis s WHERE s.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "Sicabis.findByNombre", query = "SELECT s FROM Sicabis s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Sicabis.findByParentescoCve", query = "SELECT s FROM Sicabis s WHERE s.parentescoCve = :parentescoCve"),
    @NamedQuery(name = "Sicabis.findByItoEstado", query = "SELECT s FROM Sicabis s WHERE s.itoEstado = :itoEstado"),
    @NamedQuery(name = "Sicabis.findByCodPost", query = "SELECT s FROM Sicabis s WHERE s.codPost = :codPost"),
    @NamedQuery(name = "Sicabis.findByCalleBen", query = "SELECT s FROM Sicabis s WHERE s.calleBen = :calleBen"),
    @NamedQuery(name = "Sicabis.findByPoblaBen", query = "SELECT s FROM Sicabis s WHERE s.poblaBen = :poblaBen"),
    @NamedQuery(name = "Sicabis.findByNcoCveBen", query = "SELECT s FROM Sicabis s WHERE s.ncoCveBen = :ncoCveBen"),
    @NamedQuery(name = "Sicabis.findByNomColBen", query = "SELECT s FROM Sicabis s WHERE s.nomColBen = :nomColBen"),
    @NamedQuery(name = "Sicabis.findByCliCve", query = "SELECT s FROM Sicabis s WHERE s.cliCve = :cliCve"),
    @NamedQuery(name = "Sicabis.findByNumExtBen", query = "SELECT s FROM Sicabis s WHERE s.numExtBen = :numExtBen"),
    @NamedQuery(name = "Sicabis.findByNomMupioBen", query = "SELECT s FROM Sicabis s WHERE s.nomMupioBen = :nomMupioBen"),
    @NamedQuery(name = "Sicabis.findByNombreCli", query = "SELECT s FROM Sicabis s WHERE s.nombreCli = :nombreCli"),
    @NamedQuery(name = "Sicabis.findByFechaTerminoBen", query = "SELECT s FROM Sicabis s WHERE s.fechaTerminoBen = :fechaTerminoBen")})
public class Sicabis implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "num_issste")
    private BigDecimal numIssste;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "rfc_tra")
    private String rfcTra;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "curp_tra")
    private String curpTra;
    @Size(max = 2)
    @Column(name = "tipo_dh")
    private String tipoDh;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "apa_tra")
    private String apaTra;
    @Size(max = 40)
    @Column(name = "ama_tra")
    private String amaTra;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nom_tra")
    private String nomTra;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "calle_tra")
    private String calleTra;
    @Size(max = 5)
    @Column(name = "num_ext_tra")
    private String numExtTra;
    @Column(name = "codigo_postal")
    private BigDecimal codigoPostal;
    @Size(max = 60)
    @Column(name = "colonia")
    private String colonia;
    @Column(name = "cve_col")
    private BigDecimal cveCol;
    @Size(max = 45)
    @Column(name = "pobla_tra")
    private String poblaTra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cve_clin")
    private BigDecimal cveClin;
    @Size(max = 60)
    @Column(name = "nom_clin")
    private String nomClin;
    @Column(name = "dto_estado")
    private Character dtoEstado;
    @Column(name = "clinica_edo")
    private Character clinicaEdo;
    @Size(max = 60)
    @Column(name = "nom_mupio")
    private String nomMupio;
    @Column(name = "fecha_baja_tra")
    @Temporal(TemporalType.DATE)
    private Date fechaBajaTra;
    @Column(name = "ito_id")
    private Double itoId;
    @Size(max = 18)
    @Column(name = "curp")
    private String curp;
    @Size(max = 40)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Size(max = 40)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Size(max = 40)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "parentesco_cve")
    private BigDecimal parentescoCve;
    @Size(max = 2)
    @Column(name = "ito_estado")
    private String itoEstado;
    @Column(name = "cod_post")
    private BigDecimal codPost;
    @Size(max = 45)
    @Column(name = "calle_ben")
    private String calleBen;
    @Size(max = 45)
    @Column(name = "pobla_ben")
    private String poblaBen;
    @Column(name = "nco_cve_ben")
    private BigDecimal ncoCveBen;
    @Size(max = 60)
    @Column(name = "nom_col_ben")
    private String nomColBen;
    @Column(name = "cli_cve")
    private BigDecimal cliCve;
    @Size(max = 5)
    @Column(name = "num_ext_ben")
    private String numExtBen;
    @Size(max = 60)
    @Column(name = "nom_mupio_ben")
    private String nomMupioBen;
    @Size(max = 60)
    @Column(name = "nombre_cli")
    private String nombreCli;
    @Column(name = "fecha_termino_ben")
    @Temporal(TemporalType.DATE)
    private Date fechaTerminoBen;

    public Sicabis() {
    }

    public BigDecimal getNumIssste() {
        return numIssste;
    }

    public void setNumIssste(BigDecimal numIssste) {
        this.numIssste = numIssste;
    }

    public String getRfcTra() {
        return rfcTra;
    }

    public void setRfcTra(String rfcTra) {
        this.rfcTra = rfcTra;
    }

    public String getCurpTra() {
        return curpTra;
    }

    public void setCurpTra(String curpTra) {
        this.curpTra = curpTra;
    }

    public String getTipoDh() {
        return tipoDh;
    }

    public void setTipoDh(String tipoDh) {
        this.tipoDh = tipoDh;
    }

    public String getApaTra() {
        return apaTra;
    }

    public void setApaTra(String apaTra) {
        this.apaTra = apaTra;
    }

    public String getAmaTra() {
        return amaTra;
    }

    public void setAmaTra(String amaTra) {
        this.amaTra = amaTra;
    }

    public String getNomTra() {
        return nomTra;
    }

    public void setNomTra(String nomTra) {
        this.nomTra = nomTra;
    }

    public String getCalleTra() {
        return calleTra;
    }

    public void setCalleTra(String calleTra) {
        this.calleTra = calleTra;
    }

    public String getNumExtTra() {
        return numExtTra;
    }

    public void setNumExtTra(String numExtTra) {
        this.numExtTra = numExtTra;
    }

    public BigDecimal getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(BigDecimal codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public BigDecimal getCveCol() {
        return cveCol;
    }

    public void setCveCol(BigDecimal cveCol) {
        this.cveCol = cveCol;
    }

    public String getPoblaTra() {
        return poblaTra;
    }

    public void setPoblaTra(String poblaTra) {
        this.poblaTra = poblaTra;
    }

    public BigDecimal getCveClin() {
        return cveClin;
    }

    public void setCveClin(BigDecimal cveClin) {
        this.cveClin = cveClin;
    }

    public String getNomClin() {
        return nomClin;
    }

    public void setNomClin(String nomClin) {
        this.nomClin = nomClin;
    }

    public Character getDtoEstado() {
        return dtoEstado;
    }

    public void setDtoEstado(Character dtoEstado) {
        this.dtoEstado = dtoEstado;
    }

    public Character getClinicaEdo() {
        return clinicaEdo;
    }

    public void setClinicaEdo(Character clinicaEdo) {
        this.clinicaEdo = clinicaEdo;
    }

    public String getNomMupio() {
        return nomMupio;
    }

    public void setNomMupio(String nomMupio) {
        this.nomMupio = nomMupio;
    }

    public Date getFechaBajaTra() {
        return fechaBajaTra;
    }

    public void setFechaBajaTra(Date fechaBajaTra) {
        this.fechaBajaTra = fechaBajaTra;
    }

    public Double getItoId() {
        return itoId;
    }

    public void setItoId(Double itoId) {
        this.itoId = itoId;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getParentescoCve() {
        return parentescoCve;
    }

    public void setParentescoCve(BigDecimal parentescoCve) {
        this.parentescoCve = parentescoCve;
    }

    public String getItoEstado() {
        return itoEstado;
    }

    public void setItoEstado(String itoEstado) {
        this.itoEstado = itoEstado;
    }

    public BigDecimal getCodPost() {
        return codPost;
    }

    public void setCodPost(BigDecimal codPost) {
        this.codPost = codPost;
    }

    public String getCalleBen() {
        return calleBen;
    }

    public void setCalleBen(String calleBen) {
        this.calleBen = calleBen;
    }

    public String getPoblaBen() {
        return poblaBen;
    }

    public void setPoblaBen(String poblaBen) {
        this.poblaBen = poblaBen;
    }

    public BigDecimal getNcoCveBen() {
        return ncoCveBen;
    }

    public void setNcoCveBen(BigDecimal ncoCveBen) {
        this.ncoCveBen = ncoCveBen;
    }

    public String getNomColBen() {
        return nomColBen;
    }

    public void setNomColBen(String nomColBen) {
        this.nomColBen = nomColBen;
    }

    public BigDecimal getCliCve() {
        return cliCve;
    }

    public void setCliCve(BigDecimal cliCve) {
        this.cliCve = cliCve;
    }

    public String getNumExtBen() {
        return numExtBen;
    }

    public void setNumExtBen(String numExtBen) {
        this.numExtBen = numExtBen;
    }

    public String getNomMupioBen() {
        return nomMupioBen;
    }

    public void setNomMupioBen(String nomMupioBen) {
        this.nomMupioBen = nomMupioBen;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    public Date getFechaTerminoBen() {
        return fechaTerminoBen;
    }

    public void setFechaTerminoBen(Date fechaTerminoBen) {
        this.fechaTerminoBen = fechaTerminoBen;
    }
    
}
