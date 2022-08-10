package com.g5.app.models.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.g5.app.models.entity.Rol;
import com.g5.app.models.entity.Usuario;

public class UsuarioDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	
	
	public UsuarioDetails(Usuario usuario) {
		super();
		this.usuario = usuario;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Rol> roles = usuario.getRoles();
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Rol rol: roles) {
			authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return usuario.getContraseña();
	}

	@Override
	public String getUsername() {
		return usuario.getEmail();
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
		return this.usuario.getTrabajador().getApellidoPaterno() + " " +
				this.usuario.getTrabajador().getApellidoMaterno() + " " +
				this.usuario.getTrabajador().getNombreCompleto();
	}
	

}
