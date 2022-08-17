package com.g5.app.models.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.g5.app.models.entity.Rol;
import com.g5.app.models.entity.Trabajador;

public class UsuarioDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Trabajador trabajador;
	
	
	public UsuarioDetails(Trabajador trabajador) {
		super();
		this.trabajador = trabajador;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Rol> roles = trabajador.getRoles();
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Rol rol: roles) {
			authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return trabajador.getPassword();
	}

	@Override
	public String getUsername() {
		return trabajador.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getNombres() {
		return this.trabajador.getApellidoPaterno() + " " +
				this.trabajador.getApellidoMaterno() + " " +
				this.trabajador.getNombreCompleto();
	}
	

}
