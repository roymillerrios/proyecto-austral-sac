package com.g5.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.g5.app.models.entity.Trabajador;

public interface ITrabajadorService {

	public List<Trabajador> findAll();

	public Page<Trabajador> findAll(Pageable pageable);

	public void save(Trabajador trabajador);

	public Trabajador findOne(Long id);

	public void delete(Long id);
}
