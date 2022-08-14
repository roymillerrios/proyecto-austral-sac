package com.g5.app.models.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.g5.app.models.dao.ITrabajadorDao;
import com.g5.app.models.dao.UsuarioDetails;
import com.g5.app.models.entity.Trabajador;

@Service
public class LoginServiceImpl implements UserDetailsService{

	@Autowired
	private ITrabajadorDao trabajadorDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Trabajador trabajador = trabajadorDao.findByEmail(username);
        if(trabajador == null) {
            throw new UsernameNotFoundException("Email o Contraseña inválidos");
        }
        return new UsuarioDetails(trabajador);
	}
	
}
