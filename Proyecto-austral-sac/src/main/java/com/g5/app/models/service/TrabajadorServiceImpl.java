package com.g5.app.models.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.g5.app.models.entity.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.g5.app.models.entity.Trabajador;
import com.g5.app.models.dao.ITrabajadorDao;

@Service
public class TrabajadorServiceImpl implements ITrabajadorService {

	@Autowired
	private ITrabajadorDao trabajadorDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Trabajador> findAll() {
		// TODO Auto-generated method stub
		return (List<Trabajador>) trabajadorDao.findAll();
	}

	@Override
	@Transactional
	public void save(Trabajador trabajador) {
		trabajadorDao.save(trabajador);

	}

	@Override
	@Transactional(readOnly = true)
	public Trabajador findOne(Long id) {
		// TODO Auto-generated method stub
		return trabajadorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		trabajadorDao.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Page<Trabajador> findAll(Pageable pageable) {
		return trabajadorDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Trabajador findByEmail(String email){
		return trabajadorDao.findByEmail(email);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Trabajador trabajador = trabajadorDao.findByEmail(username);
		if(trabajador == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(trabajador.getEmail(),trabajador.getPassword(), mapearAutoridadesRoles(trabajador.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(List<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getDescripcion())).collect(Collectors.toList());
	}
}
