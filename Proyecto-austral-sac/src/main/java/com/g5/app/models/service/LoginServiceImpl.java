package com.g5.app.models.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.g5.app.models.dao.IUsuarioDao;
import com.g5.app.models.dao.UsuarioDetails;
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
        return new UsuarioDetails(usuario);
	}
	
}
