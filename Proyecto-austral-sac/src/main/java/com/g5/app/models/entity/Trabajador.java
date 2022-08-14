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

	private long id;
	
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, optional = false, mappedBy = "trabajador")
    @PrimaryKeyJoinColumn(referencedColumnName = "id")
    private Usuario usuario;
	
	@NotEmpty
	private String DNI;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaIngreso;
	
	@NotEmpty
	private String nombreCompleto;
	
	@NotEmpty
	private String apellidoMaterno;
	
	@NotEmpty
	private String apellidoPaterno;
	
	private String edad;
	
	private String sexo;
	
	private String celular;
	
	@NotEmpty
	private String examenSeguridad;
	
	@Lob
	private String SCTRPension;
	
	@Lob
	private String SCTRSalud;
	
	@Lob
	private String antPenales;
	
	@Lob
	private String antPoliciales;
	
	private String foto;

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
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getExamenSeguridad() {
		return examenSeguridad;
	}

	public void setExamenSeguridad(String examenSeguridad) {
		this.examenSeguridad = examenSeguridad;
	}

	public String getSCTRPension() {
		return SCTRPension;
	}

	public void setSCTRPension(String sCTRPension) {
		SCTRPension = sCTRPension;
	}

	public String getSCTRSalud() {
		return SCTRSalud;
	}

	public void setSCTRSalud(String sCTRSalud) {
		SCTRSalud = sCTRSalud;
	}

	public String getAntPenales() {
		return antPenales;
	}

	public void setAntPenales(String antPenales) {
		this.antPenales = antPenales;
	}

	public String getAntPoliciales() {
		return antPoliciales;
	}

	public void setAntPoliciales(String antPoliciales) {
		this.antPoliciales = antPoliciales;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	

	
	
}