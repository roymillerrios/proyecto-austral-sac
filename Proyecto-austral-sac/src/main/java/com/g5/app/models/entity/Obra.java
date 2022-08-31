package com.g5.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@OneToMany(mappedBy = "obra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ItemsObra> items;
	
	
	public Obra(){
		this.items = new ArrayList<ItemsObra>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void addItem(ItemsObra items) {
		this.items.add(items);
	}

	public List<ItemsObra> getItems() {
		return items;
	}

	public void setItems(List<ItemsObra> items) {
		this.items = items;
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
	
	private static final long serialVersionUID = 1L;
}
