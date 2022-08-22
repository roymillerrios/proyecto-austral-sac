package com.g5.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="trabajadores", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Trabajador implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Pattern(regexp="[0-9]{8}", message="Se permiten solo 8 caracteres numericos")
	private String DNI;
	
	@PastOrPresent
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaIngreso;
	
	@NotNull
	@Size(min= 2,message="Campo requerido")
	private String nombreCompleto;
	
	@NotNull
	private String apellidoMaterno;
	
	@NotNull
	private String apellidoPaterno;
	
	@Min(value=18,message="No se permiten edades menores a 18")
	@Max(value=70,message="No se permiten edades mayores a 70")
	private String edad;
	
	@NotEmpty
	private String sexo;
	
	@Email
	private String email;

	@NotNull
	private String password;
	
	
	
	@Pattern(regexp="[0-9]{9}", message="Se permiten solo 9 caracteres numericos")
	private String celular;
	
	private String foto;
	
	private String antecedentesPenales;
	
	private String antecedentesPoliciales;
	
	private String certificadoDomiciliario;

	private String direccion;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name = "trabajador_rol",
			joinColumns = { @JoinColumn(name = "trabajador_id") },
			inverseJoinColumns = { @JoinColumn(name = "rol_id") })
	private List<Rol> roles;
	
	public Trabajador() {
		this.roles = new ArrayList<Rol>() ;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public void addRol(Rol rol){
		this.roles.add(rol);
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	
	public String getAntecedentesPenales() {
		return antecedentesPenales;
	}

	public void setAntecedentesPenales(String antecedentesPenales) {
		this.antecedentesPenales = antecedentesPenales;
	}

	public String getAntecedentesPoliciales() {
		return antecedentesPoliciales;
	}

	public void setAntecedentesPoliciales(String antecedentesPoliciales) {
		this.antecedentesPoliciales = antecedentesPoliciales;
	}

	public String getCertificadoDomiciliario() {
		return certificadoDomiciliario;
	}

	public void setCertificadoDomiciliario(String certificadoDomiciliario) {
		this.certificadoDomiciliario = certificadoDomiciliario;
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


	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}


	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	private static final long serialVersionUID = 1L;
	
}