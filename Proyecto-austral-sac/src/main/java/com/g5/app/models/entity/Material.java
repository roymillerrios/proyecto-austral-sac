package com.g5.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="materiales")
public class Material implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1,max=50,message="El nombre debe medir entre 1 y 20 de caracteres de largo")
	private String nombre;
	
	@NotEmpty
	private String marca;
	
	@NotNull
	private Double cantidad;
		
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="unidad_medida_id")
	private UnidadMedida unidadMedida;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tipo_id")
	private Tipo tipo;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Inventario inventario;
	
	@ManyToMany(mappedBy="materiales",fetch = FetchType.LAZY, 
			cascade= {CascadeType.PERSIST,CascadeType.MERGE})
	@JsonIgnore
	private List<Trabajador> trabajadores;
	
	public Material() {
		
	}
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getMarca() {
		return marca;
	}



	public void setMarca(String marca) {
		this.marca = marca;
	}



	public Double getCantidad() {
		return cantidad;
	}



	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}



	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}


	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}


	public Tipo getTipo() {
		return tipo;
	}


	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Inventario getInventario() {
		return inventario;
	}


	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}


	public List<Trabajador> getTrabajadores() {
		return trabajadores;
	}


	public void setTrabajadores(List<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}


	private static final long serialVersionUID = 1L;
}
