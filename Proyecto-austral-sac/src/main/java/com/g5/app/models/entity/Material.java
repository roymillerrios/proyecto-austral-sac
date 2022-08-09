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

@Entity
@Table(name="materiales")
public class Material implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min=1,max=8,message="El codigo debe medir entre 1 y 8 ")
	private String codigo;
	
	@NotEmpty
	@Size(min=1,max=50,message="El nombre debe medir entre 1 y 20 de largo")
	private String nombre;
	
	@NotEmpty
	private String marca;
	
	@NotNull
	private Double cantidad;
		
	@NotEmpty
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="unidad_medida_id")
	private UnidadMedida unidadMedida;
	
	@NotEmpty
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tipo_id")
	private Tipo tipo;
	
	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY,
		cascade= {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="material_inventario",
		joinColumns= {@JoinColumn(name="material_id")},
		inverseJoinColumns = {@JoinColumn(name="inventario_id")})
	private List<Inventario> inventarios;
	
	public Material() {
		this.inventarios = new ArrayList<Inventario>() ;
	}
	
	private static final long serialVersionUID = 1L;
}
