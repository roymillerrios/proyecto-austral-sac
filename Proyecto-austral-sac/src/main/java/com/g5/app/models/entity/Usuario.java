package com.g5.app.models.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="login")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idTrabajador")
	private Long id;
	
	@Column
	@NotEmpty
	private String email;
	
	@Column
	@NotEmpty
	private String contraseña;
	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name="rol_login",
			joinColumns = @JoinColumn(name="idTrabajador"),
			inverseJoinColumns = @JoinColumn(name="idRol")
			)
	private Set<Rol> roles = new HashSet<>();
	
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario")
	@JoinColumn(name = "idTrabajador", nullable = false)
	private Trabajador trabajador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContraseña() {
		
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	
	

}
