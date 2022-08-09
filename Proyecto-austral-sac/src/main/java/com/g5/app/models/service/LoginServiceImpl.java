package com.g5.app.models.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.g5.app.models.dao.IUsuarioDao;
import com.g5.app.models.entity.Rol;
import com.g5.app.models.entity.Usuario;

@Service
public class LoginServiceImpl implements UserDetailsService{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByEmail(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Email o Contraseña inválidos");
        }
        return new User(usuario.getEmail(),usuario.getContraseña(), mapearAutoridadesRoles(usuario.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Set<Rol> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getDescripcion())).collect(Collectors.toList());
    }

}
