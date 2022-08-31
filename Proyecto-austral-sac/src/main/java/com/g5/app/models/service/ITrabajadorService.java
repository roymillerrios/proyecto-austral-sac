package com.g5.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.g5.app.models.entity.Material;
import com.g5.app.models.entity.Trabajador;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface ITrabajadorService extends UserDetailsService {

	public List<Trabajador> findAll();

	public Page<Trabajador> findAll(Pageable pageable);

	public void save(Trabajador trabajador);

	public Trabajador findOne(Long id);

	public Trabajador findByEmail(String email);

	public void delete(Long id);
	
	public List<Trabajador> findByNombreCompleto(String term);

    public Trabajador findByID(Long id);

}
