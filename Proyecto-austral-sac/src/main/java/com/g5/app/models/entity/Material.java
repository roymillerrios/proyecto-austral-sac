package com.g5.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="unidad_medida_id")
	@JsonIgnore
	private UnidadMedida unidadMedida;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tipo_id")
	@JsonIgnore
	private Tipo tipo;

	@OneToMany(mappedBy = "material", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ItemTrabajador> items;

	@OneToMany(mappedBy = "material", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ItemInventario> itemsInventario;
	
	public Material() {
		this.items=new ArrayList<ItemTrabajador>();
		this.itemsInventario = new ArrayList<ItemInventario>();
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


	public List<ItemTrabajador> getItems() {
		return items;
	}

	public void setItems(List<ItemTrabajador> items) {
		this.items = items;
	}

	public List<ItemInventario> getItemsInventario() {
		return itemsInventario;
	}

	public void setItemsInventario(List<ItemInventario> itemsInventario) {
		this.itemsInventario = itemsInventario;
	}

	private static final long serialVersionUID = 1L;
}
