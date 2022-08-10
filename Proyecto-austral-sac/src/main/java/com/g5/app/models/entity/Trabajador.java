package com.g5.app.models.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="trabajador")
public class Trabajador {

	
	@Id
	@Column(name="idTrabajador")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, optional = false, mappedBy = "trabajador")
    @PrimaryKeyJoinColumn(referencedColumnName = "id")
    private Usuario usuario;
	
	@NotEmpty
	@Column(name = "DNI")
	private String DNI;
	
	@NotNull
	@Column(name = "FechaIngreso")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date FechaIngreso;
	
	@NotEmpty
	@Column(name = "NombreCompleto")
	private String NombreCompleto;
	
	@NotEmpty
	@Column(name = "ApellidoMaterno")
	private String ApellidoMaterno;
	
	@NotEmpty
	@Column(name = "ApellidoPaterno")
	private String ApellidoPaterno;
	
	@NotEmpty
	@Column (name = "ExamenSeguridad")
	private String ExamenSeguridad;
	
	@Lob
	@Column(name = "SCTRPension")
	private byte[] SCTRPension;
	
	@Lob
	@Column(name = "SCTRSalud")
	private byte[] SCTRSalud;
	
	@Lob
	@Column(name = "AntPenales")
	private byte[] AntPenales;
	
	@Lob
	@Column(name = "AntPoliciales")
	private byte[] AntPoliciales;
	
	@Lob
	@Column(name = "CertDomiciliario")
	private byte[] CertDomiciliario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public Date getFechaIngreso() {
		return FechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		FechaIngreso = fechaIngreso;
	}

	public String getNombreCompleto() {
		return NombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		NombreCompleto = nombreCompleto;
	}

	public String getApellidoMaterno() {
		return ApellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		ApellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return ApellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		ApellidoPaterno = apellidoPaterno;
	}

	public String getExamenSeguridad() {
		return ExamenSeguridad;
	}

	public void setExamenSeguridad(String examenSeguridad) {
		ExamenSeguridad = examenSeguridad;
	}

	public byte[] getSCTRPension() {
		return SCTRPension;
	}

	public void setSCTRPension(byte[] sCTRPension) {
		SCTRPension = sCTRPension;
	}

	public byte[] getSCTRSalud() {
		return SCTRSalud;
	}

	public void setSCTRSalud(byte[] sCTRSalud) {
		SCTRSalud = sCTRSalud;
	}

	public byte[] getAntPenales() {
		return AntPenales;
	}

	public void setAntPenales(byte[] antPenales) {
		AntPenales = antPenales;
	}

	public byte[] getAntPoliciales() {
		return AntPoliciales;
	}

	public void setAntPoliciales(byte[] antPoliciales) {
		AntPoliciales = antPoliciales;
	}

	public byte[] getCertDomiciliario() {
		return CertDomiciliario;
	}

	public void setCertDomiciliario(byte[] certDomiciliario) {
		CertDomiciliario = certDomiciliario;
	}
	
	
}