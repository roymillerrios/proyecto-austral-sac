package com.g5.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
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
	
	private String marca;
		
	private UnidadMedida unidadMedida;
	
	private Tipo tipo;
	
	private 
	
	private static final long serialVersionUID = 1L;
	

}
