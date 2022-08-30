package com.g5.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="obras")
public class Obra implements Serializable{
	
	@Id
	@GeneratedValue (strategy = GenerationType. IDENTITY)
	private Long id;

	@NotNull
	private String descripcion;

	@Temporal (TemporalType.DATE) 
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date fechaInicio;

	@NotNull
	private String zona;

	@NotNull
	private String estado;

	@NotNull
	private String ubicacion;
	
	/*
	@OnetoMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="obra_trabajador",
			joinColumns = {@JoinColumn(name="obra_id")},
			inverseJoinColumns = {@JoinColumn(name="trabajador_id")})
	private List<Trabajador> trabajadores;
	public Obra(){
		this.obras = new ArrayList<Trabajador>();
	}*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
}
