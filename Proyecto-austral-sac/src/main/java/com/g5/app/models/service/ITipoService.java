package com.g5.app.models.service;

import java.util.List;

import com.g5.app.models.entity.Tipo;

public interface ITipoService {
	
	public List<Tipo> findAll();

	public void save(Tipo tipo);
	
	public Tipo findOne(Long id);
	
	public void delete(Long id);

}
